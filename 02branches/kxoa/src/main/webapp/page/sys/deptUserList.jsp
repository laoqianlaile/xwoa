<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
 
<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
	<%@ include file="/page/common/css.jsp"%>
		<title>人员列表</title>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		
		<fieldset>
			<legend>
				&nbsp;&nbsp;查询条件&nbsp;&nbsp;
			</legend>
			<s:form action="deptManager" namespace="/sys">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td width="250">用户名:
							<s:textfield name="s_USERNAME" />
						</td>
						<td width="250">
							登录名:
							<s:textfield name="s_LOGINNAME" />
						</td>

						<td>
							<s:submit method="listuser" cssClass="btn" value="查询" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

			<ec:table action="deptManager!listuser.do" items="userList" var="sysUser"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />
			
				<ec:column property="username" title="用户名" style="text-align:center" />

				<ec:column property="loginname" title="登录名" style="text-align:center" />
				<ec:column property="isvalid" title="状态" sortable="false" style="text-align:center">
					${USE_STATE[sysUser.isvalid]}
				</ec:column>
				<ec:column property="userdesc" title="用户描述" style="text-align:center"></ec:column>
				<ec:column property="userOpt" title="操作" sortable="false"
					style="text-align:center">
					<a href='deptManager!viewUserRole.do?userinfo.usercode=${sysUser.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'>部门权限管理</a>
		
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
