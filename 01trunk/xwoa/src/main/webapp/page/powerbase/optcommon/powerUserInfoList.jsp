<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="powerUserInfo.list.title" />
		</title>
			 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		
		<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"  type="text/javascript" />
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="powerUserInfo" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">登录名：</td>
					<td><input type="text" class="span2" name="s_loginname"
						value="${s_loginname }" /></td>

					<td class="addTd">用户名：</td>
					<td><input type="text" class="span2" name="s_username" value="${s_username }" /></td>
				</tr>
				

				<tr >
				    <td class="addTd">所属单位：</td>
				    <td>
				  <select name="s_queryByUnit"  style="width: 195px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.unitcode}"
									<c:if test="${s_queryByUnit eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select>			
							</td>
				</tr>
				
				<tr class="searchButton">	
				    <td colspan="4">
						<s:submit method="list"  value="查询" cssClass="btn"/>
<%-- 						<s:submit method="built" cssClass="btn btnWide" value="添加事权关联" /> --%>
					</td>
				</tr>
						
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerbase/powerUserInfo!list.do" items="userList" var="fUserinfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tusercode">用户代码</c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />

				<c:set var="tusername">用户名</c:set>	
				<ec:column property="username" title="${tusername}" style="text-align:center" />


				<c:set var="tloginname">登录名</c:set>	
				<ec:column property="loginname" title="${tloginname}" style="text-align:center" />

               <ec:column property="primaryUnit" title="主机构" style="text-align:center" >
               ${cp:MAPVALUE("unitcode",fUserinfo.primaryUnit)}
               </ec:column>

				<c:set var="tuserdesc">用户描述</c:set>	
				<ec:column property="userdesc" title="${tuserdesc}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					
					
<%-- 					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set> --%>
<%-- 					<a href='powerbase/powerUserInfo!view.do?usercode=${fUserinfo.usercode}&itemId=${powerUserInfo.itemId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a> --%>
					<a href='powerbase/powerUserInfo!powerList.do?usercode=${fUserinfo.usercode}'>关联事权</a>
<%-- 					<a href='powerbase/powerUserInfo!delete.do?usercode=${fUserinfo.usercode}&itemId=${powerUserInfo.itemId}'  --%>
<%-- 							onclick='return confirm("${deletecofirm}powerUserInfo?");'><s:text name="opt.btn.delete" /></a> --%>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
