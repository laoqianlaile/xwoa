<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaAssetinformationBond.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaAssetinformationBond" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaAssetinformationBond.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationBond.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaAssetinformationBond.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationBond.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
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

		<ec:table action="oa/oaAssetinformationBond!list.do" items="objList" var="oaAssetinformationBond"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaAssetinformationBonds.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaAssetinformationBonds.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjid"><s:text name='oaAssetinformationBond.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tno"><s:text name='oaAssetinformationBond.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tcreater"><s:text name='oaAssetinformationBond.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatetime"><s:text name='oaAssetinformationBond.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaAssetinformationBond!view.do?djid=${oaAssetinformationBond.djid}&no=${oaAssetinformationBond.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaAssetinformationBond!edit.do?djid=${oaAssetinformationBond.djid}&no=${oaAssetinformationBond.no}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaAssetinformationBond!delete.do?djid=${oaAssetinformationBond.djid}&no=${oaAssetinformationBond.no}' 
							onclick='return confirm("${deletecofirm}oaAssetinformationBond?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
