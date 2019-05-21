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
  <table cellspacing='0' cellpadding='0' style="width:98%;margin-left:1%; border-collapse:collapse;">
     <tr>
        <td width="20%">用户名</td>
        <td width="60%">${cp:MAPVALUE("usercode",object.usercode)}</td>
        <td rowspan="5">
          <!-- 公开 -->
          <c:if test="${'1' eq object.isusepicture }">
             <c:choose>
								<c:when test="${not empty picturename }">
									<img id="picture"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=1'
										style="width:100%; height:500%;" />
								</c:when>
								<c:otherwise>
									<img id="picture"
										src='${pageContext.request.contextPath }/themes/blue/images/bbsheadpicturename.png'
										style="width:100%; height:500%;" />
								</c:otherwise>
							</c:choose>
          </c:if>
          <!-- 不公开 -->
           <c:if test="${'0' eq object.isusepicture }">
            <c:choose>
								<c:when test="${not empty headpicturename }">
									<img id="appicon"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&userPicture=0'
										style="width:100%; height:500%;"  />
								</c:when>
								<c:otherwise>
									<img id="appicon"
										src='${pageContext.request.contextPath }/themes/blue/images/bbsheadpicturename.png'
										style="width:100%; height:500%;" />
								</c:otherwise>
							</c:choose>
          </c:if>
        </td>
     </tr>
     <tr>
       <td>性别</td>
       <td>${cp:MAPVALUE('sex',sex)}</td>
     </tr>
     <tr>
       <td>固定电话</td>
       <td>${object.telephone }</td>
     </tr>
     <tr>
       <td>移动电话</td>
       <td>${object.mobilephone }</td>
     </tr>
     <tr>
       <td>Email</td>
       <td>${object.email }</td>
     </tr>
       <tr>
       <td>个人签名</td>
       <td colspan="2">${object.personalsignature}</td>
     </tr>
      <tr>
       <td>工作场所</td>
       <td colspan="2">${object.workplace}</td>
     </tr>
  </table>
  <a style='background:#14aae4;width:90px;height:36px;color:white;text-align:center;line-height:36px;display:inline-block;margin-top:20px;margin-left: 635px;' href="${pageContext.request.contextPath}/oa/oaUserinfo!userinfoEdit2.do?s_usercode=${object.usercode}">编辑</a>
</body>
</html>