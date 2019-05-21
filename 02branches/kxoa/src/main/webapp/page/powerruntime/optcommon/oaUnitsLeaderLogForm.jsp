<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitsLeaderLog.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaUnitsLeaderLog.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaUnitsLeaderLog"  method="post" namespace="/powerruntime" id="oaUnitsLeaderLogForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.leadercode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="leadercode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.isuse" />
					</td>
					<td align="left">
	
  
						<s:textfield name="isuse"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.unitcodes" />
					</td>
					<td align="left">
  
						<s:textarea name="unitcodes" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.createuser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createuser"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaUnitsLeaderLog.remark" />
					</td>
					<td align="left">
  
						<s:textarea name="remark" cols="40" rows="2"/>
	
	
					</td>
				</tr>

</table>


</s:form>
