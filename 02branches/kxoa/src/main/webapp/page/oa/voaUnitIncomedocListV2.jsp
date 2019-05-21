<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
			<s:text name="oaUnitIncomedoc.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend class="headTitle">
				阅办文开放阅读配置
			</legend>
			<div class="searchDiv">
			<s:form id="voaunitincomedoclistv2form" method="post" action="oaUnitIncomedoc" namespace="/oa" style="margin-top:0;margin-bottom:5">
			 <div class="searchArea">
			<input type="hidden" name="nos" id="hid_codes" value="${hid_codes}"/>
				<table style="width: auto;">

					<tr>
					<c:if test="${not empty VoaUnitIncomedocs }">
					<td class="searchBtnArea">
						
							 <s:submit method="openList"
								onclick='return confirm("是否确定批量公开?");' 
								id="a_list_mail_box_cancleDrop" cssClass="whiteBtnWide"
								value="批量公开"></s:submit>	
							<s:submit method="closeList"
								onclick="return confirm('是否确定批量不公开？')" 
								id="a_list_mail_box_drop" cssClass="whiteBtnWide"
								value="批量不公开"></s:submit>
					</td>
						   </c:if>
					
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">阅办文号:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_acceptarchiveno" value="${s_acceptarchiveno }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">文件标题:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_incomedDocTitle" value="${s_incomedDocTitle }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">阅办文时间:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_begincomeDate"
						<c:if test="${not empty s_begincomeDate }"> value="${s_begincomeDate}" </c:if>
						<c:if test="${empty s_begincomeDate  }">value="${param['s_begincomeDate'] }"</c:if>
						name="s_begincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" style="height: auto;" id="s_endincomeDate"
						<c:if test="${not empty s_endincomeDate }"> value="${s_endincomeDate }" </c:if>
						<c:if test="${empty s_endincomeDate  }"> value="${param['s_endincomeDate'] }" </c:if>
						name="s_endincomeDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">&nbsp;&nbsp;
				<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
					</td>
				<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
					<tr id="gaoji_more" style="display: none;">
					<td></td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">拟发文单位:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_income_Dept_Name"
						value="${s_income_Dept_Name }" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea">
					<label class="searchCondTitle" style="width: 65px;">拟发文字号:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_income_Doc_No"
						value="${s_income_Doc_No }" />
					</td>
					
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>
          <!-- 添加请求参数表单域 -->
         <ec:reqeustAttributeForm id="listReqAttrParam"/>
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
				<a href='javascript:void(0)' onclick="viewDetail('${oaUnitIncomedoc.no }',0)">
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
					    <a class="gongkai" href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=0'>公开</a>
					</c:if>
					<c:if  test="${oaUnitIncomedoc.isopen eq '1' }">
					    <a class="bugongkai" href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=1'>不公开</a>
					</c:if>
					<c:if test="${empty oaUnitIncomedoc.isopen  }">
					<a class="gongkai" href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=0'>公开</a>
					    <a class="bugongkai" href='oa/oaUnitIncomedoc!save.do?no=${oaUnitIncomedoc.no }&id=${oaUnitIncomedoc.id}&isopen=1'>不公开</a>
					</c:if>
					<%-- <a href='oa/oaUnitIncomedoc!delete.do?id=${oaUnitIncomedoc.id}' 
							onclick='return confirm("${deletecofirm}oaUnitIncomedoc?");'><s:text name="opt.btn.delete" /></a> --%>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	 function viewDetail(djId,nodeInstId){
		 var paramForm = $("#listReqAttrParam");
		 paramForm.attr("action","${ctx}/dispatchdoc/incomeDoc!generalOptView.do");
		 
		 var djIdInput = paramForm.find("input[name='djId']"),
			nodeInstIdInput = paramForm.find("input[name='nodeInstId']");
			if(djIdInput.length == 0){
				djIdInput = $("<input>",{"name":"djId","type":"hidden"});
				paramForm.append(djIdInput);
			}
			if(nodeInstIdInput.length == 0){
				nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
				paramForm.append(nodeInstIdInput);
			}
			djIdInput.val(djId);
			nodeInstIdInput.val(nodeInstId);
		 paramForm.submit();
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
	 function sub(){
			$("#voaunitincomedoclistv2form").attr("action","oaUnitIncomedoc!listDoc.do");
			$("#voaunitincomedoclistv2form").submit();
		} 
	 function showgaoji(){
			$("#shouqi").show();
			$("#gaoji_more").show();
			$("#gaoji").hide();
		}
		function toshouqi(){
			$("#shouqi").hide();
			$("#gaoji_more").hide();
			$("#gaoji").show();
		}
		function gj(){
			var t=false;
			if($("input[name=s_income_Dept_Name]").val().trim()!=""&&$("input[name=s_income_Dept_Name]").val()!=null){
				t=true;
			}
			if($("input[name=s_income_Doc_No]").val().trim()!=""&&$("input[name=s_income_Doc_No]").val()!=null){
				t=true;
			}
			return t;
		}
		 $(function(){
				if(gj()){
					showgaoji();
				}
			});
	</script>
</html>
