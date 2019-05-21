<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />

<div class="improveLeft">
	<div class="leftInfo">
		<div class="leftCont">
			<div>
				<i class="i-l"></i>
				<div class="childBlock" >
					<div class="childBlockTile">
						
						<div class="childBlockTile_left">
							<div id="tongzhi-div"
								class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('tongzhi');"
								style="background: url('../themes/default/improve/6_06.png') no-repeat left 10px;">通知公告</div>
							<c:if test="${cp:HASOPTPOWER('GRBGLDRCAP') }">
								<i></i>
								<div id="leader-div" class="childBlockTile_left1"
									onclick="switchBlock('leader');">领导日程</div>
							</c:if>
							<c:if test="${cp:HASOPTPOWER('GRBGGRRCAP') }">
								<i></i>
								<div id="personal-div" class="childBlockTile_left1"
									onclick="switchBlock('personal');">个人日程</div>
							</c:if>
						</div>
						<div class="childBlockTile_right">
							<a class="chooseRCType" target="navTab" external="true"
								title="通知公告" rel="external_TZGG_GGZY"
								href="../oa/oaInformation!list.do?infoType=info&flag=GGZY">
								<span>更多</span>
							</a>
						</div>
					
					</div>


					<!-- 通知公告 -->
					<%-- <c:if test="${cp:HASOPTPOWER('TZGG_') }"> --%>
						<table style="width: 98%;" id="tongzhi">
							<c:forEach items="${tzggList}" varStatus="i" var="o" end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td class="itemTitle"><a 
										title="${o.title}"  onclick="popWin2('<%=request.getContextPath()%>/oa/oaInformation!view.do?no=${o.no}&flag=GGZY&show_type=F','通知公告'); return false;"
										href='javaScript:void(0);'>${fn:length(o.title)
											>16 ? fn:substring(o.title, 0,16): o.title}</a></td>
									<td class="itemTime"><fmt:formatDate
											value='${o.releaseDate}' pattern='yyyy-MM-dd' />
									</td>
								</tr>
							</c:forEach>
						</table>
					<%-- </c:if> --%>

					<!-- 领导日程 -->
					<c:if test="${cp:HASOPTPOWER('GRBGLDRCAP') }">
						<table style="width: 100%; display: none;" id="leader">
							<c:forEach items="${ldrcList}" varStatus="i" var="o" end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td style="text-align: left;width: 67%" title="${o.title }"
										class="itemTitle" ><a 
										title="领导日程" onclick="popWin2('<%=request.getContextPath()%>/oa/oaSchedule!view.do?schno=${o.schno}&ec_p=${ec_p}&ec_crd=${ec_crd}&s_sehType=2&show_type=F','领导日程');return false;"
										href="javaScript:void(0);">
											<span title="${o.title }">${fn:length(o.title) > 15 ?
												fn:substring(o.title, 0, 15) : o.title }</span>
									</a></td>
									<td class="itemTime" style="width: 33%"><fmt:formatDate
											value='${o.planBegTime}' pattern='MM-dd' /> <c:if
											test="${not empty o.dateTag and  o.dateTag ne '' }"> (${o.dateTag}) </c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<!-- 个人日程安排 -->
					<c:if test="${cp:HASOPTPOWER('GRBGGRRCAP') }">
						<table style="width: 100%; display: none;" id="personal">
							<c:forEach items="${gerenrcList}" varStatus="i" var="o" end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td style="text-align: left;width: 67%" title="${o.title }"
										class="itemTitle"><a 
										title="个人日程安排"  onclick="popWin2('<%=request.getContextPath()%>/oa/oaSchedule!view.do?schno=${o.schno}&ec_p=${ec_p}&ec_crd=${ec_crd}&s_sehType=1&show_type=T','个人日程');return false;"
										href="javaScript:void(0);">
											<span title="${o.title }">${fn:length(o.title) > 15 ?
												fn:substring(o.title, 0, 15) : o.title }</span>
									</a></td>
									<td class="itemTime" style="width: 33%"><fmt:formatDate
											value='${o.planBegTime}' pattern='MM-dd' /> <c:if
											test="${not empty o.dateTag and  o.dateTag ne '' }"> (${o.dateTag})</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<i class="i-r"></i>
			</div>
			<div>
				<i class="i-l r-l"></i>
				<div class="childBlock" >
					<div class="childBlockTile">
						<div class="childBlockTile_left">

						<c:if test="${empty userRank}">
							<!-- <div class="test3">待办事项</div> -->
							<div id="daiban-div" class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('daiban');"
								style="background: url('../themes/default/improve/2_14.png') no-repeat left 10px;">待办事项</div>
							<c:if test="${cp:HASOPTPOWER('SWGDCK') }">	
							 <i ></i>
							<div id="shouwen-div" class="childBlockTile_left1" onclick="switchBlock('shouwen');"
								>部门收文</div> 
							</c:if>
							<div class="childBlockTile_right">
								<!-- <i class="icon icon-green icon-refresh"></i>
					      <i class="icon icon-green icon-add"></i> -->
							</div>
						</c:if>


						<c:if test="${not empty userRank}">
							<div class="childBlockTile_left">
								<!-- <div class="test3">待办事项</div> -->
								<div id="daiban-div1"
									class="childBlockTile_left1 document-switch-hover"
									onclick="switchBlock('daiban1');"
									style="background: url('../themes/default/improve/2_14.png') no-repeat left 10px;">待办事项</div>
								<c:if test="${cp:HASOPTPOWER('SWGDCK') }">
								 <i ></i>
								<div id="shouwen-div1" class="childBlockTile_left1" onclick="switchBlock('shouwen1');"
								>部门收文</div> 
								</c:if>
								<!-- 						添加统计 -->
								<i></i>
								<div id="tongji-div-zbj" class="childBlockTile_left1"
									onclick="switchBlock('tongji-zbj');">在办件统计</div>
								<i></i>
								<div id="tongji-div-ybj" class="childBlockTile_left1"
									onclick="switchBlock('tongji-ybj');">已办件统计</div>
							</div>

						</c:if>
						</div>
						<div class="childBlockTile_right">
						<!-- 收文更多 --> 
							<c:if test="${cp:HASOPTPOWER('SWGDCK') }">
							<div id="shouwen_right" style="display: none;">
								<a target="navTab" external="true" title="部门收文"
							rel="external_SWGDCK" href="../oa/oaUnitIncomedoc!list.do"> <span>更多</span></a>
							</div>
							</c:if>
							<!-- <i class="icon icon-green icon-refresh"></i>
					      <i class="icon icon-green icon-add"></i> -->
						</div>
						

					</div>
					<div id="tongji-zbj" style="display: none;">
						<!-- 办件统计依据登录人员行政级别显示 -->
						<c:if test="${userRank eq 'TZ'}">
							<div>
							
								<iframe id="bjtj-zbj"
									src="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJTLD&bizstate=W"
									border="0" marginwidth="0" width="100%" frameborder="no"
									scrolling="yes"> </iframe>
							</div>
						</c:if>

						<c:if test="${userRank eq 'CZ'}">
							<div>
	
								<iframe id="bjtj-zbj"
									src="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJCLD&bizstate=W"
									border="0" marginwidth="0" width="100%" height="300px"
									frameborder="no" scrolling="no"> </iframe>
							</div>
						</c:if>
					</div>


					<div id="tongji-ybj" style="display: none;">
						<!-- 办件统计依据登录人员行政级别显示 -->
						<c:if test="${userRank eq 'TZ'}">
							<div>
								<iframe id="bjtj-ybj"
									src="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJTLD&bizstate=C"
									border="0" marginwidth="0" width="100%" frameborder="no"
									scrolling="yes"> </iframe>
							</div>
						</c:if>

						<c:if test="${userRank eq 'CZ'}">
							<div>
								<div class="container" style="padding-top: 1px; width: 97%"></div>
								<iframe id="bjtj-ybj"
									src="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJCLD&bizstate=C"
									border="0" marginwidth="0" width="100%" height="300px"
									frameborder="no" scrolling="no"> </iframe>
							</div>
						</c:if>
					</div>
					<!-- 统计 end-->


					<div id="daiban">
						<%
							int i = 0;
						%>
						<div class="childBlockCont"
							style="border-bottom: 2px solid #e9e9e9;">
							<s:iterator value="dashboardList" status="status" var="i">

								<!-- 收文待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('SWGLSWDB') and i.type eq 'SW'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" external="true" title="${i.name}"
											rel="external_SWGLSWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-1.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">收文待办</span>
										</a>
									</div>
								</c:if>

								<!-- 发文待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('FWGLFWDB') and i.type eq 'FW'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" external="true" title="${i.name}"
											rel="external_FWGLFWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-2.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">发文待办</span>
										</a>
									</div>
								</c:if>

								<!-- 督查待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('DBHF') and i.type eq 'DCDB'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" external="true" title="${i.name}"
											rel="external_DBHF"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-3.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">督查待办</span>
										</a>
									</div>
								</c:if>

								<!-- 会议申请待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('HYSSYDB') and i.type eq 'HYSQ'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" rel="external_HYSSYDB" external="true"
											title="${i.name}"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-4.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">会议申请</span>
										</a>
									</div>
								</c:if>
							</s:iterator>
						</div>


						<div class="childBlockCont">
							<s:iterator value="dashboardList" status="status" var="i">

								<!-- 内部事权待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('NBSXSXBL') and i.type eq 'SQ'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" external="true" title="${i.name}"
											rel="external_NBSXSXBL"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-5.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">内部事权</span>
										</a>
									</div>
								</c:if>

								<!-- 签报待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('QBDB') and i.type eq 'QB'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" external="true" title="${i.name}"
											rel="external_QBDB"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-6.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">签报待办</span>
										</a>
									</div>
								</c:if>

								<!-- 车辆申请待办菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('CLSYDB') and i.type eq 'CARSQ'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" rel="external_CLSYDB" external="true"
											title="${i.name}"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=${i.type}&isShow=T'
											style="background: url('../themes/default/improve/daiban-7.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">车辆申请</span>
										</a>
									</div>
								</c:if>
								<!-- 我的提醒菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('WDTX') and i.type eq 'WDTX'}">
									<%
										i++;
									%>
									<div>
										<a target="navTab" rel="external_WDTX" external="true"
											title="${i.name}"
											href='<%=request.getContextPath()%>/oa/oaRemindInformation!recipientList.do?s_bizType=0'
											style="background: url('../themes/default/improve/daiban-9.png') no-repeat center center; text-decoration: none;">
											<label id="td_${i.type}_name">${i.sum}</label> <span
											id="td_${i.type}_sum">我的提醒</span>
										</a>
									</div>
								</c:if>
							</s:iterator>
						</div>
					</div>
					<!-- 部门收文 -->
					<div id="shouwen" style="display: none;">
						<c:if test="${cp:HASOPTPOWER('SWGDCK') }">
						<table style="width: 98%; ">
						<c:forEach items="${bmswList}" varStatus="i" var="o" end="4">
							<tr>
								<td class="itemTitle"><a target="navTab" external="true"
									title="部门收文" rel="external_SWGDCK"
									href='<%=request.getContextPath()%>/dispatchdoc/incomeDoc!generalOptView.do?djId=${o.no }&nodeInstId=0&show_type=F'>
										<span title="${o.incomedDocTitle }">${fn:length(o.incomedDocTitle)
											> 16 ? fn:substring(o.incomedDocTitle, 0, 16) :
											o.incomedDocTitle }</span>
								</a></td>
								<td class="itemTime"><fmt:formatDate
										value="${o.incomeDate }" pattern="yyyy-MM-dd" />
								</td>
							</tr>
						</c:forEach>
					</table>
					
					</c:if>
					
					</div>
					<!-- end -->
					
					
					
				</div>
				<i class="i-r r-r"></i>
			</div>
		</div>
		<div class="leftHead" style="padding:0">
			<div class="leftTitle leftTitle_custom">
				<div style="float:right">
					<div class="mail">
						<div>
							<a class="wdyj" 
									title="未读邮件" target="navTab" external="true"
									href="<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I&s_msgstate=U&isShow=T"
									style="margin-left: 0;">未读邮件<span>${dashboardList[0].sum}</span></a> 
							<a class="fjx"<%--  onclick="popWin('<%=request.getContextPath()%>/oa/innermsg!list.do?s_mailtype=O&s_msgtype=P&s_NP_senddate=true&isShow=T','发件箱');return false;" --%>
									title="发件箱" target="navTab" external="true"
									href="<%=request.getContextPath()%>/oa/innermsg!list.do?s_mailtype=O&s_msgtype=P&s_NP_senddate=true&isShow=T">发件箱<span>${dashboardList[1].sum}</span></a>
							<a class="cgx"<%--  onclick="popWin('<%=request.getContextPath()%>/oa/innermsg!list.do?s_msgtype=P&s_mailtype=D&isShow=T','草稿箱');return false;" --%>
									title="草稿箱" target="navTab" external="true"
									href="<%=request.getContextPath()%>/oa/innermsg!list.do?s_msgtype=P&s_mailtype=D&isShow=T">草稿箱<span>${dashboardList[2].sum}</span></a> 
								<%-- <a target="navTab" external="true" rel="external_SJX"
									title="${dashboardList[4].name}"
									href='<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I'>收件箱（${dashboardList[4].sum}）</a> --%>
						</div>
					</div>
				</div>
				<div style="float:left">
					<!--轮播开始 -->
					<div id="tipTitle" class="imgfocus">
								<!--轮播图片部分开始 -->
								<div id="decoimg_a2" class="imgbox">
									<div class="decoimg_b2" style="height:63px;margin-left:-10px;background: url('../themes/default/improve/01.gif')"><a href="javascript:void(0)" hidefocus="true" target="_self"></a></div>
				<!-- 					<div class="decoimg_b2" style="height:63px;margin-left:-10px;background: url('../themes/default/improve/02.jpg')"><a href="javascript:void(0)" hidefocus="true" target="_self"></a></div>
									<div class="decoimg_b2" style="height:63px;margin-left:-10px;background: url('../themes/default/improve/03.jpg')"><a href="javascript:void(0)" hidefocus="true" target="_self"></a></div> -->
								</div>
								<!--轮播图片部分结束 -->
								
								<!--轮播小圆点部分开始 -->
							<!-- 	<ul id="deconum2" class="num_a2">
									<li><a href="javascript:void(0)" onclick="return false;" hidefocus="true" target="_self"></a></li>
									<li><a href="javascript:void(0)" hidefocus="true" onclick="return false;" target="_self"></a></li>
									<li><a href="javascript:void(0)" hidefocus="true" onclick="return false;" target="_self"></a></li>
								</ul> -->
								<!--轮播小圆点部分结束 -->
								
					</div>
					<!--轮播结束-->
				</div>
			</div>
	    </div>
		<div class="leftCont">
			<div>
				<i class="i-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="geren-div"
								class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('geren');"
								style="background: url('../themes/default/improve/5_08.png') no-repeat left 10px;">个人文档</div>
							<i></i>
							<div id="bumen-div" class="childBlockTile_left1"
								onclick="switchBlock('bumen');">部门文档</div>
						</div>
						<div class="childBlockTile_right">
							<a class="chooseType" target="navTab" external="true"
								title="个人文档" rel="external_GRBGGRWD"
								href="../app/publicinfo!listFile.do?mode=PERSONFILE"> <span>更多</span>
							</a>
						</div>
					</div>

					<!-- 个人文档 -->
					<c:if test="${cp:HASOPTPOWER('PERSONFILE') }">
						<table style="width: 100%;" id="geren">
							<c:forEach items="${personalFileList}" varStatus="i" var="o"
								end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td class="itemTitle" style="width: 83%"><c:choose>
											<c:when test="${'1' eq o.isfolder }">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
												<a target="navTab" external="true" title="个人文档" 
													rel="external_GRBGGRWD" 
													href='<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=PERSONFILE&path=${o.infocode}'>${fn:length(o.filename)
													> 17 ? fn:substring(o.filename, 0 ,17) : o.filename}</a>
											</c:when>
											<c:otherwise>
												<span class="icon document"
													style="background: url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important "></span>&nbsp;&nbsp;
												<a onclick="downloadFile('PERSONFILE',${o.infocode});return false;"  
													href='javascript:void(0);'>${fn:length(o.filename) > 17 ?
													fn:substring(o.filename, 0, 17) : o.filename}</a>
											</c:otherwise>
										</c:choose></td>
									<td class="itemTime" style="width: 17%"><fmt:formatDate
											value='${o.uploadtime}' pattern='MM-dd' />
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<!-- 部门文档 -->
					<c:if test="${cp:HASOPTPOWER('GRBGBMWD') }">
						<table style="width: 100%; display: none;" id="bumen">
							<c:forEach items="${unitFileList}" varStatus="i" var="o" end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td class="itemTitle" style="width: 83%"><c:choose>
											<c:when test="${'1' eq o.isfolder }">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
												<a target="navTab" external="true" title="部门文档"
													rel="external_GRBGBMWD"
													href='<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=GRBGBMWD&path=${o.infocode}'>${fn:length(o.filename)
													> 17 ? fn:substring(o.filename, 0 ,17) : o.filename}</a>
											</c:when>
											<c:otherwise>
												<span class="icon document"
													style="background: url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important "></span>&nbsp;&nbsp;
												<a onclick="downloadFile('GRBGBMWD',${o.infocode});return false;"
													href='javascript:void(0);'>${fn:length(o.filename) > 17 ?
													fn:substring(o.filename, 0, 17) : o.filename}</a>
											</c:otherwise>
										</c:choose></td>
									<td class="itemTime" style="width: 17%"><fmt:formatDate
											value='${o.uploadtime}' pattern='MM-dd' />
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<i class="i-r"></i>
			</div>
			<div>
				<i class="i-l r-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left1 document-switch-hover"
							style="background: url('../themes/default/improve/4_10.png') no-repeat left 10px;">资料库</div>
						<div class="childBlockTile_right">
							<!-- <i class="icon icon-green icon-refresh"></i>
							<i class="icon icon-green icon-add"></i> -->
							<a target="navTab" external="true" title="资料库"
								rel="external_PUBLICFILE"
								href="../app/publicinfo!listFile.do?mode=PUBLICFILE"> <span>更多</span>
							</a>
						</div>
					</div>

					<!-- 资料库 -->
					<c:if test="${cp:HASOPTPOWER('PUBLICFILE') }">
						<table style="width: 100%;">
							<c:forEach items="${fileList}" varStatus="i" var="o" end="4">
								<tr ${i.index % 2 !=0 ? ' class="even"' : ''}>
									<td class="itemTitle" style="width: 83%"><c:choose>
											<c:when test="${'1' eq o.isfolder }">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
												<a target="navTab" external="true" title="资料库"
													rel="external_PUBLICFILE"
													href='<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=PUBLICFILE&path=${o.infocode}'>${fn:length(o.filename)
													> 19 ? fn:substring(o.filename, 0 ,19) : o.filename}</a>
											</c:when>
											<c:otherwise>
												<span class="icon document"
													style="background: url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important "></span>&nbsp;&nbsp;
												<a onclick="downloadFile('PUBLICFILE',${o.infocode});return false;"
													href='javascript:void(0);'>${fn:length(o.filename) >17 ?
													fn:substring(o.filename, 0, 17) : o.filename}</a>
											</c:otherwise>
										</c:choose></td>
									<td class="itemTime" style="17%"><fmt:formatDate
											value='${o.uploadtime}' pattern='MM-dd' />
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<i class="i-r r-r"></i>
			</div>
		</div>
	</div>
