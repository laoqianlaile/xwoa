<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaBbsDiscussion.list.title" /></title>
<style type="text/css">
#infoTab {
	position: relative;
	height: 26px;
	line-height: 23px;
	overflow: auto;
	padding-top: 1px;
}

#infoTab li {
	cursor: pointer;
	position: relative;
	float: left;
/* 	padding: 0 15px; */
	margin-right: 6px;
	border-bottom: none;
	list-style:none;
}
/* #infoView { border:1px solid #bbb; border-top:none; padding:0px 10px 10px; } */
#infoTab .select {
	top: 1px;
	font-weight: bold;
	cursor: default;
}

#infoTab .current {
	border: 1px solid #ff0000;
	border-bottom: none;
	color: #ff0000;
}

#infoTab .disable {
	cursor: default;
	border: 1px solid #ddd;
	border-bottom: none;
	color: #ddd;
	font-weight: bold;
}
/* #infoView fieldset { display:none; } */
</style>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>


	<s:form action="vBbsThemeUser" namespace="/bbs"
		style="margin-top:0;margin-bottom:5">
        <input  type="hidden" name="s_usercode" value="${s_usercode}"/>
		
	
<ul id="infoTab">
			<li
				url="<%=request.getContextPath()%>/bbs/oaBbsTheme!ownerThemeList.do?s_usercode=${s_usercode}"
				${empty curUrl ? "" : "class='select'"}>主题|</li>
			<li
				url="<%=request.getContextPath()%>/bbs/oaBbsTheme!ownerReplayList.do?s_usercode=${s_usercode}">回复</li>

		</ul>
<div id="infoView">
    <!-- 修改id，不进入系统的comm*.js,没有iframe style -->
	<iframe id="tabFrames1" name="tabFrames1" src="<%=request.getContextPath()%>${curUrl}" 
	onload="onLoadHeight(this);"  width="100%"
			frameborder="0" scrolling="no" border="0" marginwidth="0"></iframe>
</div>
</s:form>

</body>

<script type="text/javascript">
	function onLoadHeight(t) {
		var height = window.frames['tabFrames1'].document.body.scrollHeight + 2;
		if (height > 500) {
			t.height = height;
		} else {
			t.height = 500;
		}

	}
	function sNav() {
		$("#infoTab li.current").addClass("select");
		$("#infoTab")
				.click(
						function(e) {
							var e = e || window.event;
							var target = e.srcElement || e.target;
							if (target.tagName.toLowerCase() == "li"
									&& $(target).attr("class") != "disable") {
								if (!$(target).hasClass("select")) {
									$("#infoTab li").removeClass("select");
									$(target).addClass("select");
									$("#tabFrames1").attr("src",
											$(target).attr("url"));
								}
							}
						});
	}
	sNav();
</script>
</html>
