<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaArchive.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend>
				 公文已归档
			</legend>
			
			<s:form action="oaArchive" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaArchive.title" />:</td>
						<td ><s:textfield name="s_title" value="%{#parameters['s_title']}"/> </td>
						<td class="addTd"><s:text name="oaArchive.docno" />:</td>
						<td><s:textfield name="s_docno"  value="%{#parameters['s_docno']}"/></td>
					</tr>	
					<tr >
						<td class="addTd"><s:text name="oaArchive.responsibledep" />:</td>
						<td><s:textfield name="s_responsibledep" value="%{#parameters['s_responsibledep']}"/> </td>
						<td class="addTd"><s:text name="oaArchive.keywords" />:</td>
						<td><s:textfield name="s_keywords" value="%{#parameters['s_keywords']}"/> </td>
					</tr>	
					<tr>
					<td class="addTd">
						<s:text name="oaArchive.duration" />:
					</td>
						<td align="left"><select id="duration" style="width:140px;height:25px;" name="s_duration">
							<option value="">--请选择--</option>
							<c:forEach var="row" items="${cp:DICTIONARY('BGNX') }">
								<option value="${row.datacode}" <c:if test="${s_duration==row.datacode}">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">
						<s:text name="oaArchive.filingannual" />:
					</td>
						<td align="left"><select id="filingannual" style="width:140px;height:25px;" name="s_filingannual">
							<option value="">--请选择--</option>
						<c:forEach var="row" items="${ndlist}">
								<option value="${row.filingannual}"
									<c:if test="${parameters.s_filingannual[0] eq row.filingannual}">selected="selected"</c:if>>
									<c:out value="${row.filingannual}" />
								</option>
							</c:forEach>
					</select></td>
					</tr>
					<tr >
						<td class="addTd">日期:</td>
                        <td> <input type="text" class="Wdate" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endsenddate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
                        </td> 
					
						 <td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaArchive!list.do" items="objList" var="oaArchive"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
		    	<c:set var="ttitanic"><s:text name='oaArchive.titanic' /></c:set>	
				<ec:column property="titanic" title="${ttitanic}" style="text-align:center" />

				

				
				<c:set var="ttitle"><s:text name='oaArchive.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" >
				<c:if test="${oaArchive.doctype eq 'S' }">
				<a href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${oaArchive.no }&nodeInstId=0'>
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 36}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 36)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.title}" />
						</c:otherwise>
					</c:choose>
				</a>
				</c:if>
				<c:if test="${oaArchive.doctype eq 'F' }">
				<a href='${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${oaArchive.no }&nodeInstId=0'>
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 36}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 36)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.title}" />
						</c:otherwise>
					</c:choose>
				</a>
				</c:if>
				<c:if test="${oaArchive.doctype ne  'F' and  oaArchive.doctype ne 'S' }">
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 36}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 36)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.title}" />
						</c:otherwise>
					</c:choose>
				</c:if>				
				</ec:column>			
 				<c:set var="tdocno">文号</c:set>	
				<ec:column property="docno" title="${tdocno}" style="text-align:center" />
				
				<c:set var="tfilingannual"><s:text name='oaArchive.filingannual' /></c:set>	
				<ec:column property="filingannual" title="${tfilingannual}" style="text-align:center" />
		<%--
				<c:set var="tfilingdate"><s:text name='oaArchive.filingdate' /></c:set>	
				<ec:column property="filingdate" title="${tfilingdate}" style="text-align:center" >
				<fmt:formatDate value='${oaArchive.filingdate}' pattern='yyyy-MM-dd HH:mm:ss' />
				</ec:column>

				<c:set var="tdoctype"><s:text name='oaArchive.doctype' /></c:set>	
				<ec:column property="doctype" title="${tdoctype}" style="text-align:center" >
				${cp:MAPVALUE('DOCPATTEARN',oaArchive.doctype)}
				</ec:column> --%>

				<c:set var="tcreatetime">文件形成时间</c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" >
				<fmt:formatDate value='${oaArchive.createtime}' pattern='yyyy-MM-dd HH:mm' />
				</ec:column>
				
			   <c:set var="tresponsibledep"><s:text name='oaArchive.responsibledep' /></c:set>	
				<ec:column property="responsibledep" title="${tresponsibledep}" style="text-align:center" >
				 <c:choose>
						<c:when test="${fn:length(oaArchive.responsibledep) > 10}">
							<c:out
								value="${fn:substring(oaArchive.responsibledep, 0,10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.responsibledep}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaArchive!view.do?id=${oaArchive.id}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaArchive!edit.do?id=${oaArchive.id}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaArchive!deleteObj.do?id=${oaArchive.id}&no=${oaArchive.no}&doctype=${oaArchive.doctype}&duration=${oaArchive.duration}' 
							onclick='return confirm("是否确认删除?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
