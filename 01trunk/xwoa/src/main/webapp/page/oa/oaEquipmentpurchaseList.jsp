<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaEquipmentpurchase.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaEquipmentpurchase" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaEquipmentpurchase.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaEquipmentpurchase.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.itentype" />:</td>
						<td><s:textfield name="s_itentype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.ecategory" />:</td>
						<td><s:textfield name="s_ecategory" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.tmodel" />:</td>
						<td><s:textfield name="s_tmodel" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.thenumber" />:</td>
						<td><s:textfield name="s_thenumber" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.theprice" />:</td>
						<td><s:textfield name="s_theprice" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.serialnumber" />:</td>
						<td><s:textfield name="s_serialnumber" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.applydate" />:</td>
						<td><s:textfield name="s_applydate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.applyuser" />:</td>
						<td><s:textfield name="s_applyuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.remarkContent" />:</td>
						<td><s:textfield name="s_remarkContent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaEquipmentpurchase.optId" />:</td>
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

		<ec:table action="oa/oaEquipmentpurchase!list.do" items="objList" var="oaEquipmentpurchase"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaEquipmentpurchases.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaEquipmentpurchases.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaEquipmentpurchase.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaEquipmentpurchase.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="titentype"><s:text name='oaEquipmentpurchase.itentype' /></c:set>	
				<ec:column property="itentype" title="${titentype}" style="text-align:center" />

				<c:set var="tecategory"><s:text name='oaEquipmentpurchase.ecategory' /></c:set>	
				<ec:column property="ecategory" title="${tecategory}" style="text-align:center" />

				<c:set var="ttmodel"><s:text name='oaEquipmentpurchase.tmodel' /></c:set>	
				<ec:column property="tmodel" title="${ttmodel}" style="text-align:center" />

				<c:set var="tthenumber"><s:text name='oaEquipmentpurchase.thenumber' /></c:set>	
				<ec:column property="thenumber" title="${tthenumber}" style="text-align:center" />

				<c:set var="ttheprice"><s:text name='oaEquipmentpurchase.theprice' /></c:set>	
				<ec:column property="theprice" title="${ttheprice}" style="text-align:center" />

				<c:set var="tserialnumber"><s:text name='oaEquipmentpurchase.serialnumber' /></c:set>	
				<ec:column property="serialnumber" title="${tserialnumber}" style="text-align:center" />

				<c:set var="tapplydate"><s:text name='oaEquipmentpurchase.applydate' /></c:set>	
				<ec:column property="applydate" title="${tapplydate}" style="text-align:center" />

				<c:set var="tapplyuser"><s:text name='oaEquipmentpurchase.applyuser' /></c:set>	
				<ec:column property="applyuser" title="${tapplyuser}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaEquipmentpurchase.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tremarkContent"><s:text name='oaEquipmentpurchase.remarkContent' /></c:set>	
				<ec:column property="remarkContent" title="${tremarkContent}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaEquipmentpurchase.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaEquipmentpurchase.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaEquipmentpurchase.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaEquipmentpurchase.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaEquipmentpurchase.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaEquipmentpurchase.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaEquipmentpurchase.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaEquipmentpurchase.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaEquipmentpurchase.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaEquipmentpurchase.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaEquipmentpurchase!view.do?djId=${oaEquipmentpurchase.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaEquipmentpurchase!edit.do?djId=${oaEquipmentpurchase.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaEquipmentpurchase!delete.do?djId=${oaEquipmentpurchase.djId}' 
							onclick='return confirm("${deletecofirm}oaEquipmentpurchase?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
