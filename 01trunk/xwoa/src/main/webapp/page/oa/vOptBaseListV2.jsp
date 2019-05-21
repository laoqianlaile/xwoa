<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>

<title>查询</title>
<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>

<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle"> 办件查看 </legend>
		<div class="searchDiv">
		<s:form action="vOptBaseList!list.do" namespace="/oa" id="vOptBaseList_form" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			<div class="searchArea">
				<table style="width: auto;">
				<tr>
				<td class="searchTitleArea">
				<label class="searchCondTitle">办件状态:</label>
				</td>
					<td class="searchCountArea">
				<s:radio name="s_bizstate" id="s_bizstate" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle"
							list="#{'W':'在办','C':'已办','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_bizstate']}" onclick="doRadioChick();">
			    </s:radio>
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">办件名称:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" class="searchCondContent" name="s_transaffairname" value="${s_transaffairname }" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td >
				<label class="searchCondTitle">登记时间:</label>
				</td>
					<td class="searchCountArea">
						<input type="text" class="Wdate searchCondContent"  id="s_begincreateDate" <c:if test="${not empty s_begincreateDate }"> value="${s_begincreateDate}" </c:if>
	                            <c:if test="${empty s_begincreateDate  }">value="${param['s_begincreateDate'] }"</c:if> name="s_begincreateDate"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     <label class="searchCondTitle">至</label>
						        <input type="text" class="Wdate searchCondContent"  id="s_endcreateDate" <c:if test="${not empty s_endcreateDate }"> value="${s_endcreateDate }" </c:if>
	                            <c:if test="${empty s_endcreateDate  }">value="${param['s_endcreateDate'] }"</c:if> name="s_endcreateDate" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
	                            
				
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				
				
				<td   class="searchTitleArea">
				<label class="searchCondTitle">办件类型:</label>
				</td>
					<td class="searchCountArea">
				<select class="searchCondContent"
						id="s_itemType" name="s_itemType">
							<option value="">全部</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode == s_itemType}"> selected="selected"</c:if>
									>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> 
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td   class="searchTitleArea">
				<label class="searchCondTitle">当前步骤:</label>
				</td>
				<td class="searchCountArea">
				<input type="text" class="searchCondContent" name="s_nodename" value="${s_nodename }" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td   class="searchTitleArea">
				<label class="searchCondTitle">办理人员:</label>
				</td>
				<td class="searchCountArea">
				<input type="text" class="searchCondContent" name="s_bizusernames" value="${s_bizusernames }" />
				</td>
				</tr>
				<c:if test="${not empty userRank}">
						<tr id="gaoji_more2" style="display: none;">
						
				<td class="searchCondArea" colspan="2">
							<c:if test="${userRank=='TZ' }">
								<input type="checkbox" id="queryUnderUnit" name="s_queryUnderUnit"  
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${s_queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看全部</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>

							<c:if test="${userRank=='CZ' }">
								<input type="checkbox" id="queryUnderUnit" name="s_queryUnderUnit"
									value="true" style="background:none;border:none;vertical-align:middle"
									<c:if test="${s_queryUnderUnit=='true' }"> checked="checked" </c:if>>
						<span style="color:#6b9bcf;">查看本科室</span>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						<c:if test="${userRank=='TZ' }">
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle" >查询部门:</label>
						</td>
						<td class="searchCountArea">
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
    <!-- 添加请求参数表单域 -->
    <ec:reqeustAttributeForm id="listReqAttrParam"/>
	<ec:table action="oa/vOptBaseList!list.do" items="vOptBaseList"
		var="vOptBaseinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		
		<%-- <ec:column property="none" title="状态" style="text-align:center"
				sortable="false" width="6%">
			<c:if test="${vOptBaseinfo.bizstate eq 'W' or vOptBaseinfo.bizstate eq 'T'}">
						<img title="办理中"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-blz.png">
			</c:if>	
			<c:if test="${vOptBaseinfo.bizstate eq 'C'}">
						<img title="已办结"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-ybj.png">
			</c:if>	
		</ec:column> --%>
			<%-- <ec:column property="djId" title="办件编号" style="text-align:center"
				sortable="true" width="8%"/> --%>
			<%-- <ec:column property="biztype" title="办件类型" style="text-align:left" width="10px"
				sortable="true">【${cp:MAPVALUE('optTypeName',vOptBaseinfo.itemType)}】
				<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.powername) > 10}">
							<c:out value="${fn:substring(vOptBaseinfo.powername, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.powername}" />
						</c:otherwise>
					</c:choose>
			</ec:column> --%>
			<ec:column  title="序号"  property="rowcount" cell="rowCount" sortable="false" style="text-align:center" width="4%"/>	
				
			<ec:column property="transaffairname" title="办件名称"
					style="text-align:center" sortable="true"> 
					<input type="hidden" value="${vOptBaseinfo.transaffairname}[${vOptBaseinfo.powername}]"/> 
					 <c:if test="${vOptBaseinfo.flowSupervise eq 'T'}">
					 <span class="icon"
						style="background:url(${pageContext.request.contextPath}/themes/css/images/icon/opa-icons-blue16.png);"
						title="督办件"></span>
					</c:if>
               	
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.transaffairname) > 20}">
                        <a  onclick="viewDetail('${vOptBaseinfo.djId}','${cp:MAPVALUE('optType',vOptBaseinfo.itemType)}','${vOptBaseinfo.applyItemType}')"
						href="javascript:void(0)"><c:out
								value="${fn:substring(vOptBaseinfo.transaffairname, 0, 20)}..." /></a>
						</c:when>
						<c:otherwise>
						<a  onclick="viewDetail('${vOptBaseinfo.djId}','${cp:MAPVALUE('optType',vOptBaseinfo.itemType)}','${vOptBaseinfo.applyItemType}')"
						href="javascript:void(0)"><c:out value="${vOptBaseinfo.transaffairname}" /></a>
						</c:otherwise>
					</c:choose>
				</ec:column>
				<%-- <ec:column property="bizstate" title="办理状态"
					style="text-align:center" sortable="true" width="8%">${cp:MAPVALUE('oa_bizstate',vOptBaseinfo.bizstate)}</ec:column> --%>
		
				<ec:column property="createdate" title="登记日期"
					style="text-align:center" sortable="true" width="6%">
					<fmt:formatDate value="${vOptBaseinfo.createdate}"
						pattern="yyyy-MM-dd HH:mm" />
				</ec:column>
				<ec:column property="creater" title="登记人" style="text-align:center"
					sortable="true" width="6%" >${cp:MAPVALUE("usercode",vOptBaseinfo.creater)}</ec:column>
				 <ec:column property="nodename" title="办理步骤"
					style="text-align:center" sortable="true">
					<input type="hidden" value="${vOptBaseinfo.nodename}"/> 
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.nodename) > 12}">
							<c:out
								value="${fn:substring(vOptBaseinfo.nodename, 0, 12)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.nodename}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="bizusernames" title="办理人员" style="text-align:center" sortable="true">
					<input type="hidden" value="${vOptBaseinfo.bizusernames}"/> 
					<c:choose>
						<c:when test="${fn:length(vOptBaseinfo.bizusernames) > 10}">
							<c:out
								value="${fn:substring(vOptBaseinfo.bizusernames, 0, 10)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vOptBaseinfo.bizusernames}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				 <ec:column property="none" title="状态" style="text-align:center"
						sortable="false" width="6%">
					<c:if test="${vOptBaseinfo.bizstate eq 'W' or vOptBaseinfo.bizstate eq 'T'}">
						<img title="办理中"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-blz.png">
					</c:if>	
					<c:if test="${vOptBaseinfo.bizstate eq 'C'}">
						<img title="已办结"
							src="<%=request.getContextPath()%>/themes/css/images/icon/oa-bj-ybj.png">
					</c:if>	
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center" width="8%">
					<a class="check_email" onclick="viewDetail('${vOptBaseinfo.djId}','${cp:MAPVALUE('optType',vOptBaseinfo.itemType)}','${vOptBaseinfo.applyItemType}')"
						href="javascript:void(0)">查看</a>
						<c:if test="${vOptBaseinfo.itemType eq 'QB'  && vOptBaseinfo.nodename eq '拟稿'}">
					<a class="delete_email" onclick="return confirm('是否确定删除？')" href="<%=request.getContextPath()%>/oa/vOptBaseList!deleteDoc.do?djId=${vOptBaseinfo.djId}">删除</a>
						</c:if>
					<%-- <c:if test="${ (cp:CHECKUSEROPTPOWER('QBCK', 'adminList') and vOptBaseinfo.itemType eq 'QB') or (cp:CHECKUSEROPTPOWER('FWGLFWCK', 'adminList') and vOptBaseinfo.itemType eq 'FW') or (cp:CHECKUSEROPTPOWER('SWGLSWCK', 'adminList') and vOptBaseinfo.itemType eq 'SW')
								   or (cp:CHECKUSEROPTPOWER('SQCK', 'adminList') and vOptBaseinfo.itemType eq 'SQ') or (cp:CHECKUSEROPTPOWER('DBCK', 'adminList') and vOptBaseinfo.itemType eq 'DCDB')	}">
						<a class="guanli" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!listFlowInstNodes.do?flowInstId=${vOptBaseinfo.flowInstId}">跟踪流程</a>
					</c:if> --%>
				</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	$(function() {
		$('#queryUnderUnit').live("click", function() {
			unitListIsShow();
		});
		unitListIsShow();
		
		if(gj()){
			showgaoji();
		}
		
		toshouqi();
	});
	function doRadioChick(){
		$("#vOptBaseList_form").submit();
	}
	
	function viewDetail(djId,optType,applyItemType){
		 var paramForm = $("#listReqAttrParam");
		 paramForm.attr("action","${ctx}/"+optType+"!generalOptView.do");
		 
		 var djIdInput = paramForm.find("input[name='djId']"),
			nodeInstIdInput = paramForm.find("input[name='nodeInstId']"),
			applyItemTypeInput = paramForm.find("input[name='applyItemType']");
			if(djIdInput.length == 0){
				djIdInput = $("<input>",{"name":"djId","type":"hidden"});
				paramForm.append(djIdInput);
			}
			if(nodeInstIdInput.length == 0){
				nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
				paramForm.append(nodeInstIdInput);
			}
			if(applyItemType.length == 0){
				applyItemTypeInput = $("<input>",{"name":"applyItemType","type":"hidden"});
			}
			djIdInput.val(djId);
			nodeInstIdInput.val(0);
			applyItemTypeInput.val(applyItemType);
		 paramForm.submit();
	}
	function unitListIsShow() {

		if ('checked' == $('#queryUnderUnit').attr("checked")) {
			if ('TZ' == "${userRank}") {
				$("#tr_unitlist").show();
			}
		} else {
			$("#tr_unitlist").hide();
		}
	};
	/* function email(){
		var email=$("#email");
		email.action("/oa/innermsg");
		email.meth("editDraft");
		
	} */
	function sub(){
		
			$("#vOptBaseList_form").submit();
		
	}
	function showgaoji(){
		$("#shouqi").show();
		$("#gaoji_more").show();
		$("#gaoji").hide();
		$("#gaoji_more2").show();
	}
	function toshouqi(){
		$("#shouqi").hide();
		$("#gaoji_more").hide();
		$("#gaoji").show();
		$("#gaoji_more2").hide();
	}
	function gj(){
		var t=false;
		if($("input[name=s_nodename]").val().trim()!=""&&$("input[name=s_nodename]").val()!=null){
			t=true;
		}
		if($("input[name=s_bizusernames]").val().trim()!=""&&$("input[name=s_bizusernames]").val()!=null){
			t=true;
		}
		if($("#s_itemType").val()!=""){
			t=true;
		}
		var query='<%=request.getAttribute("userRank")%>';
		if(query!=null&&query!=""&&query!="undefined"){
			if($("#s_unitcode").val()!="1"&&$("#s_unitcode").val()!=null&&$("#s_unitcode").val()!="")
				t=true;
		}
		return t;
	}
</script>
</html>
