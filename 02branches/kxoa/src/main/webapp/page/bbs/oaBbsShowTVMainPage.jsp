<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link
	href="${pageContext.request.contextPath}/styles/default/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/styles/default/css/tv/tv.css"
	rel="stylesheet" type="text/css" />

<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/tv/tv.js"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<title>视频节目</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" style="display: block;">
			<div style="width: 20%; position: absolute; top: 20%;">
				<input type="text" name="name" class="form-control" id="search"
					placeholder="视频名称或关键字">
			</div>
			<div style="width: 73%;; position: relative; top: 10px; left: 25%;">
				<button type="submit" class="btn btn-default" id="btn" style="height:35px !important;">搜索</button>
				<!-- <a class="pull-right btn navbar-btn btn-primary"
					style="position: absolute; right: 1%;color:white;height:20px;" href="/book/add"><span
					class="glyphicon glyphicon-cloud-upload"></span> 添加视频</a> -->
			</div>
		</div>
	</nav>
	<div id="focus_background">
		<div id="focus_box">
			<img alt=""
				src="${contextPath}/${objList[0].smallImage }"
				id="focus_box_background" /> <span class="prev"></span> <span
				class="next"></span>
			<ul class="smal_pic">
			    <c:forEach items="${objList }" varStatus="i" var="info" begin="0" end="5">
				<li><a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${info.no}"> <img style="width:66px;height:44px;"
						src="${contextPath}/${info.smallImage}"
						alt=""
						backImg="${contextPath}/${info.bigImage}"
						backColor="#704F84" />
				</a></li>
				</c:forEach>
			</ul>
			<span class="ico"></span>
			<div class="focus_title">
			   <c:forEach items="${objList }" varStatus="i" var="info" begin="0" end="5">
				<a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${info.no}" style="display: block">${info.title} </a> 
			   </c:forEach>
			   <script type="text/javascript">
			    if(${objList.size()>1}){
			    	$('.focus_title a').eq(0).show().siblings('a').hide();
			    }
			   </script>
			</div>
		</div>
		<div id="focus_left">
	        <div class="mod_hd">
	           <span>视频排行榜</span>
	        </div>
	        <ul class="mod_rank">
	          <c:forEach items="${objList }" varStatus="i" var="info" begin="0" end="6">
	           <li>
	             <em class="top_one">${i.index+1 }</em>
	             <a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${info.no}">${info.title }</a>
	             <span class="click-rate">
	                <span class="click-rate_icon"></span><em>${info.clickNum }</em>
	             </span>
	            </li>
	          </c:forEach>
	        </ul>
	
	    </div>
	</div>
	
	<s:iterator value="infoList" var="info1" status="status">
	  <s:if test="#status.odd==true">
	<div class="hot_box">
	
		<div class="hot_left">
		  
			<div class="hot_l_title">
			   <a class="tabbox_image">
			      <img alt="" src="${pageContext.request.contextPath}/styles/default/css/tvimage/<s:property value="#status.index+1"/>.jpg">
				</a>
				<div class="tabbox">
					<a href="#" class="hot_style">${info1.datavalue}</a>
				</div>
				<a style="font-size:14px;  font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;float:right;line-height: 28px;color:#3287BD;" href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do?typeSearch=${info1.datacode}" class="hot_style">更多></a>
			</div>
		  </s:if>
			<s:if test="#status.odd==false">
			 <ul class="public" style="display: block">
			<s:iterator value="info1" var="info">
				<li>
				<a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${info.no}" class="pic"> <span class="play_icon"></span> <img
						src="${contextPath}/${info.smallImage}"
						alt="" /> <span class="masktxt"> ${info.title} </span>
				</a> <a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${info.no}" class="pic_title">  ${info.title}
				</a>
				<%-- <div class="clicknum">${info.clickNum }</div>
				<span class="clicknum_icon"></span> --%>
				</li>
			</s:iterator>
				</ul>
			
	       
        </div>
     </div>
     </s:if>
     </s:iterator>
</body>
<script type="text/javascript">
       $(function() {
       	$('#btn').click(function(){
       	         window.location.href='${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do?s_search='+$("#search").val();  
           });
       });
       $(document).ready(
   			function() {
   				init = setInterval("FrameUtils.initialize(window, init)",
   						MyConstant.initTimeForAdjustHeight);
   			});
   	
</script>
</html>