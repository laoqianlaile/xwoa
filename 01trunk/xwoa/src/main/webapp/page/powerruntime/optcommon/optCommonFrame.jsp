<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			${jspInfo.title}
		</title>
		<meta http-equiv="X-UA-Compatible" content="IE=8">
		<%@ include file="/page/common/css.jsp"%>
		<!-- 新样式文件 -->
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>				
        <script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>	
        <script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
		<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/lrtk.css">
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/arrow.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/kjyj.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/arrowTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dtree.js"></script>
		<link href="${pageContext.request.contextPath}/workflow/css/dtree.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/centit.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/jquery-ui/jquery-ui-1.9.2.custom.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/opendiv_Win.js"/>" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />	
    <!-- 	悬浮	 -->
	<link href="${pageContext.request.contextPath}/styles/default/css/sidebar.css" rel="stylesheet" type="text/css" />
	<style>
	
.scrollsidebar {
	position: absolute;
	z-index: 999;
	top: 45px;
	display: block;
}
ul.line{height: inherit;}
	</style>
	</head>
<body style="background:white" >
<div class="flow-container" id="container">
	<div class="flow-form-container">
	
	
	<s:form id="ioDocTasksExcuteFrame" name="ioDocTasksExcuteFrame" method="post" action="ioDocTasksExcute" namespace="/dispatchdoc">
	</br>
		<input type="hidden" id="flowPhase" name="flowPhase" value="${flowPhase}" />
		<input type="hidden" id="isReady" name="isReady" />
		<input type="hidden" id="djId" name="djId" value="${sqdjId}" />
		<input type="hidden" id="dbdjid" name="dbdjid" value="${dbdjid}" />
		<input type="hidden" id="dashboard" value="${dashboard}">
		<input type="hidden" id="s_itemtype" value="${s_itemtype}">
		<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
		<!-- 办件名称、办件标题显示 -->
		<div class="flowTitle" align="center" style="width:89%">
			<%-- <span style="padding-right:20px;">本环节已用时间:${worktime}</span> --%>
			
			<span class="affairTitle title red" style="font-size:18px;">${affairTitle}</span>
			
		</div>	
		
		<div class="flowSummary">
			当前位置：<span class="nodeName node" style="color: blue;font-weight: bold;">${nodeName}</span>
		<%-- 	<c:if test="${not empty nodePromiseTime}">
				<span class="nodePromiseTime node">环节总时限：${nodePromiseTime}</span>
			</c:if>
			<c:if test="${not empty nodeTime}">
				<span class="nodeTime node red">剩余期限：${nodeTime}</span>
			</c:if> --%>
			<c:if test="${not empty flowDefTime}">
				<span class="flowDefTime node">办件总时限：${flowDefTime}</span>
			</c:if>
			<c:if test="${not empty flowTime}">
				<span class="flowTime node">办件整体剩余时限：${flowTime}</span>
			</c:if>
			<c:if test="${not empty dbdjid }">
				<span class="node red">该办件已被督办，请回复督办后进行操作！&nbsp;&nbsp;</span>	
			</c:if>
		</div>
	</s:form>
	</div>
	
	<c:if test="${'jcfw_gz' eq nodeCode || 'xwfw_gz' eq nodeCode || 'xwbmfw_gz' eq nodeCode || 'xwfw_bmsw' eq nodeCode || 'jcfw_bmsw' eq nodeCode}">
		<input type="hidden" id="pdfFile" name="pdfFile" value="${pdfFile}" style="width:600px"/>
		<!-- <input type="hidden" id="affixUrl" value="http://117.60.146.30/StampServer"/> -->
		<!-- 现场电子签章地址 -->
		<input type="hidden" id="affixUrl" value="http://172.19.193.10/StampServer"/>
	</c:if>
	
	<div class="clear"></div>
	 
    <!-- 右侧导航 -->
    <div class="nav_right">
		<div class="nav_right_link">
		   <c:forEach var="fInfo" items="${jspInfo.frameList}" varStatus="vs">
		      <a href="#${fInfo.frameId}" class="${vs.index==0?'select':''}" onclick="scrollToAfter(this)"><label></label>${empty fInfo.frameLegend?'业务信息':fInfo.frameLegend}</a>
		   </c:forEach>
		</div>
		<div class="nav_right_button">
		    <c:if test="${hasRelSubject}">
		      <a href="javascript:void(0);" onclick="showView('关联事项','${ctx}/oa/oaBizBindInfo!listbiz4tab.do?djid=${sqdjId}')">关联事项</a><br/>
		    </c:if>
		     <c:if test="${flag eq 0}">
				<a href="javascript:void(0);" onclick="showView('过程信息','${ctx}/powerruntime/generalOperator!listIdeaLogs.do?djId=${sqdjId}')">过程信息</a><br/>
			 </c:if>
			   <!--   F--不显示流程图 -->
           <c:if test='${cp:MAPVALUE("SYSPARAM","isFlowShow") ne "F"}'>
			<c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('流程图','${ctx}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}')">流程图</a><br/>
			</c:if>
			</c:if>
		<%-- 	<c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('办件讨论','${ctx}/oa/oaLeaveMessage!replayList.do?s_djid=${sqdjId}&s_infoType=OAGW')">办件讨论</a><br/>
			</c:if> --%>
			
			<c:if test="${not empty collstatus}">
			<a href="javascript:void(0);" onclick="proCollect(this,'${sqdjId}')">${collstatus eq 'coll' ? '办件收藏' : '取消收藏'}</a><br/>
			</c:if>
			
			<c:if test="${'T' eq moduleParam.hasDb}">
			<a href="javascript:void(0);" onclick="openDB(this,'${sqdjId}')">督办批示</a><br/>
			</c:if>
			
			<c:if test="${'jcfw_gz' eq nodeCode || 'xwfw_gz' eq nodeCode || 'xwbmfw_gz' eq nodeCode}">
			<a href="javascript:void(0);" onclick="Seal()">盖章</a>
			</c:if>
			
			<c:if test="${'xwfw_bmsw' eq nodeCode || 'jcfw_bmsw' eq nodeCode}"> 
			<a href="javascript:void(0);" onclick="DetachPdf()">脱密打印<br>正文PDF</a>
			<!-- <a href="javascript:void(0);" onclick="SendPrintPDF()">打印<br>正文PDF</a> -->
			</c:if>
		</div>
	</div>
	<div class="flow-scroll">
			<c:forEach var="temp" items="${optNewlyIdeaList}">	
			<c:if test="${ not empty temp.djId  }">
			<li url="<c:url value='${temp.url}'/>"<c:if test="${temp.nodeid==nodeId}">class="current"</c:if> >${temp.nodename}</li>
			<iframe class="flow-iframe" id="${temp.nodeid}" name="{temp.nodename}" src="<c:url value='${temp.url}'/>" style="overflow: hidden;" width="100%" frameborder="0" scrolling="no"  border="0" marginwidth="0" onload="autoHeight(this);"></iframe>
			</c:if>
			</c:forEach>
	</div>
	<!-- 动态框架整合区 -->
	<div class="nav_linkage" style="width:89%">
	<c:forEach var="fInfo" items="${jspInfo.frameList}" >
		<iframe class="flow-iframe" id="${fInfo.frameId}" name="${fInfo.frameId}" src="<c:url value='${fInfo.frameSrc}'/>" style="overflow: hidden;"width="100%" frameborder="0" scrolling="no" border="0" marginwidth="0" onload="autoHeight(this);">
		</iframe>
	</c:forEach>
   </div>
   
   <div>	
		<!-- 流程操作 -->
		<div class="flow-operator">
		
		  <c:if test="${fn:substringBefore(sqdjId, '0' ) eq 'SW'  or fn:substringBefore(sqdjId, '0' ) eq 'FW'}">
			<input id="saveAndSubmit" type="button" name="subFrame" class="btn" onclick="submitEvent();" value="${cp:MAPVALUE('optComButton',fn:substringBefore(sqdjId, '0' ))}">
		  </c:if>
		   <c:if test="${fn:substringBefore(sqdjId, '0' ) ne 'SW'  and fn:substringBefore(sqdjId, '0' ) ne 'FW'}">
			<input id="saveAndSubmit" type="button" name="subFrame" class="btn" onclick="submitEvent();" value="提交">
		  </c:if>
		  
			<c:if test="${fn:substringBefore(sqdjId, '0' ) eq 'SW'  or fn:substringBefore(sqdjId, '0' ) eq 'FW'}">
			<span id="saveSpan">
				<input type="button" name="saveFrame" class="btn"  onclick="saveEvent();" value="暂存">
			</span>
			</c:if>
			
			<c:if test="${moduleParam.canDefer eq 'T' }">
					<input type="button" name="subFrame" class="btn" onclick="suspendEvent();" value="暂 缓" />
				</c:if>
				<c:if test="${moduleParam.canRollback eq 'T' }">
					<input type="button" name="subFrame" class="btn" onclick="rollBackEvent();"  value="回 退" />
				</c:if>
				<c:if test="${moduleParam.canClose eq 'T' }">
					<input type="button" name="endFlow" class="btn" onclick="endFlowEvent();" value="办 结" />
				</c:if>
					<%-- <c:if test="${moduleParam.moduleCode eq 'xwsw_cxdj' }">
					<input type="button" name="czpw" class="btn" onclick="deleteFlowEvent()" value="删除" >'
				</c:if> --%>
			<%-- <input type="button" name="backFrame" class="btn  btnWide" onclick="showFlow('${flowInstId}');" value="查看流程图"> --%>
			<c:if test="${not empty dbdjid }">
					<input type="button" name="backFrame" class="btn" onclick="dbhf(this);" value="督办回复" />
				</c:if>
				<c:if test="${moduleParam.hasSq eq 'T' }">
				<input type="button" name="nbsq" class="btn btnWide" onclick="popWin('<%=request.getContextPath()%>/powerruntime/optApply!startEntrance.do?djId=${sqdjId}&nodeInstId=${nodeInstId }&itemtype=SQ','关联内部事权');" value="启动事权" />
				</c:if>
				<c:if test="${moduleParam.hasFw eq 'T' }">
				<input type="button" name="qdfw" class="btn" onclick="popWin('<%=request.getContextPath()%>/powerruntime/optApply!startEntrance.do?djId=${sqdjId}&nodeInstId=${nodeInstId }&itemtype=FW','关联发文');" value="启动发文" />
				</c:if>
				
				 <c:if test="${moduleParam.hasQb eq 'T' }"> 
					 <input type="button" name="subFrame" class="btn" onclick="popWin('<%=request.getContextPath()%>/powerruntime/optApply!startEntrance.do?djId=${sqdjId}&nodeInstId=${nodeInstId }&itemtype=QB','关联签报');" value="启动签报" /> 
				 </c:if> 
			
			<%--  <input type="button" onclick="popWin('<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&from=public','发送邮件');"  value="发送邮件"
							class="btn" />  
							
			<input type="button" onclick="popWin('<%=request.getContextPath()%>/oa/oaRemindInformation!edit.do?from=public','发起提醒');" class="btn" value="发起提醒"/>   --%>
			<%-- <c:if test="${empty bashboard }">
				<input type="button" name="backFrame" class="btn" onclick="parent.navTab.reload();" value="返 回">
			</c:if> --%>
			
			<div style="height: 50px;"></div>
		</div>
	</div>
