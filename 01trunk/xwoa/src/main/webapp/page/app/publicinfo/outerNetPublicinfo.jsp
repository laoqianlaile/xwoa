<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

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
<div style="padding-left:10px;font-size:14px;color:red">友情提示：外网同步过来的文件只保留7天</div>
<div class="file-container" id="file-container">
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
			
			<c:if test="${cp:CHECKUSEROPTPOWER(param['mode'], 'edit') or cp:CHECKUSEROPTPOWER(param['mode'], 'editall')}">
				<a class="btn delete bst2">
					<em class="icon"></em>
					<b class="text">删除</b>
				</a>
				
				<a class="btn more bst2 bst3" href="javascript:;">
					<em class="icon"></em>
					<b class="text">重命名</b>
				</a>
			</c:if>
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
	$.publicinfo.init('${param["mode"]}',{
		public_url: '${ctx}/app/outerNetPublicinfo!dirPublicFolder.do',		// 查询公共文件夹
		personal_url: '${ctx}/app/outerNetPublicinfo!dirPersonalFolder.do',	// 查询个人文件夹
		view_url: '${ctx}/app/outerNetPublicinfo!view.do',						// 查看文件
		add_url: '${ctx}/app/outerNetPublicinfo!addFolder.do',					// 添加文件夹
		download_url: '${ctx}/app/outerNetPublicinfo!download.do',		// 下载文件（公共文件夹处理）
		toDownload_file_url: '${ctx}/app/outerNetFileinfoFs!toDownloadfile.do',	// 准备下载文件（文件处理）
		download_file_url: '${ctx}/app/outerNetFileinfoFs!downloadfile.do',		// 下载文件（文件处理）
		delete_url: '${ctx}/app/outerNetPublicinfo!delete.do',					// 删除文件
		rename_url: '${ctx}/app/outerNetPublicinfo!rename.do',					// 重命名文件
		copy_url: '${ctx}/app/outerNetPublicinfo!copy.do',						// 复制文件
		move_url: '${ctx}/app/outerNetPublicinfo!move.do',						// 移动文件
		upload_url:'${ctx}/app/outerNetPublicinfo!upload.do'
	});
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
		mode:$.publicinfo.MODE
	};
}

</script>

</html>