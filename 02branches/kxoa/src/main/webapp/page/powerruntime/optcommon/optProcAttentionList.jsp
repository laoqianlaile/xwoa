<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="optProcAttention.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="optProcAttention" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td class="addTd">关注设置时间:</td>
						<td width="180"><s:textfield name="s_attsettime" /> </td>
						<td class="addTd">关注设置人员:</td>
						<td width="180"><s:textfield name="s_attsetuser" /> </td>
						<td class="addTd">已关注:</td>
						<td width="100"><s:checkbox name="s_isAtt" value="T" /> </td>
						<td>
							<s:submit method="listAtt"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/optProcAttention!listAtt.do" items="attentionList" var="optProcAttention"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="transaffairname" title="办件名称" style="text-align:center" >
				<a href="<%=request.getContextPath()%>/wwd/srPermitApply!generalOptView.do?djId=${optProcAttention.djId}">${optProcAttention.transaffairname}</a>
				</ec:column>
				<ec:column property="userCode" title="审阅人" style="text-align:center" >
				${cp:MAPVALUE("usercode",optProcAttention.userCode)}
				</ec:column>
				<ec:column property="attSetTime" title="关注设置时间" style="text-align:center" />

				<ec:column property="attSetUser" title="关注设置人员" style="text-align:center" >
				${cp:MAPVALUE("usercode",optProcAttention.attSetUser)}
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set><!--
					<a href='powerruntime/optProcAttention!view.do?djId=${optProcAttention.djId}&usercode=${optProcAttention.userCode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					-->
					<c:if test="${optProcAttention.isAtt ne '1'}">
					<a href='powerruntime/optProcAttention!edit.do?cid.djId=${optProcAttention.djId}&cid.userCode=${optProcAttention.userCode}'>关注</a>
					</c:if>
					&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/wwd/srPermitApply!generalOptView.do?djId=${optProcAttention.djId}">办件查看</a>
					<!--<a href='powerruntime/optProcAttention!delete.do?djId=${optProcAttention.djId}&usercode=${optProcAttention.userCode}' 
							onclick='return confirm("${deletecofirm}optProcAttention?");'><s:text name="opt.btn.delete" /></a>-->
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
