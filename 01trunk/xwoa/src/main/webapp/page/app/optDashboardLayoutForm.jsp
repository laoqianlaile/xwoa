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
  .layout-set-panel{height:500px;border:1px #ccc solid;border-top:none;}
 .layout-set-panel table td{border:1px #ccc dashed}
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
		<input type="button" class="btn" value="保存"
			onclick="save();" />
		<input class="btn" id="back" type="button"  value="返回"/>
	 </div>
		<input type="hidden" id="id" name="id" value="${object.id}" />
		<input type="hidden" id="content" name="content" value="${object.content}"/>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable" style="table-layout: fixed;width:100%">
			<tr>
			    <td class="addTd" style="height:35px!important">排版名称<span style="color: red;line-height:25px">*</span></td> 
				<td align="left"><input type="text" name="layoutName" id="layoutName" value="${object.layoutName}" maxlength="30" style="height: 25px;width:300px;"/> </td>
				<td class="addTd" style="height:35px!important">排版方式<span style="color: red;line-height:25px">*</span></td> 
				<td align="left">
				    <select id="layoutMethodId" name="layoutMethodId" onchange="renderLayout(this.value)" style="height: 25px;width:200px;">
				       <option value="">---请选择-----</option>
				       <c:forEach var="methodItem" items="${methodList}">
				          <option value="${methodItem.id}" <c:if test="${methodItem.id==object.layoutMethodId}">selected="selected"</c:if>>${methodItem.methodName}</option>
				       </c:forEach>
				    </select>
				</td>
			</tr>
			<tr>
			    <td class="addTd" style="height:35px!important">创建类型<span style="color: red;line-height:25px">*</span></td> 
				<td align="left">
				  <select id="layoutType" name="layoutType" style="height: 25px;width:200px;" onchange="linkUserScope(this.value)">
				       <option value="BUILTIN">系统内置</option>
				       <option value="ALLOCATED">按职务分配</option>
				        <option value="PERSONAL">自定义</option>
				  </select>
				</td>
				<td class="addTd userScopeTD" style="height:35px!important;display:none">行政职务</td> 
				<td align="left" class="userScopeTD" style="display:none">
				    <input type="text" name="userScope"  id="userScope" style="height: 25px;width:200px;" value="${object.userScope}"/>
				</td>
			</tr>
			<tr>
			    <td class="addTd" style="height:60px!important">备注</td> 
				<td align="left" colspan="3">
				   <textarea  name="remark" id="remark" style="height:50px;width:100%">${object.remark}</textarea>
				</td>
			</tr>
		   <tr>
			   <td colspan="4" style="padding:2px">
			    <div>
			       <div class="layout-set-modules">
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
	$(".layout-set-panel").height($(window).height()-275);
	
	$('#back').click(function(){
		var srForm = document.getElementById("optDashboardLayoutForm");
		srForm.action = 'optDashboardLayout!list.do';
		srForm.submit();
	});
	
	if($("#id").val()!=''){
		initForEdit();
	}
});

function checkForm(){
   	if($("#layoutName").val()==""){
   		alert("排版名称不能为空！");
   		return false;
   	}
   	if($("#layoutMethodId").val()==""){
   		alert("排版方式不能为空！");
   		return false;
   	}
   	if($("#layoutType").val()=="ALLOCATED" && $("#userScope").val()==""){
   		alert("行政职务不能为空!");
   		return false;
   	}
   	if($("#remark").val().length > 100){
   		alert("备注不能超过100个字!");
   		return false;
   	}
   	if($("#content").val().length == 0){
   		alert("请设置排版!");
   		return false;
   	}
   	return true;
}

function linkUserScope(v){
   if(v == "ALLOCATED") {
	   $(".userScopeTD").show();
   }else{
	   $(".userScopeTD").hide();
   }	
}

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
			bindDragEvent($div);
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
					    	  
					    	  bindDragEvent(cloneDiv); 
					    	  bindDragEvent(dragparent.find("div.moduleItem")); 
					      }
					    });
				});
			}
		});
	}
}
function save(){
	var srForm = document.getElementById("optDashboardLayoutForm");
	srForm.action = 'optDashboardLayout!save.do';
	getLayoutJSON();
	srForm.submit();
}

function getLayoutJSON(){
	var JSONStr = "";//IE8不支持JSON.stringfy方法
	$(".layout-set-panel").find("td").each(function(i,ele){
		if($(ele).find("div.moduleItem").length==1){
			var moduleCode = $(ele).find("div.moduleItem").attr("id").replace("div-","");
			obj["moduleCode"] = moduleCode;
			obj["tdIndex"] = i;
			JSONStr+=',{&quot;moduleCode&quot;:&quot;'+moduleCode+'&quot;,&quot;tdIndex&quot;:&quot;'+i+'&quot;}'
		}
	});
	if(JSONStr.length>0){
		$("#content").val("["+JSONStr.substring(1)+"]");
	}
}
function createModuleJQObj(id,text){
	var $div = $("<div>",{"class":"moduleItem","style":"width:100%;height:100%;border:1px #ccc solid;cursor:move","id":id});
	var content = '<div style="height:30px;line-height:30px;background:#ccc;font-size:12px;font-weight: bold;padding-left:10px;color:#000">'+text+"</div>"
	            + '<div style="padding-top:40px;text-align:center;color:#ccc">模块内容</div>'
	$div.html(content);
	return $div;
}

function chkclick(ele){
	if($("#layoutMethodId").val()==""){
		$(ele).prop("checked",false);
		alert("请选择排版方式");
	}
	var id = "div-"+$(ele).data("modulecode");
	var text = $(ele).data("modulename");
	if($(ele).is(':checked')){
		if($(".layout-set-panel").find("#"+id).length==0){
			var $div = createModuleJQObj(id,text);
			var isFull = true;
			$(".layout-set-panel").find("td").each(function(){
				if($(this).find("div").length==0){
					$(this).html($div);
					isFull = false;
					return false;
				}
			});
			if(isFull){
				$(ele).prop("checked",false);
				alert("没有空间填充模块了");
			}
			bindDragEvent($div);
		}
	}else{
		$(".layout-set-panel").find("#"+id).parent().html("&nbsp;");
	}
}

function bindDragEvent($ele){
	$ele.draggable({
		revert: "invalid", // when not dropped, the item will revert back to its initial position
		containment: ".layout-set-panel"
	});
}
</script>
</html>
