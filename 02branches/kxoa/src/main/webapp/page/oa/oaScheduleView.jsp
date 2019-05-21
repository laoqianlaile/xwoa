<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>				
<style>
      .BoaSchedule{background-color: #ade2fc;color: #fff;}
      .GoaSchedule{background-color:#9fda9f;color: #fff;}
      .NoaSchedule{background-color:#FFFFFF;color: #000000;}
      .YoaSchedule{background-color:#fdfd95;color: blue;}
      .PoaSchedule{background-color:#e6c2e6;color: #fff;}
      .RoaSchedule{background-color: #cb6768;color: #fff;}
      .OoaSchedule{background-color: #ffd68c;color: #fff;}
    </style>
    
<script type="text/javascript">
	// 	$(function() {
	// 		$('#tabFrames1').hide();
	// 		$('#button_xy').click(function(){
	// 			$('#tabFrames1').show();
	// 			$('#tabFrames1').attr("style","visibility:visible;");
	// 			$('#tabFrames1'). reload();

	// 		});
	// 	});
</script>
<title><s:text name="oaSchedule.view.title" /></title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
				<s:text name="oaSchedule.view.title" />
		</legend>



		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="oaSchedule" method="post" namespace="/oa"
			id="oaScheduleForm">
			<s:hidden name="show_type" value="%{show_type}"></s:hidden>
			<s:hidden name="s_sehType" value="%{s_sehType}"></s:hidden>
			<!-- 		响应按钮 -->
			<%-- <c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode ne creater and isDo eq '3' and 'Edit' eq resShow}">
				<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaScheduleResponse!ownResEdit.do?schno=${schno}&viewtype=F','响应',null);"  value="响应">
		</c:if> --%>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<%-- <tr>
				<td class="addTd"><s:text name="oaSchedule.schno" /></td>
				<td align="left" colspan="5"><s:property value="%{schno}" /></td>
			</tr> --%>
				<tr>
					<td class="addTd" width="15%"><s:text name="oaSchedule.itemtype" /></td>
					<td align="left" width="38%">${cp:MAPVALUE('oaItemType',itemtype)}</td>
					<td class="addTd"><s:text name="oaSchedule.sehType" /></td>
					<td align="left">${cp:MAPVALUE('oaSehType',sehType)}</td>
				</tr>
				<%-- <tr>
					<td class="addTd"><s:text name="oaSchedule.importantTag" /></td>
					<td align="left" colspan="3">${cp:MAPVALUE('IMP',importantTag)}</td>
					<td class="addTd"><s:text name="oaSchedule.taskTag" /></td>
					<td align="left" class="${taskTag}oaSchedule">${cp:MAPVALUE('TASKTAG',taskTag)}</td>
				</tr> --%>
			
				<tr>
					<td class="addTd"><s:text name="oaSchedule.title" /></td>
					<td align="left"><s:property value="%{title}" /></td>
					<td class="addTd"><s:text name="oaSchedule.importantTag" /></td>
					<td align="left">${cp:MAPVALUE('IMP',importantTag)}</td>
				</tr>
				<c:if test="${  sehType eq '2'or s_sehType eq '2'}">
					<tr>
						<td class="addTd">领导成员</td>
						<td align="left" colspan="3"><s:property value="%{leaders}" /></td>
					</tr>
				</c:if>

				<!-- 3:邀请与会者 -->
				<c:if test="${'3' eq itemtype}">
					<c:if test="${ not empty oaSchedule.meetid}">
						<tr>
							<td class="addTd"><s:text name="oaSchedule.meetid" /></td>
							<td align="left" colspan="3"><s:property value="%{meetid}" /></td>
						</tr>
					</c:if>
				<%-- 	<tr>
						<td class="addTd"><s:text name="oaSchedule.city" /></td>
						<td align="left" colspan="5"><s:property value="%{city}" /></td>
					</tr> --%>
				</c:if>
				<tr>
					<td class="addTd"><s:text name="oaSchedule.address" /></td>
					<td align="left" colspan="3"><s:property value="%{address}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaSchedule.remark" /></td>
					<td align="left" colspan="3"><s:property value="%{remark}" /></td>
				</tr>
	<tr>
					<td class="addTd"><s:text name="oaSchedule.planBegTime" /></td>
					<td align="left"><s:date name="%{planBegTime}"
							format="yyyy-MM-dd HH:mm" /></td>
					<td class="addTd"><s:text name="oaSchedule.planEndTime" /></td>
					<td align="left" width="33%"><s:date
							name="%{planEndTime}" format="yyyy-MM-dd HH:mm" /></td>
				</tr>
				
				<c:if test="${'3' eq itemtype}">
					<%-- 	 <c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode eq creater}">	
			<tr>
				<td class="addTd"><s:text name="oaSchedule.isEmail" /></td>

				<td>${cp:MAPVALUE('oaIsEmail',isEmail)}</td>
				<td class="addTd"><s:text name="oaSchedule.noticeSign" /></td>

				<td >${cp:MAPVALUE('oaNoticeSign',noticeSign)}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaSchedule.isDo" /></td>
				<td colspan="5">${cp:MAPVALUE('oaIsDo',isDo)}</td>
			</tr>
			</c:if> --%>

					<!-- 			日程响应，领导日程不显示 -->

					<c:if test="${  sehType eq '1'or s_sehType eq '1'}">


						<%--<c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode eq creater}">	
			
			<!-- 				编辑时出现响应信息 -->

				 <c:if test="${ not empty schno}">
					<tr>
						<td class="addTd">接受响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListJS}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应
						<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
						
						&nbsp;<br>
							</c:forEach></td>
					</tr>

					<tr>
						<td class="addTd">暂缓响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListZH}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应,${cp:MAPVALUE('oaResType',att.resType)}至
						<fmt:formatDate value='${att.stopTime}'
									pattern='yyyy-MM-dd  HH:mm' />&nbsp;
							<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
								<br>
							</c:forEach></td>
					</tr>

					<tr>
						<td class="addTd">拒绝响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListJJ}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应
						<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
								<br>
							</c:forEach></td>
					</tr>
				</c:if>
			</c:if> --%>
						<%-- <tr>
				<td class="addTd"><s:text name="oaSchedule.resRemark" /></td>
				<td align="left" colspan="5"><s:property value="%{resRemark}" /></td>
			</tr> --%>
					</c:if>
				</c:if>
				<tr>
					<td class="addTd"><s:text name="oaSchedule.creater" /></td>
					<td align="left">${cp:MAPVALUE('usercode',creater)}</td>
					<td class="addTd"><s:text name="oaSchedule.createdate" /></td>
					<td align="left"><s:date name="%{createdate}"
							format="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</table>

			<div class="formButton">
			
		
				<c:if test="${ empty show_type and empty dashboard}">
					<input type="button" onclick="doback(-1);" class="btn"
						value="返回" />
				</c:if>
				<c:if test="${not empty dashboard and dashboard eq 'RCAP'}">
				
			 	<input type="button" name="back" Class="btn"
				onclick="doback();" value="返回2" />
		
				</c:if>   
			</div>
			<c:if test="${ not empty creater }">

				<c:if
					test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode ne creater}">
					<c:if test="${'View' eq resShow}">
						<fieldset class="form">
							<iframe id="tabFrames2" name="tabFrames2"
								src="<%=request.getContextPath()%>/oa/oaScheduleResponse!ownResView.do?schno=${schno}&viewtype=F"
								onload="autoHeight(this);" width="100%" frameborder="0"
								scrolling="auto" border="0" marginwidth="0"></iframe>
						</fieldset>
					</c:if>
				</c:if>

			</c:if>

		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'dialogHeight=350px;dialogWidth=700px;help:no;status:no;top=250;left=500;scroll:no';
		}
		var tag = window.showModalDialog(winUrl, winName, winProps);
		if (tag == true) {
			window.location.reload();
		}

	}
	function doback(type){	
		var win = art.dialog.open.origin;//来源页面
			
			if (type=-1){
				if(win){
					// 如果父页面重载或者关闭其子对话框全部会关闭
		       		win.location.reload(true);
		       		art.dialog.close();
				}
				
			}else{
				window.location="${contextPath}/oa/oaSchedule!tabList.do?s_sehType=1&s_canAdd=T";
				}
			}
		
</script>
<%@ include file="/page/common/scripts.jsp"%>
</html>
