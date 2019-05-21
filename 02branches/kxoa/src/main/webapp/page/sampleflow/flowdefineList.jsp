<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<title>系统中工作流程列表</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
</head>

<body>
	<s:include value="/page/common/messages.jsp"/>
	<fieldset  class="search">
		<legend>流程定义</legend>
		<s:form action="sampleFlowDefine" namespace="/sampleflow" theme="simple"  style="margin-top:0;margin-bottom:4">
			<table cellpadding="0" cellspacing="0">
				<tr class="searchButton">
						<td width="250">流程名称:
							<s:textfield name="s_wfName" style="width:180px" value="%{#parameters.s_wfName}" />	
						</td>
						<!-- 
						<td>
							流程类别:&nbsp;&nbsp;
							<s:textfield name="wfclass"></s:textfield>							
						</td>
						 -->
					<td>
						<s:submit method="list" cssClass="btn" value="查询" />
						<s:submit method="preDefFlow" cssClass="btn" value="创 建" />&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<ec:table action="sampleFlowDefine!list.do" items="objList" var="wfFlow"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		rowsDisplayed="15"
		 >
		<ec:row>
			<ec:column property="flowCode" title="代码" style="text-align:center" width="4%"/>
			
			<ec:column property="flowName" title="流程名称" style="text-align:center"/>
			<ec:column property="version" title="版本" style="text-align:center" width="4%"/>
			
			<ec:column property="flowDesc" title="描述" style="text-align:center"/>
			
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center" width="30%">
				<c:if test="${not empty wfFlow.flowXmlDesc}">
					<c:if test="${wfFlow.flowState eq 'A'}">
						<a href='sampleFlowDefine!publishFlow.do?flowCode=${wfFlow.flowCode}' onclick='return confirm("是否发布新版本");'>发 布</a>&nbsp;
					</c:if>
					<c:if test="${wfFlow.flowState eq 'E'}">
						已发布
					</c:if>
				</c:if>
				<c:if test="${empty wfFlow.flowXmlDesc}">
				不可发布
				</c:if>
				<a href="javascript:editFlow('${wfFlow.flowCode}');" >编辑流程草稿</a>
				<a href='sampleFlowDefine!preEditFlow.do?flowCode=${wfFlow.flowCode}&version=0'>编辑</a>
				<a href='sampleFlowDefine!getMyAllVersions.do?flowCode=${wfFlow.flowCode}'>全部版本</a>
				<a href='sampleFlowDefine!copyFlow.do?flowCode=${wfFlow.flowCode}&version=${wfFlow.version}'>复制</a>
				<%-- <a href='sampleFlowDefine!viewflowxml.do?flowCode=${wfFlow.flowCode}&version=${wfFlow.version}&flag=ie'>查看</a> --%>
				<a href="javascript:viewFlow('${wfFlow.flowCode}','${wfFlow.version}');" >查看</a>
			</ec:column>
		</ec:row>
	</ec:table>
	<script type="text/javascript">
	function editFlow(flowcode){
		if( $.browser.msie && $.browser.version<9 ){
			location.href = 'sampleFlowDefine!getworkflowxml.do?flowCode='+flowcode+'&version=0';
		}else{		
		location.href = 'sampleFlowDefine!getflowxml.do?flowCode='+flowcode+'&version=0';
	    }
	}
	
	function viewFlow(flowcode,version){
		if( $.browser.msie && $.browser.version<9 ){
			location.href = 'sampleFlowDefine!viewflowxml.do?flowCode='+flowcode+'&version='+version+'&flag=ie';
		}else{		
		    location.href = 'sampleFlowDefine!viewflowxml.do?flowCode='+flowcode+'&version='+version+'&flag=notie';
	    }
	}
</script>
</body>
</html>
