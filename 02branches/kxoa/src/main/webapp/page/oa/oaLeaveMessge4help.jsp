<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css" rel="stylesheet"/>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/themes/oaHelpInfo/style.css" rel="stylesheet" type="text/css" />
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
	<style type="text/css">
		img {
		 		position:absolute;
			}
	</style>
</head>
<body style="width:100%;">	
	<c:if test="${fn:length(objList)>=1}">
<%-- 		<a id="scrlBotm" style="right: 50px; position: fixed; top: 110px; z-index: 16; cursor: pointer; overflow: hidden; text-indent: -9999px; width: 50px; height: 50px; background: url(${contextPath}/images/gototop-bg-v2.png);"></a> --%>
		<c:forEach items="${objList }" var="oaLeaveMessage">
		<div  style="width:960px;margin:auto;">
			<table>
			<tr>
			  <td style="width:15%;padding:10px 0px;vertical-align:text-top;">${cp:MAPVALUE("usercode",oaLeaveMessage.creater)}<br/><br/>
			  <fmt:formatDate value="${oaLeaveMessage.creatertime }" pattern="yyyy-MM-dd HH:mm:ss" />
			  <c:if test="${'mgr' eq  backcolumnType }">
			     <br><a href='${pageContext.request.contextPath}/oa/oaLeaveMessage!deleteMessage.do?no=${oaLeaveMessage.no }&s_djid=${s_djid}&backcolumnType=${backcolumnType}' 
							style="color:red" onclick='return confirm("确定删除该条回复吗?");'>删除</a>
			  </c:if></td>
			  <td style="padding-left:10px;background:#fff;">${oaLeaveMessage.messageComment}</td>
			</tr>
			</table>
		</div>
		</c:forEach>
	<c:if test="${ ! empty objList }">
   	 <c:set var="listURL" value="oaLeaveMessage!replayList.do" />
     <c:set var="maxPageItems" value="10"></c:set>
     <%@ include file="/page/common/pagingBar.jsp"%>
   </c:if>
		<a id="scrlTop" title="快速置顶" style="width:30px;height:33px;position: fixed;right: 50px;background: url(/oa/styles/default/images/lanren_top.jpg);"></a>
	</c:if>		
	<form action="oaLeaveMessage" namespace="/oa" id="oaLeaveMessageForm" style="width:960px;margin:auto;" method="post">
		<input type="hidden" id="s_djid" name="s_djid" value="${s_djid}" />
		<input type="hidden" id="s_infoType" name="s_infoType" value="${s_infoType}" />
		<input type="hidden" id="backcolumnType" name="backcolumnType" value="${backcolumnType}" />
			<div  style="width:100%;">
				<table>
				  <tr>
				    <td style="width:15%;text-align:center;">留言</td>
				    <td><s:textarea name="messageComment" id="newcontent" value="" cols="40" rows="2" style="width:100%;" /></td>
				  </tr>
				</table>
				<div  style="margin:2% 10% 5% 20%;width:90%;">
					<span  style="text-align: right;width:5%;padding-top:2%;"></span>
					<span  style="width:95%;padding:2% 63% 5% 2%;">
						<input type="button" class="btn" id="save" style="padding-left:20px;padding-right:30px;color:#fff;" value="留言" onclick="submitItemFrame('SAVE');" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="back" class="btn" style="padding-left:20px;padding-right:30px;color:#fff;" value="返回" />
					</span>
				</div>
			</div>
	</form>
</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	function submitItemFrame(subType){
		var srForm  = document.getElementById("oaLeaveMessageForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaLeaveMessage!save.do';
		}
		editor.sync(false);
		srForm.submit();
		}
	 $(function(){
		 $('#scrlTop').click(function () {
				window.top.scrollTop();
			 }
			);
		$("input[name='back']").click(function(){
		    	 var columnType='${backcolumnType}';
		    	 if(columnType=='mgr'){
		    	 window.parent.location="oaHelpinfo!list4Manager.do";
		    	// parent.document.location.href="oaHelpinfo!list4Manager.do";
		    	 }
		    	 else{
		    		 window.top.location.href="oaHelpinfo!list.do?s_columnType="+columnType;
		    	 }
		    });
		setInterval(function(){
			var t = window.top.returnScroll()-300;
			$('#scrlTop').css({top:t+"px"});
		},100);
 			});	
    </script>
</html>

