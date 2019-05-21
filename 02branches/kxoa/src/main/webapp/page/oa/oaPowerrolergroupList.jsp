<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	    <%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaPowerrolergroup.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
					<c:choose>
					<c:when test="${s_groupType eq '1' }">分组定义</c:when>
					<c:when test="${s_groupType eq '2' }">公共分组定义</c:when>
					</c:choose>
			</legend>
			
			<s:form action="oaPowerrolergroup" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
                <s:hidden id="s_groupType" name="s_groupType" value="%{s_groupType}"></s:hidden>
					<tr >
						<td class="addTd"><s:text name="oaPowerrolergroup.groupName" />:</td>
						<td><s:textfield name="s_groupName"  value="%{#parameters['s_groupName']}"/> </td>
					    <td class="addTd"><s:text name="oaPowerrolergroup.remark" />:</td>
						<td><s:textfield name="s_remark"  value="%{#parameters['s_remark']}"/>
						<input type="checkbox" id="s_isBoth" name="s_isBoth"
						value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用
						 </td>
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

		<ec:table action="oa/oaPowerrolergroup!list.do" items="objList" var="oaPowerrolergroup"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

<%-- 				<c:set var="tno"><s:text name='oaPowerrolergroup.no' /></c:set>	 --%>
<%-- 				<ec:column property="no" title="${tno}" style="text-align:center" /> --%>


<%-- 				<c:set var="tgroupType"><s:text name='oaPowerrolergroup.groupType' /></c:set>	 --%>
<%-- 				<ec:column property="groupType" title="${tgroupType}" style="text-align:center" /> --%>

				<c:set var="tgroupName"><s:text name='oaPowerrolergroup.groupName' /></c:set>	
				<ec:column property="groupName" title="${tgroupName}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaPowerrolergroup.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" >
				<c:choose>
					<c:when test="${fn:length(remark) > 20}">
						<c:out value="${fn:substring(oaPowerrolergroup.remark, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaPowerrolergroup.remark}" />
					</c:otherwise>
				</c:choose>
				</ec:column>

				<c:set var="tstate"><s:text name='oaPowerrolergroup.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" >
				${USE_STATE[oaPowerrolergroup.state]} 
				</ec:column>

				

				<c:set var="tcreatertime"><s:text name='oaPowerrolergroup.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
					<fmt:formatDate value='${oaPowerrolergroup.creatertime}'
					pattern='yyyy-MM-dd' />
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!view.do?no=${oaPowerrolergroup.no}&s_groupType=${s_groupType}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!edit.do?no=${oaPowerrolergroup.no}&s_groupType=${s_groupType}'><s:text name="opt.btn.edit" /></a>
					<a href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!delete.do?no=${oaPowerrolergroup.no}&s_groupType=${s_groupType}' 
							onclick='return confirm("${deletecofirm}该分组信息?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
