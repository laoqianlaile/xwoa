<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>外出请假报备</title>
</head>

<body class="sub-flow">
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>外出请假报备信息</b>
		</legend>
<s:form action="oaLeavereported"  method="post" namespace="/oa" id="oaLeavereportedForm"  target="parent" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="s_itemtype" name="s_itemtype" value="${s_itemtype}" />	
			<input type="hidden" id="s_applyItemType" name="s_applyItemType" value="${s_applyItemType}" />	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<%-- <tr>
					<td class="addTd">
						标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr> --%>
				<tr>
					
					<td class="addTd">
						姓名<span style="color:red;">*</span>
					</td>
					<td align="left" >
						<%-- <span>${cp:MAPVALUE('usercode', object.creater)}</span></td> --%>
						<s:textfield name="solvenote"  id="solvenote" value ="%{object.solvenote}" style="width:100%;height:30px;"/>
					</td>
					
					<td class="addTd">
						职务
					</td>
					<td align="left" >
                          <%--  <span>${cp:MAPVALUE('userunit', object.postleve)}</span>    --%>
                          
						<s:textfield name="postleve"  id="postleve" style="width:100%;height:30px;"/>
					</td>
			
				</tr>
				<tr>
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" >
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:30px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>	
					<td class="addTd">
						日期
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="creatertime" name="creatertime"   style="width:200px;height:30px;"
			                  value='<fmt:formatDate value="${object.creatertime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>	
				</tr>
				<tr>
				<td class="addTd">
						申请部门
					</td>
					<td align="left" colspan="3">
					<input type="text" id="applyuser" name="applyuser" style="width:100%"/>
              		<%-- ${cp:MAPVALUE("unitcode",applyuser)} --%>
					</td>
				</tr>

				

				<tr>
					<td class="addTd">
						外出事由<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remarkContent" id="remarkContent" style="width:100%;height:80px;" />
					</td>
				</tr>
					
				<tr>	
					<td class="addTd">
						离开时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:30px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					<td class="addTd">
						返回时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="endtime" name="endtime"   style="width:200px;height:30px;"
			                  value='<fmt:formatDate value="${object.endtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				
				<tr>
				<td class="addTd">
						外出地点
					</td>
					<td align="left" >
                              
						<s:textfield name="leaveaddress"  id="leaveaddress" style="width:100%;height:30px;"/>
	
					</td>
					<td class="addTd">
						联系方式
					</td>
					<td align="left" >
                              
						<s:textfield name="telephone"  id="telephone" style="width:100%;height:30px;"/>
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remark" id="remark" style="width:100%;height:80px;" />
					</td>
				</tr>
				
				<%-- <c:if test="${not empty moduleParam }">
					<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr>
					<td class="addTd">${moduleParam.teamRoleName}<span style="color:red;">*</span></td>
					<td align="left" colspan="3">
					<input type="text" id="bjUserNames" name="bjUserNames" style="width: 100%;height:30px;" value="${bjUserNames}" 	readonly="readonly" />
					<input type="hidden" id="bjCodes"	name="teamUserCodes" value="" /> 
					<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />	
					</td>		
					</tr>
					</c:if>
				</c:if> --%>
               </table>


			</s:form>
		
		</fieldset>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		/* $("#transaffairname").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("transaffairname",400);
		});
		$("#remarkContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("remarkContent",4000);
		});  */
		
	});
	//页面元素
	$("#bjUserNames").click(function(){
		 var d = '${userjson}';
	     if (d.trim().length) {
	    	 window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"));
		} 
	});
	
	function selectPopWinTree(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow,1).init();/* 此处人员限制为一人 */
		setAlertStyle("attAlert");
	}
	
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		/* if( document.oaLeavereportedForm.transaffairname.value == ""){
			alert("标题不能为空");
			 document.oaLeavereportedForm.transaffairname.focus();
			return false;
		} */
		/* totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("标题超出最大长度限制");
				document.oaLeavereportedForm.transaffairname.focus();
				return false;
	    } */
		totalLen = CommonUtils.checkLength($("#leaveaddress").val());
		if( totalLen>300){
				alert("外出地点超出最大长度限制");
				document.oaLeavereportedForm.leaveaddress.focus();
				return false;
	    }
		
		
		if(!IsPhoneNumber($("#telephone").val())){
			   alert("联系电话格式不正确");
			   $("#telephone").focus();
		       return false;
	    }
		
	    if( document.oaLeavereportedForm.solvenote.value == ""){
			alert("姓名不能为空");
			 document.oaLeavereportedForm.solvenote.focus();
			return false;
		} 
	    
		if( document.oaLeavereportedForm.remarkContent.value == ""){
			alert("外出事由不能为空");
			 document.oaLeavereportedForm.remarkContent.focus();
			return false;
		} 
		totalLen = CommonUtils.checkLength($("#remarkContent").val());
		if( totalLen>4000){
				alert("外出事由超出最大长度限制");
				document.oaLeavereportedForm.remarkContent.focus();
				return false;
	    }
		var begD = $("#applydate").val();
		var endD = $("#endtime").val();
		if (endD != "" && begD > endD) {
			alert("返回时间不能早于离开时间。");
			return false;
		}	 
		
		/* if('${moduleParam.assignTeamRole}' == 'T'){ 
			if( document.oaLeavereportedForm.bjUserNames.value == ""){
				alert("选择分管领导不能为空");
				 document.oaLeavereportedForm.bjUserNames.focus();
				return false;
				}
		} */
		
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