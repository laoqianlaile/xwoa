<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="voaBizBindInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="voaBizBindInfo" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="voaBizBindInfo.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="voaBizBindInfo.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.orgcode" />:</td>
						<td><s:textfield name="s_orgcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.powerid" />:</td>
						<td><s:textfield name="s_powerid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.powername" />:</td>
						<td><s:textfield name="s_powername" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.createdate" />:</td>
						<td><s:textfield name="s_createdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="voaBizBindInfo.itemtype" />:</td>
						<td><s:textfield name="s_itemtype" /> </td>
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

		<ec:table action="oa/voaBizBindInfo!list.do" items="objList" var="voaBizBindInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="voaBizBindInfos.xls" ></ec:exportXls>
			<ec:exportPdf fileName="voaBizBindInfos.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='voaBizBindInfo.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='voaBizBindInfo.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="torgcode"><s:text name='voaBizBindInfo.orgcode' /></c:set>	
				<ec:column property="orgcode" title="${torgcode}" style="text-align:center" />

				<c:set var="tpowerid"><s:text name='voaBizBindInfo.powerid' /></c:set>	
				<ec:column property="powerid" title="${tpowerid}" style="text-align:center" />

				<c:set var="tpowername"><s:text name='voaBizBindInfo.powername' /></c:set>	
				<ec:column property="powername" title="${tpowername}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='voaBizBindInfo.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='voaBizBindInfo.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tcreatedate"><s:text name='voaBizBindInfo.createdate' /></c:set>	
				<ec:column property="createdate" title="${tcreatedate}" style="text-align:center" />

				<c:set var="titemtype"><s:text name='voaBizBindInfo.itemtype' /></c:set>	
				<ec:column property="itemtype" title="${titemtype}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/voaBizBindInfo!view.do?djId=${voaBizBindInfo.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/voaBizBindInfo!edit.do?djId=${voaBizBindInfo.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/voaBizBindInfo!delete.do?djId=${voaBizBindInfo.djId}' 
							onclick='return confirm("${deletecofirm}voaBizBindInfo?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
