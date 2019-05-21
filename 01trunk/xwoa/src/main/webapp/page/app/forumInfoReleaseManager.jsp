<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />

</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>

		<legend>新闻平台管理</legend>

		<s:form id="forumListForm" namespace="/app"
			action="forum" style="margin-top:0;margin-bottom:5"
			method="post">
			 <input type="hidden" name="boardcode" value="INFO_REL" />
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd">新闻版块名称：</td>
					<td width="180"><input type="text" class="span2"
						name="s_forumname" value="${s_forumname }" /></td>
					<td>
						<s:submit method="listInfoReleaseManager" key="opt.btn.query" cssClass="btn" />
<!-- 					<input type="submit" class="btn" -->
<!-- 						data-form="#forumListForm" value="查询" />  -->
						<s:submit method="editInfoRelease" value="创建新闻版块" cssClass="btn"/>
<!-- 						<input type="button" -->
<!-- 						class="btn" target="submit" data-form="#forumListForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/app/forum!editInfoRelease.do" --%>
<!-- 						value="创建新闻版块">  -->
						</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/app/forum!listInfoReleaseManager.do"
		items="objList" var="forum"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>

			<ec:column property="forumid" title="新闻版块名称" style="text-align:center">

				<a 
				href="${pageContext.request.contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&pageBoardCood=infoRM&boardcode=INFO_REL" >
					${forum.forumname} </a>
			</ec:column>
<%-- 			<ec:column property="mebernum" title="成员数" style="text-align:center" /> --%>
			<ec:column property="threadnum" title="帖子数量"
				style="text-align:center">
				${fn:length(forum.threads)} 
<%-- 				${empty forum.threadnum ? 0 : forum.threadnum} --%>
             </ec:column>
			<ec:column property="replynum" title="回复数量" style="text-align:left；">
					${empty forum.replynum ? 0 : forum.replynum}
				</ec:column>
			
			
			<ec:column property="createtime" title="创建时间"
				style="text-align:center">
				<fmt:formatDate value="${forum.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				
					<a 
					href="${contextPath }/app/thread1!forumByInfoReleaseAudit.do?forum.forumid=${forum.forumid}&isAudit=true&pageBoardCood=infoRM&boardcode=INFO_REL"
						> 
						 审批
					</a>
				
					<a 
					href="${contextPath }/app/forum!editInfoRelease.do?forumid=${forum.forumid}&boardcode=INFO_REL"> 
						编辑
					</a>
					<a href="${contextPath }/app/forum!deleteInfoRelease.do?forumid=${forum.forumid}&boardcode=INFO_REL"> 
						删除
					</a>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>