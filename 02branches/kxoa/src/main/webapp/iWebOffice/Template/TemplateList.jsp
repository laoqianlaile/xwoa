<%@page import="com.centit.sys.service.CodeRepositoryUtil"%>
<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*,java.sql.*" %>
<%@ include file="/page/common/css.jsp"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
<title>模板管理</title>
<style>
	.viewTable { border-collapse:collapse; margin-top:10px; color:#555; }
	.viewTable,.viewTable td { border:1px solid #b8d0d6; }
	.viewTable td { padding:4px 10px 4px 10px; line-height:16px; }
	.viewTable b { color:#fff; display:block; height:30px; line-height: 30px; }
	.viewTable th { border:1px solid #B8D0D6; height:14px; padding:8px 0; text-align:center; background:#2599CD; color:#fff; font-weight:bold; }
	.viewTable td { text-align:center; color:#333; }
</style>
<script language="javascript">
function ConfirmDel(FileUrl){
	if (confirm('是否确定删除该模板！')){
		location.href=FileUrl;
	}
}
</script>
</head>
<%
String tempType = request.getParameter("tempType");
%>
<body>
<fieldset style="display:block; padding:10px;">
<legend><b>模版管理</b></legend>
<br>
<table style="" border=0  cellspacing='0' cellpadding='0' class="viewTable" >
	<tr>
		<td colspan="5" style="text-align:left;"><!--
		  <input type=button name="AddDocTemplate" value="新建word模板"  onclick="javascript:location.href='TemplateEdit.jsp?FileType=.doc';">
		  <input type=button name="AddXslTemplate" value="新建excel模板" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.xls';">-->
		  <input type=button name="AddXslTemplate" value="新建模板" class="btn" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.doc&tempType=<%=tempType%>';" />
		  <!--<input type=button name="AddDocTemplate" value="新建金山表格模板"  onclick="javascript:location.href='TemplateEdit.jsp?FileType=.et';">
		  <input type=button name="AddXslTemplate" value="新建ppt模板" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.ppt';">
		  <input type=button name="AddXslTemplate" value="新建visio模板" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.vsd';">
		 
		  <input type=button name="Return" value="返 回"  onclick="javascript:location.href='../DocumentList.jsp';">
		   -->
		</td>
	</tr>
	<tr>
		<th>模板编号</th>
		<th>模板名称</th>
		<th>模版分类</th>
		<th>文件类型</th>
		<th>操作</th>
	</tr>
<%

  iDBManager2000 DbaObj=new iDBManager2000();
  String baseSQL = "SELECT RECORDID,FILENAME,FILETYPE,DESCRIPT,TEMPTYPE,ORDERBY FROM TEMPLATE_FILE";
  
  if(tempType != null){
 	 baseSQL += " WHERE TEMPTYPE ='" + tempType +"'";
  }
  	baseSQL += " ORDER BY ORDERBY";
  
  if (DbaObj.OpenConnection())
  {
    try
    {
    	//System.out.println(baseSQL);
      ResultSet result=DbaObj.ExecuteQuery(baseSQL) ;
      while ( result.next() )
      {
        String mRecordID=result.getString("RecordID");
        String mFileName=result.getString("FileName");
        String mFileType=result.getString("FileType");
        String mDescript=result.getString("Descript");
        tempType = result.getString("tempType");
        String orderby=result.getString("ORDERBY");
%>
	<tr>
		<td align=center><%=mRecordID%>&nbsp;</td>
		<td align=center><%=mDescript%>&nbsp;</td>
		<td align=center><%=CodeRepositoryUtil.getValue("TEMPLATE_TYPE", tempType)%>&nbsp;</td>
		<td align=center><%=mFileType%>&nbsp;</td>
		<td align=center width=148 nowrap>
			<a href="javascript:location.href='TemplateEdit.jsp?RecordID=<%=mRecordID%>&FileType=<%=mFileType%>&tempType=<%=tempType%>'" >修 改</a>
			<a href="javascript:ConfirmDel('TemplateDel.jsp?RecordID=<%=mRecordID%>&tempType=<%=tempType%>');" >删 除</a>
			<a href="javascript:location.href='TemplateCopy.jsp?RecordID=<%=mRecordID%>&FileType=<%=mFileType%>&tempType=<%=tempType%>'" >复制模版</a>
		</td>
	</tr>
<%
      }
      result.close() ;
    }
    catch(Exception e)
    {
      System.out.println(e.toString());
    }
    DbaObj.CloseConnection() ;
  }
  else
  {
    out.println("OpenDatabase Error") ;
  }
%>
</table>
</fieldset>
</body>
</html>
