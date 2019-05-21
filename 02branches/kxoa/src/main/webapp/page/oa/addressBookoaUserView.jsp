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
	<%-- 			<li><a href="${pageContext.request.contextPath}/oa/addressbooks!oaView.do">通讯录信息维护</a> </li> --%>
	<!-- 		</ul> -->

	<table cellpadding="0" cellspacing="0" align="left"
		style="margin-top: 10px;">
		<tr>
			<td width="22%" style="position: relative; background: #f0f6e4;vertical-align:top;">
				<ul id="innermsg_tree" class="ztree"
					style="overflow-x: hidden; overflow-y: hidden; border: 1px solid #dddddd;; height: auto; position: absolute; width: 100%; margin: 0px; padding: 5px 0px">
				</ul>
			</td>
			<td>
				
						<!-- 						<a -->
						<%-- 							href="${pageContext.request.contextPath}/oa/addressbooks!oaEdit.do?addrbookid=${addrbookid}&bodycode=${bodycode}&type=${type}"> --%>
						<!-- 							编辑人员通讯录信息 </a> -->

						<form
					action="${pageContext.request.contextPath}/oa/addressbooks!save.do"
					method="post" id="addressbooksForm" data-validate="true">
					<input type="hidden" id="addrbookid" name="addrbookid"
						value="${addrbookid }" /> <input id="txt_innermsg_share_usercode"
						type="hidden" name="shareUserCode" value="${usercodes }" /> <input
						type="hidden" id="type" name="type" value="${type}" />
					<fieldset class="_new">
					
						<table border="0" cellpadding="0" cellspacing="0"
							class="viewTable">
							<legend> 机构通讯录基本信息 </legend>
<%-- 							<c:if test="${ not empty bodycode}"> --%>

								

								<tr>
									<td class="addTd">部门</td>
									<td align="left">${cp:MAPVALUE('unitcode',unitcode)}</td>
									<td class="addTd">固定电话</td>
									<td align="left">${addressbooks.telphone }</td>
								</tr>
								<tr>
									<td class="addTd">Email</td>
									<td align="left">${addressbooks.email }</td>
									<td class="addTd">其他联系方式</td>
									<td align="left">${addressbooks.otherphone }</td>
								</tr>
								<tr>
									<td class="addTd">备注</td>
									<td align="left" colspan="3">${addressbooks.remark }</td>
								</tr>
						</table>
<%-- 						</c:if> --%>
					</fieldset>


					<fieldset class="_new">
						<c:if test="${ not empty bodycode}">

							<legend> 人员通讯录基本信息 </legend>
							<table border="0" cellpadding="0" cellspacing="0"
								class="viewTable">
<!-- 								<tr> -->
<!-- 									<td class="addTd">部门</td> -->
<%-- 									<td align="left" colspan="3">${unitName }</td> --%>
<!-- 								</tr> -->
								<tr>
									<td class="addTd">姓名</td>
									<td align="left">${userName }</td>
									<td class="addTd">性别</td>
									<td align="left">${cp:MAPVALUE("sex",sex)}</td>
								</tr>
								<tr>
									<td class="addTd">单位</td>
									<td align="left">${unitName }</td>
									<td class="addTd">职位</td>
									<td align="left" colspan="3">${rankName }</td>
								</tr>
								<tr>
									<td class="addTd">手机</td>
									<td align="left">${mobilephone }</td>
									<td class="addTd">Email</td>
									<td align="left">${email }</td>
								</tr>
								<tr>
									<td class="addTd">固定电话</td>
									<td align="left">${telphone }</td>
									<td class="addTd">其他联系方式</td>
									<td align="left">${otherphone }</td>
								</tr>
								<tr>
									<td class="addTd">备注</td>
									<td align="left" colspan="3">${remark }</td>
								</tr>

							</table>
						</c:if>
						<!-- 							<div class="formButton"> -->
						<!-- 								<input type="button" name="back" Class="btn" -->
						<!-- 									onclick="history.back(-1);" value="返回" /> -->
						<!-- 							</div> -->
				</form>

				</fieldset>

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
