<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="subprocessProject.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden" name="numPerPage" value="${pageDesc.pageSize}" /> <input type="hidden" name="orderField"
		value="${s_orderField}" />
</form>



<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="/dispatchdoc/subprocessProject.do" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				
					<li><label><c:out value="subprocessProject.djId" />:</label> <s:textfield name="s_djId" value="%{#parameters['s_djId']}" /></li>
				
				
					<li><label><c:out value="subprocessProject.headunitcode" />:</label> <s:textfield name="s_headunitcode" value="%{#parameters['s_headunitcode']}" /></li>
				
					<li><label><c:out value="subprocessProject.accpetDate" />:</label> <s:textfield name="s_accpetDate" value="%{#parameters['s_accpetDate']}" /></li>
				
					<li><label><c:out value="subprocessProject.projectUnitName" />:</label> <s:textfield name="s_projectUnitName" value="%{#parameters['s_projectUnitName']}" /></li>
				
					<li><label><c:out value="subprocessProject.projectName" />:</label> <s:textfield name="s_projectName" value="%{#parameters['s_projectName']}" /></li>
				
					<li><label><c:out value="subprocessProject.projectType" />:</label> <s:textfield name="s_projectType" value="%{#parameters['s_projectType']}" /></li>
				
					<li><label><c:out value="subprocessProject.evaluationReason" />:</label> <s:textfield name="s_evaluationReason" value="%{#parameters['s_evaluationReason']}" /></li>
				
					<li><label><c:out value="subprocessProject.evaluationContent" />:</label> <s:textfield name="s_evaluationContent" value="%{#parameters['s_evaluationContent']}" /></li>
				
					<li><label><c:out value="subprocessProject.opinions" />:</label> <s:textfield name="s_opinions" value="%{#parameters['s_opinions']}" /></li>
				
					<li><label><c:out value="subprocessProject.createUserCode" />:</label> <s:textfield name="s_createUserCode" value="%{#parameters['s_createUserCode']}" /></li>
				
					<li><label><c:out value="subprocessProject.createDate" />:</label> <s:textfield name="s_createDate" value="%{#parameters['s_createDate']}" /></li>
				
					<li><label><c:out value="subprocessProject.syncErrorDesc" />:</label> <s:textfield name="s_syncErrorDesc" value="%{#parameters['s_syncErrorDesc']}" /></li>
				
					<li><label><c:out value="subprocessProject.updateDate" />:</label> <s:textfield name="s_updateDate" value="%{#parameters['s_updateDate']}" /></li>
				
					<li><label><c:out value="subprocessProject.syncDate" />:</label> <s:textfield name="s_syncDate" value="%{#parameters['s_syncDate']}" /></li>
				
					<li><label><c:out value="subprocessProject.syncSign" />:</label> <s:textfield name="s_syncSign" value="%{#parameters['s_syncSign']}" /></li>
				
					<li><label><c:out value="subprocessProject.wfcode" />:</label> <s:textfield name="s_wfcode" value="%{#parameters['s_wfcode']}" /></li>
				
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
			<li><a class="add" href="${contextPath }/dispatchdoc/subprocessProject!edit.do" rel="" target='dialog'><span>添加</span></a></li>
			<li><a class="edit" href="${contextPath }/dispatchdoc/subprocessProject!edit.do?djId={pk}" warn="请选择一条记录" rel="" target='dialog'><span>编辑</span></a></li>
			<li><a class="delete" href="${contextPath }/dispatchdoc/subprocessProject!delete.do?djId={pk}" warn="请选择一条记录" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>

	<div layoutH="116">
		<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc">
			<thead align="center">

				<tr>
					
						<c:set var="tdjId"><bean:message bundle='dispatchdocRes' key='subprocessProject.djId' /></c:set>	
						<th>${tdjId}</th>
					
					
						<c:set var="theadunitcode"><bean:message bundle='dispatchdocRes' key='subprocessProject.headunitcode' /></c:set>	
						<th>${theadunitcode}</th>
					
						<c:set var="taccpetDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.accpetDate' /></c:set>	
						<th>${taccpetDate}</th>
					
						<c:set var="tprojectUnitName"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectUnitName' /></c:set>	
						<th>${tprojectUnitName}</th>
					
						<c:set var="tprojectName"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectName' /></c:set>	
						<th>${tprojectName}</th>
					
						<c:set var="tprojectType"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectType' /></c:set>	
						<th>${tprojectType}</th>
					
						<c:set var="tevaluationReason"><bean:message bundle='dispatchdocRes' key='subprocessProject.evaluationReason' /></c:set>	
						<th>${tevaluationReason}</th>
					
						<c:set var="tevaluationContent"><bean:message bundle='dispatchdocRes' key='subprocessProject.evaluationContent' /></c:set>	
						<th>${tevaluationContent}</th>
					
						<c:set var="topinions"><bean:message bundle='dispatchdocRes' key='subprocessProject.opinions' /></c:set>	
						<th>${topinions}</th>
					
						<c:set var="tcreateUserCode"><bean:message bundle='dispatchdocRes' key='subprocessProject.createUserCode' /></c:set>	
						<th>${tcreateUserCode}</th>
					
						<c:set var="tcreateDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.createDate' /></c:set>	
						<th>${tcreateDate}</th>
					
						<c:set var="tsyncErrorDesc"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncErrorDesc' /></c:set>	
						<th>${tsyncErrorDesc}</th>
					
						<c:set var="tupdateDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.updateDate' /></c:set>	
						<th>${tupdateDate}</th>
					
						<c:set var="tsyncDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncDate' /></c:set>	
						<th>${tsyncDate}</th>
					
						<c:set var="tsyncSign"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncSign' /></c:set>	
						<th>${tsyncSign}</th>
					
						<c:set var="twfcode"><bean:message bundle='dispatchdocRes' key='subprocessProject.wfcode' /></c:set>	
						<th>${twfcode}</th>
					
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${objList }" var="subprocessProject">
						<tr target="pk" rel="${subprocessProject.djId}">
							
								<td>${subprocessProject.djId}</td>
							
							
								<td>${subprocessProject.headunitcode}</td>
							
								<td>${subprocessProject.accpetDate}</td>
							
								<td>${subprocessProject.projectUnitName}</td>
							
								<td>${subprocessProject.projectName}</td>
							
								<td>${subprocessProject.projectType}</td>
							
								<td>${subprocessProject.evaluationReason}</td>
							
								<td>${subprocessProject.evaluationContent}</td>
							
								<td>${subprocessProject.opinions}</td>
							
								<td>${subprocessProject.createUserCode}</td>
							
								<td>${subprocessProject.createDate}</td>
							
								<td>${subprocessProject.syncErrorDesc}</td>
							
								<td>${subprocessProject.updateDate}</td>
							
								<td>${subprocessProject.syncDate}</td>
							
								<td>${subprocessProject.syncSign}</td>
							
								<td>${subprocessProject.wfcode}</td>
							
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
		<title><c:out value="subprocessProject.list.title" /></title>
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
			<html:form action="/dispatchdoc/subprocessProject.do" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr height="22">
						<td><c:out value="subprocessProject.djId" />:</td>
						<td><html:text property="s_djId" /> </td>
					</tr>	


					<tr height="22">
						<td><c:out value="subprocessProject.headunitcode" />:</td>
						<td><html:text property="s_headunitcode" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.accpetDate" />:</td>
						<td><html:text property="s_accpetDate" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.projectUnitName" />:</td>
						<td><html:text property="s_projectUnitName" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.projectName" />:</td>
						<td><html:text property="s_projectName" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.projectType" />:</td>
						<td><html:text property="s_projectType" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.evaluationReason" />:</td>
						<td><html:text property="s_evaluationReason" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.evaluationContent" />:</td>
						<td><html:text property="s_evaluationContent" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.opinions" />:</td>
						<td><html:text property="s_opinions" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.createUserCode" />:</td>
						<td><html:text property="s_createUserCode" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.createDate" />:</td>
						<td><html:text property="s_createDate" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.syncErrorDesc" />:</td>
						<td><html:text property="s_syncErrorDesc" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.updateDate" />:</td>
						<td><html:text property="s_updateDate" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.syncDate" />:</td>
						<td><html:text property="s_syncDate" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.syncSign" />:</td>
						<td><html:text property="s_syncSign" /> </td>
					</tr>	

					<tr height="22">
						<td><c:out value="subprocessProject.wfcode" />:</td>
						<td><html:text property="s_wfcode" /> </td>
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

			<ec:table action="subprocessProject.do" items="subprocessProjects" var="subprocessProject"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="subprocessProjects.xls" ></ec:exportXls>
			<ec:exportPdf fileName="subprocessProjects.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				
					<c:set var="tdjId"><bean:message bundle='dispatchdocRes' key='subprocessProject.djId' /></c:set>	
					<ec:column property="djId" title="${tdjId}" style="text-align:center" />
				
				
					<c:set var="theadunitcode"><bean:message bundle='dispatchdocRes' key='subprocessProject.headunitcode' /></c:set>	
					<ec:column property="headunitcode" title="${theadunitcode}" style="text-align:center" />
				
					<c:set var="taccpetDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.accpetDate' /></c:set>	
					<ec:column property="accpetDate" title="${taccpetDate}" style="text-align:center" />
				
					<c:set var="tprojectUnitName"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectUnitName' /></c:set>	
					<ec:column property="projectUnitName" title="${tprojectUnitName}" style="text-align:center" />
				
					<c:set var="tprojectName"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectName' /></c:set>	
					<ec:column property="projectName" title="${tprojectName}" style="text-align:center" />
				
					<c:set var="tprojectType"><bean:message bundle='dispatchdocRes' key='subprocessProject.projectType' /></c:set>	
					<ec:column property="projectType" title="${tprojectType}" style="text-align:center" />
				
					<c:set var="tevaluationReason"><bean:message bundle='dispatchdocRes' key='subprocessProject.evaluationReason' /></c:set>	
					<ec:column property="evaluationReason" title="${tevaluationReason}" style="text-align:center" />
				
					<c:set var="tevaluationContent"><bean:message bundle='dispatchdocRes' key='subprocessProject.evaluationContent' /></c:set>	
					<ec:column property="evaluationContent" title="${tevaluationContent}" style="text-align:center" />
				
					<c:set var="topinions"><bean:message bundle='dispatchdocRes' key='subprocessProject.opinions' /></c:set>	
					<ec:column property="opinions" title="${topinions}" style="text-align:center" />
				
					<c:set var="tcreateUserCode"><bean:message bundle='dispatchdocRes' key='subprocessProject.createUserCode' /></c:set>	
					<ec:column property="createUserCode" title="${tcreateUserCode}" style="text-align:center" />
				
					<c:set var="tcreateDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.createDate' /></c:set>	
					<ec:column property="createDate" title="${tcreateDate}" style="text-align:center" />
				
					<c:set var="tsyncErrorDesc"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncErrorDesc' /></c:set>	
					<ec:column property="syncErrorDesc" title="${tsyncErrorDesc}" style="text-align:center" />
				
					<c:set var="tupdateDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.updateDate' /></c:set>	
					<ec:column property="updateDate" title="${tupdateDate}" style="text-align:center" />
				
					<c:set var="tsyncDate"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncDate' /></c:set>	
					<ec:column property="syncDate" title="${tsyncDate}" style="text-align:center" />
				
					<c:set var="tsyncSign"><bean:message bundle='dispatchdocRes' key='subprocessProject.syncSign' /></c:set>	
					<ec:column property="syncSign" title="${tsyncSign}" style="text-align:center" />
				
					<c:set var="twfcode"><bean:message bundle='dispatchdocRes' key='subprocessProject.wfcode' /></c:set>	
					<ec:column property="wfcode" title="${twfcode}" style="text-align:center" />
						
				<c:set var="optlabel"><bean:message key="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><bean:message key="label.delete.confirm"/></c:set>
					<a href='subprocessProject.do?djId=${subprocessProject.djId}&method=view'><bean:message key="opt.btn.view" /></a>
					<a href='subprocessProject.do?djId=${subprocessProject.djId}&method=edit'><bean:message key="opt.btn.edit" /></a>
					<a href='subprocessProject.do?djId=${subprocessProject.djId}&method=delete' 
							onclick='return confirm("${deletecofirm}subprocessProject?");'><bean:message key="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
 --%>