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
			<td class="addTd">用户工号：</td>
			<td align="left" width="150"><s:property value="%{usercode}"/></td>
			<td class="addTd">用户名：</td>
			<td align="left" width="150"><s:property value="%{username}"/></td>
			<td class="addTd">登录名：</td>
			<td align="left"><s:property value="%{loginname}"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="5">
			 <input type="button" value="添加用户机构" class="btn btnWide" onclick="location.href='userDef!addUserUnit.do?usercode=${usercode}'" />
			 <input type="button" value="添加用户角色" class="btn btnWide" onclick="location.href='userDef!bulitUserRole.do?usercode=${usercode}'" />
			 <%-- <a href="userDef!addUserUnit.do?usercode=${usercode}" class="btnA"><span class="btn">添加用户机构</span></a>			
			 <a href="userDef!bulitUserRole.do?usercode=${usercode}" class="btnA"><span class="btn">添加用户角色</span></a> --%>
			<input type="button" value="返回" class="btn" onclick="window.history.back();">					
			</td>			
		</tr>
	</table>

     <ec:table items="userunits" var="fUserunit"  action="userDef!view.do?usercode=${usercode}" sortable="false" showPagination="false"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="uu" >
		<ec:row>
			<ec:column property="unitcode" title="机构代码" style="text-align:center" />
			<ec:column property="unitname" title="机构名" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('unitcode',fUserunit.unitcode)}" />
			</ec:column>
			<ec:column property="userstation" title="用户岗位" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('StationType',fUserunit.userstation)}" />
			</ec:column>
			<ec:column property="userrank" title="行政职务" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('RankType',fUserunit.userrank)}" />
			</ec:column>
			<ec:column property="isprimary" title="是否主单位" sortable="false" style="text-align:center" >
				<c:out value="${YES_NO[fUserunit.isprimary]}" />
			</ec:column>
			<ec:column property="rankmemo" title="授权说明" sortable="false" style="text-align:center" />
			<ec:column property="unitopt" title="操作" sortable="false"	 >
				<c:if test="${fUserunit.isprimary ne 'T'}">
				<a href='userDef!editUserUnit.do?userUnit.usercode=${usercode}&userUnit.unitcode=${fUserunit.unitcode}&userUnit.userstation=${fUserunit.userstation}&userUnit.userrank=${fUserunit.userrank}'>
					编辑
				</a>
				<a href='userDef!deleteUserUnit.do?userUnit.usercode=${usercode}&userUnit.unitcode=${fUserunit.unitcode}&userUnit.userstation=${fUserunit.userstation}&userUnit.userrank=${fUserunit.userrank}'
					onclick='return confirm("是否删除此条数据");'>
					删除
				</a>
				</c:if>
			</ec:column>
		
		</ec:row>
	</ec:table> 	
	<br/>	
<%
    java.util.Date myDate = new java.util.Date();
    request.setAttribute("myDate",myDate);             
%>
	
      <ec:table items="userroles" var="role" action="userDef!view.do?usercode=${userinfo.usercode}" sortable="false"  showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="ur" >
			<ec:row>
				<ec:column property="rolecode" title="角色代码" style="text-align:center" />
				<ec:column property="rolename" title="角色名" sortable="false" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('rolecode',role.rolecode)}" />
				</ec:column>
				<ec:column property="obtaindate" title="获取时间" style="text-align:center" cell="date" format="yyyy-MM-dd"/>
				<ec:column property="secededate" title="失效时间" style="text-align:center"  cell="date" format="yyyy-MM-dd"/>
				<ec:column property="changedesc" title="授权说明" sortable="false" style="text-align:center" />
				<ec:column property="roleopt" title="操作" sortable="false"	 >
					<c:if test="${role.secededate == null or  myDate lt role.secededate }">
					<a href='userDef!editUserRole.do?usercode=${usercode}&userrole.id.rolecode=${role.rolecode}&obtaindate=${role.obtaindate}'>
						编辑
					</a>
					</c:if>
					<c:if test="${role.obtaindate gt myDate and (role.secededate == null or myDate lt role.secededate) }">
						<a href='userDef!deleteUserRole.do?userrole.usercode=${usercode}&userrole.rolecode=${role.rolecode}&obtaindate=${role.obtaindate}'
							onclick='return confirm("是否删除用户角色：${role.rolecode}?");'>
							删除
						</a>
					</c:if>
					
					<c:if test="${role.obtaindate lt myDate and ( role.secededate == null or role.secededate gt myDate)}">
						<a href='userDef!deleteUserRole.do?userrole.usercode=${usercode}&userrole.rolecode=${role.rolecode}&obtaindate=${role.obtaindate}'
							onclick='return confirm("是否回收用户角色：${role.rolecode}?");'>
							回收
						</a>
					</c:if>
					
				</ec:column>
			
			</ec:row>
		</ec:table> 		

</body>
</html>
