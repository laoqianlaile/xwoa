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
		<fieldset style="padding-top:6px;">
			<legend>查询条件</legend>
			<s:form action="userDef" theme="simple">			
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td width="340">用户名：<s:textfield name="s_USERNAME" value="%{#parameters['s_USERNAME']}" />	
						</td>
						<td colspan="2">
							登录名：
							<s:textfield name="s_LOGINNAME"  ></s:textfield>							
						</td>
					</tr>
					<tr>
						<td width="340">				
								主机构：<select name="s_queryByUnit">
								<option value="" selected="selected">选择主机构 
								<c:forEach var="row" items="${cp:LVB('unitcode')}">
									<option value="${row.value}"><c:out value="${row.label}"/>
									</option>
								</c:forEach>
							</select>
						</td>
						
						<td width="100">
							<s:checkbox label="包含禁用" name="s_isAll" value="#parameters['s_isAll']" fieldValue="true" />包含禁用	
						</td>
						<td>
							<s:submit method="pwdlist" cssClass="btn" value="查询" ></s:submit>
													
						</td>
						<td></td>
					</tr>
				</table>
		
			</s:form>
		</fieldset>
			<ec:table action="userDef!pwdlist.do" items="objList" var="fUserinfo"
			imagePath="${sessionScope.STYLE_PATH}/images/table/*.gif"
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"
			>
			<ec:row>
				<ec:column property="usercode1"
				title='<button name="quanxuan" class="btnLittle" target="false">全选</button>'
				sortable="false" style="text-align:center">
				<center>						
					<input type="checkbox" id="${fUserinfo.usercode}" class="ecbox" 
						value="${fUserinfo.usercode}">						
					</center>
				</ec:column>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />
			
				<ec:column property="username" title="用户名" style="text-align:center" />

				<ec:column property="loginname" title="登录名" style="text-align:center" />
				<ec:column property="Isvalid" title="状态" sortable="false" style="text-align:center">
					${USE_STATE[fUserinfo.isvalid]}
				</ec:column>
				<ec:column property="userdesc" title="用户描述" style="text-align:center"></ec:column>
				<ec:column property="Opt" width="8%" title='<button name="quanVf" class="btnLittle">重置登陆  </button>' sortable="false"
					style="text-align:center">
					<a  href="sys/userDef!resetpwd.do?object.usercode=${fUserinfo.usercode}&norejsp=1" onclick="return confirm('是否重置该用户密码?');">重置登陆</a>
				</ec:column>
			</ec:row>
		</ec:table>
		<s:form action="userDef!resetpwds.do" id="form2" namespace="/sys">
		<s:hidden name="resetUsers" id="resetUsers" /> 		
		</s:form>
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
	$("[name='quanVf']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		
		for (var i = 0; i < items.length; i++) {
		     // 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=''){
			$("#resetUsers").attr("value",vfstring);
			if(confirm("是否重置所有被选中的用户密码")){
			 document.form2.submit();  
			}
		}
		
	});
	$("[name='quanVfRTX']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		
		for (var i = 0; i < items.length; i++) {
		     // 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=''){
			$("#resetUsersRtx").attr("value",vfstring);
			if(confirm("是否重置所有被选中的用户RTX密码")){
			 document.form3.submit();  
			}
		}
		
	});
</script>