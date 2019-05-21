<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script type="text/javascript">
	function onBack()
	{
		if (window.history.length > 0)
			window.history.back();
		else
      		window.close();
  	}
</script>
<title><s:text name="oaUserinfo.view.title" /></title>
</head>

<body class="sub-flow">
<fieldset class="_new">
		<legend>
			<p>查看内部通讯录</p>
		</legend>
<input type="button" value="返回" class="btn" onclick="onBack()" />
<p>	
	
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
				<tr>
					<td class="addTd">
						用户名
					</td>
					<td align="left">
						${cp:MAPVALUE("usercode",object.usercode)} 
					</td>
					<td class="addTd">职务</td>
				    <td align="left">${object.fUserinfo.userdesc }<%-- ${cp:MAPVALUE("RankType",userrank)} --%></td>
				 </tr>	
				<%--  <tr>
					<td class="addTd">
						<s:text name="oaUserinfo.sex" />
					</td>
					<td align="left">
						 ${cp:MAPVALUE('sex',sex)}
					</td>
				     <td class="addTd">
						<s:text name="oaUserinfo.birthday" />
					</td>
					<td align="left">
					<fmt:formatDate value='${birthday}' pattern='yyyy-MM-dd'/>
					</td>
				 </tr>	 --%>
				 <tr>
					<td class="addTd">
						<s:text name="oaUserinfo.telephone" />
					</td>
					<td align="left">
						<s:property value="%{telephone}" />
					</td>
					<td class="addTd">
						<s:text name="oaUserinfo.mobilephone" />
					</td>
					<td align="left">
						<s:property value="%{mobilephone}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						电子邮件
					</td>
					<td align="left">
						<s:property value="%{email}" />
					</td>
					<td class="addTd">
						办公地点
					</td>
					<td align="left">
						<s:property value="%{workplace}" />
					</td>
				</tr>
				<tr>	
				
					<td class="addTd">
						<s:text name="oaUserinfo.remark" />
					</td>
					<td align="left" colspan="4">
						<s:property value="%{remark}" />
					</td>
				</tr>	
             <%--   <tr>
				<td class="addTd">用户说明</td>
				<td align="left" colspan="4">${object.fUserinfo.userdesc }</td>
			   </tr> --%>
</table>

</fieldset>

</body>
</html>
