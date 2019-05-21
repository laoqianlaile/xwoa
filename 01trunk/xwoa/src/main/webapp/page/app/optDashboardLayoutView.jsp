﻿<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title></title>
<style type="text/css">
  .layout-set-modules{height:60px;border:1px #ccc solid;overflow: hidden;}
  .layout-set-modules li{float:left;margin:5px;font-weight: bold; font-size:12px; white-space: nowrap;}
  .layout-set-panel{height:500px;border:1px #ccc solid;}
  .layout-set-panel table td{border:dashed 1px #ccc;}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.8.19.custom.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.8.19.custom.min.js" type="text/javascript"></script>
</head>

<body>
<fieldset class="_new">
		<legend>
			<p>首页模块排版</p>
		</legend>

	<s:form action="optDashboardLayout" method="post" namespace="/oa"
		id="optDashboardLayoutForm" onsubmit="return checkForm();">
     <div align="left">
		<input class="btn" id="back" type="button"  value="返回"/>
	 </div>
		<input type="hidden" id="id" name="id" value="${object.id}" />
		<input type="hidden" id="content" name="content" value="${object.content}"/>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable" style="table-layout: fixed;width:100%">
			<tr>
			    <td class="addTd" style="height:35px!important">排版名称</td> 
				<td align="left">${object.layoutName}</td>
				<td class="addTd" style="height:35px!important">排版方式</td> 
				<td align="left">
				   <c:forEach var="methodItem" items="${methodList}">
				      <c:if test="${methodItem.id==object.layoutMethodId}">${methodItem.methodName}</c:if>
				   </c:forEach>
				   <input type="hidden" id="layoutMethodId" value="${object.layoutMethodId}">
				</td>
			</tr>
			<tr>
			    <td class="addTd" style="height:35px!important">创建类型</td> 
				<td align="left">
				  <c:if test="${object.layoutType=='BUILTIN'}">系统内置</c:if>
				  <c:if test="${object.layoutType=='ALLOCATED'}">按职务分配</c:if>
				  <c:if test="${object.layoutType=='PERSONAL'}">自定义</c:if>
				</td>
				<td class="addTd userScopeTD" style="height:35px!important;<c:if test="${object.layoutType!='ALLOCATED'}">display:none</c:if>">行政职务</td> 
				<td align="left" class="userScopeTD" style="<c:if test="${object.layoutType!='ALLOCATED'}">display:none</c:if>">
				   ${object.userScope}
				</td>
			</tr>
			<tr>
			    <td class="addTd" style="height:60px!important">备注</td> 
				<td align="left" colspan="3">
				   ${object.remark}
				</td>
			</tr>
		   <tr>
			   <td colspan="4" style="padding:2px">
			    <div>
			       <div class="layout-set-modules" style="display:none">
			         <ul>
			           <c:forEach var="moduleItem" items="${moduleList}">
			              <li><input type="checkbox" data-modulename="${moduleItem.moduleName}" id="chk-${moduleItem.moduleCode}" data-modulecode="${moduleItem.moduleCode}" onclick="chkclick(this)">&nbsp;&nbsp;${moduleItem.moduleName}</li>
			           </c:forEach>
			         </ul>
			       </div>
			       <div class="layout-set-panel">
			          
			       </div>
			    </div>
			   </td>	
			</tr>
		</table>
	 </s:form>
	 
</fieldset>
</body>
<script type="text/javascript">
$(function(){
	$(".layout-set-panel").height($(window).height()-215);
	
	$('#back').click(function(){
		var srForm = document.getElementById("optDashboardLayoutForm");
		srForm.action = 'optDashboardLayout!list.do';
		srForm.submit();
	});
	
	if($("#id").val()!=''){
		initForEdit();
	}
});

function initForEdit(){
	var layoutMethodId = $("#layoutMethodId").val();
	if(layoutMethodId!=''){
		//渲染布局
		renderLayout(layoutMethodId);
		//解析布局参数
		parseLayoutData();
	}
}

function parseLayoutData(){
	var jsonContent = $("#content").val();
	if(jsonContent!=''){
		var jsonObjArr = eval("("+jsonContent+")");
		$.each(jsonObjArr,function(i,obj){
			var chkInput = $("#chk-"+obj.moduleCode);
			//勾选checkbox
			$("#chk-"+obj.moduleCode).prop("checked",true);
			//渲染模块
			var id = "div-"+chkInput.data("modulecode");
			var text = chkInput.data("modulename");
			var $div = createModuleJQObj(id,text);
			$(".layout-set-panel").find("td").eq(obj.tdIndex).html($div);
		});
	}
}

function renderLayout(mid){
	if(mid==''){
		$(".layout-set-panel").html("");
	}else{
		$.ajax({
			type:"post",
			async:false,
			url:"${ctx}/oa/optLayoutMethod!getContent.do",
			data:{"id":mid},
			dataType:"html",
			success:function(data){
				$(".layout-set-panel").html(data);
				$(".layout-set-panel").find("td").each(function(){
					var obj = $(this);
					obj.droppable({
					      accept: "div.moduleItem",
					      drop: function( event, ui ) {
					    	  var cloneDiv = ui.draggable.clone();
					    	  var dragparent = ui.draggable.parent();
					    	  //如果目标td中已经存在模块，那么交换位置
					    	  if(obj.find("div.moduleItem").length == 1){
					    		  ui.draggable.parent().html(obj.html());
					    	  }
					    	  if(obj.find("div.moduleItem").length == 0){
					    		  ui.draggable.parent().html("&nbsp;");
					    	  }
					    	   obj.html(cloneDiv.css("left","0px").css("top","0px"));
					      }
					    });
				});
			}
		});
	}
}

function createModuleJQObj(id,text){
	var $div = $("<div>",{"class":"moduleItem","style":"width:100%;height:100%;border:1px #ccc solid;cursor:move","id":id});
	var content = '<div style="height:30px;line-height:30px;background:#ccc;font-size:12px;font-weight: bold;padding-left:10px;color:#000">'+text+"</div>"
	            + '<div style="padding-top:40px;text-align:center;color:#ccc">模块内容</div>'
	$div.html(content);
	return $div;
}


</script>
</html>
