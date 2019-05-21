<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<div class="pageHeader">
	<s:form action="queryModel!list.do" namespace="/stat" id="pagerForm" onsubmit="return navTabSearch(this);" >
		<input type="hidden" name="pageNum" value="1" /> 
		<input type="hidden" name="numPerPage" value="${pageDesc.pageSize}" />
		<input type="hidden" name="orderField" value="${param['orderField'] }" /> 
		<input type="hidden" name="orderDirection" value="${param['orderDirection'] }" />
		
		<div class="searchBar">
			<ul class="searchContent">
 				<li>
					<label>模板编码：</label> 
					<s:textfield name="s_modelName" />
				</li>
<%--				
				<li>
					<label>查询语句：</label> 
					<s:textfield name="s_querySql" />
				</li>
				
				<li>
					<label>查询描述：</label> 
					<s:textfield name="s_queryDesc" />
				</li> --%>
				
				<li>
					<label>模板名称：</label> 
					<s:textfield name="s_formNameFormat" />
				</li>
				
				<li>
					<label>返回页面：</label> 
					<s:textfield name="s_resultName" />
				</li>
				
				<%-- <li>
					<label>是否按行画图：</label> 
					<s:textfield name="s_rowDrawChart" />
				</li>
				
				<li>
					<label>画图数据起始列：</label> 
					<s:textfield name="s_drawChartBeginCol" />
				</li>
				
				<li>
					<label>画图数据结束列：</label> 
					<s:textfield name="s_drawChartEndCol" />
				</li>
				
				<li>
					<label>是否添加额外行：</label> 
					<s:textfield name="s_additionRow" />
				</li>
				
				<li>
					<label>行逻辑：</label> 
					<s:textfield name="s_rowLogic" />
				</li> --%>
			</ul>
			
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</s:form>
</div>

<div class="pageContent">
	<c:set var="panel_buttons">
		<a class="add" href="${pageContext.request.contextPath }/stat/queryModel!built.do" 
			target="navTab" rel="statDefForm" title="新增统计模板">
			<span>新增</span>
		</a>
	</c:set>
	
	<%@ include file="../common/panelBar.jsp"%>
	<table class="list" style="width:100%" layoutH=".pageHeader 55">
	
		<thead>
			<tr align="center">
				<th>模板编码</th>
				<!--<th>查询类别</th>
				<th>属主类别</th>
				<th>属主代码</th>--> 
				<th>模板名称</th>
				<th>返回页面</th>
				<th>查询描述</th>
				<!--<th>按行画图</th>
				<th>画图起始列</th>
				<th>画图结束列</th>
				<th>添加额外行</th>-->
				<th>操作</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${objList}" var="queryModel">
				<tr>
					<td>${queryModel.modelName}</td>
<%--				<td>${queryModel.modelType}</td>
 					<td>${queryModel.ownerType}</td>
					<td>${queryModel.ownerCode}</td> --%>
					<td>${queryModel.formNameFormat}</td>
					<td>${queryModel.resultName}</td>
					<td>${queryModel.queryDesc}</td>
<%--					<td>
						${cp:MAPVALUE('TrueOrFalse', queryModel.rowDrawChart) }
					</td>
					<td>${queryModel.drawChartBeginCol}</td>
					<td>${queryModel.drawChartEndCol}</td>
					<td>
						${cp:MAPVALUE('TrueOrFalse', queryModel.additionRow) }
					</td>--%>
					<td>
					
						<%-- <a href='${pageContext.request.contextPath }/stat/queryModel!view.do?modelName=${queryModel.modelName}'  
							target="navTab" rel="statDefView" title="查看统计模块">
							<span class="icon icon-search"></span>
						</a> --%>
						<a href='${pageContext.request.contextPath }/stat/queryModel!edit.do?modelName=${queryModel.modelName}'
							target="navTab" rel="statDefForm" title="编辑统计模板">
							<span class="icon icon-edit" title="编辑"></span>
						</a>
						<a href='${pageContext.request.contextPath }/stat/queryModel!delete.do?modelName=${queryModel.modelName}'
							target="ajaxTodo" title="确定删除吗？">
							<span class="icon icon-trash" title="删除"></span>	
						</a>
						<a href="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=${queryModel.modelName}" 
							title='${queryModel.formNameFormat}' target="navTab" rel="show_tab" external="true">
							<span title="统计模块展示" class="icon icon-link"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../common/panelBar.jsp"%>
