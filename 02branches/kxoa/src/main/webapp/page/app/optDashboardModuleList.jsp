<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<title>
		   首页功能模块
		</title>
	</head>
	<body>
		<fieldset class="search" >
			<legend>
			查询条件	
			</legend>
			<s:form action="optDashboardModule" namespace="/app" method="post">
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">模块名称:</td>
						<td >
						<input type="text" name="s_moduleName" value="${moduleName}" />
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
		<ec:table action="app/optDashboardModule!list.do" items="objList" var="optDashboardModule"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="moduleCode" title="模块编码" style="text-align:center"  width="15%"/>
					<ec:column property="moduleName" title="模块名称" style="text-align:center" width="15%"/>
					<ec:column property="displayUrl" title="数据展示地址" style="text-align:center" width="25%"/>
			        <ec:column property="linkUrl" title="链接更多地址" style="text-align:center" width="25%"/>
				    <ec:column property="isUsed" title="是否使用" style="text-align:center" width="5%">
				       <c:if test="${optDashboardModule.isUsed=='T'}">是</c:if>
				       <c:if test="${optDashboardModule.isUsed=='F'}">否</c:if>
				    </ec:column>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="15%">
						    <a href="${contextPath}/app/optDashboardModule!view.do?id=${optDashboardModule.id}">查看</a>
							<a href="${contextPath}/app/optDashboardModule!edit.do?id=${optDashboardModule.id}">编辑</a>
							<a href="javascript:void(0);" onclick="doRemove(${optDashboardModule.id})">删除</a>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	  function doRemove(id){
		  if(confirm("确定删除该模块吗？")){
			  window.location.href="${ctx}/app/optDashboardModule!delete.do?id="+id
		  }
	  }
	</script>
</html>
