<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>字典明细--<c:out	value="${catalog.catalogname}" />
		</title>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>
<body class="sub-flow">
	 <fieldset style="padding:10px;">
	 	<legend style="margin-bottom:10px;">字典明细</legend>
	 	
	 	<input type="button" value="添加新条目" class="btn btnWide" onclick="location.href='dictionary!editDetail.do?catalogcode=${catalog.catalogcode}'" />
	    <input type="button" value="返回" class="btn" onclick="window.history.back()" />
	 <%-- <a href='dictionary!editDetail.do?catalogcode=${catalog.catalogcode}' class="btnA"><span class="btn">添加新条目</span></a>
	 <a href='#' onclick="window.history.back()" class="btnA"><span class="btn">返回</span></a> --%>
    <table cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">
						字典代码
					</td>
					<td align="left">
						<c:out value="${catalog.catalogcode}" />
					</td>

					<td class="addTd">
						字典名称
					</td>
					<td align="left">
						<c:out value="${catalog.catalogname}" />
					</td>
				</tr>

				<tr>
					<td class="addTd">
						字典描述
					</td>
					<td align="left" colspan="3">
						<c:out value="${catalog.catalogdesc}" /> 
					</td>					
				</tr>
			</table>
	
 <c:if test="${catalog.catalogtype eq 'L'}">
             
      <ec:table items="dictDetails" var="detail"  action="dictionary!view.do"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="dc">
			<ec:row>
				<ec:column property="datacode" title="${fdesc[0]}"  style="text-align:center" />
				<ec:column property="extracode" title="${fdesc[1]}" sortable="false" style="text-align:center" />
				<ec:column property="extracode2" title="${fdesc[2]}" sortable="false" style="text-align:center" />
				<ec:column property="datatag" title="${fdesc[3]}" sortable="false" style="text-align:center">
				<input type="hidden" value="${detail.datatag}"/> 
				<c:choose>
						<c:when test="${fn:length(detail.datatag) > 16}">
							<c:out
								value="${fn:substring(detail.datatag, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${detail.datatag}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="datavalue" title="${fdesc[4]}"  style="text-align:center" />
				<ec:column property="datastyle" title="${fdesc[5]}" sortable="false" style="text-align:center" />
				<ec:column property="datadesc" title="${fdesc[6]}" sortable="false" style="text-align:center">
				<input type="hidden" value="${detail.datadesc}"/> 
				<c:choose>
						<c:when test="${fn:length(detail.datadesc) > 16}">
							<c:out
								value="${fn:substring(detail.datadesc, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${detail.datadesc}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="detailopt" title="操作" sortable="false"	 >
					<a href='dictionary!editDetail.do?catalogcode=${catalog.catalogcode}&datacode=${detail.datacode}'>
						编辑
					</a>
					
					<a href='dictionary!deleteDetail.do?catalogcode=${catalog.catalogcode}&datacode=${detail.datacode}'
						onclick='return confirm("是否删除数据：${detail.datavalue}?");'>
						删除
					</a>
				</ec:column>
			
			</ec:row>
		</ec:table> 	
  </c:if>
  
  <c:if test="${catalog.catalogtype eq 'T'}">
  

  
  <ec:tree identifier="datacode" parentAttribute="extracode" items="dictDetails"
		action="dictionary!view.do"
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="detail" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
     <ec:row>
        <ec:column property="datacode" title="${fdesc[0]}" cell="org.extremecomponents.tree.TreeCell"/>
		<ec:column property="extracode2" title="${fdesc[2]}" style="text-align:center" />
		<ec:column property="datatag" title="${fdesc[3]}" style="text-align:center" />
		<ec:column property="datavalue" title="${fdesc[4]}" sortable="false" style="text-align:center" />
		<ec:column property="datastyle" title="${fdesc[5]}" style="text-align:center" />
		<ec:column property="datadesc" title="${fdesc[6]}" sortable="false" style="text-align:center" />
		<ec:column property="detailopt" title="操作" sortable="false"	 >
			<a href='dictionary!editDetail.do?catalogcode=${catalog.catalogcode}&datacode=${detail.datacode}'>
				编辑
			</a>
			
			<a href='dictionary!deleteDetail.do?catalogcode=${catalog.catalogcode}&datacode=${detail.datacode}'
			onclick='return confirm("是否删除数据：${detail.datavalue}?");' >
				删除
			</a>
		</ec:column>
	  </ec:row>
   </ec:tree>   
 </c:if>
 </fieldset>
						
</body>
</html>
