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
				
					<tr>
						<td colspan="4">发文代字:</td>
						<td><s:textfield name="s_descript" style="width:180px;height:25px;"/> </td>
					</tr>	
					<tr class="searchButton">				
						<td colspan="4">
							<s:submit method="selectZWHList"  key="opt.btn.query" cssClass="btn"/>
							<input type="button" name="czpw" Class="btn" value="提交" />
						</td>
					</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="dispatchdoc/oaFwdepmange!selectZWHList.do" items="ZWHList"
		 var="detail"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
		<ec:row>
			   <ec:column property="datavalue" title="发文代字"  style="text-align:center" />
			   

				<%-- <ec:column property="datatag" title="置文号规则" sortable="false" style="text-align:center">
				<input type="hidden" value="${detail.datatag}"/> 
				<c:choose>
						<c:when test="${fn:length(detail.datatag) > 16}">
							<c:out
								value="${fn:substring(detail.datatag, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${detail.datatag}" />
						</c:otherwise>
					</c:choose>
				</ec:column> --%>
				<ec:column property="none" title="文号规则示例"  style="text-align:center" >
				${cp:MAPDESC('ZWHGZ',detail.datadesc)}
				</ec:column>
				
				<%-- <ec:column property="datadesc" title="文号描述" sortable="false" style="text-align:center">
				<input type="hidden" value="${detail.datadesc}"/> 
				<c:choose>
						<c:when test="${fn:length(detail.datadesc) > 16}">
							<c:out
								value="${fn:substring(detail.datadesc, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${detail.datadesc}" />
						</c:otherwise>
					</c:choose>
				</ec:column> --%>
				
				<ec:column property="opt" title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
					  sortable="false"  style="width:8%">		
						<input type="checkbox" id="${detail.datadesc}" class="chk_one" 
							value="${detail.datadesc}">
				
				
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
				   url: "<%=request.getContextPath()%>/dispatchdoc/oaFwdepmange!saveSelectZWHList.do",
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
