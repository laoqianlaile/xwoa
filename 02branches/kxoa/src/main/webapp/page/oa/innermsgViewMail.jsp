<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="cn">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%>  

<style>

</style>
<title>我的邮箱</title>
</head>
<body>

	

	<div class="container-fluid">
	
		<ul class="breadcrumb">
		  <li><a href="${contextPath }/app/innermsg!listMailBox.do?s_mailaccount=${param['s_mailaccount'] }&s_mailtype=${param['s_mailtype'] }">${object.c.mailaccount }<c:forEach var="type" items="${cp:DICTIONARY('MAIL_TYPE') }" varStatus="s"><c:if test="${type.datacode == object.mailtype }"> &nbsp;>>&nbsp;  ${type.datadesc }</c:if></c:forEach></a> <span class="divider">/</span></li>
		  <li class="active">${object.msgtitle }</li>
		</ul>
		
		
		<div class="row-fluid">
		
			<div class="span12 box">
			
				<div class="box-content">

					<form class="form-horizontal" id="innermsg_form" action="${pageContext.request.contextPath}/app/innermsg!saveSendMail.do" method="post" validate="true"">
						<input id="hid_innermsg_mail_add" type="hidden" name="filecodes" />
						<input type="hidden" name="emailid" value="${object.c.emailid }" />
						<input type="hidden" name="msgtitle" value="回复：${object.msgtitle }" />
						<input type="hidden" name="to" value="${object.sender }" />
						<input type="hidden" name="sender" value="${object.c.mailaccount }" />
						<input id="hid_mailtype" type="hidden" name="s_mailtype" value="${param['s_mailtype'] }"/>
						<input id="hid_mailaccount" type="hidden" name="s_mailaccount" value="${param['s_mailaccount'] }" />
						<input id="hid_mail_type" type="hidden" name="mailtype" value="O" />
						
						
						<fieldset>
							<legend>[${object.c.mailaccount }]</legend>
							
							<div class="control-group">
								<label class="control-label" for="msgtitle">标题：</label>
								
								<div class="controls">
									<label class="control-label text-left" for="msgtitle" style="width: 450px;">${object.msgtitle }</label>	
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="msgtitle">发件人：</label>
								
								<div class="controls">
									<label class="control-label text-left" for="msgtitle">${object.sender }</label>	
								</div>
							</div>
							
							<c:set var="sendC" value="0" />
							<c:set var="sendB" value="0" />
							<div class="control-group">
								<label class="control-label" for="mailpassword">收件人：</label>
								
								<div class="controls">
									<label class="control-label text-left" for="msgtitle"><c:forEach var="r" items="${object.innermsgRecipients }" varStatus="s">
											<c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'T') eq r.mailtype }">
												<span>${r.receive }<c:if test="${not s.last }">;</c:if></span>
											</c:if>
											<c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'B') eq r.mailtype }">
												<c:set var="sendB" value="1" />
											</c:if>
											<c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'C') eq r.mailtype }">
												<c:set var="sendC" value="1" />
											</c:if>
										</c:forEach></label>
								</div>
							</div>
							
							
							<c:if test="${1 eq sendC }">
								<div class="control-group">
									<label class="control-label" for="mailpassword">抄送人：</label>
									
									<div class="controls">
										<label class="control-label text-left" for="msgtitle"><c:forEach var="r" items="${object.innermsgRecipients }" varStatus="s">
												<c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'C') eq r.mailtype }">
													<span>${r.receive }<c:if test="${not s.last }">;</c:if></span>
												</c:if>
											</c:forEach></label>
									</div>
								</div>
							</c:if>
								
							<c:if test="${1 eq sendB }">
								<div class="control-group">
									<label class="control-label" for="mailpassword">密送人：</label>
									
									<div class="controls">
										<label class="control-label text-left" for="msgtitle"><c:forEach var="r" items="${object.innermsgRecipients }" varStatus="s">
												<c:if test="${cp:MAPVALUE('RECIPIENT_TYPE', 'B') eq r.mailtype }">
													<span>${r.receive }<c:if test="${not s.last }">;</c:if></span>
												</c:if>
											</c:forEach></label>
									</div>
								</div>
							</c:if>

							
							<c:if test="${not empty object.msgannexs }">
								<div class="control-group">
									<label class="control-label" for="smtpurl">附件：</label>
									
										<div class="controls">
											<c:forEach var="fi" items="${object.msgannexs }">
												<div style="margin-bottom: 10px;">
													<a target="download" filecode="${fi.fileinfo.filecode}">${fi.fileinfo.filename}.${fi.fileinfo.fileext}</a>
												</div>
											</c:forEach>	
										</div>
								</div>
							</c:if>
							<div class="control-group">
								<label class="control-label" for="msgcontent">内容：</label>
								
								<div class="controls">
									<label class="control-label text-left" for="msgcontent">${object.msgcontent }</label>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label" for="msgcontent">快捷回复：</label>
								
								<div class="controls">
									<textarea name="msgcontent" class="span6 cleditor" rows="5" cols="50"></textarea>
								</div>
							</div>
							
							
							
							<div id="innermsg_btns" class="form-actions">
								<input id="btn_o" v = 'O' type="button" class="btn btn-primary" value="快捷回复邮件" />
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


