<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html lang="en">
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>人员列表</title>
		 <script type="text/javascript" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>" charset="utf-8"></script>
        <link href="${pageContext.request.contextPath}/scripts/colorbox/colorbox.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/centit.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/jquery-ui/jquery-ui-1.9.2.custom.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/opendiv_Win.js"/>" charset="utf-8"></script>
		
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	<style type="text/css">
		.tree .last_collapsable{background: url(../themes/blue/images/tree1.png) center 5px no-repeat !important;margin-left: 17px;margin-top: 9px;margin-right: 5px;float: left;}
		.tree .last_expandable{float:left;}
	</style>
<script type="text/javascript">
		var path="${pageContext.request.contextPath}";				
	 $(document).ready(function() {  
		 centit.ajax.initAjax({urlPrefix:path});  
		 }); 
	
	 /* $(function() {
	   $( "#dialog-modal" ).dialog({
	     height: 240,
	     modal: true
	   }); */
/* 	 }); */

	</script>
	</head>
	<body>
		<fieldset style="padding-top:6px;" class="search">
			<legend>查询条件</legend>
			<s:form action="userDef" theme="simple">			
				<table cellpadding="0" cellspacing="0" cellspacing="0" align="center">
					<tr>
						<td>用户名：</td>
						<td><s:textfield name="s_USERNAME" value="%{#parameters['s_USERNAME']}" /></td>
						<td>登录名：</td>
						<td><s:textfield name="s_LOGINNAME" value="%{#parameters['s_LOGINNAME']}"   ></s:textfield></td>
					</tr>
					<tr>
						<td>所属机构：</td>
						<td><input type="hidden" id="s_queryByUnit" name="s_queryByUnit" value="${param.s_queryByUnit }">
							<input type="text" id="orgName" name="orgName" value="${cp:MAPVALUE('unitcode',param.s_queryByUnit)}">
						</td>
						<td colspan="2">
							<s:checkbox label="包含禁用" name="s_isAll" value="#parameters['s_isAll']" fieldValue="true" />包含禁用	
						</td>
					</tr>
					<tr class="searchButton">
						<td colspan="4">
							<s:submit method="list" cssClass="btn" value="查询" ></s:submit>
							<s:submit method="built" cssClass="btn" value="新增" ></s:submit>
							<!-- <a class="btnA"> --><s:submit method="pwdlist" cssClass="btn btnWide" value="批量重置密码" ></s:submit><!-- </a> -->
							</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
			<ec:table action="userDef!list.do" items="objList" var="fUserinfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" 
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"
			>
			<ec:row>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />
			
				<ec:column property="username" title="用户名" style="text-align:center" />

				<ec:column property="loginname" title="登录名" style="text-align:center" />
				<ec:column property="Isvalid" title="状态" sortable="false" style="text-align:center">
					${USE_STATE[fUserinfo.isvalid]}
				</ec:column>
				<ec:column property="userdesc" title="用户描述" style="text-align:center"></ec:column>
				<ec:column property="userOpt" title="操作" sortable="false"
					style="text-align:center">
					<a href='userDef!view.do?usercode=${fUserinfo.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'>查看明细</a>
					<a href='userDef!edit.do?usercode=${fUserinfo.usercode}'>编辑</a>
					<c:if test="${fUserinfo.isvalid eq 'F'}">
						<a href='userDef!renew.do?usercode=${fUserinfo.usercode}'>启用</a>
					</c:if>
					<c:if test="${fUserinfo.isvalid eq 'T' && fUserinfo.usercode != '0'}">
					
						<a href='userDef!delete.do?usercode=${fUserinfo.usercode}'
							onclick='return confirm("是否禁用该用户?");'>禁用</a>
					</c:if>
					<c:if test="${fUserinfo.addrbookid gt 0}">
						<a href='#' onclick="addressBook.showDetail('${fUserinfo.addrbookid}')">查看通讯信息</a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

</body>
	<script type="text/javascript">
		function bindEvent(o1,o2,$this){
			o1.val($this.html());
			o2.val($this.attr("rel"));
			if(getID("shadow")){
				$("#shadow").hide();
				$("#boxContent").hide();
			}
		}
		$("#orgName").bind('click',function(){	
			var menuList = ${unitsJson};
			var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree' style='background-color: #DDDDDD;'>Loader</div><div id='close'>×</div></div>";
			if(getID("shadow")){
				$("#shadow").show();
				$("#boxContent").show();
			}else{
				//alert(menuList);
				$("body").append(shadow);
				$("#shadow").height(document.body.scrollHeight);
				setTimeout(function(){
					menuDisplay(menuList,"0");	
				},0);
			};
			$("#lists span").live('click',function(){
				var $this = $(this);
				bindEvent($("#orgName"),$("#s_queryByUnit"),$this);
				$("#lists span").die("click");
			});
		});

	</script>
</html>
