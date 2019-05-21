<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>人员列表</title>
		 <script type="text/javascript" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>" charset="utf-8"></script>
        <link href="${pageContext.request.contextPath}/scripts/colorbox/colorbox.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/centit.js"/>" charset="utf-8"></script>
	
	<script type="text/javascript">
		var path="${pageContext.request.contextPath}";				
	 $(document).ready(function() {  
		 centit.ajax.initAjax({urlPrefix:path});  
		 }); 
	</script>
	</head>
	<body>
		<fieldset style="padding-top:6px;"  class="search">
			<legend>查询条件</legend>
			<s:form action="userDef" theme="simple">			
				<table cellpadding="0" cellspacing="0" align="center" >
					<tr>
						<td >用户名：</td>
						<td ><s:textfield name="s_USERNAME" value="%{#parameters['s_USERNAME']}" />	
						</td>
						<td >登录名：</td>
						<td><s:textfield name="s_LOGINNAME"  ></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="addTd">所属机构：</td>
						<td>
						<select name="s_byUnderUnit" style="width:auto">
						<c:forEach var="row" items="${unitList}">
							<option value="<c:out value='${row.unitcode}'/>"
								<c:if test="${row.unitcode==param.s_byUnderUnit}">selected="selected"</c:if>>
							<c:out value="${row.unitname}" /></option>
						</c:forEach>
						</select>
						</td><td colspan="2">
						<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构	
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:checkbox label="包含禁用" name="s_isAll" value="#parameters['s_isAll']" fieldValue="true" />包含禁用			
						</td>
					</tr>
					
					<tr class="searchButton">
						<td colspan="4">
							<s:submit method="listUserInfo" cssClass="btn" value="查询" ></s:submit>
							<s:submit method="builtUnderUnit" cssClass="btn" value="新增" ></s:submit>
						</td>
					
					</tr>
					
				</table>
		
			</s:form>
		</fieldset>
	<ec:table action="userDef!listUserInfo.do" items="objList" var="fUserinfo"
			imagePath="${sessionScope.STYLE_PATH}/images/table/*.gif"
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"
			>
			
			<c:if test="${fUserinfo.usercode ne '99999999'}">
			<ec:row>
			<ec:column property="user"
				title='<button name="quanxuan" target="false" >全选</button><button name="czpw" id="czpw" target="false" >重置密码</button>'
				sortable="false"  style="width:100px">		
					<input type="checkbox" id="${fUserinfo.usercode}" class="ecbox" 
						value="${fUserinfo.usercode}">
			</ec:column>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />			
				<ec:column property="username" title="用户名" style="text-align:center" />

				<ec:column property="loginname" title="登录名" style="text-align:center" />
				<ec:column property="Isvalid" title="状态" sortable="false" style="text-align:center">
					${USE_STATE[fUserinfo.isvalid]}
				</ec:column>
				<ec:column property="userdesc" title="用户描述" style="text-align:center"></ec:column> 
				<ec:column property="userOpt" title="操作" sortable="false"
					style="text-align:center">
					<a href='userDef!viewUnderUnit.do?usercode=${fUserinfo.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'>查看明细</a>
					<a href='userDef!editUnderUnit.do?usercode=${fUserinfo.usercode}&underUnit=T'>编辑</a>
					<c:if test="${fUserinfo.isvalid eq 'F'}">
						<a href='userDef!renew.do?usercode=${fUserinfo.usercode}&underUnit=T'>启用</a>
					</c:if>
					<c:if test="${fUserinfo.isvalid eq 'T' && fUserinfo.loginname != 'admin'}">
					
						<a href='userDef!delete.do?usercode=${fUserinfo.usercode}&underUnit=T'
							onclick='return confirm("是否禁用该用户?");'>禁用</a>
					</c:if>
					<c:if test="${fUserinfo.addrbookid gt 0}">
						<a href='#' onclick="addressBook.showDetail('${fUserinfo.addrbookid}')">查看通讯信息</a>
					</c:if>
				</ec:column>

			</ec:row>
			</c:if>
			
		</ec:table> 

	</body>
</html>
<script type="text/javascript">
	
	$("[name='quanxuan']").bind('click',function(){
		if($("[name='quanxuan']").attr("target")=="false"){
		$("[name='quanxuan']").attr("target","true");
		$("input[class='ecbox']").attr("checked","true"); 
		}
		else{
			$("[name='quanxuan']").attr("target","false");
			$("input[class='ecbox']").removeAttr("checked");
		}	
	});

	$("#czpw").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		
		for (var i = 0; i < items.length; i++) {
		     // 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
		if(confirm("是否重置选中的用户的密码")){
			
			//alert(vfstring);
			
			$.ajax({
				   type : 'post',
				   url: "<%=request.getContextPath()%>/sys/userDef!resetpwds.do",
				   dataType:'text',
				   async: false,
				   data : {
					   resetUsers:  vfstring
				   },
				   success: function(data){
				         //  alert(data);
				           location.href="<%=request.getContextPath()%>/sys/userDef!listUserInfo.do?s_byUnderUnit=thisunit";
				   }
		});
		}
		
		}
		else{
			alert("没有选中的用户");
		}
			
	}); 
	
</script>

