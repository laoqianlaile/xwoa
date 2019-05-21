<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
<!-- 	<p class="ctitle"> -->
<!-- 		编辑机构部门管理信息表 -->
<!-- 	</p> -->

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="powerOrgInfo" method="post" namespace="/powerbase"
		id="powerOrgInfoForm">		
		
		<div >
	<fieldset style="display: block; padding: 10px;">
			<legend style="margin-bottom:5px;">
				 <b>权力事项信息</b>
			</legend>
			<input type="button"  value="返回" Class="btn" onclick="window.location.href='${pageContext.request.contextPath}/powerbase/vsuppowerinusing!list.do'" />
			<input type="button"  value="新增" Class="btn" onclick="doBuiltOrg('${object.itemId}');" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
					<td class="addTd">
						权力编码
					</td>
					<td align="left">
					<s:property value="%{object.itemId}"/>
					</td>
				</tr>
			<tr>
					<td class="addTd">
						权力名称
					</td>
					<td align="left">
					<s:property value="%{object.itemName}"/>
					</td>
				</tr>	
				</table>
			
			
			
			</fieldset>
	<fieldset style="display: block; padding: 10px;">
			<legend>
				 <b>权力、部门、流程关联信息</b>
			</legend>		
		<ec:table action="powerbase/powerOrgInfo!listOrgList.do" items="selectList" var="vpowerOrgInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false">
			<ec:row>
				<ec:column property="unitName" title="执行部门" style="text-align:center" sortable="false"/>
				<ec:column property="wfName" title="流程名称" style="text-align:center" sortable="false"/>
				<ec:column property="setime" title="设置时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${vpowerOrgInfo.setime}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>		
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<a href='powerbase/powerOrgInfo!editInfo.do?itemId=${vpowerOrgInfo.itemId}&unitcode=${vpowerOrgInfo.unitcode}'>编辑</a>
					<a href='powerbase/powerOrgInfo!delete.do?itemId=${vpowerOrgInfo.itemId}&unitcode=${vpowerOrgInfo.unitcode}' 
							onclick="return confirm('是否确定此操作?');">删除</a>
				</ec:column>
			</ec:row>
		</ec:table>
</fieldset>
</div>
	</s:form>
	</body>
	<script type="text/javascript">
	
	function doBuiltOrg(itemid){
		window.location.href="powerbase/powerOrgInfo!builtOrg.do?itemId="+itemid;
		
	}
	</script>
	</html>