</div>

<!-- 返回顶部浮层 -->
<div class="sider">
	<ul>
	<li id="sy" style="display:list-item;">
		<A HREF="javascript:void(0);"
			onclick="returnNewPage('sy')">
			返回首页
		</A>
	</li>
	<c:if test="${empty dashboard}">
    <li id="fhdb" style="display:list-item;">
		<A HREF="javaScript:void(0);" title="返回待办"
		onclick="parent.navTab.reload();"
			>
			返回待办
		</A>
	</li>
	</c:if>
	<c:if test="${not empty dashboard}">
    <li id="fhdb" style="display:list-item;">
		<A HREF="${ctx}/dispatchdoc/vuserTaskListOA!list.do" title="返回待办"
			>
			返回待办
		</A>
	</li>
	</c:if>
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
		<A href="javascript:window.scrollTo(0,0);">
			返回顶部
		</A>
	</li>
	</ul>
</div> 
	<div id="alertRole" class="alert" style="position:absolute;top:20px;left:20%;overflow: hidden;width: 300px;height: 100px;">
		<s:form name="nodeForm" method="post" action="ioDocTasksExcute!assignWorkGroup.do" target="pfFrame" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<s:hidden id="djId" name="djId" value="%{sqdjId}" />
			<s:hidden id="flowInstId" name="flowInstId" value="%{flowInstId}" />
			<s:hidden id="nodeInstId" name="nodeInstId" value="%{nodeInstId}" />
			
			
			<h4><span>主办承办</span><span id="close3" class="close"  onclick="closeAlert('alertRole');" >关闭</span></h4>
			<tr>
				<td class="addTd">办件角色：</td>
				<td>
					<select name="roleCode">   
						<option value="">   
								<c:out value="-- 请选择 --"/>   
						</option>    
						<c:forEach var="row" items="${cp:DICTIONARY('WFTeamRole')}">     
								<option value="${row.key}" <c:if test="${param.roleCode==row.key}">selected="selected"</c:if>><c:out value="${row.value}" /></option>
						</c:forEach> 
					</select>
				</td>
			</tr>
			<br/>
			<tr>
				<td class="addTd">添加人员：</td>
				<td>
					<input size="32" type="text" name="userName" id="userName" >
					<s:hidden name="userCode" id="userCode"  />
				</td>
			</tr>
			<br>
			<tr>
				<td align="center">
					<input type="submit" id="sub" value="确定" class="sub" onclick="javascript:hideDiv('#alertRole')"/>
						&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span>
				</td>
			</tr>
		</s:form>
	</div>
		
	<!-- 选择人员的模块 -->
	<div id="attAlert" class="alert"
		style="width: 600px; height: 340px; position:absolute;bottom:160px;left:20%;overflow: hidden;">
		<h4>
			<span id="selectTT">选择</span><span id="close2"
				style="float: right; margin-right: 8px;" class="close"
				onclick="closeAlert('attAlert');">关闭</span>
		</h4>
		<div class="fix">
			<div id="leftSide"></div>
			<div id="l-r-arrow">
				<div class="lb"></div>
				<div class="rb"></div>
			</div>
			<div id="rightSide">
				<ul></ul>
			</div>
			<div id="t-b-arrow">
				<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
				<b class="btns"><input id="save" class="btn" type="button"
					value="保     存" /><input id="clear" class="btn" type="button"
					value="取     消" style="margin-top: 5px;" /></b>
			</div>
		</div>
	</div>
	
	<!-- 选择机构模块 -->
	<div id="alertOrg" class="alert" style="width: 300px;height: 100px;">
		<s:form id="orgNodeForm" name="nodeForm" method="post" action="ioDocTasksExcute!assignFlowOrganize.do" target="pfFrame" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<s:hidden id="djId" name="djId" value="%{sqdjId}" />
			<s:hidden id="flowInstId" name="flowInstId" value="%{flowInstId}" />
			<s:hidden id="nodeInstId" name="nodeInstId" value="%{nodeInstId}" />
			<h4><span>协办处室</span><span id="close1" class="close"  onclick="closeAlert('alertOrg');" >关闭</span></h4>
				<tr>
				<td class="addTd">办件角色：</td>
				<td>
				<select name="roleCode">
					<option value="">
						<c:out value="-- 请选择 --"/>
					</option>
					<c:forEach var="row" items="${cp:DICTIONARY('IODOC_HQ')}">
						<option value="${row.key}" <c:if test="${param.roleCode==row.key}">selected="selected"</c:if>><c:out value="${row.value}" /></option>
					</c:forEach>
				</select>
				</td>
				</tr>
			<tr>
			<br>
			<td class="addTd">添加相关（部门）处室：</td>
			<td>
			<input size="32" type="text" name="orgName" id="orgName" >
			<s:hidden name="orgCode" id="orgCode"  />
			</td>
			</tr>
			<br>
			<tr>
			<td align="center">
			<input type="submit" id="sub" value="确定" class="sub" onclick="javascript:hideDiv('#alertOrg');" />
				&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span></td></tr>
		</s:form>
	</div>
	<input type="hidden" id="unitsJson" name="unitsJson" value='${unitsJson}' />
	<input type="hidden" id="stuffid" name="stuffid" value="${stuffid}" />
	<input type="hidden" id="wj" name="wj" value="${wj}" />
	<input type="hidden" id="uploadfilepath" name="uploadfilepath" value="${uploadfilepath}" />
	
