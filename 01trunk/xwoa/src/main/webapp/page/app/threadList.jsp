<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<title>帖子</title>
</head>
<body>

	<form action="" method="post">
		<input type="hidden" name="forum.forumid" value="${object.forum.forumid }" />
		<input type="hidden" name="pageBoardCood" value="${pageBoardCood }" />
	</form>

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

		
				<c:if test="${not empty objList }">
					<li class="active"><c:if
							test="${empty param['s_threadType'] }">
			  			${objList[0].forum.forumname }
			  		</c:if> <c:if test="${not empty param['s_threadType'] }">
							<a
								href="${contextPath }/app/thread1!list.do?forum.forumid=${objList[0].forum.forumid }&pageBoardCood=${param['pageBoardCood']}">
								${objList[0].forum.forumname } </a>
							
						</c:if></li>
				</c:if>
			<c:if test="${not empty param['s_threadType'] }">
				<li class="active">/${param['s_threadType'] }</li>
			</c:if>
             <a href="javascript:history.go(-1);"> 返回 </a>

		</ul>

		<c:if test="${canCreate }">
			<div class="row toolsBar" style="padding: 10px 0px;">
				<div class="span12 ">
					<div class="btn-group" style="float: left;">
<!-- 						<a class="btn" -->
<%-- 							href="${contextPath}/app/thread!edit.do?forum.forumid=${object.forum.forumid }&pageBoardCood=${param['pageBoardCood']}"> --%>
							<c:choose>
							<c:when test="${pageBoardCood eq 'infoRM' or pageBoardCood eq 'infoR'}">
					  		<input type="button" Class="btn"
							onclick="javascript:window.location.href='${contextPath}/app/thread1!edit.do?forum.forumid=${object.forum.forumid }&pageBoardCood=${param['pageBoardCood']}';" 
							value="创建新闻" />	
					  		</c:when>
							<c:otherwise>
							<input type="button"  Class="btn"
							onclick="javascript:window.location.href='${contextPath}/app/thread1!edit.do?forum.forumid=${object.forum.forumid }&pageBoardCood=${param['pageBoardCood']}';" 
							value="创建帖子" />
					  		</c:otherwise>
							</c:choose>
<!-- 						</a> -->

					</div>

					<%-- <c:choose>
					<c:when test="${isInfoReleaseAudit and empty param['isFromInfoRelease'] }">
			  			<a href="${contextPath}/app/forum!listInfoReleaseManager.do" style="margin-left: 50px; font-size: 12; color: green;">返回 新闻管理平台</a>
			  		</c:when>
			  		<c:when test="${not empty param['isFromInfoRelease'] }">
			  			<a href="${contextPath }/app/forum!forumByInfoRelease.do" style="margin-left: 50px; font-size: 12; color: green;">返回 新闻平台</a>
			  		</c:when>
			  		<c:otherwise>
			  			<a href="${contextPath }/app/forum!list.do" style="margin-left: 50px; font-size: 12; color: green;">返回 内部讨论版</a>
			  		</c:otherwise>
			  	</c:choose> --%>





					<%-- <c:if test="${!isInfoReleaseAudit }"> --%>

					<%-- </c:if> --%>

				</div>
			</div>
		</c:if>

<!-- 		<div class="btn-group" style="float: right;"> -->
<!-- 			<form id="frm_thread_search" -->
<%-- 				action="${contextPath }/app/thread!search.do"> --%>
<!-- 				<div class="input-append"> -->
<!-- 					<input class="span4" id="appendedInput" name="key" -->
<%-- 						style="width: 175px;" type="text" value="${param['key'] }" /> <span --%>
<!-- 						class="add-on" id="span_thread_search">搜索</span> -->
<!-- 				</div> -->
<!-- 			</form> -->
<!-- 		</div> -->

		<div class="row-fluid">
			<div class="span12">
				<c:forEach items="${objList }" var="thread">
					<div id="div_${thread.threadid }">
						<div style="color: #006699">
							<h3>
								<a
									href="${contextPath}/app/reply!list.do?thread.threadid=${thread.threadid}&pageBoardCood=${pageBoardCood}">${thread.titol
									}</a>

								<c:if test="${'T' eq thread.threadLock }">
									<span style="margin-left: 50px; font-size: 5px; color: red;">
										[锁定 未发布] </span>
								</c:if>

								<c:if
									test="${isInfoReleaseAudit and not empty param['isAudit'] }">
									<a
										href="${contextPath}/app/thread1!passInfoAudit.do?threadid=${thread.threadid}&pageBoardCood=${param['pageBoardCood']}"
										style="margin-left: 50px; font-size: 5px; color: #005580;"
										target="ajax" warn="是否通过审核？" callback="callback1">通过审核</a>
								</c:if>
							</h3>
						</div>
						<div
							STYLE="height: 80px; overflow: hidden; text-overflow: ellipsis">

							<c:choose>
								<c:when test="${not empty thread.summary }">
							${thread.summary }
						</c:when>
								<c:otherwise>
							${thread.content }
						</c:otherwise>
							</c:choose>


						</div>
						<div
							style="position: relative; background-color: #f2f8ef; padding: 2px; color: gray;">
							<span>${cp:MAPVALUE('usercode', thread.wirterid)}</span> <span><fmt:formatDate
									value="${thread.posttime}" pattern="yyyy-MM-dd HH:mm:ss" /></span> <span>浏览数
								${empty thread.viewnum ? 0 : thread.viewnum}</span> <span>回复数
								${empty thread.replnum ? 0 : thread.replnum}</span>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
		<c:set var="listURL" value="thread!list.do" />
		<%@ include file="/page/common/pagingBar.jsp"%>
	</div>
	<div class="background" id="background" style="display: none;"></div>
	<div class="progressBar" id="progressBar" style="display: none;"></div>
	<%@ include file="/page/common/charisma-js.jsp"%>

	<script>
		<c:choose>
		<c:when test="${isInfoReleaseAudit }">
		function callback1(json) {
			json = $.parseJSON(json);
			if (json.info) {
				$('#div_' + json.id).remove();
			}
		}
		</c:when>
		<c:otherwise>
		$(function() {
			$('#span_thread_search').click(function() {
				$('#frm_thread_search').submit();
			});

		});
		</c:otherwise>
		</c:choose>
	</script>

</body>
</html>


