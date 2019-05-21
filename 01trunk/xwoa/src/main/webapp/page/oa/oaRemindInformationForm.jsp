<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaRemindInformation.edit.title" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
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
	<fieldset class="_new">
		<c:if test="${empty xzrc_sy}">
			<legend>
					<s:text name="oaRemindInformation.edit.title" />
			</legend>
		</c:if>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaRemindInformation" method="post" namespace="/oa"
			id="oaRemindInformationForm">

            <c:if test="${empty xzrc_sy}">
            <div style="margin-top:5px;">
                 <input type="button"
					class="btn" value="暂存" onclick="submitItemFrame('SAVE');" />
				 <input type="button" class="btn" value="提交" onclick="submitItemFrame('SUB');" />
				 <!-- <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" /> -->
			</c:if>
			<c:if test="${not empty xzrc_sy}">
			<div style="margin-top:0px;margin-bottom: 0px;">
                 <input type="button"
					class="whiteBtnWide" value="暂存" onclick="submitItemFrame('SAVE');" />
				 <input type="button" class="whiteBtnWide" value="提交" onclick="submitItemFrame('SUB');" />
			</c:if>			
			</div>
				
			<input type="hidden" name="xzrc_sy" id="xzrc_sy" value="${xzrc_sy}" />
			<input type="hidden" id="no" name="no" value="${object.no}" />
			<input type="hidden" id="originate" name="originate" value="${originate }" />
			
			<table style="margin-top: 10px;" border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd"><s:text name="oaRemindInformation.title" /><font color="red">*</font>
					</td>
					<td align="left" colspan="3"><s:textfield name="title"
							id="title" style="width: 100%;height: 25px;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaRemindInformation.begtime" /><font color="red">*</font>
					</td>
					<td align="left"><input type="text" class="Wdate" id="begtime"
						name="begtime"
						style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.begtime}" pattern="yyyy-MM-dd  HH:mm:ss"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})"
						placeholder="选择日期"> <%-- 	                	<sj:datepicker id="begtime" name="begtime" value="%{object.begtime}" style="width: 180px;" --%>
						<%-- 							 yearRange="2000:2020" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"   --%>
						<%-- 							 timepicker="true" changeYear="true" readonly="true" cssStyle="width: 100px;" />			 --%>
					</td>

					<td class="addTd"><s:text name="oaRemindInformation.endtime" /><font color="red">*</font>
					</td>
					<td align="left"><input type="text" class="Wdate" id="endtime"
						name="endtime"
						style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.endtime}" pattern="yyyy-MM-dd  HH:mm:ss"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})"
						placeholder="选择日期"> <%-- 	             <sj:datepicker id="endtime" name="endtime" value="%{object.endtime}" style="width: 180px;" --%>
						<%-- 							 yearRange="2000:2020" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  --%>
						<%-- 							  timepicker="true" changeYear="true" readonly="true" cssStyle="width: 100px;" />			 --%>
					</td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaRemindInformation.usercode" /><font color="red">*</font>
					</td>
					<td align="left" colspan="3"><input type="text" id="usercode"
						name="usercode" style="width: 100%; height: 27px;"
						value="${usercode}" readonly="readonly" /> <s:hidden
							name="userValue" id="userValue" value="%{userValue}"></s:hidden>



					</td>
				</tr>

					<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen')}">
				<tr>
					<td class="addTd">短信提醒
					</td>

					<td align="left" colspan="3"><%-- <select name="reType"
						style="width: 150px" id="reType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_superviseType')}">
								<option value="${row.key}"
									<c:if test="${object.reType  eq row.key or reTypeFrom eq row.key}"> selected = "selected" </c:if>
									<c:if test="${empty object.reType  and row.key eq '01'}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select> &nbsp;&nbsp;&nbsp; --%>
							<label style="font-size: 14px;">是否发送短信提醒：</label>
							<input id="isSendMsgView" class="checkboxClass" type="checkbox"
								name="isSendMsgView"
								<c:if test="${'T' eq object.isSendMsg }">checked="checked" </c:if> />
							<input type="hidden" id="isSendMsg" name="isSendMsg"
								value="${object.isSendMsg }" />
							<input type="hidden" name="reType" value="OT">
						</td>
				</tr>
				</c:if>
				<tr>
					<td class="addTd"><s:text name="oaRemindInformation.remark" />
					</td>
					<td align="left" colspan="3"><s:textarea name="remark" cssStyle="height:40px;"
							cols="40" rows="2" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaRemindInformation.createRemark" /></td>
					<td align="left" colspan="3"><s:textarea name="createRemark" cssStyle="height:40px;"
							cols="40" rows="2" style="width: 100%;" /></td>
				</tr>

			</table>

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
						<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function submitItemFrame(subType) {
		debugger;
		if (docheck() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaRemindInformationForm");
			if (subType == 'SAVE') {
				srForm.action = 'oaRemindInformation!save.do';
			}
			if (subType == 'SUB') {
				srForm.action = 'oaRemindInformation!submit.do';
			}
			
			if($("#cla").val()!=null){
				 if(window.confirm("是否确认发起提醒？", "确认")){
					openLoadIng(true);
					srForm.submit();
					DialogUtil.close();
				 }else{
					 return;
				 }
					 
			}else{
				
				/* 这里用ajax方式提交是因为用普通方式提交后后面的内容不会被执行 */
				if($("#xzrc_sy").val()!=null&&$("#xzrc_sy").val()=="T"){
					if (subType == 'SAVE') {
						$.ajax({
			                 type: "post",
			                 url: "oa/oaRemindInformation!save.do",     
			                 data: $("#oaRemindInformationForm").serialize(),    
			                 success: function(data) {
			                    var s=$("#xzrc_sy").val();
			         			if(s=="T"){
			         				var t="../oa/oaRemindInformation!builtV2.do?xzrc_sy=T";
			    					var c=$('#tx_table', parent.document);
			    					c.attr('src',t);
			        				}
			                 },
			                 error: function(data) {
			                     alert("保存失败，请重新尝试或联系管理员！");
			                 }
			             });
	             }
					if (subType == 'SUB') {
						submitRemind();
					}
				}else{
					 openLoadIng(true);
					 srForm.submit();
				}
				
			}
		}
		
	}

	function docheck() {
		if ($("#title").val() == '') {
			alert("提醒主题不可为空！");
			$('#title').focus();
			return false;
		}

		if ($("#begtime").val() == '') {
			alert("提醒开始时间不可为空！");
			$('#begtime').focus();
			return false;
		}
		if ($("#endtime").val() == '') {
			alert("提醒结束时间不能为空！");
			$('#endtime').focus();
			return false;
		}
		if ($("#usercode").val() == '') {
			alert("提醒人员不能为空！");
			$('#usercode').focus();
			return false;
		}

	}
	
	function submitRemind(){
		$.ajax({
            type: "post",
            url: "oa/oaRemindInformation!submit.do",     
            data: $("#oaRemindInformationForm").serialize(),    
            success: function(data) {
               var s=$("#xzrc_sy").val();
    			if(s=="T"){
    				var t="../oa/oaRemindInformation!builtV2.do?xzrc_sy=T";
					var c=$('#tx_table', parent.document);
					c.attr('src',t);
   				}
            },
            error: function(data) {
           	 alert("保存失败，请重新尝试或联系管理员！");
            }
        });
	}

	var oOrgUser = new Object();

	oOrgUser["usercode"] = $("#usercode");

	$("#usercode").click(function() {
		var d = '${userjson}';

		if (d.trim().length) {
			selectPopWin(jQuery.parseJSON(d), $("#usercode"), $("#userValue"));
		}
		;
	});
	
	$('#isSendMsgView').click(function(){
		var $this = $(this);
		if($this.attr('checked') == 'checked'){
			$('#isSendMsg').val('T');
		}else{
			$('#isSendMsg').val('F');
		}
	});
	function selectPopWin(json, o1, o2) {
		new treePerson(json, o1, o2).init();
		setAlertStyle("attAlert");
	}
</script>

</html>
