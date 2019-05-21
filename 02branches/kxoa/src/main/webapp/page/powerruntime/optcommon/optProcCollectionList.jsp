<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="optProcCollection.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="optProcCollection" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="optProcCollection.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	
					<tr >
						<td><s:text name="optProcCollection.atttype" />:</td>
						<td><s:textfield name="s_atttype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcCollection.attsettime" />:</td>
						<td><s:textfield name="s_attsettime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcCollection.isatt" />:</td>
						<td><s:textfield name="s_isatt" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optProcCollection.removesettime" />:</td>
						<td><s:textfield name="s_removesettime" /> </td>
					</tr>	

					<tr>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
						<td>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/optProcCollection!list.do" items="objList" var="optProcCollection"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tdjId"><s:text name='optProcCollection.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />

				<c:set var="tusercode"><s:text name='optProcCollection.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />

				<c:set var="tatttype"><s:text name='optProcCollection.atttype' /></c:set>	
				<ec:column property="atttype" title="${tatttype}" style="text-align:center" />

				<c:set var="tattsettime"><s:text name='optProcCollection.attsettime' /></c:set>	
				<ec:column property="attsettime" title="${tattsettime}" style="text-align:center" />
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/optProcCollection!view.do?djId=${optProcCollection.djId}&usercode=${optProcCollection.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/optProcCollection!edit.do?djId=${optProcCollection.djId}&usercode=${optProcCollection.usercode}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/optProcCollection!delete.do?djId=${optProcCollection.djId}&usercode=${optProcCollection.usercode}' 
							onclick='return confirm("${deletecofirm}optProcCollection?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>