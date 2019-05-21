<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaUnitIncomedoc.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				阅办文开放阅读配置
			</legend>
			
			<s:form action="oaUnitIncomedoc" namespace="/oa" style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="nos" id="hid_codes"/>
				<table cellpadding="0" cellspacing="0" align="center">

					<tr>
					    <td class="addTd">阅办文号:</td>
						<td >
						<input type="text" name="s_acceptarchiveno" value="${s_acceptarchiveno }" />

						<td class="addTd">文件标题:</td>
						<td >
						<input type="text" name="s_incomedDocTitle" value="${s_incomedDocTitle }" />
					</tr>
					
					
					<tr>
					<td class="addTd">拟发文单位:</td>
					<td><input type="text" name="s_incomeDeptName"
						value="${s_incomeDeptName }" /></td>
					<td class="addTd">拟发文字号:</td>
					<td><input type="text" name="s_incomeDocNo"
						value="${s_incomeDocNo }" /></td>
				  </tr>
					
					<tr>
					<td class="addTd">阅办文时间:</td>
					<td><input type="text" class="Wdate" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
					</td>
				
						<td colspan="2"  >
						<c:if test="${not empty VoaUnitIncomedocs }">
						
							 <s:submit method="openList"
								onclick='return confirm("是否确定批量公开?");' style="float:right;"
								id="a_list_mail_box_cancleDrop" cssClass="btn btnDelete"
								value="批量公开"></s:submit>	
							<s:submit method="closeList"
								onclick="return confirm('是否确定批量不公开？')" style="float:right;"
								id="a_list_mail_box_drop" cssClass="btn btnDelete"
								value="批量不公开"></s:submit>
						   </c:if>
						<s:submit method="list" key="opt.btn.query" style="float:right;"
							cssClass="btn" /></td>
				</tr>
					
				<%-- 	<tr>
						 <td class="addTd">更新时间:</td>
						<td>
					<input type="text" class="Wdate" id="s_begUpdateTime"
						<c:if test="${not empty s_begUpdateTime }"> value="${s_begUpdateTime}" </c:if>
						<c:if test="${empty s_begUpdateTime  }">value="${param['s_begUpdateTime'] }"</c:if>
						name="s_begUpdateTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endUpdateTime"
						<c:if test="${not empty s_endUpdateTime }"> value="${s_endUpdateTime }" </c:if>
						<c:if test="${empty s_endUpdateTime  }"> value="${param['s_endUpdateTime'] }" </c:if>
						name="s_endUpdateTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						 </td> 
						  <td class="addTd">发文字号:</td>
						<td >
						<input type="text" name="s_incomeDocNo" value="${s_incomeDocNo }" /></td>
					<tr> --%>
				<%-- 	
					<tr class="searchButton" >
				
						<td  colspan="5">
						<c:if test="${not empty VoaUnitIncomedocs }">
						
							 <s:submit method="openList"
								onclick='return confirm("是否确定批量公开?");'
								id="a_list_mail_box_cancleDrop" cssClass="btn btnDelete"
								value="批量公开"></s:submit>	
							<s:submit method="closeList"
								onclick="return confirm('是否确定批量不公开？')"
								id="a_list_mail_box_drop" cssClass="btn btnDelete"
								value="批量不公开"></s:submit>
						</c:if>
							<s:submit method="listDoc"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr> --%>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaUnitIncomedoc!listDoc.do" items="VoaUnitIncomedocs" var="oaUnitIncomedoc"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			   <ec:column property="oaUnitIncomedoc.no"
				  title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				  sortable="false" width="2%">
				  <input class="chk_one" type="checkbox" id="${oaUnitIncomedoc.no}"
					class="ecbox" value="${oaUnitIncomedoc.id}|${oaUnitIncomedoc.no}">
			    </ec:column>
			    <ec:column property="incomeDocType"  title="文书分类" style="text-align:center" width="8%">
			    ${cp:MAPVALUE("incomeDocType",oaUnitIncomedoc.incomeDocType)}
			    </ec:column>
		        <ec:column property="acceptarchiveno"  title="阅办文号" style="text-align:center" width="8%"/>
				<ec:column property="incomedDocTitle" title="文件标题" style="text-align:center" width="18%">
				<a href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${oaUnitIncomedoc.no }&nodeInstId=0'>
				${oaUnitIncomedoc.incomedDocTitle}</a>
				</ec:column>
				<ec:column property="incomeDeptName" title="拟发文单位" style="text-align:center" width="12%"/>
				<ec:column property="incomeDocNo" title="拟发文字号" style="text-align:center" width="8%"/>
				<ec:column property="incomeDate" title="阅办文日期" style="text-align:center " width="8%">
				 <fmt:formatDate value='${oaUnitIncomedoc.incomeDate}' pattern='yyyy-MM-dd' />
				</ec:column>
				
				
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center" width="6%">
					<c:if test="${oaUnitIncomedoc.isopen eq '0' }">
					    <a href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=0'>公开</a>
					</c:if>
					<c:if test="${oaUnitIncomedoc.isopen eq '1' }">
					    <a href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=1'>不公开</a>
					</c:if>
					<c:if test="${empty oaUnitIncomedoc.isopen  }">
					<a href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=0'>公开</a>
					    <a href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=1'>不公开</a>
					</c:if>
					<%-- <a href='oa/oaUnitIncomedoc!delete.do?id=${oaUnitIncomedoc.id}' 
							onclick='return confirm("${deletecofirm}oaUnitIncomedoc?");'><s:text name="opt.btn.delete" /></a> --%>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
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
					var id = LISTMAIL.getSelected();
					$('#hid_codes').val(id.join(','));
					if (0 < id.length) {
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
				var id = [];
				$.each($('input:checkbox.chk_one:checked'), function(index,
						checkbox) {
					id.push($(this).val());
				});

				return id;
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
