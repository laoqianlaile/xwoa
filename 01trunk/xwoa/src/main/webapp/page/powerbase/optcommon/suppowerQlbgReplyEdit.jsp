<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>权力变更回复</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			<s:text name="suppower.edit.title" />
		</legend>
		<s:form action="suppowerchglog" method="post" namespace="/powerbase" id="suppowerchglogForm" enctype="multipart/form-data">
		 	<input id="version" type="hidden" name="version" value="${suppowerqlbgsq.version}" />
		 	<input id="changeId" type="hidden" name="changeId" value="${suppowerqlbgsq.changeId}" />
		 	<input id="itemId" type="hidden" name="itemId" value="${suppowerqlbgsq.itemId}" />
		 	<input id="itemName" type="hidden" name="itemName" value="${suppowerqlbgsq.itemName}" />
		 	<input id="chgType" type="hidden" name="chgType" value="${suppowerqlbgsq.chgType}" />
		 	<input id="orgId" type="hidden" name="orgId" value="${suppowerqlbgsq.orgId}" />
		 	<input id="requestTime" type="hidden" name="requestTime" value="${suppowerqlbgsq.requestTime}" />
		 	<input id="chgReason" type="hidden" name="chgReason" value="${suppowerqlbgsq.chgReason}" />
		 	<input id="chgContent" type="hidden" name="chgContent" value="${suppowerqlbgsq.chgContent}" />
		 	<input id="requester" type="hidden" name="requester" value="${suppowerqlbgsq.requester}" />
		 	<input id="auditContent" type="hidden" name="auditContent" value="${suppowerqlbgsq.auditContent}" />
		 	<input id="auditTime" type="hidden" name="auditTime" value="${suppowerqlbgsq.auditTime}" />
		 	<input id="auditor" type="hidden" name="auditor" value="${suppowerqlbgsq.auditor}" />
		 	<input id="chgResult" type="hidden" name="chgResult" value="${suppowerqlbgsq.chgResult}" />
		 	<input id="begTime" type="hidden" name="begTime" value="${suppowerqlbgsq.begTime}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd" width="130">权力编码</td>
				<td colspan="3"><s:property value="%{suppowerqlbgsq.itemId}" /></td>
				</tr>
				<tr>
				<td class="addTd" width="130">权力事项名称</td>
				<td colspan="3"><s:property value="%{suppowerqlbgsq.itemName}" /></td>
				</tr>
				<tr>
				<td class="addTd" width="130">行使部门</td>
				<td >${cp:MAPVALUE("depno",suppowerqlbgsq.orgId)}</td>
				<td class="addTd" width="130">权力类型</td>
				<td >${cp:MAPVALUE("ITEM_TYPE",suppowerqlbgsq.itemType)}</td>
				</tr>
				<tr>
				<td class="addTd" width="130">变更类型</td>
				<td ><c:if test="${suppowerqlbgsq.chgType eq 'fz'}">
					废止
					</c:if>
					<c:if test="${suppowerqlbgsq.chgType eq 'gq'}">
					挂起
					</c:if>
					<c:if test="${suppowerqlbgsq.chgType eq 'xg'}">
					修改
					</c:if>
					<c:if test="${suppowerqlbgsq.chgType eq 'tj'}">
					添加
					</c:if></td>
				<td class="addTd" width="130">变更申请时间</td>
				<td ><s:property value="%{suppowerqlbgsq.requestTime}" /><s:date name="requestTime"
							format="yyyy-MM-dd" /></td>
				</tr>
				<tr>
				<td class="addTd" width="130">变更申请人</td>
				<td >${cp:MAPVALUE("PROPOSER_TYPE",suppowerqlbgsq.requester)}</td>
				<td class="addTd" width="130">变更状态</td>
				<td >	
				<c:if test="${suppowerqlbgsq.chgType eq 'fz' or suppowerqlbgsq.chgType eq 'xg' or suppowerqlbgsq.chgType eq 'gq' or suppowerqlbgsq.chgType eq 'qy'}">
					部门回复意见
					</c:if></td>
				</tr>
				<tr>
				<td class="addTd" width="130">变更意见</td>
				<td colspan="3"><s:property value="%{suppowerqlbgsq.chgReason}" /></td>
				</tr>
			</table>
			<fieldset>
			<legend>权力变更意见信息</legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">法规处变更意见</td>
					<td ><s:property value="%{suppowerqlbgsq.auditContent}" /> <div class="r" align="right">
					审核人员：<s:property value="%{suppowerqlbgsq.auditor}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;审核时间：<s:property value="%{suppowerqlbgsq.auditTime}" /></div></td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>审核者信息</legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" width="130">回复意见</td>
					<td align="left" colspan="3">
					<s:textarea name="replyContent" cols="40" rows="2" style="width:98%;height:40px;" /><span style="color: red">*</span></td>
				</tr>
			</table>
			</fieldset>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr align="center">
				<td align="center">
			<s:submit name="save" method="SuppowerReplySave" cssClass="btn" key="opt.btn.save" />
			<s:submit name="back" method="listSupPowerReply" cssClass="btn" key="opt.btn.back" />
			</td>
			</tr>
		</table>
		</s:form>
	</fieldset>
	<script type="text/javascript">
		function checkItemType() {
			var item_type = document.getElementById("itemType");
			var punishobject_tr = document.getElementById("punishobject_tr");
			var islawsuit_tr = document.getElementById("islawsuit_tr");
			var punishbasiscontent_tr = document.getElementById("punishbasiscontent_tr");
			var punishbasis_tr = document.getElementById("punishbasis_tr");
			var punishClass_tr = document.getElementById("punishClass_tr");
			var levyUpon_tr = document.getElementById("levyUpon_tr");
			var freeJudge_tr = document.getElementById("freeJudge_tr");
			if (item_type.value == '6') {
				punishobject_tr.style.display = "block";
				islawsuit_tr.style.display = "block";
				punishbasiscontent_tr.style.display = "block";
				punishbasis_tr.style.display = "block";
				punishClass_tr.style.display = "block";
				levyUpon_tr.style.display = "block";
				freeJudge_tr.style.display = "block";
			} else {
				punishobject_tr.style.display = "none";
				islawsuit_tr.style.display = "none";
				punishbasiscontent_tr.style.display = "none";
				punishbasis_tr.style.display = "none";
				
				punishClass_tr.style.display = "none";
				levyUpon_tr.style.display = "none";
				freeJudge_tr.style.display = "none";
			}
		}
		function inital(v) {

			var item_type = document.getElementById("itemType");

			var punishobject_tr = document.getElementById("punishobject_tr");
			var islawsuit_tr = document.getElementById("islawsuit_tr");
			var punishbasiscontent_tr = document.getElementById("punishbasiscontent_tr");
			var punishbasis_tr = document.getElementById("punishbasis_tr");
			
			var punishClass_tr = document.getElementById("punishClass_tr");
			var levyUpon_tr = document.getElementById("levyUpon_tr");
			var freeJudge_tr = document.getElementById("freeJudge_tr");
			if (item_type.value == '6') {
				punishobject_tr.style.display = "block";
				islawsuit_tr.style.display = "block";
				punishbasiscontent_tr.style.display = "block";
				punishbasis_tr.style.display = "block";
				
				punishClass_tr.style.display = "block";
				levyUpon_tr.style.display = "block";
				freeJudge_tr.style.display = "block";
			} else {
				punishobject_tr.style.display = "none";
				islawsuit_tr.style.display = "none";
				punishbasiscontent_tr.style.display = "none";
				punishbasis_tr.style.display = "none";
				
				punishClass_tr.style.display = "none";
				levyUpon_tr.style.display = "none";
				freeJudge_tr.style.display = "none";
			}
			if (v == '1') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				chargeStandard_tr.style.display = "none";
				chargeBasis_tr.style.display = "none";
			}
		}

		function checkReceive(v) {
			var chargeStandard_tr = document
					.getElementById("chargeStandard_tr");
			var chargeBasis_tr = document.getElementById("chargeBasis_tr");

			if (v == '1') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				chargeStandard_tr.style.display = "none";
				chargeBasis_tr.style.display = "none";
			}
		}
		function downFile(id, fileType) {
			var url = "supPower!downloadStuff.do?itemId=" + id + "&fileType="
					+ fileType;
			document.location.href = url;
		}

		function deleteFile(id, fileType) {
			var url = "supPower!deleteStuff.do?itemId=" + id + "&fileType="
					+ fileType;
			document.location.href = url;
		}
	</script>


</body>
</html>
