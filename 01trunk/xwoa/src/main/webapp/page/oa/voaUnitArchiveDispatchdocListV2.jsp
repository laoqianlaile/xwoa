<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
			<s:text name="voaUnitArchiveDispatchdoc.list.title" />
		</title>
	</head>
<style type="text/css">
	.eXtremeTable{margin-top:0!important}
	.searchDiv{margin-bottom:0!important;padding-bottom:5px!important}
	</style>
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; " class="search">
			<legend class="headTitle">
				发文归档
			</legend>
			<div class="searchDiv">
			<s:form id="voaunitarchivedispachdocform" method="POST" action="voaUnitArchiveDispatchdoc" namespace="/oa" style="margin-top:0;margin-bottom:0">
				<div class="searchArea">
				<table style="width: auto;">
					
					<tr>
						<td class="searchTitleArea">
						
						<label class="searchCondTitle" style="width: 65px;">发文号:</label>
						</td>
					<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_DISPATCH_DOC_NO" style="height: auto;" value="${s_DISPATCH_DOC_NO }" />
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle" style="width: 65px;">文件标题:</label>
						</td>
					<td class="searchCountArea">
						<input class="searchCondContent" style="height: auto;" type="text" name="s_transaffairname" value="${s_transaffairname }" />
					</td>
					
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">拟文日期:</label>
					</td>
					<td class="searchCountArea">	
						<input type="text" class="Wdate searchCondContent"  style="height: auto;" id="s_begincomeDate" <c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
	                    <c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if> name="s_begincomeDate"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						     <label class="searchCondTitle">至</label> 
						<input type="text" class="Wdate searchCondContent" style="height: auto;"  id="s_endincomeDate" <c:if test="${not empty s_endincomeDate}"> value="${s_endincomeDate}" </c:if>
	                    <c:if test="${empty s_endincomeDate  }">value="${param['s_endincomeDate'] }"</c:if> name="s_endincomeDate" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
					</tr>
					<tr id="gaoji_more" style="display: none;">
						<td class="searchTitleArea">	
					<label class="searchCondTitle" style="width: 65px;">主送单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_MAIN_NOTIFY_UNIT" value="${s_MAIN_NOTIFY_UNIT }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">抄送单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_OTHER_UNITS" value="${s_OTHER_UNITS }" />
					</td>
					
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>
 <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
		<ec:table action="oa/voaUnitArchiveDispatchdoc!list.do" items="objList" var="voaUnitArchiveDispatchdoc"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit"> 
			<ec:row>
			    <%-- <ec:column property="transaffairname"  title="标题" style="text-align:center" >
			    </ec:column>
			    <ec:column property="dispatchDocNo"  title="发文号" style="text-align:center" >
			    </ec:column>
			     <ec:column property="mainNotifyUnit"  title="主送单位" style="text-align:center" >
			    </ec:column>
				<ec:column property="optUnitName" title="单位名称" style="text-align:center" >
				<a href='${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${voaUnitArchiveDispatchdoc.no }&nodeInstId=0'>
				${voaUnitArchiveDispatchdoc.optUnitName}</a>
				</ec:column>
				<ec:column property="createDate" title="收文日期" style="text-align:center">
				 <fmt:formatDate value='${voaUnitArchiveDispatchdoc.createDate}' pattern='yyyy-MM-dd' />
				</ec:column>
 --%>

			<ec:column property="dispatchDocNo" title="发文号" style="text-align:center" />
			<ec:column property="transaffairname" title="文件标题" style="text-align:center" >
			<a href="javascript:void(0)" onclick="viewDetail('${voaUnitArchiveDispatchdoc.no}',0)"> 
			 <c:choose>
						<c:when test="${fn:length(voaUnitArchiveDispatchdoc.transaffairname) > 16}">
							<c:out
								value="${fn:substring(voaUnitArchiveDispatchdoc.transaffairname, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${voaUnitArchiveDispatchdoc.transaffairname}" />
						</c:otherwise>
					</c:choose></a>
			</ec:column>
			<ec:column property="createDate" title="发文时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
			<ec:column property="draftUserName" title="经办人" style="text-align:center">${voaUnitArchiveDispatchdoc.draftUserName}</ec:column>
			<ec:column property="dispatchUser" title="签发人" style="text-align:center">${cp:MAPVALUE("usercode",voaUnitArchiveDispatchdoc.dispatchUser)}</ec:column>
			<ec:column property="mainNotifyUnit" title="主送单位" style="text-align:center"/>
			<ec:column property="bizstate" title="状态" style="text-align:center">${cp:MAPVALUE("oa_bizstate",voaUnitArchiveDispatchdoc.bizstate)}</ec:column>
			<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center" >
					    <a href='oa/oaArchive!add.do?no=${voaUnitArchiveDispatchdoc.no }&bookdate=${voaUnitArchiveDispatchdoc.createDate}&title=${voaUnitArchiveDispatchdoc.transaffairname}&docno=${voaUnitArchiveDispatchdoc.dispatchDocNo }&flag=F'>归档</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script>
	function viewDetail(djId,nodeInstId){
		 var paramForm = $("#listReqAttrParam");
		 paramForm.attr("action","${ctx}/dispatchdoc/dispatchDoc!generalOptView.do");
		 
		 var djIdInput = paramForm.find("input[name='djId']"),
			nodeInstIdInput = paramForm.find("input[name='nodeInstId']");
			if(djIdInput.length == 0){
				djIdInput = $("<input>",{"name":"djId","type":"hidden"});
				paramForm.append(djIdInput);
			}
			if(nodeInstIdInput.length == 0){
				nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
				paramForm.append(nodeInstIdInput);
			}
			djIdInput.val(djId);
			nodeInstIdInput.val(nodeInstId);
		 paramForm.submit();
	}
  	$(function() {
		$('a').each(function() {
			$this = $(this);
			var href = $this.attr('href');
			href=encodeURI(encodeURI(href));
			$this.attr('href', href);
		});
		if(gj()){
			showgaoji();
		}
	}); 
  	
  	function checkFrom(){
		var begD = $("#s_begTime").val();
		var endD = $("#s_endTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
			return false;
		}
		return true;
	} 
  	function sub(){
  		if(checkFrom()==true){
		$("#voaunitarchivedispachdocform").attr("action","voaUnitArchiveDispatchdoc!list.do");
		$("#voaunitarchivedispachdocform").submit();
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
		if($("input[name=s_MAIN_NOTIFY_UNIT]").val().trim()!=""&&$("input[name=s_MAIN_NOTIFY_UNIT]").val()!=null){
			t=true;
		}
		if($("input[name=s_OTHER_UNITS]").val().trim()!=""&&$("input[name=s_OTHER_UNITS]").val()!=null){
			t=true;
		}
		return t;
	}
</script>
</html>
