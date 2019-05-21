<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="powerOrgInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="powerOrgInfo" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="powerOrgInfo.itemId" />:</td>
						<td><s:textfield name="s_itemId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="powerOrgInfo.unitcode" />:</td>
						<td><s:textfield name="s_unitcode" /> </td>
					</tr>	


					<tr >
						<td><s:text name="powerOrgInfo.wfcode" />:</td>
						<td><s:textfield name="s_wfcode" /> </td>
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

		<ec:table action="powerruntime/powerOrgInfo!list.do" items="objList" var="powerOrgInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="powerOrgInfos.xls" ></ec:exportXls>
			<ec:exportPdf fileName="powerOrgInfos.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="titemId"><s:text name='powerOrgInfo.itemId' /></c:set>	
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

	</body>
</html>
