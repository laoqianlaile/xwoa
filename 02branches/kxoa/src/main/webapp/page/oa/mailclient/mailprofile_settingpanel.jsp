<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>邮箱设置</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;}
       ul li { list-style-type: none; }
       h4{color:#004492;margin:10px;}
       .container{overflow:hidden;margin:10px;border:1px solid #ccc}
       .container > div{float:left;}
       .btn-group{border:1px solid #ccc;width:200px;padding:5px 5px;border-left-width: 0;border-top-width: 0;background:#F7F7F7}
       .btn-group input{width:50px;height:28px;background:#fff;border:1px solid #ccc;cursor:pointer;}
       .btn-group input:hover{background:#F7F7F7}
       .nav-list{border:1px solid #ccc;border-left-width: 0;background:#F7F7F7;margin-top:1px;}
       .nav-list li a{display:block;height:25px;width:200px;padding:5px 5px;border-bottom:1px #ccc solid;text-decoration: none;}
       .nav-list li a:hover,.current{text-decoration: none;background:#C1D9F3;color:#fff}
       .nav-content{border:1px solid #ccc;width:700px;height:500px;margin-left:1px;border-right-width: 0;border-top-width: 0;}
    </style>
    <script type="text/javascript">
    $(function(){
    	resizeLayout();
    	$(".nav-list").find("li").eq(0).children("a").click();
    });
      function popAccountAddWin(){
    	  DialogUtil.open("新建账号","${ctx}/oa/mailprofile!accountAdd.do",500,300,function(){
    			var iframe = this.iframe.contentWindow;
    			if(!iframe.checkLocal()){
    				return false;
    			}
    			if(!iframe.checkProfileAvailable()){
    				return false;
    			}
    			if(iframe.saveData()){
    				window.location.reload();
    				return true;
    			}else{
    				return false;
    			}
    			
    	  },function(){/**放个空函数**/});
      }
      function resizeLayout(){
    	  var winHeight = $(window).height();
    	  var containerHeight = winHeight - $("h4").outerHeight(true)-15;
    	  $(".container").height(containerHeight);
    	  $(".nav-list").height(containerHeight-$(".btn-group").outerHeight(true));
    	  $(".nav-content").height(containerHeight);
    	  $(".nav-content").width($(".container").width()-$(".btn-group").outerWidth(true)-2);
      }
      function linkContent(e,id){
    	  $(".current").removeClass("current");
    	  $(e).addClass("current");
    	  var url = "${ctx}/oa/mailprofile!settingForm.do?id="+id;
    	  $("iframe").attr("src",url);
      }
      function loading(f){
    	  if(f){
    		  $("#loadingBar").show();
    	  }else{
    		  $("#loadingBar").hide();
    	  }
      }
      function removeProfile(){
    	  var id = $(".current").data("profileid");
    	  if(id!=''){
    		  DialogUtil.confirm("确定要永久删除该配置项吗？",function(){
    			  $.ajax({
    				 type:"post",
    				 url:"${ctx}/oa/mailprofile!removeProfile.do",
    				 data:{"id":id},
    				 dataType:"json",
    				 success:function(resp){
    					 if(resp){
    						 window.location.reload();
    					 }else{
    						 DialogUtil.alert("操作失败");
    					 }
    				 }
    			  });
    		  });
    	  }
      }
    </script>
  </head>
  <body>
     <h4>邮箱设置</h4>
     <div class="container">
       <div>
	        <div class="btn-group">
	             <input type="button" value="新建" onclick="popAccountAddWin()"/>
	             <input type="button" value="删除" onclick="removeProfile()"/>
	        </div>    
	       <div class="nav-list">
	           <ul>
	             <c:forEach var="profile" items="${profileList}">
	                <li><a href="javascript:void(0)" data-profileid="${profile.id}" onclick="linkContent(this,'${profile.id}')">${profile.displayName}
	                 <c:if test="${profile.isActive=='F'}"><span style="color:red;font-size:12px">(禁用)</span></c:if>
	                </a>
	                </li>
	             </c:forEach>
	           </ul>
	       </div>
       </div>
       <div class="nav-content">
          <iframe border="0" frameBorder="0" style="width:100%;height:100%"></iframe>             
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