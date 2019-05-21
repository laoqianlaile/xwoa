<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>流程权限委托</title>
</head>
<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<body class="sub-flow">
<%@ include file="/page/common/messages.jsp"%>
<fieldset class="form">
<legend>新增流程权限委托</legend>
<s:form action="sampleFlowRoleRelegateExpand"  method="post" namespace="/oa" id="wfRoleRelegateForm"  >
<%-- 	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" /> --%>
<!-- 	<input type="button"  value="返回" Class="btn"  onclick="window.history.back()"/> -->
	<input type="hidden" name="grant" value="${grant}"/>
	<input type="hidden" name="relegateno" value="${object.relegateno}" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
				<tr>
					<td class="addTd">
						受委托人<font color='red'>*</font>
					</td>
					<td align="left">
						<input type="text" name="userName" id="userName" style="width:85%;height:32px;" readonly="readonly" value="${cp:MAPVALUE('usercode',object.grantee)}" onclick="onclickSelectUser()"/>
						<input type="hidden" name="grantee" value="${object.grantee}" id="grantee" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						委托人<font color='red'>*</font>
					</td>
					<td align="left">
					<c:if test="${SPRING_SECURITY_CONTEXT.authentication.principal.usercode ne '0'}">
						<c:if test="${empty object.grantor}">
							<span>${SPRING_SECURITY_CONTEXT.authentication.principal.username}</span>
							<input type="hidden" name="grantor" value="${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}" />
						</c:if>
						<c:if test="${not empty object.grantor}">
							<span>${cp:MAPVALUE('usercode',object.grantor)}</span>
							<input type="hidden" name="grantor" value="${object.grantor}" />
						</c:if>
					</c:if>
					
					<c:if test="${SPRING_SECURITY_CONTEXT.authentication.principal.usercode eq '0'}">
						<div class="userDiv">
							<span>${cp:MAPVALUE('usercode',object.grantor)}</span>
							<input type="hidden" id="grantor" name="grantor" value="${object.grantor}"/>
							<ul id="list1" class="ulList"></ul>
						</div>
						<script type="text/javascript">
							 $(function(){
								initValue($("input[name=grantorLabel]"),$("#list1"),"${pageContext.request.contextPath}/sys/userDef!getUsers.do",$("#grantor"));
							})
						 </script>
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						委托开始时间<font color='red'>*</font>
					</td>
					<td align="left">
						<input type="text" class="Wdate"  style="width:200px;height:25px;" 
						 id="relegatetime" value='<fmt:formatDate value="${object.relegatetime}" pattern="yyyy-MM-dd"/>' 
						 name="relegatetime"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker id="relegatetime" --%>
<%-- 							name="relegatetime" value="%{object.relegatetime}" --%>
<%-- 							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
					</td>
				</tr>

				<tr>
					<td class="addTd">
						截止时间
					</td>
					<td align="left">
						<input type="text" class="Wdate"  style="width:200px;height:25px;" 
						 id="expiretime" value='<fmt:formatDate value="${object.expiretime}" pattern="yyyy-MM-dd"/>' 
						 name="expiretime" 
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker id="expiretime" --%>
<%-- 							name="expiretime" value="%{object.expiretime}" --%>
<%-- 							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
						
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						委托授予说明
					</td>
					<td align="left">
						<s:textarea name="grantdesc" id="grantdesc" style="width:100%;height:60px" rows="2" value="%{object.grantdesc}"/>
					</td>
				</tr>

</table>
		<div class="formButton">
		<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" onclick="return checkForm();"/>
		<input type="button"  value="返回" Class="btn"  onclick="window.history.back(-1)"/>
		</div>
</s:form>

</fieldset>
<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
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
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	function onclickSelectUser(){
		var d = '${userjson}';
		if (d.trim().length) {
			selectPopWin(jQuery.parseJSON(d),
					$("#userName"),
					$("#grantee"));
		}
	}
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	function init(){
		hiddenAllRoleDIv();
		if(trim(_get('roletype').value).length!=0){
			divHid(_get('no'));
			divShow(_get(_get('roletype').value));
		}
	}
	
	function hiddenAllRoleDIv(){
		divHid(_get('xz'));
		divHid(_get('bj'));
		divHid(_get('gw'));
	}
	function showDivByType(type){
		hiddenAllRoleDIv();
		if(type != ''&& type !=='en'){
			divShow(_get(type));
			divHid(_get('no'));
		}else{
			divShow(_get('no'));
		}
	}
	
	function setRoleCode(v){
		_get('rolecode').value=v;
	}
	
	function checkForm(){
		if(trim($("#grantee").val()).length==0&&trim($("#userName").val()).length==0){
			alert("受委托人不能为空");
			$("#grantee").focus();
			return false;
		} 
		if(trim($("#grantee").val()).length==0&&trim($("#userName").val()).length!=0){
			alert("受委托人必须为系统用户");
			$("#userName").focus();
			return false;
		} 
		if($("#relegatetime").val()==""){
			alert("请选择委托开始时间");
			return false;
		}
		var begD = $("#relegatetime").val();
		var endD = $("#expiretime").val();
		if(endD!=""&&begD>endD){
			alert("截止时间不能早于委托开始时间。");
			return false;
		}
		
		if($("#grantor").val()==''){
			alert("委托人不能为空");
			$("#grantor").focus();
			return false;
		}
		if($("#grantdesc").val()!=''){
			if($("#grantdesc").val().length>128){
				alert("委托授予说明长度不能超过256个字符");
				$("#grantdesc").focus();
				return false;
			}
		}
		return true;
	}

	//节点对象获取
	var _get = function (id) {
		return document.getElementById(id);
	};
	
	//字符空格处理
	var trim = function (str) {
		return str.replace(/^\s+|\s+$/g, "");
	};
	
	//DIV控件显示
	var divShow = function (obj) {
		return obj.style.display = "";
	};
	//DIV控件隐藏
	var divHid = function (obj) {
		return obj.style.display = "none";
	};
	
	//init();
</script>