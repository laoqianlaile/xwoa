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
<div class="improveLeft">
	<div class="leftInfo">
		<div class="leftCont">
			<div>
				<i class="i-l"></i>
				<div class="childBlock" >
					<div class="childBlockTile">
						
						<div class="childBlockTile_left">
							<div id="dwbl-div" name="dwbl-div"
								class="childBlockTile_left1 document-switch-hover"
								onmouseover="switchBlock('dwbl');">
								
								待办事项<span id="dwbl-span" style="display: none">${nummap['1']}</span></div>
							<i></i>
							<div id="swdb-div" class="childBlockTile_left1" style="display: none;"
								onmouseover="switchBlock('swdb');">阅办文待办</div>
							<i id="swdb-div-i" style="display: none;"></i>
							<div id="fwdb-div" class="childBlockTile_left1" style="display: none;"
								onmouseover="switchBlock('fwdb');">拟发文待办</div>
							<i id="fwdb-div-i" style="display: none;"></i>
							<!-- <div id="jjcq-div" class="childBlockTile_left1" style="display: none;"
								onmouseover="switchBlock('jjcq');">即将超期<span id="jjcq-span" style="display: none">1</span></div>
							<i id="jjcq-div-i" style="display: none;"></i>
							<div id="ycq-div" class="childBlockTile_left1" style="display: none;"
								onmouseover="switchBlock('ycq');">已超期</div> -->
						</div>
						<div id="dbcq_more" class="childBlockTile_right" name="dwbl-div">
							<a class="chooseRCType" 
								title="待办事项" 
								onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do');return false;"
								href="javaScript:void(0);">
								<span class="childBlockMore">更多</span>
							</a>
						</div>
					
					</div>


					<table style="width: 100%;" id="dwbl">
					</table>
					
					<table style="width: 100%; display: none;" id="swdb">
					</table>
					
					<table style="width: 100%; display: none;" id="fwdb">
					</table>
					
					<table style="width: 100%; display: none;" id="jjcq">
					</table>
					
					<table style="width: 100%; display: none;" id="ycq">
					</table>
					
				</div>
				<i class="i-r"></i>
			</div>
			<div>
				<i class="i-l r-l"></i>
				<div class="childBlock" >
					<div class="childBlockTile">
						<div class="childBlockTile_left">
                             <div id="tongzhi-div"
								class="childBlockTile_left1 document-switch-hover">通知公告</div>
						</div>
						<div class="childBlockTile_right" name="dwbl-div">
							<a class="chooseRCType" 
								title="通知公告" 
								onclick="openMenu(this,'tzgg','<%=request.getContextPath()%>/oa/oaInformation!list.do?infoType=info&flag=GGZY');return false;"
								href="javaScript:void(0);">
								<span class="childBlockMore">更多</span>
							</a>
						</div>				
					</div>
					
					<table style="width: 100%;" id="tzgg">
					</table>
									
					
				</div>
				<i class="i-r r-r"></i>
			</div>
		</div>
		
		<div class="leftCont">
			<div>
				<i class="i-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="jwbl-div"
								class="childBlockTile_left1 document-switch-hover"
								onmouseover="switchBlock('jwbl');">经我办理</div>
							<i></i>
							<div id="blz-div" class="childBlockTile_left1"
								onmouseover="switchBlock('blz');">办理中</div>
							<i></i>
							<div id="ybj-div" class="childBlockTile_left1"
								onmouseover="switchBlock('ybj');">已办结</div>
						</div>
						<div class="childBlockTile_right">
							<a id="jwbl_more" class="chooseType" 
								title="经我办理" 
								onclick="openMenu(this,'jwbl','<%=request.getContextPath()%>/oa/vOptBaseList!list.do');return false;"
								href="javaScript:void(0);"> <span>更多</span>
							</a>
						</div>
					</div>

					<table style="width: 100%;" id="jwbl">
						
					</table>
									
					<table style="width: 100%; display:none;" id="blz">
						
					</table>
									
					<table style="width: 100%; display:none;" id="ybj">
						
					</table>
																
				</div>
				<i class="i-r"></i>
			</div>
			
			
			<div>
				<i class="i-l r-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="sjx-div"
								class="childBlockTile_left1 document-switch-hover"
								onmouseover="switchBlock('sjx');">我的提醒<span id="sjx-span" style="display: none">${nummap['2']}</span></div>
							<i></i>
							<%-- <div id="cgx-div" class="childBlockTile_left1"
								onmouseover="switchBlock('cgx');">草稿箱</div>
							<i></i>
							<div id="fyj-div" class="childBlockTile_left1">
								<a  title="发邮件"
									onclick="openMenu(this,'fyj','<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I');return false;"
								  href="javaScript:void(0);">发邮件</a></div> --%>
								<!-- onclick="switchBlock('fyj');">发件箱</div> -->
						</div>

						<div  class="childBlockTile_right">
							<a  title="收件箱"
								 id="yj_more"
								onclick="openMenu(this,'sjx','<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I');return false;"
								href="javaScript:void(0);"> <span>更多</span>
							</a>
						</div>
					</div>

					<table style="width: 100%;" id="sjx">
					</table>
					
					<table style="width: 100%; display:none;" id="cgx">
					</table>
					
					<table style="width: 100%; display:none;" id="fyj">
					</table>
					
					
				</div>
				<i class="i-r r-r"></i>
			</div>
		</div>
	
		<div class="leftCont">
			<div>
				<i class="i-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
						
							<div id="bumen-div" class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('bumen');">部门文件</div>
						</div>
						<div class="childBlockTile_right">
							<a class="chooseType" target="navTab" external="true"
								title="个人文档" rel="external_GRBGGRWD"
								href="../app/publicinfo!listFile.do?mode=PERSONFILE"> <span>更多</span>
							</a>
						</div>
					</div>

					

					<!-- 部门文档 -->
