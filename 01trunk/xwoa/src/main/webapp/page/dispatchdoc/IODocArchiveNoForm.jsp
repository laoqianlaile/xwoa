<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html >
<head>
<title>置文号</title>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="sub-flow">
	<s:form action="ioDocArchiveNo" namespace="/dispatchdoc" method="post" id="ioDocArchiveNoForm" target="_parent">
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}" />
 		<input type="hidden" id="orgCode" name="orgCode" value="${orgCode}" />
		<input type="hidden" id="djId" name="djId" value="${djId}" />
		<input type="hidden" id="flowPhase" name="flowPhase" value="${flowPhase}" />
		<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}" />
		<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
		<input type="hidden" name="ideacode" value="T" />
		
		<div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">办理信息</div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
         </div>
			
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">置文号<font color="red">*</font></td>
					<td style="width: 120px;">发文代字
						<div>
							<select id="wjlx" name="wjlx" onchange="getNewFwh(1);">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${dictionaryZWH}">
									<option value="${row.key}"
										<c:if test="${row.key==wjlx}">selected</c:if>>
										<c:out value="${row.value}" /></option>
								</c:forEach>
							</select>
						</div>
					</td>
					<td style="width: 70px;">年份
						<div>
							<select id="lshYear" name="lshYear" onchange="getNewFwh(2);" style="width: 65px;">
								<c:forEach var="option" items="${yearOptions}">
									<option value="${option.value}" ${option.value eq object.lshYear ? "selected" : ""}>
										<c:out value="${option.label}" />
									</option>
								</c:forEach>
							</select>
						</div>
					</td>
					<td style="width: 160px;">流水号
						<div>
							<input type="text" id="lsh" name="lsh" maxlength="6" value="${object.lsh}" size="6"
								onchange="getNewFwh(3);" />
							<!-- <input type="button" id="btn_reserved" name="btn_reserved" class="btn" value="预留号" /> -->
						</div>
					</td>
					<td>文号
						<div>
							<input type="text" id="fwh" name="fwh" style="width:60%" value="${object.fwh}"/>
						</div>
					</td>
				</tr>
				
				<!-- 短信提醒开关 -->
				    <c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen')}">	
				    <tr>
				    	<td class="addTd">短信提醒</td>					    
				    	<td  colspan="4">
						<input id="isSendMsgView" class="checkboxClass" type="checkbox"
								name="isSendMsgView" />是否短信提醒下一步操作人员
						<input type="hidden" id="isSendMessage" name="isSendMessage" />
							
						</td>
					</tr>
					</c:if>
				
				<c:if test="${not empty moduleParam.ideaContent}">
				<tr>
						<td class="addTd">${moduleParam.ideaContent}<c:if
							test="${moduleParam.btIdea ne 'F'}">
					<font
						color="red">*</font></c:if></td>
						<td align="left"  colspan="4">
							 <textarea name="transcontent" cols="" rows="" id="transcontent" style="width:100%; height:40px;"></textarea>
							<span id="transcontentMessage">&nbsp;</span></td>
					</tr> 
				</c:if>
				
				
				<tr>
				 	<td class="addTd">修改正文</td>
				 	<td align="left" colspan="4">
						<span id="zwedit"> <span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="editdc" onclick="updtDoc();"
									value="修改正文" class="btn processBtn" /></span> 
						 <script type="text/javascript">
											var textt = $.ajax({
														url: "${contextPath}/dispatchdoc/dispatchDoc!getDispatchDocTitleByDjId.do?djId=${djId}",
														async: false
													}).responseText;
											document.getElementById("fwname").innerHTML = textt + ".doc";
						</script>
				  </td>			
				</tr> 	
				
				
				
				
				 <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="5" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提示：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>
			</table>
		<center style="margin-top: 10px;display:none;" >
			<input type="button" id="submitBtn" name="submitBtn" class="btn" value="发 送" />
<%-- 			<s:submit id="submitBtn" name="submitBtn" method="submitOpt" cssClass="btn" value="发 送" /> --%>
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn" method="suspendNodeInstance" cssClass="btn" value="暂 缓" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<s:submit id="rollBackBtn" name="rollBackBtn" method="rollBackOpt" cssClass="btn" value="回 退" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow" cssClass="btn" value="办 结" />
			</c:if>
			<input type="button" id="saveBtn" name="saveBtn" class="btn" value="保 存" />
<%-- 			<s:submit id="saveBtn" name="saveBtn" method="saveIODocArchiveNoInfo" cssClass="btn" value="保 存" /> --%>
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>    
	</s:form>
</body>
<script type="text/javascript">
var fwh = "";
var canSubmit = true;
var _get = function (id) {
	return document.getElementById(id);
};
//字符空格处理
var trim = function (str) {
	return str.replace(/^\s+|\s+$/g, "");
};
$(document).ready(function() {
	fwh = $("#fwh").val();
});

