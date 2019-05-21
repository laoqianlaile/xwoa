<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<s:include value="/page/common/formValidator.jsp"></s:include>
<title>工作流程定义页面</title>
<script type="text/javascript">
jQuery(document).ready(function(){

	$.formValidator.initConfig({formid:"form1",
	onerror:function(msg,obj,errorlist){
		alert(msg);
		
	},
	onsuccess:checkselect
	});
	$("#flowName").formValidator().inputValidator({min:1,onerror:"流程名称不能为空"});	
	/* $("#timeLimit").formValidator().inputValidator({min:1,onerror:"完成期限不能为空"}); */
	/* $("#atPublishDate").formValidator().inputValidator({min:1,onerror:"自动发布日期不能为空"}); */
	
	
}); 

function checkselect(){
	var optidvalue= $("#optId").attr("value");
	if(optidvalue=="0"){
		alert("请选择业务模块");
		return false;
	}
}
function changeTL(){

	if($("#timeLimit").attr("value")==""){
		$("#expireTr").hide();
	}else{
		$("#expireTr").show();
	}
	
}	
	
/* 	var expireOptvalue= $("#expireOpt").attr("value");
	if(expireOptvalue=="0"){
		alert("请选择逾期处理方法");
		return false;
	}
	else{
		return true;
	} */
		


</script>

</head>

<body class="sub-flow">
<%@ include file="/page/common/messages.jsp"%>
<s:form action="sampleFlowDefine" namespace="/sampleflow" id="form1" theme="simple">
<fieldset style="padding:10px;">
	<legend  style="width:auto;margin-bottom:10px;">流程基本信息</legend>

	<s:submit method="saveFlowField" cssClass="btn" value="保存" /><!--
	<input type="button" name="test" cssClass="btn" value="测试" />
	--><s:hidden name="version" value="%{object.version}" />
	<!-- 
	<input type="button" value="从当前版本编辑流程图" class="btn" onclick="location.href='sampleFlowDefine!getworkflowxml.do?flowCode=${object.flowCode}&version=${object.version}'" >
	<input type="button" value="从草稿版本编辑流程图" class="btn" onclick="location.href='sampleFlowDefine!getworkflowxml.do?flowCode=${object.flowCode}&version=0'" >
	 -->
	<input type="button"  value="返回" Class="btn"  onclick="window.history.back()"/>
		
<table class="viewTable" cellpadding="0" cellspacing="0">		
  <tr>
    <td class="addTd">流程代码*</td>
    <td><label>
    <c:if test="${not empty object.flowCode}"><s:textfield name="object.flowCode" value="%{object.flowCode}" readonly="true" style="width:200px;height:25px;" /></c:if>
    <c:if test="${empty object.flowCode}"> <s:textfield id="flowCode" name="object.flowCode" style="width:200px;height:25px;" /></c:if>
    </label></td>
   </tr>
    <tr> 
    <td class="addTd">流程名称*</td>
    <td><label>
    <c:if test="${not empty object.flowName}"><s:textfield name="flowName" value="%{object.flowName}"  style="width:200px;height:25px;" /></c:if>
      <c:if test="${empty object.flowName}"> <s:textfield id="flowName"  name="flowName"  style="width:200px;height:25px;" /></c:if>
       <span id="flowNameTip" style="line-height: "></span>
    </label></td>
  </tr>
      <tr> 
    <td class="addTd">业务模块*</td>
    <td><label><select  id="optId"   name="optId" style="width:200px;height:25px;">   
			<option value="0">   
					<c:out value="-- 请选择 --"/>   
			</option>    
			<c:forEach var="opt" items="${cp:OPTINFO('W')}">
				<c:if test="${object.optId eq opt.optid}">
				<option value="${opt.optid}" selected = "selected" >   
					<c:out value="${opt.optname}"/>   
				</option>
				</c:if>
		<c:if test="${object.optId ne opt.optid}">
				<option value="${opt.optid}">   
					<c:out value="${opt.optname}"/>   
				</option>
				</c:if>  
			</c:forEach> 
	</select>
    </label>
    </td>
  </tr>
   <tr> 
    <td class="addTd">完成期限</td>
    <td><label>
    <c:if test="${not empty object.timeLimit}"><s:textfield id="timeLimit1"  name="object.timeLimit" value="%{object.timeLimit}" onchange="changeTL();"  style="width:200px;height:25px;" /></c:if>
      <c:if test="${empty object.timeLimit}"> <s:textfield id="timeLimit1"  name="object.timeLimit"  style="width:200px;height:25px;"  onchange="changeTL();" /></c:if>
     <!--   <span id="timeLimitTip" style="line-height: "></span> -->
    </label></td>
  </tr>
    <tr id="expireTr"> 
    <td class="addTd">逾期处理方法*</td>
    <td>
	    <select  id="expireOpt1"   name="object.expireOpt"  style="width:200px;height:25px;">   
				<option value="0">   
						<c:out value="-- 请选择 --"/>   
				</option>    
				<c:forEach var="dict" items="${cp:DICTIONARY('WFExpireOpt')}">
					<c:if test="${object.expireOpt eq dict.key}">
						<option value="${dict.key}" selected = "selected" >   
							<c:out value="${dict.value}"/>   
						</option>
					</c:if>
					<c:if test="${object.expireOpt ne dict.key}">
						<option value="${dict.key}">   
							<c:out value="${dict.value}"/>   
						</option>
					</c:if>  
				</c:forEach> 
		</select>
    </td>
  </tr>
     <tr> 
