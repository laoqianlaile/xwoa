<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="voaUnitArchiveDispatchdoc.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; " class="search">
			<legend>
				拟发文归档
			</legend>
			
			<s:form action="voaUnitArchiveDispatchdoc" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					
					<tr>
					    <td class="addTd">拟发文号:</td>
						<td >
						<input type="text" name="s_dispatchDocNo" value="${s_dispatchDocNo }" />
						<td class="addTd" >文件标题:</td>
						<td >
						<input type="text" name="s_transaffairname" value="${s_transaffairname }" />
						
						</td>
					</tr>
					<tr>
					<td class="addTd">主送单位:</td>
					<td><input type="text" name="s_mainNotifyUnit" value="${s_mainNotifyUnit }" /></td>
					
					<td class="addTd">抄送单位:</td>
					<td><input type="text" name="s_otherUnits" value="${s_otherUnits }" /></td>
				    </tr>
				    
					<tr>
						<td class="addTd">拟文日期:</td>
						<td colspan="3">
						<input type="text" class="Wdate"  id="s_begincomeDate" <c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
	                    <c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if> name="s_begincomeDate"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						      至
						<input type="text" class="Wdate"  id="s_endincomeDate" <c:if test="${not empty s_endincomeDate}"> value="${s_endincomeDate}" </c:if>
	                    <c:if test="${empty s_endincomeDate  }">value="${param['s_endincomeDate'] }"</c:if> name="s_endincomeDate" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						</td>
					
					</tr>
					
					
					<tr class="searchButton" >
				
						<td  colspan="5">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/voaUnitArchiveDispatchdoc!list.do" items="objList" var="voaUnitArchiveDispatchdoc"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit"> 
			<ec:row>
			    <%-- <ec:column property="transaffairname"  title="标题" style="text-align:center" >
			    </ec:column>
			    <ec:column property="dispatchDocNo"  title="发文号" style="text-align:center" >
			    </ec:column>
			     <ec:column property="mainNotifyUnit"  title="主送单位" style="text-align:center" >
			    </ec:column>
				<ec:column property="optUnitName" title="单位名称" style="text-align:center" >
				<a href='${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${voaUnitArchiveDispatchdoc.no }&nodeInstId=0'>
				${voaUnitArchiveDispatchdoc.optUnitName}</a>
				</ec:column>
				<ec:column property="createDate" title="收文日期" style="text-align:center">
				 <fmt:formatDate value='${voaUnitArchiveDispatchdoc.createDate}' pattern='yyyy-MM-dd' />
				</ec:column>
 --%>

			<ec:column property="dispatchDocNo" title="拟发文号" style="text-align:center" />
			<ec:column property="transaffairname" title="文件标题" style="text-align:center" >
			<a href="${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${voaUnitArchiveDispatchdoc.no}&nodeInstId=0"> 
			 <c:choose>
						<c:when test="${fn:length(voaUnitArchiveDispatchdoc.transaffairname) > 20}">
							<c:out
								value="${fn:substring(voaUnitArchiveDispatchdoc.transaffairname, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${voaUnitArchiveDispatchdoc.transaffairname}" />
						</c:otherwise>
					</c:choose></a>
			</ec:column>
			<ec:column property="createDate" title="拟发文时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
			<ec:column property="draftUserName" title="经办人" style="text-align:center">${voaUnitArchiveDispatchdoc.draftUserName}</ec:column>
			<ec:column property="dispatchUser" title="签发人" style="text-align:center">${cp:MAPVALUE("usercode",voaUnitArchiveDispatchdoc.dispatchUser)}</ec:column>
			<ec:column property="mainNotifyUnit" title="主送单位" style="text-align:center"/>
			<ec:column property="bizstate" title="状态" style="text-align:center">${cp:MAPVALUE("oa_bizstate",voaUnitArchiveDispatchdoc.bizstate)}</ec:column>
			<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center" >
					    <a href='oa/oaArchive!add.do?no=${voaUnitArchiveDispatchdoc.no }&bookdate=${voaUnitArchiveDispatchdoc.createDate}&title=${voaUnitArchiveDispatchdoc.transaffairname}&docno=${voaUnitArchiveDispatchdoc.dispatchDocNo }&flag=F'>归档</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script>
  	$(function() {
		$('a').each(function() {
			$this = $(this);
			var href = $this.attr('href');
			href=encodeURI(encodeURI(href));
			$this.attr('href', href);
		});
	}); 
  	
  	function checkFrom(){
		var begD = $("#s_begTime").val();
		var endD = $("#s_endTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
			return false;
		}
		return true;
	} 
</script>
</html>
