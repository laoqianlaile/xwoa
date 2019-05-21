<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title>资源公共类型</title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search" >
		<legend>
			资源类型
		</legend>

		<s:form action="oaCommonType" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
				<s:hidden id="hid_ids" name="ids" />
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd">编码:</td>
					<td><s:textfield name="s_coding"  value="%{#parameters['s_coding']}"/></td>
					<td class="addTd">名称:</td>
					<td><s:textfield name="s_comName" value="%{#parameters['s_comName']}"/>
					<input type="checkbox" id="s_isBoth" name="s_isBoth"
						value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用
					</td>
				</tr>
<!-- 				<tr> -->

<!-- 					<td class="addTd">创建日期:</td> -->
<!-- 					<td colspan="2"> -->
<!-- 				<input type="text" class="Wdate" name="s_begTime"  id="s_begTime" -->
<%-- 				value='${param['s_begTime'] }' --%>
<!-- 				onfocus="WdatePicker({minDate:'2000-1',maxDate:'2020-12'})"  -->
<!-- 				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期"> -->
<!-- 						至 -->
<!-- 				<input type="text" class="Wdate" name="s_endTime"  id="s_endTime" -->
<%-- 				value='${param['s_endTime'] }' --%>
<!-- 				onfocus="WdatePicker({minDate:'2000-1',maxDate:'2020-12'})"  -->
<!-- 				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期"> -->
<!-- 					</td> -->
<!-- 					<td><input type="checkbox" id="s_isBoth" name="s_isBoth" -->
<!-- 						value="true" -->
<%-- 						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>> --%>
<!-- 						包含禁用</td> -->
<!-- 				</tr> -->
				<tr   class="searchButton">


					<td colspan="4"><s:submit method="list" key="opt.btn.query" onclick="return checkFrom();"
							cssClass="btn" /> 
							<s:submit method="edit" key="opt.btn.new"
							cssClass="btn" /> 
							<c:if
							test="${not empty objList }">
							<s:submit method="deleteIds"
								onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
								cssClass="btn  btnDelete" value="批量删除"></s:submit>
						</c:if></td>
						

				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaCommonType!list.do" items="objList"
		var="fOaCommonType" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		 <ec:column property="no"
							title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
							sortable="false" width="2%">
							<c:if test="${ empty  fOaCommonType.state}">
							<input class="chk_one" type="checkbox" id="${fOaCommonType.no}"
								class="ecbox" value="${fOaCommonType.no}">
								</c:if>&nbsp;
	   </ec:column>
		
		
		
			<%-- 	         <ec:column property="no" title="流水号" style="text-align:center" /> --%>
			<ec:column property="state" title="启用状态" style="text-align:center">
			${cp:MAPVALUE('equState',fOaCommonType.state)}
			       <%-- ${USE_STATE[fOaCommonType.state]}    --%>
			</ec:column>
			<ec:column property="orderno" title="排序" style="text-align:center" />
			<ec:column property="coding" title="编码" style="text-align:center" />
			<ec:column property="comName" title="名称" style="text-align:center" />
			<%-- <ec:column property="creater" title="创建人" style="text-align:center" >
				        ${cp:MAPVALUE("usercode",fOaCommonType.creater) }  
            </ec:column>
            <ec:column property="creatertime" title="创建日期" style="text-align:center" >
				 <fmt:formatDate value='${fOaCommonType.creatertime}' pattern='yyyy-MM-dd ' />          
            </ec:column>  --%>
			<ec:column property="remark" title="描述" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(fOaCommonType.remark) gt 20}">
						<c:out value="${fn:substring(fOaCommonType.remark, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${fOaCommonType.remark}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a href='oa/oaCommonType!view.do?no=${fOaCommonType.no}'><s:text
						name="opt.btn.view" /> </a>
				<%-- 				<c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==fOaCommonType.creater}">	 --%>
				<a href='oa/oaCommonType!edit.do?no=${fOaCommonType.no}'><s:text
						name="opt.btn.edit" /> </a>
				<c:if test="${ empty  fOaCommonType.state}">
					<a class="delete" warn="确定要删除吗?" onclick='return confirm("${deletecofirm}？");'
						href="${pageContext.request.contextPath}/oa/oaCommonType!delete.do?no=${fOaCommonType.no}">
						删除</a>
				</c:if>
				<%-- 				</c:if>	 --%>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
<script>
function checkFrom(){
	var begD = $("#s_begTime").val();
	var endD = $("#s_endTime").val();
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
		$('#chk_all').change(
				function() {
					var $checked = $(this).prop('checked');
					$.each($('input:checkbox.chk_one'), function(index,
							checkbox) {
						$(this).prop('checked', $checked);
						if ($checked) {
							$(this).parent('span').addClass('checked');
						} else {
							$(this).parent('span')
									.removeClass('checked');
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