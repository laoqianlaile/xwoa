<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaWorkLog.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaWorkLog" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	


					<tr >
						<td class="addTd"><s:text name="oaWorkLog.infoType" />:</td>
						<td><s:textfield name="s_infoType" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.releaseDate" />:</td>
						<td><s:textfield name="s_releaseDate" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.creatertime" />:</td>
						<td><s:textfield name="s_creatertime" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaWorkLog.isallowcomment" />:</td>
						<td><s:textfield name="s_isallowcomment" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaWorkLog.isshare" />:</td>
						<td><s:textfield name="s_isshare" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaWorkLog.shares" />:</td>
						<td><s:textfield name="s_shares" /> </td>
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

		<ec:table action="oa/oaWorkLog!list.do" items="objList" var="oaWorkLog"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaWorkLogs.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaWorkLogs.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tno"><s:text name='oaWorkLog.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tinfoType"><s:text name='oaWorkLog.infoType' /></c:set>	
				<ec:column property="infoType" title="${tinfoType}" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaWorkLog.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="treleaseDate"><s:text name='oaWorkLog.releaseDate' /></c:set>	
				<ec:column property="releaseDate" title="${treleaseDate}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaWorkLog.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaWorkLog.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatertime"><s:text name='oaWorkLog.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" />

				<c:set var="tisallowcomment"><s:text name='oaWorkLog.isallowcomment' /></c:set>	
				<ec:column property="isallowcomment" title="${tisallowcomment}" style="text-align:center" />

				<c:set var="tisshare"><s:text name='oaWorkLog.isshare' /></c:set>	
				<ec:column property="isshare" title="${tisshare}" style="text-align:center" />

				<c:set var="tshares"><s:text name='oaWorkLog.shares' /></c:set>	
				<ec:column property="shares" title="${tshares}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${contextPath }/oa/oaWorkLog!view.do?no=${oaWorkLog.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='${contextPath }/oa/oaWorkLog!edit.do?no=${oaWorkLog.no}'><s:text name="opt.btn.edit" /></a>
					<a href='${contextPath }/oa/oaWorkLog!delete.do?no=${oaWorkLog.no}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
