<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link rel="stylesheet"
	href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<title>我的消息</title>
</head>
<body>
	<fieldset>
		<legend>公告</legend>

		<form id="innermsg_form"
			action="${pageContext.request.contextPath}/oa/innermsg!list.do"
			method="post" data-changeSubmit="true">
			<input id="s_msgtype" type="hidden" name="s_msgtype" value="A" />
		</form>
	</fieldset>

	<table cellpadding="0" cellspacing="0" align="left">
		<tr>
			
			<td><ec:table
					action="${pageContext.request.contextPath}/oa/innermsg!list.do"
					items="objList" var="innermsg"
					imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
					retrieveRowsCallback="limit">

					<ec:row>
						<ec:column property="innermsg.msgtitle" title="标题"
							style="text-align:center">
							<c:choose>
								<c:when test="${fn:length(innermsg.msgtitle) gt 10 }">${fn:substring(innermsg.msgtitle , 0, 10) }...</c:when>
								<c:otherwise>${innermsg.msgtitle} </c:otherwise>
							</c:choose>
						</ec:column>
						<ec:column property="innermsg.sender" title="内容"
							style="text-align:center">
			            ${innermsg.msgcontent}
		 	             </ec:column>

						<c:choose>
							<c:when test="${'O' eq param['s_mailtype'] }">
								<ec:column property="innermsg.msgcontent" title="发送时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:when test="${'I' eq param['s_mailtype'] }">
								<ec:column property="innermsg.msgcontent" title="接收时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:otherwise>
								<ec:column property="innermsg.msgcontent" title="时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:otherwise>
						</c:choose>




						<c:set var="optlabel">
							<s:text name="opt.btn.collection" />
						</c:set>
						<ec:column property="opt" title="${optlabel}" sortable="false"
							style="text-align:center">
							<a target="submit"
								data-href="${contextPath }/oa/innermsg!view.do?msgcode=${innermsg.msgcode}">
								查看 </a>
						</ec:column>
					</ec:row>
				</ec:table></td>
		</tr>
	</table>







	<%-- 	<%@ include file="/page/common/charisma-js.jsp"%>  --%>
	<%@ include file="/page/common/scripts.jsp"%>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>

	<SCRIPT type="text/javascript">
	<!--
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : function(event, treeId, treeNode, clickFlag) {
					if (treeNode.isParent) {
						return;
					}

					$('#innermsg_form').attr('action', treeNode.action);
					$('#innermsg_form').submit();
				}
			}
		};

		var LISTMAIL = {
			init : function() {
				for ( var e in LISTMAIL) {
					if ('init' != e && $.isFunction(LISTMAIL[e])) {
						LISTMAIL[e]();
					}
				}
			},
			initCheckbox : function() {
				$('#chk_all').change(
						function() {
							var $checked = $(this).attr('checked');

							$.each($('input:checkbox.chk_one'), function(index,
									checkbox) {
								$(this).attr('checked', 'checked' == $checked);
								if ('checked' == $checked) {
									$(this).parent('span').addClass($checked);
								} else {
									$(this).parent('span')
											.removeClass($checked);
								}
							});
						});
			},

			bindCheckboxClick : function() {
				disabledBtn($('#a_list_mail_box_delete'), true);
				$('#chk_all, input:checkbox.chk_one').change(function() {
					var msgcodes = LISTMAIL.getSelected();
					$('#hid_msgcodes').val(msgcodes.join(','));
					if (0 < msgcodes.length) {

						disabledBtn($('#a_list_mail_box_delete'), false);
					} else {
						disabledBtn($('#a_list_mail_box_delete'), true);
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
			} else {
				$btn.removeClass('disabled');
				$btn.addClass('btn-danger');
			}

		};

		
	//-->
	</SCRIPT>
</body>
</html>


