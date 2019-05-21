<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaScheduleResponse.view.title" /></title>
</head>

<body>
	<p class="ctitle">
		<s:text name="oaScheduleResponse.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>
	 <c:if test="${F eq viewtype}">

	<a
		href='oa/oaScheduleResponse!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}'
		property="none"> <s:text name="opt.btn.back" />
	</a>
	</c:if>
	<p>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

		<!-- 				<tr> -->
		<!-- 					<td class="TDTITLE"> -->
		<%-- 						<s:text name="oaScheduleResponse.no" /> --%>
		<!-- 					</td> -->
		<!-- 					<td align="left"> -->
		<%-- 						<s:property value="%{no}" /> --%>
		<!-- 					</td> -->
		<!-- 				</tr> -->
		<!-- 				<tr> -->
		<!-- 					<td class="TDTITLE"> -->
		<%-- 						<s:text name="oaScheduleResponse.schno" /> --%>
		<!-- 					</td> -->
		<!-- 					<td align="left"> -->
		<%-- 						<s:property value="%{schno}" /> --%>
		<!-- 					</td> -->
		<!-- 				</tr>	 -->

		<tr>
			<td class="TDTITLE"><s:text name="oaScheduleResponse.resType" />
			</td>
			<td align="left">${cp:MAPVALUE('oaResType',object.resType)}</td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="oaScheduleResponse.usercode" />
			</td>
			<td align="left">${cp:MAPVALUE('usercode',object.usercode)}</td>
		</tr>
		<c:if test="${'2' eq object.resType }">
			<tr>
				<td class="TDTITLE"><s:text name="oaScheduleResponse.stopTime" />
				</td>
				<td align="left"><s:date name="%{object.stopTime}" format="yyyy-MM-dd  HH:mm" />
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="TDTITLE"><s:text name="oaScheduleResponse.remark" />
			</td>
			<td align="left"><s:property value="%{object.remark}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="oaScheduleResponse.createdate" />
			</td>
			<td align="left"><s:date name="%{object.createdate}"
					format="yyyy-MM-dd" /></td>
		</tr>

	</table>
</body>
</html>
