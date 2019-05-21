<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/styles/default/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/styles/default/css/tv/tv.css"
	rel="stylesheet" type="text/css" />

<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/tv/tv.js"></script>

<title>视频节目</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" style="display: block;">
			<div style="width: 20%; position: absolute; top: 20%;">
				<input type="text" name="name" class="form-control" id="search"
					placeholder="视频名称或关键字">
			</div>
			<div style="width: 73%;; position: relative; top: 10px; left: 25%;">
				<button type="submit" class="btn btn-default" id="btn" style="height:35px !important;">搜索</button>
				<!-- <a class="pull-right btn navbar-btn btn-primary"
					style="position: absolute; right: 1%;color:white;height:20px;" href="/book/add"><span
					class="glyphicon glyphicon-cloud-upload"></span> 添加视频</a> -->
			</div>
		</div>
	</nav>
	

    
	<div
		style="width: 100%;font-size:16px;margin-bottom:15px;margin-top:15px;">
		您当前的位置：<a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoMainpage.do" style="font-size:16px;">首页</a> ><a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoMainpage.do" style="font-size:16px;">视频节目</a> >
        <a style="font-size:16px;">${objList[0].title}</a>
	</div>
     <div class="catalogList">
	   <ul class="inner_catalogList">
	      <li>
	         <div class="label">按年代:</div>
	         <div class="con" id="yearSearch">
	            <a <c:if test="${empty yearSearch}">class="active"</c:if> value="" href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do">全部</a>
	            <c:forEach begin="${0}" end="${4}" var="i">
					   <a href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do?yearSearch=${year+i+1}" value="${year+i+1}" 
					      <c:if test="${yearSearch eq year+i+1}">class="active"</c:if>  id="searchlist">${year+i+1}
					    </a>
				</c:forEach>
	         </div>
	      </li>
	      <li>
	         <div class="label">按类型:</div>
	         <div class="con" id="typeSearch">
	            <a value="" href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do" <c:if test="${empty typeSearch}">class="active"</c:if>>全部</a>
	            <c:forEach var="row" items="${cp:DICTIONARY('videoType')}">
	                <a value="${row.datacode }" href="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoShowList.do?typeSearch=${row.datacode}" 
	                     <c:if test="${typeSearch eq row.datacode}">class="active"</c:if> id="searchlist"><c:out value="${row.datavalue}" />
	                </a>
	            </c:forEach>
	         </div>
	      </li>
	      <li></li>
	   </ul>
	
	</div>
	<div class="hot_box" style="margin-top:60px;">
		<div class="hot_left" style="min-height:200px !important;">
			<div class="hot_l_title" style="background-color:#fff !important;">
				<div class="tabbox" >
					<a href="" class="hot_style">该视频没有可播放的资源....</a>
				</div>
			</div>
        </div>
     </div>
   
	
</body>
<script type="text/javascript">
        window.scrollTo(0, 0);
       $(function() {
       	$('#btn').click(function(){
       	         window.location.href='oa/oaSubvideoInformation!oasubvideoShowList.do?s_search='+$("#search").val();  
           });
       });
</script>
</html>