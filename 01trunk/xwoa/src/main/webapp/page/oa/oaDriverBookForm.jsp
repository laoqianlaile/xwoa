<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>

<title><s:text name="oaDriverBook.edit.title" /></title>
</head>

<body class="sub-flow">
<p class="ctitle"><s:text name="oaDriverBook.edit.title" /></p>

	<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaDriverBook"  method="post" namespace="/oa" id="oaDriverBookForm" >
	   <input type="button" class="btn"  value="保存" onclick="submitItemFrame('SAVE');"/>
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
	  <s:hidden name="edittype" value="%{edittype}"></s:hidden>
	<fieldset>
		<legend>	
		车辆使用记录
		</legend>

 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
 
				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.djid" /><font color='red'>*</font>
					</td>
				<td colspan="3">
					<c:if test="${not empty object.djid}">
						 ${object.djid}
						 <input type="hidden" id="djid" name="djid" value="${object.djid}" />
						 </c:if>
						<c:if test="${empty object.djid}">
						<s:textfield id="djid" name="djid" value="%{object.djid}" style="width:400px;" readonly="readonly"></s:textfield>
  					
  						<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaCarApply!selectlist.do?','powerWindow',null);" value="选择">
					</c:if>
					</td>
				</tr>
              <tr>
				
					</td>
					<td class="addTd">
					车辆号
					</td>
					<td align="left" >
	           <s:textfield name="cardjid"  id="cardjid" value="%{object.cardjid}" />
            
	
					</td>
					<td class="addTd">
						<s:text name="oaDriverBook.driver" />
					</td>
					<td align="left" >
	           <s:textfield name="driver"  id="driver" value="%{object.driver}" />
  
	
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.carno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="carno"  id="carno" value="%{object.carno}" />
	
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.brand" />
					</td>
					<td align="left">
	
  	               <s:textfield name="brand"  id="brand" value="%{object.brand}" />
						
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.depno" />
					</td>
					<td align="left">

						<s:textfield name="depno" id="depno" value="%{object.depno}"/>
	
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.caruser" />
					</td>
					<td align="left">
		             <s:textfield name="caruser" id="caruser" value="%{object.caruser}"/>

					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.startDate" />
					</td>
					<td align="left" >		
			<input type="text" class="Wdate"id="startDate" 
			style="height: 25px; width: 138px; border-radius: 3px; border: 1px solid #cccccc;" 
			value='<fmt:formatDate value="${object.startDate}" pattern="yyyy-MM-dd HH:mm"/>' 
			name="startDate" 
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" 
			placeholder="选择日期">				
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.backDate" />
					</td>
				<td align="left" >	
			<input type="text" class="Wdate"id="backDate" 
			style="height: 25px; width: 138px; border-radius: 3px; border: 1px solid #cccccc;" 
			value='<fmt:formatDate value="${object.backDate}" pattern="yyyy-MM-dd HH:mm"/>' 
			name="backDate" 
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm '})" 
			placeholder="选择日期">						
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.startMileage" /><font color='red'>*</font>
					</td>
					<td align="left">
	
  
						<s:textfield name="startMileage"   id="startMileage" onkeyup='this.value=this.value.replace(/\D/gi,"")' value="%{object.startMileage}"/>
	
					</td>
		
					<td class="addTd">
						<s:text name="oaDriverBook.endMileage" /><font color='red'>*</font>
					</td>
					<td align="left">
	
  		   <s:textfield name="endMileage"   id="endMileage" value="%{object.endMileage}" onkeyup='this.value=this.value.replace(/\D/gi,"")' onblur="getVal(this.id)"/>

					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.thisMileage" />
					</td>
					<td align="left">
	
             <s:textfield name="thisMileage"   id="thisMileage" value="%{object.thisMileage}"  readonly="true"/>
	
	
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.totalCost" />
					</td>
					<td align="left">
	        <s:textfield name="totalCost"   id="totalCost" value="%{object.totalCost}"/>
  
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.remark" />
					</td>
					<td align="left"colspan="3">
					<s:textarea id="remark" name="remark" value="%{object.remark}" style="width:100%;height:50px;" />
 
					</td>
				</tr>
				
</table>
</fieldset>
	  <s:hidden name="edittype" value="%{edittype}"></s:hidden>
	  <c:if test="${ edittype eq 'F' }">
<c:if test="${not empty object.djid}">
<p>
	<fieldset>
		<legend style="width: auto; margin-bottom: 10px;">	
		新增子记录
		</legend>
		  <div >
		          <input type="button" class='btn' id="sub1" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaCostInfo!built.do?djid=${object.djid} ','powerWindow',null);" value="费用明细" />
		
		          <input type="button" class='btn' id="sub2" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaAccidentRecord!built.do?djid=${object.djid} ','powerWindow',null);" value="事故记录" />
	
		          <input type="button" class='btn' id="sub3" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaTrafficRecord!built.do?djid=${object.djid} ','powerWindow',null);" value="违章记录" />
		         
			 </div>
	</fieldset>
	</c:if>
	</c:if>
	<p>
<%@ include file="/page/oa/common/carViewInfo.jsp"%>
</s:form>

	<script type="text/javascript">
	    
	function openNewWindow(winUrl,winName,winProps){
		if(winProps =='' || winProps == null){
			winProps = 'height=600px,width=900px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	function submitItemFrame(subType){
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaDriverBookForm");
		if(subType == 'SAVE'){
		 	srForm.action = 'oaDriverBook!savePermitReg.do';
		}

	
		srForm.submit();
		}
}
	
	
	function docheck() {
		if($("#djid").val()==''){
			alert("车辆申请ID不可为空！");
			$('#djid').focus();
			return false;

		}	
		//定义正则表达式部分 数字验证
	
	    if( $("#startMileage").val()!='' ){
	    
	    	 if(isNaN($("#startMileage").val())){
	    		 alert(12);
	    			alert("请输入数字！");
	    			$('#startMileage').focus();
	    			return false; 
	    	 }
	
	    }
	    if( $("#endMileage").val()!='' ){
	    	 if(isNaN($("#endMileage").val())){
	    			alert("请输入数字！");
	    			$('#endMileage').focus();
	    			return false; 
	    	 }
	
	    }	  
	    if( $("#endMileage").val()!='' ){
	    	 if(isNaN($("#endMileage").val())){
	    			alert("请输入数字！");
	    			$('#endMileage').focus();
	    			return false; 
	    	 }
	
	    }

	
        return true;
        
    
		
    }
	function getVal(){
	    if( $("#endMileage").val()<$("#startMileage").val()){
    		alert("结束公里数不能小于起始公里数！");
    		$('#endMileage').focus();
    		return false; 

    }
    		var num=$("#endMileage").val()-$("#startMileage").val();
    		document.getElementById("thisMileage").value=num;
    	   
    	
    }


	function openNewWindow(winUrl,winName,winProps){
		if(winProps =='' || winProps == null){
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}

    </script>	


</body>
</html>