</div>
<div class="improveRight">
	<div class="rightInfo">
		
		
		<div class="rightCont3" style="margin-top: 5px;">

			<!-- 日历 -->
			<iframe
				src="${pageContext.request.contextPath}/page/app/calendar.html"
				border="0" marginwidth="0" width="99%" height="250px"
				frameborder="no" scrolling="no"></iframe>

			
		</div>
	
	
	
	    <div class="inter"  style="margin-top:10px;background:url('../themes/default/improve/inter_1.jpg');height: 235px" > 
    
    <table style="width: 100%">
    <tr><td><br/><br /><br /></td></tr>
     <tr>
    					<%
							int z = 0;
						%>
								<!-- 收文登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
									<%
										z++;
									%>
									<td >
										<a target="navTab" external="true"  
											rel="external_SWGLSWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=XJ370000FG-SW-0001'
											style="background: url('../themes/default/improve/con_inter_01.png') no-repeat center 5px; text-decoration: none;border:0 none">
											<span style="color: white;"
											><br/><br/><br/>收文登记</span>
										</a>
									</td>
								</c:if>

								<!-- 发文登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
									<%
										z++;
									%>
									<td>
										<a target="navTab" external="true" 
											rel="external_FWGLFWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard'
											style="background: url('../themes/default/improve/con_inter_02.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>发文登记</span>
										</a>
									</td>
								</c:if>

								<!-- 督查发起菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('DBFQ') }">
									<%
										z++;
									%>
									<td>
										<a target="navTab" external="true" 
											rel="external_DBHF"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!selectDbList.do'
											style="background: url('../themes/default/improve/con_inter_03.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>督办发起</span>
										</a>
									</td>
								</c:if>

								<!-- 会议申请发起菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('HYSSYDJ') }">
					<%if(z%3==0){ %> 	
						</tr> 
						<tr class="childBlockCont">
							 <%} %> 	
									<%
										z++;
									%>
									<td>
										<a target="navTab" rel="external_HYSSYDJ" external="true"
											href='<%=request.getContextPath()%>/oa/oaMeetApply!list.do?s_biztype=F&show_type=F'
											style="background: url('../themes/default/improve/con_inter_04.png') no-repeat center 5px; text-decoration: none;border:0 none">
											<span style="color: white;"
											><br/><br/><br/>会议登记</span>
										</a>
									</td>
								</c:if>

								<!-- 内部事权发起菜单权限 -->
								
								<c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
						 <%if(z%3==0){ %> 	
						</tr> 
						<tr class="childBlockCont">
							 <%} %> 
									<%
										z++;
									%>
									<td>
										<a target="navTab" external="true"
											rel="external_NBSXSXBL"
											href='<%=request.getContextPath()%>/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1'
											style="background: url('../themes/default/improve/con_inter_08.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>事权登记</span>
										</a>
									</td>
								</c:if>
								<!-- 签报登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('QBDJ') }">
					 <%if(z%3==0){ %> 	
						</tr> 
						<tr class="childBlockCont">
							 <%} %> 
									<%
										z++;
									%>
									<td>
										<a target="navTab" external="true" "
											rel="external_QBDB"
											href='<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard'
											style="background: url('../themes/default/improve/con_inter_06.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>签报登记</span>
										</a>
									</td>
								</c:if>
								
								<!-- 车辆申请登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('CLSYDJ') }">
						 <%if(z%3==0){ %> 	
							</tr> 
							<tr class="childBlockCont">
							 <%} %> 
									<%
										z++;
									%>
									<td>
										<a target="navTab" rel="external_CLSYDB" external="true"
											href='<%=request.getContextPath()%>/oa/oaCarApply!list.do?s_biztype=F&show_type=F'
											style="background: url('../themes/default/improve/con_inter_07.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>申请用车</span>
										</a>
									</td>
								</c:if> 
								
						
								<!-- 我的提醒菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('YFSTX') }">
						 <%if(z%3==0){ %> 	
						</tr> 
						<tr class="childBlockCont">
							 <%} %> 		
									<%
										z++;
									%>
									<td>
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?dashboard=dashboard'
											style="background: url('../themes/default/improve/faqitixing.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>发起提醒</span>
										</a>
									</td>
								</c:if>
								
								<%if(z%3==0){ %> 	
						</tr> 
						<tr class="childBlockCont">
							 <%} %> 		
									<%
										z++;
									%>
								
								<td onclick="return false;">
									<a 
											href="javascript:void(0);"
											onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"
											style="background: url('../themes/default/improve/con_inter_add.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>更多</span>
										</a>
								</td>
						 </tr> 
     </tr>
    </table>
   </div>
		
		<div class="rightCont2" style="margin-top: 15px;">
			
			<!-- 学习园地 -->
			<a class="learn"
				href="${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsIframe.do"
				title="学习园地" target="navTab" rel="external_XXYD" external="true"></a>
		</div>
	</div>
