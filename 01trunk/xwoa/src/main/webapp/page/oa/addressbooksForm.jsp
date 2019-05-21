<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaSchedule.edit.title" /></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>通讯录</title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
				<s:text name="addressbooks.edit.title" />
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/addressbooks!save.do"
			method="post" id="addressbooksForm" data-validate="true">
			<input type="hidden" id="addrbookid" name="addrbookid"
				value="${addrbookid }" /> <input type="hidden" id="type"
				name="type" value="${object.type}" />
				<input type="hidden" id="unitcode"
				name="unitcode" value="${object.unitcode}" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<c:if test="${object.type ne  'O' }">
				<tr>
					<c:if test="${object.type ne  'O' }">
						<td class="addTd">姓名</td>
						<td align="left">
						<input type="text"
							name="userName" id="userName"  style="width:200px;height:25px;" class="focused required" value="${userName }" /> <span style="color: red">*</span></td>
					</c:if>
				    <td class="addTd">性别</td>
						<td align="left"><select id="sex"  style="width:200px;height:25px;"
							name="sex">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('sex')}">
									<option value="${row.datacode}"
										<c:if test="${row.datacode==sex}"> selected="selected"</c:if>>
										<c:out value="${row.datavalue}" />
									</option>
								</c:forEach>
						</select></td>
						<%-- <td id="td_isshare" >
						<!-- 个人通讯录是否共享 -->
						<c:if test="${object.type eq 'P'}">
						<input type="checkbox" id="isshare" 
							name="isshare" value="1"
							<c:if test="${isshare==1 }"> checked="checked" </c:if>>
							是否共享
						</c:if>
					<!-- 公共通讯录是否同步到机关人员下面 -->
					<c:if test="${object.type eq 'C'}">
						<input type="checkbox" 
							name="isshare" value="1"
							<c:if test="${isshare==1 }"> checked="checked" </c:if>>
							同步到机关通讯录
					</c:if>
							</td> --%>
				</tr>
				</c:if>
				<c:if test="${object.type eq 'P'}">
					<%-- <tr id="tr_share">
						<td class="addTd">共享人</td>
						<td align="left" colspan="3">
						<textarea  readonly="readonly"
								id="txa_innermsg_share_name" name="shareNames" rows="3"
								cols="50"> ${shareNames } </textarea> <input
							id="txt_innermsg_share_usercode" type="hidden"
							name="shareUserCode" value="${usercodes }" />
							</td>
					</tr> --%>
				</c:if>
				<c:if test="${object.type eq 'P'}">
<!-- 					<tr> -->
						<%-- 	<td class="addTd">类别</td>
					<td align="left"><select data-rel="chosen" id="type"
						name="type" class="combox">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('addBookType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==type}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td> --%>

						<%-- <td class="addTd">分组</td>
						<td align="left" colspan="3"><select style="width:200px;height:25px;"
							id="belond" name="belond">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('addBookbelond')}">
									<option value="${row.datacode}"
										<c:if test="${row.datacode==belond}"> selected="selected"</c:if>>
										<c:out value="${row.datavalue}" />
									</option>
								</c:forEach>
						</select></td> --%>
<!-- 					</tr> -->
				</c:if>
				<!-- 				个人 -->
			<%-- 	<c:if test="${object.type ne 'O'}">
					<tr class='tr_addBookType_P'>

						
						

					</tr>
				</c:if> --%>


				<c:if test="${object.type eq 'C' or object.type eq 'P'}">
