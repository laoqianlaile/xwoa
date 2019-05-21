<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title><s:text name="generalModuleParam.edit.title" /></title>
</head>

<body onload="doInitial();" class="sub-flow">
<%@ include file="/page/common/messages.jsp"%>
<style>
.alert_title_div{
	position:absolute;
	width:80%;
	left:20px;
	display:none;
	background-color:#FEF9D9;
	line-height: 30px;
	padding: 10px;
	border-radius: 5px;
	color: #8C3957;
}
</style>
<fieldset class="form">
	<legend>
		<s:text name="generalModuleParam.edit.title" />
	</legend>
	<s:form action="generalModuleParam" method="post"
		namespace="/powerruntime" id="generalModuleParamForm" onsubmit="return docheck();" target="curWindow" >
		<input type="hidden" id="isAutoClose" name="isAutoClose"  value="${isAutoClose}" />
<%-- 		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" /> --%>
<!-- 		<input type="button"  value="返回" Class="btn" onclick="window.history.back()" /> -->

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
			<tr>
				<td class="addTd" >环节名称</td>
			<td align="left" colspan="3"><s:textfield id="nodeName" name="nodeName" style="width:200px;height:25px;"
						value="%{object.nodeName}" /><span style="color: red">*</span></td>
			</tr>
			<tr>
				<td class="addTd" >模块代码</td>
				<td align="left" colspan="3">
				<c:if test="${empty object.moduleCode}">
				<s:textfield name="moduleCode" style="width:200px;height:25px;" value="%{object.moduleCode}"/> <span style="color: red">*</span>
				</c:if>
				<c:if test="${not empty object.moduleCode}">
				<input type="hidden" id="moduleCode" name="moduleCode"  value="${object.moduleCode}" />
				<s:property value="%{object.moduleCode}" />
				</c:if>
			</tr>

			<tr>
			</td>

				<td class="addTd" >结果标签</td>
				<td align="left" colspan="3"><s:textfield name="ideaLabel"  style="width:200px;height:25px;"
						value="%{object.ideaLabel}" /></td>
			</tr>

			<tr>
				<td class="addTd" >结果代码</td>
				<td align="left" colspan="3">
					<s:textfield name="ideaCatalog"  style="width:200px;height:25px;" value="%{object.ideaCatalog}" />&nbsp;&nbsp;
				<input type="checkbox"  name="hasIdea" value="F"
				<c:if test="${object.hasIdea eq 'F' }">checked="checked"</c:if> onclick="doInitial();"/>屏蔽
					&nbsp;&nbsp;<font color="red" size="2">(*勾选后，该步骤办理界面将不显示办理结果信息)</font>
				</td>
			</tr>
			
			<%-- <tr>
				<td class="addTd" >快捷意见</td>
				<td align="left" colspan="3">
					<s:textfield name="quickContentResult"  style="width:200px;height:25px;" value="%{object.quickContentResult}" />&nbsp;&nbsp;
				<input type="checkbox"  name="isQuickContent" value="T"
				<c:if test="${object.isQuickContent eq 'T' }">checked="checked"</c:if>/>是否需要快捷意见
					&nbsp;&nbsp;(勾选后，该步骤办理意见会有快捷意见)
				</td>
			</tr> --%>
			
			<tr>
				<td class="addTd" >内容标签</td>
				<td align="left" colspan="3"><s:textfield name="ideaContent"  style="width:200px;height:25px;"
						value="%{object.ideaContent}" />&nbsp;&nbsp;
						<input type="checkbox"  name="btIdea" value="F"
				 <c:if test="${object.btIdea eq 'F' }">checked="checked"</c:if> />必填
					&nbsp;&nbsp;<font color="red" size="2">(*勾选后，该步骤办理意见为非必填项)</font>
						</td>
			</tr>
			<tr>
				<td class="addTd" >温馨提示<img class="wxtx_img" onmousemove="titleShow('wxts')" title="温馨提示" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" ></td>
				<td align="left" colspan="3"><s:textfield name="tips"  style="width:90%;height:25px;"
						value="%{object.tips}" />
					<div id="wxts_div" class="alert_title_div" style="top:190px;">
					<font color="red" size="2">温馨提示：提示内容。</font>
					注：如需换行请将括号内的内容复制入输入框(&lt;br&gt;&amp;#12288;&amp;#12288;&amp;#12288;&amp;#12288;&amp;#12288;&amp;#12288;&amp;#12288;)
					</div>
				</td>
			</tr>
<%-- <tr>
				<td class="addTd" >风险类别</td>
				<td align="left" colspan="3"><input type="hidden" id="riskId" name="riskId"
					value="${object.riskInfo.riskid}"> 
					<input type="text" id="riskdes" name="object.riskInfo.riskdes"
					value="${object.riskInfo.riskdes}" style="width: 400px;"
					readonly="readonly"> 
					<input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/riskInfo!listSelect.do?riskid=${object.riskInfo.riskid}','powerWindow',null);"
					value="选择"> 
					<input type="button" class='btn' name="powerBtn" onClick="doclear();" value="清除"></td>
			</tr> --%>
			<tr>
				<td class="addTd" >节点操作配置</td>
				<td align="left" colspan="3">			
				<input type="checkbox"  name="canDefer" value="T"
				<c:if test="${object.canDefer eq 'T' }">checked="checked"</c:if>/>是否可以暂缓
				<input type="checkbox"  name="canRollback" value="T"
				<c:if test="${object.canRollback eq 'T' }">checked="checked"</c:if>/>是否可以回退
				<input type="checkbox"  name="canClose" value="T"
				<c:if test="${object.canClose eq 'T' }">checked="checked"</c:if>/>是否可以结束流程
				<input type="checkbox"  name="hasJZ" value="T"
				<c:if test="${object.hasJZ eq 'T' }">checked="checked"</c:if>/>是否兼职|分管部门
				
                <!--短信功能开关 -->
				<c:if test="${cp:MAPVALUE('sendMSg','isopen') eq 'T'}">
				<input type="checkbox"  name="canSendMessage" value="T"
				<c:if test="${object.canSendMessage eq 'T' }">checked="checked"</c:if>/>是否可以发送短信
				</c:if>
				
				</td>
			</tr>
			
			<tr>
				<td class="addTd" >节点业务功能</td>
				<td align="left" colspan="3">			
					<%-- <input type="checkbox"  name="hasPreIdea" value="T"
					<c:if test="${object.hasPreIdea eq 'T' }">checked="checked"</c:if>/>是否引用上一步骤的办理意见 --%>
					<input type="checkbox"  name="docReadOnly" value="T"
					<c:if test="${object.docReadOnly eq 'T' }">checked="checked"</c:if> />是否只读/打印文书
					<input type="checkbox"  name="hasSq" value="T"
					<c:if test="${object.hasSq eq 'T' }">checked="checked"</c:if> />是否启动事权
					<input type="checkbox"  name="hasFw" value="T"
					<c:if test="${object.hasFw eq 'T' }">checked="checked"</c:if> />是否启动拟发文
					<input type="checkbox"  name="hasQb" value="T"
					<c:if test="${object.hasQb eq 'T' }">checked="checked"</c:if> />是否启动签报
				</td>
			</tr>
			<tr>
				<td class="addTd" >分块配置</td>
				<td align="left" colspan="3">
				<input type="checkbox"  name="hasAttention" id="hasAttention" value="T"
				<c:if test="${object.hasAttention eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否有关注<span style="vertical-align:super; font-size:10px">[1]</span>
				<input type="checkbox"  name="hasDocument"  id="hasDocument" value="T"
				<c:if test="${object.hasDocument eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否编辑文书<span style="vertical-align:super; font-size:10px">[2]</span>
				<input type="checkbox"  name="assignTeamRole" id="assignTeamRole" value="T"
				<c:if test="${object.assignTeamRole eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否设置办件角色<span style="vertical-align:super; font-size:10px">[3]</span>

				<input type="checkbox"  name="hasOrgRole" id="hasOrgRole" value="T"
				<c:if test="${object.hasOrgRole eq 'T' }">checked="checked"</c:if> onclick="doInitial();" />是否批分<span style="vertical-align:super; font-size:10px">[4]</span>
				<input type="checkbox"  name="hasStuff"  id="hasStuff" value="T"
				<c:if test="${object.hasStuff eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否有附件<span style="vertical-align:super; font-size:10px">[5]</span>
				<input type="checkbox"  name="isShowInNode" id="isShowInNode"  value="T"
				<c:if test="${object.isShowInNode eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否显示环节意见<span style="vertical-align:super; font-size:10px">[6]</span>
				<input type="checkbox"  name="assignEngineRole" id="assignEngineRole" value="T"
				<c:if test="${object.assignEngineRole eq 'T' }">checked="checked"</c:if> onclick="doInitial();"/>是否指定权限引擎角色<span style="vertical-align:super; font-size:10px">[7]</span>
		</td>
			</tr>
			<tr id="hasAttention_tr">
				<td class="addTd" >关注标签<span style="vertical-align:super; font-size:10px">[1]</span></td>
				<td align="left"><s:textfield name="attentionLabel" style="width:200px;height:25px;"
						value="%{object.attentionLabel}" /></td>			
				<td class="addTd" >人员筛选表达式</td>
				<td align="left"><s:textfield name="attentionFilter"  value="%{object.attentionFilter}" style="width:200px;height:25px;"/></td>
			</tr>
		
			<tr id="hasDocument_tr_1">
				<td class="addTd" >文书标签<span style="vertical-align:super; font-size:10px">[2]</span></td>
				<td align="left"><s:textfield name="documentLabel" style="width:200px;height:25px;"
						value="%{object.documentLabel}" /></td>	
				<td class="addTd" >文书类别</td>
				<td align="left">
						<select id="documentType" name="documentType" style="width:200px;height:25px;">
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('TEMPLATE_TYPE')}">
								<option value="${row.key}" label="${row.value}"  <c:if test="${object.documentType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" /></option>
							</c:forEach>
							</select>
						</td>
			</tr>

			<tr id="hasDocument_tr_3">
				<td class="addTd" >文书模板</td>
				<td align="left" colspan="3"><s:hidden id="documentTemplateIds" name="documentTemplateIds"  value="%{object.documentTemplateIds}" />
						<textarea id="documentTemplateNames" name="documentTemplateNames" cols="80" readonly="true"
						rows="2" style="width: 400px;;height:40px;">${object.documentTemplateNames}</textarea>
			<input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/templateFile!listSelect.do','powerWindow',null);"
					value="选择">
					<input type="button" class='btn' name="powerBtn" onClick="clearTemplate();" value="清除"></td>
				</td>
			</tr>			
		
			<tr id="assignTeamRole_tr_1">
				<td class="addTd" >办件角色<span style="vertical-align:super; font-size:10px">[3]</span></td>
				<td align="left">
					<select name="teamRoleCode" style="width:200px;height:25px;">
								 	<option value=""></option>
									<c:forEach var="row" items="${cp:DICTIONARY('WFTeamRole')}">
										<option value="${row.key}" <c:if test="${object.teamRoleCode eq row.key}"> selected = "selected" </c:if>>
										<c:out value="${row.value}" />(${row.key})</option>
									</c:forEach>
					</select>
				</td>		
			<td class="addTd" >办件角色标签</td>
				<td align="left"><s:textfield name="teamRoleName" style="width:200px;height:25px;"
						value="%{object.teamRoleName}" /></td>
			</tr>		

		
			<tr id="assignTeamRole_tr_3">				
				<td class="addTd" >办件角色筛选式</td>
				<td align="left" colspan="3"><s:textfield name="teamRoleFilter" value="%{object.teamRoleFilter}" style="width:200px;height:25px;"/></td>
			</tr>
			
			<tr id="xbOrgRole_tr_1">
				<td class="addTd" >主办单位角色<span style="vertical-align:super; font-size:10px">[4]</span></td>
				<td align="left">
					<select name="zbOrgRoleCode" style="width:200px;height:25px;">
					 	<option value=""></option>
						<c:forEach var="row" items="${cp:DICTIONARY('IODOC_HQ')}">
							<option value="${row.key}" <c:if test="${object.zbOrgRoleCode eq row.key}"> selected = "selected" </c:if>>
							<c:out value="${row.value}" />(${row.key})</option>
						</c:forEach>
					</select>		
				</td>
				<td class="addTd" >协办单位角色</td>
				<td align="left">
					<select name="xbOrgRoleCode" style="width:200px;height:25px;">
					 	<option value=""></option>
						<c:forEach var="row" items="${cp:DICTIONARY('IODOC_HQ')}">
							<option value="${row.key}" <c:if test="${object.xbOrgRoleCode eq row.key}"> selected = "selected" </c:if>>
							<c:out value="${row.value}" />(${row.key})</option>
						</c:forEach>
					</select>		
				</td>	
			</tr>
		
			<tr id="xbOrgRole_tr_2">
			<td class="addTd" >协办单位标签</td>
				<td align="left"><s:textfield name="xbOrgRoleName" style="width:200px;height:25px;"
						value="%{object.xbOrgRoleName}" /></td>						
				<td class="addTd" >协办单位筛选式</td>
				<td align="left" ><s:textfield name="xbOrgRoleFilter" value="%{object.xbOrgRoleFilter}" style="width:200px;height:25px;"/></td>
			</tr>
		<tr id="hasStuff_tr">
				<td class="addTd">附件材料分组<span style="vertical-align:super; font-size:10px">[5]</span></td>
				<td align="left" colspan="3"><s:textfield name="stuffGroupName" style="width:400px;height:25px;" id="stuffGroupName" value="%{stuffGroupName}"
						readonly="true"/>
						<s:hidden name="stuffGroupId" value="%{object.stuffGroupId}" id="stuffGroupId"> </s:hidden><input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/generalOperator!selectstuffGroup.do',null,null);"
					value="选择"> </td>				
			</tr>
			
			<tr class="isShowInNode_tr">
				<td class="addTd" >环节意见标签<span style="vertical-align:super; font-size:10px">[6]</span></td>
				<td align="left"><s:textfield name="nodeLabel" style="width:200px;height:25px;"
						value="%{object.nodeLabel}" /></td>			
				<td class="addTd" >环节意见排序</td>
				<td align="left"><s:textfield name="nodeOrder"  value="%{object.nodeOrder}" style="width:200px;height:25px;"/></td>
			</tr>
			<tr class="isShowInNode_tr">
				<td class="addTd" >环节意见模板<span style="vertical-align:super; font-size:10px">[6]</span>
				<img class="wxtx_img" onmousemove="titleShow('hjmb')"  src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" 
				/>
				
				</td>
				<td class="alert_title" style="position:relative" align="left" colspan="3"><textarea id="ideaModule"name="ideaModule" style="width: 100%; height: 50px;">${object.ideaModule}</textarea>
				<div id='alert_title_div' class="alert_title_div" style="bottom :100px;" >
				<span >type:意见是否取单条（sigle）|DATE_PATTERN：格式化日期（yyyy-MM-dd HH:mm）|{username}:用户名|{transdate}：日期|{transcontent}：意见|GeneralOperatorAction.getAllIdeaInfoByModuleCode()</span><br>
			    <span ><font color="red" size="2">示例（默认值）：</font><br>&nbsp;
			    <c:out value='{"type":"","DATE_PATTERN":"yyyy-MM-dd HH:mm","dataModule":"{transcontent} </br> <div class="r" align="right"> {username} {transdate}</div>"}' escapeXml="true" /> 意见 办理人员 时间
			    <br>
			    <c:out value='{"type":"sigle","DATE_PATTERN":"yyyy-MM-dd","dataModule":"{username}  {transcontent} {transdate}"}' escapeXml="true" />办理人员  意见  时间 
			  
			    </span>
			    </div>
			     </td>			
				
			</tr>
					<tr id="assignEngineRole_tr_1">
				<td class="addTd" width="130">权限引擎角色<span style="vertical-align:super; font-size:10px">[7]</span></td>
					<td align="left">
						<select name="engineRoleCode" style="width:180px">
						 	<option value=""></option>
							<c:forEach var="row" items="${cp:DICTIONARY('ENGINE_ROLE')}">
								<option value="${row.key}" <c:if test="${object.engineRoleCode eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />U(${row.key})</option>
							</c:forEach>
						</select><font color="red" size="2">温馨提示：工作流界面的权限引擎表达式写上U(XXX)即可。</font>
					</td>
			</tr>
			<tr id="assignEngineRole_tr_2">
				<td class="addTd" width="130">权限引擎角色标签</td>
				<td align="left"><s:textfield name="engineRoleName" size="40"
						value="%{object.engineRoleName}" /></td>
			</tr>


			<tr id="assignEngineRole_tr_3">
				<td class="addTd" width="130">权限引擎角色筛选表达式</td>
				<td align="left" colspan="3"><s:textfield name="engineRoleFilter" value="%{object.engineRoleFilter}" size="40"/></td>
			</tr>
		</table>
		<div class="formButton">
				<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save"  />
				<c:if test="${isAutoClose ne 'T' }"><input type="button"  value="返回" Class="btn" onclick="window.history.back()" /></c:if><c:if test="${isAutoClose eq 'T' }"><input type="button"  value="关闭" Class="btn" onclick="window.close();" /></c:if>
			</div>
	</s:form>
	</fieldset>
