<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<head>
		<title>
			执行人员信息
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	</head>
<body>
<%@ include file="/page/common/messages.jsp"%>
	<s:form action="lawmen" method="post" namespace="/powerbase" id="lawmenForm" enctype="multipart/form-data">
	<input type="button" value="保存" class="btn" onclick="shSave()"/>
	<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
	<input id="userId" type="hidden" name="userId" value="${object.userId}" />
	<input id="lawmenId" type="hidden" name="lawmenId" value="${object.lawmenId}" />
<fieldset>
<legend><b>执行人员基本信息</b></legend>
	<table border="0" cellpadding="1" cellspacing="1" class="viewTable">
			<tr>
					<td class="addTd" width="20%"><s:text name="lawmen.userName" /></td>
					<td align="left">${object.userName}</td>
					<td class="addTd" width="130">部门名称</td>
					<td align="left">${cp:MAPVALUE("depno",deptcode)}</td>
					</tr>
				<tr>
				<td class="addTd" >出生年月</td>	
				<td align="left"><fmt:formatDate value='${object.birth}' pattern='yyyy-MM-dd' /></td>
					<td class="addTd"><s:text name="lawmen.sex" /></td>
				<td align="left">
				${cp:MAPVALUE("sex",sex)}
				</td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="lawmen.politicalLandscape" /></td>
					<td align="left">${cp:MAPVALUE("POLLANDSCAPE",politicalLandscape)}</td>
					<td class="addTd" width="130"><s:text name="lawmen.education" /></td>
					<td align="left">${cp:MAPVALUE("EDUCATION",education)}</td>
				</tr>
				<tr>
				<td class="addTd" ><s:text name="lawmen.tel" /></td>	
				<td class="left" >${object.tel}</td>
				<td class="addTd" width="130"><s:text name="lawmen.position" /></td>
					<td align="left">${cp:MAPVALUE("POSITION",position)}</td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="lawmen.organization" /></td>
					<td align="left">${cp:MAPVALUE("ORGANIZATION",organization)}</td>
					<td class="addTd" width="130"><s:text name="lawmen.updateType" /></td>
					<td align="left">${cp:MAPVALUE("jcry_type",updateType)}</td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="执法证编号" /></td>
					<td align="left">${cp:MAPVALUE("ORGANIZATION",userId)}</td>
					<td class="addTd" width="130"><s:text name="执法种类" /></td>
					<td align="left">${cp:MAPVALUE("jcry_type",legalRange)}</td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="发证机关" /></td>
					<td align="left">${cp:MAPVALUE("ORGANIZATION",giveCertOrgan)}</td>
					<td class="addTd" width="130"><s:text name="执法证有效期" /></td>
					<td align="left">${cp:MAPVALUE("jcry_type",legalArea)}</td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="lawmen.datesource" /></td>
					<td align="left"><s:if
						test='%{datesource==\"1\"}'>本级数据</s:if> <s:if
						test='%{ datesource==\"2\"}'>自建系统上报数据</s:if>
				</td></td>
					<td class="addTd" width="130"><s:text name="lawmen.updateDate" /></td>
					<td align="left"><fmt:formatDate value='${updateDate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
				</tr>
		</table>
		</fieldset>
		<fieldset>
			<legend class="ctitle" style="width: auto; margin-bottom: 5px;"><b>审核信息</b></legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" width="130"><span style="color: red">*</span>是否通过</td>
						<td  colspan="3"><select name="isPass" id="isPass">
							<c:forEach var="row" items="${cp:DICTIONARY('isPass')}">
								<option value="${row.key}"
									<c:if test="${isPass eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			<%-- 	<tr>
					<td class="addTd" width="130"><span style="color: red">*</span>审核意见</td>
					<td align="left" colspan="4">
					<s:textarea name="auditReason" id="auditReason" cols="40" rows="2" style="width:98%;height:40px;" /></td>
				</tr> --%>
			</table>
			</fieldset>
		</s:form>
</body>
<script type="text/javascript">
function shSave(){
	 var isPass=document.getElementById("isPass").value;
    if(''==isPass){
   	 alert("请选择是否通过");
   	 document.forms[0].isPass.focus();
   	 return;
    }
    var form=document.getElementById("lawmenForm");
    form.action="lawmen!updateStateSh.do?isPass="+isPass;     
    form.submit();
}
</script>
</html> 
