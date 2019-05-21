<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>用户角色编辑-- <c:out
	value="${cp:MAPVALUE('usercode',userForm.map.usercode)}" />: <c:out
	value="${cp:MAPVALUE('rolecode',userForm.map.rolecode)}" /></title>
<%-- <sj:head locale="zh_CN" /> --%>



</head>

<body class="sub-flow">
<fieldset style="padding:10px;"  class="form">
	<legend  style="width:auto;margin-bottom:10px;">用户角色编辑-- <c:out
	value="${cp:MAPVALUE('usercode',fUserrole.usercode)}" />: <c:out
	value="${cp:MAPVALUE('rolecode',fUserrole.rolecode)}" /></legend>
<s:form action="userDef" namespace="/sys" cssClass="userroleForm"
	theme="simple" onsubmit="return validateUserroleForm(this);">
	<input type="hidden" name="underUnit" id="underUnit" value="${underUnit}" >
	<input type="hidden" name="usercode" id="usercode" value="${userrole.id.usercode}" >
	<table cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="addTd">用户代码</td>
			<td align="left" ><s:textfield name="userrole.id.usercode"
				value="%{userrole.id.usercode}" readonly="true" style="width:140px;"
				theme="simple" /></td>
		</tr>
		<tr>
			<td class="addTd">用户名</td>
			<td align="left"><c:out
				value="${cp:MAPVALUE('usercode',userrole.id.usercode)}" /></td>
		</tr>
		<tr>
			<td class="addTd">用户角色</td>
			<td align="left">
				<c:if test="${not empty userrole.rolecode }">
					<input type="hidden" name="userrole.id.rolecode" value="${userrole.id.rolecode }">
					<select name="userrole.id.rolecode" disabled="disabled" style="width:140px;">
						<c:forEach var="row" items="${cp:ROLEINFO('G-')}">
							<option value="<c:out value='${row.rolecode}'/>"
								<c:if test="${row.rolecode==userrole.id.rolecode}">selected="selected"</c:if>>
							<c:out value="${row.rolename}" /></option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${empty userrole.id.rolecode }">
					<select name="userrole.id.rolecode" style="width:140px;">
						<c:forEach var="row" items="${cp:ROLEINFO('G-')}">
							<option value="${row.rolecode}">
							<c:out value="${row.rolename}" /></option>
						</c:forEach>
						<c:forEach var="row" items="${cp:ROLEINFO('P-')}">
							<option value="${row.rolecode}">
							<c:out value="${row.rolename}" /></option>
						</c:forEach>
					</select>
				</c:if>
			
			</td>
		</tr>
		<tr>
			<td class="addTd">获取时间</td>
			<td align="left">
				<s:date name="userrole.id.obtaindate" format="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<td class="addTd">到期时间</td>
			<td align="left">
			<input type="text" class="Wdate"  id="userrole.secededate" name="userrole.secededate" 
					style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
					value='<fmt:formatDate value="${userrole.secededate}" pattern="yyyy-MM-dd"/>'
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 			<sj:datepicker id="userrole.secededate" --%>
<%-- 				name="userrole.secededate" value="%{userrole.secededate}" style="width:120px;" --%>
<%-- 				yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
			</td>
		</tr>
		<tr>
			<td class="addTd">授权说明</td>
			<td align="left"><s:textarea
				name="userrole.changedesc" style="width:600px;height:50px;" value="%{userrole.changedesc}" /></td>
		</tr>
	</table>
	<div class="formButton">
		<s:submit method="saveUserRole" cssClass="btn" value="保存" />
		<input type="button" value="返回" class="btn" onclick="window.history.go(-1)" />
	</div>
</s:form>
</fieldset>
</body>
</html>
