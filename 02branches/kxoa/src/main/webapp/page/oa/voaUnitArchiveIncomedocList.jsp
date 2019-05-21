<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitArchiveIncomedoc.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend> 阅办文归档 </legend>

		<s:form action="voaUnitArchiveIncomedoc" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="nos" id="hid_codes" />
			<input type="hidden" id="itemSource" name="itemSource"
				value="${itemSource}" />
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">阅办文号:</td>
					<td><input type="text" name="s_acceptarchiveno"
						value="${s_acceptarchiveno }" />
					<td class="addTd">文件标题:</td>
					<td><input type="text" name="s_incomedDocTitle"
						value="${s_incomedDocTitle }" />
				</tr>

				<tr>
					<td class="addTd">拟发文字号:</td>
					<td><input type="text" name="s_incomeDocNo"
						value="${s_incomeDocNo }" /></td>

					<td class="addTd">拟发文单位:</td>
					<td><input type="text" name="s_incomeDeptName"
						value="${s_incomeDeptName }" />
				</tr>

				<tr>
					<td class="addTd">阅办文时间:</td>
					<td colspan="4"><input type="text" class="Wdate"
						id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }">value="${param['s_endincomeDate'] }"</c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
					</td>
					<%--  <td class="addTd">更新时间:</td>
						<td>
						<input type="text" class="Wdate"  id="s_begUpdateTime" <c:if test="${not empty s_begUpdateTime }"> value="${begincomeDate}" </c:if>
	                    <c:if test="${empty s_begUpdateTime  }">value="${param['s_begUpdateTime'] }"</c:if> name="s_begUpdateTime"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						      至
						<input type="text" class="Wdate"  id="s_endUpdateTime" <c:if test="${not empty s_endUpdateTime }"> value="${s_endUpdateTime }" </c:if>
	                    <c:if test="${empty s_endUpdateTime  }">value="${param['s_endUpdateTime'] }"</c:if> name="s_endUpdateTime" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						 </td>  --%>
				<tr>
				<tr class="searchButton">

					<td colspan="5"><s:submit method="list" key="opt.btn.query"
							cssClass="btn" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/voaUnitArchiveIncomedoc!list.do" items="objList"
		var="oaUnitArchiveIncomedoc"
		imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="incomeDocType" title="分类"
				style="text-align:center" width="4%">
			    ${cp:MAPVALUE("incomeDocType",oaUnitArchiveIncomedoc.incomeDocType)}
			    </ec:column>
			<ec:column property="acceptarchiveno" title="阅办文号"
				style="text-align:center" width="7%" />
			<ec:column property="incomedDocTitle" title="文件标题"
				style="text-align:center" width="19%">
				<a
					href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${oaUnitArchiveIncomedoc.no }&nodeInstId=0'>
					 <c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.incomedDocTitle) > 20}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.incomedDocTitle, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.incomedDocTitle}" />
						</c:otherwise>
					</c:choose>

				</a>
			</ec:column>
			<ec:column property="incomeDeptName" title="拟发文单位"
				style="text-align:center" width="12%" >
				 <c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.incomeDeptName) >10}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.incomeDeptName, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.incomeDeptName}" />
						</c:otherwise>
					</c:choose>
			 </ec:column>
			<ec:column property="incomeDocNo" title="拟发文字号"
				style="text-align:center" width="8%" >
				<c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.incomeDocNo) > 10}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.incomeDocNo, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.incomeDocNo}" />
						</c:otherwise>
					</c:choose>
			</ec:column>
			<ec:column property="incomeDate" title="阅办文日期"
				style="text-align:center " width="7%">
				<fmt:formatDate value='${oaUnitArchiveIncomedoc.incomeDate}'
					pattern='yyyy-MM-dd' />
			</ec:column>
<ec:column property="bizstate" title="状态" width="4%" style="text-align:center">${cp:MAPVALUE("oa_bizstate",oaUnitArchiveIncomedoc.bizstate)}</ec:column>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center" width="6%">
				<a
					href='oa/oaArchive!add.do?no=${oaUnitArchiveIncomedoc.no }&bookdate=${oaUnitArchiveIncomedoc.incomeDate}&title=${oaUnitArchiveIncomedoc.incomedDocTitle}&docno=${oaUnitArchiveIncomedoc.acceptarchiveno }&flag=S'>归档</a>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script>
	$(function() {
		$('a').each(function() {
			$this = $(this);
			var href = $this.attr('href');
			href = encodeURI(encodeURI(href));
			$this.attr('href', href);
		});
	});
</script>
</html>
