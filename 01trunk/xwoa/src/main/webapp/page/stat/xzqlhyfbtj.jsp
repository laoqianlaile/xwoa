<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>${formName}</title>
</head>
<body>

			<s:form action="twodimenform">

				<input type="hidden" name="resultName" value="${resultName}">
				<input type="hidden" name="ec_o_${cp:MAPVALUE('stat_info','topdepno')}" value="true">
				<table cellpadding="0" cellspacing="0" align="center" class="tablegray">
					<tr height="12">
						<td>
							<img src="${pageContext.request.contextPath}/styles/default/images/imgIndex/jt3.gif" width="11" height="11"> 当前位置：
							<span class="fontred">${formName}</span>
						</td>
						<td></td>
					</tr>
					<c:forEach var="cond" items="${conditions}" varStatus="status">
						<c:if test="${status.index%3==0 }">
							<tr height="30">
						</c:if>
						<c:choose>
							<c:when test="${cond.condStyle =='H'}">
								<input type="hidden" name="${cond.condName}"
									value="${cond.condValue}">
							</c:when>
							<c:when test="${cond.condStyle =='R'}">
								<td align="right">${cond.condLabel}</td>
								<td><input type="text" readonly="readonly"
									name="${cond.condName}" value="${cond.condValue}" /></td>
							</c:when>
							<c:when test="${cond.condStyle =='Y'}">
								<td align="right">${cond.condLabel}</td>
								<td><select id="year${status.index }"
									name="${cond.condName}">
										<option value="2012"
											<c:if test="${cond.condValue=='2012' }">selected="selected"</c:if>>2012年</option>
										<option value="2011"
											<c:if test="${cond.condValue=='2011' }">selected="selected"</c:if>>2011年</option>
										<option value="2010"
											<c:if test="${cond.condValue=='2010' }">selected="selected"</c:if>>2010年</option>
										<option value="2009"
											<c:if test="${cond.condValue=='2009' }">selected="selected"</c:if>>2009年</option>
										<option value="2008"
											<c:if test="${cond.condValue=='2008' }">selected="selected"</c:if>>2008年</option>
								</select></td>
							</c:when>
							<c:when test="${cond.condStyle =='M'}">
								<td align="right">${cond.condLabel}</td>
								<td><select id="month" name="${cond.condName}">
										<option value="1"
											<c:if test="${cond.condValue=='1' }">selected="selected"</c:if>>1月</option>
										<option value="2"
											<c:if test="${cond.condValue=='2' }">selected="selected"</c:if>>2月</option>
										<option value="3"
											<c:if test="${cond.condValue=='3' }">selected="selected"</c:if>>3月</option>
										<option value="4"
											<c:if test="${cond.condValue=='4' }">selected="selected"</c:if>>4月</option>
										<option value="5"
											<c:if test="${cond.condValue=='5' }">selected="selected"</c:if>>5月</option>
										<option value="6"
											<c:if test="${cond.condValue=='6' }">selected="selected"</c:if>>6月</option>
										<option value="7"
											<c:if test="${cond.condValue=='7' }">selected="selected"</c:if>>7月</option>
										<option value="8"
											<c:if test="${cond.condValue=='8' }">selected="selected"</c:if>>8月</option>
										<option value="9"
											<c:if test="${cond.condValue=='9' }">selected="selected"</c:if>>9月</option>
										<option value="10"
											<c:if test="${cond.condValue=='10' }">selected="selected"</c:if>>10月</option>
										<option value="11"
											<c:if test="${cond.condValue=='11' }">selected="selected"</c:if>>11月</option>
										<option value="12"
											<c:if test="${cond.condValue=='12' }">selected="selected"</c:if>>12月</option>
								</select></td>
							</c:when>
							<c:when test="${cond.condStyle =='D'}">
								<td align="right">${cond.condLabel}</td>
								<td><input type="text" name="${cond.condName}" class="date"
									value="${cond.condValue}" pattern="yyyy-MM-dd" yearstart="-10"
									yearend="0" /></td>
							</c:when>
							<c:when test="${cond.condStyle =='U'}">
								<td align="right">${cond.condLabel}</td>
								<td><input type="hidden" name="${cond.condName}" /> <input
									type="text" onclick="$.myChart.showUnitSearch(this, true)"
									readonly="readonly" />

									<div id="unitbox"></div> <script type="text/javascript">
										var tree = new MzTreeView("tree");
										tree
												.setIconPath("${pageContext.request.contextPath}/scripts/Mztreeview1.0/TreeView/");
										<c:forEach var="unit" items="${units }" varStatus="st">
										tree.nodes["${unit.parentunit}_${unit.unitcode}"] = "text:${unit.unitshortname };method:$.myChart.choseUnit(tree, '${unit.depno}')";
										</c:forEach>

										document.getElementById('unitbox').innerHTML = tree
												.toString();
									</script></td>
							</c:when>

							<c:when test="${cond.condStyle =='P'}">
								<td align="right">${cond.condLabel}</td>
								<td><select id="selectunit" name="${cond.condName}">

										<c:forEach var="row" items="${cp:LVB(cond.condType)}">

											<option
												<c:if test="${cond.condValue==row.value }">selected="selected"</c:if>
												value="${row.value}">
												<c:out value="${row.label}" />
											</option>
										</c:forEach>
								</select></td>
							</c:when>


							<c:otherwise>
								<td align="right">${cond.condLabel}</td>
								<td><input type="text" name="${cond.condName}"
									value="${cond.condValue}" /></td>
							</c:otherwise>
						</c:choose>


						<c:if test="${(status.index+1)%3==0 }">
							</tr>
						</c:if>
					</c:forEach>
				<%if(request.getParameter("isxj")==null||Integer.parseInt(request.getParameter("isxj"))!=1){%>
				
					<td>是否合并&nbsp; <select id="selectunit" name="modelName">

							<option
								<c:if test="${modelName=='xzqlhyfbtjhb'}">selected="selected"</c:if>
								value="xzqlhyfbtjhb">是</option>
							<option
								<c:if test="${modelName=='xzqlhyfbtj'}">selected="selected"</c:if>
								value="xzqlhyfbtj">否</option>

					</select>
					</td>
				  <% }else{%>
				   <input type="hidden" name="isxj" value="1">
				  <input type="hidden" name="modelName" value="${modelName}">
				  
				  <% }%>
					<tr>
						<td></td>
						<td><s:submit type="submit" method="doStat" cssClass="btn"
								value="统计" /></td>
					</tr>
				</table>
			</s:form>


	<input type="hidden" name="statRows" value="${rowCount}" />
	<!-- 不包含统计数据行 -->
	<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
	<!-- 是否按行画统计图   T 画  F 不画 -->
	<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
	<!-- 按行画统计图  去数据起始列 -->
	<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
	<!-- 按行画统计图  去数据借宿列 -->



	<c:set var="rowInd">0</c:set>
	<div class="eXtremeTable">
		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
		
			<jsp:include page="childs/normalTableHead.jsp"></jsp:include>
			
			<jsp:include page="childs/normalTableBody.jsp"></jsp:include>
			
		</table>
	</div>

</body>
<script type="text/javascript">
	$(function() {
		$.myChart.init('#ec_table', $.evalJSON('${jsonFormData}'), {
			defaultType:'pie',
			stat:[{cell:'合计', type:'row'}]
		});
	});


</script>
</html>