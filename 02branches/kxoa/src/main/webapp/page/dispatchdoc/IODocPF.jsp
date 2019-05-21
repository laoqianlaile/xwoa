<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>批分</title>
<base target="_self">
<%-- <sj:head locale="zh_CN" /> --%>
<%-- <SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/scripts/scrolltop.js"></SCRIPT> --%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/lrtk.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/css/alertDiv.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/workflow/css/dtree.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/css/arrow.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/colorbox/colorbox.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.9.2.custom.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/alertDiv.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/arrow.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dtree.js"></script>
	<script type="text/javascript" src="jquery-1.6.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<s:url value="/scripts/addressBook.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<s:url value="/scripts/centit.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<s:url value="/scripts/jquery-ui/jquery-ui-1.9.2.custom.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<s:url value="/scripts/opendiv_Win.js"/>"></script>	
</head>
<body>
	<s:form action="ioDocTasksExcute" method="post" namespace="/dispatchdoc" id="ioDocTasksExcuteForm" onsubmit="return checkForm();">
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}" />
		<%-- <input type="hidden" id="djId" name="djId"  value="${djId}" />
		<input type="hidden" id="userjson" name="userjson" value="${userjson}" />
		<input type="hidden" id="unitsJson" name="unitsJson" value="${unitsJson}" /> --%>
		<input type="hidden" id="iszbfb" name="iszbfb" value="${iszbfb}" />
		<input type="hidden" id="iszb" name="iszb" value="${iszb}" />
		<input type="hidden" id="isxb" name="isxb" value="${isxb}" />
		<fieldset style="padding: 10px;">
		<legend style="padding:4px 8px 3px;"><b>批分</b></legend>
		<table border="0" cellpadding="0" cellspacing="0" id="tb" class="viewTable" style="margin-top: 20px;">
		<c:if test="${iszbfb eq 'T' }">
			<tr>
				<td class="addTd" width="13%">主办处室</td>
				<td align="left">
					<input type="hidden" id="selZbcs" name="selZbcs" value="${zbcsUnit}" />
					<select id="zbcsUnitCode" name="zbcsUnitCode">
						<option value="">---请选择---</option>
						<c:forEach items="${unitList}" var="unit">
							<option value="${unit.unitcode}" ${zbcsUnit eq unit.unitcode ? "selected = 'selected'" : ""}>
								<c:out value="${unit.unitname}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</c:if>
		<c:if test="${iszb eq 'T' }">
			<tr>
				<td class="addTd" width="13%">主办承办人</td>
					<td>
						<table cellpadding="0" cellspacing="0" class="viewTable">
							<thead>
								<tr>
									<td class="tableHeader" width="150">办件角色</td>
									<td class="tableHeader">小组成员</td>
									<td class="tableHeader" width="150">操作</td>
								</tr>
							</thead>
							<c:forEach var="team" items="${teamMap}">
								<tr>
									<td align="center">${cp:MAPVALUE("WFTeamRole",team.key)}</td>
									<td><c:forEach var="user" items="${team.value}">
							${cp:MAPVALUE("usercode",user)} <a
												href="ioDocTasksExcute!deleteWorkGroupUser.do?flowInstId=${flowInstId}&nodeInstId=${nodeInstId}&roleCode=${team.key}&userCode=${user}" ><img border="0" src="../images/close.png" ></a>
						</c:forEach></td>
									<td align="center"><a
										href="ioDocTasksExcute!deleteWorkGroup.do?flowInstId=${flowInstId}&nodeInstId=${nodeInstId}&roleCode=${team.key}">删除小组</a>
									</td>
								</tr>

							</c:forEach>
						</table>
					</td>
					<td><input type="button" class="btn" value="选择"
						onclick="selRole();"></td>
				</tr>
			</c:if>
			<c:if test="${isxb eq 'T' }">
			<tr id="xbArea">
				<td class="addTd" width="13%">协办处室</td>
				<td>
						<table cellpadding="0" cellspacing="0" class="viewTable">
							<thead>
								<tr>
									<td class="tableHeader" width="150">办件角色</td>
									<td class="tableHeader">组织机构</td>
									<td class="tableHeader" width="150">操作</td>
								</tr>
							</thead>
							<c:forEach var="team" items="${orgMap}">
							<c:if test="${iszbfb ne 'T' or 'zbcsfb' ne cp:MAPVALUE('WFTeamRole',team.key)}">
								<tr>
									<td align="center">${cp:MAPVALUE("WFTeamRole",team.key)}</td>
									<td><c:forEach var="org" items="${team.value}">${cp:MAPVALUE("unitcode",org)} 
										<a href="ioDocTasksExcute!deleteFlowOrganizeUnit.do?flowInstId=${flowInstId}&nodeInstId=${nodeInstId}&roleCode=${team.key}&orgCode=${org}&iszbfb=${iszbfb}&iszb=${iszb}&isxb=${isxb}"><img border="0" src="../images/close.png"></a>
						</c:forEach></td>
									<td align="center">
										<a href="ioDocTasksExcute!deleteFlowOrganize.do?flowInstId=${flowInstId}&nodeInstId=${nodeInstId}&roleCode=${team.key}&iszbfb=${iszbfb}&iszb=${iszb}&isxb=${isxb}">删除机构</a>
									</td>
								</tr></c:if>
							</c:forEach>
						</table>
					</td>
				<td><input type="button" class="btn" value="选择" onclick="selOrg();"></td>
			</tr>
			</c:if>		
		</table>
		</fieldset>
		
		
		<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="submitOpt" cssClass="btn" value="发 送" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn" method="suspendNodeInstance" cssClass="btn" value="暂 缓" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<s:submit id="rollBackBtn" name="rollBackBtn" method="rollBackOpt" cssClass="btn" value="回 退" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow" cssClass="btn" value="办 结" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="saveOpt" cssClass="btn" value="保 存" />
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>
	</s:form>
	<%-- <div id="alertRole" class="alert" style="width: 300px;height: 100px;">
				<s:form name="nodeForm" method="post" action="generalOperator!assignWorkGroup.do" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
					<s:hidden id="flowInstId" name="flowInstId" value="%{flowInstId}" />
					<h4><span>工作小组</span><span id="close3" class="close"  onclick="closeAlert('alertRole');" >关闭</span></h4>
						<tr>
						<td class="addTd">办件角色：</td>
						<td>
						<select name="roleCode">   
							<option value="">   
									<c:out value="-- 请选择 --"/>   
							</option>    
							<c:forEach var="row" items="${cp:DICTIONARY('WFTeamRole')}">     
									<option value="${row.key}" <c:if test="${param.roleCode==row.key}">selected="selected"</c:if>><c:out value="${row.value}" /></option>
							</c:forEach> 
						</select>
						</td>
						</tr>
					<tr>
					<br>
					<td class="addTd">添加人员：</td>
					<td>
					<input size="32" type="text" name="userName" id="userName" >
					<s:hidden name="userCode" id="userCode"  />
					</td>
					</tr>
					<br>
					<tr>
					<td align="center">
					<input type="submit" id="sub" value="确定" class="sub" />
						&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span></td></tr>
				</s:form>
		</div>
		<div id="attAlert" class="alert"
			style="width: 600px; height: 330px; overflow: hidden;">
			<input type="hidden" id="attention" name="attentionJson" value="${attentionJson}" />
			<input type="hidden" id="userjson" name="userjson" value="${userjson}" />
			<h4>
				<span>选择人员</span><span id="close2"
					style="float: right; margin-right: 8px;" class="close"
					onclick="closeAlertTemp('attAlert');">关闭</span>
			</h4>
			<div class="fix">
				<div id="leftSide"></div>
				<div id="l-r-arrow">
					<div class="lb"></div>
					<div class="rb"></div>
				</div>
				<div id="rightSide">
					<ul></ul>
				</div>
				<div id="t-b-arrow">
					<!-- <div class="tb"></div>
		            <div class="bb"></div> -->
					<b class="btns"><input id="save" class="btn" type="button"
						value="保存" /><input id="clear" class="btn" type="button"
						value="取消" style="margin-top: 5px;" /></b>
				</div>
			</div>
		</div>
		<div id="alertOrg" class="alert" style="width: 300px;height: 100px;">
				<s:form name="nodeForm" method="post" action="generalOperator!assignFlowOrganize.do" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
					<s:hidden id="flowInstId" name="flowInstId" value="%{flowInstId}" />
					<h4><span>机构成员</span><span id="close1" class="close"  onclick="closeAlert('alertOrg');" >关闭</span></h4>
						<tr>
						<td class="addTd">办件角色：</td>
						<td>
						<select name="roleCode">   
							<option value="">   
									<c:out value="-- 请选择 --"/>   
							</option>    
							<c:forEach var="row" items="${cp:DICTIONARY('WFTeamRole')}">     
									<option value="${row.key}" <c:if test="${param.roleCode==row.key}">selected="selected"</c:if>><c:out value="${row.value}" /></option>
							</c:forEach> 
						</select>
						</td>
						<!-- 
						<td>
							<input type="hidden" id="s_queryByUnit" name="s_queryByUnit" value="">
							<input type="text" id="orgName" name="orgName" value="">
						</td> -->
						</tr>
					<tr>
					<br>
					<td class="addTd">添加机构：</td>
					<td>
					<input size="32" type="text" name="orgName" id="orgName" >
					<s:hidden name="orgCode" id="orgCode"  />
					</td>
					</tr>
					<br>
					<tr>
					<td align="center">
					<input type="submit" id="sub" value="确定" class="sub" />
						&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span></td></tr>
				</s:form>
		</div> --%>
