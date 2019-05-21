<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<div style="height:310px; overflow:auto;" class="ztree" id="ztree"> </div>
	<input type="hidden" value="${param['type'] }" />
</div>

<script type="text/javascript">
	var settings = {
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			showLine:false,
			selectedMulti:false
		},
		async: {
			enable: true,
			url: Centit.contextPath+"/app/publicinfo!selectFolder.do?mode="+$.publicinfo.MODE,
			autoParam: ["id=infocode"]
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
	
	$.publicinfo.ZTREE_FOLDER = $.fn.zTree.init($("#ztree"), settings, []);
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		$.publicinfo.ZTREE_FOLDER.expandNode($.publicinfo.ZTREE_FOLDER.getNodes()[0], true, false, false);
	};
</script>