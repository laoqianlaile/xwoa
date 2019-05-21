<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>会议室信息</title>
</head>

<body>
	<fieldset class="form">
		<legend>
				<s:text name="oaCommonType.edit.title" />
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/oaCommonType!save.do"
			method="post" id="oaCommonTypeForm"  data-validate="true">

			<input type="hidden" id="no" name="no" value="${no }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">类别</td>
					<td align="left"><select id="publicType" style="width:200px;height:25px;"
						select name="publicType">
							<c:forEach var="row" items="${cp:DICTIONARY('publicType') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==publicType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">启用状态</td>
					<td align="left"><select id="state" style="width:200px;height:25px;"
						name="state">
							<c:if test="${ empty state}">
								<option value=""></option>
							</c:if>
							<c:forEach var="row" items="${cp:DICTIONARY('equState') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==state}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>

				</tr>

				<tr>
					<td class="addTd">编码</td>
					<td align="left"><input type="text" name="coding" 
						style="width:200px;height:25px;" id="coding"  class="focuse required"
						maxlength="16" value="${coding }" /></td>
					<td class="addTd">排序</td>
					<td align="left"><input type="text" name="orderno" 
						style="width:200px;height:25px;" id="orderno"  class="focuse digits" 
						maxlength="38"  value="${orderno }" /></td>


				</tr>
				<tr>
					<td class="addTd">名称</td>
					<td align="left" colspan="3"><input type="text" name="comName"
						style="width: 100%;" id="comName" class="focuse required"
						maxlength="133" value="${comName }" /></td>
					<!-- 			<td class="addTd">是否公开</td> -->
					<!-- 			 <td align="left" > -->
					<!-- 					<select data-rel="chosen" id="isopen" -->
					<!-- 						name="isopen" class="combox" > -->
					<%-- 							<c:forEach var="row" items="${cp:DICTIONARY('IS_BOOLEAN') }"> --%>
					<%-- 								<option value="${row.datacode}" --%>
					<%-- 							    	<c:if test="${row.datacode==isopen}"> selected="selected"</c:if> --%>
					<!-- 								  > -->
					<%-- 									<c:out value="${row.datavalue}" /> --%>
					<!-- 								</option> -->
					<%-- 							</c:forEach> --%>
					<!-- 					</select> -->
					<!-- 			  </td> -->
				</tr>

				<tr>
					<td class="addTd">描述</td>
					<td align="left" colspan="3"><textarea id="remark"
							style="width: 100%;" name="remark" rows="5" cols="50">${remark }</textarea></td>
				</tr>
			</table>
			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
				<button type="submit" class="btn btn-primary">保存</button>
			</div>

		</form>
	</fieldset>

</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>