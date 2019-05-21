<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post" id="pagerForm">
		<div class="searchBar">
			<ul class="searchContent">
				
				<li><label><c:out value="forum.forumid" />:</label> <c:out value="${forum.forumid}" /></li>  
				<li><label><c:out value="forum.boardcode" />:</label> <c:out value="${forum.boardcode}" /></li> 
				<li><label><c:out value="forum.forumname" />:</label> <c:out value="${forum.forumname}" /></li> 
				<li><label><c:out value="forum.forumpic" />:</label> <c:out value="${forum.forumpic}" /></li> 
				<li><label><c:out value="forum.announcement" />:</label> <c:out value="${forum.announcement}" /></li> 
				<li><label><c:out value="forum.joinright" />:</label> <c:out value="${forum.joinright}" /></li> 
				<li><label><c:out value="forum.viewright" />:</label> <c:out value="${forum.viewright}" /></li> 
				<li><label><c:out value="forum.postright" />:</label> <c:out value="${forum.postright}" /></li> 
				<li><label><c:out value="forum.replyright" />:</label> <c:out value="${forum.replyright}" /></li> 
				<li><label><c:out value="forum.isforumer" />:</label> <c:out value="${forum.isforumer}" /></li> 
				<li><label><c:out value="forum.createtime" />:</label> <c:out value="${forum.createtime}" /></li> 
				<li><label><c:out value="forum.mebernum" />:</label> <c:out value="${forum.mebernum}" /></li> 
				<li><label><c:out value="forum.threadnum" />:</label> <c:out value="${forum.threadnum}" /></li> 
				<li><label><c:out value="forum.replynum" />:</label> <c:out value="${forum.replynum}" /></li> 
				<li><label><c:out value="forum.forumstate" />:</label> <c:out value="${forum.forumstate}" /></li> 
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

	
	<div layoutH="116">
		<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc">
			<thead align="center">

				<tr>
					 
						
							<th><c:out value="thread.threadid" /></th> 
						 
					 
					 
						 
					 
						
							<th><c:out value="thread.titol" /></th> 
						 
					 
						
							<th><c:out value="thread.content" /></th> 
						 
					 
						
							<th><c:out value="thread.wirterid" /></th> 
						 
					 
						
							<th><c:out value="thread.wirter" /></th> 
						 
					 
						
							<th><c:out value="thread.posttime" /></th> 
						 
					 
						
							<th><c:out value="thread.viewnum" /></th> 
						 
					 
						
							<th><c:out value="thread.replnum" /></th> 
						 
					 
						
							<th><c:out value="thread.annexnum" /></th>
						 
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="thread" items="${forum.threads}">
					<tr target="sid_user" rel="${user.usercode}">
						 
							
								<td><c:out value="${thread.threadid}" /></td> 
							 
						 
						 
							 
						 
							
								<td><c:out value="${thread.titol}" /></td> 
							 
						 
							
								<td><c:out value="${thread.content}" /></td> 
							 
						 
							
								<td><c:out value="${thread.wirterid}" /></td> 
							 
						 
							
								<td><c:out value="${thread.wirter}" /></td> 
							 
						 
							
								<td><c:out value="${thread.posttime}" /></td> 
							 
						 
							
								<td><c:out value="${thread.viewnum}" /></td> 
							 
						 
							
								<td><c:out value="${thread.replnum}" /></td> 
							 
						 
							
								<td><c:out value="${thread.annexnum}" /></td>
							 
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div layoutH="116">
		<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc">
			<thead align="center">

				<tr>
					 
						 
					 
						
							<th><c:out value="forumStaff.usercode" /></th> 
						 
					 
					 
						
							<th><c:out value="forumStaff.userrole" /></th> 
						 
					 
						
							<th><c:out value="forumStaff.jointime" /></th> 
						 
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="forumStaff" items="${forum.forumStaffs}">
					<tr target="sid_user" rel="${user.usercode}">
						 
							 
						 
							
								<td><c:out value="${forumStaff.usercode}" /></td> 
							 
						 
						 
							
								<td><c:out value="${forumStaff.userrole}" /></td> 
							 
						 
							
								<td><c:out value="${forumStaff.jointime}" /></td> 
							 
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>



<%-- 
<html>
<head>
<title><c:out value="forum.view.title" /></title>
<link href="<c:out value='${STYLE_PATH}'/>/css/am.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/extremecomponents.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css" rel="stylesheet">

</head>

