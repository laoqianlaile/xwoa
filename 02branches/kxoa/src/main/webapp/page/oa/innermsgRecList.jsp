<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		


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
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<title>我的消息</title>
</head>
<body>
	<fieldset class="search">
		<legend>我的${cp:MAPVALUE("msgtype",s_msgtype) }</legend>

		<s:form id="innermsg_form" namespace="/oa" action="innermsg"
			style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">

			<input id="hid_msgcodes" type="hidden" name="msgcodes" />
			<input type="hidden" name="s_msgtype" id="s_msgtype"
				value="${s_msgtype }" />
			<input type="hidden" name="s_mailtype" value="${s_mailtype }" />
<%-- 			<input type="hidden" name="s_msgstate" value="${s_msgstate }" /> --%>
			<input type="hidden" name="mailtype" value="${s_mailtype }" />
			<input type="hidden" name="isShow" value="${isShow}" />
			<table cellpadding="0" cellspacing="0" align="center">
			
			<c:if test="${s_showtype eq 'F'}">
					<input type="hidden" name="s_showtype" id="s_showtype" value="${s_showtype }" />
					<input type="hidden" name="s_msgstate" id="s_msgstate" value="${s_msgstate }" />
			</c:if>
				
				<c:if test="${s_showtype ne 'F'}">	
				<tr>
					<td class="addTd">标题:</td>
					<td><input type="text" class="span2" name="s_msgtitle"
						value="${s_msgtitle }" /></td>

					
	              <td class="addTd">阅读状态:</td>
					<td><select name="s_msgstate" id="s_msgstate" >
							<option value="" <c:if test="${empty s_msgstate}">selected="selected"</c:if>>---全部--</option>

							<option value="U" <c:if test="${s_msgstate eq 'U' }">selected="selected"</c:if> label="未读">未读</option>

							<option value="R" <c:if test="${s_msgstate eq 'R' }">selected="selected"</c:if> label="已读">已读</option>
					</select></td>
					
					
				</tr>
				
				<tr>
				<c:choose>
						<c:when test="${'O' eq param['s_mailtype'] }">
							<td class="addTd">时间:</td>
						</c:when>
						<c:when test="${'I' eq param['s_mailtype'] }">
							<td class="addTd">接收时间:</td>
						</c:when>
						<c:otherwise>
							<td class="addTd">发件时间:</td>
						</c:otherwise>
					</c:choose>
				<td colspan="3">
					<input type="text" class="Wdate"  id="s_begsenddate" <c:if test="${not empty s_begsenddate }"> value="${s_begsenddate}" </c:if>
	                            <c:if test="${empty s_begsenddate  }">value="${param['s_begsenddate'] }"</c:if> name="s_begsenddate"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     至
						        <input type="text" class="Wdate"  id="s_endsenddate" <c:if test="${not empty s_endsenddate }"> value="${s_endsenddate }" </c:if>
	                            <c:if test="${empty s_endsenddate  }">value="${param['s_endsenddate'] }"</c:if> name="s_endsenddate" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">

					</td>	
					
				
					
				</tr>
				</c:if>
				
				<c:if test="${s_showtype eq 'F'}">	
				<tr>
					<td class="addTd">标题:</td>
					<td><input type="text" class="span2" name="s_msgtitle"
						value="${s_msgtitle }" /></td>

					<c:choose>
						<c:when test="${'O' eq param['s_mailtype'] }">
							<td class="addTd">时间:</td>
						</c:when>
						<c:when test="${'I' eq param['s_mailtype'] }">
							<td class="addTd">接收时间:</td>
						</c:when>
						<c:otherwise>
							<td class="addTd">发件时间:</td>
						</c:otherwise>
					</c:choose>

					<td colspan="3">
					<input type="text" class="Wdate"  id="s_begsenddate" <c:if test="${not empty s_begsenddate }"> value="${s_begsenddate}" </c:if>
	                            <c:if test="${empty s_begsenddate  }">value="${param['s_begsenddate'] }"</c:if> name="s_begsenddate"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     至
						        <input type="text" class="Wdate"  id="s_endsenddate" <c:if test="${not empty s_endsenddate }"> value="${s_endsenddate }" </c:if>
	                            <c:if test="${empty s_endsenddate  }">value="${param['s_endsenddate'] }"</c:if> name="s_planBeginTimeEnd" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">

					</td>
				</tr>
				</c:if>
				
				<tr class="searchButton">
					<td colspan="5">
					<c:if test="${not empty objList }">
					<s:submit method="deleteRec"
								onclick='return confirm("彻底删除后该记录不可恢复，还继续执行删除操作吗?");'
								id="a_list_mail_box_delete" cssClass="btn btnDelete"
								value="彻底删除"></s:submit>
					<c:if test="${s_mailtype eq 'T'}">
							
							<s:submit method="cancleDropRec"
								onclick='return confirm("还原后可在收件箱中查看该记录，还继续执行还原操作吗?");'
								id="a_list_mail_box_cancleDrop" cssClass="btn btnDelete"
								value="批量还原"></s:submit>	
                    </c:if>
                    <c:if test="${s_mailtype eq 'I'}">
							<s:submit method="dropRec"
								onclick="return confirm('是否确定批量删除？')"
								id="a_list_mail_box_drop" cssClass="btn btnDelete"
								value="批量删除"></s:submit>
                    </c:if>
						</c:if>
					<s:submit method="recList" key="opt.btn.query" onclick="return checkFrom();"
							cssClass="btn" /> <%-- 					<input type="hidden" name="s_mailtype" value="${ s_mailtype}"/> --%>
						<c:choose>
							<c:when test="${s_msgtype eq 'P' }">
								<s:submit method="editDraft" cssClass="btn" value='发送邮件' />
							</c:when>
							<c:when test="${s_msgtype eq 'F' }">
								<s:submit method="add" cssClass="btn" value='发送文件' />
							</c:when>
						</c:choose> <!-- 					<input type="button" class="btn" target="submit" style="cursor:pointer;" -->
						<!-- 						data-form="#innermsg_form" --> <%-- 						data-href="${pageContext.request.contextPath}/oa/innermsg!add.do" --%>
						<%-- 						value='发送${cp:MAPVALUE("msgtype",s_msgtype) } '>  --%>



						</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/innermsg!recList.do" styleClass="ectableRegions tableRegion"
		items="objList" var="innermsg"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">

		<ec:row>
			<ec:column property="innermsg.msgcode"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<input class="chk_one" type="checkbox" id="${innermsg.msgcode}"
					class="ecbox" value="${innermsg.msgcode}">
			</ec:column>
            <ec:column property="none"  title='<span class="icon icon-blue icon-envelope-closed" title="未读" /></span><span class="icon icon-color icon-page" title="附件" /></span>'
			
            sortable="false" style="border-right:none;text-align:left;width:4%;">
            	<c:if test="${'U' eq innermsg.msgstate}">
					<span class="icon icon-blue icon-envelope-closed" title="未读" /></span>
				</c:if>
				<c:if test="${'U' ne innermsg.msgstate}">
				<span class="icon icon-blue icon-envelope-open" title="已读" /></span>
				</c:if>
				<c:if test="${not empty innermsg.msgannexs}">
					<span class="icon icon-color icon-page" title="附件"></span>
				</c:if>
				
            </ec:column>
			<ec:column property="innermsg.msgtitle" title="标题" sortable="true"
				style="text-align:left;">
				<input  type="hidden"  value="${innermsg.msgtitle}" />
			
				<c:choose>
					<c:when test="${fn:length(innermsg.msgtitle) gt 30 }">${fn:substring(innermsg.msgtitle , 0, 30) }...</c:when>
					<c:otherwise>${innermsg.msgtitle} </c:otherwise>
				</c:choose>
				
			</ec:column>
			<ec:column property="innermsg.sender" title="发件人"
				style="text-align:center" width="25%">
			               ${cp:MAPVALUE("usercode",innermsg.sender) }
			               </ec:column>

			<c:choose>
				<c:when test="${'O' eq param['s_mailtype'] }">
					<ec:column property="innermsg.senddate" title="时间"
						style="text-align:center" width="20%">
						<fmt:formatDate value="${innermsg.senddate}"
							pattern="yyyy-MM-dd" />
					</ec:column>
				</c:when>
				<c:when test="${'I' eq param['s_mailtype'] }">
					<ec:column property="innermsg.senddate" title="接收时间"
						style="text-align:center" width="20%">
						<fmt:formatDate value="${innermsg.senddate}"
							pattern="yyyy-MM-dd" />
					</ec:column>
				</c:when>
				<c:otherwise>
					<ec:column property="innermsg.senddate" title="发件时间"
						style="text-align:center" width="20%">
						<fmt:formatDate value="${innermsg.senddate}"
							pattern="yyyy-MM-dd" />
					</ec:column>
				</c:otherwise>
			</c:choose>




			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center" width="20%">
				<a class="check_email"
					href="${contextPath }/oa/innermsg!view.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}&s_msgstate=${s_msgstate}">
					查看 </a>
					
				
				<c:if test="${s_mailtype eq 'I'}">	
					<a class="delete_email"
					href="${contextPath }/oa/innermsg!dropRec.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}&s_mailtype=${s_mailtype}&s_msgstate=${s_msgstate}&s_showtype=${s_showtype}"
					onclick='return confirm("确认删除该记录?");'> 删除 </a>
				</c:if>
				<c:if test="${s_mailtype eq 'T'}">
				<a class="restore_email"
					href="${contextPath }/oa/innermsg!cancleDropRec.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}&s_mailtype=${s_mailtype}"
					onclick='return confirm("还原后可在收件箱中查看该记录，还继续执行还原操作吗?");'> 还原 </a>
				<a class="delete_email_completely"
					href="${contextPath }/oa/innermsg!deleteRec.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&mailtype=${s_mailtype}&s_mailtype=${s_mailtype}"
					onclick='return confirm("彻底删除后该记录不可恢复，还继续执行删除操作吗?");'> 彻底删除 </a>
				</c:if>
				
				<c:if test="${s_mailtype eq 'I'}">
					<a class="forward_email"
						href="${contextPath }/oa/innermsg!rewardMail.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}">转发</a>
				</c:if>
				<c:if test="${s_mailtype eq 'D'}">
					<a class="edit_email"
						href="${contextPath }/oa/innermsg!editDraft.do?msgcode=${innermsg.msgcode}&s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}">编辑
					</a>
				</c:if>
			</ec:column>
		</ec:row>
	</ec:table>