<script type="text/javascript">
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


function openDB() {
	url="${pageContext.request.contextPath}/oa/oaSupervise!flowEdit.do?supDjid=${sqdjId}&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '督办发起', width: 800, height: 250});
}

	function dbhf(){
		
		url="<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000859&s_supdjid=${dbdjid}";
		window.location.href=url;
		
	}
	</script>
<script type="text/javascript">
// 缩小和其他iframe之间的距离
 $("#frm_showIdeas").attr("style","margin-bottom:-10px;");


function selectPopWin(json,o1,o2,oShow){
	new person(json,o1,o2,oShow).init();
	setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?($("#firstPage",window.parent.document).scrollTop()-200==0?-200:$("#firstPage",window.parent.document).scrollTop()-200):$(document).scrollTop()));
}
function selectPopWinTemp(json,o1,o2 ){
	new person(json,o1,o2).init();
	setAlertStyle("attAlert");
}

function selectPopWinTree(ja,o1,o2){
	new treePerson($.parseJSON(ja), o1, o2).init();/* 此处人员限制为一人 */
	
	setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?($("#firstPage",window.parent.document).scrollTop()-200==0?-200:$("#firstPage",window.parent.document).scrollTop()-200):$(document).scrollTop()));
	 $("#attAlert").removeAttr("top");
}
function selectPopWinTree(ja,o1,o2,oShow){
	new treePerson($.parseJSON(ja), o1, o2,oShow).init();/* 不限制人数--限制人数时无法批量选择人员 */
	setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?($("#firstPage",window.parent.document).scrollTop()-200==0?-200:$("#firstPage",window.parent.document).scrollTop()-200):$(document).scrollTop()));
}
function selectPopWinTree2(ja,o1,o2,oShow){/* 不限制人数--限制人数时无法批量选择人员 */
	new treePerson($.parseJSON(ja), o1, o2,oShow).init();
	setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?($("#firstPage",window.parent.document).scrollTop()-200==0?-200:$("#firstPage",window.parent.document).scrollTop()-200):$(document).scrollTop()));
}
function selRole(css){
	setAlertStyleTemp('alertRole', css);;
}

