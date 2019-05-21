<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>
		<legend style="padding:4px 8px 3px;"><b>项目申报待办</b></legend>
		<s:form action="incomeDoc" namespace="/dispatchdoc" method="post">		
			<input type="hidden" id="s_itemSource" name="s_itemSource" value="W" />
			<input type="hidden" id="s_bizstate" name="s_bizstate" value="W" />
			<table cellpadding="0" cellspacing="0" align="left" class="norap">
				<tr>
					<td class="addTd">来文号:</td>
					<td width="180"><s:textfield id="s_incomeDocNo" name="s_incomeDocNo" value="%{#parameters['s_incomeDocNo']}" style="width:180px" /> </td>
				    <td class="addTd">来文标题:</td>
					<td width="180"><s:textfield id="s_incomeDocTitle" name="s_incomeDocTitle" value="%{#parameters['s_incomeDocTitle']}" style="width:180px" /> </td>
					<td class="addTd">来文单位:</td>
					<td width="180"><s:textfield id="s_incomeDeptName" name="s_incomeDeptName" value="%{#parameters['s_incomeDeptName']}" style="width:180px"/></td>
					<td><s:submit method="listSyncIncomeDoc" key="opt.btn.query" cssClass="btn"/>&nbsp;</td>
				</tr>	
               </table>
           </s:form>
       </fieldset>
	   <ec:table action="incomeDoc!listSyncIncomeDoc.do" items="incomeDocList" var="incomeDoc"
				imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="powername"  title="权力名称" style="text-align:center" />	
			<ec:column property="incomeDeptName"  title="来文单位" style="text-align:center" />	
			<ec:column property="applyUser"  title="申报人" style="text-align:center" />	
			<ec:column property="docCreateDate" title="制发日期" style="text-align:center" format="yyyy-MM-dd hh:mm" cell="date" />	
			<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
			<ec:column property="opt" title="操作" sortable="false" style="text-align:center">
				<c:choose>
					<c:when test="${fn:startsWith(incomeDoc.applyItemType, 'gw')}">
						<a href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?djId=${incomeDoc.djId}">办理</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!edit.do?djId=${incomeDoc.djId}">办理</a>
					</c:otherwise>
				</c:choose>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
</html>