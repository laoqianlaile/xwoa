<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaLeaveMessage.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaLeaveMessage" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaLeaveMessage.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaLeaveMessage.infoType" />:</td>
						<td><s:textfield name="s_infoType" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessage.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessage.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessage.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessage.messagecomment" />:</td>
						<td><s:textfield name="s_messagecomment" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessage.state" />:</td>
						<td><s:textfield name="s_state" /> </td>
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

		<ec:table action="oa/oaLeaveMessage!list.do" items="objList" var="oaLeaveMessage"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<%-- <ec:exportXls fileName="oaLeaveMessages.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaLeaveMessages.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

				<c:set var="tno"><s:text name='oaLeaveMessage.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tinfoType"><s:text name='oaLeaveMessage.infoType' /></c:set>	
				<ec:column property="infoType" title="${tinfoType}" style="text-align:center" />

				<c:set var="tdjid"><s:text name='oaLeaveMessage.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaLeaveMessage.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaLeaveMessage.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tmessagecomment"><s:text name='oaLeaveMessage.messagecomment' /></c:set>	
				<ec:column property="messagecomment" title="${tmessagecomment}" style="text-align:center" />

				<c:set var="tstate"><s:text name='oaLeaveMessage.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${contextPath }/oa/oaLeaveMessage!view.do?no=${oaLeaveMessage.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='${contextPath }/oa/oaLeaveMessage!edit.do?no=${oaLeaveMessage.no}'><s:text name="opt.btn.edit" /></a>
					<a href='${contextPath }/oa/oaLeaveMessage!delete.do?no=${oaLeaveMessage.no}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
