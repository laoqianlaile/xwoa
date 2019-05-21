<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="pcpunishItem.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="pcpunishItem" namespace="/punish" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="pcpunishItem.punishclassid" />:</td>
						<td><s:textfield name="s_punishclassid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="pcpunishItem.itemId" />:</td>
						<td><s:textfield name="s_itemId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.version" />:</td>
						<td><s:textfield name="s_version" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.punishclassname" />:</td>
						<td><s:textfield name="s_punishclassname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.depid" />:</td>
						<td><s:textfield name="s_depid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.punishclasscode" />:</td>
						<td><s:textfield name="s_punishclasscode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.punishobject" />:</td>
						<td><s:textfield name="s_punishobject" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.isinuse" />:</td>
						<td><s:textfield name="s_isinuse" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.punishbasiscontent" />:</td>
						<td><s:textfield name="s_punishbasiscontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.punishbasis" />:</td>
						<td><s:textfield name="s_punishbasis" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.islawsuit" />:</td>
						<td><s:textfield name="s_islawsuit" /> </td>
					</tr>	

					<tr >
						<td><s:text name="pcpunishItem.isreconsider" />:</td>
						<td><s:textfield name="s_isreconsider" /> </td>
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

		<ec:table action="punish/pcpunishItem!list.do" items="objList" var="pcpunishItem"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="pcpunishItems.xls" ></ec:exportXls>
			<ec:exportPdf fileName="pcpunishItems.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tpunishclassid"><s:text name='pcpunishItem.punishclassid' /></c:set>	
				<ec:column property="punishclassid" title="${tpunishclassid}" style="text-align:center" />


				<c:set var="titemId"><s:text name='pcpunishItem.itemId' /></c:set>	
				<ec:column property="itemId" title="${titemId}" style="text-align:center" />

				<c:set var="tversion"><s:text name='pcpunishItem.version' /></c:set>	
				<ec:column property="version" title="${tversion}" style="text-align:center" />

				<c:set var="tpunishclassname"><s:text name='pcpunishItem.punishclassname' /></c:set>	
				<ec:column property="punishclassname" title="${tpunishclassname}" style="text-align:center" />

				<c:set var="tdepid"><s:text name='pcpunishItem.depid' /></c:set>	
				<ec:column property="depid" title="${tdepid}" style="text-align:center" />

				<c:set var="tpunishclasscode"><s:text name='pcpunishItem.punishclasscode' /></c:set>	
				<ec:column property="punishclasscode" title="${tpunishclasscode}" style="text-align:center" />

				<c:set var="tpunishobject"><s:text name='pcpunishItem.punishobject' /></c:set>	
				<ec:column property="punishobject" title="${tpunishobject}" style="text-align:center" />

				<c:set var="tisinuse"><s:text name='pcpunishItem.isinuse' /></c:set>	
				<ec:column property="isinuse" title="${tisinuse}" style="text-align:center" />

				<c:set var="tpunishbasiscontent"><s:text name='pcpunishItem.punishbasiscontent' /></c:set>	
				<ec:column property="punishbasiscontent" title="${tpunishbasiscontent}" style="text-align:center" />

				<c:set var="tpunishbasis"><s:text name='pcpunishItem.punishbasis' /></c:set>	
				<ec:column property="punishbasis" title="${tpunishbasis}" style="text-align:center" />

				<c:set var="tremark"><s:text name='pcpunishItem.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tislawsuit"><s:text name='pcpunishItem.islawsuit' /></c:set>	
				<ec:column property="islawsuit" title="${tislawsuit}" style="text-align:center" />

				<c:set var="tisreconsider"><s:text name='pcpunishItem.isreconsider' /></c:set>	
				<ec:column property="isreconsider" title="${tisreconsider}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='punish/pcpunishItem!view.do?punishclassid=${pcpunishItem.punishclassid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='punish/pcpunishItem!edit.do?punishclassid=${pcpunishItem.punishclassid}'><s:text name="opt.btn.edit" /></a>
					<a href='punish/pcpunishItem!delete.do?punishclassid=${pcpunishItem.punishclassid}' 
							onclick='return confirm("${deletecofirm}pcpunishItem?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
