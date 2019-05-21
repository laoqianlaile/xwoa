<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>用户机构权限--<c:out
				value="${userinfo.username}" />
		</title>


	</head>

<body>
<fieldset style="padding:10px;">
	<legend>部门权限管理</legend>
	<table cellpadding="0" cellspacing="0" width="100%" >
		<tr>
			<td class="TTITLE" width="80">用户工号:</td>
			<td align="left" width="110">${userinfo.usercode}</td>
			<td class="TTITLE" width="80">用户名:</td>
			<td align="left" width="110">${userinfo.username}</td>
			<td class="TTITLE" width="80">登录名:</td>
			<td align="left" width="110">${userinfo.loginname}</td>
			<td width="200"><div style="width:200px;"><a href="deptManager!bulitUserRole.do?userrole.usercode=${userinfo.usercode}" class="btnA"><span class="btn">添加用户机构角色</span></a></div></td>
			<td>			 
			    <%-- <input type="button" value="添加用户机构角色" class="btn" onclick="location.href='deptManager!bulitUserRole.do?userrole.usercode=${userinfo.usercode}'">	 --%>			
				<input type="button" value="返回" class="btn" onclick="location.href='deptManager!listuser.do?unitcode=thisunit&ec_p=${param.ec_p}&ec_crd=${param.ec_crd}'">				
			</td>
		</tr>
		<tr>
						
		</tr>
	</table>
 </fieldset>	 
<%
    java.util.Date myDate = new java.util.Date();
    request.setAttribute("myDate",myDate);             
%>
	
      <ec:table items="userroles" var="role"  action="deptManager!viewUserRole.do?usercode=${userinfo.usercode}"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="rolecode" title="部门角色代码" style="text-align:center" />
				<ec:column property="rolename" title="部门角色名" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('rolecode',role.rolecode)}" />
				</ec:column>
				<ec:column property="obtaindate" title="获取时间" style="text-align:center" cell="date" format="yyyy-MM-dd"/>
				<ec:column property="secededate" title="失效时间" style="text-align:center" cell="date" format="yyyy-MM-dd"/>
				<ec:column property="changedesc" title="授权说明" sortable="false" style="text-align:center" />
				<ec:column property="roleopt" title="操作" sortable="false"	 >
					<c:if test="${role.obtaindate lt myDate and ( role.secededate == null or role.secededate gt myDate)}">
					<a href='deptManager!editUserRole.do?userrole.rolecode=${role.rolecode}&userrole.usercode=${userinfo.usercode}'>
						编辑
					</a>
					</c:if>
					<c:if test="${role.secededate == null or role.secededate lt myDate}">
						<a href='deptManager!deleteUserRole.do?userrole.rolecode=${role.rolecode}&userrole.usercode=${userinfo.usercode}&userrole.obtaindate=${role.obtaindate}'
							onclick='return confirm("是否删除用户部门角色：${role.rolecode}?");'>
							删除
						</a>
					</c:if>
					
					<c:if test="${role.obtaindate lt myDate and ( role.secededate == null or role.secededate gt myDate)}">
						<a href='deptManager!deleteUserRole.do?userrole.rolecode=${role.rolecode}&userrole.usercode=${userinfo.usercode}&userrole.obtaindate=${role.obtaindate}'
							onclick='return confirm("是否回收用户部门角色：${role.rolecode}?");'>
							回收
						</a>
					</c:if>
					
				</ec:column>
			
			</ec:row>
		</ec:table> 
  		
</body>
</html>
