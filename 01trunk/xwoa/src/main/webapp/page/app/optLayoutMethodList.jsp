<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<title>
		   首页功能排版方式
		</title>
	</head>
	<body>
		<fieldset class="search" >
			<legend>
			查询条件	
			</legend>
			<s:form action="optLayoutMethod" namespace="/app" method="post">
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">排版方式:</td>
						<td >
						<input type="text" name="s_methodName" />
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
		<ec:table action="app/optLayoutMethod!list.do" items="objList" var="optLayoutMethod"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="methodName" title="排版方式" style="text-align:center" width="15%"/>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="15%">
							<a href="${contextPath}/app/optLayoutMethod!edit.do?id=${optLayoutMethod.id}">编辑</a>
							<a href="javascript:void(0);" onclick="doRemove(${optLayoutMethod.id})">删除</a>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	  function doRemove(id){
		  if(confirm("确定删除该模块吗？")){
			  window.location.href="${ctx}/app/optLayoutMethod!delete.do?id="+id
		  }
	  }
	</script>
</html>
