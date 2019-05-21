<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaUnitsLeader.list.title" />
		</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaUnitsLeader" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaUnitsLeader.leadercode" />:</td>
						<td><s:textfield name="s_leadercode" /> </td>				
						<td class="addTd"><s:text name="oaUnitsLeader.unitcodes" />:</td>
						<td><s:textfield name="s_unitNames" /> </td>
					</tr>	

					<%-- <tr >
						<td><s:text name="oaUnitsLeader.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	 --%>

					<tr >
						<td class="addTd"><s:text name="oaUnitsLeader.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>					
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>						
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/oaUnitsLeader!list.do" items="objList" var="oaUnitsLeader"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tleadercode"><s:text name='oaUnitsLeader.leadercode' /></c:set>	
				<ec:column property="leadercode" title="${tleadercode}" style="text-align:center" >
				${cp:MAPVALUE("usercode",oaUnitsLeader.leadercode)}
				</ec:column>


				
				<c:set var="tunitNames"><s:text name='oaUnitsLeader.unitNames' /></c:set>	
				<ec:column property="unitNames" title="${tunitNames}" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(oaUnitsLeader.unitNames) > 30}">
						<c:out value="${fn:substring(oaUnitsLeader.unitNames, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaUnitsLeader.unitNames}" />
					</c:otherwise>
				</c:choose>
				</ec:column>

				<c:set var="tcreatetime"><s:text name='oaUnitsLeader.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
				
				<c:set var="tisuse"><s:text name='oaUnitsLeader.isuse' /></c:set>	
				<ec:column property="isuse" title="${tisuse}" style="text-align:center">
				${cp:MAPVALUE("TrueOrFalse",oaUnitsLeader.isuse)}
				</ec:column>
		
				<c:set var="tremark"><s:text name='oaUnitsLeader.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" >
				<c:choose>
					<c:when test="${fn:length(oaUnitsLeader.remark) > 10}">
						<c:out value="${fn:substring(oaUnitsLeader.remark, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaUnitsLeader.remark}" />
					</c:otherwise>
				</c:choose>
				</ec:column>
				
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/oaUnitsLeader!view.do?leadercode=${oaUnitsLeader.leadercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/oaUnitsLeader!edit.do?leadercode=${oaUnitsLeader.leadercode}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/oaUnitsLeader!delete.do?leadercode=${oaUnitsLeader.leadercode}' 
							onclick='return confirm("${deletecofirm}oaUnitsLeader?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>