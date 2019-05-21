<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head>
<title><s:text name="queryModel.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="queryModel.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="queryModel"  method="post" namespace="/stat" id="queryModelForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 	<thead>
 		<tr>
 			<td width="10%"></td>
 			<td width="20%"></td>
 			<td width="10%"></td>
 			<td width="60%"></td>
 		</tr>
 	</thead>
				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.modelName" />
					</td>
					<td align="left">
							<s:textfield name="modelName" size="40" />
					</td>
					
					<td class="TDTITLE" rowspan="10">
						<s:text name="queryModel.querySql" />
					</td>
					<td align="left" rowspan="10">
						<s:textarea name="querySql" cols="100" rows="10" cssStyle="width:650px; height:320px;"/>
					</td>
				</tr>
				
				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.queryDesc" />
					</td>
					<td align="left">
						<s:textfield name="queryDesc" size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.formNameFormat" />
					</td>
					<td align="left">
						<s:textfield name="formNameFormat" size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.resultName" />
					</td>
					<td align="left">
						<s:textfield name="resultName"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.rowDrawChart" />
					</td>
					<td align="left">
						<s:textfield name="rowDrawChart"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.drawChartBeginCol" />
					</td>
					<td align="left">
						<s:textfield name="drawChartBeginCol"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.drawChartEndCol" />
					</td>
					<td align="left">
						<s:textfield name="drawChartEndCol"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.additionRow" />
					</td>
					<td align="left">
						<s:textfield name="additionRow"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="queryModel.rowLogic" />
					</td>
					<td align="left">
						<s:textfield name="rowLogic"  size="40"/>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="逻辑地址" />
					</td>
					<td align="left">
						<s:textfield name="logicUrl"  size="40"/>
					</td>
				</tr>

</table>


<p/>
<div class="eXtremeTable" >
	<table id="t_queryColumn"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
	
  
				<td class="tableHeader">
					<s:text name="queryColumn.colName" />
				</td>	


				<td class="tableHeader">
					<s:text name="queryColumn.optType" />
				</td>	

				<td class="tableHeader">
					<s:text name="queryColumn.drawChart" />
				</td>	

				<td class="tableHeader">
					<s:text name="queryColumn.colType" />
				</td>	

				<td class="tableHeader">
					<s:text name="queryColumn.colLogic" />
				</td>
				<td class="tableHeader">顺序</td>	
		        <td class="tableHeader">是否显示</td>	
				<td class="tableHeader">
					<a href='javascript:void(0)' onclick='addQueryColumnItem(this);'><s:text name="opt.btn.new" /></a>
				</td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		 <s:iterator value="queryColumns" status="status" id="col">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><s:textfield name="colName" /> </td>   

  
				<td>
					<s:textfield name="optType" cssStyle="display:none"/>
					<s:select list="#{'0':'无操作', '1':'合计' , '2':'平均', '3':'平均&合计'}" name="optType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)"></s:select>
				</td>   
  
				<td>
					<s:textfield name="drawChart" cssStyle="display:none"/> 
					<input type="radio" name="drawChart${status.index }" value="T" onclick="changePreSiblingInputValue(this)" <c:if test="${col.drawChart=='T' }">checked</c:if>/>画图 
					<input type="radio" name="drawChart${status.index }" value="F" onclick="changePreSiblingInputValue(this)" <c:if test="${col.drawChart=='F' }">checked</c:if>/>不画图 
				</td>   
  
				<td>
					<s:textfield name="colType" cssStyle="display:none;"/> 
					<s:select list="#{'S':'字符串', 'N':'数字' }" name="colType" listKey="key" listValue="value" onchange="changePreSiblingInputValue(this)"></s:select>
				</td>   
  
				<td><s:textfield name="colLogic" /> </td>   
		        <td><s:textfield name="colorder" /> </td>
		        <td>
		        	<s:textfield name="isShow" cssStyle="display:none;"/> 
		        	<input type="radio" name="isShow${status.index }" value="T" onclick="changePreSiblingInputValue(this)" <c:if test="${col.isShow=='T' }">checked</c:if>/>显示
					<input type="radio" name="isShow${status.index }" value="F" onclick="changePreSiblingInputValue(this)" <c:if test="${col.isShow=='F' }">checked</c:if>/>不显示
		        </td>
				<td>
					<a href='javascript:void(0)' onclick='delQueryColumnItem(this);'><s:text name='opt.btn.delete' /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</s:iterator> 
		</tbody>        
	</table>
</div>

