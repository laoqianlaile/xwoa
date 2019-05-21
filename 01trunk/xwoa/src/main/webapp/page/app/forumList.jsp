<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%>
<%-- <%@ include file="/page/common/css.jsp"%> --%>
<title>讨论版列表</title>
</head>
<body>

	<div class="container-fluid">

		<ul class="breadcrumb">
			<li class="active">内部讨论版</li>
		</ul>


<!-- 		<div class="btn-group" style="float: right;"> -->
<%-- 			<form id="frm_thread_search" action="${contextPath }/app/thread!search.do"> --%>
<!-- 				<div class="input-append"> -->
<%-- 				  <input class="span4" id="appendedInput" name="key" style="width: 175px;" type="text" value="${param['key'] }"/> --%>
<!-- 				  <span class="add-on" id="span_thread_search">搜索</span> -->
<!-- 				</div> -->
<!-- 			</form> -->
<!-- 		</div> -->
		
		
		<c:forEach var="forum" items="${objList }" varStatus="s">
			<c:if test="${s.first or s.index % 3 == 0 }">
				<div class="row-fluid" style="margin-bottom: 15px;">
					<div class="span12">
			</c:if>
			
				<div class="span4" style="border: 1px solid #dddddd; border-radius: 4px; height: 315px;">
					<div style="border: 1px solid #dddddd; height: 20px; background-color: #E6F2FA; margin-bottom: 5px;">
						<span class="pull-left">${forum.forumname }</span>
						<span class="pull-right"><a href="${contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&pageBoardCood=info">更多</a></span>
						
					</div>
					<div style="margin-top: 5px;">
						<c:if test="${not empty forum.threads }">
							<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
								<tbody align="left">
									<c:forEach items="${forum.threads }" var="thread" varStatus="ts">
										<c:if test="${7 >= ts.index }">
											<tr>
												<td>
													<a href="${contextPath}/app/reply!list.do?thread.threadid=${thread.threadid}&pageBoardCood=info">
														${thread.titol}
													</a>
												</td>
											</tr>
										</c:if>
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
		
		
		<ul class="breadcrumb" id="ul_wait_join_forum">
			<li class="active">申请加入讨论版列表</li>
			<c:forEach var="w" items="${waitJoinForums }">
				<li class="active"><span style="color: green; margin-left: 5px;">${w.forumname }</span><a href="#" join="0" forumid="${w.forumid }"><span style="color: purple; margin-left: 5px;">申请加入</span></a></li>
			</c:forEach>
			
			
			<li class="active" style="float: right;"><a href="${contextPath }/app/forum!listApply.do">更多</a></li>
		</ul>
		
	</div>

</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>

<%@ include file="/page/common/scripts.jsp"%> 
<script>

	$(function(){
		$('#ul_wait_join_forum a').click(function() {
			var $this = $(this);

			if(1 == $this.attr('join')) {
				return;
			}
			var forumid = $this.attr('forumid');

			$.post('${contextPath }/app/forum!ajaxApplyJoin.do', {
				forumid : forumid
			}, function(result){
				if(0 == result) {
					$this.children('span').text('已加入');
				} else if(2 == result) {
					$this.children('span').text('已加入，等待审批');
				}
				
				$this.attr('join', 1);
			}, 'text');
		});
		
		
		$('#span_thread_search').click(function() {
			$('#frm_thread_search').submit();
		});
		
	});


</script>
</html>

