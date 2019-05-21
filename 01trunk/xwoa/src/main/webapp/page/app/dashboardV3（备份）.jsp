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
.zhiding{color: red !important}

-->
</style>
<div class="left_custom">
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
		   <a href="javascript:void(0);" onclick="openMenu(this,'txl','<%=request.getContextPath()%>/oa/addressbooks!list_new.do');return false;" rel="GRBGGRTXL" title="通讯录" ><img alt='电话簿' src='../newStatic/image/left_custom2.png'/></a>
			<%-- <a title="资料库" href="javascript:void(0);" onclick="openMenu(this,'zlk','<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=PUBLICFILE');return false;"><img src='../newStatic/image/left_custom4.png'/></a> --%>
		</div>
	</c:if>
	<div class="seg_line"></div>
	
	<ul style="margin-top: 14px;">
	
			<c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
				<li class="shortcutKey"><img src='../themes/default/improve/fawendengji.png'/><a 
					onclick="openMenu(this,'fwdj','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard');return false;"
					href='javaScript:void(0);'> <span style="color: white;">&nbsp;发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 文 </span>
				</a></li>
			</c:if>
	
			<c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
				<li class="shortcutKey"><img src='../themes/default/improve/shouwendengji.png'/><a href="javaScript:void(0);"
					onclick="openMenu(this,'swdj','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=XJ370000FG-SW-0001');return false;">
					<span style="color: white;">&nbsp;收 &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;文</span>
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
<div class="improveLeft" style="height: 92%;">
	<table>
		<tr>
		<c:if test="${empty isLD}"> 
		  <td class='fw_custom'>
				<div title="发文待办" rel="FWGLFWDB">
					<span id="fwTaskNumSpan">${fwTaskNum }</span>
					<div class="td_link">
						发文待办
					<a></a>
					</div>
				</div>
			</td>
			<td class='sw_custom'>
				<div title="收文待办" rel="SWGLSWDB">
					<span id="swTaskNumSpan">${swTaskNum }</span>
					<div class="td_link">
						收文待办 
					<a></a>
					</div>
				</div>
			</td>
			</c:if> 
			<c:if test="${not empty isLD and isLD eq 'true'}">
			 <td class='zxfw_custom'>
				<div title="发文待办" rel="ZXFWGLFWDB">
					<span id="fwTaskNumSpan">${fwTaskNum }</span>
					<div class="td_link">
					审批
					<a></a>
					</div>
				</div>
			</td>
			
			<td class='zxsw_custom'>
				<div title="收文待办" rel="ZXSWGLSWDB">
					<span id="fwTaskNumSpan">${swTaskNum }</span>
					<div class="td_link">
						审阅
					<a></a>
					</div>
				</div>
			</td>
			</c:if>  
			
			<td class='gg_custom'>
				<div title="来文未读待办">
					<span id="lwTaskNumSpan">${lwTaskNum }</span>
					<div class="td_link">
						来文未读待办<a></a>
					</div>
				</div>
  			</td>
			<td class='yj_custom'>
				<div title="未读邮件" class="yj">
					<div class="td_link_left">
						<span id="unReadEmailNumSpan">未读邮件&nbsp;&nbsp;${unReadEmailNum }</span><a></a> 
					</div>
				</div>
				<div title="提醒" class="tx">
					<div class="td_link_left">
						<span id="unReadRemindNumSpan">内部提醒 &nbsp;&nbsp;${unReadRemindNum }</span><a></a>
					</div> 
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" valign="top" style="padding: 0 10px 0 20px;">
							<div class="childBlockTile dbsy_custom">
									<div class="childBlockTile_left">
			                             <div id="tongzhi-div"
											class="childBlockTile_left1 document-switch-hover">待办事宜</div>
									</div>
									<div class="childBlockTile_right" name="dwbl-div">
										<a class="chooseRCType" 
											title="待办事宜" 
											onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do');return false;"
											href="javaScript:void(0);">
											<span class="childBlockMore"></span>
										</a>
									</div>				
								</div>
								
								
								<table id="dwbl" class="tzgg_custom">
									
								</table>

				
			</td>
			<td style="width:33%;padding: 0 20px 0 10px;" valign="top"> 
			
							<div class="childBlockTile nbzx_custom">
									<div class="childBlockTile_left">
			                             <div id="nbzx-div"
											class="childBlockTile_left1 document-switch-hover">通知公告</div>
									</div>
									<div class="childBlockTile_right" name="nbzx-div">
										<a class="chooseRCType" 
											title="通知公告" 
											onclick="openMenu(this,'nbzx','<%=request.getContextPath()%>/oa/oaInformation!mainlist.do?flag=GGZY');return false;"
											href="javaScript:void(0);">
											<span class="childBlockMore"></span>
										</a>
									</div>			
								</div>
								<div id="div_list" class="div_list" style="height:330px;overflow:hidden;">
									<ul class="nbzx_ul" id="nbzx" >
										<!-- <marquee id="nbzx" direction="up" contenteditable="true"
											onstart="this.firstChild.innerHTML+=this.firstChild.innerHTML;"
											scrollamount="2" width="100%" onmouseover="this.stop();"
											onmouseout="this.start();" height="270px"> </marquee> -->
									</ul>
								</div>
								
			
		    </td>
			
		</tr>
	</table>
	