function selOrg(css){
	setAlertStyleTemp('alertOrg', css);;
}

$("#userName").click(function(){
	var d = '${userjson}';
	$('#attAlert').show();
	if(d.trim().length){
		selectPopWinTemp(jQuery.parseJSON(d),$("#userName"),$("#userCode"));
	};
});

$("#orgName").click(function(){
	var d = '${unitsJson}';
	$('#attAlert').show();
	if(d.trim().length){
		selectPopWinTemp(jQuery.parseJSON(d),$("#orgName"),$("#orgCode"));
	};
});

$("#_userName").click(function(){
	var d = '${userjson}';
	$('#attAlert').show();
	if(d.trim().length){
		selectPopWinTemp(jQuery.parseJSON(d),$("#_userName"),$("#_userCode"));
	};
});

function dohide(){
	if(getID("boxContent"))
		$("#boxContent").hide();
		
}

function bindEvent(o1,o2,$this){
	o1.val($this.html());
	o2.val($this.attr("rel"));
		//$("#shadow").hide();
		$("#boxContent").hide();
}
function  hideDiv(o){
	$(o).hide();
}
</script>

<!-- 上面是选择人员的模块 -->

<script type="text/javascript">
function getOptBaseInfoJson(){
	return ${optBaseInfoJson};
}

