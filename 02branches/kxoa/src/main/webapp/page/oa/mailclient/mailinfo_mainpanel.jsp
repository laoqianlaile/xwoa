<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>我的邮箱</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;font-size:14px;}
       ul li { list-style-type: none; }
       .container{border:1px #929AA2 solid;margin:1px;overflow: hidden;}
       .btn-group{height:40px;background:#f5f8fa;border-bottom:1px #929AA2 solid;}
       .btn-group input{width:80px;height:30px;background:#fff;border:1px solid #ccc;cursor:pointer;margin-top:5px;margin-left:5px;}
       .btn-group input:hover{background:#F7F7F7}
        .btn-group span{float:right;display:inline-block;margin-top:8px;color:#5EB5EF;font-weight:bolder;}
       .main-content > div{float:left;height:100%}
       .mail-profile-list{background:#f5f8fa;border-right:1px #929AA2 solid;}
       .mail-profile-list span{display:inline-block;width:150px;height:25px;padding:3px;cursor:pointer;}
       .on{background:#c1d9f3;}
       .close{width:10px;height:24px;display:inline-block;background:url('${ctxStatic}/image/arrow_right.png') no-repeat center 7px;margin-bottom:-6px;margin-right:5px;}
       .open{width:10px;height:24px;display:inline-block;background:url('${ctxStatic}/image/arrow_down.png') no-repeat center 7px;margin-bottom:-6px;margin-right:5px;}
       .mail-profile-list li span:hover{background:#c1d9f3; }
       .mail-profile-list .subcollapse span{padding-left:20px ;width:133px;}
       
       .mail-list{width:350px;}
       .mail-detail{background:#f5f8fa}
    </style>
    <script type="text/javascript">
       $(function(){
    	   resizeLayout();
    	   $(".mail-profile-list").find("li.collapse").eq(0).children("span").click();
       });
       function resizeLayout(){
    	   var winHeight = $(window).height();
    	   var winWidth = $(window).width();
    	   $(".main-content").height(winHeight - $(".btn-group").outerHeight(true)-5);
    	   $(".mail-list").width(winWidth-$(".mail-profile-list").outerWidth(true)-6);
       }
       function displayEmail(displayName,emailAddr){
    	   $("#currrentEmail").html("当前邮箱："+displayName+"（"+emailAddr+"）");
    	   $("#currrentEmail").data("currrentEmail",emailAddr);
       }
       function toggle(ele){
    	   displayEmail($(ele).data("displayname"),$(ele).data("email"));
    	   var current = $(".collapse").find(".on");
    	   current.removeClass("on");
    	   current.children("i").removeClass("open").addClass("close");
    	   current.next().hide();
    	   
    	   $(ele).addClass("on");
    	   $(ele).children("i").removeClass("close").addClass("open");
    	   $(ele).next().show();
       }
       
       function goCreateEmail(){
    	   var currentEmail = $("#currrentEmail").data("currrentEmail");
    	   DialogUtil.open("写邮件","${ctx}/oa/mailinfo!mailForm.do?sendType=11&currentEmail="+currentEmail,"80%","90%");
       }
       function linkDetail(ele,location){
    	   var titleEle = $(ele).parents(".collapse").children(":first");
    	   displayEmail(titleEle.data("displayname"),titleEle.data("email"));
    	   $(".on").removeClass("on");
    	   $(ele).addClass("on");
    	   var url = "${ctx}/oa/mailinfo!list.do?s_email="+titleEle.data("email");
    	   if(location!=4){
    		   url = url +"&s_location="+location;
    		   $("#btnDisable").removeAttr("disabled");
    	   }else{
    		   $("#btnDisable").attr("disabled",true);
    	   }
    	   $("#rightDetail").attr("src",url);
       }
       function disableEmail(){
    	$("#rightDetail")[0].contentWindow.disableEmail();
       }
       function removeEmail(){
    	   $("#rightDetail")[0].contentWindow.removeEmail();
       }
       function pullEmail(){
    	    $("#loadingBar").show();
    	   $.ajax({
    		   type:"post",
    		   url:"${ctx}/oa/mailinfo!pullEmail.do",
    		   dataType:"json",
    		   data:{
    			   "email":$("#currrentEmail").data("currrentEmail")
    		   },
    		   success:function(resp){
    			   $("#loadingBar").hide();
    			   if(resp.result){
    				   var parentli;
    				   if($(".on").parent().hasClass("collapse")){
    					   parentli = $(".on").parent();
    				   }else{
    					   parentli = $(".on").parents(".collapse");
    				   }
    				   parentli.find(".subcollapse").eq(0).children("span").click();
    			   }else{
    				   if(resp.message==''){
    					   DialogUtil.alert("收取失败");
    				   }else{
    					   DialogUtil.alert(resp.message);
    				   }
    				  
    			   }
    		   }
    	   });
       }
    </script>
  </head>
  <body>
    <div class="container">
       <div class="btn-group">
         <input type="button" value="收取" onclick="pullEmail()"/>
          <input type="button" value="写邮件" onclick="goCreateEmail()"/>
          <input type="button" id="btnDisable" value="删除" onclick="disableEmail()"/>
          <input type="button" value="彻底删除" onclick="removeEmail()"/>
          <span id="currrentEmail">当前邮箱：${profile.displayName}（${profile.email}）</span>
       </div>
       <div class="main-content">
          <div class="mail-profile-list">
            <ul>
              <c:forEach var="profile" items="${profileList}">
                 <li class="collapse">
                  <span onclick="toggle(this)" data-displayname="${profile.displayName}" data-email="${profile.email}"><i class="close"></i>${profile.displayName}</span>
                  <ul style="display:none">
                     <li class="subcollapse"><span onclick="linkDetail(this,2)">收件箱<code></code></span></li>
                     <li class="subcollapse"><span onclick="linkDetail(this,3)">草稿箱</span></li>
                     <li class="subcollapse"><span onclick="linkDetail(this,1)">已发送邮件</span></li>
                     <li class="subcollapse"><span onclick="linkDetail(this,4)">已删除邮件</span></li>
                  </ul>
                </li>
              </c:forEach>
             </ul>
          </div>
          <div class="mail-list">
             <iframe style="width:100%;height:100%;" id="rightDetail" frameborder="0"></iframe>
          </div>
       </div>
    </div>
    <div id="loadingBar" style="display:none">
        <div  style="position:absolute;top:0;left:0;width:100%;height:100%;z-index:999;background:#fff;opacity: 0.0;filter: alpha(opacity=00);"></div>
	    <div  style="position:absolute;top:40%;left:40%;width:200px;height:60px;line-height:60px;padding:5px;z-index:1000;border:2px solid #4A5B79;background:#fff">
	        <img src="${ctxStatic}/image/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您加载…</span>&nbsp;
	    </div>
     </div>
  </body>
</html>