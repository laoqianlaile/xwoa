<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="oaFeedback.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 情况汇总
			</legend>
			
			<s:form action="oaFeedback" id="oaFeedbackForm" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<s:hidden id="hid_ids" name="ids" />
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td class="addTd"><s:text name="oaFeedback.djid" />:</td>
						<td><s:textfield name="s_djid"   value="%{#parameters['s_djid']}"  /> </td>
				
						<td class="addTd"><s:text name="oaFeedback.title" />:</td>
						<td><s:textfield name="s_title"   value="%{#parameters['s_title']}"  /> </td>
					</tr>	
					   <tr>
						<td class="addTd">提交日期:</td>
						<td colspan="3">
					<input type="text" class="Wdate" id="s_begcreatertime"
                    style="height: 25px; width: 160px; border-radius: 3px; border: 1px solid #cccccc;" 
                    value="${param['s_begcreatertime'] }"name="s_begcreatertime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
								至					<input type="text" class="Wdate" id="s_endcreatertime"
                    style="height: 25px; width: 160px; border-radius: 3px; border: 1px solid #cccccc;" 
                    value="${param['s_endcreatertime'] }"name="s_endcreatertime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
								</td> 
				      </tr>
				      <tr  class="searchButton" >
						<td colspan="4">
							<s:submit method="listHZ" style="margin-left:60px" key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
					   <c:if test="${not empty objList }">
					   <input type="button" class="btn" id="a_list_ids_open" value="批量公开" 
					   onclick="changes('open');"/>
					   <input type="button" class="btn" id="a_list_ids_close" value="批量不公开" 
					   onclick="changes('close');"/>
					   </c:if>
					   
					   
					    </td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaFeedback!listHZ.do" items="objList" var="oaFeedback"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
           <ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />全选'
				sortable="false" width="2%">
						<input class="chk_one" type="checkbox" id="${oaFeedback.djid}"
							class="ecbox" value="${oaFeedback.djid}">
			</ec:column>
				<c:set var="tdjid"><s:text name='oaFeedback.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />


				<c:set var="ttitle"><s:text name='oaFeedback.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />


				<c:set var="tcreatertime"><s:text name='oaFeedback.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
				<fmt:formatDate value="${oaFeedback.creatertime}"
					pattern="yyyy-MM-dd" />
				</ec:column>

              <%--   <c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column> --%>
				
				<c:set var="treception"><s:text name='oaFeedback.reception' /></c:set>	
				<ec:column property="reception" title="${treception}" style="text-align:center">
				${cp:MAPVALUE("usercode",oaFeedback.reception) }
				</ec:column>
				
				<c:set var="tisopen"><s:text name='oaFeedback.isopen' /></c:set>	
				<ec:column property="isopen" title="${tisopen}" style="text-align:center">
				${cp:MAPVALUE("IS_BOOLEAN",oaFeedback.isopen) }
				</ec:column>
				
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${contextPath }/oa/oaFeedback!view.do?djid=${oaFeedback.djid}'>查看</a>
					<c:if test="${ empty oaFeedback.isopen  }">
				     <a onclick="changeItem('open','${oaFeedback.djid}')"
				     href='#' >公开</a>
				     <a onclick="changeItem('close','${oaFeedback.djid}')"
				     href='#'>不公开</a>
				    </c:if>
				    <c:if test="${ oaFeedback.isopen eq '1' }">
				     <a onclick="changeItem('close','${oaFeedback.djid}')"
				     href='#'>不公开</a>
				    </c:if>
				    <c:if test="${ oaFeedback.isopen eq '0' }">
				     <a onclick="changeItem('open','${oaFeedback.djid}')"
				     href='#'>公开</a>
				    </c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
<script>

function checkFrom(){
	var begD = $("#s_begcreatertime").val();
	var endD = $("#s_endcreatertime").val();
	if(endD!=""&&begD>endD){
		alert("结束时间不能早于开始时间。");
//			$("#s_endTime").focus();
		return false;
	}
	return true;
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
			disabledBtn($('#a_list_ids_open'), true);
			disabledBtn($('#a_list_ids_close'), true);
			$('#chk_all, input:checkbox.chk_one').change(function() {
				var msgcodes = LISTMAIL.getSelected();
				$('#hid_ids').val(msgcodes.join(','));
				if (0 < msgcodes.length) {

					disabledBtn($('#a_list_ids_open'), false);
					disabledBtn($('#a_list_ids_close'), false);
				} else {
					disabledBtn($('#a_list_ids_open'), true);
					disabledBtn($('#a_list_ids_close'), true);
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
	
	function changes(subType){
		var srForm  = document.getElementById("oaFeedbackForm");
		if(subType == 'open'){
			if (confirm("确认批量公开?")==true){
				srForm.action = '${contextPath }/oa/oaFeedback!saveOpenIds.do?isopen=1';
				}else{
				return false;
				}
		}else if(subType == 'close'){
			if (confirm("确认批量不公开?")==true){
				srForm.action = '${contextPath }/oa/oaFeedback!saveOpenIds.do?isopen=0';
				}else{
				return false;
				}
		}
		srForm.submit();
	}
	function changeItem(subType,djid){
		var srForm  = document.getElementById("oaFeedbackForm");
		if(subType == 'open'){
			if (confirm("确认公开?")==true){
				var url='${contextPath }/oa/oaFeedback!saveOpen.do?djid='+djid+'&isopen=1';
				srForm.action = url;
				}else{
				return false;
				}
		}else if(subType == 'close'){
			if (confirm("确认不公开?")==true){
				var url='${contextPath }/oa/oaFeedback!saveOpen.do?djid='+djid+'&isopen=0';
				srForm.action = url;
				}else{
				return false;
				}
		}
		srForm.submit();
	}
</script>