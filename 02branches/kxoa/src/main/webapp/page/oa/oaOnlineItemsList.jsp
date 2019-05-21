<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaOnlineItems.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaOnlineItems" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaOnlineItems.itemid" />:</td>
						<td><s:textfield name="s_itemid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaOnlineItems.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaOnlineItems.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
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

		<ec:table action="oa/oaOnlineItems!list.do" items="objList" var="oaOnlineItems"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
		<%-- 	<ec:exportXls fileName="oaOnlineItemss.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaOnlineItemss.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

				<c:set var="titemid"><s:text name='oaOnlineItems.itemid' /></c:set>	
				<ec:column property="itemid" title="${titemid}" style="text-align:center" />


				<c:set var="tno"><s:text name='oaOnlineItems.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaOnlineItems.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaOnlineItems!view.do?itemid=${oaOnlineItems.itemid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaOnlineItems!edit.do?itemid=${oaOnlineItems.itemid}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaOnlineItems!delete.do?itemid=${oaOnlineItems.itemid}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
