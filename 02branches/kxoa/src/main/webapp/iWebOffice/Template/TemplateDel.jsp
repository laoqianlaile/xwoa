<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*,java.sql.*" %>
<%
String mRecordID = request.getParameter("RecordID");
String operType=request.getParameter("operType");
iDBManager2000 DbaObj=new iDBManager2000();
if (DbaObj.OpenConnection())
{
  java.sql.PreparedStatement prestmt=null;
  String mSql="Delete from Template_File where RecordID = '"+ mRecordID + "'";
  prestmt =DbaObj.Conn.prepareStatement(mSql);
  DbaObj.Conn.setAutoCommit(true) ;
  prestmt.execute();
  DbaObj.Conn.commit();
  prestmt.close();
}
DbaObj.CloseConnection();

response.sendRedirect("TemplateList.jsp?operType="+operType);
%>

