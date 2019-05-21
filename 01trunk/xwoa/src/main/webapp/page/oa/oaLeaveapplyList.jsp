<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaLeaveapply.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaLeaveapply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaLeaveapply.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaLeaveapply.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.applydate" />:</td>
						<td><s:textfield name="s_applydate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.endtime" />:</td>
						<td><s:textfield name="s_endtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.leavenum" />:</td>
						<td><s:textfield name="s_leavenum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.applyuser" />:</td>
						<td><s:textfield name="s_applyuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.postleve" />:</td>
						<td><s:textfield name="s_postleve" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.remarkContent" />:</td>
						<td><s:textfield name="s_remarkContent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.disploytime" />:</td>
						<td><s:textfield name="s_disploytime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.disployuser" />:</td>
						<td><s:textfield name="s_disployuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveapply.optId" />:</td>
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

		<ec:table action="oa/oaLeaveapply!list.do" items="objList" var="oaLeaveapply"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaLeaveapplys.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaLeaveapplys.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaLeaveapply.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaLeaveapply.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="tapplydate"><s:text name='oaLeaveapply.applydate' /></c:set>	
				<ec:column property="applydate" title="${tapplydate}" style="text-align:center" />

				<c:set var="tendtime"><s:text name='oaLeaveapply.endtime' /></c:set>	
				<ec:column property="endtime" title="${tendtime}" style="text-align:center" />

				<c:set var="tleavenum"><s:text name='oaLeaveapply.leavenum' /></c:set>	
				<ec:column property="leavenum" title="${tleavenum}" style="text-align:center" />

				<c:set var="tapplyuser"><s:text name='oaLeaveapply.applyuser' /></c:set>	
				<ec:column property="applyuser" title="${tapplyuser}" style="text-align:center" />

				<c:set var="tpostleve"><s:text name='oaLeaveapply.postleve' /></c:set>	
				<ec:column property="postleve" title="${tpostleve}" style="text-align:center" />

				<c:set var="tremarkContent"><s:text name='oaLeaveapply.remarkContent' /></c:set>	
				<ec:column property="remarkContent" title="${tremarkContent}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaLeaveapply.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaLeaveapply.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tdisploytime"><s:text name='oaLeaveapply.disploytime' /></c:set>	
				<ec:column property="disploytime" title="${tdisploytime}" style="text-align:center" />

				<c:set var="tdisployuser"><s:text name='oaLeaveapply.disployuser' /></c:set>	
				<ec:column property="disployuser" title="${tdisployuser}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaLeaveapply.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaLeaveapply.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaLeaveapply.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaLeaveapply.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaLeaveapply.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaLeaveapply.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaLeaveapply.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaLeaveapply.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaLeaveapply!view.do?djId=${oaLeaveapply.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaLeaveapply!edit.do?djId=${oaLeaveapply.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaLeaveapply!delete.do?djId=${oaLeaveapply.djId}' 
							onclick='return confirm("${deletecofirm}oaLeaveapply?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
