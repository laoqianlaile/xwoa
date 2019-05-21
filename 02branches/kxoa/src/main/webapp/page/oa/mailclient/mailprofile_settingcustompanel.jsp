<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>邮箱项目定制</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
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
    </style>
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
  	
  	  return true;
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
        loading(true);
        setTimeout(function(){
        	if(!checkLocal()){
  	    	  loading(false);
  	    	  return;
  	      }
  	      if(!checkProfileAvailable()){
  	    	 loading(false);
  	    	  return;
  	      }
  	  	  $.ajax({
  	  		  type:"post",
  	  		  url:"${ctx}/oa/mailprofile!saveProfile.do",
  	  		  dataType:"json",
  	  		  data:getJsonData(),
  	  		  success:function(data){
  	  			  loading(false);
  	  			  if(!data){
  	  				DialogUtil.alert("操作失败");
  	  			  }else{
  	  				DialogUtil.alert("操作成功");
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
      	  param["isActive"] =   $("body").find("input[name='isActive']").val();
      	  param["receiveHostType"] = $("body").find("input[name='receiveHostType']").val();
      	  param["receiveHostAddr"] = $("body").find("input[name='receiveHostAddr']").val();
      	  param["receiveProtocol"] = $("body").find("input[name='receiveProtocol']").val();
      	  param["receivePort"] = $("body").find("input[name='receivePort']").val(); 
      	  param["sendHostAddr"] = $("body").find("input[name='sendHostAddr']").val(); 
      	  param["sendProtocol"] = $("body").find("input[name='sendProtocol']").val(); 
      	  param["sendPort"] = $("body").find("input[name='sendPort']").val();
      	  return param;
        }
        
       function afterWrite(emailprefix){
    	  if($("body").find("input[name='senderName']").val()==''){
    		  $("body").find("input[name='senderName']").val(emailprefix);  
    	  }
    	   $("body").find("input[name='email']").val(emailprefix+'@njgh.org');
       }
       function loading(f){
     	  if(f){
     		  $("#loadingBar").show();
     	  }else{
     		  $("#loadingBar").hide();
     	  }
       }
    </script>
  </head>
  <body>
       <!-- 隐藏区域 -->
       <div>
          <input name="id" value="${object.id}" type="hidden"/>
          <input name="isActive" value="T" type="hidden"/>
          <input name="receiveHostType" value="pop3" type="hidden"/>
          <input name="receiveHostAddr" value="mail.njgh.org" type="hidden"/>
          <input name="receiveProtocol" value="normal" type="hidden"/>
          <input name="receivePort" value="110" type="hidden"/>
          <input name="sendHostAddr" value="mail.njgh.org" type="hidden"/>
          <input name="sendProtocol" value="normal" type="hidden"/>
          <input name="sendPort" value="25" type="hidden"/>
           <input name="email"  type="hidden" value="${object.email}"/>
       </div>
       <div class="form-group">
                <div class="form-group-title">
                                                      基本信息
                </div>
	            <div class="row">
	               <label>Email地址：</label>
	               <input class="txt" name="emailprefix" type="text" value="${fn:replace(object.email,'@njgh.org','')}" maxlength="100"  onblur="afterWrite(this.value)"/>@njgh.org
	            </div>
	            <div class="row">
	               <label>密码：</label>
	               <input class="txt" name="password" type="password" value="${object.plaintextPassword}" maxlength="50"/>
	            </div>
	            <div class="row">
	               <label>显示名称：</label>
	               <input class="txt" name="displayName" type="text" maxlength="30" value="工会邮箱"/>
	            </div>
	            <div class="row">
	               <label>发信名称：</label>
	               <input class="txt" name="senderName" type="text" value="${object.senderName}" maxlength="30"/>
	            </div>
       </div>
       <div class="form-group">
                <div class="form-group-title">
                                                      服务器信息
                </div>
	            <div class="row">
	               <label>邮箱类型：</label>
	               pop3
	            </div>
	            <div class="row">
	               <label>收件服务器：</label>
	               mail.njgh.org
	               <label style="min-width:30px;">端口：</label>
	                110
	            </div>
	            <div class="row">
	               <label>发件服务器：</label>
	               mail.njgh.org
	               <label style="min-width:30px;">端口：</label>
	               25
	            </div>
	            <div class="row">
	               <label>发件服务器身份验证：</label>
	                                             和收件服务器相同
	            </div>
       </div>
       <div class="btn-group">
           <input type="button" value="保存" onclick="saveData()">
       </div>
      <div id="loadingBar" style="display:none">
        <div  style="position:absolute;top:0;left:0;width:100%;height:100%;z-index:999;background:#fff;opacity: 0.0;filter: alpha(opacity=00);"></div>
	    <div  style="position:absolute;top:40%;left:40%;width:200px;height:60px;line-height:60px;padding:5px;z-index:1000;border:2px solid #4A5B79;background:#fff">
	        <img src="${ctxStatic}/image/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您加载…</span>&nbsp;
	    </div>
     </div>
  </body>
</html>