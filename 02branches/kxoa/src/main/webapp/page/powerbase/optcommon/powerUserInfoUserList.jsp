<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		
<title><s:text name="powerUserInfo.edit.title" /></title>
</head>

<body>
	<%-- <p class="ctitle">
		<s:text name="powerUserInfo.edit.title" />
	</p> --%>

	<fieldset class="form">
		<legend>
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${null eq powerUserInfo.usercode || null eq powerUserInfo.itemId }"> --%>
<%-- 					<p class="ctitle">新增权力关联</p></c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 				    <p class="ctitle">修改权力关联</p> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
                <p class="ctitle">事权关联配置</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="powerUserInfo" method="post" namespace="/powerbase"
			id="powerUserInfoForm">
			<input type="hidden" name="itemId" value="${suppowerinusing.itemId }"/>
			<input type="hidden" name="usercodes" id="usercodes" value=${usercodes }>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

               <tr>
					<td class="addTd">权力编号
					</td>
					<td align="left" >${suppowerinusing.itemId }</td>
					
				</tr>
				
				<tr>
					<td class="addTd">权力名称</td>
					<td align="left" colspan="3">${suppowerinusing.itemName}</td>
				</tr>
			</table>
		
			<div class="formButton">
				<input type="button" class='btn' name="powerBtn"
								onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerUserInfo!userSelectList.do?itemId=${suppowerinusing.itemId}','powerWindow',null);"
								value="关联人员">
<!-- 			  <input type="button" name="gluser" class="btn" onclick="oaOpenUser();" value="关联人员" /> -->
<%-- 				<s:submit name="save" method="save" cssClass="btn" key="关联事权" /> --%>
<%-- 				<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" /> --%>
	<input type="button" name="back" Class="btn"
				onclick="javascript:window.location.href='<%=request.getContextPath()%>/powerbase/vsuppowerinusing!list.do';" value="返回" />
			</div>
			 </s:form>	
            <ec:table action="powerbase/powerUserInfo!userList.do" items="powerUserInfoList" var="vPowerUserInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

			<ec:column property="usercode" title="用户编码" style="text-align:center" sortable="false"/>

			<ec:column property="none" title="用户名" style="text-align:center" sortable="false">
			${cp:MAPVALUE("usercode",vPowerUserInfo.usercode)}
			</ec:column>

			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center"> 
				<a
					href='powerbase/powerUserInfo!deleteUser.do?itemId=${suppowerinusing.itemId}&usercode=${vPowerUserInfo.usercode}' onclick='return confirm("确认删除该事权配置?");'><s:text 
						name="opt.btn.delete" /></a>  
 			</ec:column> 

		</ec:row>
	</ec:table>
	
	</fieldset>
	</body>
	<script type="text/javascript">
function oaOpenPower(){
	var url ="<%=request.getContextPath()%>/powerbase/powerUserInfo!userSelectList.do?itemId=${suppowerinusing.itemId}";
	document.location.href=url;
}

function openNewWindow(winUrl, winName, winProps) {
	if (winProps == '' || winProps == null) {
		winProps = 'height=900px,width=1100px,directories=false,location=false,top=0,left=150,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}

</script>