</div>
<div id="improveFooter">
	<div>
	版权所有：新疆维吾尔自治区交通运输厅	
	</div>
	<div>技术支持：江苏南大先腾信息产业有限公司 </div>
</div>

<style> 
 .black_overlay{  
 	 display: none;  
 	 position: absolute;
     top: 0%;  left: 0%;  
     width: 400;  
     height: 327%; 
     background-color: black;
     z-index:1001;  
     -moz-opacity: 0.8;
     opacity:.80; 
     filter: alpha(opacity=80);  }
.white_content {  
     display: none;  
     position: absolute;  
     top: 25%;  left: 25%;  
     width: 40%;  height: 400px;  
     padding: 5px;  border: 2px solid #196DB3;  
     background-color: white;  z-index:1002;  
     overflow: auto;  }  
 </style> 
 
<div id="light" class="white_content"> 
	<div class="childBlock" id="faqi_content" style="height: 370px;">
    	<div id="faqi-more" name="faqi-more"  style="background-color: #196DB3;height:365px" >
					
						<%
							int s = 0;
						%>
						<div  class="childBlockCont_faqi"
							style="border-bottom: 2px solid #e9e9e9;">
							<%-- <s:iterator value="dashboardList" status="status" var="i"> --%>

							

								<!-- 收文登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
									<%
										s++;
									%>
									<div >
										<a target="navTab" external="true"  
											rel="external_SWGLSWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=XJ370000FG-SW-0001'
											style="background: url('../themes/default/improve/con_inter_01.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" external="true" 
											rel="external_FWGLFWDB"
											href='<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard'
											style="background: url('../themes/default/improve/con_inter_02.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>发文登记</span>
										</a>
									</div>
								</c:if>

								<!-- 督查发起菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('DBFQ') }">
									<%
										s++;
									%>
									<div>
										<a target="navTab" external="true" 
											rel="external_DBHF"
											href='<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!selectDbList.do'
											style="background: url('../themes/default/improve/con_inter_03.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>督办发起</span>
										</a>
									</div>
								</c:if>

								<!-- 会议申请发起菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('HYSSYDJ') }">
									<%
										s++;
									%>
									<div>
										<a target="navTab" rel="external_HYSSYDB" external="true"
											
											href='<%=request.getContextPath()%>/oa/oaMeetApply!list.do?s_biztype=F&show_type=F'
											style="background: url('../themes/default/improve/con_inter_04.png') no-repeat center 3px; text-decoration: none;border:0 none">
											<span
											><br/><br/><br/>会议登记</span>
										</a>
									</div>
								</c:if>
							
								<c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
						 
									<%
										s++;
									%>
									<div>
										<a target="navTab" external="true"
											rel="external_NBSXSXBL"
											href='<%=request.getContextPath()%>/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1'
											style="background: url('../themes/default/improve/con_inter_08.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" external="true" "
											rel="external_QBDB"
											href='<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard'
											style="background: url('../themes/default/improve/con_inter_06.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>签报登记</span>
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
										<a target="navTab" rel="external_CLSYDB" external="true"
											href='<%=request.getContextPath()%>/oa/oaCarApply!list.do?s_biztype=F&show_type=F'
											style="background: url('../themes/default/improve/con_inter_07.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>申请车辆</span>
										</a>
									</div>
								</c:if> 
								
						
								<!-- 我的提醒菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('YFSTX') }">
									<%
										s++;
									%>
									<div>
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?dashboard=dashboard'
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&dashboard=dashboard'
											style="background: url('../themes/default/improve/fayoujian.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>发送邮件</span>
										</a>
									</div>
								</c:if>
								
								<%-- <!-- 请销假菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('FJX') }">
									<%
										s++;
									%>
									<div>
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I'
											style="background: url('../themes/default/improve/qingxiaojia.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>请销假(待完成)</span>
										</a>
									</div>
								</c:if> --%>
								
								
								<!-- 个人日程菜单权限 -->
								
									<%
										s++;
									%>
									<div>
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaSchedule!built.do?s_sehType=1&dashboard=dashboard'
											style="background: url('../themes/default/improve/con_inter_05.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaSchedule!built.do?s_sehType=2&dashboard=dashboard'
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaInformation!built.do?infoType=info&flag=GGZY&dashboard=dashboard'
											style="background: url('../themes/default/improve/fatongzhi.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/sampleflow/sampleFlowRelegate!list.do?grant=T&s_grant=T'
											style="background: url('../themes/default/improve/con_inter_08.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/addressbooks!list_new.do'
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/voaUnitArchiveIncomedoc!list.do '
											style="background: url('../themes/default/improve/swguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/voaUnitArchiveDispatchdoc!list.do'
											style="background: url('../themes/default/improve/fwguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaArchive!toDoadd.do?'
											style="background: url('../themes/default/improve/rgguidang.png') no-repeat center 3px; text-decoration: none;border:0 none">
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
										<a target="navTab" rel="external_WDTX" external="true"
											href='<%=request.getContextPath()%>/oa/oaSmssend!editSend.do?dashboard=dashboard'
											style="background: url('../themes/default/improve/faduanxing.png') no-repeat center 3px; text-decoration: none;border:0 none">
											 <span
											><br/><br/><br/>发送短信</span>
										</a>
									</div>
								</c:if>
							
							
							</div>
							
		</div>
	</div>
					

	

	<div align="right">
	<input type="button" value="关闭" class="btn" onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
	</div>
