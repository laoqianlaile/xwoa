<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaCarinfo.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="search" >
		<legend>
				<c:choose>
					<c:when test="${ showTag eq 'F' }">车辆安排查询</c:when>
					<c:when test="${ empty showTag}">车辆信息</c:when>
				</c:choose>
		</legend>

		<s:form action="oaCarinfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<s:hidden id="hid_ids" name="ids" />
			<input type="hidden"  id="showTag" name="showTag"  value="${showTag}"/>
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd"><s:text name="oaCarinfo.carno" />:</td>
					<td><s:textfield name="s_carno" value="%{#parameters['s_carno']}"/></td>
					<td class="addTd"><s:text name="oaCarinfo.carType" />:</td>
					<td><select name="s_carType"
						id="s_carType" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('canDriveType')}">
								<option value="${row.value}"
									<c:if test="${s_carType eq row.value }">selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
					</select>
					<c:if test="${'F' ne showTag}">
					<input type="checkbox" id="s_isBoth" name="s_isBoth" value="true"<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用</td></c:if>
				</tr>

				<tr class="searchButton">
					
				<td colspan="4"><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					<c:if test="${'F' ne showTag}">
					<s:submit method="built" key="opt.btn.new" cssClass="btn" />
					<c:if test="${not empty objList }">
								<s:submit method="deleteIds" onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete" cssClass="btn  btnDelete" value="批量删除"></s:submit>
							</c:if>
					</c:if>
					</td>
				</tr>
				
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaCarinfo!list.do" items="objList" var="oaCarinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>
		<c:if test="${'F' ne showTag}">
			<ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" style="width:70px;text-align:center;">
				<c:if test="${ empty  oaCarinfo.isuse}">
						<input class="chk_one" type="checkbox" id="${oaCarinfo.djid}"
							class="ecbox" value="${oaCarinfo.djid}">&nbsp;
				</c:if>
					<%--  ${ROWCOUNT}. --%>
			</ec:column>
		</c:if>
		<c:if test="${'F' eq showTag}">
		<ec:column property="none"
				title='序号'
				sortable="false" style="width:70px;text-align:center;">
                 <%--  ${ROWCOUNT}. --%>
		</ec:column>
		</c:if>
			<%-- <c:set var="tdjid">
				<s:text name='oaCarinfo.djid' />
			</c:set>
			<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>
		
			<c:set var="tcarno">
				<s:text name='oaCarinfo.carno' />
			</c:set>
			<ec:column property="carno" title="${tcarno}"
				style="text-align:center" />

			<c:set var="tbrand">
				<s:text name='oaCarinfo.brand' />
			</c:set>
			<ec:column property="brand" title="${tbrand}"
				style="text-align:center" />

			<c:set var="tmodelType">
				<s:text name='oaCarinfo.modelType' />
			</c:set>
			<ec:column property="modelType" title="${tmodelType}"
				style="text-align:center" />

			<c:set var="tcarType">
				<s:text name='oaCarinfo.carType' />
			</c:set>
			<ec:column property="carType" title="${tcarType}"
				style="text-align:center" >
				${cp:MAPVALUE('canDriveType',oaCarinfo.carType) }
				</ec:column>
				
			<c:set var="tdriver">
			<s:text name='oaCarinfo.driver' />
			</c:set>
			<ec:column property="driver" title="${tdriver}"
				style="text-align:center" >
			
				${oaCarinfo.oaDriverInfo.name}
				</ec:column>
			
	        <c:set var="tisuse">状态
			</c:set>
			<ec:column property="isuse" title="${tisuse}"
				style="text-align:center" >
			${USE_STATE[oaCarinfo.isuse]} 	
			</ec:column>
		

		<%-- 	<c:set var="tdepno">
				<s:text name='oaCarinfo.depno' />
			</c:set>
			<ec:column property="depno" title="${tdepno}"
				style="text-align:center" >
				${cp:MAPVALUE('unitcode',oaCarinfo.depno)}
				</ec:column>

			<c:set var="tresponsibleDep">
				<s:text name='oaCarinfo.responsibleDep' />
			</c:set>
			<ec:column property="responsibleDep" title="${tresponsibleDep}"
				style="text-align:center" >
				${cp:MAPVALUE('unitcode',oaCarinfo.responsibleDep)}
				</ec:column>

			<c:set var="tresponsiblePersion">
				<s:text name='oaCarinfo.responsiblePersion' />
			</c:set>
			<ec:column property="responsiblePersion"
				title="${tresponsiblePersion}" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaCarinfo.responsiblePersion)}
				</ec:column> --%>

			

			<%-- <c:set var="tmotifytime">
				<s:text name='oaCarinfo.motifytime' />
			</c:set>
			<ec:column property="motifytime" title="${tmotifytime}"
				style="text-align:center" >
					<fmt:formatDate value='${oaCarinfo.motifytime}' pattern='yyyy-MM-dd HH:mm ' />
				</ec:column> --%>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				
				<a href='oa/oaCarinfo!generalOptView.do?djid=${oaCarinfo.djid}&nodeInstId=0&showTag=${showTag}'>查看使用详情</a>
				<c:if test="${'F' ne showTag}">
				<a
					href='oaCarinfo!view.do?djid=${oaCarinfo.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<a href='oaCarinfo!edit.do?djid=${oaCarinfo.djid}'><s:text
						name="opt.btn.edit" /></a>
				<c:if test="${ empty  oaCarinfo.isuse}">		
				<a href='oaCarinfo!delete.do?djid=${oaCarinfo.djid}'
					onclick='return confirm("${deletecofirm}?");'><s:text
						name="opt.btn.delete" /></a>
				</c:if>		
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