<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaBudgetstravel.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaBudgetstravel.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaBudgetstravel"  method="post" namespace="/oa" id="oaBudgetstravelForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.djId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djId" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.title" />
					</td>
					<td align="left">
  
						<s:textarea name="title" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.depno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="depno"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.address" />
					</td>
					<td align="left">
  
						<s:textarea name="address" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.meetingNo" />
					</td>
					<td align="left">
  
						<s:textarea name="meetingNo" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.begTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="begTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.endTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="endTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.trans" />
					</td>
					<td align="left">
	
  
						<s:textfield name="trans"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.transStandard" />
					</td>
					<td align="left">
	
  
						<s:textfield name="transStandard"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountTrans" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountTrans"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.accomStandard" />
					</td>
					<td align="left">
	
  
						<s:textfield name="accomStandard"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountAccom" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountAccom"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.standardFood" />
					</td>
					<td align="left">
	
  
						<s:textfield name="standardFood"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountFood" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountFood"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.miscellaneousStandard" />
					</td>
					<td align="left">
	
  
						<s:textfield name="miscellaneousStandard"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountMiscellaneous" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountMiscellaneous"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.otStandard" />
					</td>
					<td align="left">
  
						<s:textarea name="otStandard" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountOther" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountOther"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.otStandard2" />
					</td>
					<td align="left">
  
						<s:textarea name="otStandard2" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountOther2" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountOther2"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.amountall" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountall"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.flowCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.optid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.solvenote" />
					</td>
					<td align="left">
  
						<s:textarea name="solvenote" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.flowInstId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowInstId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.bizstate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="bizstate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.biztype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="biztype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.solvestatus" />
					</td>
					<td align="left">
	
  
						<s:textfield name="solvestatus"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBudgetstravel.solvetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="solvetime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
