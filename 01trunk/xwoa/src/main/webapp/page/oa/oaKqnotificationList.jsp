<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaKqnotification.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaKqnotification" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaKqnotification.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaKqnotification.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.periods" />:</td>
						<td><s:textfield name="s_periods" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.kqdepname" />:</td>
						<td><s:textfield name="s_kqdepname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.kqtime" />:</td>
						<td><s:textfield name="s_kqtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.visitors" />:</td>
						<td><s:textfield name="s_visitors" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.contactuser" />:</td>
						<td><s:textfield name="s_contactuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.contactphone" />:</td>
						<td><s:textfield name="s_contactphone" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.kqcontent" />:</td>
						<td><s:textfield name="s_kqcontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqnotification.optId" />:</td>
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

		<ec:table action="oa/oaKqnotification!list.do" items="objList" var="oaKqnotification"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaKqnotifications.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaKqnotifications.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaKqnotification.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaKqnotification.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="tperiods"><s:text name='oaKqnotification.periods' /></c:set>	
				<ec:column property="periods" title="${tperiods}" style="text-align:center" />

				<c:set var="tkqdepname"><s:text name='oaKqnotification.kqdepname' /></c:set>	
				<ec:column property="kqdepname" title="${tkqdepname}" style="text-align:center" />

				<c:set var="tkqtime"><s:text name='oaKqnotification.kqtime' /></c:set>	
				<ec:column property="kqtime" title="${tkqtime}" style="text-align:center" />

				<c:set var="tvisitors"><s:text name='oaKqnotification.visitors' /></c:set>	
				<ec:column property="visitors" title="${tvisitors}" style="text-align:center" />

				<c:set var="tcontactuser"><s:text name='oaKqnotification.contactuser' /></c:set>	
				<ec:column property="contactuser" title="${tcontactuser}" style="text-align:center" />

				<c:set var="tcontactphone"><s:text name='oaKqnotification.contactphone' /></c:set>	
				<ec:column property="contactphone" title="${tcontactphone}" style="text-align:center" />

				<c:set var="tkqcontent"><s:text name='oaKqnotification.kqcontent' /></c:set>	
				<ec:column property="kqcontent" title="${tkqcontent}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaKqnotification.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatetime"><s:text name='oaKqnotification.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaKqnotification.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaKqnotification.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaKqnotification.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaKqnotification.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaKqnotification.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaKqnotification.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaKqnotification.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaKqnotification.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaKqnotification!view.do?djId=${oaKqnotification.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaKqnotification!edit.do?djId=${oaKqnotification.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaKqnotification!delete.do?djId=${oaKqnotification.djId}' 
							onclick='return confirm("${deletecofirm}oaKqnotification?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
