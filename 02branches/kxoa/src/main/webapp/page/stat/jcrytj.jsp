<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />

<script type="text/javascript">

</script>
<style type="text/css">
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
a:hover{
 text-decoration:underline;  /*鼠标放上去有下划线*/
}
</style>


<title>${formName}</title>


</head>
<body>
	<jsp:include page="childs/search.jsp"></jsp:include>
	
	<div class="eXtremeTable">

		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

			<jsp:include page="childs/normalTableHead.jsp"></jsp:include>
			
			<tbody class="tableBody">
				<c:set value="odd" var="rownum" />

				<c:forEach var="datas" items="${formData}" varStatus="status">
					<tr class="${rownum}" id="item_${status.count}"
						onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
						<c:forEach var="data" items="${datas}" varStatus="vs">
							<c:if test="${vs.index==0}"><c:set  var="depNo" value="${data}"/></c:if>
						
						<c:choose>
						   <c:when test="${vs.index==1}">
						   <td class="withLink" style="text-align:left;">
						   <c:set var="temp" value="${fn:split(data, ' ') }"></c:set>
						  ${fn: replace(temp[0],'#','&nbsp;')}<a href="twodimenform!doStat.do?modelName=jcrytj&ORGID=${depNo}">${isShowColumn[st.index]}${temp[1]}</a>
						   </td>
						   </c:when>
						   <c:otherwise>
						  <td  <c:if test="${isShowColumn[vs.index]=='F' }">style="display:none;" </c:if>>${data}</td>
						   </c:otherwise>
						   
						</c:choose>
						</c:forEach>
					</tr>
					<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />

				</c:forEach>
				<c:if test="${additionRow=='1' or  additionRow=='3'}">
					<c:set value="1" var="firstCol" />
					<tr class="${rownum}" id="item_${status.count}"
						onmouseover="this.className='highlight'"
						onmouseout="this.className='${rownum}'">
						<c:forEach var="col" items="${columns}" varStatus="status">
							<td><c:if test="${firstCol == '1'}">合计</c:if> <c:if
									test="${firstCol != '1' && col.optType !='0'}"> ${col.sumValue} </c:if>
							</td>
							<c:set value="2" var="firstCol" />
						</c:forEach>
					</tr>
					<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
				</c:if>
				<c:if test="${additionRow=='2' or  additionRow=='3'}">
					<c:set value="1" var="firstCol" />
					<tr class="${rownum}" id="item_${status.count}"
						onmouseover="this.className='highlight'"
						onmouseout="this.className='${rownum}'">
						<c:forEach var="col" items="${columns}" varStatus="status">
							<td><c:if test="${firstCol == '1'}">平均</c:if> <c:if
									test="${firstCol != '1' && col.optType !='0'}"> ${col.averageValue} </c:if>
							</td>
							<c:set value="2" var="firstCol" />
						</c:forEach>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>