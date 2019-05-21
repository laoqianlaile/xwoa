<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaDriverInfo.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="search" >
		<legend>
				司机信息
		</legend>

		<s:form action="oaDriverInfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<s:hidden id="hid_ids" name="ids" />
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd"><s:text name="oaDriverInfo.name" />:</td>
					<td>
					<input type="text" class="span2"
						name="s_name" value="${s_name }" />
					</td>
				
					<td class="addTd"><s:text name="oaDriverInfo.drLicenseNo" />:</td>
					<td>
					<input type="text" class="span2"name="s_drLicenseNo" value="${s_drLicenseNo }" />
					</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaDriverInfo.publicType" />:</td>
					<td>
					<select id="s_publicType"
						name="s_publicType" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('canDriveType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_publicType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					<input type="checkbox" id="s_isBoth"
						name="s_isBoth" value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用</td>
				</tr>
				
				<tr class="searchButton">
					<td colspan="4">
					<s:submit method="list" key="opt.btn.query" cssClass="btn" />
					<s:submit method="built" key="opt.btn.new" cssClass="btn" />
					<c:if test="${not empty objList }">
								<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="btn  btnDelete" value="批量删除"></s:submit>
					</c:if>
					</td>	
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaDriverInfo!list.do" items="objList"
		var="oaDriverInfo" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		<ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" style="width:70px;text-align:right;">
				<c:if test="${ empty  oaDriverInfo.state}">
						<input class="chk_one" type="checkbox" id="${oaDriverInfo.no}"
							class="ecbox" value="${oaDriverInfo.no}">
				</c:if>&nbsp;
					
			</ec:column>
			<%-- <c:set var="tcodenum">
				<s:text name='oaDriverInfo.codenum' />
			</c:set>
			<ec:column property="codenum" title="${tcodenum}"
				style="text-align:center" /> --%>

			<c:set var="tname">
				<s:text name='oaDriverInfo.name' />
			</c:set>
			<ec:column property="name" title="${tname}" style="text-align:center" />
			
			<c:set var="tsex">
				<s:text name='oaDriverInfo.sex' />
			</c:set>
            <ec:column property="sex" title="${tsex}" style="text-align:center" >
			${cp:MAPVALUE('sex',oaDriverInfo.sex)}
			</ec:column>
		
			<%-- <c:set var="tdrLicenseNo">
				<s:text name='oaDriverInfo.drLicenseNo' />
			</c:set>
			<ec:column property="drLicenseNo" title="${tdrLicenseNo}"
				style="text-align:center" />--%>
				
			<c:set var="tpublicType">
				<s:text name='oaDriverInfo.publicType' />
			</c:set>
			<ec:column property="publicType" title="${tpublicType}"
				style="text-align:center" >
				${cp:MAPVALUE('canDriveType',oaDriverInfo.publicType)}
				</ec:column>

			<%-- <c:set var="ttelephone">
				<s:text name='oaDriverInfo.telephone' />
			</c:set>
			<ec:column property="telephone" title="${ttelephone}"
				style="text-align:center" /> --%>

			<%-- <c:set var="tage">
				<s:text name='oaDriverInfo.age' />
			</c:set>
			<ec:column property="age" title="${tage}" style="text-align:center" /> --%>

			<c:set var="tmobile">
				<s:text name='oaDriverInfo.mobile' />
			</c:set>
			<ec:column property="mobile" title="${tmobile}"
				style="text-align:center" />

			<c:set var="tstate">
				<s:text name='oaDriverInfo.state' />
			</c:set>
			<ec:column property="state" title="${tstate}"
				style="text-align:center" >
<%-- 				${USE_STATE[oaDriverInfo.state]}  --%>
				${cp:MAPVALUE('equState',oaDriverInfo.state)}
				</ec:column>
				
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${contextPath }/oa/oaDriverInfo!view.do?no=${oaDriverInfo.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<a href='${contextPath }/oa/oaDriverInfo!edit.do?no=${oaDriverInfo.no}'><s:text
						name="opt.btn.edit" /></a>
				<c:if test="${ empty  oaDriverInfo.state}">		
				<a href='${contextPath }/oa/oaDriverInfo!delete.do?no=${oaDriverInfo.no}'
					onclick='return confirm("${deletecofirm}该司机信息?");'><s:text
						name="opt.btn.delete" /></a>
				</c:if>
						
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
<script>
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