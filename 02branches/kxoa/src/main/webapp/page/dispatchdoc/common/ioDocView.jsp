<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
<style type="text/css">
#viewInfoTab {
	position: relative;
	height: 26px;
	line-height: 23px;
	border-bottom: 1px solid #bbb;
	overflow: auto;
	padding-top: 1px;
}

#viewInfoTab li {
	cursor: pointer;
	position: relative;
	float: left;
	padding: 0 15px;
	border: 1px solid #bbb;
	margin-right: 6px;
	border-bottom: none;
	background: #fff;
}

#viewInfoView {
	border: 1px solid #bbb;
	border-top: none;
	padding: 0px 10px 10px;
}

#viewInfoTab .select {
	top: 1px;
	font-weight: bold;
	cursor: default;
}

#viewInfoTab .current {
	border: 1px solid #ff0000;
	border-bottom: none;
	color: #ff0000;
}

#viewInfoTab .disable {
	cursor: default;
	border: 1px solid #ddd;
	border-bottom: none;
	color: #ddd;
	font-weight: bold;
}

#viewInfoView fieldset {
	display: none;
}
</style>
</head>
<body>
	<s:form action="ioDocTasksExcute" method="post" namespace="/dispatchdoc" styleId="dispatchdocForm">
		<ul id="viewInfoTab">
			<c:forEach var="frameInfo" items="${tabFrameList}">
				<li ref="${frameInfo.frameId}" ${(tabShowFrameId eq frameInfo.frameId) ? "class='select'" : ""}>${frameInfo.frameLegend}</li>
			</c:forEach>
		</ul>
		<div id="viewInfoView">
			<c:forEach var="frameInfo" items="${tabFrameList}">
				<iframe id="${frameInfo.frameId}" name="${frameInfo.frameId}" src="${contextPath}${frameInfo.frameSrc}" 
					ref="tabFrame" ${(empty tabShowFrameId or tabShowFrameId ne frameInfo.frameId) ? "style='display: none;'" : ""}
					width="100%" style="margin-bottom: 10px;" frameborder="0" scrolling="auto" border="0" marginwidth="0">
				</iframe>
			</c:forEach>
		</div>
	</s:form>
</body>
<script type="text/javascript">
	$("#viewInfoTab").click(function(e) {
		var e = e || window.event;
		var target = e.srcElement || e.target;
		if (target.tagName.toLowerCase() == "li" && $(target).attr("class") != "disable") {
			if (!$(target).hasClass("select")) {
				$("#viewInfoTab li").removeClass("select");
				$("#viewInfoView iframe").hide();
				$(target).addClass("select");
				$("#" + $(target).attr("ref")).show();
				setTimeout(function() {
					$("#" + $(target).attr("ref"))[0].style.height = window.frames[$(target).attr("ref")].window.document.body.scrollHeight + "px";
				}, 200);
			}
		}
	});
</script>
</html>