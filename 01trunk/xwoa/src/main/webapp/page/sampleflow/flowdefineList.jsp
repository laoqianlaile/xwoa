<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<title>系统中工作流程列表</title>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	width: 90px;
	border: 2px solid #bbb;
	background: #F0F0F0;
	margin-bottom: 0px;
}
</style>
</head>

<body>
	<s:include value="/page/common/messages.jsp" />
	<fieldset class="search">
		<legend>流程定义</legend>
		<s:form action="sampleFlowDefine" namespace="/sampleflow"
			theme="simple" style="margin-top:0;margin-bottom:4">
			<table cellpadding="0" cellspacing="0">
				<tr class="searchButton">
					<td width="250">流程名称: <s:textfield name="s_wfName"
							style="width:180px" value="%{#parameters.s_wfName}" />
					</td>
					<!-- 
						<td>
							流程类别:&nbsp;&nbsp;
							<s:textfield name="wfclass"></s:textfield>							
						</td>
						 -->
					<td><s:submit method="list" cssClass="btn" value="查询" /> <s:submit
							method="preDefFlow" cssClass="btn" value="创 建" />&nbsp;&nbsp;</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<ec:table action="sampleFlowDefine!list.do" items="objList"
		var="wfFlow"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		rowsDisplayed="15">
		<ec:row>
			<ec:column property="flowCode" title="代码" style="text-align:center"
				width="4%" />

			<ec:column property="flowName" title="流程名称" style="text-align:center" />
			<ec:column property="version" title="版本" style="text-align:center"
				width="4%" />

			<ec:column property="flowDesc" title="描述" style="text-align:center" />
			
			<ec:column property="flowState" title="状态" style="text-align:center" width="4%">
			<c:if test="${not empty wfFlow.flowXmlDesc}">
					<c:if test="${wfFlow.flowState eq 'A'}">
						<a
							href='sampleFlowDefine!publishFlow.do?flowCode=${wfFlow.flowCode}'
							onclick='return confirm("是否发布新版本");'><font style="color: red;font-weight: bold;">发 布</font></a>&nbsp;
					</c:if>
					<c:if test="${wfFlow.flowState eq 'E'}">
						<font style="color: green;font-weight: bold;">已发布</font>
					</c:if>
				</c:if>
				<c:if test="${empty wfFlow.flowXmlDesc}">
				<font style="color: #7030A0;font-weight: bold;">不可发布</font>
				</c:if>
			</ec:column>

			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center" width="10%">
				
				<select name='${wfFlow.flowCode}' id='${wfFlow.flowCode}'
					onchange="todo('${wfFlow.flowCode}','${wfFlow.version}');">
					<option value="">--请选择--</option>
					<option value="editFW">编辑流程图</option>
					<option value="edit">编辑信息</option>
					<option value="all">全部版本</option>
					<option value="copy">复制</option>
					<option value="view">预览</option>
					<option value="property">环节配置</option>
				</select>
			<%-- 	<a href="javascript:editFlow('${wfFlow.flowCode}');">编辑流程草稿</a>
				<a
					href='sampleFlowDefine!preEditFlow.do?flowCode=${wfFlow.flowCode}&version=0'>编辑</a>
				<a
					href='sampleFlowDefine!getMyAllVersions.do?flowCode=${wfFlow.flowCode}'>全部版本</a>
				<a
					href='sampleFlowDefine!copyFlow.do?flowCode=${wfFlow.flowCode}&version=${wfFlow.version}'>复制</a>
				<a href='sampleFlowDefine!viewflowxml.do?flowCode=${wfFlow.flowCode}&version=${wfFlow.version}&flag=ie'>查看</a>
				<a
					href="javascript:viewFlow('${wfFlow.flowCode}','${wfFlow.version}');">查看</a> --%>
			</ec:column>
		</ec:row>
	</ec:table>
	<script type="text/javascript">
		function todo(flowcode, version) {
			var type = document.getElementById(flowcode).value;
			//alert(type);
			if (type == "editFW") {//编辑流程图
				editFlow(flowcode);
			}
			if (type == "edit") {//编辑信息
				location.href = 'sampleFlowDefine!preEditFlow.do?flowCode='+flowcode+'&version=0';
			}
			if (type == "all") {//全部版本
				var url= '${pageContext.request.contextPath}/sampleflow/sampleFlowDefine!getMyAllVersions.do?flowCode='+flowcode;
				DialogUtil.open("流程"+[flowcode]+"全部版本", url, "80%", "100%");
			}
			if (type == "copy") {//复制
				location.href = 'sampleFlowDefine!copyFlow.do?flowCode='+flowcode+'&version='+version;
			}
			if (type == "view") {//预览
				viewFlow(flowcode, version);
			}
			if (type == "property") {//环节配置
				location.href ='${pageContext.request.contextPath}/powerruntime/generalModuleParam!listModeCode.do?s_flowCode='+flowcode+'&s_version='+version;
			}
		}
		function editFlow(flowcode) {
			if ($.browser.msie && $.browser.version < 9) {
				location.href = 'sampleFlowDefine!getworkflowxml.do?flowCode='
						+ flowcode + '&version=0';
			} else {
				location.href = 'sampleFlowDefine!getflowxml.do?flowCode='
						+ flowcode + '&version=0';
			}
		}

		function viewFlow(flowcode, version) {
			var url='';
			if ($.browser.msie && $.browser.version < 9) {
				url= '${pageContext.request.contextPath}/sampleflow/sampleFlowDefine!viewflowxml.do?flowCode='
						+ flowcode + '&version=' + version + '&flag=ie';
			} else {
				url= '${pageContext.request.contextPath}/sampleflow/sampleFlowDefine!viewflowxml.do?flowCode='
						+ flowcode + '&version=' + version + '&flag=notie';
			}
			DialogUtil.open("在线预览工作流", url, "80%", "100%");
		}
	</script>
</body>
</html>
