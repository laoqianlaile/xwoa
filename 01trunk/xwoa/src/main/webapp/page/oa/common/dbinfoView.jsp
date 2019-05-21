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
<ul id="infoTab">
	<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listIdeaLogs.do?djId=${object.djId}" ${empty curUrl ? "" : "class='select'"}>办件过程信息</li>
	<li url="<%=request.getContextPath()%>/powerruntime/generalOperator!listStuffs.do?djId=${object.djId}">
	    <c:if test="${empty param.itemType}">已上传材料列表</c:if>
	   <c:if test="${not empty param.itemType}">${cp:MAPVALUE("oa_ITEM_TYPE",param.itemType)}材料</c:if>
	</li>
	<li url="<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}" <c:if test="${flowInstId ==9999999}">class="disable"</c:if>>查看流程图</li>
	<%-- <li url="<%=request.getContextPath()%>/oa/oaSupervise!superviselist.do?s_supDjid=${object.supDjid}" <c:if test="${empty version}">class="disable"</c:if>>督办发起信息</li> --%>

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