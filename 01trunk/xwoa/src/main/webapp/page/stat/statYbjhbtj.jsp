<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>${formName}</title>
</head>
<body>
	<jsp:include page="childs/search.jsp"></jsp:include>

	<input type="hidden" name="statRows" value="${rowCount}" />
	<!-- 不包含统计数据行 -->
	<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
	<!-- 是否按行画统计图   T 画  F 不画 -->
	<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
	<!-- 按行画统计图  去数据起始列 -->
	<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
	<!-- 按行画统计图  去数据借宿列 -->

	<c:set var="rowInd">0</c:set>
	<c:set var="rowNum">-1</c:set>
	
    <c:forEach var="con" items="${conditions}" begin="1" end="2" varStatus="status">
	   <c:set var="monthN">${con.condValue}</c:set>		
	</c:forEach>
			
	<ec:tree identifier="col0" parentAttribute="col1"
		items="ListObjects_formData" action="twodimenform!doStat.do"
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false" var="optinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
		
		<ec:row>
		<c:if test="${rowNum==-1}">
		<c:forEach var="col" items="${columns}" varStatus="status">
				<c:set var="name" value="col${status.index }" />

				<c:if test="${rowInd == 2}">
					<ec:column property="${col.colProperty}" title="${col.colName}"
						style="text-align:left" cell="org.extremecomponents.tree.TreeCell" />
				</c:if>

				<c:if test="${rowInd > 2}">  
					<ec:column property="${col.colProperty}" title="${(monthN+status.index-3+1)>12?(monthN+status.index-3+1-12):(monthN+status.index-3+1)}月"    sortable="false" style="text-align:left">
					    
					</ec:column>
				</c:if>
				<c:set var="rowInd">${rowInd+1}</c:set>
			</c:forEach>
		 </c:if>
		 <c:if test="${rowNum>-1}">
		 <c:forEach var="col" items="${columns}" varStatus="status">
				<c:set var="name" value="col${status.index }" />

				<c:if test="${rowInd == 2}">
					<ec:column property="${col.colProperty}" title="${col.colName}"
						style="text-align:left" cell="org.extremecomponents.tree.TreeCell" />
				</c:if>
				<c:if test="${rowInd > 2}">
					<ec:column property="${col.colProperty}" title="${col.colName}"
						sortable="false" style="text-align:left">
					    ${optinfo[name] }
					    
					</ec:column>
				</c:if>
				<c:set var="rowInd">${rowInd+1}</c:set>
			</c:forEach>
		 
		 </c:if>
		</ec:row>
		
		<c:set var="rowNum">${rowNum+1}</c:set>
	</ec:tree>

</body>
<script type="text/javascript">
	$(function() {
		$.myChart.init('#ec', $.evalJSON('${jsonFormData}'),{
			defaultType:'line',
			rowCategories:'table'
		});
	});
</script>
</html>