<%--                     <c:if test="${object.type eq 'P'}"> --%>
					<!-- 				公司 -->
					<tr class='tr_addBookType_C'>
						<td class="addTd">单位</td>
						<td align="left" ><input type="text" name="unitName"
							 style="width:200px;height:25px;" id="unitName" value="${unitName }" /></td>
						<td class="addTd">手机</td>
						<td align="left"><input type="text" name="mobilephone"  class="focused isphone"
							 style="width:200px;height:25px;" id="mobilephone" value="${mobilephone }" /></td>
						
					</tr>
				</c:if>
                   <%--  <c:if test="${object.type eq 'P'}">
					<!-- 				公司 -->
					<tr class='tr_addBookType_C'>
						<td class="addTd">单位</td>
						<td align="left"><input type="text" name="unitName"
							 style="width:200px;height:25px;"  value="${unitName }" /></td>
						<td class="addTd">行业</td>
						<td align="left"><input type="text" name="profession"
							 style="width:200px;height:25px;" value="${profession }" /></td>

					</tr>
				</c:if> --%>
				<!-- 				机关单位 -->
				<tr class='tr_addBookType_O'>
				<!-- 公共通讯录部门下拉框选择 -->
				<%-- <c:if test="${object.type eq  'C' }">
					<td class="addTd">部门</td>
					<td align="left"><select style="width:200px;height:25px"
						name="deptName">
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==depno}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
				 </c:if> --%>
				 	<!-- 个人通讯录部门手动填写 -->
			<%-- 	 <c:if test="${object.type ne  'O' }">
					<td class="addTd">部门</td>
					<td align="left"><input type="text" name="deptName"
						 style="width:200px;height:25px;" value="${deptName }" /></td>
				 </c:if> --%>
				 	<!-- 人员信息填写职位-->
                <%--  <c:if test="${object.type ne  'O' }">
					<td class="addTd">职位</td>
					<td align="left"><input type="text" name="rankName"
						 style="width:200px;height:25px;" id="rankName" value="${rankName }" /></td>
                 </c:if>
                 <c:if test="${object.type eq 'O' }">
                  <td colspan="2"></td>
                  </c:if> --%>
				</tr>

				<tr>

					<td class="addTd">固定电话</td>
					<td align="left"><input type="text" name="telphone" class="number"
						 style="width:200px;height:25px;" id="telphone" value="${telphone }" /></td>
						<td class="addTd">Email</td>
					<td align="left" ><input type="text" name="email" class="email"
						id="email"  style="width:200px;height:25px;" value="${email }" /></td>
					
				</tr>
				<tr>
				<td class="addTd">其他联系方式</td>
					<td align="left"><input type="text"  style="width:200px;height:25px;"
						name="otherphone" id="otherphone" value="${otherphone }" /></td>
				
						<td class="addTd">备注</td>
					<td align="left" >
					  <input type="text" name="remark" 
						id="remark"  style="width:200px;height:25px;" value="${remark }" />
					  </td>

				</tr>
<!-- 				<tr> -->
<!-- 					<td class="addTd">备注</td> -->
<!-- 					<td align="left" colspan="3"><textarea id="remark" -->
<%-- 							 style="width:200px;height:25px;" name="remark" rows="5" cols="50">${remark }</textarea></td> --%>
<!-- 				</tr> -->
				<!-- 				暂时打开，zkp别删 -->
				<%--   <tr >
					<td class="addTd">共享人</td>
					<td align="left" colspan="3"><textarea
							id="txa_innermsg_share_name" name="shareNames" rows="3" cols="50"
							style="width: 70%;"> ${shareNames } </textarea> <input
						id="txt_innermsg_share_usercode" type="hidden"
						name="shareUserCode" value="${usercodes }" />
						
						 <!--  <a id="a_href" type="button" class="btn"
							href="#" onclick="alertTree()"
							width="400" height=""  >添加xin人员</a> -->
							
							</td>
				</tr> --%>
			</table>

			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						已选人员
						<ul></ul>
					</div>
					<div id="t-b-arrow">

						<b class="btns"> <input id="save" class="btn" type="button"
							value="确     定" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</form>
	</fieldset>

</body>

<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	$(function() {

		$('#a_href').attr('height', window.screen.availHeight - 200);
		//是否共享
		function isShare() {
			if ('checked' == $('#isshare').attr("checked")) {
				$('#tr_share').show();
			} else {
				$('#tr_share').hide();
			}
		}

		$('#isshare').live("click", function() {
			isShare();
		});

		isShare();

		//通讯录类别

		function bodyType() {

			var v = $('#type').find('option:selected').val();

			if (v == '') {
				$('.tr_addBookType_P').hide();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_C').hide();

			}

			if (v == 'P') {
				$('.tr_addBookType_P').show();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_C').hide();

			}
			if (v == 'C') {
				$('.tr_addBookType_C').show();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_P').hide();

			}
			if (v == 'O') {
				$('.tr_addBookType_O').show();
				$('.tr_addBookType_P').hide();
				$('.tr_addBookType_C').hide();

			}
		}

		$('#type').change(function() {
			bodyType();
		});

		bodyType();

	});

	$("#txa_innermsg_share_name").click(function() {
		var d = '${userjson}';
		if (d.trim().length) {
			alertTree();
		}
		;
	});

	function alertTree() {
		var selectNodeId = '${userjson}';
		new treePerson(jQuery.parseJSON(selectNodeId),
				$("#txa_innermsg_share_name"),
				$("#txt_innermsg_share_usercode")).init();
		setAlertStyle("attAlert");
	}
</script>
</html>