<script type="text/javascript">

if($("#timeLimit").attr("value")==""){
	$("#expireTr").hide();
}else{
	$("#expireTr").show();
}


</script>
    <td class="addTd">自动发布日期</td>
    <td><label>
     <c:if test="${not empty object.atPublishDate}">
     <input type="text" class="Wdate" id="atPublishDate" style="width:200px;height:25px;"
     	value='<fmt:formatDate value="${atPublishDate}" pattern="yyyy-MM-dd"/>' 
		name="atPublishDate" 
		onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 		    <sj:datepicker id="atPublishDate" --%>
<%-- 				name="atPublishDate" value="%{atPublishDate}"  style="width:120px;" --%>
<%-- 				yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"/> --%>
	    </c:if>
      <c:if test="${empty object.atPublishDate}">
           <input type="text" class="Wdate" id="atPublishDate"  style="width:200px;height:25px;"
		     	value='' name="atPublishDate" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 		    <sj:datepicker id="atPublishDate" --%>
<%-- 			name="atPublishDate" value=""  style="width:120px;" --%>
<%-- 			yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"  readonly="true" /> --%>
	</c:if>
       <span id="atPublishDateTip" style="line-height: "></span>
    </label></td>
  </tr>
  <tr>
    <td class="addTd">流程描述</td>
    <td >
      <label>
      	<c:if test="${not empty object.flowDesc}"><s:textarea name="flowDesc"  style="width:600px;height:50px;" value="%{object.flowDesc}"/></c:if>
        <c:if test="${empty object.flowDesc}"> <s:textarea name="flowDesc"  style="width:600px;height:50px;" /></c:if>
        </label>
    </td>
  </tr>
</table>

</fieldset>
    
<p/>
<div class="eXtremeTable" >
<fieldset>
<legend   style="width:auto;margin-bottom:10px;">流程阶段信息</legend>
	<table id="t_wfFlowStage"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
  
  				<!--<td class="tableHeader">
					流水号
				</td>	

				<td class="tableHeader">
					流程代码
				</td>
				
				--><td class="tableHeader">
					阶段编码
				</td>	

				<td class="tableHeader">
					阶段名称
				</td>	

				<td class="tableHeader">
					是否记入（流程）时间 
				</td>	

				<td class="tableHeader">
					期限类别
				</td>	

				<td class="tableHeader">
					期限时间
				</td>	

				<td class="tableHeader">
					逾期处理办法
				</td>
		
				<td class="tableHeader">
					<a href='#' onclick='addWfFlowStageItem(this);'>新增</a>
				</td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		 <s:iterator var="stage" id="stage" value="wfFlowStages" status="status" >    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td style="display:none"><s:textfield  name="stageId" /></td>

				<td style="display:none"><s:textfield  name="flowCode" /></td> 
  
				<td><s:textfield name="stageCode" style="width:120px;"/> </td>   
  
				<td><s:textfield name="stageName" style="width:120px;"/> </td>   
  
				<td>
					<select name="isAccountTime"  style="width:120px;">
						<option value="">---请选择---</option>
						<option value="T" <c:if test="${stage.isAccountTime eq 'T'}"> selected = "selected" </c:if>>是</option>
						<option value="N" <c:if test="${stage.isAccountTime eq 'N'}"> selected = "selected" </c:if>>否</option>
					</select>
				</td>   
  
				<td>
					<select id="limitType" name="limitType"  style="width:120px;">   
							<c:forEach var="dict" items="${cp:DICTIONARY('LIMIT_TYPE')}">
									<option value="${dict.key}"  <c:if test="${stage.limitType eq dict.key}"> selected = "selected" </c:if> >   
										<c:out value="${dict.value}"/>   
									</option>
							</c:forEach> 
					</select>
				</td>   
  
				<td><s:textfield name="timeLimit" style="width:120px;"/> </td>   
  
				<td> 			
					<select  id="expireOpt"   name="expireOpt"  style="width:120px;">   
							<option value="0">   
									<c:out value="-- 请选择 --"/>   
							</option>    
							<c:forEach var="dict" items="${cp:DICTIONARY('WFExpireOpt')}">
									<option value="${dict.key}"  <c:if test="${stage.expireOpt eq dict.key}"> selected = "selected" </c:if> >   
										<c:out value="${dict.value}"/>   
									</option>
							</c:forEach> 
					</select>	
	
				</td>   
		
				<td>
					<a href='javascript:void(0)' onclick='delWfFlowStageItem(this);'>删除</a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</s:iterator> 
		</tbody>        
	</table>
