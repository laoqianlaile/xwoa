<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("GBK");
%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="com.centit.powerbase.po.*" %>
<html>
<head>
<title><s:text name="suppower.edit.title" /></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
</head>


<body onload="inital('${object.itemType}');checkReceive(${object.ischarge});">

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="form">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			<s:text name="suppower.edit.title" />
		</legend>

		<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm" enctype="multipart/form-data">
<%-- 		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" onclick="return check();"/> --%>
<!-- 		<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" /> -->
		<input type="hidden" id="version" name="version"  value="${param.version}" /> 
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd" ><s:text name="suppower.itemId" /><font color='red'>*</font></td>
					<td align="left" ><c:if
							test="${empty object.itemId}">
							<s:textfield name="itemId" style="width:200px;height:25px;" value="%{object.itemId}"/>
						</c:if> <c:if test="${not empty object.itemId}">
							<input type="hidden" id="itemId" name="itemId"
								value="${object.itemId}" />
							<s:property value="%{object.itemId}" />
						</c:if></td>
					<td class="addTd" width="130">版本号<font color='red'>*</font></td>
					<td align="left">
					<select name="version_bg" style="width:200px;height:25px;" onchange="checkVersion();">
					<s:iterator value="versionList" id="Id"> 
								<option value="${Id}" <c:if test="${version_bg eq Id}">selected="selected"</c:if>>
									<c:out value="${Id}" />
								</option>
					</s:iterator>
					</select></td>
				</tr>
				<%-- <tr>
					<td class="addTd" width="130"><s:text name="suppower.outItemId" /></td>
					<td align="left" colspan="3"><s:textfield name="outItemId" style="width:200px;height:25px;"
							value="%{object.outItemId}" /></td>
					
				</tr> --%>
				<tr>
					<td class="addTd"><s:text name="suppower.itemName" /><font color='red'>*</font></td>
					<td align="left" colspan="3"><s:textfield name="itemName"
							style="width:100%;height:25px;" value="%{object.itemName}" /></td>
				</tr>
				
				<tr>
					<td class="addTd" width="130">所属部门<font color='red'>*</font></td>
					<td align="left"><select name="orgId" style="width:200px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList}">       
							<option value="<c:out value='${row.depno}'/>"
							<c:if test="${row.depno==object.orgId}">selected="selected"</c:if>>   
								<c:out value="${row.unitname}"/>   
							</option>       
					</c:forEach> 
					</select></td>


					<td class="addTd" >权力类型<font color='red'>*</font></td>
					<td align="left"><select name="itemType" style="width:200px;height:25px;"id="itemType"
						onchange="checkItemType();">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
								<option value="${row.key}"
									<c:if test="${object.itemType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				
				<tr>
					<td class="addTd"  width="130">事项类型<font color='red'>*</font></td>
					<td align="left" ><select name="optItemType" style="width:200px;height:25px;"id="optItemType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('optItemType')}">
								<option value="${row.key}"
									<c:if test="${object.optItemType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				    <td class="addTd">公开方式<font color='red'>*</font></td>
					<td align="left" ><select name="optOpenStyle" style="width:200px;height:25px;"id="optOpenStyle">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('optOpenStyle')}">
								<option value="${row.key}"
									<c:if test="${object.optOpenStyle eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				
				
				<tr>
					<td class="addTd"><s:text name="suppower.qlDepid" /><font color='red'>*</font></td>
					<td align="left"><select name="qlDepid" style="width:200px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList}">       
						<option value="<c:out value='${row.depno}'/>"
						<c:if test="${row.depno==object.qlDepid}">selected="selected"</c:if>>   
							<c:out value="${row.unitname}"/>   
						</option>       
					</c:forEach> 
					</select></td>
					<td class="addTd"><s:text
							name="suppower.qlDepstate" /><font color='red'>*</font></td>
					<td align="left"><select name="qlDepstate"
						style="width:200px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('QL_DepState')}">
								<option value="${row.key}"
									<c:if test="${object.qlDepstate eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<%-- <tr>
					<td class="addTd"><s:text name="suppower.acceptDepname" /></td>
					<td align="left"><s:textfield name="acceptDepname" style="width:200px;height:25px;"
							value="%{object.acceptDepname}" /></td>
					<td class="addTd" width="130"><s:text
							name="suppower.compatibleType" /></td>
					<td align="left"><s:radio name="compatibleType"
							list="#{'1':'个人','2':'机构'}" value="%{object.compatibleType}"
							listKey="key" listValue="value" /></td>
				</tr> --%>
				<tr>
					<td class="addTd"><s:text
							name="suppower.timeLimit" /></td>
					<td align="left"><s:textfield name="timeLimit" style="width:150px;height:25px;"
							value="%{object.timeLimit}" /> <select name="promiseType"
						style="width: 80px">
							<option value="">-请选择-</option>
							<c:forEach var="row" items="${cp:DICTIONARY('Promise_Type')}">
								<option value="${row.key}"
									<c:if test="${object.promiseType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd"><s:text
							name="suppower.legalTimeLimit" /></td>
					<td align="left"><s:textfield name="legalTimeLimit" style="width:150px;height:25px;"
							value="%{object.legalTimeLimit}" /> <select name="anticipateType" style="width:80px;">
							<option value="">-请选择-</option>
							<c:forEach var="row" items="${cp:DICTIONARY('Promise_Type')}">
								<option value="${row.key}"
									<c:if test="${object.anticipateType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text
							name="suppower.isNetwork" /></td>
					<td align="left" colspan="3"><s:radio name="isNetwork"
							list="#{'0':'是','1':'否'}" value="%{object.isNetwork}"
							listKey="key" listValue="value" /></td>

				<%-- 	<td class="addTd"><s:text
							name="suppower.isFormula" /></td>
					<td align="left"><s:radio name="isFormula"
							list="#{'1':'是','0':'否'}" value="%{object.isFormula}"
							listKey="key" listValue="value" /></td> --%>
				</tr>
				 <tr>
					<td class="addTd">实施依据</td>
					<td align="left" colspan="3"><s:textfield name="actualDependent"
							style="width:100%;height:40px;" value="%{object.actualDependent}" /></td>
				</tr> 


			<%-- 	<tr>
					<td class="addTd"><s:text name="suppower.phone" /></td>
					<td align="left"><s:textfield name="phone" style="width:200px;height:25px;"
							value="%{object.phone}" /></td>
					<td class="addTd"><s:text
							name="suppower.monitorPhone" /></td>
					<td align="left"><s:textfield name="monitorPhone" style="width:200px;height:25px;"
							value="%{object.monitorPhone}" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text
							name="suppower.acceptLink" /></td>
					<td align="left"><s:textfield name="acceptLink" style="width:200px;height:25px;"
							value="%{object.acceptLink}" /></td>
					<td class="addTd"><s:text name="suppower.qlState" /><font color='red'>*</font></td>
					<td align="left"><select name="qlState" style="width:200px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('QL_State')}">
								<option value="${row.key}"
									<c:if test="${object.qlState eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="suppower.transactDepname" /></td>
					<td align="left" colspan="3"><s:textarea
							name="transactDepname" cols="40" rows="2"
							style="width:100%;height:40px;" value="%{object.transactDepname}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.address" /></td>
					<td align="left" colspan="3"><s:textfield name="address" style="width:100%;height:25px;"
							 value="%{object.address}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.acceptTimeScope" /></td>
					<td align="left" colspan="3"><s:textfield name="acceptTimeScope" style="width:100%;height:25px;"
							size="60" value="%{object.acceptTimeScope}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.ischarge" /></td>
					<td align="left"><s:radio name="ischarge"
							list="#{'1':'是','0':'否'}" onclick="checkReceive(this.value);" value="%{object.ischarge}"
							listKey="key" listValue="value" /></td>
				   <td class="addTd"><s:text name="suppower.itemClass" /></td>
					<td align="left"><select name="itemClass" style="width:200px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('itemClass')}">
								<option value="${row.key}"
									<c:if test="${object.itemClass eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr id="chargeBasis_tr">
					<td class="addTd"><s:text
							name="suppower.chargeBasis" /></td>
					<td align="left" colspan="3"><s:textarea name="chargeBasis"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.chargeBasis}" /></td>
				</tr>
				<tr id="chargeStandard_tr">
					<td class="addTd"><s:text
							name="suppower.chargeStandard" /></td>
					<td align="left" colspan="3"><s:textarea name="chargeStandard"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.chargeStandard}" /></td>
				</tr>
				
					<tr>
					<td class="addTd"><s:text name="suppower.hasItem" /></td>
					<td align="left" colspan="3"><s:radio name="hasItem"  value="%{object.hasItem}"
							list="#{'1':'是','0':'否'}" 
							listKey="key" listValue="value" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.stateQueryUrl" /></td>
					<td align="left" colspan="3"><s:textfield name="stateQueryUrl"
							size="60" value="%{object.stateQueryUrl}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.resultQueryUrl" /></td>
					<td align="left" colspan="3"><s:textfield name="resultQueryUrl"
							size="60" value="%{object.resultQueryUrl}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.applyMaterial" /></td>
					<td align="left" colspan="3"><s:textarea name="applyMaterial"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.applyMaterial}" /></td>
				</tr>
					<tr id="material_tr1">
			<td class="addTd"><s:text name="suppower.risk" /></td>
			<td colspan="3">


