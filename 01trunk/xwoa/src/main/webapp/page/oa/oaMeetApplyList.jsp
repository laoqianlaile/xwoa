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
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
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
						<td><s:textfield name="s_applyNo" value="%{#parameters.s_applyNo}" /> </td>
					
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
					
					<tr>
						<td class="addTd">会议室:</td>
						<td>
					<s:select name="s_meetingNo" list="meetings" listKey="id" listValue="name" headerKey="" headerValue="--请选择--"/>	
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
							<s:submit method="list"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
					
							<c:if test="${ 'T' ne show_type }"> 
					
							<s:submit method="built" value="申请" cssClass="btn"/>
							</c:if>
						</td>
						
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaMeetApply!list.do" items="objList" var="oaMeetApply" styleClass="ectableRegions tableRegion"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" showStatusBar="false" showTitle="false">
			<ec:row>
	          <%--    <c:if test="${show_type ne 'F' }">	 --%><!--meetState：1申请中2已安排3已使用4不同意5被抢占6暂存7取消  -->
				<ec:column property="state" title="状态" style="text-align:left" sortable="false" >
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
				</c:if>
				<c:if test="${oaMeetApply.state eq '7' }">	
				<span class="icon-white" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				${cp:MAPVALUE('meetState',oaMeetApply.state)}
				</ec:column>		
				<%-- </c:if> --%>
					 <ec:column property="doBegTime" title="使用时间" style="text-align:center" sortable="false" >
				<c:if test="${ not empty oaMeetApply.doBegTime  or not empty oaMeetApply.doBegTime  }">
				 <fmt:formatDate value='${ oaMeetApply.doBegTime }' pattern='MM月dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.doEndTime }' pattern='MM月dd HH:mm' />
				 </c:if>
				 <c:if test="${empty oaMeetApply.doBegTime and empty oaMeetApply.doBegTime }">
				 <fmt:formatDate value='${ oaMeetApply.planBegTime }' pattern='MM月dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.planEndTime }' pattern='MM月dd HH:mm' />
				 </c:if>
				</ec:column>
				<ec:column property="meetingName" title="会议室" style="text-align:center" sortable="false">
			          ${ oaMeetApply.oaMeetinfo.meetingName }  
				</ec:column>
			 <ec:column property="title" title="会议主题" style="text-align:center" sortable="false">
			   <input type="hidden" value="${oaMeetApply.title}"/>      
			          <c:choose>
						<c:when test="${fn:length(oaMeetApply.title) > 12}">
							<c:out
								value="${fn:substring(oaMeetApply.title, 0, 12)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaMeetApply.title}" />
						</c:otherwise>
					</c:choose>
			 </ec:column>
			 <ec:column property="attendingLeaderNames" title="参会领导" style="text-align:center" sortable="false">
			   	<input type="hidden" value="${oaMeetApply.attendingLeaderNames}"/>
				<c:choose>
					<c:when test="${fn:length(oaMeetApply.attendingLeaderNames) >12}">
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 0,12)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaMeetApply.attendingLeaderNames}" />
					</c:otherwise>
				</c:choose>
			   <%--        <c:choose>
					<c:when test="${fn:length(oaMeetApply.attendingLeaderNames) > 90}">
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 0, 30)}" /><br><c:out value="${fn:substring(oaMeetApply.attendingLeaderNames,30,60)}" /><br>
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 60, 90)}" /><br><c:out value="${fn:substring(oaMeetApply.attendingLeaderNames,90,fn:length(oaMeetApply.attendingLeaderNames))}" />
					</c:when>
					<c:when test="${fn:length(oaMeetApply.attendingLeaderNames) > 60 and fn:length(oaMeetApply.attendingLeaderNames)<=90}">
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 0, 30)}" /><br><c:out value="${fn:substring(oaMeetApply.attendingLeaderNames,30,60)}" /><br>
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 60, 90)}" />
					</c:when>
					<c:when test="${fn:length(oaMeetApply.attendingLeaderNames) > 30 and fn:length(oaMeetApply.attendingLeaderNames)<=60}">
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 0, 30)}" /><br><c:out value="${fn:substring(oaMeetApply.attendingLeaderNames,30,60)}" />
					</c:when>
					<c:when test="${fn:length(oaMeetApply.attendingLeaderNames) > 20 and fn:length(oaMeetApply.attendingLeaderNames)<=40}">
						<c:out value="${fn:substring(oaMeetApply.attendingLeaderNames, 0, 20)}" /><br><c:out value="${fn:substring(oaMeetApply.attendingLeaderNames,20,fn:length(oaMeetApply.attendingLeaderNames))}" />
					</c:when>
					<c:otherwise>
						<c:out value="${oaMeetApply.attendingLeaderNames}" />
					</c:otherwise>
				</c:choose> --%>
			 </ec:column>	
			   
			
			<ec:column property="depno" title="申请部门" style="text-align:center" sortable="false">
				                 ${cp:MAPVALUE("unitcode2JC",oaMeetApply.depno)} 
                </ec:column>
              <ec:column property="applyNo" title="申请单号" style="text-align:center" sortable="false">
				   <c:if test="${oaMeetApply.state eq '6' }">--</c:if>
				   <c:if test="${oaMeetApply.state ne '6' }">${ oaMeetApply.applyNo } </c:if> 
                </ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!generalOptView.do?djId=${oaMeetApply.djId}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaMeetApply.biztype eq 'F' }">
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!edit.do?djId=${oaMeetApply.djId}&show_type=${show_type}'><s:text name="opt.btn.edit" /></a>
					<a href='${pageContext.request.contextPath}/oa/oaMeetApply!delete.do?djId=${oaMeetApply.djId}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</c:if>
				
                <!-- 本人		发起流程的会议申请取消     --><!--meetState：1申请中2已安排3已使用4不同意5被抢占6暂存  7取消-->
                <c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaMeetApply.creater}">
				<c:if test="${oaMeetApply.biztype ne 'F' and (oaMeetApply.state eq 1 or oaMeetApply.state eq 2)}">
				<a href='${pageContext.request.contextPath}/oa/oaMeetApply!cancleApply.do?djId=${oaMeetApply.djId}' 
							onclick='return confirm("确定取消该会议申请记录吗?");'><s:text name="取消预定" /></a>
				</c:if>
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
