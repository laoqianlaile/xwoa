<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaConsumablesParts.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaConsumablesParts" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaConsumablesParts.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaConsumablesParts.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.applydate" />:</td>
						<td><s:textfield name="s_applydate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.applyuser" />:</td>
						<td><s:textfield name="s_applyuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.unitcode" />:</td>
						<td><s:textfield name="s_unitcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.consContent" />:</td>
						<td><s:textfield name="s_consContent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.consNum" />:</td>
						<td><s:textfield name="s_consNum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.partsContent" />:</td>
						<td><s:textfield name="s_partsContent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.partsNum" />:</td>
						<td><s:textfield name="s_partsNum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaConsumablesParts.optId" />:</td>
						<td><s:textfield name="s_optId" /> </td>
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

		<ec:table action="oa/oaConsumablesParts!list.do" items="objList" var="oaConsumablesParts"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaConsumablesPartss.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaConsumablesPartss.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaConsumablesParts.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaConsumablesParts.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="tapplydate"><s:text name='oaConsumablesParts.applydate' /></c:set>	
				<ec:column property="applydate" title="${tapplydate}" style="text-align:center" />

				<c:set var="tapplyuser"><s:text name='oaConsumablesParts.applyuser' /></c:set>	
				<ec:column property="applyuser" title="${tapplyuser}" style="text-align:center" />

				<c:set var="tunitcode"><s:text name='oaConsumablesParts.unitcode' /></c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" />

				<c:set var="tconsContent"><s:text name='oaConsumablesParts.consContent' /></c:set>	
				<ec:column property="consContent" title="${tconsContent}" style="text-align:center" />

				<c:set var="tconsNum"><s:text name='oaConsumablesParts.consNum' /></c:set>	
				<ec:column property="consNum" title="${tconsNum}" style="text-align:center" />

				<c:set var="tpartsContent"><s:text name='oaConsumablesParts.partsContent' /></c:set>	
				<ec:column property="partsContent" title="${tpartsContent}" style="text-align:center" />

				<c:set var="tpartsNum"><s:text name='oaConsumablesParts.partsNum' /></c:set>	
				<ec:column property="partsNum" title="${tpartsNum}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaConsumablesParts.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaConsumablesParts.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaConsumablesParts.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaConsumablesParts.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaConsumablesParts.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaConsumablesParts.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaConsumablesParts.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaConsumablesParts.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaConsumablesParts.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaConsumablesParts.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaConsumablesParts.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaConsumablesParts!view.do?djId=${oaConsumablesParts.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaConsumablesParts!edit.do?djId=${oaConsumablesParts.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaConsumablesParts!delete.do?djId=${oaConsumablesParts.djId}' 
							onclick='return confirm("${deletecofirm}oaConsumablesParts?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
