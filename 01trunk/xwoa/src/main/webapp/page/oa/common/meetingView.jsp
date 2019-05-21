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
	<li url="<%=request.getContextPath()%>/oa/oaMeetApply!calendarView.do?meetingNo=${object.djid}&show_type=${showTag}">会议室安排查询</li>

	<li url="<%=request.getContextPath()%>/oa/oaMeetApply!list.do?show_type=self&s_meetingNo=${object.djid}">列表展示</li>
<%-- 	<li url="<%=request.getContextPath()%>/oa/oaMeetinfo!view.do?djid=${object.djid}&showTag=${showTag}">会议室信息</li> --%>
<%-- 	<li> <a href="<%=request.getContextPath()%>/oa/oaMeetinfo!list.do?showTag=${showTag}">返回</a></li> --%>
	<c:if test="${hideReturnBtn=='F'}">
	<%-- <li> <a href="<%=request.getContextPath()%>/oa/oaMeetinfo!list.do?showTag=${showTag}">返回</a></li> --%>

	</c:if>
</ul>
<div id="infoView">
    <!-- 修改id，不进入系统的comm*.js,没有iframe style -->
	<iframe id="tabFrames1" name="tabFrames"  onload="autoHeight(this);"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe>
</div>
<script type="text/javascript">
	/*弹框时iframe自适应高度有问题，这种写法无法代码级触发事件，在此干掉重写 
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
	} */
	$(function(){
		$("#infoTab").find("li").each(function(index,ele){
			$(ele).click(function(){
				if(!$(this).hasClass("select")){
					$("#infoTab li").removeClass("select");
					$(this).addClass("select");
					$("#tabFrames1").attr("src",$(this).attr("url"));
				}
			});
		});
		$("#infoTab").find("li").eq(0).click();
	});
	
</script>
</s:form>
</body>


</html>