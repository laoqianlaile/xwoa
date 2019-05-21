<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<div id="unitbox">
	<table id="unittree-table">
		<c:forEach var="unit" items="${cp:UNITSLIST(primaryUnit) }" varStatus="st">
			<c:if test="${st.first }">
				<tr data-tt-id="${unit.unitcode }">
					<td data-depno="${unit.depno }"><span class="td">${unit.unitshortname }</span></td>
				</tr>
			</c:if>
			
			<c:if test="${not st.first }">
				<tr data-tt-id="${unit.unitcode }" data-tt-parent-id="${unit.parentunit}">
					<td data-depno="${unit.depno }">
						<span class="td">${unit.unitshortname }</span>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</div> 

<script>																		  
	$('#unittree-table').treetable({ expandable: true }).treetable("expandNode", '1');
	$('#unittree-table span.td').css({cursor:'pointer'}).click(function() {
		var td = $(this);
		$.myChart.choseUnit(td.html(), td.closest('td').attr('data-depno'));
	});
</script>

<c:if test="${fn:length(conditions)>0}">

<div class="pageHeader" style="position: static;">

<s:form action="twodimenform!doStat.do" id="pagerForm">
	<input type="hidden" name="modelName" value="${modelName}">
	<input type="hidden" name="resultName" value="${resultName}">
	<input type="hidden" name="ec_o_${cp:MAPVALUE('stat_info','topdepno')}" value="true">
	
	<div class="searchBar">
		<ul class="searchContent">
			<c:forEach var="cond" items="${conditions}" varStatus="status">
				<c:choose>
					<%-- 隐藏属性 --%>
					<c:when test="${cond.condStyle =='H'}">
						<input type="hidden" name="${cond.condName}" value="${cond.condValue}">
					</c:when>
					
					<%-- 只读属性 --%>
					<c:when test="${cond.condStyle =='R'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<input type="text" readonly="readonly" name="${cond.condName}" value="${cond.condValue}" />
						</li>
					</c:when>
					
					<%-- 选择年份 --%>
					<c:when test="${cond.condStyle =='Y'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<select id="year${status.index }" name="${cond.condName}">
								<option value="2013" <c:if test="${cond.condValue=='2013' }">selected="selected"</c:if> >2013年</option>
								<option value="2012" <c:if test="${cond.condValue=='2012' }">selected="selected"</c:if> >2012年</option>
								<option value="2011" <c:if test="${cond.condValue=='2011' }">selected="selected"</c:if> >2011年</option>
								<option value="2010" <c:if test="${cond.condValue=='2010' }">selected="selected"</c:if> >2010年</option>
								<option value="2009" <c:if test="${cond.condValue=='2009' }">selected="selected"</c:if> >2009年</option>
								<option value="2008" <c:if test="${cond.condValue=='2008' }">selected="selected"</c:if> >2008年</option>
							</select>
						</li>
					</c:when>
					
					<%-- 选择月份 --%>
					<c:when test="${cond.condStyle =='M'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<select id="month" name="${cond.condName}">
								<option value="1" <c:if test="${cond.condValue=='1' }">selected="selected"</c:if> >1月</option>
								<option value="2" <c:if test="${cond.condValue=='2' }">selected="selected"</c:if>>2月</option>
								<option value="3" <c:if test="${cond.condValue=='3' }">selected="selected"</c:if>>3月</option>
								<option value="4" <c:if test="${cond.condValue=='4' }">selected="selected"</c:if>>4月</option>
								<option value="5" <c:if test="${cond.condValue=='5' }">selected="selected"</c:if>>5月</option>
								<option value="6" <c:if test="${cond.condValue=='6' }">selected="selected"</c:if>>6月</option>
								<option value="7" <c:if test="${cond.condValue=='7' }">selected="selected"</c:if>>7月</option>
								<option value="8" <c:if test="${cond.condValue=='8' }">selected="selected"</c:if>>8月</option>
								<option value="9" <c:if test="${cond.condValue=='9' }">selected="selected"</c:if>>9月</option>
								<option value="10" <c:if test="${cond.condValue=='10' }">selected="selected"</c:if>>10月</option>
								<option value="11" <c:if test="${cond.condValue=='11' }">selected="selected"</c:if>>11月</option>
								<option value="12" <c:if test="${cond.condValue=='12' }">selected="selected"</c:if>>12月</option>
							</select>
						</li>
					</c:when>
					
					<%-- 选择日期 --%>
					<c:when test="${cond.condStyle =='D'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<input type="text" class="Wdate" style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;" 
							value="${cond.condValue}"name="${cond.condName}" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						</li>
					</c:when>
					
					<c:when test="${cond.condStyle =='U'}">
						<li>
							<label>${cond.condLabel}：</label>
							<c:forEach var="u" items="${cp:UNITSLIST(primaryUnit) }" varStatus="st">
								<c:if test="${cond.condValue==u.depno }">
									<c:set var="unitname" value="${u.unitshortname }"></c:set>
								</c:if>
							</c:forEach>
							<input type="hidden" name="${cond.condName}" value="${cond.condValue }"/> 
							<input type="text" onclick="$.myChart.showUnitSearch(this, true)" readonly="readonly" value="${unitname }" style="cursor:hand" title="单击获取单位"/>
					</c:when>
					
					<%-- 数据字典 --%>
					<c:when test="${cond.condStyle =='P'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<select id="selectunit" name="${cond.condName}">
								<option value="">
									<c:out value="-- 请选择 --" />
								</option>
								<c:forEach var="row" items="${cp:LVB(cond.condType)}">
									<option value="${row.value}" <c:if test="${cond.condValue==row.value }">selected="selected"</c:if>>								
										<c:out value="${row.label}" />
									</option>
								</c:forEach>
							</select>
						</li>
					</c:when>
					
					<%-- 数据字典checkbox --%>
					<c:when test="${cond.condStyle =='C'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<c:set var="condValue" value=",${cond.condValue },"></c:set>
							<c:forEach var="row" items="${cp:LVB(cond.condType)}">
								<c:set var="rowValue" value=",${row.value },"></c:set>
								<input type="checkbox" name="${cond.condName}" value="${row.value}"
								 <c:if test="${fn:indexOf(condValue, rowValue)>=0 }">checked="true"</c:if>	><c:out value="${row.label}" />&nbsp;
							</c:forEach>
						</li>
					</c:when>
					
					<%-- 数据字典checkbox --%>
					<c:when test="${cond.condStyle =='S'}">
						<li>
							<label>${cond.condLabel}：</label> 
							<select name="${cond.condName}">
								<option value="">——请选择——</option>
								<c:forEach var="op" items="${datas[cond.condName] }">
									<option value="${op[0] }" title="${op[1] }" <c:if test="${op[0] == cond.condValue }">selected="selected"</c:if>>${op[1] }</option>	
								</c:forEach>
							</select>
						</li>
					</c:when>
					
					<c:otherwise>
						<li>
							<label>${cond.condLabel}：</label> 
							<input type="text" name="${cond.condName}" value="${cond.condValue}" />
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
			
		<div class="subBar">
			<ul>
				<li>
					<input type="button" value="导出" class="btn" onclick="javascript:exportTableToExcel();"/>
				</li>
			</ul>
			<ul>
				<li>
					<input type="button" value="打印" class="btn" onclick="javascript:doPrintStat();"/>
				</li>
			</ul>
		
			<ul>
				<li>
					<input type="submit" value="统计" class="btn"/>
				</li>
			</ul>
		</div>
	</div>
</s:form>
</div>
</c:if>
<form id="dataForm" action="${pageContext.request.contextPath}/stat/twodimenform!doExport.do" method="post">
	 <input name="exportData" id="exportData" type="hidden" ></input>
	 <input name="formName" id="formName" value="${formName }" type="hidden" ></input>
</form>
