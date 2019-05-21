﻿<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.centit.support.utils.DatetimeOpt"%>
<%@page import="org.springframework.security.core.context.SecurityContext"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="com.centit.core.dao.HQLUtils"%>
<%@page import="DBstep.iMsgServer2000"%>
<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,java.util.Date,javax.servlet.*,javax.servlet.http.*" %><!-- ,com.centit.support.utils.NumberBaseOpt -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/page/common/css.jsp"%>
<title>文档在线编辑</title>
<%
  ResultSet result=null;
  String mSubject=null;
  String mStatus=null;
  String mFileName=null;
  String mFileDate=null;
  String mHTMLPath="";
  String mDisabled="";
  String mDisabledSave="";
  String mWord="";
  String mExcel="";
 
  //流程步骤或者模块简写(保留属性，以后将逐渐取消，或者用flowPhase代替)
  String flowStep = (String)request.getParameter("flowStep");
  flowStep = (StringUtils.isBlank(flowStep)? "" : flowStep);
  
  /**
   * 格式文书类别archiveType：<br>
   * ************************************************<br>
   * 登记—— 01: 申请表<br>
   * 受理—— 02: 受理通知书、 03: 补正通知书<br>
   * 审核（第二步）—— 04: 审核意见书、 05: 审核意见书附表  <br>
   * 审查（上报后的第一步）—— 06: 审查意见书<br>    
   * 发证—— 07: 许可决定书<br>
   * ************************************************ <br> 
   */
  String archiveType = request.getParameter("archiveType");
  /**
  *文号规则 
  */
  String codeCode = request.getParameter("codeCode");
   /**
  *机构代码 
  */
  String primaryUnit = request.getParameter("primaryUnit");
   
  /**
   *文号规则 
   */
   String nodeCode = request.getParameter("nodeCode");
  
  //自动获取OfficeServer和OCX文件完整URL路径
  String mHttpUrlName=request.getRequestURI();
  String mScriptName=request.getServletPath();
  
  String mServerName="";
  String serverURI = "";
  String mServerUrl= "";
  String mHttpUrl = "";
  