function getNewFwh(type, newLsh) {
	canSubmit = false;
	
	var url = "${contextPath}/dispatchdoc/ioDocArchiveNo!afterChange.do?djId=" + $("#djId").val()
				+ "&wjlx=" + $("#wjlx").val() + "&lshYear=" + $("#lshYear").val() + "&orgCode=" + $("#orgCode").val();
	if (3 == type) { // 仅当流水号手动改变或者使用预留文号的时候，才传入到后台
		if (newLsh) {
			url += "&usedReserved=yes&lsh=" + newLsh;
		} else {
			url += "&lsh=" + $("#lsh").val();
		}
	}
	
	$.ajax({
		type : "POST",
		url : url,
		dataType : "json",
		success : function(data) {
			if ("success" == data.status) {
				if (newLsh && $("#lsh").val(newLsh));
				if (data.lsh) {
					if (3 == type) {
						alert("流水号 " + $("#lsh").val() + " 已经被占用，自动调整流水号为 " + data.lsh + " 。");
					}
					$("#lsh").val(data.lsh);
				}
				$("#fwh").val(data.fwh);
				fwh = data.fwh;
				canSubmit = true;
			} else if ("unavailable" == data.status) {
				alert("所选的预留文号已经被使用或删除，请重新设置流水号");
			} else if ("" == $("#wjlx").val()) {
				alert("请先选择发文代字。"); 
			} 
			
			else {
				alert("获取新的文号发生错误");
			}
		},
		error : function() {
			alert("获取新的文号发生错误");
		}
	});
}

$("#btn_reserved").click(function() {
	JDialog.open({
		src : "${contextPath}/dispatchdoc/ioDocArchiveNo!useZwhReserved.do?orgCode=" + $("#orgCode").val() 
				+ "&wjlx=" + $("#wjlx").val() + "&lshYear=" + $("#lshYear").val() + "&lsh=" + $("#lsh").val(),
		width : 800,
		height : 350,
		oDocument: window.parent.document
	});
});

$("#saveBtn,#submitBtn").click(function() {
	if (!canSubmit) {
		return false;
	}
	if('${moduleParam.btIdea}' != 'F' &&trim(_get('transcontent').value).length==0){
		alert("${moduleParam.ideaContent}不能为空。");
		openLoadIng(false);
		_get('transcontent').focus();
		return false;
	}
	
	var id = $(this).attr("id");
	var url = "${contextPath}/dispatchdoc/ioDocArchiveNo!saveIODocArchiveNoInfo.do"
	var djid=$("#djId").val();
	var wjlx=$("#wjlx").val();
	var lshYear=$("#lshYear").val();
	var lsh= $("#lsh").val();
	var fwh=$("#fwh").val();
	var orgCode=$("#orgCode").val();
	$.ajax({
		type : "POST",
		url : url,
		data:{"djId":djid,"wjlx":wjlx,"lshYear":lshYear,"lsh":lsh,"fwh":fwh,"orgCode":orgCode},
		dataType : "json",
		success : function(data) {
			if ("success" == data.status) {
				if ("saveBtn" == id) {
					alert("保存成功");
				} else {
					$("#ioDocArchiveNoForm").attr("action", "ioDocArchiveNo!submitOpt.do");
					$("#ioDocArchiveNoForm").submit();
				}
			} else if ("unavailable" == data.status) {
				alert("所选的文号已经被使用，请重新设置流水号");
			} else {
				alert("保存文号信息失败");
			}
		},
		error : function() {
			alert("保存文号信息失败");
		}
	});
});

$('#isSendMsgView').click(function(){
	var $this = $(this);
	if($this.attr('checked') == 'checked'){
		$('#isSendMessage').val('T');
	}else{
		$('#isSendMessage').val('F');
	}
});


function updtDoc(archiveType){
	debugger;
	var nodeCode = $("#nodeCode").val();
	var archiveType = 'zw';
	var curTemplateId = "null";
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var flowStep = "ZW_EDIT";
	flowStep = getFlowStepByArchiveType(flowStep, archiveType);
	//清稿阶段--印刷
	if(nodeCode=='ys'){
		flowStep = "PRINT_EDIT";
	}
	var param = "flowStep=" + flowStep + "&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType+"&fileStyle=2"+ "&nodeCode=" + $("#nodeCode").val();
	openNewWindow(uri + "?"+ param,'editForm','');
}

function getFlowStepByArchiveType(flowStep, archiveType) {
	if ("sl" == archiveType) {
		flowStep = "SL_UP";
	} else if ("nsl" == archiveType) {
		flowStep = "NSL_UP";
	} else if ("bz" == archiveType) {
		flowStep = "BZ_UP";
	}
	return flowStep;
}


</script>
</html>