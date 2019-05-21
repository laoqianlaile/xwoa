<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
	 <!-- 	悬浮	 -->
	<link href="${pageContext.request.contextPath}/styles/default/css/sidebar.css" rel="stylesheet" type="text/css" />
	<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/lrtk.css">
   <script type="text/javascript" src="jquery-1.6.min.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
		<title>
			${jspInfo.title}
		</title>
	</head>
<body>
	<div class="flowTitle">${jspInfo.title}</div>
	<s:form action=" " method="post" namespace="/powerruntime" id="vOptBaselist_form" validator="true">
	 <input type="hidden" id="djId" name="djId" value="${optBaseInfo.djId}" />
	<%-- 	 <s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	<div class="nav_linkage" style="width:89%;padding-top:30px;">
	 <c:forEach var="fInfo" items="${jspInfo.frameList}" >
		<iframe id="${fInfo.frameId}" name="${fInfo.frameId}" src="<c:url value='${fInfo.frameSrc}'/>" width="98%" style="margin-bottom:10px;min-height:130px"
			frameborder="no" scrolling="false" border="0" marginwidth="0" onload="autoHeight(this);"></iframe>
	</c:forEach>
	</div>
	</s:form>
<script type="text/javascript">
	var sh = function(h){
		if(document.getElementById("viewFrame")){ 
			var t = document.getElementById("viewFrame");
			t.height = h;
		}
	};
	
	function proCollect(ele,djId){
		var alertvalue=$.trim($(ele).text());
		if(window.confirm("您确定["+alertvalue+"]操作吗？")){
		$.ajax({
			type : "post",
			async: false,
			dataType : "json",
			url : "${ctx}/powerruntime/VOptProcCollection!saveColl.do?djId="+djId,
			success : function(data) {				
				if(data.status=="coll"){
					$(ele).text("取消收藏");
					alert("["+alertvalue+"]操作成功！\n 被收藏的公文请在公文收藏中查看。");
				}else{
					$(ele).text("办件收藏");
					alert("["+alertvalue+"]操作成功！");
				}			
			},
			error : function() {
				alert("失败");
			}
		});
		}
	}
	//右侧导航滚动关联
	function scrollToAfter(th){
		$('.nav_right_link>a').removeClass('select');
		$(th).addClass('select');
	    $(window).unbind("scroll");
	    setTimeout(function(){
	    	 $(window).bind("scroll",scrollEvent);
	    },200);
	}
	function scrollEvent(){
		var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
		var tempHeight = $(window).height() + scrollTop - 50;//50为手动调的误差值
		var currIndex = 0;
		var h = 70;//初始值位距离顶部的距离
	    $(".nav_linkage iframe").each(function(i,ele){
	    	h+=($(ele).height());//累计iframe的高度
	    	if(h >= tempHeight){
	    		currIndex = i;
	    		return false;
	    	}
	    });
	    $('.nav_right_link>a').removeClass('select');
	    $(".nav_right_link a").eq(currIndex).addClass('select');
	}
	function resizeIframeWidth(){
		var sideWidth = $(".sider").outerWidth(true);
		var navbarWidth = $(".nav_right").outerWidth(true);
		var leftWidth = $(window).width() - sideWidth - navbarWidth -60;
		
		$("iframe").each(function(){
			$(this).width(leftWidth);
		});
	}
	$(document).ready(function() {
	    $(window).unbind("scroll").bind("scroll",scrollEvent);
	    resizeIframeWidth();
	});
	function showView(title,link){
		DialogUtil.open(title,link,1200,700);
	}
</script>
<!-- 返回顶部浮层 -->
<div class="sider">
	<ul>
	<li id="sy" style="display:list-item;">
		<A HREF="javascript:void(0);"
			onclick="returnNewPage('sy')">
			返回首页
		</A>
	</li>
    <%-- <li id="fhdb" style="display:list-item;">
		<A HREF="javascript:void(0);" title="返回待办"
			onclick="openMenu(this,'fhdb','${ctx}/dispatchdoc/vuserTaskListOA!list.do');return false;">
			返回待办
		</A>
	</li> --%>
	<li id="fsyj" style="display:list-item;">
		<A HREF="javascript:void(0);" title="发邮件"
			onclick="addRT('${ctx}/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&from=public');return false;">
			发送邮件
		</A>
	</li>
	<li id="fstx" style="display:list-item;">
		<A HREF="javascript:void(0);" title="发提醒"
			onclick="addRT('${ctx}/oa/oaRemindInformation!builtV2.do?xzrc_sy=T');return false;">
			发送提醒
		</A>
	</li> 
	
	<li id="myDiv_fhdb">
		<A href="javascript: window.scrollTo(0, 0);">
			返回顶部
		</A>
	</li>
	</ul>
