<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="powerUserInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="powerUserInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerbase/powerUserInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerUserInfo.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerUserInfo.itemId" />
					</td>
					<td align="left">
						<s:property value="%{itemId}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="powerUserInfo.setoperator" />
					</td>
					<td align="left">
						<s:property value="%{setoperator}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="powerUserInfo.settime" />
					</td>
					<td align="left">
						<s:property value="%{settime}" />
					</td>
				</tr>	

</table>



</body>
</html>
