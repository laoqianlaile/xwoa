<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@page import="com.centit.monitor.po.Pamonthpunish"%>
<html>
<head>
<title>行政诉讼</title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
</head>

<body >

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			诉讼查看
		</legend>
		<s:form action="lawsuit" method="post" namespace="/powerbase"  enctype="multipart/form-data">
			
			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	
			<tr>
				<td class="addTd"><s:text name="lawsuit.lawsuitno" /></td>
				<td align="left" colspan="3"><s:property value="%{lawsuitno}" /></td>
				
			</tr>
		<tr>
				<td class="addTd"><s:text name="lawsuit.lawsuitdate" /></td>
				<td align="left" colspan="3"><fmt:formatDate value='${phobject.lawsuitdate}' pattern='yyyy-MM-dd hh:mm:ss' /> </td>
			
			</tr>
			<tr>
				<td class="addTd"><s:text name="lawsuit.lawsuitapplyunit" /></td>
				<td align="left">${cp:MAPEXPRESSION
			        ("depno",phobject.lawsuitapplyunit)}</td>

				<td class="addTd"><s:text name="lawsuit.lawsuitdep" /></td>
				<td align="left">${cp:MAPEXPRESSION
			        ("depno",phobject.lawsuitdep)}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="lawsuit.lawsuitenddate" /></td>
				<td align="left" colspan="3"><fmt:formatDate value='${phobject.lawsuitenddate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="lawsuit.lawsuitresult" /></td>
				<td align="left" colspan="3">${cp:MAPEXPRESSION
			        ("LawsuitResult",phobject.lawsuitresult)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="lawsuit.bookoperator" /></td>
				<td align="left" colspan="3"><s:property value="%{bookoperator}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="lawsuit.bookdate" /></td>
				<td align="left" colspan="3"><fmt:formatDate value='${phobject.bookdate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>
			</table>
		<%-- 	<fieldset> 
 			<legend>审核信息</legend> 
 			<table border="0" cellpadding="0" cellspacing="0" class="viewTable"> 
				<tr> 
					<td class="addTd"><s:text name="增减分审批人" /></td>
				<td align="left"><s:property value="%{auditor}" /></td>

						<td class="addTd"><s:text name="增减分审批时间" /></td>
				<td align="left"><fmt:formatDate value='${phobject.auditDate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
 		
			<tr>
				<td class="addTd"><s:text name="pamonthpunish.auditResult" /></td>
				<td align="left" colspan="3">
					 <s:if
						test='%{ auditResult==\"0\"}'>不同意</s:if> <s:if
						test='%{ auditResult==\"1\"}'>同意</s:if>
				</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="pamonthpunish.auditDesc" /></td>
				<td align="left" colspan="3"><s:property value="%{auditDesc}" /></td>
			</tr>
	</table> 
 			</fieldset>  --%>
		</s:form>
	</fieldset>
	<script type="text/javascript">
	function checkItemType() {
		var statType = document.getElementById("statType");
		
		var chargeBasis_tr = document.getElementById("chargeBasis_tr");
		var charge_tr = document.getElementById("charge_tr");

		if (statType.value == '1') {
			chargeBasis_tr.style.display = "none";
			charge_tr.style.display = "block";
			
		} else {
			chargeBasis_tr.style.display = "block";
			charge_tr.style.display = "none";
		}


	}
	</script>
</body>
</html>
