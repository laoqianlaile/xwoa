<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	<!-- 新样式文件 -->
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	
		<title>
			待办事项
		</title>
	</head>

	<body>
		<fieldset class="search">
			<legend class="headTitle">
					<c:choose>			
					<c:when  test="${ empty s_flowCode and empty s_itemtype   }">待办事项</c:when>
				<c:otherwise>
				${cp:MAPVALUE("oa_ITEM_TYPE",s_itemtype)}待办
				</c:otherwise>
				</c:choose>				
			</legend>
			<div class="searchDiv">
			<s:form id="vuserTask_form" action="vuserTaskListOA" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			 <s:hidden name="s_flowCode" value="%{s_flowCode}"/>
			 <input type="hidden" name="ec_crd" value="${param['ec_crd'] }">
			 <input type="hidden" name="s_itemtype" value="${s_itemtype}"/>
			 <div class="searchArea">
				<table style="width: auto;">
				<tr>				
					<td class="searchTitleArea" >
					<c:choose>
						<c:when test="${'000857' eq s_flowCode  }">
							<label class="searchCondTitle">会议主题:</label>
						</c:when>
						<c:otherwise>
							<label class="searchCondTitle">办件名称:</label>
						</c:otherwise>
					</c:choose>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_flowOptName" value="${s_flowOptName }" /> 
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td>
					<label class="searchCondTitle">更新时间:</label>
					</td>
					<td class="searchCountArea" colspan="4">
					<input type="text" class="Wdate searchCondContent"  id="s_begTime" <c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
	                            <c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if> name="s_begTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     <label class="searchCondTitle">至</label>
						        <input type="text" class="Wdate searchCondContent"  id="s_endTime" <c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
	                            <c:if test="${empty s_endTime  }">value="${param['s_endTime'] }"</c:if> name="s_endTime" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">&nbsp;&nbsp;
	                            <input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
								<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">	
	                        </td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜  索</div></td>
					</tr>
				<tr id="gaoji_more" style="display: none;">
				<td class="searchTitleArea" >
					<label class="searchCondTitle">业务类别:</label>
					</td>
					<td class="searchCountArea">
					<select
						id="s_itemTypeName" name="s_itemTypeName" class="searchCondContent"
						style="width: 158px;">
							<option value="">全部</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode == s_itemTypeName}"> selected="selected"</c:if>
									>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> 
					</td>
			<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>					
					<td class="searchTitleArea">
						<label class="searchCondTitle">缓急程度:</label></td>
						<td class="searchCountArea" >
						<select id="criticalLevel" name="s_criticalLevel" style="width: 158px;" class="searchCondContent">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}">
								<option value="${row.key}" ${(s_criticalLevel eq row.key or (empty s_criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="fenggexian"  style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea" >
					<label class="searchCondTitle">当前步骤:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_nodeName" id="s_nodeName" value="${s_nodeName }" /> 
					</td>
				</tr>
				
			</table>
			</div>	
			</s:form>
			</div>
		</fieldset>

		<ec:table action="dispatchdoc/vuserTaskListOA!list.do" items="objList" var="vuserTaskListOA"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			
			<ec:row>
			<c:if test="${!('000857' eq s_flowCode or '000858' eq s_flowCode)}">
			
				<ec:column property="criticalLevel" title="缓急程度" style="text-align:center" width="6%">	
				
				<c:choose>
				 <c:when test="${vuserTaskListOA.criticalLevel eq '0'}">
					&nbsp;
				 </c:when>
				  <c:when test="${fn:contains(vuserTaskListOA.criticalLevel, '1')}">
					<span style='color: green;'>【<c:if test="${fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_levelsq",vuserTaskListOA.criticalLevel)}</c:if><c:if test="${!fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_level",vuserTaskListOA.criticalLevel)}</c:if>】</span>
				 </c:when>
				 <c:when  test="${fn:contains(vuserTaskListOA.criticalLevel, '2')}">
					<span style='color: #00ADEF;'>【<c:if test="${fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_levelsq",vuserTaskListOA.criticalLevel)}</c:if><c:if test="${!fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_level",vuserTaskListOA.criticalLevel)}</c:if>】</span>
				 </c:when>
				 <c:when test="${fn:contains(vuserTaskListOA.criticalLevel, '3')}">
					<span style='color: #FABC09;'>【<c:if test="${fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_levelsq",vuserTaskListOA.criticalLevel)}</c:if><c:if test="${!fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_level",vuserTaskListOA.criticalLevel)}</c:if>】</span>
				 </c:when>
				 <c:when test="${fn:contains(vuserTaskListOA.criticalLevel, '4')}">
					<span style='color: #F1521B;'>【<c:if test="${fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_levelsq",vuserTaskListOA.criticalLevel)}</c:if><c:if test="${!fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_level",vuserTaskListOA.criticalLevel)}</c:if>】</span>
				 </c:when>
				 <c:when test="${fn:contains(vuserTaskListOA.criticalLevel, '5')}">
					<span style='color: red;'>【<c:if test="${fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_levelsq",vuserTaskListOA.criticalLevel)}</c:if><c:if test="${!fn:contains(vuserTaskListOA.criticalLevel, '0')}">${cp:MAPVALUE("critical_level",vuserTaskListOA.criticalLevel)}</c:if>】</span>
				 </c:when>				 
				 <c:otherwise>
				 &nbsp;
				 </c:otherwise>
				 </c:choose> 
				</ec:column>
			</c:if>
			
			  <c:choose>
				<c:when test="${'000857' eq s_flowCode  }">
				
	               <ec:column property="flowOptName" title="会议主题" style="text-align:left">	
	              
	                <c:if test="${vuserTaskListOA.isSuprised eq '1'}">
					<i class="icon  icon-blue icon-edit" style="margin-left: 15px;"title="已督办" ></i>
					</c:if>
				  <c:if test="${vuserTaskListOA.warnType eq '0'}">
					<i class="icon  icon-blue icon-clock" style="margin-left: 15px;"title="提醒" ></i>
					</c:if>
					<c:if test="${vuserTaskListOA.warnType eq '1'}">
				   <span class="icon icon-red  icon-alert"  style="margin-left: 15px;" title="超时"></span>
					</c:if>
					<c:if test="${vuserTaskListOA.warnType ne '1' and vuserTaskListOA.warnType ne '0' and vuserTaskListOA.isSuprised ne '1'}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
	               	<c:choose>
						<c:when test="${fn:length(vuserTaskListOA.transaffairname) > 30}">
							<c:out value="${fn:substring(vuserTaskListOA.transaffairname, 0, 30)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vuserTaskListOA.transaffairname}" />
						</c:otherwise>
					</c:choose>
	               </ec:column>	

				</c:when>
				
				<c:otherwise>
				
               <ec:column property="flowOptName" title="办件名称" style="text-align:left">	
               <a  href="<c:url value='${vuserTaskListOA.nodeOptUrl}' />&s_itemtype=${s_itemtype}" />
               <input type="hidden" value="${vuserTaskListOA.flowOptName}[${vuserTaskListOA.powername}]"/>   
                   <c:if test="${vuserTaskListOA.flowSupervise eq 'T'}">
				 <span class="icon"
						style="background:url(${pageContext.request.contextPath}/themes/css/images/icon/opa-icons-blue16.png);"
						title="督办件"></span>
					</c:if>
               	
               	<c:choose>
					<c:when test="${fn:length(vuserTaskListOA.flowOptName) > 30}">
						<c:out value="${fn:substring(vuserTaskListOA.flowOptName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vuserTaskListOA.flowOptName}" />
					</c:otherwise>
				</c:choose>
				
				  	<c:if test="${vuserTaskListOA.newwarn eq '1'}">
					<span class="icon"
						style="background:url(${pageContext.request.contextPath}/images/red.gif);"
						title="超时"></span>
				</c:if>
               	<c:choose>
               		<c:when test="${'I' eq vuserTaskListOA.overdueType }">
               			<span class="icon-email icon-jjOverdue" title="即将超期"></span>
               		</c:when>
               		<c:when test="${'O' eq vuserTaskListOA.overdueType }">
               			<span class="icon-email icon-overdue" title="已超期"></span>
               		</c:when>
               	</c:choose>
               	
               </ec:column>	
				</c:otherwise>
			</c:choose>


			<ec:column property="nodeCreateTime" title="更新时间" style="text-align:center" format="yyyy-MM-dd" cell="date">
				<fmt:formatDate value="${vuserTaskListOA.nodeCreateTime}"
					pattern="yyyy-MM-dd" />
				</ec:column>
				<ec:column property="createuser" title="登记人" style="text-align:center" width="8%">	
				${cp:MAPVALUE("usercode",vuserTaskListOA.createuser) }
				</ec:column>
				
				<ec:column property="nodeName" title="当前步骤" style="text-align:center">	
				${vuserTaskListOA.nodeName}<c:if test="${vuserTaskListOA.grantor ne null }">
					 (<%-- ${cp:MAPVALUE("usercode",vuserTaskListOA.grantor) } --%>委托)
				</c:if>
				</ec:column>
				
				<ec:column property="readstate" title="状态" style="text-align:center" width="6%">
				<c:choose>
					<c:when test="${vuserTaskListOA.readstate eq '已读'}">
						<!-- <span class="read"/> --><span style="color: green;">
					</c:when>
					<c:when test="${vuserTaskListOA.readstate eq '未读'}">
						<!-- <span class="notread"/> --><span style="color: orange;">
					</c:when>
					<c:when test="${vuserTaskListOA.readstate eq '暂存'}">
						<!-- <span class="zancun"/> --><span style="color: blue;">
					</c:when>
					<c:otherwise>
						<span style="color: #20DC3C;">
					</c:otherwise>
				</c:choose>
				【${cp:MAPVALUE("readstate",vuserTaskListOA.readstate)}】</span>
				</ec:column>
		    
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center" width="8%">
					<a class="banli" href="<c:url value='${vuserTaskListOA.nodeOptUrl}' />&s_itemtype=${s_itemtype}" >办理</a>
                 
                 <!--   F--不显示流程图 -->
                <c:if test='${cp:MAPVALUE("SYSPARAM","isFlowShow") ne "F"}'>