//   String mServerName="/OfficeServer?fileType=" + fileType + "&archiveType="+archiveType+"&flowStep="+flowStep+"&codeCode="+codeCode+"&primaryUnit="+primaryUnit;
//   String serverURI = "http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
//   String mServerUrl= serverURI + mServerName;//取得OfficeServer文件的完整URL
//   String mHttpUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
  
  String mRecordID=request.getParameter("RecordID");
  /*
  *模版编号
  */
  String mTemplate=request.getParameter("Template");
  
  //强行制定.doc类型
  String mFileType=".doc";
  String mEditType=request.getParameter("EditType");
  String mShowType=request.getParameter("ShowType");
  String mNeedBookMark=request.getParameter("NeedBookMark");

  //设置编号初始值
  if (mRecordID==null){
     mRecordID="";
  }

  //设置编辑状态初始值
  if (mEditType==null || mEditType==""){
    mEditType="1,1";
  }

  //设置显示模式初始值
  if (mShowType==null || mShowType==""){
    mShowType="1";
  }

  //设置文档类型初始值
  if (mFileType==null || mFileType==""){
    mFileType=".doc";
  }

  //设置模板初始值
  if (mTemplate==null || mTemplate.trim().equals("")){
    mTemplate="OLD_DOC";//领导要求默认OLD_DOC
  }

  String fileName = "";
  //打开数据库
  iDBManager2000 DbaObj=new iDBManager2000();
  if (DbaObj.OpenConnection()){
	  
	//参数描述 ：fileType 文件分类-默认1 ---- 1：格式文书 2：申请附件 3：办理附件 4：办理附件 7:正文
	  String fileType = "7";//之前是1
	   String TemplateSql = "select datadesc from f_datadictionary where catalogcode='TEMPLATE_TYPE' and datacode=" + HQLUtils.buildHqlStringForSQL(archiveType);
	 /* result = DbaObj.ExecuteQuery(TemplateSql);
	  if (result.next()) {
		  fileType = result.getString("datadesc");
	  } */
	  mServerName="OfficeServer?fileType=" + fileType + "&archiveType="+archiveType+"&flowStep="+flowStep+"&codeCode="+codeCode+"&primaryUnit="+primaryUnit;
	  serverURI = "http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
	  mServerUrl= serverURI + mServerName;//取得OfficeServer文件的完整URL
	  mHttpUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
	  
	  
    String mSql="Select * From Document Where RecordID="+ HQLUtils.buildHqlStringForSQL(mRecordID);
    
    if(mTemplate.length() != 0){
    	mSql += " and TEMPLATE="+HQLUtils.buildHqlStringForSQL(mTemplate);
    }
    
    try{
    	
      result=DbaObj.ExecuteQuery(mSql);
      if (result.next()){
        mRecordID=result.getString("RecordID");
        mTemplate=result.getString("Template");
        mSubject=result.getString("Subject");
        mFileDate=result.getString("FileDate");
        mStatus=result.getString("Status");
        //mx 2012-7-25 强行制定.doc类型
        //mFileType=result.getString("FileType");
        mFileType=".doc";
        mHTMLPath=result.getString("HTMLPath");
      }
      else{
        //取得唯一值(mRecordID)
        //java.util.Date dt=new java.util.Date();
       // long lg=dt.getTime();
       // Long ld=new Long(lg);
        //初始化值
       // mRecordID=ld.toString();//保存的是文档的编号，通过该编号，可以在里找到所有属于这条纪录的文档
        mTemplate=mTemplate;
        mSubject="请输入主题";
        mFileDate=DbaObj.GetDateTime();
        mStatus="DERF";
        mFileType=mFileType;
        mHTMLPath="";
      }

    // modify 2015-12-03 oa 文书标题改成和发文标题一致的文件名称
    
   
      TemplateSql = "SELECT FILENAME FROM OPT_STUFF_INFO WHERE DJ_ID=" + HQLUtils.buildHqlStringForSQL(mRecordID) + " and ARCHIVETYPE=" +HQLUtils.buildHqlStringForSQL(archiveType);
      result = DbaObj.ExecuteQuery(TemplateSql);
      if (result.next()) {
    		  fileName = result.getString("FILENAME") ;
      }
       else{
    	   TemplateSql = "SELECT FILETYPE,FILENAME,TEMPTYPE,DESCRIPT FROM Template_File WHERE RECORDID=" + HQLUtils.buildHqlStringForSQL(mTemplate);
    	      result = DbaObj.ExecuteQuery(TemplateSql);
    	      if (result.next()) {
    	    		  fileName = result.getString("DESCRIPT") + result.getString("FILETYPE");
    	      }
    	       else{
    	      		fileName = "正文_"+ mRecordID +"_"+ mTemplate+".doc";
    	      } 
      }
      result.close();
    }
    
    catch(SQLException e){
      System.out.println(e.toString());
    }
    DbaObj.CloseConnection() ;
  }

  if (mEditType=="0,0"){
    mDisabled="disabled";
    mDisabledSave="disabled";
  }
  else{
    mDisabled="";
  }

  mFileName=mRecordID + mFileType;  //取得完整的文档名称
  if (mFileType.equalsIgnoreCase(".doc") || mFileType.equalsIgnoreCase(".wps")){
    mWord="";
    mExcel="disabled";
  }
  else if (mFileType==".xls"){
    mWord="disabled";
    mExcel="";
  }
  else{
    mDisabled="disabled";
  }
  
