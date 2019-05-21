<!-- <!DOCTYPE html> -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaBbsDashboard.edit.title" /></title>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
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
	<fieldset class="form">
		<legend>
			<p>
			编辑讨论区
			</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/bbs/oaBbsDashboard!save.do"
			method="post" id="oaBbsDashboardForm" data-validate="true">
			
            <input type="hidden" name="layoutcode" value="${object.layoutcode }" />
			<input type="hidden" name="state" value="${object.state }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">版面名称<font color="#ff0000">*</font></td>
					<td align="left" colspan="3"><input type="text" class="focused required"
						name="layoutname" value="${object.layoutname }"
						style="width: 96%; height: 30px" /></td>
				</tr>
				<tr>
					<td class="addTd">所属部门<font color="#ff0000">*</font></td>
					<td align="left"><select id="unitcode" name="unitcode" style="width: 200px;height: 30px;" class="focused required">
							<c:forEach items="${units }" var="u">
								<option value="${u.unitcode }"
									<c:if test="${u.unitcode eq object.unitcode}" >selected="selected" </c:if>>
									${u.unitshortname}</option>
							</c:forEach>
					</select></td>
					<td class="addTd">是否公开<font color="#ff0000">*</font></td>
					<td align="left"><select name="openType" style="width: 200px;height: 30px;" class="focused required">
							<c:forEach items="${cp:LVB('DashOpenType')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq object.openType }">selected="selected" </c:if>>${v.label }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd">模块类型<font color="#ff0000">*</font></td>
					<td align="left"><select name="layouttype" id="layouttype"class="focused required"
						style="width: 200px;height: 30px;">
							<c:forEach items="${cp:LVB('LayOutType') }" var="v">
							  <!-- 如果不是高级管理员，只能选择内部模块 -->
							   <c:if test="${(session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode != '0' 
							       && session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode !='99999999'
							       && v.value =='U') || session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode=='0'
							       || session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode=='99999999'}">
								<option value="${v.value }"
									<c:if test="${v.value eq object.layouttype }">selected="selected"</c:if>>${v.label
									}</option>
								</c:if>
							</c:forEach>
					</select></td>

					<td class="addTd">顺序号<font color="#ff0000">*</font></td>
					<td align="left"><input type="text" name="orderno" class="focused required number"
						value="${object.orderno }" style="width: 200px; height: 30px;"/></td>
				</tr>

				<tr>


					<td class="addTd">子模块管理员<font color="#ff0000">*</font></td>
					<td align="left" colspan="3"><input type="text" readonly="readonly"
						data-rule-maxlength="1200" id="txa_innermsg_receive_name"
						name="approvalNames" rows="1" cols="1" style="width: 96%; height: 30px"
						value="${object.approvalNames }" class="focused required " /> <input id="txt_innermsg_receive_usercode"
						type="hidden" name="approvals" value="${object.approvals }" /> <%-- <input type="text" class="required autocomplete" 
					  name="approvals" data-pre-populate='${populate}' 
					  data-url="${pageContext.request.contextPath}/bbs/oaBbsDashboard!selectUser.do" /> --%>
					</td>
				</tr>
				
				<tr>
				
                    <td class="addTd">是否设置开放时间：</td>
					<td align="left" colspan="3">
					    <select id="isdocontral" name="isdocontral" style="width: 200px;height: 30px;" class="focused required" >
							<option value="F"
								<c:if test="${'F' eq object.isdocontral }">selected="selected" </c:if>>不设置</option>
					        <option value="T"
								<c:if test="${'T' eq object.isdocontral }">selected="selected" </c:if>>设置</option>
					    </select></td>
				</tr>

				<tr id="amtime">
				    <td class="addTd">上午时间开始</td>
					<td align="left"><input type="text" class="Wdate" data-type="time"
						id="starttimeTemp" name="starttimeTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						
						<c:if test="${null ne object.starttime && '' ne object.starttime}" >
						value="${fn:substring(object.starttime, 11, 16) }"						
						</c:if>
						
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'00:00',maxDate:'12:00'})" placeholder="选择日期" />
											
					<td class="addTd">上午时间结束</td>
					<td align="left"><input type="text" class="Wdate"
						id="endtimeTemp" name="endtimeTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						
						<c:if test="${null ne object.endtime && '' ne object.endtime}" >
						value="${fn:substring(object.endtime, 11, 16) }"						
						</c:if>
						
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'00:00',maxDate:'12:00'})" placeholder="选择日期" />
				</tr>
				
				<tr id="pmtime">
				    <td class="addTd">下午时间开始</td>
					<td align="left"><input type="text" class="Wdate"
						id="starttimepmTemp" name="starttimepmTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						
						<c:if test="${null ne object.starttimepm && '' ne object.starttimepm}">
						value="${fn:substring(object.starttimepm, 11, 16) }"						
						</c:if>
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'12:00',maxDate:'23:59'})" placeholder="选择日期" /></td>
					
					<td class="addTd">下午时间结束</td>
					<td align="left"><input type="text" class="Wdate"
						id="endtimepmTemp" name="endtimepmTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						
						<c:if test="${null ne object.endtimepm && '' ne object.endtimepm}" >
						value="${fn:substring(object.endtimepm, 11, 16) }"						
						</c:if>
						
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'12:00',maxDate:'23:59'})" placeholder="选择日期" /></td>
				</tr>

				<tr>
					<td class="addTd">备注</td>
					<td align="left" colspan="3"><s:textarea name="approvalremark" cols="40"
							rows="2" style="width: 100%;height:45px;"/></td>
				</tr>

			</table>

			<div class="formButton">
				<input type="button" id="saveBtn" name="save" class="btn" value="保存"/>
				<input type="button" class="btn" value="返回"
					onclick="window.location.href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!list.do'" />
			</div>

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
							value="保      存" /><input id="clear" class="btn" type="button"
							value="取      消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</form>

	</fieldset>

	<script type="text/javascript">
	   //add by lay 2015-11-19
	   var userTreeJson = '';
	   function initUsers(unitCode,layoutType){
		   $.ajax({
			   type:"post",
			   url:"${pageContext.request.contextPath}/bbs/oaBbsDashboard!initUsers.do",
			   dataType:"json",
			   data:{
				   "userCode":"${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}",
				   "unitCode":unitCode,
				   "layoutType":layoutType
			   },
			   success:function(data){
				   userTreeJson = data;
			   },
			   error:function(data){
				   alert("用户编码和模块类型以及所属部门不能为空");
			   }
			   
		   });
	   }
		$("#txa_innermsg_receive_name").click(
				function() {
						selectPopWin(userTreeJson,
								$("#txa_innermsg_receive_name"),
								$("#txt_innermsg_receive_usercode"));
				});

		function selectPopWin(json, o1, o2, oShow) {
			new treePerson(json, o1, o2, oShow).init();
			setAlertStyle("attAlert");
		}
		
		
		$(function() {
			var amtime = $('#amtime');
			var pmtime = $('#pmtime');

			var isdocontral = $('#isdocontral').val();

			var starttime = $('#starttimeTemp');
			var endtime = $('#endtimeTemp');
			var starttimepm = $('#starttimepmTemp');
			var endtimepm = $('#endtimepmTemp');

			/* 判断是否显示开放时间控件 */
			if ('T' == isdocontral) {
				amtime.show();
				pmtime.show();
				
			} else {
				amtime.hide();
				pmtime.hide();
				
			}

			/* 根据“是否开放设置时间”值显示或隐藏控件 */
			$('#isdocontral').change(function() {
				var isdocontral = $(this).val();

				if ('T' == isdocontral) {
					amtime.show();
					pmtime.show();

					// 添加required属性
					$('#starttimeTemp').addClass('required');
					$('#endtimeTemp').addClass('required');
					$('#starttimepmTemp').addClass('required');
					$('#endtimepmTemp').addClass('required');
				} else {
					amtime.hide();
					pmtime.hide();
					// 不设置开放时间时，将原先所有的时间点都设为空
					starttime.val('');
					endtime.val('');
					starttimepm.val('');
					endtimepm.val('');
					
					// 去除时间点的required属性
					$('#starttimeTemp').removeClass('required');
					$('#endtimeTemp').removeClass('required');
					$('#starttimepmTemp').removeClass('required');
					$('#endtimepmTemp').removeClass('required');
				}
			});
			
			
		/* 提交时给时间点加上默认的年月日 */
			$('#saveBtn').click(function() {
				
				if (null != starttime.val() && '' != starttime.val()) {
					var v = '2000-01-01 ' + starttime.val();
					starttime.val(v);
				} else
					starttime.val('');

				if (null != endtime.val() && '' != endtime.val()) {
					var v = '2000-01-01 ' + endtime.val();
					endtime.val(v);
				} else
					endtime.val('');

				if (null != starttimepm.val() && '' != starttimepm.val()) {
					var v = '2000-01-01 ' + starttimepm.val();
					starttimepm.val(v);
				} else
					starttimepm.val('');

				if (null != endtimepm.val() && '' != endtimepm.val()) {
					var v = '2000-01-01 ' + endtimepm.val();
					endtimepm.val(v);
				} else
					endtimepm.val('');

				$('#oaBbsDashboardForm').submit();
			});
		
		  //add by lay 2015-11-19 初始化人员
		   if($("#unitcode").val().length > 0 && $("#layouttype").val().length > 0){
			   initUsers($("#unitcode").val(),$("#layouttype").val());
		   }
		  $("#unitcode").change(function(){
			  $("#txa_innermsg_receive_name").val('');
			  $("#txt_innermsg_receive_usercode").val('');
			  initUsers(this.value,$("#layouttype").val());
		  });
		  $("#layouttype").change(function(){
			  $("#txa_innermsg_receive_name").val('');
			  $("#txt_innermsg_receive_usercode").val('');
			  initUsers($("#unitcode").val(),this.value);
		  });
		  //end add
		});
	</script>


</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>
