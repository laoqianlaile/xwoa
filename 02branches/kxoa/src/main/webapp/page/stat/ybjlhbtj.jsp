<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />


<title>${formName}</title>

<script>
	$(function() {
		$.myChart.init('#ec_table', $.evalJSON('${jsonFormData}'));
	});
</script>
</head>
<body>
	<jsp:include page="childs/search.jsp"></jsp:include>

	<div class="eXtremeTable">
		<input type="hidden" name="statRows" value="${rowCount}" />
		<!-- 不包含统计数据行 -->
		<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
		<!-- 是否按行画统计图   T 画  F 不画 -->
		<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
		<!-- 按行画统计图  去数据起始列 -->
		<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
		<!-- 按行画统计图  去数据借宿列 -->

		<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
			class="tableRegion" width="100%">

			<thead>
				<tr>
					<c:forEach var="col" items="${columns}" begin="0" end="2"
						varStatus="status">
						<td class="tableHeader" stat="${col.drawChart}">
							<!-- 本列是否画统计图   T 画  F 不画 --> ${col.colName}
						</td>
					</c:forEach>
					<c:forEach var="con" items="${conditions}" begin="1" end="2"
						varStatus="status">
						<%
							for (int i = 0; i < 12; i++) {
									request.setAttribute("i", i);
						%>
						<td class="tableHeader" stat="${col.drawChart}"><c:if
								test="${con.condValue-i<=0}">${con.condValue-i+12}月</c:if> <c:if
								test="${con.condValue-i>0}">${con.condValue-i}月</c:if></td>
						<%
							}
						%>

					</c:forEach>

				</tr>
			</thead>
			
			<jsp:include page="childs/normalTableBody.jsp"></jsp:include>
		</table>
	</div>
</body>
</html>