<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		

<title></title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search" >
			<legend class="headTitle">分管部门办件查看</legend>
			<div class="searchDiv">
		<s:form action="vOptBaseList!listBM.do" namespace="/oa" id="vOptBaseList"
			style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			<div class="searchArea">
			<table style="width: auto;">
				<tr>
				<td class="searchTitleArea" >
						<label class="searchCondTitle">办件状态:</label>
						</td>
					<td class="searchCountArea">
					<s:radio name="s_bizstate" id="s_bizstate" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle"
							list="#{'W':'在办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}"></s:radio>
				</td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">办件名称:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" name="s_transaffairname"  class="searchCondContent"
							value="${s_transaffairname}" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">登记时间:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" class="searchCondContent" id="s_begincreateDate"
						<c:if test="${not empty s_begincreateDate }"> value="${s_begincreateDate}" </c:if>
						<c:if test="${empty s_begincreateDate  }">value="${param['s_begincreateDate'] }"</c:if>
						name="s_begincreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="searchCondContent" id="s_endcreateDate"
						<c:if test="${not empty s_endcreateDate }"> value="${s_endcreateDate }" </c:if>
						<c:if test="${empty s_endcreateDate  }"> value="${param['s_endcreateDate'] }" </c:if>
						name="s_endcreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">	
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
					</tr>
				<tr id="gaoji_more" style="display: none;">
				<td></td>	<td></td>
						<td  class="searchTitleArea"  >
				<label class="searchCondTitle">办件类型:</label>
				</td>
					<td class="searchCountArea">
				<select
						id="s_itemType" name="s_itemType" class="searchCondContent"
						style="width: 158px;">
							<option value="">全部</option>
							<c:forEach var="row" items="${cp:DICTIONARY('optTypeName')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode == s_itemType}"> selected="selected"</c:if>
									>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> 
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">所属部门:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" name="s_unitname" class="searchCondContent"
							value="${s_unitname}" />
					
				</td>
				</tr>

			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="oa/vOptBaseList!listBM.do" items="vOptBaseList"
		var="vOptBaseinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		
		<ec:column property="none" title="状态" style="text-align:center"
				sortable="false" width="5%">
			<c:if test="${vOptBaseinfo.bizstate eq 'W' or vOptBaseinfo.bizstate eq 'T'}">
						<img title="办理中"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-blz.png">
			</c:if>	
			<c:if test="${vOptBaseinfo.bizstate eq 'C'}">
						<img title="已办结"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-ybj.png">
			</c:if>	
		</ec:column>
			<%-- <ec:column property="djId" title="办件编号" style="text-align:center"
				sortable="true" width="8%"/> --%>
			<ec:column property="biztype" title="办件类型" style="text-align:center"
				sortable="true" width="12%">${cp:MAPVALUE('optTypeName',vOptBaseinfo.itemType)}</ec:column>
			<ec:column property="transaffairname" title="办件名称"
					style="text-align:center" sortable="true" width="25%">
					<input type="hidden" value="${vOptBaseinfo.transaffairname}"/> 
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.transaffairname) > 20}">
							<c:out
								value="${fn:substring(vOptBaseinfo.transaffairname, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.transaffairname}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="unitname" title="所属部门"
					style="text-align:center" sortable="true" width="20%">
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.unitname) > 20}">
							<c:out
								value="${fn:substring(vOptBaseinfo.unitname, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.unitname}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<%-- <ec:column property="bizstate" title="办理状态"
					style="text-align:center" sortable="true" width="8%">${cp:MAPVALUE('oa_bizstate',vOptBaseinfo.bizstate)}</ec:column> --%>
				<ec:column property="nodename" title="办理步骤"
					style="text-align:center" sortable="true" width="20%">
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.nodename) > 12}">
							<c:out
								value="${fn:substring(vOptBaseinfo.nodename, 0, 12)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.nodename}" />
						</c:otherwise>
					</c:choose>
				</ec:column>

				<ec:column property="createdate" title="登记日期"
					style="text-align:center" sortable="true" width="6%" format="yyyy-MM-dd" cell="date"/>
				<ec:column property="creater" title="登记人" style="text-align:center"
					sortable="true" width="6%" >${cp:MAPVALUE("usercode",vOptBaseinfo.creater)}</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center" width="12%">
					<a class="check_email"
						href="${pageContext.request.contextPath}/${cp:MAPVALUE('optType',vOptBaseinfo.itemType)}!generalOptView.do?djId=${vOptBaseinfo.djId}&nodeInstId=0&applyItemType=${vOptBaseinfo.applyItemType}">查看办件</a>
				<c:if test="${vOptBaseinfo.flowCode ne '000859' and vOptBaseinfo.bizstate ne 'C'}">
					<c:if test="${cp:CHECKUSEROPTPOWER('DBCBFQ', 'oaSupervise') }">
					<a class="dubanfaqi" href='<%=request.getContextPath()%>/oa/oaSupervise!edit.do?supDjid=${vOptBaseinfo.djId}'>督办发起</a>
					</c:if>
					</c:if>
				</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	$(function() {
		$('#queryUnderUnit').live("click", function() {
			unitListIsShow();
		});
		unitListIsShow();
		if(gj()){
			showgaoji();
		}
	});
	function sub(){
			$("#vOptBaseList").submit();
	}
	function unitListIsShow() {

		if ('checked' == $('#queryUnderUnit').attr("checked")) {
			if ('TZ' == "${userRank}") {
				$("#tr_unitlist").show();
			}
		} else {
			$("#tr_unitlist").hide();
		}
	};
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
	function gj(){
		var t=false;
		if($("#s_itemType").val().trim()!=""&&$("#s_itemType").val()!=null){
			t=true;
		}
		if($("input[name=s_unitname]").val().trim()!=""&&$("input[name=s_unitname]").val()!=null){
			t=true;
		}
		return t;
	}
</script>
</html>
