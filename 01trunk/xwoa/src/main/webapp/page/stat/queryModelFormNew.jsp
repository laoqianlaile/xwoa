<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<style>
table.statTable td {width:130px;}
table.statTable input[type=text],table.statTable select {
	width:120px;
	clear:both;
	margin:0;
}

</style>

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
            fixed: {width:width - 30, height:height - 150},
            onFinish:function() {
                $("table#t_queryColumn tr").each(function(i)
                {
                    $(this).attr("id", "tr_queryColumn" + i);
                    $("#tr_queryColumn" + i + "  input[type='text']").each(function(j)
                    {
                        $(this).attr("name", "newQueryColumns["+(i-1)+"]." + CentitUI.queryColumnColName[j]);
                    });
                });
                $("table#t_queryConditon tr").each(function(i)
                {
                    $(this).attr("id", "tr_queryConditon" + i);
                    $("#tr_queryConditon" + i + "  input[type='text']").each(function(j)
                    {
                       $(this).attr("name", "newQueryConditons["+(i-1)+"]." + CentitUI.queryConditonColName[j]);
                    });
                });
                
                $('#pagerForm', navTab.getCurrentPanel()).submit();
            }
  		});
      
	});
</script>

<s:form action="queryModel!save.do" method="post" namespace="/stat" id="pagerForm" onsubmit="return validateCallback(this, navTabAjaxDone);">
<div id="wizard" class="swMain" style="margin-left:auto; margin-right:auto;">
	<ul>
		<li>
			<a renderTo="#step-1">
	            <span class="stepNumber">1</span>
	            <span class="stepDesc">
	               	模板详情<br />
	               <small>填写模板信息，查询SQL</small>
	            </span>
	        </a>
	    </li>
		
		<li>
			<a renderTo="#step-2">
	            <span class="stepNumber">2</span>
	            <span class="stepDesc">
	              	 展示字段<br />
	               <small>设置展示字段，以及样式、格式</small>
	            </span>                   
	         </a>
         </li>
         
         <li>
	         <a renderTo="#step-3">
	            <span class="stepNumber">3</span>
	            <span class="stepDesc">
	               	查询参数<br />
	               <small>添加查询参数，设置过滤条件</small>
	            </span>
	        </a>
	     </li>
	</ul>
	
	<div id="step-1">	
		<div class="pageFormContent">
			<div class="unit">	
				<label>
					模板类型：
				</label>
				
				<s:select class="combox" list="#{'2':'普通二维报表', '3':'同比分析报表' , '4':'环比分析报表', '5':'交叉报表'}" 
					name="modelType" listKey="key" listValue="value" cssStyle="margin:0;">
				</s:select>
				
				<label>
					类型：
				</label>
				
				<s:select class="combox" list="#{'R':'报表', 'M':'菜单'}" 
					name="ownerType" listKey="key" listValue="value" cssStyle="margin:0;">
				</s:select>
				
				<label>上级代码：</label>
				<s:textfield name="ownerCode" />
			</div>
		
			<div class="unit">
				<label>模板编码：</label>
				<s:textfield name="modelName" />
			</div>
			
			<div class="unit">	
				<label>
					模板名称：
				</label>
				<s:textfield name="formNameFormat" />
			</div>
			
			<div class="unit">	
				<label>
					查询描述：
				</label>
				<s:textfield name="queryDesc" />
			</div>
			
			<div class="unit">	
				<label>
					返回页面：
				</label>
				<s:textfield name="resultName" />
			</div>
			
			<div class="unit">
				<s:hidden name="rowLogic" />
				
				<label>
					链接地址：
				</label>
				<s:textfield name="logicUrl" size="100"/>
			</div>
			
			<div class="unit">	
				<label>
					数据操作：
				</label>
				
				<label>
					<s:select class="combox" list="#{'0':'无操作', '1':'合计'}" name="additionRow" listKey="key" listValue="value">
					</s:select>
				</label>
				
				<input type="hidden" name="rowDrawChart" value="F">
				<input type="hidden" name="drawChartBeginCol" value="1">
				<input type="hidden" name="drawChartEndCol" value="1">
			</div>
			
			<div class="unit">
				<label>
					查询语句：
				</label>
				<s:textarea name="querySql" cols="100" rows="5" cssStyle="height:125px;"/>
			</div>
			
			<div class="unit">
				<label>
					列语句：
				</label>
				<s:textarea name="columnSql" cols="100" rows="5" cssStyle="height:125px;"/>
			</div>
		</div>
   	</div>
    	
	<div id="step-2">
		<div class="pageFormContent">
			树形结构：<input type="checkbox" name="isTree" value="1" <c:if test="${object.isTree == 1 }">checked="checked"</c:if>
				title="如果选择树形结构，必须保证第一个字段为ID，第二个字段为父级ID"/>
			<div class="panelBar" style="margin-top:10px;">
				<ul class="toolBar">
					<li class="new">
						<a href='javascript:void(0)' onclick='addQueryColumnItem();' class="add">
							<span>添加字段</span>
						</a>
					</li>
				</ul>
			</div>
		
			<table id="t_queryColumn" class="list statTable" width="100%">
				<thead align="center">
					<tr>
						<th width="130">字段名称</th>
						<th width="130">显示类别</th>
						<th width="130" style="display:none;">数据操作</th>
						<th width="130" style="display:none;">是否画图</th>
						<th width="130">数据类型</th>	
						<th width="130">数据格式</th>
						<th width="130">默认值</th>
						<th width="130">链接地址</th>
						<th width="130">链接类型</th>
						<th width="130">顺序</th>
				        <th width="130">是否显示</th>
				        <th>操作</th>
					</tr>  
				</thead>
				<tbody align="center">
					 <s:iterator value="queryColumns" status="status" id="col"> 
						<tr>
							<td><s:textfield name="colName" cssClass="none" /> </td>   
							<td>
								<s:textfield name="showType" cssStyle="display:none"/>
								<s:select class="combox" list="#{'D':'数据', 'R':'行头' , 'C':'列头'}" 
									name="showType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)">
								</s:select>
							</td>
							<td style="display:none;">
								<s:textfield name="optType" cssStyle="display:none"/>
								<s:select class="combox" list="#{'0':'无操作', '1':'合计' , '2':'平均', '3':'平均&合计'}" 
									name="optType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)">
								</s:select>
							</td>   
							<td style="display:none;">
								<s:textfield name="drawChart" cssStyle="display:none"/> 
								<input type="radio" name="drawChart${status.index }" value="T" onclick="changePreSiblingInputValue(this)" 
									<c:if test="${col.drawChart=='T' }">checked</c:if>/>画图 
								<input type="radio" name="drawChart${status.index }" value="F" onclick="changePreSiblingInputValue(this)" 
									<c:if test="${col.drawChart=='F' }">checked</c:if>/>不画图 
							</td>   
							<td>
								<s:textfield name="colType" cssStyle="display:none;"/> 
								<s:select list="#{'S':'字符串', 'N':'数字', 'P':'百分比', 'C':'货币', 'D':'日期', 'U':'单位', 'E':'部门', 'O':'权力类型', 'T':'权力状态', 'L':'数据字典', 'H':'HTML代码' }" 
									name="colType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)">
								</s:select>
							</td>   
							<td><s:textfield name="colFormat" /> </td> 
							<td><s:textfield name="defaultValue" /> </td>     
							<td><s:textfield name="colLogic" /> </td> 
							<td>
								<s:textfield name="linkType" cssStyle="display:none;"/> 
								<s:select list="#{'_blank':'', 'navTab':'新TAB页', 'dialog':'弹出窗口'}" 
									name="linkType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)">
								</s:select>
							 </td>     
					        <td><s:textfield name="colOrder" /> </td>
					        <td>
					        	<s:textfield name="isShow" cssStyle="display:none;"/> 
					        	<input type="radio" name="isShow${status.index }" value="T" 
					        		onclick="changePreSiblingInputValue(this)" <c:if test="${col.isShow=='T' }">checked</c:if>/>显示
								<input type="radio" name="isShow${status.index }" value="F" 
									onclick="changePreSiblingInputValue(this)" <c:if test="${col.isShow=='F' }">checked</c:if>/>不显示
					        </td>
							<td>
								<a href='javascript:void(0)' onclick='delQueryColumnItem(this);'>
									<span title="删除" class="icon icon-trash"></span>
								</a>
							</td>
						</tr>  
					</s:iterator> 
				</tbody>        
			</table>		
		</div>
   	</div>  
    	                    
	<div id="step-3">
		<div class="pageFormContent">
		
			<!-- 查询参数模板 -->
			<select onchange="changePreSiblingInputValue(this)" style="display:none" id="sample" disabled="disabled">
				<c:forEach var="row" items="${cp:DATACATALOG()}">  
					<option value="${row.value }">${row.label }</option>
				</c:forEach>
			</select>
			
			
			<div class="panelBar">
				<ul class="toolBar">
					<li class="new">
						<a href='javascript:void(0)' onclick='addQueryConditonItem();' class="add">
							<span>添加参数</span>
						</a>
					</li>
				</ul>
			</div>
			
			<table id="t_queryConditon" class="list statTable" width="100%" >
				<thead align="center">
					<tr>
						<th width="120">参数编码</th>
						<th width="120">参数名称</th>
						<th width="120">比较类型</th>
						<th width="120">参数样式</th>
						<th width="120">参数数据</th>
						<th width="120">默认值</th>
				        <th width="50">顺序</th>
				        <th width="120">条件目标位置</th>	
				        <th width="120">添加过滤语句</th>
				        <th>操作</th>
					</tr>  
				</thead>
				<tbody align="center">
					 <s:iterator value="queryConditions" status="status" id="col">    
						<tr>
							<td><s:textfield name="condName" cssClass="none" /> </td>   
							<td><s:textfield name="condLabel" /> </td>   
							<td>
								<s:textfield name="compareType" cssStyle="display:none" /> 
								<s:select name="compareType" listKey="key" listValue="value" 
									list="#{'':'无','3':'年份', '4':'月份'}" 
									onchange="changePreSiblingInputValue(this);">
								</s:select>
							</td> 
							<td>
								<s:textfield name="condStyle" cssStyle="display:none" /> 
								<s:select name="condStyle" listKey="key" listValue="value" 
									list="#{'T':'普通文本框', 'Y':'年份下拉框', 'M':'月份下拉框', 'D':'日期弹出框', 'U':'单位下拉框', 'S':'SQL数据', 'P':'参数参考字典', 'C':'多选框',  'H':'隐藏', 'R':'只读' }" 
									onchange="changePreSiblingInputValue(this);change4ConditionStyle(this);">
								</s:select>
							</td>  
							<td>
								<s:textfield name="condType" cssStyle="display:none" /> 
								
								<select onchange="changePreSiblingInputValue(this)" name="condType">
									<c:forEach var="row" items="${cp:DATACATALOG()}">  
										<option value="${row.value }" <c:if test="${ col.condType==row.value}">selected="selected"</c:if> >${row.label }</option>
									</c:forEach>
								</select>
							</td>   
							<td><s:textfield name="condDefault" /> </td>  
							<td><s:textfield name="condOrder" /> </td>   
					        <td><s:textfield name="condPlace" /> </td>  
							<td><s:textfield name="condFilterSql" /> </td>
							<td>
								<a href='javascript:void(0)' onclick='delQueryConditonItem(this);'>
									<span title="删除" class="icon icon-trash"></span>
								</a>
							</td>
						</tr>  
					</s:iterator> 
				</tbody>        
			</table>
		</div>
   	</div>
    	
