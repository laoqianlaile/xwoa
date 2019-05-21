<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset    class="search">

		<legend>讨论版管理</legend>

		<s:form id="forumListForm" namespace="/app"
			action="forum"
			method="post">
			<s:hidden id="hid_ids" name="ids" />
			<table cellpadding="0" cellspacing="0" align="left">
				<tr class="searchButton">
					<td class="addTd">讨论版名称：</td>
					<td width="180"><input type="text" class="span2"
						name="s_forumname" value="${s_forumname }" /></td>
					<td >
					<s:submit method="manager" key="opt.btn.query" cssClass="btn" />
<!-- 					<input type="button" class="btn" -->
<!-- 					 	target="submit"  -->
<!-- 						data-form="#forumListForm"  -->
<%-- 						data-href="${pageContext.request.contextPath}/app/forum!manager.do" --%>
<!-- 						value="查询" />  -->
					<s:submit method="edit" value="创建讨论版" cssClass="btn btnWide" />
<!-- 					<input type="button" -->
<!-- 						class="btn" target="submit" data-form="#forumListForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/app/forum!edit.do" --%>
<!-- 						value="创建讨论版"/>  -->
					<s:submit method="confirmApplyJoin" value="加入审批" cssClass="btn" />
<!-- 					<input type="button" class="btn" -->
<!-- 						target="submit" data-form="#forumListForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/app/forum!confirmApplyJoin.do" --%>
<!-- 						value="审批加入讨论版"/> -->
					<c:if test="${not empty objList }">
						<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="btn  btnDelete" value="批量删除"></s:submit>
					</c:if>
						
						</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/app/forum!manager.do"
		items="objList" var="forum"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>
        <ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" style="width:70px;text-align:right;">
			<input class="chk_one" type="checkbox" id="${forum.forumid}"
							class="ecbox" value="${forum.forumid}">
					
			</ec:column>
			<ec:column property="forumid" title="讨论版名称" style="text-align:center">

			 <a href=
					"${pageContext.request.contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&pageBoardCood=infoM" >
					${forum.forumname} </a>
<%-- 					${forum.forumname} --%>
			</ec:column>
			<ec:column property="mebernum" title="成员数" style="text-align:center" />
			<ec:column property="threadnum" title="帖子数量"
				style="text-align:center">
				${fn:length(forum.threads)} 
<%-- 				${empty forum.threadnum ? 0 : forum.threadnum} --%>
             </ec:column>
			<ec:column property="replynum" title="回复数量" style="text-align:left；">
					${empty forum.replynum ? 0 : forum.replynum}
				</ec:column>
			<ec:column property="forumStaffs" title="管理员" sortable="false"
				style="text-align:center">
				<c:set var="manager">
					<c:forEach var="u" items="${forum.forumStaffs}">
						<c:if test="${'1' eq u.userrole}">
                                    		${cp:MAPVALUE('usercode', u.cid.usercode)}
                        </c:if>
					</c:forEach>
				</c:set> ${empty manager ? '无' : manager}
			</ec:column>
			<ec:column property="forumstate" title="类型" style="text-align:left；">
				<c:choose>
					<c:when test="${'0' eq forum.forumstate }">停用</c:when>
					<c:when test="${'1' eq forum.forumstate }">启用</c:when>
					<c:when test="${'2' eq forum.forumstate }">隐藏</c:when>
				</c:choose>
			</ec:column>
			<ec:column property="createtime" title="创建时间"
				style="text-align:center">
				<fmt:formatDate value="${forum.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				<c:if test="${staffRole[forum.forumid] }">
					<a href="${contextPath }/app/forumStaff!list.do?s_forumid=${forum.forumid}"
						title="审批加入[${forum.forumname}]讨论版"> 
						 审批
					</a>
				</c:if>
					<a href="${contextPath }/app/forum!edit.do?forumid=${forum.forumid}"> 
						编辑
					</a>
					<a href='${contextPath }/app/forum!delete.do?forumid=${forum.forumid}'
					onclick='return confirm("确认删除?");'>删除</a>
					
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%>

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