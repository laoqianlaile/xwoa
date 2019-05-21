<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/page/common/css.jsp"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,javax.servlet.*,javax.servlet.http.*" %>
<%
  ResultSet result=null;

  String mDescript="";
  String mFileName="";

  String mHttpUrlName=request.getRequestURI();
  String mScriptName=request.getServletPath();
  String mServerName="/OfficeServer";
  String mServerUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/"+mServerName;

  String mRecordID=request.getParameter("RecordID");
  String mFileType=".doc";//request.getParameter("FileType");
  String tempType=request.getParameter("tempType");
  String mEditType="1,1";
  String mUserName="Administrator";
  
  String orderBy = "";

	String codeCode = "";

  //取得模式
  if (mEditType==null)
  {
    mEditType="2,1";		//2 起草
  }
  //取得类型
  if ( mFileType==null)
  {
    mFileType=".doc";	// 默认为.doc文档
  }
  //取得用户名
  if (mUserName==null)
  {
    mUserName="";
  }

  //取得模板
  if ( mRecordID==null)
  {
    mRecordID="";	// 默认没有模板
  }


  //打开数据库
  iDBManager2000 DbaObj=new iDBManager2000();
  if (DbaObj.OpenConnection())
  {
    String mSql="Select * From Template_File Where RecordID='"+ mRecordID + "'";
    try
    {
      result=DbaObj.ExecuteQuery(mSql);
      if (result.next())
      {
        mRecordID=result.getString("RecordID");
        mFileName=result.getString("FileName");
        mFileType=result.getString("FileType");
        mDescript=result.getString("Descript");
        tempType = result.getString("tempType");
        orderBy = result.getString("orderBy");
        codeCode = result.getString("codeCode");
        if(codeCode==null || "".equals(codeCode)){
        codeCode="";
        }
      }
      else
      {
	//取得唯一值(mRecordID)
        java.util.Date dt=new java.util.Date();
        long lg=dt.getTime();
        Long ld=new Long(lg);
	//初始化值
        mRecordID=ld.toString();
        mFileName="";
        mFileType=mFileType;
        mDescript="";
      }
      result.close();
    }
    catch(Exception e)
    {
      System.out.println(e.toString());
    }
    DbaObj.CloseConnection() ;
  }

%>

<head>
<title>模板管理</title>
<link rel='stylesheet' type='text/css' href='../test.css'>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language=javascript>
// 根据值设置select中的选项       
function _SelectItemByValue(objSelect, objItemText) {            
    //判断是否存在        
    //var isExit = false;        
    for (var i = 0; i < objSelect.options.length; i++) {        
        if (objSelect.options[i].value == objItemText) {        
            objSelect.options[i].selected = true;        
            //isExit = true;        
            break;        
        }        
    } 
}

//作用：显示操作状态
function StatusMsg(mString){
  StatusBar.innerText=mString;
}

