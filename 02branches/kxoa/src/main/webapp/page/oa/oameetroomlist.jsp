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

		<s:form action="oaMeetroomApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">会议室申请ID:</td>
					<td>
					<s:textfield name="s_djId" /></td>
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

	<ec:table action="oa/oaMeetroomApply!selectmeet.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="djId${sa.djId}" name="djId${sa.djId}" value="${sa.djId}">
	    <input type="hidden" id="meetingNo${sa.djId}" name="meetingNo${oa.djId}" value="${sa.meetingNo }">
		<input type="hidden" id="meeting${sa.djId}" name="meetingNo${oa.djId}" value="${sa.oaMeetinfo.meetingName }">
        <input type="hidden" id="doBegTime${sa.djId}" name="doBegTime${sa.djId}" value="<fmt:formatDate value='${sa.doBegTime}' pattern='yyyy-MM-dd HH:mm:ss' />">
		<input type="hidden" id="doEndTime${sa.djId}" name="doEndTime${sa.djId}" value="<fmt:formatDate value='${sa.doEndTime}' pattern='yyyy-MM-dd HH:mm:ss' />">
	
		<ec:row>
			<ec:column property="djId" title="会议室申请ID"	style="text-align:center" sortable="false"/>
                    
			<ec:column property="title" title="会议室"	style="text-align:center" sortable="false">	
		                    	  ${sa.oaMeetinfo.meetingName }
			</ec:column>
			
			<ec:column property="doBegTime" title="会议室安排开始时间"	style="text-align:center" sortable="false">	
				     	 	<fmt:formatDate value='${sa.doBegTime}' pattern='yyyy-MM-dd HH:mm' />
			</ec:column>
				<ec:column property="doEndTime" title="会议室安排结束时间"	style="text-align:center" sortable="false">	
				     	 	<fmt:formatDate value='${sa.doEndTime}' pattern='yyyy-MM-dd HH:mm' />
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
		parentDocument.getElementById('text').value = document.getElementById('meeting' + no).value;
		parentDocument.getElementById('meetingNo').value = document.getElementById('meetingNo' + no).value;
		parentDocument.getElementById('planBegTime').value = document.getElementById('doBegTime' + no).value;
		parentDocument.getElementById('planEndTime').value = document.getElementById('doEndTime' + no).value;
		parentDocument.getElementById('supDjid').value = document.getElementById('djId' +no).value;
		//日期控件时间限制
// 		parentDocument.getElementById('planBegTime').setAttribute("onclick","WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '"+document.getElementById('doBegTime' + no).value+"',maxDate: '"+document.getElementById('doEndTime' + no).value+"'})");
// 		parentDocument.getElementById('planEndTime').setAttribute("onclick","WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '"+document.getElementById('doBegTime' + no).value+"',maxDate: '"+document.getElementById('doEndTime' + no).value+"'})");
		parentDocument.getElementById('temp_hys_doBegTime').value = document.getElementById('doBegTime' + no).value;
		parentDocument.getElementById('temp_hys_doEndTime').value = document.getElementById('doEndTime' +no).value;
		
		window.close();
	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