</div>

<div id="improveFooter">
	<div>
<!-- 	版权所有：新疆维吾尔自治区交通运输厅	 -->
	</div>
	<div>版权所有：连云港市徐圩 &nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业股份有限公司 </div>
</div>

 <!--业务入口  -->
<div id="attAlert_yw" class="alert" 
				style="width: 700px; height: 460px; position: absolute; top: 20px; left: 20%; overflow: hidden;display: none;">
				<h4>
					<span id="selectyw"></span><span id="close3"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert_yw');">关闭</span>
				</h4>
				<div class="fix">
					<div class="childBlock" id="faqi_content" style="height: 365px;"><!-- #196DB3 -->
						<div id="faqi-more" name="faqi-more"  style="background-color:#A8ACB4;height:365px" >
					
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
												><br/><br/><br/>收文登记</span>
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
												><br/><br/><br/>发文登记</span>
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
												><br/><br/><br/>督办发起</span>
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
											><br/><br/><br/>收文待办</span>
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
											><br/><br/><br/>发文待办</span>
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
												><br/><br/><br/>会议登记</span>
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
												><br/><br/><br/>事权登记</span>
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
												><br/><br/><br/>签报登记</span>
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
											><br/><br/><br/>接收邮件</span>
										</a>
									</div>
								</c:if>
									
								</div>	
								<div  class="childBlockCont_faqi"
								style="border-bottom: 2px solid #e9e9e9;">		
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
												><br/><br/><br/>申请用车</span>
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
												><br/><br/><br/>发起提醒</span>
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
												><br/><br/><br/>发送邮件</span>
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
												><br/><br/><br/>个人日程</span>
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
												><br/><br/><br/>领导日程</span>
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
												><br/><br/><br/>发送通知</span>
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
												><br/><br/><br/>公务授权</span>
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
												><br/><br/><br/>通讯录</span>
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
												><br/><br/><br/>收文归档</span>
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
												><br/><br/><br/>发文归档</span>
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
												><br/><br/><br/>人工归档</span>
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
												><br/><br/><br/>发送短信</span>
											</a>
										</div>
									</c:if>
								
								
								</div>
							
			</div>
		</div>
				
					
				</div>
</div>

