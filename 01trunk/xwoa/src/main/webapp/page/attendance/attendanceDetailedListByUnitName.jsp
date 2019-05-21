<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<link rel="stylesheet"
	href="${contextPath }/scripts/plugin/zTree/css/demo.css"
	type="text/css">
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>考勤信息，部门树</title>
<style type="text/css">
fieldset {
	padding: 0px;
	margin: 0px;
	height: 98% !important;
}

html,body {
	height: 100%;
}

table {
	height: 98%
}


a:hover {background-color:transparent;}


#infoTab { position:relative; height:26px; line-height:23px; border-bottom:1px solid #bbb; overflow:auto; padding-top:1px;}
#infoTab li { cursor:pointer; position:relative; float:left; padding:0 15px; border:1px solid #bbb; margin-right:6px; border-bottom:none; background:#afe7ff; }
#infoView { border:0px solid #bbb; border-top:none; padding:0px 30px 0px 10px; }
#infoTab .select { top:1px; font-weight:bold; cursor:default; }
#infoTab .current { border:1px solid #ff0000; border-bottom:none; color:#ff0000; }
#infoTab .disable { cursor:default; border:1px solid #ddd; border-bottom:none; color:#ddd; font-weight:bold;}
#infoView fieldset { display:none; }

</style>
</head>
<body>
	<table cellpadding="0" cellspacing="0" align="left"
		style="margin-top: 10px;">
		<tr>
			<td width="15%" style="position: relative; background: #f0f6e4;vertical-align:top;">
				<ul id="innermsg_tree" class="ztree"
					style="overflow-x: hidden; overflow-y: hidden; border: 1px solid #dddddd;; height: auto; position: absolute; width: 100%; margin: 0px; padding: 5px 0px">
				</ul>
			</td>
			<td>
				<div id="infoView">
		    	<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
					<iframe id="tabFrames1" name="tabFrames1"   onload="onLoadHeight(this);" width="100%"
						frameborder="0" scrolling="no" style="padding:0px 10px 0px 5px;" border="0" marginwidth="0"></iframe>
				</div> 
			</td>
		</tr>
	</table>

	<%-- 		<%@ include file="/page/common/charisma-js.jsp"%> --%>
	<%@ include file="/page/common/scripts.jsp"%>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/sys/selectUnitUser_iframe.js"></script>

	<script type="text/javascript">
		$(function() {
			//部门人员树
			var param = {
				'treeObj' : 'innermsg_tree',
				'usercode' : 'txt_innermsg_receive_usercode',
				'username' : 'txa_innermsg_receive_name',
				'btnAdd' : 'btn_innermsg_add_tree',
				'selectNodeId' : '${param["bodycode"]}',
				'close' : function() {
					$('#btn_close').click();
				},
				callback : {
					onClick : function(eventjs, treeId, treeNode) {
                            	 $("#tabFrames1").attr("src","<%=request.getContextPath()%>/attendance/attendanceDetailed!list.do?listType=0&unitName="+treeNode.name);
						}
					}
			};
			var innermsg = new UnitUser(param);
			innermsg.funs.init($.parseJSON('${unit }'));
			var treeObj =innermsg.funs.getTree();
			var nodes = treeObj.getNodes();
			if (nodes.length>0) {
				treeObj.selectNode(nodes[0]);
				$("#tabFrames1").attr("src",'<%=request.getContextPath()%>/attendance/attendanceDetailed!list.do?listType=0');
			}
		});
		function onLoadHeight(t){
			var _height=window.frames["tabFrames1"].document.body.scrollHeight+10;
			if(_height>460){t.height=_height;}
			else{t.height=460;}
		}
	</script>
</body>
</html>
