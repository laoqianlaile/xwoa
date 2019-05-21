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
	<div class="tableright" style="left: 0px;position:static;width: auto;">
		<section class="public-position" id="publicPosition" >
 			<div class="pubpos-nav">
				当前位置：
				<c:if test="${empty infoType and  empty notread }">&nbsp;
			<a title="全部" class="CurrChnlCls"
						href="${pageContext.request.contextPath}/oa/oaHelpinfo!list4Manager.do">帮助中心信息</a>
				</c:if>
			</div>
			
			<div class="pubpos-search-box" style="float: right;">

				<%@ include file="/page/common/messages.jsp"%>
				<s:form action="oaHelpinfo" method="post" namespace="/oa"
					id="oaInformationForm">
					<input type="hidden" id="infoType" name="infoType"
						value="${infoType}" />
					<input type="hidden" id="notread" name="notread"
						value="${notread}" />
					<input type="hidden" id="flag" name="flag" value="GGZY" />
					<s:hidden name="show_type" value="%{show_type}"></s:hidden>
					<c:if test="${isadmin=='99999999'}">
						<s:submit method="modify"  key="opt.btn.new" cssClass="whiteBtnWide" readonly="" cssStyle="width:74px;height:24px;border:none;float:left;"/>
					</c:if>
					<input name="s_infoName" class="search-txt" id="txt_searchWord_nav" type="text" size="100" style="float:left;margin-left:5px" placeholder="请输入关键词">
				    <!-- <input name="s_optid" type="hidden"/> -->
					<input name="submit" class="submit-botton" id="home_btnSearchSubmit" type="submit" value=" ">
				</s:form>
			</div>
		</section>
		<table class="maintable" border="0" cellpadding="0" cellspacing="0" style="margin-left: 0px;margin-right: 0px;table-layout: fixed;width:100%">
			<c:if test="${empty objList }">
				<tr><td style="text-align: right;">未找到记录</td></tr>
			</c:if>
			<c:forEach items="${objList }" var="oaHelpinfo" varStatus='i'>
				<tr style="width: auto;">
					<td class="td_search" style="padding-left: 0px;padding-bottom: 0px;" colspan="3">
					<a class="title" href='oa/oaHelpinfo!view.do?djid=${oaHelpinfo.djid}&backcolumnType=mgr&ec_p=${ec_p}&ec_crd=${ec_crd}' style="text-decoration: none"> 
							<c:if test="${oaHelpinfo.isgood =='1' && oaHelpinfo.state ne 'F'}">
								<span style="color: red">【精华】</span>
							</c:if>
							<c:if test="${oaHelpinfo.isgood =='0' && oaHelpinfo.state ne 'F'}">
								【普通】
							</c:if>
							<c:choose>
								<c:when test="${fn:length(oaHelpinfo.infoName) gt 40 }">${fn:substring(oaInformation.infoName , 0, 40) }...</c:when>
								<c:otherwise>${oaHelpinfo.infoName} </c:otherwise>
							</c:choose></td>
					<td style="text-align: right;width:300px;" >
						<a class="xiangqing" href='oa/oaHelpinfo!view.do?djid=${oaHelpinfo.djid}&backcolumnType=mgr&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
						<c:if test="${isadmin=='99999999'}">
							<a class="bianji" href='oa/oaHelpinfo!modify.do?djid=${oaHelpinfo.djid }'>编辑</a>
							<a class="delete_email" href='oa/oaHelpinfo!delete.do?djid=${oaHelpinfo.djid}' onclick='return confirm("${deletecofirm}确定删除?");'><s:text name="opt.btn.delete" /></a>
							<c:if test="${oaHelpinfo.isgood eq '0' && oaHelpinfo.state ne 'F'  }">
								<a class="shouchang" href='oa/oaHelpinfo!setGood.do?djid=${oaHelpinfo.djid}' onclick='return confirm("确定将此置为精华帖?");'>置为精华帖</a>
							</c:if>
							<c:if  test="${oaHelpinfo.isgood eq '1' && oaHelpinfo.state ne 'F' }">
								<a class="shouchang" href='oa/oaHelpinfo!setNormal.do?djid=${oaHelpinfo.djid}' onclick='return confirm("确定取消精华帖?");'>取消精华帖</a>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>

		<div>
			<c:set var="listURL" value="oa/oaHelpinfo!list4Manager.do" />
			<c:set var="maxPageItems" value="15"></c:set>
			<c:if test="${fn:length(objList)>=1}">
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
									'${pageContext.request.contextPath}/oa/oaHelpinfo!list4Manager.do');
					$form.submit();
				});

		
				

	setTimeout(function(){
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		},200); 

	});
	
	
</script>

</html>