%>
<script type="text/javascript">

	var step = '<%=flowStep%>';
	var ndCode = '<%=nodeCode%>';
	var mTemplate = '<%=mTemplate%>';
	

	//父页面文档对象
	var parentDocument = window.opener.document;
	
	var flowInstId = _getParentElementValue("flowInstId");
	var nodeInstId = _getParentElementValue("nodeInstId");

	/*TODO最好直接做成保存清稿版本 */
	function clearRevisions(){
		webform.WebOffice.ClearRevisions();
	}
	
	function savePDFDocument(){
		if(!webform.WebOffice.WebSavePDF()){
			  alert("PDF保存失败！");
		 }
	}
	//作用：保存文档
	function SaveDocument(){
	  //webform.WebOffice.WebSetMsgByName("MyDefine1","自定义变量值1");  //设置变量MyDefine1="自定义变量值1"，变量可以设置多个  在WebSave()时，一起提交到OfficeServer中
	  if (!webform.WebOffice.WebSave(true)){    //交互OfficeServer的OPTION="SAVEFILE"  注：WebSave()是保存复合格式文件，包括OFFICE内容和手写批注文档；如只保存成OFFICE文档格式，那么就设WebSave(true)
	     StatusMsg(webform.WebOffice.Status);
	     return false;
	  }else{
		  /* step的含义：
		  	ZW_EDIT：	正文编辑；
		  	SL_UP：	受理通知书上传；
		  	NSL_UP：不受理通知书上传；
		  	BZ_UP：	补正材料上传；
		  	PRINT_EDIT:印刷编辑
		  	PDF_UP：PDF文件上传
		  	VIEW：查看功能，不可任何编辑
		  */
		  if(step == 'ZW_EDIT' || step == 'ZW_ADD' || step == 'SL_UP' || step == 'NSL_UP' || step == 'BZ_UP'){
			  if(parentDocument.getElementById("curTemplateId") != undefined){
				  parentDocument.getElementById("curTemplateId").value = '<%=mTemplate%>';
			  }
			  
			  if(parentDocument.getElementById("archiveType") != undefined){
				  parentDocument.getElementById("archiveType").value = '<%=archiveType%>';
			  }
			  
			  //var isAdjust = false;
			  if(step == 'ZW_EDIT' || step == 'ZW_ADD') {
				  if(parentDocument.getElementById('recordId') != undefined){
					  var textt = parentDocument.getElementById("fwname").innerHTML;
					  if (parentDocument.getElementById('dispatchDocTitle')) {
						  textt = parentDocument.getElementById('dispatchDocTitle').value + ".doc";
					  }
					  if (!textt) {
						  var indexx=parentDocument.getElementById('recordId').selectedIndex ;
				          var textt=parentDocument.getElementById("recordId").options[indexx].text + ".doc";
					  }
					  
			          parentDocument.getElementById("fwname").innerHTML = textt;
				  }
		          
				  if(parentDocument.getElementById("addDoc") != undefined){
					  parentDocument.getElementById("addDoc").style.display="none";
					  parentDocument.getElementById("zwedit").style.display="";
				  }
				  // add 2015-01-06 
					//清稿
// 				  clearRevisions();
				  //保存清稿
// 				  webform.WebOffice.WebSave(true);
				  
			  } else if (step == 'SL_UP' || step == 'NSL_UP' || step == "BZ_UP"){
				  if ($("#" + mTemplate + "_add", window.opener.document)[0]) {
					  $("#" + mTemplate + "_add", window.opener.document).hide();
					  $("#templateEdit_" + mTemplate, window.opener.document).show();
				  }
			  }
		  }
	  		
	  	  if(step == 'SQ_EDIT'){
	  		  var archiveType = '<%=archiveType%>';
	  		  if(parentDocument.getElementById("dnTemplateId") != undefined){
				  parentDocument.getElementById("dnTemplateId").value = '<%=mTemplate%>';
			  }
	  		  if(parentDocument.getElementById("addDn") != undefined){
				  parentDocument.getElementById("addDn").style.display="none";
				  parentDocument.getElementById("dnedit").style.display="";
				  parentDocument.getElementById("dnname").innerHTML = ("sq" == archiveType ? "送签" : "会签") + "说明书.doc";
			  }
			    
	  	  }
		
		  
		  if(step == 'PRINT_EDIT'){
			  <%
			  	//清稿阶段设置
			  	String clearServerUrl= serverURI +  "/OfficeServer?flowStep=CLEAR";
			  %>
			  var userid = '${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}';
			  var clearWebURL = "<%=clearServerUrl%>&usercode="+userid +"&flowInstId="+flowInstId+"&nodeInstId="+nodeInstId;
				
			  webform.WebOffice.WebUrl = clearWebURL;
			  //清稿
			  clearRevisions();
			  //保存清稿
			  webform.WebOffice.WebSave(true);
			  //保存PDF
			  //
		  }
		  if( ndCode.indexOf("ys") >= 0){
// 			  savePDFDocument();
		  }
	     StatusMsg(webform.WebOffice.Status);
	     //webform.WebOffice.WebMkDirectory("C:\\centitwordbak");
	     //webform.WebOffice.WebSaveLocalFile("C:/centitwordbak/" + getCurDate() + "_" + "<%=fileName%>");
	     return true;
	  }
	}

	//获取系统时间
	function getCurDate()
	{
		 var d = new Date();
		 var years = d.getYear();
		 var month = add_zero(d.getMonth()+1);
		 var days = add_zero(d.getDate());
		 var hours = add_zero(d.getHours());
		 var minutes = add_zero(d.getMinutes());
		 var seconds=add_zero(d.getSeconds());
		 var ndate = years+month+days+hours+minutes+seconds;
		 return ndate;
	}
	
	function getChDate()
	{
		 var d = new Date();
		 var years = d.getYear();
		 var month = add_zero(d.getMonth()+1);
		 var days = add_zero(d.getDate());
		 var ndate = years+"年"+month+"月"+days+"日";
		 return ndate;
	}

	//时间和日期补0
	function add_zero(temp)
	{
	 if(temp<10) return "0"+temp;
	 else return temp;
	}

	//作用：载入iWebOffice
	function Load(){
		
		var userName = '${SPRING_SECURITY_CONTEXT.authentication.principal.username}';
		var userid = '${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}';
		var webURL = "<%=mServerUrl%>&usercode="+userid + "&flowInstId=" +flowInstId + "&nodeInstId=" +nodeInstId;
		
		var webObj = webform.WebOffice;
		try{
			
		  	//以下属性必须设置，实始化iWebOffice
		    webObj.WebUrl= webURL ;             //WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
		    webObj.RecordID="<%=mRecordID%>";            //RecordID:本文档记录编号
		    webObj.Template="<%=mTemplate%>";            //Template:模板编号
		    webObj.FileName='<%=fileName%>';            //FileName:文档名称
		    webObj.FileType="<%=mFileType%>";            //FileType:文档类型  .doc  .xls  .wps  .et(wps的电子表格)
		    webObj.UserName = userName;            //UserName:操作用户名，痕迹保留需要
		    webObj.EditType="<%=mEditType%>";            //EditType:编辑类型  方式一、方式二  <参考技术文档>
		                                                             //第一位可以为0,1,2,3 其中:0不可编辑;1可以编辑,无痕迹;2可以编辑,有痕迹,不能修订;3可以编辑,有痕迹,能修订；
		                                                          //第二位可以为0,1 其中:0不可批注,1可以批注。可以参考iWebOffice2009的EditType属性，详细参考技术白皮书
			webObj.MaxFileSize = 4 * 1024;               //最大的文档大小控制，默认是8M，现在设置成4M。
			webObj.Language="CH";                        //Language:多语言支持显示选择   CH简体 TW繁体 EN英文
			//webObj.ShowWindow = true;                  //控制显示打开或保存文档的进度窗口，默认不显示
			webObj.PenColor="#FF0000";                   //PenColor:默认批注颜色
			webObj.PenWidth="1";                         //PenWidth:默认批注笔宽
			webObj.Print="1";                            //Print:默认是否可以打印:1可以打印批注,0不可以打印批注
			webObj.ShowMenu="1";                         //控制整体菜单显示
			webObj.ShowToolBar="1";
			webObj.VisibleTools('全屏',false);
			webObj.VisibleTools('新建文件',false);
			webObj.VisibleTools('打开文件',false);
			webObj.VisibleTools('文字批注',false);
			webObj.VisibleTools('手写批注',false);
			webObj.VisibleTools('文档清稿',false);
			webObj.VisibleTools('重新批注',false);
			//初始化快捷键、
			webObj.DisableKey("CTRL+P");
			webObj.ShortCutKey="CTRL+S;CTRL+P;F12";
			//WebSetRibbonUIXML();                                  //控制OFFICE2007的选项卡显示

			webObj.WebOpen();                            //打开该文档    交互OfficeServer  调出文档OPTION="LOADFILE"    调出模板OPTION="LOADTEMPLATE"     <参考技术文档>
			
			if(ndCode != 'N_jd' && ndCode != 'N_ys'&& ndCode != 'jd'&& ndCode != 'ys' ){
				setTimeout(function(){
				    transBookMark(webObj);					
				},1000);
			}		
			
			if(step == 'ZW_EDIT' || step == 'ZW_ADD'){//拟文或主动发文
				//webObj.ShowToolBar="1";                      //ShowToolBar:是否显示工具栏:1显示,0不显示
				webObj.WebObject.ShowRevisions=0;			//不显示修订
				webObj.WebObject.PrintRevisions=0;
				//隐藏几个自定义工具栏的按钮
				webObj.VisibleTools("保存文件",false);
				//自定义按钮，按钮响应事件在onToolsclick
				webObj.AppendTools("90","显示修订",99);
				webObj.AppendTools("91","隐藏修订",99);
				webObj.AppendTools("92","显示红头",99);
				webObj.AppendTools("93","隐藏红头",99);
				webObj.AppendTools("98","清理痕迹",99); 
			}
			webObj.AppendTools("94","打印文档",99);
			webObj.AppendTools("95","退出编辑",99);
			webObj.AppendTools("96","本地保存",99);
			
			if(step != null && step != 'VIEW'){
				webObj.AppendTools("97","保存文档",99);
			}else{
				webObj.VisibleTools("重新批注,新建文件,打开文件,保存文件,文字批注,手写批注,文档清稿",false);
				webObj.WebObject.ShowRevisions=0;
				webObj.WebObject.PrintRevisions=0;
			}
					
			webObj.WebObject.ActiveWindow.View.TableGridlines=0;
			if($.browser.msie && $.browser.version==10){
				   $("#WebOffice").height(window.innerHeight);
			}
			$("#DivID").height(window.screen.availHeight-50);
		}catch(e){
		  //alert(e.description);                                   //显示出错误信息
			alert("温馨提示：编辑正文后请按 Ctrl+S 保存"); 
		}
	}

	function _getParentElementValue(id){
		if(parentDocument.getElementsByName(id)[0] == undefined){
			return '';
		}
		return parentDocument.getElementsByName(id)[0].value;
	}
	
	//设置文书标签
	function transBookMark(webObj){

		var jsonArr = window.opener.getOptBaseInfoJson();
	
		var obj = new Array();
		for(var able in jsonArr){		
			obj.push(able);
		}	
		for(var i=0,len=obj.length;i<len;i++){
			if(webObj.WebObject.Bookmarks.Exists(obj[i])){
				webObj.WebSetBookMarks(obj[i],jsonArr[obj[i]]);
			}
				//j小于书签重复的次数
			for(var j=1; j < 6; j++){
				var tempMark = obj[i]+"_" + j;
				if(webObj.WebObject.Bookmarks.Exists(tempMark)){
					webObj.WebSetBookMarks(tempMark,jsonArr[obj[i]]);
				}
			}
		}
		//以下是获取当前步骤的人员信息，并将信息插入书签
		var userName = '${SPRING_SECURITY_CONTEXT.authentication.principal.username}';
		if(webObj.WebObject.Bookmarks.Exists("<%=nodeCode %>_userName")){
			webObj.WebSetBookMarks("<%=nodeCode %>_userName",  userName );
		}
		//以下是获取当前日期信息，并将日期信息插入书签
		if(webObj.WebObject.Bookmarks.Exists("<%=nodeCode %>_createDate")){
			webObj.WebSetBookMarks("<%=nodeCode %>_createDate",  getChDate() );
		}
		var transcontent = _getParentElementValue("transcontent");
		if(webObj.WebObject.Bookmarks.Exists("<%=nodeCode %>_transcontent")){
			webObj.WebSetBookMarks("<%=nodeCode %>_transcontent",  transcontent);
		}
		//以下是循环插入当前步骤书签
		for(var j=1; j < 6; j++){
			var tempMark = "<%=nodeCode %>_userName_" + j;
			if(webObj.WebObject.Bookmarks.Exists(tempMark)){
				webObj.WebSetBookMarks(tempMark,  userName );
			}
			var dateMark = "<%=nodeCode %>_createDate_" + j;
			if(webObj.WebObject.Bookmarks.Exists(dateMark)){
				webObj.WebSetBookMarks(dateMark,  getChDate() );
			}
			var contentMark = "<%=nodeCode %>_transcontent_" + j;
			if(webObj.WebObject.Bookmarks.Exists(contentMark)){
				webObj.WebSetBookMarks(contentMark,  transcontent );
			}
		}
		
	}
	

	
