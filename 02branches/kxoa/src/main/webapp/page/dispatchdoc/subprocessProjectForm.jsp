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

<div class="pageContent">
	<s:form action="subprocessProject" method="post" namespace="/dispatchdoc"  id="subprocessProjectForm">

		<input type="button" class="btn" id="save" value="保存"/>
		<input type="button" class="btn btnWide" id="saveAndSubmit" value="保存并提交"/>
<%-- 		<%@ include file="/page/powerruntime/optcommon/optBaseInfoForm.jsp"%> --%>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="preinstid" name="preinstid" value="${object.preinstid}" />
			<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
			<input type="hidden" id="prenodeinstid" name="prenodeinstid" value="${object.prenodeinstid}" />
			<input type="hidden" id="supDjId" name="supDjId" value="${object.supDjId}" />
			<input type="hidden" id="optId" name="optBaseInfo.optId" value="${optId}" />
			<input type="hidden" id="flowCode" name="optBaseInfo.flowCode" value="${object.optBaseInfo.flowCode}" />
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="">
			<input type="hidden" id="processType" name="processType" value="${processType }">
			<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId" value="" />
		
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding:4px 8px 3px;">
				<b>委托评估登记</b>
			</legend>
			<table width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">
						主办科室<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<input type="hidden" id="headunitcode" name="headunitcode" value="${object.headunitcode }">
						<input type="text" id="deptname" name="deptname" value="${cp:MAPVALUE('unitcode',object.headunitcode)}" size="30">
					</td>
				
					<td class="addTd">
						受理时间<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<input type="text" class="Wdate"  id="accpetDate" name="accpetDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.accpetDate}" pattern="yyyy-MM-dd  HH:mm"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm'})" placeholder="选择日期">
<%-- 						<sj:datepicker --%>
<%-- 							id="accpetDate" name="accpetDate"  value="%{object.accpetDate }" --%>
<%-- 							style="width:120px;" timepicker="false" timepickerFormat="hh:mm" --%>
<%-- 							displayFormat="yy-mm-dd" validator="input" min="1"  --%>
<%-- 							errorshow="请输入 有效时间"/> --%>
					</td>
					
				</tr>
				<tr>
					<td class="addTd">
						项目申请人<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<input type="text" id="projectUnitName" name="projectUnitName" maxlength="100" value="${object.projectUnitName}" size="40"/>
					</td>
					
				</tr>
				<tr>
					<td class="addTd">
						项目名称<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<input type="text" id="projectName" name="projectName" maxlength="100" value="${object.projectName}" size="40"/>
					</td>
					
				</tr>
				<tr>
					<td class="addTd">
						项目类型<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<select id="projectType" name=projectType style="width: 120px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('PROJECT_TYPE')}">
									<option value="${row.key}" ${(object.projectType eq row.key or (empty object.projectType and row.key eq '0')) ? 'selected = "selected"' : ''}>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select>
						
					</td>
					
				</tr>
					<tr>
					<td class="addTd">
						评估理由<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="evaluationReason"
							cols="40" rows="2" style="width:90%;height:40px;"
							value="%{object.evaluationReason}" />
					</td>
					
				</tr>
				
					<tr>
					<td class="addTd">
						评估内容<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="evaluationContent"
							cols="40" rows="2" style="width:90%;height:40px;"
							value="%{object.evaluationContent}" />
					</td>
					
				</tr>
				
				<tr>
					<td class="addTd">
						办理意见<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="opinions"
							cols="40" rows="2" style="width:90%;height:40px;"
							value="%{object.opinions}" />
					</td>
					
				</tr>
			
		
				
			</table>

	</s:form>
</div>
</body>
<script type="text/javascript">
		var menuList = ${unitsJson};
		
		function bindEvent(o1,o2,$this){
			o1.val($this.html());
			o2.val($this.attr("rel"));
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
				bindEvent($("#deptname"),$("#headunitcode"),$this);
				$("#lists span").die("click");
			});
		});

		$(document).ready(function() {
			function doSaveCheck() {
				if ("" == $.trim($("#headunitcode").val())) {
					alert("请选择主办科室");
					return false;
				}
				if ("" == $.trim($("#accpetDate").val())) {
					alert("请输入受理时间");
					return false;
				}
				if ("" == $.trim($("#projectUnitName").val())) {
					alert("请输入项目申请人");
					return false;
				}
				if ("" == $.trim($("#projectName").val())) {
					alert("请输入项目名称");
					return false;
				}
				if ("" == $.trim($("#projectType").val())) {
					alert("请选择项目类型");
					return false;
				}
				if ("" == $("#evaluationReason").val()) {
					alert("请输入评估原因");
					return false;
				}
				if ("" == $("#evaluationContent").val()) {
					alert("请输入评估内容");
					return false;
				}
				if ("" == $("#opinions").val()) {
					alert("请输入办理意见");
					return false;
				}
				
				return true;
			}
			
			function doSubmitCheck() {
				if (!doSaveCheck()) {
					return false;
				}
				
				
				return true;
			}
			
			function cloneProjectInfo() { // 将iframe里的字段（除_djId外）克隆到当前页面
				//$("#opinions").val() = null;
				//$("#evaluationContent").val() = null;
				//$("#evaluationReason").val() = null;
			}
			
			$("#save,#saveAndSubmit").click(function() {
				var id = $(this).attr("id");
				if ("save" == id) { // 保存
					if (doSaveCheck()) {
						//cloneProjectInfo();
						$("#subprocessProjectForm").attr("action", "subprocessProject!savePermitReg.do");
						$("#subprocessProjectForm").submit();
					}
				} else if ('saveAndSubmit' == id) { // 提交
					if (doSaveCheck() && doSubmitCheck()) { // 校验通过
						//cloneProjectInfo();
						$("#subprocessProjectForm").attr("action", "subprocessProject!saveAndSubmitPermit.do");
						$("#subprocessProjectForm").submit();
					}
				}
				
				return false;
			});
		});
	</script>
</html>