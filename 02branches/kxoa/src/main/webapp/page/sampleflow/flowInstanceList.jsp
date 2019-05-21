<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	<meta name="decorator" content='${LAYOUT}'/>
		<title>
			流程查询
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"  type="text/javascript" />
</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 流程查询
			</legend>
			
			<s:form action="sampleFlowManager" namespace="/sampleflow">
				<table cellpadding="0" cellspacing="0" >
					<tr >
						<td class="addTd">业务名称:</td>
						<td>
						<s:textfield name="s_flowOptName" value="%{#parameters['s_flowOptName']}"/>
						 </td>
						<td class="addTd">业务号:</td>
						<td>
							<s:textfield name="s_flowOptTag" value="%{#parameters['s_flowOptTag']}"/>
						</td>
					</tr>
					<tr>
						<td class="addTd" >创建时间:</td>
						<td>
						<input type="text" class="Wdate" 
						 id="s_createtime"
						value="${param['s_createtime']}" name="s_createtime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker id="s_createtime" --%>
<%-- 							name="s_createtime" value="%{#parameters['s_createtime']}"  --%>
<%-- 							style="width:162px" --%>
<%-- 							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
						
						 </td>
						<td class="addTd">完成时间:</td>
						<td>
							<input type="text" class="Wdate" 
						 id="lastUpdateTime"
						value="${param['s_lastUpdateTime']}" name="s_lastUpdateTime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker id="lastUpdateTime" style="width:162px" --%>
<%-- 							name="s_lastUpdateTime" value="%{#parameters['s_lastUpdateTime']}" --%>
<%-- 							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
						
						 </td>
					</tr>
					<tr >
						<td class="addTd">流程状态:</td>
						<td width="300">
							<select name="s_inststate">
							 	<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('WFInstType')}">
									<option value="${row.key}" <c:if test="${param.s_inststate==row.key}">selected="selected"</c:if>>
									
									<c:out value="${row.value}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr class="searchButton">
						<td colspan="4">
							<s:submit method="list"  value="查询" cssClass="btn"/>
							<input type="button" class="btn" value="重置" onclick="resetForm();"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="sampleFlowManager!list.do" items="objList" var="wfFlowInstance"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<%--<ec:column property="flowInstId" title="流程实例编号" style="text-align:center" />
				<ec:column property="flowName" title="流程名称" style="text-align:left"/>
				 <ec:column property="version" title="流程版本" style="text-align:center"/> --%>
				
				<ec:column property="flowOptName" title="业务名称" style="text-align:left">
				 <input type="hidden" value="${wfFlowInstance.flowOptName}"/>      
			          <a onmouseover="this.style.cursor='hand'" onclick="doShow('${wfFlowInstance.flowOptTag}','${cp:MAPVALUE('optType',fn:substringBefore(wfFlowInstance.flowOptTag, '0'))}');" ><c:choose>
						<c:when test="${fn:length(wfFlowInstance.flowOptName) > 18}">
							<c:out
								value="${fn:substring(wfFlowInstance.flowOptName, 0, 18)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${wfFlowInstance.flowOptName}" />
						</c:otherwise>
					</c:choose>		</a>		
				</ec:column>
				<ec:column property="flowOptTag" title="业务号" style="text-align:center" />
			<%-- 	<ec:column property="createTime" title="创建时间" style="text-align:center" >
					<fmt:formatDate value="${wfFlowInstance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</ec:column>
				<ec:column property="instState" title="办件状态" style="text-align:center">
				${cp:MAPVALUE("WFInstType",wfFlowInstance.instState)}
				</ec:column> --%>
				<%-- <ec:column property="timeLimitStr" title="剩余时间" style="text-align:center" /> --%>
				<%--
				<ec:column property="subFlow" title="是否是子流程" style="text-align:center" />
				--%>
				<ec:column property="unitCode" title="主办处室" style="text-align:center">
					${cp:MAPVALUE("unitcode2JC",wfFlowInstance.unitCode)}
				</ec:column>
				<ec:column property="instState" title="办件状态" style="text-align:center">
				${cp:MAPVALUE("WFInstType",wfFlowInstance.instState)}
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<a href="javascript:showDivAlert('${wfFlowInstance.flowInstId}')">更改机构</a>
					<a href='sampleFlowManager!listFlowInstNodes.do?flowInstId=${wfFlowInstance.flowInstId}'>流程管理</a>
					<a href='sampleFlowManager!viewxml.do?flowInstId=${wfFlowInstance.flowInstId}'>查看流程图</a>
				</ec:column>

			</ec:row>
		</ec:table>
		<div id="alert" class="alert" style="width: 300px;height: 100px;">
				<s:form name="nodeForm" method="post" onsubmit="return checkUnit();" action="sampleFlowManager!modifyFlowInst.do" namespace="/sampleflow" style="margin-top:0;margin-bottom:5">
				<input type="hidden" id="flowInstId" name="flowInstId" value="">
					<h4><span>机构选择</span><span id="close" class="close"  onclick="closeAlert('alert');" >关闭</span></h4>
					<p>
						<select name="unitCode">   
							<option value="">   
									<c:out value="-- 请选择 --"/>   
							</option>    
							<c:forEach var="row" items="${cp:LVB('unitcode')}">     
									<option value="${row.value}">   
										<c:out value="${row.label}"/>   
									</option>
							</c:forEach> 
						</select>
					&nbsp;&nbsp;<input type="submit" id="sub" value="确定" class="sub" />
					<br>
						&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span>
					</p>
				</s:form>
		</div>
	
	</body>
		<script type="text/javascript">
		function showDivAlert(val)
		{
			document.getElementById("flowInstId").value = val;
			document.getElementById("errorInfo").innerHTML="";
			setAlertStyle('alert');;
		}
		
		function checkUnit(){
			if(document.getElementById("unitCode").value.length == 0){
				document.getElementById("errorInfo").innerHTML="* 请选择机构";
				return false;
			}
			return true;
		}
		
		function resetForm(){
			 $('[name=s_flowOptName]').val('');
			 $('[name=s_flowOptTag]').val('');
			 $('[name=s_createtime]').val('');
			 $('[name=s_lastUpdateTime]').val('');
			 $('[name=s_inststate]').val('');
		 }
		function doShow(djid,menthods) {
			var url="${contextPath }/"+menthods+"!generalOptView.do?djId="+djid+"&nodeInstId=0&s_itemtype="+djid.substring(0, 2);
			window.location.href=url;
		}
	
	</script>
</html>
