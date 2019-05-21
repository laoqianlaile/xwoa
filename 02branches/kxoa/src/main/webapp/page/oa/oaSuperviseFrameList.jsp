<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />



<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaSupervise.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaSupervise" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
  					<s:hidden name="show_type" value="%{show_type}"/>
					<tr >
						<td class="addTd">督办流水号:</td>
						<td><s:textfield name="s_djId" value="%{#parameters['s_djId']}"/> </td>
					
						<td class="addTd">督办业务流水号:</td>
						<td><s:textfield name="s_supDjid" value="%{#parameters['s_supDjid']}"/> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaSupervise.title" />:</td>
						<td><s:textfield name="s_title" value="%{#parameters['s_title']}"/> </td>
				
						<td class="addTd"><s:text name="oaSupervise.superviseType" />:</td>
						<td>
	
						<select name="s_superviseType" id="s_superviseType" >
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_superviseType')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${param.s_superviseType==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						</td>
					</tr>	

			
					<tr >
						<td class="addTd"><s:text name="oaSupervise.creatertime" />:</td>
						  <td >		
						   <input type="text" class="Wdate" 
						value="${param['s_begTime']}"name="s_begTime"  id="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">						    	
				    	至
				    	 <input type="text" class="Wdate" 
						value="${param['s_endTime']}"name="s_endTime"  id="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">	
						</td>
						<td class="addTd"><s:text name="oaSupervise.state" />:</td>
						<td>
						<select name="s_state" id="s_state" >
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_state')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${param.s_state==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						 </td>
					</tr>	

					<tr class="searchButton">
						<td colspan="5">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
						<c:if test="${show_type eq 'F'}">
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</c:if></td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaSupervise!list.do" items="objList" var="oaSupervise"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
	
			<ec:row>
	        	
				
				<ec:column property="djId" title="督办流水号" style="text-align:center" >
						<c:if test="${oaSupervise.warnType eq '0'}">
			
					<i class="icon icon-blue icon-clock" title="提醒"></i>${oaSupervise.djId }</c:if>
					<c:if test="${oaSupervise.warnType eq '1'}">
			
					<i class="icon icon-red icon-alert" title="超时"></i>${oaSupervise.djId }</c:if>
					
				</ec:column>

				
				<ec:column property="supDjid" title="督办业务流水号" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaSupervise.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="tsuperviseType"><s:text name='oaSupervise.superviseType' /></c:set>	
				<ec:column property="superviseType" title="${tsuperviseType}" style="text-align:center" >
			      ${cp:MAPVALUE("oa_superviseType",oaSupervise.superviseType) }
				</ec:column>

				<c:set var="tcreatertime"><s:text name='oaSupervise.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value='${oaSupervise.creatertime}' pattern='yyyy-MM-dd HH:mm' />			
				
				</ec:column>

				<c:set var="tcreater"><s:text name='oaSupervise.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				${cp:MAPVALUE("usercode",oaSupervise.creater) }
				</ec:column>

				<%-- <c:set var="tsuperviseDepno"><s:text name='oaSupervise.superviseDepno' /></c:set>	
				<ec:column property="superviseDepno" title="${tsuperviseDepno}" style="text-align:center" >
			    	${cp:MAPVALUE("unitcode",oaSupervise.superviseDepno) }
				</ec:column> --%>

				<c:set var="tstate"><s:text name='oaSupervise.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" >
				${cp:MAPVALUE("oa_state",oaSupervise.state) }
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSupervise!generalOptView.do?djId=${oaSupervise.djId}&nodeInstId=0&viewtype=T'><s:text name="opt.btn.view" /></a>
					<c:if test="${oaSupervise.biztype eq 'F' }">
					<a href='oa/oaSupervise!edit.do?djId=${oaSupervise.djId}'><s:text name="opt.btn.edit" /></a>
				
					<a href='oa/oaSupervise!delete.do?djId=${oaSupervise.djId}' 
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
