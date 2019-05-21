<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<%-- <sj:head locale="zh_CN" /> --%>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />	
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>				
		
		<title>
			<s:text name="militarycases.list.title" />
		</title>	
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search" >
			<legend class="headTitle">
					<c:choose>
						<c:when test="${s_biztype eq 'F' }">会议申请登记</c:when>
						<c:when test="${show_type eq 'myself'}">我的申请</c:when>
						<c:when test="${ empty s_biztype and show_type ne 'myself'}">会议申请查看</c:when>
					</c:choose>
			</legend>
			<div class="searchDiv">
			<s:form id="oameetapplyform" method="POST" action="oaMeetApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<div class="searchArea">
				  <input type="hidden" name="show_type" value="${show_type}" />
				  <input type="hidden" name="s_biztype" value="${s_biztype}" />
				<table style="width: auto;">
            
		
					<tr>
					<c:if test="${ 'T' ne show_type }"> 
					<td class="searchBtnArea">
<%-- 					<s:submit method="built" value="申请" cssClass="whiteBtnWide"  /> --%>
					<input type="button" value="申请" class="whiteBtnWide" id=built />
					</td>
					</c:if>
					
					<td class="searchTitleArea">
					<label class="searchCondTitle">会议室:</label>
					</td>
					<td class="searchCountArea">
					<s:select name="s_tmeetingNo" list="meetings" listKey="id" listValue="name" headerKey="" headerValue="--请选择--"
					cssStyle="border-radius: 10px !important; background-color: #e6f0fa !important; color:#6b9bcf;width: 120px;" />	
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">申请单号:</label>
					</td>
					<td class="searchCountArea">
						 
						<input type="text" class="searchCondContent" name="s_tapplyNo" value="${s_tapplyNo}" /> 
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
						<label class="searchCondTitle">使用时间:</label>
						</td>
					<td class="searchCountArea">
					   	 <input type="text" class="Wdate searchCondContent" id="s_cpBegTime"
						<c:if test="${not empty s_cpBegTime }"> value="${s_cpBegTime}" </c:if>
						<c:if test="${empty s_cpBegTime  }">value="${param['s_cpBegTime'] }"</c:if>
						name="s_cpBegTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_cpEndTime"
						<c:if test="${not empty s_cpEndTime }"> value="${s_cpEndTime }" </c:if>
						<c:if test="${empty s_cpEndTime  }"> value="${param['s_cpEndTime'] }" </c:if>
						name="s_cpEndTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
					</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
					</tr>
					<tr id="gaoji_more" style="display: none;">
					<c:if test="${ 'T' ne show_type }"> 
					<td >
					</td>
					</c:if>
					<td class="searchTitleArea">
					<label class="searchCondTitle">状态:</label>
					</td>
					<td class="searchCountArea">
					<select name="s_tstate" id="s_state" class="searchCondContent" style="width: 120px;">
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('meetState')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${s_tstate==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">申请部门:</label>
					</td>
					<td class="searchCountArea">
					<select id="s_depno" name="s_tdepno" class="searchCondContent" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitlist }">
								<option value="${row.unitcode}"
									<c:if test="${parameters.s_tdepno[0] eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					        </select>
					</td>
					</tr>
					
					
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>

		<ec:table action="oa/oaMeetApply!list.do" items="objList" var="oaMeetApply" styleClass="ectableRegions tableRegion"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
	          <%--    <c:if test="${show_type ne 'F' }">	 --%><!--meetState：1申请中2已安排3已使用4不同意5被抢占6暂存7取消  -->
				<ec:column property="state" title="状态" style="text-align:left" sortable="false" >
				<input type="hidden" name="state" value="${cp:MAPVALUE('meetState',oaMeetApply.state)}"/>
				<c:if test="${oaMeetApply.state eq '1' }">
				<span class="icon-yello" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '2' }">	
				<span class="icon-gren" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '4' }">	
				<span class="icon-redd" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '3' }">	
				<span class="icon-blue" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '5' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '6' }">	
				<span class="icon-orange" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				<c:if test="${oaMeetApply.state eq '7' }">	
				<span class="icon-white" title="${cp:MAPVALUE('meetState',oaMeetApply.state)}"></span>
				</c:if>
				${cp:MAPVALUE('meetState',oaMeetApply.state)}
				</ec:column>		
				<%-- </c:if> --%>
					 <ec:column property="doBegTime" title="使用时间" style="text-align:center" sortable="false" >
				<%-- <c:if test="${ not empty oaMeetApply.doBegTime  or not empty oaMeetApply.doBegTime  }">
				 <fmt:formatDate value='${ oaMeetApply.doBegTime }' pattern='MM月dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.doEndTime }' pattern='MM月dd HH:mm' />
				 </c:if>
				 <c:if test="${empty oaMeetApply.doBegTime and empty oaMeetApply.doBegTime }">
				 <fmt:formatDate value='${ oaMeetApply.planBegTime }' pattern='MM月dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.planEndTime }' pattern='MM月dd HH:mm' />
				 </c:if> --%>
				  <fmt:formatDate value='${ oaMeetApply.cpBegTime }' pattern='MM月dd HH:mm' />至
				 <fmt:formatDate value='${ oaMeetApply.cpEndTime }' pattern='MM月dd HH:mm' />
				</ec:column>
				<ec:column property="meetingName" title="会议室" style="text-align:center" sortable="false">
			          ${ oaMeetApply.meetingName }  
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
					<a class="check_email" href='${pageContext.request.contextPath}/oa/oaMeetApply!generalOptView.do?djId=${oaMeetApply.djId}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaMeetApply.biztype eq 'F' }">
					<a class="bianji" href='${pageContext.request.contextPath}/oa/oaMeetApply!edit.do?djId=${oaMeetApply.djId}&show_type=${show_type}'><s:text name="opt.btn.edit" /></a>
					<a class="delete_email" href='${pageContext.request.contextPath}/oa/oaMeetApply!delete.do?djId=${oaMeetApply.djId}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</c:if>
				
                <!-- 本人		发起流程的会议申请取消     --><!--meetState：1申请中2已安排3已使用4不同意5被抢占6暂存  7取消-->
                <c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaMeetApply.creater}">
				<c:if test="${oaMeetApply.biztype ne 'F' and (oaMeetApply.state eq 1 or oaMeetApply.state eq 2)}">
				<a class="quxiao" href='${pageContext.request.contextPath}/oa/oaMeetApply!cancleApply.do?djId=${oaMeetApply.djId}' 
							onclick='return confirm("确定取消该会议申请记录吗?");'><s:text name="取消预定" /></a>
				</c:if>
				</c:if>
				</ec:column>

			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	$(function(){
		$("#built").click(
			function(){
				url='${contextPath}/oa/oaMeetApply!built.do?show_type=${show_type}&s_biztype=${s_biztype}';
				art.dialog
				.open(url,
						 {title: '会议申请', width: 1050, height: 800});
			});
		
	})
	
	
	function checkFrom(){
		var begD = $("#s_cpBegTime").val();
		var endD = $("#s_cpEndTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_cpEndTime").focus();
			return false;
		}
		return true;
	}
	function sub(){
		if(checkFrom()==true){
		$("#oameetapplyform").attr("action","oaMeetApply!list.do");
		$("#oameetapplyform").submit();
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
	$(function() {
		if(gj()){
			showgaoji();
		}
	});
	function gj(){
		var t=false;
		if($("#s_state").val().trim()!=""&&$("#s_state").val()!=null){
			t=true;
		}
		if($("#s_depno").val().trim()!=""&&$("#s_depno").val()!=null){
			t=true;
		}
		return t;
	}
	</script>
</html>
