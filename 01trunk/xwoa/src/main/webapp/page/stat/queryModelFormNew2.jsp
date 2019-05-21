<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<style>
.reportTypeTable {width:900px; text-align: center; margin:10% auto;}
.reportTypeTable td.text {padding:20px; font-size: 22px; font-family: "微软雅黑"}

.reportTypeTable .selected-type {font-weight: bold; }
.reportTypeTable .selected-type .shadow-img {border:4px solid #FCE59F;}
.reportTypeTable .selected-type .shadow-img,.reportTypeTable .hover-type .shadow-img {
	-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=100)"; /* ie8  */
    filter:alpha(opacity=100);    /* ie5-7  */
    opacity: 1;    /* css standard, currently it works in most modern browsers  */
}

.reportTypeTable .shadow-img {
	moz-box-shadow: 5px 5px 8px #888; -webkit-box-shadow: 5px 5px 8px #888; box-shadow: 5px 5px 8px #888; 
	border-radius: 8px;
	-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=50)"; /* ie8  */
    filter:alpha(opacity=50);    /* ie5-7  */
    opacity: 0.5;    /* css standard, currently it works in most modern browsers  */
}

.reportTypeTable .img-tr img{cursor:pointer;}

.form-page {padding:20px; margin:auto;}
#step-2 .form-page { background-color:#DBEEF4; 
-webkit-border-radius: 10px 10px 10px 10px; -moz-border-radius: 10px 10px 10px 10px; border-radius: 10px 10px 10px 10px;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#3f4747), to(#1f2424));
	background-image: -webkit-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -moz-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -ms-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -o-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: linear-gradient(top, #EEF3F6, #B3DAE9);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#EEF3F6', EndColorStr='#B3DAE9');
}
.form-page .form-title {height:42px; line-height: 26px; font-size:16px; font-family:"微软雅黑"; text-align: right;}
.form-page .form-content input.textInput{height:26px;width:280px; font-size: 14px;}

