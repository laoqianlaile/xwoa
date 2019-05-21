<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrow.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
</head>

<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form method="post" namespace="/dispatchdoc" action="dispatchDoc"
		id="dispatchDocForm">

		<input type="hidden" id="roleCode" name="roleCode" value="zbcshq" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="optId" name="optBaseInfo.optId"
			value="${object.optBaseInfo.optId}" />
		<input type="hidden" id="wfcode" name="optBaseInfo.flowCode"
			value="${object.optBaseInfo.flowCode}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId"
			value="${nodeInstId}">
		<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId"
			value="${flowInstId}" />
		<input type="hidden" id="powerid" name="optBaseInfo.powerid"
			value="${object.itemId}" />

		<input type="hidden" id="internalNo" name="internalNo"
			value="${object.internalNo}" />
		<%-- 	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding: 4px 8px 3px;">
				<b>${object.optBaseInfo.powername}拟文</b>
			</legend> --%>
		<fieldset class="_new">
			<legend>发文信息</legend>
			<!-- 
			<div class="legend_left"></div>
			<div class="legend">
				<b>发文信息</b>
			</div>
			<div class="legend_right"></div>
			 -->
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">文件标题</td>
					<td align="left" colspan="3"><c:out
							value="${object.dispatchDocTitle}" /></td>
				</tr>
				<c:if test="${not empty object.dispatchDocNo}">
				<tr>
					<td class="addTd">发文号</td>
					<td align="left" colspan="3"><c:out
							value="${object.dispatchDocNo}" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="addTd">发文代<span style="font-family:Simsun !important">字</span></td>
					<td align="left" width="33%"><c:forEach var="row"
							items="${cp:DICTIONARY('WJLX')}">
							<c:if test="${object.dispatchFileType eq row.key}">
								<c:out value="${row.value}" />
							</c:if>
						</c:forEach></td>
					<td class="addTd">是否处室会签文</td>
					<td align="left" width="33%">${cp:MAPVALUE("YES_NO", object.isCountersign)}
					</td>
				</tr>

				<c:if test="${object.isCountersign eq '1'}">
					<tr>
						<td class="addTd">会签处室</td>
						<td align="left" colspan="3"><c:out value="${selUnitName}" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="addTd">是否信息公开</td>
					<td align="left">${cp:MAPVALUE("FW_CAN_OPEN", object.dispatchCanOpen)}</td>
					<td class="addTd">紧急程度</td>
				 	<td align="left">${cp:MAPVALUE("critical_level", object.criticalLevel)}</td>
				<%--	<td class="addTd">密级</td>
					<td align="left">${cp:MAPVALUE("FW_SECRETS_LEVEL", object.secretsDegree)}</td>
				 --%></tr>

				<tr>
					<td class="addTd">文件摘要</td>
					<td align="left" colspan="3"><c:out
							value="${object.dispatchDocSummary}" /></td>
				</tr>
				<tr>
					<td class="addTd">经办人</td>
					<td align="left"><c:out value="${object.draftUserName}" /></td>
					<td class="addTd">拟文日期</td>
					<td align="left"><fmt:formatDate value='${object.draftDate}'
							pattern='yyyy-MM-dd' /></td>
							
							
					<!-- 					<td class="addTd">公文种类 -->
					<!-- 					</td> -->
					<!-- 					<td align="left"> -->
					<%-- 					${cp:MAPVALUE("FW_DOC_TYPE", object.dispatchDocType)}</td> --%>
				</tr>
				<!-- 				<tr> -->
				<!-- 					<td class="addTd">签发人 -->
				<!-- 					</td> -->
				<!-- 					<td align="left"> -->
				<%-- 					<c:out value="${object.dispatchUser}" /></td> --%>
				<!-- 					<td class="addTd">发文范围 -->
				<!-- 					</td> -->
				<!-- 					<td align="left"> -->
				<%-- 					<c:out value="${object.dispatchRander}" /></td>
								</tr>
				<tr>
					<!-- 					<td class="addTd">发文字号 -->
					<!-- 					</td> -->
					<!-- 					<td align="left"> -->
					<%-- 					<c:out value="${object.dispatchword}" /></td> --%>
				</tr>
				
				
				<tr>
					<td class="addTd">印数(汉)</td>
					<td align="left">${object.dispatchCopies}</td>
					<td class="addTd">印数(维)</td>
					<td align="left">${object.dispatchcopiew}</td>
				</tr>
				<tr>
					<td class="addTd">主题词</td>
					<td align="left"><c:out
							value="${object.dispatchTitle}" /></td>
					<td class="addTd">主送单位</td>
					<td align="left"><c:out
							value="${object.mainNotifyUnit}" /></td>
				</tr>
			
				<tr>
					<td class="addTd">抄送单位</td>
					<td align="left" colspan="3"><c:out
							value="${object.otherUnits}" /></td>
				</tr>
					<c:if test="${not empty object.dispatchUser}">
				<tr>
					<td class="addTd">签发人</td>
					<td align="left">${cp:MAPVALUE("usercode", object.dispatchUser)}</td>
					<td class="addTd">发文时间</td>
					<td align="left"><fmt:formatDate value='${object.dispatchdate}'	pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>	
				</c:if>
				<tr>
				<td class="addTd">业务状态</td>
					<td align="left" colspan="3">${cp:MAPVALUE("oa_bizstate", object.optBaseInfo.bizstate)}</td>
				</tr>
			</table>
		</fieldset>
	</s:form>
