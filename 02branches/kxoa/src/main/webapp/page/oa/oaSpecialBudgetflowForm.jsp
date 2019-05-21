<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaSpecialBudget.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaSpecialBudget.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaSpecialBudget"  method="post" namespace="/oa" id="oaSpecialBudgetForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.djId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djId" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.title" />
					</td>
					<td align="left">
  
						<s:textarea name="title" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.depno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="depno"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.applyNo" />
					</td>
					<td align="left">
	
  
						<s:textfield name="applyNo"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.meetingNo" />
					</td>
					<td align="left">
  
						<s:textarea name="meetingNo" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.meettype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="meettype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.outerPersions" />
					</td>
					<td align="left">
	
  
						<s:textfield name="outerPersions"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.meetingPersionsNum" />
					</td>
					<td align="left">
	
  
						<s:textfield name="meetingPersionsNum"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.begTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="begTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.endTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="endTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard" />
					</td>
					<td align="left">
  
						<s:textarea name="standard" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard2" />
					</td>
					<td align="left">
  
						<s:textarea name="standard2" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount2" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount2"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard3" />
					</td>
					<td align="left">
  
						<s:textarea name="standard3" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount3" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount3"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard4" />
					</td>
					<td align="left">
  
						<s:textarea name="standard4" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount4" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount4"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard5" />
					</td>
					<td align="left">
  
						<s:textarea name="standard5" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount5" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount5"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.standard6" />
					</td>
					<td align="left">
  
						<s:textarea name="standard6" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amount6" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount6"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.amountall" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amountall"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.flowcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.optid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.solvenote" />
					</td>
					<td align="left">
  
						<s:textarea name="solvenote" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.flowInstId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowInstId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.bizstate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="bizstate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.biztype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="biztype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.solvestatus" />
					</td>
					<td align="left">
	
  
						<s:textfield name="solvestatus"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSpecialBudget.solvetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="solvetime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
