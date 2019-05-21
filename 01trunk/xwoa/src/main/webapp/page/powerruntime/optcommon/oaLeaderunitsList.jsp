<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaLeaderunits.list.title" />
		</title>
			<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaLeaderunits" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaLeaderunits.leadercode" />:</td>
						<td><s:textfield name="s_leadercode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaderunits.unitcode" />:</td>
						<td><s:textfield name="s_unitcode" /> </td>
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

		<ec:table action="powerruntime/oaLeaderunits!list.do" items="objList" var="oaLeaderunits"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaLeaderunitss.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaLeaderunitss.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tleadercode"><s:text name='oaLeaderunits.leadercode' /></c:set>	
				<ec:column property="leadercode" title="${tleadercode}" style="text-align:center" />

				<c:set var="tunitcode"><s:text name='oaLeaderunits.unitcode' /></c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" />

		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/oaLeaderunits!view.do?leadercode=${oaLeaderunits.leadercode}&unitcode=${oaLeaderunits.unitcode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/oaLeaderunits!edit.do?leadercode=${oaLeaderunits.leadercode}&unitcode=${oaLeaderunits.unitcode}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/oaLeaderunits!delete.do?leadercode=${oaLeaderunits.leadercode}&unitcode=${oaLeaderunits.unitcode}' 
							onclick='return confirm("${deletecofirm}oaLeaderunits?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
