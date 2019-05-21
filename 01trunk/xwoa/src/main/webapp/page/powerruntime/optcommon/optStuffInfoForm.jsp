<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="optStuffInfo.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="optStuffInfo.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="optStuffInfo"  method="post" namespace="/powerruntime" id="optStuffInfoForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="stuffid" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.djId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="djId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffname" />
					</td>
					<td align="left">
	
  
						<s:textfield name="stuffname"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffcontent" />
					</td>
					<td align="left">
	
  
						<s:textfield name="stuffcontent"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.iszhi" />
					</td>
					<td align="left">
	
  
						<s:textfield name="iszhi"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.filename" />
					</td>
					<td align="left">
	
  
						<s:textfield name="filename"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.nodeInstId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeInstId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.uploadtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="uploadtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.uploadusercode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="uploadusercode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.nodename" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodename"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.filetype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="filetype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.archivetype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="archivetype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.groupid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="groupid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.sortId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="sortId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.isuse" />
					</td>
					<td align="left">
	
  
						<s:textfield name="isuse"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.recordid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="recordid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.wsno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="wsno"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.isUpload" />
					</td>
					<td align="left">
	
  
						<s:textfield name="isUpload"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optStuffInfo.stuffpath" />
					</td>
					<td align="left">
  
						<s:textarea name="stuffpath" cols="40" rows="2"/>
	
	
					</td>
				</tr>

</table>


</s:form>