</fieldset>
</div>

</s:form>

	<script type="text/javascript">
	    
		var t_wfFlowStageRowCount; // 行数

	    $(function()
	    {
	
			t_wfFlowStageRowCount = $("table#t_wfFlowStage tr").length - 1; // 除去标题行   
	    	var wfFlowStageColName = 
	    	          ["stageId","flowCode","stageCode","stageName","timeLimit","guard"];
	    	
			var wfSelectColName =  ["isAccountTime","limitType","expireOpt"];
			
	        $("input[name='method:saveFlowField']").bind("click", function()
	        {
	            $("table#t_wfFlowStage tr").each(function(i)
	            {
	                $(this).attr("id", "tr_wfFlowStage" + i);
	                
	                $("#tr_wfFlowStage" + i + "  input[type='text']").each(function(j)
	                {
	                	
	                    $(this).attr("name", "newWfFlowStages["+(i-1)+"]." + wfFlowStageColName[j]);
	                });
	                
	                $("#tr_wfFlowStage" + i + "  select").each(function(k)
    	                {
    	                    $(this).attr("name", "newWfFlowStages["+(i-1)+"]." + wfSelectColName[k]);
    	                });
	            });
	            
	        });
	    });    
	    
        function addWfFlowStageItem()
        {
             var htmlItem = '<tr>';
  
			htmlItem += '<td style="display:none"><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].stageId" /></td>'; 

			htmlItem += '<td style="display:none"><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].flowCode" /></td>'; 

			htmlItem += '<td><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].stageCode" style="width:120px;"/></td>'; 

			htmlItem += '<td><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].stageName" style="width:120px;"/></td>'; 

			htmlItem += '<td><select name="newWfFlowStages['+t_wfFlowStageRowCount+'].isAccountTime" style="width:120px;"><option  value="">-- 请选择 --</option><option value="T">是</option><option value="N">否</option></select></td>'; 

			//htmlItem += '<td><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].limitType" style="width:120px;"/></td>'; 

			htmlItem +='<td><select  id="limitType"  name="newWfFlowStages['+t_wfFlowStageRowCount+'].limitType"  style="width:120px;">'   
						+'<c:forEach var="dict" items="${cp:DICTIONARY(\'LIMIT_TYPE\')}">'
						+		'<option value="${dict.key}">'   
						+			'<c:out value="${dict.value}"/>'   
						+		'</option>'
						+'</c:forEach> '
						+'</select></td>';
						
			htmlItem += '<td><input type="text" name="newWfFlowStages['+t_wfFlowStageRowCount+'].timeLimit" style="width:120px;"/></td>'; 

			htmlItem +='<td><select  id="expireOpt"  name="newWfFlowStages['+t_wfFlowStageRowCount+'].expireOpt"  style="width:120px;">'   
						+'<option value="0">'   
						+		'<c:out value="-- 请选择 --"/>'   
						+'</option>'    
						+'<c:forEach var="dict" items="${cp:DICTIONARY(\'WFExpireOpt\')}">'
						+		'<option value="${dict.key}">'   
						+			'<c:out value="${dict.value}"/>'   
						+		'</option>'
						+'</c:forEach> '
						+'</select></td>';
            
            t_wfFlowStageRowCount++;
            htmlItem += "<td> <a href='javascript:void(0)' onclick='delWfFlowStageItem(this);'>删除</a></td></tr>";
            $("table#t_wfFlowStage").append(htmlItem);

   		    $('table#t_wfFlowStage.tableRegion tr:odd').attr('class','odd')
   		    .hover(function(){
       		    	$(this).addClass("highlight");
       		    },function(){
       		    	$(this).removeClass("highlight");
       		});
   		    $('table#t_wfFlowStage.tableRegion tr:even').attr('class','even')
   		    .hover(function(){
	   		    	$(this).addClass("highlight");
	   		    },function(){
	   		    	$(this).removeClass("highlight");
   		    });
     	}
        
        function delWfFlowStageItem(varBtn)
        {
            $(varBtn).parent().parent().remove();
            t_wfFlowStageRowCount--;
   		    $('table#t_wfFlowStage.tableRegion tr:odd').attr('class','odd');
   		    $('table#t_wfFlowStage.tableRegion tr:even').attr('class','even');
        }

    </script>	
</body>
</html>

