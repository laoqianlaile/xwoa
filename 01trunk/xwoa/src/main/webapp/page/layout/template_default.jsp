<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><sitemesh:title/></title>
    <%@include file="/page/common/header.jsp" %>
    <sitemesh:head/>
</head>
<body <sitemesh:getProperty property="body.style" writeEntireProperty="1"/>>
   <sitemesh:body/>
</body>
</html>