<%-- 					<c:if test="${cp:HASOPTPOWER('GRBGBMWD') }"> --%>
						<table style="width: 100%; " id="bumen">
							
								<tr class="">
									<td class="itemTitle" style="width: 83%">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
											部门共享文件夹1
												
										</td>
									<td class="itemTime" style="width: 17%">16-04-19
									</td>
								</tr>
								<tr class="even">
									<td class="itemTitle" style="width: 83%">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
											部门共享文件夹2
												
										</td>
									<td class="itemTime" style="width: 17%">16-01-1
									</td>
								</tr>
								<tr >
									<td class="itemTitle" style="width: 83%">
												<span class="icon document"
													style="background:url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important"></span>&nbsp;&nbsp;
											宁工女〔2015〕4号关于举办第二期女职工干部…….doc
												
										</td>
									<td class="itemTime" style="width: 17%"> 15-05-13 
									</td>
								</tr>
									
						</table>
<%-- 					</c:if> --%>
				</div>
				<i class="i-r"></i>
			</div>
			<div>
				<i class="i-l r-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left1 document-switch-hover"
							>市总文件</div>
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
						<table style="width: 100%; " id="bumen">
							
								<tr>
									<td class="itemTitle" style="width: 83%">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/folder.png) right center no-repeat !important"></span>&nbsp;&nbsp;
											市总文件共享文件夹
												
										</td>
									<td class="itemTime" style="width: 17%">16-04-15
									</td>
								</tr>
								<tr class="even">
									<td class="itemTitle" style="width: 83%">
												<span class="icon document"
													style="background:url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important"></span>&nbsp;&nbsp;
											宁工发〔2016〕8号 关于印发《南京市……办法》（修订稿）的通知
												
										</td>
									<td class="itemTime" style="width: 17%"> 16-01-25 
									</td>
								</tr>
									
								<tr>
									<td class="itemTitle" style="width: 83%">
												<span class="icon folder"
													style="background:url(${pageContext.request.contextPath}/images/document.png) right center no-repeat !important"></span>&nbsp;&nbsp;
										宁工通〔2014〕3号 关于转发《江苏省基层工会……办法》的通知
												
										</td>
									<td class="itemTime" style="width: 17%">15-11-1
									</td>
								</tr>
								
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
		
		
		
	    <div class="inter"  style="margin-top:0px;background:url('../themes/default/improve/inter_1.jpg');height: 235px" > 
	    <div style="color: white;font-size: 17px;padding-top: 6px;
    padding-left: 18px;font-weight: 600;">工作台</div>
  <!--业务入口  -->  
    <table style="width: 100%" id="ywrk_right">
    <tr><td><br/></td></tr>
     <tr>
     			
    					<%
							int z = 0;
						%>
						
								<!-- 收文登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('GWLZSWGL') }">
									<%
										z++;
									%>
									<td >
										<a href="javaScript:void(0);"
												onclick="openMenu(this,'swdj1','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?optId=IO_DOC&itemId=NJXXZX-SW-0001');return false;" 
											style="background: url('../themes/default/improve/shouwendengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
											<span style="color: white;"
											><br/><br/><br/>阅办文登记</span>
										</a>
									</td>
								</c:if>

								<!-- 发文登记菜单权限 -->
								<c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
									<%
										z++;
									%>
									<td>
										<a onclick="openMenu(this,'fwdj1','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&dashboard=dashboard');return false;" 
												href='javaScript:void(0);'
											style="background: url('../themes/default/improve/fawendengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
										<a href='javaScript:void(0);' 
												onclick="openMenu(this,'dbfq1','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!selectDbList.do');return false;"
											style="background: url('../themes/default/improve/dubanfaqi.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'hydj1','<%=request.getContextPath()%>/oa/oaMeetinfo!generalOptView.do');return false;";
											style="background: url('../themes/default/improve/huiyidengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'sqdj1','<%=request.getContextPath()%>/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1');return false;"
											style="background: url('../themes/default/improve/shiquandengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'qbdj1','<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard');return false;"
											style="background: url('../themes/default/improve/qianbaodengji.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'sqyc1','<%=request.getContextPath()%>/oa/oaCarinfo!generalOptView.do?nodeInstId=0&showTag=F');return false;";
											style="background: url('../themes/default/improve/shengqincheliang.png') no-repeat center 5px; text-decoration: none;border:0 none">
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
									<td onclick="return false;">
										<a href='javaScript:void(0);'
												onclick="openMenu(this,'fqtx1','<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?dashboard=dashboard');return false;"
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
											onclick="ywgnPopWin();"
											style="background: url('../themes/default/improve/con_inter_add.png') no-repeat center 5px; text-decoration: none;border:0 none">
											 <span style="color: white;"
											><br/><br/><br/>更多</span>
										</a>
								
								
								
								</td>
								 
						 </tr> 
     
    </table>
   </div>
	
	<div class="rightCont3" style="margin-top: 5px; margin-left: -4px;border-bottom: 1px #aaaaaa solid;">

			<!-- 日历 -->
			<iframe
				src="${pageContext.request.contextPath}/page/app/calendars.jsp"
				border="0" marginwidth="0" width="99%" height="300px"
				frameborder="no" scrolling="no"></iframe>
			
		</div>
		
		<div class="rightCont2" style="margin-top: -13px;" align="right">
			<span  ><img width="20px" height="20px" src="../themes/default/improve/con_inter_add_1.png"  
				onclick="addRT();"
			/></span>

			<ul style="position:relative;text-align: left;height:180px; overflow:auto;" class="jjrc" id="calendarrc">
				<li class="first_li">
					<!-- <label class="time_label">9:30</label>
					<label class="noline select"><img src="../themes/default/improve/circle.png"/></label>
					<label class="text_label">【会议】kkkkkkkXXXXXXXXXX</label>
					<a></a> -->
				</li>
				
			</ul>

		</div>

	</div>
