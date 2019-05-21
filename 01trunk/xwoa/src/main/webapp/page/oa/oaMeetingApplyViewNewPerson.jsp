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
		<s:form action="oaMeetingApply" method="post" namespace="/oa"
			id="oaMeetingApplyForm">
			<%-- <h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${object.meetApplyName}</h2> --%>
			<input type="button" class="btn" value="返回" onclick="history.back(-1);">
			<input type="hidden" id="mId" name="mId" value="${object.mId}" />
			<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议名称</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">${object.meetApplyName}</td>
				</tr>

				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议地点</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;">${object.meetApplyAddress}</td>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议时间</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="object.meetApplytime" format="yyyy-MM-dd HH:mm" /></td>
				</tr>
				
					<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">参会领导</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">	${object.attendLeaderName}</td>
				</tr>
				
				<%-- <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">参会单位</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">	${object.attendUnitName}</td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来人员</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">${object.foreigUserName}</td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来部门</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">		${object.foreigUnitName}</td>
				</tr> --%>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">备注</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">${object.meetingRemark}</td>
				</tr>
				
				<c:if test="${ not empty listAllmeet && size ne 0}">
                            
                             <table cellpadding="0" cellspacing="0" class='table_border'> 
                             <br>
    <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">议程</div>
	     <div class="title-split-line"><span></span></div>
   </div>

            <tr style="border:1px solid;">
                <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">序号</th>
                <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 20%;">议程名称</th>
                <!-- <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 50%;">参会人员</th>
                <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">汇报人</th> -->
                <!-- <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 43%;">附件材料</th> -->
                <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">操作</th>
            </tr>
            <c:forEach var="obj" items="${listAllmeet}" varStatus="loop">
                <tr class=="row">
               		<td  style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">
						${obj.orderId}
						</td>
                    <td  style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 20%;">
						${obj.meetingName}
						</td>
                   <%--  <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 50%;">
						 ${obj.meetingAttendees}
                    </td>
                    <td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">
						 ${obj.reportName}&nbsp;
                    </td> --%>
					<%-- <td  style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 43%;">
						<c:if test="${ not empty stuffList}">
							<c:forEach var="stuff" items="${stuffList}">
						 	<c:if test="${stuff.djId eq obj.djId}">
						 		<c:if test="${stuff.iszhi=='1'}"><a style="text-decoration: underline; color: blue;" href="javascript:void(0)">${stuff.filename}</a></c:if>
		          				<c:if test="${stuff.iszhi!='1'}"><a style="text-decoration: underline; color: blue;" href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${stuff.stuffid}">${stuff.filename}</a></c:if>
						 	</c:if>
						 </c:forEach>
						</c:if>
					</td> --%>
				    <c:if test="${obj.flagRight=='1'}">
					<td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">
						<a style="text-decoration: underline; color: blue;" href="${pageContext.request.contextPath}/oa/oaMeetingmaterial!viewInfo.do?djId=${obj.djId}">查看详情</a>
					</td>
					</c:if>
					 <c:if test="${obj.flagRight!='1'}">
						<td style="font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 10%;">
						</td>
					</c:if>
                </tr>
            </c:forEach>

        </table>
                            
          </c:if>
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