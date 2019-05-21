<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/default/css/tv/liuyan.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script>
	var editor;
	KindEditor
			.ready(function(K) {
				editor = K
						.create(
								'textarea[id="newcontent"]',
								{
									cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
									uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
									fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.getElementById(
													"oaBbsThemeForm").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"oaBbsThemeForm").submit();
										});
									}
								});
				prettyPrint();
			});
</script>
<title><s:text name="oaBbsTheme.view.title" /></title>
<style type="text/css">
.reply-list {
	margin-top: 10px
}

.reply-list .right {
	border-bottom: 1px solid #e0e0e0;
}

.reply-list .right-top {
	height: 30px;
	line-height: 30px;
	background: #f0f0f0
}

.reply-list .left {
	width: 200px;
	border-right: 1px solid #e0e0e0;
	border-bottom: 1px solid #e0e0e0;
}

.reply-title {
	hegith: 30px;
	line-height: 30px;
	padding-left: 80px;
	background: #f0f0f0;
}

a {
	text-decoration: none !important
}

p {
	padding-left: 10px;
	padding-top: 10px;
}

.top-title {
	height: 30px;
	margin-top: 10px;
	margin-bottom: 10px;
	background: #f0f0f0;
}

.tool {
	width: 70px;
	margin: auto
}

.tool .zt {
	float: left;
	padding: 2px;
	border-right: 1px solid rgb(209, 209, 209)
}

.tool .jf {
	float: left;
	padding: 2px;
	border-right: 1px solid rgb(209, 209, 209)
}

.tool .dj {
	float: left;
	padding: 2px;
}

tr {
	border: 1px solid #CCCCCC;
}

td {
	word-break: break-all;
}

#oaBbsThemeForm {
	border: 1px solid #CCCCCC;
	padding-bottom: 20px;
}

.ksft {
	border-bottom: 1px solid #CCCCCC;
	background: #F0F0EE;
	height: 30px;
	margin-bottom: 10px;
	line-height: 30px;
	padding-left: 20px
}

div.Album_info {
	display: none;
	position: absolute;
	margin-left: 80px;
	z-index: 101;
	-moz-box-shadow: 0 0 10px #000000;
	-webkit-box-shadow: 0 0 10px #000000;
	box-shadow: 0px 0 10px #000000;
}

p.ps {
	border-bottom: thin dotted #000000;
}

a.del {
	color: #889db6;
	font-size: 12px;
	float: right;
	margin-right: 55px;
}

div.userPic {
	float: left;
	width: 50px;
	height: 50px;
	margin-right: 25px;
	border-radius: 5px;
}

div.list-div {
	margin-left: 20px;
	padding: 15px 0;
}
</style>
</head>

