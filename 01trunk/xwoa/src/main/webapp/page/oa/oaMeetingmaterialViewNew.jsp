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
	     <div class="title-name">议程</div>
	     <div class="title-split-line"></div>
   </div>
	<fieldset class="form">
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="oaMeetingApply" method="post" namespace="/oa"
			id="oaMeetingApplyForm">
			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span></h2>
			<input type="button" class="btn" value="返回" onclick="window.close();">
			<input type="hidden" id="mId" name="mId" value="${object.mId}" />
			<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
				<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						议程名称				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${object.meetingName}
					</td>
										
				</tr>
				 <tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						汇报人		
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;width:25%;" >
						${object.reportName}
					</td>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:10%;">
						序号		
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;width:25%;" >
						${object.orderId}
					</td>
				</tr>
				<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						参与人员				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${object.meetingAttendees}
					</td>
										
				</tr>
				<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						参会部门				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${object.meetingUnitnames}
					</td>
										
				</tr>
				<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						外来人员				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${object.meetingComein}
					</td>
										
				</tr>
				<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						外来单位				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${object.meetingComeindeps}
					</td>
										
				</tr>
					<tr>
					<td style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:80px;font-weight: bold;width:15%;">				
						附件材料				
					</td>
					<td style="font-size: 14px;text-align:center;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						<c:if test="${ not empty docAttachments}">
							<c:forEach var="stuff" items="${docAttachments}">
						 		<c:if test="${stuff.iszhi=='1'}"><a href="javascript:void(0)">${stuff.filename}</a></c:if>
		          				<c:if test="${stuff.iszhi!='1'}"><a href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${stuff.stuffid}">${stuff.filename}</a></c:if></br>
						 </c:forEach>
						</c:if>
					</td>
										
				</tr>
					
				
			</table>
 			
		</s:form>
	</fieldset>
	<br>
	
	</body>
<script>
	function info(meetingAttendee,djId) {
		var url ="<%=request.getContextPath()%>/oa/oaMeetingmaterialinfos!meetingDownFile.do?meetingAttendee="+ meetingAttendee+"&djId="+ djId;
		DialogUtil.open("会议材料附件查看",url,600,400);
	}

	//返回按钮
	function renturns(){
		window.location.href='${pageContext.request.contextPath}/oa/oaMeetingApply!list.do';
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