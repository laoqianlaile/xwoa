<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="useroptLog.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="useroptLog" namespace="/sys" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="useroptLog.id" />:</td>
						<td><s:textfield name="s_id" /> </td>
					</tr>	


					<tr >
						<td><s:text name="useroptLog.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.createuser" />:</td>
						<td><s:textfield name="s_createuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.bizname" />:</td>
						<td><s:textfield name="s_bizname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.runerrortype" />:</td>
						<td><s:textfield name="s_runerrortype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.createrip" />:</td>
						<td><s:textfield name="s_createrip" /> </td>
					</tr>	

					<tr >
						<td><s:text name="useroptLog.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
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

		<ec:table action="sys/useroptLog!list.do" items="objList" var="useroptLog"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="useroptLogs.xls" ></ec:exportXls>
			<ec:exportPdf fileName="useroptLogs.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tid"><s:text name='useroptLog.id' /></c:set>	
				<ec:column property="id" title="${tid}" style="text-align:center" />


				<c:set var="tcreatetime"><s:text name='useroptLog.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tcreateuser"><s:text name='useroptLog.createuser' /></c:set>	
				<ec:column property="createuser" title="${tcreateuser}" style="text-align:center" />

				<c:set var="tbizname"><s:text name='useroptLog.bizname' /></c:set>	
				<ec:column property="bizname" title="${tbizname}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='useroptLog.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="trunerrortype"><s:text name='useroptLog.runerrortype' /></c:set>	
				<ec:column property="runerrortype" title="${trunerrortype}" style="text-align:center" />

				<c:set var="tcreaterip"><s:text name='useroptLog.createrip' /></c:set>	
				<ec:column property="createrip" title="${tcreaterip}" style="text-align:center" />

				<c:set var="tremark"><s:text name='useroptLog.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='sys/useroptLog!view.do?id=${useroptLog.id}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='sys/useroptLog!edit.do?id=${useroptLog.id}'><s:text name="opt.btn.edit" /></a>
					<a href='sys/useroptLog!delete.do?id=${useroptLog.id}' 
							onclick='return confirm("${deletecofirm}useroptLog?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
