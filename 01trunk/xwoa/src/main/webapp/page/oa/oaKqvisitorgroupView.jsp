<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaKqvisitorgroup.view.title" /></title>
</head>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p><s:text name="oaKqvisitorgroup.edit.title" /></p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaKqvisitorgroup" method="post" namespace="/oa"
			id="oaKqvisitorgroupForm">
			<div class="formButtonOfMail">
			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />
			</div>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.approvalremark" /></td>
					<td align="left"><s:property value="%{approvalremark}" /></td>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.approtime" /></td>	
					<td align="left"  colspan="3"><s:date name="%{approtime}"  format="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.bodycontent" /></td>
					<td align="left"><s:property value="%{bodycontent}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.approval" /></td>
					<td align="left"><s:property value="%{approval}" /></td>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.jdtype" /></td>
					<td align="left">${cp:MAPVALUE("jdType",jdtype)}</td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.travel" /></td>
					<td align="left"><s:property value="%{travel}" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.leavetime" /></td>
					<td align="left"><s:property value="%{leavetime}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.lodgingcase" /></td>
					<td align="left"><s:property value="%{lodgingcase}" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.mealplace" /></td>
					<td align="left"><s:property value="%{mealplace}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.mealcase" /></td>
					<td align="left"><s:property value="%{mealcase}" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.kqdepname" /></td>
					<td align="left"><s:property value="%{kqdepname}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.meetplase" /></td>
					<td align="left"><s:property value="%{meetplase}" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.remark" /></td>
					<td align="left"><s:property value="%{remark}" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