<script type="text/javascript">
   
   // 设置内部资讯初始高度
	var height1 = $('.nbzx_custom').offset().top;
	var height2 = $('#improveFooter').offset().top -$('#improveFooter').height();
    var nbzxDivHeight = height2- height1 -20;
    
    $(function(){
    	
    	<%-- $('.yj_custom').find('div').click(function(){
    		openMenu(this, 'wdtx', "<%=request.getContextPath()%>/oa/oaRemindInformation!recipientList.do?s_bizType=0");
    	}); --%>
    	
    	$('#unReadRemindNumSpan').click(function(){
    		openMenu(this, 'wdtx', "<%=request.getContextPath()%>/oa/oaRemindInformation!recipientList.do?s_bizType=0");
    	});
    	
    	$('#unReadEmailNumSpan').click(function(){
    		openMenu(this, 'wdyj', "<%=request.getContextPath()%>/oa/mailinfo!mainPanel.do");
    	});
    	
    	$('.gg_custom').find('div').click(function(){
    		<%-- openMenu(this, 'wdtz', "<%=request.getContextPath()%>/oa/oaInformation!mainlist.do?notread=T&flag=GGZY"); --%>
    		openMenu(this, 'wdlw', "<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_readstate=F");
    	});
    	
    	$('.sw_custom').find('div').click(function(){
    		openMenu(this, 'wdsw', "<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW");
    	});
    	
    	$('.fw_custom').find('div').click(function(){
    		openMenu(this, 'wdfw', "<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=FW");
    	});
    	$('.zxsw_custom').find('div').click(function(){
    		openMenu(this, 'zxswdb', "<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW");
    	});
    	
    	$('.zxfw_custom').find('div').click(function(){
    		openMenu(this, 'zxfwdb', "<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=FW");
    	});
    	getUserCountOnline();  // 获取在线人数
    	
    	sydb(); //待办
    	
    	nbzx(); // 内部资讯
    	
    	kuang();
    	
    	$('#div_list').height(nbzxDivHeight);
    	
    	//刷新页面
    	setInterval("refresh()",100000);
    	
    });
    //动态获取页面数据
    function refresh(){
    	 sydb();
    	 nbzx();
    	 ajaxnotreadNum();
    }
    
    // 屏幕大小变化时调整内部资讯div的高度
    window.onresize = function(){
    	
    	var height1 = $('.nbzx_custom').offset().top;
    	var height2 = $('#improveFooter').offset().top -$('#improveFooter').height();
    	
    	$('#div_list').height(height2-height1-20);
    	$('#nbzx').css('margin-top',0);
    }
    function omitText_new(src){
    /* 	if(src.length>40){
    		src=src.substring(0,60);
    		src+="...";
    	} */
    	return src;
    }
    
    function changeTime(time){
    	var s=time.substr(5,5);
    	return s;
    }

    function sydb(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!sydb_new.do?acType=auto",
    		type:"post",
    		dataType:"json",
    		success:function(date){
    			
    			var list=date;
    			$("#dwbl").html("");
    			 $.each(list,function(i,o){
    				 var item;
    				 item="<tr>";
  					if(o.criticalLevel!="0"){
  						item+="<td style='padding:0 0 0 5px;;width:62px;' ";
  					if(o.criticalLevel=="1"){
  						item+="><span style='width:57px;background-color: green;color: white;'>【平急】</span>";
  					}else if(o.criticalLevel=="2"){
  						item+="><span style='background-color: #00ADEF;color: white;width:57px;'>【加急】</span>";
  					}else if(o.criticalLevel=="3"){
  						item+="><span style='background-color: #FABC09;color: white;width:57px;'>【特提】</span>";
  					}else if(o.criticalLevel=="4"){
  						item+="><span style='background-color: #F1521B;color: white;width:57px;'>【特急】</span>";
  					}else if(o.criticalLevel=="5"){
  						item+="><span style='background-color: red;color: white;width:57px;'>【急件】</span>";
  					}else{
  						item+=">";
  						}
  					item+="</td>";
  					}
  					item+="<td ";
  					if(o.criticalLevel=="0"){
  						item+=" colspan='2' style='width:75%'";
  					}else{
  						item+="style='width:60%' ";
  					}
  					
  					item+=">"+
						"<span class='mark'>【"+o.itemtype+"】</span>"+
						"<a id='dwbl"+i+"' href='javaScript:void(0);retrun false;'>"+
						"<span style='color:black;' title='"+o.transaffairname+"'>"+o.transaffairname+"</span></a></td>";
						/* 办理步骤 */
						item+="<td style='width:120px;float: right;text-align:left '><span>"+o.nodeName+"</span></td>";
						/*阅读状态 */
						item+="<td style='width:72px;'> ";
    					if(o.readstate=="已读"){
    						item+="<span class='read'><span style='color:green; ";
    					}else{
    						item+="<span class='notread'><span style='color:orange; ";
    					}
    					item+="float: right;'>【"+
    					o.readstate+ 
    					"】</span></td>";
    					/*时间  */
					item+="<td style='width:108px;'><span class='date' title='"+formatString(o.nodeCreateTime)+"'>"+formatTime(o.nodeCreateTime)+"</span></td>"+
					"</tr>";
					if(i<=6){
						$("#dwbl").append(item);
						var id="dwbl"+i;
						$('#' + id).click(function(){
							openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + o.nodeOptUrl+'%26dashboard%3DT' ,o.itemtype);	
							return false;
						});						
					};
    			}) ;
    		}
    	});
    }
    
    function nbzx(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!nbzx_dashboard.do?acType=auto",
    		type:"post",
    		async: false,
    		dataType:"json",
    		success:function(data){
    			var list=data;
    			$("#nbzx").html('');
    			$.each(list,function(i,o){
    				 var item;
    				 item="<li>";
    				 if('通知' == o.infoType){
    					 item += "<div class='tz_label' >";
    				 }else if('公告' == o.infoType){
    					 item += "<div class='gg_label'>";
    				 }else{
    					 item += "<div class='xw_label'>";
    				 }
    				 
    				 item += "【" + o.infoType + "】</div><a id='nbzx"+i +"'";
    				 
    				 if('未读' == o.readstate){
    					 item += ' class="weidu_a"';
    				 }
    				 
    				 item += ">";
    				 if('T' == o.isTop){
    					 item +=' <img style="vertical-align: middle;" src="<%=request.getContextPath()%>/newStatic/image/zhiding.png" border="0" />';
    				 }
    				 item+= o.title+"</a></li>";
					
					if(i<=10){
						$("#nbzx").append(item);
						var id="nbzx"+i;
						$('#' + id).click(function(){
							openMenu(this, 'nbzx', '<%=request.getContextPath()%>/oa/oaInformation!view.do?no=' + o.no +'&flag=GGZY' ,o.infoType );	
							return false;
						});	
						
					};
    			}) ;
    			scroll_div();
    		}
    	});
    }
    //获取页面显示数量
    function ajaxnotreadNum(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!ajaxDashboardNum.do?acType=auto",
    		type:"post",
    		dataType:"json",
    		success:function(data){
    			for(var key in data){
    				if(data.hasOwnProperty(key)){
    						if(key=="unReadEmailNumSpan"){
    							$("#"+key).html("未读邮件&nbsp;&nbsp;"+data[key]);
    						}else if(key=="unReadRemindNumSpan"){
    							$("#"+key).html("内部提醒 &nbsp;&nbsp;"+data[key]);
    						}else{
    							$("#"+key).html(data[key]);
    						}
    				}
    			}
    		}
    	});
    }
    
    // 文字滚动
   function scroll_div(){
	   
	   	$(".div_list").myScroll({
	   		speed:40, //数值越大，速度越慢
	   		rowHeight:35 //li的高度
	   	});
   }
  
	//根据业务数据动态设置业务快捷入口高度
	function kuang(){
   	 var num=<%=s%>/6;
   	 if(num<=1){
     		$('#attAlert_yw').css('height','1117');
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

	function formatTime(date){
		var times=date.split("-");
		var changetime=times[0]+"年"+times[1]+"月"+times[2].substring(0,2)+"日";
		return changetime;
	}
	function formatString(str){
		var reg=new RegExp("T","g"); //创建正则RegExp对象  
		var newstr=str.replace(reg," ");//装T替换成空格
		return newstr;
	}
</script>