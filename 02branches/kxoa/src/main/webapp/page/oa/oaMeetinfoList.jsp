<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search" >

		<legend>
			<c:choose>
			<c:when test="${empty showTag }">会议室管理</c:when>
			<c:when test="${showTag eq 'F' }">会议室安排查询</c:when>
			</c:choose>
		</legend>

		<s:form id="oaMeetinfoForm" action="oaMeetinfo" namespace="/oa"
			method="post" style="margin-top:0;margin-bottom:5">
			
			<input type="hidden"  id="showTag" name="showTag"  value="${showTag}"/>
			<s:hidden id="hid_ids" name="ids" />
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd">名称:</td>
					<td ><input type="text" name="s_meetingName"
						value="${s_meetingName }" /></td>
				
					<td class="addTd">会议室类型:</td>
					<td>	<select id="s_meetingType" name="s_meetingType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('meetingType') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_meetingType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}"/>
								</option>
							</c:forEach>
					</select>
				
					<c:if test="${'F' ne showTag}">
					<input type="checkbox" id="s_isBoth" name="s_isBoth"
						value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用
					</c:if>	
						</td>
				</tr>
				<tr   class="searchButton">
					<td colspan="4">
					<c:if test="${'F' ne showTag}">
							<c:if test="${not empty objList }">
								<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="btn  btnDelete" value="批量删除"></s:submit>
							</c:if>
							</c:if>
					<s:submit method="list" key="opt.btn.query"
							cssClass="btn" /> 
							<c:if test="${'F' ne showTag}">
							<s:submit method="edit" key="opt.btn.new" cssClass="btn" />
						</c:if></td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaMeetinfo!list.do"
		items="objList" var="fOaMeetinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
		<c:if test="${'F' ne showTag}">
			<ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" style="2%">
				<c:if test="${ empty  fOaMeetinfo.isuse}">
						<input class="chk_one" type="checkbox" id="${fOaMeetinfo.djid}"
							class="ecbox" value="${fOaMeetinfo.djid}">
				</c:if>
<%-- 					 ${ROWCOUNT}. --%>
			</ec:column>
		</c:if>
		<%-- <c:if test="${'F' eq showTag}">
		<ec:column property="none"
				title='序号'
				sortable="false" style="width:70px;text-align:right;">
                  ${ROWCOUNT}.
		</ec:column>
		</c:if> --%>
			<%-- <ec:column property="isuse" title="启用标识" style="text-align:center">
					${USE_STATE[fOaMeetinfo.isuse]} 
			</ec:column> --%>
			<%-- <ec:column property="coding" title="编号" style="text-align:center" /> --%>
			<ec:column property="meetingName" title="名称" style="text-align:center"/>
		
      <%--       <ec:column property="chartList" title="使用情况" sortable="false">
				<div class="fixed-assets">
					<c:forEach items="${fOaMeetinfo.chartList }" var="chartInfo"
						varStatus="s">
						<c:if test="${!s.last}">
						<div class="${chartInfo.color}" style="width:${chartInfo.value}%"
							title=" ${cp:MAPVALUE('usercode',chartInfo.user)}  ${chartInfo.using}  起始时间：<fmt:formatDate
										value='${chartInfo.beginTime}' pattern='MM-dd  HH:mm:ss' />/结束时间：<fmt:formatDate
										value='${chartInfo.endTime}' pattern='MM-dd  HH:mm:ss' />"></div>
						</c:if>
						<c:if test="${s.last}">
							<div class="line current"
								style="width:2px; left:${chartInfo.value}%;" title="当前时间"></div>
						</c:if>
					</c:forEach>
				</div>
			</ec:column> --%>
			<ec:column property="meetingType" title="会议室类型"
				style="text-align:center">
				${cp:MAPVALUE("meetingType",fOaMeetinfo.meetingType)}
             </ec:column>
			<ec:column property="meetingNumber" title="容纳人数"
				style="text-align:left；">
				<%-- 					${cp:MAPVALUE("usercode",fEquipmentInfo.equipmentCharge)} --%>
			</ec:column>
			<%-- 			<ec:column property="meetingUseing" title="主要用途" --%>
			<%-- 				style="text-align:center" /> --%>
			<ec:column property="meetingAddress" title="地点"
				style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(fOaMeetinfo.meetingAddress) gt 10}">
						<c:out
							value="${fn:substring(fOaMeetinfo.meetingAddress, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${fOaMeetinfo.meetingAddress}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			<ec:column property="meetingInfloor" title="所在楼层"
				style="text-align:center" />
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<%-- <a href="${pageContext.request.contextPath}/oa/oaMeetApply!calendarView.do?meetingNo=${fOaMeetinfo.djid}">
					查看使用详情</a> --%>
				<a
					href='oa/oaMeetinfo!generalOptView.do?djid=${fOaMeetinfo.djid}&nodeInstId=0&showTag=${showTag}'>查看使用详情</a>
				<c:if test="${'F' ne showTag}">

					<a
						href="${pageContext.request.contextPath}/oa/oaMeetinfo!view.do?djid=${fOaMeetinfo.djid}">
						查看</a>
					<a
						href="${pageContext.request.contextPath}/oa/oaMeetinfo!edit.do?djid=${fOaMeetinfo.djid}">
						编辑</a>
					<c:if test="${fOaMeetinfo.isuse!='F' }">
						<a  onclick='return confirm("确定要禁用吗?");'
							href="${pageContext.request.contextPath}/oa/oaMeetinfo!unInUse.do?djid=${fOaMeetinfo.djid}">
							禁用</a>
					</c:if>
					<c:if test="${fOaMeetinfo.isuse!='T' }">
						<a
							href="${pageContext.request.contextPath}/oa/oaMeetinfo!inUse.do?djid=${fOaMeetinfo.djid}">
							启用</a>
					</c:if>
					<c:if test="${ empty  fOaMeetinfo.isuse}">
						<a class="delete"  onclick='return confirm("确定要删除吗?");'
							href="${pageContext.request.contextPath}/oa/oaMeetinfo!delete.do?djid=${fOaMeetinfo.djid}">
							删除</a>
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