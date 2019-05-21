<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" %>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>办理信息</title>
<!-- 可以跳过流程节点开发的阻塞 -->
<body>
<s:form action="punishTasksExecute" method="post" namespace="/punish" target="_parent" id="generalOperatorForm" >
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="djId" name="djId"  value="${djId}" />
<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">

<fieldset style=" display: block; padding: 10px;">
			<legend>
				<b>办理信息</b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" id="tb" class="viewTable" style="margin-top: 20px;">
				<tr>
					<td class="addTd" width="140">办理意见<font color="red">*</font></td>
					<td align="left">
						<input type="hidden" name="transidea" value="" id="transidea">
						<select id="ideacode"  name="ideacode" onchange="_getSelectedItemLabel(this)">
						<option VALUE="T" >通过</option>
						</select>
					</td>
				</tr>
	</table>
		</fieldset>
		
		<center style="margin-top: 10px;">
			<s:submit name="saveAndSubmit" method="submitOpt" cssClass="btn" value="提 交" />
			<s:submit name="rollBackOpt" method="rollBackOpt" cssClass="btn" value="回 退" />
			<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>
		</s:form>

</body>

</html>