<input type="button" onclick="addOrderRow('detailItemBody1','0','0',this,'add')"  class="btn" id="btnAdd1" name="btnAdd" value="添加"/>
<table width="95%" id="table_b1" cellspacing="0" cellpadding="0" >
    <tbody class="detailItemBody" id="detailItemBody1"> 
   <tr class="b_darkblue">
  <td width="10%">风险环节</td>
    <td width="20%">风险点描述</td>
    <td width="10%">风险等级</td>
    <td width="20%">防控措施</td>     
    <td width="8%">操作</td>
   </tr>
   <tr style="display: none">    
    <td><input type="text" id="seq_type" name="seq_type" />
	</td>  
    <td><input type="text" id="seq_base_name" name="seq_base_name"/>  
	</td>  
	<td>
		<select name="seq_id" style="width: 80px">
							
							<c:forEach var="row" items="${cp:DICTIONARY('seq_id')}">
								<option value="${row.key}"
									<c:if test="${process.seq_id eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select>
    </td>
     <td><input type="text" id="seq_remark" name="seq_remark" />
	</td>  
		<td><input type="button" onclick="addOrderRow('detailItemBody',2,11,this,'delete')" value="删除" class="btn" id="btnDelete" name="btnDelete" />
		<input type="hidden" name="buttable">
		</td>
	</tr>
	<c:forEach items="${list2 }" varStatus="i" var="process" >
	<tr>    
    <td><input id="seq_type" name="seq_type" value="${process.seq_type }" />
	</td>  
    <td><input id="seq_base_name" name="seq_base_name" value="${process.seq_base_name }"/>
	</td>  
	<td>
	
	<select name="seq_id" style="width: 80px">
							
							<c:forEach var="row" items="${cp:DICTIONARY('seq_id')}">
								<option value="${row.key}"
									<c:if test="${process.seq_id eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select>
		
    </td>
     <td><input type="text" id="seq_remark" name="seq_remark" value="${process.seq_remark }"/>
	</td>  
		<td><input type="button" onclick="addOrderRow('detailItemBody','i','',this,'delete')" value="删除" class="btn" id="btnDelete" name="btnDelete" />
		<input type="hidden" name="buttable">
		</td>
	</tr>
   
	</c:forEach>
	</tbody>
