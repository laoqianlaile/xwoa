<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<script>
if (!window.Config) {
	window.Centit = {
		contextPath : '${contextPath}'	
	};
}
</script>
<html>
<head>
		<%-- <sj:head locale="zh_CN" /> --%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<script type="text/javascript" data-main="../scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<!-- <link rel="stylesheet" -->
<%-- 	href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" --%>
<!-- 	type="text/css"> -->

<title>草稿箱</title>
</head>
<body>
	<fieldset  class="search">
		<legend>我的${cp:MAPVALUE("msgtype",s_msgtype) }</legend>
		<s:form id="innermsg_form" namespace="/oa"
			action="innermsg" style="margin-top:0;margin-bottom:5"
			method="post" data-changeSubmit="true">

			<input id="hid_msgcodes" type="hidden" name="msgcodes" /> 
			<input type="hidden" name="s_msgtype" id="s_msgtype" value="${s_msgtype }" />
			<input type="hidden" name="mailtype" value="${s_mailtype }" />
			<table cellpadding="0" cellspacing="0" align="center">
			
<!-- 				<tr> -->
<!-- 					<td>邮箱类型:</td> -->
<!-- 					<td> -->
<%-- 					<s:radio value="%{#parameters['s_mailtype']}" list="#{'I':'收件箱' ,'O':'发件箱'}" onclick="changeoccur(this.value);" name="s_miltype" id="s_mailtype" listKey="key" listValue="value"></s:radio> --%>
<!-- 					</td> -->
						
<!-- 				</tr> -->
			  <tr>
			  <td class="addTd">标题:</td>
					<td>
					<input type="text" class="span2"
						name="s_msgtitle" value="${s_msgtitle }" />
					</td>
					<td class="addTd">发件时间:</td>
                     <td colspan="2">
                     <input type="text" class="Wdate" 
						value="${param['s_begsenddate']}"name="s_begsenddate" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
								至								
					<input type="text" class="Wdate" 
						value="${param['s_endsenddate']}"name="s_endsenddate" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
								
					 </td> 	
				</tr>
			
				
				<tr   class="searchButton">				
					<td colspan="5">
					<s:submit method="list" key="opt.btn.query" cssClass="btn" />
					<input type="hidden" name="s_mailtype" value="${ s_mailtype}"/>
					<s:submit method="add" cssClass="btn" value='发送消息'/>
<!-- 					<input type="button" class="btn" target="submit" style="cursor:pointer;" -->
<!-- 						data-form="#innermsg_form" -->
<%-- 						data-href="${pageContext.request.contextPath}/oa/innermsg!add.do" --%>
<%-- 						value='发送${cp:MAPVALUE("msgtype",s_msgtype) } '>  --%>
					
					
					
					<c:if test="${not empty objList }">
					<s:submit method="deleteMsg" onclick="return confirm('是否确定批量删除？')" id="a_list_mail_box_delete" cssClass="btn  btnDelete" value="批量删除"></s:submit>

				</c:if>
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<table cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td><ec:table
					action="${pageContext.request.contextPath}/oa/innermsg!list.do"
					items="objList" var="innermsg"
					imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
					retrieveRowsCallback="limit">

					<ec:row>
						<ec:column property="innermsgRecipient.id"
							title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
							sortable="false">
							<input class="chk_one" type="checkbox" id="${innermsg.msgcode}"
								class="ecbox" value="${innermsg.msgcode}">
						</ec:column>


						<ec:column property="innermsg.msgtitle" title="标题"  sortable="true"
							style="text-align:center;width:170px;">
							<c:choose>
								<c:when test="${fn:length(innermsg.msgtitle) gt 10 }">${fn:substring(innermsg.msgtitle , 0, 10) }...</c:when>
								<c:otherwise>${innermsg.msgtitle} </c:otherwise>
							</c:choose>
							<c:if  test="${not empty innermsg.msgannexs}">
						     	<i class="icon icon-color icon-page" style="margin-left: 15px;" title="附件" />
							</c:if>
							
						</ec:column>
						<ec:column property="innermsg.sender" title="发件人"
							style="text-align:center">
			               ${cp:MAPVALUE("usercode",innermsg.sender) }
			               </ec:column>
			               <ec:column property="innermsg.receivename" title="收件人"
							style="text-align:center">
			               ${cp:MAPVALUE("usercode",innermsg.receivename) }
			               </ec:column>

						<c:choose>
							<c:when test="${'O' eq param['s_mailtype'] }">
								<ec:column property="innermsg.senddate" title="发送时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:when test="${'I' eq param['s_mailtype'] }">
								<ec:column property="innermsg.senddate" title="接收时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:otherwise>
								<ec:column property="innermsg.senddate" title="发件时间"
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
							<a href="${contextPath }/oa/innermsg!view.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}">
								查看 </a>
							<a target="submit" data-form="#innermsg_form" class="delete" warn="确定要删除吗?" style="cursor:pointer;"
								data-href="${contextPath }/oa/innermsg!deleteMsg.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}">
								删除 </a>
							<a target="submit" data-form="#innermsg_form"style="cursor:pointer;"
								data-href="${contextPath }/oa/innermsg!editDraft.do?msgcode=${innermsg.msgcode}&mailtype=${s_mailtype}">
								编辑 </a>
<!-- 							   <a target="submit" data-form="#innermsg_form"  style="cursor:pointer;" -->
<%-- 								data-href="${contextPath }/oa/innermsg!rewardMail.do?msgcode=${innermsg.msgcode}">转发</a> --%>

						</ec:column>
					</ec:row>
				</ec:table></td>
		</tr>
	</table>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>

	<SCRIPT type="text/javascript">
// 	function changeoccur(v) {
// 		var msgtype = $("#s_msgtype").val();
// 		if ('I' == v) {
// 			var href = 'oa/innermsgRecipient!list.do?s_msgtype='+msgtype+'&s_mailtype=I';
// 			window.location.href=href;

// 		} else if ('O' == v) {
// 			var href = 'oa/innermsg!list.do?s_msgtype='+msgtype+'&s_mailtype=O';
// 			window.location.href=href;
// 	}
// 	}
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
				$btn.hide();

			} else {
				$btn.removeClass('disabled');
				$btn.addClass('btn-danger');
				$btn.show();
			}

		};

		var zNodes = [ {
			id : 1,
			pId : 0,
			name : '我的${cp:MAPVALUE("msgtype",s_msgtype)}',
			open : true
		}, {
			id : 11,
			pId : 1,
			name : "收件箱",
			action : '${contextPath}/oa/innermsgRecipient!list.do?s_mailtype=I'
		}, {
			id : 12,
			pId : 1,
			name : "发件箱",
			action : '${contextPath}/oa/innermsg!list.do?s_mailtype=O'
		} 
		];

		$(document).ready(function() {
			LISTMAIL.init();
// 			debugger;
		});
	//-->
	</SCRIPT>
</body>
</html>