</div>
</s:form>
<!-- End SmartWizard Content -->  		
    		

<script type="text/javascript">
    
	var t_queryColumnRowCount; // 行数
    
	var t_queryConditonRowCount; // 行数
	
	function changePreSiblingInputValue(el) {
		$(el).parent().find('input:first').attr('value', el.value);
	}
	
	function change4ConditionStyle(el, flag){
		el = $(el);
		var value = el.val();
		var td = el.parent('td:first').next('td');
		
		// 如果选择参考字典，则参考字典下拉框激活，否则参考字典下拉框只读
		if ("P" == value || 'C'==value) {
			td.find('input').hide();
			td.find('select').show().prop('disabled', false);
		}
		else if ("S" == value) {
			if (!flag) {
				td.find('input').show().val('');
			}
			else {
				td.find('input').show();
			}
			
			td.find('select').attr('disabled', 'true').hide();
		}
		else {
			td.find('select').attr('disabled', 'true').hide();
		}
	}

    $(function()
    {
    	$('select[name="condType"]').each(function(index, el) {
    		el = $(el);
    		var condStyle = el.parent().prev('td').find('input:first');
    		
    		change4ConditionStyle(condStyle, true);
    		
    	});

		t_queryColumnRowCount = $("table#t_queryColumn tr").length - 1; // 除去标题行   
		
    	CentitUI.queryColumnColName = 
    	          ["colName","showType",
                     "optType","drawChart","colType","colFormat","defaultValue","colLogic","linkType","colOrder","isShow","guard"];

		t_queryConditonRowCount = $("table#t_queryConditon tr").length - 1; // 除去标题行   
    	CentitUI.queryConditonColName = 
    	          ["condName",
                     "condLabel","compareType","condStyle","condType","condDefault","condOrder","condPlace","condFilterSql","guard"];
    	
		
        $("#queryModelBtn").bind("click", function()
        {
            $("table#t_queryColumn tr").each(function(i)
            {
                $(this).attr("id", "tr_queryColumn" + i);
                $("#tr_queryColumn" + i + "  input[type='text']").each(function(j)
                {
                    $(this).attr("name", "newQueryColumns["+(i-1)+"]." + CentitUI.queryColumnColName[j]);
                });
            });
            $("table#t_queryConditon tr").each(function(i)
            {
                $(this).attr("id", "tr_queryConditon" + i);
                $("#tr_queryConditon" + i + "  input[type='text']").each(function(j)
                {
                   $(this).attr("name", "newQueryConditons["+(i-1)+"]." + CentitUI.queryConditonColName[j]);
                });
            });
            
        });
    });    
    
	     function addQueryColumnItem()
	     {
	     	var id = '#newcoltr'+t_queryColumnRowCount;
	          var htmlItem = '<tr id="newcoltr'+t_queryColumnRowCount+'">';
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].colName" /></td>'; 
	
	//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].optType" /></td>'; 
	htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].showType" value="D" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this)" class="combox">';
	htmlItem += '<option value="D">数据</option>';
	htmlItem += '<option value="R">行头</option>';
	htmlItem += '<option value="C">列头</option>';
	htmlItem += '</select>';
	htmlItem += '</td>';
	
	//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].optType" /></td>'; 
	htmlItem += '<td style="display:none;"><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].optType" value="0" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this)" class="combox">';
	htmlItem += '<option value="0">无操作</option>';
	htmlItem += '<option value="1">合计</option>';
	htmlItem += '<option value="2">平均</option>';
	htmlItem += '<option value="3">平均&合计</option>';
	htmlItem += '</select>';
	htmlItem += '</td>'; 
	
	//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].drawChart" /></td>';
	htmlItem += '<td style="display:none;"><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].drawChart" value="T" style="display:none"/>';
	htmlItem += '<input type="radio" name="drawChart'+t_queryColumnRowCount+'" value="T" onclick="changePreSiblingInputValue(this)" checked/>画图 ';
	htmlItem += '<input type="radio" name="drawChart'+t_queryColumnRowCount+'" value="F" onclick="changePreSiblingInputValue(this)"/>不画图 ';
	htmlItem += '</td>';
	
	//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colType" /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].colType" value="S" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this)" class="combox">';
	htmlItem += '<option value="S">字符串</option>';
	htmlItem += '<option value="N">数字</option>';
	htmlItem += '<option value="P">百分比</option>';
	htmlItem += '<option value="C">货币</option>';
	htmlItem += '<option value="D">日期</option>';
	htmlItem += '<option value="U">单位</option>';
	htmlItem += '<option value="E">部门</option>';
	htmlItem += '<option value="O">权力类型</option>';
	htmlItem += '<option value="T">权力状态</option>';
	htmlItem += '<option value="L">数据字典</option>';
	htmlItem += '<option value="H">HTML代码</option>';
	htmlItem += '</select>';
	htmlItem += '</td>'; 
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].colFormat" /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].defaultValue" /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].colLogic" /></td>'; 
	
