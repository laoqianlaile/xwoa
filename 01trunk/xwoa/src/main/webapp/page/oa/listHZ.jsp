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
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaFeedback" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaFeedback.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>
					<tr>
						<td class="addTd"><s:text name="oaFeedback.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
					</tr>	
					   <tr>
						<td class="addTd">提交日期:</td>
						<td>
							<input type="text" class="Wdate"name="s_begcreatertime" 
							value="${params['s_begcreatertime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">							
<%-- 						<sj:datepicker name="s_begcreatertime" readonly="true" --%>
<%-- 								value="%{#parameters['s_begcreatertime']}" yearRange="2010:2030" --%>
<%-- 								displayFormat="yy-mm-dd" changeYear="true" />&nbsp;&nbsp; --%>
								至
							<input type="text" class="Wdate"name="s_endcreatertime"
							value="${params['s_endcreatertime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">								
<%-- 								&nbsp;&nbsp;<sj:datepicker name="s_endcreatertime" readonly="true" --%>
<%-- 								value="%{#parameters['s_endcreatertime']}" yearRange="2010:2030" --%>
<%-- 								displayFormat="yy-mm-dd" changeYear="true" /></td>  --%>
					</tr>
					<tr>
						<td>
							<s:submit method="listHZ" style="margin-left:60px" key="opt.btn.query" cssClass="btn"/>
					    </td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaFeedback!list.do" items="objList" var="oaFeedback"
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

                <c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column>
				
				<c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column>
				
				<c:set var="tisopen"><s:text name='oaFeedback.isopen' /></c:set>	
				<ec:column property="isopen" title="${tisopen}" style="text-align:center">
				${cp:MAPVALUE("IS_BOOLEAN",oaFeedback.isopen) }
				</ec:column>
				
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaFeedback!view.do?djid=${oaFeedback.djid}'>查看</a>
					<c:if test="${ empty oaFeedback.isopen}">
				     <a href='oa/oaFeedback!saveOpen.do?djid=${oaFeedback.djid}&isopen=0'>公开</a>
				     <a href='oa/oaFeedback!saveOpen.do?djid=${oaFeedback.djid}&isopen=0'>不公开</a>
				    </c:if>
				    <c:if test="${ oaFeedback.isopen eq '1' }">
				     <a href='oa/oaFeedback!saveOpen.do?djid=${oaFeedback.djid}&isopen=0'>不公开</a>
				    </c:if>
				    <c:if test="${ oaFeedback.isopen eq '0' }">
				     <a href='oa/oaFeedback!saveOpen.do?djid=${oaFeedback.djid}&isopen=1'>公开</a>
				    </c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
