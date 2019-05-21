<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="textml; charset=UTF-8">
<title></title>
</head>

<body style="height:100%">
	<h1
		style="font-size: 24px; width: 100%; height: 37px; padding-left: 10%; color: #333; font-weight: normal;">${title}</h1>
	<div
		style="width: 100%; padding-left: 10%; position: relative; bottom: 10px;">
		您当前的位置：<a href="">首页</a> ><a href="">电影</a>

	</div>
	<div
		style="margin: 0 auto; width: 100%; height: 420px; position: relative;">
		<div id="play"
			style="width: 50%; position: absolute; left: 30%; height: 400px; margin: 0px auto; background-color: #000000">
			<object type="application/x-shockwave-flash"
				data="${pageContext.request.contextPath}/upload/tools/vcastr3.swf"
				width="500" height="400" id="vcastr3">
				<param name="movie"
					value="${pageContext.request.contextPath}/upload/tools/vcastr3.swf" />
				<param name="allowFullScreen" value="true" />
				<param name="FlashVars"
					value="xml=	<vcastr>
								<channel>
								<item>
								<source>${videoPaths }</source>
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
						        <scaleMode>showAll</scaleMode>
								</config>
								</vcastr>" />
			</object>




			<%--  <object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/upload/tools/vcastr3.swf"
						style="height:400px;"  id="vcastr3">
						<param name="movie" value="${pageContext.request.contextPath}/upload/tools/vcastr3.swf" />
						<param name="allowFullScreen" value="true" />
						<param name="isAutoPlay" value="false" />
						<param name="FlashVars"value="xml= 
						<vcastr><channel><item><source>${videoPaths }</source> 
						 </item></channel></vcastr>" />
					</object> --%>
		</div>
		<div style="height: 100%; position: absolute; left: 10%; width: 20%;">
			<%@ include file="/page/oa/oavideoCommon.jsp"%>
		</div>
	</div>
	<div style="width: 71%; height: 100px; position: relative; left: 10%;">
		<h3
			style="color: #333; line-height: 30px; font-weight: normal; font-size: 15px; word-break: break-all;">简介:
			《咱们结婚吧》是由中央电视台、湖南广播电视台、湖南广播电视台卫视频道、北京华录百纳影视股份有限公司、北京完美蓬瑞影视文化有限公司联合摄制，中央电视台、湖南广播电视台、湖南广播电视台卫视频道、北京华录百纳影视股份有限公司、北...
		</h3>
	</div>
	<!--   是否可以留言回复 -->
	<!-- UY BEGIN -->
	<!-- <div id="uyan_frame" style="width:75%;position:relative;left:10%;"></div>
	<script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=2026916"></script> -->
	<!-- UY END -->
	<%-- <c:if test="${'Y' eq isAllowComment}">  --%>
	<!-- 	  <div style="width:100%;height:40px; padding-left:16%;">
 	  <input type="button" name="back" Class="btn"  value="展开留言"  id="s_replay"/>  
 	  </div> -->

	<div class="bodyq" style="width: 80%; position: relative; left: 10%;"
		id='div_replay'>
		<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
		<iframe id="tabFrames1" name="tabFrames"
			src="<%=request.getContextPath()%>/oa/oaLeaveMessage!replayList.do?s_djid=${no}&s_infoType=video"
			onload="autoHeight(this);" width="90%" frameborder="0"
			style="margin: 0px auto;" scrolling="auto" border="0" marginwidth="0"></iframe>
	</div>
	<%-- </c:if> --%>
</body>
<script type="text/javascript">
	//是否显示留言
	function isShow() {
		if ('checked' == $('#div_replay').attr("class")) {
			$('#div_replay').hide();
			$('#div_replay').removeAttr("class");
			$('#s_replay').attr("value", "展开留言");
		} else {
			$('#div_replay').show();
			$('#div_replay').attr("class", "checked");
			$('#s_replay').attr("value", "收起留言");

		}
	}

	$('#s_replay').live("click", function() {
		isShow();
	});

	$('#div_replay').show();

	function reinitIframe() {

		var iframe = document.getElementById("tabFrames1");

		try {

			var bHeight = iframe.contentWindow.document.body.scrollHeight;

			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

			var height = Math.max(bHeight, dHeight);

			iframe.height = height;

		} catch (ex) {
		}

	}

	window.setInterval("reinitIframe()", 200);
</script>
</html>
