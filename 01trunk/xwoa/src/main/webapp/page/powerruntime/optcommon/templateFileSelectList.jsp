<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			模版选择窗口
		</title>
		
		<script type="text/javascript">
			//获取父页面对象
			var parentDocument = window.opener.document;
			
			function selectTemplate(){
				var templates =  document.getElementsByName("recordId");
				
				var isChecked = false;
				var tempIds = "";
				var tempNames = "";
				for(var i = 0 ; i < templates.length; i++){
					if(templates[i].checked){
						isChecked= true;
						var tempArr = templates[i].value.split('|');
						tempIds += tempArr[0] + ",";
						tempNames +=  tempArr[1] + ",";
					}
				}
					parentDocument.getElementById('documentTemplateIds').value = tempIds.substring(0,tempIds.length-1);
					parentDocument.getElementById('documentTemplateNames').value = tempNames.substring(0,tempNames.length-1);
			}
		
		</script>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="templateFile" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td>模版名称:</td>
						<td><s:textfield name="s_descript" /> </td>
					</tr>	

					<tr >
						<td>模版分类:</td>
						<td><s:textfield name="s_tempType" /> </td>
					</tr>	

					<tr>
						<td align="center" colspan="2">
							<s:submit method="listSelect"  key="opt.btn.query" cssClass="btn"/>
							&nbsp;
							<input type="button" class="btn" value="关闭" onclick="window.close();">
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/templateFile!listSelect.do" items="templateList" var="templateFile"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<ec:column property="recordId" title="模版编号" style="text-align:center" />

				<ec:column property="descript" title="模版名称" style="text-align:center" />
				
				<ec:column property="fileDate" title="创建日期" style="text-align:center" />

				<ec:column property="tempType" title="分类" style="text-align:center" />

				<ec:column property="opt" title="选择" sortable="false"
					style="text-align:center">
					<input type="checkbox" id="recordId" name="recordId${templateFile.recordId}" onclick="selectTemplate()" value="${templateFile.recordId}|${templateFile.descript}">
				</ec:column>

			</ec:row>
		</ec:table>
		<!--<center>
				<input type="button" class="btn" value="选择" onclick="selectTemplate()">
				&nbsp;
				<input type="button" class="btn" value="关闭" onclick="window.close();">
		</center>
	--></body>
	
	<script type="text/javascript">
		var ids = parentDocument.getElementById('documentTemplateIds').value;
		if(ids != null && ids != ''){
			var idArr = ids.split(',');
			for(var i = 0 ; i< idArr.length; i++ ){
				document.getElementsByName("recordId"+idArr[i])[0].checked =true;
			}
		}
	</script>
</html>
