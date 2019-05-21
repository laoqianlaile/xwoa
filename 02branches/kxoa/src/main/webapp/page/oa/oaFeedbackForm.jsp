<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
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
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<script type="text/javascript" data-main="../scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
	<script>
		var editor;
		var flag='${flag}';
		if(flag=='edit'){
			KindEditor.ready(function(K) {
				editor = K.create('textarea[id="replyInformation"]', {
					cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
					uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
					fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
					allowFileManager : true,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.getElementById("oaFeedbackForm").submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.getElementById("oaFeedbackForm").submit();
						});
					}
				});			
				prettyPrint();
			});
		}else{
			KindEditor.ready(function(K) {
				editor = K.create('textarea[id="remark"]', {
					cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
					uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
					fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
					allowFileManager : true,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.getElementById("oaFeedbackForm").submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.getElementById("oaFeedbackForm").submit();
						});
					}
				});			
				prettyPrint();
			});
		}
	</script>
</head>

<body>

<%@ include file="/page/common/messages.jsp"%>
    <fieldset class="form">
		<legend>
		<p class="ctitle">
		情况反馈
	    </p>
		</legend> 

	<s:form action="oaFeedback" method="post" namespace="/oa" id="oaFeedbackForm"  enctype="multipart/form-data" >
		<input type="hidden" id="djid" name="djid" value="${object.djid}" />
		<c:if test="${flag eq 'edit' }">
		<iframe src="<%=request.getContextPath()%>/oa/oaFeedback!view.do?djid=${djid}&flag=edit" id="oaFeedback" name="oaFeedback" width="100%"  frameborder="no"
		scrolling="auto" border="0" marginwidth="0"
		onload="this.height=window.frames['oaFeedback'].document.body.scrollHeight+10"></iframe>
		 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
			   <td class="addTd">回复信息</td>
			   <td colspan="3">
<%-- 			   <s:textarea id="replyInformation" name="replyInformation" value="%{object.replyInformation}" style="width:100%;height:50px;" /> --%>
			   <textarea name="replyInformation" id="replyInformation"  cols="40"
						rows="2" style="width: 100%;" 
					value="${object.replyInformation }">${object.replyInformation }</textarea>
			   </td>
			</tr>
			</table>
		</c:if>
		<c:if test="${flag ne 'edit' }">
		
        <table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd"><s:text name="oaFeedback.depno" /><span style="color:red">*</span></td>
					<td align="left" >
					<select id="depno" ref="#reception"  data-value="${depno }"  style="width:200px;height:25px;"
					refUrl="${pageContext.request.contextPath}/oa/oaMeetinfo!option.do?type=US&unitcode={value}"
						name="depno" >
						<option value="" >---请选择---</option>
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
							    	<c:if test="${row.unitcode==depno}"> selected="selected"</c:if> >
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select>
					</td>
                    <td class="addTd"><s:text name="oaFeedback.reception" /><span style="color:red">*</span></td>
					<td align="left">
					<select  id="reception"  style="width:200px;height:25px;"
						name="reception"  data-value="${reception }">
							<c:forEach var="row" items="${users }">
								<option value="${row.usercode}"
							    	<c:if test="${row.usercode==reception}"> selected="selected"</c:if>
								  >
									<c:out value="${row.username}" />
								</option>
							</c:forEach>
					</select>
					</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaFeedback.isanonymous" /></td>	
				<td align="left" colspan="3" >
				  <input type="checkbox" name="isanonymous"  id="isanonymous"  value="1" checked=true/>
										匿名
				</td>
					</tr>
               <tr>
				<td class="addTd"><s:text name="oaFeedback.feedFile" /></td>
				<td align="left" colspan="3"><s:file type="file"  name="stuffFile_"/>	
				 <c:if
							test="${not empty object.feedFileName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.djid}','docFile_')">&nbsp;</c:if></td>
				</td>
			</tr>
          	<tr>
				<td class="addTd"><s:text name="oaFeedback.title" /><span style="color:red">*</span></td>
				<td colspan="3">
					<s:textfield id="title" name="title" value="%{object.title}"  style="width:100%;" />
				
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaFeedback.remark" /><span style="color:red">*</span></td>
				<td align="left" colspan="3">	
<%-- 				<s:textarea id="remark" name="remark" value="%{object.remark}" style="width:100%;height:50px;" /> --%>
					<textarea name="remark" id="remark"  cols="40"
						rows="2" style="width: 100%;"
					value="${object.remark }">${object.remark }</textarea>
				</td>
			</tr>
          		
		</table>
		
      </c:if>
      <div class="formButton">
    
		<c:if test="${flag eq 'edit'}">
		<input type="button" class="btn" id="save" value="保存" onclick="submitItemFrame('SUB');"/>
		 <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
		</c:if>
		<c:if test="${flag ne 'edit'}">
		<input type="button" class="btn" id="save" value="保存" onclick="submitItemFrame('SAVE');"/>
		</c:if>
		</div>
      
	</s:form>
	</fieldset>

	</body>
	<script type="text/javascript">
function submitItemFrame(subType){
	    
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaFeedbackForm");
		if(subType == 'SAVE'){
			srForm.action='oaFeedback!savePermitReg.do';
		}if(subType == 'SUB'){
			var sd = document.getElementsByTagName("iframe")[1].contentDocument.body.innerHTML;
			$("#replyInformation").val(sd);
			srForm.action='oaFeedback!saveAndSubmit.do';
		}

	
		srForm.submit();
		}
}
	
	
	function docheck() {
		if($("#title").val()==''){
			alert("标题不可为空！");
			$('#title').focus();
			return false;
		}
		var sd = document.getElementsByTagName("iframe")[0].contentDocument.body.innerHTML;
		$("#remark").val(sd);
		if($("#remark").val()==''){
			alert("反馈内容不可为空不可为空！");
			$('#remark').focus();
			return false;
		}
		if($("#depno").val()==''){
			alert("接收部门不可为空！");
			$('#depno').focus();
			return false;
		}
		if($("#reception").val()==''){
			alert("接收人员不可为空！");
			$('#reception').focus();
			return false;
		}
		if("${flag}"!='edit'){
			if(window.confirm("是否确定匿名?")){
				 return  true;
			}else{
				return false;
			}
		}
		
    }
	function getStringLen(Str) {
		var i, len, code;
		if (Str == null || Str == "")
			return 0;
		len = Str.length;
		for (i = 0; i < Str.length; i++) {
			code = Str.charCodeAt(i);
			if (code > 255) {
				len++;
			}
		}
		return len;
	}

	function downFile(id,v, fileType) {
		var url = "oaMeetMinutes!downStuffInfo.do?djid=" + id +"&version="+v+ "&fileType="
				+ fileType;
		document.location.href = url;
	}
	$(function() {
		
		$('select[refUrl]').change(function() {
			
			var select = $(this);
			
			var ref = select.attr('ref');
			var url = select.attr('refUrl');
			
			if (ref && url) {
				var refSelect = $(ref);
				var refUrl = url.replaceAll('{value}', select.val());
				
				$.get(refUrl, function(json) {
					refSelect.find('option').remove();
					
					for(var i=0; i<json.length; i++) {
						var key = json[i].key;
						var value = json[i].value;
						
						refSelect.append('<option value="'+key+'">'+value+'</option>');
					}
					
//		                            无autoSelect()	
//	 				refSelect.autoSelect();
					
					// 如果是chosen下拉框：
					if ('chosen' == refSelect.data('rel')) {
						refSelect.trigger('liszt:updated');
					}
					
				}, "json");
			}
				
		});
	});

	
</script>
</html>