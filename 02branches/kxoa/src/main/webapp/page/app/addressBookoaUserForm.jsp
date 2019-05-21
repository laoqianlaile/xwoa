<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

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
<!-- 			</li> -->
<!-- 		</ul> -->

			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
			<td width="22%" style="position: relative;background:#f0f6e4">
						<ul id="innermsg_tree" class="ztree" style="overflow-y:hidden;border:1px solid #dddddd;;height:auto;position: absolute;width:100%;margin:0px;paddind:5px 0px" >
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
										<input type="hidden" name="orderField"
											value="${param['orderField'] }" /> <input type="hidden"
											name="orderDirection" value="${param['orderDirection'] }" />
										<input type="hidden" name="addrbookid" value="${addrbookid }" />
										<input type="hidden" name="bodycode" value="${bodycode }" />
										<input type="hidden" name="bodytype" value="${bodytype }" />

										<div class="tab-content">
											<fieldset>
												<c:if test="${ empty bodycode}">
													<legend>新增通讯录基本信息</legend>
												</c:if>
												<c:if test="${ not empty bodycode}">
													<legend>
														编辑人员通讯录基本信息
														<%-- 												<c:if test="${cp:CHECKUSEROPTPOWER('humaninfo', 'view')  or session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode== usercode  }"> --%>
														<a
															href="${pageContext.request.contextPath}/app/addressBook!oaView.do?addrbookid=${addrbookid}&bodycode=${bodycode}&bodytype=${bodytype}">
															<span style="font-size: 15px; color: green;">
																查看人员信息</span>
														</a>
														<%-- 												</c:if> --%>
													</legend>
												</c:if>
												<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
													<c:if test="${ 1 eq bodytype}">
														<tr>
															<td>通讯主体编号：</td>
															<td colspan="3">
																<%-- 													<input type="hidden" name="bodycode" value="${bodycode}"/> --%>
																<!-- 													<input type="text" style="width: 145px;" class="users required"  placeholder="输入用户名、拼音或登录名" autocomplete="off"  -->
																<%-- 														usercodeRef="bodycode" value="${cp:MAPVALUE('usercode',bodycode)}"/> --%>
																${cp:MAPVALUE('usercode',bodycode)}
															</td>
														</tr>
														<tr>
															<td>职务：</td>
															<td>${cp:MAPVALUE("RankType",rankname)} <!-- 													<select name="rankname" class="combox" style="width: 145px;"> -->
																<%-- 														<c:forEach var="row" items="${cp:LVB('RankType')}"> --%>
																<%-- 															<option value="${row.value}" <c:if test="${row.value==rankname}">selected="selected"</c:if>> --%>
																<%-- 																<c:out value="${row.label}" /> --%>
																<!-- 															</option> --> <%-- 														</c:forEach> --%>
																<!-- 													</select> -->
															</td>
															<td>通讯主体类别：</td>
															<td><c:if test="${bodytype=='1' }">用户</c:if> <c:if
																	test="${bodytype=='2' }">单位</c:if> <%-- 													<select name="bodytype" id="bodytype" data-value="${bodytype }" style="width: 145px;"> --%>
																<!-- 														<option  value="1" selected="selected"> -->
																<!-- 								                                                                           用户 -->
																<!-- 								                        </option> --> <!-- 								                        <option  value="2" > -->
																<!-- 								                                                                            单位 -->
																<!-- 								                        </option> --> <!-- 													</select> -->
															</td>
														</tr>
														<tr>
															<td>单位：</td>
															<td>${cp:MAPVALUE("StationType",unitname)} <!-- 													<select name="unitname" class="combox" style="width: 145px;"> -->
																<%-- 														<c:forEach var="row" items="${cp:LVB('StationType')}"> --%>
																<%-- 															<option value="${row.value}" <c:if test="${row.value==unitname}">selected="selected"</c:if>> --%>
																<%-- 																<c:out value="${row.label}" /> --%>
																<!-- 															</option> --> <%-- 														</c:forEach> --%>
																<!-- 													</select> -->
															</td>
															<td>部门：</td>
															<td>${cp:MAPVALUE("unitcode",deptname)} <!-- 													<select id="deptname" name="deptname" class="combox" style="width: 145px;" class=" focused required"> -->
																<%-- 														<c:forEach var="row" items="${cp:LVB('unitcode')}"> --%>
																<%-- 															<option value="${row.value}" <c:if test="${row.value==deptname}">selected="selected"</c:if>> --%>
																<%-- 																<c:out value="${row.label}" /> --%>
																<!-- 															</option> --> <%-- 														</c:forEach> --%>
																<!-- 													</select> -->
															</td>
														</tr>
													</c:if>
													<%-- 										<c:if test="${ 2 eq bodytype}"> --%>
													<!-- 											<tr> -->
													<!-- 												<td >通讯主体编号：</td> -->
													<!-- 												<td colspan="3"> -->
													<%-- 													${cp:MAPVALUE('unitcode',bodycode)} --%>
													<!-- 												</td> -->
													<!-- 											</tr> -->
													<!-- 											<tr> -->
													<!-- 												<td >通讯主体类别：</td> -->
													<!-- 												<td colspan="3"> -->
													<%-- 													<c:if test="${bodytype=='1' }">用户</c:if> <c:if test="${bodytype=='2' }">单位</c:if> --%>
													<!-- 												</td> -->
													<!-- 											</tr> -->
													<%-- 										</c:if> --%>
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
												<table border="0" cellpadding="0" cellspacing="0" class="viewTable"
													id="addressBookTb">
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
														<td colspan="3"><input type="text"
															class="{email:true}" name="email3" id="email3"
															value="${email3}" style="width: 145px;" /></td>
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
													<tr>
														<td>网页地址：</td>
														<td><input type="text" class="{url:true}"
															name="homepage" id="homepage" value="${homepage}"
															style="width: 145px;" /></td>
														<td>旺旺：</td>
														<td><input type="text" name="wangwang" id="wangwang"
															value="${wangwang}" style="width: 145px;" /></td>
													</tr>

												</table>
												<div>
													<font style="color: blue; font-size: 20px;">电话号码: </font>
												</div>
												<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
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
														<td>住宅电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="homephone" id="homephone" value="${homephone}"
															style="width: 145px;" /></td>
														<td>单位电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="unitphone" id="unitphone" value="${unitphone}"
															style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>住宅电话2：</td>
														<td><input type="text" class="{telephone:true}"
															name="homephone2" id="homephone2" value="${homephone2}"
															style="width: 145px;" /></td>
														<td>助理电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="assiphone" id="assiphone" value="${assiphone}"
															style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>住宅电话3：</td>
														<td><input type="text" class="{telephone:true}"
															name="homephone3" id="homephone3" value="${homephone3}"
															style="width: 145px;" /></td>
														<td>回电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="callbacphone" id="callbacphone"
															value="${callbacphone}" style="width: 145px;" /></td>
													</tr>
													<tr>
														<td>住宅传真：</td>
														<td><input type="text" class="{telephone:true}"
															class="{number:true,maxlength:11}" name="homefax"
															id="homefax" value="${homefax}" style="width: 145px;" />
														</td>
														<td>车载电话：</td>
														<td><input type="text" class="{telephone:true}"
															name="carphone" id="carphone" value="${carphone}"
															style="width: 145px;" /></td>
													</tr>

												</table>
												<div>
													<font style="color: blue; font-size: 20px;">地址: </font>
												</div>
												<table border="0" cellpadding="0" cellspacing="0" class="viewTable" id="infoTable">
													<tr>
														<td>常用通讯地址：</td>
														<td colspan="3"><select id="inuseaddress"
															name="inuseaddress" data-value="${inuseaddress }">
																<option id="option_inuseaddress1" value="1"
																	selected="selected">商务</option>
																<option id="option_inuseaddress2" value="2">住宅</option>
																<option id="option_inuseaddress3" value="3">其他</option>
														</select></td>
													</tr>
													<tr>
														<td id="td_unitzip">单位邮编：</td>
														<td colspan="3"><input type="text"
															class="{number:true,maxlength:8}" name="unitzip"
															id="unitzip" value="${unitzip}" style="width: 500px;" />
														</td>
													</tr>

													<tr>
														<td>单位省：</td>
														<td colspan="3"><input type="text"
															name="unitprovince" id="unitprovince"
															value="${unitprovince}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>单位市：</td>
														<td colspan="3"><input type="text" name="unitcity"
															id="unitcity" value="${unitcity}" style="width: 500px;" />
														</td>
													</tr>
													<tr>
														<td>单位区：</td>
														<td colspan="3"><input type="text"
															name="unitdistrict" id="unitdistrict"
															value="${unitdistrict}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>单位街道：</td>
														<td colspan="3"><input type="text" name="unitstreet"
															id="unitstreet" value="${unitstreet}"
															style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>单位地址：</td>
														<td colspan="3"><input type="text" name="unitaddress"
															id="unitaddress" value="${unitaddress}"
															style="width: 500px;" /></td>
													</tr>
													<tr>
														<td id="td_homezip">住宅邮编：</td>
														<td colspan="3"><input type="text"
															class="{number:true,maxlength:8}" name="homezip"
															id="homezip" value="${homezip}" style="width: 500px;" />
														</td>
													</tr>
													<tr>
														<td>住宅省：</td>
														<td colspan="3"><input type="text"
															name="homeprovince" id="homeprovince"
															value="${homeprovince}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅市：</td>
														<td colspan="3"><input type="text" name="homecity"
															id="homecity" value="${homecity}" style="width: 500px;" />
														</td>
													</tr>
													<tr>
														<td>住宅区：</td>
														<td colspan="3"><input type="text"
															name="homedistrict" id="homedistrict"
															value="${homedistrict}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅街道：</td>
														<td colspan="3"><input type="text" name="homestreet"
															id="homestreet" value="${homestreet}"
															style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅地址：</td>
														<td colspan="3"><input type="text" name="homeaddress"
															id="homeaddress" value="${homeaddress}"
															style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅邮编2：</td>
														<td colspan="3"><input type="text"
															class="{number:true,maxlength:8}" name="home2zip"
															id="home2zip" value="${home2zip}" style="width: 500px;" />
														</td>
													</tr>
													<tr>
														<td>住宅省2：</td>
														<td colspan="3"><input type="text"
															name="home2province" id="home2province"
															value="${home2province}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅市2：</td>
														<td colspan="3"><input type="text" name="home2city"
															id="home2city" value="${home2city}" style="width: 500px;" />
														</td>
													</tr>
													<tr>
														<td>住宅区2：</td>
														<td colspan="3"><input type="text"
															name="home2district" id="home2district"
															value="${home2district}" style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅街道2：</td>
														<td colspan="3"><input type="text" name="home2street"
															id="home2street" value="${home2street}"
															style="width: 500px;" /></td>
													</tr>
													<tr>
														<td>住宅地址2：</td>
														<td colspan="3"><input type="text"
															name="home2address" id="home2address"
															value="${home2address}" style="width: 500px;" /></td>
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
// 			//动态添加表格
// 			$.editTable.editTable();
// 			$.dynamicTable.dynamicTable();

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
			}else{
				var trs = $('#infoTable tr:gt(0)').hide();
				if ('1' == selectValue) {
					trs.filter(':lt(6)').show();
				} else if ('2' == selectValue) {
					trs.filter(':gt(5):lt(6)').show();
				} else if ('3' == selectValue) {
					trs.filter(':gt(11):lt(6)').show();

			}
			}
		});

		$('#inuseaddress').change(function() {
			changeAddress(this);
		});
		function changeAddress(obj){
			var value = $(obj).val();
			var trs = $('#infoTable tr:gt(0)').hide();

			if ('1' == value) {
				trs.filter(':lt(6)').show();
			} else if ('2' == value) {
				trs.filter(':gt(5):lt(6)').show();
			} else if ('3' == value) {
				trs.filter(':gt(11):lt(6)').show();
			}
			
		}
	</script>
</body>
</html>
