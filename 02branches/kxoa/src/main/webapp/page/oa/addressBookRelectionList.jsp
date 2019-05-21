<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="addressBookRelection.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden" name="numPerPage" value="${pageDesc.pageSize}" /> <input type="hidden" name="orderField"
		value="${s_orderField}" />
</form>



<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="/oa/addressBookRelection.do" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				
					<li><label><c:out value="addressBookRelection.abno" />:</label> <s:textfield name="s_abno" value="%{#parameters['s_abno']}" /></li>
				
					<li><label><c:out value="addressBookRelection.shareman" />:</label> <s:textfield name="s_shareman" value="%{#parameters['s_shareman']}" /></li>
				
				
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<s:submit method="list"><bean:message key="opt.btn.query" /></s:submit>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<!-- 参数 navTabId 根据实际情况填写 -->
								<button type="button" onclick="javascript:navTabAjaxDone({'statusCode' : 200, 'callbackType' : 'closeCurrent', 'navTabId' : ''});">返回</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</s:form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${contextPath }/oa/addressBookRelection!edit.do" rel="" target='dialog'><span>添加</span></a></li>
			<li><a class="edit" href="${contextPath }/oa/addressBookRelection!edit.do?abnoshareman={pk}" warn="请�?择一条记�? rel="" target='dialog'><span>编辑</span></a></li>
			<li><a class="delete" href="${contextPath }/oa/addressBookRelection!delete.do?abnoshareman={pk}" warn="请�?择一条记�? target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>

	<div layoutH="116">
		<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc">
			<thead>

				<tr>
					
						<c:set var="tabno"><bean:message bundle='oaRes' key='addressBookRelection.abno' /></c:set>	
						<th>${tabno}</th>
					
						<c:set var="tshareman"><bean:message bundle='oaRes' key='addressBookRelection.shareman' /></c:set>	
						<th>${tshareman}</th>
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${objList }" var="addressBookRelection">
						<tr target="pk" rel="${addressBookRelection.abno}">
							
								<td>${addressBookRelection.abno}</td>
							
								<td>${addressBookRelection.shareman}</td>
							
							
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="../common/panelBar.jsp"></jsp:include>

<%-- 
<html>
	<head>
		<title><c:out value="addressBookRelection.list.title" /></title>
		<link href="<c:out value='${STYLE_PATH}'/>/css/am.css" type="text/css"
			rel="stylesheet">
		<link href="<c:out value='${STYLE_PATH}'/>/css/extremecomponents.css"
			type="text/css" rel="stylesheet">
		<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css"
			type="text/css" rel="stylesheet">
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			<html:form action="/oa/addressBookRelection.do" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr height="22">
						<td><c:out value="addressBookRelection.abno" />:</td>
						<td><html:text property="s_abno" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="addressBookRelection.shareman" />:</td>
						<td><html:text property="s_shareman" /> </td>
					</tr>	


					<tr>
						<td>
							<html:submit property="method_list" styleClass="btn" > <bean:message key="opt.btn.query" /></html:submit>
						</td>
						<td>
							<html:submit property="method_edit" styleClass="btn" > <bean:message key="opt.btn.new" /> </html:submit>
						</td>
					</tr>
				</table>
			</html:form>
		</fieldset>

			<ec:table action="addressBookRelection.do" items="addressBookRelections" var="addressBookRelection"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="addressBookRelections.xls" ></ec:exportXls>
			<ec:exportPdf fileName="addressBookRelections.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				
					<c:set var="tabno"><bean:message bundle='oaRes' key='addressBookRelection.abno' /></c:set>	
					<ec:column property="abno" title="${tabno}" style="text-align:center" />
				
					<c:set var="tshareman"><bean:message bundle='oaRes' key='addressBookRelection.shareman' /></c:set>	
					<ec:column property="shareman" title="${tshareman}" style="text-align:center" />
				
						
				<c:set var="optlabel"><bean:message key="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><bean:message key="label.delete.confirm"/></c:set>
					<a href='addressBookRelection.do?abno=${addressBookRelection.abno}&shareman=${addressBookRelection.shareman}&method=view'><bean:message key="opt.btn.view" /></a>
					<a href='addressBookRelection.do?abno=${addressBookRelection.abno}&shareman=${addressBookRelection.shareman}&method=edit'><bean:message key="opt.btn.edit" /></a>
					<a href='addressBookRelection.do?abno=${addressBookRelection.abno}&shareman=${addressBookRelection.shareman}&method=delete' 
							onclick='return confirm("${deletecofirm}addressBookRelection?");'><bean:message key="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
 --%>
