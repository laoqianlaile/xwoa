<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法人员管理
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 执法人员信息查询--登记
			</legend>
			
			<s:form action="lawExecutor" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr>
						<td class="addTd" align="right">姓名:</td>
						<td><s:textfield name="s_staffname" value="%{#parameters['s_staffname']}"/></td>
						<td class="addTd" align="right">身份证号:</td>
						<td><s:textfield name="s_idcard" id="s_idcard"  value="%{#parameters['s_idcard']}" /> </td>
						<td class="addTd" align="right">证书编号:</td>
						<td><s:textfield name="s_passcode" value="%{#parameters['s_passcode']}"/></td>
						
					</tr>
					<tr>
						<td class="addTd" align="right">审核状态:</td>
						<td>
							<select id="s_auditIdeaCode" name="s_auditIdeaCode" style="width: 150px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:LVB('LAW_AUDIT')}">
									<option value="${row.value}" 
										<c:if test="${param.s_auditIdeaCode == row.value}">selected</c:if>>
										<c:out value="${row.label}" />
									</option>
								</c:forEach>
							</select>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<s:submit method="listRegister"  key="opt.btn.query" cssClass="btn"/>
							<s:reset value="重置" cssClass="btn"></s:reset>
							<input type="button" value="登记" class="btn" onclick="window.location = 'lawExecutor!built.do?backurl=${backurl}'">
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerbase/lawExecutor!listRegister.do" items="objList" var="lawExecutor"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="lawExecutors.xls" ></ec:exportXls>
			<ec:row>
				<ec:column property="passcode" title="证书编号" style="text-align:center" />

				<ec:column property="staffname" title="姓名" style="text-align:center" />

				<ec:column property="sex" title="性别" style="text-align:center">
					${cp:MAPVALUE('sex',lawExecutor.sex)}
				</ec:column>

				<ec:column property="nation" title="民族" style="text-align:center" >
					${cp:MAPVALUE('LAW_NATION',lawExecutor.nation)}
				</ec:column>

				<ec:column property="idcard" title="身份证号" style="text-align:center" />

				<ec:column property="politics" title="政治面貌" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_POLITICS',lawExecutor.politics)}
				</ec:column>

				<ec:column property="education" title="学历" style="text-align:center" >
				    ${cp:MAPVALUE('LAW_EDUCATION',lawExecutor.education)}
				</ec:column>

				<ec:column property="deptname" title="执法主体" style="text-align:center" />

				<ec:column property="position" title="职务" style="text-align:center" />

				<ec:column property="plait" title="编制情况" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_PLAIT',lawExecutor.plait)}
				</ec:column>
				
				<ec:column property="auditIdeaCode" title="审核状态" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_AUDIT',lawExecutor.auditIdeaCode)}
				</ec:column>
				
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerbase/lawExecutor!view.do?staffno=${lawExecutor.staffno}&backurl=${backurl}'>详细</a>
					<c:if test="${lawExecutor.auditIdeaCode eq '0' || lawExecutor.auditIdeaCode eq '3' || lawExecutor.auditIdeaCode == null}">
						<a href='powerbase/lawExecutor!edit.do?staffno=${lawExecutor.staffno}&backurl=${backurl}'>编辑</a>
						<a href='powerbase/lawExecutor!delete.do?staffno=${lawExecutor.staffno}&backurl=${backurl}' 
								onclick='return confirm("${deletecofirm} ${lawExecutor.staffname}?");'><s:text name="opt.btn.delete" /></a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
