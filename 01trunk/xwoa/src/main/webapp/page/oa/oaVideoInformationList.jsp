<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${pageContext.request.contextPath}/themes/css/video.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function() {
		$("#uploadVideo").bind("click", function() {
			window.location.href = "oaVideoInformation!built.do";
		});
	});
</script>
<style type="text/css">
/*    td{border-right:1px solid #ADADAD;border-bottom:1px solid #ADADAD} */
body{padding-left:0px !important;padding-right:0px !important;}
a:link {
	text-decoration: none;
}
.video{
  position:relative;
  float:left;
  margin-bottom:10px;
  width: 96%;
  margin-top: 10px;
  border: 1px solid #cdcdcd !important;
  padding:0px 10px 0px 10px;
  margin-left:2%;
}
.video_h3{
  background: #ADD8E6; 
  height: 26px; 
  width: 100%;
  margin-left:-10px;
  padding-right:20px;
}
.nav_right{
 width: 30%; 
 margin-top: 20px; 
 padding-left: 20px;
 float:right !important;
}
.nav_left{
  width: 59%; 
  height: auto; 
  margin-top: 20px; 
  padding-left: 20px; 
  border: 0px solid #ADADAD;
  float:left !important;
  text-overflow: ellipsis;
  overflow: hidden;
}
.nav_info{
  width: 60%; 
  height: 40%; 
  margin-top: 5px; 
  margin-left: 20px;
}
.search{margin-left:2%;width:96%}
#ec{margin-left:2%;width:98%}
</style>
</head>

<body style="color: #333; padding-top: 20px; padding-left: 10%; padding-right: 10%">

<!-- 	<div id="nav1" -->
<!-- 		style="border: 1px solid #ADADAD; height: 100px; padding-top: 10px; position: relative; width: 99%"> -->
		<!-- 		<div style="width: 38%;"> -->
		<!-- 			<img style="width:100%;margin-top:10px;" -->
		<%-- 				src="${pageContext.request.contextPath}/styles/default/images/centit.jpg" /> --%>
		<!-- 		</div> -->
		<!-- 		<div style="text-align:center;width:60%"> -->
		<!-- 			<div -->
		<!-- 				style=" padding-left: 24px; height:50px;float: right;width:100%; margin-top: 1px; padding-top: 10px; padding-bottom: 0px;"> -->
		<fieldset  class="search" >
		<legend>
			视频节目查看
		</legend>
		<s:form style="margin-top:0;margin-bottom:5"
			action="oaVideoInformation" namespace="/oa">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td >名称： <s:textfield name="s_title"
							value="%{#parameters['s_title']}" />
					</td>
					<td>简介： <s:textfield name="s_videoName"
							value="%{#parameters['s_videoName']}" /> 
							
					</td>
					<td><s:submit method="list"
							style="margin-left:20px" key="opt.btn.query" cssClass="btn" /></td>
				</tr>
				<tr>
					<td colspan="3">按类型： 
<%-- 					<input type="checkbox" id="${ck_all}" --%>
<!-- 						value="" /> -->
						
						<a href="<%=request.getContextPath()%>/oa/oaVideoInformation!list.do?s_infoType="
						class="zjsz">全部</a> <c:forEach var="row"
							items="${cp:DICTIONARY('videoType')}">
<%-- 							<input type="checkbox" id="ck_${row.datacode}" --%>
<%-- 								value="${row.datacode}" /> --%>
							<a
								href="<%=request.getContextPath()%>/oa/oaVideoInformation!list.do?s_infoType=${row.datacode}"
								class="zjsz"> <c:out value="${row.datavalue}" /></a>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="3">按时间：
					 <%
						java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
									"yyyy");

							java.util.Date currentTime = new java.util.Date();//得到当前系统时间

							String year = formatter.format(currentTime); //将日期时间格式化 
							request.setAttribute("year", year);
					%> 
					<a href="<%=request.getContextPath()%>/oa/oaVideoInformation!list.do"
						class="zjsz">全部</a> 
					<c:forEach begin="${0}" end="${10}" var="i">
							<c:set var="obj" value="${i}" />
				         	<a
								href="<%=request.getContextPath()%>/oa/oaVideoInformation!list.do?s_year=${year-i}"
								class="zjsz"> ${year-i }  </a>  
					</c:forEach>
					<a href="<%=request.getContextPath()%>/oa/oaVideoInformation!list.do?s_begyear=${year-10}"
						class="zjsz">更多</a> 
					</td>
				</tr>


			</table>
		</s:form>
		</fieldset>
		<!-- 			</div> -->
		<!-- 		</div> -->
<!-- 	</div> -->
	<ec:table action="oa/oaVideoInformation!list.do" items="objList"
		var="oaVideoInformation" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<c:if test="${not empty oaVideoInformation.no}">
				<div class="video" id="div_${oaVideoInformation.no }">
					<div class="video_h3">
						<h3>
							<a
								href="oa/oaVideoInformation!view.do?no=${oaVideoInformation.no}"
								class="zjsz">${oaVideoInformation.title}</a>
						</h3>
					</div>
					<div class="video_nav" id="nav">
						<div class="nav_right">
							<span id="overfpic"><img id="appicon"
								src='${contextPath }/oa/oaVideoInformation!downloadPhoto.do?no=${oaVideoInformation.no}' /></span>
						</div>
						<div class="nav_left">
							<span>${oaVideoInformation.videoName} </span>
						</div>
						<div style="width: 60%; height: 7%; margin-top: 8px; margin-left: 20px;">
							<span style="font-size: 15px; font-weight: bold;">视频信息:</span>
						</div>
						<div class="nav_info">
							<table
								style="width: 100%; height: 90%; border-left: 1px solid #ADADAD; border-top: 1px solid #ADADAD"
								cellpadding="0" cellspacing="0" class="viewTable">
								<tr>
									<!-- 					<td>尺寸 :120x720</td> -->
									<%-- 					<td>格式:   ${oaVideoInformation.videoStyle }</td> --%>
									<td>发布时间 : <fmt:formatDate
											value='${oaVideoInformation.releaseDate}'
											pattern='yyyy-MM-dd' />
									</td>
									<td>视频类型:
										${cp:MAPVALUE("videoType",oaVideoInformation.infoType )}</td>
								</tr>
								<tr>
									<td>视频状态: 上线</td>
									<td>导演:${oaVideoInformation.derector }</td>
									<!-- 					<td>清晰度:<img style="margin-top:5px;height:15px;margin-left:10px;" -->
									<%-- 							src="${pageContext.request.contextPath}/styles/default/images/speed.jpg"/></td> --%>
								</tr>
								<tr>

									<td>上传者:
										${cp:MAPVALUE("usercode",oaVideoInformation.creater )}</td>
									<td>关键字: ${oaVideoInformation.publicKey }</td>
								</tr>

							</table>

						</div>
					</div>
				</div>
			</c:if>
			<%-- </ec:column> --%>
			<%--   </s:iterator> --%>
		</ec:row>
	</ec:table>


	<%--    <c:set var="listURL" value="oaVideoInformation!list.do"></c:set> --%>
	<%--    <%@ include file="/page/common/pagingBar.jsp"%> --%>

	<div class="background" id="background" style="display: none;"></div>
	<div class="progressBar" id="progressBar" style="display: none;"></div>
	<%@ include file="/page/common/charisma-js.jsp"%>

</body>

</html>
