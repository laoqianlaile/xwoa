<!DOCTYPE html>
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
<title>行政许可查询</title>
<%-- <sj:head locale="zh_CN" /> --%>
<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<fieldset class="search">
		<legend class="headTitle">
			<c:choose>
				<c:when test="${s_itemtype eq 'QB' }">签报查看</c:when>
				<c:when test="${s_itemtype eq 'SQ' }">事项查看</c:when>
				<c:when test="${empty s_itemtype}">许可查询</c:when>
			</c:choose>
		</legend>
		<div class="searchDiv">
		<s:form id="optapplysq_form" action="optApply" namespace="/powerruntime"
			style="margin-top:0;margin-bottom:5">
				<input type="hidden" name="s_itemtype" value="${s_itemtype}">
				<input type="hidden" name="s_bizstate" value="${s_bizstate}">
				<input type="hidden" name="djid" value="${djid}">
				<div class="searchArea">
			<table style="width: auto;">
				<tr >
				
				<td class="searchTitleArea">
				<label class="searchCondTitle">
				<c:if test="${s_itemtype eq 'SQ' }">
						事项名称:
				</c:if>
				 <c:if test="${s_itemtype eq 'QB' }">
						签报名称:
						</c:if>
				</label>
				 </td>
					<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_powername"
							value="${s_powername}" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">办件名称:</label>
				 </td>
				<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_transaffairname"
							value="${s_transaffairname}" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">登记时间:</label>
				</td>
				<td class="searchCountArea">
				<input type="text" class="Wdate searchCondContent" id="s_begincreateDate"
						<c:if test="${not empty s_begincreateDate }"> value="${s_begincreateDate}" </c:if>
						<c:if test="${empty s_begincreateDate  }">value="${param['s_begincreateDate'] }"</c:if>
						name="s_begincreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label><input type="text" class="Wdate searchCondContent" id="s_endcreateDate"
						<c:if test="${not empty s_endcreateDate }"> value="${s_endcreateDate }" </c:if>
						<c:if test="${empty s_endcreateDate  }"> value="${param['s_endcreateDate'] }" </c:if>
						name="s_endcreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
				<c:if test="${not empty userRank}">
				<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
				</c:if>
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
				<c:if test="${not empty userRank}">
				<tr id="gaoji_more" style="display: none;">
				<c:if test="${not empty userRank}">
				<td class="searchTitleArea" colspan="2">
							<c:if test="${userRank=='TZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看全部</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>

							<c:if test="${userRank=='CZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked"  </c:if>>
						<span style="color:#6b9bcf;">查看处室</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						</c:if>
					<c:if test="${userRank=='TZ' }">
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
						<label class="searchCondTitle">查询部门：</label>
						</td>
				<td class="searchCountArea">
						<select name="s_unitcode" class="searchCondContent">
								<c:forEach var="row" items="${unitList }">
									<option value="${row.unitcode}"
										<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
										<c:out value="${row.unitname}" />
									</option>
								</c:forEach>
						</select>
						</td>
				</c:if>
				</tr>
				</c:if>
				
				


			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="powerruntime/optApply!listSQ.do"
		items="srPermitApplyList" var="srPermitApply"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<%-- <ec:column property="djId" title="办件编号" style="text-align:center"
				sortable="false" /> --%>
			<c:if test="${s_itemtype eq 'SQ' }">
				<ec:column property="powername" title="事项名称"
					style="text-align:center" sortable="false">
					<c:choose>
						<c:when test="${fn:length(srPermitApply.powername) > 20}">
							<c:out
								value="${fn:substring(srPermitApply.powername, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${srPermitApply.powername}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
			</c:if>
			<c:if test="${s_itemtype eq 'QB' }">
				<ec:column property="transaffairname" title="签报名称"
					style="text-align:center" sortable="false">
					<c:choose>
						<c:when test="${fn:length(srPermitApply.powername) > 20}">
							<c:out
								value="${fn:substring(srPermitApply.powername, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${srPermitApply.powername}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
			</c:if>
			<ec:column property="transaffairname" title="办件名称"
				style="text-align:center" sortable="false" />
			<ec:column property="applyDate" title="填写日期"
				style="text-align:center" sortable="false">
				<fmt:formatDate value="${srPermitApply.applyDate}"
					pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="usercode" title="填写人"
				style="text-align:center" sortable="false" >
				${cp:MAPVALUE("usercode",srPermitApply.usercode)}
				</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<c:if test="${empty djid }">
					<c:set var="deletecofirm">
						<s:text name="label.delete.confirm" />
					</c:set>
					<c:if test="${ srPermitApply.bizstate eq 'F'}">
						<a class="bianji"
							href='${pageContext.request.contextPath}/powerruntime/optApply!editDoOrReadSQ.do?djId=${srPermitApply.djId}&isapplyuser=${isapplyuser}&s_itemtype=${s_itemtype}'>编辑</a>
						<a class="delete_email"
							href='${pageContext.request.contextPath}/powerruntime/optApply!delete.do?djId=${srPermitApply.djId}&s_itemtype=${s_itemtype}'
							onclick='return confirm("${deletecofirm}该记录？");'>删除</a>
					</c:if>
					<a class="check_email"
						href="${pageContext.request.contextPath}/powerruntime/optApply!generalOptView.do?djId=${srPermitApply.djId}&nodeInstId=0&s_itemtype=${s_itemtype}&applyItemType=${srPermitApply.applyItemType}&flowInstId=${srPermitApply.flowInstId}">查看办件</a>
				</c:if>
				<c:if test="${not empty djid }">
					<a class="guanlianshixiang" href='javaScript:void(0);' onclick="confirmSQ('${srPermitApply.djId}','${djid}');return false;">关联事项</a>
				</c:if>
				<%-- <c:if test="${cp:CHECKUSEROPTPOWER('SQCK', 'adminList') and srPermitApply.bizstate ne 'F'}">
						<a class="guanli" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listFlowInstNodes.do?flowInstId=${srPermitApply.flowInstId}">跟踪流程</a>
					</c:if> --%>
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	function confirmSQ(no,djid){
		if(window.confirm("是否确认关联事权")){
			 $.post(
					  "<%=request.getContextPath()%>/oa/oaAssetinformationBond!saveCombineSQ.do?no="+no+"&djid="+djid ,
			         function(){
						  window.opener.location.reload();
						  window.close();	
			         }
			   );
			return true;
		}else{
			return false;
		}
	}
