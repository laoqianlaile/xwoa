<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


<html>
<head>
<title>内部事权</title>
<%-- <sj:head locale="zh_CN" /> --%>
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/centitFlow/imageDocument/registerList.css" rel="stylesheet" type="text/css" />
</head>

<body class="registerList">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
		公文登记
		<%-- <c:choose>
		<c:when test="${s_itemType eq 'SW' }">收文登记</c:when>
		<c:when test="${s_itemType eq 'FW' }">发文登记</c:when>
		</c:choose> --%>
		</legend>
		
	</fieldset>

	<%-- <ec:table action="powerbase/supPower!documentList.do" items="orgPowerList"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>

			<ec:column property="itemName" title="标题" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

            
			<ec:column property="orgId" title="行使部门" style="text-align:center">
					${cp:MAPVALUE("unitcode",suppower.unitcode)}
			</ec:column>

		  
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				  <c:if test="${suppower.itemType eq 'SW' }"> 	
				<a
					href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${suppower.unitcode}'>登记</a>
		     	</c:if>
		     	 <c:if test="${suppower.itemType eq 'FW' }"> 	
				<a
					href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${suppower.unitcode}'>登记</a>
		     	</c:if>
			</ec:column>
		</ec:row>
	</ec:table> --%>
	

	<div class="registerList">
	
	
	<div class="improvecustom" style="height: 92%;">
	<table border="0" cellpadding="0" cellspacing="0"  width="90%" >
					<tbody align="left">
				<tr>
				 <td width="40px;"></td>
				  <td  width='160px;'></td>
				   <td  width='160px;'></td>
				    <td  width='160px;'></td>
				</tr>	
					
				<c:if test="${not empty orgPowerSWList }">

				                   <div title="收文"  >
									<div class="title" style="color: #666666;padding-top:0;background:white;position:relative;text-indent:30px;line-height:40px">
											<span style="display:inline-block;height:30px;width:10px;position:absolute;left:10px;top: 5px;background: #14aae4;"></span>收文登记
									</div>
									</div>
						
							
								 <tr>
								 <c:forEach items="${orgPowerSWList}" var="suppower" varStatus="ts">
                              
								 <td class="${suppower.itemId}_custom suppower_custom" width='160px;'>
									<div title="${suppower.itemName}" >
										<%-- <div class="td_linkdoc">
										<a class="word"  href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&&orgcode=${suppower.unitcode}'>
											${suppower.itemName}
										 <span class="in">
					                     </span>
					                     </a>
										</div> --%>
										<a class="td_linkdoc"  href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&&orgcode=${suppower.unitcode}'>
											${suppower.itemName}
					                     </a>
									</div>
								</td>
								</c:forEach>
						      </tr>
								
						
						
					
			</c:if>
	       <c:if test="${not empty orgPowerFWList }">

			                        <div title="发文"  >
									<div class="title" style="color: #666666;padding-top:0;background:white;position:relative;text-indent:30px;line-height:40px">
											<span style="display:inline-block;height:30px;width:10px;position:absolute;left:10px;top: 5px;background: #14aae4;"></span>发文登记
									</div>
									</div>
									<tr>
						     <c:forEach items="${orgPowerFWList}" var="suppower" varStatus="fw">
                              
								 <td class="suppower_custom"  width='160px;' >
								 
									<div title="${suppower.itemName}"  >
										<%-- <div class="td_linkdoc">
										<a class="word"  href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${suppower.unitcode}'>
											${suppower.itemName}
										 <span class="in">
					                     </span>
					                     </a>
										</div> --%>
										<a class="td_linkdoc"  href='<%=request.getContextPath()%>/${suppower.applyItemType}?optId=${suppower.optid }&itemId=${suppower.itemId}&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${suppower.unitcode}'>
											${suppower.itemName}
					                     </a>
									</div>
								</td>
								
						</c:forEach>
						</tr>
				
			</c:if>
				</tbody>
				</table>

	
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	</div>
	
</body>
<script type="text/javascript">

	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=500px,width=790px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>
