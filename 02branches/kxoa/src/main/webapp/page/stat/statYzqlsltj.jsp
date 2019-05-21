<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>${formName}</title>
</head>
<body>
	<jsp:include page="childs/search.jsp"></jsp:include>

	<input type="hidden" name="statRows" value="${rowCount}"/> <!-- 不包含统计数据行 --> 
	<input type="hidden" name="isStatRow" value="${rowDrawChart}"/> <!-- 是否按行画统计图   T 画  F 不画 --> 
	<input type="hidden" name="statStartCol" value="${drawChartBeginCol}"/>  <!-- 按行画统计图  去数据起始列 --> 
	<input type="hidden" name="statEndCol" value="${drawChartEndCol}"/>  <!-- 按行画统计图  去数据借宿列 --> 
	
	<c:set var="rowInd" >0</c:set>
	<ec:tree identifier="col0" parentAttribute="col1" items="ListObjects_formData" 
		action="twodimenform!doStat.do" 
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="optinfo" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
		<ec:row>
			<c:forEach var="col" items="${columns}" varStatus="status" >  
			   <c:set var="name" value="col${status.index }" />
				
				<c:if  test="${rowInd == 2}">
					<ec:column property="${col.colProperty}" title="${col.colName}" style="text-align:left" cell="org.extremecomponents.tree.TreeCell"  />
				</c:if>
				<c:if  test="${rowInd > 2}">
				    <c:if test="${status.index==0 }"><c:set value="${optinfo[name]}" var="depno"></c:set></c:if>
				    
				    <c:if test="${status.index<3}">
				       <ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" />
				    </c:if>
				    
				    <c:if test="${status.index==3}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=1">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==4}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=6">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==5}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=3">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==6}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=7">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==7}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=5">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==8}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=8">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==9}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=9">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==10}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=10">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==11}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=11">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==12}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=2">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==13}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						<a href="/jttsunzw/itemClass.do?action=search&depID=${depno}&usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&isHave=1&isSuspend=1&isDisuse=1&type=4">${optinfo[name] }</a>
					</ec:column>
					</c:if>
					
					<c:if test="${status.index==14}">
					<ec:column property="${col.colProperty}" title="${col.colName}" sortable="false"	style="text-align:left" >
						${optinfo[name] }
					</ec:column>
					</c:if>
					
				</c:if>
			<c:set var="rowInd" >${rowInd+1}</c:set>
			</c:forEach>
		</ec:row>
	</ec:tree>	

</body>
<script type="text/javascript">
$(function() {
	$.myChart.init('#ec_table', $.evalJSON('${jsonFormData}'));
});
</script>
</html>