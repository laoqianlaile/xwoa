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
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
			<s:text name="oaSupervise.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend class="headTitle">
				 <c:choose>
				 <c:when test="${! empty s_biztype }">督办发起</c:when>
				 <c:when test="${show_type eq 'T' }">督办查看</c:when>
				 <c:when test="${empty s_biztype and empty show_type }">督办催办发起</c:when>
				 </c:choose>
			</legend>
			<div class="searchDiv">
			<s:form id="oasuperviselistv2form" method="post" action="oaSupervise" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<div class="searchArea">
  					<s:hidden name="show_type" value="%{show_type}"/>
				<table style="width: auto;">
					<tr >
					
					               <td class="searchTitleArea">
						<label class="searchCondTitle"><s:text name="oaSupervise.superviseType" />:</label>
					</td>
				<td class="searchCountArea">
						<select name="s_supervise_Type" id="s_supervise_Type" class="searchCondContent" style="width: 120px;">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_ITEM_TYPE')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${param.s_supervise_Type==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						
						<td class="searchTitleArea">
						<label class="searchCondTitle">督办业务流水号:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_supDjid" value="${s_supDjid}"/> 
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle"><s:text name="oaSupervise.creatertime" />:</label>
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
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<td class="searchTitleArea">
						<label class="searchCondTitle"><s:text name="oaSupervise.state" />:</label>
						</td>
						<td class="searchCountArea">
						<select name="s_state" id="s_state" class="searchCondContent" style="width: 120px;">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_state')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${param.s_state==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">督办流水号:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_djId" value="${s_djId}"/> 
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle"><s:text name="oaSupervise.title" />:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_title" value="${s_title}"/> 
						</td>
				</tr>
					<c:if test="${show_type eq 'T'}">
					<tr id="gaoji_more2" style="display: none;">
						 <c:if test="${not empty userRank}">
						 <td></td>
						<td class="searchTitleArea">
							<c:if test="${userRank=='TZ' }">
							<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
							<c:if test="${queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看全部</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>

							<c:if test="${userRank=='CZ' }">
								<input type="checkbox" id="queryUnderUnit" name="queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${queryUnderUnit=='true' }"> checked="checked"  </c:if>>
						<span style="color:#6b9bcf;">查看处室</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						</c:if>
						</c:if>
						<c:if test="${show_type eq 'T'}">
						<c:if test="${userRank=='TZ' }">
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchCountArea">
						<label class="searchCondTitle">查询部门：</label>
						<select name="s_unitcode" class="searchCondContent">
								<c:forEach var="row" items="${unitList }">
									<option value="${row.unitcode}"
										<c:if test="${s_unitcode eq row.unitcode}">selected="selected"</c:if>>
										<c:out value="${row.unitname}" />
									</option>
								</c:forEach>
						</select>
						</td>
				</c:if>
				</tr>
               </c:if>
				
				</table>
				</div>
			</s:form>
			</div>
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
					<a class="check_email" href='${contextPath}/oa/oaSupervise!generalOptView.do?djId=${oaSupervise.djId}&nodeInstId=0&viewtype=T&flowInstId=${oaSupervise.flowInstId}'><s:text name="opt.btn.view" /></a>
					<c:if test="${oaSupervise.biztype eq 'F' }">
					<a class="bianji" href='oa/oaSupervise!edit.do?djId=${oaSupervise.djId}'><s:text name="opt.btn.edit" /></a>
				
					<a class="delete_email" href='oa/oaSupervise!delete.do?djId=${oaSupervise.djId}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</c:if>
				<c:if test="${cp:CHECKUSEROPTPOWER('DBCK', 'adminList') and oaSupervise.biztype ne 'F'}">
						<a class="guanli" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listFlowInstNodes.do?flowInstId=${oaSupervise.flowInstId}">跟踪流程</a>
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
	 function sub(){
		 if(checkFrom()==true){
			$("#oasuperviselistv2form").attr("action","oaSupervise!list.do");
			$("#oasuperviselistv2form").submit();
		 }
		} 
	 $(function() {
			$('#queryUnderUnit').live("click", function() {
				unitListIsShow();
			});
			unitListIsShow();
			if(gj()){
				showgaoji();
			}
		});

		function unitListIsShow() {

			if ('checked' == $('#queryUnderUnit').attr("checked")) {
				if ('TZ' == "${userRank}") {
					$("#tr_unitlist").show();
				}
			} else {
				$("#tr_unitlist").hide();
			}
		};
		function showgaoji(){
			$("#shouqi").show();
			$("#gaoji_more").show();
			$("#gaoji_more2").show();
			$("#gaoji").hide();
		}
		function toshouqi(){
			$("#shouqi").hide();
			$("#gaoji_more").hide();
			$("#gaoji_more2").hide();
			$("#gaoji").show();
		}
		function gj(){
			var t=false;
			if($("#s_state").val()!=""&&$("#s_state").val()!=null){
				t=true;
			}
			if($("input[name=s_djId]").val().trim()!=""&&$("input[name=s_djId]").val()!=null){
				t=true;
			}
			if($("input[name=s_title]").val().trim()!=""&&$("input[name=s_title]").val()!=null){
				t=true;
			}
			var showtype='<%=request.getAttribute("show_type")%>';
			var query='<%=request.getAttribute("userRank")%>';
			if(showtype=='T'){
				if(query!=null&&query!=""&&query!="undefined"){
					if($("#queryUnderUnit").is(":checked"))
						t=true;
				}
			}
			return t;
		}
	</script>
</html>