<body>
	<div class="top-title">
		<c:if test="${not empty bbsDiscussion.oaBbsDashboard}">
			<a
				href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsMainPage.do'>
				讨论版</a>>
		    <a
				href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsDisMainPage.do?layoutcode=${bbsDiscussion.oaBbsDashboard.layoutcode}'>
				${bbsDiscussion.oaBbsDashboard.layoutname} </a>>
	        <a
				href='${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${bbsDiscussion.layoutno}'>
				${bbsDiscussion.sublayouttitle} </a>
		</c:if>
	</div>
	<%@ include file="/page/common/messages.jsp"%>

	<c:set var="listURL" value="oaBbsTheme!view.do" />
	<c:set var="maxPageItems" value="10"></c:set>
	<c:if test="${fn:length(replyList)>=1}">
		<%@ include file="/page/common/pagingBar.jsp"%>
	</c:if>
	<table class="reply-list">
		<tr style="border: 1px solid #CCCCCC;">
			<td class="top-title" align="center"
				style="border: 1px solid #CCCCCC;">回复数：${empty postsnum ?
				0:postsnum}
				查看数：${empty postsviewnum ? 0:postsviewnum}</td>
			<td>
				<div class="reply-title" align="left">
					<c:if test="${themeset eq 'G' }">
							  			[${cp:MAPVALUE('themeset',themeset) }]&nbsp;&nbsp;
							  			 <i class="icon icon-color icon-star-on"
							style="margin-left: 15px;" title="公告"></i>
					</c:if>
					<c:if test="${theme.themeset eq 'T' }">
							  			[${cp:MAPVALUE('themeset',themeset) }]&nbsp;&nbsp;
							  			 <i class="icon icon-color icon-star-on"
							style="margin-left: 15px;" title="置顶"></i>
					</c:if>
					<c:if test="${theme.themeset eq 'J' }">
							  			[${cp:MAPVALUE('themeset',themeset) }]&nbsp;&nbsp;
							  			 <i class="icon icon-color icon-star-off"
							style="margin-left: 15px;" title="精华"></i>
					</c:if>
					<%-- 					<a href='theme!view.do?themeNo=${themeno}' --%>
					<!-- 						style="text-decoration: none;">  -->
					<%-- 						<c:choose> --%>
					<%-- 							<c:when test="${fn:length(sublayouttitle) gt 10 }">${fn:substring(sublayouttitle , 0, 10) }...</c:when> --%>
					<%-- 							<c:otherwise> --%>
					<span style="font-size: 18px;"> ${object.sublayouttitle} </span>
					<%-- 							</c:otherwise> --%>
					<%-- 						</c:choose> --%>
					<!-- 						</a> -->
				</div>
			</td>
		</tr>


		<tr>
			<!-- 图片区域-->
			<td class="left userinfo" align="center"
				data-usercode="${oaUserinfo.usercode }" data-id="0"><a
				href="${contextPath }/oa/oaUserinfo!generalOptView.do?s_usercode=${creater}">
					<c:choose>
						<c:when test="${not empty oaUserinfo.headpicturename }">
							<img alt="头像"
								src='${contextPath }/oa/oaUserinfo!showImage.do?usercode=${oaUserinfo.usercode}'
								style="width: 100px; height: 100px" />
						</c:when>
						<c:otherwise>
							<img style="width: 100px; height: 100px"
								src='${ctx}/themes/blue/images/bbsheadpicturename.png' />
						</c:otherwise>
					</c:choose>
			</a> <%--         <a href="${contextPath }/oa/oaUserinfo!generalOptView.do?s_usercode=${creater}">  <img alt="头像" src='${contextPath }/oa/oaUserinfo!showImage.do?usercode=${creater}' width="200px" height="25px" /> --%>

				<!--        </a> --> <%--           <img alt="" src='${ctx}/themes/blue/images/photo.jpg'> --%>
				<div class="tool" data-usercode="${oaUserinfo.usercode }">
					<!-- 					<div class="zt"> -->
					<!-- 						<span >20</span><br/> <span>主题</span> -->
					<!-- 					</div> -->
					<div class="jf">
						<span>0</span><br /> <span>积分</span>
					</div>
					<div class="dj">
						<span>新秀</span><br /> <span>等级</span>
					</div>
				</div> <%-- <div class="Album_info">
					<iframe name="userinfo"
						src="<%=request.getContextPath() %>/oa/oaUserinfo!view.do?usercode=${oaUserinfo.usercode }"></iframe>

				</div> --%></td>
			<!--回复内容区域-->
			<td class="right" valign="top">

				<div class="right-top">

					${cp:MAPVALUE("usercode",creater)}
					<%--  <img alt="楼主" title="楼主"
						style="width: 46px; height: 25px"
						src='${ctx}/themes/blue/images/bbsLZ.png' /> --%>
					<fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss" />

					<%--  <img alt="只看楼主" title="只看楼主" 
						style="width: 46px; height: 25px"
						src='${ctx}/themes/blue/images/bbsLZ.png' />
						#ff003c; --%>
					<%-- 					<c:if test="${empty s_creater}">	 --%>
					<!-- 					<input type="button" id="zklz" value="只看楼主" /> -->
					<%-- 					 					</c:if> --%>
					<%-- 					 					<c:if test="${not empty s_creater}">	 --%>
					<!-- 					<input type="button" id="ckqb" value="查看全部" /> -->
					<%-- 					 					</c:if> --%>
					<!-- 					<input type="button" id="desc" value="倒序查看" /> <input -->
					<!-- 						type="button" id="asc" value="取消倒序查看" /> -->

					<%-- 	<div style="width: 100%;" id='div_replay'>
						<iframe id="tabFrames1" name="tabFrames1"
							src="${pageContext.request.contextPath}/oa/oaLeaveMessage!replayList.do?s_djid=${object.themeno}&s_infoType=BBS"
							 onload="reinitIframe(this)"
							 width="100%" frameborder="0" 
							scrolling="no" border="0" marginwidth="0"></iframe>
					</div> --%>



				</div>
				<p>${bodycontent}</p>
				<div align="center"
					style="position: relative; z-index: 100; margin-top: 30px; margin-bottom: 30px;">


					<img alt="收藏" title="收藏" class="bbs" data-laytype="S"
						style="width: 25px; height: 25px"
						src='${ctx}/themes/blue/images/bbssc.png' /> 收藏(<font type="text"
						id="laytypeS" readonly="readonly"> 0 </font>) <img alt="关注"
						title="关注" class="bbs" data-laytype="G"
						style="width: 25px; height: 25px"
						src='${ctx}/themes/blue/images/bbsgz.png' /> 关注(<font type="text"
						id="laytypeG" readonly="readonly">0</font>) <img alt="支持"
						title="支持" class="bbs" data-laytype="Z"
						style="width: 25px; height: 25px"
						src='${ctx}/themes/blue/images/bbszc.png' />支持 (<font type="text"
						id="laytypeZ" readonly="readonly">0</font>) <img alt="反对"
						title="反对" class="bbs" data-laytype="F"
						style="width: 25px; height: 25px"
						src='${ctx}/themes/blue/images/bbsfd.png' /> 反对(<font type="text"
						id="laytypeF" readonly="readonly">0</font>)

				</div>

				<p class="ps"></p>
				<p align="center">"${oaUserinfo.personalsignature }"
				<p>
			</td>
		</tr>
		<c:if test="${fn:length(replyList)>=1}">
			<c:forEach items="${replyList }" var="oaLeaveMessage"
				varStatus="status">

				<tr>
					<td class="left userinfo" align="center"
						data-usercode="${oaLeaveMessage.creater}"
						data-id="${oaLeaveMessage.no}"><a
						href="${contextPath }/oa/oaUserinfo!generalOptView.do?s_usercode=${oaLeaveMessage.creater}">


							<c:choose>
								<c:when
									test="${not empty oaLeaveMessage.sign && 'T' eq oaLeaveMessage.sign}">
									<img alt="头像"
										src='${contextPath }/oa/oaUserinfo!showImage.do?usercode=${oaLeaveMessage.creater}'
										width="100px" height="100px" />
								</c:when>
								<c:otherwise>
									<img style="width: 100px; height: 100px"
										src='${ctx}/themes/blue/images/bbsheadpicturename.png' />
								</c:otherwise>
							</c:choose>
					</a> <%-- 						              <img alt="" src='${ctx}/themes/blue/images/photo.jpg'> --%>

						<div class="tool " data-usercode="${oaLeaveMessage.creater}">
							<!-- <div class="zt">
								<span >0</span><br /> <span>主题</span>
							</div> -->
							<div class="jf">
								<span>0</span><br /> <span>积分</span>
							</div>
							<div class="dj">
								<span>新秀</span><br /> <span>等级</span>
							</div>
						</div> <%-- <div class="Album_info">
							<iframe name="userinfo" id="${oaLeaveMessage.no }" src=""></iframe>

						</div> --%></td>
					<td class="right list" valign="top">
						<div class="right-top userName">
							<a href="javascript:;"
								title="${cp:MAPVALUE('usercode',oaLeaveMessage.creater)}">${cp:MAPVALUE("usercode",oaLeaveMessage.creater)}</a>
							<fmt:formatDate value="${oaLeaveMessage.creatertime }"
								pattern="yyyy-MM-dd HH:mm:ss" />
							<%--  <!-- 顺序显示评论  -->
								<c:if test="${'creatertime asc'eq s_ORDER_BY}">
								<input type="button"   value="#${status.count}楼"/>
								</c:if>
								 <!-- 倒序显示评论  -->
								<c:if test="${'creatertime desc' eq s_ORDER_BY}">
								<input type="button"   value="#${fn:length(replyList)-status.count}楼" onclick="submitItemFrame('SAVE');"/>
								</c:if> --%>

						</div> <input type="hidden" id="parent_lno"
						value="${oaLeaveMessage.no }" />

						<p>
							<!-- 			删除留言提示 -->
							<c:if test="${'D' eq oaLeaveMessage.state}">
								<span style="color: red;"> 该条回复已被管理员删除或禁言</span>
							</c:if>
							<c:if test="${'D' ne oaLeaveMessage.state}">
						${oaLeaveMessage.messageComment} <a class="del"
									href="javascript:;">[回复]</a>
							</c:if>
						</p>

						<p class="ps"></p>
						<p align="center">"${oaLeaveMessage.oauserinfo.personalsignature }"
						<p>

							<c:if test="${not empty  oaLeaveMessage.oaLeaveMessagereplys}">
								<c:forEach items="${oaLeaveMessage.oaLeaveMessagereplys }"
									var="replys">
									<div class="list-div">

										<div class="userPic">
											<img
												src="${contextPath }/oa/oaUserinfo!showImage.do?usercode=${replys.creater}"
												width="50px" height="50px" style="margin-left: 20px;" />
										</div>
										<div class="content">
											<input type="hidden" id="parent_lno" value="${replys.lno }" />
											<div class="userName">

												<a href="javascript:;"
													title="${cp:MAPVALUE('usercode',replys.creater)}">${cp:MAPVALUE("usercode",replys.creater)}</a>

												<span><fmt:formatDate value='${replys.creatertime}'
														pattern='yyyy-MM-dd hh:mm:ss' /></span>
											</div>
											<div class="msgInfo" style="margin-left: 20px;">
												<!-- 			删除留言提示 -->
												<c:if test="${'D' eq replys.state}">
													<span style="color: red;"> 该条回复已被管理员删除或禁言</span>
												</c:if>
												<c:if test="${'D' ne replys.state}">
											     ${replys.messagecomment}
											     <a class="del" href="javascript:;">[回复]</a> <a
													class="email_icon"></a>
											</c:if>
											</div>

											<div style="clear: both;"></div>
										</div>
									</div>
								</c:forEach>
							</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<form id="themeView" action="oaBbsTheme!view.do" method="post">
		<input type="hidden" name="s_ORDER_BY" value="${s_ORDER_BY }" /> <input
			type="hidden" name="themeno" value="${themeno}">

		<!-- 		只看楼主时赋值 -->
		<input type="hidden" name="s_replycreater" value="${s_replycreater }" />
		<input type="hidden" id="themecreater" name="themecreater"
			value="${creater }" />

	</form>
	<c:if test="${fn:length(replyList)>=1}">
		<%@ include file="/page/common/pagingBar.jsp"%>
	</c:if>

	<c:if test="${'T' eq approvalresults}">
		<div class="body">
			<div class="content">

				<form action="oaBbsTheme" namespace="/bbs" id="oaBbsThemeForm"
					method="post" isPlus="false">
					<div class="ksft">快速回复</div>
					<input type="hidden" name="s_ORDER_BY" value="${s_ORDER_BY }" /> <input
						type="hidden" name="s_replycreater" value="${s_replycreater }" />
					<input type="hidden" id="themeno" name="themeno" value="${themeno}" />
					<input type="hidden" name="s_layoutno" value="${s_layoutno}" />
					<div style="width: 100%;">
						<table>
							<tr>
								<td style="width: 15%; text-align: center;">留言</td>
								<td><s:textarea name="oaLeaveMessage.messageComment"
										id="newcontent" value="" rows="4" style="width:100%;" /></td>
							</tr>
						</table>
						<div style="margin: 2% 10% 5% 20%; width: 90%;">
							<span style="text-align: right; width: 5%; padding-top: 2%;"></span>
							<span style="width: 95%; padding: 2% 63% 5% 2%;"> <input
								type="button" class="btn" id="save" value="留言"
								onclick="submitItemFrame('SAVE');" /> &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" Class="btn"
								onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${s_layoutno}';"
								value="返回" />
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:if>
</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">

