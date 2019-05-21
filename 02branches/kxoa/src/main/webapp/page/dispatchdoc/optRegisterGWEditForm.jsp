<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="办（阅办）件登记" />
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="incomeDoc" method="post" namespace="/dispatchdoc"  target="_parent" onsubmit="return doSubmitCheck();" >
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="optId" name="optId" value="${optId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
			<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId" value="${flowInstId}" />	
			<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
							
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
					<legend style="padding:4px 8px 3px;">
						<b>${object.optBaseInfo.powername}登记</b>
					</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
										<tr>
							<td class="addTd">
								文件标题<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocTitle" name="incomeDocTitle" maxlength="200" value="${object.incomeDocTitle}" style="width: 100%;height: 24"/>
							</td>
							</tr>
						<tr>						
							<td class="addTd">
								阅办文编号
							</td>
							<td align="left">
							${object.optBaseInfo.acceptArchiveNo}
							</td>	
								<td class="addTd">
								阅办文日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<input type="text" class="Wdate" 
							style="width: 200px;height:25px; border-radius: 3px; border: 1px solid #cccccc;"
							 value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							id="incomeDate" name="incomeDate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
							</td>						
						</tr>
						<tr>
							<td class="addTd">
								拟发文机关<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="incomeDeptName" name="incomeDeptName" maxlength="200" value="${object.incomeDeptName}" style="width: 100%;height: 24"/>
							</td>
								<td class="addTd">
								拟发文字号<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" style="width: 100%;height:25px;"/>
							</td>
					
						</tr>
							<tr>
							
							<td class="addTd">
								文书分类
							</td>
							<td align="left">
								${cp:MAPVALUE("incomeDocType",object.incomeDocType)}
							</td>
								<td class="addTd">
								来文单位分类
							</td>
							<td align="left">
								<c:if test="${object.incomeDocType eq 'SWXZ' }">
					${cp:MAPVALUE("SWXZ", object.incomeDeptType)}	
					</c:if>		
					<c:if test="${object.incomeDocType eq 'SWDW' }">
					${cp:MAPVALUE("SWDW", object.incomeDeptType)}	
					</c:if>	
								</td>
						</tr>
						
		<tr>
<%-- 							<td class="addTd">
								密级<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<select id="secretDegree" name="secretDegree" style="width: 200px;height:25px;">
										<c:forEach var="row" items="${cp:DICTIONARY('FW_SECRETS_LEVEL')}">
										<option value="${row.key}" ${(object.secretDegree eq row.key or (empty object.secretDegree and row.key eq '0')) ? 'selected = "selected"' : ''}>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
							</td> --%>
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" colspan="3">
						<select id="criticalLevel" name="criticalLevel" style="width: 200px;height:24px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}">
								<option value="${row.key}" ${(object.criticalLevel eq row.key or (empty object.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>							
						</tr>
					
					</table>
				</fieldset>			
				
		<br/><br/>
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=143"
				onload="this.height=window.frames['basicsj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="saveAndSubmitGW" cssClass="btn" value="发 送" />
			<s:submit id="saveBtn" name="saveBtn" method="saveGW" cssClass="btn" value="保 存" />
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>
		</s:form>
	</body>
	<script type="text/javascript">		
	
	function editShip(djId, no) {
		JDialog.open({
			src : "${contextPath}/dispatchdoc/incomeDoc!editShip.do?djId=" + djId + "&ship.no=" + (no ? no : ""),
			width : 800,
			height : 350
		});
	}

	function deleteShip(self, djId, no) {
		var result = $.ajax({
			url: "${contextPath}/dispatchdoc/incomeDoc!deleteShip.do?djId=" + djId + "&ship.no=" + no,
			async: false
		}).responseText;
		if ("success" == result) {
			$(self).parent().parent().remove();
		} else {
			alert("删除失败");
		}
	}
			function doSubmitCheck(subType) {	
			/* if ("" == $.trim($("#itemId").val())) {
					alert("请选择权力事项！");
					$('#itemId').focus();
					return false;
				} */
				if ("" == $.trim($("#incomeDocTitle").val())) {
					alert("文件标题不可为空！");
					$('#incomeDocTitle').focus();
					return false;
				}
				if ("" == $.trim($("#incomeDeptName").val())) {
					alert("拟发文机关不可为空！");
					$('#incomeDeptName').focus();
					return false;
				}
				if ("" == $.trim($("#incomeDocNo").val())) {
					alert("拟发文字号不可为空！");
					$('#incomeDocNo').focus();
					return false;
				}
				if ("" == $.trim($("#incomeDate").val())) {
					alert("阅办文日期不可为空！");
					$('#incomeDate').focus();
					return false;
				}
				
				
				return true;
				}
					</script>
</html>

