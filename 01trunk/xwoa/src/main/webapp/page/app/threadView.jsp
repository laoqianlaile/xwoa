<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<title>讨论版列表</title>
<%-- <sj:head locale="zh_CN" /> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
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
								'textarea[id="reply"]',
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
													"innermsg_form").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"innermsg_form").submit();
										});
									}
								});
				prettyPrint();
			});
</script>
</head>
<body>
	<form action="" method="post"><input type="hidden" name="thread.threadid" value="${object.thread.threadid }" />
		<input type="hidden" name="pageBoardCood" value="${pageBoardCood }" />
	</form>


	<c:if test="${canReply }">
		<a id="scrlBotm" style="right: 50px; position: fixed; top: 110px; z-index: 16; cursor: pointer; overflow: hidden; text-indent: -9999px; width: 50px; height: 50px; background: url(${contextPath}/images/gototop-bg-v2.png);"></a>
	</c:if>

	<div class="container-fluid">
		<ul class="breadcrumb">
			<li class="active"><c:choose>
					<c:when
						test="${pageBoardCood eq 'infoRM'}">
						<a href="${contextPath }/app/forum!listInfoReleaseManager.do">新闻管理平台</a>
					</c:when>
					<c:when test="${pageBoardCood eq 'infoR'}">
						<a href="${contextPath }/app/forum!forumByInfoRelease.do">新闻平台</a>
					</c:when>
					<c:when
						test="${pageBoardCood eq 'infoM'}">
						<a href="${contextPath }/app/forum!manager.do?manager=1">内部讨论版管理</a>
					</c:when>
					<c:when
						test="${pageBoardCood eq 'info'}">
						<a href="${contextPath }/app/forum!list.do">内部讨论版</a>
					</c:when>
				</c:choose> <span class="divider">/</span></li>
				 <li class="active">
				<a href="${contextPath}/app/thread1!list.do?forum.forumid=${thread.forum.forumid}&pageBoardCood=${pageBoardCood}">${thread.forum.forumname }</a>
			  </li>
			   <c:if test ="${not empty thread.threadType }">
			    <span class="divider">/</span>
			  <li class="active"><a href="${contextPath}/app/thread1!list.do?forum.forumid=${thread.forum.forumid}&s_threadType=${thread.threadType }&pageBoardCood={pageBoardCood}">${thread.threadType }</a><!-- <span class="divider">/</span> --></li>
				</c:if>
				
			
		</ul>
		
<!-- 		<div class="btn-group" style="float: right;"> -->
<%-- 			<form id="frm_thread_search" action="${contextPath }/app/thread!search.do" method="post"> --%>
<!-- 				<div class="input-append"> -->
<%-- 				  <input class="span4" id="appendedInput" name="key" style="width: 175px;" type="text" value="${param['key'] }"/> --%>
<!-- 				  <span class="add-on" id="span_thread_search">搜索</span> -->
<!-- 				</div> -->
<!-- 			</form> -->
<!-- 		</div> -->

		

		<div class="row-fluid">
			<div class="span12">
				<div class="box-content">
					<div>
						<div style="color: #006699">
							<h3>${thread.titol }</h3>
						</div>
						<div style="position: relative; background-color: #f2f8ef; color: gray; margin-bottom: 10px; margin-top: 10px;">
							<span>${cp:MAPVALUE('usercode', thread.wirterid)}</span>
							<span><fmt:formatDate value="${thread.posttime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							<span>浏览数 ${empty thread.viewnum ? 0 : thread.viewnum }</span>
							<span>回复数 ${empty thread.replnum ? 0 : thread.replnum }</span>
						</div>
						<div>
							${thread.content }
						</div>
						
						<c:if test="${not empty fileinfos }">
							<div style="margin-top: 10px;">
								<c:forEach var="fi" items="${fileinfos }">
									<div style="margin-top: 10px">
										<a target="download" data-filecode="${fi.filecode}">${fi.filename}.${fi.fileext}</a>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
					
					
				<c:if test="${not empty objList }">
					<hr />
					<div class="box-content">
						<c:forEach var="obj" items="${objList}">
							<div style="border:1px solid green; margin-bottom: 5px;">
								<div style="position: relative; background-color: #f2f8ef; color: gray; margin-bottom: 10px; margin-top: 10px;">
									<span>回帖人：${cp:MAPVALUE('usercode', obj.userid)}</span>
									<span><fmt:formatDate value="${obj.replytime }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
								<div>
									${obj.reply }
								</div>
								
								<c:if test="${not empty replyAnnexs[obj.replyid]}">
									<div style="margin-top: 10px;">
										<c:forEach var="fi" items="${replyAnnexs[obj.replyid]}">
											<div style="margin-top: 10px;">
												<a target="download" data-filecode="${fi.filecode}">${fi.filename}.${fi.fileext}</a>
											</div>
										</c:forEach>
									</div>
								</c:if>
							</div>
						</c:forEach>
						
						<c:set var="listURL" value="reply!list.do" />
						<%@ include file="/page/common/pagingBar.jsp"%>
					</div>
				</c:if>
				
				<c:if test="${canReply }">
						<a id="scrlTop" style="margin-left:94%;">快速置顶</a>
				</c:if>
				<c:if test="${canReply or ('INFO_REL' eq thread.forum.boardcode and 'T' eq thread.threadReply)}">
					<hr />
					<div class="box-content">
						<form class="form-horizontal" id="innermsg_form" action="${pageContext.request.contextPath}/app/reply!save.do" method="post" validate="true">
								<input type="hidden" name="thread.threadid" value="${thread.threadid }" />
								<input type="hidden" name="pageBoardCood" value="${pageBoardCood }" />
								<input id="hid_reply_annex" type="hidden" name="replyAnnexId" />
								
								
								<fieldset  class="form">
									<legend>回复</legend>
										<div class="control-group">
											<label class="control-label" for="smtpurl">附件：</label>
											
												<div class="controls">
													<input type="file" id="upload-reply-thread" data-opt-id="FORUM" data-input-id="hid_reply_annex"/>
<!-- 													<input type="file" id="upload-reply-thread" optID="FORUM" inputID="hid_reply_annex"/> -->
													
													
												</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="msgcontent">内容：</label>
											
											<div class="controls">
<!-- 												<textarea name="reply" class="cleditor" rows="5" cols="50"></textarea> -->
												<textarea name="reply" id="reply"
												cols="40" rows="2" style="width: 100%;"></textarea>	
											</div>
										</div>
										
										<div id="innermsg_btns" class="formButton">
											<button class="btn" onclick="javascript:history.back();return false;">返回</button>
											<input type="submit" data-form="innermsg_form" class="btn" value="回复帖子" />
										</div>
								</fieldset>
							
						</form>
					</div>
				</c:if>
			</div>
		</div>
		
		
	</div>

</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
 

<%@ include file="/page/common/scripts.jsp"%>
<script>
	$(function() {
	
		$('#scrlBotm').click(function () {
            $('html, body').animate({
                scrollTop: $(document).height()
            },
            1500);
            return false;
        });
		$('#scrlTop').click(function(){
			  $('html, body').animate({
	                scrollTop:parseInt(0)
	            },
	            1500);
	            return false;
		});
		$('#span_thread_search').click(function() {
			$('#frm_thread_search').submit();
		});
	});
</script>
</html>



