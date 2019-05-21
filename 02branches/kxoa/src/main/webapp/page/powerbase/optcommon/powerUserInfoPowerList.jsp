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
			<input type="hidden" name="usercode" value="${userunit.id.usercode }"/>
			<input type="hidden" name="itemIds" id="itemIds" value=${itemIds }>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

               <tr>
					<td class="addTd">用户代码
					</td>
					<td align="left" >${userunit.id.usercode }</td>
					<td class="addTd">被授权人员
					</td>
					<td align="left">${cp:MAPVALUE('usercode',userunit.id.usercode) }</td>
				</tr>
				
				<tr>
					<td class="addTd">主部门</td>
					<td align="left" colspan="3">${cp:MAPVALUE('unitcode',userunit.id.unitcode)}</td>
				</tr>
	 

				<tr>
					<td class="addTd">岗位
					</td>
					<td align="left">${cp:MAPVALUE('StationType',userunit.id.userstation)}

					</td>
						<td class="addTd">职务
					</td>
					<td align="left">${cp:MAPVALUE('RankType',userunit.id.userrank)}
					</td>
				</tr>
			</table>
		
			<div class="formButton">
				<input type="button" class='btn' name="powerBtn"
								onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerUserInfo!powerSelectList.do?itemIds=${itemIds}&usercode=${userunit.id.usercode}&num='+Math.random()+new Date(),'powerWindow',null);"
								value="关联事权">
<!-- 			   <input type="button" name="glpoewr" class="btn" onclick="oaOpenPower();" value="关联事权" /> -->
<%-- 				<s:submit name="save" method="save" cssClass="btn" key="关联事权" /> --%>
<%-- 				<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" /> --%>
				
				<input type="button" name="back" Class="btn"
				onclick="javascript:window.location.href='<%=request.getContextPath()%>/powerbase/powerUserInfo!list.do';" value="返回" />
			</div>
			 </s:form>	
            <ec:table action="powerbase/powerUserInfo!powerList.do" items="vPowerUserInfoList" var="vPowerUserInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

			<ec:column property="itemId"  title="权力编号" style="text-align:center" sortable="false"/>

			<ec:column property="itemName" title="权力名称" style="text-align:center" sortable="false">
			<input  type="hidden"  title="${vPowerUserInfo.itemName}" value="${vPowerUserInfo.itemName}" />
				<c:choose>
					<c:when test="${fn:length(vPowerUserInfo.itemName) > 15}">
						<c:out value="${fn:substring(vPowerUserInfo.itemName, 0, 15)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vPowerUserInfo.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

		     	<ec:column property="orgId" title="行使部门" style="text-align:center" sortable="false">
					${cp:MAPVALUE("depno",vPowerUserInfo.orgId)}
				</ec:column>
			<ec:column property="itemType" title="权力类型" style="text-align:center" sortable="false">
					${cp:MAPVALUE("ITEM_TYPE",vPowerUserInfo.itemType)}
			</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center"> 
				<a
					href='powerbase/powerUserInfo!deletePower.do?itemId=${vPowerUserInfo.itemId}&usercode=${userunit.id.usercode}' onclick='return confirm("确认删除该事权配置?");'><s:text 
						name="opt.btn.delete" /></a>  
 			</ec:column> 

		</ec:row>
	</ec:table>
	
	</fieldset>
	</body>
	<script type="text/javascript">
function oaOpenPower(){
	var url ="<%=request.getContextPath()%>/powerbase/powerUserInfo!powerSelectList.do?usercode=${userunit.id.usercode}";
	document.location.href=url;
}

function openNewWindow(winUrl, winName, winProps) {
	if (winProps == '' || winProps == null) {
		winProps = 'height=900px,width=1100px,directories=false,location=false,top=0,left=150,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
</script>