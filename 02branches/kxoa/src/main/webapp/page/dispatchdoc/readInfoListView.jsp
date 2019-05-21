<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			阅件查看
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				查询条件
			</legend>
			
			<s:form action="readInfo" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td>文件标题:</td>
						<td><s:textfield name="s_incomeDocTitle"  value="%{#parameters['s_incomeDocTitle']}"/> </td>					
						<td>来文单位:</td>
						<td><s:textfield name="s_incomeDeptName"  value="%{#parameters['s_incomeDeptName']}"/> </td>
					
					
					</tr>	
					
						<tr >
					
					
						<td>制发日期:</td>
						<td>
							<input type="text" class="Wdate"name="s_incomeDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_incomeDate']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker name="s_incomeDate" style="width: 120px;" --%>
<%-- 						value="%{#parameters['s_incomeDate']}" --%>
<%-- 						yearRange="2000:2020" displayFormat="yy-mm-dd" --%>
<%-- 						changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
						 </td>
						 <td>文件类型:</td>
						<td><select id="s_docFileType"  name="s_docFileType">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('DOC_FILE_TYPE')}">
								<option value="${row.key}" <c:if test="${parameters.s_docFileType[0] eq row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select> </td>
					
					</tr>
					<tr>
						<td>
							<s:submit method="listView"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/readInfo!listView.do" items="infoList" var="readInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="incomeDocTitle" title="文件标题" style="text-align:center" />
				<ec:column property="incomeDeptName" title="来文单位" style="text-align:center" />
				<ec:column property="incomeDate" title="制发日期" style="text-align:center" >
					<fmt:formatDate value="${readInfo.incomeDate}" pattern="yyyy-MM-dd"/>
				</ec:column>
				<ec:column property="createName" title="登记人" style="text-align:center">
				${cp:MAPVALUE("userCode",readInfo.createName)}
				</ec:column>
				<ec:column property="incomeDocName" title="正文" style="text-align:center">
				<a  style="text-decoration:none;color: blue;" href="<%=request.getContextPath()%>/dispatchdoc/readInfo!downloadstuff.do?readNo=${readInfo.readNo}">${readInfo.incomeDocName}</a>
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<a href='readInfo!view.do?readNo=${readInfo.readNo}&ec_p=${ec_p}&ec_crd=${ec_crd}'>查看</a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>
