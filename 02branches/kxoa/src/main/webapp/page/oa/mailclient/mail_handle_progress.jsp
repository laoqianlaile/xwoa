<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
     <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
     <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
     <script>
        $(function(){
        	setTimeout(function(){
        		var mailFuncIframe = $(top.document).find("#firstPage").children("div:visible").find("iframe");
        		var mailListIframe = mailFuncIframe.contents().find("#rightDetail");
        		mailListIframe.attr("src",mailListIframe.attr("src"));
        		DialogUtil.close();
        	},1000);
        });
     </script>
  </head>
  <body>
  <div id="loadingBar">
        <div  style="position:absolute;top:0;left:0;width:100%;height:100%;z-index:999;background:#fff;opacity: 0.0;filter: alpha(opacity=00);"></div>
	    <div  style="position:absolute;top:40%;left:40%;width:200px;height:60px;line-height:60px;padding:5px;z-index:1000;border:2px solid #4A5B79;background:#fff">
	        <img src="${ctxStatic}/image/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您处理…</span>&nbsp;
	    </div>
     </div>
  </body>
</html>