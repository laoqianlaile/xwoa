<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title><s:text name="oaAssetinformation.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle">
		    <c:if test="${isDept eq 'T' }">${cp:MAPVALUE('UnitType', isDept) }</c:if>
		     <c:if test="${isDept ne 'T' }">${cp:MAPVALUE('unitcode', unitcode) }</c:if>
		</legend>
<div class="searchDiv">
		<s:form id="oaassetinformationform" method="post" action="oaAssetinformation" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="s_datacode" value="${datacodeType }" />
			<input type="hidden" name="hasChildNode" value="${hasChildNode }" />
			<div class="searchArea">
			<table style="width: auto;">
				<tr>
					<td class="searchBtnArea">
						<c:if test="${hasChildNode  ne 'Y' }">  
					        <s:submit method="built" key="opt.btn.new" cssClass="whiteBtnWide" />
					    </c:if>
					    <s:hidden name="datacodeType" />
					    <s:submit method="exportExcelByPo" value="导出资产信息" cssClass="whiteBtnWide2" cssStyle="height:24px;"/> 
					
					</td>
					<td class="searchTitleArea">
						<label class="searchCondTitle" style="width: 65px;">资产名称:</label>
						</td>
						<td class="searchCountArea">
						<select name="s_datacode" class="searchCondContent">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('equipment')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_datacode }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">记录时间:</label>
					</td>
						<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent"  id="s_begcreatertime" <c:if test="${not empty s_begcreatertime }"> value="${s_begcreatertime}" </c:if>
	                    <c:if test="${empty s_begcreatertime  }">value="${param['s_begcreatertime'] }"</c:if> name="s_begcreatertime"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						  <label class="searchCondTitle">至</label>
						<input type="text" class="Wdate searchCondContent"  id="s_endcreatertime" <c:if test="${not empty s_endcreatertime }"> value="${s_endcreatertime }" </c:if>
	                    <c:if test="${empty s_endcreatertime  }">value="${param['s_endcreatertime'] }"</c:if> name="s_endcreatertime" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">&nbsp;&nbsp;	
						<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
					</td>
					<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<td></td>
				<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">启用状态:</label>
					</td>
						<td class="searchCountArea">
					<select id="s_state" name="s_state" class="searchCondContent">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('zcstate')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_state }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select>
					</td>
					<c:if test="${isDept eq 'T' }">
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">所属机构:</label>
					</td>
						<td class="searchCountArea">
					<select id="s_createDepno" name="s_createDepno" class="searchCondContent" style="width: 200px;">
							<option value="">---请选择---</option>
							<c:forEach items="${unitlist}" var="u">
								<option value="${u.unitcode }"
									<c:if test="${s_createDepno eq u.unitcode }">selected="selected"</c:if>>${u.unitname
									}</option>
							</c:forEach>
					</select>
					</td>
					</c:if>
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="oa/oaAssetinformation!list.do" items="objList"
		var="oaAssetinformation" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<c:set var="tdatacode">资产名称</c:set>
			<ec:column property="datacode" title="${tdatacode}"
				style="text-align:center">
				<a href='oa/oaAssetinformation!viewInfo.do?no=${oaAssetinformation.no}'>${cp:MAPVALUE('equipment',
					oaAssetinformation.datacode) }</a>
			</ec:column>

			<c:set var="tassetnum">
				<s:text name='oaAssetinformation.assetnum' />
			</c:set>
			<ec:column property="assetnum" title="${tassetnum}"
				style="text-align:center" >
				<c:if test="${oaAssetinformation.assetnum >5&&oaAssetinformation.assetnum<=10}">
				<img align="middle" alt="提醒" src="${pageContext.request.contextPath}/images/green.gif" />${oaAssetinformation.assetnum}
				</c:if>
				<c:if test="${ oaAssetinformation.assetnum <=5}">
				<img align="middle" alt="预警" src="${pageContext.request.contextPath}/images/red.gif" />${oaAssetinformation.assetnum}
				</c:if>
		    </ec:column>
			<c:set var="tcreateDepno">
				<s:text name='oaAssetinformation.createDepno' />
			</c:set>
			<ec:column property="createDepno" title="${tcreateDepno}"
				style="text-align:center">
				${cp:MAPVALUE('unitcode', oaAssetinformation.createDepno) }
				</ec:column>
			<c:set var="tcreatetime">
				<s:text name='oaAssetinformation.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center">
				<fmt:formatDate value='${oaAssetinformation.createtime}'
					pattern='yyyy-MM-dd HH:mm:ss' />
			</ec:column>
			<c:set var="tsenddeptype">
				<s:text name='oaAssetinformation.senddeptype' />
			</c:set>
			<ec:column property="senddeptype" title="${tsenddeptype}"
				style="text-align:center">
				${cp:MAPVALUE('UnitType', oaAssetinformation.senddeptype) }
				</ec:column>
			<c:set var="tassetleftalert">
				<s:text name='oaAssetinformation.assetleftalert' />
			</c:set>
			<ec:column property="assetleftalert" title="${tassetleftalert}"
				style="text-align:center" >
            </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="state" title="启用状态"
				style="text-align:center">
				${cp:MAPVALUE('zcstate', oaAssetinformation.state) }
				</ec:column>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<c:if test="${oaAssetinformation.state eq 'T' }">
				<a class="bianji"
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
				<a class="ruku"
					href='oa/oaAssetinformationInlog!edit.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}'>进库</a>
				<a class="chuku"
					href='oa/oaAssetinformationOutlog!edit.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}&isDept=${isDept}'>出库</a>
					<a class="tinyong"
					href='oa/oaAssetinformation!oaAssetinformationStart.do?no=${oaAssetinformation.no}&s_state=D'>停用</a>
				</c:if>
				<c:if test="${oaAssetinformation.state eq 'D' }">
				 <a class="bianji"
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
				<a class="qiyong"
					href='oa/oaAssetinformation!oaAssetinformationStart.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}&s_state=T'>启用</a>
				</c:if>
				<c:if test="${oaAssetinformation.state eq 'F'  }">
				    <a class="bianji"
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
					<a class="qiyong"
					href='oa/oaAssetinformation!oaAssetinformationStart.do?no=${oaAssetinformation.no}&s_state=T'>启用</a>
					<c:if test="${oaAssetinformation.hasOutIn ne 'T'  }">
					<a class="delete_email"
					href='oa/oaAssetinformation!delete.do?no=${oaAssetinformation.no}'>删除</a>
					</c:if>
				</c:if>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
function sub(){
	$("#oaassetinformationform").attr("action","oaAssetinformation!list.do");
	$("#oaassetinformationform").submit();
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
	if($("#s_state").val().trim()!=""&&$("#s_state").val()!=null){
		t=true;
	}
	var isDept='<%=request.getAttribute("isDept")%>';
	if(isDept=='T'){
		if($("#s_createDepno").val().trim()!=""&&$("#s_createDepno").val()!=null){
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
