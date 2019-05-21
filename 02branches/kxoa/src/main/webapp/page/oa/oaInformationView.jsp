<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaInformation.view.title" /></title>


<!-- <link -->
<%-- 	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/global.css" --%>
<!-- 	rel="stylesheet"> -->
<!-- <link -->
<%-- 	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/layout.css" --%>
<!-- 	rel="stylesheet"> -->
	
<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/information.css"
	rel="stylesheet">
<style type="text/css">
   .docFile{background:url('${ctx}/scripts/kindeditor-4.1.7/themes/default/default.png') no-repeat 0 -95px;display:inline-block;height:17px;font-size:12px;padding-left:18px;}
   .videoFile{background:url('${ctx}/scripts/kindeditor-4.1.7/themes/default/default.png') no-repeat 0 -528px;display:inline-block;height:17px;font-size:12px;padding-left:18px;}
	body { 
	    overflow-x : hidden;   
	    overflow-y : hidden;   
	}

</style>	
</head>


<body class="sub-flow">
	<%-- <div class="tableleft">
		<table border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td class="news"><a title="${row.datavalue}"
					href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?flag=GGZY">
						新闻中心 </a></td>
			</tr> 
			<c:forEach var="row" items="${cp:DICTIONARY('infoType')}">
				<tr>
				<c:if test="${row.datatag eq 'T'}">
					<td ${row.datacode eq infoType ? ' style="background: #D8E2E5;"' : ''}><a
						title="${row.datavalue}"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${row.datacode}&flag=GGZY">
							<c:out value="${row.datavalue}" />
					</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div> --%>

	<div class="tableright" style="left:0px;position:static;width:98%" >
		<section class="public-position" id="publicPosition">
			<div class="pubpos-nav">
				您当前的位置：
				<c:if test="${not empty infoType}">&nbsp;&nbsp;
			<a title="${cp:MAPVALUE(" infoType",infoType) }" class="CurrChnlCls"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?flag=GGZY">${cp:MAPVALUE("infoType",infoType)
						}</a>
				</c:if>
			</div>
			<div class="pubpos-search-box">

				<%@ include file="/page/common/messages.jsp"%>
				<s:form action="oaInformation" method="post" namespace="/oa"
					id="oaInformationForm">
					<input type="hidden" id="infoType" name="infoType"
						value="${infoType}" />
					<input type="hidden" id="flag" name="flag" value="${flag}" />
					<s:hidden name="show_type" value="%{show_type}"></s:hidden>

					<input name="s_searchword" class="search-txt"
						id="txt_searchWord_nav" type="text" size="100"
						placeholder="请输入关键词">



					<input name="submit" class="submit-botton"
						id="home_btnSearchSubmit" type="submit" value="搜 索">
				</s:form>
			</div>
		</section>

		<div class="cont-tabShow">

			<span
				style="width: 100%; position: relative; text-align: center; font-size: 20px; display: block;">
				<s:property value="%{title}" /><br> <c:if
					test="${not empty  docNo}">             
	                        文号:<s:property value="%{docNo}" />&nbsp;&nbsp;&nbsp;
	       </c:if>
			</span>

			<hr style="page-break-after: always; size="2" color="#ff0000" />

			<div>
				<span
					style="width: 100%; position: relative; font-size: 15px; line-height: 20px; text-align: center; display: block;"><br>
					发布日期:<s:date name="releaseDate" format="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;
					<c:if test="${not empty  author}">            
	                        作者:<s:property value="%{author}" />&nbsp;&nbsp;&nbsp;
	                        </c:if> <c:if test="${not empty  docNo}">
	                       重要度:${cp:MAPVALUE('IMP',majorDegree)}
	       </c:if> </span>
			</div>
		
		<div class="show-hd">
			<div>
				<span style="width: 100%; padding-left: 10px; padding-top: 15px;">
				 <iframe id="bodyContentIframe" 	width="100%" frameborder="0" 
				 scrolling="auto" border="0"></iframe>
				</span>
			</div>
			
			<c:if test="${not empty docAttachments || not empty videoAttachments}">
				<div>
					<span style="width: 100%; position: relative; padding-left: 20px;"><font
						color="blue"><strong>附件下载</strong></font>:
						<c:if test="${not empty docAttachments}">
							<c:forEach var="docFile" items="${docAttachments}">
							  <a href="#" class="docFile" onclick="downFile('${docFile.id}')"
							   style="text-decoration: underline"> ${docFile.fileName} </a>&nbsp;&nbsp;
							</c:forEach>
						</c:if>  
						<c:if test="${not empty videoAttachments}">
						   <c:forEach var="videoFile" items="${videoAttachments}">
						     <a href="#" class="videoFile" onclick="downFile('${videoFile.id}')"
						       style="text-decoration: underline"> ${videoFile.fileName} </a>&nbsp;&nbsp;
						  </c:forEach>  
						</c:if>
						</span>
				</div>
			</c:if>
			<br>
		</div>
		<div style="padding-bottom: 20px;">
			<!--   是否可以留言回复 -->
			<c:if test="${'Y' eq isAllowComment}">
				<input type="button" name="back" Class="btn" value="展开留言"
					id="s_replay" />
			</c:if>
			<%-- <c:if test="${'F' ne show_type}">
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>
			</c:if>	
			<c:if test="${'F' eq show_type}"> --%>
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.location.href='${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${infoType}'"/>
			<%-- </c:if> --%>
			<%-- <c:if test="${ empty show_type }">
			<input type="button" class="btn" style="cursor:pointer;" id="backButton" value='返回'>
	</c:if>		 --%>
		</div>
		<c:if test="${'Y' eq isAllowComment}">
			<div style="width: 100%; float: left;" id='div_replay'>
				<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
				<iframe id="tabFrames1" name="tabFrames"
					src="<%=request.getContextPath()%>/oa/oaLeaveMessage!replayList.do?s_djid=${no}&s_infoType=${infoType}"
					width="100%" frameborder="0" onload="onLoadHeight(this);" height="500" scrolling="no" border="0"
					marginwidth="0"></iframe>
			</div>
		</c:if>
		<!-- 	</fieldset> -->
	</div>
