<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form  action="oaSurveydetail" namespace="/oa"
		style="margin-top:0;margin-bottom:5" >
        <input type="hidden" id="s_djid"  name="s_djid" value="${s_djid}">
	  	<input type="hidden" id="s_itemType" name="s_itemType"  value="${s_itemType}">
    	<input type="hidden" id="s_type"  name="s_type"  value="${s_type }">
	 	<div>
			<span
				style="width: 100%; position: relative; text-align: center; border-bottom: 1px dashed #111; font-size: 20px;"><br>
				<c:if test="${not empty oaSurvey}">${oaSurvey.title }</c:if> </span>
		</div>


		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr class="button">
				<td>
	            <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
				</td>
				</tr>
			<s:iterator value="objList" status="status" var="oaOnlineItem">
				<tr 
					style="color: #000; font-weight: bold; background-color: #dbf2ff">
					<td>${status.count }、&nbsp;&nbsp;${oaOnlineItem.title }&nbsp;&nbsp;&nbsp;&nbsp;
					${cp:MAPVALUE("OAChoseType",oaOnlineItem.chosetype) }
					
					</td>
				</tr>
				<tr>
					<td><c:if test="${'3' ne oaOnlineItem.chosetype}">
							<div style="width: 100%; float: left;" id='div_replay'>
								<iframe class="tabFrames" name="tabFrames"
									src="<%=request.getContextPath()%>/stat/twodimenform!doStat.do?modelName=ZXDCTJ&itemNo=${oaOnlineItem.no}"
									width="100%" frameborder="0" hight="800px" scrolling="no"
									border="0" marginwidth="0" onload="reinitIframe();"></iframe>
							</div>
						</c:if></td>
				</tr>
			</s:iterator>
				<tr class="button">
				<td>
	            <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
				</td>
				</tr>
		</table>
	</s:form>
</body>
<script type="text/javascript">

function reinitIframe(){
	setTimeout(function() {
		var iframe = $(".tabFrames");

		try{
			
	   for( var i=0;i<iframe.length;i++){
			
				
		var bHeight = iframe[i].contentWindow.document.body.scrollHeight;

		var dHeight = iframe[i].contentWindow.document.documentElement.scrollHeight;

		var height = Math.max(bHeight, dHeight);

		iframe[i].height =  height;
	  
		}	
		}catch (ex){}

		}
	, 80);
}
	

</script>
</html>
