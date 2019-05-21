<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<title><s:text name="oaSupervise.edit.title" /></title>
</head>

<body class="sub-flow">
<%-- <p class="ctitle"><s:text name="oaSupervise.edit.title" /></p> --%>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaSupervise"  method="post" namespace="/oa" id="oaSuperviseForm" >
					
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowinstid" name="flowinstid" value="${flowinstid}" />
		<input type="hidden" id="flowCode" name="flowCode" value="000859" />
	<fieldset>
		<legend>	
		督办详情
		</legend>

  <input type="hidden" id="supDjid" name="supDjid" value="${object.supDjid}" />
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
				
                  <tr>
					<td class="addTd">
						督办批示时限<span style="color: red">*</span>
					</td>
					<td align="left">
							<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.limittime}" pattern="yyyy-MM-dd "/>'
					  		id="limittime" name="limittime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />		
					</td>
					
				</tr>
				<tr >
				<td class="addTd">
					批示意见
				</td>
				<td align="left">
					<s:textarea id="advice" name="advice" value="%{object.advice}" style="width:100%;height:50px;" />
				</td>
				</tr>
				
				

</table>
        <div class="formButton">

		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
			<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
		</div>

</fieldset>
</s:form>

</body>
	<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
</html><script type="text/javascript">

function cancel() {
	DialogUtil.prototype.close();
}
function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
function submitItemFrame(subType) {
	if (docheck() == false) {
		return;
	} else {
		var srForm = document.getElementById("oaSuperviseForm");
	
		if (subType == 'SUB') {
			srForm.action = 'oaSupervise!flowSaveAndSubmit.do';
		}
	
		 var win = art.dialog.open.origin;//来源页面
		if(win){
		$.ajax({
            type: "post",
            url: srForm.action,     
            data: $("#oaSuperviseForm").serialize(),
            async: false,
            success: function(data) {
           		// 如果父页面重载或者关闭其子对话框全部会关闭
               // win.location.reload(true);
            	art.dialog.close();
            },
            error: function(data) {
                alert("发起督办失败，请重新尝试！");
            }
        });
		} 
		
		
	}
}
function docheck() {

	if ($("#limittime").val() == '') {
		alert("督办时限不可为空！");
		$('#limittime').focus();
		return false;
	}
		return true;
	}
	
	
</script>