<div id="bodyContentDiv" style="display:none">${bodyContent}</div>
</body>

<script type="text/javascript">
	function downFile(id) {
		var url = "oaInformation!downLocalStuffInfo.do?no=" + id;
		document.location.href = url;
	}
	function onLoadHeight(t) {
		var height = window.frames['tabFrames'].document.body.scrollHeight + 16;
		if (height > 500) {
			t.height = height;
			t.style.height = height+"px";
		} else {
			t.height = 500;
			t.style.height = 500+"px";
		}
	}
	//是否显示留言
	$(function(){
		function isShow() {
			if ('checked' == $('#div_replay').attr("class")) {
				$('#div_replay').hide();
				$('#div_replay').removeAttr("class");
				$('#s_replay').attr("value", "展开留言");
			} else {
				$('#div_replay').show();
				$('#div_replay').attr("class", "checked");
				$('#s_replay').attr("value", "收起留言");

			}
		}
		$('#s_replay').live(
				"click",
				function() {
					isShow();
					setInterval("FrameUtils.initialize(window, init)",
							MyConstant.initTimeForAdjustHeight);
				});
		

		$('#div_replay').hide();
	});
	

	

	$(document).ready(
			function() {
				init = setInterval("FrameUtils.initialize(window, init)",
						MyConstant.initTimeForAdjustHeight);
			});
	$(function() {
		$('#home_btnSearchSubmit')
				.click(
						function() {
							$form = $('#oaInformationForm');
							$form
									.attr('action',
											'${pageContext.request.contextPath}/oa/oaInformation!mainlist.do');
							$form.submit();
						});
		
		readContent();
	});

	function readContent(){
		$("#bodyContentIframe").contents().find("body").html($("#bodyContentDiv").html());
		setTimeout(function(){
			var h=$("#bodyContentIframe").contents().find("body")[0].scrollHeight+27;
			$("#bodyContentIframe").height(h);
			setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		},200);
	}
</script>
</html>
