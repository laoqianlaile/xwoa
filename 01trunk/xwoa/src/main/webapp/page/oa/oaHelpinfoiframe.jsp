<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<head>
<link href="${pageContext.request.contextPath}/themes/oaHelpInfo/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<style type="text/css">
	.black{
		color:black;
	  }
	.gray{
		color:gray;
	}
</style>
</head>
<body>
    <div class="content"> 
		<div style="padding-left:1%;color:#303030;float:left;font-size:12px;text-align:left;">
		<font id="indexforfingle">首页</font>
		<c:if test="${ not empty s_columnType }">><font  id="columntype">${cp:MAPVALUE('columntype',s_columnType) }</font></c:if></div>
		<div style="padding-left: 92%;color:blue;font-weight: bold;">
		<a href="oaHelpinfo!edit.do?columnType=${s_columnType}" class="post" style="text-decoration: none;color:#303030;">发新帖</a></div>
	<table cellpadding="2" cellspacing="0">	
	  	<tr height="29">
	  		<th style="width:60%;text-align: left;padding-left:10px;">
	  		<span id="normal" <c:choose><c:when test="${ empty  s_isgood  or s_isgood eq '0' }">class="black"</c:when><c:otherwise>class="gray"</c:otherwise></c:choose>>普通主题</span>
	  		|<span id="excellent"<c:choose><c:when test="${s_isgood eq '1' }">class="black"</c:when><c:otherwise>class="gray"</c:otherwise></c:choose>>精华主题</span>
	  		</th>
	  		<th style="width:10%;text-align: center">发帖</th>
	  		<th style="width:10%;text-align: center">回复/查看</th>
	  	</tr>
 	  	
	  <c:forEach items="${objList }" var="OaHelpinfo">
	  	<tr class="tr_bg" height="32"><td style="width:60%;text-align: left;">
	  			[${cp:MAPVALUE('columntype',OaHelpinfo.columnType) }]&nbsp;&nbsp;
	  			<c:if test="${OaHelpinfo.isgood eq '1' }">
	  			 <i class="icon icon-color icon-star-on" style="margin-left:15px;" title="精华帖"></i>
	  			</c:if>
	  			<c:if test="${OaHelpinfo.isgood eq '0' }">
	  			 <i class="icon icon-color icon-star-off" style="margin-left:15px;" title="普通帖"></i>
	  			</c:if>
	  			<a href='oaHelpinfo!view.do?djid=${OaHelpinfo.djid}&backcolumnType=${backcolumnType}' style="text-decoration: none;">
	  			<c:choose><c:when test="${fn:length(OaHelpinfo.infoName) gt 10 }">${fn:substring(OaHelpinfo.infoName , 0, 10) }...</c:when>
								<c:otherwise>${OaHelpinfo.infoName} </c:otherwise>
				</c:choose></a>
	  			<c:if test="${not empty OaHelpinfo.fileDocname }">
						        <i class="icon icon-color icon-page" style="margin-left:15px;" title="附件"></i>
							</c:if>
				<c:if test="${ loginer eq   OaHelpinfo.creater }">
									<a href="oaHelpinfo!edit.do?djid=${OaHelpinfo.djid}">
									<i class="icon icon-color icon-edit" style="margin-left:15px;" title="修改"></i>
									</a>
							</c:if>
	  			</td>
	  		<td style="width:10%;text-align: center">${cp:MAPVALUE('usercode',OaHelpinfo.creater)}</td>
	  		<td style="width:10%;text-align: center">${OaHelpinfo.replynum }/${OaHelpinfo.viewnum }</td>
	  	</tr>
	  </c:forEach>
    </table>
    <c:if test="${ not empty s_columnType}">
    <div  class="bottom" style="margin-top:3%;">
    	<div style="padding-left:1%;color:#303030;height:29px;line-height:29px;">板块信息</div>
    	<div style="background:#FFF;height:25px;padding-left:10px;line-height:25px;">
    	<span>版块名称：<font color='red'>${cp:MAPVALUE('columntype',s_columnType) }</font>|</span>
    	<span>主题：<font color='red'>
    	<c:choose>
    	<c:when test="${ null ne  totalTitles }">${totalTitles }</c:when>
    	<c:otherwise>0</c:otherwise>
    	</c:choose>
    	</font>|</span>
    	<span>帖子：<font color='red'>${total }</font>|</span>
    	<span>今日帖子：<font color='red'>${today }</font></span></div>
    </div></c:if>
   </div>
   <c:if test="${ ! empty objList }">
   	 <c:set var="listURL" value="oaHelpinfo!list4iframe.do" />
   	 <c:set var="maxPageItems" value="10"></c:set>
      <%@ include file="/page/common/pagingBar.jsp"%>
   </c:if>
</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
$(function(){
	$('#indexforfingle').click(function(){
		var isgood='${s_isgood}';
		document.location.href="oaHelpinfo!list4iframe.do?s_isgood="+isgood;
	});
	$('#columntype').click(function(){
		 var columnType='${s_columnType}';
		 var isgood='${s_isgood}';
		 window.parent.location="oaHelpinfo!list.do?s_columnType="+columnType+"&s_isgood="+isgood;
	});
});
$(function(){
	$('#normal').click(function(){
		$('#normal').toggleClass("black");
		$('#normal').toggleClass("gray");
		$('#excellent').toggleClass("black");
		$('#excellent').toggleClass("gray");
		var columnType='${s_columnType}';
		document.location.href="oaHelpinfo!list4iframe.do?s_isgood=0&s_columnType="+columnType;
	});
	$('#excellent').click(function(){
		$('#normal').toggleClass("black");
		$('#normal').toggleClass("gray");
		$('#excellent').toggleClass("black");
		$('#excellent').toggleClass("gray");
		var columnType='${s_columnType}';
		document.location.href="oaHelpinfo!list4iframe.do?s_isgood=1&s_columnType="+columnType;
	});
});
</script>
</html>