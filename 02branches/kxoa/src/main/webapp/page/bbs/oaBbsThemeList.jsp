<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaBbsDiscussion.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend> 帖子列表 </legend>

		<s:form action="vBbsThemeUser" namespace="/bbs" id="vBbsThemeUser"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input id="hid_layoutno" type="hidden" name="layoutnos" />
                 <!-- 				记录checkbox选中值 -->
				<input id="s_themenos" type="hidden" name="s_themenos" />
				<input id="s_layoutno" type="hidden" name="s_layoutno" value="${s_layoutno }" />
				<input id="s_layoutcode" type="hidden" name="s_layoutcode" value="${s_layoutcode }" />
				<tr>
					<%-- <td class="addTd">状态：</td>
					<td><select name="s_state">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('ThemeState')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_state }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select>
					</td> --%>
					<td class="addTd">主题:</td>
					<td><input type="text" name="s_sublayouttitle" value="${s_sublayouttitle }" /></td>
					<td class="addTd">帖子类型：</td>
					<td><select name="s_state">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('themeset')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_themeset }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select>
					</td>
					<td class="addTd">审核结果：</td>
					<td><select name="s_approvalresults">
							<option value="">---请选择---</option>
							<option value="T"
								<c:if test="${'T' eq s_approvalresults }">selected="selected" </c:if>>通过</option>
							<option value="F"
								<c:if test="${'F' eq s_approvalresults }">selected="selected" </c:if>>不通过</option>
					</select>
					</td>
				</tr>

				<tr>
				    <td class="addTd">创建时间：从</td>
					<td><input type="text" name="s_startCreateTime" value="${s_startCreateTime }"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
					<td class="addTd">到：</td>	
				    <td align="left"><input type="text" name="s_endCreateTime" value="${s_endCreateTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
					
					<td class="addTd">
					包含已删除:</td>
					<td><input type="checkbox" name="s_includeDel" value="T" <c:if test="${s_includeDel eq 'T'}">checked="checked"</c:if>></td>
				</tr>

				<tr class="searchButton">

					<td colspan="4"><c:if test="${not empty objList }">
							<s:submit method="updateStates"
								onclick="submitItemFrame('stateT');"
								id="a_list_mail_box_updateStateT" cssClass="btn" value="公开"></s:submit>
							<s:submit method="updateStates"
								onclick="submitItemFrame('stateF');"
								id="a_list_mail_box_updateStateF" cssClass="btn" value="不公开"></s:submit>
							<s:submit method="updateStates"
								onclick="submitItemFrame('approvalresultsT');"
								id="a_list_mail_box_approvalresultsT" cssClass="btn" value="通过"></s:submit>
							<s:submit method="updateStates"
								onclick="submitItemFrame('approvalresultsF');"
								id="a_list_mail_box_approvalresultsF" cssClass="btn" value="不通过"></s:submit>
							<s:submit method="updateStates"
								onclick="submitItemFrame('stateD');"
								id="a_list_mail_box_updateStateD" cssClass="btn btnDelete"
								value="删除"></s:submit>
						</c:if><s:submit method="themeReplayList" key="opt.btn.query" cssClass="btn" value="留言管理"/> <s:submit method="list" key="opt.btn.query" cssClass="btn" /></td>
				</tr>

			</table>
		</s:form>
	</fieldset>

	<ec:table action="bbs/vBbsThemeUser!list.do" items="objList"
		var="vBbsThemeUser" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="vBbsThemeUser.vid"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<input class="chk_one" type="checkbox" id="${vBbsThemeUser.themeno}"
					class="ecbox" value="${vBbsThemeUser.themeno}">
			</ec:column>
			<c:set var="tsublayouttitle">标题</c:set>
			<ec:column property="sublayouttitle" title="${tsublayouttitle}"
				style="text-align:left;width:40%">
				[${cp:MAPVALUE('themeset', vBbsThemeUser.themeset) }]<a
					href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeView.do?themeno=${vBbsThemeUser.themeno}'>
					<c:choose>
						<c:when test="${fn:length(vBbsThemeUser.sublayouttitle) gt 50 }">${fn:substring(vBbsThemeUser.sublayouttitle , 0, 50) }...</c:when>
						<c:otherwise>${vBbsThemeUser.sublayouttitle} </c:otherwise>
					</c:choose>

				</a>

			</ec:column>

			<c:set var="tcreater">
				<s:text name='oaBbsTheme.creater' />
			</c:set>
			<ec:column property="creater" title="${tcreater}"
				style="text-align:center">
				${cp:MAPVALUE('usercode', vBbsThemeUser.creater) }</ec:column>

			<c:set var="tcreatetime">
				<s:text name='oaBbsTheme.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center">
				<fmt:formatDate value="${vBbsThemeUser.createtime }"
					pattern="yyyy-MM-dd HH:mm" />
			</ec:column>

			<c:set var="tstate">
				<s:text name='oaBbsTheme.state' />
			</c:set>
			<ec:column property="state" title="${tstate}"
				style="text-align:center">
				<c:if test="${'D' eq vBbsThemeUser.state }">
					<span style="color: red;">
				</c:if>
				${cp:MAPVALUE('ThemeState', vBbsThemeUser.state) }
				<c:if test="${'D' eq vBbsThemeUser.state }">
					</span>
				</c:if>


			</ec:column>

			<c:set var="tapprovalresults">审核结果</c:set>
			<ec:column property="approvalresults" title="${tapprovalresults}"
				style="text-align:center">
				<c:if test="${'T' eq vBbsThemeUser.approvalresults }">通过</c:if>
				<c:if test="${'F' eq vBbsThemeUser.approvalresults }">
					<span style="color: red;">不通过</span>
				</c:if>
			</ec:column>

			<%-- <c:set var="tapproval">
				<s:text name='oaBbsTheme.approval' />
			</c:set>
			<ec:column property="approval" title="${tapproval}"
				style="text-align:center">
				${cp:MAPVALUE('usercode', vBbsThemeUser.approval) }</ec:column> --%>

			<%-- 				<c:set var="tapprovaltime"><s:text name='oaBbsTheme.approvaltime' /></c:set>	 --%>
			<%-- 				<ec:column property="approvaltime" title="${tapprovaltime}" style="text-align:center" /> --%>

			<%-- <c:set var="tthemeset">帖子类型</c:set>	
				<ec:column property="themeset" title="${tthemeset}" style="text-align:center" >
				${cp:MAPVALUE('themeset', vBbsThemeUser.themeset) }</ec:column> --%>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<c:if test="${vBbsThemeUser.state ne 'D'}">
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center;width:10%">
				<%-- <c:if test="${vBbsThemeUser.state ne 'T'}">
					<a
						href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeUpdate.do?themeno=${vBbsThemeUser.themeno}&state=T'>公开</a>
				</c:if>
				<c:if test="${vBbsThemeUser.state ne 'F'}">
					<a
						href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeUpdate.do?themeno=${vBbsThemeUser.themeno}&state=F'>不公开</a>
				</c:if>
				<c:if test="${vBbsThemeUser.approvalresults ne 'T'}">
					<a
						href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeUpdate.do?themeno=${vBbsThemeUser.themeno}&approvalresults=T'>通过</a>
				</c:if>
				<c:if test="${vBbsThemeUser.approvalresults ne 'F'}">
					<a
						href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeUpdate.do?themeno=${vBbsThemeUser.themeno}&approvalresults=F'>不通过</a>
				</c:if>
 --%>
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeEdit.do?themeno=${vBbsThemeUser.themeno}&edit=Y'><s:text
						name="opt.btn.edit" /></a>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeDelete.do?themeno=${vBbsThemeUser.themeno}'
					onclick='return confirm("${deletecofirm}该帖子?");'><s:text
						name="opt.btn.delete" /></a>
			</ec:column>
           </c:if>
           <c:if test="${vBbsThemeUser.state eq 'D'}">
              <ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center;width:10%">
				 <a
					href='${pageContext.request.contextPath}/bbs/oaBbsTheme!setup.do?themeno=${vBbsThemeUser.themeno}'
					onclick='return confirm("确定启用该帖子?");'>启用</a>
			  </ec:column>
           </c:if>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	function submitItemFrame(updateType) {

		var srForm = document.getElementById("vBbsThemeUser");
		if (updateType == 'stateT') {
			confirm("确定批量公开所选帖子吗?");
			srForm.action = '${contextPath}/bbs/oaBbsTheme!themeUpdates.do?state=T';
		} else if (updateType == 'stateF') {
			confirm("确定批量不公开所选帖子吗?");
			srForm.action = '${contextPath}/bbs/oaBbsTheme!themeUpdates.do?state=F';
		} else if (updateType == 'stateD') {
			confirm("确定批量删除所选帖子吗?");
			srForm.action = '${contextPath}/bbs/oaBbsTheme!themeUpdates.do?state=D';
		} else if (updateType == 'approvalresultsT') {
			confirm("确定批量通过所选帖子吗?");
			srForm.action = '${contextPath}/bbs/oaBbsTheme!themeUpdates.do?approvalresults=T';
		} else if (updateType == 'approvalresultsF') {
			confirm("确定批量不通过所选帖子吗?");
			srForm.action = '${contextPath}/bbs/oaBbsTheme!themeUpdates.do?approvalresults=F';
		}
		srForm.submit();
	}
	$(document).ready(function() {
		//批量操作
		LISTMAIL.init();

	});

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
			// 			disbtn(true);
			disabledBtn($('#a_list_mail_box_updateStateT'), true);
			disabledBtn($('#a_list_mail_box_updateStateF'), true);
			disabledBtn($('#a_list_mail_box_updateStateD'), true);
			disabledBtn($('#a_list_mail_box_approvalresultsT'), true);
			disabledBtn($('#a_list_mail_box_approvalresultsF'), true);
			$('#chk_all, input:checkbox.chk_one').change(function() {
				var msgcodes = LISTMAIL.getSelected();
				$('#hid_layoutno').val(msgcodes.join(','));
				$('#s_themenos').val($('#hid_layoutno').val());
				
				if (0 < msgcodes.length) {
					// 					disbtn(false);
					disabledBtn($('#a_list_mail_box_updateStateT'), false);
					disabledBtn($('#a_list_mail_box_updateStateF'), false);
					disabledBtn($('#a_list_mail_box_updateStateD'), false);
					disabledBtn($('#a_list_mail_box_approvalresultsT'), false);
					disabledBtn($('#a_list_mail_box_approvalresultsF'), false);
				} else {
					// 					disbtn(true);
					disabledBtn($('#a_list_mail_box_updateStateT'), true);
					disabledBtn($('#a_list_mail_box_updateStateF'), true);
					disabledBtn($('#a_list_mail_box_updateStateD'), true);
					disabledBtn($('#a_list_mail_box_approvalresultsT'), true);
					disabledBtn($('#a_list_mail_box_approvalresultsF'), true);
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
</script>
</html>
