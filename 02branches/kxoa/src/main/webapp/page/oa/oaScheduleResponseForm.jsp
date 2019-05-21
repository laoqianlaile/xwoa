<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaScheduleResponse.edit.title" /></title>
</head>

<body class="sub-flow">
<p class="ctitle"><s:text name="oaScheduleResponse.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaScheduleResponse"  method="post" namespace="/oa" id="oaScheduleResponseForm" >

	<input type="button" class="btn" id="save" value="保存"
			onclick="submit1();" />
<%-- 	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" onclick="save()" /> --%>
	 <c:if test="${F eq viewtype}">
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
	 </c:if>
<table width="200" border="0" cellpadding="1" cellspacing="1" >		
       <input type="hidden" id="no" name="no" value="${object.no}" />
        <input type="hidden" id="schno" name="schno" value="${object.schno}" />
        <input type="hidden" id="viewtype" name="viewtype" value="${viewtype}" />
     
				
				<tr id="tr_resType">
					<td class="TDTITLE">
						<s:text name="oaScheduleResponse.resType" />
					</td>
					<td align="left">
						<select id="resType" name="resType" style="width:120px;">
				         	<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oaResType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==object.resType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
	
					</td>
				</tr>
				<tr id="tr_stopTime">
					<td class="TDTITLE">
						<s:text name="oaScheduleResponse.stopTime" />
					</td>
					<td align="left">
				<input type="text" class="Wdate"  id="stopTime" name="stopTime" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期">					
<%-- 	                   <sj:datepicker id="stopTime" --%>
<%-- 						value="%{object.stopTime}" name="stopTime" --%>
<%-- 						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd" --%>
<%-- 						timepickerFormat="hh:mm:ss" timepicker="true" changeYear="true" --%>
<%-- 						readonly="true" /> --%>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaScheduleResponse.remark" />
					</td>
					<td align="left">
  
						<s:textarea name="remark" cols="40" rows="6"/>
	
	
					</td>


</table>


</s:form>
<script type="text/javascript">
$(function(){
	
	//暂缓
	function isShow(){
		var v=$('#resType').find('option:selected').val();
	
		if('2'==v){
			$('#tr_stopTime').show();
		}else{
			$('#tr_stopTime').hide();
		}
		
		
	};
	
	$('#resType').change(function(){
		isShow();
	});
	$('#tr_stopTime').hide();
	isShow();
	
	

	
});
function submit1() {
	if (docheck() == false) {
		return;
	}else {
		var srForm = document.getElementById("oaScheduleResponseForm");
		srForm.action = 'oaScheduleResponse!save.do';
		srForm.submit();
		window.close();
		window.returnValue =true;
		
	}
}
function docheck() {
	
	if ($("#resType").val() == '') {
		alert("响应类型不能为空！");
		$('#resType').focus();
		return false;
	}
}
</script>