function getOptCommonBizJson(){
	return ${optCommonBizJson};
}

function getHeight(height){
	$("#transFrame").height(height);
}

/********************框架主控事件****************************/
//事件按钮ID统一使用:流程提交-submitBtn、业务保存-saveBtn、暂缓-suspendBtn、回退-rollBackBtn、办结-endFlowBtn
function submitEvent() {
      debugger;	
	//督办信息
	var dbdjid = $("#dbdjid").val();
		if (dbdjid!= null&&dbdjid!='') {
			alert("该办件的环节正在被督办，请回复督办后并刷新操作！");
				return false;
		
		} else {
			if(typeof document.getElementById("transFrame").contentWindow.checkForm === 'function'){
                if(!document.getElementById("transFrame").contentWindow.checkForm()){
                    return false;
                }
            }
            openLoadIng(true);
			getFormByFrame('transFrame').submitBtn.click();
			if($("#dashboard")!=null&&$("#dashboard")!=""){
				DialogUtil.close();
			}
		}
	
	
	return false;
}
//保存事件
function saveEvent(){
	getFormByFrame('transFrame').saveBtn.click();
	if($("#dashboard")!=null&&$("#dashboard")!=""){
		DialogUtil.close();
	}
}
//暂缓事件
function suspendEvent(){
	getFormByFrame('transFrame').suspendBtn.click();
}