</div> 
 <!-- 右侧导航 -->
    <div class="nav_right">
		<div class="nav_right_link">
		   <c:forEach var="fInfo" items="${jspInfo.frameList}" varStatus="vs">
		      <a href="#${fInfo.frameId}" class="${vs.index==0?'select':''}" onclick="scrollToAfter(this)"><label></label>${empty fInfo.frameLegend?'业务信息':fInfo.frameLegend}</a>
		   </c:forEach>
		</div>
		<div class="nav_right_button">
		    <c:if test="${hasRelSubject}">
		       <a href="javascript:void(0);" onclick="showView('关联事项','${ctx}/oa/oaBizBindInfo!listbiz4tab.do?djid=${djId}')">关联事项</a><br/>
		    </c:if>
			<a href="javascript:void(0);" onclick="showView('过程信息','${ctx}/powerruntime/generalOperator!listIdeaLogs.do?djId=${djId}')">过程信息</a><br/>
			 <!--   F--不显示流程图 -->
            <c:if test='${cp:MAPVALUE("SYSPARAM","isFlowShow") ne "F"}'>
			<c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('流程图','${ctx}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}')">流程图</a><br/>
			</c:if>
			</c:if>
			<%-- <c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('办件讨论','${ctx}/oa/oaLeaveMessage!replayList.do?s_djid=${djId}&s_infoType=OAGW')">办件讨论</a><br/>
			</c:if> --%>
			
		</div>
	</div>
		<div class="flow-operator">
		<c:if test="${not empty prevDjId || not empty nextDjId}">
		   <ec:reqeustAttributeForm id="listParamForm"/>
		</c:if>
	<c:if test="${not empty startDjid }">
	<input type="button" name="back" Class="btn"
				onclick="window.close();" value="关闭" />
	</c:if>
	<c:if test="${empty startDjid and empty dashboard}">
	  <c:if test="${not empty prevDjId}">
	   <input type="button" name="next" Class="btn"
			onclick="viewNeighbouringCase('${prevDjId}')" value="上一份" />
	   </c:if>
	   <c:if test="${not empty nextDjId}">
	   <input type="button" name="next" Class="btn"
			onclick="viewNeighbouringCase('${nextDjId}')" value="下一份" />
	   </c:if>
	<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
	</c:if>
	<c:if test="${not empty dashboard and dashboard eq 'T'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'C'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do?s_bizstate=C" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'W'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do?s_bizstate=W" 
			 						value='返回' /></c:if>	
  </div>
</body>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
function addRT(url) {
	art.dialog
			.open(url,
					 {title: '', width: 1050, height: 640});

}
function returnNewPage(type){
	if(type=="sy"){
	 	top.location.href="<%=request.getContextPath()%>/sys/mainFrame!showMain.do";
	}
}
function viewNeighbouringCase(djId){
	var djIdInput = $("#listParamForm").find("input[name='djId']"),
	nodeInstIdInput = $("#listParamForm").find("input[name='nodeInstId']");
	if(djIdInput.length == 0){
		djIdInput = $("<input>",{"name":"djId","type":"hidden"});
		$("#listParamForm").append(djIdInput);
	}
	if(nodeInstIdInput.length == 0){
		nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
		$("#listParamForm").append(nodeInstIdInput);
	}
	djIdInput.val(djId);
	nodeInstIdInput.val(0);
	var type = djId.replace(/[0-9]/g,'');
	if(type=='FW'){
		$("#listParamForm").attr("action","${ctx}/dispatchdoc/dispatchDoc!generalOptView.do");
	}else if(type=='SW'){
		$("#listParamForm").attr("action","${ctx}/dispatchdoc/incomeDoc!generalOptView.do");
	}else if(type=="CARSQ"){
		$("#listParamForm").attr("action","${ctx}/oa/oaCarApply!generalOptView.do");
	}
	else{
		alert("无法识别服务器地址");
		return;
	}
	$("#listParamForm").submit();
}
</script>
</html>