<body>
	<p class="ctitle">
		<c:out value="forum.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<html:button styleClass="btn" onclick="window.history.back()" property="none">
		<bean:message key="opt.btn.back" />
	</html:button>
	<p>
	<table width="200" border="0" cellpadding="1" cellspacing="1">
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.forumid" /></td>
			<td align="left"><c:out value="${forum.forumid}" /></td>
		</tr>
		 
		<tr>
			<td class="TDTITLE"><c:out value="forum.boardcode" /></td>
			<td align="left"><c:out value="${forum.boardcode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.forumname" /></td>
			<td align="left"><c:out value="${forum.forumname}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.forumpic" /></td>
			<td align="left"><c:out value="${forum.forumpic}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.announcement" /></td>
			<td align="left"><c:out value="${forum.announcement}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.joinright" /></td>
			<td align="left"><c:out value="${forum.joinright}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.viewright" /></td>
			<td align="left"><c:out value="${forum.viewright}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.postright" /></td>
			<td align="left"><c:out value="${forum.postright}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.replyright" /></td>
			<td align="left"><c:out value="${forum.replyright}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.isforumer" /></td>
			<td align="left"><c:out value="${forum.isforumer}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.createtime" /></td>
			<td align="left"><c:out value="${forum.createtime}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.mebernum" /></td>
			<td align="left"><c:out value="${forum.mebernum}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.threadnum" /></td>
			<td align="left"><c:out value="${forum.threadnum}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.replynum" /></td>
			<td align="left"><c:out value="${forum.replynum}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="forum.forumstate" /></td>
			<td align="left"><c:out value="${forum.forumstate}" /></td>
		</tr>
		
	</table>

	
	<p />
	<div class="eXtremeTable">
		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

			<thead>
				<tr>
					
					<td class="tableHeader"><c:out value="thread.threadid" /></td>   
					<td class="tableHeader"><c:out value="thread.titol" /></td> 
					<td class="tableHeader"><c:out value="thread.content" /></td> 
					<td class="tableHeader"><c:out value="thread.wirterid" /></td> 
					<td class="tableHeader"><c:out value="thread.wirter" /></td> 
					<td class="tableHeader"><c:out value="thread.posttime" /></td> 
					<td class="tableHeader"><c:out value="thread.viewnum" /></td> 
					<td class="tableHeader"><c:out value="thread.replnum" /></td> 
					<td class="tableHeader"><c:out value="thread.annexnum" /></td>
					<td class="tableHeader"><bean:message key="opt.btn.collection" /></td>
				</tr>
			</thead>

			<tbody class="tableBody">
				<c:set value="odd" var="rownum" />

				<c:forEach var="thread" items="${forum.threads}">
					<tr class="${rownum}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
						
						<td><c:out value="${thread.threadid}" /></td>  
						<td><c:out value="${thread.titol}" /></td> 
						<td><c:out value="${thread.content}" /></td> 
						<td><c:out value="${thread.wirterid}" /></td> 
						<td><c:out value="${thread.wirter}" /></td> 
						<td><c:out value="${thread.posttime}" /></td> 
						<td><c:out value="${thread.viewnum}" /></td> 
						<td><c:out value="${thread.replnum}" /></td> 
						<td><c:out value="${thread.annexnum}" /></td>
						<td><c:set var="deletecofirm">
								<bean:message key="label.delete.confirm" />
							</c:set> <a href='thread.do?forumid=${forum.forumid}&threadid=${thread.threadid}&method=edit'><bean:message key="opt.btn.edit" /></a> <a href='thread.do?forumid=${forum.forumid}&threadid=${thread.threadid}&method=delete'
							onclick='return confirm("${deletecofirm}thread?");'><bean:message key="opt.btn.delete" /></a></td>
					</tr>
					<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<p />
	<div class="eXtremeTable">
		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

			<thead>
				<tr>
					 
					<td class="tableHeader"><c:out value="forumStaff.usercode" /></td>  
					<td class="tableHeader"><c:out value="forumStaff.userrole" /></td> 
					<td class="tableHeader"><c:out value="forumStaff.jointime" /></td> 
					<td class="tableHeader"><bean:message key="opt.btn.collection" /></td>
				</tr>
			</thead>

			<tbody class="tableBody">
				<c:set value="odd" var="rownum" />

				<c:forEach var="forumStaff" items="${forum.forumStaffs}">
					<tr class="${rownum}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
						
						<td><c:out value="${forumStaff.usercode}" /></td>  
						<td><c:out value="${forumStaff.userrole}" /></td> 
						<td><c:out value="${forumStaff.jointime}" /></td> 
						<td><c:set var="deletecofirm">
								<bean:message key="label.delete.confirm" />
							</c:set> <a href='forumStaff.do?forumid=${forum.forumid}&forumid=${forumStaff.forumid}&usercode=${forumStaff.usercode}&method=edit'><bean:message key="opt.btn.edit" /></a> <a href='forumStaff.do?forumid=${forum.forumid}&forumid=${forumStaff.forumid}&usercode=${forumStaff.usercode}&method=delete'
							onclick='return confirm("${deletecofirm}forumStaff?");'><bean:message key="opt.btn.delete" /></a></td>
					</tr>
					<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
				</c:forEach>
			</tbody>
		</table>
	</div>
	

</body>
</html> --%>
