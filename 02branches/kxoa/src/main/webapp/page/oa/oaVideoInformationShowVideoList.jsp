
<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

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

<title>视频节目</title>
</head>
<body style="background-color:#F0F0F0;">
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
	
	<div
		style="width: 100%;font-size:16px;margin-bottom:15px;margin-top:15px;">
		您当前的位置：<a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoMainpage.do" style="font-size:16px;">首页</a> ><a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoMainpage.do" style="font-size:16px;">视频节目</a> >
        <a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideoList.do?s_no=${objList[0].no}" style="font-size:16px;">${objList[0].title}</a>
	</div>
	
    <div style="margin: 0 auto; width: 100%; position: relative;background-color: #FBFBFB;">
		<div id="play" class="play_div" >
			<object type="application/x-shockwave-flash"
				data="${pageContext.request.contextPath}/upload/tools/vcastr3.swf"
				width="500" height="419" id="vcastr3">
				<param name="movie"
					value="${pageContext.request.contextPath}/upload/tools/vcastr3.swf" />
				<param name="allowFullScreen" value="true" />
				<param name="wmode" value="opaque">
				<param name="FlashVars"
					value="xml=	<vcastr>
								<channel>
								<item>
								<source>${object.videoPath }</source>
								<duration></duration>
								<title>咱们结婚吧</title>
								</item>
								</channel>
								<config>
								<bufferTime>4</bufferTime>           
						        <contralPanelAlpha>0.75</contralPanelAlpha>   
						        <controlPanelBgColor>0x333333</controlPanelBgColor>    
						        <controlPanelBtnColor>0xffffff</controlPanelBtnColor> 
						        <contralPanelBtnGlowColro>0x333333</contralPanelBtnGlowColro>  
						        <controlPanelMode>float</controlPanelMode>    
						        <defautVolume>0.8</defautVolume>   
						        <isAutoPlay>false</isAutoPlay>           
						        <isLoadBegin>true</isLoadBegin>    
						        <isShowAbout>true</isShowAbout> 
						        <scaleMode>exactFil</scaleMode>
								</config>
								</vcastr>" />
			</object>
		</div>
		<div class="play_remark" style="position: absolute; height: 100%; left: 70%; width: 30%;top:0;background:#1C1C1C">
		   <div style="padding-top:32px;padding-bottom:16px;padding-left:32px;border-bottom:1px solid #252525;">
		      <h1 style="font-size:18px;color:#999;font-weight:normal;font: 20px/1.5 'Microsoft YaHei';">${object.title}</h1>
		      <ul>
		        <li style="margin-top:8px;height:18px;color:#666; overflow:hidden;font-family:Helvetica,Arial;">点击次数:${object.clickNum}次</li>
		        <li style="margin-top:8px;height:18px; color:#666;overflow:hidden;font-family:Helvetica,Arial;">上传日期:<fmt:formatDate value='${object.creatertime}' pattern='yyyy-MM-dd' /></li>
		      </ul>
		   </div>
		   <div style="padding-top:15px;padding-bottom:22px;padding-left:32px;border-bottom:1px solid #252525;">
		     <div style="font-family:Helvetica,Arial;color:#666;">视频介绍:</div>
		     <div style="margin-top:6px;line-height:22px;color:#666;overflow:hidden; word-wrap:break-word;">${object.remark} </div>
		   </div>
		</div>
		<a class="open_close_icon"></a>
		<a class="open_close_icon_panel" style="display:none;"><i>展开列表</i></a>
    </div>
     <div class="hot_box">
		<div class="hot_left" style="background-color: #FBFBFB;margin-bottom:30px;">
			<div class="hot_l_title">
				<div class="tabbox">
					<a href="" class="hot_style">视频剧集</a>
				</div>
			</div>
			<ul class="public">
				<c:forEach items="${objList }" varStatus="i" var="info" begin="0" end="9">
				<li>
				<a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!show.do?id=${info.id}" class="pic"> <span class="play_icon"></span> <img
						src="${contextPath}/${info.smallimage}"
						alt="" /> <span class="masktxt"> ${info.title} </span>
				</a> <a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!showVideo.do?s_no=${info.no}" class="pic_title">  ${info.title}
				</a>
				</li>
			    </c:forEach>			
			</ul>

		</div>
	</div> 
	<div style="width: 100%; ">
		<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
		<iframe id="tabFrames1" name="tabFrames"
			src="<%=request.getContextPath()%>/oa/oaLeaveMessage!replayList.do?s_djid=${objList[0].id}&s_infoType=video"
			onload="onLoadHeight(this);" width="100%" frameborder="0"
			style="margin: 0px auto;" scrolling="no" border="0" marginwidth="0"></iframe>
	</div>
</body>
<script type="text/javascript">
        $(document).ready(
			function() {
				init = setInterval("FrameUtils.initialize(window, init)",
						MyConstant.initTimeForAdjustHeight);
			});
        $('.open_close_icon').bind('click',function(){
        	$('.play_remark').css("display","none");
        	$('.open_close_icon').css('display','none');
        	$('.play_div').removeClass('play_div').addClass('play_div_end');
        	$('.open_close_icon_panel').css('display','block')
        	;
        });
        $('.open_close_icon_panel').bind('click',function(){
        	$('.play_div_end').removeClass('play_div_end').addClass('play_div');
        	$('.play_remark').css("display","block");
        	$('.open_close_icon').css('display','block');
        	$('.open_close_icon_panel').css('display','none');
        });
		function onLoadHeight(t) {
			var height = window.frames['tabFrames'].document.body.scrollHeight + 16;
			if (height > 500) {
				t.height = height;
			} else {
				t.height = 500;
			}
		}

       $(function() {
       	$('#btn').click(function(){
       	         window.location.href='oa/oaSubvideoInformation!oasubvideoShowList.do?s_search='+$("#search").val();  
           });
       });
</script>
</html>