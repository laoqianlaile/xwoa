<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaUnitArchiveIncomedoc.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaUnitArchiveIncomedoc.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/voaUnitArchiveIncomedoc!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomedDocTitle" />
					</td>
					<td align="left">
						<s:property value="%{incomedDocTitle}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDate" />
					</td>
					<td align="left">
						<s:property value="%{incomeDate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.acceptarchiveno" />
					</td>
					<td align="left">
						<s:property value="%{acceptarchiveno}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDeptName" />
					</td>
					<td align="left">
						<s:property value="%{incomeDeptName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDocNo" />
					</td>
					<td align="left">
						<s:property value="%{incomeDocNo}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.lastmodifytime" />
					</td>
					<td align="left">
						<s:property value="%{lastmodifytime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.updateuser" />
					</td>
					<td align="left">
						<s:property value="%{updateuser}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDeptType" />
					</td>
					<td align="left">
						<s:property value="%{incomeDeptType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.belongUnitcode" />
					</td>
					<td align="left">
						<s:property value="%{belongUnitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.incomeDocType" />
					</td>
					<td align="left">
						<s:property value="%{incomeDocType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveIncomedoc.itemSource" />
					</td>
					<td align="left">
						<s:property value="%{itemSource}" />
					</td>
				</tr>	

</table>



</body>
</html>