//作用：显示操作状态
function StatusMsg(mString){
	//alert(mString);
  //webform.StatusBar.value=mString;
}

//作用：退出iWebOffice
function UnLoad(){
	try{
	  webform.WebOffice.WebClose();
	  }catch(e){
	  alert(e.description);
	}
}

//作用，将正在编辑的文档保存为本地文件
function SaveAsLocalFile(){
	webform.WebOffice.WebSaveLocal();
}

//打印份数控制
function WebCopysCtrlPrint(webObj){
  var mCopies,objPrint;
  objPrint = webObj.WebObject.Application.Dialogs(88);     //打印设置对话框
  if (objPrint.Display==-1){
    mCopies=objPrint.NumCopies;    //取得需要打印份数
    webObj.WebSetMsgByName("COMMAND","COPIES");
    webObj.WebSetMsgByName("OFFICEPRINTS",mCopies.toString());   //设置变量OFFICEPRINTS的值，在WebSendMessage()时，一起提交到OfficeServer中
    webObj.WebSendMessage();                               //交互OfficeServer的OPTION="SENDMESSAGE"
    if (webObj.Status=="1") {
      alert("可以允许打印，注：该实例设置总文档打印份数2份");
      objPrint.Execute;
    }else{
      alert("已超出允许的打印份数");
      return false;
    }
  }
}