</div> 
<div id="fade" class="black_overlay"> 
</div> 
<iframe id="downloadBox" height="0"></iframe>
    

<script type="text/javascript">


function changeUrl(t){
	if('TZ'=='${userRank}'){
	   	if(t=='W'){
	   		$("#bjtj-zbj").attr("src","${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJTLD&bizstate=W");
	   	}if(t=='C'){
	   		$("#bjtj-ybj").attr("src","${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJTLD&bizstate=C");
	   	}
	   	
	}
	if('CZ'=='${userRank}'){
	   	if(t=='W'){
	   		$("#bjtj-zbj").attr("src","${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJCLD&bizstate=W");
	   	}if(t=='C'){
	   		$("#bjtj-ybj").attr("src","${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName=DOABJTJCLD&bizstate=C");
	   	}
	}
	
   }


    function switchBlock(id){
    	
    	if(id=="geren"){
    		var v = $('a.chooseType');
    		
    		v.attr('title','个人文档');
    		v.attr('href','../app/publicinfo!listFile.do?mode=PERSONFILE');
    		
    		if($("#geren").is(":visible")){
    			return 
    		}
    		$("#bumen").hide();
    		$("#geren").show();
    		$("#bumen-div").removeClass("document-switch-hover");
    		$("#geren-div").addClass("document-switch-hover");
    	}
    	else if(id == "bumen"){
    		    	
    	    var v = $('a.chooseType');
    		v.attr('title','部门文档');
    		v.attr('href','../app/publicinfo!listFile.do?mode=GRBGBMWD');
    		
    		if($("#bumen").is(":visible")){
    			return 
    		}   
    		$("#geren").hide();
    		$("#bumen").show();
    		$("#geren-div").removeClass("document-switch-hover");
    		$("#bumen-div").addClass("document-switch-hover");
    	}
       else if( id == "tongzhi"){
    		
    		var v = $('a.chooseRCType');
     		v.attr('title','通知公告');
     		v.attr('href','../oa/oaInformation!list.do?infoType=info&flag=GGZY');
     		
     		if($("#tongzhi").is(":visible")){
     			return 
     		} 
     		$("#tongzhi").show();
     		$("#personal").hide();
     		$("#leader").hide();
     		$("#tongzhi-div").addClass("document-switch-hover");
     		$("#personal-div").removeClass("document-switch-hover");
     		$("#leader-div").removeClass("document-switch-hover");
    	}
    	else if( id == "leader"){
    		
    		var v = $('a.chooseRCType');
     		v.attr('title','领导日程');
     		v.attr('href','../oa/oaSchedule!generalOptView.do?s_sehType=2&s_canAdd=F');
     		
     		if($("#leader").is(":visible")){
     			return 
     		}   
     		$("#leader").show();
     		$("#personal").hide();
     		$("#tongzhi").hide();
     		$("#leader-div").addClass("document-switch-hover");
     		$("#personal-div").removeClass("document-switch-hover");
     		$("#tongzhi-div").removeClass("document-switch-hover");
    	}
    	else if(id == "personal"){
    		
    		var v = $('a.chooseRCType');
     		v.attr('title','个人日程安排');
     		v.attr('href','../oa/oaSchedule!generalOptView.do?s_sehType=1&s_canAdd=T');
     		
     		if($("#personal").is(":visible")){
     			return 
     		}   
     		$("#personal").show();
     		$("#leader").hide();
     		$("#tongzhi").hide();
     		$("#personal-div").addClass("document-switch-hover");
     		$("#leader-div").removeClass("document-switch-hover");
     		$("#tongzhi-div").removeClass("document-switch-hover");
    	}
    	//统计
    	else if(id=="daiban1"){
    	
    		if($("#daiban1").is(":visible")){
    			return 
    		}
    		$("#tongji-zbj").hide();
    		$("#tongji-ybj").hide();
    		$("#shouwen").hide();
    		$("#daiban").show();
    		$("#shouwen_right").hide();
    		$("#shouwen-div1").removeClass("document-switch-hover");
    		$("#tongji-div-zbj").removeClass("document-switch-hover");
    		$("#tongji-div-ybj").removeClass("document-switch-hover");
    		$("#daiban-div1").addClass("document-switch-hover");
    	}
    	else if(id == "tongji-zbj"){//bumen
    		    	
//     		if($("#tongji-zbj").is(":visible")){
//     			return 
//     		}
    		changeUrl("W");
    		$("#daiban").hide();
    		$("#shouwen").hide();
    		$("#tongji-zbj").show();
    		$("#tongji-ybj").hide();
    		$("#shouwen_right").hide();
    		$("#shouwen-div1").removeClass("document-switch-hover");
    		$("#daiban-div1").removeClass("document-switch-hover");
    		$("#tongji-div-ybj").removeClass("document-switch-hover");
    		$("#tongji-div-zbj").addClass("document-switch-hover");
    		
    	}
    	else if(id == "tongji-ybj"){//bumen
	    	
    		if($("#tongji-ybj").is(":visible")){
    			return 
    		}
    		changeUrl("C");
    		$("#daiban").hide();
    		$("#shouwen").hide();
    		$("#tongji-ybj").show();
    		$("#tongji-zbj").hide();
    		$("#shouwen_right").hide();
    		$("#daiban-div1").removeClass("document-switch-hover");
    		$("#shouwen-div1").removeClass("document-switch-hover");
    		$("#tongji-div-zbj").removeClass("document-switch-hover");
    		$("#tongji-div-ybj").addClass("document-switch-hover");
    		
    	}else if(id=="shouwen1"){
    	
    		if($("#shouwen").is(":visible")){
    			return 
    		}
    		$("#tongji-zbj").hide();
    		$("#tongji-ybj").hide();
    		$("#daiban").hide();
    		$("#shouwen").show();
    		$("#shouwen_right").show();
    		$("#daiban-div1").removeClass("document-switch-hover");
    		$("#tongji-div-zbj").removeClass("document-switch-hover");
    		$("#tongji-div-ybj").removeClass("document-switch-hover");
    		$("#shouwen-div1").addClass("document-switch-hover");
    	}else if(id=="shouwen"){
    	
    		if($("#shouwen").is(":visible")){
    			return 
    		}
    		$("#daiban").hide();
    		$("#shouwen").show();
    		$("#shouwen_right").show();
    		$("#daiban-div").removeClass("document-switch-hover");
    		$("#shouwen-div").addClass("document-switch-hover");
    	}else if(id=="daiban"){
    	
    		if($("#daiban").is(":visible")){
    			return 
    		}
    		$("#shouwen").hide();
    		$("#daiban").show();
    		$("#shouwen_right").hide();
    		$("#shouwen-div").removeClass("document-switch-hover");
    		$("#daiban-div").addClass("document-switch-hover");
    	}
    }
    
    function downloadFile(type,infocode){
    	
    	var href = "<%=request.getContextPath()%>/app/publicinfo!download.do";
    	$.post(href, {infocode: infocode, mode: type}, function(data){
    		downloadFileCallback(data, true);
		}, 'json');
    	
    }
    
    function downloadFileCallback(data, result) {
    	
		if (result !== true) return false;
		
		var callback = function(data) {
			if (!data || '0' != data.result) {
				alert(data.description);
				return false;
			}
			
			$("#downloadBox").attr("src","<%=request.getContextPath()%>/app/fileinfoFs!downloadfile.do?filecode="+data.filecode);
		};
		
		$.post('<%=request.getContextPath()%>/app/fileinfoFs!toDownloadfile.do', {
			filecode : data.filecode
		}, callback, 'json');
		
	}
    
    /**
    *待办板块布局重新渲染
    */
    function reRenderPendingLayout(){
    	//每一行的圆圈最大数
    	var everyRowChildrenSize = 4;
    	//获取第一行元素对象
    	var firstRowEle = $("#daiban").find(".childBlockCont").eq(0);
    	//获取第一行圆圈的数目
    	var divCountFirstRow = firstRowEle.children("div").length;
    	//获取第二行元素对象
    	var secondRowEle = $("#daiban").find(".childBlockCont").eq(1);
    	//获取第二行圆圈的数目
    	var divCountSecondRow = secondRowEle.children("div").length;
    	
    	if(divCountFirstRow < everyRowChildrenSize){
    		//第一行差几个圈满最大数
    		var diffCount  = everyRowChildrenSize - divCountFirstRow;
    		//从第二行补到第一行里去,差值与第二行的个数相比取最小值，作为移动数
    		var moveCount = (divCountSecondRow < diffCount) ? divCountSecondRow : diffCount;
    		if(moveCount > 0){
    			var willMoveEles = [];
    			for(var i = 0;i < moveCount;i++){
    				//不要在遍历的时候去移动元素，因为移动一个元素的下标就会变了， 就跟list在遍历删除元素同理
    				//所以这里定义一个容器来存储需要删除的元素对象
    				willMoveEles.push(secondRowEle.children().eq(i));
    			}
    			$.each(willMoveEles,function(){
    				firstRowEle.append(this);
    			});
    			//如果第二行已经没有元素，那么第一行的底部横线去除
    			if(secondRowEle.children("div").length == 0){
    				firstRowEle.css("border-bottom","none");
    			}
    		}
    	}
    	
    }
    
  
     /**
      *业务入口板块布局重新渲染
      */
      function faqiMorePendingLayout(){
      	//每一行的圆圈最大数
      	var everyRowChildrenSize = 6;
      	//获取第一行元素对象
      	var firstRowEle = $("#faqi-more").find(".childBlockCont_faqi").eq(0);
      	//获取第一行圆圈的数目
      	var divCountFirstRow = firstRowEle.children("div").length;
      	//获取第二行元素对象
      	var secondRowEle = $("#faqi-more").find(".childBlockCont_faqi").eq(1);
      	//获取第二行圆圈的数目
      	var divCountSecondRow = secondRowEle.children("div").length;
      	
      	if(divCountFirstRow < everyRowChildrenSize){
      		//第一行差几个圈满最大数
      		var diffCount  = everyRowChildrenSize - divCountFirstRow;
      		//从第二行补到第一行里去,差值与第二行的个数相比取最小值，作为移动数
      		var moveCount = (divCountSecondRow < diffCount) ? divCountSecondRow : diffCount;
      		if(moveCount > 0){
      			var willMoveEles = [];
      			for(var i = 0;i < moveCount;i++){
      				//不要在遍历的时候去移动元素，因为移动一个元素的下标就会变了， 就跟list在遍历删除元素同理
      				//所以这里定义一个容器来存储需要删除的元素对象
      				willMoveEles.push(secondRowEle.children().eq(i));
      			}
      			$.each(willMoveEles,function(){
      				firstRowEle.append(this);
      			});
      			//如果第二行已经没有元素，那么第一行的底部横线去除
      			if(secondRowEle.children("div").length == 0){
      				firstRowEle.css("border-bottom","none");
      			}
      		}
      	}
      	
      }
    function kuang(){
    	 var num=<%=s%>/6;
    	 if(num<=1){
      		$('#light').css('height','120');
      		$('#faqi-more').css('height','92');
			$('#faqi_content').css('height','100');
      	} else
		if(num<=2){
			$('#light').css('height','230');
			$('#faqi-more').css('height','182');
			$('#faqi_content').css('height','200');
      	}else
		if(num<=3){
			$('#light').css('height','335');
			$('#faqi-more').css('height','274');
			$('#faqi_content').css('height','290');
		}
      
    }
    $(function(){
    	reRenderPendingLayout();
    	faqiMorePendingLayout();
    	kuang();
    });
    function popWin(path,name){
		DialogUtil.open(name,path,1200,700,null,null,function(){
			$("#firstPage1",window.top.document).click();
		}); 
	}
    function popWin2(path,name){
		DialogUtil.open(name,path,1200,700); 
	}	
</script>