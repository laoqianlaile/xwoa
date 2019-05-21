<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
 <%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>  --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title><s:text name="oaUnitIncomedoc.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle"> 阅办文开放阅读查看 </legend>
	<div class="searchDiv">
		<s:form id="oaunitincomedocv2form" method="POST" action="oaUnitIncomedoc" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			 <div class="searchArea">
			<table style="width: auto;">

				<tr>
				<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">阅办文号:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_acceptarchiveno" value="${s_acceptarchiveno }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">文件标题:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_incomedDocTitle" value="${s_incomedDocTitle }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">阅办文时间:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_endincomeDate"
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
					<label class="searchCondTitle" style="width: 65px;">拟发文单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_income_Dept_Name"
						value="${s_income_Dept_Name }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">拟发文字号:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_income_Doc_No"
						value="${s_income_Doc_No }" />
					</td>
				
				
				</tr>


			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
  <!-- 添加请求参数表单域 -->
    <ec:reqeustAttributeForm id="listReqAttrParam"/>
	<ec:table action="oa/oaUnitIncomedoc!list.do" items="VoaUnitIncomedocs"
		var="oaUnitIncomedoc" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="incomeDocType" title="文书分类"
				style="text-align:center" width="8%">
				${cp:MAPVALUE("incomeDocType",oaUnitIncomedoc.incomeDocType)}
				</ec:column>
			<ec:column property="acceptarchiveno" title="阅办文号"
				style="text-align:center" width="8%" />
			<ec:column property="incomedDocTitle" title="文件标题"
				style="text-align:center" width="18%">
				<a
					href="javascript:void(0)" onclick="viewDetail('${oaUnitIncomedoc.no }',0)">
					${oaUnitIncomedoc.incomedDocTitle}</a>
			</ec:column>
			<ec:column property="incomeDeptName" title="拟发文单位"
				style="text-align:center" width="14%" />
			<ec:column property="incomeDocNo" title="拟发文字号"
				style="text-align:center" width="8%" />
			<ec:column property="incomeDate" title="阅办文日期"
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
				<a class="check_email"
					href="javascript:void(0)" onclick="viewDetail('${oaUnitIncomedoc.no }',0)"><s:text
						name="opt.btn.view" /></a>
				<%-- <a href='oa/oaUnitIncomedoc!edit.do?id=${oaUnitIncomedoc.id}'>公开</a> --%>
				<%-- <a href='oa/oaUnitIncomedoc!delete.do?id=${oaUnitIncomedoc.id}' 
							onclick='return confirm("${deletecofirm}oaUnitIncomedoc?");'><s:text name="opt.btn.delete" /></a> --%>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
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
function sub(){
	$("#oaunitincomedocv2form").attr("action","oaUnitIncomedoc!list.do");
	$("#oaunitincomedocv2form").submit();
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
	if($("input[name=s_income_Dept_Name]").val().trim()!=""&&$("input[name=s_income_Dept_Name]").val()!=null){
		t=true;
	}
	if($("input[name=s_income_Doc_No]").val().trim()!=""&&$("input[name=s_income_Doc_No]").val()!=null){
		t=true;
	}
	return t;
}
 $(function(){
		if(gj()){
			showgaoji();
		}
	});
</script>
</html>
