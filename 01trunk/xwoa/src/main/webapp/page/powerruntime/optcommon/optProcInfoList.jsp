<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="optProcInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="optProcInfo" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="optProcInfo.nodeinstid" />:</td>
						<td><s:textfield name="s_nodeinstid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="optProcInfo.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.nodename" />:</td>
						<td><s:textfield name="s_nodename" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.nodeinststate" />:</td>
						<td><s:textfield name="s_nodeinststate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.transcontent" />:</td>
						<td><s:textfield name="s_transcontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.ideacode" />:</td>
						<td><s:textfield name="s_ideacode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.transidea" />:</td>
						<td><s:textfield name="s_transidea" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.transdate" />:</td>
						<td><s:textfield name="s_transdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.usercode" />:</td>
						<td><s:textfield name="s_usercode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.unitcode" />:</td>
						<td><s:textfield name="s_unitcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.istrans" />:</td>
						<td><s:textfield name="s_istrans" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.optcode" />:</td>
						<td><s:textfield name="s_optcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.isrisk" />:</td>
						<td><s:textfield name="s_isrisk" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.risktype" />:</td>
						<td><s:textfield name="s_risktype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.riskdesc" />:</td>
						<td><s:textfield name="s_riskdesc" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcInfo.riskresult" />:</td>
						<td><s:textfield name="s_riskresult" /> </td>
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

		<ec:table action="powerruntime/optProcInfo!list.do" items="objList" var="optProcInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="optProcInfos.xls" ></ec:exportXls>
			<ec:exportPdf fileName="optProcInfos.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tnodeinstid"><s:text name='optProcInfo.nodeinstid' /></c:set>	
				<ec:column property="nodeinstid" title="${tnodeinstid}" style="text-align:center" />


				<c:set var="tdjId"><s:text name='optProcInfo.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />

				<c:set var="tnodename"><s:text name='optProcInfo.nodename' /></c:set>	
				<ec:column property="nodename" title="${tnodename}" style="text-align:center" />

				<c:set var="tnodeinststate"><s:text name='optProcInfo.nodeinststate' /></c:set>	
				<ec:column property="nodeinststate" title="${tnodeinststate}" style="text-align:center" />

				<c:set var="ttranscontent"><s:text name='optProcInfo.transcontent' /></c:set>	
				<ec:column property="transcontent" title="${ttranscontent}" style="text-align:center" />

				<c:set var="tideacode"><s:text name='optProcInfo.ideacode' /></c:set>	
				<ec:column property="ideacode" title="${tideacode}" style="text-align:center" />

				<c:set var="ttransidea"><s:text name='optProcInfo.transidea' /></c:set>	
				<ec:column property="transidea" title="${ttransidea}" style="text-align:center" />

				<c:set var="ttransdate"><s:text name='optProcInfo.transdate' /></c:set>	
				<ec:column property="transdate" title="${ttransdate}" style="text-align:center" />

				<c:set var="tusercode"><s:text name='optProcInfo.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />

				<c:set var="tunitcode"><s:text name='optProcInfo.unitcode' /></c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" />

				<c:set var="tistrans"><s:text name='optProcInfo.istrans' /></c:set>	
				<ec:column property="istrans" title="${tistrans}" style="text-align:center" />

				<c:set var="toptcode"><s:text name='optProcInfo.optcode' /></c:set>	
				<ec:column property="optcode" title="${toptcode}" style="text-align:center" />

				<c:set var="tisrisk"><s:text name='optProcInfo.isrisk' /></c:set>	
				<ec:column property="isrisk" title="${tisrisk}" style="text-align:center" />

				<c:set var="trisktype"><s:text name='optProcInfo.risktype' /></c:set>	
				<ec:column property="risktype" title="${trisktype}" style="text-align:center" />

				<c:set var="triskdesc"><s:text name='optProcInfo.riskdesc' /></c:set>	
				<ec:column property="riskdesc" title="${triskdesc}" style="text-align:center" />

				<c:set var="triskresult"><s:text name='optProcInfo.riskresult' /></c:set>	
				<ec:column property="riskresult" title="${triskresult}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/optProcInfo!view.do?nodeinstid=${optProcInfo.nodeinstid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/optProcInfo!edit.do?nodeinstid=${optProcInfo.nodeinstid}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/optProcInfo!delete.do?nodeinstid=${optProcInfo.nodeinstid}' 
							onclick='return confirm("${deletecofirm}optProcInfo?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
