<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>

	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>处罚种类信息</b>
		</legend>
	
		<input type="button" value="添加" Class="btn"
			onclick="doAddType('${object.itemId}','${object.version}');" />
		<ec:table action="powerbase/pcpunishStandard!listPcType.do"
			items="pcpunishStandardsList" var="pcpunishStandard"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false"
			showStatusBar="false" showTitle="false">
			<ec:row>
				<c:set var="tpunishtypeid">处罚种类</c:set>
				<c:forEach var="row" items="${cp:DICTIONARY('punishType')}">
					<c:if test="${pcpunishStandard.punishtypeid eq row.key}">
						<c:set var="punishtypename" value="${row.value}" />
					</c:if>
				</c:forEach>
				<ec:column property="punishtypeid" title="${tpunishtypeid}"
					style="text-align:center" sortable="false">
					<a href='#'
						onclick="editPcType('${pcpunishStandard.itemId}','${pcpunishStandard.version}','${pcpunishStandard.punishtypeid}');">
						<c:out value="${punishtypename}" />
					</a>
				</ec:column>
				<c:set var="tisinuse">是否使用</c:set>
				<ec:column property="opt" title="${tisinuse}"
					style="text-align:center" sortable="false">
					<c:if test="${pcpunishStandard.isinuse==1}">正在使用</c:if>
					<c:if test="${pcpunishStandard.isinuse==0}">停止使用</c:if>
				</ec:column>
				<c:set var="tlowlimit">处罚下限</c:set>
				<ec:column property="lowlimit" title="${tlowlimit}"
					style="text-align:center" sortable="false">
				</ec:column>
				<c:set var="tlowlimitUnit">处罚下限单位</c:set>
				<ec:column property="lowlimitUnit" title="${tlowlimitUnit}"
					style="text-align:center" sortable="false" />
				<c:set var="ttoplimit">处罚上限</c:set>
				<ec:column property="toplimit" title="${ttoplimit}"
					style="text-align:center" sortable="false">
				</ec:column>
				<c:set var="ttoplimitUnit">处罚上限单位</c:set>
				<ec:column property="toplimitUnit" title="${ttoplimitUnit}"
					style="text-align:center" sortable="false" />
				<c:set var="tremark">备注</c:set>
				<ec:column property="opt" title="${tremark}"
					style="text-align:center" sortable="false">
					<c:if test="${pcpunishStandard.punishtype==2}">按比例计算</c:if>
				</ec:column>

				<c:set var="optlabel">操作</c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href='#'
						onclick="IsInUse('${pcpunishStandard.itemId}','${pcpunishStandard.version}','${pcpunishStandard.punishtypeid}','${pcpunishStandard.isinuse}');">
						<c:if test="${pcpunishStandard.isinuse==1}">禁止</c:if> <c:if
							test="${pcpunishStandard.isinuse==0}">启用</c:if>
					</a>
				
				</ec:column>
			</ec:row>
		</ec:table>
	</fieldset>


</body>
<script type="text/javascript">
	function doAddType(itemId,version) {
		var url = "powerbase/pcpunishStandard!edit.do?itemId="+itemId+"&version="+version ;
		window.parent.location.href = url;
	}
	function IsInUse(itemId,version, punishtypeid, isinuse) {
		var id;
		var url;
		if (isinuse == "1") {
			id = "禁用";
		} else {
			id = "启用";
		}
		if (window.confirm("是否要将相应的自由裁量" + id)) {
			url = "powerbase/pcpunishStandard!editFreeumpireTypeIsInUse.do?itemId="+itemId+"&version="+version + "&punishtypeid=" + punishtypeid;
			document.location.href = url;
		} else { 
			url = "powerbase/pcpunishStandard!editIsInUse.do?itemId="+itemId+"&version="+version + "&punishtypeid=" + punishtypeid;
			document.location.href = url;
		 } 
	}
	function listfreeumpiretype(itemId,version, punishtypeid) {
		var url = "powerbase/pcfreeumpiretype!list.do?itemId="+itemId+"&version="+version + "&punishtypeid=" + punishtypeid;
		window.parent.location.href = url;
	}
	function editPcType(itemId,version, punishtypeid) {
		var url = "powerbase/pcpunishStandard!edit.do?itemId="+itemId+"&version="+version + "&punishtypeid=" + punishtypeid;
		parent.location.href = url;
	}
</script>
</html>

