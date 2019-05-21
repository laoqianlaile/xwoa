<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
			<p>参与活动人员</p>
		</legend>

		<s:form id="oaInfosummaryForm" action="oaInfosummary" namespace="/oa"
			method="post" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<input type="button" name="back" Class="btn" onclick="doback();" value="返回" />
<%-- 					<s:submit method="exportExcelAct" value="导出人员"
						cssClass="btn btnWide" /> --%>
					<input type="button"  Class="btn" onclick="exportExcelAct();" value="导出人员" />
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaInfosummary!list.do"
		items="objList" var="foaInfosummary"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="creater" title="姓名" style="text-align:center">
				${cp:MAPVALUE('usercode',foaInfosummary.creater)}
			</ec:column>
			<ec:column property="sex" title="性别" style="text-align:center" >
			${cp:MAPVALUE('sex',foaInfosummary.sex)}
			</ec:column>
			<ec:column property="unitcode" title="所属部门" style="text-align:center">
				${cp:MAPVALUE('unitcode',foaInfosummary.unitcode)}
			</ec:column>
			<ec:column property="telephone" title="联系方式"
				style="text-align:center" />
			<ec:column property="creatertime" title="提交时间"
				style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	$(function() {
		setTimeout(function() {
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		}, 200);
	});

	function doback() {
		var url = "oaInformation!publishlist.do?";
		document.location.href = url;
	}
	
	//导出按钮，注意事项，数据都是，先查询，在导出，如果不加条件则默认是导出这张表的数据
    function exportExcelAct(){
	    var srEXForm  = document.getElementById("oaInfosummaryForm");
	    srEXForm.action= 'oaInfosummary!exportExcelAct.do?infoNo=${foaInfosummary.no}';
	    srEXForm.submit();
	}

</script>
</html>