//作用：载入iWebOffice
function Load(){
  try{

	  //以下属性必须设置，实始化iWebOffice
	  webform.WebOffice.WebUrl="<%=mServerUrl%>";	//WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
	  webform.WebOffice.RecordID="<%=mRecordID%>";	//RecordID:本文档记录编号
	  webform.WebOffice.Template="<%=mRecordID%>";	//Template:模板编号
	  webform.WebOffice.FileName="<%=mFileName%>";	//FileName:文档名称
	  webform.WebOffice.FileType="<%=mFileType%>";	//FileType:文档类型  .doc  .xls  .wps
	  webform.WebOffice.EditType="<%=mEditType%>";	//EditType:编辑类型  方式一、方式二  <参考技术文档>
	  webform.WebOffice.UserName="<%=mUserName%>";	//UserName:操作用户名
	  webform.WebOffice.WebOpen();			//打开该文档    交互OfficeServer的OPTION="LOADTEMPLATE"
	  webform.WebOffice.ShowType=1;			//文档显示方式  1:表示文字批注  2:表示手写批注  0:表示文档核稿
	webform.WebOffice.WebObject.ShowRevisions=0;			//不显示修订
	webform.WebOffice.WebObject.PrintRevisions=0;
	//隐藏几个自定义工具栏的按钮
	//webform.WebOffice.VisibleTools("重新批注,新建文件,打开文件,保存文件,文字批注,手写批注,文档清稿",false);
	//自定义按钮，按钮响应事件在onToolsclick
	webform.WebOffice.AppendTools("90","显示修订",99);
	webform.WebOffice.AppendTools("91","隐藏修订",99);
	webform.WebOffice.AppendTools("92","显示红头",99);
	webform.WebOffice.AppendTools("93","隐藏红头",99);
	webform.WebOffice.VisibleTools('全屏',false);
	webform.WebOffice.VisibleTools('新建文件',false);
	/* webform.WebOffice.VisibleTools('打开文件',false); */
	webform.WebOffice.VisibleTools('文字批注',false);
	webform.WebOffice.VisibleTools('手写批注',false);
	webform.WebOffice.VisibleTools('文档清稿',false);
	webform.WebOffice.VisibleTools('重新批注',false);
	webform.WebOffice.VisibleTools('显示修订',false);
	webform.WebOffice.VisibleTools('隐藏修订',false);
	if($.browser.msie && $.browser.version == 10){
	      $("#WebOffice").height($("#tdMenu").height());		
		}
  }catch(e){
	  
  }
}

//作用：退出iWebOffice
function UnLoad(){
  try{
  if (!webform.WebOffice.WebClose()){
     StatusMsg(webform.WebOffice.Status);
  }else{
     StatusMsg("关闭文档...");
  }
  }catch(e){}
}


//作用：打开文档
function LoadDocument(){
  StatusMsg("正在打开文档...");
  if (!webform.WebOffice.WebLoadTemplate()){  //交互OfficeServer的OPTION="LOADTEMPLATE"
     StatusMsg(webform.WebOffice.Status);
  }else{
     StatusMsg(webform.WebOffice.Status);
  }
}


//作用：刷新文档
function WebReFresh(){
  webform.WebOffice.WebReFresh();
  StatusMsg("文档已刷新...");
}



//作用：打印文档
function WebOpenPrint(){
  try{
    webform.WebOffice.WebOpenPrint();
    StatusMsg(webform.WebOffice.Status);
  }catch(e){}
}

//作用：页面设置
function WebOpenPageSetup(){
   try{
	if (webform.WebOffice.FileType==".doc"){
	  webform.WebOffice.WebObject.Application.Dialogs(178).Show();
	}
	if(webform.WebOffice.FileType==".xls"){
	  webform.WebOffice.WebObject.Application.Dialogs(7).Show();
	}
   }catch(e){

   }
}


//作用：存为本地文件
function WebSaveLocal(){
  try{
    webform.WebOffice.WebSaveLocal();
    StatusMsg(webform.WebOffice.Status);
  }catch(e){}
}

//作用：打开本地文件
function WebOpenLocal(){
  try{
    webform.WebOffice.WebOpenLocal();
    StatusMsg(webform.WebOffice.Status);
  }catch(e){}
}

//作用：保存文档
function SaveDocument(){
	var Descript = document.getElementById("Descript");
	var tempType = document.getElementById("tempType");
	var orderBy = document.getElementById("orderBy");
	
	
	if(Descript.value == '' && Descript.value.length==0){
		alert('请输入模版名');
		Descript.focus();
		return false;
	}
	
	if(orderBy.value == '' && orderBy.value.length==0){
		alert('次序');
		orderBy.focus();
		return false;
	}
	
	if(tempType.value == '' && tempType.value.length==0){
		alert('请选择模版分类');
		return false;
	}

  //webform.WebOffice.WebSetMsgByName("MyDefine1","自定义变量值1");  //设置变量MyDefine1="自定义变量值1"，变量可以设置多个  在WebSaveTemplate()时，一起提交到OfficeServer中
  if (!webform.WebOffice.WebSaveTemplate(true)){    //交互OfficeServer的OPTION="SAVETEMPLATE"，参数true表示保存OFFICE文档
     StatusMsg(webform.WebOffice.Status);
     return false;
  }else{
     return true;
  }
}

