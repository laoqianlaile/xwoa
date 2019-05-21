<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!-- WdatePicker -->
<script src="${pageContext.request.contextPath}/scripts/plugin/My97DatePicker/myWdatePicker.js"></script>
<c:if test="${fn:length(conditions)>0}">
<div class="search stat">
<div class="crumbs">${param['title']}</div>
<form action="${pageContext.request.contextPath}/stat/twodimenform!doStat.do" id="statForm" style="margin-top:0px;margin-bottom:5px;">
	<input type="hidden" name="modelName" value="${modelName}"> 
	<input type="hidden" name="resultName" value="${resultName}">
	<input type="hidden" name="title" value="${param['title']}">
	<table cellpadding="0" cellspacing="0">
	
	<c:set var="count" value="0"></c:set>
	<%-- 查询条件 --%>	
	<c:forEach var="cond" items="${conditions}" varStatus="status">
		<c:if test="${count % 4 == 0 }">
			<tr>
		</c:if>
		
		<c:set var="step" value="2"></c:set>
		<c:choose>
		
			<%-- 隐藏属性 --%>
			<c:when test="${cond.condStyle =='H'}">
				<input type="hidden" name="${cond.condName}" value="${cond.condValue}">
				<c:set var="step" value="0"></c:set>
			</c:when>
			
			<%-- 只读属性 --%>
			<c:when test="${cond.condStyle =='R'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<input type="text" readonly="readonly" class=""
			  			name="${cond.condName}" value="${cond.condValue}" />
			  	</td>
			</c:when>
			
			<%-- 选择年份 --%>
			<c:when test="${cond.condStyle =='Y'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td> 
			  	<td >
			  		<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value="${cond.condValue}"
			  			name="${cond.condName}" onclick="WdatePicker({dateFmt:'yyyy'})" placeholder="选择年份">
			  	</td>
			</c:when>
			
			<%-- 选择年月 --%>
			<c:when test="${cond.condStyle =='M'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value="${cond.condValue}"
			  			name="${cond.condName}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="选择年、月">
			  	</td>
			</c:when>
			
			<%-- 选择日期 --%>
			<c:when test="${cond.condStyle =='D'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value="${cond.condValue}"
			  			name="${cond.condName}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />
			  	</td>
			</c:when>
			
			<%-- 选择部门 --%>
			<c:when test="${cond.condStyle =='U'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		TODO: 选择部门1
			  	</td>
			</c:when>
			
			<%-- 数据字典 --%>
			<c:when test="${cond.condStyle =='P'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<select name="${cond.condName}">
			  			<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:LVB(cond.condType)}">
							<option value="${row.value}" title="${row.label}" <c:if test="${cond.condValue==row.value }">selected="selected"</c:if>>								
								<c:out value="${row.label}" />
							</option>
						</c:forEach>
			  		</select>
			  	</td>
			</c:when>
			
			<%-- SQL --%>
			<c:when test="${cond.condStyle =='S'}">
				<td class="addTd">
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<select name="${cond.condName}" class="chosen">
			  		 <c:if test="${allowSelectAll=='T'}">
			  			<option value="">---请选择---</option>
			  		  </c:if>
						<c:forEach var="op" items="${datas[cond.condName] }">
							<option value="${op[0] }" title="${op[1] }" <c:if test="${op[0] == cond.condValue }">selected="selected"</c:if>>${op[1] }</option>	
						</c:forEach>
			  		</select>
			  	</td>
			</c:when>
			
			<c:otherwise>
			
				<td class="addTd" >
			  		${cond.condLabel}：
			  	</td>
			  	<td >
			  		<input type="text" class=""
			  			name="${cond.condName}" value="${cond.condValue}" />
			  	</td>
			</c:otherwise>
		</c:choose>
		
		<c:set var="count" value="${count+step }"></c:set> 
		<c:if test="${count % 4 == 0 && !status.last}">
			</tr>
		</c:if>
		
	</c:forEach>
	
			<td>
				<input type="submit" class="btn" value="查询统计" />
               <input type="button" class="btn btn-success btn-sm excel" value="导出" title="导出Excel文件"
                 onclick="exportTableToExcel()" style="${allowExport=='T'?'':'display:none'}"/>
			</td>
		</tr>
</table>
</form>
</div>

<form method="post" id="dataForm" action="twodimenform!doExport.do">
	<input name="formName" id="formName" value="${formName }" type="hidden" ></input>
	<input name="exportData" id="exportData" type="hidden" ></input>
</form>
</c:if>