</table> 



			 
			</td>			
		</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.acceptCondition" /></td>
					<td align="left" colspan="3"><input type="button"
						onclick="addOrderRow('detailItemBody4','0','0',this,'add')"
						class="btn" id="btnAdd1" name="btnAdd" value="添加" />
						<table width="95%" id="table_b1" cellspacing="0" cellpadding="0">
							<tbody class="detailItemBody5" id="detailItemBody4">
								<tr class="b_darkblue">
								  <td width="20%">条件类型</td>
								    <td width="50%">条件</td>
								        
								    <td width="8%">操作</td>
								   </tr>
								<tr style="display: none">
									<td><textarea style="width: 90%;" id="stand_id" name="stand_id"></textarea></td>
									<td><textarea style="width: 90%;" id="stand_code"
										name="stand_code"></textarea></td>
									
									<td><input type="button"
										onclick="addOrderRow('detailItemBody5',2,11,this,'delete')"
										value="删除" class="btn" id="btnDelete" name="btnDelete" /> <input
										type="hidden" name="buttable"></td>
								</tr>
								<c:forEach items="${list3 }" varStatus="i" var="process">
									<tr>
										<td><textarea style="width: 90%;" id="stand_id" name="stand_id">${process.stand_id } </textarea></td>
										<td><textarea style="width: 90%;" id="stand_code" name="stand_code">${process.stand_code }</textarea></td>
										
										<td><input type="button"
											onclick="addOrderRow('detailItemBody5','i','',this,'delete')"
											value="删除" class="btn" id="btnDelete" name="btnDelete" /> <input
											type="hidden" name="buttable"></td>
									</tr>

								</c:forEach>
							</tbody>
						</table></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.processWorkDesc" /></td>
					<td align="left" colspan="3"><input type="button"
						onclick="addOrderRow('detailItemBody2','0','0',this,'add')"
						class="btn" id="btnAdd1" name="btnAdd" value="添加" />
						<table width="95%" id="table_b1" cellspacing="0" cellpadding="0">
							<tbody class="detailItemBody1" id="detailItemBody2">
								<tr class="b_darkblue">
									<td width="15%">步骤</td>
									<td width="30%">申请人要做的事情</td>
									<td width="30%">部门要做的事情</td>
									<td width="15%">回应时间</td>
						
									<td width="8%">操作</td>
								</tr>
								<tr style="display: none">
									<td><input type="text" id="seq_base_lowmul" name="seq_base_lowmul" /></td>
									<td><input type="text" id="seq_base_topmul"
										name="seq_base_topmul" /></td>
									<td><textarea style="width: 90%;" id="lastmodyfidate" name="lastmodyfidate"></textarea></td>
									<td><textarea style="width: 90%;" id="seq_base_unit" name="seq_base_unit"></textarea>
									</td>
									<td><input type="button"
										onclick="addOrderRow('detailItemBody1',2,11,this,'delete')"
										value="删除" class="btn" id="btnDelete" name="btnDelete" /> <input
										type="hidden" name="buttable"></td>
								</tr>
								<c:forEach items="${list1 }" varStatus="i" var="process">
									<tr>
										<td><input style="width: 90%;" id="seq_base_lowmul" name="seq_base_lowmul"
											value="${process.seq_base_lowmul }" /></td>
										<td><textarea style="width: 90%;" id="seq_base_topmul" name="seq_base_topmul">${process.seq_base_topmul }</textarea></td>
										<td><textarea style="width: 90%" id="lastmodyfidate" name="lastmodyfidate">${process.lastmodyfidate }</textarea></td>
										<td><input  style="width: 90%;" type="text" id="seq_base_unit" name="seq_base_unit"
											value="${process.seq_base_unit }" /></td>
										<td><input type="button"
											onclick="addOrderRow('detailItemBody1','i','',this,'delete')"
											value="删除" class="btn" id="btnDelete" name="btnDelete" /> <input
											type="hidden" name="buttable"></td>
									</tr>

								</c:forEach>
							</tbody>
						</table></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.regulatoryMeasures" /></td>
					<td align="left" colspan="3"><s:textarea name="regulatoryMeasures"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.regulatoryMeasures}" /></td>
				</tr>
				
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.srvDirectoryStuff" /></td>
					<td align="left" colspan="3"><s:file name="srvDirectoryStuff_" /> <c:if
							test="${not empty object.srvDirectoryStuff}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.itemId}','${object.version }','srvDirectoryStuff_')">&nbsp;
				<input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.itemId}','${object.version }','srvDirectoryStuff_')">
						</c:if></td>
				</tr>
				
				<tr id="punishbasis_tr">
					<td class="addTd" width="130">法律依据</td>
					<td align="left" colspan="3"><s:textarea name="punishbasis"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.punishbasis}" /></td>
				</tr>
				<tr id="punishbasiscontent_tr">
					<td class="addTd" width="130">法律依据条文</td>
					<td align="left" colspan="3"><s:textarea name="punishbasiscontent"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.punishbasiscontent}" /></td>
				</tr>
				<tr id="punishClass_tr">
					<td class="addTd" width="20%"><s:text
							name="suppower.punishClass" /></td>
					<td align="left" colspan="3" width="80%"><s:textarea name="punishClass"
							cols="40" rows="2"  style="width:500px;"
							value="%{object.punishClass}" readonly="true"/>
					<input type="hidden" id="dis_standard" name="dis_standard" />
					<input type="button" name="built" value="查看" class="btn"
								onclick="showZyclstand('${object.itemId}', '${object.version}')">&nbsp;
				<input type="button" name="built" value="编辑" class="btn"
								onclick="editZyclstand('${object.itemId}', '${object.version}')">		
					</td>
				</tr>

				<tr id="freeJudge_tr">
					<td class="addTd" width="130"><s:text
							name="自由裁量权" /></td>
				    <td align="left" colspan="3" width="80%"><s:textarea name="freeJudge"
							cols="40" rows="2"  style="width:500px;"
							value="%{object.freeJudge}" readonly="true"/>
					
				  <input type="hidden" id="dis_detail" name="dis_detail" />
				   <input type="button" name="built" value="查看" class="btn"
								onclick="showZycl('${object.itemId}', '${object.version}')">&nbsp;
				<input type="button" name="built" value="编辑" class="btn"
								onclick="editZycl('${object.itemId}', '${object.version}')">		
				    </td>
				</tr>
				
				
				<tr id="levyUpon_tr">
					<td class="addTd" width="130"><s:text name="suppower.levyUpon" /></td>
					<td align="left" colspan="3"><s:textarea name="levyUpon"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.levyUpon}" /></td>
				</tr>
				




				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.processDesc" /></td>
					<td align="left" colspan="3"><s:file name="processDesc_" /> <c:if
							test="${not empty object.fileName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.itemId}','${object.version }','processDesc_')">&nbsp;
				<input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.itemId}','${object.version }','processDesc_')">
						</c:if></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.inFlowImg" /></td>
					<td align="left" colspan="3"><s:file name="inFlowImg_" /> <c:if
							test="${not empty object.inFlowImgName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.itemId}','${object.version }','inFlowImg_')">&nbsp;
				<input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.itemId}','${object.version }','inFlowImg_')">
						</c:if></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.applyForm" /></td>
					<td align="left" colspan="3"><s:file name="applyForm_" /> <c:if
							test="${not empty object.formName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.itemId}','${object.version }','applyForm_')">&nbsp;
				<input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.itemId}','${object.version }','applyForm_')">
						</c:if></td>
				</tr>
				
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.applyFormDemo" /></td>
					<td align="left" colspan="3"><s:file name="applyFormDemo_" /> <c:if
							test="${not empty object.applyFormDemo}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.itemId}','${object.version }','applyFormDemo_')">&nbsp;
				<input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.itemId}','${object.version }','applyFormDemo_')">
						</c:if></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.inFlowInfo" /></td>
					<td align="left" colspan="3"><s:textarea name="inFlowInfo"
							cols="40" rows="2" id="inFlowInfo" style="width:600px;height:70px;"
							value="%{object.inFlowXml}" /> &nbsp;<input id="flowedit" type="button" value="编辑" class="btn" onclick="editFlow();" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.condition" /></td>
					<td align="left" colspan="3"><s:textarea name="condition"
							cols="40" rows="2" style="width:100%;height:40px;"  value="%{object.condition}"/></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.entrustName" /></td>
					<td align="left" colspan="3"><s:textfield name="entrustName"
							size="60" value="%{object.entrustName}" /></td>
				</tr>
				
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.question" /></td>
					<td align="left" colspan="3"><s:textarea name="question"
							cols="40" rows="2" style="width:100%;height:40px;" value="%{object.question}"/></td>
				</tr> --%>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.remark" /></td>
					<td align="left" colspan="3"><s:textarea name="remark"
							cols="40" rows="2" style="width:100%;height:40px;" value="%{object.remark}"/></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.lastmodifytime" /></td>
					<td align="left" colspan="3"><s:date name="lastmodifytime"
							format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</table>
		<div class="formButton">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" onclick="return check();"/>
		<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
		</div>
		</s:form>
	</fieldset>
	<script type="text/javascript">
	function checkVersion() {
		 var form=document.getElementById("suppowerForm");
	     form.action="supPower!edit.do";     
	     form.submit();
	}
		function checkItemType() {

			var item_type = document.getElementById("itemType");
			var punishbasiscontent_tr = document.getElementById("punishbasiscontent_tr");
			var punishbasis_tr = document.getElementById("punishbasis_tr");
			
			var punishClass_tr = document.getElementById("punishClass_tr");
			var levyUpon_tr = document.getElementById("levyUpon_tr");
			//var dis_detail_tr = document.getElementById("dis_detail_tr");
			var freeJudge_tr = document.getElementById("freeJudge_tr");
			//var dis_standard_tr = document.getElementById("dis_standard_tr");
			if (item_type.value == 'CF') {
				
				punishbasiscontent_tr.style.display = "block";
				punishbasis_tr.style.display = "block";
				
				punishClass_tr.style.display = "block";
				levyUpon_tr.style.display = "block";
				freeJudge_tr.style.display = "block";
				//dis_detail_tr.style.display = "block";
				//dis_standard_tr.style.display = "block";
			} else {
				
				punishbasiscontent_tr.style.display = "none";
				punishbasis_tr.style.display = "none";
				
				punishClass_tr.style.display = "none";
				levyUpon_tr.style.display = "none";
				freeJudge_tr.style.display = "none";
				//dis_detail_tr.style.display = "none";
				//dis_standard_tr.style.display = "none";
			}
		}
		function inital(v) {

			var item_type = document.getElementById("itemType");

			
			var punishbasiscontent_tr = document.getElementById("punishbasiscontent_tr");
			var punishbasis_tr = document.getElementById("punishbasis_tr");
			
			var punishClass_tr = document.getElementById("punishClass_tr");
			var levyUpon_tr = document.getElementById("levyUpon_tr");
			var freeJudge_tr = document.getElementById("freeJudge_tr");
			if (item_type.value == 'CF') {
				
				punishbasiscontent_tr.style.display = "block";
				punishbasis_tr.style.display = "block";
				
				punishClass_tr.style.display = "block";
				levyUpon_tr.style.display = "block";
				freeJudge_tr.style.display = "block";
				//dis_detail_tr.style.display = "block";
				//dis_standard_tr.style.display = "block";
			} else {
				
				//punishbasiscontent_tr.style.display = "none";
				//punishbasis_tr.style.display = "none";
				
				//punishClass_tr.style.display = "none";
				//levyUpon_tr.style.display = "none";
				//freeJudge_tr.style.display = "none";
				//dis_detail_tr.style.display = "none";
				//dis_standard_tr.style.display = "none";
			}
			if (v == 'XK') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				//chargeStandard_tr.style.display = "none";
				//chargeBasis_tr.style.display = "none";
			}

		}

		function checkReceive(v) {
			var chargeStandard_tr = document
					.getElementById("chargeStandard_tr");
			var chargeBasis_tr = document.getElementById("chargeBasis_tr");

			if (v == '1') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				//chargeStandard_tr.style.display = "none";
				//chargeBasis_tr.style.display = "none";
			}
		}
		function downFile(id,v, fileType) {
			var url = "supPower!downloadStuff.do?itemId=" + id +"&version="+v+ "&fileType="
					+ fileType;
			document.location.href = url;
		}

		function deleteFile(id,v, fileType) {
			var url = "supPower!deleteStuff.do?itemId=" + id +"&version="+v+ "&fileType="
					+ fileType;
			document.location.href = url;
		}
	</script>
