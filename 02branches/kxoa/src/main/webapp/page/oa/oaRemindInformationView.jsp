<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaRemindInformation.view.title" /></title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
				<s:text name="oaRemindInformation.view.title" />
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<%-- <a href='oa/oaRemindInformation!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none"> --%>
		<%-- 	<s:text name="opt.btn.back" /> --%>


		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
		<%-- 	<tr>
				<td class="addTd"><s:text name="oaRemindInformation.no" /></td>
				<td align="left" colspan="3"><s:property value="%{no}" /></td>
			</tr> --%>


			<tr>
				<td class="addTd"><s:text name="oaRemindInformation.title" />
				</td>
				<td align="left"><s:property value="%{title}" /></td>
				<td class="addTd"><s:text name="oaRemindInformation.reType" />
				</td>
				<td align="left">${cp:MAPVALUE("oa_superviseType",object.reType) }</td>

				<%-- 	<tr>
					<td class="addTd">
						<s:text name="oaRemindInformation.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>	 --%>


				<%-- <td class="addTd"><s:text name="oaRemindInformation.thesign" />
				</td>
				<td align="left">${cp:MAPVALUE("oa_thesign",thesign) }</td> --%>
			</tr>

			<%-- <tr>
				<td class="addTd"><s:text name="oaRemindInformation.djid" /></td>
				<td align="left"><s:property value="%{djid}" /></td>

				<td class="addTd"><s:text name="oaRemindInformation.reType" />
				</td>
				<td align="left">${cp:MAPVALUE("oa_superviseType",object.reType) }</td>
			</tr> --%>

			<tr>
				<td class="addTd"><s:text name="oaRemindInformation.begtime" />
				</td>
				<td align="left"><s:date name="begtime"
						format="yyyy-MM-dd HH:mm" /></td>

				<td class="addTd"><s:text name="oaRemindInformation.endtime" />
				</td>
				<td align="left"><s:date name="endtime"
						format="yyyy-MM-dd HH:mm" /></td>
			</tr>

			<%-- 	<tr>
					<td class="addTd">
						<s:text name="oaRemindInformation.frequency" />
					</td>
					<td align="left">
						<s:property value="%{frequency}" />
					</td>
				</tr>	 --%>

			<tr>
				<td class="addTd"><s:text name="oaRemindInformation.remark" />
				</td>
				<td align="left" colspan="3"><s:property value="%{remark}" />
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaRemindInformation.creater" />
				</td>
				<td align="left">${cp:MAPVALUE("usercode",creater) }</td>

				<td class="addTd"><s:text name="oaRemindInformation.createtime" />
				</td>
				<td align="left"><s:date name="createtime"
						format="yyyy-MM-dd HH:mm" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text
						name="oaRemindInformation.createRemark" /></td>
				<td align="left" colspan="3"><s:property
						value="%{createRemark}" /></td>
			</tr>

		</table>
		<div class="formButton">
			<!-- <input type="button" name="back" Class="btn"
				onclick="doback();" value="返回" /> -->
				<input type="button" name="back" Class="btn"
				onclick="window.location.href='${pageContext.request.contextPath}/oa/oaRemindInformation!recipientList.do?s_bizType=0'" value="返回" />
		</div>
	</fieldset>


</body>

<script type="text/javascript">
	function doback(){		
		window.location="${contextPath}/oa/oaRemindInformation!recipientList.do?s_bizType=0";
		
	}

</script>

</html>
