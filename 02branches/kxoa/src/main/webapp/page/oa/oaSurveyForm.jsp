<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<html>
<head>
<title><s:text name="oaSurvey.edit.title" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaSurvey.edit.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaSurvey" method="post" onsubmit="return checkForm();"
			namespace="/oa" id="oaSurveyForm">
			<c:if test="${'T' ne s_rEdit }">

				<s:hidden id="djid" name="djid" value="%{djid}"></s:hidden>
			</c:if>
			<c:if test="${'T' eq s_rEdit }">
				<s:hidden id="s_djid" name="s_djid" value="%{djid}"></s:hidden>

			</c:if>

			<s:hidden id="s_type" name="s_type" value="%{s_type}"></s:hidden>
			<s:hidden id="s_rEdit" name="s_rEdit" value="%{s_rEdit}"></s:hidden>

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<%-- 		<tr>
					<td class="addTd">
						<s:text name="oaSurvey.djid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djid" size="40" />
	
					</td>
				</tr>
 --%>
				<tr>
					<td class="addTd"><s:text name="oaSurvey.reType" /></td>
					<td align="left" colspan="4"><select style="height: 20px; width: 200px;"
						id="reType" name="reType">
							<c:forEach var="row" items="${oaSurveyTypeList}">
								<option value="${row.no}"
									<c:if test="${row.no==reType}"> selected="selected"</c:if>>
									<c:out value="${row.reType}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaSurvey.begtime" /></td>
					<td align="left"><input type="text" class="Wdate"
						style="height: 20px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${begtime}"  pattern="yyyy-MM-dd  HH:mm:ss"/>'
						id="begtime" name="begtime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" /></td>

					<td class="addTd"><s:text name="oaSurvey.endtime" /></td>
					<td align="left"><input type="text" class="Wdate"
						style="height: 20px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${endtime}"  pattern="yyyy-MM-dd  HH:mm:ss"/>'
						id="endtime" name="endtime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaSurvey.title" /></td>
					<td align="left" colspan="3"><s:textarea name="title"
							cols="40" rows="2" style="width: 100%;height: 27px;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaSurvey.sendusers" /></td>
					<td align="left" colspan="3"><input type="text" id="txt_name"
						name="sendusers" style="width: 100%; height: 27px;"
						value="${sendusers}" readonly="readonly" /> <input
						id="txt_usercode" type="hidden" name="userValue"
						value="${userValue}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaSurvey.remark" /></td>
					<td align="left" colspan="3"><s:textarea name="remark"
							cols="40" rows="2" style="width: 100%;" /></td>
				</tr>





				<tr>
					<td class="addTd"><s:text name="oaSurvey.createRemark" /></td>
					<td align="left" colspan="3"><s:textarea name="createRemark"
							cols="40" rows="2" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td class="addTd">是否<s:text name="oaSurvey.isautoend" /></td>
					<td align="left"><input type="checkbox" id="isautoend"
						name="isautoend" value="Y"
						<c:if test="${isautoend=='Y'}"> checked="checked" </c:if>>
						<s:text name="oaSurvey.isautoend" /></td>
					<td class="addTd"><s:text name="oaSurvey.createDepno" /></td>
					<td align="left"><select id="createDepno" style="height: 20px; width: 200px;""
						name="createDepno" >
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==createDepno}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaSurvey.isviewresult" /></td>
					<td align="left"><input type="checkbox" id=isviewresult
						name="isviewresult" value="Y"
						<c:if test="${object.isviewresult eq'Y'}"> checked="checked" </c:if>>可以查看投票结果
					</td>

					<td class="addTd">是否<s:text name="oaSurvey.isbookn" /></td>
					<td align="left">
						<%-- 					<s:checkbox name="isbookn" fieldValue="Y" label="记名投票"></s:checkbox> --%>

						<input type="checkbox" id=isbookn name="isbookn" value="Y"
						<c:if test="${object.isbookn eq 'Y'}"> checked="checked" </c:if>>记名投票
					</td>
				</tr>

				<c:if test="${not empty djid}">
					<tr>
						<td class="addTd"><s:text name="oaSurvey.creater" /></td>
						<td align="left">${cp:MAPVALUE("usercode",creater) }</td>

						<td class="addTd"><s:text name="oaSurvey.createtime" /></td>
						<td align="left"><fmt:formatDate value='${createtime}'
								pattern='yyyy-MM-dd' /> <input type="hidden" id="createtime"
							name="createtime"
							value="<fmt:formatDate value='${createtime}' pattern='yyyy-MM-dd HH:mm:ss' />">
						</td>
					</tr>
				</c:if>
			</table>
			<div class="formButton">
				<input type="button" value="返回" class="btn"
					onclick="window.history.go(-1);" id="backBtn" />
				<c:if test="${'T' ne s_rEdit }">
					<s:submit name="save" method="save" cssClass="btn"
						key="opt.btn.save" id="save1" />
				</c:if>
				<c:if test="${'T' eq s_rEdit }">
					<s:submit name="save" method="republish" cssClass="btn" key="重新发布"
						id="save1" />
				</c:if>
			</div>

			<%-- <div class="eXtremeTable" >
	<table id="t_oaOnlineItem"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.no" />
				</td>	

	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.chosetype" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.title" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.itemnames" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.chosenum" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.limitnum" />
				</td>	

				<td class="tableHeader">
					<s:text name="oaOnlineItem.thesign" />
				</td>	
		
				<td class="tableHeader">
					<a href='javascript:void(0)' onclick='addOaOnlineItemItem(this);'><s:text name="opt.btn.new" /></a>
				</td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		 <s:iterator value="oaOnlineItems" status="status" >    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><s:textfield name="no" /> </td>   

  
				<td><s:textfield name="chosetype" /> </td>   
  
				<td><s:textfield name="title" /> </td>   
  
				<td><s:textfield name="itemnames" /> </td>   
  
				<td><s:textfield name="chosenum" /> </td>   
  
				<td><s:textfield name="limitnum" /> </td>   
  
				<td><s:textfield name="thesign" /> </td>   
		
				<td>
					<a href='javascript:void(0)' onclick='delOaOnlineItemItem(this);'><s:text name='opt.btn.delete' /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</s:iterator> 
		</tbody>        
	</table>
