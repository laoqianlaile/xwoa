<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>耗材配件购置信息</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>耗材配件购置信息</b>
		</legend>
<s:form action="oaConsumablesParts"  method="post" namespace="/oa" id="oaConsumablesPartsForm"  target="parent" >
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
						申请标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
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
						缓急程度
					</td>
					<td align="left" >
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
						申请人
					</td>
					<td align="left" >
                              
						<%-- <s:textfield name="applyuser"  id="applyuser" style="width:100%;height:24px;"/> --%>
						<span>${cp:MAPVALUE('usercode', Creater)}</span>
	
					</td>
			
			
					<td class="addTd">
						申请人所在部门
					</td>
					<td align="left">
                        <select id="unitcode" name="unitcode" style="height:27px">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${unitcode == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
				</tr>
		
				<tr>
					<td class="addTd">耗材/配件</td>
					<td align="left" colspan="3" style="width: 170px;" >
					<select name="sbtype" id="sbtype" onchange="sbtypeChanged(this.value);" style="width: 75px;height:27px">
					<option value="1" <c:if test="${sbtype eq 1 }">selected="selected"</c:if> >耗材</option>
					<option value="2" <c:if test="${sbtype eq 2 }">selected="selected"</c:if> >配件</option>
					</select>
					</td>
				</tr>

				<tr id="hc_td" <c:if test="${sbtype eq 2 and not empty sbtype}">style="display: none;"</c:if>>
					<td class="addTd">
						耗材内容<span style="color:red;">*</span>
					</td>
					<td align="left" >
					
						<s:textfield name="consContent" id="consContent" style="width:100%;height:60px;" />
					</td>
					<td class="addTd">
						耗材数量
					</td>
					
					<td align="left" >
					<s:textfield name="consNum" id="consNum" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr id="pj_td" <c:if test="${sbtype eq 1 or empty sbtype}">style="display: none;"</c:if>>
					<td class="addTd">
						配件内容<span style="color:red;">*</span>
					</td>
					<td align="left" >
					<s:textfield name="partsContent" id="partsContent" style="width:100%;height:60px;" />
					</td>
					<td class="addTd">
						配件数量
					</td>
					
					<td align="left" >
					<s:textfield name="partsNum" id="partsNum" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
				<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
					<s:textfield name="remark" id="remark" style="width:100%;height:80px;" />
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
		$("#consContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("consContent",1000);
		}); 
		$("#partsContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("partsContent",1000);
		}); 
		
		
	});
	
	
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		if( document.oaConsumablesPartsForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaConsumablesPartsForm.transaffairname.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("申请标题超出最大长度限制");
				document.oaConsumablesPartsForm.transaffairname.focus();
				return false;
	    }
		if ($("#sbtype").val() == "1"){
			if( document.oaConsumablesPartsForm.consContent.value == ""){
			alert("耗材内容不能为空");
			 document.oaConsumablesPartsForm.consContent.focus();
			return false;
			} 
			totalLen = CommonUtils.checkLength($("#consContent").val());
			if( totalLen>1000){
					alert("耗材内容超出最大长度限制");
					document.oaConsumablesPartsForm.consContent.focus();
					return false;
		    }
			if( document.oaConsumablesPartsForm.consNum.value != ""){
				if(!num.test( document.oaConsumablesPartsForm.consNum.value)){
					alert("耗材数量格式不正确");
					$("#consNum").val('');
					document.oaConsumablesPartsForm.consNum.focus();
					return false;
				}
	        }
		}
		if ($("#sbtype").val() == "2"){
			if( document.oaConsumablesPartsForm.partsContent.value == ""){
			alert("配件内容不能为空");
			 document.oaConsumablesPartsForm.partsContent.focus();
			return false;
			} 
			totalLen = CommonUtils.checkLength($("#partsContent").val());
			if( totalLen>1000){
					alert("配件内容超出最大长度限制");
					document.oaConsumablesPartsForm.partsContent.focus();
					return false;
		    }
			if( document.oaConsumablesPartsForm.partsNum.value != ""){
				if(!num.test( document.oaConsumablesPartsForm.partsNum.value)){
					alert("配件数量格式不正确");
					$("#partsNum").val('');
					document.oaConsumablesPartsForm.partsNum.focus();
					return false;
				}
	        }
		}
		
		totalLen = CommonUtils.checkLength($("#remark").val());
		if( totalLen>4000){
				alert("备注超出最大长度限制");
				document.oaConsumablesPartsForm.remark.focus();
				return false;
	    }
			return true;
	}
	function sbtypeChanged(objValue) {
   	 if (objValue == "1") {
   		 $("#hc_td").show();
   		 $("#pj_td").hide();
   	 } else {
   		 $("#pj_td").show();
   		 $("#hc_td").hide();
   	 }
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