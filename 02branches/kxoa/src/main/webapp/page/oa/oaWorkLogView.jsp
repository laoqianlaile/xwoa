<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaWorkLog.view.title" /></title>
</head>

<body>
 <fieldset class="form">
		<legend>
		<p class="ctitle">
		<s:text name="oaWorkLog.view.title" />
	   </p>
		</legend> 
	

	<%@ include file="/page/common/messages.jsp"%>

	
	<p>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd"><s:text name="oaWorkLog.infoType" /></td>
			<td align="left">${cp:MAPVALUE("OAInfoType",infoType) }</td>

			<td class="addTd"><s:text name="oaWorkLog.releaseDate" /></td>
			<td align="left"><fmt:formatDate value='${releaseDate}'
					pattern='yyyy-MM-dd' /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaWorkLog.creater" /></td>
			<td align="left">${cp:MAPVALUE("usercode",creater) }</td>
			<td class="addTd"><s:text name="oaWorkLog.creatertime" /></td>
			<td align="left"><fmt:formatDate value='${creatertime}'
					pattern='yyyy-MM-dd HH:mm:ss' /></td>
		</tr>
		<!-- 工作日志才可以共享，共享才可以回复    -->
		<c:if test="${'w' eq infoType}">
       <!-- 		本人才可以看见这些信息 -->
		<c:if test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==creater}">
	<%-- 		<tr>
				<td class="addTd"><s:text name="oaWorkLog.isshare" /></td>
				<td align="left">
				<c:choose>
					<c:when test="${'Y' eq isshare}">
				共享
				</c:when>
				<c:otherwise>
				不共享
				</c:otherwise>
				</c:choose>
				
				<input type="checkbox" id="isshare"
					name="isshare" value="1"
					<c:if test="${isshare==1}"> checked="checked" </c:if>>
					
					</td>
				<td class="addTd"><s:text name="oaWorkLog.isallowcomment" /></td>
				<td align="left">
				<c:choose>
					<c:when test="${'Y' eq isallowcomment}">
				可回复
				</c:when>
				<c:otherwise>
				不可回复
				</c:otherwise>
				</c:choose>
				
				<input type="checkbox" id="isallowcomment"
					name="isallowcomment" value="1"
					<c:if test="${isallowcomment==1}"> checked="checked" </c:if>>
					
				</td>

			</tr> --%>

			<tr>
				<td class="addTd"><s:text name="oaWorkLog.shares" /></td>
				<td align="left" colspan="3"><s:property value="%{shares}" />
				</td>
			</tr>
           </c:if>
		</c:if>
		<tr>
			<td class="addTd"><s:text name="oaWorkLog.title" /></td>
			<td align="left" colspan="3"><s:property value="%{title}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaWorkLog.remark" /></td>
			<td align="left" colspan="3">${remark}
			</td>
		</tr>
	</table>
	
	   <div class="formButton">
		<input type="button" name="back" Class="btn"
				onclick="javascript:window.location.href='oa/oaWorkLog!calendarList.do';" value="返回" />
	
<!--   是否可以留言回复 -->
 	 <c:if test="${'Y' eq isallowcomment}"> 
 	<input type="button" name="back" Class="btn"  value="展开留言"  id="s_replay"/>  
 	
 	</div>	
 	<br>
	<div tyle="width:100%; float:left;" id='div_replay'>
					<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
					<iframe id="tabFrames1" name="tabFrames"
						src="<%=request.getContextPath()%>/oa/oaLeaveMessage!replayList.do?s_djid=${no}&s_infoType=workLog"
						height="500" width="100%" frameborder="0"
						scrolling="no" border="0" marginwidth="0"></iframe>
	</div>
	</c:if>	
	</fieldset>
</body>
<script type="text/javascript">
//是否显示留言
function isShow()  {

	if ('checked' == $('#div_replay').attr("class")) {
		$('#div_replay').hide();
		$('#div_replay').removeAttr("class");
		$('#s_replay').attr("value","展开留言");
	} else {
		$('#div_replay').show();
		$('#div_replay').attr("class","checked");
		$('#s_replay').attr("value","收起留言");
		
	}
// 	reinitIframe();
}

$('#s_replay').live("click", function() {
	isShow();
});

$(function(){
	$('#div_replay').hide();	
})

// function adjustHeight() {//自动调整页面高度
// 		if (window.document.getElementById("transFrame")) {
// 			window.parent.document.getElementById("transFrame").style.height = document.body.scrollHeight
// 					+ "px";
// 		}
// 	}

// function reinitIframe(){

// var iframe = document.getElementById("tabFrames1");

// try{

// var bHeight = iframe.contentWindow.document.body.scrollHeight;

// var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

// var height = Math.max(bHeight, dHeight);

// iframe.height =  height;

// }catch (ex){}

// }

// window.setInterval("reinitIframe()", 200);
	
</script>
</html>
