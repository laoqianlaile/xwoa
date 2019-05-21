<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html lang="en">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta name="decorator" content='${LAYOUT}'/>
		<title>在线人员列表</title>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>				
		<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/navTab.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/centitui/tab.js" type="text/javascript"></script>

<body style="padding: 0;">
	
	<div class="fix" style="width: 800px;">
					<div class="childBlock" id="faqi_content" style="height: 300px;"><!-- #196DB3 -->
						<div id="faqi-more" name="faqi-more"  style="background-color:#A8ACB4;height:300px" >
					
							<%
								int s = 0;
							%>
							<div  class="childBlockCont_faqi"
								>
								<%-- <s:iterator value="dashboardList" status="status" var="i"> --%>
	
								
	
									<!-- 收文登记菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
										<%
											s++;
										%>
										<div >
											<a 	href="javaScript:void(0);"
												onclick="openMenu(this,'swdj','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=XJ370000FG-SW-0001');return false;" 
												style="background: url('../themes/default/improve/shouwendengji.png') no-repeat center 3px; text-decoration: none;border:0 none">
												<span
												><br/><br/>阅办文登记</span>
											</a>
										</div>
									</c:if>
	
									<!-- 发文登记菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
										<%
											s++;
										%>
										<div>
											<a 
												onclick="openMenu(this,'fwdj','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard');return false;" 
												href='javaScript:void(0);'
												style="background: url('../themes/default/improve/fawendengji.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>拟发文登记</span>
											</a>
										</div>
									</c:if>
	
									<%-- 	<!-- 督查发起菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('DBFQ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);' 
												onclick="openMenu(this,'dbfq','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!selectDbList.do');return false;"
												style="background: url('../themes/default/improve/dubanfaqi.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>督办发起</span>
											</a>
										</div>
									</c:if> --%>
									
									<!-- 收文待办菜单权限 -->
								<c:if test="${true }">
									<%
										s++;
									%>
									<div>
										<a href='javaScript:void(0);' 
												onclick="openMenu(this,'swdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW');return false;"
											style="background: url('../themes/default/improve/dubanfaqi.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/>阅办文待办</span>
										</a>
									</div>
								</c:if> 

								<!--发文待办  -->
								<c:if test="${true }">
									<%
										s++;
									%>
									<div>
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'fwdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=FW');return false;";
											style="background: url('../themes/default/improve/shiquandengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
											<span style="color: white;"
											><br/><br/>拟发文待办</span>
										</a>
									</div>
								</c:if>
	
									<!-- 会议申请发起菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('HYSSYDJ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'hydj','<%=request.getContextPath()%>/oa/oaMeetinfo!generalOptView.do');return false;";
												style="background: url('../themes/default/improve/huiyidengji.png') no-repeat center 3px; text-decoration: none;border:0 none">
												<span
												><br/><br/>会议登记</span>
											</a>
										</div>
									</c:if>
								
									<%-- <c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
							 
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'sqdj','<%=request.getContextPath()%>/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1');return false;"
												style="background: url('../themes/default/improve/shiquandengji.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>事权登记</span>
											</a>
										</div>
									</c:if>
									<!-- 签报登记菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('QBDJ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'qbdj','<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/qianbaodengji.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>签报登记</span>
											</a>
										</div>
									</c:if> --%>
									
									<c:if test="${true }">
									<%
										s++;
									%>
									<div>
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'sjx','<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I');return false;"
											style="background: url('../themes/default/improve/faqitixing.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/>接收邮件</span>
										</a>
									</div>
								</c:if>
									
								</div>	
								<div  class="childBlockCont_faqi"
								>		
									 <!-- 车辆申请登记菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('CLSYDJ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'sqyc','<%=request.getContextPath()%>/oa/oaCarApply!list.do?s_biztype=F&show_type=F');return false;";
												style="background: url('../themes/default/improve/shengqincheliang.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>申请用车</span>
											</a>
										</div>
									</c:if> 
									
							
									<!-- 我的提醒菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('YFSTX') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'fqtx','<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/faqitixing.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>发起提醒</span>
											</a>
										</div>
									</c:if>
								
									
								<!-- 发送邮件菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('QF_FJX') }">
										<%
											s++;
										%>
										<div>
											<a
												href='javaScript:void(0);'
												onclick="openMenu(this,'fsyj','<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/fasongyoujian.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>发送邮件</span>
											</a>
										</div>
									</c:if>
									
									
									
									<!-- 个人日程菜单权限 -->
									
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'grrc','<%=request.getContextPath()%>/oa/oaSchedule!built.do?s_sehType=1&dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/gerengricheng.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>个人日程</span>
											</a>
										</div>
									
									
									
									<!-- 领导日程菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('RCBGLDRCAP') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'ldrc','<%=request.getContextPath()%>/oa/oaSchedule!built.do?s_sehType=2&dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/lindaoricheng.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>领导日程</span>
											</a>
										</div>
									</c:if>
							</div>
							<div class="childBlockCont_faqi">
									
									<!-- 发送通知菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('TZGG_') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'fstz','<%=request.getContextPath()%>/oa/oaInformation!built.do?infoType=info&flag=GGZY&dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/fasongtongzhi.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>发送通知</span>
											</a>
										</div>
									</c:if>
									
									<!-- 公务授权菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('RCBGGWSQ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'gwsq','<%=request.getContextPath()%>/sampleflow/sampleFlowRelegate!list.do?grant=T&s_grant=T');return false;"
												style="background: url('../themes/default/improve/gongwushouquan.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>公务授权</span>
											</a>
										</div>
									</c:if>
									
									
									<!-- 通讯录菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('GRBGGRTXL') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'txl','<%=request.getContextPath()%>/oa/addressbooks!list_new.do');return false;"
												style="background: url('../themes/default/improve/tongxunlu.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>通讯录</span>
											</a>
										</div>
									</c:if>
									
									<!-- 收文归档菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('SWGD') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0); '
												onclick="openMenu(this,'swgd','<%=request.getContextPath()%>/oa/voaUnitArchiveIncomedoc!list.do');return false;"
												style="background: url('../themes/default/improve/shouwenguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>阅办文归档</span>
											</a>
										</div>
									</c:if>
									<!-- 发文归档菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('FWGD') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'fwgd','<%=request.getContextPath()%>/oa/voaUnitArchiveDispatchdoc!list.do');return false;"
												style="background: url('../themes/default/improve/fawenguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>拟发文归档</span>
											</a>
										</div>
									</c:if>
									
									
									<!-- 人工归档菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('GDDJ') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'rggd','<%=request.getContextPath()%>/oa/oaArchive!toDoadd.do?');return false;"
												style="background: url('../themes/default/improve/renggongguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>人工归档</span>
											</a>
										</div>
									</c:if>
									</div>
							<div class="childBlockCont_faqi">
									<!-- 发送短信菜单权限 -->
									<c:if test="${cp:HASOPTPOWER('MESSAGE') }">
										<%
											s++;
										%>
										<div>
											<a 
												href='javaScript:void(0);'
												onclick="openMenu(this,'fsdx','<%=request.getContextPath()%>/oa/oaSmssend!editSend.do?dashboard=dashboard');return false;"
												style="background: url('../themes/default/improve/faqiduanxing.png') no-repeat center 3px; text-decoration: none;border:0 none">
												 <span
												><br/><br/>发送短信</span>
											</a>
										</div>
									</c:if>
								
								
								</div>
							
			</div>
		</div>
				
					
				</div>
	
	
