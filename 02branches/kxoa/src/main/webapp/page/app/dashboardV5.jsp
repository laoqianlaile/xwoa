<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/easyui/jquery.messager.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<style>
<!--
.leftInfo table{table-layout: fixed}
.leftInfo ul{table-layout: fixed}
.itemTitle a{
    display: block;
    width: 80%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}


-->
</style>
<div class="left_custom" >
	<c:if test="${not(username=='noname')}">
		<authz:authentication var="username" property="name" />
		<div><c:if test="${username!='noname'}">
								您好，<%-- ${cp:MAPVALUE("unitcode",unitcode) } --%> ${username}
								</c:if>
								<span class="icon  icon-carat-1-s">	</span></div>
		<div><a style="color:white; font-size: 12px;" onmouseover="this.style.cursor='hand'" onclick="doShow();" >当前在线<span id="userCountOnlineDash" style="cursor:pointer;"></span>人</a></div>
		<!-- <div>上次登录2016-04-27&nbsp;12:00</div> -->
		<div>
		   <a title="锁屏" href="javascript:void(0);" onclick="lockScreen(false);return false;"><img alt='锁屏' src='../newStatic/image/left_custom1.png'/></a>
		   <c:if test="${cp:MAPSTATE('SYSPARAM','CAS') eq 'T'}">
				<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
			</c:if>
			<c:if test="${not (cp:MAPSTATE('SYSPARAM','CAS') eq 'T')}">
				<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
			</c:if>
			<a  title="修改密码" href="<s:url value='/sys/userDef!modifyPwdPage.do' />" target="dialog" drawable="false" width="550" height="350"><img src='../newStatic/image/white_key.png'/></a>
		   <c:if test="${cp:CHECKUSEROPTPOWER('NBTXL', 'list')}">
		   <a href="javascript:void(0);" onclick="openMenu(this,'txl','<%=request.getContextPath()%>/oa/addressbooks!list_new.do');return false;" rel="GRBGGRTXL" title="通讯录" ><img alt='电话簿' src='../newStatic/image/left_custom2.png'/></a>
			</c:if>
			<%-- <a title="资料库" href="javascript:void(0);" onclick="openMenu(this,'zlk','<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=PUBLICFILE');return false;"><img src='../newStatic/image/left_custom4.png'/></a> --%>
		</div>
	</c:if>
	<div class="seg_line"></div>
	
	<ul style="margin-top: 14px;">
	
			<c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
				<li class="shortcutKey"><img src='../themes/default/improve/fawendengji.png'/><a 
					onclick="openMenu(this,'fwdj','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard');return false;"
					href='javaScript:void(0);'> <span style="color: white;">&nbsp;拟&nbsp;&nbsp;&nbsp;&nbsp; 发&nbsp;&nbsp;&nbsp;&nbsp; 文 </span>
				</a></li>
			</c:if>
	
			<c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
				<li class="shortcutKey"><img src='../themes/default/improve/shouwendengji.png'/><a href="javaScript:void(0);"
					onclick="openMenu(this,'swdj','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=XJ370000FG-SW-0001');return false;">
					<span style="color: white;">&nbsp;阅 &nbsp;&nbsp;&nbsp; 办 &nbsp; &nbsp;&nbsp;文</span>
				</a></li>
			</c:if>
			<c:if test="${empty isLD }">
		    <c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ','nfwdb') }">
				<li class="shortcutKey"><img src='../themes/default/improve/shouwenguidang.png'/><a href='javaScript:void(0);'
					onclick="openMenu(this,'fwdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=FW');return false;">
					<span style="color: white;">&nbsp; &nbsp;&nbsp;<c:if test="${not empty isLD and isLD eq 'true'}">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批&nbsp;&nbsp;&nbsp;</c:if><c:if test="${empty isLD }"> 拟 发 文 待 办</c:if></span>
				</a></li>
			</c:if>
			
		    <c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ','ybwdb') }">
				<li class="shortcutKey"><img src='../themes/default/improve/fawenguidang.png'/><a href='javaScript:void(0);'
					onclick="openMenu(this,'swdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW');return false;">
					<span style="color: white;">&nbsp;&nbsp;&nbsp;<c:if test="${not empty isLD and isLD eq 'true'}">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅&nbsp;&nbsp;&nbsp;</c:if><c:if test="${empty isLD }"> 阅 办 文 待 办</c:if></span>
				</a></li>
			</c:if>
			</c:if>
			
			<c:if test="${not empty isLD and isLD eq 'true'}">
		    <c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ','nfwdb') }">
				<li class="shortcutKey"><img src='../themes/default/improve/shouwenguidang.png'/><a href='javaScript:void(0);'
					onclick="openMenu(this,'zxfwdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=FW');return false;">
					<span style="color: white;">&nbsp; &nbsp;&nbsp;审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批&nbsp;&nbsp;&nbsp;</span>
				</a></li>
			</c:if>
			
		    <c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ','ybwdb') }">
				<li class="shortcutKey"><img src='../themes/default/improve/fawenguidang.png'/><a href='javaScript:void(0);'
					onclick="openMenu(this,'zxswdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW');return false;">
					<span style="color: white;">&nbsp;&nbsp;&nbsp;审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅&nbsp;&nbsp;&nbsp;</span>
				</a></li>
			</c:if>
			</c:if>
		
		<%-- <c:if test="${cp:HASOPTPOWER('HYSSYDJ') }">
			<li class="shortcutKey"><img src='../themes/default/improve/huiyidengji.png'/><a href='javaScript:void(0);'
				onclick="openMenu(this,'hydj','<%=request.getContextPath()%>/oa/oaMeetinfo!generalOptView.do');return false;">
				<span style="color: white;">&nbsp;会 议 登 记</span>
			</a></li>
		</c:if>
		<li class="shortcutKey"><img src='../themes/default/improve/gongwushouquan.png'/><a href='javaScript:void(0);'
			onclick="openMenu(this,'hydb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000857');return false;">
			<span style="color: white;">&nbsp;会 议 待 办</span>
		</a></li> 
		<c:if test="${cp:HASOPTPOWER('YFSTX') }">
			<li class="shortcutKey"><img src='../themes/default/improve/faqitixing.png'/><a href='javaScript:void(0);'
				onclick="openMenu(this,'fqtx','<%=request.getContextPath()%>/oa/oaRemindInformation!builtV2.do?reType=FW&dashboard=dashboard');return false;">
				<span style="color: white;">&nbsp;发 起 提 醒</span>
			</a></li>
		</c:if>--%>
		
		<li class="shortcutKey"><img src='../themes/default/improve/mail.png'/><a href='javaScript:void(0);'
			onclick="openMenu(this,'fsdx','<%=request.getContextPath()%>/oa/oaSmssend!editSend.do');return false;">
			<span style="color: white;">&nbsp;发&nbsp; 送 &nbsp;短&nbsp; 信</span>
		</a></li>
		
	</ul>
	