</div>
<div id="improveFooter">
	<div>
<!-- 	版权所有：新疆维吾尔自治区交通运输厅	 -->
	</div>
	<div>技术支持：江苏南大先腾信息产业有限公司 </div>
</div>

 <!--业务入口  -->
<div id="attAlert_yw" class="alert" 
				style="width: 700px; height: 460px; position: absolute; top: 20px; left: 20%; overflow: hidden;display: none">
				<h4>
					<span id="selectyw">工作台</span><span id="close3"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert_yw');">关闭</span>
				</h4>
				<div class="fix">
					<div class="childBlock" id="faqi_content" style="height: 365px;"><!-- #196DB3 -->
						<div id="faqi-more" name="faqi-more"  style="background-color:#54abee;height:365px" >
					
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
												><br/><br/><br/>阅办文登记</span>
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
	
									<!-- 督查发起菜单权限 -->
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
								
									<c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
							 
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
												><br/><br/><br/>阅办文归档</span>
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


<!-- 日程安排、提醒 -->
<div id="attAlert" class="alert"  
				style="width: 700px; height: 460px; position: absolute; top: 20px; left: 20%; display: none;z-index: 99999">
				<h4>
					<span id="selectTT"></span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlerts('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<i class="i-l r-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="rcap-div"
								class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('rcap');">日程安排</div>
							<i></i>
							<div id="tx-div" class="childBlockTile_left1"
								onclick="switchBlock('tx');">提醒</div>
							<i></i>
						</div>
					</div>

					<table id="rcap" style="width: 98%;" >
					<iframe id="rcap_table" frameborder="0" height="420px" width="100%" src=""></iframe>
					</table>
					 <table id="txs" style="width: 98%;display:none; " >
					<iframe id="tx_table" frameborder="0" height="410px" width="100%" src=""></iframe>
					</table> 
					
					
					
				</div>
				<i class="i-r r-r"></i>
				</div>
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
		
		if(id=="dwbl"){
			sydb();
			var v = $('#dbcq_more');
			
			v.attr('title','待我办理');
			v.attr('onclick',"openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do');return false;");
			
			if($("#dwbl").is(":visible")){
				return 
			}
			$("#jjcq").hide();
			$("#ycq").hide();
			$("#swdb").hide();
			$("#fwdb").hide();
			$("#dwbl").show();
			$("#dwbl-span").hide();
			if($("#jjcq-span").html()>0){
				$("#jjcq-span").show();
			}
			$("#jjcq-div").removeClass("document-switch-hover");
			$("#ycq-div").removeClass("document-switch-hover");
			$("#swdb-div").removeClass("document-switch-hover");
			$("#fwdb-div").removeClass("document-switch-hover");
			$("#dwbl-div").addClass("document-switch-hover");
		}
		else if(id == "jjcq"){
			jjcq();
		    var v = $('#dbcq_more');
			v.attr('title','即将超期');
			v.attr('href','#');
			
			if($("#jjcq").is(":visible")){
				return 
			}   
			$("#dwbl").hide();
			$('#ycq').hide();
			$("#swdb").hide();
			$('#fwdb').hide();
			$("#jjcq").show();
			
			$("#dwbl-div").removeClass("document-switch-hover");
			$("#ycq-div").removeClass("document-switch-hover");
			$("#swdb-div").removeClass("document-switch-hover");
			$("#fwdb-div").removeClass("document-switch-hover");
			$("#jjcq-div").addClass("document-switch-hover");
		}
		else if(id == "ycq"){
			ycq();
		    var v = $('#dbcq_more');
			v.attr('title','已超期');
			v.attr('href','#');
			
			if($("#ycq").is(":visible")){
				return 
			}   
			$("#dwbl").hide();
			$("#jjcq").hide();
			$("#swdb").hide();
			$("#fwdb").hide();
			$("#ycq").show();
			
			$("#dwbl-div").removeClass("document-switch-hover");
			$("#jjcq-div").removeClass("document-switch-hover");
			$("#swdb-div").removeClass("document-switch-hover");
			$("#fwdb-div").removeClass("document-switch-hover");
			$("#ycq-div").addClass("document-switch-hover");
		}
		else if(id == "swdb"){
			swdb();
		    var v = $('#dbcq_more');
			v.attr('title','阅办文待办');
			v.attr('onclick',"openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemTypeName=SW');return false;");
			
			if($("#swdb").is(":visible")){
				return 
			}   
			$("#dwbl").hide();
			$("#jjcq").hide();
			$("#swdb").show();
			$("#fwdb").hide();
			$("#ycq").hide();
			
			$("#dwbl-div").removeClass("document-switch-hover");
			$("#jjcq-div").removeClass("document-switch-hover");
			$("#swdb-div").addClass("document-switch-hover");
			$("#fwdb-div").removeClass("document-switch-hover");
			$("#ycq-div").removeClass("document-switch-hover");
		}
		else if(id == "fwdb"){
			fwdb();
		    var v = $('#dbcq_more');
			v.attr('title','发文待办');
			v.attr('onclick',"openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemTypeName=FW');return false;");
			
			if($("#fwdb").is(":visible")){
				return 
			}   
			$("#dwbl").hide();
			$("#jjcq").hide();
			$("#fwdb").show();
			$("#swdb").hide();
			$("#ycq").hide();
			
			$("#dwbl-div").removeClass("document-switch-hover");
			$("#jjcq-div").removeClass("document-switch-hover");
			$("#fwdb-div").addClass("document-switch-hover");
			$("#swdb-div").removeClass("document-switch-hover");
			$("#ycq-div").removeClass("document-switch-hover");
		}
       else if( id == "jwbl"){
    	    jwbl();
    		var v = $('#jwbl_more');
     		v.attr('title','经我办理');
     		v.attr('onclick',"openMenu(this,'jwbl','<%=request.getContextPath()%>/oa/vOptBaseList!list.do');return false;");
     		
     		if($("#jwbl").is(":visible")){
     			return 
     		} 
     		$("#jwbl").show();
     		$("#blz").hide();
     		$("#ybj").hide();
     		$("#jwbl-div").addClass("document-switch-hover");
     		$("#blz-div").removeClass("document-switch-hover");
     		$("#ybj-div").removeClass("document-switch-hover");
    	}
    	else if( id == "blz"){
    		jwbl("W");
    		var v = $('#jwbl_more');
     		v.attr('title','办理中');
     		v.attr('onclick',"openMenu(this,'blz','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_bizstate=W');return false;");
     		
     		if($("#blz").is(":visible")){
     			return 
     		}   
     		$("#blz").show();
     		$("#jwbl").hide();
     		$("#ybj").hide();
     		$("#blz-div").addClass("document-switch-hover");
     		$("#jwbl-div").removeClass("document-switch-hover");
     		$("#ybj-div").removeClass("document-switch-hover");
    	}
    	else if(id == "ybj"){
    		jwbl("C");
    		var v = $('#jwbl_more');
     		v.attr('title','已办结');
     		v.attr('onclick',"openMenu(this,'ybj','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_bizstate=C');return false;");
     		
     		if($("#ybj").is(":visible")){
     			return 
     		}   
     		$("#ybj").show();
     		$("#jwbl").hide();
     		$("#blz").hide();
     		$("#ybj-div").addClass("document-switch-hover");
     		$("#jwbl-div").removeClass("document-switch-hover");
     		$("#blz-div").removeClass("document-switch-hover");
    	} else if(id == "sjx"){
    		//I=收件箱 O=发件箱 D=草稿箱 T=废件箱
    	     yj("I");
    		var v = $('#yj_more');
     		v.attr('title','收件箱');
     		v.attr('onclick',"openMenu(this,'sjx','<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I');return false;");
     		
     		if($("#sjx").is(":visible")){
     			return 
     		}   
     		$("#sjx").show();
     		$("#cgx").hide();
     		$("#fyj").hide();
    		$("#sjx-span").hide();
     		$("#sjx-div").addClass("document-switch-hover");
     		$("#cgx-div").removeClass("document-switch-hover");
     		$("#fyj-div").removeClass("document-switch-hover");
    	} else if(id == "cgx"){
    		//I=收件箱 O=发件箱 D=草稿箱 T=废件箱
   	     	yj("D");
    		var v = $('#yj_more');
     		v.attr('title','草稿箱');
     		v.attr('onclick',"openMenu(this,'cgx','<%=request.getContextPath()%>/oa/innermsg!list.do?s_msgtype=P&s_mailtype=D');return false;");
     		
     		if($("#cgx").is(":visible")){
     			return 
     		}   
     		$("#cgx").show();
     		$("#sjx").hide();
     		$("#fyj").hide();
     		if($("#sjx-span").html()>0){
    			$("#sjx-span").show();
    		}
     		$("#cgx-div").addClass("document-switch-hover");
     		$("#sjx-div").removeClass("document-switch-hover");
     		$("#fyj-div").removeClass("document-switch-hover");
    	}else if(id == "dwsw"){
    		sw();  	
    	    var v = $('#sw_more');
    		v.attr('title','单位阅办文');
    		v.attr('onclick',"openMenu(this,'dwsw','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listV2.do?show_type=T');return false;");
    		
    		if($("#dwsw").is(":visible")){
    			return 
    		}   
    		$("#bmsw").hide();
    		$("#dwsw").show();
    		/* if($("#bmsw-span").html()>0){
    			$("#bmsw-span").show();
    		}
    		$("#dwsw-span").hide(); */
    		$("#bmsw-div").removeClass("document-switch-hover");
    		$("#dwsw-div").addClass("document-switch-hover");
    	}else if(id == "bmsw"){
    		sw("bmsw");   	
    	    var v = $('#sw_more');
    		v.attr('title','部门阅办文');
    		v.attr('onclick',"openMenu(this,'bmsw','<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listV2.do?show_type=T&s_bmsw=T');return false;");
    		
    		if($("#bmsw").is(":visible")){
    			return 
    		}   
    		$("#dwsw").hide();
    		$("#bmsw").show();
    		$("#dwsw-div").removeClass("document-switch-hover");
    		$("#bmsw-div").addClass("document-switch-hover");
    	}else if(id == "dwfw"){
    		fw();	
    	    var v = $('#fw_more');
    		v.attr('title','单位发文');
    		v.attr('onclick',"openMenu(this,'dwfw','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!listV2.do?show_type=T');return false;");
    		
    		if($("#dwfw").is(":visible")){
    			return 
    		}   
    		$("#bmfw").hide();
    		$("#dwfw").show();
    		$("#bmfw-div").removeClass("document-switch-hover");
    		$("#dwfw-div").addClass("document-switch-hover");
    	}else if(id == "bmfw"){
    		fw("bmfw");   	
    	    var v = $('#fw_more');
    		v.attr('title','部门发文');
    		v.attr('onclick',"openMenu(this,'bmfw','<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!listV2.do?show_type=T&s_bmfw=T');return false;");
    		
    		if($("#bmfw").is(":visible")){
    			return 
    		}   
    		$("#dwfw").hide();
    		$("#bmfw").show();
    		$("#dwfw-div").removeClass("document-switch-hover");
    		$("#bmfw-div").addClass("document-switch-hover");
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
    	}else if(id=="rcap"){
        	 $("#rcap").show();
    		$("#txs").hide(); 
    		$("#tx-div").removeClass("document-switch-hover");
    		$("#rcap-div").addClass("document-switch-hover");
    	}
    	else if(id=="tx"){
    		 $("#txs").show();
    		$("#rcap").hide(); 
    		$("#rcap-div").removeClass("document-switch-hover");
    		$("#tx-div").addClass("document-switch-hover");
    	}
    }
    
    function downloadFile(type,infocode){
    	
    	var href = "<%=request.getContextPath()%>/app/publicinfo!download.do";
    	$.post(href, {infocode: infocode, mode: type}, function(data){
    		downloadFileCallback(data, true);
		}, 'json');
    	
    }
    
    $(function(){
    	
    	/* $.post('${pageContext.request.contextPath }/oa/oaInformation!tzgg_dashboard.do',
    		function(result){
    		
    		
    		   //alert(result);
    	},'json'); */
    	
    	var xzjb='${userRank}';//根据行政级别显示对应模块
    	sydb();//待办
    	swdb();
    	fwdb();
    	jjcq();
    	ycq();
    	tzgg();//通知公告
    	yj("I");//邮箱  I=收件箱 O=发件箱 D=草稿箱 T=废件箱
    	jwbl();//经我办理  ""=全部 "W"||"T"="办理中" "C"=已办结
    	//if(xzjb=='TZ'){sw();}else{sw('bmsw');}//收文  ""=单位收文 "bmsw"=部门收文
    	//if(xzjb=='TZ'){fw();}else{fw('bmfw');}//发文  ""=单位发文 “bmfw”=部门发文
    	if(xzjb=='TZ'){switchBlock("dwsw");}else{switchBlock("bmsw");}
    	if(xzjb=='TZ'){switchBlock("dwfw");}else{switchBlock("bmfw");}
    	kuang();
    });
    function omitText(src){
    	if(src.length>14){
    		src=src.substring(0,14);
    		src+="...";
    	}
    	return src;
    }
    function changeTime(time){
    	var s=time.substr(5,5);
    	return s;
    }
    function emailreadstate(state){
    	if(state=="U")
    		return "未读";
    	else if(state=="R")
    		return "已读";
    }
    function jwblstate(state){
    	if(state=="W"||state=="T"){
    		return "办理中";
    	}
    	if(state=="C")
    		{
    		return "已办结";
    		}
    }
    
    function sydb(){
    	
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!sydb_new.do",
    		type:"post",
    		dataType:"json",
    		success:function(date){
    			
    			var list=date;
    			$("#dwbl").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle_left' ";
    					if(o.readstate=="已读"){
    						item+="style='color:green;' ";
    					}else{
    						item+="style='color:orange;' ";
    					}
    					item+=">["+o.readstate
						+"]</td>"+
    					"<td class='itemTitle'  >"+
    					"<a id='dwbl"+i+"' target='navTab' external='true'"+
    					"title='待我办理'>" +
								"<span title='"+o.transaffairname+"'>"+"["+o.itemtype+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						
					"</tr>";
					if(i<=5){
						$("#dwbl").append(item);
						var id="dwbl"+i;
						$('#' + id).click(function(){
							openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + o.nodeOptUrl+'%26dashboard%3DT' );							
						});						
					};
    			}) ;
    		}
    	});
    }

    //收文待办
 function swdb(){
    	
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!sydb_new.do",
    		type:"post",
    		data:{"s_itemtype":"SW"},
    		dataType:"json",
    		success:function(date){
    			
    			var list=date;
    			$("#swdb").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle_left' ";
    					if(o.readstate=="已读"){
    						item+="style='color:green;' ";
    					}else{
    						item+="style='color:orange;' ";
    					}
    					item+=">["+o.readstate
						+"]</td>"+
    					"<td class='itemTitle'  >"+
    					"<a id='swdb"+i+"' target='navTab' external='true'"+
    					"title='待我办理'>" +
								"<span title='"+o.transaffairname+"'>"+"["+o.itemtype+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						
					"</tr>";
					if(i>0){
						$("#swdb-div").show();
						$("#swdb-div-i").show();
					}
					if(i<=5){
						$("#swdb").append(item);
						var id="swdb"+i;
						$('#' + id).click(function(){
							openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + o.nodeOptUrl+'%26dashboard%3DT' );							
						});						
					};
    			}) ;
    		}
    	});
    }
 //发文待办
 function fwdb(){
    	
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!sydb_new.do",
    		type:"post",
    		data:{"s_itemtype":"FW"},
    		dataType:"json",
    		success:function(date){
    			
    			var list=date;
    			$("#fwdb").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle_left' ";
    					if(o.readstate=="已读"){
    						item+="style='color:green;' ";
    					}else{
    						item+="style='color:orange;' ";
    					}
    					item+=">["+o.readstate
						+"]</td>"+
    					"<td class='itemTitle'  >"+
    					"<a id='fwdb"+i+"' target='navTab' external='true'"+
    					"title='待我办理'>" +
								"<span title='"+o.transaffairname+"'>"+"["+o.itemtype+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						
					"</tr>";
    				if(i>0){
    						$("#fwdb-div").show();
    						$("#fwdb-div-i").show();
    				}
					if(i<=5){
						$("#fwdb").append(item);
						var id="fwdb"+i;
						$('#' + id).click(function(){
							openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + o.nodeOptUrl+'%26dashboard%3DT' );							
						});						
					};
    			}) ;
    		}
    	});
    }

    
    function jjcq(){
    	
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!cq_new.do?overdueType=I" ,
    		type:"post",
    		dataType:"json",
    		success:function(data){
    			
    			var list=data;
    			$("#jjcq").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					
    					"<td class='itemTitle'  >"+
    					"<span class='icon-email icon-jjOverdue' title='即将超期'></span>" +
    					"<a id='jjcq"+i+"' target='navTab' external='true'"+
    					"title='即将超期' style='display: inline-block;'>" +
								"<span title='"+o.transaffairname+"'>"+"["+o.itemTypeName+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						
					"</tr>";
    			   if(i>0){
    				  $("#jjcq-div").show();
    				  $("#jjcq-div-i").show();
    				}	
					if(i<=5){
						$("#jjcq").append(item);
						var id="jjcq"+i;
						$('#' + id).click(function(){
							openMenu(this, 'jjcq', '<%=request.getContextPath()%>' + o.nodeOptUrl+'%26dashboard%3DT' );							
						});						
					};
    			}) ;
    		}
    	});
    }
    
	function ycq(){
    	
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!cq_new.do?overdueType=O" ,
    		type:"post",
    		dataType:"json",
    		success:function(data){
    			
    			var list=data;
    			$("#ycq").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					
    					"<td class='itemTitle'  >"+
    					"<span class='icon-email icon-overdue' title='已超期'></span>" +
    					"<a id='ycq"+i+"' target='navTab' external='true'"+
    					"title='已超期' style='display: inline-block;'>" +
								"<span title='"+o.transaffairname+"'>"+"["+o.itemTypeName+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						
					"</tr>";
    					if(i>0){
    						$("#ycq-div").show();
    					}
    					if(i<=5){
						$("#ycq").append(item);
						var id="ycq"+i;
						$('#' + id).click(function(){
							openMenu(this, 'ycq', '<%=request.getContextPath()%>' + o.nodeOptUrl + '&s_itemtype='+ o.itemtype);							
						});						
					};
    			}) ;
    		}
    	});
    }
    
    function tzgg(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!tzgg_new.do",
    		type:"post",
    		dataType:"json",
    		success:function(date){
    			var list=date;
    			$("#tzgg").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr><td  class='itemTitle_left'";
    					if(o.readstate=="已读"){
    						item+="style='color:green;' ";
    					}else{
    						item+="style='color:orange;' ";
    					}
    					item+=">["+
						 o.readstate+ 
						"]</td>"+
    					"<td class='itemTitle' >";
						
						
						
    					item+="<a  id='tzgg"+i+"' target='navTab' external='true'>";
    					if(o.newmsg=="1"&&o.readstate=="未读"){
							item+="<img style='vertical-align: middle;margin-right: 1px;' src='${pageContext.request.contextPath}/themes/default/improve/new.png' "+
							"border='0' />";
						}
						item+="<span title='"+o.title+"'>"+o.title+"</span>";
						
						item+="</a>";
						/* +"<td class='itemTitle_right' style='width:20px'>"+o.readstate
						+"</td>" */
						item+="</td><td  style='text-align:right;width:50px;padding-right:15px'>"+
						changeTime(o.creatertime)+
						"</td>"+
					"</tr>";
						if(i<=5){
						$("#tzgg").append(item);
						var id="tzgg"+i;						
						$('#' + id).click(function(){
							openMenu(this, 'tzgg', "<%=request.getContextPath()%>/oa/oaInformation!view.do?no="+o.no+"&flag=GGZY&show_type=F");							
						});
						}
    			}) ;
    		}
    	});
    }
  //I=收件箱 O=发件箱 D=草稿箱 T=废件箱
    function yj(mailtype){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!syyj_new.do",
    		type:"post",
    		data:{"mailtype":mailtype},
    		dataType:"json",
    		success:function(date){
    			var list=date;
    			if(mailtype=="I"){
    				$("#sjx").html("");
    			}else if(mailtype=="O"){
    				$("#fyj").html("");
    			}else if(mailtype=="D"){
    				$("#cgx").html("");
    			}
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>";
    					if(mailtype=="I"){
    					item+=
    						"<td class='itemTitle_left' ";
    						/* if(mailtype=="I"){ */
    						if(o.msgstate=="U"){
    							item+="style='color:orange;' ";
    						}else{
    							item+="style='color:green;' ";
    						}
    						item+=">["+emailreadstate(o.msgstate)+"]</td>";
    						} 
    					item+="<td class='itemTitle'  >"+
    					"<a style='height:16px!important;'";
    					if(mailtype=="I"){
    						item+=" id='sjx"+i+"' ";
    					}else if(mailtype=="D"){
    						item+=" id='cgx"+i+"' ";
    					}
    					item+="target='navTab' external='true'"+
    					"title='收件箱' >" + 
								"<span title='"+o.sender+"'>";
						if(mailtype=="I"){
							item+="["+omitText2(o.sender)+"]</span><span title='"+o.msgtitle+"'>";
						}
						item+=omitText(o.msgtitle)+"</span>"+
						"</a>"+"</td>";
						/* +
						"<td class='itemTitle' style='width:12%'>";
						if(mailtype=="I"){
							item+=emailreadstate(o.msgstate);
						} */
						item+="<td  style='text-align:right;width:50px;padding-right:15px'>";
						item+=changeTime(o.senddate)+
						"</td>"+
					"</tr>";
					 if(i<=5){
						if(mailtype=="I"){
		    				$("#sjx").append(item);
		    				var id="sjx"+i;
		    				$('#' + id).click(function(){
		    					openMenu(this, 'sjx', "<%=request.getContextPath()%>/oa/innermsg!view.do?msgcode=" +
		    							o.msgcode+"&s_msgtype=P&s_mailtype=I&isShow=isShow");
		    				});
		    			}else if(mailtype=="O"){
		    				$("#fyj").append(item);
		    			}else if(mailtype=="D"){
		    				$("#cgx").append(item);
		    				var id="cgx"+i;
		    				
		    				$('#' + id).click(function(){
		    					openMenu(this, 'cgx',"<%=request.getContextPath()%>/oa/innermsg!view.do?msgcode="+o.msgcode+"&s_msgtype=P&s_mailtype=D&isShow=isShow");
		    				});						
						} ;
					 };
    			}) ;
    		}
    	});
    }
  //""=全部 "W"||"T"="办理中" "C"=已办结
    function jwbl(state){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!jwbl_new.do",
    		type:"post",
    		dataType:"json",
    		data:{"bizstate":state},
    		beforeSend:function(){ 
    			if(state=="W"||state=="T"){
    				$("#blz").html("<tr><td align='center'><img style='width:50px;height:50px;margin-top:60px;' src='<%=request.getContextPath()%>/newStatic/image/jindutiao.gif'/></td></tr>");
    			}else if(state=="C"){
    				$("#ybj").html("<tr><td align='center'><img style='width:50px;height:50px;margin-top:60px;' src='<%=request.getContextPath()%>/newStatic/image/jindutiao.gif'/></td></tr>");
    			}else{
    				$("#jwbl").html("<tr><td align='center'><img style='width:50px;height:50px;margin-top:60px;' src='<%=request.getContextPath()%>/newStatic/image/jindutiao.gif'/></td></tr>");
    			}
    			
    		}, 
    		success:function(date){
    			var list=date;
    			if(state=="W"||state=="T"){
    				$("#blz").html("");
    			}else if(state=="C"){
    				$("#ybj").html("");
    			}else{
    				$("#jwbl").html("");
    			}
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle'  style='text-align: left;width:82%'>"+
    					"<a target='navTab' external='true'"+
    					"title='经我办理'  ";
    					if(state=="W"||state=="T"){
    						item+=" id='blz"+i+"'";
    					}else if(state=="C"){
    						item+=" id='ybj"+i+"'";
    					}else{
    						item+=" id='jwbl"+i+"'";
    					}
								item+="> "+
								"<span title='"+o.transaffairname+"'>"+"["+o.itemTypeName+"]"+o.transaffairname+"</span>"+
						"</a>"+"</td>"+
						"<td style='text-align:right;width:50px;padding-right:15px'>"+
						jwblstate(o.bizstate)+
						"</td>"+
					"</tr>";
					 if(i<=5){
						 if(state=="W"||state=="T"){
			    				$("#blz").append(item);
			    				var id="blz"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'blz',"<%=request.getContextPath()%>/"+o.itemType+"!generalOptView.do?djId="+o.djId+"&nodeInstId=0&applyItemType="+"&dashboard=W");
			    				});
			    				
			    			}else if(state=="C"){
			    				$("#ybj").append(item);
			    				var id="ybj"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'ybj',"<%=request.getContextPath()%>/"+o.itemType+"!generalOptView.do?djId="+o.djId+"&nodeInstId=0&applyItemType="+"&dashboard=C");
			    				});
			    			}else{
			    				$("#jwbl").append(item);
			    				var id="jwbl"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'jwbl',"<%=request.getContextPath()%>/"+o.itemType+"!generalOptView.do?djId="+o.djId+"&nodeInstId=0&applyItemType="+"&dashboard=T");
			    				});
			    			}
					} 
    			}) ;
    		}
    	});
    }
    //""=单位收文 "bmsw"=部门收文
    function sw(bmsw){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!sw_new.do",
    		type:"post",
    		dataType:"json",
    		data:{"bmsw":bmsw},
    		success:function(date){
    			var list=date;
    			if(bmsw=="bmsw"){
    				$("#bmsw").html("");
    			}else{
    				$("#dwsw").html("");
    			}
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle'  style='text-align: left;width: 70%'>"+
    					"<a target='navTab' external='true'"+
    					"title='阅办文'  ";
    					if(bmsw=="bmsw"){
							item+=" id='bmsw"+i+"'";
						}else{
							item+=" id='dwsw"+i+"'";
						}
									item+="> "+
								"<span title='"+o.transaffairname+"'>"+o.transaffairname+"</span>"+
						"</a></td><td style='text-align:right;width:50px;padding-right:15px''>"+changeTime(o.incomeDate)+"</td>"+
					"</tr>";
					 if(i<=5){
						 if(bmsw=="bmsw"){
			    				$("#bmsw").append(item);
			    				var id="bmsw"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'bmsw',"<%=request.getContextPath()%>/dispatchdoc/incomeDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=BMSW");
			    				});
			    			}else{
			    				$("#dwsw").append(item);
			    				var id="dwsw"+i;
			    				
			    				$('#' + id).click(function(){
			    					openMenu(this, 'dwsw',"<%=request.getContextPath()%>/dispatchdoc/incomeDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=DWSW");
			    				});
			    			}
					} 
    			}) ;
    		}
    	});
    }
    //""=单位发文 “bmfw”=部门发文
    function fw(bmfw){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!fw_new.do",
    		type:"post",
    		dataType:"json",
    		data:{"bmfw":bmfw},
    		success:function(date){
    			var list=date;
    			if(bmfw=="bmfw"){
    				$("#bmfw").html("");
    			}else{
    				$("#dwfw").html("");
    			}
    			$("#geren").html("");
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr>"+
    					"<td class='itemTitle'  >"+
    					"<a target='navTab' external='true'"+
    					"title='发文'  ";
    					if(bmfw=="bmfw"){
							item+=" id='bmfw"+i+"'";
						}else{
							item+=" id='dwfw"+i+"'";
						}
						item+="> "+
								"<span title='"+o.dispatchDocTitle+"'>"+o.dispatchDocTitle+"</span>"+
						"</a>"+"</td><td style='text-align:right;width:50px;padding-right:15px''>"+changeTime(o.draftDate)+"</td>"+
						
					"</tr>";
					 if(i<=5){
						 if(bmfw=="bmfw"){
			    				$("#bmfw").append(item);
			    				var id="bmfw"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'bmfw',"<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=BMFW");
			    				});
			    			}else{
			    				$("#dwfw").append(item);
			    				var id="dwfw"+i;
			    				$('#' + id).click(function(){
			    					openMenu(this, 'dwfw',"<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=DWFW");
			    				});
			    			}
					} 
    			}) ;
    		}
    	});
    }
    function popWin(url,type){
    	DialogUtil.open("",url,1200,700,null,null,function(){
			if(type=="sydb"){
				sydb();
			}else if(type=="tzgg"){
				tzgg();
			}else if(type=="sjx"){
				yj("I");
			}else if(type=="cgx"){
				yj("D");
			}else if(type=="jwbl"){
				jwbl();
			}else if(type=="blz"){
				jwbl("W");
			}else if(type=="ybj"){
				jwbl("C");
			}else if(type=="bmsw"){
				sw("bmsw");
			}else if(type=="dwsw"){
				sw();
			}else if(type=="dwfw"){
				fw();
			}else if(type=="bmfw"){
				fw("bmfw");
			}else if(type=="xzrc"){
				sxjrrc();
			}
		});
    }
    
    function openMenu(obj,tabid, url){
    	
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
    	}
		navTab.openTab(tabName, url,{title:title, fresh:fresh, external:external});
    }
    
    
    function sxjrrc(){
		var t=$("#newtime").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/app/dashboard!showCalendar.do?date="+t+" 00:00:00&timestamp="+timestamp(),
    		type:"post",
    		dataType:"json",
    		success:function(date){
    				var c=$('#calendarrc');
    				c.html("");
    				var string="<input type='hidden' id='newtime' value='"+t+"' />";
    				c.append(string);
    				var n;
    				$.each(date,function(j,o){
    					n=j;
    				});	
    			 $.each(date,function(i,o){
    				 var item;
    				 item="<li class='";
    				  if(n>=0&&i==0){
    				 	 item+="first_li"; 
    				  }else if(n>0&&i==n){
    					 item+="last_li";
    				 } 
    				 item+="'><label class='time_label'>"+
    				 changeTime1(o.begTime)+
 					"</label><label class='";
    				 if(n>0){
    					 item+="line select";
	    				 }else{ 
	    					 item+="noline select";
	    				  } 
 					item+="' ><img alt='' src='../themes/default/improve/circle.png'/></label>"+
 					"<label class='text_label' title='"+o.title+"'>"+
 					 "<a "+
 					"title='"+o.title+"' id='ca"+i+"'";
					item+=" href='javaScript:void(0);'>"+
    				 "【"+o.itemType+"】"+
 					omitText1(o.title)+
 					"</a>"+
 					"</label></li>";
 					c.append(item);
 					 var id="ca"+i;
					 if(o.reType=="RCAP"){
						$('#' + id).click(function(){
							openMenu(this,'rcap_c',"<%=request.getContextPath()%>/oa/oaSchedule!view.do?schno="+o.no+"&dashboard=RCAP");
							return false;
						});
					}else if(o.reType=="TX"){
						$('#' + id).click(function(){
							openMenu(this,'fqtx1',"<%=request.getContextPath()%>/oa/oaRemindInformation!view.do?no="+o.no+"&dashboard=TX");
							return false;
	    				});
					} 					
    			}); 
    		}
		});
    }
    function changeTime1(time){
    	var s=time.substr(time.length-8,5);
    	return s;
    }
	function omitText1(src){
    	if(src.length>8){
    		src=src.substring(0,8);
    		src+="...";
    	}
    	return src;
    }function omitText2(src){
    	if(src.length>4){
    		src=src.substring(0,4);
    		src+="...";
    	}
    	return src;
    }
	function timestamp(){
		var timestamp = parseInt(new Date().getTime()/1000); 
		return timestamp;
	}
	function selectPopWin() {
		var time=$("#newtime").val();
    	var t="<%=request.getContextPath()%>/oa/oaSchedule!addOaSchedule.do?&planBegTime_s="+time+" 11:00:00&planEndTime_s="+time+" 20:00:00&s_sehType=1&s_canAdd=T&xzrc_sy=T";
    	var s="<%=request.getContextPath()%>/oa/oaRemindInformation!builtV2.do?xzrc_sy=T";
    	$("#rcap_table").attr('src',t);
    	$("#tx_table").attr('src',s);
		setAlertStyle("attAlert");
		var malert=document.getElementById("attAlert");
	}
	function ywgnPopWin() {
		setAlertStyle("attAlert_yw");
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
	function closeAlerts(alert){
		sxjrrc();
		closeAlert(alert);
	}
	function writeywrk(){
		
	}
	function addRT() {
		var t=$("#newtime").val();
		art.dialog
				.open('${pageContext.request.contextPath}/app/dashboard!calendarSX.do?newtime='+t,
						 {title: '', width: 1050, height: 640,close:function(){sxjrrc();}},false);

	}
</script>