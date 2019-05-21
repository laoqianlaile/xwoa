<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		</title>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend class="headTitle">
			发文汇总
			</legend>
			<div class="searchDiv" >
			<s:form id="dispathcDoc_form" action="dispatchDoc" namespace="/dispatchdoc" method="post" style="margin-top:0;margin-bottom:5"  data-changeSubmit="true"> 
			 <s:hidden name="show_type" value="%{show_type}"/>
			 <%-- <input type="hidden" id="s_bizstate" name="s_bizstate" value="${param['s_bizstate']}" /> --%>
			 <input type="hidden" id="s_bmfw" name="s_bmfw" value="${param['s_bmfw']}" />
				<div class="searchArea">
				<table style="width: auto;">
					<tr>
					<td class="searchBtnArea">
				<%-- 	<c:if test="${ 'T' ne show_type }"> 
					<s:submit method="built"  key="opt.btn.new" cssClass="whiteBtnWide" />
					</c:if> --%>
					<s:submit method="exportExcelByPoV2" value="导出Excel"
							cssClass="whiteBtnWide" />
					</td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">办件状态:</label>
					</td>
					<td class="searchCountArea">
					<s:radio name="s_bizstate" id="s_bizstate"  cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle;"
							list="#{'W':'未办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}" onclick="doRadioChick();"></s:radio>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
			<%-- 		<td class="searchTitleArea">
					<label class="searchCondTitle">组织范围:</label>
					 </td>
						<td class="searchCountArea">
						<s:radio name="s_bmfw" id="s_bmfw" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle"
							list="#{'':'厅','T':'处室' }" listKey="key"
							listValue="value" value="%{#parameters['s_bmfw']}"></s:radio>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td> --%>
					<td class="searchTitleArea">
					<label class="searchCondTitle">文件标题:</label>
					 </td>
						<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_transaffairname" value="${s_transaffairname }" />
					</td>
					
					
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">经手人员:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_username" value="${s_username}" />
					<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
					<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					 <td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<td style="padding-left: 0px;padding-right: 0px;"></td>
				
					<td class="searchTitleArea">
					<label class="searchCondTitle">发文号:</label>
					 </td>
						<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_dispatchDocNo" value="${s_dispatchDocNo }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">主送单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_mainNotifyUnit" value="${s_mainNotifyUnit }" />
					</td>
					<%-- <td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>	
					<td class="searchTitleArea">
					<label class="searchCondTitle">抄送单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_otherUnits" value="${s_otherUnits }" />
					</td> --%>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>	
					<td class="searchTitleArea">
					<label class="searchCondTitle">拟文日期:</label>
					 </td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
						<%-- <img src="${contextPath }/newStatic/image/search.png" style="height:26px;vertical-align: bottom" onclick="sub()"/> --%>
					</td>
				
					</tr>
                 </table>
                 </div>
             </s:form>
             </div>
          </fieldset>
            <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
		<ec:table action="dispatchdoc/dispatchDoc!listV2.do" items="dispatchDocList" var="dispatchDoc"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="dispatchDocNo" title="发文号" style="text-align:center;" />
					<ec:column property="dispatchDocTitle" title="文件标题" style="text-align:left;" >
					   <input type="hidden" value="${dispatchDoc.dispatchDocTitle}"/>      
			          <c:choose>
						<c:when test="${fn:length(dispatchDoc.dispatchDocTitle) > 20}">
						<a  href="javascript:void(0);" onclick="viewDetail('${dispatchDoc.djId}',0)"><c:out
								value="${fn:substring(dispatchDoc.dispatchDocTitle, 0, 20)}..." /></a>
						</c:when>
						<c:otherwise>
								<a  href="javascript:void(0);" onclick="viewDetail('${dispatchDoc.djId}',0)"><c:out value="${dispatchDoc.dispatchDocTitle}"/></a>
						</c:otherwise>
					</c:choose>
					</ec:column>
					<ec:column property="createDate" title="发文时间" style="text-align:center;width:12%;" format="yyyy-MM-dd HH:mm" cell="date" />
			        <ec:column property="draftUserName" title="经办人" style="text-align:center;width:6%;" >${cp:MAPVALUE('usercode',dispatchDoc.draftUserName ) }</ec:column>
				    <ec:column property="dispatchUser" title="签发人" style="text-align:center;width:6%;" >${cp:MAPVALUE('usercode',dispatchDoc.dispatchUser) }</ec:column>
				    <ec:column property="mainNotifyUnit" title="主送单位" style="text-align:center;width:12%;" >${dispatchDoc.mainNotifyUnit}</ec:column>
					<c:if test="${s_bizstate ne 'C' }">
					<ec:column property="nodename" title="办理步骤"
					style="text-align:center" sortable="true">
					<input type="hidden" value="${dispatchDoc.nodename}"/> 
					<c:choose>
						<c:when test="${fn:length(dispatchDoc.nodename) > 20}">
							<c:out
								value="${fn:substring(dispatchDoc.nodename, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${dispatchDoc.nodename}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="bizusernames" title="办理人员" style="text-align:center;width:8%;" sortable="true">
					<input type="hidden" value="${dispatchDoc.bizusernames}"/> 
					<c:choose>
						<c:when test="${fn:length(dispatchDoc.bizusernames) > 10}">
							<c:out
								value="${fn:substring(dispatchDoc.bizusernames, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${dispatchDoc.bizusernames}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
					</c:if>
					<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center;width:6%;">
				    <c:if test="${dispatchDoc.bizstate ne 'F' }">
					    <a class="check_email" href="javascript:void(0);" onclick="viewDetail('${dispatchDoc.djId}',0)">查看</a>
					</c:if>
					<c:if test="${dispatchDoc.bizstate eq 'F' }">
						<a class="bianji" href="${contextPath}/dispatchdoc/dispatchDoc!registerEdit.do?djId=${dispatchDoc.djId}">编辑</a>
						<a class="delete_email" href="<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!deleteObject.do?djId=${dispatchDoc.djId}">删除</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
/* 	$(function(){
		 openLoadIng(false);		
	}); */
	 function viewDetail(djId,nodeInstId){
		 var paramForm = $("#listReqAttrParam");
		 paramForm.attr("action","${ctx}/dispatchdoc/dispatchDoc!generalOptView.do");
		 
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
			var begD = $("#s_begTime").val();
			var endD = $("#s_endTime").val();
			if(endD!=""&&begD>endD){
				alert("结束时间不能早于开始时间。");
//	 			$("#s_endTime").focus();
				return false;
			}
			return true;
		} 
	 function sub(){
			if(checkFrom()==true){
				$("#dispathcDoc_form").attr("action","dispatchDoc!listV2.do");
			$("#dispathcDoc_form").submit();
			}
		
	}
		function replaceUrl(a) {
			var doOptUrl = a.href;
			doOptUrl = doOptUrl.replaceAll("amp%3B","");
			a.href=doOptUrl;
			return false;
		}
		
		$(function(){
			 openLoadIng(false);
			$('#queryUnderUnit').live("click", function() {
				unitListIsShow();
			});
			unitListIsShow();
		});
		
		 function doRadioChick(){
			    $("#dispathcDoc_form").attr("action","dispatchDoc!listV2.do");
				$("#dispathcDoc_form").submit();
			}
		

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
