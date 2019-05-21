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
<title><s:text name="oaBbsDashboard.list.title" /></title>
	<style type="text/css">
	    a {text-decoration: none;}
	   .fileList{overflow:hidden;margin-left:0px;}
	   .fileList li{float:left;margin-right:10px; list-style:none; cursor:default}
	    .docFile{background:url('${ctx}/scripts/kindeditor-4.1.7/themes/default/default.png') no-repeat 0 -95px;display:inline-block;height:17px;font-size:12px;padding-left:18px;}
	</style>
</head>
<body id="body_">
	<div class="tableright" style="left: 0px;position:static;width:98%">

		<section class="public-position" id="publicPosition">
			<div class="pubpos-nav">
				您当前的位置：<c:if test="${not empty s_infoType}">&nbsp;
			<a title="${cp:MAPVALUE("optTypeName",s_infoType) }" class="CurrChnlCls" style="text-decoration: none;"
						href="${pageContext.request.contextPath}/oa/oaFilemanager!list.do?s_infoType=${s_infoType}">${cp:MAPVALUE("optTypeName",s_infoType) }</a>
				</c:if>
		
			</div>
			

			<div class="pubpos-search-box" style="float: right;">

				<%@ include file="/page/common/messages.jsp"%>
				<s:form action="oaFilemanager" method="post" namespace="/oa"  
					id="oaFilemanagerForm">
					<input type="hidden" id="s_infoType" name="s_infoType"
						value="${s_infoType}" />
						
					<input type="hidden" id="s_state" name="s_state"  value="T" />
						
					<input name="s_searchword" class="search-txt"
						id="txt_searchWord_nav" type="text" size="100"
						placeholder="请输入关键词">

					<input name="submit" class="submit-botton"
						id="home_btnSearchSubmit" type="submit" value="搜 索">
				</s:form>
			</div>
			<c:if test="${(cp:CHECKUSEROPTPOWER('FILEMANAGEFW', 'edit') and s_infoType eq 'FW')
						or (cp:CHECKUSEROPTPOWER('FILEMANAGESW', 'edit') and s_infoType eq 'SW' )}">
			<div style="float: right;margin-top: 4px;margin-right: 20px;">
					<s:form action="oaFilemanager" method="post" namespace="/oa"
					id="oaFilemanager_add" >
					<input type="hidden" id="s_infoType" name="s_infoType"
						value="${s_infoType}" />
						
					<s:submit method="built"  key="opt.btn.new"  readonly=""
					cssClass="whiteBtnWide"
					cssStyle="width:74px;height:24px;"/>
					
					</s:form>
			
			</div>
			</c:if>
		</section>
		<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 0px;margin-right: 0px;">
			<c:if test="${empty objList }">
				<tr><td style="text-align: right;">未找到记录</td></tr>
			</c:if>
			<c:forEach items="${objList }" var="oaFilemanager" varStatus='i'>
				<tr >
					<!-- <td style="width: 58px;padding-left: 2px;padding-right: 0px;padding-bottom: 0px;">
					
					</td> -->
					<td  colspan="3"><a
						href='${pageContext.request.contextPath}/oa/oaFilemanager!view.do?no=${oaFilemanager.no}'
						style="text-decoration: none;"> <c:choose>
								<c:when test="${fn:length(oaFilemanager.title) gt 40 }">${fn:substring(oaFilemanager.title , 0, 40) }...</c:when>
								<c:otherwise>${oaFilemanager.title} </c:otherwise>
							</c:choose></td>
					<td style="text-align: right;">
					    <c:if test="${(cp:CHECKUSEROPTPOWER('FILEMANAGEFW', 'edit') and s_infoType eq 'FW')
						or (cp:CHECKUSEROPTPOWER('FILEMANAGESW', 'edit') and s_infoType eq 'SW' )}">
							<a class="bianji" style='text-decoration: none;'  href='oa/oaFilemanager!edit.do?no=${oaFilemanager.no}&s_infoType=${s_infoType}'>  <s:text name="opt.btn.edit" /></a>
				   		 </c:if>
				   		 	<c:if test="${(cp:CHECKUSEROPTPOWER('FILEMANAGEFW', 'delete') and s_infoType eq 'FW')
						or (cp:CHECKUSEROPTPOWER('FILEMANAGESW', 'delete') and s_infoType eq 'SW' )}">
							<a class="delete_email" style='text-decoration: none;'  href='oa/oaFilemanager!delete.do?no=${oaFilemanager.no}&s_infoType=${s_infoType}' 
								onclick='return confirm("确认删除该记录?");'><s:text name="opt.btn.delete" /></a>
						 </c:if>
					</td>
					
				</tr>
				<tr ${i.count % 1 ==0 ? ' style="border-bottom: 1px dashed #dedede;"' : ''} >
				<td colspan="3">

				 <span style="width: 100%; position: relative;"><font
						color="blue"><strong>附件下载:</strong></font></span> 
				      <c:forEach var="fileItem" items="${oaFilemanager.docAttachments}">
				        <a href="#" class="docFile" onclick="downFile('${fileItem.id}')">
							    ${fileItem.fileName} </a>&nbsp;&nbsp;
							 </c:forEach>
				  </td>
				  <td align="right" style="padding:0px 5px ; color: #BCBCBC"  width="220px">
				  发布者：${cp:MAPVALUE('usercode',oaFilemanager.creater)}
				  <fmt:formatDate
							value="${oaFilemanager.releaseDate}" pattern="yyyy-MM-dd" /></td>
				</tr>
				<%-- <tr ${i.count % 1 ==
					0 ? ' style="border-bottom: 1px dashed #dedede;"' : ''} style="padding:0" class="odd" onmouseover="this.className='highligh'" onmouseout="this.className='odd'">
					<td></td><td style="padding:0px;text-align: left;color: #BCBCBC">发布者：${cp:MAPVALUE('usercode',oaFilemanager.creater)}</td>
					<td></td><td align="right" style="padding:0px"><fmt:formatDate
							value="${oaFilemanager.releaseDate}" pattern="yyyy-MM-dd" /></td>
					</tr> --%>
			</c:forEach>
		</table>

		<div>
			<c:set var="listURL" value="oaFilemanager!list.do" />
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
					$form = $('#oaFilemanagerForm');
					$form
							.attr('action',
									'${pageContext.request.contextPath}/oa/oaFilemanager!list.do');
					$form.submit();
				});

		
				

	setTimeout(function(){
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		},200); 

	});
	
	function downFile(id) {
		var url = "oaFilemanager!downLocalStuffInfo.do?no=" + id;
		document.location.href = url;
	}
	
</script>

</html>
