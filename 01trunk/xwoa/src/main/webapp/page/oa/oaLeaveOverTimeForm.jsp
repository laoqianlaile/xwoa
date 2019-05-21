<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <%@ include file="/page/common/taglibs.jsp"%> 
 <%@ include file="/page/common/charisma-css.jsp"%> 
<script type="text/javascript" data-main="${pageContext.request.contextPath }/scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.tokeninput/styles/token-input-facebook.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/plugin/treetable/Treetable_files/jqtreetable.css" />
<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
</script>
<title>LIST</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/oa/oaLeaveOverTime!list.do">请假列表页面</a></li>
		</ul>
		<!-- <div class="row searchArea box"> -->
<%-- 				<form id="channelArticleForm" action="${pageContext.request.contextPath}/oa/channelArticle!list.do" method="post">
					<fieldset>
						<input type="hidden" name="orderField" value="${param['orderField'] }" /> 
						<input type="hidden" name="orderDirection" value="${param['orderDirection'] }" /> 
						<input type="hidden" name="s_channelId" value="${s_channelId}" /> 
				        <div class="row searchBar">
				        	<div class="span2 title">发表时间：</div>
					        <div class="span2"><input type="text" class="span2 Wdate" name="s_createtime" value="${s_createtime}" /></div>
					        <div class="span2 title">信息标题：</div>
							<div class="span2"><input type="text" class="span2"  name="s_title" value="${s_title }"/></div> 
							<div class="span2 title">信息发布人：</div>
							<div class="span2"><input type="text" class="autocomplete"   name="s_publisher"  data-token-limit='1' 
							      	data-pre-populate='${populate}' 
									data-url="${pageContext.request.contextPath}/oa/channelArticle!selectUser.do" />
							</div>
					    </div>
					    <div  class="row searchBar">
							<div class="span8 title">&nbsp;<a class="btn btn-primary" target="submit" form="#channelArticleForm" href="javascript:;">
									<i class="icon-search icon-white"></i> 查询</a>
							</div>
						</div>
					</fieldset>
				</form> --%>
	<!-- 	</div> -->
		<div class="row-fluid">
				<div class="span3" style="border: 1px solid #dddddd; border-radius: 4px;">
				<table class="tablemain" style="min-width: 20px; width: 100%">
					<tbody align="center" id="table-detailTree">
						<c:forEach items="${units}" var="detail">
							<tr id="tr_${detail.unitcode}">
								<td align="left">
								<a href="${pageContext.request.contextPath}/oa/oaLeaveOverTime!edit.do?s_unitcode=${detail.unitcode}&navTab=${param['navTab']}">
								${detail.unitname}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="span9">
<%-- 			<div class="row toolsBar">
				<a class="btn btn-success" href="${pageContext.request.contextPath}/oa/channelArticle!edit.do">
					<i class="icon-plus icon-white"></i> 信息发布
				</a>
			</div> --%>
					<div class="tab-content">
						<div class="tab-pane <c:if test='${"profile" eq param["navTab"]  or empty param["navTab"] }'>active</c:if>" id="profile">
						<table class="center table table-striped table-bordered bootstrap-datatable datatable custom" form="#channelArticleForm">

								<thead>
									<tr>
										<th width="20%" >部门名称</th>
										<th width="15%" >用户名</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${unitusers }" var="obj">
										<tr>
										<td>${cp:MAPVALUE('unitcode',obj.id.unitcode) }</td>
										<td>${cp:MAPVALUE('usercode',obj.id.usercode) }</td>
										<td><a class="btn btn-info" href="oaLeaveOverTime!add.do?usercode=${obj.id.usercode}"
													title="编辑"> <i class="icon-plus icon-white"></i>
												</a></td>
<%-- 									<td title="${obj.oaChannel.channelName}">${obj.oaChannel.channelName }</td>
											<td title="${obj.title }" orderField="title">${obj.title }</td>
											<td title="${cp:MAPVALUE('ArticleType',obj.type )}">${cp:MAPVALUE('ArticleType',obj.type )}</td>
											<td title="${obj.author}">${obj.author}</td>
											<td title="${obj.deptno }">${obj.deptno }</td>
											<td title="<fmt:formatDate value="${obj.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${obj.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:choose>
												<c:when test="${obj.type eq 'link' && not empty obj.link}">
													<a class="btn btn-success" onclick="locate('${obj.link}')"
													title="查看"> <i class="icon-zoom-in icon-white"></i>
													</a> 
												</c:when>
												<c:otherwise>
												<a class="btn btn-success" href="channelArticle!view.do?articleId=${obj.articleId}"
													title="查看"> <i class="icon-zoom-in icon-white"></i>
												</a> 
												</c:otherwise>
												</c:choose>
												<a class="btn btn-info" href="channelArticle!edit.do?articleId=${obj.articleId}"
													title="编辑"> <i class="icon-edit icon-white"></i>
												</a>

												<button class="btn btn-danger"
													title="删除 "  onclick="qualify('${obj.articleId}')"> <i
													class="icon-trash icon-white"></i>
												</button>
										</td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:set var="listURL" value="${pageContext.request.contextPath }/oa/channelArticle!list.do"></c:set>
							<%@ include file="/page/common/pagingBar.jsp" %>
								<c:if test="${empty maxPageItems}">
								<c:set var="maxPageItems" value="20"></c:set>
							</c:if> 
						</div>
					</div>
				</div>
			</div>
		</div>
	<form id="channelArticleDeleteForm" action="${pageContext.request.contextPath}/oa/channelArticle!delete.do" method="post">
		<input type="hidden" name="articleId" id="articleId" />
	</form>
	<div class="background" id="background" style="display: none;"></div>
	<div class="progressBar" id="progressBar" style="display: none;"></div>
	<%@ include file="/page/common/charisma-js.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugin/treetable/Treetable_files/jqtreetable.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jQueryCheckExt.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function () {
        var imgpath = '${pageContext.request.contextPath}' + "/scripts/plugin/treetable/images/TreeTable";
        var $roleTree = $("#table-detailTree");
        var index = $.parseJSON('${INDEX}').indexes;
        var $objRoleTree = new jQueryCheckExt();
        $objRoleTree.makeCkeckBoxTreeTable($roleTree, index, imgpath);
        $('#tr_${s_unitcode}').css('background-color', '#5CACEE');
    });
	</script>
</body>
</html> 