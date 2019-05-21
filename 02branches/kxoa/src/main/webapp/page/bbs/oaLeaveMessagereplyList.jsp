<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaLeaveMessagereply.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaLeaveMessagereply" namespace="/bbs" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaLeaveMessagereply.lyno" />:</td>
						<td><s:textfield name="s_lyno" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaLeaveMessagereply.lno" />:</td>
						<td><s:textfield name="s_lno" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessagereply.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessagereply.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaLeaveMessagereply.messagecomment" />:</td>
						<td><s:textfield name="s_messagecomment" /> </td>
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

		<ec:table action="bbs/oaLeaveMessagereply!list.do" items="objList" var="oaLeaveMessagereply"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaLeaveMessagereplys.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaLeaveMessagereplys.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tlyno"><s:text name='oaLeaveMessagereply.lyno' /></c:set>	
				<ec:column property="lyno" title="${tlyno}" style="text-align:center" />


				<c:set var="tlno"><s:text name='oaLeaveMessagereply.lno' /></c:set>	
				<ec:column property="lno" title="${tlno}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaLeaveMessagereply.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaLeaveMessagereply.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tmessagecomment"><s:text name='oaLeaveMessagereply.messagecomment' /></c:set>	
				<ec:column property="messagecomment" title="${tmessagecomment}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='bbs/oaLeaveMessagereply!view.do?lyno=${oaLeaveMessagereply.lyno}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='bbs/oaLeaveMessagereply!edit.do?lyno=${oaLeaveMessagereply.lyno}'><s:text name="opt.btn.edit" /></a>
					<a href='bbs/oaLeaveMessagereply!delete.do?lyno=${oaLeaveMessagereply.lyno}' 
							onclick='return confirm("${deletecofirm}oaLeaveMessagereply?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
