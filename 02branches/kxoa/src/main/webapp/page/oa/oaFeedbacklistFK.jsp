<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaFeedback.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 反馈查看
			</legend>
			
			<s:form action="oaFeedback" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaFeedback.djid" />:</td>
						<td><s:textfield name="s_djid" value="%{#parameters['s_djid'] }" /> </td>
				
						<td class="addTd"><s:text name="oaFeedback.title" />:</td>
						<td><s:textfield name="s_title" value="%{#parameters['s_title'] }" /> </td>
					</tr>	
					   <tr>
						<td class="addTd">提交日期:</td>
						<td colspan="3">
					<input type="text" class="Wdate"  id="s_begcreatertime"
                    style="height: 25px; width: 160px; border-radius: 3px; border: 1px solid #cccccc;" 
                    value="${param['s_begcreatertime'] }"name="s_begcreatertime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
								至
					<input type="text" class="Wdate"  id="s_endcreatertime"
                    style="height: 25px; width: 160px; border-radius: 3px; border: 1px solid #cccccc;" 
                    value="${param['s_endcreatertime'] }"name="s_endcreatertime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
								</td> 
				    </tr>
				    <tr  class="searchButton" >
						<td  colspan="4">
							<s:submit method="listFK" style="margin-left:60px" key="opt.btn.query" cssClass="btn"  onclick="return checkFrom();"/>
					    </td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaFeedback!listFK.do" items="objList" var="oaFeedback"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tdjid"><s:text name='oaFeedback.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />


				<c:set var="ttitle"><s:text name='oaFeedback.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />


				<c:set var="tcreatertime"><s:text name='oaFeedback.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value="${oaFeedback.creatertime}"
					pattern="yyyy-MM-dd" />
				</ec:column>

              <%--   <c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column> --%>
				
				<c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column>
				
				<%-- <c:set var="tisopen"><s:text name='oaFeedback.isopen' /></c:set>	
				<ec:column property="isopen" title="${tisopen}" style="text-align:center">
				${cp:MAPVALUE("IS_BOOLEAN",oaFeedback.isopen) }
				</ec:column> --%>
				
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaFeedback!view.do?djid=${oaFeedback.djid}'>查看</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
		<script type="text/javascript">
	function checkFrom(){
		var begD = $("#s_begcreatertime").val();
		var endD = $("#s_endcreatertime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
