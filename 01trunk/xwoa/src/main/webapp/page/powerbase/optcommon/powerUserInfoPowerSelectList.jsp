<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		
<title>权力库管理</title>
</head>

<body>
	<fieldset class="search">
		<legend> 查询条件 </legend>

		<s:form action="powerUserInfo" namespace="/powerbase"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" name="usercode" value="${object.usercode}">
		    	<input type="hidden" name="itemIds" value="${itemIds}">
				<tr>
					<td class="addTd">权力编号:</td>
					<td><s:textfield name="s_itemId" style="width:180px"
							value="%{#parameters['s_itemId']}"></s:textfield></td>

					<td class="addTd">权力名称:</td>
					<td><s:textfield name="s_itemName" style="width:180px"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>
				<tr>
					<td class="addTd">行使部门:</td>
					<td><select name="s_orgId"  style="width: 195px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd">权力类型:</td>
					<td><select name="s_itemType" style="width: 180px"
						onchange="checkItemType();">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
								<option value="${row.key}"
									<c:if test="${parameters.s_itemType[0] eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr  class="searchButton">
					<td colspan="4"><s:submit method="powerSelectList" key="opt.btn.query" cssClass="btn" />
					<input type="button" name="czpw" Class="btn" value="提交" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/powerUserInfo!powerSelectList.do" items="vsuppowerinusingList"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="itemId" title="权力编号" style="text-align:center;width:15%" />

			<ec:column property="itemName" title="权力名称" style="text-align:center;width:30%" >
			<input  type="hidden"  title="${suppower.itemName}" value="${suppower.itemName}" />
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="orgId" title="行使部门" style="text-align:center;width:15%" >
					${cp:MAPVALUE("depno",suppower.orgId)}
				</ec:column>
			<ec:column property="itemType" title="权力类型" style="text-align:center;width:10%" >
					${cp:MAPVALUE("ITEM_TYPE",suppower.itemType)}
			</ec:column>
			<ec:column property="opt" title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				  sortable="false"  style="width:8%">		
					<input type="checkbox" id="${suppower.itemId}" class="chk_one" 
						value="${suppower.itemId}">
			
			
			</ec:column>

		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
/* var parentDocument = window.opener.document;//获取父页面对象
var ids = parentDocument.getElementById('itemIds').value;

if(ids != null && ids != ''){
	var idArr = ids.split(',');
	for(var i = 0 ; i< idArr.length; i++ ){
		$("#"+idArr[i]).attr("checked","true"); 
	}
}
 */
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
		var usercode = "${object.usercode}";
		for (var i = 0; i < items.length; i++) {
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
		if(confirm("是否确定提交?")){
			$.ajax({
				   type : 'post',
				   url: "<%=request.getContextPath()%>/powerbase/powerUserInfo!infoSaveByUsercode.do",
				   dataType:'text',
				   async: false,
				   data : {
					   resetItemIds:  vfstring,
					   usercode : usercode
				   },
				   success: function(data){
					opener.location.href="<%=request.getContextPath()%>/powerbase/powerUserInfo!powerList.do?usercode="+usercode;
					window.close();
					   
				   }
		});
		}
		
		}
		else{
			alert("请选中权力再提交 !");
			
		}
			
	}); 
</script>
</html>
