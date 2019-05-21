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
		<title>
			<s:text name="oaRemindInformation.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend class="headTitle">
				 <c:choose>
				 <c:when test="${s_thesign eq '1' and searchtype eq 'T' }">已发送提醒</c:when>
				  <c:when test="${s_thesign eq '2' and searchtype eq 'F' }">待发送提醒</c:when>
				 </c:choose>
			</legend>
		<div class="searchDiv">	
			<s:form id="oaremindinformationform" method="post" action="oaRemindInformation" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
			<table style="width: auto;">
			  <s:hidden name="searchtype" value="%{searchtype}"></s:hidden>
			  <s:hidden id="hid_ids" name="ids" />
				<tr>
				<td class="searchBtnArea">
				<c:if test="${not empty objList }">
								<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除?')" id="a_list_ids_delete"
									cssClass="whiteBtnWide" value="批量删除"></s:submit>
					</c:if>
					<c:if test="${s_thesign eq '2'}">
								<s:submit method="built"  key="opt.btn.new" cssClass="whiteBtnWide"/>
				   </c:if> 
				
				</td>
					<td class="searchTitleArea">
					<label class="searchCondTitle"><s:text name="oaRemindInformation.reType" />:</label>
					</td>
					<td class="searchCountArea">
					<select name="s_reType" id="s_reType" class="searchCondContent" style="width: 120px;">
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
					  		 onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />	
					</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>

				</tr>
			</table>
			</div>
		</s:form>
		</div>
		</fieldset>

		<ec:table action="oa/oaRemindInformation!list.do" items="objList" var="oaRemindInformation"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			<ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
						<input class="chk_one" type="checkbox" id="${oaRemindInformation.no}"
							class="ecbox" value="${oaRemindInformation.no}">
			</ec:column>
				 <c:set var="tno"><s:text name='oaRemindInformation.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaRemindInformation.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />


				<%-- <c:set var="tthesign"><s:text name='oaRemindInformation.thesign' /></c:set>	
				<ec:column property="thesign" title="${tthesign}" style="text-align:center" />
 --%>
			<%-- 	<c:set var="tdjid"><s:text name='oaRemindInformation.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />
 --%>
				<c:set var="treType"><s:text name='oaRemindInformation.reType' /></c:set>	
				<ec:column property="reType" title="${treType}" style="text-align:center" >
				${cp:MAPVALUE("oa_superviseType",oaRemindInformation.reType) }
				</ec:column>

			<%-- 	<c:set var="tbegtime"><s:text name='oaRemindInformation.begtime' /></c:set>	
				<ec:column property="begtime" title="${tbegtime}" style="text-align:center" />

				<c:set var="tendtime"><s:text name='oaRemindInformation.endtime' /></c:set>	
				<ec:column property="endtime" title="${tendtime}" style="text-align:center" />
 --%>
				<c:set var="tcreater"><s:text name='oaRemindInformation.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				 ${cp:MAPVALUE("usercode",oaRemindInformation.creater)}
				</ec:column>

				<c:set var="tcreatetime"><s:text name='oaRemindInformation.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a class="check_email" href='oa/oaRemindInformation!view.do?no=${oaRemindInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<c:if test="${oaRemindInformation.thesign eq '2'}">
					<a class="bianji" href='oa/oaRemindInformation!edit.do?no=${oaRemindInformation.no}'><s:text name="opt.btn.edit" /></a>
					</c:if>
					<c:if test="${oaRemindInformation.thesign eq '2'}">
					<a class="delete_email" href='oa/oaRemindInformation!delete.do?no=${oaRemindInformation.no}&searchtype=F' 
							onclick='return confirm("${deletecofirm}？");'><s:text name="opt.btn.delete" /></a>
							</c:if>
					<c:if test="${oaRemindInformation.thesign eq '1'}">
					<a class="delete_email" href='oa/oaRemindInformation!delete.do?no=${oaRemindInformation.no}&searchtype=T' 
							onclick='return confirm("${deletecofirm}？");'><s:text name="opt.btn.delete" /></a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
<script>
function checkFrom(){
	var begD = $("#s_begCreatetime").val();
	var endD = $("#s_endCreatetime").val();
	if(endD!=""&&begD>endD){
		alert("结束时间不能早于开始时间。");
//			$("#s_endTime").focus();
		return false;
	}
	return true;
}
function sub(){
	if(checkFrom()==true){
	$("#oaremindinformationform").attr("action","oaRemindInformation!list.do");
	$("#oaremindinformationform").submit();
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
	});
</script>