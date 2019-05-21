<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>设备购置（维修）</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>设备购置（维修）信息</b>
		</legend>
<s:form action="oaEquipmentpurchase"  method="post" namespace="/oa" id="oaEquipmentpurchaseForm"  target="parent" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="s_itemtype" name="s_itemtype" value="${s_itemtype}" />	
			<input type="hidden" id="s_applyItemType" name="s_applyItemType" value="${s_applyItemType}" />	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						类别
					</td>
					<td align="left" >
                        
						<select id="itentype" name="itentype" style="height:27px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('oaitentype')}">
								<option value="${row.key}" ${(optBaseInfo.itentype eq row.key or (empty optBaseInfo.itentype and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>	 
                          
						<%-- <s:textfield name="itentype"  id="itentype" style="width:100%;height:24px;"/> --%>
	
					
			
					<td class="addTd">
						设备类别
					</td>
					<td align="left">
	                   <s:textfield name="ecategory"  id="ecategory" style="width:100%;height:24px;"/>
					</td>
				</tr>
				
				<tr>
				<td class="addTd">
						缓急程度
					</td>
					<td align="left" colspan="3">
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
						型号
					</td>
					
					<td align="left" >
					<s:textfield name="tmodel" id="tmodel" style="width:100%;height:24px;" />
					</td>
				
					<td class="addTd">
						数量
					</td>
					<td align="left">
					<s:textfield name="thenumber" id="thenumber" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						价格
					</td>
					
					<td align="left" >
					<s:textfield name="theprice" id="theprice" style="width:96%;height:24px;" />元
					</td>
				
					<td class="addTd">
						设备编号
					</td>
					<td align="left">
					<s:textfield name="serialnumber" id="serialnumber" style="width:100%;height:24px;" />
					</td>
				</tr>
			
					
				
				<tr>
				<td class="addTd">
						申请时间
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				
					<td class="addTd">
						申请部门
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
				</tr>
				<tr>
				<td class="addTd">
						购置（维修）<br>理由及用途<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remark" id="remark" style="width:100%;height:80px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						备注说明
					</td>
					<td align="left" colspan="3">
					
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
		$("#remark").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("remark",4000);
		}); 
		
		
	});
	
	
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		if( document.oaEquipmentpurchaseForm.transaffairname.value == ""){
			alert("标题不能为空");
			 document.oaEquipmentpurchaseForm.transaffairname.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("标题超出最大长度限制");
				document.oaEquipmentpurchaseForm.transaffairname.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#tmodel").val());
		if( totalLen>100){
				alert("型号超出最大长度限制");
				document.oaEquipmentpurchaseForm.tmodel.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#theprice").val());
		if( totalLen>12){
				alert("价格超出最大长度限制");
				document.oaEquipmentpurchaseForm.theprice.focus();
				return false;
	    }
		if( document.oaEquipmentpurchaseForm.theprice.value != ""){
			if(!num.test( document.oaEquipmentpurchaseForm.theprice.value)){
				alert("价格格式不正确");
				$("#theprice").val('');
				document.oaEquipmentpurchaseForm.theprice.focus();
				return false;
			}
        }
		if( document.oaEquipmentpurchaseForm.thenumber.value != ""){
			if(!num.test( document.oaEquipmentpurchaseForm.thenumber.value)){
				alert("数量格式不正确");
				$("#thenumber").val('');
				document.oaEquipmentpurchaseForm.thenumber.focus();
				return false;
			}
        }
		totalLen = CommonUtils.checkLength($("#remark").val());
		if( totalLen>4000){
				alert("购置（维修）理由及用途超出最大长度限制");
				document.oaEquipmentpurchaseForm.remark.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#remarkContent").val());
		if( totalLen>4000){
				alert("备注说明超出最大长度限制");
				document.oaEquipmentpurchaseForm.remarkContent.focus();
				return false;
	    }
			  
		if( document.oaEquipmentpurchaseForm.remark.value == ""){
			alert("购置（维修）理由及用途不能为空");
			 document.oaEquipmentpurchaseForm.remark.focus();
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
	</script>
</html>