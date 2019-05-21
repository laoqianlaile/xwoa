<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="powerOrgInfo.edit.title" /></title>
</head>

<body>
	<%-- <p class="ctitle">
		<s:text name="powerOrgInfo.edit.title" />
	</p>
 --%>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="powerOrgInfo" method="post" namespace="/powerbase" id="powerOrgInfoForm">
		
		<s:submit name="save" method="saveOrg" cssClass="btn" key="opt.btn.save" />
		<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
		<input type="hidden" id="itemId" name="itemId" value="${object.itemId}"/>		
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
					<input type="hidden" id="unitcode" name="retValue" value=""/>
			<s:textarea name="uninName"
						cols="40" rows="2" style="width:70%;height:40px;" value=""/>
			<input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerOrgInfo!listSelectOrg.do?ec_o_1=true&itemId=${object.itemId}','orgWindow',null);"
					value="选择">			
					</td>
				</tr>
					<tr>
					<td class="addTd" width="130">
						流程名称
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
	</body>
	<script type="text/javascript">
	
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	
	</script>
	</html>