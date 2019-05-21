<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title>办件基础信息</title>
</head>

<body>
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="djId" name="djId"  value="${djId}" />
<input type="hidden" name="nodeInstId" value="${nodeInstId}">
<input type="hidden" id="flowCode" name="flowCode"  value="${flowCode}" />
<fieldset style=" display: block; padding: 10px; margin-bottom:10px;">
	<legend><b>办件信息</b></legend>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable" >		
				<tr>
					<td class="addTd" width="130">
						办件名称
					</td>
					<td align="left">
						<s:textfield name="optBaseInfo.transaffairname" value="%{object.optBaseInfo.transaffairname}" readonly="true" style="width:100%;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						权力事项
					</td>
					<td align="left">
						<input type="hidden" id="powername" name="optBaseInfo.powername">
  						<select name="optBaseInfo.powerid" disabled="disabled" style="width:200px;" >
  							<option value="JTTXQ-201209">交通厅行政权力</option>
  							<option value="HDJXQ-201209">航道局行政权力</option>
  						</select>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						申请单位
					</td>
					<td align="left">
						<input type="hidden" name="optBaseInfo.orgcode">
  						<s:textfield name="optBaseInfo.orgname"  value="%{object.optBaseInfo.orgname}" readonly="true"  style="width:100%;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						办件摘要	
					</td>
					<td align="left">
						<s:textarea name="optBaseInfo.content"  value="%{object.optBaseInfo.content}" cols="40" rows="2" readonly="true" style="width:100%;height:40px;" />
					</td>
				</tr>
</table>
</fieldset>
</body>
</html>
