<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<html>
<head>
<title></title>
<style type="text/css">
#infoTab { position:relative; height:26px; line-height:23px; border-bottom:1px solid #bbb; overflow:auto; padding-top:1px;}
#infoTab li { cursor:pointer; position:relative; float:left; padding:0 15px; border:1px solid #bbb; margin-right:6px; border-bottom:none; background:#afe7ff; }
#infoView { border:1px solid #bbb; border-top:none; padding:0px 10px 10px; }
#infoTab .select { top:1px; font-weight:bold; cursor:default; }
#infoTab .current { border:1px solid #ff0000; border-bottom:none; color:#ff0000; }
#infoTab .disable { cursor:default; border:1px solid #ddd; border-bottom:none; color:#ddd; font-weight:bold;}
#infoView fieldset { display:none; }
</style>
</head>

<body>
<s:form   action="ioDocTasksExcute"  method="post" namespace="/dispatchdoc"  styleId="dispatchdocForm" >
<ul id="infoTab">
<c:if test="${param.type eq 'list'}">
	<li url="<%=request.getContextPath()%>/oa/oaScheduleResponse!list.do?viewtype=${param.viewtype}" >日程安排响应</li>
</c:if>	
<c:if test="${param.type eq 'view'}">
<li url="<%=request.getContextPath()%>/oa/oaScheduleResponse!edit.do?viewtype=${param.viewtype}" >日程安排响应</li> 
</c:if>
 	
<%-- 	<li url="<%=request.getContextPath()%>/oa/oaTrafficRecord!list.do?s_djid=${object.djid}&show_type=${show_type}" <c:if test="${empty object.djid}">class="disable"</c:if>>违章记录</li> --%>
	
</ul>
<div id="infoView">
    <!-- 修改id，不进入系统的comm*.js,没有iframe style -->
 <c:if test="${param.type eq 'list'}">
	   <iframe id="tabFrames1" name="tabFrames" src="<%=request.getContextPath()%>/oa/oaScheduleResponse!list.do?viewtype=${param.viewtype}" onload="autoHeight(this);"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe> 
</c:if>	
<c:if test="${param.type eq 'view'}">
   <iframe id="tabFrames1" name="tabFrames" src="<%=request.getContextPath()%>/oa/oaScheduleResponse!edit.do?viewtype=${param.viewtype}" onload="autoHeight(this);"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe> 
</c:if> 
 
	
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
</script>
</s:form>
</body>


</html>