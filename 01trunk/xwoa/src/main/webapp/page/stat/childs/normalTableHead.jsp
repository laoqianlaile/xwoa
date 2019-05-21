<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<input type="hidden" name="statRows" value="${rowCount}" />
<!-- 不包含统计数据行 -->

<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
<!-- 是否按行画统计图   T 画  F 不画 -->

<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
<!-- 按行画统计图  去数据起始列 -->

<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
<!-- 按行画统计图  去数据借宿列 -->

<thead align="center">
	<tr>
		<c:forEach var="col" items="${columns}" varStatus="status">
			<th class="tableHeader" stat="${col.drawChart}" orderField="${col.colOrder }" <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
				<%-- 本列是否画统计图   T 画  F 不画 --%> ${col.colName}
			</th>
		</c:forEach>
	</tr>
</thead>
		
