<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>邮箱设置</title>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;}
         ul{position:absolute;bottom:0;left:5px;}
         ul li { list-style-type: none;float:left;margin-left:5px; }
         ul li a{display:block;height:30px;line-height:30px;width:80px;background:#F9F9F9;border:1px #ccc solid;border-bottom:0;text-align:center;text-decoration: none}
         .form-group{margin:10px;}
         .form-group .form-group-title{border-bottom:1px dotted #ccc;padding:2px;color:#2679b5;margin-bottom:10px;}
         .row{padding-left:100px;margin-top:5px;}
         .row label{display:inline-block; min-width:200px;text-align:right;}
         .row .txt{width:250px;border:solid 1px #ccc;padding:5px;}
         .btn-group input{width:100px;height:35px;background:#fff;border:1px solid #ccc;margin-left:350px;margin-top:50px;cursor:pointer;}
         .btn-group input:hover{background:#F7F7F7}
         .tip{margin-top:80px;padding-left:50px;line-height:30px;font-size:13px;color:#97A4AB}
    </style>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <script type="text/javascript">
    /**
     * 输入校验
     */
    function checkLocal(){
  	  var email = $("body").find("input[name='email']").val();
  	  if($.trim(email).length==0){
  		  DialogUtil.alert("邮箱账号不能为空");
  		  return false;
  	  }
  	  if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)){
  		  showErrorMsg("非法邮箱账号");
  		  return false;
  	  }
  	  var password = $("body").find("input[name='password']").val();
  	  if($.trim(password).length==0){
  		  DialogUtil.alert("密码不能为空");
  		  return false;
  	  }
  	  var displayName=$("body").find("input[name='displayName']").val();
	  if($.trim(displayName).length==0){
		  DialogUtil.alert("显示名称不能为空");
		  return false;
	  }
	  var senderName=$("body").find("input[name='senderName']").val();
	  if($.trim(senderName).length==0){
		  DialogUtil.alert("发信名称不能为空");
		  return false;
	  }
  	  var receiveHostAddr = $("body").find("input[name='receiveHostAddr']").val();
  	  if($.trim(receiveHostAddr).length==0){
  		  DialogUtil.alert("收件服务器地址不能为空");
  		  return false;
  	  }
  	  var receivePort = $("body").find("input[name='receivePort']").val();
  	  if($.trim(receivePort).length==0){
  		  DialogUtil.alert("收件服务器端口不能为空");
  		  return false;
  	  }
  	  if(!/^[0-9]*$/.test(receivePort)){
  		  DialogUtil.alert("收件服务器端口不合法");
  		  return false;
  	  }
  	  var sendHostAddr = $("body").find("input[name='sendHostAddr']").val(); 
  	  if($.trim(sendHostAddr).length==0){
  		  DialogUtil.alert("发件服务器地址不能为空");
  		  return false;
  	  }
  	  var sendPort = $("body").find("input[name='sendPort']").val();
  	  if($.trim(sendPort).length==0){
  		  DialogUtil.alert("发件服务器端口不能为空");
  		  return false;
  	  }
  	  if(!/^[0-9]*$/.test(sendPort)){
  		  DialogUtil.alert("发件服务器端口不合法");
  		  return false;
  	  }
  	  //检测账号是否重复
  	  if(checkDuplicateProfile()){
  		  DialogUtil.alert("该邮件账号已经存在");
  		  return false;
  	  }
  	  return true;
    }
    
    function checkDuplicateProfile(){
  	  var f = true;
  	  $.ajax({
  		  type:"post",
  		  async:false,
  		  url:"${ctx}/oa/mailprofile!checkDuplicateProfile.do",
  		  dataType:"json",
  		  data:{
  			  id:"${object.id}",
  			  email:$("body").find("input[name='email']").val()
  		  },
  		  success:function(data){
  			  f = data;
  		  }
  	  });
  	  return f;
    }
    /**
     * 邮箱配置是否能连接校验
     */
    function checkProfileAvailable(){
  	  var f = false;
  	  $.ajax({
  		  type:"post",
  		  async:false,
  		  url:"${ctx}/oa/mailprofile!checkProfileAvailable.do",
  		  dataType:"json",
  		  data:getJsonData(),
  		  success:function(data){
  			  f = data;
  			  if(!f){
  				  DialogUtil.alert("配置项测试不通过，请检测账号密码是否正确");    				  
  			  }
  		  }
  	  });
  	  return f;
    }
    
    function saveData(){
      parent.loading(true);
      setTimeout(function(){
	      if(!checkLocal()){
	    	  parent.loading(false);
	    	  return;
	      }
	      if(!checkProfileAvailable()){
	    	  parent.loading(false);
	    	  return;
	      }
	  	  $.ajax({
	  		  type:"post",
	  		  url:"${ctx}/oa/mailprofile!saveProfile.do",
	  		  dataType:"json",
	  		  data:getJsonData(),
	  		  success:function(data){
	  			  if(data){
	  				 parent.location.reload();
	  			  }else{
	  				 DialogUtil.alert("操作失败");
	  			  }
	  		  }
	  	  });
      },0);
    }
     /**
       * 获取表单参数
       */
      function getJsonData(){
    	  var param = {};
    	  param["id"]="${object.id}";
    	  param["email"] = $("body").find("input[name='email']").val();
    	  param["password"] = $("body").find("input[name='password']").val();
    	  param["displayName"] = $("body").find("input[name='displayName']").val();
    	  param["senderName"] = $("body").find("input[name='senderName']").val();
    	  param["isActive"] =  $('input[name="isActive"]:checked ').val();
    	  param["receiveHostType"] = "${object.receiveHostType}";
    	  param["receiveHostAddr"] = $("body").find("input[name='receiveHostAddr']").val();
    	  if($("body").find("input[name='receiveProtocol']").attr("checked")){
    		  param["receiveProtocol"] = "ssl";
    	  }else{
    		  param["receiveProtocol"] = "normal";
    	  }
    	  param["receivePort"] = $("body").find("input[name='receivePort']").val(); 
    	  param["sendHostAddr"] = $("body").find("input[name='sendHostAddr']").val(); 
    	  if($("body").find("input[name='sendProtocol']").attr("checked")){
    		  param["sendProtocol"] = "ssl";
    	  }else{
    		  param["sendProtocol"] = "normal";
    	  }
    	  param["sendPort"] = $("body").find("input[name='sendPort']").val();
    	  return param;
      }
    </script>
  </head>
  <body>
       <div class="form-group">
                <div class="form-group-title">
                                                      基本信息
                </div>
	            <div class="row">
	               <label>Email地址：</label>
	               <input class="txt" name="email" type="text" value="${object.email}" maxlength="100"/>
	            </div>
	            <div class="row">
	               <label>密码：</label>
	               <input class="txt" name="password" type="password" value="${object.plaintextPassword}" maxlength="50"/>
	            </div>
	            <div class="row">
	               <label>显示名称：</label>
	               <input class="txt" name="displayName" type="text" value="${object.displayName}" maxlength="30"/>
	            </div>
	            <div class="row">
	               <label>发信名称：</label>
	               <input class="txt" name="senderName" type="text" value="${object.senderName}" maxlength="30"/>
	            </div>
	            <div class="row">
	               <input name="isActive" type="radio" style="margin-left:200px;" value="T" <c:if test="${object.isActive=='T'}">checked="checked"</c:if>/>
	               <label style="min-width:30px">启用</label>
	               <input name="isActive" type="radio" value="F" <c:if test="${object.isActive=='F'}">checked="checked"</c:if>/>
	               <label style="min-width:30px">禁用</label>
	            </div>
       </div>
       <div class="form-group">
                <div class="form-group-title">
                                                      服务器信息
                </div>
	            <div class="row">
	               <label>邮箱类型：</label>
	               ${object.receiveHostType}
	            </div>
	            <div class="row">
	               <label>收件服务器：</label>
	               <input class="txt" type="text" name="receiveHostAddr" value="${object.receiveHostAddr}" maxlength="50"/>
	               <c:if test="${object.receiveHostType!='exchange'}">
	                   <input type="checkbox" name="receiveProtocol" style="margin-left:10px" <c:if test="${object.receiveProtocol=='ssl'}">checked="checked"</c:if>/>
	                   <label style="min-width:30px;">SSL</label>
	                   <label style="min-width:30px;">端口：</label>
	                   <input class="txt" type="text" name="receivePort" style="width:30px" value="${object.receivePort}"/>
	                </c:if>
	                <c:if test="${object.receiveHostType=='exchange'}">
	                   <input type="checkbox" name="receiveProtocol" style="margin-left:10px;display:none" <c:if test="${object.receiveProtocol=='ssl'}">checked="checked"</c:if>/>
	                    <input class="txt" type="text" name="receivePort" style="width:30px;display:none" value="${object.receivePort}"/>
	                </c:if>
	            </div>
	            <div class="row">
	               <label>发件服务器：</label>
	               <input class="txt" type="text" name="sendHostAddr" value="${object.sendHostAddr}" maxlength="50"/>
	                <c:if test="${object.receiveHostType!='exchange'}">
		                <input type="checkbox" name="sendProtocol" style="margin-left:10px" <c:if test="${object.sendProtocol=='ssl'}">checked="checked"</c:if>/>
		               <label style="min-width:30px;">SSL</label>
		               <label style="min-width:30px;">端口：</label>
		                <input class="txt" type="text" name="sendPort" style="width:30px" value="${object.sendPort}"/>
	                </c:if>
	                <c:if test="${object.receiveHostType=='exchange'}">
	                   <input type="checkbox" name="sendProtocol" style="margin-left:10px;display:none" <c:if test="${object.sendProtocol=='ssl'}">checked="checked"</c:if>/>
	                   <input class="txt" type="text" name="sendPort" style="width:30px;display:none" value="${object.sendPort}"/>
	                </c:if>
	            </div>
	            <div class="row">
	               <label>发件服务器身份验证：</label>
	                                           和收件服务器相同
	            </div>
       </div>
       <div class="btn-group">
           <input type="button" value="保存" onclick="saveData()">
       </div>
       <div class="tip">
          <p>温馨提示：</p>
          <p>1、当确定配置没错时，始终无法通过验证，请检查邮箱是否设置了独立密码，如果设立了密码填写邮箱的独立密码。</p>
          <p>2、如果邮箱开启了安全验证，请使用安全码作为邮箱的登录密码。</p>
          <p>3、许多邮箱厂方为了安全通常拒绝未知的第三方客户端连接邮箱服务器，这种情况我们只能发送邮件，不能接收邮件,例如网易邮箱。</p>
       </div>
  </body>
</html>