function zfclBookMark(webOffice) {
	//webOffice为带入金格控件的对象   
	try {
		var jsonArr = window.opener.getOptProcInfoJSON1();
		var obj = new Array();
		for(var able in jsonArr){		
			obj.push(able);
		}
		var nCars = obj.length;//会签部门数量    
		var hnum = obj.length/4;
		_webObj=webOffice.WebObject;//webobj对象
		_vba = _webObj.Application; //得到VBA操作对象         
		_selction = _vba.Selection;//当前选择对象 (MSDN:该对象代表选定范围或光标)
		if (_webObj.BookMarks.Exists("hqsq")) {//书签CarNo，定义在车牌照的第一行 ,用来判定该书签是否存在 返回boolean          
			_vba.ActiveDocument.Bookmarks("hqsq").Select();//将selection设为该书签         
			if (hnum > 4) {
				_selction.InsertRowsBelow(hnum - 4);//插入空行     
			}
			_vba.ActiveDocument.Bookmarks("hqsq").Select();//插入行后selection会变为插入的空白三行,这句代码用来重定位到书签位置          
			var x = _selction.Information(13); //VBA代码中对应位置引用的枚举值的实际数值    
			var y = _selction.Information(16); //VBA代码中对应位置引用的枚举值的实际数值       
			for ( var i = 1; i <= hnum; i++) {
				_selction.Tables(1).Cell(x + i -1 , y).Range.Text = jsonArr[obj[4*(i-1)]];//cell(3,2)开始填充值             
				_selction.Tables(1).Cell(x + i -1, y + 1).Range.Text = jsonArr[obj[4*(i-1)+1]];
				_selction.Tables(1).Cell(x + i -1, y + 2).Range.Text = jsonArr[obj[4*(i-1)+2]];
				_selction.Tables(1).Cell(x + i -1, y + 3).Range.Text = jsonArr[obj[4*(i-1)+3]];
			}
		}
	} catch (e) {
		alert("异常\r\nError:" + e + "\r\nError Code:" + e.number
				+ "\r\nError Des:" + e.description);
	}
}
/**
 * 关闭窗口按钮
 */
