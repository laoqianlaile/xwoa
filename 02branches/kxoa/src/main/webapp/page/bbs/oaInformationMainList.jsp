<!-- <!DOCTYPE html> -->
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/blue/style.css" rel="stylesheet" type="text/css" />		
<html>
<head>
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css"
	rel="stylesheet">
	<link
	href="${pageContext.request.contextPath}/styles/default/css/oaInformation/information.css"
	rel="stylesheet">	
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title><s:text name="oaBbsDashboard.list.title" /></title>
<style>

.title{
    display: block;
    width: 95%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.bianji{display: inline-block;width:30px;padding-left:15px;background:url(${pageContext.request.contextPath}/newStatic/image/bj.png) no-repeat left center}
.delete_email{display: inline-block;width:30px;padding-left:15px;background:url(${pageContext.request.contextPath}/newStatic/image/sc.png) no-repeat left center}

</style>

</head>
<body id="body_">
	<%-- <div class="tableleft">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="news"><a title="${row.datavalue}"
					href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?flag=GGZY">
						资讯 </a></td>
			</tr>
			<c:forEach var="row" items="${cp:DICTIONARY('infoType')}">
				<option value="${row.datacode}">
					<c:if test="${row.datatag eq 'T'}">
				<tr>
					
					<td ${row.datacode eq infoType ? ' style="background: #D8E2E5;"' : ''}>
					<a
						title="${row.datavalue}"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?s_infoType=${row.datacode}&flag=GGZY">
							<c:out value="${row.datavalue}" />
					</a>
					</td>
				</tr>
					</c:if>
				<!-- </option> -->
			</c:forEach>
		</table>
	</div> --%>

	<div class="tableright" style="left: 0px;position:static;width:98%">

		<section class="public-position" id="publicPosition">
			<div class="pubpos-nav">
				您当前的位置：
				<c:if test="${not empty infoType}">&nbsp;
			<a title="${cp:MAPVALUE(" infoType",infoType) }" class="CurrChnlCls"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=${infoType}">${cp:MAPVALUE("infoType",infoType)
						}</a>
				</c:if>
				<c:if test="${empty infoType and notread eq 'T'}">&nbsp;
			<a title="未读" class="CurrChnlCls"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?notread=${notread}">未读</a>
				</c:if>
				<c:if test="${empty infoType and  empty notread }">&nbsp;
			<a title="全部" class="CurrChnlCls"
						href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do">全部</a>
				</c:if>
			</div>
			

			<div class="pubpos-search-box" style="float: right;">

				<%@ include file="/page/common/messages.jsp"%>
				<s:form action="oaInformation" method="post" namespace="/oa"
					id="oaInformationForm">
					<input type="hidden" id="infoType" name="infoType"
						value="${infoType}" />
					<input type="hidden" id="notread" name="notread"
						value="${notread}" />
					<input type="hidden" id="flag" name="flag" value="GGZY" />
					<s:hidden name="show_type" value="%{show_type}"></s:hidden>

					<input name="s_searchword" class="search-txt"
						id="txt_searchWord_nav" type="text" size="100"
						placeholder="请输入关键词">

					<input name="submit" class="submit-botton"
						id="home_btnSearchSubmit" type="submit" value="搜 索">
				</s:form>
			</div>
			<c:if test="${(cp:CHECKUSEROPTPOWER('NEWS_GGZY', 'edit') and infoType eq 'news')
						or (cp:CHECKUSEROPTPOWER('TZGG_GGZY', 'edit') and infoType eq 'info' )
						or (cp:CHECKUSEROPTPOWER('BULLETIN_VIEW', 'edit') and infoType eq 'bulletin')
			}">
			<div style="float: right;margin-top: 4px;margin-right: 20px;">
					<s:form action="oaInformation" method="post" namespace="/oa"
					id="oaInformationForm_add">
					<input type="hidden" name="infoType" value="${infoType}" />
					<s:submit method="built"  key="opt.btn.new" cssClass="whiteBtnWide" readonly=""
					cssStyle="width:74px;height:24px;border:none;"
					/>
					
					</s:form>
			
			</div>
			</c:if>
		</section>
		<table class="maintable" border="0" cellpadding="0" cellspacing="0" style="margin-left: 0px;margin-right: 0px;table-layout: fixed;width:100%">
			<!-- 	 <form id="form1" -->
			<%-- 		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do" --%>
			<!-- 		method="post"> -->

			<%-- <tr class="newsIcon">
				<td colspan="2">${cp:MAPVALUE('infoType',infoType)=='' ?"新闻动态"
					: cp:MAPVALUE('infoType',infoType)}</td>
			</tr> --%>
			<c:if test="${empty vobjList }">
				<tr><td style="text-align: right;">未找到记录</td></tr>
			</c:if>
			<c:forEach items="${vobjList }" var="oaInformation" varStatus='i'>
				<tr style="width: auto;">
					<td style="width:61px;padding-left: 2px;padding-right: 0px;padding-bottom: 0px;">
					<c:if test="${oaInformation.readstate eq '已读'}">
						<!-- <span class="read" /> -->
						<span style="color: green;width: 100%">【${oaInformation.readstate }】</span>
					</c:if>	
					<c:if test="${oaInformation.readstate eq '未读'}">
						<!-- <span class="notread" /> --><span style="color: orange;width: 100%">【${oaInformation.readstate }】</span>
					</c:if>
					<%-- <c:if test="${oaInformation.isTop eq 'T'}">
						<img style='vertical-align: middle;margin-right: 1px;' src='${pageContext.request.contextPath}/themes/default/improve/new.png' "+
							"border='0' />
					</c:if> --%>
					</td>
					 <c:if test="${oaInformation.isTop eq 'T'}">
						<td style="width: 58px;padding-left: 0px;padding-right: 0px;padding-bottom: 0px;">
						<img style='vertical-align: middle;margin-right: 1px;' src='${pageContext.request.contextPath}/newStatic/image/zhiding.png' "+
							"border='0' />
						</td>
					</c:if> 
					<%-- <c:if test="${oaInformation.isTop ne 'T'}">
						<td colspan="2">
						</td>
					</c:if> --%>
					<c:if test="${oaInformation.isTop eq 'T'}">
					<td class="td_search" style="padding-left: 0px;padding-bottom: 0px;" colspan="2">
					</c:if>
					<c:if test="${oaInformation.isTop ne 'T'}">
					<td class="td_search" style="padding-left: 0px;padding-bottom: 0px;" colspan="3">
					</c:if>
					<a class="title"
						href='${pageContext.request.contextPath}/oa/oaInformation!view.do?no=${oaInformation.no}&flag=GGZY'
						style="text-decoration: none;"> <c:choose>
								<c:when test="${fn:length(oaInformation.title) gt 40 }">${fn:substring(oaInformation.title , 0, 40) }...</c:when>
								<c:otherwise>${oaInformation.title} </c:otherwise>
							</c:choose></td>
					<td style="text-align: right;width:100px;" >
						<c:if test="${(cp:CHECKUSEROPTPOWER('NEWS_GGZY', 'edit') and infoType eq 'news')
						or (cp:CHECKUSEROPTPOWER('TZGG_GGZY', 'edit') and infoType eq 'info' )
						or (cp:CHECKUSEROPTPOWER('BULLETIN_VIEW', 'edit') and infoType eq 'bulletin')
							}">
							<a class="bianji" href='oa/oaInformation!edit.do?no=${oaInformation.no}'><s:text name="opt.btn.edit" /></a>
				   		 </c:if>
				   		 <c:if test="${(cp:CHECKUSEROPTPOWER('NEWS_GGZY', 'delete') and infoType eq 'news')
						or (cp:CHECKUSEROPTPOWER('TZGG_GGZY', 'delete') and infoType eq 'info' )
						or (cp:CHECKUSEROPTPOWER('BULLETIN_VIEW', 'delete') and infoType eq 'bulletin')
							}">
							<a class="delete_email" href='oa/oaInformation!delete.do?no=${oaInformation.no}&infoType=${infoType}&flag=${flag}' 
								onclick='return confirm("确认删除该记录?");'><s:text name="opt.btn.delete" /></a>
						 </c:if>
					
					
					</td>
					
				</tr>
				<tr ${i.count % 1 ==
					0 ? ' style="border-bottom: 1px dashed #dedede;"' : ''} style="padding:0" class="odd" onmouseover="this.className='highligh'" onmouseout="this.className='odd'">
					<td></td>
					<c:if test="${oaInformation.isTop eq 'T'}">
					<td colspan="3" style="padding:0px;text-align: left;color: #BCBCBC">
					</c:if>
					<c:if test="${oaInformation.isTop ne 'T'}">
					<td colspan="3" style="padding:0px;text-align: left;color: #BCBCBC">
					</c:if>
					发布者：${cp:MAPVALUE('usercode',oaInformation.creater)}</td>
					<td align="right" style="padding:0px"><fmt:formatDate
							value="${oaInformation.releaseDate}" pattern="yyyy-MM-dd" /></td>
					</tr>
			</c:forEach>
		</table>

		<div>
			<c:set var="listURL" value="oaInformation!mainlist.do" />
			<c:set var="maxPageItems" value="15"></c:set>
			<c:if test="${fn:length(vobjList)>=1}">
				<%@ include file="/page/common/pagingBar.jsp"%>
			</c:if>

		</div>

	</div>
</body>
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
    var init;
	$(function() {
		//查询高亮显示 ----待完善
		var search = $('#search').val();
		$('.td_search').each(

				function(index, element) {
					$(element).text().replace(search,
							"<font color='red'>" + search + "</font>");

				}

		);
		$('#home_btnSearchSubmit')
		.click(
				function() {
					$form = $('#oaInformationForm');
					$form
							.attr('action',
									'${pageContext.request.contextPath}/oa/oaInformation!mainlist.do');
					$form.submit();
				});

		
				

	setTimeout(function(){
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		},200); 

	});
	
	
</script>

</html>
