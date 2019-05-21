<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSmssend.edit.title" /></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
</head>

<body class="sub-flow">

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaSmssend" method="post" namespace="/oa"
		id="oaSmssendForm" onsubmit="return doCheck();">
		<s:submit name="save" method="saveAcceptpeo" cssClass="btn" key="opt.btn.save" value="发送短信"/>
		<c:if test="${empty dashboard }">
		<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>
		</c:if>
		<input type="hidden" id="smsid" name="smsid" value="${smsid}" />
		<input type="hidden" id="originate" name="originate" value="${originate }" />
		
		<input type="hidden" id="datasource" name="datasource" value="${object.datasource }"
		
		<input type="hidden" id="edit" name="edit" value="${edit }" />
		
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
			<tr>
				<td class="addTd"><s:text name="oaSmssend.acceptpeo" /><font color='red'>*</font></td>
				<td align="left">
				<input type="text" id="acceptpeo"
					name="acceptpeo" style="width: 100%; height: 25px; boder:none"
					readonly="readonly" value="${object.acceptpeo }" <c:if test="${'T' ne edit }">onclick="selectUsers();"</c:if>/> 
					<input
					type="hidden" id="acceptpeocode" name="acceptpeocode"
					value="${object.acceptpeocode}" /></td>
				
				<c:if test="${'T' eq edit }">	
					<td class="addTd">接收者手机号</td>
					<td align="left"><input type="text" id="acceptnum" name="acceptnum" value="${object.acceptnum }" /></td>
				</c:if>
			</tr>
<tr>
				<td class="addTd"><s:text name="oaSmssend.content" /><font color='red'>*</font></td>
				<td align="left" <c:if test="${'T' eq edit }">colspan="3"</c:if>><s:textarea id="content" name="content" cols="40" rows="12" style="width: 100%;height:180px" />
				</td>
			</tr>

		</table>
		
		
		<!-- 选择人员的模块 -->
		<div id="attAlert" class="alert"
			style="width: 600px; height: 340px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
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
					<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
					<b class="btns"> <input id="save" class="btn" type="button"
						value="保     存" /><input id="clear" class="btn" type="button"
						value="取     消" style="margin-top: 5px;" /></b>
				</div>
			</div>
		</div>
	</s:form>
</body>
<script type="text/javascript">


/* 	$("#acceptpeo").click( */
	function selectUsers() {
				var d = '${userjson}';

				if (d.trim().length) {
					new treePerson(jQuery.parseJSON(d), $("#acceptpeo"),
							$("#acceptpeocode")).init();
					setAlertStyle("attAlert");
				}
				;
			}
	
	function doCheck(){
		
		 if ($('#acceptpeocode').val() == '') {
			 alert("接收人员不可为空，请选择人员！");
				$('#content').focus();
				return false;
		 }
		 if ($('#content').val() == '') {
			 alert("短信内容不可为空！");
				$('#content').focus();
				return false;
		 }
		 
		 if(!$('#acceptnum').val().match('^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$')){
			 alert("请输入正确的手机号！");
				$('#acceptnum').focus();
			 return false;
		 }
		 
		 var flag = true;
		 // 验证当月已发短信是否已达上限
		 if($('#datasource').val() == 'R'){
			 $.ajax({
	             type: "post",
	             async: false,
	             url: "oa/oaSmssend!canSendMsgs.do",     
	             success: function(data) {
	               if(null != data){
	            	   alert(data.error);
	            	   flag = false;
	               }
	             }
	         });
		 }
		 
		 if(flag && window.confirm("短信一旦提交，不可撤回，确定提交吗？")){
			 return true;
			 
		 }else{
			 return false;
		 }
	}
</script>
</html>