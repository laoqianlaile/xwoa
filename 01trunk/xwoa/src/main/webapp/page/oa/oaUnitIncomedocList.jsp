<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitIncomedoc.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend> 收文开放阅读查看 </legend>

		<s:form action="oaUnitIncomedoc" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">收文号:</td>
					<td><input type="text" name="s_acceptarchiveno"
						value="${s_acceptarchiveno }" />
					<td class="addTd">文件标题:</td>
					<td><input type="text" name="s_incomedDocTitle"
						value="${s_incomedDocTitle }" />
				</tr>

				<tr>
					<td class="addTd">发文单位:</td>
					<td><input type="text" name="s_incomeDeptName"
						value="${s_incomeDeptName }" /></td>
					<td class="addTd">发文字号:</td>
					<td><input type="text" name="s_incomeDocNo"
						value="${s_incomeDocNo }" /></td>
				</tr>
				<tr>
					<td class="addTd">收文时间:</td>
					<td><input type="text" class="Wdate" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
					</td>
				
						<td colspan="2"  ><s:submit method="list" key="opt.btn.query" style="float:right;"
							cssClass="btn" /></td>
				</tr>



				<%-- 	<tr>
						 <td class="addTd">更新时间:</td>
						<td>
				<input type="text" class="Wdate" id="s_begUpdateTime"
						<c:if test="${not empty s_begUpdateTime }"> value="${s_begUpdateTime}" </c:if>
						<c:if test="${empty s_begUpdateTime  }">value="${param['s_begUpdateTime'] }"</c:if>
						name="s_begUpdateTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endUpdateTime"
						<c:if test="${not empty s_endUpdateTime }"> value="${s_endUpdateTime }" </c:if>
						<c:if test="${empty s_endUpdateTime  }"> value="${param['s_endUpdateTime'] }" </c:if>
						name="s_endUpdateTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						 </td> 
						  <td class="addTd">发文字号:</td>
						<td >
						<input type="text" name="s_incomeDocNo" value="${s_incomeDocNo }" /></td>
					<tr> --%>

				<%-- <tr class="searchButton">
					<td colspan="4"><s:submit method="list" key="opt.btn.query"
							cssClass="btn" /></td>
				</tr> --%>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaUnitIncomedoc!list.do" items="VoaUnitIncomedocs"
		var="oaUnitIncomedoc" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="incomeDocType" title="文书分类"
				style="text-align:center" width="8%">
				${cp:MAPVALUE("incomeDocType",oaUnitIncomedoc.incomeDocType)}
				</ec:column>
			<ec:column property="acceptarchiveno" title="收文号"
				style="text-align:center" width="8%" />
			<ec:column property="incomedDocTitle" title="文件标题"
				style="text-align:center" width="18%">
				<a
					href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${oaUnitIncomedoc.no }&nodeInstId=0'>
					${oaUnitIncomedoc.incomedDocTitle}</a>
			</ec:column>
			<ec:column property="incomeDeptName" title="发文单位"
				style="text-align:center" width="14%" />
			<ec:column property="incomeDocNo" title="发文字号"
				style="text-align:center" width="8%" />
			<ec:column property="incomeDate" title="收文日期"
				style="text-align:center " width="8%">
				<fmt:formatDate value='${oaUnitIncomedoc.incomeDate}'
					pattern='yyyy-MM-dd' />
			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center" width="6%">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${oaUnitIncomedoc.no }&nodeInstId=0'><s:text
						name="opt.btn.view" /></a>
				<%-- <a href='oa/oaUnitIncomedoc!edit.do?id=${oaUnitIncomedoc.id}'>公开</a> --%>
				<%-- <a href='oa/oaUnitIncomedoc!delete.do?id=${oaUnitIncomedoc.id}' 
							onclick='return confirm("${deletecofirm}oaUnitIncomedoc?");'><s:text name="opt.btn.delete" /></a> --%>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
