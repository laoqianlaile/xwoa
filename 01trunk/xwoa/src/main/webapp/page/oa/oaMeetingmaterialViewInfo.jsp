<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaMeetingmaterial.edit.title" /></title>
  <style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     .group-title{position:relative;height:22px;}
	.group-title div{position:absolute;height:22px;top:0;line-height:22px;font-size:16px;font-weight:bold}
	.group-title .title-ico{width:5px;background:#56b9fd;z-index:1;left:0;}
	.group-title .title-name{z-index:1;left:5px;background:#fff;padding:0 10px;}
	.group-title .title-split-line{width:100%;z-index:0;left:0;padding-top:0px;position:relative;height: 22px; 
				background: url("${ctxStatic}/image/special-title-line.png") repeat-x center ;}
	span.span_state{float: right;margin: 5px 30px;}
	span.span_state1{float: left;margin: 5px 30px;}
	span.span_state a{color:#000;cursor:pointer;font-size:14px}
	span.remindIco{background-position: center;display: inline-block;background-repeat:no-repeat;width: 26px; height: 26px}
	span.remindIco-overdue{background-image: url("${ctxStatic}/image/ycqclock.gif")}
	span.remindIco-toOverdue{background-image: url("${ctxStatic}/image/jjcqclock.gif")}
	span.remindIco-none{display:none}
  </style>
</head>
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
<body>
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">会议资料</div>
	     <div class="title-split-line"></div>
   </div>
   <div class="form-container">
  
	<fieldset class="form">
	 <h2 style="color:red;font-family: Microsoft YaHei;text-align:center">${meetingName}</h2>
		<s:form action="oaMeetingmaterial" method="post" namespace="/oa"
			id="oaMeetingmaterialForm">
			<input type="button" class="btn" value="返回" onclick="history.back(-1)">
			<s:submit id="saveProxy" method="saveProxy" style="display: none;" cssClass="btn" key="opt.btn.save" />&nbsp;
			<input type="button" class="btn" value="选择代会人员" onclick="showgaoji()">
			<%-- <div id="tr_notOpenReason" style="display: none;">
						代会人员：<input type="text" readonly="readonly" id="meetingProxys" name="meetingProxys" 
							value="${object.meetingProxys }"  class="focused" style="width:80%;height:30px;cursor:pointer" > 
						<input id="meetingProxyCodes" type="hidden" name="meetingProxyCodes"
						 	value="${object.meetingProxyCodes }" />
			</div> --%>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
				<tr id="tr_notOpenReason" style="display: none;">
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">代会人员</td>
					<td colspan="" style="border:1px #e8e8e8 solid;padding-left:10px;">
					<input type="text" readonly="readonly" id="meetingProxys" name="meetingProxys" 
							value="${object.meetingProxys }"  class="focused" style="width:80%;height:30px;cursor:pointer" > 
						<input id="meetingProxyCodes" type="hidden" name="meetingProxyCodes"
						 	value="${object.meetingProxyCodes }" />
					</td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">序号</td>
					<td colspan="" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{orderId}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">议程名称</td>
					<td colspan="" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingName}" /></td>
				</tr>
				<%-- <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;"><s:text name="oaMeetingmaterial.meetingTime" /></td>
					<td colspan="" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="meetingTime"
						format="yyyy-MM-dd HH:mm" /></td>
				</tr> --%>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingAttendees" /></td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingAttendees}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">汇报人</td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{reportName}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">参会单位</td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingUnitnames}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来人员</td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingComein}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来部门</td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingComeindeps}" /></td>
				</tr>
				
				<%-- <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingUnitnames" /></td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingUnitnames}" /></td>
					</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingComein" /></td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingComein}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingComeindeps" /></td>
					<td colspan="2" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingComeindeps}" /></td>
					
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:100px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingAgenda" /></td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingAgenda}" /></td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:100px;font-weight: bold;width: 100px;"><s:text
							name="oaMeetingmaterial.meetingRemark" /></td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingRemark}" /></td>
				</tr> --%>
			</table>
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
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
		</s:form>
	</fieldset>
	</div>
	<br>
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">原始附件</div>
	     <div class="title-split-line"><span></span></div>
   </div>
	<fieldset>
		<div class="improvecustom" style="height: 92%;">
			<ec:table items="oaMeetingmaterialinfos" var="doc" sortable="false"
			showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			tableId="uu">
			<ec:row>
				<ec:column property="initalStuffId" title="原始附件" style="text-align:center">
				<a style="text-decoration: underline; color: blue;" title="${docFile.filename}"  onclick="downFile('${doc.initalStuffId}');return false;">
					${doc.optStuffInfoBeg.filename}</a>
				</ec:column>
				<ec:column property="stuffId" title="签阅后附件" style="text-align:center">
				<c:if test="${doc.isback eq 'T' and  not empty doc.remark }">
				<a style="text-decoration: underline; color: blue;" title="${docFile.filename}" onclick="downFile('${doc.cid.stuffId}');return false;">
					${doc.optStuffInfoEnd.filename}</a>
				</c:if>
				</ec:column>
				<ec:column property="backtime" title="提交时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date">
				</ec:column>
				<ec:column property="remark" title="会议记录内容" style="text-align:center"/>
			</ec:row>
		</ec:table>
		</div>
	</fieldset>
	<script>
function info(meetingAttendee,djId) {
	var url ="<%=request.getContextPath()%>/oa/oaMeetingmaterialinfos!meetingDownFile.do?meetingAttendee="+ meetingAttendee+"&djId="+ djId;
	DialogUtil.open("会议材料附件查看",url,600,400);
}

//返回按钮
function renturns(){
	window.location.href='${pageContext.request.contextPath}/oa/oaMeetingmaterial!viewList.do';
}

//点击触发下载文件
function downFile(id) {
	$.ajax({
		type : "POST",
		async : false,
		dataType : "json",
		url : "${ctx}/powerruntime/generalOperator!fileNull.do?stuffInfo.stuffid=" + id,
		success : function(json) {
			//alert(1);
			var url1 = "${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=" + id;
			document.location.href = url1;
		},
		error : function() {
			alert("系统找不到此文件！");			
		}
	});
}
function showgaoji(){
	
	$("#tr_notOpenReason").show();
	$("#saveProxy").show()
}
$(function(){
	$("#meetingProxys").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#meetingProxys"),
							$("#meetingProxyCodes"));
				}
				;
			});
});

function selectPopWin(json, o1, o2, oShow) {
	new treePerson(json, o1, o2, oShow).init();
	setAlertStyle("attAlert");
}
</script>