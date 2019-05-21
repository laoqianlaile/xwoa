<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>请假条</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>请假条</b>
		</legend>
<s:form action="oaLeaveapply"  method="post" namespace="/oa" id="oaLeaveapplyForm"  target="parent" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="s_itemtype" name="s_itemtype" value="${s_itemtype}" />	
			<input type="hidden" id="s_applyItemType" name="s_applyItemType" value="${s_applyItemType}" />	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
				<td class="addTd" >
						标题<span style="color:red;">*</span>
					</td>
					<td align="left"  colspan="5">
						<s:textfield name="transaffairname"  id="transaffairname" style="width:100%;height:30px;"/>
				</td>
				</tr>
				<tr>
				<td class="addTd">
						缓急程度
				</td>
					<td align="left"  colspan="5">
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:27px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
				   </td>	
				</tr>
				<tr>
					<td class="addTd">
						部门 <span style="color:red;">*</span>
					</td>
					<td align="left">
                        <select id="applyuser" name="applyuser" style="height:27px">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${applyuser == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
					
					
					<td class="addTd">
						职位<!-- <span style="color:red;">*</span> -->
					</td>
					<td align="left" >
                           <%-- <span>${cp:MAPVALUE('areacode', Postleve)}</span>    --%>
						<s:textfield name="postleve"  id="postleve" style="width:200px;height:24px;"/>
	
					</td>
					
					<td class="addTd">
						姓名
					</td>
					<td align="left" ><span>${cp:MAPVALUE('usercode', object.creater)}</span></td>
					</td>
				
				</tr>
				
				<tr>
					<td class="addTd">
						请假开始时间<span style="color:red;">*</span>
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd HH:mm"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
			                 <!--  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm ',onpicked:function(dp){calcDays(dp.cal.getDateStr(),'endtime');}})"> -->
					</td>
					
					<td class="addTd">
						请假结束时间<span style="color:red;">*</span>
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="endtime" name="endtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.endtime}"  pattern="yyyy-MM-dd HH:mm"/>'
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">  
			                  <!-- onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:function(dp){calcDays(dp.cal.getDateStr(),'applydate')}})" -->
					</td>
					
					<td class="addTd">
						请假天数
					</td>
					<td align="left" >
                           <!-- <input name="leavenum" id="leavenum" type="button" onclick="btnCount_Click()" /> --> 
						<s:textfield name="leavenum"  id="leavenum" style="width:160px;height:24px;"/> 天
	
					</td>
			
				</tr>
				
				<tr>
					<td class="addTd">
						请假事由<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="5">
					
						<s:textarea name="remarkContent" id="remarkContent" style="width:100%;height:80px;" />
					</td>
				</tr>

			
				
               </table>


			</s:form>
		
		</fieldset>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#transaffairname").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("transaffairname",400);
		});
		$("#remarkContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("remarkContent",4000);
		}); 
		
	});
	
	
	function calcDays(currStrDateVal,otherDateDomId){
		if($("#"+otherDateDomId).val()==''){
			$("#"+otherDateDomId)[0].focus();
		}else{
			var begD = $("#applydate").val();
			var endD = $("#endtime").val();
			if ( begD <= endD) {
				var days = DateDiff(begD,endD);
				$("#leavenum").val(days);
			}else{
				alert("请假结束时间不能早于请假开始时间。");
			}
		}
		
	}
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		if( document.oaLeaveapplyForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaLeaveapplyForm.transaffairname.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("标题超出最大长度限制");
				document.oaLeaveapplyForm.transaffairname.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#remarkContent").val());
		if( totalLen>400){
				alert("请假事由超出最大长度限制");
				document.oaLeaveapplyForm.remarkContent.focus();
				return false;
	    }
		if( document.oaLeaveapplyForm.applyuser.value == ""){
			alert("部门不能为空");
			 document.oaLeaveapplyForm.applyuser.focus();
			return false;
		}
		/* if( document.oaLeaveapplyForm.postleve.value == ""){
			alert("职位不能为空");
			 document.oaLeaveapplyForm.postleve.focus();
			return false;
		} */
		if( document.oaLeaveapplyForm.remarkContent.value == ""){
			alert("请假事由不能为空");
			 document.oaLeaveapplyForm.remarkContent.focus();
			return false;
		} 
		var begD = $("#applydate").val();
		var endD = $("#endtime").val();
		if (endD != "" && begD > endD) {
			alert("请假结束时间不能早于请假开始时间。");
			return false;
		}	 
			return true;
	}
	
	 function IsPhoneNumber(inputVal)
		{ 
		 var str=inputVal;
		 if(str.length!=0){
		   var reg=/(^[0-9]{3,4}\-[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{7,15}$)/;
		   if(!reg.test(inputVal))
		   {
		    return false;
		   }
		   return true;
		 }else{
		   return true;
		 }
		}
	 
			

					   //计算天数差的函数，通用  
					   function  DateDiff(applydate,  endTime){    //applydate和endTime是2006-12-18格式  
					       var  aDate,  oDate1,  oDate2,  iDays  
					       aDate  =  applydate.split("-")  
					       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
					       aDate  =  endTime.split("-")  
					       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
					       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24+1)    //把相差的毫秒数转换为天数  
					       return  iDays  
					   }    		 
	 
	 
	</script>
</html>