<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title></title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>
	<input id="isPcpunishStandard" type="hidden" name="isPcpunishStandard"
		value="${isPcpunishStandard}">

	<c:if test="${isPcpunishStandard eq 1}">

		<ec:table action="pcfreeumpiretype!list.do"
			items="PcfreeumpiretypeList" var="pcfreeumpiretype"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false"
			showStatusBar="false" showTitle="false">

			<ec:row>
				<c:set var="tpunishtypeid">处罚种类</c:set>
				<c:forEach var="row" items="${cp:DICTIONARY('punishType')}">
					<c:if test="${pcfreeumpiretype.punishtypeid eq row.key}">
						<c:set var="punishtypename" value="${row.value}" />
					</c:if>
				</c:forEach>
				<ec:column property="punishtypeid" title="${tpunishtypeid}"
					style="text-align:center" sortable="false">
					<a href='#'
						onclick="parent.frames['edit'].location='pcfreeumpiretype!edit.do?itemId=${suppower.itemId}&version=${suppower.version}&punishtypeid=${pcfreeumpiretype.punishtypeid}&degreeno=${pcfreeumpiretype.degreeno}&isPcpunishStandard=1&isEdit=1'">

						<c:out value="${punishtypename}" />
					</a>
				</ec:column>

				<c:set var="tlowlimit">处罚下限</c:set>
				<ec:column property="lowlimit" title="${tlowlimit}"
					style="text-align:center" sortable="false" />
				<c:set var="tlowlimitUnit">处罚下限单位</c:set>
				<ec:column property="lowlimitUnit" title="${tlowlimitUnit}"
					style="text-align:center" sortable="false" />

				<c:set var="ttoplimit">处罚上限</c:set>
				<ec:column property="toplimit" title="${ttoplimit}"
					style="text-align:center" sortable="false" />
				<c:set var="ttoplimitUnit">处罚上限单位</c:set>
				<ec:column property="toplimitUnit" title="${ttoplimitUnit}"
					style="text-align:center" sortable="false" />
				<c:set var="tremark">处罚内容 </c:set>
				<ec:column property="remark" title="${tremark}"
					style="text-align:center" sortable="false" />

				<c:set var="tpunishmax">备注</c:set>
				<ec:column property="opt" title="${tpunishmax}"
					style="text-align:center" sortable="false">
					<c:if test="${pcfreeumpiretype.punishtype==2}">按比例计算</c:if>
				</ec:column>

				<c:set var="optlabel">操作</c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href='#'
						onclick="IsInUse('${suppower.itemId}','${suppower.version}','${pcfreeumpiretype.punishtypeid}','${pcfreeumpiretype.degreeno}','${isPcpunishStandard}');">
						<c:if test="${pcfreeumpiretype.isinuse==1}">禁止</c:if> <c:if
							test="${pcfreeumpiretype.isinuse==0}">启用</c:if>
					</a>
					<a
						href='powerbase/pcfreeumpiretype!delete.do?itemId=${suppower.itemId}&&version=${suppower.version}&&punishtypeid=${pcfreeumpiretype.punishtypeid}&&degreeno=${pcfreeumpiretype.degreeno}&&isPcpunishStandard=1'>
						删除</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</c:if>
	
</body>

<script>
	function IsInUse(itemId,version,punishtypeid, degreeno, isPcpunishStandard) {
		var url = "powerbase/pcfreeumpiretype!editIsInUse.do?punishtypeid=" + punishtypeid+ "&itemId=" + itemId+ "&version=" + version
				+ "&degreeno=" + degreeno + "&isPcpunishStandard="
				+ isPcpunishStandard;
		document.location.href = url;
	}

	/* function returnEdit(){
	 window.parent.frames['edit'].document.forms[0].submit();
	 } */
</script>
</html>
