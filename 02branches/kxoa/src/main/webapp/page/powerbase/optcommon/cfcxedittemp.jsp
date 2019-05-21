<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.centit.powerbase.po.*" %>
<html>

<head>
	<title>自由裁量编缉</title>
	
  
</head>
<body>
	<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm1" enctype="multipart/form-data">
		<input type="hidden" id="itemId" name="itemId">
		<input type="hidden" id="version" name="version">
		<input type="hidden" id="dis_standard" name="dis_standard">
<script type="text/javascript" language="javascript">

var item_id = window.parent.document.getElementById("itemId");
var version = window.parent.document.getElementById("version");
var dis_standard = window.parent.document.getElementById("dis_standard");
if (item_id.value)
	document.getElementById("itemId").value = item_id.value;
else 
	document.getElementById("itemId").value = window.opener.document.getElementById("itemId").value;
if (version.value)
	document.getElementById("version").value = version.value;
else
	document.getElementById("version").value = window.opener.document.getElementById("version").value;
if (dis_standard.value)
	document.getElementById("dis_standard").value = dis_standard.value;
else
	document.getElementById("dis_standard").value=window.opener.document.getElementById("dis_standard").value;
 window.onload=function()
	{
		openflow();
	};
	function openflow(){
		var form=document.getElementById("suppowerForm1");
	     form.action="${pageContext.request.contextPath}/powerbase/supPower!editcfcx.do";   
	     form.submit();
	}

</script>
</s:form>
</body>

</html>
