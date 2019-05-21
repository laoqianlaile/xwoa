<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
		</title>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend class="headTitle"><c:choose><c:when test="${show_type eq 'T' or s_applyItemType eq 'GW' }">收文查看</c:when>
			<c:when test="${empty show_type  or empty  s_applyItemType}">个人收文</c:when></c:choose></legend>
			<div class="searchDiv">
			<s:form id="incomedoclistv1form" action="incomeDoc" namespace="/dispatchdoc" method="post" style="margin-top:0;margin-bottom:5"> 
			 	 <div class="searchArea">
			 	<s:hidden name="show_type" value="%{show_type}"/>
			 	<input type="hidden" name="s_applyItemType" value="${s_applyItemType}"/>
				<table style="width: auto;">
					<tr>
						<td class="searchBtnArea">
						<s:submit method="exportExcelByPo" value="导出Excel"
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
						<td class="searchTitleArea">
						<label class="searchCondTitle">文件标题:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_income_Doc_Title" value="${s_income_Doc_Title }" />
						</td>
						
						<td class="searchCountArea">
						
						<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
					</tr>	
						
					
						<tr id="gaoji_more" style="display: none;">
						<td></td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">收文号:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" style="width: 142px;" type="text" name="s_acceptArchiveNo" value="${s_acceptArchiveNo }" />
						</td>
						
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">发文字号:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_income_Doc_No" value="${s_income_Doc_No }" />
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">收文时间:</label>
						</td>
						<td>
						<input type="text" class="Wdate searchCondContent" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label>  <input type="text" class="Wdate searchCondContent" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
						</td>
						
						</tr>
						
						<c:if test="${not empty userRank}">
						<tr id="gaoji_more2" style="display: none;">
						<td></td>
						<td class="searchCondArea" colspan="2">
							<c:if test="${userRank=='TZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看全部</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>

							<c:if test="${userRank=='CZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看本科室</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						<c:if test="${userRank=='TZ' }">
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle" >查询部门:</label>
						</td>
						<td class="searchCountArea">
						<select name="s_unitcode" class="searchCondContent">
								<c:forEach var="row" items="${unitList }">
									<option value="${row.unitcode}"
										<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
										<c:out value="${row.unitname}" />
									</option>
								</c:forEach>
						</select>
						</td>
						</c:if>
						
						</tr>
						
						</c:if>
						<tr id="gaoji_more3" style="display: none;">
						<td></td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">办理人员:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" style="width: 142px;" type="text" name="s_bizusernames" value="${s_bizusernames }" />
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">发文单位:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_income_Dept_Name" value="${s_income_Dept_Name }" />
						</td>
						</tr>
						
                 </table>
                 </div>
             </s:form>
             </div>
          </fieldset>
               <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
		<ec:table action="dispatchdoc/incomeDoc!list.do" items="incomeDocList" var="incomeDoc"
				imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="none"
				title='序号'
				sortable="false" style="width:70px;text-align:centit;">
                  ${ROWCOUNT}
				</ec:column>
				<ec:column property="acceptArchiveNo"  title="收文号" style="text-align:center" > 
					<c:if test="${incomeDoc.warntype eq '1'}">
	
						<span class="icon"
							style="background:url(${pageContext.request.contextPath}/images/red.gif) "
							title="超时"></span>
					</c:if>
					<c:if test="${incomeDoc.warntype ne '1'}">
				
						&nbsp;</c:if> ${incomeDoc.acceptArchiveNo }
				</ec:column>
				<ec:column property="incomeDocTitle" title="文件标题" style="text-align:left" >
				
				<input type="hidden" value="${incomeDoc.incomeDocTitle}"/> 
					<c:if test="${incomeDoc.warntype eq '1'}">
	
						<span class="icon"
							style="background:url(${pageContext.request.contextPath}/images/red.gif) "
							title="超时"></span>
					</c:if>
					<c:if test="${incomeDoc.warntype ne '1'}">
				
						&nbsp;</c:if> 
					<c:choose>
	               		<c:when test="${'I' eq incomeDoc.overdueType }">
	               			<span class="icon-email icon-jjOverdue" title="即将超期"></span>
	               		</c:when>
	               		<c:when test="${'O' eq incomeDoc.overdueType }">
	               			<span class="icon-email icon-overdue" title="已超期"></span>
	               		</c:when>
               		  </c:choose>
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDocTitle) > 14}">
						 <a href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)"><c:out
								value="${fn:substring(incomeDoc.incomeDocTitle, 0, 14)}..." /></a>
						</c:when>
						<c:otherwise>
						 <a  href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)"><c:out value="${incomeDoc.incomeDocTitle}" /></a>
						</c:otherwise>
					</c:choose>
				
				</ec:column>
				<ec:column property="incomeDeptName" title="来文单位" style="text-align:center">
					<input type="hidden" value="${incomeDoc.incomeDeptName}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDeptName) > 8}">
							<c:out
								value="${fn:substring(incomeDoc.incomeDeptName, 0,8)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.incomeDeptName}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				
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
				<%-- <ec:column property="incomeDocNo" title="发文字号" style="text-align:center">
					<input type="hidden" value="${incomeDoc.incomeDocNo}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDocNo) >8}">
							<c:out
								value="${fn:substring(incomeDoc.incomeDocNo, 0,8)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.incomeDocNo}" />
						</c:otherwise>
					</c:choose>
				</ec:column> --%>
				<ec:column property="incomeDate" title="收文时间" style="text-align:center" format="yyyy-MM-dd" cell="date" />				
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
						<c:when test="${fn:length(incomeDoc.nodename) >8}">
							<c:out
								value="${fn:substring(incomeDoc.nodename, 0,8)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.nodename}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="bizusernames" title="办理人员" style="text-align:center" sortable="true" width="12%">
					<input type="hidden" value="${incomeDoc.bizusernames}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.bizusernames) >12}">
							<c:out
								value="${fn:substring(incomeDoc.bizusernames, 0,12)}..." />
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
					     <c:if test="${incomeDoc.bizstate ne 'F' }">
						     <a class="check_email" href="javascript:void(0);" onclick="viewDetail('${incomeDoc.djId}',0)">查看</a>
						     <c:if test="${incomeDoc.username eq '阴明月' }">
						      <a class="delete_email" onclick="return confirm('是否确定删除？')" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!deleteDoc.do?djId=${incomeDoc.djId}">删除</a>
						      </c:if>
						 </c:if>
						 <c:if test="${incomeDoc.bizstate eq 'F' }">
						     <a class="bianji" href="${contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?djId=${incomeDoc.djId}">编辑</a>
					
						     <a class="delete_email" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!delete.do?djId=${incomeDoc.djId}">删除</a>
					     </c:if>
					    <%-- <c:if test="${cp:CHECKUSEROPTPOWER('SWGLSWCK', 'adminList') and incomeDoc.bizstate ne 'F'}">
						      <a class="guanli" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listFlowInstNodes.do?flowInstId=${incomeDoc.flowInstId}">管理</a>
					    </c:if>  --%>
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
	 
	 function doRadioChick(){
		    $("#incomedoclistv1form").attr("action","incomeDoc!list.do");
			$("#incomedoclistv1form").submit();
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
			$("#incomedoclistv1form").attr("action","incomeDoc!list.do");
			$("#incomedoclistv1form").submit();
		 }
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
			if(gj()){
				showgaoji();
			}
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
			$("#gaoji_more2").show();
			$("#gaoji_more3").show();
			$("#gaoji").hide();
		}
		function toshouqi(){
			$("#shouqi").hide();
			$("#gaoji_more").hide();
			$("#gaoji_more2").hide();
			$("#gaoji_more3").hide();
			$("#gaoji").show();
		}
		function gj(){
			var t=false;
			if($("input[name=s_acceptArchiveNo]").val().trim()!=""&&$("input[name=s_acceptArchiveNo]").val()!=null){
				t=true;
			}
			if($("input[name=s_income_Doc_No]").val().trim()!=""&&$("input[name=s_income_Doc_No]").val()!=null){
				t=true;
			}
			if($("input[name=s_income_Dept_Name]").val().trim()!=""&&$("input[name=s_income_Dept_Name]").val()!=null){
				t=true;
			}
			var query='<%=request.getAttribute("userRank")%>';
			if(query!=null&&query!=""){
				if('checked'==$('#queryUnderUnit').attr("checked")){
					t=true;
				}
			}
			if($("input[name=s_bizusernames]").val().trim()!=""&&$("input[name=s_bizusernames]").val()!=null){
				t=true;
			}
			return t;
		}
	</script>
</html>
