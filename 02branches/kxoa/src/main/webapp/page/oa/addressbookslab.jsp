<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
<style type="text/css">
#infoTab { width: 90%; margin-bottom: 10px; border-bottom: 1px solid #dedede; overflow: hidden; }
#infoTab li { height: 30px; line-height: 30px; padding: 0 15px 0 15px; font-size: 15px; cursor:pointer; float:left; background: url("../themes/default/improve/geduan.png") no-repeat left center; }
#infoTab li:FIRST-CHILD { background: none; }
#infoTab .select { font-weight: bold; color: #006fc1; border-bottom: 3px solid #5ac45c; }
#infoTab .current { border:1px solid #ff0000; border-bottom:none; color:#ff0000; }
#infoTab .disable { cursor:default; color:#ddd; font-weight:bold;}
#infoView { border:1px solid #bbb; padding:0px 5px 0px 5px}
#infoView fieldset { display:none; }
</style>
</head>

<body>
<s:form   action="ioDocTasksExcute"  method="post" namespace="/dispatchdoc"  styleId="dispatchdocForm" >
</br>
<ul id="infoTab">
    <li url="<%=request.getContextPath()%>/oa/addressbooks!oaView.do" class="select">内部通讯录</li>
	<li url="<%=request.getContextPath()%>/oa/addressbooks!list.do?s_type=P" >个人通讯录</li>
	
<%-- 	<li url="<%=request.getContextPath()%>/app/addressBook!oaView.do">机关通讯录</li> --%>
<%-- 	<li url="<%=request.getContextPath()%>/oa/addressbooks!list.do?s_type=O">机关通讯录</li> --%>
<%-- 	<li url="<%=request.getContextPath()%>/oa/addressbooks!list.do?s_type=C">公共通讯录</li> --%>

</ul>
 <div id="infoView">
    <!-- 修改id，不进入系统的comm*.js没有iframe style -->
	<iframe id="tabFrames1" name="tabFrames1" src="<%=request.getContextPath()%>/oa/addressbooks!oaView.do" onload="onLoadHeight(this);" width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe>
</div> 
<script type="text/javascript">
	function sNav(){
		$("#infoTab li.current").addClass("select");
		$("#infoTab").click(function(e){
			var e = e || window.event;
			var target = e.srcElement || e.target;
			if( target.tagName.toLowerCase()=="li" && $(target).attr("class") != "disable" ){
				if( !$(target).hasClass("select") ){
					$("#infoTab li").removeClass("select");
					$(target).addClass("select");
					$("#tabFrames1").attr("src",$(target).attr("url"));
				}
			}
		});
	}
	sNav();	
function onLoadHeight(t){
	var _height=window.frames["tabFrames1"].document.body.scrollHeight+10;
	if(_height>460){t.height=700;}
	else{t.height=460;}
}
</script>
</s:form>
</body>


</html>