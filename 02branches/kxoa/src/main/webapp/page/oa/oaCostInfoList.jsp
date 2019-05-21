<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>

		<title>
			<s:text name="oaCostInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
	
		<ec:table action="oa/oaCostInfo!list.do" items="objList" var="oaCostInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" sortable="false">
	
			<ec:row>

				<c:set var="tno"><s:text name='oaCostInfo.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tdjid"><s:text name='oaCostInfo.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcostType"><s:text name='oaCostInfo.costType' /></c:set>	
				<ec:column property="costType" title="${tcostType}" style="text-align:center" />

				<c:set var="tthecost"><s:text name='oaCostInfo.thecost' /></c:set>	
				<ec:column property="thecost" title="${tthecost}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaCostInfo.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaCostInfo.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				     ${cp:MAPVALUE("usercode",oaCostInfo.creater)} 
				</ec:column>

				<c:set var="tcreatertime"><s:text name='oaCostInfo.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value='${oaCostInfo.creatertime}' pattern='yyyy-MM-dd ' />
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaCostInfo!view.do?no=${oaCostInfo.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}'><s:text name="opt.btn.view" /></a>
					<c:if test="${show_type eq 'T'}">
					<a href='oa/oaCostInfo!delete.do?no=${oaCostInfo.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
