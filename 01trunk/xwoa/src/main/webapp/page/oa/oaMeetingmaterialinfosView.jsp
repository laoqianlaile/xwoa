<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><p>会议资料附件</p></title>
<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/information.css"
	rel="stylesheet">
<style type="text/css">
   .docFile{background:url('${ctx}/scripts/kindeditor-4.1.7/themes/default/default.png') no-repeat 0 -95px;display:inline-block;height:17px;font-size:12px;padding-left:18px;}
	body { 
	    overflow-x : hidden;   
	    overflow-y : hidden;   
	}

</style>	
</head>


<body class="sub-flow">
	<div class="tableright" style="left:0px;position:static;width:98%" >
		<section class="public-position" id="publicPosition">
			<div class="pubpos-nav">
			</div>
		</section>

          <ec:table action="${pageContext.request.contextPath}/oa/oaMeetingmaterialinfos!meetingDownFile.do" items="meetingInfo" var="meetingInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false" >
			<ec:row>
				<ec:column property="meetingAttendee" title="参会人员" style="text-align:center" sortable="false">
				${meetingInfo.meetingAttendee}
				</ec:column>
				<ec:column property="isback" title="是否回收" style="text-align:center" sortable="false">
				${meetingInfo.isback}
				</ec:column>
				<ec:column property="transdate" title="回收时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${meetingInfo.backtime}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<%-- <ec:column property="transcontent" title="批注内容" style="text-align:center" sortable="false" width="28%">
				${meetingInfo.remark}
				</ec:column> --%>
			</ec:row>		
		</ec:table>
		</br>
		<div class="cont-tabShow">	
		<div class="show-hd">
			<div>
			<span style="width: 100%; position: relative; padding-left: 20px;"><font
						color="blue"><strong>附件下载</strong></font>:
						<c:if test="${not empty docAttachments}">
							<c:forEach var="docFile" items="${docAttachments}">
							  <a href="#" class="docFile" onclick="downFile('${docFile.stuffid}')"
							   style="text-decoration: underline"> ${docFile.filename} </a>&nbsp;&nbsp;
							</c:forEach>
						</c:if>  
			</span>
			</div>			
			<br>
		</div>
	</div>
<div id="bodyContentDiv" style="display:none">${bodyContent}</div>
</body>

<script type="text/javascript">
	function downFile(id) {
		var url = "<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=" + id;
		document.location.href = url;
	}
</script>
</html>