// 	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+']." /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].linkType" value="S" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this)" class="combox">';
	htmlItem += '<option value="_blank"></option>';
	htmlItem += '<option value="navTab">新TAB页</option>';
	htmlItem += '<option value="dialog">弹出窗口</option>';
	htmlItem += '</select>';
	htmlItem += '</td>'; 
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].colOrder" value="'+(t_queryColumnRowCount+1)+'" /></td>'; 
	
	//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].isShow" /></td>';
	htmlItem += '<td><input type="text" class="textInput" name="newQueryColumns['+t_queryColumnRowCount+'].isShow" value="T" style="display:none;" />';
	htmlItem += '<input type="radio" name="isShow'+t_queryColumnRowCount+'" value="T" onclick="changePreSiblingInputValue(this)" checked/>显示&nbsp';
	htmlItem += '<input type="radio" name="isShow'+t_queryColumnRowCount+'" value="F" onclick="changePreSiblingInputValue(this)"/>不显示';
	htmlItem += '</td>';
	
	      t_queryColumnRowCount++;
	      htmlItem += "<td> <a href='javascript:void(0)' onclick='delQueryColumnItem(this);'><span class='icon icon-trash' title='删除'></span></a></td></tr>";
	      $("table#t_queryColumn").append(htmlItem);
	
	   scroll2Me($(id));
	  	}
	     
	     function scroll2Me(el) {
	     	el = $(el);
	     	var height = el.height();
	     	var clientHeight = document.body.clientHeight;
	     	
	     	window.scroll(0, el.position().top-clientHeight+height);
	     	el.find('td:first').find('input:first').focus();
	     }
	     
	     function delQueryColumnItem(varBtn)
	     {
	         $(varBtn).parent().parent().remove();
	         t_queryColumnRowCount--;
			    $('table#t_queryColumn.tableRegion tbody tr:odd').attr('class','odd');
			    $('table#t_queryColumn.tableRegion tbody tr:even').attr('class','even');
	     }
	     function addQueryConditonItem()
	     {
	     	var id = '#newcontr'+t_queryConditonRowCount;
	          var htmlItem = '<tr id="newcontr'+t_queryConditonRowCount+'">';
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condName" /></td>'; 
	
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condLabel" /></td>';
	
	//htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condStyle" /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].compareType" value="T" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this);" name="compareType">';
	htmlItem += '<option value="">无</option>';
	htmlItem += '<option value="3">年份</option>';
	htmlItem += '<option value="4">月份</option>';
	htmlItem += '</select>';
	htmlItem += '</td>'; 
	
	//htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condStyle" /></td>'; 
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condStyle" value="T" style="display:none;"/>';
	htmlItem += '<select onchange="changePreSiblingInputValue(this);change4ConditionStyle(this);" name="condStyle">';
	htmlItem += '<option value="T">普通文本框</option>';
	htmlItem += '<option value="Y">年份下拉框</option>';
	htmlItem += '<option value="M">月份下拉框</option>';
	htmlItem += '<option value="D">日期弹出框</option>';
	htmlItem += '<option value="U">单位下拉框</option>';
	htmlItem += '<option value="S">SQL数据</option>';
	htmlItem += '<option value="P">参数参考字典</option>';
	htmlItem += '<option value="C">多选框</option>';
	htmlItem += '<option value="H">隐藏</option>';
	htmlItem += '<option value="R">只读</option>';
	htmlItem += '</select>';
	htmlItem += '</td>'; 
	
	//htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condType" /></td>'; 
	htmlItem += '<td id="td'+t_queryConditonRowCount+'"><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condType" style="display:none"/></td>'; 
	
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condDefault" /></td>';
	
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condOrder" value="'+(t_queryConditonRowCount+1)+'"/></td>';
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condPlace" /></td>';
	htmlItem += '<td><input type="text" class="textInput" name="newQueryConditons['+t_queryConditonRowCount+'].condFilterSql" /></td>';
	      
	t_queryConditonRowCount++;
	      htmlItem += "<td> <a href='javascript:void(0)' onclick='delQueryConditonItem(this);'><span class='icon icon-trash' title='删除'></span></a></td></tr>";
	      $("table#t_queryConditon").append(htmlItem);
	      
	      $('#td'+(t_queryConditonRowCount-1)).append($('#sample').clone().css({display:'block'}))
	      	.find('input:first').val($('#sample').find('option:first').attr('value'));
	
		scroll2Me($(id));
	  	}
	     
	     function delQueryConditonItem(varBtn)
	     {
	         $(varBtn).parent().parent().remove();
	         t_queryConditonRowCount--;
	     }
	
	 </script>	