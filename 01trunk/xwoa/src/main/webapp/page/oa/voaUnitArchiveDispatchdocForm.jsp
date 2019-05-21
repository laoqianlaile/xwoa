<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaUnitArchiveDispatchdoc.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaUnitArchiveDispatchdoc.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="voaUnitArchiveDispatchdoc"  method="post" namespace="/oa" id="voaUnitArchiveDispatchdocForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.usercode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="usercode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.transaffairname" />
					</td>
					<td align="left">
  
						<s:textarea name="transaffairname" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.dispatchDocNo" />
					</td>
					<td align="left">
  
						<s:textarea name="dispatchDocNo" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.optUnitName" />
					</td>
					<td align="left">
  
						<s:textarea name="optUnitName" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.mainNotifyUnit" />
					</td>
					<td align="left">
  
						<s:textarea name="mainNotifyUnit" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.otherUnits" />
					</td>
					<td align="left">
  
						<s:textarea name="otherUnits" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.createTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.createDate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createDate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.lastmodifytime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lastmodifytime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.unitcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="unitcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.updateuser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="updateuser"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
