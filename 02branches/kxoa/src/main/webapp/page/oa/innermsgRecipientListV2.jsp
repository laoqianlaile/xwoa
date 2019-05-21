<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
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
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title>我的消息</title>
</head>
<body>
	<fieldset  class="search">
		<legend class="headTitle">我的${cp:MAPVALUE("msgtype",s_msgtype) }</legend>
<div class="searchDiv">
		<s:form id="innermsg_form" action="innermsgRecipient" 
		 namespace="/oa" 
			style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">

			<input id="ids" type="hidden" name=ids />
			<input type="hidden" name="s_msgtype" id="s_msgtype" value="${s_msgtype }" />
			<input type="hidden" name="mailtype" value="${s_mailtype }" />
			<input type="hidden" id="s_mailtype" name="s_mailtype" value="${s_mailtype }" />
			<div class="searchArea">
			<table style="width: auto;" >

				<tr>
				<td class="searchBtnArea">
				<c:if test="${not empty objList }">
						<s:submit method="delete" onclick="return confirm('是否确定批量删除？')" id="a_list_mail_box_delete" cssClass="whiteBtnWide" value="批量删除"></s:submit>
							

						</c:if>
				<c:choose>
					<c:when test="${s_msgtype eq 'P' }">
					<input type="button" name="back" Class="whiteBtnWide"
					onclick="javascript:window.location.href='${pageContext.request.contextPath}/oa/innermsg!editDraft.do?s_msgtype=${s_msgtype }&s_mailtype=${s_mailtype }';" 
					value="发送消息" />
					</c:when>
					<c:when test="${s_msgtype eq 'F' }">
					<input type="button" name="back" Class="whiteBtnWide"
					onclick="javascript:window.location.href='${pageContext.request.contextPath}/oa/innermsg!add.do?s_msgtype=${s_msgtype }&s_mailtype=${s_mailtype }';" 
					value="发送文件" />
					</c:when>
					</c:choose>
				</td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">标题:</label>
				</td>
						<td class="searchCountArea">
				<input type="text" class="searchCondContent" name="s_msgtitle"
						value="${s_msgtitle}" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle">收件时间:</label>
				</td>
						<td class="searchCountArea">
				<input type="text" class="Wdate searchCondContent" id="s_begsenddate"
						<c:if test="${not empty s_begsenddate }"> value="${s_begsenddate}" </c:if>
						<c:if test="${empty s_begsenddate  }">value="${param['s_begsenddate'] }"</c:if>
						name="s_begsenddate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" id="s_endsenddate"
						<c:if test="${not empty s_endsenddate }"> value="${s_endsenddate }" </c:if>
						<c:if test="${empty s_endsenddate  }"> value="${param['s_endsenddate'] }" </c:if>
						name="s_endsenddate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">	
				</td>
				<td class="searchCondArea" ><div class="clickDiv" onclick="sub();"></div></td>
				</tr>
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
	
	<table cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td><ec:table
					action="${pageContext.request.contextPath}/oa/innermsgRecipient!list.do"
					items="objList" var="innermsgRecipient"
					imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
					retrieveRowsCallback="limit">

					<ec:row>
						<ec:column property="innermsgRecipient.id"
							title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
							sortable="false">
							<input class="chk_one" type="checkbox"
								id="${innermsgRecipient.id}" class="ecbox"
								value="${innermsgRecipient.id}">
						</ec:column>


						<ec:column property="innermsg.msgtitle" title="标题" style="width:170px">
							${innermsgRecipient.innermsg.msgtitle}
								<c:if test="${'U' eq innermsgRecipient.msgstate }">
							<i class="icon  icon-color icon-envelope-closed" style="margin-left:20px;"title="未读"></i>
							</c:if>
							<c:if test="${'R' eq innermsgRecipient.msgstate }">
								<i class="icon icon-color icon-envelope-open" style="margin-left:20px;"title="已读"></i>
							</c:if>
							<c:if test="${not empty innermsgRecipient.innermsg.msgannexs }">
						        <i class="icon icon-color icon-page"t style="margin-left:20px;"title="附件"></i>
							</c:if>
						</ec:column>
						<ec:column property="innermsg.sender" title="发件人"
							style="text-align:center">
			${cp:MAPVALUE("usercode", innermsgRecipient.innermsg.sender)}
			</ec:column>
			               <ec:column property="innermsg.receivename" title="收件人"
							style="text-align:center">
			               ${cp:MAPVALUE("usercode",innermsgRecipient.innermsg.receivename) }
			               </ec:column>

						<c:choose>
							<c:when test="${'O' eq param['s_mailtype'] }">
								<ec:column property="innermsg.senddate" title="发送时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsgRecipient.innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:when test="${'I' eq param['s_mailtype'] }">
								<ec:column property="innermsg.senddate" title="接收时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsgRecipient.innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:when>
							<c:otherwise>
								<ec:column property="innermsg.senddate" title="收件时间"
									style="text-align:center">
									<fmt:formatDate value="${innermsgRecipient.innermsg.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</ec:column>
							</c:otherwise>
						</c:choose>

						<c:set var="optlabel">
							<s:text name="opt.btn.collection" />
						</c:set>
						<ec:column property="opt" title="${optlabel}" sortable="false"
							style="text-align:center">
							<a class="check_email" href="${contextPath }/oa/innermsg!view.do?msgcode=${innermsgRecipient.innermsg.msgcode }&s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}">
								查看 </a>
						    <a class="delete_email" href="${contextPath }/oa/innermsgRecipient!delete.do?id=${innermsgRecipient.id }&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}&s_mailtype=${s_mailtype}"  onclick='return confirm("确认删除该记录?");'">
								删除 </a>
						    <a class="forward_email" href="${contextPath }/oa/innermsg!rewardMail.do?msgcode=${innermsgRecipient.innermsg.msgcode }&filecodes=${innermsgRecipient.innermsg.msgannexs}&s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}">
								转发</a>
						</ec:column>
					</ec:row>
				</ec:table></td>
		</tr>
	</table>







<%-- 	<%@ include file="/page/common/scripts.jsp"%> --%>

	<SCRIPT type="text/javascript">
	function checkFrom(){
		var begD = $("#s_begsenddate").val();
		var endD = $("#s_endsenddate").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	function sub(){
		if(checkFrom()==true){
		$("#innermsg_form").attr("action","innermsgRecipient!list.do");
		$("#innermsg_form").submit();
		}
	} 
	
			function changeoccur(v) {
				var msgtype = $("#s_msgtype").val();
				if ('I' == v) {
					var href = 'oa/innermsgRecipient!list.do?s_msgtype='+msgtype+'&s_mailtype=I';
					window.location.href=href;

				} else if ('O' == v) {
					var href = 'oa/innermsg!list.do?s_msgtype='+msgtype+'&s_mailtype=O';
					window.location.href=href;
			}
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
					$('#ids').val(msgcodes.join(','));
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

		$(document).ready(function() {

			LISTMAIL.init();
			
		});
	</SCRIPT>
</body>
</html>


