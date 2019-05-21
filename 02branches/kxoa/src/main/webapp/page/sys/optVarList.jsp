<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>流程业务变量表</title>
   <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script>
        <script>
          var module;//发布出去供全局对象使用
          $(function(){
             module= $.require("menuDependies");
             module.init();
         });
        </script>
</head>

<body>

<fieldset>
  <legend style="border-bottom-width:1px">
           流程变量列表  <a style="float:right;margin-right:10" href="javascript:void(0);" onclick = "module.popEditorWin('流程变量新增','${pageContext.request.contextPath}/sys/optVar!built.do?s_OPTID=${param.s_OPTID}')">新增</a>
  </legend> 
  <div style="max-height:250px;overflow-y:auto">
	<ec:table action="optVar!list.do" items="objList" var="object"
		imagePath="${sessionScope.STYLE_PATH}/images/table/*.gif"
		rowsDisplayed="15" filterRowsCallback="limit"
		retrieveRowsCallback="limit" sortRowsCallback="limit">
		<ec:row>
			<ec:column property="varname" title="变量名称" style="text-align:center"></ec:column>
			<ec:column property="vardesc" title="变量描述" style="text-align:center"></ec:column>
			<ec:column property="vartype" title="变量类型" style="text-align:center"></ec:column>
			<ec:column property="defaultvalue" title="变量初值"
				style="text-align:center"></ec:column>
			<ec:column property="isvalid" title="状态" style="text-align:center"></ec:column>
			<ec:column property="varOpt" title="操作" sortable="false"
				style="text-align:center">
				<a href='javascript:void(0)' onclick = "module.popEditorWin('流程变量新增','${pageContext.request.contextPath}/sys/optVar!edit.do?varname=${object.varname}&optid=${object.optid}')">编辑</a>
				<c:if test="${object.isvalid eq 'F' }">
					<a href='optVar!renew.do?varname=${object.varname}&optid=${object.optid}'>启用</a>
				</c:if>
				<c:if test="${object.isvalid eq 'T' }">
					<a href='${pageContext.request.contextPath}/sys/optVar!disable.do?varname=${object.varname}&optid=${object.optid}' onclick='return confirm("是否禁用该变量?");'>禁用</a>
				</c:if>
			</ec:column>
		</ec:row>
	</ec:table>
   </div>
</fieldset>
</body>
</html>