<script>


function showZycl(item_id, version){

    var url='supPower!viewzycl.do?itemId='+item_id+"&version="+version;
   
   
    openNewWindow(url); 
}


function showZyclstand(item_id, version){

    var url='supPower!viewcfcx.do?itemId='+item_id+"&version="+version;
    openNewWindow(url); 
}


function editZycl(item_id, version){

    var url='${pageContext.request.contextPath}/page/powerbase/optcommon/zycledittemp.jsp';
    openNewWindow(url, 'right');
}

function editZyclstand(item_id, version){
    var url='${pageContext.request.contextPath}/page/powerbase/optcommon/cfcxedittemp.jsp';
    openNewWindow(url); 
}
function addOrderRow(tab,rowNum,colNum,obj,addType){
	var detailbody=document.getElementById(tab); 
	var row = document.createElement("tr"); 
	var newrow=obj.parentNode.parentNode.innerHTML; 
	if(addType=='add'){
	var row = detailbody.insertRow(); 
	for(var i=0;i<detailbody.getElementsByTagName("TR")[1].childNodes.length;i++){ 
	var cell=row.insertCell();
	//if(i==0)
	//cell.innerHTML=parseInt(detailbody.getElementsByTagName("TR").length-2)+1;
	//else
	cell.innerHTML=detailbody.getElementsByTagName("TR")[1].childNodes[i].innerHTML;

	//如果表单中有值就清空 
	for(var k=0;k<cell.childNodes.length;k++){ 
	if (cell.childNodes[k].type == 'text') { cell.childNodes[k].value = ''; } 
	if (cell.childNodes[k].type == 'textarea') { cell.childNodes[k].value = ''; } 
	if (cell.childNodes[k].type == 'checkbox') { cell.childNodes[k].checked = false; } 
	if (cell.childNodes[k].type == 'radio') { cell.childNodes[k].checked = false; } 
	if (cell.childNodes[k].type == 'select-multiple') { cell.childNodes[k].selectedIndex = -1; } 
	if (cell.childNodes[k].type == 'select-one') { cell.childNodes[k].selectedIndex = ''; } 
	} 
	//cell.innerHTML=arr[i]; 
	} 
	}else if(addType=='copy'){ 
	//copy 
	//detailbody.insertRow().insertCell().innerHTML = newrow;   ok 
	var row = detailbody.insertRow(); 
	for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){ 
	var cell=row.insertCell(); 
	if(i==0)
	cell.innerHTML=parseInt(obj.parentNode.parentNode.childNodes[i].innerHTML)+1;
	else
	cell.innerHTML=obj.parentNode.parentNode.childNodes[i].innerHTML;
	} 
	}else{ 
	//delete 
	if((obj.parentNode.parentNode.parentNode.childNodes.length)!=2){
	if(confirm("是否确定删除此项?")){
	    obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);    
	}else{ 
	return false; 
	} 
	}else{
	alert("此项不可删除,直接填写信息!");
	return false; 
	}
	} 


	} 
	
	
