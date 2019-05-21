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
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle"> 内部通讯录 </legend>
		<div class="searchDiv">
		<s:form id="addressbooksForm" action="addressbooks"
			style="margin-top:0;margin-bottom:5" method="post">
			<input type="hidden" id="hid_addrbookids" name="addrbookids" />
			<input type="hidden" id="type" name="type" value="C" />
			<input type="hidden" id="type" name="s_type" value="C" />
			<input type="hidden" id="type" name="bodycode" value="${bodycode}" />
			<div class="searchArea">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
				
				<td class="searchBtnArea">
				
				
				
				
				
				</td>
				
				
				
				
				
				
					<td class="addTd">姓名:</td>
					<td><input type="text" name="s_userName"
						value="${s_userName }" /></td>

					<td class="addTd">部门:</td>
					<td><input type="text" name="s_deptName" value="${s_deptName}" />
					
                    <!-- 只有顶级部门时才显示 -->
					<c:if test="${bodycode eq '1'}">
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构	
					</c:if>
					</td>

				</tr>
				<tr>
					<td class="addTd">移动电话:</td>
					<td><input type="text" name="s_mobilephone"
						value="${s_mobilephone}" /></td>
					<td class="addTd">固定电话:</td>
					<td><input type="text" name="s_telphone" value="${s_telphone}" /></td>
				</tr>

				<tr class="searchButton">
					<td colspan="5"><s:submit method="oaList" key="opt.btn.query"
							cssClass="btn" /> 
							 <c:if
							test="${cp:CHECKUSEROPTPOWER('GRBGGRTXL', 'isGG')}">
							<s:submit method="edit" key="opt.btn.new"
							cssClass="btn" /> 
							</c:if>
							 <s:submit method="exportExcel" value="导出通讯录"
							cssClass="btn btnWide" /> 
							<c:if test="${not empty objList }">

							<s:submit method="deleteAdds"
								onclick="return confirm('是否确定批量删除？')"
								id="a_list_addrbookid_delete" cssClass="btn  btnDelete"
								value="批量删除"></s:submit>
						     </c:if>
						     
						   
						</td>
				</tr>

			</table>
			</div>
		</s:form>
		</div>
	</fieldset>


	<ec:table
		action="${pageContext.request.contextPath}/oa/addressbooks!oaList.do"
		items="objList" var="fAddressbooks"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>

			<ec:column property="fAddressbooks.addrbookid"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<c:if test="${cp:CHECKUSEROPTPOWER('GRBGGRTXL', 'isGG') }">
						<input class="chk_one" type="checkbox"
							id="${fAddressbooks.addrbookid}" class="ecbox"
							value="${fAddressbooks.addrbookid}">
				</c:if>

			</ec:column>

			<ec:column property="userName" title="姓名" style="text-align:center">
				${fAddressbooks.userName }
				<c:if test="${not empty fAddressbooks.sex }">
				[
				</c:if>
				${cp:MAPVALUE("sex",fAddressbooks.sex)}
				<c:if test="${not empty fAddressbooks.sex }">
				]
				</c:if>
			</ec:column>
			<ec:column property="deptName" title="部门" style="text-align:left；">
          ${cp:MAPVALUE('unitcode',fAddressbooks.deptName)}
			</ec:column>
			<ec:column property="mobilephone" title="移动电话" style="text-align:left；">

			</ec:column>

			<ec:column property="telphone" title="固定电话" style="text-align:center">

			</ec:column>

			<ec:column property="remark" title="备注" style="text-align:center">

			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				<a
					href="${pageContext.request.contextPath}/oa/addressbooks!view.do?addrbookid=${fAddressbooks.addrbookid}&s_type=C&bodycode=${bodycode}">
					查看</a>

				 <c:if test="${cp:CHECKUSEROPTPOWER('GRBGGRTXL', 'isGG')}">

					<a
						href="${pageContext.request.contextPath}/oa/addressbooks!edit.do?addrbookid=${fAddressbooks.addrbookid}&s_type=C&bodycode=${bodycode}">
						编辑</a>
					<a
						href="${pageContext.request.contextPath}/oa/addressbooks!delete.do?addrbookid=${fAddressbooks.addrbookid}&s_type=C&bodycode=${bodycode}"
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