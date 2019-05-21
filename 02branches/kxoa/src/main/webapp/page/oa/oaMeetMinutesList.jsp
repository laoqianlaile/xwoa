<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		
			
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset   class="search" >
			<legend>
						会议纪要
			</legend>
			
			<s:form action="oaMeetMinutes" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				  <s:hidden name="show_type" value="%{show_type}"></s:hidden>

					<tr>
					    <td class="addTd">会议主题:
						<td><s:textfield name="s_title" value="%{#parameters['s_title'] }" /> </td>
						<td class="addTd">登记时间:</td>
						<td><input type="text" class="Wdate" name="s_begTime"  id="s_begTime"
							value='${param['s_begTime'] }'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">		
					    	至
							<input type="text" class="Wdate" name="s_endTime"  id="s_endTime"
							value='${param['s_endTime'] }'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">  	
						</td>
					</tr>
					<tr class="searchButton" >
						<td colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
			
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
							
						</td>
						
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaMeetMinutes!list.do" items="vobjList" var="oaMeetMinutes"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
	         
				<ec:column property="djid" title="会议室申请ID" style="text-align:center" >
				   ${ oaMeetMinutes.djid }  
                </ec:column>
			   <ec:column property="meetingNo" title="会议室" style="text-align:center" >
			           ${ oaMeetMinutes.oaMeetinfo.meetingName }  
			 
				</ec:column>
			
	             <ec:column property="title" title="会议主题" style="text-align:center" >
			         ${ oaMeetMinutes.title } 
					</ec:column>
		
			<ec:column property="docFileName" title="正文文件名" style="text-align:center" >
				            ${ oaMeetMinutes.docFileName }  
                </ec:column>
               	<ec:column property="motifyTime" title="更新时间" style="text-align:center" >
               		<fmt:formatDate value='${oaMeetMinutes.motifyTime}' pattern='yyyy-MM-dd HH:mm' />
               		</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaMeetMinutes!viewAll.do?s_djid=${oaMeetMinutes.djid}'><s:text name="opt.btn.view" /></a>
				
					<a href='oa/oaMeetMinutes!edit.do?djid=${oaMeetMinutes.djid}&version=${oaMeetMinutes.version}'><s:text name="opt.btn.edit" /></a>

				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	function checkFrom(){
		var begD = $("#s_begTime").val();
		var endD = $("#s_endTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
