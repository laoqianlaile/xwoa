<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaLeaveReply.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaLeaveReply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaLeaveReply.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaLeaveReply.ino" />:</td>
						<td><s:textfield name="s_ino" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveReply.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveReply.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveReply.messagecomment" />:</td>
						<td><s:textfield name="s_messagecomment" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveReply.perno" />:</td>
						<td><s:textfield name="s_perno" /> </td>
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

		<ec:table action="oa/oaLeaveReply!list.do" items="objList" var="oaLeaveReply"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<%-- <ec:exportXls fileName="oaLeaveReplys.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaLeaveReplys.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

				<c:set var="tno"><s:text name='oaLeaveReply.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tino"><s:text name='oaLeaveReply.ino' /></c:set>	
				<ec:column property="ino" title="${tino}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaLeaveReply.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaLeaveReply.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tmessagecomment"><s:text name='oaLeaveReply.messagecomment' /></c:set>	
				<ec:column property="messagecomment" title="${tmessagecomment}" style="text-align:center" />

				<c:set var="tperno"><s:text name='oaLeaveReply.perno' /></c:set>	
				<ec:column property="perno" title="${tperno}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaLeaveReply!view.do?no=${oaLeaveReply.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaLeaveReply!edit.do?no=${oaLeaveReply.no}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaLeaveReply!delete.do?no=${oaLeaveReply.no}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
