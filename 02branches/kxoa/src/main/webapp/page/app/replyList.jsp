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

	<div class="container">

		<ul class="breadcrumb">
			<li class="active">内部讨论版</li>
		</ul>

		<div class="row searchArea box">
			<div class="span12">
				<form id="forumListForm"
					action="${pageContext.request.contextPath}/app/forum!list.do"
					method="post">
					<fieldset>
						<input type="hidden" name="orderField"
							value="${param['orderField'] }" /> <input type="hidden"
							name="orderDirection" value="${param['orderDirection'] }" />

						<div class="row searchBar">
							<div class="span2 title">讨论版名称：</div>
							<div class="span2">
								<input type="text" name="s_forumname" class="span2"
									value="${s_forumname}" />
							</div>
							<div class="span2 offset6">
								<a class="btn btn-primary" target="submit" form="#forumListForm"
									href="javascript:;"> <i class="icon-search icon-white"></i>
									查询
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<div class="row toolsBar">
			<div class="span12">
				<a class="btn btn-primary"
					action="${contextPath }/app/forum!edit.do" target="submit"
					form="#forumListForm"> <i class="icon-plus icon-white"></i>
					创建讨论版
				</a>

				<c:if test="${cp:CHECKUSEROPTPOWER('FORUM', 'confirmApplyJoin') }">
					<a class="btn"
						action="${contextPath }/app/forum!confirmApplyJoin.do"
						target="submit" form="#forumListForm"> <i
						class="icon-eye-open"></i> 审批加入讨论版
					</a>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="span12">

				<table class="table table-striped table-bordered bootstrap-datatable datatable custom" form="#forumListForm">
					<thead align="center">
						<tr>

							<c:set var="tforumname">讨论版名称</c:set>
							<th orderField="forumname" width="20%">${tforumname}</th>

							<c:set var="tmebernum">成员数</c:set>
							<th width="10%">${tmebernum}</th>

							<c:set var="tthreadnum">帖子数量</c:set>
							<th width="10%">${tthreadnum}</th>

							<c:set var="treplynum">回复数量</c:set>
							<th width="10%">${treplynum}</th>

							<th width="15%">管理员</th>
							
							<c:set var="tcreatetime">创建时间</c:set>
							<th orderField="createtime" width="17%">${tcreatetime}</th>
							
							<th>功能</th>

						</tr>
					</thead>
					<tbody align="center">
						<c:forEach items="${objList }" var="reply">
							<tr target="pk" rel="${reply.replyid}">
								
									<td>${reply.replyid}</td>
								
								
									<td>${reply.threadid}</td>
								
									<td>${reply.reply}</td>
								
									<td>${reply.replytime}</td>
								
									<td>${reply.userid}</td>
								
									<td>${reply.username}</td>
								
									<td>${reply.annexnum}</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<c:set var="listURL" value="forum!list.do"></c:set>
		<%@ include file="/page/common/pagingBar.jsp"%>
	</div>

</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/charisma-js.jsp"%>
</html>


