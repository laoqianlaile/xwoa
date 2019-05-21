<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaUnitArchiveDispatchdoc.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaUnitArchiveDispatchdoc.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/voaUnitArchiveDispatchdoc!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.transaffairname" />
					</td>
					<td align="left">
						<s:property value="%{transaffairname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.dispatchDocNo" />
					</td>
					<td align="left">
						<s:property value="%{dispatchDocNo}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.optUnitName" />
					</td>
					<td align="left">
						<s:property value="%{optUnitName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.mainNotifyUnit" />
					</td>
					<td align="left">
						<s:property value="%{mainNotifyUnit}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.otherUnits" />
					</td>
					<td align="left">
						<s:property value="%{otherUnits}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.createTime" />
					</td>
					<td align="left">
						<s:property value="%{createTime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.createDate" />
					</td>
					<td align="left">
						<s:property value="%{createDate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.lastmodifytime" />
					</td>
					<td align="left">
						<s:property value="%{lastmodifytime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaUnitArchiveDispatchdoc.updateuser" />
					</td>
					<td align="left">
						<s:property value="%{updateuser}" />
					</td>
				</tr>	

</table>



</body>
</html>
