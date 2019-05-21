<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaLeaveMessage.list.title" /></title>
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
						document.getElementById("oaLeaveMessageForm").submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.getElementById("oaLeaveMessageForm").submit();
					});
				}
			});			
			prettyPrint();
		});
	</script>
</head>
<body style="width:99%;background-color:#FBFBFB;">
	<%@ include file="/page/common/messages.jsp"%>
  <div id="container">
	<s:form action="oaLeaveMessage" namespace="/oa" id="oaLeaveMessageForm"
		style="margin-top:0;margin-bottom:5;margin-top: 0px; padding-right: 70px; border: 1px solid rgb(204, 204, 204);">
		<input type="hidden" id="s_djid" name="s_djid" value="${s_djid}" />
		<input type="hidden" id="s_infoType" name="s_infoType"
			value="${s_infoType}" />
		
		<table border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td class="TDTITLE">
				<%-- <s:text
						name="oaLeaveMessage.messagecomment" /> --%>
				留言:	
						</td>
				<td align="left" colspan="2">
				<s:textarea name="messageComment" id="newcontent"
						value="" cols="40" rows="2" style="width: 100%;" /></td>
			</tr>
			<tr>
			    <td class="TDTITLE"  colspan="3"></td>
				<td align="right"  >
				<%-- <s:submit name="save" method="save" cssClass="btn" key="回复" /> --%>
				<input type="button" class="btn" id="save" value="留言" onclick="submitItemFrame('SAVE');" />
				</td>
			</tr>
		</table>
		
	</s:form>
<c:if test="${fn:length(objList)>=1}">

	<ec:table action="oa/oaLeaveMessage!replayList.do" items="objList"
		var="oaLeaveMessage" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<c:if test="${not empty oaLeaveMessage.creatertime}">
				<tr>
					<td style="color:#000; font-weight:bold;background-color: #dbf2ff">
<%-- 					${empty cp:MAPVALUE("usercode",oaLeaveMessage.creater) ?"匿名" : cp:MAPVALUE("usercode",oaLeaveMessage.creater)} --%>
					
						${cp:MAPVALUE("usercode",oaLeaveMessage.creater)}&nbsp;&nbsp;留言于：<fmt:formatDate value='${oaLeaveMessage.creatertime}' pattern='yyyy-MM-dd hh:mm:ss' />
					
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${oaLeaveMessage.messageComment}</td>
				</tr>
			</c:if>		
			</table>
		</ec:row>
	</ec:table>
</c:if>
</div>
</body>
<script type="text/javascript">
	function submitItemFrame(subType){
		var srForm  = document.getElementById("oaLeaveMessageForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaLeaveMessage!save.do';
		}
		editor.sync();
		srForm.submit();

}
   window.setInterval(function(){
	   parent.document.getElementById("tabFrames1").height = $("#container").height();
   }, 200);	
    </script>
</html>

