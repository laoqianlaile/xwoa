<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search">

		<legend>申请加入讨论版</legend>

		<s:form id="forumListForm" namespace="/app"
			action="forum" style="margin-top:0;margin-bottom:5"
			method="post">
			<table cellpadding="0" cellspacing="0" align="left">
				<tr class="searchButton">
					<td class="addTd">讨论版名称：</td>
					<td width="180"><input type="text" class="span2"
						name="s_forumname" value="${s_forumname }" /></td>
					<td>
					<s:submit method="listApply" key="opt.btn.query" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/app/forum!listApply.do"
		items="objects" var="obj"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>
			<ec:column property="obj.forumid" title="讨论版名称" style="text-align:center" >
				<a href="${pageContext.request.contextPath}/app/thread1!list.do?forum.forumid=${obj.forumid}&pageBoardCood=info">
					${obj.forumname} </a>
<%-- 					${obj.forumname} --%>
			</ec:column>

			<ec:column property="obj.mebernum" title="成员数" style="text-align:center" >
			${obj.mebernum}
			</ec:column>
			<ec:column property="obj.key.threadnum" title="帖子数量"
 			style="text-align:center">  
				${empty obj.threadnum ? 0 : obj.threadnum}  
             </ec:column>  
			<ec:column property="obj.replynum" title="回复数量" style="text-align:left；">
					${empty obj.replynum ? 0 : obj.replynum}
				</ec:column>
			<ec:column property="obj.forumStaffs" title="管理员"
				style="text-align:center">
				<c:set var="manager">
					<c:forEach var="u" items="${obj.forumStaffs}">
						<c:if test="${'1' eq u.userrole}">
                                    		${cp:MAPVALUE('usercode', u.cid.usercode)}
                        </c:if>
					</c:forEach>
				</c:set> ${empty manager ? '无' : manager}
			</ec:column>

			<ec:column property="obj.createtime" title="创建时间"
				style="text-align:center">
				<fmt:formatDate value="${obj.createtime}"
 					pattern="yyyy-MM-dd HH:mm:ss" /> 
			</ec:column> 

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center"> 


				<c:choose>
					<c:when test="${1 eq obj.flag }">
					<a href="${contextPath }/app/forum!applyJoin.do?forumid=${obj.forumid}"> 
						申请加入
					</a>
					</c:when>
					<c:when test="${2 eq obj.flag }">待批准</c:when>
					<c:when test="${3 eq obj.flag }">
					<a  href="${contextPath }/app/forum!inOut.do?forumid=${obj.forumid}"> 
						退出
					</a>
					</c:when>
				</c:choose>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>