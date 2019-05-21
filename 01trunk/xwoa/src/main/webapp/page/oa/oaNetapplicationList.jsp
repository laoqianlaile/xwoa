<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaNetapplication.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaNetapplication" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaNetapplication.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaNetapplication.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.applydate" />:</td>
						<td><s:textfield name="s_applydate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.applyuser" />:</td>
						<td><s:textfield name="s_applyuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.remarkContent" />:</td>
						<td><s:textfield name="s_remarkContent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaNetapplication.optId" />:</td>
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

		<ec:table action="oa/oaNetapplication!list.do" items="objList" var="oaNetapplication"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaNetapplications.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaNetapplications.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaNetapplication.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaNetapplication.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="tapplydate"><s:text name='oaNetapplication.applydate' /></c:set>	
				<ec:column property="applydate" title="${tapplydate}" style="text-align:center" />

				<c:set var="tapplyuser"><s:text name='oaNetapplication.applyuser' /></c:set>	
				<ec:column property="applyuser" title="${tapplyuser}" style="text-align:center" />

				<c:set var="tremarkContent"><s:text name='oaNetapplication.remarkContent' /></c:set>	
				<ec:column property="remarkContent" title="${tremarkContent}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaNetapplication.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaNetapplication.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaNetapplication.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaNetapplication.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaNetapplication.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaNetapplication.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaNetapplication.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaNetapplication.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaNetapplication.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaNetapplication.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaNetapplication!view.do?djId=${oaNetapplication.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaNetapplication!edit.do?djId=${oaNetapplication.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaNetapplication!delete.do?djId=${oaNetapplication.djId}' 
							onclick='return confirm("${deletecofirm}oaNetapplication?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
