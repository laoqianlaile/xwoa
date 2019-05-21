<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<title>执法人员登记</title>
	<%-- <sj:head locale="zh_CN" /> --%>
	<s:include value="/page/common/formValidator.jsp"></s:include>
	<script type="text/javascript" src="<s:url value="/scripts/opendiv_Win.js"/>" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		/*  	jQuery.validator.addMethod("isMobile", function(value, element) {
		 var length = value.length;
		 var tel =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/  ;
		 return this.optional(element) || (length==11 && tel.test(value));
		 }, "请正确填写您的手机号码"); */
	
		$(document).ready(function() {
			$.formValidator.initConfig({
				formid : "lawExecutorForm",
				onerror : function(msg, obj, errorlist) {
					alert(msg);
				}
			});
			$('#deptid').formValidator({
				onShow : "请选择 执法主体"
			}).inputValidator({
				min:1,
				onerror : "执法主体 是必选项"
			});
		});
	</script>
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">执法人员信息</legend>
		<s:form action="lawExecutor" method="post" namespace="/powerbase"
			id="lawExecutorForm" enctype="multipart/form-data">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" onclick="lawExecutorForm.auditIdeaCode.value='0'"/>
			<s:submit name="save" method="save" cssClass="btn" value="提交审核" onclick="lawExecutorForm.auditIdeaCode.value='1'"/>
			<input type="button" value="返回" Class="btn" onclick="window.location.replace('${backurl}')" />
			<!-- 审核状态 -->
			<input type="hidden" name="auditIdeaCode" value="">
			<!-- 返回参数 -->
			<s:hidden name="backurl"/>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="TDTITLE">证书编号</td>
					<td align="left" colspan="3">
						<input type="text" name="passcode" size="40">
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">姓名</td>
					<td align="left">
						<input type="text" name="staffname" size="40">
					</td>
					<td class="TDTITLE" rowspan="3">证件照片</td>
					<td align="left" rowspan="3">
						<input type="file" name="file" value=""/>
					</td>
				</tr>
				
				<tr>
					<td class="TDTITLE">性别</td>
					<td align="left">
						<select id="sex" name="sex" style="width: 200px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('sex')}">
								<option value="${row.value}">
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">民族</td>
					<td align="left">
						<select id="nation" name="nation" style="width: 200px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('LAW_NATION')}">
								<option value="${row.value}">
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">身份证号</td>
					<td align="left">
						<input type="text" name="idcard" size="40">
					</td>
					<td class="TDTITLE">证件种类</td>
					<td align="left">
						<select id="cardkind" name="cardkind" style="width: 200px">
							<c:forEach items="${cp:LVB('LAW_CARDKIND')}" var="row">
								<option value="${row.value }">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">政治面貌</td>
					<td align="left">
						<select id="politics" name="politics" style="width: 200px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('LAW_POLITICS')}">
								<option value="${row.value}">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="TDTITLE">学历</td>
					<td align="left">
						<select id="edutation" name="education" style="width: 200px">
							<c:forEach items="${cp:LVB('LAW_EDUCATION')}" var="row">
								<option value="${row.value}">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">执法主体</td>
					<td align="left">
						<input type="hidden" id="deptid" name="deptid" value="">
						<input type="text" id="deptname" name="deptname" value="" size="40">
					</td>

					<td class="TDTITLE">职务</td>
					<td align="left">
						<input type="text" name="position" size="40">
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">电话</td>
					<td align="left">
						<input type="text" name="telephone" id="telephone" size="40">
					</td>
					<td class="TDTITLE">编制情况</td>
					<td align="left">
						<select id="plait" name="plait" style="width: 200px">
							<c:forEach items="${cp:LVB('LAW_PLAIT') }" var="row">
								<option value="${row.value }">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">发证时间</td>
					<td>
						<sj:datepicker id="getpasstime" name="getpasstime"
							style="width:120px;" timepicker="false" timepickerFormat="hh:mm"
							displayFormat="yy-mm-dd" validator="input" min="1"
							errorshow="请输入发证时间" />
					</td>
					<td class="TDTITLE">发证部门</td>
					<td align="left">
						<input type="text" name="issueddept" size="40">
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">执法区域</td>
					<td align="left">
						<textarea name="executionarea" rows="2" cols="40"></textarea>
					</td>
					<td class="TDTITLE">执法种类</td>
					<td align="left">
						<textarea name="executionclass" rows="2" cols="40"></textarea>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">职级</td>
					<td align="left">
						<select id="executionjob" name="executionjob" style="width: 200px">
							<c:forEach items="${cp:LVB('EXECUTIONJOB')}" var="row">
								<option value="${row.value }">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>

					<td class="TDTITLE">执法证变更时间</td>
					<td align="left">
						<sj:datepicker
							id="changepasstime" name="changepasstime"
							style="width:120px;" timepicker="false" timepickerFormat="hh:mm"
							displayFormat="yy-mm-dd" validator="input" min="1"
							errorshow="请输入 执法证变更时间" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">有效期</td>
					<td align="left">
						<sj:datepicker
							id="passlife" name="passlife"
							style="width:120px;" timepicker="false" timepickerFormat="hh:mm"
							displayFormat="yy-mm-dd" validator="input" min="1"
							errorshow="请输入 有效时间" />
					</td>
					<td class="TDTITLE">人员状态</td>
					<td align="left">
						<select id="status" name="status" style="width: 200px">
							<c:forEach items="${cp:LVB('LAW_STATUS')}" var="row">
								<option value="${row.value }">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">执法证状态</td>
					<td align="left">
						<select id="staffstatus" name="staffstatus" style="width: 200px">
							<c:forEach items="${cp:LVB('LAW_STAFFSTATUS')}" var="row">
								<option value="${row.value }">
									<c:out value="${row.label}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="TDTITLE">备注</td>
					<td align="left"><s:textarea name="memo" cols="40" rows="2" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
	<script type="text/javascript">
		var menuList = ${unitsJson};
		
		function bindEvent(o1,o2,$this){
			o1.val($this.html());
			var key = $this.attr("rel");
			for (var i=0; i<menuList.length; i++) {
				if (key == menuList[i].MID) {
					o2.val(menuList[i].depno);
				}
			}
			if(getID("shadow")){
				$("#shadow").hide();
				$("#boxContent").hide();
			}
		}
		
		$("#deptname").bind('click',function(){
			var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
			if(document.getElementById("shadow") != undefined){
				$("#shadow").show();
				$("#boxContent").show();
			}else{
				$("body").append(shadow);
				$("#shadow").height(document.body.scrollHeight);
				setTimeout(function(){
					menuDisplay(menuList,"${parentUnit}");	
				},0);
			};
			$("#lists span").live('click',function(){
				var $this = $(this);
				bindEvent($("#deptname"),$("#deptid"),$this);
				$("#lists span").die("click");
			});
		});

	</script>
</html>
