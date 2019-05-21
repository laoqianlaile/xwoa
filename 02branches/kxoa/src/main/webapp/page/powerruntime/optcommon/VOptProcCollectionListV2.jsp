<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>		
<title><s:text name="VOptProcCollection.list.title" /></title>
	<script type="text/javascript">
	 function viewDetail(djId,nodeInstId,url){
		 var paramForm = $("#listReqAttrParam");
		 paramForm.attr("action",url.substring(0,url.indexOf('?')));
		 
		 var djIdInput = paramForm.find("input[name='djId']"),
			nodeInstIdInput = paramForm.find("input[name='nodeInstId']");
			if(djIdInput.length == 0){
				djIdInput = $("<input>",{"name":"djId","type":"hidden"});
				paramForm.append(djIdInput);
			}
			if(nodeInstIdInput.length == 0){
				nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
				paramForm.append(nodeInstIdInput);
			}
			djIdInput.val(djId);
			nodeInstIdInput.val(nodeInstId);
		 paramForm.submit();
	 }
	</script>
	<style type="text/css">
	.eXtremeTable{margin-top:0!important}
	.searchDiv{margin-bottom:0!important;padding-bottom:5px!important}
	</style>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search" >
			<legend class="headTitle">
		${cp:MAPVALUE("GWTYPE",s_atttype)}收藏
		</legend>
		<div class="searchDiv" >
		<s:form id="voptproccollectionv2form" action="VOptProcCollection" namespace="/powerruntime" method="post">
				<input type="hidden"  name="s_atttype" value="${s_atttype}" />
			<div class="searchArea">
			<table style="width: auto;">
				<tr>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px">当前步骤:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_nodeName" value="${s_nodeName }"/>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px">业务名称:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_transaffairname" value="${ s_transaffairname}"/>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px">收藏时间:</label>
					</td>
					<td class="searchCountArea">
					 <input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label><input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期"> 
					
					</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				
					
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
     <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
	<ec:table action="powerruntime/VOptProcCollection!list.do"
		items="objList" var="VOptProcCollection"
		imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<input type="hidden" name="s_atttype" value="${s_atttype }" />
		<ec:row>
		<ec:column property="atttype" title="业务类别" style="text-align:center">
			${cp:MAPVALUE("oa_ITEM_TYPE",VOptProcCollection.atttype)}
			</ec:column>
			<ec:column property="transaffairname" title="业务名称"
				style="text-align:center">
				<input type="hidden" value="${VOptProcCollection.transaffairname}"/> 
					<c:choose>
						<c:when test="${fn:length(VOptProcCollection.transaffairname) > 20}">
							<c:out
								value="${fn:substring(VOptProcCollection.transaffairname, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${VOptProcCollection.transaffairname}" />
						</c:otherwise>
					</c:choose>	
			</ec:column>
			<ec:column property="userCode" title="收藏者" style="text-align:center">
			${cp:MAPVALUE("usercode",VOptProcCollection.cid.usercode)}
			</ec:column>
			<ec:column property="attsettime" title="收藏时间"
				style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
			<ec:column property="nodeName" title="当前步骤" style="text-align:center" />
			<ec:column property="bizstate" title="业务状态" style="text-align:center" >
			${cp:MAPVALUE("oa_bizstate",VOptProcCollection.bizstate)}
			</ec:column>
			<ec:column property="lastUpdateTime" title="更新时间"
				style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">			
				<a class="check_email"
					href='javascript:void(0);' onclick="viewDetail('${VOptProcCollection.cid.djId}',0,'${ctx}/${VOptProcCollection.superUrl}')"><s:text
						name="opt.btn.view" /></a>
						<c:if test="${VOptProcCollection.isPower eq '1'}">
				<a class="banli" href="<c:url value='${VOptProcCollection.nodeOptUrl}' />" >办理</a>
				</c:if>
				<c:if test="${VOptProcCollection.isatt eq 'T'}">
				<a class="quxiaoshouchang"
					href='powerruntime/VOptProcCollection!removeColl.do?cid.djId=${VOptProcCollection.cid.djId}&cid.usercode=${VOptProcCollection.cid.usercode}&s_atttype=${s_atttype}'
					onclick='return confirm("${deletecofirm}确定取消收藏操作吗?");'>取消收藏</a>
					</c:if>
			   <c:if test="${VOptProcCollection.isatt eq 'F'}">
				<a class="shouchang"
					href='powerruntime/VOptProcCollection!removeColl.do?cid.djId=${VOptProcCollection.cid.djId}&cid.usercode=${VOptProcCollection.cid.usercode}&s_atttype=${s_atttype}'
					onclick='return confirm("${deletecofirm}确定取消收藏操作吗?");'>办件收藏</a>
					</c:if>
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
function sub(){
		$("#voptproccollectionv2form").attr("action","VOptProcCollection!list.do");
		$("#voptproccollectionv2form").submit();
	} 
</script>
</html>
