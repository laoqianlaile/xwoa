<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSurveyType.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaSurveyType.edit.title" />
			</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaSurveyType" method="post" namespace="/oa"
			id="oaSurveyTypeForm">
			

			<table border="0" cellpadding="1" cellspacing="1" class="viewTable">
				<c:if test="${ ! empty object.no }">
					<input type="hidden" name="no" value="${object.no }" />
					<input type="hidden" name="creater" value="${object.creater }" />
					<input type="hidden" name="creatertime"
						value="${object.creatertime }" />
				</c:if>
				<!-- 			<tr> -->
				<!-- 					<td class="TDTITLE"> -->
				<%-- 						<s:text name="oaSurveyType.no" /> --%>
				<!-- 					</td> -->
				<!-- 					<td align="left"> -->
				<%-- 							<s:textfield name="no" size="40" /> --%>
				<!-- 					</td> -->
				<!-- 				</tr> -->
				<tr>
					<td class="addTd"><s:text name="oaSurveyType.reType" /></td>
					<td align="left"><s:textfield name="reType" size="40" /></td>
				</tr>
				<!-- 				<tr> -->
				<!-- 					<td class="addTd"> -->
				<%-- 						<s:text name="oaSurveyType.comName" /> --%>
				<!-- 					</td> -->
				<!-- 					<td align="left"> -->
				<%-- 						<s:textfield name="comName"  size="40"/> --%>
				<!-- 					</td> -->
				<!-- 				</tr> -->
				<tr>
					<td class="addTd"><s:text name="oaSurveyType.remark" /></td>
					<td align="left"><s:textfield name="remark" size="40" /></td>
				</tr>
				<input type="hidden" name="state" value="T" />
				<!-- 				<tr> -->
				<!-- 					<td class="addTd"> -->
				<%-- 						<s:text name="oaSurveyType.state" /> --%>
				<!-- 					</td> -->
				<!-- 					<td align="left"> -->
				<!-- 						<select data-rel="chosen" id="state" select style="width: 120px;"name="state" class="combox" > -->
				<!-- 					    	<option value=""></option> -->
				<%-- 								<c:forEach var="row" items="${cp:DICTIONARY('equState') }"> --%>
				<%-- 									<option value="${row.datacode}" --%>
				<%-- 								    	<c:if test="${row.datacode==object.state}"> selected="selected"</c:if>> --%>
				<%-- 										<c:out value="${row.datavalue}" /> --%>
				<!-- 									</option> -->
				<%-- 								</c:forEach> --%>
				<!-- 					   </select>	 -->
				<!-- 					</td> -->
				<!-- 				</tr> -->
				<tr>
				</tr>
				<c:if test="${ ! empty object.no }">
					<tr>
						<td class="addTd"><s:text name="oaSurveyType.creatertime" />
						</td>
						<td align="left"><fmt:formatDate
								value="${object.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
				</c:if>

			</table>
		<div class="formButton">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />
		</div>

		</s:form>
	</fieldset>
</body>