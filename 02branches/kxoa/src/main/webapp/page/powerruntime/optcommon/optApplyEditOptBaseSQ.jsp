<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
	$(function(){
		$("#orgName").click(function(){
			var s = '${unitsJson}';
			if(s.trim().length){
				window.parent.selectPopWin(jQuery.parseJSON(s),$("#orgName"),$("#recievedepno"));
			}
		});
		
		//材料上传
		var powerid='${object.optBaseInfo.powerid}';
		
		var djid='${object.djId}';
		if(powerid!=''){
			$.ajax({
			type:"POST",
			url: "${pageContext.request.contextPath}/powerruntime/powerOptInfo!getGroupIDByItemid.do?object.itemId="+powerid,
			contentType:"text/html",					
			dataType:"json",
			processData:false,
			async: false,
			success:function(data){
				var obj = document.getElementById("slfieldset");
				obj.style.display="block";
				var url2='${pageContext.request.contextPath}/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId='
									+ djid
									+ '&stuffInfo.nodeInstId=0&stuffInfo.groupid='
									+ data.groupidByitemid;
							var objfram = document.getElementById('basicsj');
							objfram.src = url2;
						},
						error : function() {
							alert("系统提交失败");
						}
					});
		}
	});
</script>
<html>
<head>


<title>办件基础信息</title>
</head>
<body class="sub-flow">
 <s:form action="optApply"  method="post" namespace="/powerruntime" id="optApplyForm" target="_parent" onsubmit="return checkForm();">
	<input type="hidden" id="djId" name="djId" value="${object.djId}" />
	<input type="hidden" id="flowInstId" name="flowInstId"
		value="${flowInstId}" />
	<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
	<input type="hidden" id="nodeInstId" name="nodeInstId"
		value="${nodeInstId}">
	<input type="hidden" id="flowCode" name="flowCode" value="000977" />
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;"
		class="_new">
		<legend style="padding: 4px 8px 3px;">
			<b>办件信息</b>
		</legend>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd" width="130"><c:if
						test="${vPowerUserInfo.itemType eq 'SQ' }">
						事项标题
					</c:if> <c:if test="${vPowerUserInfo.itemType eq 'QB' }">
						签报标题
					</c:if></td>
				<td align="left"><s:textfield
						name="optBaseInfo.transaffairname"
						value="%{object.optBaseInfo.transaffairname}"
						style="width:100%;height:27px;" /></td>
			</tr>

			<tr>
				<td class="addTd"><c:if
						test="${vPowerUserInfo.itemType eq 'SQ' }">
						事项内容
					</c:if> <c:if test="${vPowerUserInfo.itemType eq 'QB' }">
						签报内容
					</c:if></td>
				<td align="left"><s:textarea name="optBaseInfo.content"
						value="%{object.optBaseInfo.content}"
						style="width:100%;height:40px;" /></td>
			</tr>
			<tr>
				<td class="addTd">接收部门</td>
				<td align="left"><input type="text" id="orgName" name="orgName"
					rows="1" cols="1" style="width: 100%;height: 30px" value="${unitValue }"/> <input id="recievedepno" type="hidden"
					name="recievedepno" value="${object.recievedepno }" /></td>
			</tr>

			<input type="hidden" id="riskType" name="optBaseInfo.riskType"
				value="${object.optBaseInfo.riskType}" />
			<input type="hidden" id="riskDesc" name="optBaseInfo.riskDesc"
				value="${object.optBaseInfo.riskDesc}" />

		</table>
		<center style="margin-top: 10px;">
			<span style="display: none;"> <s:submit name="saveAndSubmit"
					method="submitReditSQ" cssClass="btn" value="提 交" id="submitBtn" />
			</span> <span style="display: none;"> <s:submit name="save"
					method="saveReditSQ" cssClass="btn" value="保 存" id="saveBtn" />
			</span> <span style="display: none;"> <input type="button"
				value="返回" class="btn" onclick="window.history.go(-1);" id="backBtn" />
			</span>
		</center>	
	</fieldset>
	<div id="slfieldset" style="display: none;">
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;"
				class="_new">
				<legend style="padding: 4px 8px 3px;">
					<b>材料上传</b>
				</legend>
				<input type="hidden" id="groupId" name="groupId" value="" />
				<iframe id="basicsj" name="sjfj" height="" src="" width="100%"
					style="overflow: hidden;" frameborder="no" scrolling="false"
					border="0" marginwidth="0" marginheight="0" />
			</fieldset>
		</div>
	</s:form>
</body>
</html>
