<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>
			<s:text name="optRunRec.list.title" />
		</title>
	</head>

	<body>
		
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			<s:form action="optRunRec" namespace="/sys">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td width="100" align="right"><s:text name="optRunRec.actionurl" />:</td>
						<td><s:textfield name="s_actionurl" /> </td>
					</tr>	

					<tr >
						<td align="right"><s:text name="optRunRec.funcname" />:</td>
						<td><s:textfield name="s_funcname" /> </td>
					</tr>	

					<tr >
						<td align="right"><s:text name="optRunRec.lastreqtime" />:</td>
						<td><s:textfield name="s_lastreqtime" /> </td>
					</tr>	

					<tr>
						<td></td>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="sys/optRunRec!list.do" items="objList" var="optRunRec"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="optRunRecs.xls" ></ec:exportXls>
			<ec:exportPdf fileName="optRunRecs.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tactionurl"><s:text name='optRunRec.actionurl' /></c:set>	
				<ec:column property="actionurl" title="${tactionurl}" style="text-align:center" />

				<c:set var="tfuncname"><s:text name='optRunRec.funcname' /></c:set>	
				<ec:column property="funcname" title="${tfuncname}" style="text-align:center" />


				<c:set var="treqtimes"><s:text name='optRunRec.reqtimes' /></c:set>	
				<ec:column property="reqtimes" title="${treqtimes}" style="text-align:center" />
				<!-- 
				<c:set var="truntimes"><s:text name='optRunRec.runtimes' /></c:set>	
				<ec:column property="runtimes" title="${truntimes}" style="text-align:center" />
 				-->
				<c:set var="tlastreqtime"><s:text name='optRunRec.lastreqtime' /></c:set>	
				<ec:column property="lastreqtime" title="${tlastreqtime}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	

			</ec:row>
		</ec:table>

	</body>
</html>
