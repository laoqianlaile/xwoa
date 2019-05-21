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
</style>
</head>
<body>
  	<form action="${pageContext.request.contextPath }/oa/oaUserinfo!userinfoSave2.do" method="post"  enctype="multipart/form-data"
		id="oaUserinfoForm">
		
		<input type="hidden" id="usercode" name="usercode"
			value="${object.usercode}" />
				<table cellspacing='0' cellpadding='0' style="width:98%;margin-left:1%; border-collapse:collapse;">
				   	<tr>
						<td align="right" >选择人员</td>
						<td>${cp:MAPVALUE("usercode",object.usercode)}</td>
					</tr>
				</table>
			<div>
				<input type="submit" value="保存" style='background: #14aae4;width: 90px;height: 36px;color: white;text-align: center;line-height: 36px;border: 0;float: right;margin: 10px 2px 10px 10px;' />
			</div>
		</form>
</body>
</html>