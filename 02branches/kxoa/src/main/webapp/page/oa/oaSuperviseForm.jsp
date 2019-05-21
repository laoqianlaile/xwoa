<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<title><s:text name="oaSupervise.edit.title" /></title>
</head>

<body class="sub-flow">
<%-- <p class="ctitle"><s:text name="oaSupervise.edit.title" /></p> --%>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaSupervise"  method="post" namespace="/oa" id="oaSuperviseForm" >
					
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowinstid" name="flowinstid" value="${flowinstid}" />
		<input type="hidden" id="flowCode" name="flowCode" value="000859" />
	<fieldset>
		<legend>	
		督办发起
		</legend>
 <input type="hidden" id="nodecode" name="nodecode" value="${object.nodecode}" />
  <input type="hidden" id="superviseUsers" name="superviseUsers" value="${object.superviseUsers}" />
 
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
				<tr>
					<td class="addTd">
						 督办业务流水号<span style="color: red">*</span>
					</td>
					<td align="left" colspan="3">
	                 <c:if test="${not empty object.supDjid}">
						 ${object.supDjid}
						 <input type="hidden" id="supDjid" name="supDjid" value="${object.supDjid}" />
						 </c:if>
						<c:if test="${empty object.supDjid}">
						<s:textfield id="supDjid" name="supDjid"   value="%{object.supDjid}" style="width:80%;height:25px;" readonly="true"></s:textfield>
  					
  						<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!selectlist.do?','powerWindow',null);" value="选择">
					</c:if>
 
					</td>
				</tr>

				

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.title" /><span style="color: red">*</span>
					</td>
					<td align="left">
  	                <s:textfield id="title" name="title" value="%{object.title}" style="width:100%;height:25px;" />
						
	
	
					</td>
				</tr>

				<%-- <tr>
					<td class="addTd">
						<s:text name="oaSupervise.superviseType" />
					</td>
					<td align="left">
                          	<select name="superviseType" style="width:150px" id="pooriginstate">
							 	<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('oa_superviseType')}">
									<option value="${row.key}" 
									<c:if test="${object.superviseType  eq row.key}"> selected = "selected" </c:if>
									<c:if test="${empty object.superviseType  and row.key eq '01'}"> selected = "selected" </c:if>
									>
									<c:out value="${row.value}" /></option>
								</c:forEach>
						</select>

	
					</td>
				</tr> --%>
<tr>
					<td class="addTd">
						<s:text name="oaSupervise.limittime" /><span style="color: red">*</span>
					</td>
					<td align="left">
	
                  <%--  	<sj:datepicker id="limittime"
						value="%{object.limittime}" name="limittime"
						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true"
						changeYear="true" readonly="true" /> --%>
							<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.limittime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="limittime" name="limittime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
	              <span style="color: red">【*温馨提醒：超过此督办限定时限，系统将会再次发起督办！】</span>
					</td>
					
				</tr>
				<tr >
				<td class="addTd">
					督办意见
				</td>
				<td align="left">
					<s:textarea id="advice" name="advice" value="%{object.advice}" style="width:100%;height:50px;" />
				</td>
				</tr>
				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.remark" /><span style="color: red">*</span>
					</td>
					<td align="left">
	
    	                <s:textarea id="remark" name="remark" value="%{object.remark}" style="width:100%;height:50px;" />

					</td>
				</tr>
				<tr>
				<td class="addTd">
					发起提醒
				</td>
				<td align="left">
					<input id="tx" type="checkbox"  onclick="showman();" />发起提醒
				</td>
				</tr>
				<tr class="txry" style="display: none;">
				<td class="addTd">
					提醒人员(分管领导)
				</td>
				<c:if test="${not empty txnames}">
				<td align="left">
				<c:forEach items="${txnames}" var="usercode">
				<input name="txusercode" type="checkbox" value="${usercode.key}" />${usercode.value}
				
				</c:forEach>
				<!-- <input type="button" class="whiteBtnWide" value="发起" onclick="showView('发起提醒')" /> -->
				</td>
				</c:if>
				<c:if test="${ empty txnames}">
				<td align="left"><input type="text" readonly="readonly"  data-rule-maxlength="1200" 
						id="txusername" name="txusername" style="width: 100%"
						class="focused required " /> 
						<input id="txusercode" type="hidden" name="txusercode"  /> 
						</td>
				</c:if>
				</tr>
				<tr class="txry" style="display: none;">
				<td class="addTd">
					意见
				</td>
				<td align="left">
					<s:textarea id="adviceTOLd" name="adviceTOLd" value="%{adviceTOLd}" style="width:100%;height:50px;" />
				</td>
				</tr>
				

		
				

