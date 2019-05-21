<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title>办件基础信息</title>
</head>
<script type="text/javascript">
/**
 * common window dialogs
 */
function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}

</script>
<body>
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="djId" name="djId" value="${object.djId}" />
<input type="hidden" id="optId" name="optBaseInfo.optId" value="${object.optBaseInfo.optId}" />
<input type="hidden" name="nodeInstId" value="${nodeInstId}">
<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
<fieldset style="padding:10px;display:block;margin-bottom:10px;">
<legend style="padding:4px 8px 3px;"><b>办件信息</b></legend>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable"><tr>
					<td class="addTd">
						权力事项
					</td>
					<td align="left">
						<input type="hidden" id="powerid" name="optBaseInfo.powerid" value="${object.optBaseInfo.powerid}">
						<input type="text" id="powername" name="optBaseInfo.powername" value="${object.optBaseInfo.powername}" style="width:400px;" readonly="readonly">
  						<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/supPower!listSupPower.do?itemType=XK&s_itemType=XK&s_orgId=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}','powerWindow',null);" value="选择">
					</td>
				</tr>
				<tr>
					<td class="addTd">办理部门</td>
					<td align="left">
						<input type="hidden" id="orgcode" name="optBaseInfo.orgcode" value="${object.optBaseInfo.orgcode}">
  						<s:textfield id="orgname" name="optBaseInfo.orgname" style="width:400px;" value="%{object.optBaseInfo.orgname}"  readonly="readonly"/>
					</td>
				</tr>
					<tr>
					<td class="addTd" width="130">
						办件编号
					</td>
					<td align="left">
						<s:textfield name="optBaseInfo.transAffairNo" value="%{object.optBaseInfo.transAffairNo}" style="width:400px;" />
					</td>
				</tr>	
				<tr>
					<td class="addTd" width="130">
						办件名称
					</td>
					<td align="left">
						<s:textfield name="optBaseInfo.transaffairname" value="%{object.optBaseInfo.transaffairname}" style="width:400px;" />
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						办件摘要	
					</td>
					<td align="left">
						<s:textarea name="optBaseInfo.content"  value="%{object.optBaseInfo.content}" style="width:100%;height:40px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd" width="130">
						办件查询密码
					</td>
					<td align="left">
						<s:password name="optBaseInfo.transAffairQueryKey" value="%{object.optBaseInfo.transAffairQueryKey}" style="width:400px;" />
					</td>
				</tr>
				
				<input type="hidden" id="riskType" name="optBaseInfo.riskType"  value="${object.optBaseInfo.riskType}" />
				<input type="hidden" id="riskDesc" name="optBaseInfo.riskDesc"  value="${object.optBaseInfo.riskDesc}" />
					
					<tr id="riskTypeID" style="display:none">
					<td class="addTd" width="130" >
						风险类别
					</td>
					<td align="left">
					<input type="text" name="optBaseInfo.riskType" id="riskTypes" value="${cp:MAPVALUE('RISKTYPE',object.optBaseInfo.riskType)}" style="width:80%;height:22px;" disabled="disabled"/>
					</td>
				</tr>	
				<tr id="riskDescID" style="display:none">
					<td class="addTd" width="130">
						风险描述
					</td>
					<td align="left">
					
					<input type="text" name="optBaseInfo.riskDesc" id="riskDescs" value="${object.optBaseInfo.riskDesc}" style="width:80%;height:22px;" disabled="disabled"/>
					</td>
				</tr>
				
				<tr id="resultID" style="display:none">
					<td class="addTd">
						风险内控手段与结果	
					</td>
					<td align="left">
						<input type="text" name="optBaseInfo.riskResult" id="riskResults" value="${object.optBaseInfo.riskResult}" style="width:100%;height:40px;"/>
					</td>
				</tr>					
</table>
</fieldset>
</body>
</html>