</body>
<script type="text/javascript">
$(function(){
	/* kuang(); */
});
function kuang(){
  	 var num=<%=s%>/6;
  	 if(num<=1){
    		$('#attAlert_yw').css('height','117');
    		$('#faqi-more').css('height','92');
			$('#faqi_content').css('height','92');
    	} else
		if(num<=2){
			$('#attAlert_yw').css('height','207');
			$('#faqi-more').css('height','182');
			$('#faqi_content').css('height','182');
    	}else
		if(num<=3){
			$('#attAlert_yw').css('height','299');
			$('#faqi-more').css('height','274');
			$('#faqi_content').css('height','274');
		}
    
  }
function openMenu(obj,tabid, url){
	debugger;
	
	$("#layout").removeClass("improveStyle");
	var $this = $(obj);

	$("#ul_YGJG>li>div>div.first_collapsable").removeClass("first_collapsable").addClass("first_expandable");
	$("#ul_YGJG>li>div>div.collapsable").removeClass("collapsable").addClass("expandable");
	navTab.closeAllTab();
	
	var title = $this.attr("title") || $this.text();
	var tabName = $this.attr("rel") || "_blank";
	var fresh = eval($this.attr("fresh") || "true");
	if(tabid == "dwbl" || tabid == "jjcq" || tabid == "ycq"){
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		/* setTimeout(function() { */
			$('#menu_GRBGGRDB>div>a').click();
			$("#menu_GRBGDBSX>div>a").addClass("selected");
		/* }, 200);  */
	}else if(tabid == "tzgg"){
		menuShow();
		$(".toggleCollapse h2").html("日常办公");
		hideMenu();
		$("#menu_YGJGRCBG").show();
		if (!$("#ul_YGJGRCBG").is(":visible"))
			$("#menu_YGJGRCBG>div>a").click();
		$("#menu_YGJGRCBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GGZY>div>div.expandable").size() > 0)
				$("#menu_GGZY>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_TZGG_GGZY>div>a").addClass("selected");
		}, 300);
	}else if(tabid == "jwbl" || tabid == "blz" || tabid == "ybj"){
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRDB>div>div.expandable").size() > 0)
				$("#menu_GRBGGRDB>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GRBGBJCK>div>a").addClass("selected");
		}, 300);
	}
	else if(tabid == "sjx"){
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SJX>div>a").addClass("selected");
		}, 300);
	}else if(tabid == "cgx"){
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DRAFTSBOX>div>a").addClass("selected");
		}, 300);
	}else if(tabid == "dwsw" || tabid == "bmsw"){
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZSWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZSWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SWGLSWCK>div>a").addClass("selected");
		}, 300);
	}else if(tabid == "dwfw" || tabid == "bmfw"){
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZFWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZFWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FWGLFWCK>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="swdj"){//
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZSWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZSWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SWGLSWDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fwdj"){//
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZFWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZFWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FWGLFWDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="dbfq"){//督办发起
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("督办发起");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGDBCB>div>div.expandable").size() > 0)
				$("#menu_GRBGDBCB>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DBCBFQ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="hydj"){//会议登记
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("办公资源");
		hideMenu();
		$("#menu_YGJGBGZY").show();
		if (!$("#ul_YGJGBGZY").is(":visible"))
			$("#menu_YGJGBGZY>div>a").click();
		$("#menu_YGJGBGZY>div").hide();
		setTimeout(function() {
			if ($("#menu_HYSGL_NEW>div>div.expandable").size() > 0)
				$("#menu_HYSGL_NEW>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_HYSQDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="sqdj"){//事权登记
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("内部事权");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_NBSQBL>div>div.expandable").size() > 0)
				$("#menu_NBSQBL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_NBSXSXDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="qbdj"){//签报登记
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("内部事权");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_RCBGQBGL>div>div.expandable").size() > 0)
				$("#menu_RCBGQBGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_QBDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="sqyc"){//申请用车
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("办公资源");
		hideMenu();
		$("#menu_YGJGBGZY").show();
		if (!$("#ul_YGJGBGZY").is(":visible"))
			$("#menu_YGJGBGZY>div>a").click();
		$("#menu_YGJGBGZY>div").hide();
		setTimeout(function() {
			if ($("#menu_CLGL_new>div>div.expandable").size() > 0)
				$("#menu_CLGL_new>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_CLSYDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fqtx"){//发起提醒
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("日常办公");
		hideMenu();
		$("#menu_YGJGRCBG").show();
		if (!$("#ul_YGJGRCBG").is(":visible"))
			$("#menu_YGJGRCBG>div>a").click();
		$("#menu_YGJGRCBG>div").hide();
		setTimeout(function() {
			if ($("#menu_TXSJ>div>div.expandable").size() > 0)
				$("#menu_TXSJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DFSTX>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fsyj"){//发送邮件
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FJX>div>a").addClass("selected");
		}, 300);
	}
	else if(tabid=="grrc"){//个人日程
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGRCAP>div>div.expandable").size() > 0)
				$("#menu_GRBGRCAP>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GRBGGRRCAP>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="ldrc"){//领导日程
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGRCAP>div>div.expandable").size() > 0)
				$("#menu_GRBGRCAP>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GRBGLDRCAP>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fstz"){//发送通知
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("日常办公");
		hideMenu();
		$("#menu_YGJGRCBG").show();
		if (!$("#ul_YGJGRCBG").is(":visible"))
			$("#menu_YGJGRCBG>div>a").click();
		$("#menu_YGJGRCBG>div").hide();
		setTimeout(function() {
			if ($("#menu_INFO_>div>div.expandable").size() > 0)
				$("#menu_INFO_>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_TZGG_>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="gwsq"){//公务授权
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRDB>div>div.expandable").size() > 0)
				$("#menu_GRBGGRDB>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_RCBGGWSQ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="txl"){//通讯录
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGFZBG>div>div.expandable").size() > 0)
				$("#menu_GRBGFZBG>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GRBGGRTXL>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="swgd"){//收文归档
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZXGGN>div>div.expandable").size() > 0)
				$("#menu_GWLZXGGN>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SWGD>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fwgd"){//发文归档
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZXGGN>div>div.expandable").size() > 0)
				$("#menu_GWLZXGGN>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FWGD>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="rggd"){//人工归档
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZXGGN>div>div.expandable").size() > 0)
				$("#menu_GWLZXGGN>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GDDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fsdx"){//发送短信
		closeAlerts("attAlert_yw");//关闭遮雾层
		menuShow();
		$(".toggleCollapse h2").html("日常办公");
		hideMenu();
		$("#menu_YGJGRCBG").show();
		if (!$("#ul_YGJGRCBG").is(":visible"))
			$("#menu_YGJGRCBG>div>a").click();
		$("#menu_YGJGRCBG>div").hide();
		setTimeout(function() {
			if ($("#menu_MESSAGEGL>div>div.expandable").size() > 0)
				$("#menu_MESSAGEGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_MESSAGE>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="swdj1"){//
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZSWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZSWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SWGLSWDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fwdj1"){//
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZFWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZFWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FWGLFWDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="dbfq1"){//督办发起
		menuShow();
		$(".toggleCollapse h2").html("督办发起");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGDBCB>div>div.expandable").size() > 0)
				$("#menu_GRBGDBCB>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DBCBFQ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="hydj1"){//会议登记
		menuShow();
		$(".toggleCollapse h2").html("办公资源");
		hideMenu();
		$("#menu_YGJGBGZY").show();
		if (!$("#ul_YGJGBGZY").is(":visible"))
			$("#menu_YGJGBGZY>div>a").click();
		$("#menu_YGJGBGZY>div").hide();
		setTimeout(function() {
			if ($("#menu_HYSGL_NEW>div>div.expandable").size() > 0)
				$("#menu_HYSGL_NEW>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_HYSQDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="sqdj1"){//事权登记
		menuShow();
		$(".toggleCollapse h2").html("内部事权");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_NBSQBL>div>div.expandable").size() > 0)
				$("#menu_NBSQBL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_NBSXSXDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="qbdj1"){//签报登记
		menuShow();
		$(".toggleCollapse h2").html("内部事权");
		hideMenu();
		$("#menu_YGJGNBSX").show();
		if (!$("#ul_YGJGNBSX").is(":visible"))
			$("#menu_YGJGNBSX>div>a").click();
		$("#menu_YGJGNBSX>div").hide();
		setTimeout(function() {
			if ($("#menu_RCBGQBGL>div>div.expandable").size() > 0)
				$("#menu_RCBGQBGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_QBDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="sqyc1"){//申请用车
		menuShow();
		$(".toggleCollapse h2").html("办公资源");
		hideMenu();
		$("#menu_YGJGBGZY").show();
		if (!$("#ul_YGJGBGZY").is(":visible"))
			$("#menu_YGJGBGZY>div>a").click();
		$("#menu_YGJGBGZY>div").hide();
		setTimeout(function() {
			if ($("#menu_CLGL_new>div>div.expandable").size() > 0)
				$("#menu_CLGL_new>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_CLSYDJ>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fqtx1"){//发起提醒
		menuShow();
		$(".toggleCollapse h2").html("日常办公");
		hideMenu();
		$("#menu_YGJGRCBG").show();
		if (!$("#ul_YGJGRCBG").is(":visible"))
			$("#menu_YGJGRCBG>div>a").click();
		$("#menu_YGJGRCBG>div").hide();
		setTimeout(function() {
			if ($("#menu_TXSJ>div>div.expandable").size() > 0)
				$("#menu_TXSJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DFSTX>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="rcap_c"){//个人日程_calendar
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGRCAP>div>div.expandable").size() > 0)
				$("#menu_GRBGRCAP>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_GRBGGRRCAP>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fyj"){//发邮件
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FJX>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="syx"){//收件箱
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SJX>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="cgx"){//草稿箱
		menuShow();
		$(".toggleCollapse h2").html("个人办公");
		hideMenu();
		$("#menu_YGJGGRBG").show();
		if (!$("#ul_YGJGGRBG").is(":visible"))
			$("#menu_YGJGGRBG>div>a").click();
		$("#menu_YGJGGRBG>div").hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable").size() > 0)
				$("#menu_GRBGGRYJ>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DRAFTSBOX>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="fwdb"){//发文待办
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZFWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZFWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FWGLFWDB>div>a").addClass("selected");
		}, 300);
	}else if(tabid=="swdb"){//收文待办
		menuShow();
		$(".toggleCollapse h2").html("公文流转");
		hideMenu();
		$("#menu_YGJGGWLZ").show();
		if (!$("#ul_YGJGGWLZ").is(":visible"))
			$("#menu_YGJGGWLZ>div>a").click();
		$("#menu_YGJGGWLZ>div").hide();
		setTimeout(function() {
			if ($("#menu_GWLZSWGL>div>div.expandable").size() > 0)
				$("#menu_GWLZSWGL>div>a").click();		
		}, 200);
		setTimeout(function() {
			$("#menu_SWGLSWDB>div>a").addClass("selected");
		}, 300);
	}
	navTab.openTab(tabName, url,{title:title, fresh:fresh, external:external});
}

</script>
</html>