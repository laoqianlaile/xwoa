<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaBbsAttention.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaBbsAttention.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='bbs/oaBbsAttention!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.themeno" />
					</td>
					<td align="left">
						<s:property value="%{themeno}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.laytype" />
					</td>
					<td align="left">
						<s:property value="%{laytype}" />
					</td>
				</tr>	

</table>



</body>
</html>
