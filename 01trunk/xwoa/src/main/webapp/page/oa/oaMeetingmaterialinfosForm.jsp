<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaMeetingmaterialinfos.edit.title" /></title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaMeetingmaterialinfos.edit.title" />
			</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaMeetingmaterialinfos" method="post" namespace="/oa"
			id="oaMeetingmaterialinfosForm">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />

			<table width="200" border="0" cellpadding="1" cellspacing="1">

				<tr>
					<td class="TDTITLE"><s:text name="oaMeetingmaterialinfos.djId" />
					</td>
					<td align="left"><s:textfield name="djId" size="40" /></td>
				</tr>

				<tr>
					<td class="TDTITLE"><s:text
							name="oaMeetingmaterialinfos.meetingAttendee" /></td>
					<td align="left"><s:textfield name="meetingAttendee" size="40" />

					</td>
				</tr>


				<tr>
					<td class="TDTITLE"><s:text
							name="oaMeetingmaterialinfos.createtime" /></td>
					<td align="left"><s:textfield name="createtime" size="40" /></td>
				</tr>

				<tr>
					<td class="TDTITLE"><s:text
							name="oaMeetingmaterialinfos.isback" /></td>
					<td align="left"><s:textfield name="isback" size="40" /></td>
				</tr>

				<tr>
					<td class="TDTITLE"><s:text
							name="oaMeetingmaterialinfos.backtime" /></td>
					<td align="left"><s:textfield name="backtime" size="40" /></td>
				</tr>

			</table>


		</s:form>
	</fieldset>