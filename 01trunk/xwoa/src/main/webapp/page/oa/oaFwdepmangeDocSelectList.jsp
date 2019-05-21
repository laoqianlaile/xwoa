<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		
<title>公文分级管理</title>
</head>

<body>
	<fieldset class="search">
		<legend> 查询条件 </legend>

		<s:form action="oaFwdepmange" namespace="/dispatchdoc"
			style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="s_unitcode" value="${s_unitcode}" />
			
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
							<s:submit method="selectDocList"  key="opt.btn.query" cssClass="btn"/>
							<input type="button" name="czpw" Class="btn" value="提交" />
						</td>
					</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="dispatchdoc/oaFwdepmange!selectDocList.do" items="templateList"
		 var="templateFile"  
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="recordId" title="模版编号" style="text-align:center" />

				<ec:column property="descript" title="模版名称" style="text-align:center" />
				
				<ec:column property="tempType" title="模版分类" style="text-align:center">
				 ${cp:MAPVALUE("TEMPLATE_TYPE",templateFile.tempType)}
				</ec:column>
				
				<ec:column property="fileDate" title="创建日期" style="text-align:center" format="yyyy-MM-dd" cell="date" />

				<ec:column property="fileType" title="文件类型" style="text-align:center" />
			
				<ec:column property="opt" title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
					  sortable="false"  style="width:8%">		
						<input type="checkbox" id="${templateFile.recordId}" class="chk_one" 
							value="${templateFile.recordId}">
				
				
				</ec:column>

		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">

$('#chk_all').change(
		function() {
			var $checked = $(this).prop('checked');
			$.each($('input:checkbox.chk_one'), function(index,
					checkbox) {
				$(this).prop('checked', $checked);
				if ($checked) {
					$(this).parent('span').addClass('checked');
				} else {
					$(this).parent('span')
							.removeClass('checked');
				}
			});
		});
		
		
		
	$("[name='czpw']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "chk_one"]:checkbox:checked');
		var s_unitcode = "${s_unitcode}";
		for (var i = 0; i < items.length; i++) {
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
		if(confirm("是否确定提交?")){
			$.ajax({
				   type : 'post',
				   url: "<%=request.getContextPath()%>/dispatchdoc/oaFwdepmange!saveSelectDocList.do",
				   dataType:'text',
				   async: false,
				   data : {
					   s_templates:  vfstring,
					   s_unitcodes : s_unitcode
				   },
				   success: function(data){
					opener.location.href="<%=request.getContextPath()%>/dispatchdoc/oaFwdepmange!mangeDetail.do?s_unitcode="+s_unitcode;
					window.close();
					   
				   }
		});
		}
		
		}
		else{
			alert("请选中文件模板再提交 !");
			
		}
			
	}); 
</script>
</html>
