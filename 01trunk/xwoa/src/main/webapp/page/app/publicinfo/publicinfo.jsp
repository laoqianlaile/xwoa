<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />

<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<link href="${pageContext.request.contextPath}/scripts/frame/ui/upload/upload.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/frame/ui/publicinfo/styles/icon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/frame/ui/publicinfo/styles/publicinfo.css" rel="stylesheet" type="text/css" />


<title>
	<c:if test="${param['mode'] eq 'PUBLICFILE' }">公共文件夹</c:if>
	<c:if test="${param['mode'] eq 'PERSONFILE' }">个人文件夹</c:if>
</title>

</head>

<body data-controller="PublicinfoController" data-deps='["jquery", "Publicinfo"]'>
<div class="file-container" id="file-container">
	<div class="line navigation publicinfo-upload-add  " style="display:none">
	    
		    <%-- 新增权限 --%>
              
			<input type="file" id="publicinfo-upload" data-opt-id="UPLOADFI" data-fn-dynamic-form-data="uploadFormData"
				data-fn-upload-callback="uploadCallback" data-uploader="${pageContext.request.contextPath}/app/publicinfo!upload.do;jsessionid=${pageContext.session.id}"/>
			<a class="btnBig add bst2" style="padding:0px;width:109px;text-align: center;" >
				<b class="text" style="font:Microsoft YaHei!important; font-size: 14px;" >新建文件夹</b>
			</a>
		
		<%-- <div id="search-text" class="input-append" style="float:right; margin-right:200px; ">
		<form action="${pageContext.request.contextPath }/app/searcher!searchFiles.do" method="post" id=pagerForm>
			<input type="text" name="keywords" id="searchInput" placeHolder="输入标题、内容进行搜索" keydown="autoSubmit();"/>
			<span class="add-on" style="cursor:pointer;" title="搜索"><i class="icon-search"></i></span> 
		</form>
		</div> --%>
	</div>
	
	<div class="line path">
		<div >
			<a style="float:left; display:block; display:none; margin-right:2px;" type="dir">
				<span class="icon32 icon-home" style="vertical-align:middle;" title="返回首页"></span>
			</a>
			<a style="float:left; display:block; display:none;" type="dir">
				<span class="icon32 icon-undo" style="vertical-align:middle;" title="返回上一级"></span>
			</a>
		</div>
		<div class="info ed">
		</div>
		<div class="info th">
			<span class="status ready" style="display:none;">已全部加载，共&nbsp;<i></i>&nbsp;个</span>
			<span class="status refresh">正在加载中...</span>
		</div>
	</div>

	<div class="line operation" style="display:none;">
		<div class="info operation">
			<span class="check icon"></span>
			已选中&nbsp;<i></i>&nbsp;个文件/文件夹
			
			<a class="btn download bst2" href="javascript:;">
				<dfn class="icon"></dfn>
				<b class="text">下载</b>
			</a>
			
			 
				<a class="btn delete bst2 publicinfo-upload-delect">
					<em class="icon"></em>
					<b class="text">删除</b>
				</a>
				
				<a class="btn more bst2 bst3 publicinfo-upload-modify" href="javascript:;">
					<em class="icon"></em>
					<b class="text">重命名</b>
				</a>
			
		</div>
		
		<%-- <ul class="pull-down-menu header-menu" style="display:none;">
			<li>
				<a href="javascript:;" class="rename">重命名</a>
			</li>
			<li>
				<a href="javascript:;" class="copyto" data-toggle="modal" data-target="#file_window"  
					data-remote="${pageContext.request.contextPath}/page/app/publicinfo/selectFolder.jsp?type=copy">复制到</a>
			</li>
			<li>
				<a href="javascript:;" class="moveto" data-toggle="modal" data-target="#file_window" 
					data-remote="${pageContext.request.contextPath}/page/app/publicinfo/selectFolder.jsp?type=move">移动到</a>
			</li>
		</ul> --%>
	</div>
	
	<div class="header line">
		<div class="name info">
			<span class="check icon"></span>
			文件名
			<span class="sort icon"></span>
		</div>
		<div class="size info">大小<span class="sort icon"></span></div>
		
		<div class="owner info">
			上传者
			<span class="sort icon"></span>
		</div>
		<div class="time info">上传时间<span class="sort icon"></span></div>
	</div>
	
	<span id="rename-container" style="display:none;">
		<input type="text" name="filename" value="新建文件夹" class="newfolder" />
		<a title="确定" class="btn sure bst4" href="javascript:;">确定</a>
		<a title="取消" class="btn cancel bst4" href="javascript:;">取消</a>
	</span>
	
	<div id="public-window" class="public-window" layoutH="130">
	</div> 
	
	<div class="personal-window" style="display:none;"></div>
	<input type="hidden" name="path" value="${path }" id="path" />
	<input type="hidden" name="rootunitcode" value="${rootunitcode }" id="rootunitcode" />
</div>

</body>

<%@ include file="/page/common/scripts.jsp" %>

<script type="text/javascript">


function autoSubmit() {
	
	if(window.event.keyCode==13)
	{
	   $('#pagerForm').submit();
	}
}

function PublicinfoController($) {

	$.publicinfo.init('${param["mode"]}',null,'${param["rootunitcode"]}');

} 

function uploadCallback(data) {
	if (data.result != '0') {
		Msg.error(data.description);
		return false;
	};
	
	$.publicinfo.addFile(data.file);
}

function uploadFormData() {
	return {
		path:$.publicinfo.FILE_OPTION.infocode,
		mode:$.publicinfo.MODE,
		rootunitcode:$.publicinfo.UNITCODE
	};
}

</script>

</html>