//回退事件
function rollBackEvent(){
	getFormByFrame('transFrame').rollBackBtn.click();
}
//办件事件
function endFlowEvent(){
	getFormByFrame('transFrame').endFlowBtn.click();
}

function deleteFlowEvent(){
	getFormByFrame('transFrame').deleteFlowBtn.click();
}

//返回事件
function backEvent(){
	getFormByFrame('transFrame').backBtn.click();
}

function getFormByFrame(frameName){
	
	var frmObj = window.frames[frameName];
	var formObj = null;
	var forms = frmObj.document.forms;
	for(var i = 0 ; i <forms.length;i++ ){
		if(!forms[i].disabled){
			formObj = forms[i];
			break;
		}
	}
	return formObj;
}

//部分业务界面不需要保存按钮，可以调用此方法
function hiddSaveBtn(){
	document.getElementById("saveSpan").style.display = 'none';
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
	var tempHeight = $(window).height() + scrollTop - 75;//50为手动调的误差值
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
	$("#isReady").val("ok");
    $(window).unbind("scroll").bind("scroll",scrollEvent);
    $("#attAlert").removeAttr("top");
    resizeIframeWidth();
});

/********************框架主控事件****************************/
</script>
<script type="text/javascript">
	var sh = function(h){
		
		if(document.getElementById("viewStuffsFrame")){
			var t = document.getElementById("viewStuffsFrame");
			t.height = h;
		};
		if(document.getElementById("AllPunishOptInfoFrame")){
			document.getElementById("AllPunishOptInfoFrame").height = h;
		};
	};
	function doopenNewWindow(){
		var url = "<%=request.getContextPath()%>/powerruntime/optApply!startEntrance.do?djId=${sqdjId}&nodeInstId=${nodeInstId }&itemtype=SQ";
		window.showModalDialog(url,window,"dialogHeight:50px;dialogWidth:350px;center:yes;help:no;scroll:yes;status:no;edge:raised");
	}
	function popWin(path,name){
		DialogUtil.open(name,path,1200,700,null,null,function(){
			$("#AllInfoFrame").attr("src",$("#AllInfoFrame").attr("src"));
		});
	}
	function returnNewPage(type){
		if(type=="sy"){
		 	top.location.href="<%=request.getContextPath()%>/sys/mainFrame!showMain.do";
		}
	}
	
	function openMenu(obj,tabid, url){
		//navTab.closeAllTab();
    /* 	$("#layout").removeClass("improveStyle");
		var $this = $(obj);

		$("#ul_YGJG>li>div>div.first_collapsable").removeClass("first_collapsable").addClass("first_expandable");
		$("#ul_YGJG>li>div>div.collapsable").removeClass("collapsable").addClass("expandable");
		//navTab.closeAllTab();
		
		var title = $this.attr("title") || $this.text();
		var tabName = $this.attr("rel") || "_blank";
		var fresh = eval($this.attr("fresh") || "true");
		*/
	if(tabid=="fyj"){//发邮件
		parent.menuShow();
		$(".toggleCollapse h2",parent.document).html("个人办公");
		parent.hideMenu();
		$("#menu_YGJGGRBG",parent.document).show();
		if (!$("#ul_YGJGGRBG",parent.document).is(":visible"))
			$("#menu_YGJGGRBG>div>a",parent.document).click();
		$("#menu_YGJGGRBG>div",parent.document).hide();
		setTimeout(function() {
			if ($("#menu_GRBGGRYJ>div>div.expandable",parent.document).size() > 0)
				$("#menu_GRBGGRYJ>div>a",parent.document).click();		
		}, 200);
		setTimeout(function() {
			$("#menu_FJX>div>a",parent.document).addClass("selected");
		}, 300);
	} else if(tabid=="ftx"){//发提醒
		parent.menuShow();
		$(".toggleCollapse h2",window.parent.document).html("日常办公");
		parent.hideMenu();
		$("#menu_YGJGRCBG",window.parent.document).show();
		if (!$("#ul_YGJGRCBG",window.parent.document).is(":visible"))
			$("#menu_YGJGRCBG>div>a",window.parent.document).click();
		$("#menu_YGJGRCBG>div",window.parent.document).hide();
		setTimeout(function() {
			if ($("#menu_TXSJ>div>div.expandable",window.parent.document).size() > 0)
				$("#menu_TXSJ>div>a",window.parent.document).click();		
		}, 200);
		setTimeout(function() {
			$("#menu_DFSTX>div>a",window.parent.document).addClass("selected");
		}, 300);
	} else if(tabid == "fhdb"){
		parent.menuShow();
		$(".toggleCollapse h2",window.parent.document).html("个人办公");
		parent.hideMenu();
		$("#menu_YGJGGRBG",window.parent.document).show();
		if (!$("#ul_YGJGGRBG",window.parent.document).is(":visible"))
			$("#menu_YGJGGRBG>div>a",window.parent.document).click();
		$("#menu_YGJGGRBG>div",window.parent.document).hide();
		/* setTimeout(function() { */
			$('#menu_GRBGGRDB>div>a',window.parent.document).click();
			$("#menu_GRBGDBSX>div>a",window.parent.document).addClass("selected");
		/* }, 200);  */
	}
	window.location.href=url;
	//navTab.openTab(tabName, url,{title:title, fresh:fresh, external:external});
}
	function showView(title,link){
		DialogUtil.open(title,link,1200,700);
	}
	function addRT(url) {
		art.dialog
				.open(url,
						 {title: '', width: 1050, height: 640});

	}
	
	
	function DetachPdf() 
	{	
		doit2();
		if (pdfFile.value == "") {
			alert("请选择PDF文件");
			return;
		}
		var lID = 0;
		var nNum = 0;
		var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
		
		var lRet = obj.DetachPdf(pdfFile.value, 1, "0");
		if(lRet == 0)
			alert("脱密成功");
		else
			alert(obj.GetErrorMsg());
		SendPrintPDF();
		
	}
	
	function SendPrintPDF() 
	{
		if (pdfFile.value == "") {
			alert("请选择PDF文件");
			return;
		}
		var lID = 0;
		var nNum = 0;
		var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
		
		var lRet = obj.SendPrintPDF(pdfFile.value, "", affixUrl.value + "/extend/interfaces/ReceivePrint.aspx");
		if(lRet == 0)
			alert("打印成功");
		else
			alert(obj.GetErrorMsg());
		
	}
	
	function Seal() 
	{	
		doit2();
		if (pdfFile.value == "") {
			alert("请选择PDF文件");
			return;
		}
		var lID = 0;
		var nNum = 0;
		var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
		
		/*
		<!-- 应用系统识别码，公章系统分配 -->
		<AppCode>t8qR98kG5v</AppCode>
		<!-- 应用系统名称 -->
		<AppName>OA系统</AppName>
		<!-- 操作人 -->
		<ProcMan>张三</ProcMan>
		<!—公章所属单位，如果是个人章，填个人，服务器端只记录日志 -->
		<StampOwner>单位1</StampOwner>
		<!-- 文件名称 -->
		<FileName>XXXX.pdf</FileName>
		<!-- 标题 -->
		<Title>文件标题</Title>
		*/
		//设置操作者信息
		var lRet = obj.SetSealProcInfo("<AppCode>t8qR98kG5v</AppCode><AppName>OA系统</AppName><ProcMan>张三</ProcMan><StampOwner>单位1</StampOwner><FileName>XXXX.pdf</FileName><Title>文件标题</Title>");
		
		var lRet = obj.PDFVisualSeal(pdfFile.value, pdfFile.value, affixUrl.value, "<DeviceStyle>112</DeviceStyle>", "地方", "原因", "联系方式");
		
		if (lRet != 0)
		{
			alert(obj.GetErrorMsg());
		}
		SaveAttachment();
	}
	
	//自动下载正文生成PDF文件到C盘GZpdf文件夹下
	function doit2(){
		var stuffid = $("#stuffid").val();
		var ado_stream = new ActiveXObject("ADODB.Stream");
		var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		//下载url地址
		var strUrl = "${pageContext.request.contextPath}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid="+stuffid;
		//保存本地的文件名与磁盘上文件名保持一直
		var wj = $('#wj').val();
		var strHelpFile = "C:\\GZpdf\\"+wj;
		xmlhttp.open("Get",strUrl,false);
		xmlhttp.send();
		ado_stream.Type = 1;
		ado_stream.open();
		ado_stream.Write(xmlhttp.responseBody);
		ado_stream.SaveToFile(strHelpFile,2);
		ado_stream.close();
		
	} 
	
	// 保存到xml附件，并且通过ajax 上传  
    function SaveAttachment(){
  
		var upload_filename = $('#wj').val();//正文pdf文件名
	
		var localFilePath = "C:\\GZpdf\\"+upload_filename;//已盖章文件本地路径
		
		var uploadfilepath = $('#uploadfilepath').val();//上传服务器磁盘路径
		
        var upload_target_url = "${pageContext.request.contextPath}/powerruntime/generalOperator!doPost.do"; //调用后台上传文件方法 
          
        var strTempFile = localFilePath;  
        
        // 创建XML对象，组合XML文档数据  
        var xml_dom = createDocument();  
        
        xml_dom.loadXML('<?xml version="1.0" encoding="GBK" ?> <root/>');  
      
        // 创建ADODB.Stream对象  
        var ado_stream = new ActiveXObject("ADODB.Stream");
        // 设置流数据类型为二进制类型  
        ado_stream.Type = 1; // adTypeBinary  
        // 打开ADODB.Stream对象  
        ado_stream.Open();  
        // 将本地文件装载到ADODB.Stream对象中  
		//var strTempFile = "C:\\GZpdf\\"+upload_filename;
        ado_stream.LoadFromFile(strTempFile);  
        // 获取文件大小（以字节为单位）  
        var byte_size = ado_stream.Size; 
        // 设置数据传输单元大小为1KB  
        var byte_unit = 1024;  
        // 获取文件分割数据单元的数量  
        var read_count = parseInt((byte_size/byte_unit).toString())+parseInt(((byte_size%byte_unit)==0)?0:1);  
          
        // 创建XML元素节点，保存上传文件名称  
        var node = xml_dom.createElement("uploadfilename");  
        node.text = upload_filename.toString();  
        var root =  xml_dom.documentElement; 
        root.appendChild(node);  
        // 创建XML元素节点，保存上传文件路径  
        var filepathnode = xml_dom.createElement("uploadfilepath");  
        filepathnode.text = uploadfilepath.toString();  
        root.appendChild(filepathnode);  
    
        var sizenode = xml_dom.createElement("uploadfilesize");  
        sizenode.text = byte_size.toString();  
        root.appendChild(sizenode); 
        
        // 创建XML元素节点，保存上传文件内容  
        for(var i=0;i<read_count;i++)  
        {  
            var node = xml_dom.createElement("uploadcontent");  
            // 文件内容编码方式为Base64  
            node.dataType = "bin.base64";  
            // 判断当前保存的数据节点大小，根据条件进行分类操作  
            if((parseInt(byte_size%byte_unit)!=0)&&(i==parseInt(read_count-1)))  
            {  
                // 当数据包大小不是数据单元的整数倍时，读取最后剩余的小于数据单元的所有数据  
                node.nodeTypedValue = ado_stream.Read();  
            }  
            else  
            {  
                // 读取一个完整数据单元的数据  
                node.nodeTypedValue = ado_stream.Read(byte_unit);  
            }  
            root.appendChild(node);  
        }  
        // 关闭ADODB.Stream对象  
        ado_stream.Close();  
        delete ado_stream;  
        // 创建Microsoft.XMLHTTP对象  
        // var xmlhttp = new ActiveXObject("microsoft.xmlhttp");  
        var xmlhttp= window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHttp");  
        // 打开Microsoft.XMLHTP对象  
        xmlhttp.open("post",upload_target_url,false);  
        // 使用Microsoft.XMLHTP对象上传文件  
        xmlhttp.send(xml_dom);  
        var state = xmlhttp.readyState;  
        var success_state = true; 
        if(state != 4){  
            success_state = false;  
        }  
          
        delete xmlhttp;  
          
        return success_state;  
  }  
    
  // 创建DOMdocuemnt  
    function createDocument() {  
        var xmldom;  
        var versions = ["MSXML2.DOMDocument.6.0", "MSXML2.DOMDocument.5.0","MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument"],  
            i, len;  
        for (i = 0, len = versions.length; i < len; i++) {  
            try {  
                xmldom = new ActiveXObject(versions[i]);  
                if(xmldom != null)  
                    break;  
            } catch (ex) {  
                //跳过  
                alert("创建document对象失败！");  
            }  
        }  
        return xmldom;  
    }  
  
</script>
</body>
</html>