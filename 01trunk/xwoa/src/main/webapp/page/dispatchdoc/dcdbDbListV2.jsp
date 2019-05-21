<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<title></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle">
			<s:text name="label.list.filter" />
		</legend>
	<div class="searchDiv">
		<s:form id="dcdbDblistV2_form" action="vuserTaskListOA" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
			<table style="width: auto!important;">
				<tr>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;">业务编码:</label>
				 </td>
				<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_djId" value="${s_djId}"  />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				 <label class="searchCondTitle" style="width: 65px;">业务名称:</label>
				  </td>
				<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_flowOptName" value="${s_flowOptName}"  />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;">登记时间:</label>
				 </td>
				<td class="searchCountArea">
				<input type="text" class="Wdate searchCondContent" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="dispatchdoc/vuserTaskListOA!selectDbList.do" items="selectList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		<%-- <ec:column property="null" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${sa.djId}')">
			</ec:column> --%>
			<ec:column property="djId" title="业务编码"	style="text-align:center" sortable="false" width="10%"/>
                    
			<ec:column property="flowOptName" title="业务名称"	style="text-align:center" sortable="false">	
		    <a  href="${pageContext.request.contextPath}/${cp:MAPVALUE('optType',fn:substringBefore(sa.djId, '0' ))}!generalOptView.do?djId=${sa.djId}&nodeInstId=0" >
		    	 <input type="hidden" value="${sa.flowOptName}"/>      
			          <c:choose>
						<c:when test="${fn:length(sa.flowOptName) > 18}">
							<c:out
								value="${fn:substring(sa.flowOptName, 0, 18)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${sa.flowOptName}" />
						</c:otherwise>
					</c:choose></a>			
			</ec:column>
			
			<ec:column property="nodeName" title="步骤名称"	style="text-align:center" sortable="false" width="15%">	
				     	    ${sa.nodeName}
			</ec:column>
			
			<ec:column property="createtime" title="登记时间"	style="text-align:center" sortable="false">	
				  <fmt:formatDate value="${sa.createtime}" pattern="yyyy-MM-dd" />
			</ec:column>
			
			<ec:column property="lastupdatetime" title="最后办理时间"	style="text-align:center" sortable="false">	
				  <fmt:formatDate value="${sa.lastupdatetime}" pattern="yyyy-MM-dd" />  	   
			</ec:column>
			
		<%-- 		<ec:column property="timeLimit" title="时限"	style="text-align:center" sortable="false">	
				     	    ${sa.timeLimit}
			</ec:column> --%>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<a class="dubanfaqi" href="<%=request.getContextPath()%>/oa/oaSupervise!edit.do?supDjid=${sa.djId}">发起督办</a>
				<%-- <input type="radio" name="itemId"
					onclick="setParentVal('${sa.djId}')"> --%>
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">	
function dcdb(djId){
	
}
function sub(){
	$("#dcdbDblistV2_form").attr("action","vuserTaskListOA!selectDbList.do");
	$("#dcdbDblistV2_form").submit();
}





//获取父页面对象
/* var parentDocument = window.opener.document;
//设置返回值
function setParentVal(no) {

	if (window.confirm("确认选择吗")) {		
	
		parentDocument.getElementById('supDjid').value = no;
	    parentDocument.getElementById('nodecode').value = document.getElementById('nodeCode' + no).value;
		parentDocument.getElementById('title').value = document.getElementById('flowOptName' + no).value;
		parentDocument.getElementById('superviseUsers').value = document.getElementById('userCode' + no).value;
		//parentDocument.getElementById('bjType').value = "${flag}";
		window.close();
	}
} */
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
