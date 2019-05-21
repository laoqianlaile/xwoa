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
	 
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
<fieldset class="_new">
	<legend>车辆使用记录查看</legend>
	<!-- 
		<div class="legend_left"></div>
		<div class="legend"><b>车辆使用记录查看</b></div>
		<div class="legend_right"></div>
    -->
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
 
				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.djid" />
					</td>
				<td colspan="3">
							<s:property value="%{djid}" />
					</td>
				</tr>
              <tr>
				
					</td>
					<td class="addTd">
					车辆号
					</td>
					<td align="left" >
	         
  
	       <s:property value="%{cardjid}" />
					</td>
					<td class="addTd">
						<s:text name="oaDriverBook.driver" />
					</td>
					<td align="left" >

	       <s:property value="%{driver}" />
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.carno" />
					</td>
					<td align="left">
	
                 <s:property value="%{carno}" />
					
	
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.brand" />
					</td>
					<td align="left">
	           <s:property value="%{brand}" />
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.depno" />
					</td>
					<td align="left">
                     <s:property value="%{depno}" />
					
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.caruser" />
					</td>
					<td align="left">
					  <s:property value="%{caruser}" />
		          

					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.startDate" />
					</td>
					<td align="left" >
						<s:date name="startDate" format="yyyy-MM-dd" />
						
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.backDate" />
					</td>
				<td align="left" >
					<s:date name="backDate" format="yyyy-MM-dd" />
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.startMileage" />
					</td>
					<td align="left">
	          <s:property value="%{startMileage}" />
  
					</td>
		
					<td class="addTd">
						<s:text name="oaDriverBook.endMileage" />
					</td>
					<td align="left">
	  	     <s:property value="%{endMileage}" />
  		  

					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.thisMileage" />
					</td>
					<td align="left">
         	  <s:property value="%{thisMileage}" />
          
	
					</td>
			
					<td class="addTd">
						<s:text name="oaDriverBook.totalCost" />
					</td>
					<td align="left">
						  <s:property value="%{totalCost}" />
	       
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaDriverBook.remark" />
					</td>
					<td align="left"colspan="3">
					  <s:property value="%{remark}" />
	       
					</td>
				</tr>
				
</table>
</fieldset>

	<p>
<%@ include file="/page/oa/common/carViewInfo.jsp"%>
</s:form>

	<script type="text/javascript">
	    
	function openNewWindow(winUrl,winName,winProps){
		if(winProps =='' || winProps == null){
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
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

	
		
    }

    </script>	


</body>
</html>
