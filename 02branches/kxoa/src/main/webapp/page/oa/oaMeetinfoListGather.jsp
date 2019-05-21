<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
  <script src="${ctx}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
  <style type="text/css">
#infoTab { width: 100%; margin-bottom: 10px; border-bottom: 1px solid #dedede; overflow: hidden; }
#infoTab li {list-style-type:none; height: 30px; line-height: 30px; padding: 0 15px 0 15px; font-size: 15px;
  cursor:pointer; float:left; background: url("../themes/default/improve/geduan.png") no-repeat left center; }
#infoTab li:first-child { background: none; }
#infoTab .select { font-weight: bold; color: #006fc1; border-bottom: 3px solid #5ac45c; }
#infoView { border:1px solid #bbb; padding:0px 5px 0px 5px;}
</style>

  <script type="text/javascript">
	$(function(){
		$("#infoTab li.current").addClass("select");
		$("#infoTab").click(function(e){
			var e = e || window.event;
			var target = e.srcElement || e.target;
			if( target.tagName.toLowerCase()=="li" && $(target).attr("class") != "disable" ){
				if( !$(target).hasClass("select") ){
					$("#infoTab li").removeClass("select");
					$(target).addClass("select");
					$("#tabFrameItem").attr("src",$(target).attr("url"));
				}
			}
		});
	});
	function autoHeight(iframe){	
		var id = $(iframe).attr("id");
		var ifm = document.getElementById(id);
		var subWeb = document.frames ? document.frames[id].document : ifm.contentDocument;
		
		if (ifm != null && subWeb != null){
			//获取顶层的高度
			var topH = $(top.document).find("#external_HYCKHZ").parent().height();
			$(ifm).css("height",topH-$("#infoTab").height()-26);
		}
			
	}
  </script>
</head>

<body style="overflow: hidden;">
	<ul id="infoTab">
		<li url="${ctx}/oa/oaMeetApply!list.do?s_biztype=F&show_type=F">我的暂存</li>
      <li url="${ctx}/oa/oaMeetApply!list.do?NP_bizstate=T&show_type=myself" class="current">我的申请</li>
      <%-- <li url="${ctx}/oa/oaMeetApply!listOwn.do?show_type=T">我的经办</li> --%>
      <li url="${ctx}/oa/vOptBaseList!listOwn.do?s_itemType=HYSQ">我的经办</li>
     <li url="${ctx}/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000857&s_itemtype=HYSQ">我的待办</li>
	  <li url="${ctx}/oa/oaMeetApply!list.do?show_type=T">查看全部</li>
   </ul>
  <div id="infoView">
	  <iframe id="tabFrameItem" name="tabFrames" src="${ctx}/oa/oaMeetApply!list.do?NP_bizstate=T&show_type=myself" onload="autoHeight(this);"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe>
   </div>
	
</body>
</html>
