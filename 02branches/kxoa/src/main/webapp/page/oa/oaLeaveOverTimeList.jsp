<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>
			<s:text name="militarycases.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>请假申请查看</legend>
			<s:form action="oaLeaveOverTime" namespace="/oa" style="margin-top:0;margin-bottom:5" data-validate="true">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
					    <td class="addTd">查看人:</td>
						<td  align="left" >
						<input type="text" class="autocomplete"   name="s_useCod"  data-token-limit='1' 
							data-pre-populate='${populate}' 
							data-url="${pageContext.request.contextPath}/oa/oaLeaveOverTime!selectUser.do" />
						 </td>
						<td class="addTd">查看时间:	</td>
						<td  align="left">	
						<input type="text" class="Wdate" id="s_begTime"
						value="${param['s_begTime'] }" 
						name="s_begTime"  style="height:25px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="选择日期">			
					</tr>
					<tr  class="searchButton">
						<td colspan="4">
							<s:submit method="editDraft" cssClass="btn" value='导出excel1' />&nbsp;
							<s:submit method="editDraft" cssClass="btn" value='导出excel2' />&nbsp;
							<s:submit method="editDraft" cssClass="btn" value='导出excel3' />&nbsp;
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<br/>
		<div style="color:red;font-size: 20px;">2014年1月，xxx部门所有人员请假统计</div>
		<br/>
		<ec:table action="oa/oaLeaveOverTime!list.do" items="objList" var="voaLeaveOverTime"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
 				<ec:column property="usercode"  title="姓名" style="text-align:center" sortable="false" >
 				<%-- ${cp:MAPVALUE('usercode',oaLeaveOverTime.usercode )} --%>
 				  ${cp:MAPVALUE("usercode",oaLeaveOverTime.usercode ) }
 				</ec:column>
                <ec:column property="num0" title="年休假"  style="text-align:center" sortable="false" >
				</ec:column>
				<ec:column property="num1" title="病假"  style="text-align:center" >
                </ec:column>
                <ec:column property="num2" title="事假"  style="text-align:center" >
                </ec:column>
                 <ec:column property="num3" title="丧假 "  style="text-align:center" >
                </ec:column>
                <ec:column property="num4" title="探亲假"  style="text-align:center" >
                </ec:column>
                <ec:column property="num5" title="产假"  style="text-align:center" >
                </ec:column>
                <ec:column property="num6" title="护理假"  style="text-align:center" >
                </ec:column>  
                <ec:column property="num7" title="婚假 " style="text-align:center" >
                </ec:column>
                <ec:column property="num8" title="旷工"  style="text-align:center" >
                </ec:column>
               <ec:column property="num9" title="其他"  style="text-align:center" >
                </ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
							<%-- <a href="oaLeaveOverTime!view.do?usercode=${voaLeaveOverTime.usercode }">查看</a>		
							<a href="oaLeaveOverTime!add.do?usercode=${voaLeaveOverTime.usercode }">新增</a> --%>
							<a href="oaLeaveOverTime!edit.do?usercode=${voaLeaveOverTime.usercode }">修改</a>
							<%-- <a href="oaLeaveOverTime!delete.do?usercode=${voaLeaveOverTime.usercode }">删除</a> --%>
				</ec:column> 
			</ec:row> 
		</ec:table>
			<%@ include file="/page/common/scripts.jsp"%> 
	</body>
	<script type="text/javascript"></script>
</html>
 