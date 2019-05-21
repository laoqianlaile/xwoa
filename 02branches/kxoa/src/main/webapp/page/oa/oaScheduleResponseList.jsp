<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaScheduleResponse.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaScheduleResponse" namespace="/oa" style="margin-top:0;margin-bottom:5">
			
			   <input type="hidden" id="s_schno" name="s_schno" value="${s_schno}" />
			
			
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaScheduleResponse.resType" />:</td>
						
						<td>
						<select id="s_resType" name="s_resType" style="width:120px;">
				         	<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oaResType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_resType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					</tr>	

			
					<tr>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
<!-- 						<td> -->
<%-- 							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/> --%>
<!-- 						</td> -->
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaScheduleResponse!list.do" items="objList" var="oaScheduleResponse"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

			
				<c:set var="tresType"><s:text name='oaScheduleResponse.resType' /></c:set>	
				<ec:column property="resType" title="${tresType}" style="text-align:center" >
				${cp:MAPVALUE('oaResType',oaScheduleResponse.resType)}
				</ec:column>

				<c:set var="tusercode"><s:text name='oaScheduleResponse.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaScheduleResponse.usercode)}
				</ec:column>

				<c:set var="tstopTime"><s:text name='oaScheduleResponse.stopTime' /></c:set>	
				<ec:column property="stopTime" title="${tstopTime}" style="text-align:center" >
				<s:date name="%{oaScheduleResponse.stopTime}" format="yyyy-MM-dd" />
				</ec:column>

				<c:set var="tremark"><s:text name='oaScheduleResponse.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tcreatedate"><s:text name='oaScheduleResponse.createdate' /></c:set>	
				<ec:column property="createdate" title="${tcreatedate}" style="text-align:center" >
				<s:date name="%{oaScheduleResponse.createdate}" format="yyyy-MM-dd" />
<%--                 <s:text name="oaScheduleResponse.createdate" /> --%>
                </ec:column>		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaScheduleResponse!view.do?no=${oaScheduleResponse.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
				
				 <c:if test="${F eq viewtype}">
				<a href='oa/oaScheduleResponse!edit.do?no=${oaScheduleResponse.no}'><s:text name="opt.btn.edit" /></a>
				</c:if>	
					<a href='oa/oaScheduleResponse!delete.do?no=${oaScheduleResponse.no}' >删除
				</a>
				
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
