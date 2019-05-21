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


<body class="sub-flow" onload="inital('${object.itemType}');">

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="form">
		<legend>
			<s:text name="suppower.edit.title" />
		</legend>

		<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm" enctype="multipart/form-data">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" onclick="return check();"/>
		<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
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


					<td class="addTd" >事项类型<font color='red'>*</font></td>
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
					<td class="addTd"  width="130">事项分类<font color='red'>*</font></td>
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
				 <tr>
					<td class="addTd">实施依据</td>
					<td align="left" colspan="3"><s:textfield name="actualDependent"
							style="width:100%;height:40px;" value="%{object.actualDependent}" /></td>
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
		  alert("请填写事项编号");
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
