<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaSupervise.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>

		<ec:table action="oa/oaSupervise!list.do" items="superviselist" var="oaSupervise"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" sortable="false">
	
			<ec:row>

				
				<ec:column property="djId" title="督办流水号" style="text-align:center" />

				
				<ec:column property="supDjid" title="督办业务流水号" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaSupervise.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="tsuperviseType"><s:text name='oaSupervise.superviseType' /></c:set>	
				<ec:column property="superviseType" title="${tsuperviseType}" style="text-align:center" >
			      ${cp:MAPVALUE("oa_superviseType",oaSupervise.superviseType) }
				</ec:column>

				<c:set var="tcreatertime"><s:text name='oaSupervise.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value='${oaSupervise.creatertime}' pattern='yyyy-MM-dd HH:mm' />			
				
				</ec:column>

				<c:set var="tcreater"><s:text name='oaSupervise.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				${cp:MAPVALUE("usercode",oaSupervise.creater) }
				</ec:column>

				<c:set var="tstate"><s:text name='oaSupervise.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" >
				${cp:MAPVALUE("oa_state",oaSupervise.state) }
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSupervise!view.do?djId=${oaSupervise.djId}&nodeInstId=0&viewtype=T'><s:text name="opt.btn.view" /></a>
		
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
