<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>${formName}</h1>
<div class="eXtremeTable">
	<input type="hidden" name="statRows" value="${rowCount}" />
	<!-- ������ͳ�������� -->
	
	<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
	<!-- �Ƿ��л�ͳ��ͼ   T ��  F ���� -->
	
	<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
	<!-- ���л�ͳ��ͼ  ȥ������ʼ�� -->
	
	<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
	<!-- ���л�ͳ��ͼ  ȥ���ݽ����� -->

	<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
		<thead>
			<tr>
				<c:forEach var="col" items="${columns}" varStatus="status">
					<td class="tableHeader" stat="${col.drawChart}" <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
						<%-- �����Ƿ�ͳ��ͼ   T ��  F ���� --%> ${col.colName}
					</td>
				</c:forEach>
			</tr>
		</thead>
		
		<tbody class="tableBody">
			<c:set value="odd" var="rownum" />
			<c:forEach var="datas" items="${formData}" varStatus="status">
				<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
					<c:forEach var="data" items="${datas}" varStatus="st">
						<td <c:if test="${isShowColumn[st.index]=='F' }">style="display:none;"</c:if> >
							${data}
						</td>
					</c:forEach>
				</tr>
				<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
			</c:forEach>
			
			<c:if test="${additionRow=='1' or  additionRow=='3'}">
				<c:set value="1" var="firstCol" />
				<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
					<c:forEach var="col" items="${columns}" varStatus="status">
						<td <c:if test="${col.isShow=='F' }">style="display:none"</c:if> >
							<c:if test="${firstCol == '1'}">�ϼ�</c:if>
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
							<c:if test="${firstCol == '1'}">ƽ��</c:if> 
							<c:if test="${firstCol != '1' && col.optType !='0'}"> ${col.averageValue} </c:if>
						</td>
						<c:set value="2" var="firstCol" />
					</c:forEach>
				</tr>
			</c:if>
			
		</tbody>
	</table>
</div>