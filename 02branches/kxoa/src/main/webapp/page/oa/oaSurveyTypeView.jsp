<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSurveyType.view.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaSurveyType.view.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>




		<table width="200" border="0" cellpadding="1" cellspacing="1"
			class="viewTable">

			<!-- 				<tr> -->
			<!-- 					<td class="addTd"> -->
			<%-- 						<s:text name="oaSurveyType.no" /> --%>
			<!-- 					</td> -->
			<!-- 					<td align="left"> -->
			<%-- 						<s:property value="%{no}" /> --%>
			<!-- 					</td> -->
			<!-- 				</tr> -->


			<tr>
				<td class="addTd"><s:text name="oaSurveyType.reType" /></td>
				<td align="left"><s:property value="%{reType}" /></td>
			</tr>

			<!-- 				<tr> -->
			<!-- 					<td class="addTd"> -->
			<%-- 						<s:text name="oaSurveyType.comName" /> --%>
			<!-- 					</td> -->
			<!-- 					<td align="left"> -->
			<%-- 						<s:property value="%{comName}" /> --%>
			<!-- 					</td> -->
			<!-- 				</tr>	 -->

			<tr>
				<td class="addTd"><s:text name="oaSurveyType.remark" /></td>
				<td align="left"><s:property value="%{remark}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaSurveyType.state" /></td>
				<td align="left">
					<%-- 						<s:property value="%{state}" /> --%>
					${cp:MAPVALUE("equState",object.state)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaSurveyType.creater" /></td>
				<td align="left">
					<%-- 						<s:property value="%{creater}" /> --%>
					${cp:MAPVALUE('usercode',object.creater)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaSurveyType.creatertime" /></td>
				<td align="left">
					<%-- 						<s:property value="%{creatertime}" /> --%> <fmt:formatDate
						value="${object.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>

		</table>
		<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="javascript:window.location.href='oa/oaSurveyType!list.do';"
				value="返回" />
		</div>

	</fieldset>
</body>
</html>
