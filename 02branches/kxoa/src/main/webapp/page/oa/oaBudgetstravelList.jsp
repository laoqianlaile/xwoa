<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaBudgetstravel.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaBudgetstravel" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaBudgetstravel.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBudgetstravel.depno" />:</td>
						<td><s:textfield name="s_depno" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBudgetstravel.meetingNo" />:</td>
						<td><s:textfield name="s_meetingNo" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBudgetstravel.begTime" />:</td>
						<td><s:textfield name="s_begTime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBudgetstravel.endTime" />:</td>
						<td><s:textfield name="s_endTime" /> </td>
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

		<ec:table action="oa/oaBudgetstravel!list.do" items="objList" var="oaBudgetstravel"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaBudgetstravels.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaBudgetstravels.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				<c:set var="ttitle"><s:text name='oaBudgetstravel.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="tdepno"><s:text name='oaBudgetstravel.depno' /></c:set>	
				<ec:column property="depno" title="${tdepno}" style="text-align:center" />

				<c:set var="tmeetingNo"><s:text name='oaBudgetstravel.meetingNo' /></c:set>	
				<ec:column property="meetingNo" title="${tmeetingNo}" style="text-align:center" />

				<c:set var="tbegTime"><s:text name='oaBudgetstravel.begTime' /></c:set>	
				<ec:column property="begTime" title="${tbegTime}" style="text-align:center" />

				<c:set var="tendTime"><s:text name='oaBudgetstravel.endTime' /></c:set>	
				<ec:column property="endTime" title="${tendTime}" style="text-align:center" />

				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaBudgetstravel!view.do?djId=${oaBudgetstravel.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaBudgetstravel!edit.do?djId=${oaBudgetstravel.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaBudgetstravel!delete.do?djId=${oaBudgetstravel.djId}' 
							onclick='return confirm("${deletecofirm}oaBudgetstravel?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
