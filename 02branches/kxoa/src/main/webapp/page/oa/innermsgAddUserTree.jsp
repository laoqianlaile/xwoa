<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="/page/common/taglibs.jsp"%>
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


<script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${contextPath }/scripts/sys/selectUnitUser_iframe.js"></script>
<script type="text/javascript">
	$(function() {
		//frame为菜单项的optid
		var frame='external_RCBGWJQF';
		if("${s_msgtype }"=='P' ){
			frame='external_GRBGGRYJ';
		}else if("${s_msgtype }"=='A' ){
			frame='external_RCBGGGTZ';
		}
		
		var param = {
				'treeObj' : 'innermsg_tree',
				'usercode' : 'txt_innermsg_receive_usercode',
				'username' : 'txa_innermsg_receive_name',
				'btnAdd' : 'btn_roleusersave',
				'frame' : frame,
				'close' : function() {
					$('#btn_roleuserclose').click();
				}
		};
		
		var innermsg = new UnitUser(param);
		//console.info('${unit }');
		//debugger;
		innermsg.funs.initCheckbox($.parseJSON('${unit }'));
		
		/* $('#chk_msgtype_a').change(function(){
			if('checked' == $(this).attr('checked')) {
				$('#tr_receive').hide();
			} else {
				$('#tr_receive').show();
			}
		}); */
	});
</script>




