<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaInformation.list.title" />
		</title>
		<script type="text/javascript">
		   $(function(){
			  $("#reforward").bind("click",function(){
				 window.location.href="oaInformation!list?infoType=${infoType}&flag=${flag}";
			  });
		  }); 
		</script>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend>
			${cp:MAPVALUE('infoType',infoType)}
			</legend>
			
			<s:form action="oaInformation" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" id="infoType" name="infoType" value="${infoType}" />
				<input type="hidden" id="flag" name="flag" value="${flag}" />
				<s:hidden id="hid_ids" name="ids" />
					<tr >
						<%-- <td class="addTd"><s:text name="oaInformation.infoType" />:</td>
						<td><select  id="infoType"
						name="infoType" disabled="disabled" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('infoType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==infoType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> </td> --%>
						<td class="addTd"><s:text name="oaInformation.title" />:</td>
						<td><s:textfield name="s_title"  value="%{#parameters['s_title']}"/> </td>
					    <td class="addTd"><s:text name="oaInformation.docNo" />:</td>
						<td><s:textfield name="s_docNo" value="%{#parameters['s_docNo']}" /> </td>
					</tr>	

					<tr >
						<td class="addTd"><s:text name="oaInformation.publicKey" />:</td>
						<td><s:textfield name="s_publicKey" value="%{#parameters['s_publicKey']}"  />  
						<c:if test="${flag ne 'GGZY'}">
						<input type="checkbox" id="s_isBoth" 
						name="s_isBoth" value="true">包含禁用</td>
						</c:if>
						
						<td class="addTd">发布时间:</td>
						<td colspan="2">
					 <input type="text" class="Wdate" id="s_begReleaseDate"
						<c:if test="${not empty s_begReleaseDate }"> value="${s_begReleaseDate}" </c:if>
						<c:if test="${empty s_begReleaseDate  }">value="${param['s_begReleaseDate'] }"</c:if>
						name="s_begReleaseDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endReleaseDate"
						<c:if test="${not empty s_endReleaseDate }"> value="${s_endReleaseDate }" </c:if>
						<c:if test="${empty s_endReleaseDate  }"> value="${param['s_endReleaseDate'] }" </c:if>
						name="s_endReleaseDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">					
								</td> 
					</tr>	

					<tr class="searchButton">
					     
						<td colspan="4">
						<c:if test="${flag ne 'GGZY' }">
						<c:if test="${not empty vobjList }">
								<s:submit method="deleteIds"
									onclick="return confirm('是否确定批量删除？')" id="a_list_ids_delete"
									cssClass="btn  btnDelete" value="批量删除"></s:submit>
							</c:if>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>							
					</c:if>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"  onclick="return checkFrom();"/>
						    <input type="hidden" id="flag" name="flag" value="${flag}" />
						
						
					   
					    </td>
					  
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaInformation!list.do" items="vobjList" var="oaInformation"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		    retrieveRowsCallback="limit">
			<ec:row>
			<c:if test="${flag ne 'GGZY'}">
			<ec:column property="none"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<%-- <c:if test="${empty  oaInformation.state}"> --%><!-- 未选择是否启用的对象才能删除 -->
						<input class="chk_one" type="checkbox" id="${oaInformation.no}"
							class="ecbox" value="${oaInformation.no}">
				<%-- </c:if> --%>
			</ec:column>
	     	</c:if>
			
<%-- 
				<c:set var="tno"><s:text name='oaInformation.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />
 --%>
                <%-- <c:if test="${flag ne 'GGZY' }">
				<c:set var="tinfoType"><s:text name='oaInformation.infoType' /></c:set>	
				<ec:column property="infoType" title="${tinfoType}" style="text-align:center" >
				${cp:MAPVALUE("infoType",oaInformation.infoType) }
				</ec:column>
				</c:if> --%>

				<c:set var="ttitle"><s:text name='oaInformation.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" >
				<c:if test="${oaInformation.state eq '2' }"><font color='red'></c:if>
				    <c:choose>
					<c:when test="${fn:length(oaInformation.title) gt 18}">${fn:substring(oaInformation.title, 0, 18) }...</c:when>
					<c:otherwise>${oaInformation.title}</c:otherwise>
					</c:choose>
				<c:if test="${oaInformation.state eq '2' }"></font></c:if>	
				</ec:column>

				<c:set var="tmajorDegree"><s:text name='oaInformation.majorDegree' /></c:set>	
				<ec:column property="majorDegree" title="${tmajorDegree}" style="text-align:center">
				${cp:MAPVALUE("IMP",oaInformation.majorDegree) }
				</ec:column>

				<c:set var="treleaseDate"><s:text name='oaInformation.releaseDate' /></c:set>	
				<ec:column property="releaseDate" title="${treleaseDate}" style="text-align:center" format="yyyy-MM-dd" cell="date"/>

				<c:set var="tpublicKey"><s:text name='oaInformation.publicKey' /></c:set>	
				<ec:column property="publicKey" title="${tpublicKey}" style="text-align:center" />

				<c:set var="tdocNo"><s:text name='oaInformation.docNo' /></c:set>	
				<ec:column property="docNo" title="${tdocNo}" style="text-align:center" />

				<c:set var="tauthor"><s:text name='oaInformation.author' /></c:set>	
				<ec:column property="author" title="${tauthor}" style="text-align:center" />
			
				<c:set var="treadstate"><s:text name='oaInformation.readstate' /></c:set>	
				<ec:column property="readstate" title="状态" style="text-align:center" />
			
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${oaInformation.no}&flag=${flag }&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<c:if test="${flag ne 'GGZY' }">
					<a href='oa/oaInformation!edit.do?no=${oaInformation.no}'><s:text name="opt.btn.edit" /></a>
					<%-- <c:if test="${empty  oaInformation.state}"> --%><!-- 未选择是否启用的对象才能删除 -->
					<a href='oa/oaInformation!delete.do?no=${oaInformation.no}&infoType=${infoType}&flag=${flag}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
					<%-- </c:if>	 --%>
				    </c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	
</html>
<script>
function checkFrom(){
	var begD = $("#s_begReleaseDate").val();
	var endD = $("#s_endReleaseDate").val();
	if(endD!=""&&begD>endD){
		alert("结束时间不能早于开始时间。");
//			$("#s_endTime").focus();
		return false;
	}
	return true;
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