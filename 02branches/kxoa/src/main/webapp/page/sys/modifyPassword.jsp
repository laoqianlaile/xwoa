<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<div class="pageContent">

	<s:form id="frm_change_pwd" action="" namespace="/sys"
		cssClass="pageForm required-validate">
		<div class="pageFormContent" layoutH="58">


			<div class="unit">
				<label>用户名</label>
				<authz:authentication property="name" />
			</div>

			<div class="unit">
				<label>旧密码*</label> <input type="password" id="password"
					name="userPwd.oldPassword" minlength="6" maxlength="20"
					alt="字母、数字、下划线 6-20位" class="required alphanumeric" autocomplete="off">
			</div>

			<div class="unit">
				<label>新密码*</label> <input type="password" id="cp_newPassword"
					name="userPwd.newPassword" minlength="6" maxlength="20"
					alt="字母、数字、下划线 6-20位" class="required alphanumeric" autocomplete="off">
			</div>

			<div class="unit">
				<label>确认新密码*</label> <input type="password" id="confirmPassword"
					equalTo="#cp_newPassword" name="userPwd.confirmPassword"
					minlength="6" maxlength="20" class="required alphanumeric" autocomplete="off">
			</div>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="sub">提交</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
<script>
	$(function() {
		$('#sub').click(
				function() {
					//alert($('#frm_change_pwd').validate().errorList.length);
					if($('#frm_change_pwd').validate().errorList.length <=0) {
					
					$.ajax({
						type : "POST",
						async : false,
						dataType : "json",
						url : "userDef!modifypwd.do?userPwd.oldPassword="
								+ $("#password").val()
								+ "&userPwd.newPassword="
								+ $("#cp_newPassword").val()
								+ "&userPwd.confirmPassword="
								+ $("#confirmPassword").val(),
						success : function(json) {
							if (json.status == '密码修改成功！') {
								alert("密码更新成功");
// 								$('div.dialog').hide();
// 								$('div.dialog').trigger($('div.close').click());
								$('div.close').click();
								
							} else {
								alert(json.status);
							}
						},
						error : function() {
							alert(json.status);
						}
					});
					}
				});
				
	});
</script>