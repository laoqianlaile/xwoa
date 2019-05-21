<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新疆·交通 阳光机关，欢迎您！</title>
<script src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/index.css" rel="stylesheet" type="text/css" />
  
<script type="text/javascript">
 document.onkeydown=function(e){
	var events = window.event || e;
	var code = events.keyCode ||events.which;
	if(code == 13){
		$("#btn").click();
	}
 };
 function tjbtn(){
	 
	 if($('#username').val() == ''){
		 alert("用户名不能为空！");
		 return false;
	 }else if($('#password').val() == ''){
		 alert("密码不能为空！");
		 return false;
	 }
	 document.getElementById("form").submit();
 }

</script>

</head>
<body>

    <div id="div_login_alert" class="alert alert-danger alert-dismissable login-msg">
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
		        <c:choose>
		            <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
		                <span style="color:red; font-family:Microsoft YaHei!important; font-size: 14px; ">用户名或者密码错误</span>
		            </c:when>
		            <c:otherwise>
		                <span></span>
		            </c:otherwise>
		        </c:choose>
		</c:if>
		<c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
             <span></span>
	    </c:if>
	</div>
	


 <div class="content">
<s:form action="/j_spring_security_check" method="post" id="form" focus="loginName">
<html:hidden property="action" />
 <table width="100%" border="0" cellspacing="0" cellpadding="0" style="position:relative;top:310px;
        left:140px; width:110px; height:115px;">
          <tr>
            <td><input type="text" id="username" name="j_username" class="required" style="width:130px; height:20px" value="${SPRING_SECURITY_LAST_USERNAME}" ></td>
          </tr>
          <tr>
            <td><input type="password" name="j_password" id="password" class="required" style="width:130px; height:20px" /></td>
          </tr>
          <tr>
            <td><input type="button" id="btn" class="td2" name="login" onclick="return tjbtn();"/></td>
          </tr>
  </table>
</s:form>
</div>
</body>

<script type="text/javascript">
$(document).ready(function() {
    $("#loginForm").validate({
       // errorLabelContainer: "#messageBox",      wrapper: "li",
        rules: {
            j_username: "required",
            j_password: "required"},
        messages: {
            j_username: "请输入用户名",
            j_password: "请输入密码"} ,
        showErrors: function(errorMap, errorList) {
            var errorTips = $('div');
            var info='';
            $.each(errorList,function(i,e){
                   info += e?'<li>'+e.message+'</li>':'';
            });
            errorTips.html(info);
            var screenWidth = $(document).width();
            errorTips.css({'position':'absolute','left':screenWidth/2-200,'top':'0px','width':'400px','background':'#ffffcc','font-size':'10pt'});
           errorTips.appendTo('body');
            (errorList.length==0)?errorTips.hide():errorTips.show();
        },
        success: function(label) {
            //alert("提交成功");
        }
    });
    
    <c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
	   $('#div_login_alert').hide();
	</c:if>
	
	var hide = function() {
		setTimeout(function() {
	        $('div.login-msg').fadeOut();
	    }, 2000);
	};
	
	
	hide();
	var show = function(text) {
		$('#div_login_alert>span').text(text);
		$('#div_login_alert').show();
		
		
		hide();
	};
	
		
});

function submit(event) {
	
};

</script>
</html>