<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
		</title>
	</head>

	<body style="width:100%;">
		<%@ include file="/page/common/messages.jsp"%>	
		<fieldset style="display: block; padding: 10px;">
			<legend>
				 <b>权力、部门、流程关联信息</b>
			</legend>		
		<ec:table action="powerruntime/powerOrgInfo!listOrgList.do" items="selectList" var="powerOrgInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="titemId">权力编码</c:set>	
				<ec:column property="itemId" title="${titemId}" style="text-align:center" />

				<c:set var="tunitcode"><s:text name='powerOrgInfo.unitcode' /></c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" />


				<c:set var="twfcode"><s:text name='powerOrgInfo.wfcode' /></c:set>	
				<ec:column property="wfcode" title="${twfcode}" style="text-align:center" />

				<c:set var="tsetime"><s:text name='powerOrgInfo.setime' /></c:set>	
				<ec:column property="setime" title="${tsetime}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/powerOrgInfo!view.do?itemId=${powerOrgInfo.itemId}&unitcode=${powerOrgInfo.unitcode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/powerOrgInfo!edit.do?itemId=${powerOrgInfo.itemId}&unitcode=${powerOrgInfo.unitcode}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/powerOrgInfo!delete.do?itemId=${powerOrgInfo.itemId}&unitcode=${powerOrgInfo.unitcode}' 
							onclick='return confirm("${deletecofirm}powerOrgInfo?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>
</fieldset>
	</body>
</html>
