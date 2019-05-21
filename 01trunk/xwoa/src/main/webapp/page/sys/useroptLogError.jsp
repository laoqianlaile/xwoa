<%@ include file="/page/common/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作异常</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>

<script type="text/javascript">
  $(function(){
	  var h = $(window).height();
	  var top = (h-$("div.box").height())/2-100;
	  $("div.box").css("margin-top",top);
  });
</script>
<style type="text/css">
*{margin:0;padding:0}
 body{background:#e2e2e2}
 .box{width:475px;height:224px;margin:0 auto;padding:12px 10px;
      background:url('${pageContext.request.contextPath}/images/msgboxbg.png') no-repeat}
 .box .title{width:100%;height:160px;background:url(${pageContext.request.contextPath}/images/warn.png) 50px center no-repeat }
 .box .title span{display:inline-block;height:68px;margin-top:75px;margin-left:148px;font-size:20px;font-weight: bolder;color:#CAAFAE}
  .box .toolbar{width:100%;height:64px;}
  .box a{display:inline-block;width:30%;margin-left:10%;height:100%;line-height:64px;padding-left:40px;cursor: pointer;color:#888A8C;text-decoration: none;}
  .box a#toLogin{background:url(${pageContext.request.contextPath}/images/refresh.png) 0px 18px no-repeat}
  .box a#toClose{background:url(${pageContext.request.contextPath}/images/back.png) 0px 16px no-repeat}
</style>
</head>
<body>
   <div class="box">
     <div class="title">
       <span class="msg">
       <c:if test="${errortype eq 'AQ' }">
       	403 操作安全异常<br />
       	你要查看的办件不存在.
			如果你认为这个提示是错误的， 请联系管理员。
       </c:if>
       <c:if test="${errortype eq 'SJ' }">
  		数据异常<br/>
 			你没有查看的办件权限，系统已记录本次信息。
				
					如果你认为这个提示是错误的， 请联系管理员。
					
   
  		</c:if>
  		<c:if test="${empty errortype}">
  		测试<br/>
 			你没有查看的办件权限，系统已记录本次信息。
				
					如果你认为这个提示是错误的， 请联系管理员。
					
   
  		</c:if>
    </span>
     </div>
     <div class="toolbar">
        <a id="toLogin" href="javascript:window.history.back();">返回</a>
     </div>
   </div>
</body>
</html>