<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaSurveyType.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 调查类型
			</legend>
			
			<s:form action="oaSurveyType" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td class="addTd"><s:text name="oaSurveyType.reType" />:</td>
						<td><s:textfield name="s_reType" value="%{#parameters['s_reType']}" /></td>
						<td class="addTd"><s:text name="oaSurveyType.remark" />:</td>
						<td><s:textfield name="s_remark" value="%{#parameters['s_remark']}" /> </td>
					</tr>	
					<tr >
						<td class="addTd"><s:text name="oaSurveyType.creatertime" />:</td>
						<td>
						<input type="text" class="Wdate"
						value="${s_beginCreatertime}" id="s_beginCreatertime"
						name="s_beginCreatertime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						至<input type="text" class="Wdate"
						value="${s_endCreatertime}" id="s_endCreatertime"
						name="s_endCreatertime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						</td>
					</tr>
					
					<tr class="searchButton">
						<td colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>	
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaSurveyType!list.do" items="objList" var="oaSurveyType"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
<%-- 			<ec:exportXls fileName="oaSurveyTypes.xls" ></ec:exportXls> --%>
<%-- 			<ec:exportPdf fileName="oaSurveyTypes.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

<%-- 				<c:set var="tno"><s:text name='oaSurveyType.no' /></c:set>	 --%>
<%-- 				<ec:column property="no" title="${tno}" style="text-align:center" /> --%>


				<c:set var="treType"><s:text name='oaSurveyType.reType' /></c:set>	
				<ec:column property="reType" title="${treType}" style="text-align:center" />
				
<%-- 				<c:set var="tcomName"><s:text name='oaSurveyType.comName' /></c:set>	 --%>
<%-- 				<ec:column property="comName" title="${oaSurveyType}" style="text-align:center" /> --%>
				<c:set var="tremark"><s:text name='oaSurveyType.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" >
				${oaSurveyType.remark}
				</ec:column>
				<c:set var="tstate"><s:text name='oaSurveyType.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center">
				${cp:MAPVALUE("equState",oaSurveyType.state)}
				</ec:column>
				<c:set var="tcreater"><s:text name='oaSurveyType.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaSurveyType.creater)}
				</ec:column>
				<c:set var="tcreatertime"><s:text name='oaSurveyType.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value="${oaSurveyType.creatertime}"
													pattern="yyyy-MM-dd HH:mm:ss" />
				</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSurveyType!view.do?no=${oaSurveyType.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'>
					<s:text name="opt.btn.view" /></a>
					<c:if test="${loginer eq oaSurveyType.creater }">
					<a href='oa/oaSurveyType!edit.do?no=${oaSurveyType.no}'><s:text name="opt.btn.edit" /></a>
					<c:if test="${'T' eq oaSurveyType.state }" >
					<a href='oa/oaSurveyType!delete.do?no=${oaSurveyType.no}' onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
					</c:if>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	function checkFrom(){
		var begD = $("#s_beginCreatertime").val();
		var endD = $("#s_endCreatertime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
