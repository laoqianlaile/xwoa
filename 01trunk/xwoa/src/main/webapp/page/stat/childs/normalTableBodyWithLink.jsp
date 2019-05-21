<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		
<tbody class="tableBody">
	<c:set value="odd" var="rownum" />
	<c:set value="0" var="rowind" />
	<c:forEach var="datas" items="${formData}" varStatus="status">
		<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
			<c:forEach var="data" items="${datas}" varStatus="st">
				<td <c:if test="${isShowColumn[st.index]=='F' }">style="display:none;"</c:if> >
					<!-- 给每个变量添加链接 -->
					<a href="${logicUrl}${rowLogicUrl[rowind]}${columns[st.index].colLogic}"> ${data}</a>	
				</td>
			</c:forEach>
		</tr>
		<c:set value="${rowind+1}" var="rowind" />
		<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
	</c:forEach>
	
	<c:if test="${additionRow=='1' or  additionRow=='3'}">
		<c:set value="1" var="firstCol" />
		<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
			<c:forEach var="col" items="${columns}" varStatus="status">
				<td <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
					<c:if test="${firstCol == '1'}">合计</c:if>
					<c:if test="${firstCol != '1' && col.optType !='0'}">${col.sumValue}</c:if>
				</td>
				<c:set value="2" var="firstCol" />
			</c:forEach>
		</tr>
		<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
	</c:if>
	
	<c:if test="${additionRow=='2' or  additionRow=='3'}">
		<c:set value="1" var="firstCol" />
		<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
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
