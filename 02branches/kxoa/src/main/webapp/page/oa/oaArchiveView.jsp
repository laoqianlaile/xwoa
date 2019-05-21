<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaArchive.view.title" /></title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
	<input type="button" value="返回" class="btn" onclick="window.history.back();"/>
	<fieldset class="_new">
				<legend style="padding:4px 8px 3px;">
					<b>归档信息查看</b>
				</legend>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.allcaseno" />
					</td>
					<td align="left">
						<s:property value="%{allcaseno}" />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.titanic" />
					</td>
					<td align="left">
						<s:property value="%{titanic}" />
					</td>
					
					
				</tr>
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.title" />
					</td>
					<td align="left" colspan="4">
						<s:property value="%{title}" />
				</td>
				</tr>
				
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.parallelTitle" />
					</td>
					<td align="left" colspan="4">
						<s:property value="%{parallelTitle}" />
				</td>
				</tr>	
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.deputyTitle" />
					</td>
					<td align="left" colspan="4">
						<s:property value="%{deputyTitle}" />
				</td>
				</tr>
				<tr>
					<td class="addTd">
						文号
					</td>
					<td align="left">
					<s:property value="%{docno}" />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.bookdate" />
					</td>
					<td align="left">
						<fmt:formatDate value='${bookdate}' pattern='yyyy-MM-dd' />
					</td>
					
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.pages" />
					</td>
					<td align="left">
						<s:property value="%{pages}" />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.copies" />
					</td>
					<td align="left">
						<s:property value="%{copies}" />
					</td>
					
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.filingdate" />
					</td>
					<td align="left">
						<fmt:formatDate value='${filingdate}' pattern='yyyy-MM-dd HH:mm' />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.filingannual" />
					</td>
					<td align="left">
						<s:property value="%{filingannual}" />
					</td>
				</tr>
				<tr>
					 <td class="addTd">
						<s:text name="oaArchive.responsibledep" />
					</td>
					<td align="left">
						<s:property value="%{responsibledep}" />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.duration" />
					</td>
					<td align="left">
						${cp:MAPVALUE('BGNX',duration)}
					</td> 
				</tr>
					<tr>
				<td class="addTd">
						档案门类
					</td>
					<td align="left">
					<s:text name="oaArchive.archiveType" />
					</td>
				<td class="addTd"><s:text name="oaArchive.classification" /></td>
				<td align="left">
					${cp:MAPVALUE('GDMJ',classification)}
				     </td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.locationpath" />
					</td>
					<td align="left" >
						<s:property value="%{locationpath}" />
					</td>
					<td class="addTd">
						<s:text  name="oaArchive.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
					
				</tr>
				
				<tr>
					<td class="addTd">
						<s:text name="oaArchive.remark" />
					</td>
					<td align="left" colspan="4">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
				<td class="addTd">
						<s:text name="oaArchive.createuser" />
					</td>
					<td align="left">
						${cp:MAPVALUE('usercode',updateuser)}
					</td>
					
					<%-- <td class="addTd">
						<s:text name="oaArchive.doctype" />
					</td>
					<td align="left">
							${cp:MAPVALUE('DOCPATTEARN',doctype)}
					</td> --%>
					<td class="addTd">
						开始时间
					</td>
					<td align="left">
						<fmt:formatDate value='${createtime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.lastmodifytime" />
					</td>
					<td align="left">
					<fmt:formatDate value='${lastmodifytime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.updateuser" />
					</td>
					<td align="left">
						${cp:MAPVALUE('usercode',updateuser)}
					</td>
				</tr>
				<tr>
				<td colspan="4">
				<c:if test="${doctype eq 'S' }">
				<a href='${contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId=${object.no }&nodeInstId=0'>
				查看归档文件详情
				</a>
				</c:if>
				<c:if test="${doctype eq 'F' }">
				<a href='${contextPath}/dispatchdoc/dispatchDoc!generalOptView.do?djId=${object.no }&nodeInstId=0'>
				查看归档文件详情
				</a>
				</c:if>	
				</td>
				</tr>
</table>
<c:if test="${ not empty moduleParam.stuffGroupId}">
<br/>
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!listStuffs.do?djId=${object.id}&notitle=1"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		</c:if>
</fieldset>
</body>
</html>
