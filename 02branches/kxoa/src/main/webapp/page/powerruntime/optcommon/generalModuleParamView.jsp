<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="generalModuleParam.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="generalModuleParam.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>
<s:form action="generalModuleParam" method="post"
		namespace="/powerruntime" id="generalModuleParamForm">
<s:submit name="back" cssClass="btn" key="opt.btn.back" />

<p>	
	
<table width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  
				<tr>
					<td class="TDTITLE">模块代码
					</td>
					<td align="left">
						<s:property value="%{moduleCode}" />
					</td>
			
					<td class="TDTITLE">结果标签
					</td>
					<td align="left">
						<s:property value="%{ideaLabel}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">结果代码
					</td>
					<td align="left">
						<s:property value="%{ideaCatalog}" />
					</td>
				
					<td class="TDTITLE">内容标签
					</td>
					<td align="left">
						<s:property value="%{ideaContent}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否有关注
					</td>
					<td align="left">
						<s:property value="%{hasAttention}" />
					</td>
			
					<td class="TDTITLE">关注标签
					</td>
					<td align="left">
						<s:property value="%{attentionLabel}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">人员筛选表达式
					</td>
					<td align="left">
						<s:property value="%{attentionFilter}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否有附件
					</td>
					<td align="left">
						<s:property value="%{hasStuff}" />
					</td>
				
					<td class="TDTITLE">附件材料分组
					</td>
					<td align="left">
						<s:property value="%{stuffGroupId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否文书
					</td>
					<td align="left">
						<s:property value="%{hasDocument}" />
					</td>
			
					<td class="TDTITLE">文书标签
					</td>
					<td align="left">
						<s:property value="%{documentLabel}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">文书类别
					</td>
					<td align="left">
						<s:property value="%{documentType}" />
					</td>
			
					<td class="TDTITLE">文书模板
					</td>
					<td align="left">
						<s:property value="%{documentTemplate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否可以暂缓
					</td>
					<td align="left">
						<s:property value="%{canDefer}" />
					</td>
				
					<td class="TDTITLE">是否可以回退
					</td>
					<td align="left">
						<s:property value="%{canRollback}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否可以结束流程
					</td>
					<td align="left">
						<s:property value="%{canClose}" />
					</td>
				
					<td class="TDTITLE">风险类别
					</td>
					<td align="left">
						<s:property value="%{objeck.riskInfo.riskdes}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">是否指定办件角色
					</td>
					<td align="left">
						<s:property value="%{assignTeamRole}" />
					</td>
			
					<td class="TDTITLE">办件角色代码
					</td>
					<td align="left">
						<s:property value="%{teamRoleCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">办件角色名称
					</td>
					<td align="left">
						<s:property value="%{teamRoleName}" />
					</td>
			
					<td class="TDTITLE">办件角色筛选表达式
					</td>
					<td align="left">
						<s:property value="%{teamRoleFilter}" />
					</td>
				</tr>	

</table>

</s:form>

</body>
</html>
