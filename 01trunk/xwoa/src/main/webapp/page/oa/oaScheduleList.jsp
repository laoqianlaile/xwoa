<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%-- <sj:head locale="zh_CN" /> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<html>
<head>
<title><s:text name="oaSchedule.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
		<c:choose>
		<c:when test="${s_sehType eq '2' }">领导日程安排</c:when>
		<c:when test="${s_sehType eq '1' }">日程安排</c:when>
		</c:choose>	
		</legend>

		<s:form action="oaSchedule" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<s:hidden id="s_sehType" name="s_sehType" value="%{s_sehType}"></s:hidden>
				<s:hidden id="hid_ids" name="ids" />

				<tr>
					<td class="addTd" ><s:text name="oaSchedule.itemtype"  />:</td>
					<td><select id="s_itemtype"
						name="s_itemtype"  width="180">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oaItemType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_itemtype}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd" width="180"><s:text name="oaSchedule.title" />:</td>
					<td><input type="text" name="s_title" id="s_title"
						value="${s_title}"></td>
				</tr>
				<c:if test="${2 eq s_sehType }">
					<tr>
						<td class="addTd">领导:</td>
						<td><input type="text" name="s_leaders" id="s_leaders"
							value="${s_leaders}"></td>
					</tr>
				</c:if>
				<tr>
					<td class="addTd">安排时间:</td>
					<td><input type="text" class="Wdate" id="s_planBeginTimeBegin"
						value="${s_planBeginTimeBegin}" id="s_planBeginTimeBegin"
						name="s_planBeginTimeBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />
						&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" class="Wdate" id="s_planBeginTimeEnd"
						value="${s_planBeginTimeEnd}" id="s_planBeginTimeEnd"
						name="s_planBeginTimeEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" /></td>
						
					<td class="addTd">重要度:</td>
					<td><select id="s_importantTag"
						name="s_importantTag"  width="180">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('IMP')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_importantTag}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>

				</tr>



				<tr class="searchButton">
					<td colspan="4">
					<c:if test="${not empty objList }">
							<s:submit method="deleteIds"
								onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
								cssClass="btn  btnDelete" value="批量删除"></s:submit>
						</c:if>
					<s:submit method="tabList" key="opt.btn.query" onclick="return checkFrom();"
							cssClass="btn" />
					<s:submit method="built" key="opt.btn.new" cssClass="btn" />
						</td>
				</tr>
			</table>
		</s:form>

	</fieldset>
	<ec:table action="oa/oaSchedule!tabList.do" items="objList" styleClass="ectableRegions tableRegion"
		var="oaSchedule" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="schno" 
				title='<input id="chk_all" type="checkbox"  class="ecbox" value="全选" name="quanxuan" />'
				sortable="false" width="2%" >
				<c:if
					test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaSchedule.creater and '1' eq oaSchedule.sehType  or '2' eq oaSchedule.sehType }">
					<input class="chk_one" type="checkbox" id="${oaSchedule.schno}"
						class="ecbox" value="${oaSchedule.schno}">
				</c:if> 
	                        </ec:column>
			<%-- 			<c:set var="tschno"> --%>
			<%-- 				<s:text name='oaSchedule.schno' /> --%>
			<%-- 			</c:set> --%>
			<%-- 			<ec:column property="schno" title="${tschno}" --%>
			<%-- 				style="text-align:center" /> --%>
			<%--
	         <c:set var="titemtype">
				<s:text name='oaSchedule.itemtype' />
			</c:set>
			<ec:column property="itemtype" title="${titemtype}"
				style="text-align:center" >
				${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}
				</ec:column>


			--%>
			<%--<c:set var="tplanBegTime">
				<s:text name='oaSchedule.planBegTime' />
			</c:set>
			--%>
		<%-- 	<ec:column property="planBegTime" title="开始时间"
				style="text-align:center">
				<fmt:formatDate value='${oaSchedule.planBegTime}'
					pattern='yyyy-MM-dd  HH:mm' />
			</ec:column>
 --%>
			<%--<c:set var="tplanEndTime">
				<s:text name='oaSchedule.planEndTime' />
			</c:set>
			--%>
			<%-- <ec:column property="planEndTime" title="结束时间"
								style="text-align:center">
								<fmt:formatDate value='${oaSchedule.planEndTime}'
									pattern='yyyy-MM-dd  HH:mm' />
							</ec:column> --%>
			<c:set var="ttitle">
				<s:text name='oaSchedule.title' />
			</c:set>
			<ec:column property="title" title="${ttitle}"
				style="text-align:center" width="25%">
				<input type="hidden" name="title" value="${oaSchedule.title}"/>
				<c:choose>
                    <c:when test="${fn:length(oaSchedule.title) > 30}">
                      <c:out value="${fn:substring(oaSchedule.title, 0, 30)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${oaSchedule.title}" />
                    </c:otherwise>
                </c:choose> 
