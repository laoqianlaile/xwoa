<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link
	href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css"
	rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js"
	type="text/javascript"></script>
</head>

<body>
	<fieldset class="search">
		<legend>
			<s:text name="label.list.filter" />
		</legend>
		<div class="searchDiv">
			<s:form action="mipLog" namespace="/powerruntime" id="mipLog_form">
				<div class="searchArea">
				<table style="width: auto;">
					<tr>
						<td class="searchTitleArea"><s:text name="mipLog.infmethods" />:</td>
						<td class="searchCountArea">
						<input type="text" class="searchCondContent" name="s_infmethods" id="s_infmethods" value="${s_infmethods}" /> 
						</td>
						<td class="searchCondArea" onclick="sub();"><div
								class="clickDiv">搜 索</div></td>
					</tr>
				</table>
				</div>
			</s:form>
		</div>
	</fieldset>

	<ec:table action="powerruntime/mipLog!list.do" items="objList"
		var="mipLog" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<c:set var="tinfmethods">
				<s:text name='mipLog.infmethods' />
			</c:set>
			<ec:column property="infmethods" title="${tinfmethods}"
				style="text-align:center" />

			<c:set var="taccparameters">
				<s:text name='mipLog.accparameters' />
			</c:set>
			<ec:column property="accparameters" title="${taccparameters}"
				style="text-align:center">
				<p>	<input type="hidden" value="${mipLog.accparameters}"/> 
					<c:choose>
						<c:when test="${fn:length(mipLog.accparameters) >40}">
							<c:out
								value="${fn:substring(mipLog.accparameters, 0, 40)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${mipLog.accparameters}" />
						</c:otherwise>
					</c:choose></p>
				</ec:column>

			<c:set var="tremarkMethods">
				<s:text name='mipLog.remarkMethods' />
			</c:set>
			<ec:column property="remarkMethods" title="${tremarkMethods}"
				style="text-align:center">
			<input type="hidden" value="${mipLog.remarkMethods}"/> 
					<c:choose>
						<c:when test="${fn:length(mipLog.remarkMethods) > 20}">
							<c:out
								value="${fn:substring(mipLog.remarkMethods, 0,20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${mipLog.remarkMethods}" />
						</c:otherwise>
					</c:choose>
				</ec:column>

			<c:set var="tcreater">
				<s:text name='mipLog.creater' />
			</c:set>
			<ec:column property="creater" title="${tcreater}"
				style="text-align:center">
				${cp:MAPVALUE('usercode',mipLog.creater)}
				</ec:column>

			<c:set var="tcreatetime">
				<s:text name='mipLog.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='powerruntime/mipLog!view.do?mipid=${mipLog.mipid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<%-- 		<a href='powerruntime/mipLog!edit.do?mipid=${mipLog.mipid}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/mipLog!delete.do?mipid=${mipLog.mipid}' 
							onclick='return confirm("${deletecofirm}mipLog?");'><s:text name="opt.btn.delete" /></a> --%>
			</ec:column>

		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
	function sub(){
			$("#mipLog_form").attr("action","mipLog!list.do");
			$("#mipLog_form").submit();
	}
	</script>
</html>
