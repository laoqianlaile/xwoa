<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title>欢迎使用帮助平台</title>
<link href="${pageContext.request.contextPath}/themes/oaHelpInfo/style.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
</head>

<body style="width:100%;padding-left:0px;margin-right:0px;height:100%;" onload="reinitIframe()">
<form action="" id="oaHelpinfoForm" method="post" style="margin:0;">
<input type="hidden" name="s_columnType" value="${s_columnType}">
</form>
	<div class="head">
	 <div class="width">
	<ul id="infoTab">
	            <li class="logo"></li>
				<li class="indexPage" url="<%=request.getContextPath()%>/oa/oaHelpinfo!list.do" ${empty s_columnType ? "class='select'" : ""}>
				<a href="<%=request.getContextPath()%>/oa/oaHelpinfo!list.do" style="text-decoration: none;color:white;font-size:14px;">首页</a></li>
		<c:forEach var="row" items="${cp:DICTIONARY('columntype')}" >
				<li url="<%=request.getContextPath()%>/oa/oaHelpinfo!list4iframe.do?s_columnType=${row.datacode}&s_isgood=${s_isgood}"
				<c:if test="${s_columnType eq row.datacode }">class='select'</c:if>>
		 		${row.datavalue}</li>
		</c:forEach>
				<li class="search" style="position: absolute;right:0px;;height:23px;line-height:23px;margin-top:6px;">&nbsp;&nbsp;
				<input type="text" style="height:20px;border:none;outline:none;width:80px;background: transparent;padding:0;position:absolute;" name="search" placeholder="搜索" id="search" value="${s_search}"></li>
	</ul>
	 </div>
  </div>
  <div  class="body">
			<iframe src="<%=request.getContextPath()%>/oa/oaHelpinfo!list4iframe.do"
			width="100%" frameborder="0" name="ifr" id="ifr"
			onload="reinitIframe(this);"
			scrolling="no" border="0" marginwidth="0"></iframe>
  </div>
  <div class="foot" id="foot">
    <h5 style="line-height:42px;">技术支持：江苏南大先腾信息产业有限公司</h5>
  </div>
</body>
<script type="text/javascript">
function reinitIframe(iframe){
	try{

	var bHeight = iframe.contentWindow.document.body.scrollHeight;

	var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

	var height = Math.max(bHeight, dHeight);

	iframe.height =  height;

	}catch (ex){}

	}
	function scroll(){
		$('html,body').animate({
            scrollTop: $(document).height() 
        },
        1500);
        return false;
	}
	function scrollTop(){
		$('html,body').animate({
            scrollTop: parseInt(0)
        },
        1500);
        return false;
	}
	function returnScroll(){
		  return $('body').scrollTop();
		}
$(function(){
	var columnType='${s_columnType}';
	if(columnType!='')
		 $('#infoTab li').each(function(){
	         var $this = $(this);
	         var url=$this.attr('url');
	         if(url){
	              if(url.indexOf(columnType)>-1){
	        	    $("#infoTab li").removeClass("select");
	        	    $this.addClass("select");
	        	    $("#ifr").attr("src",$this.attr("url"));
	              }	        	 
	         }
	 		 });
});
$(function (){
	$("#infoTab").click(function(e){
		var e = e || window.event;
		var target = e.srcElement || e.target;
		if( target.tagName.toLowerCase()=="li" && $(target).attr("class") != "disable"&&$(target).attr("class") != "indexPage" ){
			if( !$(target).hasClass("select") ){
				$("#infoTab li").removeClass("select");
				$(target).addClass("select");
				$("#ifr").attr("src",$(target).attr("url"));
			}
		}
	});
});
$(function () {
	 var $search =$('#search');
	 $search.bind('keypress', function (event) {
		if (event.keyCode == "13") {
			var url='${pageContext.request.contextPath}/oa/oaHelpinfo!search.do?search="'+encodeURI(encodeURI(""+$search.val()+""));
			$("#ifr").attr("src",url);
		}
	});
	 $search.bind('focus',function(){
		 $('.search').addClass('focus');
	 });
	 $search.bind('blur',function(){
		 $('.search').removeClass('focus');
	 });
});
</script>

</html>