</div>
<div class="improveLeft" style="position:absolute;left:10px;top:20px;bottom:105px;">
     <c:if test="${not empty optDashboardLayout}">
      <input type="hidden" id="layoutContent" value="${optDashboardLayout.content}">
      </c:if>
      ${optLayoutMethod.content}
</div>

<div id="improveFooter" style="position:absolute;width:100%;bottom:0;">
	<div>
<!-- 	版权所有：新疆维吾尔自治区交通运输厅	 -->
	</div>
<!-- 	<div>版权所有：南京市总工会 &nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业有限公司 </div> -->
</div>

<script type="text/javascript">
$(function(){
	getUserCountOnline();  // 获取在线人数
	renderDashboard();
});

function renderDashboard(){
	   if($("#layoutContent").length > 0){
		   var jsonContent = $("#layoutContent").val();
		    var jsonArr = eval("("+jsonContent+")");
		    $.each(jsonArr,function(i,item){
		    	$.ajax({
		    		type:"post",
		    		url:"${ctx}/app/optDashboardModule!getJsonByModuleCode.do",
		    		data:{"moduleCode":item.moduleCode},
		    		dataType:"json",
		    		success:function(data){
		    			var ele = createHtmlEle(item);
		    			$(".improveLeft").find("td").eq(item.tdIndex).html(ele);
		    		}
		    	});
		    });
	   }
}
function createHtmlEle(data){
	var div = $("<div>",{"width":"100%","height":"100%"});
	var displayUrl = "${ctx}/"+data.displayUrl;
	var iframe = $("<iframe>",{"style":"width:100%;height:100%","frameborder":"no","border":"0","src":displayUrl});
	div.append(iframe);
	return div;
}
</script>