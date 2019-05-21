<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<style>
	.title_a:hover{ color:#0c6da0; }
</style>
		<title>
			<s:text name="oaArchive.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend class="headTitle">
				 公文已归档
			</legend>
			<div class="searchDiv">
			<s:form id="aoarchivelistv2form" method="POST" action="oaArchive" namespace="/oa" style="margin-top:0;margin-bottom:5">
				 <div class="searchArea">
				<table style="width: auto;">

					<tr >
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;"><s:text name="oaArchive.duration" />:</label>
					</td>
					<td class="searchCountArea">
					<select id="duration" style="width:120px;height:25px;" name="s_duration" class="searchCondContent">
							<option value="">--请选择--</option>
							<c:forEach var="row" items="${cp:DICTIONARY('BGNX') }">
								<option value="${row.datacode}" <c:if test="${s_duration==row.datacode}">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
						<label class="searchCondTitle" style="width: 65px;"><s:text name="oaArchive.title" />:</label>
						</td>
					<td class="searchCountArea">
						<input type="text" class="searchCondContent" name="s_title" value="${s_title}"/>
						</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">日期:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endsenddate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
					</tr>
					<tr id="gaoji_more" style="display: none;">
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;"><s:text name="oaArchive.filingannual" />:</label>
					</td>
					<td class="searchCountArea">
					<select id="filingannual" style="width:120px;height:25px;" name="s_filingannual" class="searchCondContent">
							<option value="">--请选择--</option>
						<c:forEach var="row" items="${ndlist}">
								<option value="${row.filingannual}"
									<c:if test="${parameters.s_filingannual[0] eq row.filingannual}">selected="selected"</c:if>>
									<c:out value="${row.filingannual}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
						<label class="searchCondTitle" style="width: 65px;"><s:text name="oaArchive.docno" />:</label>
						</td>
					<td class="searchCountArea">
						<input type="text" class="searchCondContent" name="s_docno"  value="${s_docno}"/>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
						<label class="searchCondTitle" style="width: 65px;"><s:text name="oaArchive.responsibledep" />:</label>
						</td>
					<td class="searchCountArea">
						<input type="text" class="searchCondContent" name="s_responsibledep" value="${s_responsibledep}"/> 
					</td>
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>
 <ec:reqeustAttributeForm id="listReqAttrParam"/>
		<ec:table action="oa/oaArchive!list.do" items="objList" var="oaArchive"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
		    	<c:set var="ttitanic"><s:text name='oaArchive.titanic' /></c:set>	
				<ec:column property="titanic" title="${ttitanic}" style="text-align:center" />

				

				
				<c:set var="ttitle"><s:text name='oaArchive.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" >
				<c:if test="${oaArchive.doctype eq 'S' }">
				<a class='title_a' href='javascript:void(0)' onclick="viewDetail('${oaArchive.no }',0,'S')">
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 20}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.title}" />
						</c:otherwise>
					</c:choose>
				</a>
				</c:if>
				<c:if test="${oaArchive.doctype eq 'F' }">
				<a class='title_a' href='javascript:void(0)' onclick="viewDetail('${oaArchive.no }',0,'F')">
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 20}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaArchive.title}" />
						</c:otherwise>
					</c:choose>
				</a>
				</c:if>
				<c:if test="${oaArchive.doctype ne  'F' and  oaArchive.doctype ne 'S' }">
				 <c:choose>
						<c:when test="${fn:length(oaArchive.title) > 20}">
							<c:out
								value="${fn:substring(oaArchive.title, 0, 20)}..." />
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
					<a class="check_email" href='oa/oaArchive!view.do?id=${oaArchive.id}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a class="bianji" href='oa/oaArchive!edit.do?id=${oaArchive.id}'><s:text name="opt.btn.edit" /></a>
					<a class="delete_email" href='oa/oaArchive!deleteObj.do?id=${oaArchive.id}&no=${oaArchive.no}&doctype=${oaArchive.doctype}&duration=${oaArchive.duration}' 
							onclick='return confirm("是否确认删除?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script>
	function viewDetail(djId,nodeInstId,flag){
		 var paramForm = $("#listReqAttrParam");
		 var url = "";
		 if(flag=='F'){
			url = "${ctx}/dispatchdoc/dispatchDoc!generalOptView.do"; 
		 }
		 if(flag=='S'){
			 url = "${ctx}/dispatchdoc/incomeDoc!generalOptView.do"
		 }
		 paramForm.attr("action",url);
		 
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
	function sub(){
		$("#aoarchivelistv2form").attr("action","oaArchive!list.do");
		$("#aoarchivelistv2form").submit();
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
		if($("#filingannual").val().trim()!=""&&$("#filingannual").val()!=null){
			t=true;
		}
		if($("input[name=s_docno]").val().trim()!=""&&$("input[name=s_docno]").val()!=null){
			t=true;
		}
		if($("input[name=s_responsibledep]").val().trim()!=""&&$("input[name=s_responsibledep]").val()!=null){
			t=true;
		}
		return t;
	}
	$(function() {
		if(gj()){
			showgaoji();
		}
	}); 
</script>
</html>
