<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItems.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaOnlineItems.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaOnlineItems"  method="post" namespace="/oa" id="oaOnlineItemsForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 <input type="hidden" id="s_djid" value="${s_djid }" name="s_djid">
				<tr>
					<td class="addTd">
						<s:text name="oaOnlineItems.itemid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="itemid" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="addTd">
						<s:text name="oaOnlineItems.no" />
					</td>
					<td align="left">
	
  
						<s:textfield name="no"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaOnlineItems.title" />
					</td>
					<td align="left">
  
						<s:textarea name="title" cols="40" rows="2"/>
	
	
					</td>
				</tr>

</table>


<p/>
<div class="eXtremeTable" >
	<table id="t_oaSurveydetail"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
	
  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.creater" />
				</td>	


				<td class="tableHeader">
					<s:text name="oaSurveydetail.no" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaSurveydetail.title" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaSurveydetail.djid" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaSurveydetail.createtime" />
				</td>	
		
				<td class="tableHeader">
					<a href='javascript:void(0)' onclick='addOaSurveydetailItem(this);'><s:text name="opt.btn.new" /></a>
				</td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		 <s:iterator value="oaSurveydetails" status="status" >    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><s:textfield name="creater" /> </td>   

  
				<td><s:textfield name="no" /> </td>   
  
				<td><s:textfield name="title" /> </td>   
  
				<td><s:textfield name="djid" /> </td>   
  
				<td><s:textfield name="createtime" /> </td>   
		
				<td>
					<a href='javascript:void(0)' onclick='delOaSurveydetailItem(this);'><s:text name='opt.btn.delete' /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</s:iterator> 
		</tbody>        
	</table>
</div>

</s:form>

	<script type="text/javascript">
	    
		var t_oaSurveydetailRowCount; // 行数

	    $(function()
	    {
	
			t_oaSurveydetailRowCount = $("table#t_oaSurveydetail tr").length - 1; // 除去标题行   
	    	var oaSurveydetailColName = 
	    	          ["creater",
                      "no","title","djid","createtime","guard"];
	    	
			
	        $("input[name='method:save']").bind("click", function()
	        {
	            $("table#t_oaSurveydetail tr").each(function(i)
	            {
	                $(this).attr("id", "tr_oaSurveydetail" + i);
	                $("#tr_oaSurveydetail" + i + "  input[type='text']").each(function(j)
	                {
	                    $(this).attr("name", "newOaSurveydetails["+(i-1)+"]." + oaSurveydetailColName[j]);
	                });
	            });
	            
	        });
	    });    
	    
        function addOaSurveydetailItem()
        {
             var htmlItem = '<tr>';
  
			htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].creater" /></td>'; 


			htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].no" /></td>'; 

			htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].title" /></td>'; 

			htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].djid" /></td>'; 

			htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].createtime" /></td>'; 
            
            t_oaSurveydetailRowCount++;
            htmlItem += "<td> <a href='javascript:void(0)' onclick='delOaSurveydetailItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
            $("table#t_oaSurveydetail").append(htmlItem);

   		    $('table#t_oaSurveydetail.tableRegion tr:odd').attr('class','odd')
   		    .hover(function(){
       		    	$(this).addClass("highlight");
       		    },function(){
       		    	$(this).removeClass("highlight");
       		});
   		    $('table#t_oaSurveydetail.tableRegion tr:even').attr('class','even')
   		    .hover(function(){
	   		    	$(this).addClass("highlight");
	   		    },function(){
	   		    	$(this).removeClass("highlight");
   		    });
     	}
        
        function delOaSurveydetailItem(varBtn)
        {
            $(varBtn).parent().parent().remove();
            t_oaSurveydetailRowCount--;
   		    $('table#t_oaSurveydetail.tableRegion tr:odd').attr('class','odd');
   		    $('table#t_oaSurveydetail.tableRegion tr:even').attr('class','even');
        }

    </script>	


</body>
</html>
