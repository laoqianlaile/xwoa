<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaDriverBook.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset   class="search">
			<legend>
					车辆使用记录
			</legend>
			
			<s:form action="oaDriverBook" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaDriverBook.djid" />:</td>
						<td><s:textfield name="s_djid" value="%{#parameters['s_djid']}" /> </td>
						
					    <td class="addTd">车辆号:</td>
						<td><s:textfield name="s_cardjid"  value="%{#parameters['s_cardjid']}"/> </td>
					</tr>	


					<tr >
						<td class="addTd"><s:text name="oaDriverBook.carno" />:</td>
						<td><s:textfield name="s_carno"   value="%{#parameters['s_carno']}"/> </td>
			
						<td class="addTd"><s:text name="oaDriverBook.driver" />:</td>
						<td><s:textfield name="s_driver" value="%{#parameters['s_driver']}"/> </td>
					</tr>	

					<tr class="searchButton">
					
						<td colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaDriverBook!list.do" items="objList" var="oaDriverBook"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
		
			<ec:row>

				<c:set var="tdjid"><s:text name='oaDriverBook.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcardjid">车辆号</c:set>	
				<ec:column property="cardjid" title="${tcardjid}" style="text-align:center" />
				
				<c:set var="tcarno"><s:text name='oaDriverBook.carno' /></c:set>	
				<ec:column property="carno" title="${tcarno}" style="text-align:center" />

				<c:set var="tdriver"><s:text name='oaDriverBook.driver' /></c:set>	
				<ec:column property="driver" title="${tdriver}" style="text-align:center" />

				<c:set var="tdepno"><s:text name='oaDriverBook.depno' /></c:set>	
				<ec:column property="depno" title="${tdepno}" style="text-align:center" />

				<c:set var="tcaruser"><s:text name='oaDriverBook.caruser' /></c:set>	
				<ec:column property="caruser" title="${tcaruser}" style="text-align:center" />

				<c:set var="ttotalCost"><s:text name='oaDriverBook.totalCost' /></c:set>	
				<ec:column property="totalCost" title="${ttotalCost}" style="text-align:center" />

				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaDriverBook!view.do?djid=${oaDriverBook.djid}&show_type=F'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaDriverBook!edit.do?djid=${oaDriverBook.djid}&show_type=T&edittype=F'><s:text name="opt.btn.edit" /></a>
				
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
