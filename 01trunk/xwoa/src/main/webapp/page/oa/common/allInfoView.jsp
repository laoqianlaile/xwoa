<meta http-equiv="X-UA-Compatible" content="IE=8">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/page/common/css.jsp"%> 
<title></title>
</head>

<body class="sub-flow">
<s:form action="ioDocTasksExcute" method="post" namespace="/dispatchdoc" styleId="dispatchdocForm" >
		<ul id="infoTab">
		<c:forEach var="temp" items="${optNewlyIdeaList}">	
		<c:if test="${ not empty temp.djId  }">
		<li url="<%=request.getContextPath()%>${temp.url}&notitle=1"<c:if test="${temp.nodeid==nodeId}">class="current"</c:if> >${temp.nodename}</li>
		</c:if>
		</c:forEach>
			<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listIdeaLogs.do?djId=${object.djId}&notitle=1" ${empty curUrl ? "" : "class='select'"}>办件过程信息</li>
			<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listStuffs.do?djId=${object.djId}&notitle=1">
			 <c:if test="${empty param.itemType}">已上传材料列表</c:if>
	         <c:if test="${not empty param.itemType}">${cp:MAPVALUE("oa_ITEM_TYPE",param.itemType)}材料</c:if>
			</li>
			 <!--   F--不显示流程图 -->
            <c:if test='${cp:MAPVALUE("SYSPARAM","isFlowShow") ne "F"}'>
			<li url="<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}&notitle=1" <c:if test="${flowInstId ==9999999}">class="disable"</c:if>>查看流程图</li>
			</c:if>
			<%-- <li url="<%=request.getContextPath()%>/oa/oaLeaveMessage!replayList.do?s_djid=${object.djId}&s_infoType=OA" <c:if test="${flowInstId ==9999999}">class="disable"</c:if>>办件讨论</li> --%>
			<c:if test="${not empty oasuplist }">
			<li url="<%=request.getContextPath()%>/oa/oaSupervise!superviselist.do?s_supDjid=${object.djId}" >督办发起信息</li>
		    </c:if> 
		</ul>
<div id="infoView">

	<iframe id="tabFrames" name="tabFrames" src="<%=request.getContextPath()%>${curUrl}&notitle=1" onload="autoHeight(this);"  width="100%"
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