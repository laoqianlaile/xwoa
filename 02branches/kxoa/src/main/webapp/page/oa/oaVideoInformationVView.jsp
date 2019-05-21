
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="textml; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${pageContext.request.contextPath}/themes/css/video.css"
	rel="stylesheet" type="text/css" />
	<style type="text/css">
   td{border-right:1px solid #ADADAD;border-bottom:1px solid #ADADAD}
</style>
<script type="text/javascript">
 $(function(){
	  $("#bofang").bind("click",function(){
		 window.location.href="oaVideoInformation!showVideo.do?no=${no}";
	  });
  });
</script>
</head>

	<body style="color: #333; padding-top: 20px; padding-left: 110px; padding-right: 110px;">
		<input type="hidden" name="no" value="${no }"/>

	<div style="background: #ADD8E6; height: 26px; width: 95%">
		<h3 id="content">
		<a href="oa/oaVideoInformation!view.do?no=${no }" class="zjsz">${oaVideoInformation.title}[HD高清][中英双字]</a>
		<a href="#" onclick="window.history.back();"  class="zjsz">返回</a>
<!-- 			<input type="button" onclick="window.history.back();" class="btn" value="返回"/> -->
		</h3>
	</div>

	<div id="nav"
		style="height: 250px; width: 95%; border: 1px solid #ADADAD;">
		<div
			style="width: 30%; margin-top: 20px;padding-left:20px;">
			<span id="overfpic"><img id="appicon" src='${contextPath }/oa/oaVideoInformation!downloadPhoto.do?no=${no}' /></span>
		</div>
		<div
			style="width: 69%; height: 70px; margin-top: 20px;padding-left:20px;border: 0px solid #ADADAD;">
			<span>${videoName}
            </span>
		</div>
		<div
			style="width: 60%; height: 7%; margin-top: 8px;  margin-left: 20px;">
			<span style="font-size: 15px; font-weight: bold;">视频信息:</span>
		</div>
		<div
			style="width: 60%; height: 40%; margin-top: 5px;  margin-left: 20px; ">
			<table style="width: 100%; height: 90%;border-left:1px solid #ADADAD;border-top:1px solid #ADADAD" cellpadding="0" cellspacing="0">
					<tr>
<!-- 					<td>尺寸 :120x720</td> -->
<%-- 					<td>格式:   ${oaVideoInformation.videoStyle }</td> --%>
	                <td>发布时间 :
						<fmt:formatDate value='${releaseDate}'
									pattern='yyyy-MM-dd' />
						</td>
					<td>视频类型: ${cp:MAPVALUE("videoType",infoType )}</td>
				</tr>
				<tr>
					<td>视频状态:  上线</td>
					<td>导演:${derector }</td>
<!-- 					<td>清晰度:<img style="margin-top:5px;height:15px;margin-left:10px;" -->
<%-- 							src="${pageContext.request.contextPath}/styles/default/images/speed.jpg"/></td> --%>
				</tr>
				<tr>
				
					<td>上传者:  ${cp:MAPVALUE("usercode",creater )} </td>
					<td>关键字: ${publicKey }</td>
				</tr>

			</table>

		</div>
		<div style="width: 95%;height:130px;margin-left:10px;margin-right:0px;margin-top:20px;">
			<div style="width:95%;height:20px;margin-left:10px; margin-top:8px;">
				<span style="font-weight: bold;font-size: 18px;">剧情介绍:</span>
				<span style="font-size:15px;">
				${videoName }
				</span>
				
			</div>
		</div>
	</div>
		
	</div>
	<div style="width: 95%; border: 1px solid #ADADAD;;height:100px;border:1px solid #ADADAD; margin-top:20px;">
		<div style="width:30%;height:40px;margin-left:18px; margin-top:8px;cursor:pointer;">
			<img id = "bofang"src="${pageContext.request.contextPath}/styles/default/images/bofang.jpg" />
		</div>
	</div>
	 
	</body>
</html>
