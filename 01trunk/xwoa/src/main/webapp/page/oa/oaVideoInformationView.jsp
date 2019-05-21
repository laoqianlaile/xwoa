<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>

<title><s:text name="oaVideoInformation.view.title" /></title>
<%-- <link href="${ctx}/themes/css/calendar.css" rel="stylesheet" type="text/css" /> --%>
<script src="${ctx}/scripts/superslider/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<style type="text/css">
  #videoSlider{
     position:relative;
     width:100%;
     margin:10px auto;
     height:200px;
     border:1px solid #ccc;
     padding:15px 5px;
  }
  #videoSlider .sliderctrl{
    position:relative;
    top:35%;
    display:block;
    float:left;
  }
   #videoSlider .sliderctrl a{
       display:block;
      width:35px;
      height:35px;
      cursor:pointer;
   }
  #videoSlider .prev{
    background:url('${ctx}/scripts/superslider/images/controls.png') no-repeat 0 -32px;  
  }
   #videoSlider .next{
    background:url('${ctx}/scripts/superslider/images/controls.png') no-repeat -43px -32px;  
  }
  #videoBD{margin-left:40px;width:93%;float:left;}
  #videoBD ul,#videoBD li{float:left}
  #videoBD li{
     margin-right:15px;
  }
  #videoBD li div.videoBox{
     width:150px;
     height:150px;
     border:1px solid #ccc;  
      position:relative;
  }
   #videoBD li div.videoBox span{
      position:absolute;
      display:block;
      background:#ccc;
      padding:2px;
      max-width: 150px;
   }
  #videoBD li div.videoBox img{
     width:146px;
     height:146px;
     margin:2px 2px;
   }
  #videoBD li div.videoText{
    text-align:center;
  }
  a.playLink{
     text-decoration:none;
  }
  a.playLink:hover{
    color:black;
  }
  a.playLink:hover .play_icon{
    cursor:pointer;
     background:url("${ctx}/styles/default/css/tvimage/playicon_50.png") no-repeat center;
     position:absolute;
    top: 35%;
    left:33%;
    opacity: 0.9;
    z-index:1000;
    display: block;
    width: 55px;
    height: 55px;
  }
</style>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaVideoInformation.view.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<input type="hidden" name="no" value="${no }">
				<tr>
					<td class="addTd">视频节目标题</td>
					<td align="left" colspan="3">${title }</td>

				</tr>
				<tr>
					<td class="addTd">视频节目分类</td>
					<td align="left">
					 ${cp:MAPVALUE("videoType",infoType )}
					</td>
						<td class="addTd">视频节目类型</td>
		           <td align="left" ><c:if test="${videoType  eq 'D' }">单剧</c:if>
		                  <c:if test="${videoType  eq 'L' }">连载</c:if>
		           </td>
					</tr>
				
					<tr>
						<td class="addTd">视频节目标签</td>
						<td>
             ${bookmark }
			</td>
                 
				</tr>
				<%-- <tr>
					<td class="addTd">
						视频节目图片
					</td>	<td>		
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${no}','big_')">&nbsp;</td>
					<td class="addTd">
						视频图片
					</td>
						
					<td	>
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${no}','upload')">&nbsp;
					</td>
				</tr> --%>
					<tr>
					<td class="addTd">是否可回复</td>
					<td align="left" colspan="3">
						 ${cp:MAPVALUE("OAISAllowComment",isAllowComment )}
					</td>
					</tr>
				<tr>
					<td class="addTd">发布日期</td>
					<td align="left">
								<fmt:formatDate value='${releaseDate}'
									pattern='yyyy-MM-dd' />
					</td>
					<td class="addTd">信息有效期</td>
					<td align="left">
								<fmt:formatDate value='${validDate}'
									pattern='yyyy-MM-dd' />
					</td>
				</tr>
                  <tr>
				
					<td class="addTd">关键字</td>
					<td align="left">${publicKey }</td>
				<td class="addTd">引用连接</td>
					<td align="left">${referenceLinks }</td>
				</tr>
				<tr>
					<td class="addTd">视频介绍</td>
					<td align="left" colspan="3">${remark }</td>
				</tr>
	
			</table>
	</fieldset>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				视频列表
			</p>
		</legend>
		<!-- 视频文件信息展示 -->
			<div id="videoSlider">
			   <span class="sliderctrl"><a class="prev"></a></span>
			   <div id="videoBD">
				   <ul>
				     <c:forEach var="videoItem" items="${videoItems}">
				      <li>
				         <div class="videoBox">
				           <a class="playLink" href="javascript:void(0)" onclick="playVideo('${ctx}/${videoItem.videoPath}','${videoItem.title}')">
				            <span title="点击次数">${empty videoItem.clickNum?0:videoItem.clickNum}</span>
				            <img src="${ctx}/${videoItem.smallimage}">
				            <i class="play_icon"></i>
				            </a>
				         </div>
				         <div class="videoText">
				            <div title="${videoItem.title}">
				               <c:if test="${fn:length(videoItem.title)>7}">${fn:substring(videoItem.title,0,7)}...</c:if>
				               <c:if test="${fn:length(videoItem.title)<=7}">${videoItem.title}</c:if>
				            </div>
				            <div>上传于：<fmt:formatDate value='${videoItem.creatertime}'
									pattern='yyyy-MM-dd HH:mm'/></div> 
				         </div>
				      </li>
				      </c:forEach>
				   </ul>
			   </div>
			   <span class="sliderctrl"><a class="next"></a></span>
			</div>
    	<div class="formButton">
			<input type="button" class="btn" target="submit" style="cursor:pointer;" 
						onclick="window.history.back();" value='返回'> 
    </fieldset>
