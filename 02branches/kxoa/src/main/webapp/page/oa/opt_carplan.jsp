<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script type="text/javascript">
function setHeight(h){
	window.parent.document.getElementById("transFrame").style.height = h+"px";
}
 $(function(){
     var begtime='${object.planBegTime}'.substring(0, 19);
     var endtime ='${object.planEndTime}'.substring(0, 19);
     if($("#doBegTime").val()==''&&$("#doEndTime").val()==''){
	     $("#doBegTime").val(begtime);
		 $("#doEndTime").val(endtime);
	 }
     var flag=$("#cardjid").val();
     if(flag==''){
    	 document.getElementById("optCarplan").style.display = "none";
    	 document.getElementById("optCarplanbrand").style.display = "none";
     }else{
    	 document.getElementById("optCarplan").style.display = "table-row";
    	 document.getElementById("optCarplanbrand").style.display = "table-row"; 
    	 
     }
  
 });
 </script>
	<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
</head>
<body class="sub-flow">
<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="_new">
		<legend>
		车辆申请&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</legend>

<s:form action="oaCarApply"  method="post" namespace="/oa" id="oaCarApplyForm" target="_parent" onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />		
        <input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
        <input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
        <input type="hidden" id="flowCode" name="flowCode"  value="000858" />
         <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
        <div id="getIframeHeight">
           <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
           
                      <!-- 			办理意见		 -->
						<c:if test="${moduleParam.hasIdea eq 'T' }">
							<tr id="tr_ideacode">
								<td class="addTd" style="border-bottom: 0px">${moduleParam.ideaLabel}<font
									color="red">*</font></td>
								<td align="left" colspan="3" style="border-bottom: 0px">
								<input type="hidden" name="ideacode" value="${optProcInfo.ideacode}" id="ideacode">
								
								<input type="hidden"
									name="optProcInfo.transidea" value="" id="transidea"> <!--通用配置  -->
									<c:forEach var="row"
										items="${cp:DICTIONARY(moduleParam.ideaCatalog)}"
										varStatus="status">
										<input type="radio" name="optProcInfo.ideacode"
											method="_getSelectedItemLabel" value="${row.key}"
											lable="${row.value}"
											${(optProcInfo.ideacode eq row.key or (empty optProcInfo.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''} />
										<c:out value="${row.value}" />
									</c:forEach> &nbsp;&nbsp;</td>
							</tr>
						</c:if>

						<c:if test="${moduleParam.hasIdea ne 'T'}">
							<input type="hidden" name="optProcInfo.ideacode" value="${optProcInfo.ideacode}"
								id="ideacode">
						</c:if>
				<%-- <tr>
					<td class="addTd">
						安排时间<span style="color:red">*</span>
					</td>
					 <td align="left" colspan="3">
						<sj:datepicker id="doTime" value="%{object.doTime}"
							name="doTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true" changeYear="true" readonly="true"/>
					<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doTime" name="doTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					
					
					</td>
				
				</tr> --%>
    
				
				<%-- <tr>
					<td class="addTd">
						安排部门
					</td>
					 <td align="left" >
						<s:textfield name="doDepno"  value="%{object.doDepno}" id="doDepno"/>
					</td>
						<td class="addTd">
						派车人
					</td>
					 <td align="left" >
						<s:textfield name="doCreater"  value="%{object.doCreater}" id="doCreater" />
					</td>
				</tr> --%>
			    <tr>
					<%-- <td class="addTd">
						车辆号<span style="color:red">*</span>
					</td>
					 <td align="left" >
						<s:textfield name="cardjid"  value="%{object.cardjid}" id="cardjid"/>
						<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaCarinfo!selectList.do?','powerWindow',null);" value="选择">
					</td> --%>
					
					
		<tr class="agreecontent">		
		<td class="addTd"> 	
		用车来源<font color="red">*</font></td>
		 <td align="left" colspan="3">
 		 <input type="radio" name="range_type" value="W" checked onclick="doChangeRange();">外租车
		 <input type="radio" name="range_type" value="N" onclick="doChangeRange();">内部车
		 </td>
		 </tr>
		 
                   <tr class="agreecontent_">

					<td class="addTd">
						车牌号
					</td>
					 <td align="left" colspan="3">
					 
					    <input type="hidden" id="cardjid" name="cardjid" value="${object.cardjid }" />
						<s:textfield name="carno"  value="%{object.carno}" id="carno" />
						<input type="button" class='btn' name="powerBtn" id="waizu" style="display:none;" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaCarinfo!outselectList.do?','powerWindow',null);change();" value="选择" >
						<input type="button" class='btn' name="powerBtn2" id="neibu" style="display:none;" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaCarinfo!selectList.do?','powerWindow',null);change();" value="选择">
					</td>
					</tr>
					<tr id="optCarplan" class="agreecontent_">
					<td class="addTd">
						司机
					</td>
					 <td align="left" >
						<s:textfield name="driver"  value="%{object.driver}" id="driver"/>
					</td>
						<td class="addTd">
						司机电话
					</td>
					 <td align="left" >
						<s:textfield name="drTelephone"  value="%{object.drTelephone}" id="drTelephone" />
					</td>
					</tr>
					<tr id="optCarplanbrand" class="agreecontent_">
					<td class="addTd">
						品牌
					</td>
					 <td align="left" >
					<%--    <input type="hidden" id="brand" name="brand" value="${object.brand }" /> --%>
						<%-- <span id="spanbrand">${object.brand}</span>  --%>
						<s:textfield name="brand" id="brand" value="%{object.brand}"/>
					</td>
						<td class="addTd">
						车型
					</td>
					 <td align="left" >
					   <s:textfield name="modelType"  value="%{object.modelType}" id="modelType"/>
					    <%-- <input type="hidden" id="modelType" name="modelType" value="${object.modelType }" /> --%>
					<%-- 	<s:textfield name="modelType" id="modelType" value="${object.modelType }" /> --%>
						<%-- <span id="spanmodelType">${object.modelType}</span>  --%>
					</td>
					</tr>
					<tr class="agreecontent_">
					<td class="addTd">
						安排开始时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					 				<input type="text" class="Wdate" style="width:150px;height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doBegTime" name="doBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					<%-- 
						<sj:datepicker id="doBegTime" value="%{object.doBegTime}"
							name="doBegTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true" changeYear="true" readonly="true"/> --%>
					</td>
						<td class="addTd">
						安排结束时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					 	<input type="text" class="Wdate" style="width:150px;height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doEndTime" name="doEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					<%-- 	<sj:datepicker id="doEndTime" value="%{object.doEndTime}"
							name="doEndTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true" changeYear="true" readonly="true"/> --%>
					</td>
					</tr>
				    <tr>
					<td class="addTd">
						安排备注
					</td>
					 <td align="left" colspan="3">
						<s:textarea name="doRemark" style="width:100%;height:50px;" id="doRemark" />
					</td>			
					</tr>			  			
</table>
<div class="agreecontent">
						<font color="blue">温馨提示</font>：<br>

					<font color="blue"> 1、用车来源为外租车的，支持手工输入或选择历史外租车记录；<br>
					 2、用车来源为内部车的，须从内部车辆列表中选择。</font>
</div>
	<center style="margin-top: 10px;">
		<span style="display:none;" >
			<s:submit name="saveAndSubmit" method="submitdoMeeting" cssClass="btn" value="提 交" id="submitBtn"/>
		</span>
		<span style="display:none;" >	
			<s:submit name="save" method="savedoMeeting" cssClass="btn" value="保 存" id="saveBtn"/>
		</span>	
		<span style="display:none;" >	
			<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);" id="backBtn"/>	
		</span>
		</center>
	</div>
</s:form>

</fieldset>
</body>
<script type="text/javascript">	

$(document).ready(function() {

	initCommon();
$('input:radio[name="optProcInfo.ideacode"]').click(function(e){
	initCommon();
	});
});
/*
 * 页面元素逻辑控制
 */
function  initCommon(){
	var ideacode = $.trim($('input:radio[name="optProcInfo.ideacode"]:checked').val()); //取radio
	$("#transidea").val($.trim($('input:radio[name="optProcInfo.ideacode"]:checked').attr("lable")));
	$("#ideacode").val(ideacode);
	var range_type = $.trim($('input:radio[name="range_type"]:checked').val()); //取radio
	
	
	
	//是否同意派车
	if(ideacode=='T' ){
		if($(".agreecontent")){
			$(".agreecontent").show();
		}	
		
		if(range_type=='W'||range_type=='N'){
			if($(".agreecontent_")){
				$(".agreecontent_").show();
			}
			
			//根据用车来源联动出现选择框
			if(range_type=='W'){
				$("#waizu").show();
				$("#neibu").hide();
			 }if(range_type=='N'){
				$("#neibu").show();
				$("#waizu").hide();
			 }
		}
	}else{
		if($(".agreecontent")){
			$(".agreecontent").hide();
		}
		if($(".agreecontent_")){
			$(".agreecontent_").hide();
		}
	  }
	};

	function doChangeRange(){
		var range_type = $.trim($('input:radio[name="range_type"]:checked').val()); //取radio
		//根据用车来源联动出现选择框
		if(range_type=='W'){
			$("#waizu").show();
			$("#neibu").hide();
		 }if(range_type=='N'){
			$("#neibu").show();
			$("#waizu").hide();
		 }
		 
	clearFormValues();		
	}
	
	function clearFormValues(){
		$("#cardjid").val('');
		$("#carno").val('');
		$("#driver").val('');
		$("#drTelephone").val('');
		$("#brand").val('');
		$("#modelType").val('');
	}
	
function openNewWindow(winUrl,winName,winProps){
	
		
	if(winProps =='' || winProps == null){
		winProps = 'height=600px,width=800px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}



	function docheckBase() {
		var flag =true;
		var ideacode = $.trim($('input:radio[name="optProcInfo.ideacode"]:checked').val()); //取radio
		if("T"==ideacode){
			
			var range_type = $.trim($('input:radio[name="range_type"]:checked').val()); //取radio
			if(range_type=='N'){
				if ($("#cardjid").val() == '') {
					alert("请选择内部车辆！");
					$('#cardjid').focus();
					flag = false;
					return flag;
				}				
			}
			if ($("#carno").val() == '') {
				alert("车牌号不能为空！");
				$('#carno').focus();
				flag = false;
				return flag;
			}
			
			if($("#doTime").val()==''){
				alert("安排时间不可为空！");
				$('#doTime').focus();
				flag = false;
				return flag;
			}	

	        if ($("#doBegTime").val() == '') {
				alert("开始时间不能为空！");
				$('#doBegTime').focus();
				flag = false;
				return flag;
			}
	        
			
			
			if ($("#doEndTime").val() == '') {
				alert("结束时间不能为空！");
				$('#doEndTime').focus();
				flag = false;
				return flag;
			}
			
			var date=new Date();
			if ($("#doBegTime").val() != '') {
			var dt = new Date($("#doBegTime").val().replace(/-/,"/"));  
			if (dt <date) {
				alert("所选时间发生在过去，请重新选择？");
				$('#doBegTime').focus();
				flag = false;
				return flag;
			   }
			}
			
			if ($("#doEndTime").val() != '') {
				var dt = new Date($("#doEndTime").val().replace(/-/,"/"));  
				if (dt <date) {
					alert("所选时间发生在过去，请重新选择？");
					$('#doEndTime').focus();
					flag = false;
					return flag;
				   }
				}
				
			if($("#doBegTime").val()>$("#doEndTime").val()){
				alert("开始时间不能大于结束时间！");
				$('#doBegTime').focus();
				flag = false;
				return flag;
			}	

			
		}else{
			flag = true;
		}
		return flag;
	}
	
	
	
	function checkForm() {
		var ideacode = $.trim($('input:radio[name="optProcInfo.ideacode"]:checked').val()); //取radio
		var range_type = $.trim($('input:radio[name="range_type"]:checked').val()); //取radio
		if(docheckBase() == true){
			var flag = true;
			var replaceflag=true;
			if("T"==ideacode && range_type=='N'){//同意安排  并且是内部有车情况 做验证
			$.ajax({
				type : "POST",
				async: false,
				dataType : "json",
				url : "oaCarApply!isTFree.do?djId="+$("#djId").val() +"&doBegTime="+$("#doBegTime").val()+"&doEndTime="+$("#doEndTime").val()+"&cardjid="+$("#cardjid").val(),
				success : function(json) {
					if(!json){
						$('#doBegTime').focus();
						flag = false;
						
						
						
						
					}
				},
				error : function() {
					alert("失败");
					flag = false;
				}
			});
			if(!flag){
				$.ajax({
					type : "POST",
					async: false,
					dataType : "json",
					url : "oaCarApply!isTReplace.do?djId="+$("#djId").val() +"&doBegTime="+$("#doBegTime").val()+"&doEndTime="+$("#doEndTime").val()+"&cardjid="+$("#cardjid").val(),
					success : function(json) {
						if(!json){
							$('#doBegTime').focus();
							replaceflag = false;
						}
					},
					error : function() {
						alert("失败");
						replaceflag = false;
					}
				});
				if(!replaceflag){
					alert("该车辆在安排时间段正在使用，回车前不可以被再次派车！");
					return false;
				}else{
					if(window.confirm("该车辆在安排时间段已被占用,是否确认提交!")){
	    				return true;
	    			}else{
	    				return false;
	    			}
				}
    		   }
			
			}
			else{
    			return flag;
    		}	
		}else{
    		return false;
    	}
		
	} 
	var _get = function (id) {
		return document.getElementById(id);
	};
	function change(){
		setHeight( parseInt(_get("getIframeHeight").offsetHeight)+115 );
	};
</script>
</html>