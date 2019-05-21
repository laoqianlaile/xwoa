<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaDuescollectioninfos.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaDuescollectioninfos" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaDuescollectioninfos.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaDuescollectioninfos.usercode" />:</td>
						<td><s:textfield name="s_usercode" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaDuescollectioninfos.unitcode" />:</td>
						<td><s:textfield name="s_unitcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaDuescollectioninfos.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaDuescollectioninfos.amount" />:</td>
						<td><s:textfield name="s_amount" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaDuescollectioninfos.isfinish" />:</td>
						<td><s:textfield name="s_isfinish" /> </td>
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

		<ec:table action="oa/oaDuescollectioninfos!list.do" items="objList" var="oaDuescollectioninfos"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaDuescollectioninfoss.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaDuescollectioninfoss.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaDuescollectioninfos.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />

				<c:set var="tusercode"><s:text name='oaDuescollectioninfos.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />


				<c:set var="tunitcode"><s:text name='oaDuescollectioninfos.unitcode' /></c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" />

				<c:set var="tcreatetime"><s:text name='oaDuescollectioninfos.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tamount"><s:text name='oaDuescollectioninfos.amount' /></c:set>	
				<ec:column property="amount" title="${tamount}" style="text-align:center" />

				<c:set var="tisfinish"><s:text name='oaDuescollectioninfos.isfinish' /></c:set>	
				<ec:column property="isfinish" title="${tisfinish}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaDuescollectioninfos!view.do?djId=${oaDuescollectioninfos.djId}&usercode=${oaDuescollectioninfos.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaDuescollectioninfos!edit.do?djId=${oaDuescollectioninfos.djId}&usercode=${oaDuescollectioninfos.usercode}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaDuescollectioninfos!delete.do?djId=${oaDuescollectioninfos.djId}&usercode=${oaDuescollectioninfos.usercode}' 
							onclick='return confirm("${deletecofirm}oaDuescollectioninfos?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
