<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaInformation.view.title" /></title>
</head>


<body class="sub-flow">
 <fieldset class="form">
		<legend>
		${cp:MAPVALUE("msgtype",s_msgtype)}查看
		</legend> 
	

	<%@ include file="/page/common/messages.jsp"%>
	<s:form action=" " method="post" namespace="/oa" id="innermsg_form" validator="true">
	<div class="formButtonOfMail">
	
<div class="buttonAreaOfMail">
	  <c:choose>
		<c:when test="${s_msgtype eq 'P' and  s_mailtype eq 'I'}"><!-- 个人邮件的收件箱或"我的首页"下，查看返回 -->
			<input type="button" class="whiteBtn" target="submit" style="cursor:pointer;" 
	 						data-form="#innermsg_form" 
							data-href="${pageContext.request.contextPath}/oa/innermsg!replayMail.do?msgcode=${object.msgcode}" 
	 						value='答复'>
	 		<input type="button" class="whiteBtnWide" target="submit" style="cursor:pointer;" 
	 						data-form="#innermsg_form" 
							data-href="${pageContext.request.contextPath}/oa/innermsg!replayAllMail.do?msgcode=${object.msgcode}" 
	 						value='全部答复'>	
	 		<input type="button" class="whiteBtn" target="submit" style="cursor:pointer;" 
	 						data-form="#innermsg_form" 
							data-href="${pageContext.request.contextPath}/oa/innermsg!rewardMail.do?msgcode=${object.msgcode}" 
	 					value='转发'>		
 		</c:when>
 	    <c:when test="${s_msgtype eq 'P' and  s_mailtype eq 'O'}">
	 	   <input type="button" class="whiteBtn" target="submit" style="cursor:pointer;" 
	 						data-form="#innermsg_form" 
							data-href="${pageContext.request.contextPath}/oa/innermsg!rewardMail.do?msgcode=${object.msgcode}" 
	 					value='转发'>			
		</c:when>
		 <c:when test="${'D' eq s_mailtype }">
		    <input type="button" class="whiteBtn" target="submit" style="cursor:pointer;" 
	 						data-form="#innermsg_form" 
							data-href="${pageContext.request.contextPath}/oa/innermsg!rewardMail.do?msgcode=${object.msgcode}" 
	 					value='转发'>		
		     <input type="button" class="whiteBtn" target="submit" style="cursor:pointer;" 
 						data-form="#innermsg_form" 
						data-href="${contextPath }/oa/innermsg!editDraft.do?msgcode=${object.msgcode}" 
 					value='编辑'>	
		 </c:when>   
		</c:choose>
        </div>

				<div class="informAreaOfMail">
					<input type="hidden" id="flag" name="flag" value="${flag}" /> 
					<input type="hidden" name="s_mailtype" value="${s_mailtype }" />
					<input type="hidden" name="s_msgtype" value="${s_msgtype }" /> 
					<input type="hidden" name="s_msgstate" value="${s_msgstate }" /> 
					<input type="hidden" name="s_NP_senddate" value="${s_NP_senddate }" /> 
					<input type="hidden" name="s_showtype" value="${s_showtype}" />

					<div class="titleOfMail">
						<c:if test="${not empty  object.msgtitle}">  
						<p> <span> ${object.msgtitle }  </span></p></c:if></div>
					
					<table class="tableOfMail">
						<c:if test="${not empty  object.sender}">
							<tr>
								<td class="leftTdOfMail">发&nbsp;件&nbsp;人&nbsp;:</td>
								<td class="rightTdOfMail">${cp:MAPVALUE('usercode',object.sender) }</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty  object.senddate}">
							<tr>
								<td class="leftTdOfMail">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间&nbsp;:</td>
								<td class="rightTdOfMail"><fmt:formatDate
									value="${object.senddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:if>
						
						<c:if test="${not empty object.ccName }">
							<tr>
								<td class="leftTdOfMail">抄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;送&nbsp;:</td>
								<td class="rightTdOfMail">${object.ccName}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty object.bccName and flag}">
							<tr>
								<td class="leftTdOfMail">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;送&nbsp;:</td>
								<td class="rightTdOfMail">${cp:MAPVALUE('usercode', session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode )}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty object.msgannexs}">
						    <tr>
								<td class="leftTdOfMail">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件&nbsp;:</td>
								<td class="rightTdOfMail">${msgannesxNum }个(
								<c:forEach items="${object.msgannexs }" var="v" varStatus="status">
								  <span class="annexOfMail"><img src=""/><a onclick="downloadFile(this,${v.fileinfo.filecode}); return false;"
								        href='javascript:void(0);' >
								        ${v.fileinfo.filename}.${v.fileinfo.fileext}</a>
								        <c:if test="${status.index + 1 < msgannesxNum }">,</c:if></span>
								</c:forEach>
								)</td>
							</tr>
						</c:if>
					</table>
						
				</div>
			</div>
	
	<%-- <div class="textOfMail">
		<span
			style="width: 100%; position: relative; padding-left: 10px; padding-top: 15px;">${object.msgcontent}</span>
	</div> --%>
	
	<div class="show-hd">
			<div>
				<span style="width: 100%; padding-left: 10px; padding-top: 15px;">
				 <iframe id="bodyContentIframe" 	width="100%" frameborder="0" 
				 scrolling="no" border="0"></iframe>
				</span>
			</div>
		</div>
	</s:form>
		
	</fieldset>
	
	<iframe id="downloadFileBox" height="0" style="display:none;"></iframe>
	<div id="bodyContentDiv" style="display:none">${object.msgcontent}</div>
	
	<script type="text/javascript">
	 
	$(function(){
		/* 设置附件的图标 */
		$('span.annexOfMail').find('a').each(function(){
			
			var $this = $(this);
			var v = $this.text().split('.');
			var docType = null;
			var img = $this.prev('img');
			if(v.length > 1){
				docType = v[v.length -1];
			}
			
			if('txt'== docType){
				img.attr('src','../themes/blue/images/fu_txt.gif');
			}else if('doc' == docType || 'docx' == docType){
				img.attr('src','../themes/blue/images/fu_doc.gif');
			}else if('xls' == docType || 'xlsx' == docType){
				img.attr('src','../themes/blue/images/fu_exl.gif');
			}else if('pdf' == docType){
				img.attr('src','../themes/blue/images/fu_pdf.gif');
			}else if('ppt' == docType){
				img.attr('src','../themes/blue/images/ppt.png');
			}else if('zip' == docType || 'rar' == docType){
				img.attr('src','../themes/blue/images/fu_rar.gif');
			}else if('jpg' == docType || 'gif' == docType || 'jpeg' == docType || 'png' == docType){
				img.attr('src','../themes/blue/images/fu_jpg.gif');
			}else{
				img.attr('src','../themes/blue/images/enclosure.png');
			};
		});
		
		readContent();
		
	});
	
	function downloadFile(obj,filecode){
    	
    	var href = "<%=request.getContextPath()%>/oa/innermsg!annexExist.do";
    	$.post(href, {filecode: filecode}, function(result){
    		
    		if(null != result && null != result.error){
    			alert(result.error);
    		}else{
    			window.location.href = "<%=request.getContextPath()%>/app/fileinfoFs!downloadfile.do?filecode="+filecode;
    		};
    		
		}, 'json');
    	
    }
	var init;
	function readContent(){
		$("#bodyContentIframe").contents().find("body").html($("#bodyContentDiv").html());
	  setTimeout(function(){
			var h=$("#bodyContentIframe").contents().find("body")[0].scrollHeight;
			$("#bodyContentIframe").height(h);
			setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		},200);
	}
	</script>
	
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>
