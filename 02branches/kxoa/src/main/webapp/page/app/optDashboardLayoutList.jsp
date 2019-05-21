<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<title>
		   首页功能排版
		</title>
	</head>
	<body>
		<fieldset class="search" >
			<legend>
			查询条件	
			</legend>
			<s:form action="optDashboardLayout" namespace="/app" method="post">
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">排版名称:</td>
						<td >
						<input type="text" name="s_layoutName" />
						</td>
					</tr>
					<tr class="searchButton">
				        <td colspan="5"><s:submit method="list" key="opt.btn.query" cssClass="btn"  />
				        <s:submit method="built"  key="opt.btn.new" cssClass="btn" />
				        </td>
					</tr>
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="app/optDashboardLayout!list.do" items="objList" var="optDashboardLayout"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="layoutName" title="排版名称" style="text-align:center" width="15%"/>
					<ec:column property="layoutType" title="排版类型" style="text-align:center" width="25%">
					   <c:if test="${optDashboardLayout.layoutType=='BUILTIN'}">系统内置</c:if>
					   <c:if test="${optDashboardLayout.layoutType=='ALLOCATED'}">按职务分配</c:if>
					   <c:if test="${optDashboardLayout.layoutType=='PERSONAL'}">自定义</c:if>
					</ec:column>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="15%">
						    <a href="${contextPath}/app/optDashboardLayout!view.do?id=${optDashboardLayout.id}">查看</a>
						    <c:if test="${optDashboardLayout.layoutType=='PERSONAL' || 'sa' == session.SPRING_SECURITY_CONTEXT.authentication.principal.loginname}">
							  <a href="${contextPath}/app/optDashboardLayout!edit.do?id=${optDashboardLayout.id}">编辑</a>
							  <a href="javascript:void(0);" onclick="doRemove(${optDashboardLayout.id})">删除</a>
							</c:if>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	  function doRemove(id){
		  if(confirm("确定删除该模块吗？")){
			  window.location.href="${ctx}/app/optDashboardLayout!delete.do?id="+id
		  }
	  }
	</script>
</html>
