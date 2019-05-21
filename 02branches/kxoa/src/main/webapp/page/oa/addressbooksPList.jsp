<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
		个人通讯录
		</legend>

		<s:form id="addressbooksForm" action="addressbooks"
			style="margin-top:0;margin-bottom:5" method="post">
			<input type="hidden" id="hid_addrbookids" name="addrbookids" />
			<input type="hidden" id="type" name="type" value="P" />
			<input type="hidden" id="type" name="s_type" value="P" />
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
						<td class="addTd">姓名:</td>
						<td><input type="text" name="s_userName"
							value="${s_userName }" /></td>
			
						<td class="addTd">单位:</td>
						<td><input type="text" name="s_unitName"
							value="${s_unitName}" /></td>
						
				</tr>
					<tr>
					<td class="addTd">移动电话:</td>
						<td><input type="text" name="s_mobilephone"
							value="${s_mobilephone}" /></td>
						<td class="addTd">固定电话:</td>
						<td><input type="text" name="s_telphone"
							value="${s_telphone}" /></td>
					</tr>
				
				<tr class="searchButton">
				<td colspan="4">
					<c:if test="${not empty objList }">
							<s:submit method="deleteAdds"
									onclick="return confirm('是否确定批量删除？')"
									id="a_list_addrbookid_delete" cssClass="btn  btnDelete"
									value="批量删除"></s:submit>
						</c:if>
				<s:submit method="list" key="opt.btn.query"
							cssClass="btn" /> 
							<s:submit method="edit" key="opt.btn.new" cssClass="btn" />
							 <s:submit method="exportExcelP" value="导出通讯录"
							cssClass="btn btnWide" />
						
					</td>
				</tr>
				
			</table>
		</s:form>
	</fieldset>


	<ec:table
		action="${pageContext.request.contextPath}/oa/addressbooks!list.do"
		items="objList" var="fAddressbooks"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			
				<ec:column property="fAddressbooks.addrbookid"
					title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
					sortable="false" width="2%">
					<c:if test="${fAddressbooks.type eq 'P'}">
						<c:if
							test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==fAddressbooks.creater}">
							<input class="chk_one" type="checkbox"
								id="${fAddressbooks.addrbookid}" class="ecbox"
								value="${fAddressbooks.addrbookid}">
						</c:if>
					</c:if>

				</ec:column>
		
				<ec:column property="userName" title="姓名" style="text-align:center" width="8%">
				${fAddressbooks.userName }
				<c:if test="${not empty fAddressbooks.sex }">
				[
				</c:if>
				${cp:MAPVALUE("sex",fAddressbooks.sex)}
				<c:if test="${not empty fAddressbooks.sex }">
				]
				</c:if>
				</ec:column>
				<ec:column property="unitName" title="单位"
					style="text-align:center" width="12%">

				</ec:column>
				<ec:column property="mobilephone" title="移动电话"
					style="text-align:left；" width="8%">

				</ec:column> 

			<ec:column property="telphone" title="固定电话" style="text-align:center" width="8%">

			</ec:column>

			<ec:column property="remark" title="备注" style="text-align:center" width="12%">

			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center" width="6%">

				<a
					href="${pageContext.request.contextPath}/oa/addressbooks!view.do?addrbookid=${fAddressbooks.addrbookid}&s_type=${s_type}">
					查看</a>
					
				 <c:if
							test="${object.type eq 'P' and session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==fAddressbooks.creater}">	
				
					<a
						href="${pageContext.request.contextPath}/oa/addressbooks!edit.do?addrbookid=${fAddressbooks.addrbookid}&s_type=${s_type}">
						编辑</a>
					<a
						href="${pageContext.request.contextPath}/oa/addressbooks!delete.do?addrbookid=${fAddressbooks.addrbookid}&s_type=${s_type}"
						onclick='return confirm("确定要删除吗?");'>删除</a>
				</c:if>

			</ec:column>
		</ec:row>
	</ec:table>
</body>

</html>
<SCRIPT type="text/javascript">
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
				var $checked = $(this).attr('checked');
				$.each($('input:checkbox.chk_one'), function(index, checkbox) {
					$(this).attr('checked', 'checked' == $checked);
					if ('checked' == $checked) {
						$(this).parent('span').addClass($checked);
					} else {
						$(this).parent('span').removeClass($checked);
					}
				});
			});
		},

		bindCheckboxClick : function() {
			disabledBtn($('#a_list_addrbookid_delete'), true);
			$('#chk_all, input:checkbox.chk_one').change(function() {
				var msgcodes = LISTMAIL.getSelected();
				$('#hid_addrbookids').val(msgcodes.join(','));
				if (0 < msgcodes.length) {

					disabledBtn($('#a_list_addrbookid_delete'), false);
				} else {
					disabledBtn($('#a_list_addrbookid_delete'), true);
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
</SCRIPT>