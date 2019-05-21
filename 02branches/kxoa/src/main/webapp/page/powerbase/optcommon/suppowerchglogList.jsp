<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="suppowerchglog.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden" name="numPerPage" value="${pageDesc.pageSize}" /> <input type="hidden" name="orderField"
		value="${s_orderField}" />
</form>



<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="/powerbase/optcommon/suppowerchglog.do" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				
					<li><label><c:out value="suppowerchglog.changeId" />:</label> <s:textfield name="s_changeId" value="%{#parameters['s_changeId']}" /></li>
				
				
					<li><label><c:out value="suppowerchglog.itemId" />:</label> <s:textfield name="s_itemId" value="%{#parameters['s_itemId']}" /></li>
				
					<li><label><c:out value="suppowerchglog.version" />:</label> <s:textfield name="s_version" value="%{#parameters['s_version']}" /></li>
				
					<li><label><c:out value="suppowerchglog.chgReason" />:</label> <s:textfield name="s_chgReason" value="%{#parameters['s_chgReason']}" /></li>
				
					<li><label><c:out value="suppowerchglog.chgContent" />:</label> <s:textfield name="s_chgContent" value="%{#parameters['s_chgContent']}" /></li>
				
					<li><label><c:out value="suppowerchglog.requestTime" />:</label> <s:textfield name="s_requestTime" value="%{#parameters['s_requestTime']}" /></li>
				
					<li><label><c:out value="suppowerchglog.requester" />:</label> <s:textfield name="s_requester" value="%{#parameters['s_requester']}" /></li>
				
					<li><label><c:out value="suppowerchglog.chgResult" />:</label> <s:textfield name="s_chgResult" value="%{#parameters['s_chgResult']}" /></li>
				
					<li><label><c:out value="suppowerchglog.auditTime" />:</label> <s:textfield name="s_auditTime" value="%{#parameters['s_auditTime']}" /></li>
				
					<li><label><c:out value="suppowerchglog.auditor" />:</label> <s:textfield name="s_auditor" value="%{#parameters['s_auditor']}" /></li>
				
					<li><label><c:out value="suppowerchglog.auditContent" />:</label> <s:textfield name="s_auditContent" value="%{#parameters['s_auditContent']}" /></li>
				
					<li><label><c:out value="suppowerchglog.chgType" />:</label> <s:textfield name="s_chgType" value="%{#parameters['s_chgType']}" /></li>
				
					<li><label><c:out value="suppowerchglog.replyPeople" />:</label> <s:textfield name="s_replyPeople" value="%{#parameters['s_replyPeople']}" /></li>
				
					<li><label><c:out value="suppowerchglog.replyTime" />:</label> <s:textfield name="s_replyTime" value="%{#parameters['s_replyTime']}" /></li>
				
					<li><label><c:out value="suppowerchglog.replyContent" />:</label> <s:textfield name="s_replyContent" value="%{#parameters['s_replyContent']}" /></li>
				
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
			<li><a class="add" href="${contextPath }/powerbase/optcommon/suppowerchglog!edit.do" rel="" target='dialog'><span>添加</span></a></li>
			<li><a class="edit" href="${contextPath }/powerbase/optcommon/suppowerchglog!edit.do?changeId={pk}" warn="请选择一条记录" rel="" target='dialog'><span>编辑</span></a></li>
			<li><a class="delete" href="${contextPath }/powerbase/optcommon/suppowerchglog!delete.do?changeId={pk}" warn="请选择一条记录" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>

	<div layoutH="116">
		<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc">
			<thead align="center">

				<tr>
					
						<c:set var="tchangeId"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.changeId' /></c:set>	
						<th>${tchangeId}</th>
					
					
						<c:set var="titemId"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.itemId' /></c:set>	
						<th>${titemId}</th>
					
						<c:set var="tversion"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.version' /></c:set>	
						<th>${tversion}</th>
					
						<c:set var="tchgReason"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgReason' /></c:set>	
						<th>${tchgReason}</th>
					
						<c:set var="tchgContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgContent' /></c:set>	
						<th>${tchgContent}</th>
					
						<c:set var="trequestTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.requestTime' /></c:set>	
						<th>${trequestTime}</th>
					
						<c:set var="trequester"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.requester' /></c:set>	
						<th>${trequester}</th>
					
						<c:set var="tchgResult"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgResult' /></c:set>	
						<th>${tchgResult}</th>
					
						<c:set var="tauditTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditTime' /></c:set>	
						<th>${tauditTime}</th>
					
						<c:set var="tauditor"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditor' /></c:set>	
						<th>${tauditor}</th>
					
						<c:set var="tauditContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditContent' /></c:set>	
						<th>${tauditContent}</th>
					
						<c:set var="tchgType"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgType' /></c:set>	
						<th>${tchgType}</th>
					
						<c:set var="treplyPeople"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyPeople' /></c:set>	
						<th>${treplyPeople}</th>
					
						<c:set var="treplyTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyTime' /></c:set>	
						<th>${treplyTime}</th>
					
						<c:set var="treplyContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyContent' /></c:set>	
						<th>${treplyContent}</th>
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${objList }" var="suppowerchglog">
						<tr target="pk" rel="${suppowerchglog.changeId}">
							
								<td>${suppowerchglog.changeId}</td>
							
							
								<td>${suppowerchglog.itemId}</td>
							
								<td>${suppowerchglog.version}</td>
							
								<td>${suppowerchglog.chgReason}</td>
							
								<td>${suppowerchglog.chgContent}</td>
							
								<td>${suppowerchglog.requestTime}</td>
							
								<td>${suppowerchglog.requester}</td>
							
								<td>${suppowerchglog.chgResult}</td>
							
								<td>${suppowerchglog.auditTime}</td>
							
								<td>${suppowerchglog.auditor}</td>
							
								<td>${suppowerchglog.auditContent}</td>
							
								<td>${suppowerchglog.chgType}</td>
							
								<td>${suppowerchglog.replyPeople}</td>
							
								<td>${suppowerchglog.replyTime}</td>
							
								<td>${suppowerchglog.replyContent}</td>
							
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/page/common/panelBar.jsp"></jsp:include>

