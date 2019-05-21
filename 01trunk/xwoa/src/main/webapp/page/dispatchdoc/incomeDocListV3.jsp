<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
		</title>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend class="headTitle"><c:choose><c:when test="${show_type eq 'T' or s_applyItemType eq 'GW' }">收文查看</c:when>
			<c:when test="${empty show_type  or empty  s_applyItemType}">个人收文</c:when></c:choose></legend>
			<div class="searchDiv">
			<s:form id="incomDoc_form" action="incomeDoc" namespace="/dispatchdoc" method="post" style="margin-top:0;margin-bottom:5"  data-changeSubmit="true"> 
			 	<s:hidden name="show_type" value="%{show_type}"/>
			 	<%-- <input type="hidden" id="s_bizstate" name="s_bizstate" value="${param['s_bizstate']}" /> --%>
			 	<input type="hidden" name="s_applyItemType" value="${s_applyItemType}"/>
			 	<input type="hidden" id="s_bmsw" name="s_bmsw" value="${param['s_bmsw']}" />
				<div class="searchArea">
				<table style="width: auto;">
					<tr>
					<td class="searchBtnArea">
					<s:submit method="exportExcelByPoV2" value="导出Excel"
							cssClass="whiteBtnWide" />
					</td>
					<td class="searchTitleArea">
						<label class="searchCondTitle">办件状态:</label>
						</td>
						<td class="searchCountArea">
						<s:radio name="s_bizstate" id="s_bizstate" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle;"
							list="#{'W':'未办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}" onclick="doRadioChick();"></s:radio>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<%-- 	<td  class="searchTitleArea" >
					<label class="searchCondTitle">组织范围:</label>
					</td>
					<td  class="searchCountArea">
						<s:radio name="s_bmsw" id="s_bmsw" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle"
							list="#{'':'厅','T':'处室' }" listKey="key"
							listValue="value" value="%{#parameters['s_bmsw']}"></s:radio>	
					</td> --%>
					<!-- <td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td> -->
					<td   class="searchTitleArea">
						<label class="searchCondTitle">文件标题:</label>
						</td>
					<td  class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_income_Doc_Title" value="${s_income_Doc_Title }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td  class="searchTitleArea">
					<label class="searchCondTitle">经办人员:</label> 
					</td>
					<td>
					<input class="searchCondContent" type="text" name="s_username" value="${s_username }" />
					<label class="searchCondTitle">发文单位:</label>
					<input class="searchCondContent" type="text" name="s_income_Dept_Name" value="${s_income_Dept_Name }" />
					<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">	
					</td>
					
					
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
						</tr>
				<tr id="gaoji_more" style="display: none;">
						<td></td>
					<td  class="searchTitleArea">
					<label class="searchCondTitle">收文号:</label>
					</td>
					<td  class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_acceptArchiveNo" value="${s_acceptArchiveNo }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td  class="searchTitleArea">
					<label class="searchCondTitle">发文字号:</label>
					</td>
					<td class="searchCountArea" >
					<input class="searchCondContent" type="text" name="s_income_Doc_No" value="${s_income_Doc_No }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea" >
					<label class="searchCondTitle">收文时间:</label>
					</td>
					<td  class="searchCountArea">
					<input type="text" class="Wdate searchCondContent" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						
						</td>
					</tr> 
                 </table>
                 </div>
             </s:form>
             </div>
          </fieldset>
           <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
		<ec:table action="dispatchdoc/incomeDoc!listV2.do" items="incomeDocList" var="incomeDoc"
				imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row >
				<ec:column property="acceptArchiveNo"  title="收文号" style="text-align:center" />
				<ec:column property="incomeDocTitle" title="文件标题" style="text-align:left">
				<input type="hidden" value="${incomeDoc.incomeDocTitle}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDocTitle) > 18}">
						 <a href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)"><c:out
								value="${fn:substring(incomeDoc.incomeDocTitle, 0, 18)}..." /></a>
						</c:when>
						<c:otherwise>
							 <a href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)"><c:out value="${incomeDoc.incomeDocTitle}" /></a>
						</c:otherwise>
					</c:choose>
				
				</ec:column>
				<ec:column property="incomeDeptName" title="发文单位" style="text-align:center" />
				<ec:column property="incomeDocNo" title="发文字号" style="text-align:center">
					<input type="hidden" value="${incomeDoc.incomeDocNo}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDocNo) > 12}">
							<c:out
								value="${fn:substring(incomeDoc.incomeDocNo, 0, 12)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.incomeDocNo}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="incomeDate" title="收文时间" style="text-align:center" format="yyyy-MM-dd" cell="date" />		
				<ec:column property="transcontent" title="拟办意见" style="text-align:center">
					<input type="hidden" value="${incomeDoc.transcontent}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.transcontent) > 8}">
							<c:out
								value="${fn:substring(incomeDoc.transcontent, 0,8)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.transcontent}" />
						</c:otherwise>
					</c:choose>
				</ec:column>		
			<%-- 	<ec:column property="bizstate" title="状态" style="text-align:center">
					<c:if test="${incomeDoc.bizstate eq 'F'}">
						未提交
					</c:if>
					<c:if test="${incomeDoc.bizstate eq 'W'}">
						办理中
					</c:if>
					<c:if test="${incomeDoc.bizstate eq 'C'}">
						结束
					</c:if>
				</ec:column> --%>
				<c:if test="${s_bizstate ne 'C' }">
					<ec:column property="nodename" title="办理步骤"
					style="text-align:center" sortable="true">
					<input type="hidden" value="${incomeDoc.nodename}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.nodename) > 20}">
							<c:out
								value="${fn:substring(incomeDoc.nodename, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.nodename}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="bizusernames" title="办理人员" style="text-align:center" sortable="true">
					<input type="hidden" value="${incomeDoc.bizusernames}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.bizusernames) > 10}">
							<c:out
								value="${fn:substring(incomeDoc.bizusernames, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.bizusernames}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
					</c:if>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">

