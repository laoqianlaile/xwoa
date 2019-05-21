<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
		<title>南京市栖霞区行政权力网上公开透明运行系统</title>
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
	<style>
<!--
body{  margin-left: 0px;
	   margin-top: 0px;
	   margin-right: 0px;
	   margin-bottom: 0px;
	   background:#fff;
	   font-family:"黑体";
	   color:#FFF;
	   font-size:15px;
	   line-height:15px;}
.bj{ height:200px;
     width:350px;
	 position: absolute;
	 top:50%;
	 left:50%;
	 margin-top:225px;
	 margin-left:100px;
	 background: url(${pageContext.request.contextPath}/images/jsjy-bj.jpg); 
	 }
.bg{
	position: relative;
	top: 55px;
	right: -70px;	
}
.closeBtn{
	width:16px;
	height:16px;
	position:absolute;
	top:2px;
	right:2px;
	background:url(${pageContext.request.contextPath}/images/gb4.png);
	z-index:1;
	border:0px;
}
-->
</style>
	
	</head> 
	<body>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15%">
					&nbsp;
				</td>
				<td>
					<img src="${pageContext.request.contextPath}/images/qx2.jpg" border="0" usemap="#Map">
				</td>
				<td width="15%">
					&nbsp;
				</td>
			</tr>
		</table>
		<map name="Map">
   <area shape="rect" coords="128,270,243,399" href="#" onclick="showSystem('Qlyx');"/>
  <area shape="rect" coords="268,270,362,401" href="#" onclick="showSystem('DZJC');"/>
  <area shape="rect" coords="404,273,490,400" href="#" onclick="showSystem('FZJD');"/>
  <area shape="rect" coords="513,272,613,403" href="#" onclick="showSystem('XZXK');"/>
  <area shape="rect" coords="629,275,721,406" href="#" onclick="showSystem('XZCF');"/>
  <area shape="rect" coords="752,274,840,406" href="#" onclick="showSystem('Zhgl');"/>
		</map>

<div id="alert" style="display: none;" class="alert">   
    <div class="bj">
    <s:form action="/j_spring_security_check" method="post" focus="loginName" onsubmit="return checkForm();" >
			<s:hidden name="sysType" id="sysType" value=""/>
    <table width="250px%" border="0" cellspacing="0" cellpadding="0" class="bg">
  <tr>
    <td width="50" height="45" >用户名</td>
    <td ><input type="text" id="username" name="j_username" value="${SPRING_SECURITY_LAST_USERNAME}" style="width:150px; height:19px;background-color:#fff;border:1px #7fb8dc solid; line-height:22px"/> </td>
  </tr>
  <tr>
    <td width="50" height="45">密&nbsp;&nbsp;码</td>
    <td><input type="password" name="j_password" id="password" style="width:150px; height:19px;background-color:#fff;border:1px #7fb8dc solid; line-height:22px"/></td>
  </tr>
  <tr>
  </tr>
  <tr>
     <td colspan="3">
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="login" id="button1" value=""  style="width:57px; height:20px;border:0px; background-image: url(${pageContext.request.contextPath}/images/dr_03.png);"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" name="button2" id="button2" value=""  style="width:57px; height:20px;border:0px; background-image: url(${pageContext.request.contextPath}/images/qk_03.png);"/>
            <br>&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span>
            </td>
  </tr>
</table>
<input name="closeBtn" id="closeBtn" value="" class="closeBtn" onclick="closeAlert('alert');"/>
</s:form>
</div>
    </div>

	</body>
	<script type="text/javascript">	
	function showSystem(systype){			
			document.getElementById("sysType").value = systype;
			setAlertStyle('alert');
	}	
	function checkForm() {
		if ($("#username").val()== '') {
			$("#errorInfo").html("* 请输入用户名");
			$("#username").focus();
			return false;
		}

		if ($("#password").val()=='') {
			$("#errorInfo").html("* 请输入密码");
			$("#password").focus();
			return false;
		}
		return true;
	}	
	</script>
</html>