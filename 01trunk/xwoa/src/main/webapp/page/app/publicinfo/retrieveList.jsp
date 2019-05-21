<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%> 

<title>LIST NEW</title>

<style type="text/css">
#list div { line-height:24px;  }
#title1 {font-size:16px;}
#list h2 { padding:5px 0; }
#list li { list-style:none; padding:15px 10px; border-bottom:1px dashed #ccc; line-height:24px; }
#list li:hover { background:#fffddd; }
#list a,span { padding-right:8px; font-size:12px; }
#list h2 a { font-size:16px; color:blue; }
</style>
</head>
<body>

	<div class="container">
	
		<ul class="breadcrumb">
		  <%-- <li><a href="${pageContext.request.contextPath }/app/publicinfo!view.do?infocode=${fn:substring(obj.dataID,fn:length(obj.optID),fn:length(obj.dataID)) }&type=" type="${obj.extension}">文件列表</a> <span class="divider">/</span></li> --%>
		  <li class="active">检索列表</li>
		</ul>

		<div class="row searchArea box">
			<div class="span12">
				<form  action="${pageContext.request.contextPath }/app/searcher!retrieve.do" method="post" id=pagerForm>
					<fieldset>
                        <input type="hidden" name="orderField" value="${param['orderField'] }"/>
						<input type="hidden" name="orderDirection" value="${param['orderDirection'] }"/>
						<input type="hidden" name="mode" value="${mode }"/>
						
						<div class="row searchBar">
							<div class="span2 title">输入检索内容：</div>
							<div class="span2">
								<input type="text" name="keywords" value="${keywords }" class="span2" />
							</div>
							<div class="span2 offset6">
								<a class="btn btn-primary" target="submit" form="#pagerForm"
									href="javascript:;"> <i class="icon-search icon-white"></i>
									查询
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="span12">

     <h1 id="title1">共检索到${pageDesc.totalRows }条记录</h1>
     
     <ul id="list">
     <c:forEach var="obj" items="${results }">
     <li>
     <input type="hidden" value="${obj.extension }" id="fileTypeTemp" />
     <h2><a class="cls_a_retrieve_list" target="download" infocode="${fn:substring(obj.dataID,fn:length(obj.optID),fn:length(obj.dataID)) }" href="${pageContext.request.contextPath }/app/publicinfo!view.do?infocode=${fn:substring(obj.dataID,fn:length(obj.optID),fn:length(obj.dataID)) }&type=" type="${obj.extension}" rel="viewDoc">${obj.title }</a></h2>
     <div class="content">${obj.summary}</div>
     </li>
     </c:forEach>
     
     </ul>
		<%-- <table class="list" width="100%">
			<c:forEach var="obj" items="${results }">
				<tr>
					<td><a
						href="${pageContext.request.contextPath }/app/publicinfo!view.do?filecode=${fn:replace(fn:replace(obj.filecode, '[', ''), ']', '') }&type=${fn:replace(fn:replace(obj.ext, '[', ''), ']', '') }"
					    target="navTab" rel="viewDoc">${fn:replace(fn:replace(obj.title, '[', ''), ']', '') }</a></td>
					<td>${fn:replace(fn:replace(obj.summary, '[', ''), ']', '') }</td>
				</tr>
			</c:forEach>
		</table> --%>

	</div>
</div>

<%-- <%@ include file="searchPanelBar.jsp"%> --%>
<%--     <c:set var="listURL" value="searcher!retrieve.do" ></c:set> --%>
<%--     <c:set var="maxPageItems" value="10" ></c:set> --%>
<%-- 		<%@ include file="/page/common/pagingBar.jsp" %>			 --%>
<!-- 	</div> -->
	<div class="background" id="background" style="display:none;"></div>
	<div class="progressBar" id="progressBar" style="display:none;"></div>
<%@ include file="/page/common/charisma-js.jsp"%>
</body>

<script src="${pageContext.request.contextPath}/scripts/sys/ui/centit.publicinfo.js"></script>

<script type="text/javascript">

$(function(){
	var FILE_TYPES = {
			'bmp':'img',
			'gif':'img',
			'jpeg':'img',
			'jpg':'img',
			'png':'img',
			'vsd':'visio',
			'pdf':'pdf',
			'doc':'word',
			'docx':'word',
			'xls':'excel',
			'xlsx':'excel',
			'txt':'txt',
			'wav':'music',
			'mp3':'music',
			'mov':'movie',
			'rm':'movie',
			'rmvb':'movie',
			'avi':'movie',
			'mkv':'movie',
			'ppt':'ppt',
			'app':'apple',
			'exe':'exe',
			'zip':'zip',
			'rar':'zip',
			'7z':'zip',
			'apk':'apk'
	};
	
	$('.cls_a_retrieve_list').each(function(index, a){
		$(a).attr('href', $(a).attr('href') + FILE_TYPES[$(a).attr('type')]);
	});
	
	$('#list a').click(function(event) {
		var infocode = $(this).attr('infocode');
		$.post($.publicinfo.FILE_OPTION.download_url, {infocode: infocode, mode: '${mode}'}, function(data) {
			$.publicinfo.downloadFileCallback(data, true);
		}, 'json');
		event.preventDefault();
		
	});
});

</script>
</html>