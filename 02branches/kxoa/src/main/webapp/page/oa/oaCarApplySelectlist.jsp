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
	<fieldset class="search">
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="oaCarApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					<td class="addTd">车辆申请ID:</td>
					<td>
					<s:textfield name="s_djId" style="width:180px" /></td>
				</tr>		
				<tr>
					<td class="addTd">车辆号:</td>
					<td>
					<s:textfield name="s_cardjid" style="width:180px"  /></td>
					<td class="addTd">申请是由:</td>
					<td>
					<s:textfield name="s_reqRemark" style="width:180px"  /></td>
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

	<ec:table action="oa/oaCarApply!selectlist.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="djId" name="djId" value="${sa.djId}">
		<input type="hidden" id="carno${sa.djId}" name="carno${sa.djId}" value="${sa.carno}">
		<input type="hidden" id="cardjid${sa.djId}" name="cardjid${oa.djId}" value="${sa.cardjid}">
        <input type="hidden" id="driver${sa.djId}" name="driver${sa.djId}" value="${sa.driver}">
		<input type="hidden" id="brand${sa.djId}" name="brand${sa.djId}" value="${sa.brand}">
		
		<ec:row>
			<ec:column property="djId" title="车辆申请ID"	style="text-align:center" sortable="false"/>
                    
			<ec:column property="cardjid" title="车辆号"	style="text-align:center" sortable="false">	
		                    	  ${sa.cardjid}
			</ec:column>
				<ec:column property="depno" title="用车部门"	style="text-align:center" sortable="false">	
		                    	      ${cp:MAPVALUE("unitcode",sa.depno)}  
			</ec:column>
				<ec:column property="creater" title="用车人"	style="text-align:center" sortable="false">	
		                    	   ${cp:MAPVALUE("usercode",sa.creater)}  
			</ec:column>
			
				<ec:column property="createtime" title="申请时间"	style="text-align:center" sortable="false">	
		                    <fmt:formatDate value='${sa.createtime}' pattern='yyyy-MM-dd ' />	
			</ec:column>
			
			<ec:column property="reqRemark" title="用车事由"	style="text-align:center" sortable="false">	
				     	    ${sa.reqRemark}
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
	    parentDocument.getElementById('cardjid').value = document.getElementById('cardjid' + no).value;
		parentDocument.getElementById('driver').value = document.getElementById('driver' + no).value;
	    parentDocument.getElementById('carno').value = document.getElementById('carno' + no).value;
		parentDocument.getElementById('brand').value = document.getElementById('brand' + no).value;
	   // parentDocument.getElementById('meetingPersions').value = document.getElementById('meetingPersions' + no).value;
	//	parentDocument.getElementById('doDepno').value = document.getElementById('doDepno' + no).value;
	   // parentDocument.getElementById('doCreater').value = document.getElementById('doCreater' + no).value;
	
	
		//parentDocument.getElementById('bjType').value = "${flag}";
		window.close();
	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