<p/>
<div class="eXtremeTable" >
	<select onchange="changePreSiblingInputValue(this)" style="width:180px; display:none" id="sample" disabled="disabled">
		<c:forEach var="row" items="${cp:DATACATALOG()}">  
			<option value="${row.value }">${row.label }</option>
		</c:forEach>
	</select>

	<table id="t_queryConditon"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
	
  
				<td class="tableHeader">
					<s:text name="queryConditon.condName" />
				</td>	


				<td class="tableHeader">
					<s:text name="queryConditon.condLabel" />
				</td>	

				<td class="tableHeader">
					<s:text name="queryConditon.condType" />
				</td>	

				<td class="tableHeader">
					<s:text name="queryConditon.condStyle" />
				</td>	
				<td class="tableHeader">
					<s:text name="queryConditon.condValue" />
				</td>
		        <td class="tableHeader">顺序</td>	
		        <td class="tableHeader">条件目标位置</td>	
		        <td class="tableHeader">添加过滤语句</td>
				<td class="tableHeader">
					<a href='javascript:void(0)' onclick='addQueryConditonItem(this);'><s:text name="opt.btn.new" /></a>
				</td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		 <s:iterator value="queryConditons" status="status" id="col">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><s:textfield name="condName" /> </td>   

  
				<td><s:textfield name="condLabel" /> </td>   
  
				<td>
					<s:textfield name="condType" cssStyle="display:none" /> 
					
					<select onchange="changePreSiblingInputValue(this)" style="width:180px;" name="condType">
						<c:forEach var="row" items="${cp:DATACATALOG()}">  
							<option value="${row.value }" <c:if test="${ col.condType==row.value}">selected="selected"</c:if> >${row.label }</option>
						</c:forEach>
					</select>
				</td>   
  
				<td>
					<s:textfield name="condStyle" cssStyle="display:none" /> 
					<s:select name="condStyle" listKey="key" listValue="value" 
						list="#{'T':'普通文本框', 'Y':'年份下拉框', 'M':'月份下拉框', 'D':'日期弹出框', 'U':'单位下拉框', 'P':'参数参考字典', 'C':'多选框',  'H':'隐藏', 'R':'只读' }" 
						onchange="changePreSiblingInputValue(this);change4ConditionStyle(this);">
					</s:select>
				</td>  
				
				<td><s:textfield name="condValue" /> </td>  
				<td><s:textfield name="conorder" /> </td>   
		        <td><s:textfield name="condPlace" /> </td>  
				<td><s:textfield name="condFilterSql" /> </td>
				<td>
					<a href='javascript:void(0)' onclick='delQueryConditonItem(this);'><s:text name='opt.btn.delete' /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</s:iterator> 
		</tbody>        
	</table>
</div>

