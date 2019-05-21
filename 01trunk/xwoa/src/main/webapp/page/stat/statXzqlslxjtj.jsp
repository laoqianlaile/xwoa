<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>${formName}</title>
</head>
<body>
	<c:if test="${fn:length(conditions)>0}">
<s:form action="twodimenform">
                <input type="hidden" name="modelName" value="${modelName}">
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
										<c:forEach var="unit" items="${cp:UNITSLIST() }" varStatus="st">
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
							<c:when test="${cond.condStyle =='C'}">
								<td align="right">${cond.condLabel}</td>
								<td>
								<c:set var="condValue" value=",${cond.condValue },"></c:set>
								<c:forEach var="row" items="${cp:LVB(cond.condType)}">
									<c:set var="rowValue" value=",${row.value },"></c:set>
									<input type="checkbox" name="${cond.condName}" value="${row.value}"
									 <c:if test="${fn:indexOf(condValue, rowValue)>=0 }">checked="true"</c:if>	><c:out value="${row.label}" />&nbsp;
								</c:forEach>
								
							</td>
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
					<tr>
						<td></td>
						<td><s:submit type="submit" method="doStat" cssClass="btn"
								value="统计S" /></td>
					</tr>
				</table>
			</s:form>
	</c:if>


	<input type="hidden" name="statRows" value="${rowCount}" />
	<!-- 不包含统计数据行 -->
	<input type="hidden" name="isStatRow" value="${rowDrawChart}" />
	<!-- 是否按行画统计图   T 画  F 不画 -->
	<input type="hidden" name="statStartCol" value="${drawChartBeginCol}" />
	<!-- 按行画统计图  去数据起始列 -->
	<input type="hidden" name="statEndCol" value="${drawChartEndCol}" />
	<!-- 按行画统计图  去数据借宿列 -->

<c:forEach var="cond2" items="${conditions}" varStatus="status">
<c:if test="${cond2.condName=='qlstate'}">
<c:set var="qlstate">${cond2.condValue}</c:set>

</c:if>
</c:forEach>

	<c:set var="rowInd">0</c:set>
	<c:set var="rowNum">-1</c:set>
	<%String longUrlc=request.getAttribute("logicUrl").toString();
	if(request.getParameter("qlstate")==null||request.getParameter("qlstate").toString().equals("A")){
		 longUrlc=longUrlc.replace("isSuspend=1","isSuspend=0");
		 longUrlc=longUrlc.replace("isDisuse=1","isDisuse=0");
	}
	else if(request.getParameter("qlstate").toString().equals("N")){

		 longUrlc=longUrlc.replace("isSuspend=1","isSuspend=1");
		 longUrlc=longUrlc.replace("isDisuse=1","isDisuse=1");
	}
	else if(request.getParameter("qlstate").toString().equals("X")){

		 longUrlc=longUrlc.replace("isSuspend=1","isSuspend=0");
		 longUrlc=longUrlc.replace("isDisuse=1","isDisuse=1");
	}
	else if(request.getParameter("qlstate").toString().equals("T")){

		 longUrlc=longUrlc.replace("isSuspend=1","isSuspend=1");
		 longUrlc=longUrlc.replace("isDisuse=1","isDisuse=0");
	}
	request.setAttribute("logicUrl", longUrlc);

	%>

	<ec:tree identifier="col0" parentAttribute="col1"
		items="ListObjects_formData" action="twodimenform!doStat.do"
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false" var="optinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
		<ec:row>
			<c:forEach var="col" items="${columns}" varStatus="status">
				<c:set var="name" value="col${status.index }" />
				<c:if test="${status.index==0 }">
				<c:set var="depno">${optinfo[name] }</c:set>
				</c:if>
				<c:if test="${rowInd == 2}">
					<ec:column property="${col.colProperty}" title="${col.colName}"
						style="text-align:left" cell="org.extremecomponents.tree.TreeCell" />
				</c:if>
				<c:if test="${rowInd > 2}">

					<ec:column property="${col.colProperty}" title="${col.colName}"
						sortable="false" style="text-align:left">
						<c:if test="${col.colLogic!=null}">
							<a href="${logicUrl }&depID=${depno}${col.colLogic}">
						</c:if>
					    ${optinfo[name] }
					     <c:if test="${col.colLogic!=null}">
							</a>
						</c:if>
					</ec:column>

				</c:if>
				<c:set var="rowInd">${rowInd+1}</c:set>
			</c:forEach>
		</ec:row>
		<c:set var="rowNum">${rowNum+1}</c:set>
	</ec:tree>

</body>
<script type="text/javascript">
	$(function() {
		$.myChart.init('#ec', $.evalJSON('${jsonFormData}'), {defaultType:'column'});
	});


</script>
</html>