<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
<!-- 	<p class="ctitle"> -->
<%-- 		<s:text name="powerOrgInfo.edit.title" /> --%>
<!-- 	</p> -->

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="powerOrgInfo" method="post" namespace="/powerbase" id="powerOrgInfoForm">
		
		<s:submit name="save" method="savePowerOrgInfo" cssClass="btn" key="opt.btn.save" />
		<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
		<input type="hidden" id="itemId" name="itemId" value="${object.itemId}"/>
		<input type="hidden" id="unitcode" name="unitcode" value="${object.unitcode }"/>
		<fieldset style="padding:10px;display:block;margin-bottom:10px;">
<legend style="padding:4px 8px 3px;"><b>关联信息</b></legend>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable"><tr>
					<td class="addTd">
						权力编码
					</td>
					<td align="left">
					<s:property value="%{object.itemId}"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">行使部门</td>
					<td align="left">
				<select  name="unitcode" disabled="disabled">
							<c:forEach var="row" items="${cp:LVB('unitcode')}">
               					 <option value="<c:out value='${row.value}'/>"  
               					 <c:if test="${row.value==object.unitcode}">selected="selected"</c:if>>
                  			 		 <c:out value="${row.label}"/>
              				  </option>
           					</c:forEach>
						</select>
					</td>
				</tr>
					<tr>
					<td class="addTd" width="130">
						流程代码
					</td>
					<td align="left">
						<select name="wfcode" style="width: 200px">

						<c:forEach var="row" items="${flowDescribesList}">
							<option value="${row.value}"
								<c:if test="${object.wfcode eq row.value}"> selected = "selected" </c:if>>
								<c:out value="${row.label}" />
							</option>
						</c:forEach>
				</select> <span style="color: red">*</span>
					</td>
				</tr>	
				
				</table>
				</fieldset>
	</s:form>