</table>
     <div class="formButton">

		<input type="button" class="btn" id="save" value="保存"
			onclick="submitItemFrame('SAVE');" />
		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
		<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);" id="backBtn"/>	
		</div>
<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				
				<!-- <div class="userTabDiv">
					<div onclick="tab(this,'unit')" class="select">部门</div>
					<div onclick="tab(this,'station')">岗位</div>
					<div onclick="tab(this,'unitLeader')">分管领导组</div>
				</div> -->

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
</fieldset>
</s:form>

</body>


</html><script type="text/javascript">
function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
function submitItemFrame(subType) {
	if (docheck() == false) {
		return;
	} else {
		var srForm = document.getElementById("oaSuperviseForm");
		if (subType == 'SAVE') {
			srForm.action = 'oaSupervise!savePermitReg.do';
		}

		if (subType == 'SUB') {
			srForm.action = 'oaSupervise!saveAndSubmit.do';
		}
		var txusercodes='';
	if($("#txusercode").length <= 0 ){//判断是input还是checkbox
		var obj=$("input[name='txusercode']");
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) 
				txusercodes+=obj[i].value+',';  
			} 
	}else{
			txusercodes=$("#txusercode").val();
	}
		var titles=$("#title").val();
		var mydate = new Date();
		var year=mydate.getFullYear(); //获取完整的年份(4位,1970-????)
		var month=mydate.getMonth()+1; //获取当前月份(0-11,0代表1月)
		var date=mydate.getDate(); //获取当前日(1-31)
		var hours=mydate.getHours(); //获取当前小时数(0-23)
		var minutes=mydate.getMinutes(); //获取当前分钟数(0-59)
		var seconds=mydate.getSeconds(); //获取当前秒数(0-59)
		var t=year+"-"+((month<10)?("0"+month):month)+"-"+((date<10)?("0"+date):date)+" "+((hours<10)?("0"+hours):hours)+":"+((minutes<10)?("0"+minutes):minutes)+":"+((seconds<10)?("0"+seconds):seconds);
		var bagtime=t;
		var endtime=$("#limittime").val();
		var reType=$("#supDjid").val().substring(0,$("#supDjid").val().indexOf("0"));
		var createRemark=$("#adviceTOLd").val();
		var djid=$("#supDjid").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/oa/oaRemindInformation!submitForDUBAN.do",
			type:"post",
    		//dataType:"json",
    		data:{"title":titles,"userValue":txusercodes,"begtime":bagtime,"endtime":endtime,"reType":reType,"createRemark":createRemark,"djid":djid},
    		success:function(date){
    			srForm.submit();
    		},
    		 error: function(XMLHttpRequest, textStatus, errorThrown){
    			if(confirm('提醒发起失败，是否继续发起督办？')){
    				srForm.submit();
    			}else{
    			   
    			}
    		}
		});
	}
}
function docheck() {
	if ($("#supDjid").val() == '') {
		alert("督办业务流水号不可为空！");
		$('#supDjid').focus();
		return false;
	}
	if ($("#title").val() == '') {
		alert("督办主题不可为空！");
		$('#title').focus();
		return false;
	}
	if ($("#limittime").val() == '') {
		alert("督办时限不可为空！");
		$('#limittime').focus();
		return false;
	}
	if ($("#remark").val() == '') {
		alert("督办内容不可为空！");
		$('#remark').focus();
		return false;
	}


		return true;
	}
	function showman(){
		if($("#tx").is(":checked")==true){
			$(".txry").show();
		}else{
			$(".txry").hide();
		}
	}
	function showView(title){
		var obj=$("input[name='txusercode']");
		var txusercodes=''; 
		var titles=$("#title").val();
		for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked) 
			txusercodes+=obj[i].value+',';  
		} 
		var link="<%=request.getContextPath()%>/oa/oaRemindInformation!builtV2.do?xzrc_sy=xzrc_sy&txusers="+txusercodes+"&title="+titles;
		DialogUtil.open(title,link,1200,700);
	}
	$("#txusername").click(
			function() {
				//$('#boxType').val('tr_receive');
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#txusername"),
							$("#txusercode"));
				}
				;
			});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
</script>
