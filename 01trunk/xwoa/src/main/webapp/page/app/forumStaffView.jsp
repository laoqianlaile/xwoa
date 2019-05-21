<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post" id="pagerForm">
		<div class="searchBar">
			<ul class="searchContent">
				
				<li><label><c:out value="forumStaff.forumid" />:</label> <c:out value="${forumStaff.forumid}" /></li> 
				<li><label><c:out value="forumStaff.usercode" />:</label> <c:out value="${forumStaff.usercode}" /></li>  
				<li><label><c:out value="forumStaff.userrole" />:</label> <c:out value="${forumStaff.userrole}" /></li> 
				<li><label><c:out value="forumStaff.jointime" />:</label> <c:out value="${forumStaff.jointime}" /></li> 
			</ul>

			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<!-- 参数 navTabId 根据实际情况填写 -->
								<button type="button" onclick="javascript:navTabAjaxDone({'statusCode' : 200, 'callbackType' : 'closeCurrent', 'navTabId' : ''});">返回</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
	</div>

	
</div>



<%-- 
<html>
<head>
<title><c:out value="forumStaff.view.title" /></title>
<link href="<c:out value='${STYLE_PATH}'/>/css/am.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css" rel="stylesheet">

</head>

<body>
	<p class="ctitle">
		<c:out value="forumStaff.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<html:button styleClass="btn" onclick="window.history.back()" property="none">
		<bean:message key="opt.btn.back" />
	</html:button>
	<p>
	<table width="200" border="0" cellpadding="1" cellspacing="1">
		
		<tr>
			<td class="TDTITLE"><c:out value="forumStaff.forumid" /></td>
			<td align="left"><c:out value="${forumStaff.forumid}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forumStaff.usercode" /></td>
			<td align="left"><c:out value="${forumStaff.usercode}" /></td>
		</tr>
		 
		<tr>
			<td class="TDTITLE"><c:out value="forumStaff.userrole" /></td>
			<td align="left"><c:out value="${forumStaff.userrole}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forumStaff.jointime" /></td>
			<td align="left"><c:out value="${forumStaff.jointime}" /></td>
		</tr>
		
	</table>

	

</body>
</html> --%>
