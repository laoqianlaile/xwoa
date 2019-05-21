<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />	
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<html>
<head>

<title><s:text name="oaWorkLog.edit.title" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[id="newcontent"]', {
				cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
				fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.getElementById("oaWorkLogForm").submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.getElementById("oaWorkLogForm").submit();
					});
				}
			});			
			prettyPrint();
		});
	</script>
</head>

<body>
 <fieldset class="form">
		<legend>
		<p class="ctitle">
		<s:text name="oaWorkLog.edit.title" />
	    </p>
		</legend> 
	

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaWorkLog"  namespace="/oa" method="post" 	id="oaWorkLogForm"  enctype="multipart/form-data">
		
		  
		<c:if test="${ not empty no}">
			<input type="hidden" id="no" name="no" value="${no}" />
		</c:if>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd"><s:text name="oaWorkLog.infoType" /></td>
				<td align="left">
				<c:if test="${ not empty no}">
				${cp:MAPVALUE("OAInfoType",infoType) }
				</c:if>
				<c:if test="${empty no}">
				<select id="infoType" name="infoType" >
						<c:forEach var="row" items="${cp:DICTIONARY('OAInfoType')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==infoType}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select>
				</c:if>
				</td>
				<td class="addTd"><s:text name="oaWorkLog.releaseDate" /></td>
				<td align="left">
<%-- 				<fmt:formatDate value='${releaseDate}' pattern='yyyy-MM-dd' /> --%>
<%-- 				<input type="hidden" id="releaseDate" name="releaseDate" value="<fmt:formatDate value='${releaseDate}' pattern='yyyy-MM-dd HH:mm:ss' />"> --%>
				<input type="text" class="Wdate" 
				value="<fmt:formatDate value='${releaseDate}' pattern='yyyy-MM-dd' />"
				name="releaseDate"  id="releaseDate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${maxDate}'})"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd,maxDate:'${maxDate}'})"
				placeholder="选择日期">	
				</td>
			</tr>
			<tr id="tr_isshare">
				<td class="addTd"><s:text name="oaWorkLog.isshare" /></td>
				<td id="td_isshare">
					<%-- 				<s:checkbox name="isshare" id="isshare"  fieldValue="1" value="%{isshare}"/> --%>

					<input type="checkbox" id="isshare" name="isshare" value="Y"
					<c:if test="${isshare=='Y'}"> checked="checked" </c:if>>
				</td>
				<td class="addTd" id="td_isallowcommentname"><s:text
						name="oaWorkLog.isallowcomment" /></td>
				<td id="td_isallowcomment"><input type="checkbox"
					id="isallowcomment" name="isallowcomment" value="Y"
					<c:if test="${isallowcomment=='Y'}"> checked="checked" </c:if>>
				</td>
			</tr>

			<tr id="tr_share">
				<td class="addTd">共享人</td>
				<td align="left" colspan="3">
				<input type="text" readonly="readonly"
					id="txa_innermsg_share_name" name="shares"
					style="width: 100%; " value="${shares}"
					readonly="readonly" /> 
					
				<input id="txt_innermsg_share_usercode"
					type="hidden" name="userValue" value="${userValue}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaWorkLog.title" /></td>
				<td align="left" colspan="3"><s:textarea name="title" cols="40"
						rows="2" style="width: 100%;" /></td>
			</tr>



			<tr>
				<td class="addTd"><s:text name="oaWorkLog.remark" /></td>
				<td align="left" colspan="3">
				<s:textarea name="remark" id="newcontent" 
						 cols="40" rows="2" style="width: 100%;" />
			</td>
			</tr>



		</table>
		
		<div class="formButton">
		<input type="button" class="btn" id="save1" value="保存"
			onclick="submitItemFrame('SAVE');" />
		<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
		</div>	
		
		<!-- 选择人员的模块 -->
		<div id="attAlert" class="alert"
			style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
			<h4>
				<span id="selectTT">选择</span><span id="close2" style="float: right; margin-right: 8px;" class="close" onclick="closeAlert('attAlert');">关闭</span>
			</h4>
			<div class="fix">
				<div id="leftSide"></div>
				<div id="l-r-arrow">
					<div class="lb"></div>
					<div class="rb"></div>
				</div>
				<div id="rightSide">
					<ul></ul>
				</div>
				<div id="t-b-arrow">

					<b class="btns"> <input id="save" class="btn" type="button"
						value="保     存" /><input id="clear" class="btn" type="button"
						value="取     消" style="margin-top: 5px;" /></b>
				</div>
			</div>
		</div>
	
	</s:form>
	</fieldset>

</body>
<script type="text/javascript">
function submitItemFrame(subType) {
	if(docheck()==false){
		return;
	}else{
	
	var srForm = document.getElementById("oaWorkLogForm");
	
	if (subType == 'SAVE') {
		srForm.action = 'oaWorkLog!save.do';
	}
	editor.sync();
	srForm.submit();
	}
}

function docheck() {
	var flag = true;
	//新增时验证
	
	
	if("${no}"==""){
		var v = $('#infoType').find('option:selected').val();
		var date=$('#releaseDate').val();
		$.ajax({
			type : "POST",
			async: false,
			dataType : "json",
			url : "oaWorkLog!isLogExit.do?s_releaseDate="+date+"&s_infoType="+v,
			success : function(json) {
				if(!json){
					alert("该日志类型已添加!");
					$('#infoType').focus();
					
					//ajax直接返回无效
					flag = false;
				}
			},
			error : function() {
				alert("失败");
				flag = false;
			}
		}); 
	}
	
	
	return flag;
	
}

	$(function() {

		$('#a_href').attr('height', window.screen.availHeight - 200);

		//是否共享
		function isShare() {
			var v = $('#infoType').find('option:selected').val();
			
			//编辑时日志类型不可修改
			if(undefined==v){
				v="${infoType}";
			}
		

			//工作日志
			if ('w' == v) {

				$('#tr_isshare').show();
				if ('checked' == $('#isshare').attr("checked")) {

					$('#tr_share').show();
					$('#td_isallowcomment').show();
					$('#td_isallowcommentname').show();
					$('#td_isshare').attr("colspan", "1");
				} else {
					$('#tr_share').hide();
					$('#td_isallowcomment').hide();
					$('#td_isallowcommentname').hide();
					$('#td_isshare').attr("colspan", "3");
				}
			} else if ('m' == v) {
				$('#tr_isshare').hide();
				$('#tr_share').hide();
			}
		}

		$('#isshare').live("click", function() {
			isShare();
		});
		$('#infoType').change(function() {
			isShare();
		});
		isShare();
	});

	$("#txa_innermsg_share_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#txa_innermsg_share_name"),
							$("#txt_innermsg_share_usercode"));
				}
				;
			});

	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}


</script>
</html>