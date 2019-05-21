<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaPowergroupDetail.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaPowergroupDetail" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaPowergroupDetail.id" />:</td>
						<td><s:textfield name="s_id" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaPowergroupDetail.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaPowergroupDetail.usercode" />:</td>
						<td><s:textfield name="s_usercode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaPowergroupDetail.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaPowergroupDetail.depid" />:</td>
						<td><s:textfield name="s_depid" /> </td>
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

		<ec:table action="oa/oaPowergroupDetail!list.do" items="objList" var="oaPowergroupDetail"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
		<%-- 	<ec:exportXls fileName="oaPowergroupDetails.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaPowergroupDetails.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

				<c:set var="tid"><s:text name='oaPowergroupDetail.id' /></c:set>	
				<ec:column property="id" title="${tid}" style="text-align:center" />


				<c:set var="tno"><s:text name='oaPowergroupDetail.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />

				<c:set var="tusercode"><s:text name='oaPowergroupDetail.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaPowergroupDetail.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tdepid"><s:text name='oaPowergroupDetail.depid' /></c:set>	
				<ec:column property="depid" title="${tdepid}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaPowergroupDetail!view.do?id=${oaPowergroupDetail.id}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaPowergroupDetail!edit.do?id=${oaPowergroupDetail.id}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaPowergroupDetail!delete.do?id=${oaPowergroupDetail.id}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
