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
	<fieldset  class="search" >

		<legend class="headTitle">
			<c:choose>
			<c:when test="${empty showTag }">会议室管理</c:when>
			<c:when test="${showTag eq 'F' }">会议室安排查询</c:when>
			</c:choose>
		</legend>
<div class="searchDiv">
		<s:form id="oaMeetinfoForm" action="oaMeetinfo" namespace="/oa"
			method="post" style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
			<input type="hidden"  id="showTag" name="showTag"  value="${showTag}"/>
			<s:hidden id="hid_ids" name="ids" />
			<table style="width: auto;">
				<tr>
				<c:if test="${'F' ne showTag}">
				<td class="searchBtnArea">
							<c:if test="${not empty objList }">
								<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="whiteBtnWide" value="批量删除"></s:submit>
							</c:if>
							<s:submit method="edit" key="opt.btn.new" cssClass="whiteBtnWide" />
				</td>
				</c:if>
					<td class="searchTitleArea">
					<c:if test="${'F' ne showTag}">
					<input type="checkbox" id="s_isBoth" name="s_isBoth"
						value="true" class="checkboxV2"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						<label style="color:#6b9bcf">包含禁用</label>
					</c:if>	
					</td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">会议室类型:</label>
					</td>
					<td class="searchCountArea">
					<select id="s_meetingType" name="s_meetingType" class="searchCondContent" style="width: 120px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('meetingType') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_meetingType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}"/>
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle">名称:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" type="text" name="s_meetingName"
						value="${s_meetingName }" />
				</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" >搜索</div></td>
				</tr>
			</table>
			</div>
		</s:form>
		</div>
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
				<c:if test="${not empty  fOaMeetinfo.isuse}">
						<input class="chk_one" type="checkbox" id="${fOaMeetinfo.djid}"
							class="ecbox" value="${fOaMeetinfo.djid}">
				</c:if>
			</ec:column>
		</c:if>
			<ec:column property="meetingName" title="名称" style="text-align:center"/>
		
			<ec:column property="meetingType" title="会议室类型"
				style="text-align:center">
				${cp:MAPVALUE("meetingType",fOaMeetinfo.meetingType)}
             </ec:column>
			<ec:column property="meetingNumber" title="容纳人数"
				style="text-align:left；">
			</ec:column>
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
				<a class="xiangqing"
					href='oa/oaMeetinfo!generalOptView.do?djid=${fOaMeetinfo.djid}&nodeInstId=0&showTag=${showTag}'>查看使用详情</a>
				<c:if test="${'F' ne showTag}">

					<a class="check_email"
						href="${pageContext.request.contextPath}/oa/oaMeetinfo!view.do?djid=${fOaMeetinfo.djid}">
						查看</a>
					<a class="bianji"
						href="${pageContext.request.contextPath}/oa/oaMeetinfo!edit.do?djid=${fOaMeetinfo.djid}">
						编辑</a>
					<c:if test="${fOaMeetinfo.isuse!='F' }">
						<a  class="jinyong" onclick='return confirm("确定要禁用吗?");'
							href="${pageContext.request.contextPath}/oa/oaMeetinfo!unInUse.do?djid=${fOaMeetinfo.djid}">
							禁用</a>
					</c:if>
					<c:if test="${fOaMeetinfo.isuse!='T' }">
						<a class="qiyong"
							href="${pageContext.request.contextPath}/oa/oaMeetinfo!inUse.do?djid=${fOaMeetinfo.djid}">
							启用</a>
					</c:if>
					<c:if test="${ empty  fOaMeetinfo.isuse}">
						<a  class="delete_email"  onclick='return confirm("确定要删除吗?");'
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
	function sub(){
		$("#oaMeetinfoForm").attr("action","oaMeetinfo!list.do");
		$("#oaMeetinfoForm").submit();
	} 
</script>