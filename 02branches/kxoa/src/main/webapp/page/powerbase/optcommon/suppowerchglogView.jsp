<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="suppower.view.title" /></title>
</head>

<body>
	<p class="ctitle">
		<s:text name="suppower.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<a
		href='powerbase/supPower!SuppowerView.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none"> <s:text name="opt.btn.back" />
	</a>


	<s:form action="supPower" namespace="/powerbase" method="post">
		<table border="0" cellpadding="1" cellspacing="1" class="viewTable">
			<tr>
				<td class="addTd"><s:text name="suppower.itemId" /></td>
				<td align="left"><s:property value="%{itemId}" /></td>
			</tr>

			<tr>
				<td class="addTd">版本号</td>
				<td align="left"><s:property value="%{version}" /></td>
			</tr>
			<tr>
				<td class="addTd">启用时间</td>
				<td align="left"><fmt:formatDate value='${beginTime}'
						pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>
			<tr>
				<td class="addTd">本版本停用时间</td>
				<td align="left"><fmt:formatDate value='${endTime}'
						pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>

			<!-- 				<tr>这个字段应该没有用了，在界面中可以不用显示(所属部门) -->
			<!-- 					<td class="addTd"> -->
			<%-- 						<s:text name="suppower.orgId" /> --%>
			<!-- 					</td> -->
			<!-- 					<td align="left"> -->
			<%-- 						<s:property value="%{orgId}" /> --%>
			<!-- 					</td> -->
			<!-- 				</tr>	 -->

			<tr>
				<td class="addTd"><s:text name="suppower.itemName" /></td>
				<td align="left"><s:property value="%{itemName}" /></td>
			</tr>

			

			<tr>
				<td class="addTd"><s:text name="suppower.itemType" /></td>
				<td align="left">
					<%-- 						<s:property value="%{itemType}" /> --%>
					${cp:MAPVALUE("ITEM_TYPE",object.itemType)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.timeLimit" /></td>
				<td align="left"><s:property value="%{timeLimit}" />
					${cp:MAPVALUE("Promise_Type",object.promiseType)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.isNetwork" /></td>
				<td align="left">
					<%-- 						<s:property value="%{isNetwork}" /> --%> <s:if
						test='%{ isNetwork==\"1\"}'>否</s:if> <s:if
						test='%{ isNetwork==\"0\"}'>是</s:if>
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.isFormula" /></td>
				<td align="left">
					<%-- 						<s:property value="%{isFormula}" /> --%> <s:if
						test='%{ isFormula==\"0\"}'>否</s:if> <s:if
						test='%{ isFormula==\"1\"}'>是</s:if>
				</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.processDesc" /></td>
				<td align="left"><s:property value="%{processDesc}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.phone" /></td>
				<td align="left"><s:property value="%{phone}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.address" /></td>
				<td align="left"><s:property value="%{address}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.zfAccording" /></td>
				<td align="left"><s:property value="%{zfAccording}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.xwAccording" /></td>
				<td align="left"><s:property value="%{xwAccording}" /></td>
			</tr>


			<tr>
				<td class="addTd"><s:text name="suppower.lastmodifytime" /></td>
				<td align="left"><fmt:formatDate value='${lastmodifytime}'
						pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.fileName" /></td>
				<td align="left"><s:property value="%{fileName}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.auditSign" /></td>
				<td align="left"><s:property value="%{auditSign}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.monitorPhone" /></td>
				<td align="left"><s:property value="%{monitorPhone}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.srvDirectory" /></td>
				<td align="left"><s:property value="%{srvDirectory}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.acceptLink" /></td>
				<td align="left"><s:property value="%{acceptLink}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.applyForm" /></td>
				<td align="left"><s:property value="%{applyForm}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.question" /></td>
				<td align="left"><s:property value="%{question}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.remark" /></td>
				<td align="left"><s:property value="%{remark}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.legalTimeLimit" /></td>
				<td align="left"><s:property value="%{legalTimeLimit}" />
					${cp:MAPVALUE("Anticipate_Type",object.anticipateType)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.charge" /></td>
				<td align="left"><s:property value="%{charge}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.formName" /></td>
				<td align="left"><s:property value="%{formName}" /></td>
			</tr>



<s:if test="%{object.itemType eq 6 }">

		
			<tr>
				<td class="addTd">法律依据条文</td>
				<td align="left"><s:property value="%{punishbasiscontent}" /></td>
			</tr>
			<tr>
				<td class="addTd">法律依据</td>
				<td align="left"><s:property value="%{punishbasis}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.freeJudge" /></td>
				<td align="left"><s:property value="%{freeJudge}" /></td>
			</tr>

			
			<tr>
				<td class="addTd"><s:text name="suppower.punishClass" /></td>
				<td align="left"><s:property value="%{punishClass}" /></td>
			</tr>
</s:if>
<tr>
				<td class="addTd"><s:text name="suppower.levyUpon" /></td>
				<td align="left"><s:property value="%{levyUpon}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.condition" /></td>
				<td align="left"><s:property value="%{condition}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.inFlowInfo" /></td>
				<td align="left"><s:property value="%{inFlowInfo}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.inFlowImg" /></td>
				<td align="left"><s:property value="%{inFlowImg}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.inFlowImgName" /></td>
				<td align="left"><s:property value="%{inFlowImgName}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.ischarge" /></td>
				<td align="left">
					<%-- 						<s:property value="%{ischarge}" /> --%> <s:if
						test='%{ ischarge==\"0\"}'>否</s:if> <s:if
						test='%{ ischarge==\"1\"}'>是</s:if>
				</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.chargeBasis" /></td>
				<td align="left"><s:property value="%{chargeBasis}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.chargeStandard" /></td>
				<td align="left"><s:property value="%{chargeStandard}" /></td>
			</tr>



			<tr>
				<td class="addTd"><s:text name="suppower.transactDepname" /></td>
				<td align="left"><s:property value="%{transactDepname}" /></td>
			</tr>


			<tr>
				<td class="addTd"><s:text name="suppower.acceptCondition" /></td>
				<td align="left"><s:property value="%{acceptCondition}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.qlDepid" /></td>
				<td align="left">
					<%-- 						<s:property value="%{qlDepid}" /> --%>
					${cp:MAPVALUE("unitcode",object.qlDepid)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.qlDepstate" /></td>
				<td align="left">
					<%-- 						<s:property value="%{qlDepstate}" /> --%>
					${cp:MAPVALUE("QL_DepState",object.qlDepstate)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.entrustName" /></td>
				<td align="left">
					<%-- 						<s:property value="%{entrustName}" /> --%>
					${cp:MAPVALUE("ENTRUST_Name",object.entrustName)}
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.qlState" /></td>
				<td align="left">
					<%-- 						<s:property value="%{qlState}" /> --%>
					${cp:MAPVALUE("QL_State",object.qlState)}
				</td>
			</tr>

		</table>
	</s:form>

	<p />
	
</body>
</html>