function checkLeave(){
	/* var n = window.event.screenX - window.screenLeft;
    var b = n > document.documentElement.scrollWidth-20;
    if(b && (window.event.clientY < 0 || window.event.altKey))
    {
		window.event.returnValue = "提醒：关闭窗口前请保存文档。";
    } */
	if(window.confirm("文档是否保存？")){
		if(SaveDocument()){
			savePDFDocument();
			document.webform.submit();
			window.close();
			//window.opener.parent.frames['stuffsFrame'].location.reload(); 
		}
	
	}else{
		window.close();
	}
}
</script>

<!-- 穆欣 2012-7-7 金格控件自定义工具按钮的事件响应函数 -->
<script language=javascript for="WebOffice" event=OnToolsClick(vIndex,vCaption)>
	var webObj = webform.WebOffice;
	var rev;
	if(vIndex==-1){//对全屏操作的响应
	
		if(vCaption== "全屏_BEGIN"){
			rev = webObj.WebObject.ShowRevisions;
			if(rev){
				webform.revision.value = '1';
			}else{
				webform.revision.value  = '0';
			}
		}
	
		if(vCaption=="返回"){
			rev = webform.revision.value;
			if(rev=='1'){
				webObj.WebObject.ShowRevisions=true;     //重新设置为默认
			    webObj.WebObject.PrintRevisions=true;
				
			}
			if(rev=='0'){
				webObj.WebObject.ShowRevisions=false;     //重新设置为默认
			    webObj.WebObject.PrintRevisions=false;
			}
		}
		
		if(vCaption=="返回_BEGIN"){                      //退出全屏操作之前
			rev = webObj.WebObject.ShowRevisions;        //获取痕迹状态
			if(rev){
				webform.revision.value = '1';
			}else{
				webform.revision.value  = '0';
			}
		}
		if(vCaption=="全屏"){                            //退出全屏操作之后
		    rev = webform.revision.value;
			if(rev=='1'){
				webObj.WebObject.ShowRevisions=true;     //重新设置为默认
			    webObj.WebObject.PrintRevisions=true;
				
			}
			if(rev=='0'){
				webObj.WebObject.ShowRevisions=false;     //重新设置为默认
			    webObj.WebObject.PrintRevisions=false;
			}
		}
	}else if(vIndex==90){//显示痕迹
		webObj.WebObject.ShowRevisions=1;
		webObj.WebObject.PrintRevisions=1;
	}else if(vIndex==91){//隐藏痕迹，但继续保留痕迹（虽然不可见）
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	}else if(vIndex==92){//显示红头
		ShowRed(webObj);
		webObj.EditType="<%=mEditType%>";
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	}else if(vIndex==93){//隐藏红头
		HideRed(webObj);
		webObj.EditType="<%=mEditType%>";
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	} 
	else if (vIndex==94){
		webObj.WebOpenPrint();     //打印文档
	}
	else if (vIndex==95){
		if(window.confirm("确定退出文档编辑？")){
			checkLeave();
			//window.close();     //退出文档
		}
		
	}
	else if (vIndex==96){  
		SaveAsLocalFile();     //保存本地
	}
	else if (vIndex==97){ //保存提交
		if(SaveDocument()){
			savePDFDocument();
			document.webform.submit(); 
			//window.opener.parent.frames['stuffsFrame'].location.reload();
			window.close();     //退出文档
		}
	}else if(vIndex==98){
		if(confirm("确认清理所有修改痕迹？")){
			document.getElementById("WebOffice").ClearRevisions();
		}
	} 
	
	if(vIndex==-2){ 
		if(vCaption=="CTRL+S"){ 
			if(SaveDocument()){
				savePDFDocument();
				document.webform.submit();   
			}
		}else if(vCaption=="CTRL+P"){ 
			webObj.WebOpenPrint();     //打印文档
		}else if(vCaption=="F12"){ 
			SaveAsLocalFile();
		} 
	}
	
	/****************************************************
	*
	* 显示红头
	*
	****************************************************/
	function ShowRed(webObj){
		try{
			webObj.EditType="1,0";//设为非保护模式
			
			if(webObj.WebObject.Bookmarks.Exists("RedHead")){
				webObj.WebObject.Bookmarks.Item("RedHead").Range.Font.Color=255;
				var b = webObj.WebObject.Bookmarks.Item("RedHead").Range.InlineShapes.Count;
				for(var i=1;i<=b;i++){
				webObj.WebObject.Bookmarks.Item("RedHead").Range.InlineShapes(i).PictureFormat.Brightness=0.5;
				}
			}
			if(webObj.WebObject.Bookmarks.Exists("RedFoot")){
				webObj.WebObject.Bookmarks.Item("RedFoot").Range.Font.Color=255;
				var b = webObj.WebObject.Bookmarks.Item("RedFoot").Range.InlineShapes.Count;
				for(i=1;i<=b;i++){
					webObj.WebObject.Bookmarks.Item("RedFoot").Range.InlineShapes(i).PictureFormat.Brightness=0.5;
				}
			}
			
			
			var ncode = '<%=nodeCode%>';
			if(ncode == 'N_dayin2'){
				for(var j = 0; j < 18 ;j++){
					if(webObj.WebObject.Bookmarks.Exists("RedHead_" + j)){
						webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.Font.Color=255;
						var b = webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.InlineShapes.Count;
						for(i=1;i<=b;i++){
						webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.InlineShapes(i).PictureFormat.Brightness=0.5;
						}
					}
				}
			}
		}catch(e){
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
	}

	/****************************************************
	*
	* 隐藏红头
	*
	****************************************************/
	function HideRed(webObj){
		
		try{
			webObj.EditType="1,0";//设为非保护模式
			if(webObj.WebObject.Bookmarks.Exists("RedHead")){
				webObj.WebObject.Bookmarks.Item("RedHead").Range.Font.Color=16777215;
				var b = webObj.WebObject.Bookmarks.Item("RedHead").Range.InlineShapes.Count;
				for(var i=1;i<=b;i++){
					webObj.WebObject.Bookmarks.Item("RedHead").Range.InlineShapes(i).PictureFormat.Brightness=1;
				}
			}
			if(webObj.WebObject.Bookmarks.Exists("RedFoot")){
				webObj.WebObject.Bookmarks.Item("RedFoot").Range.Font.Color=16777215;
				var b = webObj.WebObject.Bookmarks.Item("RedFoot").Range.InlineShapes.Count;
				for(i=1;i<=b;i++){
					webObj.WebObject.Bookmarks.Item("RedFoot").Range.InlineShapes(i).PictureFormat.Brightness=1;
				}
			}
			
			
			var ncode = '<%=nodeCode%>';
			//alert(ncode);
			if(ncode == 'N_dayin2'){
				for(var j = 0; j < 18 ;j++){
					if(webObj.WebObject.Bookmarks.Exists("RedHead_" + j)){
						webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.Font.Color=16777215;
						var b = webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.InlineShapes.Count;
						for(i=1;i<=b;i++){
							webObj.WebObject.Bookmarks.Item("RedHead_" + j).Range.InlineShapes(i).PictureFormat.Brightness=1;
						}
					}
				}
			}
		}catch(e){
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
	}

	
</script>

</head>
<body bgcolor="#ffffff" onload="Load()"  onbeforeunload="checkLeave()" onunload="UnLoad()">
<p/>
<form name="webform" method="post" action="DocumentSave.jsp" target="frame_save" onsubmit="return SaveDocument();">
    <input type="hidden" name="revision" value="">
    <input type="hidden" name="RecordID" value="<%=mRecordID%>">
    <input type="hidden" name="Template" value="<%=mTemplate%>">
    <input type="hidden" name="FileType" value="<%=mFileType%>">
    <input type="hidden" name="EditType" value="<%=mEditType%>">
    <input type="hidden" name="Subject" value="">
    <input type="hidden" name="Author" value="${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}">
    <input type="hidden" name="FileDate" value="<%=mFileDate%>">
        
       <iframe src="topName.jsp" style="position: absolute; width: 100%; height: 20px; border: none;;"></iframe>
<div style="z-index: 9999999999999999; position: absolute; width: 100px; height: 0px; background: white; top: 20px; left: 100px;"></div>
        
        
          <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
            <tr>
              <td bgcolor="menu" height="98%" valign="top">
              <div id="DivID">
	              <OBJECT name="WebOffice" id="WebOffice" style="width:100%;"
					classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499"
					codebase="iWebOffice2009.cab#version=10,7,0,0"
					  width="100%"  height="100%"> <param name="wmode" value="opaque" />
				</OBJECT>
				</div>
              </td>
            </tr>
          </table>
      <iframe width="0px" height="0px" frameborder="no" scrolling="no" src="" name="frame_save"></iframe>
</form>
</body>
</html>