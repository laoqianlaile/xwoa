<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		</title>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend ><c:choose><c:when test="${show_type eq 'T' or s_applyItemType eq 'GW' }">收文查看</c:when>
			<c:when test="${empty show_type  or empty  s_applyItemType}">个人收文</c:when></c:choose></legend>
			<s:form action="incomeDoc" namespace="/dispatchdoc" method="post" style="margin-top:0;margin-bottom:5"> 
			 	<s:hidden name="show_type" value="%{show_type}"/>
			 	<input type="hidden" id="s_bizstate" name="s_bizstate" value="${param['s_bizstate']}" />
			 	<input type="hidden" name="s_applyItemType" value="${s_applyItemType}"/>
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
					    <td class="addTd">收文号:</td>
						<td >
						<input type="text" name="s_acceptArchiveNo" value="${s_acceptArchiveNo }" />
<%-- 						<s:textfield id="s_djId" name="s_djId" value="" style="width:180px" /></td> --%>
						<td class="addTd">文件标题:</td>
						<td >
						<input type="text" name="s_income_Doc_Title" value="${s_income_Doc_Title }" />
<%-- 						<s:textfield id="s_transaffairname" name="s_transaffairname" value="" style="width:180px"/></td> --%>
							</tr>
					<tr>
					
						  <td class="addTd">发文字号:</td>
						<td >
						<input type="text" name="s_income_Doc_No" value="${s_income_Doc_No }" />
						
							<!-- 						根据行政级别显示 --> <c:if test="${not empty userRank}">
							<!-- 						<td class="addTd"> -->

							

							
							<!-- 					</td> -->
						</c:if></td>
					
					
					 
					<td class="addTd">发文单位:</td>
					<td>
                     <input type="text" name="s_income_Dept_Name" value="${s_income_Dept_Name }" />
					</tr>
					<tr>
					   
					   <td class="addTd">收文时间:</td>
						<td>
						<input type="text" class="Wdate" id="s_begincomeDate"
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
					   
					   
				
						<td class="addTd">组织范围:</td>
						<td>
							<input type="radio" name="s_bmsw" value="" <c:if test="${empty s_bmsw}">checked="checked"</c:if> />厅
							<input type="radio" name="s_bmsw" value="T" <c:if test="${not empty s_bmsw }">checked="checked"</c:if> />处室
						
						</td>
					</tr>
				
						
					
					
					<tr>
					<tr class="searchButton">
						<td colspan="4"><s:submit method="listV2" key="opt.btn.query" cssClass="btn" onclick="return checkFrom();" />&nbsp;
					<s:submit method="exportExcelByPoV2" value="导出Excel"
							cssClass="btn btnWide" /></td>
					</tr>	
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="dispatchdoc/incomeDoc!listV2.do" items="incomeDocList" var="incomeDoc"
				imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="acceptArchiveNo"  title="收文号" style="text-align:center" />
				<ec:column property="incomeDocTitle" title="文件标题" style="text-align:center">
				<input type="hidden" value="${incomeDoc.incomeDocTitle}"/> 
					<c:choose>
						<c:when test="${fn:length(incomeDoc.incomeDocTitle) > 14}">
							<c:out
								value="${fn:substring(incomeDoc.incomeDocTitle, 0, 14)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${incomeDoc.incomeDocTitle}" />
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
						 <a href="${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${incomeDoc.djId}&nodeInstId=0">查看</a>
						</c:if>
						<c:if test="${incomeDoc.bizstate eq 'F' }">
						<a href="${contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?djId=${incomeDoc.djId}">编辑</a>
					
						<a href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!delete.do?djId=${incomeDoc.djId}">删除</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
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
	</script>
</html>