</body>
<script type="text/javascript">
	var canAdjustHeight = true;
	var flowInstId = "";
	var unitString = "${unitString}";
	
	function getOptBaseInfoJson() {
		return ${optCommonBizJson};
	}

	function adjustHeight() {
		if (canAdjustHeight) {
			if (window.parent.document.getElementById("editFrame")) {
				window.parent.document.getElementById("editFrame").style.height = document.body.scrollHeight + "px";
			}
			
			if (window.parent.document.getElementById("d_000_shadow")) {
				window.parent.document.getElementById("d_000_shadow").style.height = window.parent.document.body.scrollHeight + "px";
			}
		}
	}
	

	function checkUnionDispatchUnits() {
		if ("1" == $("#isUnionDispatch").val()) {
			$("#unionDispatchUnitsTr").show();
		} else {
			$("#unionDispatchUnitsTr").hide();
		}
		adjustHeight();
	}
	
	function checkCountersignUnits() {
		if ("1" == $("#isCountersign").val()) {
			$("#countersignUnitsTr").show();
		} else {
			$("#countersignUnitsTr").hide();
		}
		adjustHeight();
	}

	function checkIsUnionOther() {
		if ($("#isUnionOther").attr("checked")) {
			$("#isUnionOther").parent().parent().next().show();
		} else {
			$("#isUnionOther").parent().parent().next().hide();
		}
		adjustHeight();
	}
	
	
	$(document).ready(function() {
		checkIsUnionOther();
		if ($("div[id^='relative_']").length > 0) {
			$("#isUnionOther").attr("checked", true);
			$("#isUnionOther").parent().parent().next().show();
		}
		
		checkUnionDispatchUnits();
		checkCountersignUnits();
		checkIsUnionOther();
		
		$("#saveBtn,#submitBtn").click(function() {
			var id = $(this).attr("id");
			if ("saveBtn" == id) { // 保存
				if (doCheck()) {
					//cloneProjectInfo();
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveDispatchDoc.do");
					$("#dispatchDocForm").submit();
				}
			} else if ('submitBtn' == id) { // 提交
				if ("" == $("#curTemplateId").val()) {
					alert("请选择正文模板");
					return false;
				}
				if ($("#curTemplateId").val() != $("#recordId").val()) {
					alert("当前正文与选择模板不一致，你保存当前模板所对应的正文");
					return false;
				}
				if ($("#powerid").val() == 'SD370000FG-GW-0008') {
					if ("" == $("#sqTemplateId").val()) {
						alert("请保存送签说明书");
						return false;
					}
				}
				if (doCheck()) { // 校验通过
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveAndSubmitDispatchDoc.do");
													$("#dispatchDocForm")
															.submit();
												}
											}

											return false;
										});

						$("#addCountersignUnits")
								.click(
										function() {
											JDialog
													.open({
														src : "${pageContext.request.contextPath}/dispatchdoc/ioDocTasksExcute!selectOrganize.do?roleCode=zbcshq",
														title : "选择机构",
														width : 408,
														height : 340,
														scrolling : "no",
														oDocument : window.parent.document
													});
										});
					});

	$("#orgName").click(
			function() {
				var d = '${unitsJson}';
				$('#attAlert').show();
				if (d.trim().length) {
					selectPopWinTemp(jQuery.parseJSON(d), $("#orgName"),
							$("#orgCode"));
				}
				;
			});

	$("#mainNotifyUnitName,#otherUnitNames")
			.click(
					function() {
						var id = $(this).attr("id");
						var selectType;
						if ("mainNotifyUnitName" == id) {
							selectType = "radio";
						} else if ("otherUnitNames" == id) {
							selectType = "checkbox";
						} else {
							return false;
						}

						var frameId = window.frameElement.id;
						JDialog
								.open({
									src : "${pageContext.request.contextPath}/dispatchdoc/applyUnitInfo!selectList.do?JDialogLink=yes&selectType="
											+ selectType
											+ "&frameId=dispatchDocForm",
									width : 800,
									height : 350,
									oDocument : window.document
								});

						return false;
					});

	function selectPopWin(json, o1, o2) {
		new person(json, o1, o2).init();
		setAlertStyle("attAlert");
	}
	function selectPopWinTemp(json, o1, o2) {
		new person(json, o1, o2).init();
		setAlertStyle("attAlert");
	}

	function refreshDocRelativeArea() {
		$
				.ajax({
					type : "POST",
					url : "${contextPath}/dispatchdoc/dispatchDoc!getDocRelatives.do?dispatchNo="
							+ $("#djId").val(),
					dataType : "json",
					success : function(data) {
						if ("success" == data.status) {
							var buffer = new StringBuffer();
							var array = data.jsonData;
							for (var i = 0; i < array.length; i++) {
								var obj = array[i];
								buffer
										.append("<div id='relative_" + obj["dId"] + "'><input type='hidden' id='incomeNo_"
												+ obj["dId"]);
								buffer.append("' name='selIncomeNo' value='"
										+ obj["dId"] + "' /> &nbsp;&nbsp; ");
								buffer.append((obj["tName"] ? obj["tName"]
										: "无标题")
										+ " ["
										+ (obj["tNo"] ? obj["tNo"] : "无文号"));
								buffer
										.append("]<a href='#' onclick='deleteIncomeNo(\""
												+ obj["dId"] + "\");'>");
								buffer
										.append("<img border='0' src='../images/close.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;</div>");
							}
							$("#unionOthersArea").empty();
							$(buffer.toString()).appendTo("#unionOthersArea");
							FrameUtils.adjustParentHeight(window);
						} else {
							alert("刷新收文关联列表失败！");
						}
					},
					error : function() {
						alert("刷新收文关联列表失败！");
					}
				});
	}
</script>
</html>