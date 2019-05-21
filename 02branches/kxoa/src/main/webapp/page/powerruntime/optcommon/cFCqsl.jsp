<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript">
/*控件上传*/
	function onSubmit(file) {
		/* 	alert(file.target); */
		
		$("#stuffname").attr("value", file.stuffname);
		$("#sortId").attr("value", file.target);
		file.name = "stuffFile";
		form1.submit();
	}
	/*纸质文件触发事件*/
	function onSubmit2(che) {
		//alert(che.checked);
		if (che.checked) {
			$("#stuffname").attr("value", che.target);
			$("#sortId").attr("value", che.id);
			che.name = "stuffInfo.iszhi";
			//$("input[target='"+che.id+"']").attr("disabled","true");
			form1.submit();
		} else {
			//alert("xxx");
			/* 	$("#stuffname").attr("value",che.target); */
			$("#sortId").attr("value", che.id);
			che.name = "stuffInfo.iszhi";
			$("input[target='" + che.id + "']").removeAttr("disabled");
			form1.submit();
		}
	}
</script>
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
<body style="padding-left: 5px; width: 98%;">
	<s:hidden id="isAllsc" value="%{isAllsc}"></s:hidden>
	<script type="text/javascript">
		var is = $("#isAllsc").attr("value");
		var bt1 = window.parent.parent.document.getElementById('saveAndSubmit');

		var bt2 = window.parent.document.getElementById('saveAndSubmit');

		if (is == "0") {
			if (bt1 != null)
				bt1.disabled = "true";
			else {
				bt2.disabled = "true";
			}
		}
		if (is == "1") {

			if (bt1 != null)
				bt1.disabled = undefined;
			else {
				bt2.disabled = undefined;
			}
		}
		//	parentDocument.getElementById('itemFrameDiv').style.display = "";
	</script>

	<fieldset class="_new">
		<legend style="padding: 4px 8px 3px;">
			<b>材料上传</b>
		</legend>



		<s:form action="generalOperator!saveCFStuff.do" method="POST"
			enctype="multipart/form-data" name="form1" target="">
			<s:hidden name="stuffInfo.djId" value="%{stuffInfo.djId}"></s:hidden>
			<s:hidden name="stuffInfo.nodeInstId" value="%{stuffInfo.nodeInstId}"></s:hidden>
			<s:hidden id="stuffname" name="stuffInfo.stuffname"></s:hidden>
			<s:hidden id="sortId" name="stuffInfo.sortId"></s:hidden>
			<s:hidden id="groupid" name="stuffInfo.groupid"
				value="%{suppowerstuffinfo.groupId}" />

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
						<td align="center"><input type="checkbox" id="${obj.sortId}"
							target="${obj.stuffName}" iszhiname="stuffInfo.iszhi"
							onclick="onSubmit2(this)" value="1"></input></td>

						<td align="center"><c:if test="${obj.isNeed == '1'}">
							<p style="color: red">是</p>
						</c:if> <c:if test="${obj.isNeed == '0'}">
							<p style="color: red">否</p>
						</c:if></td>

						<td><c:if test="${optStuffs != null}">

								<c:forEach var="info" items="${optStuffs}">
									<c:if test="${info.sortId == obj.sortId}">
										<a style="text-decoration: none; color: blue;"
											href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${info.stuffid}">${info.filename}</a>
										<c:if test="${info.iszhi != '1'}">
											<a
												href="<%=request.getContextPath()%>/powerruntime/generalOperator!deleteCFStuffInfo.do?stuffInfo.stuffid=${info.stuffid}&iscf=1">删除</a>
											<br> <script type="text/javascript">
												var sid = '${obj.sortId}';
												$("#" + sid + "").attr(
														"disabled", "disabled");
											</script>
										</c:if>
									</c:if>
								</c:forEach>

							</c:if></td>

						<td width="50px"><s:file target="${obj.sortId}"
								stuffname="${obj.stuffName}" onchange="onSubmit(this);"></s:file></td>

					</tr>

					<c:if test="${optStuffs != null}">
						<c:forEach var="info" items="${optStuffs}">
							<c:if test="${info.sortId == obj.sortId}">
								<c:if test="${info.iszhi == '1'}">
									<script type="text/javascript">
										var id = '${obj.sortId}';
										$("#" + id + "")
												.attr("checked", "true");
										$("input[target='" + id + "']").attr(
												"disabled", "true");
									</script>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>


				</c:forEach>

			</table>
		</s:form>		
	</fieldset>
	<!-- <script type="text/javascript">
		var obj = window.parent.document.getElementById("basicsj");

		obj.height = document.body.scrollHeight;
	</script> -->
</body>
</html>