</script>

<script language=javascript>
 function OnToolsClick(vIndex,vCaption){
	var webObj = webform.WebOffice;
	var rev;
	if(vIndex==-1){//对全屏操作的响应
	
		if(vCaption== "全屏_BEGIN"){
			rev = webObj.WebObject.ShowRevisions;
			if(rev){
				webform.revision.value = 1;
			}else{
				webform.revision.value  = 0;
			}
		}
	
		if(vCaption=="返回"){
			rev = webform.revision.value;
		    webObj.WebObject.ShowRevisions=rev; //重新设置
		    webObj.WebObject.PrintRevisions=rev;
		}
		
		if(vCaption=="返回_BEGIN"){                      //退出全屏操作之前
			rev = webObj.WebObject.ShowRevisions;        //获取痕迹状态
			if(rev){
				webform.revision.value = 1;
			}else{
				webform.revision.value  = 0;
			}
		}
		if(vCaption=="全屏"){                            //退出全屏操作之后
		    rev = webform.revision.value;
		    webObj.WebObject.ShowRevisions=rev;     //重新设置为默认
		    webObj.WebObject.PrintRevisions=rev;
		}
	}else if(vIndex==90){//显示痕迹
		webObj.WebObject.ShowRevisions=1;
		webObj.WebObject.PrintRevisions=1;
	}else if(vIndex==91){//隐藏痕迹，但继续保留痕迹（虽然不可见）
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	}else if(vIndex==92){//显示红头
		webObj.EditType="1,0";
		try{
			var viewtype=webObj.WebObject.ActiveWindow.View.Type;
			webObj.WebObject.ActiveWindow.view.Type=3;
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			try{
				webObj.WebObject.Styles.Item("文头").Font.Color=255;
			}
			catch(e){}
			webObj.WebObject.Shapes.SelectAll();
			if(webObj.WebObject.Application.Selection.Count>=1){
				webObj.WebObject.Application.Selection.ShapeRange.Visible=-1;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=9;
			webObj.WebObject.Application.Selection.HeaderFooter.Shapes.SelectAll();
			if(webObj.WebObject.Application.Selection.Count>=1){
				webObj.WebObject.Application.Selection.ShapeRange.Visible=-1;
			}
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			webObj.WebObject.Application.Selection.GoTo(9,2,1,"Word.Picture.8");
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=0.5;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=10;
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=0.5;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=0;
			webObj.WebObject.Application.Selection.GoTo(9,2,1,"Word.Document.8");
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=0.5;
			}
			webObj.WebObject.Application.Selection.MoveRight(1,1);
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			webObj.WebObject.ActiveWindow.view.Type=viewtype;
		}catch(e){
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
	
		webObj.EditType="<%=mEditType%>";
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	}else if(vIndex==93){//隐藏红头
		webObj.EditType="1,0";
		try{
			var viewtype=webObj.WebObject.ActiveWindow.View.Type;
			webObj.WebObject.ActiveWindow.view.Type=3;
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			try{
				webObj.WebObject.Styles.Item("文头").Font.Color=16777215;
			}
			catch(e){}
			webObj.WebObject.Shapes.SelectAll();
			if(webObj.WebObject.Application.Selection.Count>=1){
				webObj.WebObject.Application.Selection.ShapeRange.Visible=0;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=9;
			webObj.WebObject.Application.Selection.HeaderFooter.Shapes.SelectAll();
			if(webObj.WebObject.Application.Selection.Count>=1){
				webObj.WebObject.Application.Selection.ShapeRange.Visible=0;
			}			
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			webObj.WebObject.Application.Selection.GoTo(9,2,1,"Word.Picture.8");
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=1;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=10;
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=1;
			}
			webObj.WebObject.ActiveWindow.View.SeekView=0;
			webObj.WebObject.Application.Selection.GoTo(9,2,1,"Word.Document.8");
			webObj.WebObject.Application.Selection.MoveRight(1,1,1);
			if(webObj.WebObject.Application.Selection.Type==7){
				webObj.WebObject.Application.Selection.Range.InlineShapes(1).PictureFormat.Brightness=1;
			}
			
			webObj.WebObject.Application.Selection.MoveRight(1,1);
			webObj.WebObject.Application.Selection.HomeKey(6,0);
			webObj.WebObject.ActiveWindow.view.Type=viewtype;
			
		}catch(e){
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
		webObj.EditType="<%=mEditType%>";
		webObj.WebObject.ShowRevisions=0;
		webObj.WebObject.PrintRevisions=0;
	}	
	
	 if (vIndex==1){  //打开本地文件
	      WebOpenLocal();
	   }
	   if (vIndex==2){  //保存本地文件
	      WebSaveLocal();
	   }
	   if (vIndex==4){  //保存并退出
	     SaveDocument();    //保存正文
	     webform.submit();  //提交表单
	   }
	   if (vIndex==6){  //打印文档
	      WebOpenPrint();
	   }
 }
</script>

</head>
<body class="sub-flow" onload="Load()" onunload="UnLoad()"> <!--引导和退出iWebOffice-->

<fieldset style="padding:10px;"  class="form">
<legend >模板编辑</legend>
<form name="webform" method="post" action="TemplateSave.jsp" onsubmit="return SaveDocument();"> <!--保存iWebOffice后提交表单信息-->
<input type="hidden" name="RecordID" value="<%=mRecordID%>" >
<input type="hidden" name="revision" value="">
<input type="hidden" name="FileName" value=""  >
<div align="left">
	<input type=submit value="保 存  " class="btn">
	<input type=button value="返 回  " onclick="history.back()" class="btn"></div>

<table border=0  cellspacing='0' cellpadding='0' style="width:98%; height:1400px;" class="viewTable">
<tr>
  <td align=right class="addTd" width="100px;">模版名:</td>
  <td class="TDStyle"><input type="text" name="Descript" value="<%=mDescript%>" style="width: 100%; height: 30px"></td>
</tr>
<tr>
  <td align=right class="addTd" width="100px;">次序:</td>
  <td class="TDStyle"><input type="text" name="orderBy" value="<%=orderBy%>" style="width: 100%; height: 30px" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
</tr>
<tr>
  <td align=right class="addTd" width="100px;">模版分类:</td>
  	<td class="TDStyle">
		<select id="tempType" name="tempType">
			<option value="">---请选择---</option>
			<c:forEach var="row" items="${cp:DICTIONARY('TEMPLATE_TYPE')}">
				<option value="${row.key}" label="${row.value}">
				<c:out value="${row.value}" /></option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
  <td align=right class="addTd" width="100px;">文号规则:</td>
  <td class="TDStyle"><input type="text" name="codeCode" value="<%=codeCode%>" style="width: 100%; height: 30px"></td>
</tr>
<tr>
  <!--td align=right valign=top  class="addTdStyle" width=64>内容</td-->
  <td align='right' valign='top'  width="100px;" >
<input type=button value="选择附件" class="btn"  onclick="WebOpenLocal()">
  </td>
  <td>
 <iframe src="../topName.jsp" style="position: absolute; width: 100%; height: 25px; border: none;"></iframe>
<div style="z-index: 9999999999999999; position: absolute; width: 100px; height: 0px;top: 25px; left: 100px;"></div>
        <table border="0" cellspacing="0" cellpadding="0" width='100%' height='100%' >
        <tr>
          <td id="tdMenu" bgcolor="menu">
            <!--调用iWebOffice，注意版本号，可用于升级-->
            <script src="../iWebOffice2009.js"></script>
          </td>
        </tr>
        <tr>
          <td bgcolor=menu height='20'>
		<div id=StatusBar>状态栏</div>
          </td>
        </tr>
        </table>
  </td>
</tr>
</table>
<div class="formButton">
</div>
</form>
</fieldset>
<script type="text/javascript">
_SelectItemByValue(document.getElementById('tempType'),'<%=tempType%>');
</script>
</body>
</html>
