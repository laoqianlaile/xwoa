<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="pcdef" namespace="/punish"	style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			<input type="hidden" name="depid" value="${object.depid }">
				<tr>
					<td class="addTd">项目代码:</td>
					<td>
					<s:textfield name="s_punishclasscode" style="width:180px" value="%{#parameters['s_punishclasscode']}" /></td>
				</tr>
				<tr>
					<td class="addTd">类别名称:</td>
					<td>
					<s:textfield name="s_punishclassname" style="width:180px" value="%{#parameters['s_punishclassname']}" /></td>
				</tr>

				<tr>
					<td align="center" colspan="2">
					<s:submit method="listPcdef" key="opt.btn.query" cssClass="btn" /> 
					<input type="button" class="btn" value="关闭" onclick="window.close();">
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="pcdef!listPcdef.do" items="pcdefList" var="pcdef"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="punishclasscode${pcdef.punishclassid}" name="punishclasscode${pcdef.punishclassid}" value="${pcdef.punishclasscode}">
		<input type="hidden" id="punishclassname${pcdef.punishclassid}" name="punishclassname${pcdef.punishclassid}" value="${pcdef.punishclassname}">
		<input type="hidden" id="punishobject${pcdef.punishclassid}" name="punishobject${pcdef.punishclassid}" value="${pcdef.punishobject}">
		<ec:row>
			<c:set var="tpunishclasscode">
				权力编码
			</c:set>
			<ec:column property="punishclasscode" title="${tpunishclasscode}"	style="text-align:center" sortable="false"/>
			
			<c:set var="tpunishclassname">
				类别名称
			</c:set>
			<ec:column property="punishclassname" title="${tpunishclassname}"	style="text-align:center" sortable="false">	
				<c:choose>
					<c:when test="${fn:length(pcdef.punishclassname) > 30}">
						<c:out value="${fn:substring(pcdef.punishclassname, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${pcdef.punishclassname}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
		
			<c:set var="tpunishobject">
				处罚对象
			</c:set>
			<ec:column property="punishobject" title="${tpunishobject}"	style="text-align:center" sortable="false">
			 <c:if test="${pcdef.punishobject==0}">个人</c:if>
		     <c:if test="${pcdef.punishobject==1}">机构</c:if>
		     <c:if test="${pcdef.punishobject==2}">个人、机构</c:if>
			</ec:column>
			
			<c:set var="optlabel">
				操作
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<input type="radio" name="punishclassid"
					onclick="setParentVal('${pcdef.punishclassid}')">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">	
	var parentDocument = window.opener.document;//获取父页面对象

	//设置返回值
	function setParentVal(punishclassid) {
		if (window.confirm("确认选择此处罚项目吗？选择后窗口将自动关闭。")) {
				parentDocument.getElementById('punishclasscode').value = document.getElementById('punishclasscode' + punishclassid).value;
				parentDocument.getElementById('punishclassname').value = document.getElementById('punishclassname' + punishclassid).value;
				parentDocument.getElementById('punishobject').value = document.getElementById('punishobject' + punishclassid).value;
				

			
				
			window.close();
		}
		/*****************业务数据页面跳转end*********/
	}
</script>
</html>
