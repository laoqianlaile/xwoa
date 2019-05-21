<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	<!-- 新样式文件 -->
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />	
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>	
</head>
<body>
	<fieldset class="search">
			<legend class="headTitle">
					会议资料			
			</legend>
			<div class="searchDiv">
			<s:form id="oaMeetingmaterial" action="oaMeetingmaterial" namespace="/oa" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			 <div class="searchArea">
				<table style="width: auto;">
				<tr>				
					<td class="searchTitleArea" >
					<label class="searchCondTitle">议程名称:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_meetingName" value="${s_meetingName }" /> 
					</td>
					<%-- <td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td>
					<label class="searchCondTitle">会议时间:</label>
					</td>
					<td class="searchCountArea" colspan="4">
					<input type="text" class="Wdate searchCondContent"  id="s_endTime" <c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
	                            <c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if> name="s_begTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
	                                                     至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
	                           <!--  <input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
								<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()"> -->	
	                        </td> --%>
						<td class="searchCondArea"><s:submit method="viewList" key="opt.btn.query" cssClass="btn" />
					</td>
					</tr>	
			</table>
			</div>	
			</s:form>
			</div>
		</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaMeetingmaterial!viewList.do"
		items="objList" var="foaMeetingmaterial"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="meetingName" title="议程名称"
				style="text-align:center" />

			<%-- <ec:column property="meetingAddress" title="会议地址"
				style="text-align:center">
				<c:choose>
					<c:when
						test="${fn:length(foaMeetingmaterial.meetingAddress) gt 10}">
						<c:out
							value="${fn:substring(foaMeetingmaterial.meetingAddress, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${foaMeetingmaterial.meetingAddress}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			 <ec:column property="meetingTime" title="会议时间" style="text-align:center" sortable="false" >
				<fmt:formatDate value='${ foaMeetingmaterial.meetingTime }' pattern='yyyy-MM-dd HH:mm' />
			 </ec:column>	 --%>
			  <ec:column property="meetingAttendees" title="参会人员" style="text-align:center">
				<c:choose>
					<c:when
						test="${fn:length(foaMeetingmaterial.meetingAttendees) gt 50}">
						<c:out value="${fn:substring(foaMeetingmaterial.meetingAttendees, 0, 50)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${foaMeetingmaterial.meetingAttendees}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:if test="${'F' ne showTag}">

					<a
						href="${pageContext.request.contextPath}/oa/oaMeetingmaterial!viewInfo.do?djId=${foaMeetingmaterial.djId}">
						查看详情</a>
				</c:if>
			</ec:column>
		</ec:row>
	</ec:table>
</body>

<script type="text/javascript">
	function getStringLen(Str) {
		var i, len, code;
		if (Str == null || Str == "")
			return 0;
		len = Str.length;
		for (i = 0; i < Str.length; i++) {
			code = Str.charCodeAt(i);
			if (code > 255) {
				len++;
			}
		}
		return len;
	}
	function adduser(){
		$('#boxType').val('tr_receive');
		var d = '${userjson}';
		if (d.trim().length) {
			
			selectPopWin(jQuery.parseJSON(d),
					$("#txa_innermsg_receive_name"),
					$("#txt_innermsg_receive_usercode"));
		}
	}
	function groupBy(type, o1, o2){
		
		if('unit' == type){
			createList(jQuery.parseJSON('${userjson}'),o1,o2);
		}else if('station' == type){
			createList(jQuery.parseJSON('${stationUsers}'),o1,o2);
		}if('unitLeader' == type){
			createList(jQuery.parseJSON('${unitLeaderuserjson}'),o1,o2);
		}
	}
	$(function() {
		$('#a_href').attr('height', window.screen.availHeight - 200);

		$('#chk_msgtype_a').change(function() {
			if ('checked' == $(this).attr('checked')) {
				$('#tr_receive').hide();
				$('#msgtype').val('A');

			} else {
				$('#tr_receive').show();
				$('#msgtype').val("${s_msgtype}");
			}
		});

	});
</script>
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
</html>