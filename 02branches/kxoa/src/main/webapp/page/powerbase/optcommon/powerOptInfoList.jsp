<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="powerOptInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="powerOptInfo" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td align="right"><s:text name="powerOptInfo.itemId" />:</td>
						<td><s:textfield name="s_itemId" value="%{#parameters['s_itemId']}"/> </td>
					</tr>	

					<tr >
						<td align="right">申请事项类型:</td>
						<td>
						<select name="s_applyItemType"
					style="width: 200px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('APPLYITEM')}">
							<option value="${row.key}"
								<c:if test="${param.s_applyItemType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select>
						
						</td>
					</tr>	


					<tr>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
						<td>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/powerOptInfo!list.do" items="objList" var="powerOptInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="titemId"><s:text name='powerOptInfo.itemId' /></c:set>	
				<ec:column property="itemId" title="${titemId}" style="text-align:center" />

				<c:set var="twfcode">申请事项类型</c:set>	
				<ec:column property="applyItemType" title="申请事项类型" style="text-align:center" >
				${cp:MAPVALUE("APPLYITEM",powerOptInfo.applyItemType)}
				</ec:column>

				<c:set var="tsetime"><s:text name='powerOptInfo.setime' /></c:set>	
				<ec:column property="setime" title="${tsetime}" style="text-align:center" >
				<fmt:formatDate value="${powerOptInfo.setime}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/powerOptInfo!edit.do?itemId=${powerOptInfo.itemId}&wfcode=${powerOptInfo.wfcode}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/powerOptInfo!delete.do?itemId=${powerOptInfo.itemId}&wfcode=${powerOptInfo.wfcode}' 
							onclick='return confirm("${deletecofirm}powerOptInfo?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
