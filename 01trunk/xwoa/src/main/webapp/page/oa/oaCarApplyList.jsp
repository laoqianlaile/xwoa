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
		<fieldset   class="search">
			<legend>
					<c:choose>
							<c:when  test="${show_type eq 'F' }">车辆申请登记</c:when>
							<c:when test="${ show_type eq 'T'}">车辆申请查看</c:when>
							<c:when test="${ empty s_biztype}">车辆申请查看</c:when>
					</c:choose>
			</legend>
			
			<s:form action="oaCarApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				  <s:hidden name="show_type" value="%{show_type}"></s:hidden>
				  <input type="hidden" name="s_cardjid" value="${s_cardjid}">
		
					<tr>
					    <td class="addTd">申请单号:</td>
						<td><s:textfield name="s_applyNo" value="%{#parameters['s_applyNo']}"/> </td>
				        <td class="addTd">申请部门:</td>
						<td><select name="s_depno">
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
						<c:if test="${ 'T' eq show_type }"> 
						<td class="addTd">申请状态:</td>				
						<td>
						<select name="s_solvestatus" id="s_solvestatus" >
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('solvestatus')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${s_solvestatus==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
								
						</td>
						</c:if>
					</tr>
					<tr class="searchButton">

						<td colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
					
							<c:if test="${ 'T' ne show_type }"> 
					
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
							</c:if>
						</td>
						
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaCarApply!list.do" items="objList" var="oaCarApply" styleClass="ectableRegions tableRegion"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			<c:if test="${show_type ne 'F' }">
			<ec:column property="state" title="状态" style="text-align:center" sortable="false" >
				<input type="hidden" name="state" value="${cp:MAPVALUE('meetState',oaCarApply.state)}"/>
				<!-- 1申请中2已调配6暂存4不同意5被抢占3已使用  -->
				<c:if test="${oaCarApply.state eq '1' }">
				<span class="icon-blue" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '2' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if> 
				<c:if test="${oaCarApply.state eq '4' }">	
				<span class="icon-gren" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '3' }">	
				<span class="icon-redd" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '5' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '6' }">	
				<span class="icon-blue" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				</ec:column>	
				</c:if>
	    <%--          <c:set var="djId">流水号</c:set>	
				<ec:column property="djId" title="${djId}" style="text-align:center" />	--%>	
				
				<ec:column property="applyNo" title="申请单号" style="text-align:center" >
				   ${ oaCarApply.applyNo }  
                </ec:column>
		
	       <%--       <ec:column property="createtime" title="登记时间" style="text-align:center" >
				<fmt:formatDate value='${oaCarApply.createtime}' pattern='yyyy-MM-dd ' />
					</ec:column> --%>
		
			<ec:column property="depno" title="申请部门" style="text-align:center" >
				               ${cp:MAPVALUE("unitcode",oaCarApply.depno)}   
                </ec:column>
                
                 <ec:column property="transaffairname" title="标题" style="text-align:center" >
				               ${oaCarApply.transaffairname}   
                </ec:column>
               <%-- <c:if test="${ 'F' ne show_type }"> 
               <ec:column property="solvestatus" title="申请状态" style="text-align:center" >
				               ${cp:MAPVALUE("solvestatus",oaCarApply.solvestatus)}
                </ec:column>  
                
                </c:if>--%>
                 <ec:column property="doBegTime" title="用车时间" style="text-align:center" sortable="false" >
				<c:if test="${oaCarApply.doBegTime ne '' or oaCarApply.doBegTime ne ''}">
				 <fmt:formatDate value='${ oaCarApply.doBegTime }' pattern='yyyy-MM-dd HH:mm' />
				 <fmt:formatDate value='${ oaCarApply.doEndTime }' pattern='yyyy-MM-dd HH:mm' />
				 </c:if>
				 <c:if test="${empty oaCarApply.doBegTime and empty oaCarApply.doBegTime }">
				 <fmt:formatDate value='${ oaCarApply.planBegTime }' pattern='yyyy-MM-dd HH:mm' />
				 <fmt:formatDate value='${ oaCarApply.planEndTime }' pattern='yyyy-MM-dd HH:mm' />
				 </c:if>
				</ec:column>
				
				<ec:column property="meetingPersionsNum" title="乘车人数" style="text-align:center" >
				               ${oaCarApply.meetingPersionsNum}   
                </ec:column>
                
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaCarApply!generalOptView.do?djId=${oaCarApply.djId}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaCarApply.biztype eq 'F' }">
					<a href='oa/oaCarApply!edit.do?djId=${oaCarApply.djId}&show_type=${show_type}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaCarApply!delete.do?djId=${oaCarApply.djId}&show_type=${show_type}' 
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
