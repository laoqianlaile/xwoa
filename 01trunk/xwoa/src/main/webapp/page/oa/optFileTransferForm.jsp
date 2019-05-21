﻿<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <title></title>
	<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
		rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
		type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
		type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
		rel="stylesheet" type="text/css" />
	<style type="text/css">
		   a.btnPlus{display:inline-block;width:20px;height:20px;background:url('${ctxStatic}/image/ico-plus.png') no-repeat center;}
		   a.btnSubtract{display:inline-block;width:20px;height:20px;background:url('${ctxStatic}/image/ico-subtract.png') no-repeat center;}
		    .fileList{overflow:hidden;}
		   .fileList li{float:left;margin-right:10px;margin-bottom:2px;}
	</style>
</head>
<body class="sub-flow">
 <fieldset class="form">
		<legend>
			<p>文件传输</p>
		</legend>

	<s:form action="optFileTransferSend" method="post" namespace="/oa"
		id="optFileTransferSend" enctype="multipart/form-data">
     <div align="left">
		<input type="button" class="btn" value="提交"
			onclick="save();" />
		<input class="btn" id="back" type="button"  value="返回"/>
	 </div>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		   <tr>
			    <td class="addTd">标题<span style="color: red">*</span></td> 
				<td align="left"><input type="text" name="subject" id="subject" style="width: 94%; height: 25px;"/>
				</td>
			</tr>
			<tr>
			    <td class="addTd">收件人<span style="color: red">*</span></td> 
				<td align="left"><input type="text" name="receiverName" id="receiverName" style="width: 94%; height: 25px;"/>
				  <input type="hidden" name="receiverCode" id="receiverCode"/>
				</td>
			</tr>
			<tr>
			   <td class="addTd" width="14%">短信提醒&nbsp;</td>
			   <td align="left" width="86%">
				
				<input id="isSendMsgView" class="checkboxClass" type="checkbox"
						name="isSendMsgView"  />是否短信提醒收件人
					
					
				</td>
			 </tr>
			<tr>
			<td class="addTd">文件<span style="color: red">*</span></td>
				<td align="left">
				  <div class="inputFileArea">
				     <input type="file" id="stuff" name="stuff" size="40" style="width: 94%;height: 25px;"/>
				     <a class="btnPlus" href="javascript:void(0);" onclick="appendInputFile(this);"></a>
				   </div>  
				</td> 
			</tr>
			<tr>
			    <td class="addTd">备注</td> 
				<td align="left">
				  <textarea rows="" cols="" name="remark" id="remark" style="width:94%;height:60px;"></textarea>
				</td>
			</tr>
		</table>
		 <input type="hidden" name="sendType" id="sendType" value="${object.sendType}">
	     <input type="hidden" name="scopeType" id="scopeType" value="${object.scopeType}">
	 </s:form>
</fieldset>
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
</body>
<script type="text/javascript">
$(function(){
	$('#back').click(function(){
		var srForm = document.getElementById("optFileTransferSend");
		if($("#sendType").val()=="1"){//上报
			srForm.action = 'optFileTransferSend!listReportToSysin.do';	
		}
		if($("#sendType").val()=="2"){//下发
			srForm.action = 'optFileTransferSend!listIssued.do';	
		}
		srForm.submit();
	});
	$("#receiverName").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#receiverName"),
							$("#receiverCode"));
				}
				;
			});
});
function save(){
	
	if(checkForm()){
		var srForm = document.getElementById("optFileTransferSend");
		srForm.action = 'optFileTransferSend!save.do';
		srForm.submit();
	}
	
	
}

function checkForm() {
	
    var flag=true;
    if ($('#subject').val() == '') {
		alert("标题不可为空！");
		$('#subject').focus();
		flag = false;
		return flag;
	}
    if ($('#receiverName').val() == '') {
		alert("收件人不可为空！");
		$('#receiverName').focus();
		flag = false;
		return flag;
	}
    return flag;
}

function appendInputFile(obj){
	var divContainer = $(obj).parent("div");
	var btnSubtract = $("<a>",{"class":"btnSubtract","href":"javascript:void(0);"}).click(function(){
		removeInputFile(this);
	});
	if(divContainer.find(".btnSubtract").length>0){
		divContainer.find(".btnSubtract").remove();
	}
	divContainer.append(btnSubtract);
	var newDivContainer = divContainer.clone();
	newDivContainer.insertAfter(divContainer);
	//当前加号删除
	$(obj).remove();
	//重新绑定事件
	newDivContainer.find(".btnSubtract").click(function(){removeInputFile(this);});
	newDivContainer.find(".btnPlus").click(function(){appendInputFile(this);});
	divContainer.find(".btnSubtract").click(function(){removeInputFile(this);});
}
function removeInputFile(obj){
 	var divContainer = $(obj).parent("div");
	var divContainerParent = divContainer.parent();
	//如果删除行中有加号按钮，需要将加号按钮添加到上一行去
    if($(obj).prev().hasClass("btnPlus")){
    	var btnPlus = $("<a>",{"class":"btnPlus","href":"javascript:void(0);"}).click(function(){
    		appendInputFile(this);
    	});
    	btnPlus.insertBefore(divContainer.prev().find(".btnSubtract"));
	} 
	divContainer.remove();
	//判断是否剩下最后一个了，如果是那么减号操作不允许
	if(divContainerParent.find("div.inputFileArea").length==1){
		divContainerParent.find("div.inputFileArea").find(".btnSubtract").remove();
	}
}
function selectPopWin(json, o1, o2, oShow) {
	new treePerson(json, o1, o2, oShow).init();
	setAlertStyle("attAlert");
}
</script>
</html>
