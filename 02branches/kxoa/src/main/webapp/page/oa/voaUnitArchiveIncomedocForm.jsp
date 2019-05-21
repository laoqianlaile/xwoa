<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaUnitArchiveIncomedoc.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaUnitArchiveIncomedoc.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="voaUnitArchiveIncomedoc"  method="post" namespace="/oa" id="voaUnitArchiveIncomedocForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.usercode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="usercode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomedDocTitle" />
					</td>
					<td align="left">
  
						<s:textarea name="incomedDocTitle" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="incomeDate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.acceptarchiveno" />
					</td>
					<td align="left">
  
						<s:textarea name="acceptarchiveno" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDeptName" />
					</td>
					<td align="left">
  
						<s:textarea name="incomeDeptName" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDocNo" />
					</td>
					<td align="left">
  
						<s:textarea name="incomeDocNo" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.unitcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="unitcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.lastmodifytime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lastmodifytime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.updateuser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="updateuser"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDeptType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="incomeDeptType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.belongUnitcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="belongUnitcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDocType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="incomeDocType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.itemSource" />
					</td>
					<td align="left">
	
  
						<s:textfield name="itemSource"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
