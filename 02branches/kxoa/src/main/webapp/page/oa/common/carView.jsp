<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
<style type="text/css">
#infoTab { width: 100%; margin-bottom: 10px; border-bottom: 1px solid #dedede; overflow: hidden; }
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
	<li url="<%=request.getContextPath()%>/oa/oaCarApply!calendarView.do?cardjid=${object.djid}" ${empty curUrl ? "" : "class='select'"}>车辆安排查询</li>
	<li url="<%=request.getContextPath()%>/oa/oaCarApply!list.do?s_cardjid=${object.djid}&show_type=mipSelf">列表展示</li>
<%-- 	<li url="<%=request.getContextPath()%>/oa/oaCarinfo!view.do?djid=${object.djid}">车辆信息</li> --%>
<%-- 	<li> <a href="<%=request.getContextPath()%>/oa/oaCarinfo!list.do?showTag=${showTag}">返回车辆列表</a></li> --%>
</ul>
<div id="infoView">
    <!-- 修改id，不进入系统的comm*.js,没有iframe style -->
	<iframe id="AllInfoFrame" name="tabFrames" src="<%=request.getContextPath()%>${curUrl}" onload="autoHeight(this);"  width="100%"
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
					$("#AllInfoFrame").attr("src",$(target).attr("url"));
				}
			}
		});
	}
	sNav();			
</script>
</s:form>
</body>


</html>