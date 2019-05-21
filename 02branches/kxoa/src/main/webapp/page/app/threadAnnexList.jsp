<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="threadAnnex.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden" name="numPerPage" value="${pageDesc.pageSize}" /> <input type="hidden" name="orderField"
		value="${s_orderField}" />
</form>



<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="/app/threadAnnex.do" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				
					<li><label><c:out value="threadAnnex.threadid" />:</label> <s:textfield name="s_threadid" value="%{#parameters['s_threadid']}" /></li>
				
					<li><label><c:out value="threadAnnex.filecode" />:</label> <s:textfield name="s_filecode" value="%{#parameters['s_filecode']}" /></li>
				
				
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
			<li><a class="add" href="${contextPath }/app/threadAnnex!edit.do" rel="" target='dialog'><span>添加</span></a></li>
			<li><a class="edit" href="${contextPath }/app/threadAnnex!edit.do?threadidfilecode={pk}" warn="请选择一条记录" rel="" target='dialog'><span>编辑</span></a></li>
			<li><a class="delete" href="${contextPath }/app/threadAnnex!delete.do?threadidfilecode={pk}" warn="请选择一条记录" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>

	<div layoutH="116">
		<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc">
			<thead align="center">

				<tr>
					
						<c:set var="tthreadid"><bean:message bundle='appRes' key='threadAnnex.threadid' /></c:set>	
						<th>${tthreadid}</th>
					
						<c:set var="tfilecode"><bean:message bundle='appRes' key='threadAnnex.filecode' /></c:set>	
						<th>${tfilecode}</th>
					
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${objList }" var="threadAnnex">
						<tr target="pk" rel="${threadAnnex.threadid}">
							
								<td>${threadAnnex.threadid}</td>
							
								<td>${threadAnnex.filecode}</td>
							
							
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
		<title><c:out value="threadAnnex.list.title" /></title>
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
			<html:form action="/app/threadAnnex.do" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr height="22">
						<td><c:out value="threadAnnex.threadid" />:</td>
						<td><html:text property="s_threadid" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="threadAnnex.filecode" />:</td>
						<td><html:text property="s_filecode" /> </td>
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

			<ec:table action="threadAnnex.do" items="threadAnnexs" var="threadAnnex"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="threadAnnexs.xls" ></ec:exportXls>
			<ec:exportPdf fileName="threadAnnexs.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				
					<c:set var="tthreadid"><bean:message bundle='appRes' key='threadAnnex.threadid' /></c:set>	
					<ec:column property="threadid" title="${tthreadid}" style="text-align:center" />
				
					<c:set var="tfilecode"><bean:message bundle='appRes' key='threadAnnex.filecode' /></c:set>	
					<ec:column property="filecode" title="${tfilecode}" style="text-align:center" />
				
						
				<c:set var="optlabel"><bean:message key="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><bean:message key="label.delete.confirm"/></c:set>
					<a href='threadAnnex.do?threadid=${threadAnnex.threadid}&filecode=${threadAnnex.filecode}&method=view'><bean:message key="opt.btn.view" /></a>
					<a href='threadAnnex.do?threadid=${threadAnnex.threadid}&filecode=${threadAnnex.filecode}&method=edit'><bean:message key="opt.btn.edit" /></a>
					<a href='threadAnnex.do?threadid=${threadAnnex.threadid}&filecode=${threadAnnex.filecode}&method=delete' 
							onclick='return confirm("${deletecofirm}threadAnnex?");'><bean:message key="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
 --%>