<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<link rel="stylesheet"
	href="${contextPath }/scripts/plugin/zTree/css/demo.css"
	type="text/css">
<link rel="stylesheet"
	href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<title>通讯录信息维护</title>
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

a:hover {
	background-color: transparent;
}
</style>
</head>
<body>

	<!-- 		<ul class="breadcrumb"> -->
	<!-- 			<li><a -->
	<%-- 				href="${pageContext.request.contextPath}/app/addressBook!oaView.do">通讯录信息维护</a> --%>
	<!-- 				<span class="divider">/</span></li> -->
	<!-- 		</ul> -->


	<table cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td width="22%" style="position: relative; background: #f0f6e4 ;vertical-align:top;">
				<ul id="innermsg_tree" class="ztree"
					style="overflow-x: hidden; overflow-y: hidden; border: 1px solid #dddddd; height: auto; position: absolute; width: 100%; margin: 0px; padding: 5px 0px">
				</ul>
			</td>
			<td>
				<div class="row-fluid">
					<div class="span9  ">
						<div class="box-content">
								<form
									action="${pageContext.request.contextPath}/oa/addressbooks!saveTree.do"
									method="post" id="addressbooksForm" data-validate="true">
									<input type="hidden" id="addrbookid" name="addrbookid"
										value="${addrbookid }" /> <input type="hidden" id="type"
										name="type" value="${object.type}" /> <input type="hidden"
										name="bodycode" value="${bodycode}" />
										 <input type="hidden"
										name="unitcode" value="${object.unitcode}" />

									<div class="tab-content">
										<fieldset class="_new">
											<c:if test="${ empty bodycode}">
												<legend>新增机构通讯录基本信息 </legend>
											</c:if>
											<c:if test="${ not empty bodycode}">
												<legend>
													编辑机构通讯录基本信息 
												</legend>
											</c:if>
<!-- 											<a -->
<%-- 												href="${pageContext.request.contextPath}/oa/addressbooks!oaView.do?addrbookid=${addrbookid}&bodycode=${bodycode}&bodytype=${bodytype}"> --%>

<!-- 												查看机构通讯录基本信息  </a> -->
											<table border="0" cellpadding="0" cellspacing="0"
												class="viewTable">
												<tr>
													<td class="addTd">固定电话</td>
													<td align="left"><input type="text" name="telphone"
														 style="width: 200px; height: 25px;"
														id="telphone" value="${telphone }" /></td>
													<td class="addTd">传真</td>
													<td align="left"><input type="text"
														style="width: 200px; height: 25px;" name="otherphone"
														id="otherphone" value="${otherphone }" /></td>
												</tr>
												<tr>
													<td class="addTd">Email</td>
													<td align="left" colspan="3"><input type="text"
														name="email" class="email" id="email"
														style="width: 200px; height: 25px;" value="${email }" /></td>

												</tr>
												<tr>
													<td class="addTd">备注</td>
													<td align="left" colspan="3"><textarea id="remark"
															style="width: 200px; height: 25px;" name="remark"
															rows="5" cols="50">${remark }</textarea></td>
												</tr>
											</table>

											<div class="formButton">
												<input type="button" name="back" Class="btn"
													onclick="history.back(-1);" value="返回" />
												<button type="submit" class="btn btn-primary">保存</button>
											</div>
								</form>
			</td>
		</tr>
	</table>
	<%-- 	<%@ include file="/page/common/charisma-js.jsp"%> --%>
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
						window.location = '${pageContext.request.contextPath}/oa/addressbooks!oaView.do?bodycode='
								+ treeNode.id + '&type=' + treeNode.p;
					}
				}
			};
			var innermsg = new UnitUser(param);
			innermsg.funs.init($.parseJSON('${unit }'));
		});

		
	</script>
</body>
</html>