<%-- 					    <a href="${contextPath}/dispatchdoc/incomeDoc!registerDoOrReadView.do?djId=${incomeDoc.djId}">查看</a> --%>
                        <c:if test="${incomeDoc.bizstate ne 'F' }">
						 <a class="check_email" href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)">查看</a>
						 </c:if>
						<c:if test="${incomeDoc.bizstate eq 'F' }">
						<a class="bianji" href="${contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?djId=${incomeDoc.djId}">编辑</a>
					
						<a class="delete_email" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!delete.do?djId=${incomeDoc.djId}">删除</a>
					</c:if>
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
	 function checkFrom(){
			var begD = $("#s_begUpdateTime").val();
			var endD = $("#s_endUpdateTime").val();
			if(endD!=""&&begD>endD){
				alert("结束时间不能早于开始时间。");
//	 			$("#s_endTime").focus();
				return false;
			}
			return true;
		}
	 function sub(){
			if(checkFrom()==true){
				$("#incomDoc_form").attr("action","incomeDoc!listV2.do");
			$("#incomDoc_form").submit();
			}
		
	}
	 
	 
	 function doRadioChick(){
		    $("#incomDoc_form").attr("action","incomeDoc!listV2.do");
			$("#incomDoc_form").submit();
		}
	 
	 
		function replaceUrl(a) {
			var doOptUrl = a.href; 		
			doOptUrl = doOptUrl.replaceAll("amp%3B","");
			a.href=doOptUrl;
			return false;
		}
		
        $(function(){
			
			$('#queryUnderUnit').live("click", function() {
				unitListIsShow();
			});
			unitListIsShow();
		});

		function unitListIsShow(){
			
			if('checked'==$('#queryUnderUnit').attr("checked")){
				if('TZ'=="${userRank}"){
					$("#tr_unitlist").show();
				}
			}else{
				$("#tr_unitlist").hide();
			}
		};
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
	</script>
</html>
