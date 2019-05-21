<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
<sj:head locale="zh_CN" />
<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>分管部门办件查看</legend>
		<s:form action="vOptBaseList" namespace="/oa" id="vOptBaseList"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd">办件状态</td>
					<td><s:radio name="s_bizstate" id="s_bizstate"
							list="#{'W':'在办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}"></s:radio></td>
				
				<td class="addTd">办件类型</td>
				<td>
			 	<select
						id="s_itemType" name="s_itemType"
						style="width: 120px;">
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
				</tr>
				<tr>
					<td class="addTd">办件名称</td>
					<td><s:textfield name="s_transaffairname"
							value="%{#parameters['s_transaffairname']}" /></td>
					<td class="addTd">办件步骤:</td>
					<td><s:textfield name="s_nodename"
							value="%{#parameters['s_nodename']}" /></td>

				</tr>
				<tr>
					<td class="addTd">所属部门</td>
					<td><s:textfield name="s_unitname"
							value="%{#parameters['s_unitname']}" /></td>
					<td class="addTd"></td>
					<td></td>
				</tr>
				<tr>
					<td class="addTd">登记时间</td>
					<td><input type="text" class="Wdate" id="s_begincreateDate"
						<c:if test="${not empty s_begincreateDate }"> value="${s_begincreateDate}" </c:if>
						<c:if test="${empty s_begincreateDate  }">value="${param['s_begincreateDate'] }"</c:if>
						name="s_begincreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endcreateDate"
						<c:if test="${not empty s_endcreateDate }"> value="${s_endcreateDate }" </c:if>
						<c:if test="${empty s_endcreateDate  }"> value="${param['s_endcreateDate'] }" </c:if>
						name="s_endcreateDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">

</td>
				</tr>

				<tr class="searchButton">

					<td colspan="5"><s:submit method="listOwn" key="opt.btn.query"
							cssClass="btn" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/vOptBaseList!listOwn.do" items="vOptBaseList"
		var="vOptBaseinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		
		<ec:column property="none" title="状态" style="text-align:center"
				sortable="false" width="5%">
			<c:if test="${vOptBaseinfo.bizstate eq 'W' or vOptBaseinfo.bizstate eq 'T'}">
						<img title="办理中"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-blz.png">办理中
			</c:if>	
			<c:if test="${vOptBaseinfo.bizstate eq 'C'}">
						<img title="已办结"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-ybj.png">已办结
			</c:if>	
		</ec:column>
			<ec:column property="djId" title="办件编号" style="text-align:center"
				sortable="true" width="8%"/>
			<ec:column property="biztype" title="办件类型" style="text-align:center"
				sortable="true" width="12%">${cp:MAPVALUE('optTypeName',vOptBaseinfo.itemType)}</ec:column>
			<ec:column property="transaffairname" title="办件名称"
					style="text-align:center" sortable="true" width="25%">
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
					<a
						href="${pageContext.request.contextPath}/${cp:MAPVALUE('optType',vOptBaseinfo.itemType)}!generalOptView.do?djId=${vOptBaseinfo.djId}&nodeInstId=0&applyItemType=${vOptBaseinfo.applyItemType}">查看办件</a>
				<c:if test="${vOptBaseinfo.flowCode ne '000859' and vOptBaseinfo.bizstate ne 'C'}">
					<c:if test="${cp:CHECKUSEROPTPOWER('DBCBFQ', 'oaSupervise') }">
					<a href='<%=request.getContextPath()%>/oa/oaSupervise!edit.do?supDjid=${vOptBaseinfo.djId}'>督办发起</a>
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
