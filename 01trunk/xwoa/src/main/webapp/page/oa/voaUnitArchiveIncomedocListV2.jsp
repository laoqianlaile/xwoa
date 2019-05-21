<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
	

<title><s:text name="oaUnitArchiveIncomedoc.list.title" /></title>
</head>
<style type="text/css">
	.eXtremeTable{margin-top:0!important}
	.searchDiv{margin-bottom:0!important;padding-bottom:0px!important}
	</style>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle">收文归档 </legend>
	<div class="searchDiv">
		<s:form id="voaunitarchiveincomeform" method="POST" action="voaUnitArchiveIncomedoc" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			 <div class="searchArea">
			<input type="hidden" name="nos" id="hid_codes" />
			<input type="hidden" id="itemSource" name="itemSource"
				value="${itemSource}" />
			<table style="width: auto;">

				<tr>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;">收文号:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" name="s_acceptarchiveno" class="searchCondContent" style="height: auto;" 
						value="${s_acceptarchiveno }" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">文件标题:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" name="s_INCOME_DOC_TITLE" class="searchCondContent" style="height: auto;" 
						value="${s_INCOME_DOC_TITLE }" />
				
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;">收文时间:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" class="Wdate searchCondContent"
						id="s_begincomeDate" style="height: auto;"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endincomeDate" style="height: auto;"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }">value="${param['s_endincomeDate'] }"</c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<td class="searchTitleArea">
			
					<label class="searchCondTitle" style="width: 65px;">发文字号:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" name="s_INCOME_DOC_NO" class="searchCondContent" style="height: auto;"
						value="${s_INCOME_DOC_NO }" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">发文单位:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" name="s_INCOME_DEPT_NAME" class="searchCondContent" style="height: auto;"
						value="${s_INCOME_DEPT_NAME }" />
				</td>
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
  <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
	<ec:table action="oa/voaUnitArchiveIncomedoc!list.do" items="objList"
		var="oaUnitArchiveIncomedoc"
		imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="incomeDocType" title="分类"
				style="text-align:center" width="4%">
			    ${cp:MAPVALUE("incomeDocType",oaUnitArchiveIncomedoc.incomeDocType)}
			    </ec:column>
			<ec:column property="acceptarchiveno" title="收文号" style="text-align:center" width="15%" sortable="true">
				<input type="hidden" value="${oaUnitArchiveIncomedoc.acceptarchiveno}"/> 			
					 <c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.acceptarchiveno) > 20}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.acceptarchiveno, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.acceptarchiveno}" />
						</c:otherwise>
					</c:choose>			
			</ec:column>
				
				
			
			<ec:column property="incomedDocTitle" title="文件标题"
				style="text-align:center" width="15%">
				<input type="hidden" value="${oaUnitArchiveIncomedoc.incomedDocTitle}"/> 	
				<a
					href='javascript:void(0)' onclick="viewDetail('${oaUnitArchiveIncomedoc.no }',0)" title="${oaUnitArchiveIncomedoc.incomedDocTitle}">
					 <c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.incomedDocTitle) > 16}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.incomedDocTitle, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.incomedDocTitle}" />
						</c:otherwise>
					</c:choose>

				</a>
			</ec:column>
			<ec:column property="incomeDeptName" title="发文单位"
				style="text-align:center" width="10%" >
		   	<input type="hidden" value="${oaUnitArchiveIncomedoc.incomeDeptName}"/>
				 <c:choose>
						<c:when test="${fn:length(oaUnitArchiveIncomedoc.incomeDeptName) >8}">
							<c:out
								value="${fn:substring(oaUnitArchiveIncomedoc.incomeDeptName, 0, 8)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaUnitArchiveIncomedoc.incomeDeptName}" />
						</c:otherwise>
					</c:choose>
			 </ec:column>
			<ec:column property="incomeDocNo" title="发文字号"
				style="text-align:center" width="8%" >
	        <input type="hidden" value="${oaUnitArchiveIncomedoc.incomeDocNo}"/>
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
			<ec:column property="incomeDate" title="收文日期"
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
				<a class="guidang"
					href='oa/oaArchive!add.do?no=${oaUnitArchiveIncomedoc.no }&bookdate=${oaUnitArchiveIncomedoc.incomeDate}&title=${oaUnitArchiveIncomedoc.incomedDocTitle}&docno=${oaUnitArchiveIncomedoc.acceptarchiveno }&flag=S'>归档</a>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script>
function viewDetail(djId,nodeInstId){
	 var paramForm = $("#listReqAttrParam");
	 paramForm.attr("action","${ctx}/dispatchdoc/incomeDoc!generalOptView.do");
	 
	 var djIdInput = paramForm.find("input[name='djId']"),
		nodeInstIdInput = paramForm.find("input[name='nodeInstId']");
		if(djIdInput.length == 0){
			djIdInput = $("<input>",{"name":"djId","type":"hidden"});
			paramForm.append(djIdInput);
		}
		if(nodeInstIdInput.length == 0){
			nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
			paramForm.append(nodeInstIdInput);
		}
		djIdInput.val(djId);
		nodeInstIdInput.val(nodeInstId);
	 paramForm.submit();
}
	$(function() {
		$('a').each(function() {
			$this = $(this);
			var href = $this.attr('href');
			href = encodeURI(encodeURI(href));
			$this.attr('href', href);
		});
		if(gj()){
			showgaoji();
		}
	});
	function sub(){
		$("#voaunitarchiveincomeform").attr("action","voaUnitArchiveIncomedoc!list.do");
		$("#voaunitarchiveincomeform").submit();
	} 
	function showgaoji(){
		$("#shouqi").show();
		$("#gaoji_more").show();
		$("#gaoji").hide();
	}
	function toshouqi(){
		$("#shouqi").hide();
		$("#gaoji_more").hide();
		$("#gaoji").show();
	}
	function gj(){
		var t=false;
		if($("input[name=s_INCOME_DOC_NO]").val().trim()!=""&&$("input[name=s_INCOME_DOC_NO]").val()!=null){
			t=true;
		}
		if($("input[name=s_INCOME_DEPT_NAME]").val().trim()!=""&&$("input[name=s_INCOME_DEPT_NAME]").val()!=null){
			t=true;
		}
		return t;
	}
</script>
</html>