<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<SCRIPT type="text/javascript">
	 function checkFrom(){
			var begD = $("#s_begsenddate").val();
			var endD = $("#s_endsenddate").val();
			if(endD!=""&&begD>endD){
				alert("结束时间不能早于开始时间。");
//	 			$("#s_endTime").focus();
				return false;
			}
			return true;
		} 
	
		function changeoccur(v) {
			var msgtype = $("#s_msgtype").val();
			if ('I' == v) {
				var href = 'oa/innermsgRecipient!list.do?s_msgtype=' + msgtype
						+ '&s_mailtype=I';
				window.location.href = href;

			} else if ('O' == v) {
				var href = 'oa/innermsg!list.do?s_msgtype=' + msgtype
						+ '&s_mailtype=O';
				window.location.href = href;
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
				disabledBtn($('#a_list_mail_box_drop'), true);
				disabledBtn($('#a_list_mail_box_cancleDrop'), true);
				$('#chk_all, input:checkbox.chk_one').change(function() {
					var msgcodes = LISTMAIL.getSelected();
					$('#hid_msgcodes').val(msgcodes.join(','));
					if (0 < msgcodes.length) {
						disabledBtn($('#a_list_mail_box_cancleDrop'), false);
						disabledBtn($('#a_list_mail_box_delete'), false);
						disabledBtn($('#a_list_mail_box_drop'), false);
					} else {
						disabledBtn($('#a_list_mail_box_cancleDrop'), true);
						disabledBtn($('#a_list_mail_box_delete'), true);
						disabledBtn($('#a_list_mail_box_drop'), true);
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
		} /* ,
									 			{ id:12, pId:1, name:"废件箱", action : '${contextPath}/oa/innermsg!list.do?s_mailtype=T'} */

		];

		$(document).ready(function() {
			
			LISTMAIL.init();
			
		});
	//-->
	</SCRIPT>
</body>
</html>


