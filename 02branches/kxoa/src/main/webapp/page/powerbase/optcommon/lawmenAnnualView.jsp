<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<%-- <sj:head locale="zh_CN" /> --%>
<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
<title>执法人员年检</title>
<c:out value="${cp:MAPVALUE('unitcode',unitForm.map.unitcode)}" />
<c:out value="${cp:MAPVALUE('usercode',unitForm.map.usercode)}" />
<link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="<s:url value="/scripts/autocomplete/autocomplete.js"/>"
	type="text/javascript"></script>
<script language="javascript"
	src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js"
	type="text/JavaScript"></script>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js"
	type="text/JavaScript"></script>
<style type="text/css">
.viewTable td {
	width: 37%;
}

.viewTable td.addTd {
	width: 13%;
}
</style>

</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			执法人员年检 </legend>
		<s:form action="lawmen" method="post" namespace="/powerbase"
			id="lawmenForm" enctype="multipart/form-data">
				<input type="hidden" name="updateType" value="${updateType}">
				<input type="hidden" name="lawmenId" value="${lawmenId}" />
				<input type="hidden" name="lawmenIds" value="${lawmenIds}">
				<input type="hidden" name="annualType" value="${annualType}">
				
				<c:if test="${annualType==1}">
			<input type="button" class="btn" value="保存" onclick="saves();" />
			   </c:if>
			   <c:if test="${annualType!=1}">
			<input type="button" class="btn" value="保存" onclick="save();" />
			   </c:if>
			   
			   
			<input type="button" class="btn" value="返回"
				onclick="javascript:history.go(-1);" />
			<c:if test="${updateType==1}">	
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" width="20%"><span style="color: red">*年检时间</td>
					<td align="left"><sj:datepicker id="annualDate"
							name="annualDate" style="width:140px" readonly="true"
							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"
							value="%{object.annualDate}" /></td>
				</tr>
				<tr>
					<td class="addTd">执法证有效期</td>
					<td align="left"><sj:datepicker id="validity" name="validity"
							style="width:140px" readonly="true" yearRange="2000:2020"
							displayFormat="yy-mm-dd" changeYear="true"
							value="%{object.validity}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130">年检说明</td>
					<td align="left"><s:textfield name="separationReason"
							value="%{object.separationReason}" />
				</tr>
			</table>
			</c:if>
			<c:if test="${updateType!=1}">	
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" width="20%"><span style="color: red">*年检时间</td>
					<td align="left"><sj:datepicker id="annualDate"
							name="annualDate" style="width:140px" readonly="true"
							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"
							value="%{object.annualDate}" /></td>
				</tr>
				<tr>
					<td class="addTd">${cp:MAPVALUE("jcry_type",updateType)}时间</td>
					<td align="left"><sj:datepicker id="separationDate" name="separationDate"
							style="width:140px" readonly="true" yearRange="2000:2020"
							displayFormat="yy-mm-dd" changeYear="true"
							value="%{object.separationDate}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130">${cp:MAPVALUE("jcry_type",updateType)}说明  </td>
					<td align="left"><s:textfield name="separationReason"
							value="%{object.separationReason}" />
				</tr>
			</table>
			</c:if>
		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function save(){
	     var form=document.getElementById("lawmenForm");
	     form.action="lawmen!annualSave.do";     
	     form.submit();
	}
	function saves(){
	     var form=document.getElementById("lawmenForm");
	     form.action="lawmen!annualSaves.do";     
	     form.submit();
	}
	</script>
</html>
