<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>用户角色编辑-- <c:out
		value="${cp:MAPVALUE('usercode',userForm.map.usercode)}" />: <c:out
		value="${cp:MAPVALUE('rolecode',userForm.map.rolecode)}" /></title>
<sj:head locale="zh_CN" />
<script language="javascript"
	src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>
<link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="<s:url value="/scripts/autocomplete/autocomplete.js"/>"
	type="text/javascript"></script>

<script type="text/javascript">
        var list = [];
        <c:forEach var="userinfo" varStatus="status" items="${userlist}">
            list[${status.index}] = { username:'<c:out value="${userinfo.username}"/>', loginname:'<c:out value="${userinfo.loginname}"/>', usercode:'<c:out value="${userinfo.usercode}"/>',pinyin:'<c:out value="${userinfo.usernamepinyin}"/>'  };
        </c:forEach>
        function selectUser(obj) {
        		
               userInfo.choose(obj, {dataList:list,userName:$('#userName')});
        }
    </script>
</head>
<body class="sub-flow">
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">
			用户角色编辑--
			<c:out value="${cp:MAPVALUE('usercode',fUserrole.usercode)}" />
			:
			<c:out value="${cp:MAPVALUE('rolecode',fUserrole.rolecode)}" />
		</legend>


		<s:form action="roleDef" namespace="/sys" cssClass="userroleForm"
			theme="simple" onsubmit="return validateUserroleForm(this);">


			<s:submit method="saveUserRole" cssClass="btn" value="保存" />
			<input type="button" value="返回" class="btn"
				onclick="window.history.go(-1)" />
			<s:hidden name="underUnit" value="%{underUnit}"></s:hidden>
			<table cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">用户角色</td>
					<td align="left"><s:textfield name="userrole.id.rolecode"
							value="%{userrole.id.rolecode}" readonly="true"
							style="width:140px;" theme="simple" /></td>
				</tr>
				<tr>
					<td class="addTd">角色名</td>
					<td align="left"><c:out
							value="${cp:MAPVALUE('rolecode',userrole.id.rolecode)}" /></td>
				</tr>
				<c:if test="${not empty userrole.id.usercode }">
				<tr><td class="addTd">用户代码</td>
							<td align="left"><s:textfield name="userrole.usercode" value="%{userrole.id.usercode}" readonly="true"/></td>
				</tr>
				<tr>
					<td class="addTd">用户名</td>
							<td align="left" id="un">
								<div id="userName">
									<c:out value="${cp:MAPVALUE('usercode',userrole.usercode)}" />
								</div>
							</td>
				</tr>
				</c:if>
				<c:if test="${empty userrole.id.usercode }">
					<tr>
						<td class="addTd">用户代码</td>
						<td align="left"><s:textfield name="userrole.usercode"
								onclick="selectUser(this)" id="userCode" style="width:140px;" />
						</td>
					</tr>
					<tr>
						<td class="addTd">用户名</td>
						<td align="left" id="un">
							<div id="userName">
								<c:out value="${cp:MAPVALUE('usercode',userrole.usercode)}" />
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="addTd">获取时间</td>
					<td align="left"><s:date name="userrole.id.obtaindate"
							format="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="addTd">到期时间</td>
					<td align="left"><sj:datepicker id="userrole.secededate"
							name="userrole.secededate" value="%{userrole.secededate}"
							style="width:120px;" yearRange="2000:2020"
							displayFormat="yy-mm-dd" changeYear="true" /></td>
				</tr>
				<tr>
					<td class="addTd">授权说明</td>
					<td align="left"><s:textarea name="userrole.changedesc"
							style="width:600px;height:50px;" value="%{userrole.changedesc}" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>
