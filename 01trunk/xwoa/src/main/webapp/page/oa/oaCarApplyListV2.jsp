<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>	
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/css/icon-css.css" rel="stylesheet" type="text/css" />	
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />

<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>					
		<title>
		
			<s:text name="militarycases.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset   class="search">
			<legend class="headTitle">
					<c:choose>
							<c:when  test="${show_type eq 'F' }">我的申请</c:when>
							<c:when test="${ show_type eq 'T'}">车辆申请查看</c:when>
							<c:when test="${ empty s_biztype}">车辆申请查看</c:when>
					</c:choose>
			</legend>
			<div class="searchDiv">
			<s:form id="oacarapplylistform" method="post" action="oaCarApply" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<div class="searchArea">
				  <input type="hidden" name="show_type" value="${show_type}" />
				  <input type="hidden" name="s_cardjid" value="${s_cardjid}" />
				<table style="width: auto;">
		
					<tr>
					<c:if test="${ 'T' ne show_type }"> 
					<td class="searchBtnArea">
					
<%-- 							<s:submit method="built"  key="opt.btn.new" cssClass="whiteBtnWide"/> --%>
						<input type="button" value="申请" class="whiteBtnWide" id=built />
						</td>
					</c:if>
					
						
						<td class="searchTitleArea">
						<label class="searchCondTitle">申请部门:</label>
						</td>
						<td class="searchCountArea">
						<select name="s_depno" class="searchCondContent" style="width: 200px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitlist }">
								<option value="${row.unitcode}"
									<c:if test="${parameters.s_depno[0] eq row.unitcode}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
							</select>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">申请单号:</label>
						</td>
						<td class="searchCountArea">
						<input type="text" class="searchCondContent" name="s_applyNo" value="${s_applyNo}"/>
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle">登记时间:</label>
						</td>
						<td class="searchCountArea">
						<input type="text" class="Wdate searchCondContent" id="s_docpTimeBegin" readonly="readonly"
						<c:if test="${not empty s_docpTimeBegin }"> value="${s_docpTimeBegin}" </c:if>
						<c:if test="${empty s_docpTimeBegin  }">value="${param['s_docpTimeBegin'] }"</c:if>
						name="s_docpTimeBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_docpTimeEnd"
						<c:if test="${not empty s_docpTimeEnd }"> value="${s_docpTimeEnd }" </c:if>
						<c:if test="${empty s_docpTimeEnd  }"> value="${param['s_docpTimeEnd'] }" </c:if>
						name="s_docpTimeEnd" readonly="readonly"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期"><c:if test="${ 'T' eq show_type }"> &nbsp;&nbsp;	
						<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">				
						</c:if>
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
					</tr>
					<c:if test="${ 'T' eq show_type }"> 
					<tr id="gaoji_more" style="display: none;">
					<td class="searchTitleArea">
						<label class="searchCondTitle">申请状态:</label>	
						</td>
						<td class="searchCountArea">		
						<select name="s_solvestatus" id="s_solvestatus" class="searchCondContent" style="width: 200px;">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('solvestatus')}">
								<option value="${row.key}" label="${row.value}" <c:if test="${s_solvestatus==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					</c:if>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>

		<ec:table action="oa/oaCarApply!list.do" items="objList" var="oaCarApply" styleClass="ectableRegions tableRegion"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			<c:if test="${show_type ne 'F' }">
			<!-- 1申请中2已调配3已使用4不同意5被抢占6暂存7取消  -->
			<ec:column property="state" title="状态" style="text-align:center" sortable="false" >
				<input type="hidden" name="state" value="${cp:MAPVALUE('meetState',oaCarApply.state)}"/>
				<c:if test="${oaCarApply.state eq '1' }">
				<span class="icon-yello" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '2' }">	
				<span class="icon-gren" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if> 
				<c:if test="${oaCarApply.state eq '4' }">	
				<span class="icon-redd" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '3' }">	
				<span class="icon-blue" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '5' }">	
				<span class="icon-purle" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '6' }">	
				<span class="icon-orange" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>
				<c:if test="${oaCarApply.state eq '7' }">	
				<span class="icon-white" title="${cp:MAPVALUE('meetState',oaCarApply.state)}"></span>
				</c:if>${cp:MAPVALUE('meetState',oaCarApply.state)}
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
			
				 <fmt:formatDate value='${ oaCarApply.cpBegtime }' pattern='yyyy-MM-dd HH:mm' />--
				 <fmt:formatDate value='${ oaCarApply.cpEndtime }' pattern='yyyy-MM-dd HH:mm' />
				
				</ec:column>
				
				<ec:column property="meetingPersionsNum" title="乘车人数" style="text-align:center" >
				               ${oaCarApply.meetingPersionsNum}   
                </ec:column>
                
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a class="check_email" href='oa/oaCarApply!generalOptView.do?djId=${oaCarApply.djId}&nodeInstId=0'><s:text name="opt.btn.view" /></a>
						<c:if test="${oaCarApply.biztype eq 'F' }">
					<a class="bianji" href=''  onclick="art.dialog
						.open('${pageContext.request.contextPath}/oa/oaCarApply!edit.do?djId=${oaCarApply.djId}&show_type=${show_type}&random=Math.random()',
								 {title: '车辆申请', width: 1050, height: 800}) ;return false"><s:text name="opt.btn.edit" /></a>
					<a class="delete_email" href='oa/oaCarApply!delete.do?djId=${oaCarApply.djId}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</c:if>
				
				  <!-- 本人		发起流程的会议申请取消     --><!--State：1申请中2已安排3已使用4不同意5被抢占6暂存  7取消-->
                <c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaCarApply.creater}">
				<c:if test="${oaCarApply.biztype ne 'F' and (oaCarApply.state eq 1 or oaCarApply.state eq 2)}">
				<a class="quxiao" href='${pageContext.request.contextPath}/oa/oaCarApply!cancleApply.do?djId=${oaCarApply.djId}' 
							onclick='return confirm("确定取消该车辆申请记录吗?");'><s:text name="取消" /></a>
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
					url='${contextPath}/oa/oaCarApply!built.do?show_type=${show_type}&s_biztype=${s_biztype}';
					art.dialog
					.open(url,
							 {title: '车辆申请', width: 1050, height: 500});
				});
			
		})
			
		
	 	
	function checkFrom(){
		var begD = $("#s_docpTimeBegin").val();
		var endD = $("#s_docpTimeEnd").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_docpTimeEnd").focus();
			return false;
		}
		return true;
	}
	function sub(){
		if(checkFrom()==true){
		$("#oacarapplylistform").attr("action","oaCarApply!list.do");
		$("#oacarapplylistform").submit();
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
		var showtype='<%=request.getAttribute("show_type")%>';
		if(showtype=='T'){
			if($("#s_solvestatus").val().trim()!=""&&$("#s_solvestatus").val()!=null){
				t=true;
			}
		}
		return t;
	}
	$(function() {
		if(gj()){
			showgaoji();
		}
	});
	</script>
</html>
