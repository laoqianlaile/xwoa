<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<style type="text/css">
.viewTable {
	color: #000;
}

.viewTable th {
	text-align: left;
	text-indent: 10px;
	border: 1px solid #cdcdcd;
	height: 30px;
}
</style>
</head>
<body style="width: 98%; padding-left: 5px;">
<s:hidden id="isAllsc" value="%{isAllsc}"></s:hidden>
<script type="text/javascript">
	var is = $("#isAllsc").attr("value");
	var bt1 = window.parent.parent.document.getElementById('saveAndSubmit');
	var bt2 = window.parent.document.getElementById('saveAndSubmit');

	if (is == "0") {
		if (bt1 != null){
			$(bt1).hide();
// 			bt1.disabled = "true";	
		}
			
		else if (bt2 != null) {
			$(bt2).hide();
// 			bt2.disabled = "true";
		}
	}
	if (is == "1") {
		if (bt1 != null){
			$(bt1).show();
// 			bt1.disabled = undefined;
		}
		
		else if(bt2 != null){
			$(bt2).show();
// 			bt2.disabled = undefined;
		}
	}
	//	parentDocument.getElementById('itemFrameDiv').style.display = "";
</script>
<s:form action="generalOperator!saveStuff.do" method="POST"
	enctype="multipart/form-data" name="form1" target="">
	<s:hidden name="stuffInfo.djId" value="%{stuffInfo.djId}"></s:hidden>
	<s:hidden name="stuffInfo.nodeInstId" value="%{stuffInfo.nodeInstId}"></s:hidden>
	<s:hidden id="stuffname" name="stuffInfo.stuffname"></s:hidden>
	<s:hidden id="sortId" name="stuffInfo.sortId"></s:hidden>
	<s:hidden name="stuffInfo.groupid" value="%{stuffInfo.groupid}" />
	<input type="hidden" id="filetype" name="stuffInfo.filetype" />
	<input type="hidden" id="archivetype" name="stuffInfo.archivetype" />

	<table border="0" class="viewTable">
		<tr>
			<th>材料名称</th>
			<th width="70" align="center">是否纸质</th>
			<th width="70" align="center">是否必须</th>
			<th>已上传文件</th>
			<th>文件上传</th>
		</tr>
		<!-- ×××××××××××××××××××新的××××××××××××××××××××××××××××× -->
		<c:forEach var="obj" items="${suppowerstuffinfos}">
			<tr>
				<td>${obj.stuffName}</td>
				<td align="center"><input type="checkbox" id="${obj.sortId}" target="${obj.stuffName}" iszhiname="stuffInfo.iszhi" onclick="beforeSubmit2(this,'${obj.stuffType}','${obj.archivetype}')" value="1" /></td>
				<td align="center">
					<c:if test="${obj.isNeed == '1'}">
						<p style="color: red">是</p>
					</c:if>
					<c:if test="${obj.isNeed == '0'}">
						<p style="color: red">否</p>
					</c:if>
				</td>
				<td>
					<c:if test="${optStuffs != null}">
						<c:forEach var="info" items="${optStuffs}">
							<c:if test="${info.sortId == obj.sortId}">
							<c:if test="${info.iszhi eq '1'}">
							<xmp>${info.filename}</xmp>
							</c:if>
							<c:if test="${info.iszhi ne '1'}">
								<a style="text-decoration: none; color: blue;" title="${info.filename}"
									href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${info.stuffid}">
									<c:choose>
						<c:when test="${fn:length(info.filename) > 20}">
							<c:out value="${fn:substring(info.filename, 0, 20)}..." />
						</c:when>
						<c:otherwise><c:out value="${info.filename}" /></c:otherwise>
					</c:choose>
							</a>
			
									<!--根据流程配置决定是否可以删除 比较材料上传者和当前登录人员 -->
							     <c:if test="${empty isDelete || isDelete eq 'T' || uc eq info.uploadusercode   }">
									<a onclick='return confirm("是否确定删除？");'href="<%=request.getContextPath()%>/powerruntime/generalOperator!deleteStuffInfo.do?stuffInfo.stuffid=${info.stuffid}" style="color: red;">删除</a>
									</c:if>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
				</td>
				<td width="50px">
					<input type="file" target="${obj.sortId}" stuffname="${obj.stuffName}" onchange="beforeSubmit(this,'${obj.stuffType}','${obj.archivetype}');" />
					<s:fielderror />
				</td>
			</tr>
			<c:if test="${optStuffs != null}">
				<c:forEach var="info" items="${optStuffs}">
					<c:if test="${info.sortId == obj.sortId}">
						<c:if test="${info.iszhi eq '1'}">
							<script type="text/javascript">
								var id = '${obj.sortId}';
								$("#" + id + "").attr("checked", "true");
								$("input[target='" + id + "']").attr("disabled", "true");
							</script>
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
	</table>
</s:form>
<script type="text/javascript">
var init = null;
$(document).ready(function() {
	init = setInterval("FrameUtils.initialize_stuff(window, init)", MyConstant.initTimeForAdjustHeight);
	var errorFlag = "${param.errorFlag}";
	if(errorFlag==1){
		alert("文件上传太大，请不要超过20M");
	}
});
       
   
/*控件上传*/
function beforeSubmit(file, filetype, archivetype) {
	$("#stuffname").attr("value", file.attributes.stuffname.nodeValue);
	$("#sortId").attr("value", file.attributes.target.nodeValue);
	file.name = "stuffFile";
	$("#filetype").val(filetype);
	$("#archivetype").val(archivetype);
	form1.submit();
}
/*纸质文件触发事件*/
function beforeSubmit2(che, filetype, archivetype) {
	if (che.checked) {
		$("#stuffname").attr("value", che.target?che.target:$(che).attr("target"));
		$("#sortId").attr("value", che.id);
		che.name = "stuffInfo.iszhi";
		$("#filetype").val(filetype);
		$("#archivetype").val(archivetype);
		form1.submit();
	} else {
		$("#sortId").attr("value", che.id);
		che.name = "stuffInfo.iszhi";
		$("input[target='" + che.id + "']").removeAttr("disabled");
		$("#filetype").val(filetype);
		$("#archivetype").val(archivetype);
		form1.submit();
	}
}
</script>
</body>
</html>
