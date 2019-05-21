<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="vuserTaskListOA" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">业务编码:</td>
					<td>
					<s:textfield name="s_djId" style="width:180px" /></td>
				</tr>		
				
				<tr>
					<td align="center" colspan="2">
					<s:submit method="selectlist" key="opt.btn.query" cssClass="btn" /> 
					<input type="button" class="btn" value="关闭" onclick="window.close();">
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="dispatchdoc/vuserTaskListOA!selectlist.do" items="selectList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="djId" name="djId" value="${sa.djId}">
		<input type="hidden" id="userCode${sa.djId}" name="userCode${sa.djId}" value="${sa.userCode}">
		<input type="hidden" id="flowOptName${sa.djId}" name="flowOptName${oa.djId}" value="${sa.flowOptName}">
        <input type="hidden" id="nodeCode${sa.djId}" name="nodeCode${sa.djId}" value="${sa.nodeCode}">
		
		<ec:row>
			<ec:column property="djId" title="业务编码"	style="text-align:center" sortable="false"/>
                    
			<ec:column property="flowOptName" title="业务名称"	style="text-align:center" sortable="false">	
		                    	  ${sa.flowOptName}
			</ec:column>
			
			<ec:column property="nodeName" title="步骤名称"	style="text-align:center" sortable="false">	
				     	    ${sa.nodeName}
			</ec:column>
		<%-- 		<ec:column property="timeLimit" title="时限"	style="text-align:center" sortable="false">	
				     	    ${sa.timeLimit}
			</ec:column> --%>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${sa.djId}')">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">	
//获取父页面对象
var parentDocument = window.opener.document;
//设置返回值
function setParentVal(no) {

	if (window.confirm("确认选择吗")) {		
	
		parentDocument.getElementById('supDjid').value = no;
	    parentDocument.getElementById('nodecode').value = document.getElementById('nodeCode' + no).value;
		parentDocument.getElementById('title').value = document.getElementById('flowOptName' + no).value;
		parentDocument.getElementById('superviseUsers').value = document.getElementById('userCode' + no).value;
		//parentDocument.getElementById('bjType').value = "${flag}";
		window.close();
	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
