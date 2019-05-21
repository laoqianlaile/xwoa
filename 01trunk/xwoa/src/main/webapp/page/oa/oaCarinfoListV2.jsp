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
<title><s:text name="oaCarinfo.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="search" >
		<legend class="headTitle">
				<c:choose>
					<c:when test="${ showTag eq 'F' }">车辆安排查询</c:when>
					<c:when test="${ empty showTag}">车辆信息</c:when>
				</c:choose>
		</legend>
<div class="searchDiv">
		<s:form id="oacarinfoform" action="oaCarinfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
			<s:hidden id="hid_ids" name="ids" />
			<input type="hidden"  id="showTag" name="showTag"  value="${showTag}"/>
			<table style="width: auto;">
				<tr>
				<td class="searchBtnArea">
				<c:if test="${'F' ne showTag}">
					<c:if test="${not empty objList }">
								<s:submit method="deleteIds" onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete" cssClass="whiteBtnWide" value="批量删除"></s:submit>
					</c:if>
					<s:submit method="built" key="opt.btn.new" cssClass="whiteBtnWide" />
					</c:if>
				</td>
				<td class="searchTitleArea">
				<input type="checkbox" id="s_isBoth" class="checkboxV2"
						name="s_isBoth" value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						<span style="color: #6b9bcf">包含禁用</span>
				</td>
				<td class="searchTitleArea">
				<label class="searchCondTitle"><s:text name="oaCarinfo.carType" />:</label>
				</td>
				 <td class="searchCountArea">
				<select id="s_carType" class="searchCondContent" style="width: 120px;"
						name="s_carType" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('canDriveType')}">
								<option value="${row.value}"
									<c:if test="${s_carType eq row.value }">selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
					</select>
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle"><s:text name="oaCarinfo.carno" />:</label>
				</td>
					<td class="searchCountArea">
				<input type="text" class="searchCondContent"
						name="s_carno" value="${s_carno }" />
				</td>
			<%-- 	<td>
				<s:radio name="s_rangeType" list="#{'W':'外租车','N':'内部车'}" listKey="key" listValue="value" value="%{#parameters['s_rangeType']}"></s:radio>
		<!-- 		<input type="radio" name="range_type" id="waizuche" value="W" onclick="sub();">外租车
				<input type="radio" name="range_type" id="beibuche" value="N" onclick="sub();">内部车 -->
				</td> --%>
			
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				
				</tr>
				
			</table>
			</div>
		</s:form>
		</div>
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
		
			<c:set var="tcarno" >
				<s:text name='oaCarinfo.carno' />
			</c:set>
			<ec:column property="carno" title="${tcarno}"
				style="text-align:center" >
				${oaCarinfo.carno}
				</ec:column>
           <%--  <ec:column property="carno" title="${tcarno}"
				style="text-align:center" /> --%>
			<c:set var="tbrand">
				<s:text name='oaCarinfo.brand' />
			</c:set>
			<ec:column property="brand" title="${tbrand}"
				style="text-align:center" />

			<%-- <c:set var="tmodelType">
				<s:text name='oaCarinfo.modelType' />
			</c:set>
			<ec:column property="modelType" title="${tmodelType}"
				style="text-align:center" /> --%>

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
			
	        <c:set var="ttelephone">联系方式
			</c:set> 
			<ec:column property="oaDriverInfo.telephone" title="${ttelephone}"
				style="text-align:center" >
<%-- 			${USE_STATE[oaCarinfo.isuse]} 	 --%>
			</ec:column>
		

		

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				
				<a class="xiangqing" href='oa/oaCarinfo!generalOptView.do?djid=${oaCarinfo.djid}&nodeInstId=0&showTag=${showTag}'>查看使用详情</a>
				<c:if test="${'F' ne showTag}">
				<a class="check_email"
					href='oaCarinfo!view.do?djid=${oaCarinfo.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<a class="bianji" href='oaCarinfo!edit.do?djid=${oaCarinfo.djid}'><s:text
						name="opt.btn.edit" /></a>
				<c:if test="${ empty  oaCarinfo.isuse}">		
				<a class="delete_email" href='oaCarinfo!delete.do?djid=${oaCarinfo.djid}'
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
/* 	function doChangeRange(){
		var range_type = $.trim($('input:radio[name="range_type"]:checked').val()); //取radio
		if(range_type=='W'){
			$(".xiangqing").hide();
			$(".check_email").hide();
			$(".bianji").hide();
		}
		if(range_type=='N'){
			$(".xiangqing").show();
			$(".check_email").show();
			$(".bianji").show();
		}	
	} */
	
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
	function sub(){
		$("#oacarinfoform").attr("action","oaCarinfo!list.do");
		$("#oacarinfoform").submit();
	} 
</script>