/* 表格样式 */
.form-page table {border-collapse: collapse; border-spacing:0; text-align:center; table-layout:fixed; width:100%;}
.form-page table.modelColumn td {white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
.form-page table.modelColumn td div { height:24px; line-height:24px;}
.form-page table.modelColumn th {height:20px; font-size:12px; font-weight:bold; color:#333; border-left:1px solid #ced2d5; border-top:1px solid #bdc8d2; background:#dddfe1; }
.form-page table.modelColumn th.index {width:10px;}
.form-page table.modelColumn th.show {width:30px;}
.form-page table.modelColumn th.name {width:80px;}
.form-page table.modelColumn th.type {width:150px;}
.form-page table.modelColumn th.format {width:150px;}
.form-page table.modelColumn th.link {width:150px;}
.form-page table.modelColumn th.operator {width:75px;}
.form-page table.modelColumn th.first,.form-page table.modelColumn th.second {border-left:none;}

.form-page table.modelColumn th.first {-webkit-border-radius: 0 0 0 10px; -moz-border-radius: 0 0 0 10px; border-radius: 0 0 0 10px; border-left:none;} 
.form-page table.modelColumn th.last {-webkit-border-radius: 0 0 10px 0; -moz-border-radius: 0 0 10px 0; border-radius: 0 0 10px 0;}

.form-page table.modelColumn td {border-left:1px solid #ced2d5; font-size:14px; padding:14px 0 14px;}
.form-page table.modelColumn td.first,.form-page table.modelColumn td.second {border:0;}
.form-page table.modelColumn td.first {cursor:move;-webkit-border-radius: 10px 0 0 10px; -moz-border-radius: 10px 0 0 10px; border-radius: 10px 0 0 10px;}
.form-page table.modelColumn td.first:hover {background-color: #A8CBF7;}
.form-page table.modelColumn td.last {-webkit-border-radius: 0 10px 10px 0; -moz-border-radius: 0 10px 10px 0; border-radius: 0 10px 10px 0;}
.form-page table.modelColumn tr.even{background-color: #EEF0F0;}
.form-page table.modelColumn tr.selected {background-color: #A8CBF7;}

.form-page table.modelColumn tr.row td.first {background-color: #EA8511;}
.form-page table.modelColumn tr.column td.first {background-color: #8CC63F;}

.form-page table.modelColumn td.editable:hover {background-color:#FCE59F;}
.form-page table.modelColumn td span {cursor: pointer;}
.form-page table.modelColumn td select {height:22px; padding:2px;}
.form-page table.modelColumn td input.textInput {height:20px; padding:0; line-height: 20px;} 
.form-page table.modelColumn td.name input.textInput {width:90px;}

/*表头样式*/
.section-header {-moz-border-radius: 8px 8px 0 0; -webkit-border-radius: 8px 8px 0 0; border-radius: 8px 8px 0 0;
	height: 42px; line-height:42px; border: 1px solid #B8D0D6; border-bottom:0; background-color: #B3DAE9;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#3f4747), to(#1f2424));
	background-image: -webkit-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -moz-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -ms-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: -o-linear-gradient(top, #EEF3F6, #B3DAE9);
	background-image: linear-gradient(top, #EEF3F6, #B3DAE9);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#EEF3F6', EndColorStr='#B3DAE9');
}

.section-header .toolbar {height:32px; line-height:32px; margin:5px 20px; float:right;}
.section-header .toolbar span {cursor:pointer; margin-right:2px; vertical-align: baseline;}

.column-type .window {margin-bottom:10px;}
.column-type .window .section-header {height:24px; line-height:24px; padding-left:10px; font-size: 14px; font-weight: bold;}
.column-type .window .section-content {height:200px; border:1px solid #B8D0D6; }
</style>


<s:form action="queryModel!save.do" method="post" namespace="/stat" id="pagerForm" 
	onsubmit="return validateCallback(this, navTabAjaxDone);">
<div id="wizard" class="swMain" style="margin-left:auto; margin-right:auto;">
	<ul>
		<li>
			<a renderTo="#step-1">
	            <span class="stepNumber">1</span>
	            <span class="stepDesc">
	               	模板类型<br />
	               <small>选择模板类型</small>
	            </span>
	        </a>
	    </li>
	    
		<li>
			<a renderTo="#step-2">
	            <span class="stepNumber">2</span>
	            <span class="stepDesc">
	               	模板详情<br />
	               <small>填写模板信息，查询SQL</small>
	            </span>
	        </a>
	    </li>
		
		<li>
			<a renderTo="#step-3">
	            <span class="stepNumber">3</span>
	            <span class="stepDesc">
	              	 展示字段<br />
	               <small>设置展示字段，以及样式、格式</small>
	            </span>                   
	         </a>
         </li>
         
         <li>
	         <a renderTo="#step-4">
	            <span class="stepNumber">4</span>
	            <span class="stepDesc">
	               	查询参数<br />
	               <small>添加查询参数，设置过滤条件</small>
	            </span>
	        </a>
	     </li>
	</ul>
	
	<div id="step-1">	
		<jsp:include page="./childs/modelType.jsp"></jsp:include>
   	</div>
    	
	<div id="step-2">
		<jsp:include page="./childs/modelDetail.jsp"></jsp:include>
   	</div>  
    	                    
	<div id="step-3">
		<div class="container_12 form-page">
			<div class="grid_9 column-table">
				<div class="section-header">
					<div class="toolbar">
						<span class="icon32 icon-color icon-plus" title="添加展示字段" />
					</div>
				</div>
				
				<div class="section-content">
					<table class="modelColumn">
						<thead>
							<tr>
								<th class="first index">&nbsp;</th>
								<th class="show second">显示</th>
								<th class="name">字段名</th>
								<th class="format">格式</th>
								<th class="link">链接</th>
								<th class="last operator">操作</th>
							</tr>
						</thead>
						
						<tbody>
							<tr class="odd">
								<td class="first">&nbsp;</td>
								<td class="second">
									<input type="checkbox" name="isShow" />
								</td>
								<td class="name editable">
									<input type="text" name="colName" value="员工编号" />
								</td>
								<td class="format editable">
									<select>
										<option>字符串</option>
										<option selected="selected">数字</option>
										<option>百分比</option>
										<option>日期</option>
									</select>
									<select>
										<option>000,000</option>
										<option selected="selected">000,000.0</option>
										<option>000,000.00</option>
										<option>000,000.000</option>
									</select>
								</td>
								<td class="link editable">
									<select>
										<option selected="selected">新窗口</option>
										<option>TAB页</option>
										<option>弹出框</option>
									</select>
									
									<input type="text" name="colLogic" value="?name=人员编号" />
								</td>
								<td class="last">
									<span class="icon icon-darkgray icon-trash" title="删除" />
									<span class="icon icon-darkgray icon-carat-2-ew" title="设置为行头" />
									<span class="icon icon-darkgray icon-carat-2-ns" title="设置为列头" />
								</td>
							</tr>
							
							<tr class="even">
								<td class="first">&nbsp;</td>
								<td class="second">
									<input type="checkbox" name="isShow" />
								</td>
								<td class="name editable">
									<input type="text" name="colName" value="员工姓名" />
								</td>
								<td class="format editable">
									<select>
										<option>字符串</option>
										<option>数字</option>
										<option>百分比</option>
										<option>日期</option>
									</select>
									<select>
										<option>000,000</option>
										<option>000,000.0</option>
										<option>000,000.00</option>
										<option>000,000.000</option>
									</select>
								</td>
								<td class="link editable">
									<select>
										<option>新窗口</option>
										<option>TAB页</option>
										<option>弹出框</option>
									</select>
									
									<input type="text" name="colLogic" />
								</td>
								<td class="last">
									<span class="icon icon-darkgray icon-trash" title="删除" />
									<span class="icon icon-darkgray icon-carat-2-ew" title="设置为行头" />
									<span class="icon icon-darkgray icon-carat-2-ns" title="设置为列头" />
								</td>
							</tr>
							
							<tr class="odd">
								<td class="first">&nbsp;</td>
								<td class="second">
									<input type="checkbox" name="isShow" />
								</td>
								<td class="name editable">
									<input type="text" name="colName" value="工作量" />
								</td>
								<td class="format editable">
									<select>
										<option>字符串</option>
										<option>数字</option>
										<option>百分比</option>
										<option>日期</option>
									</select>
									<select>
										<option>000,000</option>
										<option>000,000.0</option>
										<option>000,000.00</option>
										<option>000,000.000</option>
									</select>
								</td>
								<td class="link editable">
									<select>
										<option>新窗口</option>
										<option>TAB页</option>
										<option>弹出框</option>
									</select>
									
									<input type="text" name="colLogic" />
								</td>
								<td class="last">
									<span class="icon icon-darkgray icon-trash" title="删除" />
									<span class="icon icon-darkgray icon-carat-2-ew" title="设置为行头" />
									<span class="icon icon-darkgray icon-carat-2-ns" title="设置为列头" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>		
			<div class="grid_3 column-type">
				<div class="window">
					<div class="section-header small">行头</div>
					<div class="section-content small">
						<table class="list">
							<thead>
								<tr>
									<th>名称</th>
									<th>排序</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>员工姓名</td>
									<td> <select><option selected="selected">正序</option><option>倒序</option></select> </td>
								</tr>
								<tr>
									<td>员工编码</td>
									<td> <select><option selected="selected">正序</option><option>倒序</option></select> </td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="window">
					<div class="section-header small">列头</div>
					<div class="section-content small"></div>
				</div>
			</div>
		</div>
   	</div>
   	
   	<div id="step-4">
   	</div>
    	
</div>
</s:form>
<!-- End SmartWizard Content -->  		
    			
	 
<script type="text/javascript">
    $(document).ready(function(){
    	var layoutBox = $('.layoutBox');
    	var width = layoutBox.width();
    	var height = layoutBox.height();
    	
    	// Smart Wizard 	
  		$('#wizard').smartWizard({
  			labelNext:'下一步',
            labelPrevious:'上一步',
            labelFinish:'提交',
            fixed: {width:width - 30, height:height - 150}
  		});
    	
    	changeModelType();
    	
    	editTable();
    	
    	$('#step-3').click(disableEditTable);
    	

	});
    
    function editTable() {
    	$('td.editable').each(function() {
    		
    		var $this = $(this);
    		var value = [];
    		
    		// 隐藏编辑框
    		$('input[type=text],select', $this).each(function() {
    			var element = $(this);
    			
    			if (element.is('input')) {
    				value.push(element.val());
    			}
    			else if (element.is('select')) {
    				value.push($('option[selected=selected]', element).text());
    			}
    			
    			
    			element.hide();
    		});
    		
    		var text = $this.text();
    		
    		$this.click(enableEditTable).css({
    			cursor:'pointer'
    		}).removeClass('editing').append('<div>'+ value.join(' ') +'</div>');
    		
    	});
    }
    
    // 编辑
    function enableEditTable(event) {
    	
    	var $this = $(this);
    	
    	if ($this.hasClass('editing')) return true;
    	
    	$this.addClass('editing').css({
    		cursor:'text'
    	}).find('div').fadeOut("fast", function() {
    		$this.find('input[type=text],select').fadeIn("fast");
    	});
    }
    
    // 取消编辑
    function disableEditTable(event) {
    	
    	var target = $(event.target);
    	if (target.hasClass('editing') || target.parent('td').hasClass('editing')) return true;
    	
    	var value = [];
    	$('td.editing').removeClass('editing').css({cursor:'pointer'}).each(function() {
    		var $this = $(this);
    		var value = [];
    		
    		$('input[type=text],select', $this).each(function() {
    			var element = $(this);
    			
    			if (element.is('input')) {
    				value.push(element.val());
    			}
    			else if (element.is('select')) {
    				value.push($('option[selected=selected]', element).text());
    			}
    			
    		}).fadeOut("fast", function() {
    			$this.find('div:last').html(value.join(' ')).fadeIn("fast");
    		});
    		
    	});
    }
    
</script>