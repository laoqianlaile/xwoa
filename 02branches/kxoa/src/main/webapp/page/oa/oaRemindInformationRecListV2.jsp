<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/plugin/flatlab/assets/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css" /> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title><s:text name="oaRemindInformation.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search">
		<legend class="headTitle">
			<c:choose>
			<c:when test="${s_bizType eq '0' }">未读提醒</c:when>
			<c:when test="${s_bizType eq '1' }">已读提醒</c:when>
			</c:choose>
		</legend>
<div class="searchDiv">
		<s:form id="oaremindinformationreclistform" method="post" action="oaRemindInformation" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
				<input type="hidden" id="s_bizType" name="s_bizType"
					value="${s_bizType}" />
					  <input type="hidden" id="hid_ids" name="ids" value="${ids}"/>
			<table style="width: auto;">
				<tr>
				<td class="searchBtnArea">
				<c:if test="${not empty objList }">
								<s:submit method="deleteRecIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="whiteBtnWide" value="批量删除"></s:submit>
					      </c:if>
				</td>
					
					<td class="searchTitleArea">
					<label class="searchCondTitle"><s:text name="oaRemindInformation.title" />:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_title" value="${s_title}" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle"><s:text name="oaRemindInformation.createtime" />:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent"  id="s_begCreatetime"
					  		name="s_begCreatetime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期"
					  		value="${s_begCreatetime }" />	
					  		<label class="searchCondTitle">
					  		至</label>
					  		<input type="text" class="Wdate searchCondContent" id="s_endCreatetime"
					  		name="s_endCreatetime" value="${s_endCreatetime }" 
					  		 onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />&nbsp;&nbsp;	
						<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">	
					 </td>
					
					<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<td></td>
				<td class="searchTitleArea">
					<label class="searchCondTitle"><s:text name="oaRemindInformation.reType" />:</label>
					</td>
					<td class="searchCountArea">
					<select name="s_reType" id="s_reType" class="searchCondContent">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oa_superviseType')}">
								<option value="${row.datacode}"
									<c:if test="${s_reType eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">提醒时间:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent"
						value='${s_begTime}' id="s_begTime" name="s_begTime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						<label class="searchCondTitle">
					  		至</label>
						<input type="text" class="Wdate searchCondContent"
						value='${s_endTime}' id="s_endTime" name="s_endTime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
					</td>	
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="oa/oaRemindInformation!recipientList.do"
		items="objList" var="oaRemindInformation"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		   <ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
						<input class="chk_one" type="checkbox" id="${oaRemindInformation.no}"
							class="ecbox" value="${oaRemindInformation.no}">
				<c:if test="${0 eq s_bizType}">
					<span class="icon icon-blue icon-envelope-closed" title="未读" /></span>
				</c:if>
				<c:if test="${1 eq s_bizType}">
					<span class="icon icon-blue icon-envelope-open" title="已读" /></span>
				</c:if>
			</ec:column>
			<c:set var="ttitle">
				<s:text name='oaRemindInformation.title' />
			</c:set>
			<ec:column property="title" title="${ttitle}"
				style="text-align:center">
				
				<c:choose>
				<c:when test="${fn:length(oaRemindInformation.title) gt 25 }">${fn:substring(oaRemindInformation.title , 0, 25) }...</c:when>
				<c:otherwise>${oaRemindInformation.title} </c:otherwise>
				</c:choose>	
		    </ec:column>

			<c:set var="treType">
				<s:text name='oaRemindInformation.reType' />
			</c:set>
			<ec:column property="reType" title="${treType}"
				style="text-align:center">
				${cp:MAPVALUE("oa_superviseType",oaRemindInformation.reType) }
				</ec:column>


			<c:set var="tcreater">
				<s:text name='oaRemindInformation.creater' />
			</c:set>
			<ec:column property="creater" title="${tcreater}"
				style="text-align:center">
				 ${cp:MAPVALUE("usercode",oaRemindInformation.creater)}
				</ec:column>

			<c:set var="tcreatetime">
				<s:text name='oaRemindInformation.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set> 
				<a class="check_email"
					href='oa/oaRemindInformation!view.do?no=${oaRemindInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<!-- 					提醒：已读未读  操作-->
				<c:if test="${not empty s_bizType}">
					<a class="delete_email"
						href='oa/oaRemindInformation!deletRec.do?no=${oaRemindInformation.no}&s_bizType=${s_bizType}'
						onclick='return confirm("是否需要删除");'><s:text
							name="opt.btn.delete" /></a>
				</c:if>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
<script>
function checkFrom(){
	var begB = $("#s_begCreatetime").val();
	var endB = $("#s_endCreatetime").val();
	if(endB!=""&&begB>endB){
		alert("结束时间不能早于开始时间。");
		return false;
	}
	
	var begD = $("#s_begTime").val();
	var endD = $("#s_endTime").val();
	if(endD!=""&&begD>endD){
		alert("结束时间不能早于开始时间。");
//			$("#s_endTime").focus();
		return false;
	}
	return true;
}
function sub(){
	if(checkFrom()==true){
	$("#oaremindinformationreclistform").attr("action","oaRemindInformation!recipientList.do");
	$("#oaremindinformationreclistform").submit();
	}
} 
	var LISTMAIL = {
		init : function() {
			for ( var e in LISTMAIL) {
				if ('init' != e && $.isFunction(LISTMAIL[e])) {
					LISTMAIL[e]();
				}
			}
		},
		initCheckbox : function() {
			$('#chk_all').change(function() {
				var $checked = $(this).prop('checked');
				$.each($('input:checkbox.chk_one'), function(index, checkbox) {
					$(this).prop('checked', $checked);
					if ($checked) {
						$(this).parent('span').addClass('checked');
					} else {
						$(this).parent('span').removeClass('checked');
					}
				});
			});
		},

		bindCheckboxClick : function() {
			disabledBtn($('#a_list_ids_delete'), true);
			$('#chk_all, input:checkbox.chk_one').change(function() {
				var msgcodes = LISTMAIL.getSelected();
				$('#hid_ids').val(msgcodes.join(','));
				if (0 < msgcodes.length) {

					disabledBtn($('#a_list_ids_delete'), false);
				} else {
					disabledBtn($('#a_list_ids_delete'), true);
				}
			});
		},

		getSelected : function() {
			var msgcodes = [];
			$.each($('input:checkbox.chk_one:checked'), function(index,
					checkbox) {
				msgcodes.push($(this).val());
			});

			return msgcodes;
		}
	};

	var disabledBtn = function($btn, disabled) {
		if (disabled) {
			$btn.addClass('disabled');
			$btn.removeClass('btn-danger');
			$btn.hide();

		} else {
			$btn.removeClass('disabled');
			$btn.addClass('btn-danger');
			$btn.show();
		}

	};
	$(document).ready(function() {
		LISTMAIL.init();
		if(gj()){
			showgaoji();
		}
	});
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
		if($("input[name=s_begTime]").val().trim()!=""&&$("input[name=s_begTime]").val()!=null){
			t=true;
		}
		if($("input[name=s_endTime]").val().trim()!=""&&$("input[name=s_endTime]").val()!=null){
			t=true;
		}
		if($("#s_reType").val()!=null&&$("#s_reType").val()!="")
				t=true;
		return t;
	}
</script>