function bindEvent(o1,o2,$this){
	o1.val($this.html());
	o2.val($this.attr("rel"));
	if(getID("shadow")){
		$("#shadow").hide();
		$("#boxContent").hide();
	}
}
$("#orgName").bind('click',function(){
	var menuList = ${unitsJson};
	var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
	if(getID("shadow")){
		$("#shadow").show();
		$("#boxContent").show();
	}else{
		$("body").append(shadow);
		$("#shadow").height(document.body.scrollHeight);
		setTimeout(function(){
			menuDisplay(menuList,"${parentunit}");	
		},0);
	};
	$("#lists span").live('click',function(){
		var $this = $(this);
		bindEvent($("#orgName"),$("#s_orgcode"),$this);
		$("#lists span").die("click");
	});
});

$(function() {
	$('#queryUnderUnit').live("click", function() {
		unitListIsShow();
	});
	unitListIsShow();
});

function unitListIsShow() {

	if ('checked' == $('#queryUnderUnit').attr("checked")) {
		if ('TZ' == "${userRank}") {
			$("#tr_unitlist").show();
		}
	} else {
		$("#tr_unitlist").hide();
	}
};
function sub(){
	$("#optapplysq_form").attr("action","optApply!listSQ.do");
	$("#optapplysq_form").submit();
}
function showgaoji(){
	$("#shouqi").show();
	$("#gaoji_more").show();
	$("#gaoji").hide();
}
function toshouqi(){
	$("#shouqi").hide();
	$("#gaoji_more").hide();
	$("#gaoji").show();
}
</script>
</html>
