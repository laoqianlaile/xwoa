<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
<style type="text/css">
#infoTab { position:relative; height:26px; line-height:23px; border-bottom:1px solid #bbb; overflow:auto; padding-top:1px;}
#infoTab li { cursor:pointer; position:relative; float:left; padding:0 15px; border:1px solid #bbb; margin-right:6px; border-bottom:none; background:#afe7ff;list-style: none !important; }
#infoView { border:1px solid #bbb; border-top:none; padding:0px 10px 10px; }
#infoTab .select { top:1px; font-weight:bold; cursor:default; }
#infoTab .current { border:1px solid #ff0000; border-bottom:none; color:#ff0000; }
#infoTab .disable { cursor:default; border:1px solid #ddd; border-bottom:none; color:#ddd; font-weight:bold;}
#infoView fieldset { display:none; }
</style>
</head>

<body>
<s:form   action="optApply"  method="post" namespace="/powerruntime"  styleId="powerruntimeForm" >
<ul id="infoTab">
<li url="<%=request.getContextPath()%>/powerruntime/optApply!view.do?djId=${object.djId}">申请信息</li>
<c:if test="${flag eq true }">
<li url="<%=request.getContextPath()%>/oa/oaBizBindInfo!listbiz4tab.do?djid=${object.djId}&nodelete=1">事项关联</li> <!-- nodelete=1,只是查看不可以删除关联 -->
</c:if>
<c:if test="${isapplyuser eq 'T' }">
<c:forEach var="temp" items="${optNewlyIdeaList}">	
<li url="<%=request.getContextPath()%>${temp.url}"<c:if test="${temp.nodeid==nodeId}">class="current"</c:if> >${temp.nodename}</li>
</c:forEach>
</c:if>

	<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listIdeaLogs.do?djId=${object.djId}" ${empty curUrl ? "" : "class='select'"}>办件过程信息</li>
	<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listStuffs.do?djId=${object.djId}">已上传材料列表</li>
	<li url="<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}" <c:if test="${flowInstId ==9999999}">class="disable"</c:if>>查看流程图</li>
	
<%-- 	<li url="<%=request.getContextPath()%>/oa/oaSupervise!superviselist.do?s_supDjid=${object.supDjid}" <c:if test="${empty version}">class="disable"</c:if>>督办发起信息</li> --%>

</ul>
<div id="infoView">
    <!-- 修改id，不进入系统的comm*.js,没有iframe style -->
	<iframe id="tabFrames" name="tabFrames" src="<%=request.getContextPath()%>${curUrl}" onload="autoHeight(this);"  width="100%"
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
					$("#tabFrames").attr("src",$(target).attr("url"));
				}
			}
		});
	}
	sNav();			
</script>
</s:form>
</body>


</html>