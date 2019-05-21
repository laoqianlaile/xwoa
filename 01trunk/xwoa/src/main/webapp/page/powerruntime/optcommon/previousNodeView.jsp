<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史附件查看</title>
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<script src="${ctx3rdJS}/jquery-1.9.1.min.js"></script>
<script src="${ctx3rdJS}/layer-2.1/layer.js"></script>
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
/* 	   $("li").each(function(){
		   $(this).find("span").mouseover(function(){
			   var archivetype = $(this).data("archivetype")?$(this).data("archivetype"):'办理附件';
			   var iszhi = $(this).data("iszhi")=='true'?'是':'否';
			   var filetype = $(this).data("filetype");
			   var uploader = $(this).data("uploader");
			   var id = $(this).data("id");
			   var text = '<div>'
			            + '  <div>分类：'+archivetype+'</div>'
			            + '  <div>上传人：'+uploader+'</div>'
			            + '</div>';
			   layer.tips(text, this, {
				   time: 4000,
				   tips: [4, '#0FA6D8'] //还可配置颜色
				 });
		   }).mouseout(function(){
			   layer.closeAll('tips');
		   }).click(function(){
			   if($(this).data("iszhi")==true){
				   return;
			   }else{
				   var id = $(this).data("id");
				   window.location.href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid="+id;
			   }
		   });
		  
	   }); */
	  
   });
</script>
	<style type="text/css">
		.eXtremeTable .odd td, .eXtremeTable .even td{white-space: normal;}
		.eXtremeTable .highlight td{white-space: normal;}
		/* #ec_table{table-layout: fixed} */
	</style>
</head>
<body style="font-family:Microsoft YaHei !important; font-size:14px;color:black;">
 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">上一节点办理信息</div>
	     <div class="title-split-line"><span></span></div>
</div>
    <ec:table action="powerruntime/generalOperator!listPreNode.do" items="ideaLogs" var="optIdeaInfo"
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
				<img data-usercode='${optIdeaInfo.usercode}' align="absbottom" width =16 height=16 src="${pageContext.request.contextPath}/scripts/rtx/images/blank.gif" <c:if test="${cp:MAPVALUE('SYSPARAM','RTX') eq 'T'}">onload='RAP("${cp:MAPVALUE('userloginname',optIdeaInfo.usercode)}");'</c:if>>
				</ec:column>
				<ec:column property="transdate" title="办理时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${optIdeaInfo.transdate}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<ec:column property="transidea" title="办理决定" style="text-align:center" sortable="false"/>
				<ec:column property="transcontent" title="办理意见" style="text-align:center" sortable="false" width="28%">
				${optIdeaInfo.transcontent}
				</ec:column>
			</ec:row>		
	</ec:table>
</body>
</html>