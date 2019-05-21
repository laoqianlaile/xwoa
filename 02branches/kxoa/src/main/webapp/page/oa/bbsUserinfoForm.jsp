<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUserinfo.edit.title" /></title>
<style>
.vertical_ul {
	list-style: none;
	width: 150px;
}

.vertical_ul li {
	height: 40px;
	line-height: 40px;
	text-align: center;
	background: rgb(99, 150, 194);
	border: 1px solid #ccc;
	cursor: pointer;
}

.vertical_ul li.select {
	background: white;
	font-weight: bold;
}

.r-legend {
	margin-bottom: 0px;
}

#rightDiv table.active {
	display: block;
}

fieldset td {
	border: 1px solid rgb(219, 218, 218);
	padding: 5px;
	color: rgb(80, 80, 80);
}

fieldset table {
	table-layout: fixed;
}
</style>
</head>

<body>

   <fieldset class="_new" style="width: 97%;">
		<legend>
			个人头像
		</legend>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaUserinfo" method="post" namespace="/oa"  enctype="multipart/form-data"
		id="oaUserinfoForm">
		
		<input type="hidden" id="usercode" name="usercode"
			value="${object.usercode}" />
		<input type="hidden" name="s_usercode" value="${object.usercode }" />

			<!-- <div style="width: 20%; float: left; margin-top: 10px;">
				<ul class="vertical_ul">
					<li class="select">编辑头像</li>
					<li>基本资料</li>
					<li>联系方式</li>
					<li>教育情况</li>
					<li>个人信息</li>
				</ul>
			</div> -->
				<table id="table0" class="active">
				
					<tr>
						<td align="right" >头像</td>
						<td colspan="5"><c:choose>
								<c:when test="${not empty headpicturename }">
									<img id="appicon"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=0'
										style="width: 200px; height: 150px;" />
								</c:when>
								<c:otherwise>
									<img id="appicon"
										src='${pageContext.request.contextPath }/themes/blue/images/bbsheadpicturename.png'
										style="width: 200px; height: 150px;" align="left" />
								</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<td align="right">上传头像</td>
						<td colspan="5"><s:file name="uploadHeadPicture_" size="40"
								style="width:300px;height:25px" /></td>
					</tr>
					
					<tr>
						<td align="right">正式头像</td>
						<td colspan="5"><c:choose>
								<c:when test="${not empty picturename }">
									<img id="picture"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=1'
										style="width:200px; height:150px;"/>
								</c:when>
								<c:otherwise>
									<img id="picture"
										src='${pageContext.request.contextPath }/themes/blue/images/bbsheadpicturename.png'
										style="width:200px; height:150px;"/>
								</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<td align="right">上传正式头像</td>
						<td colspan="5"><s:file name="uploadFile_" size="40"
								style="width:86%;height:25px" />
								<select name="isusepicture">
								<option value="1" <c:if test="${'1' eq object.isusepicture }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isusepicture }">selected="selected"</c:if>>不公开</option>
						</select></td>
					</tr>
					
				
				
				</table>
	   <fieldset class="_new">
				<legend>
					基本资料
				</legend>
				<table id="table1">
					<tr>
						<td align="right" >用户昵称</td>
						<td colspan="5"><input type="text" name="username" value="${object.username }" style="width: 100%" /></td>
					</tr>
					<tr>
						<td align="right">性别</td>
						<td colspan="5"><select name="sex" style=" width: 300px;">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('sex')}" var="v">
								<option value="${v.value }" <c:if test="${v.value eq object.sex }">selected="selected"</c:if>>${v.label }</option>
							</c:forEach>
					</select>
					<span><select name="isshowsex">
								<option value="1" <c:if test="${'1' eq object.isshowsex }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowsex }">selected="selected"</c:if>>不公开</option>
						</select></td>
						
					</tr>

					<tr>
						<td align="right">出生年月</td>
						<td colspan="5"><input type="text" class="Wdate" name="birthday" 
					    style="height: 25px; width: 300px; border-radius: 3px; border: 1px solid #cccccc;"
					    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						placeholder="选择日期" value="<fmt:formatDate value='${object.birthday}'
							pattern='yyyy-MM-dd' />" /></td>
					</tr>

					<tr>
						<td align="right">出生地</td>
						<td colspan="5"><input type="text" name="placeofbirth" value="${object.placeofbirth}" style="width: 86%"/>
						    <select name="isshowplaceofbirth">
								<option value="1" <c:if test="${'1' eq object.isshowplaceofbirth }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowplaceofbirth }">selected="selected"</c:if>>不公开</option>
						</select></td>
					</tr>
					
					<tr>
						<td align="right">居住地</td>
						<td colspan="5"><input type="text" name="tolive" value="${object.tolive }" style="width: 86%"/>
						    <select name="isshowtolive">
								<option value="1" <c:if test="${'1' eq object.isshowtolive }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowtolive }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>

				</table>
			</fieldset>
			 <fieldset class="_new">
				<legend>
					联系方式
				</legend>
				<table id="table2">
					<tr>
						<td align="right">固定电话</td>
						<td colspan="5"><input type="text" name="telephone" value="${object.telephone }" style="width: 86%"/>
						    <select name="isshowtelephone">
								<option value="1" <c:if test="${'1' eq object.isshowtelephone }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowtelephone }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>
					
					<tr>
						<td align="right">手机</td>
						<td colspan="5"><input type="text" name="mobilephone" value="${object.mobilephone }" style="width: 86%"/>
						    <select name="isshowmobilephone">
								<option value="1" <c:if test="${'1' eq object.isshowmobilephone }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowmobilephone }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>
					
					<tr>
						<td align="right">QQ</td>
						<td colspan="5"><input type="text" name="qq" value="${object.qq }" style="width: 86%"/>
						    <select name="isshowqq">
								<option value="1" <c:if test="${'1' eq object.isshowqq }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowqq }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>
					
					<tr>
						<td align="right">MSN</td>
						<td colspan="5"><input type="text" name="msn" value="${object.msn }" style="width: 86%"/>
						    <select name="isshowmsn">
								<option value="1" <c:if test="${'1' eq object.isshowmsn }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowmsn }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>
					
					<tr>
						<td align="right">Email</td>
						<td colspan="5"><input type="text" name="email" value="${object.email }" style="width: 86%"/>
						    <select name="isshowemail">
								<option value="1" <c:if test="${'1' eq object.isshowemail }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowemail }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>

					<tr>
						<td align="right">邮寄地址</td>
						<td colspan="5"><input type="text" name="mailingaddress" value="${object.mailingaddress }" style="width: 100%"/></td>
					</tr>
					
					<tr>
						<td align="right">其它联系方式</td>
						<td colspan="5"><input type="text" name="otherlinks" value="${object.otherlinks }" style="width: 100%"/></td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="_new">
				<legend>
					教育情况
				</legend>
				<table id="table3">
					<tr>
						<td align="right" >学历</td>
						<td colspan="5"><input type="text" name="record" value="${object.record }" style="width: 86%"/>
						    <select name="isshowrecord">
								<option value="1" <c:if test="${'1' eq object.isshowrecord }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowrecord }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>
					
					<tr>
						<td align="right">毕业院校</td>
						<td colspan="5"><input type="text" name="school" value="${object.school }" style="width: 100%"/></td>
					</tr>
				</table>
			</fieldset>
			 <fieldset class="_new">
				<legend>
					个人信息
				</legend>
				<table id="table4">
					<tr>
						<td align="right" >证件类型</td>
					    <td colspan="5"><select name="documenttype" style="width: 100%">
					            <option value="">---请选择---</option>
								<c:forEach items="${cp:LVB('DocumentType')}" var="v">
									<option value="${v.value }"
										<c:if test="${v.value eq object.documenttype }">selected="selected" </c:if>>${v.label}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td align="right">证件号</td>
						<td colspan="5"><input type="text" name="idno" value="${object.idno }" style="width: 86%"/>
						    <select name="isshowidno">
								<option value="1" <c:if test="${'1' eq object.isshowidno }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowidno }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>

					<tr>
						<td align="right">个人签名</td>
						<td colspan="5"><textarea style="width: 100%" name="personalsignature">${object.personalsignature }</textarea>
						</td>
					</tr>

					<tr>
						<td align="right">自我介绍</td>
						<td colspan="5"><textarea style="width: 86%" name="introduce">${object.introduce }</textarea>
						    <select name="isshowintroduce">
								<option value="1" <c:if test="${'1' eq object.isshowintroduce }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowintroduce }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>

					<tr>
						<td align="right">兴趣爱好</td>
						<td colspan="5"><textarea style="width: 86%" name="hobbies">${object.hobbies }</textarea>
						    <select name="isshowhobbies">
								<option value="1" <c:if test="${'1' eq object.isshowhobbies }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowhobbies }">selected="selected"</c:if>>不公开</option>
						    </select></td>
					</tr>

				</table>
				</fieldset>

			<div class="formButton">
				<s:submit name="save" method="userinfoSave" cssClass="btn" key="opt.btn.save" />
				<input type="button" class="btn" value="返回" onclick="window.history.back();" />
			</div>

		</s:form>
	
	</fieldset>
</body>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>

<script type="text/javascript">
$(document).ready(
		function() {
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		});
	$(".vertical_ul li").each(function(index) {
		$(this).click(function() {
			$(".vertical_ul li").removeClass("select");
			$(this).addClass("select");
			$("#rightDiv table").removeClass("active");
			$("#table" + index).addClass("active");
		});
	});
</script>

</html>