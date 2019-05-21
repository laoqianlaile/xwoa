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
	<fieldset class="search" >
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="powerOptInfo" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">材料名称:</td>
					<td>
					<s:textfield name="s_groupDesc"  /></td>
				</tr>		
				

				<tr>
					<td align="center" colspan="2">
					<s:submit method="listSelectSuppowerstuffgroup" key="opt.btn.query" cssClass="btn" /> 
					<input type="button" class="btn" value="关闭" onclick="window.close();">
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/powerOptInfo!listSelectSuppowerstuffgroup.do" items="list" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>
		    <ec:column property="sa.value" title="材料组编码" style="text-align:center" >
		      ${sa.value }
		    </ec:column>
		    <ec:column property="sa.label" title="材料组名称" style="text-align:center" >
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
	 	parentDocument.getElementById('group_id').value = no; 
	 	parentDocument.getElementById('grouplabel').value = value; 
		window.close();

	}
}
</script>
</html>
