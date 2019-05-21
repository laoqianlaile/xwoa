<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
		行政许可查询
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<fieldset class="search">
			<legend>
				<c:choose>
					<c:when test="${s_itemtype eq 'QB' }">签报查看</c:when>		
					<c:when test="${s_itemtype eq 'SQ' }">事项查看</c:when>
					<c:when test="${empty s_itemtype}">许可查询</c:when>
				</c:choose>
			</legend>
			<s:form action="optApply" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" name="s_itemtype" value="${s_itemtype}">
				<%-- 	<tr >
						<td class="addTd">申请者:</td>
						<td><s:textfield name="s_proposer_Name" value="%{#parameters['s_proposer_Name']}"/> </td>
						<td class="addTd">申请者机构代码:</td>
						<td><s:textfield name="s_proposer_Unitcode" value="%{#parameters['s_proposer_Unitcode']}" style="width:33%;height:25px;" /> </td>
					</tr> --%>	
					<tr>
						<td class="addTd">
						<c:if test="${s_itemtype eq 'SQ' }">
						事项标题:
						</c:if>
						<c:if test="${s_itemtype eq 'QB' }">
						签报标题:
						</c:if>
						</td>
						<td><s:textfield name="s_transaffairname" value="%{#parameters['s_transaffairname']}"/> </td>
						<%-- <td class="addTd">办理部门：</td>
						<td>						
							<input type="text" id="orgName" name="orgName" style="width:33%;height:25px;" value="${cp:MAPVALUE('unitcode',param.s_orgcode)}"/>
							<input type="hidden" id="s_orgcode" name="s_orgcode" value="${param.s_orgcode}"/>	
						<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构	
						</td>	 --%>
					</tr>
					<tr>
						<td class="addTd">登记时间</td>
						<td>
						<input type="text" class="Wdate" id="s_begincreateDate"
						<c:if test="${not empty s_begincreateDate }"> value="${s_begincreateDate}" </c:if>
						<c:if test="${empty s_begincreateDate  }">value="${param['s_begincreateDate'] }"</c:if>
						name="s_begincreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endcreateDate"
						<c:if test="${not empty s_endcreateDate }"> value="${s_endcreateDate }" </c:if>
						<c:if test="${empty s_endcreateDate  }"> value="${param['s_endcreateDate'] }" </c:if>
						name="s_endcreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						
						<!-- 						根据行政级别显示 --> <c:if test="${not empty userRank}">
							<!-- 						<td class="addTd"> -->

							<c:if test="${userRank=='TZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked" </c:if>>
						查看全部&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>

							<c:if test="${userRank=='CZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked"  </c:if>>
						查看处室&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
							<!-- 					</td> -->
						</c:if></td>
				</tr>
				<c:if test="${userRank=='TZ' }">
					<tr id="tr_unitlist">
						<td class="addTd">查询部门：</td>
						<td><select name="s_unitcode" style="width: 195px">
								<c:forEach var="row" items="${unitList }">
									<option value="${row.unitcode}"
										<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
										<c:out value="${row.unitname}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:if>

						
						
					
					<tr class="searchButton">
						<td colspan="5">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/optApply!list.do" items="srPermitApplyList" var="srPermitApply"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="djId" title="办件编号" style="text-align:center" sortable="false"/>
				<c:if test="${s_itemtype eq 'SQ' }">
				<ec:column property="transaffairname" title="事权标题" style="text-align:center" sortable="false">
					<c:choose>
					<c:when test="${fn:length(srPermitApply.transaffairname) > 20}">
						<c:out value="${fn:substring(srPermitApply.transaffairname, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${srPermitApply.transaffairname}" />
					</c:otherwise>
				</c:choose>
				</ec:column> 
				</c:if>
				<c:if test="${s_itemtype eq 'QB' }">
				<ec:column property="transaffairname" title="签报标题" style="text-align:center" sortable="false">
					<c:choose>
					<c:when test="${fn:length(srPermitApply.transaffairname) > 20}">
						<c:out value="${fn:substring(srPermitApply.transaffairname, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${srPermitApply.transaffairname}" />
					</c:otherwise>
				</c:choose>
				</ec:column> 
				</c:if>
				<ec:column property="applyDate" title="填写日期" style="text-align:center" sortable="false" >
				<fmt:formatDate value="${srPermitApply.applyDate}" pattern="yyyy-MM-dd"/>
				</ec:column>
				<ec:column property="usercode" title="填写人"
				style="text-align:center" sortable="false" >
				${cp:MAPVALUE("usercode",srPermitApply.usercode)}
				</ec:column>
				<%-- 	<ec:column property="proposerName" title="申请者" style="text-align:center" sortable="false"/>
				<ec:column property="applyItemType" title="申请事项" style="text-align:center" sortable="false">
				${cp:MAPVALUE("APPLYITEM",srPermitApply.applyItemType)}
				</ec:column>
				<ec:column property="proposerType" title="申请者类别" style="text-align:center" sortable="false">
				${cp:MAPVALUE("PROPOSER_TYPE",srPermitApply.proposerType)}
				</ec:column>
				<ec:column property="proposerUnitcode" title="申请者机构代码" style="text-align:center" sortable="false">
				</ec:column> 
				<ec:column property="applyWay" title="申请方式" style="text-align:center" sortable="false">
				${cp:MAPVALUE("bjsqfs",srPermitApply.applyWay)}
				</ec:column>  --%>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<c:if test="${ srPermitApply.bizstate eq 'F'}">
					<a href='${pageContext.request.contextPath}/powerruntime/optApply!editDoOrRead.do?djId=${srPermitApply.djId}&isapplyuser=${isapplyuser}&s_itemtype=${s_itemtype}'>编辑</a>
					<a href='${pageContext.request.contextPath}/powerruntime/optApply!delete.do?djId=${srPermitApply.djId}&s_itemtype=${s_itemtype}' 
							onclick='return confirm("${deletecofirm}该记录？");'>删除</a>
					</c:if>
				<a href="${pageContext.request.contextPath}/powerruntime/optApply!generalOptView.do?djId=${srPermitApply.djId}&nodeInstId=0&s_itemtype=${s_itemtype}">查看办件</a>
				</ec:column>
			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
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
</script>
</html>
