<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post" id="pagerForm">
		<div class="searchBar">
			<ul class="searchContent">
				
				<li><label><c:out value="reply.replyid" />:</label> <c:out value="${reply.replyid}" /></li>  
				<li><label><c:out value="reply.threadid" />:</label> <c:out value="${reply.threadid}" /></li> 
				<li><label><c:out value="reply.reply" />:</label> <c:out value="${reply.reply}" /></li> 
				<li><label><c:out value="reply.replytime" />:</label> <c:out value="${reply.replytime}" /></li> 
				<li><label><c:out value="reply.userid" />:</label> <c:out value="${reply.userid}" /></li> 
				<li><label><c:out value="reply.username" />:</label> <c:out value="${reply.username}" /></li> 
				<li><label><c:out value="reply.annexnum" />:</label> <c:out value="${reply.annexnum}" /></li>
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
					 
						 
					 
						
							<th><c:out value="replyAnnex.filecode" /></th> 
						 
					 
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="replyAnnex" items="${reply.replyAnnexs}">
					<tr target="sid_user" rel="${user.usercode}">
						 
							 
						 
							
								<td><c:out value="${replyAnnex.filecode}" /></td> 
							 
						 
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>



<%-- 
<html>
<head>
<title><c:out value="reply.view.title" /></title>
<link href="<c:out value='${STYLE_PATH}'/>/css/am.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/extremecomponents.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css" rel="stylesheet">

</head>

<body>
	<p class="ctitle">
		<c:out value="reply.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<html:button styleClass="btn" onclick="window.history.back()" property="none">
		<bean:message key="opt.btn.back" />
	</html:button>
	<p>
	<table width="200" border="0" cellpadding="1" cellspacing="1">
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.replyid" /></td>
			<td align="left"><c:out value="${reply.replyid}" /></td>
		</tr>
		 
		<tr>
			<td class="TDTITLE"><c:out value="reply.threadid" /></td>
			<td align="left"><c:out value="${reply.threadid}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.reply" /></td>
			<td align="left"><c:out value="${reply.reply}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.replytime" /></td>
			<td align="left"><c:out value="${reply.replytime}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.userid" /></td>
			<td align="left"><c:out value="${reply.userid}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.username" /></td>
			<td align="left"><c:out value="${reply.username}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="reply.annexnum" /></td>
			<td align="left"><c:out value="${reply.annexnum}" /></td>
		</tr>
		
	</table>

	
	<p />
	<div class="eXtremeTable">
		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

			<thead>
				<tr>
					 
					<td class="tableHeader"><c:out value="replyAnnex.filecode" /></td>  
					<td class="tableHeader"><bean:message key="opt.btn.collection" /></td>
				</tr>
			</thead>

			<tbody class="tableBody">
				<c:set value="odd" var="rownum" />

				<c:forEach var="replyAnnex" items="${reply.replyAnnexs}">
					<tr class="${rownum}" onmouseover="this.className='highlight'" onmouseout="this.className='${rownum}'">
						
						<td><c:out value="${replyAnnex.filecode}" /></td>  
						<td><c:set var="deletecofirm">
								<bean:message key="label.delete.confirm" />
							</c:set> <a href='replyAnnex.do?replyid=${reply.replyid}&replyid=${replyAnnex.replyid}&filecode=${replyAnnex.filecode}&method=edit'><bean:message key="opt.btn.edit" /></a> <a href='replyAnnex.do?replyid=${reply.replyid}&replyid=${replyAnnex.replyid}&filecode=${replyAnnex.filecode}&method=delete'
							onclick='return confirm("${deletecofirm}replyAnnex?");'><bean:message key="opt.btn.delete" /></a></td>
					</tr>
					<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
				</c:forEach>
			</tbody>
		</table>
	</div>
	

</body>
</html> --%>
