<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="params" value="${paramData }"></c:set>	
	
<tbody align="center">
	<c:forEach var="datas" items="${formData}" varStatus="status">
		<tr id="item_${status.count}">
			<c:forEach var="data" items="${datas}" varStatus="st">
				<c:set var="colType" value="${columns[st.index].colType }"></c:set>
				<c:set var="colFormat" value="${columns[st.index].colFormat }"></c:set>
				<td <c:if test="${isShowColumn[st.index]=='F' }">style="display:none;"</c:if> title="${data}">
					<%-- 格式化显示数据 --%>
					<c:choose>
						<%-- 数字 --%>
						<c:when test="${colType == 'N' }">
							<fmt:formatNumber value="${data }" pattern="${colFormat }"/>
						</c:when>
						
						<%-- 百分比 --%>
						<c:when test="${colType == 'P' }">
							<fmt:formatNumber type="percent" value="${data }" pattern="${colFormat }"/>
						</c:when>
						
						<%-- 货币 --%>
						<c:when test="${colType == 'C' }">
							<fmt:formatNumber type="currency" value="${data }" pattern="${colFormat }"/>
						</c:when>
						
						<%-- 日期 --%>
						<c:when test="${colType == 'D' }">
							<fmt:formatDate value="${data}" pattern="${colFormat }"/>
						</c:when>
						
						<%-- 新窗口 --%>
						<c:when test="${colType == 'L' && colFormat == 'navtab'}">
							<a href="${logicUrl}${params[status.index][st.index] }" target="navTab" rel="stat_${data }" title="${data }">${data }</a>
						</c:when>
						
						<%-- 超链接 --%>
						<c:when test="${colType == 'L'}">
							<a href="${logicUrl}${params[status.index][st.index] }" target="_Blank">${data }</a>
						</c:when>
						
						<c:otherwise>
							${data}
						</c:otherwise>					
					</c:choose>
				
					<%-- ${rowLogicUrl[status.index]} --%><%-- ${columns[st.index].colLogic} --%>
					<%-- ${logicUrl}${params[status.index][st.index] } --%>
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
	
	<c:if test="${additionRow=='1' or  additionRow=='3'}">
		<c:set value="1" var="firstCol" />
		<tr id="item_${status.count}">
			<c:forEach var="col" items="${columns}" varStatus="status">
				<td <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
					<c:if test="${firstCol == '1'}">合计</c:if>
					<c:if test="${firstCol != '1' && col.optType !='0'}">${col.sumValue}</c:if>
				</td>
				<c:set value="2" var="firstCol" />
			</c:forEach>
		</tr>
	</c:if>
	
	<c:if test="${additionRow=='2' or  additionRow=='3'}">
		<c:set value="1" var="firstCol" />
		<tr id="item_${status.count}">
			<c:forEach var="col" items="${columns}" varStatus="status">
				<td <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
					<c:if test="${firstCol == '1'}">平均</c:if> 
					<c:if test="${firstCol != '1' && col.optType !='0'}"> ${col.averageValue} </c:if>
				</td>
				<c:set value="2" var="firstCol" />
			</c:forEach>
		</tr>
	</c:if>
</tbody>