function editFlow(){
	if($.browser.msie&&parseFloat($.browser.version)<9)
		{
	    var url = '${pageContext.request.contextPath}/page/powerbase/innerflow/vml/vmlFlow.html';		
		}
	else
		{
		var url = '${pageContext.request.contextPath}/page/powerbase/innerflow/svg/svgFlow.html';
		}
	openNewWindow(url);
}

/* $(function(){
	if($.browser.msie){
		if(parseFloat($.browser.version)<9){
			$("#flowedit").hide();
		}
	}
}); */


</script>
<script>
function check(){
	if(document.forms[0].itemId.value==""){
		  alert("请填写权利编号");
		  document.forms[0].itemId.focus();
		  return false;
    }
	if(document.forms[0].itemId.value.length > 30){
		  alert("请输入长度不大于30的权力编号！");
		  document.forms[0].itemId.focus();
		  return false;
    }
	if(document.forms[0].outItemId.value.length > 20){
		  alert("请输入长度不大于20的权力外网公示编码！");
		  document.forms[0].outItemId.focus();
		  return false;
    }
	if(document.forms[0].itemName.value==""){
	  alert("请填写权力名称");
	  document.forms[0].itemName.focus();
	  return false;
	  }
	
    if(document.forms[0].orgId.value==""){
      alert("请选择所属部门");
	  document.forms[0].orgId.focus();
	  return false;
	  }
	  
    if(document.forms[0].itemType.value==""){
	  alert("请选择权力类型");
	  document.forms[0].itemType.focus();
	  return false;
	  }	   
    
    if(document.forms[0].optItemType.value==""){
  	  alert("请选择事项类型");
  	  document.forms[0].optItemType.focus();
  	  return false;
  	  }	
    
    if(document.forms[0].optOpenStyle.value==""){
  	  alert("请选择公开方式");
  	  document.forms[0].optOpenStyle.focus();
  	  return false;
  	  }	
    
    if(document.forms[0].qlDepid.value==""){
	  alert("请选择实施主体");
	  document.forms[0].qlDepid.focus();
	  return false;
	  }
    
    if(document.forms[0].qlDepstate.value==""){
  	  alert("请选择实施主体性质");
  	  document.forms[0].qlDepstate.focus();
  	  return false;
  	  }
    
    if(document.forms[0].qlState.value==""){
  	  alert("请选择权力状态");
  	  document.forms[0].qlState.focus();
  	  return false;
  	  }
}
</script>


</body>
</html>
