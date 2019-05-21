<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>物品申领</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>物品申领信息</b>
		</legend>
<s:form action="oaOfficesupplies"  method="post" namespace="/oa" id="oaOfficesuppliesForm"  target="parent" >
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
					<%-- <td class="addTd">
						申请时间
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td> --%>
					<td class="addTd">
						期号
					</td>
					<td align="left" >
						第  <s:textfield name="layoutno"  id="layoutno" style="width:50px;height:24px;"/>  期
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
				
				<%-- <tr>
					<td class="addTd">
						申请人
					</td>
					<td align="left" ><span>${cp:MAPVALUE('usercode', object.creater)}</span></td>
					</td>
					<td class="addTd">
						登记日期
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="creatertime" name="creatertime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.creatertime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					
				</tr> --%>

				

				<tr>
					<td class="addTd">
						物品<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remark" id="remark" style="width:100%;height:80px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						事由<span style="color:red;">*</span>
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
		$("#remarkContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("remarkContent",4000);
		}); 
		
		
	});
	
	
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		if( document.oaOfficesuppliesForm.transaffairname.value == ""){
			alert("标题不能为空");
			 document.oaOfficesuppliesForm.transaffairname.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("标题超出最大长度限制");
				document.oaOfficesuppliesForm.transaffairname.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#layoutno").val());
		if( totalLen>50){
				alert("期数超出最大长度限制");
				document.oaOfficesuppliesForm.layoutno.focus();
				return false;
	    }
		if( document.oaOfficesuppliesForm.layoutno.value != ""){
			if(!num.test( document.oaOfficesuppliesForm.layoutno.value)){
				alert("耗材数量格式不正确");
				$("#layoutno").val('');
				document.oaOfficesuppliesForm.layoutno.focus();
				return false;
			}
        }
		
		if( document.oaOfficesuppliesForm.remarkContent.value == ""){
			alert("事由不能为空");
			 document.oaOfficesuppliesForm.remarkContent.focus();
			return false;
		} 
		totalLen = CommonUtils.checkLength($("#remarkContent").val());
		if( totalLen>4000){
				alert("事由超出最大长度限制");
				document.oaOfficesuppliesForm.remarkContent.focus();
				return false;
	    }
		if( document.oaOfficesuppliesForm.remark.value == ""){
			alert("物品不能为空");
			 document.oaOfficesuppliesForm.remark.focus();
			return false;
		} 
		totalLen = CommonUtils.checkLength($("#remark").val());
		if( totalLen>4000){
				alert("物品超出最大长度限制");
				document.oaOfficesuppliesForm.remark.focus();
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