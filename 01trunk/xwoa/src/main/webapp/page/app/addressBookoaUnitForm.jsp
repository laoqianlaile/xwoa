<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<%@ include file="/page/common/css.jsp"%>
<html>
<head >
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<title>通讯录信息维护</title>
<style type="text/css">
fieldset{padding:0px;margin:0px;height:98% !important;}
html,body{height:100%;}
table{height:98%}
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
			<td width="22%" style="position: relative;background:#f0f6e4">
						<ul id="innermsg_tree" class="ztree" style="overflow-x:hidden;overflow-y:hidden;border:1px solid #dddddd;height:auto;position: absolute;width:100%;margin:0px;padding:5px 0px" >
						</ul>
			</td>
					<td>
						<div class="row-fluid">

							<div class="span9  ">
								<!-- 					<div class="row-fluid toolsBar"> -->
								<!-- 						<div class="span12"> -->
								<%-- 							<a class="btn btn-success" href="${pageContext.request.contextPath}/app/addressBook!oaEdit.do"> --%>
								<!-- 								<i class="icon-plus icon-white"></i> 新增通讯录信息 -->
								<!-- 							</a> -->
								<!-- 						</div> -->
								<!-- 					</div> -->

								<div class="box-content">
									<form method="post"
										action="${pageContext.request.contextPath}/app/addressBook!oaSave.do"
										class="form-horizontal" id="addressBookForm" validate="true">
										
										<input type="hidden" name="addrbookid" value="${addrbookid }" />
										<input type="hidden" name="bodycode" value="${bodycode }" />
										<input type="hidden" name="bodytype" value="${bodytype }" />

										<div class="tab-content">
											<fieldset>
												<c:if test="${ empty bodycode}">
													<legend>新增单位通讯录基本信息</legend>
												</c:if>
												<c:if test="${ not empty bodycode}">
													<legend>
														编辑单位通讯录基本信息
														<%-- 												<c:if test="${cp:CHECKUSEROPTPOWER('humaninfo', 'view')  or session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode== usercode  }"> --%>
														<a
															href="${pageContext.request.contextPath}/app/addressBook!oaView.do?addrbookid=${addrbookid}&bodycode=${bodycode}&bodytype=${bodytype}">
															<span style="font-size: 15px; color: green;">
																查看单位信息</span>
														</a>
														<%-- 												</c:if> --%>
													</legend>
												</c:if>
												<table border="0" cellpadding="0" cellspacing="0"
													class="viewTable">
													<tr>
														<td>通讯主体编号：</td>
														<td colspan="3">${cp:MAPVALUE('unitcode',bodycode)}</td>
													</tr>
													<tr>
														<td>通讯主体类别：</td>
														<td colspan="3"><c:if test="${bodytype=='1' }">用户</c:if>
															<c:if test="${bodytype=='2' }">单位</c:if></td>
													</tr>
													<tr>
														<td>描述(表示为)：</td>
														<td colspan="3"><textarea rows="2" cols="50"
																name="representation" value="${representation}"
																style="width: 500px;">${representation}</textarea></td>
													</tr>
												</table>
												<div>
													<font style="color: blue; font-size: 20px;">Internet:</font>
												</div>
												<table border="0" cellpadding="0" cellspacing="0"
													class="viewTable" id="addressBookTb">
													<tr>
														<td>电子邮件1:</td>
														<td><input type="text" class="{email:true}"
															name="email" id="email" value="${email}"
															style="width: 145px;" /></td>
														<td>电子邮件2:</td>
														<td><input type="text" class="{email:true}"
															name="email2" id="email2" value="${email2}"
															style="width: 145px;" /></td>
													</tr>

													<tr>
														<td>电子邮件3:</td>
														<td><input type="text" class="{email:true}"
															name="email3" id="email3" value="${email3}"
															style="width: 145px;" /></td>
														<td>网页地址：</td>
														<td><input type="text" class="{url:true}"
															name="homepage" id="homepage" value="${homepage}"
															style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>QQ：</td>
														<td><input type="text" class="{number:true}"
															name="qq" id="qq" value="${qq}" style="width: 145px;" />
														</td>
														<td>MSN：</td>
														<td><input type="text" name="msn" id="msn"
															value="${msn}" style="width: 145px;" /></td>

													</tr>

												</table>
												<div>
													<font style="color: blue; font-size: 20px;">电话号码: </font>
												</div>
												<table border="0" cellpadding="0" cellspacing="0"
													class="viewTable">
													<tr>
														<td>商务电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="buzphone" id="buzphone" value="${buzphone}"
															style="width: 145px;" /></td>
														<td>移动电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="mobilephone" id="mobilephone"
															value="${mobilephone}" style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>商务电话2：</td>
														<td><input type="text" class="{telephone:true}"
															name="buzphone2" id="buzphone2" value="${buzphone2}"
															style="width: 145px;" /></td>
														<td>移动电话2：</td>
														<td><input type="text" class="{telephone:true}"
															name="mobilephone2" id="mobilephone2"
															value="${mobilephone2}" style="width: 145px;" /></td>
													</tr>


													<tr>
														<td>商务传真：</td>
														<td><input type="text" class="{telephone:true}"
															name="buzfax" id="buzfax" value="${buzfax}"
															style="width: 145px;" /></td>
														<td>无线电话3：</td>
														<td><input type="text" class="{telephone:true}"
															name="mobilephone3" id="mobilephone3"
															value="${mobilephone3}" style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>单位电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="unitphone" id="unitphone" value="${unitphone}"
															style="width: 145px;" /></td>
														<td>助理电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="assiphone" id="assiphone" value="${assiphone}"
															style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>回电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="callbacphone" id="callbacphone"
															value="${callbacphone}" style="width: 145px;" /></td>
														<td>车载电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="carphone" id="carphone" value="${carphone}"
															style="width: 145px;" /></td>
													</tr>

												</table>
											</fieldset>


											<div class=" center">
												<button type="submit" class="btn btn-primary" id="saveBtn">全部保存</button>
												<!-- 									<a class="btn" onclick="javascript:history.go(-1);">返回</a> -->
											</div>

										</div>

									</form>
					</td>
				</tr>
			</table>
<%-- 	<%@ include file="/page/common/charisma-js.jsp"%> --%>
        <%@ include file="/page/common/scripts.jsp"%> 
		 <script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	     <script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
         <script type="text/javascript" src="${contextPath }/scripts/sys/selectUnitUser_iframe.js"></script>
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
						//alert(treeNode);
						// 							if ('false' == treeNode.p) {
						window.location = '${pageContext.request.contextPath}/app/addressBook!oaView.do?bodycode='
								+ treeNode.id + '&bodytype=' + treeNode.p;
						// 							}
					}
				}
			};

			var innermsg = new UnitUser(param);

			innermsg.funs.init($.parseJSON('${unit }'));

			var selectValue = '${inuseaddress }';
			if (!selectValue) {
				var trs = $('#infoTable tr:gt(6)').hide();
			}

		});

// 		$(document).ready(function() {
// 			$.editTable.editTable();
// 			$.dynamicTable.dynamicTable();
// 			$.copyable.copyall();
// 		});
		$('#inuseaddress').change(function() {
			var value = $(this).val();
			var trs = $('#infoTable tr:gt(0)').hide();

			if ('1' == value) {
				trs.filter(':lt(6)').show();
			} else if ('2' == value) {
				trs.filter(':gt(5):lt(6)').show();
			} else if ('3' == value) {
				trs.filter(':gt(11):lt(6)').show();
			}
		});
	</script>
</body>
</html>
