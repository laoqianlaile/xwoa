<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title>风险点信息</title>
</head>

<body>
<input type="hidden" id="risktype" name="risktype"  value="${moduleParam.riskInfo.risktype}" />
<input type="hidden" id="riskdesc" name="riskdesc"  value="${moduleParam.riskInfo.riskdes}" />


<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
					<tr>
					<td class="addTd" width="130">
						风险类别
					</td>
					<td align="left">
						    ${cp:MAPVALUE("RISKTYPE",moduleParam.riskInfo.risktype)}
					</td>
				</tr>	
				<tr>
					<td class="addTd" width="130">
						风险描述
					</td>
					<td align="left">${moduleParam.riskInfo.riskdes}
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						风险内控手段与结果	
					</td>
					<td align="left">
						<s:textarea name="riskresult"  value="%{moduleParam.riskInfo.riskdeal}" style="width:100%;height:40px;" />
					</td>
				</tr>			
</table>

</body>
</html>
