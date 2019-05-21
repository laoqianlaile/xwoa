<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<link href="${pageContext.request.contextPath}/themes/css/core.css"	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li a {
padding:1px 3px 0 0; margin:0; cursor:pointer; 
height:18px;  
line-height:18px;  
color:#333;
background-color: transparent;
text-decoration:none; 
vertical-align:top;
 display: inline-block
 }
</style>

<html>
<head>
<title>部门文档</title>
<script	src="${pageContext.request.contextPath}/scripts/centitui/alertMsg.js" type="text/javascript"></script>
</head>
<body>
<div id="menuContent">


		<table cellpadding="0" cellspacing="0" align="left"
			style="margin-top: 10px;">
			<tr>
				<td width="15%" style="position: relative; background: #f0f6e4; vertical-align: top; text-align: left;">
					<ul id="innermsg_tree" class="ztree"
						style="overflow-x: hidden; overflow-y: hidden; border: 1px solid #dddddd;; height: auto; position: absolute; width: 100%; margin: 0px; padding: 5px 0px">
					</ul>
				</td>
				<td>


					<div id="infoView">
						<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
						<iframe id="tabFrames1" name="tabFrames1"
							width="100%" frameborder="0"  onload="onLoadHeight(this);"
							scrolling="no" style="padding: 0px 10px 0px 5px;" border="0"
							marginwidth="0"></iframe>
					</div>

				</td>
			</tr>
		</table>

		
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"
		src="${contextPath }/scripts/sys/selectUnitUser_iframe.js"></script>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>
	<script type="text/javascript">
		// 			require(['jquery'], function($) {

		$(function() {
			CentitUI.init("${pageContext.request.contextPath}/page/frame/centitui.frag.xml");
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
                             if('1'==treeNode.id){
                            	 $("#tabFrames1").attr("src",'<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=UNITSHAREFILE&s_queryUnderUnit=true&rootunitcode='+ treeNode.id+'&random='+Math.random());
                             }else{
                            	 $("#tabFrames1").attr("src",'<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=UNITSHAREFILE&rootunitcode='+ treeNode.id+'&random='+Math.random());
                             }
					}
					}
			

			};
			var innermsg = new UnitUser(param);

			innermsg.funs.init($.parseJSON('${unit }'));
			
			//debugger;
			var treeObj =innermsg.funs.getTree();
			var nodes = treeObj.getNodes();
			if (nodes.length>0) {
				treeObj.selectNode(nodes[0]);
				 $("#tabFrames1").attr("src",'<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=UNITSHAREFILE&s_queryUnderUnit=true&rootunitcode='+ nodes[0].id);
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
