<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="useroptLog.edit.title" /></title>
</head>

<body class="sub-flow">
	<p class="ctitle">
		<s:text name="useroptLog.edit.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="useroptLog" method="post" namespace="/sys"
		id="useroptLogForm">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />

		<table cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.id" /></td>
				<td align="left"><s:textfield name="id" size="40" /></td>
			</tr>


			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.createtime" /></td>
				<td align="left"><s:textfield name="createtime" size="40" /></td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.createuser" /></td>
				<td align="left"><s:textfield name="createuser" size="40" /></td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.bizname" /></td>
				<td align="left"><s:textarea name="bizname" cols="40" rows="2" />


				</td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.biztype" /></td>
				<td align="left"><s:textfield name="biztype" size="40" /></td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.runerrortype" />
				</td>
				<td align="left"><s:textfield name="runerrortype" size="40" />

				</td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.createrip" /></td>
				<td align="left"><s:textfield name="createrip" size="40" /></td>
			</tr>

			<tr>
				<td class="TDTITLE"><s:text name="useroptLog.remark" /></td>
				<td align="left"><s:textarea name="remark" cols="40" rows="2" />


				</td>
			</tr>

		</table>


	</s:form>