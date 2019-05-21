<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	<!-- 新样式文件 -->
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
</head>
<body>
	<fieldset class="search">
			<legend class="headTitle">
					我的会议			
			</legend>
			<div class="searchDiv">
			<s:form id="oaMeetingApply" action="oaMeetingApply" namespace="/oa" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			 <div class="searchArea">
				<table style="width: auto;">
				<tr>				
					<td class="searchTitleArea" >
					<label class="searchCondTitle">会议名称:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_meetApplyName" value="${s_meetApplyName }"/> 
					</td>
					<td class="searchCondArea"><s:submit method="oaMeetingList" key="opt.btn.query" cssClass="btn" />
					</td>
					</tr>	
			</table>
			</div>	
			</s:form>
			</div>
		</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaMeetingApply!oaMeetingList.do"
		items="meetingList" var="foaMeetingapply"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column  title="序号"  property="rowcount" cell="rowCount" sortable="false" style="text-align:center" width="4%"/>	
			<ec:column property="meetApplyName" title="会议名称"
				style="text-align:center" />

			 <ec:column property="meetApplyAddress" title="会议地址"
				style="text-align:center">
				<c:choose>
					<c:when
						test="${fn:length(foaMeetingapply.meetApplyAddress) gt 10}">
						<c:out
							value="${fn:substring(foaMeetingapply.meetApplyAddress, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${foaMeetingapply.meetApplyAddress}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			 <ec:column property="meettime" title="会议时间" style="text-align:center" sortable="false" >
				<fmt:formatDate value='${ foaMeetingapply.meetApplytime }' pattern='yyyy-MM-dd HH:mm' />
			 </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
				<a class="view" href="${pageContext.request.contextPath}/oa/oaMeetingApply!view.do?mId=${foaMeetingapply.mId}">查看</a>
			</ec:column>
		</ec:row>
	</ec:table>
			
</body>
</html>