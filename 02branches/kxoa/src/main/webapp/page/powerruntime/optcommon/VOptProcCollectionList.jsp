<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="VOptProcCollection.list.title" /></title>
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="border: hidden 1px #000000;">
		<legend>
		${cp:MAPVALUE("GWTYPE",s_atttype)}收藏
		</legend>

		<s:form action="VOptProcCollection" namespace="/powerruntime"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" name="s_atttype" value="${s_atttype}">
				<tr>
					<td class="addTd">当前步骤:</td>
					<td><s:textfield name="s_nodeName" /></td>
					<td class="addTd">业务名称:</td>
					<td><s:textfield name="s_transaffairname" /></td>
				</tr>
				<tr>
					<td class="addTd">收藏时间:	</td>
						<td>	
						
						 <input type="text" class="Wdate" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期"></td>
					<td><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

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
				<a
					href='../${VOptProcCollection.superUrl}&nodeInstId=0'><s:text
						name="opt.btn.view" /></a>
						<c:if test="${VOptProcCollection.isPower eq '1'}">
				<a href="<c:url value='${VOptProcCollection.nodeOptUrl}' />" >办理</a>
				</c:if>
				<c:if test="${VOptProcCollection.isatt eq 'T'}">
				<a
					href='powerruntime/VOptProcCollection!removeColl.do?cid.djId=${VOptProcCollection.cid.djId}&cid.usercode=${VOptProcCollection.cid.usercode}&s_atttype=${s_atttype}'
					onclick='return confirm("${deletecofirm}确定取消收藏操作吗?");'>取消收藏</a>
					</c:if>
			   <c:if test="${VOptProcCollection.isatt eq 'F'}">
				<a
					href='powerruntime/VOptProcCollection!removeColl.do?cid.djId=${VOptProcCollection.cid.djId}&cid.usercode=${VOptProcCollection.cid.usercode}&s_atttype=${s_atttype}'
					onclick='return confirm("${deletecofirm}确定取消收藏操作吗?");'>办件收藏</a>
					</c:if>
			</ec:column>
		</ec:row>
	</ec:table>

</body>
</html>