</body>
<script type="text/javascript">
	var parentInit = setInterval("doParentInit()", 200);
	
	function doParentInit() {
		if ("ok" == $("#isReady", window.parent.document).val()) {
			clearInterval(parentInit);
			
			$("#saveSpan", window.parent.document).hide(); // 隐藏保存按钮
			
			if ($("#iszbfb", window.parent.document).attr("id")) {
				$("#iszbfb", window.parent.document).val($("#iszbfb").val());
			} else {
				$("#orgNodeForm", window.parent.document).append("<input type='hidden' id='iszbfb' name='iszbfb' value='" + $("#iszbfb").val() + "' />");
			}
			
			if ($("#iszb", window.parent.document).attr("id")) { // 主办
				$("#iszb", window.parent.document).val($("#iszb").val());
			} else {
				$("#orgNodeForm", window.parent.document).append("<input type='hidden' id='iszb' name='iszb' value='" + $("#iszb").val() + "' />");
			}
			
			if ($("#isxb", window.parent.document).attr("id")) { // 协办
				$("#isxb", window.parent.document).val($("#isxb").val());
			} else {
				$("#orgNodeForm", window.parent.document).append("<input type='hidden' id='isxb' name='isxb' value='" + $("#isxb").val() + "' />");
			}
		}
	}

	var transInit = setInterval("doTransInit()", 200);
	
	function doTransInit() {
		if ("ok" == $("#isReady", window.parent.window.frames["transFrame"].document).val()) {
			clearInterval(transInit);
			
			if ("hidden" != $("#ideacode", window.parent.window.frames["transFrame"].document).attr("type")) {
				window.parent.window.frames["transFrame"].window.changeIdeacode();
			}
			
			if ("checkbox" == $("#ishq", window.parent.window.frames["transFrame"].document).attr("type")) {
				if (!$("#ishq", window.parent.window.frames["transFrame"].document).attr("checked")) {
					$("#xbArea").hide();
				}
			}
			
			return false;
		}
	}

	function selRole(){
		window.parent.selRole({
			top:0,
			left:'60%'
		});
	}
	function selOrg(){
		window.parent.selOrg({
			top:0,
			left:'60%'
		});
	}
	
	function checkZbcs() {
		if ("sl" == $("#ideacode", window.parent.window.frames["transFrame"].document).val()) {
			if ($("#selZbcs").val() != $("#zbcsUnitCode").val()) {
				alert("当前选中的主办处室保存失败！");
				return false;
			} else if ("" == $("#selZbcs").val()) {
				alert("请选择主办处室！");
				return false;
			}
		}
		
		return true;
	}
	
	$(document).ready(function() {
		$("#zbcsUnitCode").change(function() {
			var unitCode = $(this).val();
			var flowInstId = $("#flowInstId").val();
			
			var method = ("" != unitCode ? "replaceFlowOrganizeAjax" : "deleteFlowOrganizeAjax");
			
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!" + method + ".do?flowInstId=" + flowInstId + "&roleCode=zbcsfb&orgCode=" + unitCode,
				dataType : "json",
				success : function(data) {
					if ("success" == data.status) {
						$("#selZbcs").val(unitCode);
					} else {
						alert("操作失败！");
					}
				},
				error : function() {
					alert("操作失败！");
				}
			});
			
			return false;
		});
	});
	/* $("#userName").click(function(){
		var d = '${userjson}';
		$('#attAlert',window.parent.document).show();
		if(d.trim().length){
			window.parent.selectPopWin(jQuery.parseJSON(d),$("#userName"),$("#userCode"));
		};
	});
	
	$("#_userName").click(function(){
		var d = '${userjson}';
		$('#attAlert',window.parent.document).show();
		if(d.trim().length){
			window.parent.selectPopWin(jQuery.parseJSON(d),$("#_userName"),$("#_userCode"));
		};
	}); */
	/* function selectPopWin(json,o1,o2 ){
 		new person(json,o1,o2).init();
 		$('#attAlert').show();
	} */
	
	/* function bindEvent(o1,o2,$this){
		o1.val($this.html());
		o2.val($this.attr("rel"));
			//$("#shadow").hide();
			$("#boxContent").hide();
	} */
	 /* $("#orgName").bind('click',function(){
		 var menuList = '${unitsJson}';
		window.parent.selectOrg(menuList,$("#orgName"),$("#orgCode"));
		 var menuList = ${unitsJson};
		var shadow = "<div id='boxContent' style='position:relative;top:-100px;z-index:1000;'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
		if(getID("boxContent")){
			//$("#shadow").show();
			$("#boxContent").show();
		}else{
			$("body").append(shadow);
			//$("#shadow").height(document.body.scrollHeight);
			setTimeout(function(){
				menuDisplay(menuList,"0");	
			},0);
		};
		$("#lists span").live('click',function(){
			var $this = $(this);
			bindEvent($("#orgName"),$("#orgCode"),$this);
			$("#lists span").die("click");
		}); 
 	}); */
</script>
</html>