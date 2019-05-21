<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script type="text/javascript" data-main="${pageContext.request.contextPath }/scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
	<%-- <sj:head locale="zh_CN" /> --%>
		<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
	</script>
<title><s:text name="oaSchedule.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search">	
					<legend>
				 			领导日程安排
					</legend>		
					<s:form action="oaSchedule" namespace="/oa"
						style="margin-top:0;margin-bottom:5">
						<table cellpadding="0" cellspacing="0" align="center">
							<s:hidden id="s_sehType" name="s_sehType" value="%{s_sehType}"></s:hidden>
							<tr>
								
									<td class="addTd"><s:text name="oaSchedule.itemtype" />:</td>
									<td><select data-rel="chosen" 
									name="s_itemtype" >
										<option value="">---请选择---</option>
										<c:forEach var="row" items="${cp:DICTIONARY('oaItemType')}">
											<option value="${row.datacode}"
												<c:if test="${row.datacode==s_itemtype}"> selected="selected"</c:if>>
												<c:out value="${row.datavalue}" />
											</option>
										</c:forEach>
								</select></td>
								<td class="addTd"><s:text name="oaSchedule.title" />:</td>
								<td><input type="text" name="s_title" id="s_title"
									value="${s_title}"> 
								</td>
									
							</tr>	
							<tr>
								<td class="addTd">领导:</td>
								<td><input type="text" class="autocomplete"   name="s_leaders"  data-token-limit='1'
							      	data-pre-populate='${populate}' 
									data-url="${pageContext.request.contextPath}/oa/oaSchedule!selectUser.do" />
								</td>
								<td class="addTd">时间:</td>
								<td colspan="3">
								<input type="text" class="Wdate"  id="s_planBeginTimeBegin" <c:if test="${not empty s_planBeginTimeBegin }"> value="${s_planBeginTimeBegin}" </c:if>
	                            <c:if test="${empty s_planBeginTimeBegin  }">value="${param['s_planBeginTimeBegin'] }"</c:if> name="s_planBeginTimeBegin"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     至
						        <input type="text" class="Wdate"  id="s_planBeginTimeEnd" <c:if test="${not empty s_planBeginTimeEnd }"> value="${s_planBeginTimeEnd }" </c:if>
	                            <c:if test="${empty s_planBeginTimeEnd  }">value="${param['s_planBeginTimeEnd'] }"</c:if> name="s_planBeginTimeEnd" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
								</td>
							</tr>					

							<tr  class="searchButton">
								<td colspan="4"><s:submit method="leaderTabList" key="opt.btn.query" onclick="return checkFrom();"
										cssClass="btn" /></td>

							</tr>
						</table>
					</s:form>
					</fieldset>

	<ec:table action="oa/oaSchedule!leaderTabList.do" items="objList" styleClass="ectableRegions tableRegion"
		var="oaSchedule"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>

			<%-- 			<c:set var="tschno"> --%>
			<%-- 				<s:text name='oaSchedule.schno' /> --%>
			<%-- 			</c:set> --%>
			<%-- 			<ec:column property="schno" title="${tschno}" --%>
			<%-- 				style="text-align:center" /> --%>
			<%--     <c:set var="titemtype">
				<s:text name='oaSchedule.itemtype' />
			</c:set>
			<ec:column property="itemtype" title="${titemtype}"
				style="text-align:center" >
				${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}
				</ec:column> --%>

			<%-- <c:set var="tplanBegTime">
								<s:text name='oaSchedule.planBegTime' />
							</c:set>
							<ec:column property="planBegTime" title="${tplanBegTime}"
								style="text-align:center">
								<fmt:formatDate value='${oaSchedule.planBegTime}'
									pattern='yyyy-MM-dd  HH:mm' />
							</ec:column>

							<c:set var="tplanEndTime">
								<s:text name='oaSchedule.planEndTime' />
							</c:set>
							<ec:column property="planEndTime" title="${tplanEndTime}"
								style="text-align:center">
								<fmt:formatDate value='${oaSchedule.planEndTime}'
									pattern='yyyy-MM-dd  HH:mm' />
							</ec:column>
 --%>

			<c:set var="ttitle">
				<s:text name='oaSchedule.title' />
			</c:set>
			<ec:column property="title" title="${ttitle}"
				style="text-align:center">
				<input type="hidden" name="title" value="${oaSchedule.title}"/>
				<c:choose>
                    <c:when test="${fn:length(oaSchedule.title) > 26}">
                      <c:out value="[${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}]${fn:substring(oaSchedule.title, 0, 30)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="[${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}]${oaSchedule.title}" />
                    </c:otherwise>
                </c:choose>
				
				</ec:column>

			<ec:column property="leaders" title="领导" style="text-align:center">
				<input type="hidden" name="leaders" value="${oaSchedule.leaders}" />
				<c:choose>
					<c:when test="${fn:length(oaSchedule.leaders) > 30}">
						<c:out value="${fn:substring(oaSchedule.leaders, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaSchedule.leaders}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="planBegTime" title="安排时间"
				style="text-align:center">
				<fmt:formatDate value='${oaSchedule.planBegTime}'
					pattern='yyyy-MM-dd' />
				<c:if
					test="${not empty oaSchedule.dateTag and  oaSchedule.dateTag ne '' }">	
				(${oaSchedule.dateTag})
				</c:if>
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
					href='${pageContext.request.contextPath}/oa/oaSchedule!view.do?schno=${oaSchedule.schno}&ec_p=${ec_p}&ec_crd=${ec_crd}&s_sehType=${s_sehType}'><s:text
						name="opt.btn.view" /></a>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script>
	function checkFrom() {
		var begD = $("#s_planBeginTimeBegin").val();
		var endD = $("#s_planBeginTimeEnd").val();
		if (endD != "" && begD > endD) {
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
