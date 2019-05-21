<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		<title>
			<s:text name="powerUserInfo.list.title" />
		</title>
		
		<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"  type="text/javascript" ></script>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="powerUserInfo" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
                <input type="hidden" name="itemId" value="${object.itemId}"/>
				<tr>
					<td class="addTd">登录名：</td>
					<td><input type="text" class="span2" name="s_loginname"
						value="${s_loginname }" /></td>

					<td class="addTd">用户名：</td>
					<td><input type="text" class="span2" name="s_username" value="${s_username }" /></td>
				</tr>
				

				<tr >
				    <td class="addTd">所属单位：</td>
				    <td>
				  <select name="s_queryByUnit"  style="width: 195px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.unitcode}"
									<c:if test="${s_queryByUnit eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select>			
							</td>
				</tr>
				
				<tr class="searchButton">	
				    <td colspan="4">
						<s:submit method="userSelectList"  value="查询" cssClass="btn"/>
<%-- 						<s:submit method="built" cssClass="btn btnWide" value="添加事权关联" /> --%>
                         <input type="button" name="czpw" Class="btn" value="提交" />
					</td>
				</tr>
						
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerbase/powerUserInfo!userSelectList.do" items="userList" var="fUserinfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"> 
			<ec:row>

				<c:set var="tusercode">用户代码</c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" />

				<c:set var="tusername">用户名</c:set>	
				<ec:column property="username" title="${tusername}" style="text-align:center" />


				<c:set var="tloginname">登录名</c:set>	
				<ec:column property="loginname" title="${tloginname}" style="text-align:center" />

				<c:set var="tuserdesc">用户描述</c:set>	
				<ec:column property="userdesc" title="${tuserdesc}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				  sortable="false"  style="width:8%">		
					<input type="checkbox" id="${fUserinfo.usercode}" class="chk_one" 
						value="${fUserinfo.usercode}">
			
			
			</ec:column>

			</ec:row>
		</ec:table>

</body>
<script type="text/javascript">
/* var parentDocument = window.opener.document;//获取父页面对象
var ids = parentDocument.getElementById('usercodes').value;

if(ids != null && ids != ''){
	var idArr = ids.split(',');
	for(var i = 0 ; i< idArr.length; i++ ){
		$("#"+idArr[i]).attr("checked","true"); 
	}
} */

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
		var users = $('[class = "chk_one"]:checkbox:checked');
		var itemId = "${object.itemId}";
		for (var i = 0; i < users.length; i++) {
		     vfstring = (vfstring + users.get(i).value + ','); 
		}
		if(vfstring!=""){
		if(confirm("是否确定提交?")){
			$.ajax({
				   type : 'post',
				   url: "<%=request.getContextPath()%>/powerbase/powerUserInfo!infoSaveByItemId.do",
				   dataType:'text',
				   async: false,
				   data : {
					   resetUsers:  vfstring,
					   itemId : itemId
				   },
				   success: function(data){
					   opener.location.href="<%=request.getContextPath()%>/powerbase/powerUserInfo!userList.do?itemId="+itemId;
						window.close();
				   }
		});
		}
		
		}
		else{
			alert("请选中人员再提交 !");
			
		}
			
	}); 
</script>
</html>

