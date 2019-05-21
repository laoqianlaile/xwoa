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
<body class="sub-flow">
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">会议资料</div>
	     <div class="title-split-line"></div>
   </div>
	<fieldset class="form">
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="oaMeetingmaterial" method="post" namespace="/oa"
			id="oaMeetingmaterialForm">
			<input type="button" class="btn" value="返回" onclick="avascript:history.go(-1)">
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="meetingAttendeesCodes"
				name="meetingAttendeesCodes" value="${object.meetingAttendeesCodes}" />
			<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
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
				
				
				
			<%-- 	<tr>
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
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:100px;font-weight: bold;width: 100px;"><s:text
							name="" />会议附件</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">
						<div>
							<span style="width: 100%; position: relative;">
								<c:if test="${not empty docAttachments}">
									<c:forEach var="docFile" items="${docAttachments}">
									<a style="text-decoration: underline; color: blue;" title="${docFile.filename}" onclick="downFile('${docFile.stuffid}');return false;">
											${docFile.filename}</a>&nbsp;&nbsp;
									</c:forEach>
								</c:if>  
							</span>
						</div>
					</td>
				</tr>
			</table>
<%-- 				<div>
					<span style="width: 100%; position: relative; padding-left: 100px;"><font
						color="blue"><strong>会议附件</strong></font>:
						<c:if test="${not empty docAttachments}">
							<c:forEach var="docFile" items="${docAttachments}">
							<a style="text-decoration: underline; color: blue;" title="${docFile.filename}"
									href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${docFile.stuffid}">
									${docFile.filename}</a>&nbsp;&nbsp;
							</c:forEach>
						</c:if>  
						</span>
				</div> --%>
		</s:form>
	</fieldset>
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
				<ec:column property="meetingAttendee" title="参会人员" style="text-align:center">
				${cp:MAPVALUE('usercode',doc.meetingAttendee)}
				</ec:column>
				<ec:column property="initalStuffId" title="原始附件" style="text-align:center">
				<a style="text-decoration: underline; color: blue;" title="${docFile.filename}" onclick="downFile('${doc.initalStuffId}');return false;">
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
		window.location.href='${pageContext.request.contextPath}/oa/oaMeetingApply!view.do';
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

</script>