</body>
<script type="text/javascript">

$(function(){
	//是否显示环节意见
	if(_get('isShowInNode').checked){
		
		$(".isShowInNode_tr").show();
	}else{
		$(".isShowInNode_tr").hide();
	}	});

 $("#generalModuleParamForm_save").click(function(){
	 window.name = "curWindow";
	 if('T'==$("#isAutoClose").val()){
		 window.returnValue = "T";
		 window.close();	 
	 }
	
 });
 
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	function docheck() {
		if (trim(_get('nodeName').value).length == 0) {
			alert("环节名称不可为空！");
			_get('nodeName').focus();
			return false;
		}	
		if (trim(_get('moduleCode').value).length == 0) {
			alert("模块代码不可为空！");
			_get('moduleCode').focus();
			return false;
		}
		return true;
	}

	var _get = function(id) {
		return document.getElementById(id);
	};

	//字符空格处理
	var trim = function(str) {
		return str.replace(/^\s+|\s+$/g, "");
	};

	function doInitial() {
	
		var hasAttention_tr = _get('hasAttention_tr');
		var hasDocument_tr_1 = _get('hasDocument_tr_1');
		var hasDocument_tr_3 = _get('hasDocument_tr_3');
		
		var assignTeamRole_tr_1 = _get('assignTeamRole_tr_1');
		var assignTeamRole_tr_3 = _get('assignTeamRole_tr_3');
		
		var xbOrgRole_tr_1 = _get('xbOrgRole_tr_1');
		var xbOrgRole_tr_2 = _get('xbOrgRole_tr_2');
		//权限引擎
		var assignEngineRole_tr_1 = _get('assignEngineRole_tr_1');
		var assignEngineRole_tr_2 = _get('assignEngineRole_tr_2');
		var assignEngineRole_tr_3 = _get('assignEngineRole_tr_3');
		
		var hasStuff_tr=_get('hasStuff_tr');
		//关注
		if(_get('hasAttention').checked){
			hasAttention_tr.style.display = "table-row";	
		}else{
			hasAttention_tr.style.display = "none";
		}
		//文书
		if(_get('hasDocument').checked){
			hasDocument_tr_1.style.display = "table-row";
			hasDocument_tr_3.style.display = "table-row";
		}else{
			hasDocument_tr_1.style.display = "none";
			hasDocument_tr_3.style.display = "none";
		}
		//办件角色
		if(_get('assignTeamRole').checked){
			assignTeamRole_tr_1.style.display = "table-row";
			assignTeamRole_tr_3.style.display = "table-row";
		}else{
			assignTeamRole_tr_1.style.display = "none";
			assignTeamRole_tr_3.style.display = "none";
		}	
		
		//办件角色
		if(_get('hasOrgRole').checked){
			xbOrgRole_tr_1.style.display = "table-row";
			xbOrgRole_tr_2.style.display = "table-row";	
		}else{
			xbOrgRole_tr_1.style.display = "none";
			xbOrgRole_tr_2.style.display = "none";
		}		
		
		//附件材料
		if(_get('hasStuff').checked){
			hasStuff_tr.style.display = "table-row";
		}else{
			hasStuff_tr.style.display = "none";
		}
		
		//是否显示环节意见
		if(_get('isShowInNode').checked){
			
			$(".isShowInNode_tr").show();
		}else{
			$(".isShowInNode_tr").hide();
		}
		
		//权限引擎角色
		if(_get('assignEngineRole').checked){
			assignEngineRole_tr_1.style.display = "table-row";
			assignEngineRole_tr_2.style.display = "table-row";
			assignEngineRole_tr_3.style.display = "table-row";
		}else{
			assignEngineRole_tr_1.style.display = "none";
			assignEngineRole_tr_2.style.display = "none";
			assignEngineRole_tr_3.style.display = "none";
		}
	}
	
	//置空风险点信息
	function doclear(){
		_get('riskId').value=0;
		_get('riskdes').value='';
	}
	
	function clearTemplate(){
		_get('documentTemplateIds').value='';
		_get('documentTemplateNames').value='';
	}
	
	//根据值设置select中的选项       
	function _getSelectedItemLabel(objSelect) {            
	    //判断是否存在        
	    for (var i = 0; i < objSelect.options.length; i++) {        
	        if ( objSelect.options[i].selected) { 	        	
	        	document.getElementById("teamRoleName").value=objSelect.options[i].label;
	            break;        
	        }        
	    } 
	}
	
</script>
<script>
	 function titleShow(th) {
		if(th=='hjmb'){
			$('#alert_title_div').show();
		}
		if(th=='wxts'){
			$('#wxts_div').show();
		}
	} 
$(function(){
	var isFocus1=false;
	var isFocus2=false;
	$("img").mouseout (function(event){
		setTimeout(function(){
			isFocus1=$("#alert_title_div").is(":focus");  
			isFocus2=$("#wxts_div").is(":focus");
			$("#alert_title_div").mouseenter(function(){
				$("#alert_title_div").show();
				return;
			});
			$("#wxts_div").mouseenter(function(){
				$("#wxts_div").show();
				return;
			});
			if(false==isFocus1){  
				$('#alert_title_div').hide();
			}
			if(false==isFocus2){
				$('#wxts_div').hide();
			}
		}, 1500);
	});
	
	$("#alert_title_div").mouseleave(function(){
		$('#alert_title_div').hide();
	});
	/* $("#alert_title_div").mouseover(function(){
		$('#alert_title_div').show();
	}); */
	$("#wxts_div").mouseleave(function(){
		$('#wxts_div').hide();
	});
	/* $("#wxts_div").mouseover(function(){
		$('#wxts_div').show();
	}); */
})
</script>
</html>