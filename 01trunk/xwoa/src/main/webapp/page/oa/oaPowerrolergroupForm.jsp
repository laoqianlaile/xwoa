<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaPowerrolergroup.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaPowerrolergroup.edit.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaPowerrolergroup" method="post" namespace="/oa"
			id="oaPowerrolergroupForm">

			<s:hidden id="no" name="no" value="%{no}"></s:hidden>
			<s:hidden id="s_groupType" name="s_groupType" value="%{s_groupType}"></s:hidden>
			<s:hidden id="groupType" name="groupType" value="%{s_groupType}"></s:hidden>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<%-- <tr>
					<td class="addTd">
						<s:text name="oaPowerrolergroup.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr> --%>


				<tr>
					<td class="addTd"><s:text name="oaPowerrolergroup.groupType" />
					</td>
					<td align="left"><c:if test="${ not empty no}">
				${cp:MAPVALUE('OAGroupType',groupType)}
		        </c:if> <c:if test="${empty no}">
						
			   ${cp:MAPVALUE('OAGroupType',s_groupType)}
	           	</c:if> </td>

					<td class="addTd"><s:text name="oaPowerrolergroup.state" /></td>
					<td align="left"><select  id="state" style="width: 200px;height:25px;"
						name="state">
							<c:if test="${ empty state}">
								<option value=""></option>
							</c:if>
							<c:forEach var="row" items="${cp:DICTIONARY('equState') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==state}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaPowerrolergroup.groupName" />
					</td>
					<td align="left" colspan="3"><input type="text" name="groupName"
						style="width: 100%;height:25px;" id="groupName" value="${groupName }" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaPowerrolergroup.remark" />
					</td>
					<td align="left" colspan="3"><textarea id="remark"
							style="width: 100%;" name="remark" rows="3" cols="50">${remark }</textarea></td>
					</td>
				</tr>
	            <%-- <tr id="tr_receive" >
					<td class="addTd">分组人员：</td>
					<td align="left" colspan="3">
					<input id="txt_usercode" type="hidden" name="usercodes"
				     value="${usercodes}" />		
					 <textarea id="txa_name" name="userNames" style="width: 80%;display: inline;">${userNames}</textarea> 
                    <a id="a_href" type="button" class="btn"
							href="${pageContext.request.contextPath}/oa/oaPowerrolergroup!getUnitUsers.do"
							width="400" height=""  target="dialog">添加人员</a>
					</td>
				</tr> --%>
			</table>
			<div class="formButton">
			
			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			
			</div>
		</s:form>
</fieldset>

</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>
