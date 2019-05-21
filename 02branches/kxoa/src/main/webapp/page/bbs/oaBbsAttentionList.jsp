<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaBbsAttention.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaBbsAttention" namespace="/bbs" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaBbsAttention.themeno" />:</td>
						<td><s:textfield name="s_themeno" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBbsAttention.usercode" />:</td>
						<td><s:textfield name="s_usercode" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaBbsAttention.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaBbsAttention.laytype" />:</td>
						<td><s:textfield name="s_laytype" /> </td>
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

		<ec:table action="bbs/oaBbsAttention!list.do" items="objList" var="oaBbsAttention"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaBbsAttentions.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaBbsAttentions.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tthemeno"><s:text name='oaBbsAttention.themeno' /></c:set>	
				<ec:column property="themeno" title="${tthemeno}" style="text-align:center" />

				<c:set var="tusercode"><s:text name='oaBbsAttention.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />


				<c:set var="tcreatetime"><s:text name='oaBbsAttention.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tlaytype"><s:text name='oaBbsAttention.laytype' /></c:set>	
				<ec:column property="laytype" title="${tlaytype}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='bbs/oaBbsAttention!view.do?themeno=${oaBbsAttention.themeno}&usercode=${oaBbsAttention.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='bbs/oaBbsAttention!edit.do?themeno=${oaBbsAttention.themeno}&usercode=${oaBbsAttention.usercode}'><s:text name="opt.btn.edit" /></a>
					<a href='bbs/oaBbsAttention!delete.do?themeno=${oaBbsAttention.themeno}&usercode=${oaBbsAttention.usercode}' 
							onclick='return confirm("${deletecofirm}oaBbsAttention?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
