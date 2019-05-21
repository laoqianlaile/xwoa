
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


	

<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<title>
			 办件过程
		</title>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/scripts/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>				
		<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<script type="text/javascript">
		  $(function(){
			  $("tbody td.userNameTD").poshytip({
				  content:function(){
					  var usercode = $(this).find("img").data("usercode");
					  var html = "";
					   $.ajax({
						  type:'post',
						  async:false,
						  url:"${pageContext.request.contextPath}/oa/oaUserinfo!getUserConnectWay.do",
						  dataType:"json",
						  data:{"usercode":usercode},
						  success:function(data){
							  html = "办公室电话："+(data.workphone==''?'无':data.workphone)+" 手机:"+(data.mobile==''?'无':data.mobile);
						  }
					  }); 
					  return html;
				  }
			  });
		  });
		</script>
		
		<style type="text/css">
		.eXtremeTable .odd td, .eXtremeTable .even td{white-space: normal;}
		.eXtremeTable .highlight td{white-space: normal;}
		/* #ec_table{table-layout: fixed} */
		</style>
	</head>

<body>
		<%@ include file="/page/common/messages.jsp"%>
	<%-- <c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	<h3 class="sub-flow-title ${notitle }">办件过程信息</h3> --%>

    <input type="hidden" value="${object.djId }" id="djid"/>
	<!-- <div style="float:right;">
		<input type="button" value="查看全部" class="btn" />
		<a href="#" onclick="openIdeaLogDetails();">查看全部</a>
	</div> -->
	<ec:table action="powerruntime/generalOperator!listIdeaLogs.do" items="ideaLogs" var="optIdeaInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false" >
			<ec:row>
				<ec:column property="nodename" title="环节名称" style="text-align:center" sortable="false">
					<c:if test="${optIdeaInfo.warntotal eq 'A' and optIdeaInfo.warntype eq '0'}">
						<span class="icon" style="background:url(${pageContext.request.contextPath}/images/risk.gif) right center no-repeat !important" title="疑似异常点"></span>
					</c:if>
					<c:if test="${optIdeaInfo.warntype ne '0'}">&nbsp; &nbsp;</c:if>
					${optIdeaInfo.nodename }
				</ec:column>
				<ec:column property="unitname" title="部门名称" style="text-align:center" sortable="false"/>
				<ec:column property="username" title="办理人员姓名" style="text-align:center" sortable="false" styleClass="userNameTD" >
				${optIdeaInfo.username}
                <!-- 				rtx 开关 -->
				<!--rtx 开关 -->
				<img data-usercode='${optIdeaInfo.usercode}' align="absbottom" width =16 height=16 src="${pageContext.request.contextPath}/scripts/rtx/images/blank.gif" <c:if test="${cp:MAPVALUE('SYSPARAM','RTX') eq 'T'}">onload='RAP("${cp:MAPVALUE('userloginname',optIdeaInfo.usercode)}");'</c:if>>
				</ec:column>
				<ec:column property="transdate" title="办理时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${optIdeaInfo.transdate}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<ec:column property="transidea" title="办理决定" style="text-align:center" sortable="false"/>
				<ec:column property="transcontent" title="办理意见" style="text-align:center" sortable="false" width="28%">
				<%--<input type="hidden" value="${optIdeaInfo.transcontent}"/>
				 <c:choose>
						<c:when test="${fn:length(optIdeaInfo.transcontent) > 20}">
							<c:out
								value="${fn:substring(optIdeaInfo.transcontent, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${optIdeaInfo.transcontent}" />
						</c:otherwise>
					</c:choose> --%>
				${optIdeaInfo.transcontent}
				</ec:column>
			</ec:row>		
		</ec:table>
		<span style="color: blue">温馨提示：鼠标移动到办理人员处，可查看对应人员的办公电话或移动电话。</span>
	</fieldset>
	
	<script type="text/javascript">
	function openIdeaLogDetails(){
		
		var djid = $('#djid').val();
		var src = "${pageContext.request.contextPath}/powerruntime/generalOperator!listIdeaLogDetails.do?djId=" 
				 + djid + "&notitle=1";
		art.dialog
		.open(src,
				 {title: '办件过程详细信息', width: 1200, height: 700});
	}
	</script>
	</body>
</html>
