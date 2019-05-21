<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<%@ include file="/page/common/css.jsp"%>
<html>
<head >

<title>通讯录信息维护</title>
<style type="text/css">
fieldset{padding:0px;margin:0px;height:98% !important;}
html,body{height:100%;}
table{height:98%}
</style>
</head>
<body>
	
<!-- 		<ul class="breadcrumb"> -->
<%-- 			<li><a href="${pageContext.request.contextPath}/app/addressBook!oaView.do">通讯录信息维护</a> </li> --%>
<!-- 		</ul> -->

	    <table cellpadding="0" cellspacing="0" align="left" style="margin-top:10px;">
			<tr>
			<td width="22%" style="position: relative;background:#f0f6e4">
						<ul id="innermsg_tree" class="ztree" style="overflow-x:hidden;overflow-y:hidden;border:1px solid #dddddd;;height:auto;position: absolute;width:100%;margin:0px;padding:5px 0px" >
						</ul>
			</td>
			<td>

						<form method="post" action="${pageContext.request.contextPath}/app/addressBook!save.do" class="form-horizontal" id="addressBookForm" validate="true">
							<input type="hidden" name="usercode" value="${usercode }" />
							 
							<div class="tab-content">
									<fieldset>

										<c:if test="${ not empty bodycode}">
										
										<legend>
										人员通讯录基本信息
<%-- 											<c:if test="${cp:CHECKUSEROPTPOWER('humaninfo', 'edit') or session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode== usercode }"> --%>
												<c:if test="${cp:CHECKUSEROPTPOWER('addressBook','edit')}">
												<a  href="${pageContext.request.contextPath}/app/addressBook!oaEdit.do?addrbookid=${addrbookid}&bodycode=${bodycode}&bodytype=${bodytype}">  
													<span style="font-size: 15px; color: green;">编辑人员通讯录信息</span>
												</a>
												</c:if>
<%-- 											</c:if> --%>
										 </legend>
										<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
										<c:if test="${ 1 eq bodytype}">
											<tr>
<!-- 												<td class="span3">通讯主体ID：<font style="color: red;">*</font></td> -->
<%-- 												<td class="span3">${addrbookid}</td> --%>
												<td>通讯主体编号：</td>
												<td colspan="3">${cp:MAPVALUE('usercode',bodycode)}
												</td>
											</tr>

											<tr>
												<td>职务：</td>
												<td>${cp:MAPVALUE("RankType",rankname)}</td>
												<td>通讯主体类别：</td>
												<td>
													<c:if test="${bodytype=='1' }">用户</c:if> <c:if test="${bodytype=='2' }">单位</c:if>
												</td>
												
											</tr>
											<tr>
												<td>单位：</td>
												<td>
													${cp:MAPVALUE("StationType",unitname)}
												</td>
												<td>部门：</td>
												<td>
													${cp:MAPVALUE("unitcode",deptname)}
												</td>
											</tr>
										</c:if>
<%-- 										<c:if test="${ 2 eq bodytype}"> --%>
<!-- 											<tr> -->
<!-- 												<td class="span3">通讯主体编号：</td> -->
<%-- 												<td colspan="3">${cp:MAPVALUE('unitcode',bodycode)} --%>
<!-- 												</td> -->
<!-- 											</tr> -->

