<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title>附件管理</title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>
	<fieldset>
		<legend>材料上传</legend>			
		<!-- 附件上传-->
		<iframe  id="basicsj" name="sjfj" src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}" width="100%"
				frameborder="no" scrolling="false" border="0" marginwidth="0"
				marginheight="0" onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>
		<!-- 附件上传-->	
	</fieldset>
</body>
</html>
<script type="text/javascript">
function manageStuffInfo(djId) {
	window.open("${contextPath}/sampleflow/sampleFlowManager!viewxml.do?djId=" + djId, "_self");
}

function viewFlow(flowInstId) {
	window.open("${contextPath}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
}
</script>