$(function(){
	$(".userinfo").hover(function(){
		var usercode=$(this).data("usercode");
		var id=$(this).data("id");
		$("#"+id).attr("src","<%=request.getContextPath()%>/oa/oaUserinfo!view.do?usercode="	+ usercode);

					$(this).children(".Album_info").fadeToggle('fast');

				});
	});
	//初始化收藏，支持数据
	$
			.each(
					$('.bbs'),
					function() {
						var laytype = $(this).data("laytype");
						var themeno = $("#themeno").val();
						//收藏，关注
						$
								.ajax({
									type : "POST",
									url : "${contextPath}/bbs/oaBbsAttention!getAttentionNum.do?laytype="
											+ laytype + "&themeno=" + themeno,
									dataType : "json",
									success : function(json) {
										$("#laytype" + laytype).html(
												json.status);
									},
									error : function() {
										parent.alert("数据获取失败，刷新后重试！");
									}

								})
					});

	//初始化发帖人，回复人等级，积分信息

	$('.userinfo')
			.each(
					function(index, element) {
						var usercode = $(element).data("usercode");

						//发帖人，回复人等级，积分信息
						$
								.ajax({
									type : "POST",
									url : "${contextPath}/oa/oaUserinfo!getUserinfo.do?usercode="
											+ usercode,
									dataType : "json",
									success : function(json) {
										$(element).find(".jf").find(
												"span:first").html(json.jf);
										$(element).find(".dj").find(
												"span:first").html(json.dj);
									},
									error : function() {
										parent.alert("数据获取失败，刷新后重试！");
									}

								})
					});

	/* 	//查看全部按钮先隐藏
	 if (null == $("input[name='s_replycreater']").val()
	 || '' == $("input[name='s_replycreater']").val()) {
	 $("#ckqb").hide();
	 } else {
	 $("#zklz").hide();
	 }

	 //查看全部、只看楼主
	 $("#zklz").click(
	 function() {
	 $("input[name='s_replycreater']").attr("value",
	 $("#themecreater").val());
	 $("#zklz").hide();
	 $("#ckqb").show();
	 var srForm = document.getElementById("themeView");
	 // 		srForm.action = 'oaBbsTheme!saveReply.do';
	 srForm.submit();
	 });

	 $("#ckqb").click(function() {
	 $("input[name='s_replycreater']").attr("value", "");
	 $("#zklz").show();
	 $("#ckqb").hide();
	 var srForm = document.getElementById("themeView");
	 srForm.submit();
	 });

	 //默认按回复时间递增排序asc---倒序s_ORDER_BY为''
	 if (null == $("input[name='s_ORDER_BY']").val()
	 || '' == $("input[name='s_ORDER_BY']").val()
	 || 'creatertime asc' == $("input[name='s_ORDER_BY']").val()) {

	 $("#asc").hide();
	 } else {
	 $("#desc").hide();
	 }
	 //查看回复顺序 升序
	 $("#asc").click(function() {
	 $("input[name='s_ORDER_BY']").attr("value", "creatertime asc");
	 $("#asc").hide();
	 $("#desc").show();
	 var srForm = document.getElementById("themeView");
	 srForm.submit();
	 });

	 $("#desc").click(function() {
	 $("input[name='s_ORDER_BY']").attr("value", "creatertime desc");
	 $("#asc").show();
	 $("#desc").hide();
	 var srForm = document.getElementById("themeView");
	 srForm.submit();
	 }); */

	function submitItemFrame(subType) {
		var srForm = document.getElementById("oaBbsThemeForm");
		if (subType == 'SAVE') {
			srForm.action = 'oaBbsTheme!saveReply.do';
		}
		editor.sync(false);
		if (null == $("#newcontent").val() || "" == $("#newcontent").val()) {
			parent.alert("请填写留言内容！");
		} else {
			srForm.submit();
		}
		;
	}

	function reinitIframe(iframe) {
		try {

			var bHeight = iframe.contentWindow.document.body.scrollHeight;

			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

			var height = Math.max(bHeight, dHeight);

			iframe.height = height;

		} catch (ex) {
		}

	}

	$(".bbs")
			.click(
					function() {
						var laytype = $(this).data("laytype");
						var themeno = $("#themeno").val();
						var layNum = parseInt($(this).next("font").html()) + 1;

						//收藏，关注
						$
								.ajax({
									type : "POST",
									url : "${contextPath}/bbs/oaBbsAttention!addSign.do?laytype="
											+ laytype + "&themeno=" + themeno,
									dataType : "json",
									success : function(json) {

										if ("true" == json.status) {
											$("#laytype" + laytype)
													.html(layNum);

											if ("S" == laytype) {
												parent.alert("收藏成功;");
											} else if ("G" == laytype) {
												parent.alert("关注成功;");
											} else {
												parent.alert("操作成功;");
											}

										} else if ("false" == json.status) {
											if ("S" == laytype) {
												parent.alert("已收藏;");
											} else if ("G" == laytype) {
												parent.alert("已关注;");
											} else {
												parent.alert("不可重复操作！");
											}

										}
									},
									error : function() {
										parent.alert("保存失败，刷新后重试！");
									}
								});
					});
	
	
	
	
	
	var get = {
			byId: function(id) {
				return typeof id === "string" ? document.getElementById(id) : id;
			},
			byClass: function(sClass, oParent) {
				var aClass = [];
				var reClass = new RegExp("(^| )" + sClass + "( |$)");
				var aElem = this.byTagName("*", oParent);
				for (var i = 0; i < aElem.length; i++) reClass.test(aElem[i].className) && aClass.push(aElem[i]);
				return aClass;
			},
			byTagName: function(elem, obj) {
				return (obj || document).getElementsByTagName(elem);
			}
		};
		/*-------------------------- +
		事件绑定, 删除
		+-------------------------- */
		var EventUtil = {
			addHandler: function (oElement, sEvent, fnHandler) {
				oElement.addEventListener ? oElement.addEventListener(sEvent, fnHandler, false) : (oElement["_" + sEvent + fnHandler] = fnHandler, oElement[sEvent + fnHandler] = function () {oElement["_" + sEvent + fnHandler]();}, oElement.attachEvent("on" + sEvent, oElement[sEvent + fnHandler]));
			},
			removeHandler: function (oElement, sEvent, fnHandler) {
				oElement.removeEventListener ? oElement.removeEventListener(sEvent, fnHandler, false) : oElement.detachEvent("on" + sEvent, oElement[sEvent + fnHandler]);
			},
			addLoadHandler: function (fnHandler) {
				this.addHandler(window, "load", fnHandler);
			}
		};
		/*-------------------------- +
		设置css样式
		读取css样式
		+-------------------------- */
		function css(obj, attr, value)
		{
			switch (arguments.length)
			{
				case 2:
					if(typeof arguments[1] == "object")
					{	
						for (var i in attr) i == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + attr[i] + ")", obj.style[i] = attr[i] / 100) : obj.style[i] = attr[i];
					}
					else
					{	
						return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj, null)[attr];
					}
					break;
				case 3:
					attr == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + value + ")", obj.style[attr] = value / 100) : obj.style[attr] = value;
					break;
			}
		};






		EventUtil.addLoadHandler(function ()
		{
			
			var oMsgBox = get.byId("msgBox");
 			var oList = get.byClass("list")[0];
			var oUl = get.byTagName("ul", oList)[0];
			var aLi = get.byTagName("li", oList);
			var bSend = false;
			var rSend = false;
			var timer = null;
			var i = 0;
			var maxNum = 140;
			
			

			//禁止表单提交
			EventUtil.addHandler(get.byTagName("form", oMsgBox)[0], "submit", function () {return false;});
			
			//为Ctrl+Enter快捷键绑定发送事件
			EventUtil.addHandler(document, "keyup", function(event){
				var event = event || window.event;
				event.ctrlKey && event.keyCode == 13 && fnSend();
			});
			
			
			//发送回复
			function replySend(){
				var reg = /^\s*$/g;
				var ev = $(this).parent().prev().prev().find("div #rec-area-text-info")[0];
				if(reg.test(ev.value)){
					alert("\u968f\u4fbf\u8bf4\u70b9\u4ec0\u4e48\u5427\uff01");
					$(ev).focus();
				}else if(!rSend){
					alert("\u4f60\u8f93\u5165\u7684\u5185\u5bb9\u5df2\u8d85\u51fa\u9650\u5236\uff0c\u8bf7\u68c0\u67e5\uff01");
					$(ev).focus();
				}else{
					var srForm  = document.getElementById("oaLeaveMessagereplyForm");
					srForm.action = 'oaLeaveMessagereply!save.do';
					srForm.submit();
				}
			}
			
			//发布字符限制
			function confine (){
				var iLen = 0;		
				for (i = 0; i < oConBox.value.length; i++) iLen += /[^\x00-\xff]/g.test(oConBox.value.charAt(i)) ? 1 : 0.5;
				oMaxNum.innerHTML = Math.abs(maxNum - Math.floor(iLen));	
				maxNum - Math.floor(iLen) >= 0 ? (css(oMaxNum, "color", ""), oCountTxt.innerHTML = "\u8fd8\u80fd\u8f93\u5165", bSend = true) : (css(oMaxNum, "color", "#f60"), oCountTxt.innerHTML = "\u5df2\u8d85\u51fa", bSend = false);
			}
			
			//回复字符控制
			function replyControl(){

				var iLen = 0;
				var oReplyMaxNum = $(this).parent().parent().next().find(".maxReply")[0];
				
				var oReplyTxt =$(this).parent().parent().next().find(".replyTxt")[0];
				
				for (i = 0; i < $(this).val().length; i++)
				iLen += /[^\x00-\xff]/g.test($(this).val().charAt(i)) ? 1 : 0.5;
				oReplyMaxNum.innerHTML = Math.abs(maxNum - Math.floor(iLen));	
				maxNum - Math.floor(iLen) >= 0 ? (css(oReplyMaxNum, "color", ""), oReplyTxt.innerHTML = "", rSend = true) : (css(oReplyMaxNum, "color", "#f60"), oReplyTxt.innerHTML = "-", rSend = false);
			}

			//回复功能
			function delLi(){
				
				var aA = get.byClass("del", oUl);
				
				for (i = 0; i < aA.length; i++){
					aA[i].onclick = function (e){
						var oLiDiv = this.parentNode.parentNode;
						
						var atUser = $(oLiDiv).find(".userName a").first().text();
						if(atUser=='${cp:MAPVALUE("usercode",loginUser.usercode)}'){
							var e = e || window.event;
							var element =e.srcElement ||e.target;
							$(element).css("cursor","not-allowed");
							return false;
						}
						if(oLiDiv.lastChild.id=='rec-div'){
							oLiDiv.removeChild(oLiDiv.lastChild);
						}else{
							
						var oReplyDiv = document.createElement("div");
						oReplyDiv.setAttribute("id","rec-div");
						oReplyDiv.innerHTML = "<form action=\"oaLeaveMessagereply\" namespace=\"/oa\" id=\"oaLeaveMessagereplyForm\">\
						        <input type=\"hidden\" id=\"oaLeaveMessagelno\" value=\"\" name=\"lno\"/>\
						        <input type=\"hidden\" id=\"themeno\" name=\"themeno\" value=\"${themeno}\" />\
							    <input type=\"hidden\" id=\"s_layoutno\" name=\"s_layoutno\" value=\"${s_layoutno}\" />\
						        <div id=\"rec-text\">\
							 	<div id=\"rec-img\"><img src=\"${contextPath }/oa/oaUserinfo!showImage.do?usercode=${loginUser.usercode}\"/></div>\
								<div id=\"rec-area\">\
								    <div id=\"rec-area-text\"><textarea id=\"rec-area-text-info\" name=\"messagecomment\"></textarea></div>\
								</div>\
								<div id=\"rec-maxNum\"><span class=\"replyTxt\"></span><span class=\"maxReply\">140</span></div>\
								<div id=\"rec-btn\"><a id=\"rec-btn-reply\" >\u56de\u590d</a></div>\
							 </div></form>";
					    //插入回复框
						oLiDiv.appendChild(oReplyDiv);

						//为回复按钮绑定事件
						var subbtn =$(oLiDiv.lastChild).find("div #rec-btn-reply");
						var replyBox = $(oLiDiv.lastChild).find("div #rec-area-text-info");
						subbtn=subbtn[0];
						replyBox=replyBox[0];
						EventUtil.addHandler(subbtn, "click", replySend);
						EventUtil.addHandler(replyBox, "keyup", replyControl);	
						EventUtil.addHandler(replyBox, "focus", replyControl);
						EventUtil.addHandler(replyBox, "change", replyControl);
						
						$(oLiDiv).find("#oaLeaveMessagelno").val($(oLiDiv).find("#parent_lno").val());
						
						if($(oLiDiv.parentNode).hasClass("extend_list_div")){
							$(oLiDiv).removeClass("content");
							$(oLiDiv).find(".times").css("padding-left","60px");
						}
						
						$(oLiDiv).find("#rec-area-text-info").val("@"+atUser+":");
						$(oLiDiv).find("#rec-area-text-info").css("font-size","13px").css("font-family","Verdana, Arial, Sans-serif;");
						$(oLiDiv).find("#rec-btn-reply").css("font-style","normal").css("font-family","'Times New Roman',Georgia,Serif; ");
						//将元素高度保存
						var iHeight = oReplyDiv.clientHeight - parseFloat(css(oReplyDiv, "paddingTop")) - parseFloat(css(oReplyDiv, "paddingBottom"));
						var alpah = count = 0;
						css(oReplyDiv, {"opacity" : "0", "height" : "0"});	
						timer = setInterval(function (){
							css(oReplyDiv, {"display" : "block", "opacity" : "0", "height" : (count += 8) + "px"});
							if (count > iHeight){
								clearInterval(timer);
								
								timer = setInterval(function (){
									css(oReplyDiv, "opacity", (alpah += 10));
									alpah > 100 && (clearInterval(timer), css(oReplyDiv, "opacity", 100));
								},30);
							}
						},30);
						$(document).ready(
								function() {
									init = setInterval("FrameUtils.initialize(window, init)",
											MyConstant.initTimeForAdjustHeight);
								});
						window.setInterval(function(){
							   parent.document.getElementById("tabFrames1").height = $("#msgBox").height();
							}, 200);
					  }
					};			
				}
			}
			delLi();
		});



		$(document).ready(
				function() {
					init = setInterval("FrameUtils.initialize(window, init)",
							MyConstant.initTimeForAdjustHeight);
				});
		window.setInterval(function(){
		   parent.document.getElementById("tabFrames1").height = $("#msgBox").height();
		}, 200);
</script>
</html>
