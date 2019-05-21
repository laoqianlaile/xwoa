<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUserinfo.edit.title" /></title>
<style type="text/css">
table td {
	white-space: nowrap;
}
</style>
</head>

<body class="sub-flow">
<fieldset class="_new">
		<legend>
			<p>编辑内部通讯录</p>
		</legend>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaUserinfo" method="post" namespace="/oa"
		id="oaUserinfoForm">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input type="button" class="btn" value="返回" onclick="window.history.back();"/>
		<input type="hidden" id="usercode" name="usercode"
			value="${object.usercode}" />
		<input type="hidden" name="bodycode" value="${bodycode }" />
		<table border="0" cellpadding="0" cellspacing="0" style="width:95%;" class="viewTable">
			<tr>
				<td class="addTd">姓名</td>
				<td align="left">${cp:MAPVALUE("usercode",object.usercode)}</td>
				<td class="addTd">职务</td>
				<td align="left">${object.fUserinfo.userdesc }</td>
			</tr>
			<%-- <tr>
				<td class="addTd">性别</td>
				<td align="left"><select id="sex"
					style="width: 200px; height: 25px;" name="sex">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('sex')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==sex}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select></td> 
				<td class="addTd">出生日期</td>
				<td align="left"><input type="text" class="Wdate"
					id="startDate"
					style="height: 25px; width: 238px; border-radius: 3px; border: 1px solid #cccccc;"
					value='<fmt:formatDate value="${birthday}" pattern="yyyy-MM-dd"/>'
					name="birthday"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
					placeholder="选择日期"></td>
			</tr> --%>

			<tr>
				<td class="addTd"><s:text name="oaUserinfo.telephone" /></td>
				<td align="left"><s:textfield name="telephone" size="35" style="width: 200px;height:30px"/></td>

				<td class="addTd"><s:text name="oaUserinfo.mobilephone" /></td>
				<td align="left"><s:textfield name="mobilephone" size="35" style="width: 200px;height:30px"/></td>
			</tr>
			<tr>
				<td class="addTd">电子邮箱</td>
				<td align="left" ><s:textfield name="email" size="35"
						class="email" id="email" style="width: 200px;height:30px"/></td>
				<td class="addTd">工作地点</td>
				<td align="left"><s:textfield name="workplace"
						size="35" style="width: 200px;height:30px"/><!-- <br/>
					<span style=" font-weight:bold;">(楼层)</span></td> -->
			</tr>
			<tr>
				<td class="addTd" >备注</td>
				<td align="left" colspan="4"><s:textarea name="remark"
						id="remark"  style="width: 100%;height:50px"/></td>
			</tr>
<%-- 
			<tr>
				<td class="addTd"><s:text name="oaUserinfo.placeofbirth" /></td>
				<td align="left" colspan="4"><s:textarea style="width:100%;"
						name="placeofbirth" /></td>
			</tr> --%>
		<%-- 	<tr>
				<td class="addTd">用户说明</td>
				<td align="left" colspan="4">${cp:MAPVALUE("RankType",userrank)}</td>
			</tr> --%>
		</table>
</s:form>
</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>