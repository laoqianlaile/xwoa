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
</head>
<body>
	<fieldset class="search">
			<legend class="headTitle">
					会议资料		
			</legend>
			<div class="searchDiv">
			<s:form id="oaMeetingApply" action="oaMeetingApply" namespace="/oa" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			 <div class="searchArea">
				<table style="width: auto;">
				<tr>				
					<td class="searchTitleArea" >
					<label class="searchCondTitle">会议名称:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_meetApplyName" value="${s_meetApplyName }" /> 
					</td>
					<td class="searchCondArea"><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					<s:submit method="built" key="opt.btn.new" cssClass="btn" /></td>
					</tr>	
			</table>
			</div>	
			</s:form>
			</div>
		</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaMeetingApply!list.do"
		items="objList" var="foaMeetingapply"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column  title="序号"  property="rowcount" cell="rowCount" sortable="false" style="text-align:center" width="4%"/>	
			<ec:column property="meetApplyName" title="会议名称"
				style="text-align:center" />
			<ec:column property="attendLeaderName" title="参会领导"
				style="text-align:center">
				<c:choose>
					<c:when
						test="${fn:length(foaMeetingapply.attendLeaderName) gt 20}">
						<c:out
							value="${fn:substring(foaMeetingapply.attendLeaderName, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${foaMeetingapply.attendLeaderName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			 <ec:column property="meetApplyAddress" title="会议地址"
				style="text-align:center">
				<c:choose>
					<c:when
						test="${fn:length(foaMeetingapply.meetApplyAddress) gt 10}">
						<c:out
							value="${fn:substring(foaMeetingapply.meetApplyAddress, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${foaMeetingapply.meetApplyAddress}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			 <ec:column property="meettime" title="会议时间" style="text-align:center" sortable="false" >
				<fmt:formatDate value='${ foaMeetingapply.meetApplytime }' pattern='yyyy-MM-dd HH:mm' />
			 </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

					
					<a
						href="${pageContext.request.contextPath}/oa/oaMeetingApply!edit.do?mId=${foaMeetingapply.mId}">
						编辑</a>
					<a  
						href="${pageContext.request.contextPath}/oa/oaMeetingApply!oaMeetingView.do?mId=${foaMeetingapply.mId}">
						查看</a>
					<a class="delete" onclick='return confirm("确定要删除吗?");'
						href="${pageContext.request.contextPath}/oa/oaMeetingApply!delete.do?mId=${foaMeetingapply.mId}">
						删除</a>
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
	$("#txa_innermsg_receive_name").click(
			function() {
				$('#boxType').val('tr_receive');
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#txa_innermsg_receive_name"),
							$("#txt_innermsg_receive_usercode"));
				}
				;
			});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
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