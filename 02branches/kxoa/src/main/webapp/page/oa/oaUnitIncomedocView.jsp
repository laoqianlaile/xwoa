<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitIncomedoc.view.title" /></title>
</head>

<body>
<fieldset>
 <legend>
    <s:text name="oaUnitIncomedoc.view.title" />
 </legend>
<%@ include file="/page/common/messages.jsp"%>

<input type="button" class="btn" value="返回" onclick="window.history.back();"/>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1" class="viewTable">		
  
				<tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.id" />
					</td>
					<td align="left">
						<s:property value="%{id}" />
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.unitcode" />
					</td>
					<td align="left">
					${cp:MAPVALUE('unitcode',unitcode)}
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.createtime" />
					</td>
					<td align="left">
						<fmt:formatDate value='${createtime}' pattern='yyyy-MM-dd' />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.createuser" />
					</td>
					<td align="left">
					${cp:MAPVALUE('usercode',createuser)}
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.isopen" />
					</td>
					<td align="left">
					${cp:MAPVALUE('isopen',isopen)}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.lastmodifytime" />
					</td>
					<td align="left">
					<fmt:formatDate value='${lastmodifytime}' pattern='yyyy-MM-dd' />
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.updateuser" />
					</td>
					<td align="left">
					${cp:MAPVALUE('usercode',updateuser)}
					</td>
				</tr>	

</table>


</fieldset>
</body>
</html>
