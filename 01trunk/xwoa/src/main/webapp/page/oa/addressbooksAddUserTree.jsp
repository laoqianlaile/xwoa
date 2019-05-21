<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="/page/common/taglibs.jsp"%>

<script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${contextPath }/scripts/sys/selectUnitUser_iframe.js"></script>
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>

<div class="pageContent">
	<div class="pageFormContent" layoutH="58">

		<div class="unit zTreeDemoBackground">
			<ul id="innermsg_tree" class="ztree"></ul>
		</div>
	</div>


	<div class="formBar">
		<ul>
			<li>
				<div class="buttonActive">
					<div class="buttonContent">
						<button id="btn_roleusersave" type="button">保存</button>
					</div>
				</div>
			</li>

			<li>
				<div class="buttonActive">
					<div class="buttonContent">
						<button id="btn_roleuserclose" type="button" class="close">取消</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		var selectNodeId = "${selectedUserCode }";
		
		var param = {
				'treeObj' : 'innermsg_tree',
				'usercode' : 'txt_innermsg_share_usercode',
				'username' : 'txa_innermsg_share_name',
				'btnAdd' : 'btn_roleusersave',
				'frame' : 'external_GRBGGRTXL',
				'close' : function() {
					$('#btn_roleuserclose').click();
				},
				'selectNodeId': selectNodeId
		};
		
		var innermsg = new UnitUser(param);
		
		innermsg.funs.initCheckbox($.parseJSON('${unit }'));
		
		
	});
</script>




