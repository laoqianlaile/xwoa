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

		<s:form action="oaMeetApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">会议申请ID:</td>
					<td>
					<s:textfield name="s_djId"  /></td>
				</tr>		
				<tr>
		
					<td class="addTd">会议主题:</td>
					<td>
					<s:textfield name="s_title" /></td>
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

	<ec:table action="oa/oaMeetApply!selectlist.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="djId" name="djId" value="${sa.djId}">
		<input type="hidden" id="title${sa.djId}" name="title${sa.djId}" value="${sa.title}">
		<input type="hidden" id="meetingNo${sa.djId}" name="meetingNo${oa.djId}" value="${sa.meetingNo}">
        <input type="hidden" id="begTime${sa.djId}" name="begTime${sa.djId}" value="${sa.begTime}">
		<input type="hidden" id="endTime${sa.djId}" name="endTime${sa.djId}" value="${sa.endTime}">
		<input type="hidden" id="meetingPersions${sa.djId}" name="meetingPersions${sa.djId}" value="${sa.meetingPersions}">
		<input type="hidden" id="doDepno${sa.djId}" name="doDepno${sa.djId}" value="${sa.doDepno}">
		<input type="hidden" id="doCreater${sa.djId}" name="doCreater${sa.djId}" value="${sa.doCreater}">
		<ec:row>
			<ec:column property="djId" title="会议申请ID"	style="text-align:center" sortable="false"/>
                    
			<ec:column property="title" title="会议室"	style="text-align:center" sortable="false">	
		                    	  ${sa.meetingNo}
			</ec:column>
			
			<ec:column property="meetingNo" title="会议主题"	style="text-align:center" sortable="false">	
				     	    ${sa.title}
			</ec:column>
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
	
		parentDocument.getElementById('djid').value = no;
	    parentDocument.getElementById('title').value = document.getElementById('title' + no).value;
		parentDocument.getElementById('meetingNo').value = document.getElementById('meetingNo' + no).value;
	    parentDocument.getElementById('begTime').value = document.getElementById('begTime' + no).value;
		parentDocument.getElementById('endTime').value = document.getElementById('endTime' + no).value;
	   // parentDocument.getElementById('meetingPersions').value = document.getElementById('meetingPersions' + no).value;
		parentDocument.getElementById('doDepno').value = document.getElementById('doDepno' + no).value;
	    parentDocument.getElementById('doCreater').value = document.getElementById('doCreater' + no).value;
	
	
		//parentDocument.getElementById('bjType').value = "${flag}";
		window.close();
	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
