<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%>

<title>讨论版列表</title>
</head>
<body>

	<div class="container-fluid">

		<ul class="breadcrumb">
			<li class="active">新闻平台</li>
		</ul>

		<div class="btn-group" style="float: right;">
			<form id="frm_thread_search" action="${contextPath }/app/thread1!search.do">
				<div class="input-append">
				  <input class="span4" id="appendedInput" name="key" style="width: 175px;" type="text" value="${param['key'] }"/>
				  <span class="add-on" id="span_thread_search">搜索</span>
				</div>
			</form>
		</div>
		
		<c:forEach var="forum" items="${objList }" varStatus="s">
			<c:if test="${s.first or s.index % 3 == 0 }">
				<div class="row-fluid" style="margin-bottom: 15px;">
					<div class="span12">
			</c:if>
			
				<div class="span4" style="border: 1px solid #dddddd; border-radius: 4px; height: 315px;">
					<div style="border: 1px solid #dddddd; height: 20px; background-color: #E6F2FA; margin-bottom: 5px;">
						<span class="pull-left">${forum.forumname }</span>
						<span class="pull-right"><a href="${contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&pageBoardCood=infoR">更多</a></span>
						
					</div>
					<div style="margin-top: 5px;">
						<c:if test="${not empty forum.threadList }">
							<table class="table table-striped table-bordered custom">
								<tbody align="left">
									<c:forEach items="${forum.threadList }" var="thread" varStatus="ts">
										
											<tr>
												<td>
													<a href="${contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&s_threadType=${thread.threadType }&pageBoardCood=infoR">
														[${thread.threadType }]
													</a>
													<a href="${contextPath}/app/reply!list.do?thread.threadid=${thread.threadid}&pageBoardCood=infoR">
														${thread.titol}
													</a>
												</td>
											</tr>
										
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
			
			
			
			<c:if test="${s.last or s.index % 3 == 2 }">
					</div>
				</div>
			</c:if>
		</c:forEach>
		
		
			</div>
		</div>
		
	</div>

</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<%-- <%@ include file="/page/common/charisma-js.jsp"%> --%>
<script>
	$(function(){
		$('#span_thread_search').click(function() {
			$('#frm_thread_search').submit();
		});
	});
</script>
</html>

