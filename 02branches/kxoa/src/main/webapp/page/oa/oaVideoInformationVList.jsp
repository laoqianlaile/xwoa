<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaVideoInformation.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search" >
		<legend>
			视频节目
		</legend>

		<s:form action="oaVideoInformation" id="form1" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
				<s:hidden id="hid_ids" name="ids" />
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">视频节目分类:</td>
					<td><select name="s_infoType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('videoType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_infoType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">视频节目标题:</td>
					<td><s:textfield name="s_title"
							value="%{#parameters['s_title']}" /></td>
				</tr>				
                <tr>
					<td class="addTd"><s:text name="oaVideoInformation.publicKey" />:</td>
					<td><s:textfield name="s_publicKey"
							value="%{#parameters['s_publicKey']}" /></td>
					<td class="addTd"><s:text name="oaVideoInformation.releaseDate" />:</td>
					<td>
					<input type="text" class="Wdate" id="s_begReleaseDate"
						<c:if test="${not empty s_begReleaseDate }"> value="${s_begReleaseDate}" </c:if>
						<c:if test="${empty s_begReleaseDate  }">value="${param['s_begReleaseDate'] }"</c:if>
						name="s_begReleaseDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endReleaseDate"
						<c:if test="${not empty s_endReleaseDate }"> value="${s_endReleaseDate }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endReleaseDate'] }" </c:if>
						name="s_endReleaseDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">		
					包含已删除：<input type="checkbox" name="s_includeDel" value="T" <c:if test="${s_includeDel=='T'}">checked="checked"</c:if>>
					</td>		
				</tr>			
				<tr  class="searchButton">
					<td colspan="4">
					 <c:if test="${not empty objList }">
					    <s:submit  onclick="submitItemFrame('publish')"
							method="batchUpdateState" cssClass="btn  btnBatch btnDelete" value="批量发布"></s:submit>
						<s:submit  onclick="submitItemFrame('offline')"
							method="batchUpdateState" cssClass="btn  btnBatch btnDelete" value="批量下线"></s:submit>
						<s:submit onclick="return confirm('是否确定批量删除？')"
							 method="deleteIds" cssClass="btn  btnBatch btnDelete" value="批量删除"></s:submit>
					</c:if>
					<s:submit method="videoList" key="opt.btn.query" cssClass="btn" onclick="return checkFrom();" />
					<s:submit method="videoEdit" key="opt.btn.new" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaVideoInformation!videoList.do" items="objList"
		var="oaVideoInformation" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
	  <ec:column property="no"
							title='全选<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
							sortable="false" width="6%">
							<input class="chk_one" type="checkbox" id="${oaVideoInformation.no}"
								class="ecbox" value="${oaVideoInformation.no}">
	   </ec:column>
		<ec:column property="title" title="视频标题" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(oaVideoInformation.title) gt 15 }">
				${fn:substring(oaVideoInformation.title,0,15)... }
				</c:when>
					<c:otherwise>${oaVideoInformation.title }</c:otherwise>
				</c:choose>
			</ec:column>
			<ec:column property="infoType" title="视频节目分类"	style="text-align:center">
				${cp:MAPVALUE('videoType',oaVideoInformation.infoType)}
				</ec:column>
				<ec:column property="videoType" title="视频节目类别"	style="text-align:center">
				<c:if test="${oaVideoInformation.videoType eq 'D' }">单剧</c:if>
		        <c:if test="${oaVideoInformation.videoType  eq 'L' }">连载</c:if>
				</ec:column>

			<c:set var="tcreatertime">
				<s:text name='oaVideoInformation.creatertime' />
			</c:set>
			<ec:column property="creatertime" title="${tcreatertime}"
				style="text-align:center" format="yyyy-MM-dd" cell="date" />

			<c:set var="tlastmodifytime">
				<s:text name='oaVideoInformation.lastmodifytime' />
			</c:set>
			<ec:column property="state" title="状态" sortable="false" style="text-align:center">
			   <c:if test="${oaVideoInformation.state=='F'}">未发布</c:if>
			   <c:if test="${oaVideoInformation.state=='T'}">已发布</c:if>
			   <c:if test="${oaVideoInformation.state=='D'}"><span style="color:red">已删除</span></c:if>
			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
					
				<a
					href='oa/oaVideoInformation!view.do?no=${oaVideoInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
			    <c:if test="${oaVideoInformation.state eq 'D' }">
				<a
					href='oa/oaVideoInformation!isstate.do?state=F&no=${oaVideoInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'>启用</a>
				</c:if>
				 <c:if test="${oaVideoInformation.state ne 'D' }">
					 <c:if test="${oaVideoInformation.state eq 'F' }">
					 <a
						href='oa/oaVideoInformation!isstate.do?state=T&no=${oaVideoInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'
						onclick='return confirm("确定要发布该节目视频上线吗?");'>发布</a>
					 </c:if>
					  <c:if test="${oaVideoInformation.state eq 'T' }">
					 <a
						href='oa/oaVideoInformation!isstate.do?state=F&no=${oaVideoInformation.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'
						onclick='return confirm("确定要将该节目视频下线吗?");'>下线</a>
					 </c:if>
				<a
					href='oa/oaVideoInformation!videoEdit.do?no=${oaVideoInformation.no}'><s:text
						name="opt.btn.edit" /></a>
				<a
					href='oa/oaVideoInformation!delete.do?no=${oaVideoInformation.no}'
					onclick='return confirm("${deletecofirm}该节目视频?");'><s:text
						name="opt.btn.delete" /></a>
				<a
					href='oa/oaSubvideoInformation!list.do?no=${oaVideoInformation.no}'>添加视频</a>
			  </c:if>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
<script>
function submitItemFrame(updateType) {

	var srForm = document.getElementById("form1");
	if (updateType == 'publish') {
		confirm("是否确定批量发布吗?");
		srForm.action = '${contextPath}/oa/oaVideoInformation!batchUpdateState.do?state=T';
	} else if (updateType == 'offline') {
		confirm("确定批量不公开所选帖子吗?");
		srForm.action = '${contextPath}/oa/oaVideoInformation!batchUpdateState.do?state=F';
	}
	srForm.submit();
}
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
		$('#chk_all').change(
				function() {
					var $checked = $(this).prop('checked');
					$.each($('input:checkbox.chk_one'), function(index,checkbox) {
						$(this).prop('checked', $checked);
						if ($checked) {
							$(this).parent('span').addClass('checked');
						} else {
							$(this).parent('span')
									.removeClass('checked');
						}
					});
				});
	},

	bindCheckboxClick : function() {
		disabledBtn($('.btnBatch'), true);
		$('#chk_all, input:checkbox.chk_one').change(function() {
			var msgcodes = LISTMAIL.getSelected();
			$('#hid_ids').val(msgcodes.join(','));
			if (0 < msgcodes.length) {

				disabledBtn($('.btnBatch'), false);
			} else {
				disabledBtn($('.btnBatch'), true);
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