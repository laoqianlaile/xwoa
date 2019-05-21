<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<style type="text/css">	
	div#divcopy {
		position:absolute; 
		padding:0 2px 0 3px; 
		width:120px; 
		line-height:23px; 
		height:23px; 
		background-color:#EFEFEF; 
		border:1px solid black;
		display:none;
	}
	
	#divcopy div {
		float:left;
	}
	
	div#divimg {
		width:20px; 
		height:21px; 
		background:url(../../styles/images/copy.png) no-repeat; 
		cursor:hand;
	}
</style>

<script type="text/javascript">
	function locateMe(parent, obj) {
		parent = $(parent);
		obj = $(obj);
		
		obj.css({
			top:parent.position().top+5,
			left:parent.position().left+parent.width()-obj.width()
		});
	}

	$(function() {
		var td=$('#tdsql');
		var div=$('#divcopy');
		
		// 不支持非IE浏览器复制剪切板
		if (!window.clipboardData) return false;
		
		locateMe(td, div);
		
		// 悬停
		td.hover(function() {
			div.fadeIn(500);
		}, function() {
			div.fadeOut(500);
		});
		
		$(window).resize(function() {
			locateMe(td, div);
		});
		
		div.find('div#divimg').click(function() {
			var sql = $('#sqlValue').attr('value');
			
			window.clipboardData.setData('text', sql); 
			alert('复制成功');
		}); 
		
		
	});
</script>

<div class="pageContent">

	<div class="panel panelDrag">
		<h1>模板详情</h1>
		<div class="pageFormContent">
	
			<div class="unit">
				<label>
					模板编码：
				</label>
				<label>
					<s:property value="%{modelName}" />
				</label>
				
				<label>
					模板名称：
				</label>
				<label>
					<s:property value="%{formNameFormat}" />
				</label>
				
				<label>
					查询描述：
				</label>
				<label>
					<s:property value="%{queryDesc}" />
				</label>
				
				<label>
					返回页面：
				</label>
				<label>
					<s:property value="%{resultName}" />
				</label>
			</div>		

			<div class="unit">
				<label>
					按行画图：
				</label>
				<label>
					${cp:MAPVALUE('TrueOrFalse', rowDrawChart) }
				</label>
				
				<label>
					画图起始列：
				</label>
				<label>
					<s:property value="%{drawChartBeginCol}" />
				</label>
				
				<label>
					画图结束列：
				</label>
				<label>
					<s:property value="%{drawChartEndCol}" />
				</label>
				
			</div>

			<div class="unit">
				<label>
					添加额外行：
				</label>
				<label>
					${cp:MAPVALUE('TrueOrFalse', additionRow) }
				</label>
			
				<label>
					逻辑行：
				</label>
				<label>
					<s:property value="%{rowLogic}" />&nbsp;
				</label>
				
				<label>
					逻辑地址：
				</label>
				<label>
					<s:property value="%{logicUrl}" />&nbsp;
				</label>
			</div>

			<div class="unit">
				<label>
					查询语句：
				</label>
				
				<div id="divcopy">
						<div>复制SQL到剪切板</div>
						<div id="divimg"></div>
				</div>
				<s:property value="%{querySql}" />
				<input type="hidden" id="sqlValue" value="${querySql }" /> 
			</div>
		</div>
		
		<div class="subBar">
			<ul>
				<li style="float:right; margin-right:50px;">
					<div class="buttonActive" style="margin-right:5px;">
						<div class="buttonContent">
							<button type="button" onclick="navTab.openTab('show', '${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=${modelName}', {title:'${formNameFormat}', external:true});">展示</button>
						</a>
						</div>
					</div>
					
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="navTab.closeCurrentTab('external_statmod')">返回</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>		

	<div class="panel panelDrag">
		<h1>展示字段</h1>
		<div class="pageFormContent">
			<table id="ec_table" class="list" width="100%" >
				<thead align="center">
					<tr>
						<th>字段名称</th>
						<th>数据操作</th>
						<th>是否画图</th>
						<th>数据类型</th>	
						<th>列逻辑</th>
						<th>顺序</th>
				        <th>是否显示</th>
					</tr>  
				</thead>
				<tbody align="center">
					<c:forEach var="queryColumn" items="${object.queryColumns}">    
						<tr>
							<td><c:out value="${queryColumn.colName}"/></td>  
							<td><c:out value="${queryColumn.optType}"/></td>  
							<td>
								${cp:MAPVALUE('TrueOrFalse', queryColumn.drawChart) }
							</td>  
							<td><c:out value="${queryColumn.colType}"/></td>  
							<td><c:out value="${queryColumn.colLogic}"/></td> 
							<td><c:out value="${queryColumn.colorder}"/></td> 
							<td>
								${cp:MAPVALUE('TrueOrFalse', queryColumn.isShow) }
							</td>
						</tr>  
					</c:forEach> 
				</tbody>        
			</table>
		</div>	
	</div>
	
	<div class="panel panelDrag">
		<h1>查询参数</h1>
		<div class="pageFormContent">
			
			<table id="ec_table" class="list" width="100%" >
				<thead align="center">
					<tr>
						<th>参数编码</th>
						<th>参数名称</th>
						<th title="参数样式中选择数据字典时，此栏有效">数据字典</th>
						<th>参数样式</th>
						<th>默认值</th>
				        <th>顺序</th>
				        <th>条件目标位置</th>	
				        <th>添加过滤语句</th>
					</tr>  
				</thead>
				<tbody align="center">
					<c:forEach var="queryConditon" items="${object.queryConditons}">    
						<tr>
							<td><c:out value="${queryConditon.condName}"/></td>  
							<td><c:out value="${queryConditon.condLabel}"/></td>  
							<td><c:out value="${queryConditon.condType}"/></td>  
							<td><c:out value="${queryConditon.condStyle}"/></td>
							<td><c:out value="${queryConditon.condValue}"/></td>  
							<td><c:out value="${queryConditon.conorder}"/></td> 
							<td><c:out value="${queryConditon.condPlace}"/></td>
							<td><c:out value="${queryConditon.condFilterSql}"/></td> 
						</tr>  
					</c:forEach> 
				</tbody>        
			</table>
		</div>
	</div>
</div>

