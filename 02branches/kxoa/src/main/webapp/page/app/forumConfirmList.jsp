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
	<table cellpadding="0" cellspacing="0" align="left">
	<br>
	<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
	</table>

	<ec:table
	
		items="objList"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>

			<ec:column property="forumname" title="讨论版名称"
				style="text-align:center">

			</ec:column>
			<ec:column property="mebernum" title="成员数" style="text-align:center" />
			<ec:column property="threadnum" title="帖子数量"
				style="text-align:center">
				${empty forum.threadnum ? 0 : forum.threadnum}
             </ec:column>
			<ec:column property="replynum" title="回复数量" style="text-align:left；">
					${empty forum.replynum ? 0 : forum.replynum}
				</ec:column>


			<ec:column property="createtime" title="创建时间"
				style="text-align:center">
				<fmt:formatDate value="${forum.createtime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="tmebernum" title="申请数量"
				style="text-align:center">
             ${fn:length(forum.forumStaffs)}
            </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				<%-- <a
					href="${contextPath }/app/forumStaff!list.do?s_forumid=${forum.forumid}"
					title="审批加入[${forum.forumname}]讨论版">  申请详情
				</a> --%>
				<a
					href="${contextPath }/app/forumStaff!list.do?s_forumid=${forum.forumid}"
					title="审批加入[${forum.forumname}]讨论版">申请通过
				</a>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>