<%-- 
<html>
	<head>
		<title><c:out value="suppowerchglog.list.title" /></title>
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
			<html:form action="/powerbase/optcommon/suppowerchglog.do" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr height="22">
						<td><c:out value="suppowerchglog.changeId" />:</td>
						<td><html:text property="s_changeId" /> </td>
					</tr>	


					<tr height="22">
						<td><c:out value="suppowerchglog.itemId" />:</td>
						<td><html:text property="s_itemId" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.version" />:</td>
						<td><html:text property="s_version" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.chgReason" />:</td>
						<td><html:text property="s_chgReason" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.chgContent" />:</td>
						<td><html:text property="s_chgContent" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.requestTime" />:</td>
						<td><html:text property="s_requestTime" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.requester" />:</td>
						<td><html:text property="s_requester" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.chgResult" />:</td>
						<td><html:text property="s_chgResult" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.auditTime" />:</td>
						<td><html:text property="s_auditTime" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.auditor" />:</td>
						<td><html:text property="s_auditor" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.auditContent" />:</td>
						<td><html:text property="s_auditContent" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.chgType" />:</td>
						<td><html:text property="s_chgType" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.replyPeople" />:</td>
						<td><html:text property="s_replyPeople" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.replyTime" />:</td>
						<td><html:text property="s_replyTime" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="suppowerchglog.replyContent" />:</td>
						<td><html:text property="s_replyContent" /> </td>
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

			<ec:table action="suppowerchglog.do" items="suppowerchglogs" var="suppowerchglog"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="suppowerchglogs.xls" ></ec:exportXls>
			<ec:exportPdf fileName="suppowerchglogs.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				
					<c:set var="tchangeId"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.changeId' /></c:set>	
					<ec:column property="changeId" title="${tchangeId}" style="text-align:center" />
				
				
					<c:set var="titemId"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.itemId' /></c:set>	
					<ec:column property="itemId" title="${titemId}" style="text-align:center" />
				
					<c:set var="tversion"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.version' /></c:set>	
					<ec:column property="version" title="${tversion}" style="text-align:center" />
				
					<c:set var="tchgReason"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgReason' /></c:set>	
					<ec:column property="chgReason" title="${tchgReason}" style="text-align:center" />
				
					<c:set var="tchgContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgContent' /></c:set>	
					<ec:column property="chgContent" title="${tchgContent}" style="text-align:center" />
				
					<c:set var="trequestTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.requestTime' /></c:set>	
					<ec:column property="requestTime" title="${trequestTime}" style="text-align:center" />
				
					<c:set var="trequester"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.requester' /></c:set>	
					<ec:column property="requester" title="${trequester}" style="text-align:center" />
				
					<c:set var="tchgResult"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgResult' /></c:set>	
					<ec:column property="chgResult" title="${tchgResult}" style="text-align:center" />
				
					<c:set var="tauditTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditTime' /></c:set>	
					<ec:column property="auditTime" title="${tauditTime}" style="text-align:center" />
				
					<c:set var="tauditor"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditor' /></c:set>	
					<ec:column property="auditor" title="${tauditor}" style="text-align:center" />
				
					<c:set var="tauditContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.auditContent' /></c:set>	
					<ec:column property="auditContent" title="${tauditContent}" style="text-align:center" />
				
					<c:set var="tchgType"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.chgType' /></c:set>	
					<ec:column property="chgType" title="${tchgType}" style="text-align:center" />
				
					<c:set var="treplyPeople"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyPeople' /></c:set>	
					<ec:column property="replyPeople" title="${treplyPeople}" style="text-align:center" />
				
					<c:set var="treplyTime"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyTime' /></c:set>	
					<ec:column property="replyTime" title="${treplyTime}" style="text-align:center" />
				
					<c:set var="treplyContent"><bean:message bundle='powerbase/optcommonRes' key='suppowerchglog.replyContent' /></c:set>	
					<ec:column property="replyContent" title="${treplyContent}" style="text-align:center" />
						
				<c:set var="optlabel"><bean:message key="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><bean:message key="label.delete.confirm"/></c:set>
					<a href='suppowerchglog.do?changeId=${suppowerchglog.changeId}&method=view'><bean:message key="opt.btn.view" /></a>
					<a href='suppowerchglog.do?changeId=${suppowerchglog.changeId}&method=edit'><bean:message key="opt.btn.edit" /></a>
					<a href='suppowerchglog.do?changeId=${suppowerchglog.changeId}&method=delete' 
							onclick='return confirm("${deletecofirm}suppowerchglog?");'><bean:message key="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
 --%>