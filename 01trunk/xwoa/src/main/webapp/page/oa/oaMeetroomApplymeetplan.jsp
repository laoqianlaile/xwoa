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
			会议室安排记录
		</legend>

	<input type="button" class="btn" value="关闭" onclick="window.close();">

	<ec:table action="oa/oaMeetApply!meetplan.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit" sortable="false">
		
		<ec:row>
		 
			<ec:column property="title" title="会议室"	style="text-align:center" sortable="false">	
		                    	  ${sa.oaMeetinfo.meetingName}
			</ec:column>
		
				<ec:column property="planBegTime" title="预计开始时间"	style="text-align:center" sortable="false">	
				  	<fmt:formatDate value='${sa.planBegTime}' pattern='yyyy-MM-dd HH:mm' />   	
			</ec:column>
			<ec:column property="planEndTime" title="预计结束时间"	style="text-align:center" sortable="false">	
					<fmt:formatDate value='${sa.planEndTime}' pattern='yyyy-MM-dd HH:mm' />   		   
			</ec:column>
			<ec:column property="doBegTime" title="安排开始时间"	style="text-align:center" sortable="false">	
				     	 <fmt:formatDate value='${sa.doBegTime}' pattern='yyyy-MM-dd HH:mm' />   		 
			</ec:column>
			<ec:column property="doEndTime" title="安排结束时间"	style="text-align:center" sortable="false">	
				    <fmt:formatDate value='${sa.doEndTime}' pattern='yyyy-MM-dd HH:mm' />   		   
			</ec:column>
	
			<%-- <ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${sa.djId}')">
			</ec:column> --%>
		</ec:row>
	</ec:table>
				
	</fieldset>

</body>
<script type="text/javascript">	
//获取父页面对象

	/*****************业务数据页面跳转end*********/

</script>
</html>
