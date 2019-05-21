<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		
			<s:text name="militarycases.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaMeetroomApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				  <s:hidden name="show_type" value="%{show_type}"></s:hidden>
            
		
					<tr>
					    <td class="addTd">流水号:</td>
						<td><s:textfield name="s_djId" /> </td>
				      
						 <td class="addTd">申请单号:</td>
						<td><s:textfield name="s_applyNo" /> </td>
						</tr>
							<tr >
						<td class="addTd">申请状态：</td>
						<td>
						<select name="s_solvestatus" id="s_solvestatus" >
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('solvestatus')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${param.solvestatus==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
								
					</td>
					
				
					<td class="addTd">会议室:</td>
					
							<td><s:textfield name="s_meetingNo" /> </td>
						
					
					</tr>	
						<tr>
						
					   <td>申请部门:</td>
						<td><select name="s_depno" style="width:153px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitlist }">
								<option value="${row.unitcode}"
									<c:if test="${parameters.s_depno[0] eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					        </select>
						</td>
						<td>登记时间:	</td>
					   <td >	
					   <input type="text" class="Wdate" name="s_begTime" 
						style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
						value='${param['s_begTime'] }'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">			
				    	至<input type="text" class="Wdate" name="s_endTime" 
						style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
						value='${param['s_endTime'] }'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">	
					</tr>
					<tr>	
						<td colspan="2" >
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
					
							<c:if test="${ 'T' ne show_type }"> 
					
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
							</c:if>
						</td>
						
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaMeetroomApply!list.do" items="objList" var="oaMeetApply"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
	             <c:set var="djId">流水号</c:set>	
				<ec:column property="djId" title="${djId}" style="text-align:center" />		
				
				<ec:column property="applyNo" title="申请单号" style="text-align:center" >
				   ${ oaMeetApply.applyNo }  
                </ec:column>
			   <ec:column property="meetingNo" title="会议室" style="text-align:center" >
			          ${ oaMeetApply.oaMeetinfo.meetingName }  
				</ec:column>
	
	             <ec:column property="createtime" title="登记时间" style="text-align:center" >
				<fmt:formatDate value='${oaMeetApply.createtime}' pattern='yyyy-MM-dd ' />
					</ec:column>
		
			<ec:column property="depno" title="申请部门" style="text-align:center" >
				                 ${cp:MAPVALUE("unitcode",oaMeetApply.depno)} 
                </ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaMeetroomApply!generalOptView.do?djId=${oaMeetApply.djId}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaMeetApply.biztype eq 'F' }">
					<a href='oa/oaMeetroomApply!edit.do?djId=${oaMeetApply.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaMeetroomApply!delete.do?djId=${oaMeetApply.djId}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