</s:form>

	<script type="text/javascript">
	    
		var t_queryColumnRowCount; // 行数
	    
		var t_queryConditonRowCount; // 行数
		
		function changePreSiblingInputValue(el) {
			$(el).parent().find('input:first').attr('value', el.value);
		}
		
		function change4ConditionStyle(el){
			el = $(el);
			var value = el.val();
			
			// 如果选择参考字典，则参考字典下拉框激活，否则参考字典下拉框只读
			if ("P" == value || 'C'==value) {
				el.parent('td:first').prevAll('td:first').find('select').prop('disabled', false);
			}else {
				el.parent('td:first').prevAll('td:first').find('select').attr('disabled', 'true');
			}
		}

	    $(function()
	    {
	    	$('.TDTITLE').css({
	    		width:'100px'
	    	});
	    	
	    	$('select[name="condType"]').each(function(index, el) {
	    		el = $(el);
	    		var condStyle = el.parent().next('td').find('input:first');
	    		
	    		// 如果参数样式不是参数字典，则参数字典只读
	    		if ('P' != condStyle.val() && 'C' != condStyle.val()) {
	    			el.prop('disabled', true);
	    		};
	    		
	    		condStyle.next().css({
	    			width:'180px'
	    		});
	    	});
	
			t_queryColumnRowCount = $("table#t_queryColumn tr").length - 1; // 除去标题行   
	    	var queryColumnColName = 
	    	          ["colName",
                      "optType","drawChart","colType","colLogic","colorder","isShow","guard"];
	
			t_queryConditonRowCount = $("table#t_queryConditon tr").length - 1; // 除去标题行   
	    	var queryConditonColName = 
	    	          ["condName",
                      "condLabel","condType","condStyle","condValue","conorder","condPlace","condFilterSql","guard"];
	    	
			
	        $("input[name='method:save']").bind("click", function()
	        {
	            $("table#t_queryColumn tr").each(function(i)
	            {
	                $(this).attr("id", "tr_queryColumn" + i);
	                $("#tr_queryColumn" + i + "  input[type='text']").each(function(j)
	                {
	                    $(this).attr("name", "newQueryColumns["+(i-1)+"]." + queryColumnColName[j]);
	                });
	            });
	            $("table#t_queryConditon tr").each(function(i)
	            {
	                $(this).attr("id", "tr_queryConditon" + i);
	                $("#tr_queryConditon" + i + "  input[type='text']").each(function(j)
	                {
	                   $(this).attr("name", "newQueryConditons["+(i-1)+"]." + queryConditonColName[j]);
	                });
	            });
	            
	        });
	    });    
	    
        function addQueryColumnItem()
        {
        	var id = '#newcoltr'+t_queryColumnRowCount;
             var htmlItem = '<tr id="newcoltr'+t_queryColumnRowCount+'">';
  
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colName" /></td>'; 


			//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].optType" /></td>'; 
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].optType" value="0" style="display:none;"/>';
			htmlItem += '<select onchange="changePreSiblingInputValue(this)">';
			htmlItem += '<option value="0">无操作</option>';
			htmlItem += '<option value="1">合计</option>';
			htmlItem += '<option value="2">平均</option>';
			htmlItem += '<option value="3">平均&合计</option>';
			htmlItem += '</select>';
			htmlItem += '</td>'; 

			//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].drawChart" /></td>';
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].drawChart" value="T" style="display:none"/>';
			htmlItem += '<input type="radio" name="drawChart'+t_queryColumnRowCount+'" value="T" onclick="changePreSiblingInputValue(this)" checked/>画图 ';
			htmlItem += '<input type="radio" name="drawChart'+t_queryColumnRowCount+'" value="F" onclick="changePreSiblingInputValue(this)"/>不画图 ';
			htmlItem += '</td>';
			
			//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colType" /></td>'; 
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colType" value="S" style="display:none;"/>';
			htmlItem += '<select onchange="changePreSiblingInputValue(this)" >';
			htmlItem += '<option value="S">字符串</option>';
			htmlItem += '<option value="N">数字</option>';
			htmlItem += '</select>';
			htmlItem += '</td>'; 

			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colLogic" /></td>'; 
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].colorder" value="'+(t_queryColumnRowCount+1)+'" /></td>'; 
			
			//htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].isShow" /></td>';
			htmlItem += '<td><input type="text" name="newQueryColumns['+t_queryColumnRowCount+'].isShow" value="T" style="display:none;" />';
			htmlItem += '<input type="radio" name="isShow'+t_queryColumnRowCount+'" value="T" onclick="changePreSiblingInputValue(this)" checked/>显示&nbsp';
			htmlItem += '<input type="radio" name="isShow'+t_queryColumnRowCount+'" value="F" onclick="changePreSiblingInputValue(this)"/>不显示';
			htmlItem += '</td>';
			
            t_queryColumnRowCount++;
            htmlItem += "<td> <a href='javascript:void(0)' onclick='delQueryColumnItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
            $("table#t_queryColumn").append(htmlItem);

   		    $('table#t_queryColumn.tableRegion tbody tr:odd').attr('class','odd')
   		    .hover(function(){
       		    	$(this).addClass("highlight");
       		    },function(){
       		    	$(this).removeClass("highlight");
       		});
   		    $('table#t_queryColumn.tableRegion tbody tr:even').attr('class','even')
   		    .hover(function(){
	   		    	$(this).addClass("highlight");
	   		    },function(){
	   		    	$(this).removeClass("highlight");
   		    });
   		   
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
  
			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condName" /></td>'; 


			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condLabel" /></td>'; 

			//htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condType" /></td>'; 
			htmlItem += '<td id="td'+t_queryConditonRowCount+'"><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condType" style="display:none"/></td>'; 

			//htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condStyle" /></td>'; 
			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condStyle" value="T" style="display:none;"/>';
			htmlItem += '<select onchange="changePreSiblingInputValue(this);change4ConditionStyle(this);" style="width:180px;" name="condStyle">';
			htmlItem += '<option value="T">普通文本框</option>';
			htmlItem += '<option value="Y">年份下拉框</option>';
			htmlItem += '<option value="M">月份下拉框</option>';
			htmlItem += '<option value="D">日期弹出框</option>';
			htmlItem += '<option value="U">单位下拉框</option>';
			htmlItem += '<option value="P">参数参考字典</option>';
			htmlItem += '<option value="C">多选框</option>';
			htmlItem += '<option value="H">隐藏</option>';
			htmlItem += '<option value="R">只读</option>';
			htmlItem += '</select>';
			htmlItem += '</td>'; 
			
			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condValue" /></td>';

			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].conorder" value="'+(t_queryConditonRowCount+1)+'"/></td>';
			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condPlace" /></td>';
			htmlItem += '<td><input type="text" name="newQueryConditons['+t_queryConditonRowCount+'].condFilterSql" /></td>';
            t_queryConditonRowCount++;
            htmlItem += "<td> <a href='javascript:void(0)' onclick='delQueryConditonItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
            $("table#t_queryConditon").append(htmlItem);
            
            $('#td'+(t_queryConditonRowCount-1)).append($('#sample').clone().css({display:'block'}))
            	.find('input:first').val($('#sample').find('option:first').attr('value'));

   		    $('table#t_queryConditon.tableRegion tbody tr:odd').attr('class','odd')
   		    .hover(function(){
       		    	$(this).addClass("highlight");
       		    },function(){
       		    	$(this).removeClass("highlight");
       		});
   		    $('table#t_queryConditon.tableRegion tbody tr:even').attr('class','even')
   		    .hover(function(){
	   		    	$(this).addClass("highlight");
	   		    },function(){
	   		    	$(this).removeClass("highlight");
   		    });
   		    
   		 	scroll2Me($(id));
     	}
        
        function delQueryConditonItem(varBtn)
        {
            $(varBtn).parent().parent().remove();
            t_queryConditonRowCount--;
   		    $('table#t_queryConditon.tableRegion tbody tr:odd').attr('class','odd');
   		    $('table#t_queryConditon.tableRegion tbody tr:even').attr('class','even');
        }

    </script>	


</body>
</html>
