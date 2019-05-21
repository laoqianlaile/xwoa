<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="optStuffInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="optStuffInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/optStuffInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffid" />
					</td>
					<td align="left">
						<s:property value="%{stuffid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.djId" />
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffname" />
					</td>
					<td align="left">
						<s:property value="%{stuffname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffcontent" />
					</td>
					<td align="left">
						<s:property value="%{stuffcontent}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.iszhi" />
					</td>
					<td align="left">
						<s:property value="%{iszhi}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.filename" />
					</td>
					<td align="left">
						<s:property value="%{filename}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.nodeInstId" />
					</td>
					<td align="left">
						<s:property value="%{nodeInstId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.uploadtime" />
					</td>
					<td align="left">
						<s:property value="%{uploadtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.uploadusercode" />
					</td>
					<td align="left">
						<s:property value="%{uploadusercode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.nodename" />
					</td>
					<td align="left">
						<s:property value="%{nodename}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.filetype" />
					</td>
					<td align="left">
						<s:property value="%{filetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.archivetype" />
					</td>
					<td align="left">
						<s:property value="%{archivetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.groupid" />
					</td>
					<td align="left">
						<s:property value="%{groupid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.sortId" />
					</td>
					<td align="left">
						<s:property value="%{sortId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.isuse" />
					</td>
					<td align="left">
						<s:property value="%{isuse}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.recordid" />
					</td>
					<td align="left">
						<s:property value="%{recordid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.wsno" />
					</td>
					<td align="left">
						<s:property value="%{wsno}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.isUpload" />
					</td>
					<td align="left">
						<s:property value="%{isUpload}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffpath" />
					</td>
					<td align="left">
						<s:property value="%{stuffpath}" />
					</td>
				</tr>	

</table>



</body>
</html>
