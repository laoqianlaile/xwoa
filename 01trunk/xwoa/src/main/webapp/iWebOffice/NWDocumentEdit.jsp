<%@page import="DBstep.iMsgServer2000"%>
<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,java.util.Date,javax.servlet.*,javax.servlet.http.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成拟文单</title>
<%
  ResultSet result=null;
  String mSubject=null;
  String mStatus=null;
  String mAuthor=null;
  String mFileName=null;
  String mFileDate=null;
  String mHTMLPath="";

  String mDisabled="";
  String mDisabledSave="";
  String mWord="";
  String mExcel="";

  //流程实例编号
  String flowInstId = request.getParameter("flowInstId");
  flowInstId = (flowInstId == null ? "" : flowInstId);
  
  //节点实例编号
  String nodeInstId = request.getParameter("nodeInstId");
  nodeInstId = (nodeInstId == null ? "" : nodeInstId);
  
  //流程步骤、或者模块缩写
  String flowStep = request.getParameter("flowStep");
  flowStep = (flowStep == null ? "" : flowStep);
  
  String usercode=request.getParameter("usercode");
  //String mUserName=new String(request.getParameter("UserName").getBytes("8859_1"));
  
  //自动获取OfficeServer和OCX文件完整URL路径
  String mHttpUrlName=request.getRequestURI();
  String mScriptName=request.getServletPath();
  String mServerName="/OfficeServer?flowStep="+flowStep+"&flowInstId="+flowInstId+"&nodeInstId="+nodeInstId+"&usercode="+usercode;

  String mServerUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/"+mServerName;//取得OfficeServer文件的完整URL
  String mHttpUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
  String mRecordID=request.getParameter("RecordID");
  String mTemplate=request.getParameter("Template");
  //强行指定.doc类型
  //String mFileType=request.getParameter("FileType");
  String mFileType=".doc";
  String mEditType=request.getParameter("EditType");
  String mShowType=request.getParameter("ShowType");

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
  if (mTemplate==null){
    mTemplate="";
  }

  String fileName = "";
  //打开数据库
  iDBManager2000 DbaObj=new iDBManager2000();
  if (DbaObj.OpenConnection()){
	  
    /*String mSql="Select * From Document Where RecordID='"+ mRecordID + "'";
    
    if(mTemplate.length() != 0){
    	mSql += " and TEMPLATE='"+mTemplate+"'";
    }
    */
    try{
    	/*
      result=DbaObj.ExecuteQuery(mSql);
      if (result.next()){
        mRecordID=result.getString("RecordID");
        mTemplate=result.getString("Template");
        mSubject=result.getString("Subject");
        mAuthor=result.getString("Author");
        mFileDate=result.getString("FileDate");
        mStatus=result.getString("Status");
        mFileType=result.getString("FileType");
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
        mAuthor=mUserName;
        mFileDate=DbaObj.GetDateTime();
        mStatus="DERF";
        mFileType=mFileType;
        mHTMLPath="";
      }
*/
      String TemplateSql = "SELECT FILETYPE,DESCRIPT FROM Template_File WHERE RECORDID='" + mTemplate + "'";
      //System.out.println(TemplateSql);
      result = DbaObj.ExecuteQuery(TemplateSql);
      if (result.next()) {
      		fileName = result.getString("DESCRIPT") + result.getString("FILETYPE");
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
	var isHQ = false;
	var isLHFW =false;
	
	//父页面文档对象
	var parentDocument = window.opener.document;
	
	function saveAllDoc(){
		webform.NWDWebOffice.WebSave();
		
		webform.NWDWebOffice.ClearRevisions();
		
	}
	
	function savePDFDocument(){
		
		<%
			request.setAttribute("flowStep","PDF_UP");
		%>
		
		webform.NWDWebOffice.WebSavePDF();
	}
	//作用：保存文档
	function SaveDocument(){
	  //webform.NWDWebOffice.WebSetMsgByName("MyDefine1","自定义变量值1");  //设置变量MyDefine1="自定义变量值1"，变量可以设置多个  在WebSave()时，一起提交到OfficeServer中
	  if (!webform.NWDWebOffice.WebSave(true)){    //交互OfficeServer的OPTION="SAVEFILE"  注：WebSave()是保存复合格式文件，包括OFFICE内容和手写批注文档；如只保存成OFFICE文档格式，那么就设WebSave(true)
	     StatusMsg(webform.NWDWebOffice.Status);
	     return false;
	  }else{
		  
		  
		  /* step的含义：
		  	FW_UP：	发文上传；
		  	NW_UP：	拟文上传；
		  	SL_UP：	受理通知书上传；
		  	NSL_UP：不受理通知书上传；
		  	BZ_UP：	补正材料上传；
		  	PRINT_EDIT:印刷编辑
		  	PDF_UP：PDF文件上传
		  	VIEW：查看功能，不可任何编辑
		  */
		  
	     StatusMsg(webform.NWDWebOffice.Status);
	     return true;
	  }
	}


	//作用：载入iWebOffice
	function Load(){
		var userName = '${SPRING_SECURITY_CONTEXT.authentication.principal.username}';
		var webObj = webform.NWDWebOffice;
		
		try{
		  	//以下属性必须设置，实始化iWebOffice
		    webObj.WebUrl="<%=mServerUrl%>";             //WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
		    webObj.RecordID="<%=mRecordID%>";            //RecordID:本文档记录编号
		    webObj.Template="<%=mTemplate%>";            //Template:模板编号
		    webObj.FileName="<%=fileName%>";            //FileName:文档名称
		    webObj.FileType="<%=mFileType%>";            //FileType:文档类型  .doc  .xls  .wps
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
			webObj.ShowToolBar="1";                      //ShowToolBar:是否显示工具栏:1显示,0不显示
			webObj.ShowMenu="1";                         //控制整体菜单显示
			webObj.DisableMenu("宏(&M);选项(&O)...");    //禁止某个（些）菜单项
	
			//动态设置会签拟文单
			var hqObj =  parentDocument.getElementsByName("sfhqw")[0];
			var lhfwObj =  parentDocument.getElementsByName("lhfw")[0];
			if(hqObj != undefined && hqObj.checked){
				webObj.Template="HQ_NWD";
				isHQ = true;
				
				if(lhfwObj != undefined && lhfwObj.checked){
					isLHFW = true;
				}
			}
			//WebSetRibbonUIXML();                                  //控制OFFICE2007的选项卡显示
			webObj.WebOpen();                            //打开该文档    交互OfficeServer  调出文档OPTION="LOADFILE"    调出模板OPTION="LOADTEMPLATE"     <参考技术文档>
			
			//穆欣 2012-7-8 设置拟文单界面所需要的控制
			webObj.VisibleTools("重新批注,新建文件,打开文件,保存文件,文字批注,手写批注,文档清稿",false);
			webObj.WebObject.ShowRevisions=0;
			webObj.AppendTools("94","打印文档",99);
			webObj.AppendTools("95","退出编辑",99);
			//webObj.AppendTools("96","本地保存",99);
			
			if(step != null && step != "VIEW"){
				webObj.AppendTools("97","保存文档",99);
			}
			NWFormBookMark(webObj);
			if($.browser.msie && $.browser.version==10){
				   $("#WebOffice").height(window.innerHeight);				
				}
			  
		}catch(e){
		  alert(e.description);                                   //显示出错误信息
		}
	}

	function _getParentElementValue(id){
		//alert(parentDocument.getElementById(id).value);
		if(parentDocument.getElementsByName(id)[0] == undefined){
			return '';
		}
		
		return parentDocument.getElementsByName(id)[0].value;
	}
	
	//获取已选择的下拉框对象的文字
	function _getSelectedOption(id){
		var objSelect = parentDocument.getElementsByName(id)[0];
		
		if(objSelect == undefined){
			return '';
		}
		
		if(objSelect.options== undefined){
			return objSelect.value;
		}
		  for (var i = 0; i < objSelect.options.length; i++) {        
		      if (objSelect.options[i].selected) {        
		          return objSelect.options[i].text;        
		      }
		  }
		  return "";
	}
	
	//拟文单书签设置
	function NWFormBookMark(webObj){
		try{ 
			
			if(isHQ){
				if(isLHFW){
					webObj.WebSetBookMarks("hqdw1",_getParentElementValue("hqdw"));
				}
				webObj.WebSetBookMarks("hqdw2",_getParentElementValue("hqdw"));
			}
			
			webObj.WebSetBookMarks("gwzl",_getSelectedOption("gwzl"));//公文种类
			webObj.WebSetBookMarks("title",_getParentElementValue("wjbt"));//文件标题
			webObj.WebSetBookMarks("bmdj",_getSelectedOption("miji"));//密级
			webObj.WebSetBookMarks("printnumber",_getParentElementValue("fs"));//份数
			webObj.WebSetBookMarks("bgqx",_getSelectedOption("bgqx"));//保管期限
			webObj.WebSetBookMarks("zsdw",_getParentElementValue("zsdw"));//主送单位
			webObj.WebSetBookMarks("csdw",_getParentElementValue("csdw"));//抄送单位
			//webObj.WebSetBookMarks("topic",_getParentElementValue("ztc"));//主题词
			webObj.WebSetBookMarks("gkyq",_getSelectedOption("wjgk"));//文件公开
			webObj.WebSetBookMarks("nwrq",_getParentElementValue("nwrq"));//拟文日期
			webObj.WebSetBookMarks("cbcs",_getParentElementValue("cbcs"));//承办处室
			webObj.WebSetBookMarks("ngr",_getParentElementValue("ngr"));//拟稿人
			webObj.WebSetBookMarks("zynr",_getParentElementValue("gknr"));//摘要内容
			
			if(_getParentElementValue("swh") != ''){
				webObj.WebSetBookMarks("swzh",_getParentElementValue("swh"));//收文字号
			}else if(window.opener.frames['frame_basic']!=undefined){
				webObj.WebSetBookMarks("swzh",window.opener.frames['frame_basic'].getSWH());
			}
			
			var strHqcs=_getParentElementValue("xbcsbm");
			var str = strHqcs.split("、").join("\n");
			webObj.WebSetBookMarks("nbhq",str);//内部会办处室
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
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
	  webform.NWDWebOffice.WebClose();
	  }catch(e){
	  alert(e.description);
	}
}
</script>
<script language=javascript for="NWDWebOffice" event=OnToolsClick(vIndex,vCaption)>
		var webObj = webform.NWDWebOffice;
		 if (vIndex==95){  
				window.close();     //退出文档
			}else if (vIndex==94){  
				webObj.WebOpenPrint();       //打印文档
			}else if (vIndex==97){ //保存提交
				if(SaveDocument()){
					document.webform.submit();   
				}
			}
</script>
</head>
<body bgcolor="#ffffff" onload="Load()" onunload="UnLoad()">
<form name="webform" method="post" action="DocumentSave.jsp" target="frame_save" onsubmit="return SaveDocument();">
    <input type="hidden" name="RecordID" value="<%=mRecordID%>">
    <input type="hidden" name="Template" value="<%=mTemplate%>">
    <input type="hidden" name="FileType" value="<%=mFileType%>">
    <input type="hidden" name="EditType" value="<%=mEditType%>">
    <input type="hidden" name="Subject" value="">
    <input type="hidden" name="Author" value="<%=usercode%>">
    <input type="hidden" name="FileDate" value="<%=mFileDate%>">
<table border=0 cellspacing='0' cellpadding='0' width=100% height="1536px;" align=center class=TBStyle>
		<%
			//if(flowStep != null && !flowStep.equals("VIEW")){
		%>
			<!--<input type="submit" value="保存文档">
		--><%
			//} 
		%><!--
	&nbsp;&nbsp;<input type=button onClick="window.close();" value="退出文档">
      --><td height="100%" colspan="2" rowspan="12" align="right" valign="top" class="TDStyle" hegith="90%">
          <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
            <tr>
              <td bgcolor="menu" height="98%" valign="top">
	              <OBJECT name="NWDWebOffice" id="WebOffice"
					classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499"
					codebase="iWebOffice2009.cab#version=10,4,6,0"
					  width="100%"  height="100%">
				</OBJECT>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </table>
       <iframe width="0px" height="0px" frameborder="no" scrolling="no" src="" name="frame_save"></iframe>
</form>
</body>
</html>