<%-- 				[${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}]${oaSchedule.title} --%>
			</ec:column>
			<c:if test="${s_sehType eq '2' }">
			<ec:column property="leaders" title="领导" style="text-align:center">
				<input type="hidden" name="leaders" value="${oaSchedule.leaders}"/>
				<c:choose>
                   <c:when test="${fn:length(oaSchedule.leaders) > 30}">
                   <c:out value="${fn:substring(oaSchedule.leaders, 0, 30)}..." />
                    </c:when>
                  <c:otherwise>
                  <c:out value="${oaSchedule.leaders}" />
                  </c:otherwise>
                </c:choose> 
			</ec:column>
			</c:if>
			<c:if test="${s_sehType eq '1' }">
			<ec:column property="itemtype" title="事项类型" style="text-align:center">
				<input type="hidden" name="itemtype" value="${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}"/>
				${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}
			</ec:column>
			<ec:column property="importantTag" title="重要度"
				style="text-align:center">
				${cp:MAPVALUE('IMP',oaSchedule.importantTag)}
			</ec:column>
			</c:if>
			<ec:column property="planBegTime" title="安排时间"
				style="text-align:center">
					<fmt:formatDate value='${oaSchedule.planBegTime}' pattern='yyyy-MM-dd' />
				<c:if test="${not empty oaSchedule.dateTag and  oaSchedule.dateTag ne '' }">	
				(${oaSchedule.dateTag})
				</c:if>
			</ec:column>
		
			<%--	
			<c:set var="taddress">
				<s:text name='oaSchedule.address' />
			</c:set>
			<ec:column property="address" title="${taddress}"
				style="text-align:center" />	
				--%>
			<%--
			<c:set var="tremark">
				<s:text name='oaSchedule.remark' />
			</c:set>
			<ec:column property="remark" title="${tremark}"
				style="text-align:center" />--%>



			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${pageContext.request.contextPath}/oa/oaSchedule!view.do?schno=${oaSchedule.schno}&ec_p=${ec_p}&ec_crd=${ec_crd}&s_sehType=${s_sehType}'><s:text
						name="opt.btn.view" /></a>

				<!-- 							编辑自己的日程安排（个人），领导部分只能在领导菜单下编辑 -->

				<c:if
					test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaSchedule.creater and '1' eq oaSchedule.sehType}">
					<a
						href='${pageContext.request.contextPath}/oa/oaSchedule!edit.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'><s:text
							name="opt.btn.edit" /></a>
					<a
						href='${pageContext.request.contextPath}/oa/oaSchedule!delete.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'
						onclick='return confirm("${deletecofirm}?");'><s:text
							name="opt.btn.delete" /></a>
				</c:if>
				<c:if test="${'2' eq oaSchedule.sehType}">
					<a
						href='${pageContext.request.contextPath}/oa/oaSchedule!edit.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'><s:text
							name="opt.btn.edit" /></a>
					<a
						href='${pageContext.request.contextPath}/oa/oaSchedule!delete.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'
						onclick='return confirm("${deletecofirm}?");'><s:text
							name="opt.btn.delete" /></a>
				</c:if>

			</ec:column>

		</ec:row>
	</ec:table>
</body>
<script>
function checkFrom(){
	var begD = $("#s_planBeginTimeBegin").val();
	var endD = $("#s_planBeginTimeEnd").val();
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
</html>
