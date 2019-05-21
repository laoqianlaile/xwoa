<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<ec:table action="powerbase/powerOptInfo!listSelectTempfile.do" items="tmlist" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		    <ec:column property="sa.value" title="文书编码" style="text-align:center" >
		      ${sa.value }
		    </ec:column>
		    <ec:column property="sa.label" title="文书名称" style="text-align:center" >
		      ${sa.label }
		    </ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="wfcode"
					onclick="setParentVal('${sa.value}','${sa.label }');">
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">	
//获取父页面对象
var parentDocument = window.opener.document;
//设置返回值
function setParentVal(no,value) {

	if (window.confirm("确认选择吗")) {		
	 	parentDocument.getElementById('recordid').value = no; 
	 	parentDocument.getElementById('recordidlabel').value = value; 
		window.close();

	}
}
</script>
</html>
