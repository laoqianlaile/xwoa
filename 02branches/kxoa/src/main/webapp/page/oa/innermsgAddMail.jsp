<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%>  

<title>我的邮箱</title>
</head>
<body class="sub-flow">

	

	<div class="container-fluid">
	
		<ul class="breadcrumb">
		  <li><a href="${contextPath }/app/innermsg!listMailBox.do?s_mailaccount=${param['s_mailaccount'] }&s_mailtype=${param['s_mailtype'] }">${object.c.mailaccount }<c:forEach var="type" items="${cp:DICTIONARY('MAIL_TYPE') }" varStatus="s"><c:if test="${type.datacode == object.mailtype }"> &nbsp;>>&nbsp;  ${type.datadesc }</c:if></c:forEach></a> <span class="divider">/</span></li>
		  <li class="active">撰写新邮件[${object.c.mailaccount }]</li>
		</ul>
		
		
		<div class="row-fluid">
		
			<div class="span12 box">
			
				<div class="box-content">

					<form class="form-horizontal" id="innermsg_form" action="${pageContext.request.contextPath}/app/innermsg!saveSendMail.do" method="post" validate="true">
						<input id="hid_innermsg_mail_add" type="hidden" name="filecodes" />
				        <input type="hidden" name="msgcode" value="${object.msgcode }" />
						<input type="hidden" name="emailid" value="${object.c.emailid }" />
						<input id="hid_mailtype" type="hidden" name="s_mailtype" value="${param['s_mailtype'] }"/>
						<input id="hid_mailaccount" type="hidden" name="s_mailaccount" value="${param['s_mailaccount'] }" />
						<input type="hidden" name="sender" value="${object.c.mailaccount }" />
						<input id="hid_mail_type" type="hidden" name="mailtype" value="O" />
						
						
						<fieldset>
							<legend>撰写新邮件[${object.c.mailaccount }]</legend>
							
							<div class="control-group">
								<label class="control-label" for="mailaccount">标题：</label>
								
								<div class="controls">
									<input type="text" name="msgtitle" id="msgtitle" class="focused span8 required" value="${object.msgtitle }" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="mailpassword">收件人：</label>
								
								<div class="controls">
									<input type="text" name="to" class="span8" value='<c:forEach var="r" items="${object.innermsgRecipients }"><c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'T') eq r.mailtype }">${r.receive };</c:if></c:forEach>' />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="mailpassword">抄送人：</label>
								
								<div class="controls">
									<input type="text" name="cc" class="span8" value='<c:forEach var="r" items="${object.innermsgRecipients }"><c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'C') eq r.mailtype }">${r.receive };</c:if></c:forEach>' />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="mailpassword">密送人：</label>
								
								<div class="controls">
									<input type="text" name="bcc" class="span8" value='<c:forEach var="r" items="${object.innermsgRecipients }"><c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'B') eq r.mailtype }">${r.receive };</c:if></c:forEach>' />
								</div>
							</div>

							
							<div class="control-group">
								<label class="control-label" for="smtpurl">附件：</label>
								
									<div class="controls">
										<input type="file" id="upload-fileinfo" optID="MSG" inputID="hid_innermsg_mail_add" />
									</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="smtpport">内容：</label>
								
								<div class="controls">
									<textarea name="msgcontent" class="span6 cleditor" rows="5" cols="50">${object.msgcontent }</textarea>
								</div>
							</div>
							
							
							
							<div id="innermsg_btns" class="form-actions">
								<input id="btn_o" v = 'O' type="button" class="btn btn-primary" value="发送邮件" />
								<input id="btn_d" v = 'D' type="button" class="btn btn-primary" value="存草稿" />
								<button class="btn" onclick="javascript:history.back();return false;">返回</button>
							</div>
						</fieldset>
					</form>

				</div>
			
			</div>
		
		</div>
				
	</div>

<%@ include file="/page/common/charisma-js.jsp"%> 

<script>
	$(function(){
		//将类型赋值
		$('#innermsg_btns input.btn-primary').click(function(){
			$('#hid_mail_type').val($(this).attr('v'));
			
			$('#innermsg_form').submit();
		});
	});
</script>
</body>

</html>


