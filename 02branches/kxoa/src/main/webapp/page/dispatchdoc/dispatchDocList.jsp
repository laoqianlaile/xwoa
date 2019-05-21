<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		</title>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend>
			<c:choose>
			<c:when test="${s_bizstate eq 'C' }">已办结拟发文</c:when>
			<c:when test="${show_type eq 'T' }">拟发文查看</c:when>			
			</c:choose>			
			</legend>
			<s:form action="dispatchDoc" namespace="/dispatchdoc" method="post">
			 <s:hidden name="show_type" value="%{show_type}"/>
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">拟发文号:</td>
						<td >
						<input type="text" name="s_dispatchDocNo" value="${s_dispatchDocNo }" />
<%-- 						<s:textfield id="s_djId" name="s_djId" value="%{#parameters['s_djId']}" /></td> --%>
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
					
						<td class="addTd">办件状态:</td>
						<td>
							<s:radio name="s_bizstate" id="s_bizstate"
							list="#{'W':'在办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}"></s:radio>
						</td>
					
						<td class="addTd">拟文日期:</td>
						<td>
							 <input type="text" class="Wdate" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">						</td>
						
						
				</tr>
				<c:if test="${userRank=='TZ' }">
					<tr id="tr_unitlist">
						<td class="addTd">查询部门：</td>
						<td><select name="s_unitcode" style="width: 195px">
								<c:forEach var="row" items="${unitList }">
									<option value="${row.unitcode}"
										<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
										<c:out value="${row.unitname}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:if>
						
					</tr>
					
					<tr class="searchButton">
				        <td colspan="5"><s:submit method="list" key="opt.btn.query" cssClass="btn" onclick="return checkFrom();" />&nbsp;
							<s:submit method="exportExcelByPo" value="导出Excel"
							cssClass="btn btnWide" />
						<c:if test="${ 'T' ne show_type }"> 
						<td>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn" />
						</td>
						</c:if>
					</tr>
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="dispatchdoc/dispatchDoc!list.do" items="dispatchDocList" var="dispatchDoc"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="dispatchDocNo" title="拟发文号" style="text-align:center" />
					<ec:column property="dispatchDocTitle" title="文件标题" style="text-align:center" >
					   <input type="hidden" value="${dispatchDoc.dispatchDocTitle}"/>      
			          <c:choose>
						<c:when test="${fn:length(dispatchDoc.dispatchDocTitle) > 20}">
							<c:out
								value="${fn:substring(dispatchDoc.dispatchDocTitle, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${dispatchDoc.dispatchDocTitle}" />
						</c:otherwise>
					</c:choose>
					</ec:column>
					<ec:column property="createDate" title="拟发文时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
			        <ec:column property="draftUserName" title="经办人" style="text-align:center" >${cp:MAPVALUE('usercode',dispatchDoc.draftUserName ) }</ec:column>
				    <ec:column property="dispatchUser" title="签发人" style="text-align:center" >${cp:MAPVALUE('usercode',dispatchDoc.dispatchUser) }</ec:column>
				    <ec:column property="mainNotifyUnit" title="主送单位" style="text-align:center" >${dispatchDoc.mainNotifyUnit}</ec:column>
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
				<ec:column property="bizusernames" title="办理人员" style="text-align:center" sortable="true">
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
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
					    <a href="${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${dispatchDoc.djId}&nodeInstId=0">查看</a>
					<c:if test="${dispatchDoc.bizstate eq 'F' }">
						<a href="${contextPath}/dispatchdoc/dispatchDoc!registerEdit.do?djId=${dispatchDoc.djId}">编辑</a>
						<a href="<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!deleteObject.do?djId=${dispatchDoc.djId}">删除</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
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
