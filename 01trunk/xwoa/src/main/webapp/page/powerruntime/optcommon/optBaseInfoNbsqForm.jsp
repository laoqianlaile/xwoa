<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" %>
<%@ include file="/page/common/taglibs.jsp"%>
<%-- <%@ include file="/page/common/css.jsp"%> --%>
<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
</script>
<html>
<head>


<script type="text/javascript">
//选择模版上传文档
function openDocEdit(val,documentType){
	//document.getElementById("remark").focus();
	if(val==null || val==''){
		alert("操作失败，对应模版没有配置!");
	}else{
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var param = "flowStep=ZW_EDIT&archiveType="+documentType+"&RecordID=${object.djId}&Template=" + val
	 			+"&NeedBookMark=1&nodeInstId=0";
	openNewWindow(uri + "?"+ param,'editForm','');
	}
}
//修改文档
function updtDoc(documentType){		
	var curTemplateId = document.getElementById("curTemplateId").value;	
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=ZW_EDIT&RecordID=${object.djId}&EditType=2,1&ShowType=1&Template="+curTemplateId+"&archiveType="+documentType;
		openNewWindow(uri + "?"+ param,'editForm','');
	} else {
		alert("请生成您需要的文书！！");
	}
}
</script>
<title>办件基础信息</title>
</head>

<body>
	<%-- <s:form action="optApply"  method="post" namespace="/powerruntime" onsubmit="return checkForm();" > --%>
	<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
	<input type="hidden" id="djId" name="djId" value="${object.djId}" />
	<input type="hidden" id="djId" name="optBaseInfo.djId" value="${object.djId}" />
	<input type="hidden" id="optId" name="optBaseInfo.optId" value="${object.optBaseInfo.optId}" />
	<input type="hidden" name="isapplyuser" value="${isapplyuser}">
	<input type="hidden" name="s_itemtype" value="${s_itemtype}">
	<input type="hidden" name="itemtype" value="${s_itemtype}">
	<input type="hidden" id="powerid" name="optBaseInfo.powerid" value="${object.optBaseInfo.powerid}">
	<input type="hidden" id="powername" name="optBaseInfo.powername" value="${object.optBaseInfo.powername}" style="width: 400px;" readonly="readonly">
	<input type="hidden" name="nodeInstId" value="${nodeInstId}">
	<input type="hidden" id="orgcode" name="optBaseInfo.orgcode" value="${object.optBaseInfo.orgcode}">
	<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
	<input type="hidden" id="nodeId" name="nodeId" value="${nodeId}" />
	<input type="hidden" id="startDjId" name="startDjId" value="${startDjId}" />
	<input type="hidden" id="curTemplateId" name="curTemplateId" value="${vPowerUserInfo.recordid}" />
	<input type="hidden" id="rolecode" name="rolecode" value="JSBM" />
	<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleParam.moduleCode}" />
	
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;" >
		<legend style="padding: 4px 8px 3px;">
			<b>办件信息</b>
		</legend>

		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">			
			<tr>
				<td class="addTd" width="130"><c:if
						test="${s_itemtype eq 'SQ' }">
						事项标题<span style="color:red;">*</span>
					</c:if> <c:if test="${s_itemtype eq 'QB' }">
						签报标题<span style="color:red;">*</span>
					</c:if></td>
				<td align="left"><s:textfield name="optBaseInfo.transaffairname" id="transaffairname" value="%{object.optBaseInfo.transaffairname}" style="width:100%;height:27px;" /></td>
			</tr>
	<tr>
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" >
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:24px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>			
				</tr>
			<tr>
				<td class="addTd"><c:if test="${s_itemtype eq 'SQ' }">
						事项内容<span style="color:red;">*</span>
					</c:if> <c:if test="${s_itemtype eq 'QB' }">
						签报内容<span style="color:red;">*</span>
					</c:if></td>
				<td align="left"><s:textarea name="optBaseInfo.content" id="content" value="%{object.optBaseInfo.content}" style="width:100%;height:40px;" /></td>
			</tr>
			<tr>
			<c:if test="${s_itemtype eq 'SQ' }">
				<td class="addTd">接收部门<span style="color:red;">*</span></td>
				<td align="left"><input type="text" id="orgName" name="orgName" rows="1" cols="1" style="width: 100%; height: 30px" value="${unitValue }" readonly="readonly"/> 
				<input id="recievedepno" type="hidden" name="recievedepno" value="${object.recievedepno }" />
				</td>
				<c:if test="${not empty vPowerUserInfo.recordid }">
				<tr>
					<td class="addTd">正文</td>
					<td align="left">		
					<a href="javascript:void(0);" onclick="openDocEdit('${vPowerUserInfo.recordid}','${vPowerUserInfo.archiveType}');" class="btnA">
									<span id="ws" class="btn">
									<c:choose>
					<c:when test="${fn:length(vPowerUserInfo.docFileName) > 10}">
						<c:out value="${fn:substring(vPowerUserInfo.docFileName, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vPowerUserInfo.docFileName}" />
					</c:otherwise>
				</c:choose>
									</span></a>
					
					</td>
				</tr>
			</c:if>
			</c:if>
				<c:if test="${s_itemtype eq 'QB' }">
				<td class="addTd">${moduleParam.teamRoleName}<span style="color:red;">*</span></td>
				<td align="left"><input type="text" id="userName" name="userName" rows="1" cols="1" style="width: 100%; height: 30px" value="${unitValue }" readonly="readonly"/> 
				<input id="recievedepno" type="hidden" name="recievedepno" value="${object.recievedepno }" />
				</td>
				<%-- <tr>
				<td class="addTd">接收部门<span style="color:red;">*</span></td>
				<td align="left"><input type="text" id="orgName" name="orgName" rows="1" cols="1" style="width: 100%; height: 30px" value="${unitValue }" readonly="readonly"/> 
				<input id="recievedepno" type="hidden" name="recievedepno" value="${object.recievedepno }" />
				</td>
				</tr> --%>
			
				
				<c:if test="${not empty vPowerOrgInfo.recordid }">
				<tr>
					<td class="addTd">正文</td>
					<td align="left">		
					<a href="javascript:void(0);" onclick="openDocEdit('${vPowerOrgInfo.recordid}','${vPowerOrgInfo.archiveType}');" class="btnA">
									<span id="ws" class="btn">
									<c:choose>
					<c:when test="${fn:length(vPowerOrgInfo.docFileName) > 10}">
						<c:out value="${fn:substring(vPowerOrgInfo.docFileName, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vPowerOrgInfo.docFileName}" />
					</c:otherwise>
				</c:choose>
									</span></a>
					
					</td>
				</tr>
			</c:if>
				</c:if>
			</tr>
			
				 <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="2" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提醒：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>
			
		</table>
	

	</fieldset>
	<%-- </s:form> --%>
</body>
<script type="text/javascript">

	$("#orgName").click(
			function() {
				var d = '${unitsJson}';
				if (d.trim().length) {
					selectPopWinTemp(jQuery.parseJSON(d), $("#orgName"),
							$("#recievedepno"));
				}
				;
			});
	
	$("#userName").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#userName"),
							$("#recievedepno"));
				}
				;
			});
	function selectPopWinTemp(json, o1, o2) {
		new person(json, o1, o2).init();
		setAlertStyle("attAlert");
	}
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow,1).init();/* 此处人员限制为一人 */
		setAlertStyle("attAlert");
	}
	function getOptBaseInfoJson(){
		return ${optBaseInfoJson};
	}
</script>

</html>