<!-- 											<tr> -->
<!-- 												<td class="span3">通讯主体类别：</td> -->
<!-- 												<td colspan="3"> -->
<%-- 													<c:if test="${bodytype=='1' }">用户</c:if> <c:if test="${bodytype=='2' }">单位</c:if> --%>
<!-- 												</td> -->
<!-- 											</tr> -->
<%-- 										</c:if> --%>
											<tr>
												<td>描述(表示为)：</td>
												<td colspan="3">${representation}</td>
											</tr>
										</table>
										<div ><font style="color: blue; font-size: 20px;">Internet:</font></div>
									<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
											<tr>
												<td>电子邮件1: </td>
												<td >${email}</td>
												<td >电子邮件2: </td>
												<td >${email2}</td>
											</tr>

											<tr>
												<td>网页地址：</td>
												<td>${homepage}</td>
												<td>电子邮件3: </td>
												<td>${email3}</td>
											</tr>
											<tr>
												<td>QQ：</td>
												<td>${qq}</td>
												<td>MSN：</td>
												<td>${msn}</td>
												
											</tr>
											<tr>
												<td>旺旺：</td>
												<td colspan="3">${wangwang}</td>
											</tr>
										</table>
										<div ><font style="color: blue; font-size: 20px;">电话号码: </font> </div>
									<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
											<tr>
												<td>商务电话：</td>
												<td>${buzphone}</td>
												<td>移动电话：</td>
												<td>${mobilephone}</td>
											</tr>
											<tr>
												<td>商务电话2:</td>
												<td>${buzphone2}</td>
												<td>移动电话2：</td>
												<td>${mobilephone2}</td>
											</tr>
											

											<tr>
												<td>商务传真：</td>
												<td>${buzfax}</td>
												<td>无线电话3：</td>
												<td>${mobilephone3}</td>
											</tr>
											<tr>
												<td>住宅电话：</td>
												<td>${homephone}</td>
												<td>单位电话：</td>
												<td>${unitphone}</td>
											</tr>
											<tr>
												<td>住宅电话2：</td>
												<td>${homephone2}</td>
												<td>助理电话：</td>
												<td>${assiphone}</td>
											</tr>
											<tr>
												<td>住宅电话3：</td>
												<td>${homephone3}</td>
												<td>回电话：</td>
												<td>${callbacphone}</td>
											</tr>
											<tr>
												<td>住宅传真：</td>
												<td>${homefax}</td>
												<td>车载电话：</td>
												<td>${carphone}</td>
											</tr>
											
										</table>
										<div ><font style="color: blue; font-size: 20px;">地址: </font> </div>
									<table border="0" cellpadding="0" cellspacing="0" class="viewTable" id="infoTable">
											
					               			<tr>
												<td>常用通讯地址：</td>
												<td colspan="3">
													<select  id="inuseaddress" name="inuseaddress">
								                        <option id="option_inuseaddress1" value="1" <c:if test="${inuseaddress=='1' }"> selected="selected"</c:if> >
								                                                                            商务
								                        </option>
								                        <option id="option_inuseaddress2" value="2"  <c:if test="${inuseaddress=='2' }"> selected="selected"</c:if>>
								                                                                            住宅
								                        </option>
								                        <option id="option_inuseaddress3" value="3" <c:if test="${inuseaddress=='3' }"> selected="selected"</c:if> >
								                                                                            其他
								                        </option>
							               			</select>
												</td>
											</tr>
											<tr>
												<td id="td_unitzip">单位邮编：</td>
												<td colspan="3">${unitzip}</td>
											</tr>

											<tr>
												<td>单位省：</td>
												<td colspan="3">${unitprovince}</td>
											</tr>
											<tr>
												<td>单位市：</td>
												<td colspan="3">${unitcity}</td>
											</tr>
											<tr>
												<td>单位区：</td>
												<td colspan="3">${unitdistrict}</td>
											</tr>
											<tr>
												<td>单位街道：</td>
												<td colspan="3">${unitstreet}</td>
											</tr>
											<tr>
												<td>单位地址：</td>
												<td colspan="3">${unitaddress}</td>
											</tr>
											<tr>
												<td id="td_homezip">住宅邮编：</td>
												<td colspan="3">${homezip}</td>
											</tr>

											<tr>
												<td>住宅省：</td>
												<td colspan="3">${homeprovince}</td>
											</tr>
											<tr>
												<td>住宅市：</td>
												<td colspan="3">${homecity}</td>
											</tr>
											<tr>
												<td>住宅区：</td>
												<td colspan="3">${homedistrict}</td>
											</tr>
											<tr>
												<td>住宅街道：</td>
												<td colspan="3">${homestreet}</td>
											</tr>
											<tr>
												<td>住宅地址：</td>
												<td colspan="3">${homeaddress}</td>
											</tr>
											<tr>
												<td>住宅邮编2：</td>
												<td colspan="3" >${home2zip}</td>
											</tr>

											<tr>
												<td>住宅省2：</td>
												<td colspan="3">${home2province}</td>
											</tr>
											<tr>
												<td>住宅市2：</td>
												<td colspan="3">${home2city}</td>
											</tr>
											<tr>
												<td>住宅区2：</td>
												<td colspan="3">${home2district}</td>
											</tr>
											<tr>
												<td>住宅街道2：</td>
												<td colspan="3">${home2street}</td>
											</tr>
											<tr>
												<td>住宅地址2：</td>
												<td colspan="3">${home2address}</td>
											</tr>
									</table>
									</c:if>
								</fieldset>
							</div>
						</form>

					</td>
				</tr>
      </table>	
	
<%-- 		<%@ include file="/page/common/charisma-js.jsp"%> --%>
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
								window.location = '${pageContext.request.contextPath}/app/addressBook!oaView.do?bodycode='
										+ treeNode.id + '&bodytype=' + treeNode.p;
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
