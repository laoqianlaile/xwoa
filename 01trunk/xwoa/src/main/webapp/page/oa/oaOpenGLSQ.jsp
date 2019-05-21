<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<title><s:text name="suppower.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="voaBizBindInfo" namespace="/oa"	style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			<input type="hidden" name="s_orgId" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}">
			<input type="hidden" name="s_itemtype" value="${s_itemtype}">	
			<input type="hidden" name="itemtype" value="${s_itemtype}">	
			<input type="hidden" name="s_djId" value="${djId }" >
			<input type="hidden" name="nodeInstId" value="${nodeInstId}" >
					
				<tr>
					<td class="addTd">业务名称:</td>
					<td>
					<s:textfield name="s_transaffairname" style="width:180px" value="%{#parameters['s_transaffairname']}" /></td>
				</tr>
				
				<c:if test="${s_itemtype ne 'FW' }">
				<tr>
				<td class="addTd">登记时间:</td>
				<td>
					<input type="text" class="Wdate"  id="s_createStartDate" name="s_createStartDate" 
					style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
					<c:if test="${not empty s_createStartDate }"> value="${s_createStartDate}" </c:if>
					<c:if test="${empty s_createStartDate  }">value="${param['s_createStartDate'] }"</c:if>
					
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">至
					<input type="text" class="Wdate"  id="s_createEndDate" name="s_createEndDate" 
					style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
					<c:if test="${not empty s_createEndDate }"> value="${s_createEndDate}" </c:if>
					<c:if test="${empty s_createEndDate  }">value="${param['s_createEndDate'] }"</c:if>
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
				</td>
				</tr>
				</c:if>
				
				<c:if test="${s_itemtype eq 'FW' }">
				<tr>
				<td class="addTd">发文号:</td>
				<td>
					<s:textfield name="s_dispatchDocNo" style="width:180px" value="%{#parameters['s_dispatchDocNo']}" />
				</td>
				</tr>
				</c:if>

				<tr>
					<td align="center" colspan="2">
					<s:submit method="oaOpenGLSQ" key="opt.btn.query" cssClass="btn" /> 
					<!-- <input type="button" class="btn" value="关闭" onclick="window.close();"> -->
					<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="voaBizBindInfo!oaOpenGLSQ.do" items="objList" var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<ec:row>
			<ec:column property="itemtype" title="业务类别"	style="text-align:center" sortable="false">
				 ${cp:MAPVALUE('optTypeName',suppower.itemtype)}
			</ec:column>
		
			

		 <ec:column property="transaffairname" title="业务名称" style="text-align:center" sortable="false">
					<input type="hidden" value="${suppower.transaffairname}"/> 
					<c:choose>
					<c:when test="${fn:length(suppower.transaffairname) > 20}">
						<c:out value="${fn:substring(suppower.transaffairname, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.transaffairname}" />
					</c:otherwise>
				</c:choose>
				</ec:column>  
				<c:if test="${s_itemtype eq 'FW' }">
			
			<ec:column property="dispatchDocNo" title="发文号"	style="text-align:center" sortable="false"/>
			<ec:column property="dispatchUser" title="签发人"	style="text-align:center" sortable="false">
			 ${cp:MAPVALUE('usercode',suppower.dispatchUser)}
			</ec:column>
			<ec:column property="dispatchDate" title="签发时间"	style="text-align:center" sortable="false">
				${suppower.dispatchDate }
			</ec:column>
		</c:if>
		<c:if test="${s_itemtype ne 'FW' }">
				<ec:column property="createdate" title="登记时间"	style="text-align:center" sortable="false">
				${suppower.createdate }
			</ec:column>
			</c:if>
			<ec:column property="bizstate" title="业务状态"	style="text-align:center" sortable="false">
			 ${cp:MAPVALUE('bizType',suppower.bizstate)}
			</ec:column>
		    <ec:column property="user"
				  title='<button name="quanxuan"  id="quanxuan" target="false" >全选</button><button name="czpw" target="false" >提交</button>'
				  sortable="false"  style="width:5%">		
					<input type="checkbox" id="${suppower.djId}" class="ecbox" 
						value="${suppower.djId}">
			</ec:column>  
			
			
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">	
$("[name='quanxuan']").bind('click',function(e){
    var e = e || window.event;
	if($("[name='quanxuan']").attr("target")=="false"){
	$("[name='quanxuan']").attr("target","true");
	$("input[class='ecbox']").attr("checked","true"); 
	$("#quanxuan").html('取消');
	if(e.preventDefault){
		e.preventDefault();
	}else{
		return false;	
	}
	}
	else{
		$("[name='quanxuan']").attr("target","false");
		$("input[class='ecbox']").removeAttr("checked");
		$("#quanxuan").html('全选');
		if(e.preventDefault){
			e.preventDefault();
		}else{
			return false;	
		}
	}	
});

$("[name='czpw']").bind('click',function(){
	var vfstring = "";
	var items = $('[class = "ecbox"]:checkbox:checked');
	var item_Type = "${bjType}";
	var nodeInstId="${nodeInstId}";
	for (var i = 0; i < items.length; i++) {
	     vfstring = (vfstring + items.get(i).value + ','); 
	}
	if(vfstring!=""){
	if(confirm("是否确定提交?")){
		$.ajax({
			   type : 'post',
			   url: "<%=request.getContextPath()%>/oa/oaBizBindInfo!submitOaBizBindInfo.do?startDjid=${startDjid}&nodeInstId=${nodeInstId}",
			   dataType:'text',
			   async: false,
			   data : {
				   resetUsers:  vfstring,bjType : item_Type
			   },
			   success: function(data){
				   var url1 ="<%=request.getContextPath()%>/powerruntime/optApply!startEntrance.do?djId=${startDjid}&nodeInstId=${nodeInstId}&itemtype=${bjType}";
				   window.location.href=url1;
			   }
	});
	}
	
	}
	else{
		alert("请选中办件再提交 !");
		
	}
		
}); 	
</script>
</html>
