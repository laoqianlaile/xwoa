<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("GBK");
%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="suppower.edit.title" /></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
</head>


<body onload="inital('${object.itemType}');checkReceive(${object.ischarge});">

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			<s:text name="suppower.edit.title" />
		</legend>

		<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm" enctype="multipart/form-data">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
		<input type="hidden" id="version" name="version"  value="${param.version}" /> 
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd" ><s:text name="suppower.itemId" /></td>
					<td align="left" ><c:if
							test="${empty object.itemId}">
							<s:textfield name="itemId" size="40" value="%{object.itemId}" />
							<span style="color: red">*</span>
						</c:if> <c:if test="${not empty object.itemId}">
							<input type="hidden" id="itemId" name="itemId"
								value="${object.itemId}" />
							<s:property value="%{object.itemId}" />
						</c:if></td>
						<td class="addTd" width="130">版本号<font color='red'>*</font></td>
					<td align="left">
					<select name="version_bg" style="width: 200px;height:25px;" onchange="checkVersion();">
					<s:iterator value="versionList" id="Id"> 
								<option value="${Id}" <c:if test="${version_bg eq Id}">selected="selected"</c:if>>
									<c:out value="${Id}" />
								</option>
					</s:iterator>
					</select><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.itemName" /></td>
					<td align="left" colspan="3"><s:textfield name="itemName"
							style="width:97%;" value="%{object.itemName}" /></td>
				</tr>
				
				<tr>
					<td class="addTd" width="130">所属部门<font color='red'>*</font></td>
					<td align="left"><select name="orgId">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList}">       
							<option value="<c:out value='${row.depno}'/>"
							<c:if test="${row.depno==object.orgId}">selected="selected"</c:if>>   
								<c:out value="${row.unitname}"/>   
							</option>       
					</c:forEach> 
					</select></td>


					<td class="addTd" width="130"><s:text name="suppower.itemType" /><font color='red'>*</font></td>
					<td align="left"><select name="itemType" style="width: 180px"
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
					<td class="addTd" width="130"><s:text name="suppower.qlDepid" /><font color='red'>*</font></td>
					<td align="left"><select name="qlDepid" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList}">       
						<option value="<c:out value='${row.depno}'/>"
						<c:if test="${row.depno==object.qlDepid}">selected="selected"</c:if>>   
							<c:out value="${row.unitname}"/>   
						</option>       
					</c:forEach> 
					</select></td>
					<td class="addTd" width="130"><s:text
							name="suppower.qlDepstate" /><font color='red'>*</font></td>
					<td align="left"><select name="qlDepstate"
						style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('QL_DepState')}">
								<option value="${row.key}"
									<c:if test="${object.qlDepstate eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.timeLimit" /></td>
					<td align="left"><s:textfield name="timeLimit" size="20"
							value="%{object.timeLimit}" /> <select name="promiseType"
						style="width: 80px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('Promise_Type')}">
								<option value="${row.key}"
									<c:if test="${object.promiseType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd" width="130"><s:text
							name="suppower.legalTimeLimit" /></td>
					<td align="left"><s:textfield name="legalTimeLimit" size="20"
							value="%{object.legalTimeLimit}" /> <select name="anticipateType"
						style="width: 80px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('Promise_Type')}">
								<option value="${row.key}"
									<c:if test="${object.anticipateType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.isNetwork" /></td>
					<td align="left"><s:radio name="isNetwork"
							list="#{'1':'是','0':'否'}" value="%{object.isNetwork}"
							listKey="key" listValue="value" /></td>

					<td class="addTd" width="130"><s:text
							name="suppower.isFormula" /></td>
					<td align="left"><s:radio name="isFormula"
							list="#{'1':'是','0':'否'}" value="%{object.isFormula}"
							listKey="key" listValue="value" /></td>
				</tr>


				<tr>
					<td class="addTd" width="130"><s:text name="suppower.phone" /></td>
					<td align="left"><s:textfield name="phone" size="40"
							value="%{object.phone}" /></td>
					<td class="addTd" width="130"><s:text
							name="suppower.monitorPhone" /></td>
					<td align="left"><s:textfield name="monitorPhone" size="40"
							value="%{object.monitorPhone}" /></td>
				</tr>

				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.acceptLink" /></td>
					<td align="left"><s:textfield name="acceptLink" size="40"
							value="%{object.acceptLink}" /></td>
					<td class="addTd" width="130"><s:text name="suppower.qlState" /><font color='red'>*</font></td>
					<td align="left"><select name="qlState" style="width: 180px">
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
					<td class="addTd" width="130"><s:text
							name="suppower.transactDepname" /></td>
					<td align="left" colspan="3"><s:textarea
							name="transactDepname" cols="40" rows="2"
							style="width:100%;height:40px;" value="%{object.transactDepname}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.address" /></td>
					<td align="left" colspan="3"><s:textfield name="address"
							size="60" value="%{object.address}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.ischarge" /></td>
					<td align="left" colspan="3"><s:radio name="ischarge"
							list="#{'1':'是','0':'否'}" onclick="checkReceive(this.value);"
							listKey="key" listValue="value" /></td>
				</tr>
				<tr id="chargeBasis_tr">
					<td class="addTd" width="130"><s:text
							name="suppower.chargeBasis" /></td>
					<td align="left" colspan="3"><s:textarea name="chargeBasis"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.chargeBasis}" /></td>
				</tr>
				<tr id="chargeStandard_tr">
					<td class="addTd" width="130"><s:text
							name="suppower.chargeStandard" /></td>
					<td align="left" colspan="3"><s:textarea name="chargeStandard"
							cols="40" rows="2" style="width:100%;height:40px;"
							value="%{object.chargeStandard}" /></td>
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
							name="suppower.inFlowInfo" /></td>
					<td align="left" colspan="3"><s:textarea name="inFlowInfo"
							cols="40" rows="2" style="width:600px;height:40px;"
							value="%{object.inFlowInfo}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.condition" /></td>
					<td align="left" colspan="3"><s:textarea name="condition"
							cols="40" rows="2" style="width:100%;height:40px;" value="%{object.condition}"/></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="suppower.entrustName" /></td>
					<td align="left" colspan="3"><s:textfield name="entrustName"
							size="60" value="%{object.entrustName}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.srvDirectory" /></td>
					<td align="left" colspan="3"><s:textarea name="srvDirectory"
							cols="40" rows="2" style="width:100%;height:40px;" value="%{object.srvDirectory}"/></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.question" /></td>
					<td align="left" colspan="3"><s:textarea name="question"
							cols="40" rows="2" style="width:100%;height:40px;" value="%{object.question}"/></td>
				</tr>
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
				
				punishbasiscontent_tr.style.display = "none";
				punishbasis_tr.style.display = "none";
				
				punishClass_tr.style.display = "none";
				levyUpon_tr.style.display = "none";
				freeJudge_tr.style.display = "none";
				///dis_detail_tr.style.display = "none";
				//dis_standard_tr.style.display = "none";
			}
			if (v == 'XK') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				chargeStandard_tr.style.display = "none";
				chargeBasis_tr.style.display = "none";
			}

		}

		function checkReceive(v) {
			var chargeStandard_tr = document
					.getElementById("chargeStandard_tr");
			var chargeBasis_tr = document.getElementById("chargeBasis_tr");

			if (v == 'XK') {
				chargeStandard_tr.style.display = "block";
				chargeBasis_tr.style.display = "block";
			} else {
				chargeStandard_tr.style.display = "none";
				chargeBasis_tr.style.display = "none";
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
</script>


</body>
</html>
