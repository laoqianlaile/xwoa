<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<script type="text/javascript" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>" charset="utf-8"></script>
        <link href="${pageContext.request.contextPath}/scripts/colorbox/colorbox.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/centit.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/jquery-ui/jquery-ui-1.9.2.custom.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/opendiv_Win.js"/>" charset="utf-8"></script>
		
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function() {
	$('#sub').live('click',function(){
		if($("#orgName").attr("value")=="")
			$("#s_queryByUnit").attr("value","");
		$("#userDefForm").attr("action","dictionary!listUserUnits.do?cdtbnm=${cdtbnm}&datacode=${datacode}");
		$("#userDefForm").submit();
	});
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
		var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
		if(getID("shadow")){
			$("#shadow").show();
			$("#boxContent").show();
		}else{
			//alert(menuList);
			$("body").append(shadow);
			$("#shadow").height(document.body.scrollHeight);
			setTimeout(function(){
				menuDisplay(menuList,"1");	
			},0);
		};
		$("#lists span").live('click',function(){
			var $this = $(this);
			bindEvent($("#orgName"),$("#s_queryByUnit"),$this);
			$("#lists span").die("click");
		});
	});
});
</script>
</head>

<body class="sub-flow">
<fieldset style="padding:10px;">
    <legend class="ctitle" style="width:auto;margin-bottom:10px;">用户信息</legend>
	<fieldset style="padding-top:6px;">
			<legend>查询条件</legend>
			<s:form action="dictionary" theme="simple" id="userDefForm">			
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td width="300">用户名：<s:textfield name="s_username" value="%{#parameters['s_username']}" />	
						</td>
						<td width="300">				
								机构：
							<input type="hidden" id="s_queryByUnit" name="s_unitcode" value="${param.s_unitcode }"> 
							<input type="text" id="orgName" name="orgName" value="${cp:MAPVALUE('unitcode',param.s_unitcode)}">
						</td>
						<td width="300">
						行政角色：
                		<select name="s_userrank" style="width:140px;">
                		<option value="">--请选择--</option>
                    	<c:forEach var="row" items="${cp:LVB('RankType')}">
                        <option value="<c:out value='${row.value}'/>"
                                <c:if test="${row.value==param.s_userrank}">selected="selected"</c:if>>
                            <c:out value="${row.label}"/>
                        </option>
                   		</c:forEach>
               			</select>
            			</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="查询" class="btn" id="sub">
							<a href="userDef!addUserUnit.do?cdtbnm=${cdtbnm}&datacode=${datacode }" class="btnA"><span class="btn">添加用户</span></a>
							<a href="dictionary!view.do?cdtbnm=${cdtbnm}" class="btnA"><span class="btn">返回</span></a>	
						</td>
					</tr>
				</table>
		
			</s:form>
		</fieldset>
	
	<ec:table items="userUnits_station" var="fUserunit"  action="dictionary!listUserUnits.do?code=${datacode }" sortable="false" 
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" 
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit" >
		<ec:row>
			<ec:column property="usercode" title="用户名" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('usercode',fUserunit.usercode)}" />
			</ec:column>
			<ec:column property="unitname" title="机构名" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('unitcode',fUserunit.unitcode)}" />
			</ec:column>
			<ec:column property="userstation" title="用户岗位" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('StationType',fUserunit.userstation)}" />
			</ec:column>
			<ec:column property="userrank" title="行政职务" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('RankType',fUserunit.userrank)}" />
			</ec:column>
			<ec:column property="isprimary" title="是否主单位" sortable="false" style="text-align:center" >
				<c:out value="${YES_NO[fUserunit.isprimary]}" />
			</ec:column>
			<ec:column property="rankmemo" title="授权说明" sortable="false" style="text-align:center" />
			<ec:column property="unitopt" title="操作" sortable="false"	 >
				<a href='userDef!editUserUnit.do?userUnit.usercode=${fUserunit.usercode}&userUnit.unitcode=${fUserunit.unitcode}&userUnit.userstation=${fUserunit.userstation}&userUnit.userrank=${fUserunit.userrank}&cdtbnm=${cdtbnm}&datacode=${datacode }'>
					编辑
				</a>
				<a href='userDef!deleteUserUnit.do?userUnit.usercode=${fUserunit.usercode}&userUnit.unitcode=${fUserunit.unitcode}&userUnit.userstation=${fUserunit.userstation}&userUnit.userrank=${fUserunit.userrank}&cdtbnm=${cdtbnm}&datacode=${datacode }'
					onclick='return confirm("是否删除此条数据");'>
					删除
				</a>
			</ec:column>
		
		</ec:row>
	</ec:table> 
</fieldset>
</body>