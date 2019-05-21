<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
	<title>用户信息--<c:out
				value="${userinfo.username}" />
	</title>

	<script type="text/javascript">
		function viewInDialog(addrbookid)
		{
			window.showModalDialog("<c:url value='/sys/addressBook.do?method=view&addrbookid='/>"+addrbookid, "查看通讯信息");
			return false;
		}
	</script>
</head>
<body>
	<br/>
		  
	<table cellpadding="0" cellspacing="0" >
		<tr>
			<td class="addTd">角色编号：</td>
			<td align="left" width="150"><s:property value="%{rolecode}"/></td>
			<td class="addTd">角色名：</td>
			<td align="left" width="150"><s:property value="%{roleinfo.rolename}"/></td>
			
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="5">
			<a href="roleDef!bulitUserRole.do?rolecode=${rolecode}" class="btnA"><span class="btn">添加角色用户</span></a>
			<input type="button" value="返回" class="btn" onclick="window.history.back();">					
			</td>			
		</tr>
	</table>

    	
	
<%
    java.util.Date myDate = new java.util.Date();
    request.setAttribute("myDate",myDate);             
%>
	
      <ec:table items="userroles" var="user" action="userDef!view.do?usercode=${userinfo.usercode}" sortable="false"  showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="ur" >
			<ec:row>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />
				<ec:column property="username" title="用户名" sortable="false" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('usercode',user.usercode)}" />
				</ec:column>
				<ec:column property="obtaindate" title="配给时间" style="text-align:center"/>
				<ec:column property="secededate" title="失效时间" style="text-align:center" />
				<ec:column property="changedesc" title="授权说明" sortable="false" style="text-align:center" />
				<ec:column property="roleopt" title="操作" sortable="false"	 >
					
					<a href='roleDef!editUserRole.do?usercode=${user.usercode}&rolecode=${user.rolecode}&obtaindate=${user.obtaindate}'>
						编辑
					</a>
					
					<c:if test="${user.obtaindate lt myDate and (user.secededate == null or myDate gt user.secededate) }">
						<a href='roleDef!deleteUserRole.do?userrole.usercode=${user.usercode}&userrole.rolecode=${user.rolecode}&obtaindate=${user.obtaindate}'
							onclick='return confirm("是否删除用户角色：${user.rolecode}?");'>
							删除
						</a>
					</c:if>
					
					<c:if test="${user.obtaindate lt myDate and ( user.secededate == null or user.secededate gt myDate)}">
						<a href='roleDef!deleteUserRole.do?userrole.usercode=${user.usercode}&userrole.rolecode=${user.rolecode}&obtaindate=${user.obtaindate}'
							onclick='return confirm("是否回收用户角色：${user.rolecode}?");'>
							回收
						</a>
					</c:if>
					
				</ec:column>
			
			</ec:row>
		</ec:table> 		

</body>
</html>
