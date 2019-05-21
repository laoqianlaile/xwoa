<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>

<title><s:text name="oaCostInfo.edit.title" /></title>
</head>

<body class="sub-flow">
<p class="ctitle"><s:text name="oaCostInfo.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaCostInfo"  method="post" namespace="/oa" id="oaCostInfoForm" >
<%-- 	<input type="hidden" id="show_type" name="show_type" value="${show_type}" />
	<input type="hidden" id="edittype" name="edittype" value="${edittype}" /> --%>
	  <input type="button" class="btn"  value="保存" onclick="submitItemFrame('SAVE');"/>
		<input type="button" class="btn" value="关闭" onclick="window.close();">
		 <input type="hidden" id="no" name="no" value="${object.no}" />
		 <fieldset>
		<legend style="width: auto; margin-bottom: 10px;">	
		费用明细
		</legend>
		 
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
	

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.djid" />
					</td>
					<td align="left">
	 <input type="hidden" id="djid" name="djid" value="${object.djid}" />
                 ${object.djid} 
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.costType" /><font color='red'>*</font>
					</td>
					<td align="left">
	
						<s:textarea name="costType" id="costType" style="width:100%;height:50px;"/>
					
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.thecost" /><font color='red'>*</font>
					</td>
					<td align="left">
					
					
						<s:textfield name="thecost" id="thecost" size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.remark" />
					</td>
					<td align="left"colspan="3">
	
                   <s:textarea name="remark" id="remark" style="width:100%;height:50px;"/>
					
	
					</td>
				</tr>	
</table>
</fieldset>
</s:form>
</body>
<script type="text/javascript">
function submitItemFrame(subType){
	if(docheck()==false){
		return;
	}else{
	var srForm  = document.getElementById("oaCostInfoForm");
	if(subType == 'SAVE'){
		srForm.action = 'oaCostInfo!savePermitReg.do';
	}
	srForm.submit();
	window.close();
	}
}


function docheck() {
	if($("#costType").val()==''){
		alert("费用类型不可为空！");
		$('#costType').focus();
		
		return false;
	}	
	if($("#thecost").val()==''){
		alert("金额不可为空！");
		$('#thecost').focus();
		
		return false;
	}	

	
}

</script>
</html>
