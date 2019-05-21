<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaCarinfo.list.title" /></title>
</head>
<fieldset class="search">
	<legend>
		<s:text name="label.list.filter" />
	</legend>

	<s:form action="oaAssetinformation" namespace="/oa"
		style="margin-top:0;margin-bottom:5">
		<input type="hidden" id="isDept" name="isDept" value="${isDept}"/>
		<table cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td class="addTd">登录名:</td>
				<td><s:textfield name="s_loginname" /></td>
				<td class="addTd">用户名:</td>
				<td><s:textfield name="s_username" /></td>
			</tr>
			<tr >
			    <td class="addTd">所属单位：</td>
			    <td>
			  <select name="s_unitcode"  style="width: 195px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${unitlist }">
							<option value="${row.unitcode}"
								<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
								<c:out value="${row.unitname}" />
							</option>
						</c:forEach>
				</select>			
						</td>
			</tr>
			<tr class="searchButton">
				<td colspan="5 "><s:submit method="userSelectList"
						key="opt.btn.query" cssClass="btn" /></td>
			</tr>
			
		</table>
	</s:form>
</fieldset>

<ec:table action="oa/oaAssetinformation!userSelectList.do"
	items="userlist" var="user"
	imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
	retrieveRowsCallback="limit" sortable="false">
	<input type="hidden" id="isDept" value="${isDept}"/>
	<ec:row>
		<input type="hidden" id="unitcode${user.orgCode }"
			value="${user.orgCode }" name="unitcode${user.orgCode }" />
		<input type="hidden" id="unitName${user.unitName }"
			value="${user.unitName }" name="unitName${user.unitName }" />
		<ec:column property="usercode" title="用户代码">
				${user.usercode }
				</ec:column>
		<ec:column property="loginname" title="登录名">
				${user.loginname }
				</ec:column>
		<ec:column property="username" title="用户名">
				${cp:MAPVALUE('usercode',user.usercode)}
				</ec:column>
		<ec:column property="orgCode" title="用户机构">
				${cp:MAPVALUE('unitcode', user.orgCode) }
				</ec:column>
	    <ec:column property="opt" title="操作" sortable="false"
			style="text-align:center;width:3%">
			<input type="radio" name="usercode"
				onclick="setParentVal('${user.usercode}','${user.username}','${user.unitName}','${user.orgCode}')">
		</ec:column>
	</ec:row>
</ec:table>

<script type="text/javascript">
	//获取父页面对象
	var parentDocument = window.opener.document;
	//设置返回值
	function setParentVal(usercode,username,unitName,orgCode) {
		if (window.confirm("确认选择吗")) {
			parentDocument.getElementById('applyuser').value = usercode;
			parentDocument.getElementById('applyusers').value =username;
			parentDocument.getElementById('applyUnitcode').value = orgCode;
			parentDocument.getElementById('applyUnitcodes').value =unitName;
			window.close();
		}
	}
</script>