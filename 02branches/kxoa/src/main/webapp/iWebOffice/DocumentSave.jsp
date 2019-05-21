<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*" %>
<%
String mRecordID=request.getParameter("RecordID");
if (mRecordID==null) mRecordID="";
String mTemplate=request.getParameter("Template");

if(mTemplate != null && mTemplate.equals("NWD")){
	  mRecordID = "NWD-" + mRecordID;//区别正文单,采用 模版编号+用户编码,拟文单不需要修改，所以，每次都是新的，不需要查询模版。
}

String mSubject=request.getParameter("Subject");
String mAuthor=request.getParameter("Author");
String mFileDate=request.getParameter("FileDate");
//强行指定.doc类型
//String mFileType=request.getParameter("FileType");
String mFileType=".doc";
//String mHTMLPath=new String(request.getParameter("HTMLPath").getBytes("8859_1"));
String mStatus="READ";

int mDocumentId=0;

iDBManager2000 DbaObj=new iDBManager2000();
if (DbaObj.OpenConnection())
{
  String mysql="SELECT DocumentID,RecordID from  Document Where RecordID='" + mRecordID + "'";
  //String mFileDate=DbaObj.GetDateTime() ;
  try
  {
    ResultSet result=DbaObj.ExecuteQuery(mysql) ;
   
    if (result.next())
    {
      mysql="update Document set DocumentID=?,RecordID=?,Template=?,Subject=?,Author=?,FileDate=?,FileType=?,Status=? where RecordID='"+mRecordID+"'";
      mDocumentId=result.getInt("DocumentID");
    }
    else
    {
      mysql="insert into Document (DocumentID,RecordID,Template,Subject,Author,FileDate,FileType,Status) values (?,?,?,?,?,?,?,?)";
      mDocumentId=DbaObj.GetMaxID("Document","DocumentID");
    }

    result.close();
  }
  catch(Exception e)
  {
      System.out.println(e.toString());
  }
  java.sql.PreparedStatement prestmt=null;
  try
  {
	  if(mTemplate != null && !"".equals(mTemplate.trim())){//规避页面模版编号空的问题
	      prestmt=DbaObj.Conn.prepareStatement(mysql);
	      prestmt.setInt(1,mDocumentId);
	      prestmt.setString(2,mRecordID);
	      prestmt.setString(3,mTemplate);
	      prestmt.setString(4,mSubject);
	      prestmt.setString(5,mAuthor);
	      prestmt.setDate(6,DbaObj.GetDate());
	      prestmt.setString(7,mFileType);
	     // prestmt.setString(8,mHTMLPath);
	      prestmt.setString(8,"READ");
	
	      DbaObj.Conn.setAutoCommit(true) ;
	      prestmt.executeUpdate();
	      DbaObj.Conn.commit();
	  
%>
<script type="text/javascript">
<!--
		alert("保存成功！");
//-->
</script>
<%
	  }
  }
  catch(Exception e)
  {
      System.out.println(e.toString());
  }
  finally
  {
      prestmt.close();
  }
  DbaObj.CloseConnection() ;
}
else
{
  out.println("OpenDatabase Error") ;
}
//response.sendRedirect("test.jsp");
%>