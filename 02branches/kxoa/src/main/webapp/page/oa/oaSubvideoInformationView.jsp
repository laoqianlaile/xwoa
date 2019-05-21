<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSubvideoInformation.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaSubvideoInformation.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>


</a>
<p>	
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
		


				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.no" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.title" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{title}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.releaseDate" />
					</td>
					<td align="left">
						<fmt:formatDate value='${creatertime}'
									pattern='yyyy-MM-dd' />
					</td>
						<td class="addTd">
						<s:text name="oaSubvideoInformation.lastmodifytime" />
					</td>
					<td align="left">
						<s:property value="%{lastmodifytime}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.isuseprarent" />
					</td>
					<td align="left" colspan="3">
					    <c:if test="${isuseprarent==1}">否</c:if>
					    <c:if test="${isuseprarent==0}">是</c:if>
					</td>
				</tr>	

			<%-- 	<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.smallimage" />
					</td>
					<td align="left">
						<s:property value="%{smallimage}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.videoPath" />
					</td>
					<td align="left">
						<s:property value="%{videoPath}" />
					</td>
				</tr>	 --%>

			
				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.creater" />
					</td>
					<td align="left">
					 ${cp:MAPVALUE("usercode",creater)}
				
					</td>
			
					<td class="addTd">
						<s:text name="oaSubvideoInformation.creatertime" />
					</td>
					<td align="left">
						<fmt:formatDate value='${creatertime}'
									pattern='yyyy-MM-dd' />
					
					</td>
				</tr>	

	<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.remark" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{remark}" />
					</td>
				</tr>	

			

</table>
<div class="formButton">
			<input type="button" class="btn" target="submit" style="cursor:pointer;" 
						onclick="window.history.back();" value='返回'> 


</body>
</html>
