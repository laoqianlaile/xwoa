<!DOCTYPE HTML>
<HTML lang="zh">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<TITLE>新闻动态</TITLE>

<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/global.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/layout.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/bug.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/global_nav.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/layout_2013.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/home_style_2013.css"
	rel="stylesheet">


<!--[if lte IE 9]>
  	<script src="./images/html5.js"></script>
  	<![endif]-->
<!--[if IE 6]>
  	<script src="./images/DD_belatedPNG_0.0.8a-min.js"></script>
  	<script>
        DD_belatedPNG.fix('#header,.SubMenuBottom,.txt-more,.more,img,background');
  	</script>
  	<![endif]-->

<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/scripts/oaInformation/public.js"></script>

<script
	src="${pageContext.request.contextPath}/scripts/oaInformation/home_global_2013.js"></script>

<script src="${pageContext.request.contextPath}/scripts/tv/tv.js"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/tv/tv.css"
	rel="stylesheet" type="text/css" />

</HEAD>


<body >

	<div class="public-position" id="publicPosition">
		<div class="pubpos-nav">
			您当前的位置：新闻动态
			<c:if test="${not empty infoType}">&nbsp;&gt;&nbsp;
			<a title="${cp:MAPVALUE(" infoType",infoType) }" class="CurrChnlCls"
					href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${infoType}">${cp:MAPVALUE("infoType",infoType)
					}</a>
			</c:if>
		</div>
		<div class="pubpos-search-box">

			<%@ include file="/page/common/messages.jsp"%>
			<s:form action="oaInformation" method="post" namespace="/oa"
				id="oaInformationForm">
				<input type="hidden" id="infoType" name="infoType"
					value="${infoType}" />
				<input type="hidden" id="flag" name="flag" value="GGZY" />
				<s:hidden name="show_type" value="%{show_type}"></s:hidden>

				<input name="s_searchword" class="search-txt"
					id="txt_searchWord_nav" type="text" size="100" placeholder="请输入关键词">

				<input name="submit" class="submit-botton" id="home_btnSearchSubmit"
					type="submit" value="搜 索">
			</s:form>
		</div>
	</div>
	<!--site top tips over-->
	<div class="page-box" id="Cbody">

		<section class="first-row">
			<div  id="first">
				<article class="mode">
					<div class="m-lf">
						<div class="module">

							<div id="focus_background">
								<div id="focus_box">
									<img alt=""
										src="${pageContext.request.contextPath}/${objList[0].imagePath}"
										id="focus_box_background" />
									<!-- 										<span class="prev"></span> <span -->
									<!-- 										class="next"></span> -->


									<ul class="smal_pic">
										<c:forEach items="${objList}" varStatus="i" var="info">
											<li><a
												href="${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${info.no }&flag=GGZY">
													<img style="width: 66px; height: 44px;"
													src="${pageContext.request.contextPath}/${info.imagePath}"
													alt=""
													backImg="${pageContext.request.contextPath}/${info.imagePath}"
													backColor="#704F84" />
											</a></li>
										</c:forEach>
									</ul>
									<!-- 									<span class="ico"></span> -->
									<div class="focus_title">
										<c:forEach items="${objList }" varStatus="i" var="info">
											<a
												href="${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${info.no }&flag=GGZY"
												style="display: block"><c:choose>
													<c:when test="${fn:length(info.title) gt 15}">${fn:substring(info.title , 0, 15) }...</c:when>
													<c:otherwise>${info.title} </c:otherwise>
												</c:choose></a>
										</c:forEach>
										<script type="text/javascript">
											    if(${objList.size()>1}){
											    	
											    	$('.focus_title a').eq(0).show().siblings('a').hide();
											    	
											    }
			                             </script>
									</div>
								</div>
							</div>

						</div>
						<!--slider over-->
						<div class="module">
							<div class="module">
								<div class="tit-tabNav" id="newsTab-2-box">
									<ul class="tabNav-list" id="newsTab-2">
										<c:forEach items="${infoTypesList }" varStatus="i" var="info"
											begin="1" end="3">
											<li><a title="${info.datavalue}"
												<c:if test="${i.first}" > class="current" </c:if>
												href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
													${info.datavalue} </a></li>
										</c:forEach>
									</ul>
									<c:forEach items="${infoTypesList }" varStatus="i" var="info"
										begin="1" end="3">
										<a title="点击查看更多" class="more"
											href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
											更多 </a>
									</c:forEach>

								</div>
								<div class="cont-tabShow" id="newsShow-2">
									<c:forEach items="${infoList }" varStatus="i" var="info"
										begin="1" end="3">
										<ul class="infor-list"
											<c:if test="${not i.first}" > style="display: none;" </c:if>>
											<c:forEach items="${info}" varStatus="d" var="infodetail">
												<li><a title="${infodetail.title}"
													href="${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${infodetail.no }&flag=GGZY">
														<c:choose>
															<c:when test="${fn:length(infodetail.title) gt 30}">${fn:substring(infodetail.title , 0, 30) }...</c:when>
															<c:otherwise>${infodetail.title} </c:otherwise>
														</c:choose>
												</a> <fmt:formatDate value="${infodetail.releaseDate}"
														pattern="yyyy-MM-dd" /></li>
											</c:forEach>
										</ul>
									</c:forEach>
								</div>
								<div class="open-bottom"></div>
							</div>
						</div>
					</div>
					<!--first row lf over-->
					<div class="m-ct" id="col-2">
						<div class="module">
							<div class="tit-tabNav" id="newsTab-3-box">
								<ul class="tabNav-list" id="newsTab-3">
									<c:forEach items="${infoTypesList }" varStatus="i" var="info"
										begin="4" end="6">
										<li><a title="${info.datavalue}"
											<c:if test="${i.first}" > class="current" </c:if>
											href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
												${info.datavalue} </a></li>
									</c:forEach>
								</ul>
								<c:forEach items="${infoTypesList }" varStatus="i" var="info"
									begin="4" end="6">
									<a title="点击查看更多" class="more"
										href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
										更多 </a>
								</c:forEach>
							</div>

							<div class="cont-tabShow" id="newsShow-3">
								<c:forEach items="${infoList }" varStatus="i" var="info"
									begin="4" end="6">
									<ul class="infor-list"
										<c:if test="${not i.first}" > style="display: none;" </c:if>>
										<c:forEach items="${info}" varStatus="d" var="infodetail">
											<li><a title="${infodetail.title}"
												href="${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${infodetail.no }&flag=GGZY">
													<c:choose>
														<c:when test="${fn:length(infodetail.title) gt 30}">${fn:substring(infodetail.title , 0, 30) }...</c:when>
														<c:otherwise>${infodetail.title} </c:otherwise>
													</c:choose>
											</a> <fmt:formatDate value="${infodetail.releaseDate}"
													pattern="yyyy-MM-dd" /></li>
										</c:forEach>
									</ul>
								</c:forEach>
							</div>
							<div class="open-bottom "></div>
						</div>
						<div class="module">
							<div class="tit-tabNav" id="newsTab-1-box">
								<ul class="tabNav-list" id="newsTab-1">

									<c:forEach items="${infoTypesList }" varStatus="i" var="info"
										begin="7"
										end='${infoTypesList.size() lt 11 ? infoTypesList.size(): "11"}'>
										<li><a title="${info.datavalue}"
											<c:if test="${i.first}" > class="current" </c:if>
											href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
												${info.datavalue} </a></li>
									</c:forEach>
								</ul>
								<c:forEach items="${infoTypesList }" varStatus="i" var="info"
									begin="7"
									end='${infoTypesList.size() lt 10 ? infoTypesList.size(): "10"}'>
									<a title="点击查看更多" class="more"
										href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${info.id.datacode}&flag=GGZY">
										更多 </a>
								</c:forEach>
							</div>
							<div class="cont-tabShow" id="newsShow-1">

								<c:forEach items="${infoList }" varStatus="i" var="info"
									begin="7"
									end='${infoList.size() lt 11 ? infoList.size(): "11"}'>
									<ul class="infor-list"
										<c:if test="${not i.first}" > style="display: none;" </c:if>>
										<c:forEach items="${info}" varStatus="d" var="infodetail">
											<li><a title="${infodetail.title}"
												href="${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${infodetail.no }&flag=GGZY">
													<c:choose>
														<c:when test="${fn:length(infodetail.title) gt 30}">${fn:substring(infodetail.title , 0, 30) }...</c:when>
														<c:otherwise>${infodetail.title} </c:otherwise>
													</c:choose>
											</a> <fmt:formatDate value="${infodetail.releaseDate}"
													pattern="yyyy-MM-dd" /></li>
										</c:forEach>
									</ul>
								</c:forEach>
							</div>
							<!-- 							<div class="open-bottom open-bottom2"></div> -->
						</div>
					</div>
					<!--first row rg over-->
				</article>
			</div>
		</section>
	</div>
</body>
<script type="text/javascript">
$(function() { $('#home_btnSearchSubmit') .click( function() { $form =
$('#oaInformationForm'); $form .attr('action',
'${pageContext.request.contextPath}/oa/oaInformation!mainlist.do');
$form.submit(); }); });
</script>

</HTML>
