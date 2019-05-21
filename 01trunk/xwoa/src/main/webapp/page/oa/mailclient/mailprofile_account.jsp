<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>新建账号</title>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;}
        
    </style>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

    <script type="text/javascript">
      $(function(){
    	  insertRow("pop3");
    	  $("#serverType").change(function(){
    		  insertRow(this.value);
    	  });
      });
      //切换端口号
      function changePort(ele,key){
    	  var port={"pop3":110,"imap":143,"smtp":25};
    	  var SSLport={"pop3":995,"imap":993,"smtp":465};
    	  if($(ele).attr("checked")){
    		  $(ele).parent().find("input[name$='Port']").val(SSLport[key]);
    	  }else{
    		  $(ele).parent().find("input[name$='Port']").val(port[key]);
    	  } 
      }
      //添加行
      function insertRow(serverType){
    	  $("#receiveServer").remove();
    	  $("#sendServer").remove();
          if(serverType=="exchange"){
        	  $("<div/>",{"class":"row","id":"receiveServer"}).html(
          			 '<label>Exchange服务器：</label>'
          			+'<input class="txt" type="text" name="receiveHostAddr" maxlength="50"/>'
          		  ).appendTo($("form"));
    	  }else{
    		  if(serverType=="pop3"){
        		  $("<div/>",{"class":"row","id":"receiveServer"}).html(
        			 '<label>POP服务器：</label> '
        			+'<input class="txt" style="width:110px;" name="receiveHostAddr" maxlength="50"/>'
        			+'<input type="checkbox" style="margin-left:5px;" name="receiveProtocol" onclick="changePort(this,\'pop3\')"/>'
        			+'<label style="min-width:30px;">SSL</label>'
        			+'<label style="min-width:30px;">端口：</label>'
        			+'<input class="txt" type="text" name="receivePort" style="width:50px" value="110" maxlength="10"/>'
        		  ).appendTo($("form"));
        	  }
              if(serverType=="imap"){
            	  $("<div/>",{"class":"row","id":"receiveServer"}).html(
             			 '<label>IMAP服务器：</label> '
             			+'<input class="txt" style="width:110px;" name="receiveHostAddr" maxlength="50"/>'
             			+'<input type="checkbox" style="margin-left:5px;" name="receiveProtocol" onclick="changePort(this,\'imap\')"/>'
             			+'<label style="min-width:30px;">SSL</label>'
             			+'<label style="min-width:30px;">端口：</label>'
             			+'<input class="txt" type="text" name="receivePort" style="width:50px" value="143" maxlength="10"/>'
             		  ).appendTo($("form"));
        	  }
              $("<div/>",{"class":"row","id":"sendServer"}).html(
         			 '<label>SMTP服务器：</label> '
         			+'<input class="txt" style="width:110px;" name="sendHostAddr" maxlength="50"/>'
         			+'<input type="checkbox" style="margin-left:5px;" name="sendProtocol" onclick="changePort(this,\'smtp\')"/>'
         			+'<label style="min-width:30px;">SSL</label>'
         			+'<label style="min-width:30px;">端口：</label>'
         			+'<input class="txt" type="text" name="sendPort" style="width:50px" value="25" maxlength="10"/>'
         		  ).appendTo($("form"));
    	  }
      }
      /**
       * 输入校验
       */
      function checkLocal(){
    	  if($("form").find("input[name='email']").length>0){
    		  var email = $("form").find("input[name='email']").val();
        	  if($.trim(email).length==0){
        		  DialogUtil.alert("邮箱账号不能为空");
        		  return false;
        	  }
        	  if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)){
        		  showErrorMsg("非法邮箱账号");
        		  return false;
        	  }
    	  }
    	  if($("form").find("input[name='password']").length>0){
    		  var password = $("form").find("input[name='password']").val();
        	  if($.trim(password).length==0){
        		  DialogUtil.alert("密码不能为空");
        		  return false;
        	  }
    	  }
    	  if($("form").find("input[name='receiveHostAddr']").length>0){
    		  var receiveHostAddr = $("form").find("input[name='receiveHostAddr']").val();
        	  if($.trim(receiveHostAddr).length==0){
        		  DialogUtil.alert("收件服务器地址不能为空");
        		  return false;
        	  }
    	  }
    	  if($("form").find("input[name='receivePort']").length>0){
    		  var receivePort = $("form").find("input[name='receivePort']").val();
        	  if($.trim(receivePort).length==0){
        		  DialogUtil.alert("收件服务器端口不能为空");
        		  return false;
        	  }
        	  if(!/^[0-9]*$/.test(receivePort)){
        		  DialogUtil.alert("收件服务器端口不合法");
        		  return false;
        	  }
    	  }
    	  if($("form").find("input[name='sendHostAddr']").length>0){
    		  var sendHostAddr = $("form").find("input[name='sendHostAddr']").val(); 
        	  if($.trim(sendHostAddr).length==0){
        		  DialogUtil.alert("发件服务器地址不能为空");
        		  return false;
        	  }
    	  }
    	  if($("form").find("input[name='sendPort']").length>0){
    		  var sendPort = $("form").find("input[name='sendPort']").val();
        	  if($.trim(sendPort).length==0){
        		  DialogUtil.alert("发件服务器端口不能为空");
        		  return false;
        	  }
        	  if(!/^[0-9]*$/.test(sendPort)){
        		  DialogUtil.alert("发件服务器端口不合法");
        		  return false;
        	  }
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
    		  data:getJsonData(),
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
    	  var f = false;
    	  $.ajax({
    		  type:"post",
    		  async:false,
    		  url:"${ctx}/oa/mailprofile!saveProfile.do",
    		  dataType:"json",
    		  data:getJsonData(),
    		  success:function(data){
    			  f = data;
    		  }
    	  });
    	  return f;
      }
      /**
       * 获取表单参数
       */
      function getJsonData(){
    	  var param = {};
    	  param["email"] = $("form").find("input[name='email']").val();
    	  param["password"] = $("form").find("input[name='password']").val();
    	  param["displayName"] = param["email"].substring(param["email"].indexOf("@")+1,param["email"].indexOf("."))+"邮箱";
    	  param["senderName"] = param["email"].substring(0,param["email"].indexOf("@"));
    	  param["receiveHostType"] = $("#serverType").val();
    	  param["receiveHostAddr"] = $("form").find("input[name='receiveHostAddr']").val();
    	  if($("form").find("input[name='receiveProtocol']").attr("checked")){
    		  param["receiveProtocol"] = "ssl";
    	  }else{
    		  param["receiveProtocol"] = "normal";
    	  }
    	  param["receivePort"] = $("form").find("input[name='receivePort']").val(); 
    	  param["sendHostAddr"] = $("form").find("input[name='sendHostAddr']").val(); 
    	  if($("form").find("input[name='sendProtocol']").attr("checked")){
    		  param["sendProtocol"] = "ssl";
    	  }else{
    		  param["sendProtocol"] = "normal";
    	  }
    	  param["sendPort"] = $("form").find("input[name='sendPort']").val();
    	  return param;
      }
    </script>
    <style type="text/css">
     .row{height:40px;line-height:40px;}
     .row label{display:inline-block; min-width:130px;text-align:right;}
     .row .txt{width:280px;border:solid 1px #ccc;padding:2px;}
    </style>
  </head>
  <body>
      <div style="padding-left:30px;padding-top:40px;">
        <form>
         <div class="row">
           <label>接收服务器类型：</label>
           <select class="txt" style="width:285px" id="serverType" name="receiveHostType">
             <option value="pop3">POP3</option>
             <option value="imap">IMAP</option>
             <option value="exchange">Exchange</option>
           </select>
         </div>
         <div class="row">
           <label>邮件账号：</label>
           <input class="txt" type="text" maxlength="100" name="email"/>
         </div>
         <div class="row">
           <label>密码：</label>
           <input class="txt" type="password" maxlength="50" name="password"/>
         </div>
        </form>
      </div>
  </body>
</html>