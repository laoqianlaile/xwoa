<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	body{
	 	margin:0;
	 	padding:0; 
	 	width:100%; 
	 	height:580px;
	 	text-align:center;
	 	margin-left:auto;
	 	margin-right:auto;
	 	background-color:#F5F5F5;
	  }
	  
	.cent{ 
		margin:10% auto auto auto; 
		width:80%; 
		height:auto; 
	} 
	.center_con{
		margin:auto; 
		width:900px;
		height: 355px;
	}
	.center_p {
		float:right;
		width: 550px;
		height:300px;
		margin-top: 55px;
		text-align: left;
	
	}
	.fengge {
 	height:1px;
 	width:90%;
 	margin:30px 0px;
 	background:#D1D1D1;
 	overflow:hidden;
 }
	.center_l{
	height: 200px;
	width:900px;
	margin-left:auto;
	margin-right:auto;
	text-align: center;
	
	}
	.center_l a{
	float: left;
	width: 210px;
	height:142px;
	text-decoration: none;
	border:0 none;
	text-align: center;
	margin: 10px 80px 10px auto;
	display: block;
	color: #ffffff;
	overflow: hidden;
	font-weight: bold;
	font-size: 30px
	}
	.center_img{
	display: block;
	float: left;
	}
</style>
</head>
<body>
<div class="cent">

	<div class="center_con">
		<img class="center_img"  src="../../themes/default/authorization/p.png"/> 
		<div class="center_p" >
			<div >
			<span style="font-size:38px;color: #555555;font-weight: bold;">您当前无权限操作，可能原因：</span>
			</div>
			<div class="fengge"></div>
			<div style="font-size:22px;color: #555555;font-weight: bold;line-height: 5px">
			<p>▪无流程提交操作权限</p>
			<p>▪该办件可能已被撤回，请从该办件过程信息中确认</p> 
			<p>▪如以上都不是，请联系管理员</p>
			<!-- <p>请联系管理员</p> -->
			<p style="padding-top: 15px;">联系电话：${cp:MAPVALUE("SYSPARAM","adminPhone")} </p>
			</div>
	
		</div>
	</div>
	
	<div class="center_l">
		
<!-- 			<a  href="#" style="background-image: url('../themes/default/authorization/rtx.png');"><span ><br/><br/><br/>RTX</span></a>-->			
            <a  href="${ctx}/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I" style="background-image: url('../../themes/default/authorization/yx.png');margin-left: 50px;"><span><br/><br/><br/>邮箱</span></a>
			<a  href="${ctx}/oa/oaRemindInformation!built.do" style="background-image: url('../../themes/default/authorization/tx.png');"><span><br/><br/><br/>提醒</span></a>
			<a  href="javascript:void(0)" onclick="history.go(-1);" style="background-image: url('../../themes/default/authorization/fh.png');margin-right: 0px;"><span><br/><br/><br/>返回</span></a>
	
	</div>

</div>
</body>
</html>