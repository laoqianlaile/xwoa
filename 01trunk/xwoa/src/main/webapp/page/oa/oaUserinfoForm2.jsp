<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/page/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<style type="text/css">
   td{border:1px solid #ccc;height:30px;font-size:12px;text-align: center}
table.pop-table td {
padding: 0;
line-hight: 30px;
}
table.pop-table td input, 
table.pop-table td select, 
table.pop-table td textarea {
	border: none;
	min-height: 30px;
	padding: 0;
	    line-height: 30px;
}
</style>
</head>
<body>
  	<form action="${pageContext.request.contextPath }/oa/oaUserinfo!userinfoSave2.do" method="post"  enctype="multipart/form-data"
		id="oaUserinfoForm">
		
		<input type="hidden" id="usercode" name="usercode"
			value="${object.usercode}" />
				<table class="pop-table" cellspacing='0' cellpadding='0' style="width:98%;margin-left:1%; border-collapse:collapse;">
				   	<tr>
						<td align="right" style="width:85px;">用户名</td>
						<td>${cp:MAPVALUE("usercode",object.usercode)}</td>
						
					</tr>
					<tr>
						<td align="right" >头像</td>
						<td><c:choose>
								<c:when test="${not empty headpicturename }">
									<img id="appicon"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=0'
										style="width: 200px; height: 150px;" align="left" />
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
						<td><s:file name="uploadHeadPicture_" size="40"
								style="width:100%;height:25px" /></td>
					</tr>
					
					<tr>
						<td align="right">正式头像</td>
						<td><c:choose>
								<c:when test="${not empty picturename }">
									<img id="picture"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=1'
										style="width:200px; height:150px;" align="left" />
								</c:when>
								<c:otherwise>
									<img id="picture"
										src='${pageContext.request.contextPath }/themes/blue/images/bbsheadpicturename.png'
										style="width:200px; height:150px;" align="left" />
								</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<td align="right">上传正式头像</td>
						<td><s:file name="uploadFile_" size="40"
								style="width:86%;height:25px" />
								<select name="isusepicture">
								<option value="1" <c:if test="${'1' eq object.isusepicture }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isusepicture }">selected="selected"</c:if>>不公开</option>
						</select></td>
					</tr>
					<tr>
						<td align="right">性别</td>
						<td><select name="sex" style=" width: 100%;">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('sex')}" var="v">
								<option value="${v.value }" <c:if test="${v.value eq object.sex }">selected="selected"</c:if>>${v.label }</option>
							</c:forEach>
					</select>
					<input type="hidden" name="isshowsex" value="1">
					<%-- <select name="isshowsex">
								<option value="1" <c:if test="${'1' eq object.isshowsex }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowsex }">selected="selected"</c:if>>不公开</option>
						</select> --%></td>
						
					</tr>
						<tr>
						<td align="right">固定电话</td>
						<td><input type="text" name="telephone" value="${object.telephone }" style="width: 100%"/>
						<input type="hidden" name="isshowtelephone" value="1">
						    <%-- <select name="isshowtelephone">
								<option value="1" <c:if test="${'1' eq object.isshowtelephone }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowtelephone }">selected="selected"</c:if>>不公开</option>
						    </select> --%></td>
					</tr>
						<tr>
						<td align="right">Email</td>
						<td><input type="text" name="email" value="${object.email }" style="width: 100%"/>
						<input type="hidden" name="isshowemail" value="1">
						    <%-- <select name="isshowtelephone">
								<option value="1" <c:if test="${'1' eq object.isshowtelephone }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowtelephone }">selected="selected"</c:if>>不公开</option>
						    </select> --%></td>
					</tr>
					<tr>
						<td align="right">手机</td>
						<td><input type="text" name="mobilephone" value="${object.mobilephone }" style="width: 100%"/>
						<input type="hidden" name="isshowmobilephone" value="1">
						   <%--  <select name="isshowmobilephone">
								<option value="1" <c:if test="${'1' eq object.isshowmobilephone }">selected="selected"</c:if>>公开</option>
								<option value="0" <c:if test="${'0' eq object.isshowmobilephone }">selected="selected"</c:if>>不公开</option>
						    </select> --%></td>
					</tr>
					<tr>
						<td align="right">个人签名</td>
						<td><textarea style="width: 100%" name="personalsignature">${object.personalsignature }</textarea>
						</td>
					</tr>
					<tr>
						<td align="right">工作场所</td>
						<td><textarea style="width: 100%" name="workplace">${object.workplace }</textarea>
						</td>
					</tr>
				</table>

			<div>
			  
				<input type="submit" value="保存" style='background: #14aae4;width: 90px;height: 36px;color: white;text-align: center;line-height: 36px;border: 0;float: right;margin: 10px 2px 10px 10px;' />
				<input type="button" value="返回" onclick="window.history.back();" style='background: #14aae4;width: 90px;height: 36px;color: white;text-align: center;line-height: 36px;border: 0;float: right;margin: 10px;'/>
			</div>

		</form>
</body>
</html>