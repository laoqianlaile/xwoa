<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaSpecialBudget.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaSpecialBudget" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaSpecialBudget.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaSpecialBudget.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.depno" />:</td>
						<td><s:textfield name="s_depno" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.applyNo" />:</td>
						<td><s:textfield name="s_applyNo" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.meetingNo" />:</td>
						<td><s:textfield name="s_meetingNo" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.meettype" />:</td>
						<td><s:textfield name="s_meettype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.outerPersions" />:</td>
						<td><s:textfield name="s_outerPersions" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.meetingPersionsNum" />:</td>
						<td><s:textfield name="s_meetingPersionsNum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.begTime" />:</td>
						<td><s:textfield name="s_begTime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.endTime" />:</td>
						<td><s:textfield name="s_endTime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSpecialBudget.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
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

		<ec:table action="oa/oaSpecialBudget!list.do" items="objList" var="oaSpecialBudget"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaSpecialBudgets.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaSpecialBudgets.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="ttitle"><s:text name='oaSpecialBudget.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="tdepno"><s:text name='oaSpecialBudget.depno' /></c:set>	
				<ec:column property="depno" title="${tdepno}" style="text-align:center" />

				<c:set var="tapplyNo"><s:text name='oaSpecialBudget.applyNo' /></c:set>	
				<ec:column property="applyNo" title="${tapplyNo}" style="text-align:center" />

				<c:set var="tmeetingNo"><s:text name='oaSpecialBudget.meetingNo' /></c:set>	
				<ec:column property="meetingNo" title="${tmeetingNo}" style="text-align:center" />

				<c:set var="tmeettype"><s:text name='oaSpecialBudget.meettype' /></c:set>	
				<ec:column property="meettype" title="${tmeettype}" style="text-align:center" />

				<c:set var="touterPersions"><s:text name='oaSpecialBudget.outerPersions' /></c:set>	
				<ec:column property="outerPersions" title="${touterPersions}" style="text-align:center" />

				<c:set var="tmeetingPersionsNum"><s:text name='oaSpecialBudget.meetingPersionsNum' /></c:set>	
				<ec:column property="meetingPersionsNum" title="${tmeetingPersionsNum}" style="text-align:center" />

				<c:set var="tbegTime"><s:text name='oaSpecialBudget.begTime' /></c:set>	
				<ec:column property="begTime" title="${tbegTime}" style="text-align:center" />

				<c:set var="tendTime"><s:text name='oaSpecialBudget.endTime' /></c:set>	
				<ec:column property="endTime" title="${tendTime}" style="text-align:center" />

				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSpecialBudget!view.do?djId=${oaSpecialBudget.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaSpecialBudget!edit.do?djId=${oaSpecialBudget.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaSpecialBudget!delete.do?djId=${oaSpecialBudget.djId}' 
							onclick='return confirm("${deletecofirm}oaSpecialBudget?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