<a class="liuchengtu" href="#" onclick="showView('流程图','${ctx}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${vuserTaskListOA.flowInstId}')">流程图</a>
					<%-- <a  class="liuchengtu" href="#" onclick="viewFlow('${vuserTaskListOA.flowInstId}');">查看流程图</a> --%>
				</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	function showView(title,link){//流程图窗口内弹出，统一风格。
		DialogUtil.open(title,link,1200,700);
	}

	function viewFlow(flowInstId) {
		window.open("<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
	}
	
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
	 $(document).ready(function() {
         openLoadIng(false);
		});
	function recipient(){
		
	}
	function sub(){
		if(checkFrom()==true){
			$("#vuserTask_form").attr("action","vuserTaskListOA!list.do");
			$("#vuserTask_form").submit();
		}
	}
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
	function gj(){
		var t=false;
		if($("#s_itemTypeName").val().trim()!=""&&$("#s_itemTypeName").val()!=null){
			t=true;
		}
		if($("#criticalLevel").val().trim()!=""&&$("#criticalLevel").val()!=null){
			t=true;
		}
		if($("#s_nodeName").val().trim()!=""&&$("#s_nodeName").val()!=null){
			t=true;
		}
		return t;
	}
	 $(function(){
			if(gj()){
				showgaoji();
			}
			//让滚动条至于顶部
			$(top.window.document).find("#firstPage").scrollTop(0);
		});
	</script>
</html>
