<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSurveydetail.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaSurveydetail.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaSurveydetail!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.itemid" />
					</td>
					<td align="left">
						<s:property value="%{itemid}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.title" />
					</td>
					<td align="left">
						<s:property value="%{title}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

</table>



</body>
</html>
