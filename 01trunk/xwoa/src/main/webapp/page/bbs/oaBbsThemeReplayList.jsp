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
	<fieldset class="search" style="width:97%;">
		<legend> 帖子回复列表 </legend>

		<s:form action="vBbsThemeUser" namespace="/bbs" id="vBbsThemeReplay"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input id="hid_replayno" type="hidden" name="replaynos" />
				<input id="s_layoutno" type="hidden" name="s_layoutno"
					value="${s_layoutno }" />
				<input id="s_layoutcode" type="hidden" name="s_layoutcode"
					value="${s_layoutcode }" />
				<input id="s_themenos" type="hidden" name="s_themenos"
					value="${s_themenos }" />
				<tr>
					<td class="addTd">关键字:</td>
					<td><input type="text" name="s_messagecomment"
						value="${s_messagecomment }" /></td>
					<td class="addTd">包含已删除:</td>
					<td><input type="checkbox" name="s_includeDel" value="T"
						<c:if test="${s_includeDel eq 'T'}">checked="checked"</c:if>></td>


					<td><c:if test="${not empty themeReplayList }">
							
							<input type=button id="a_list_mail_box_updateStateD"  class="btn btnDelete" value="删除" style="display: none;"/>
<%-- 							<s:submit  id="a_list_mail_box_updateStateD" cssClass="btn btnDelete" value="删除"/> --%>
						</c:if>
						<s:submit method="themeReplayList" key="opt.btn.query"
							cssClass="btn" /></td>
				</tr>

			</table>
		</s:form>
	</fieldset>

	<ec:table action="bbs/vBbsThemeUser!getThemeReplayList.do"
		items="themeReplayList" var="vBbsThemeReplay"
		imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="vBbsThemeReplay.replayId"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<c:if test="${'1' eq vBbsThemeReplay.type}">
					<input class="chk_one" type="checkbox"
						id="${vBbsThemeReplay.replayId}" class="ecbox"
						value="${vBbsThemeReplay.no}">
				</c:if>
				<c:if test="${'2' eq vBbsThemeReplay.type}">
					<input class="chk_one" type="checkbox"
						id="${vBbsThemeReplay.replayId}" class="ecbox"
						value="${vBbsThemeReplay.lyno}">
				</c:if>
			</ec:column>
			<c:set var="tmessageComment">详情</c:set>
			<ec:column property="messageComment" title="${tmessageComment}"
				style="text-align:left;width:90%">
				<c:if test="${'D' eq vBbsThemeReplay.state }">
					<p style="color: red;">
				</c:if>
				${cp:MAPVALUE("usercode",vBbsThemeReplay.creater) } 于
				<fmt:formatDate value="${vBbsThemeReplay.creatertime }"
					pattern="yyyy-MM-dd HH:mm:ss" /> 回复 ：   ["${vBbsThemeReplay.subLayoutTitle}""]					 
								<a target="navTab" external="true" title="查看帖子"
									rel="external_XXYDTLB"
									href='${pageContext.request.contextPath}/bbs/oaBbsTheme!view.do?themeno=${vBbsThemeReplay.djid}'
									style="text-decoration: none;"> <c:choose>
										<c:when
											test="${fn:length(vBbsThemeReplay.themeTitle) gt 50 }">${fn:substring(vBbsThemeReplay.themeTitle , 0, 50) }...</c:when>
										<c:otherwise>${vBbsThemeReplay.themeTitle} </c:otherwise>
									</c:choose></a>
							 </br> 
					 <c:choose>
						<c:when test="${fn:length(vBbsThemeReplay.messageComment) gt 50}">${fn:substring(vBbsThemeReplay.messageComment , 0, 50) }...</c:when>
						<c:otherwise> "${vBbsThemeReplay.messageComment}" </c:otherwise>
					</c:choose>   
				</a>

				<c:if test="${'D' eq vBbsThemeReplay.state }">
					</p>
				</c:if>
			</ec:column>

				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center;width:6%">
					<c:if test="${vBbsThemeReplay.state ne 'D'}">
					<c:set var="deletecofirm">
						<s:text name="label.delete.confirm" />
					</c:set>
					
					<c:if test="${'1' eq vBbsThemeReplay.type}">
					<span class="deleteSinger"  value="${vBbsThemeReplay.no}">刪除</span>
				   </c:if>
				   <c:if test="${'2' eq vBbsThemeReplay.type}">
				     <span class="deleteSinger"  value="${vBbsThemeReplay.lyno}">刪除</span>
					</c:if>
					</c:if>
				</ec:column>
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
//单个删除
$(".deleteSinger").click(function(target){
	$("#hid_replayno").val($(this).val());

	$("#a_list_mail_box_updateStateD").click();
});
	//批量删除
	$("#a_list_mail_box_updateStateD").click(function() {
		var srForm = document.getElementById("vBbsThemeReplay");
		if (confirm("确定批量删除所选回复吗?")) {
			var hid_replayno = $("#hid_replayno").val();
			$
					.ajax({
						type : "post",
						url : "${contextPath}/bbs/oaLeaveMessage!deleteMessage.do",
						data : {
							"replaynos" : hid_replayno
						},
						dataType : "json",
						success : function(data) {
							srForm.action = '${contextPath}/bbs/vBbsThemeUser!themeReplayList.do';
							srForm.submit();
						},
						error : function() {
							alert("删除留言失败，刷新后重试！");
						}
					});
				
		
		}
		
		
	});
 
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
			disabledBtn($('#a_list_mail_box_updateStateD'), true);
			$('#chk_all, input:checkbox.chk_one').change(function() {
				var msgcodes = LISTMAIL.getSelected();
				$('#hid_replayno').val(msgcodes.join(','));
				if (0 < msgcodes.length) {
					disabledBtn($('#a_list_mail_box_updateStateD'), false);
				} else {
					disabledBtn($('#a_list_mail_box_updateStateD'), true);
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
