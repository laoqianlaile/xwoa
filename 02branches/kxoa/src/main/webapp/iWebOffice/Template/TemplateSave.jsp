<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*,java.sql.*" %>
<%
String mRecordID=request.getParameter("RecordID");
String mFileName=request.getParameter("FileName");
String mDescript=request.getParameter("Descript");
String tempType = request.getParameter("tempType");
String mOrderBy = request.getParameter("orderBy");
String mCodeCode = request.getParameter("codeCode");
System.out.println("mCodeCode="+mCodeCode);
iDBManager2000 DbaObj=new iDBManager2000();
if (DbaObj.OpenConnection())
{
  java.sql.PreparedStatement prestmt=null;
  String mSql="Update Template_File Set FileName = '"+ mFileName 
  +"',Descript = '"  + mDescript 
  
  +"',TEMPTYPE = '"  + tempType 
  
  +"',ORDERBY = '"  + mOrderBy
  
  +"',CODECODE = '"  + mCodeCode
  
  +"' Where RecordID='"+ mRecordID +"'";

  //System.out.println(mSql);
  prestmt =DbaObj.Conn.prepareStatement(mSql);
  DbaObj.Conn.setAutoCommit(true) ;
  prestmt.execute();
  DbaObj.Conn.commit();
  prestmt.close();
}
DbaObj.CloseConnection();
response.sendRedirect(request.getContextPath()+"/powerruntime/templateFile!list.do?");
%>