</body>
</html>

<script type="text/javascript">
function downFile(id,v) {
	var url = "oaVideoInformation!downloadPhoto.do?no=" + id+"&type="+v ;
	document.location.href = url;
}
$(function(){
	$("#videoSlider").slide({mainCell:"#videoBD ul",effect:"left",autoPlay:true,autoPage:true,vis:5});
});
function playVideo(source,title){
	var html = '<div style="background-color: #FBFBFB;">'
		    + '<div id="play" class="play_div" >'
	        +  '<object type="application/x-shockwave-flash"'
		    +  '   data="${pageContext.request.contextPath}/upload/tools/vcastr3.swf"'
		    +  '   width="500" height="419" id="vcastr3">'
		    +  '   <param name="movie"'
			+  '       value="${pageContext.request.contextPath}/upload/tools/vcastr3.swf" />'
			+  '    <param name="allowFullScreen" value="true" />'
			+  '    <param name="wmode" value="opaque">'
			+  '    <param name="FlashVars"'
			+  'value="xml=	<vcastr>'
			+  '           <channel>'			
			+  '            <item>'				
			+  '              <source>'+source+'</source>'				
            +  '              <duration></duration>'							
			+  '               <title>'+title+'</title>'				
			+  '            </item>'				
			+  '           </channel>'				
			+  '           <config>'				
			+  '             <bufferTime>4</bufferTime>'				           
			+  '             <contralPanelAlpha>0.75</contralPanelAlpha>'		        
			+  '             <controlPanelBgColor>0x333333</controlPanelBgColor>'		           
			+  '             <controlPanelBtnColor>0xffffff</controlPanelBtnColor>'		         
			+  '             <contralPanelBtnGlowColro>0x333333</contralPanelBtnGlowColro>'		          
			+  '             <controlPanelMode>float</controlPanelMode>'		            
			+  '             <defautVolume>0.8</defautVolume>'		         
			+  '             <isAutoPlay>false</isAutoPlay>'		                  
			+  '             <isLoadBegin>true</isLoadBegin>'		            
			+  '             <isShowAbout>true</isShowAbout>'		        
			+  '              <scaleMode>exactFil</scaleMode>'		        
			+  '            </config>'				
			+  '          </vcastr>" />'				
		    +  '</object>'
		    +  '</div>'
		    top.art.dialog({
		    	title:title,
		        content: html,
		        id: 'EF893L'
		    });
}
</script> 