</div>

<p/>
<div class="eXtremeTable" >
	<table id="t_oaSurveydetail"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.itemid" />
				</td>	
  
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
    
				<td><s:textfield name="itemid" /> </td>   
    
				<td><s:textfield name="creater" /> </td>   

  
				<td><s:textfield name="no" /> </td>   
  
				<td><s:textfield name="title" /> </td>   
  
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
 --%>


			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						<ul></ul>
					</div>
					<div id="t-b-arrow">

						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function checkForm() {
		if ('checked' == $('#isautoend').attr('checked')) {
			if ("" == $('#endtime').val()) {
				alert("自动结束时，结束时间不为空");
				$('#endtime').focus();
				return false;
			}
		}
	}
	$("#txt_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#txt_name"),
							$("#txt_usercode"));
				}
				;
			});

	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}

	/* var t_oaOnlineItemRowCount; // 行数
	
	var t_oaSurveydetailRowCount; // 行数

	$(function()
	{
	
		t_oaOnlineItemRowCount = $("table#t_oaOnlineItem tr").length - 1; // 除去标题行   
		var oaOnlineItemColName = 
		          ["no",
	              "chosetype","title","itemnames","chosenum","limitnum","thesign","guard"];
	
		t_oaSurveydetailRowCount = $("table#t_oaSurveydetail tr").length - 1; // 除去标题行   
		var oaSurveydetailColName = 
		          ["itemid","creater",
	              "no","title","createtime","guard"];
		
		
	    $("input[name='method:save']").bind("click", function()
	    {
	        $("table#t_oaOnlineItem tr").each(function(i)
	        {
	            $(this).attr("id", "tr_oaOnlineItem" + i);
	            $("#tr_oaOnlineItem" + i + "  input[type='text']").each(function(j)
	            {
	                $(this).attr("name", "newOaOnlineItems["+(i-1)+"]." + oaOnlineItemColName[j]);
	            });
	        });
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
	
	function addOaOnlineItemItem()
	{
	     var htmlItem = '<tr>';
	
		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].no" /></td>'; 


		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].chosetype" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].title" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].itemnames" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].chosenum" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].limitnum" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaOnlineItems['+t_oaOnlineItemRowCount+'].thesign" /></td>'; 
	    
	    t_oaOnlineItemRowCount++;
	    htmlItem += "<td> <a href='javascript:void(0)' onclick='delOaOnlineItemItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
	    $("table#t_oaOnlineItem").append(htmlItem);

	    $('table#t_oaOnlineItem.tableRegion tr:odd').attr('class','odd')
	    .hover(function(){
		    	$(this).addClass("highlight");
		    },function(){
		    	$(this).removeClass("highlight");
		});
	    $('table#t_oaOnlineItem.tableRegion tr:even').attr('class','even')
	    .hover(function(){
		    	$(this).addClass("highlight");
		    },function(){
		    	$(this).removeClass("highlight");
	    });
	}
	
	function delOaOnlineItemItem(varBtn)
	{
	    $(varBtn).parent().parent().remove();
	    t_oaOnlineItemRowCount--;
	    $('table#t_oaOnlineItem.tableRegion tr:odd').attr('class','odd');
	    $('table#t_oaOnlineItem.tableRegion tr:even').attr('class','even');
	}
	function addOaSurveydetailItem()
	{
	     var htmlItem = '<tr>';
	
		htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].itemid" /></td>'; 
	
		htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].creater" /></td>'; 


		htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].no" /></td>'; 

		htmlItem += '<td><input type="text" name="newOaSurveydetails['+t_oaSurveydetailRowCount+'].title" /></td>'; 

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
	 */
</script>


</html>
