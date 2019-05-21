<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<%-- <sj:head locale="zh_CN" /> --%>

<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />	
<html>
	<head>
		<title>
			<s:text name="militarycases.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search" >
			<legend>
					<c:choose>
						<c:when test="${s_biztype eq 'F' }">会议申请登记</c:when>
						<c:when test="${ empty s_biztype }">会议申请查看</c:when>
					</c:choose>
			</legend>
			
			<s:form action="oaMeetApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				  <s:hidden name="show_type" value="%{show_type}"></s:hidden>
				  <s:hidden name="s_biztype" value="%{s_biztype}"></s:hidden>
            
		
					<tr>
						 <td class="addTd">申请单号:</td>
						<td><s:textfield name="s_apply_no" value="%{#parameters.s_apply_no}" /> </td>
					
						<td class="addTd">状态:</td>
						<td><select name="s_state" id="s_state" >
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('meetState')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${s_solvestatus==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr >
						<td class="addTd">会议室:</td>
						<td>
					<s:select name="s_meeting_no" list="meetings" listKey="id" listValue="name" headerKey="" headerValue="--请选择--"/>	
						</td>

					<td class="addTd">申请部门:</td>
						<td><select name="s_depno" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitlist }">
								<option value="${row.unitcode}"
									<c:if test="${parameters.s_depno[0] eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					        </select>
						</td>
					</tr>	
						<tr>
						<td class="addTd">登记时间:	</td>
					   <td >			
					   	 <input type="text" class="Wdate" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">

					</td>
					<td></td>
						<td colspan="2" >
							<s:submit method="listOwn"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
					
							<c:if test="${ 'T' ne show_type }"> 
					
							<s:submit method="built" value="申请" cssClass="btn"/>
							</c:if>
						</td>
						
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaMeetApply!listOwn.do" items="oaMeetApplyList" var="oaMeetApply" styleClass="ectableRegions tableRegion"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
	            <%--  <c:if test="${show_type ne 'F' }">	 --%>
				<ec:column property="state" title="状态" style="text-align:center" sortable="false" >
				<input type="hidden" name="state" value="${cp:MAPVALUE('meetState',oaMeetApply.state)}"/>
				<c:if test="${oaMeetApply.state eq '1' }">
				<span class="icon-blue" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '2' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '4' }">	
				<span class="icon-gren" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '3' }">	
				<span class="icon-redd" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '5' }">	
				<span class="icon-gren" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '6' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>${cp:MAPVALUE('meetState',oaMeetApply.state)}
				</ec:column>		
				<%-- </c:if> --%>
				<ec:column property="applyNo" title="申请单号" style="text-align:center" sortable="false">
				  <c:if test="${oaMeetApply.state eq '6' }">--</c:if>
				   <c:if test="${oaMeetApply.state ne '6' }">${ oaMeetApply.applyNo } </c:if> 
                </ec:column>
			   <ec:column property="meetingName" title="会议室" style="text-align:center" sortable="false">
			          ${oaMeetApply.meetingName}
				</ec:column>
			 <ec:column property="title" title="会议主题" style="text-align:center" sortable="false">
			          ${ oaMeetApply.title }  
				</ec:column>
				 <ec:column property="doBegTime" title="使用时间" style="text-align:center" sortable="false" >
				<c:if test="${ not empty oaMeetApply.doBegTime  or not empty oaMeetApply.doBegTime  }">
				 <fmt:formatDate value='${ oaMeetApply.doBegTime }' pattern='yyyy-MM-dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.doEndTime }' pattern='yyyy-MM-dd HH:mm' />
				 </c:if>
				 <c:if test="${empty oaMeetApply.doBegTime and empty oaMeetApply.doBegTime }">
				  <fmt:formatDate value='${ oaMeetApply.planBegTime }' pattern='yyyy-MM-dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.planEndTime }' pattern='yyyy-MM-dd HH:mm' />
				 </c:if>
				</ec:column>
			<ec:column property="depno" title="申请部门" style="text-align:center" sortable="false">
				          ${cp:MAPVALUE("unitcode2JC",oaMeetApply.depno)} 
                </ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!generalOptView.do?djId=${oaMeetApply.djid}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaMeetApply.biztype eq 'F' }">
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!edit.do?djId=${oaMeetApply.djid}&show_type=${show_type}'><s:text name="opt.btn.edit" /></a>
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!delete.do?djId=${oaMeetApply.djid}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
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
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
