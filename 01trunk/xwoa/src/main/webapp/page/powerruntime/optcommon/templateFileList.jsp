<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			模版管理
		</title>	
				<script language="javascript">
function ConfirmDel(FileUrl){
	if (confirm('是否确定删除该模板！')){
		location.href=FileUrl;
	}
}
</script>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				查询条件
			</legend>
			
			<s:form action="templateFile" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td>模版编号:</td>
						<td><s:textfield name="s_recordId" style="width:180px;height:25px;" value="%{#parameters.s_recordId}"/> </td>
						<td>模版名称:</td>
						<td><s:textfield name="s_descript" style="width:180px;height:25px;" value="%{#parameters.s_descript}"/> </td>
					</tr>	

					<tr >
						<td>模版分类:</td>
						<td>
						<select id="tempType" name="s_tempType" style="width:180px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('TEMPLATE_TYPE')}">
								<option value="${row.key}" label="${row.value}" 
								<c:if test="${s_tempType eq row.key }">selected="selected"</c:if>>
								<c:out value="${row.value}" /></option>
							</c:forEach>
						</select>
						
						</td>
						
					</tr>	
					<tr class="searchButton">				
						<td colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
							<input type=button name="AddXslTemplate" value="新建模板" class="btn" 
							onclick="javascript:location.href='${pageContext.request.contextPath}/iWebOffice/Template/TemplateEdit.jsp?FileType=.doc&tempType=null'" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/templateFile!list.do" items="objList" var="templateFile"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<ec:column property="recordId" title="模版编号" style="text-align:center" />

				<ec:column property="descript" title="模版名称" style="text-align:center" />
				
				<ec:column property="tempType" title="模版分类" style="text-align:center">
				 ${cp:MAPVALUE("TEMPLATE_TYPE",templateFile.tempType)}
				</ec:column>
				
				<ec:column property="fileDate" title="创建日期" style="text-align:center" format="yyyy-MM-dd" cell="date" />

				<ec:column property="fileType" title="文件类型" style="text-align:center" />

				<ec:column property="opt" title="选择" sortable="false"
					style="text-align:center">
					<a href="javascript:location.href='${pageContext.request.contextPath}/iWebOffice/Template/TemplateEdit.jsp?RecordID=${templateFile.recordId}&FileType=${templateFile.fileType}&tempType=${templateFile.tempType}'" >修 改</a>
					<a href='powerruntime/templateFile!delete.do?templateId=${templateFile.templateId}' 
							onclick="return confirm('确定此项操作?');">删除</a>
					<a href="javascript:location.href='${pageContext.request.contextPath}/iWebOffice/Template/TemplateCopy.jsp?RecordID=${templateFile.recordId}&FileType=${templateFile.fileType}&tempType=${templateFile.tempType}'" >复制模版</a>
				</ec:column>

			</ec:row>
		</ec:table>
	</body>
</html>
