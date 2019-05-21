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

		<s:form action="oaUserinfo" namespace="/oa" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">姓名:</td>
					<td>
					<s:textfield name="s_username"  /></td>
				</tr>		
				

				<tr>
					<td align="center" colspan="2">
					<s:submit method="selectuser" key="opt.btn.query" cssClass="btn" /> 
					<input type="button" class="btn" value="关闭" onclick="window.close();">
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaUserinfo!selectuser.do" items="uselist" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="usercode${sa.usercode}" name="usercode${sa.usercode}" value="${sa.usercode}">
		<input type="hidden" id="username${sa.usercode}" name="username${sa.usercode}" value="${sa.username}">

		<ec:row>
			<ec:column property="usercode" title="用户代码"	style="text-align:center" sortable="false">	
		                    	  ${sa.usercode}
			</ec:column>
			
			<ec:column property="username" title="姓名"	style="text-align:center" sortable="false"/>
                    
		
	
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="usecode"
					onclick="setParentVal('${sa.usercode}')">
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
	 	parentDocument.getElementById('usercode').value = no; 
	 	var ev  ='${cp:MAPVALUE("usercode",no)}';
	 	parentDocument.getElementById('s_usercode').value = ev; 
		window.close();

	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
