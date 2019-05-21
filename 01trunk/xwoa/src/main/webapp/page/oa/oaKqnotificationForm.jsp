<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>客情通报</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>客情通报</b>
		</legend>
<s:form action="oaKqnotification"  method="post" namespace="/oa" id="oaKqnotificationForm"  target="parent" >
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
						主题标题<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
	
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						期数
					</td>
					<td align="left" >
						第  <s:textfield name="periods"  id="periods" style="width:50px;height:24px;"/>  期
	
					</td>
					
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" >
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="height:27px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>		
					<%-- <td class="addTd">
					            登记人
					</td>
					<td align="left">
  
						<span>${cp:MAPVALUE('usercode', object.creater)}</span>
	
					</td> --%>
				</tr>
				<tr>
					<td class="addTd">
						客情部门
					</td>
					<td align="left">
							<select id="kqdepname" name="kqdepname" style="height:27px">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${kqdepname == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select></td> 
	
				
					<td class="addTd">
						客情时间
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="kqtime" name="kqtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.kqtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				<tr>
					<td class="addTd">
						来访人员
					</td>
					<td align="left" colspan="3">
	
                     	<s:textarea name="visitors" id="visitors" style="width:100%;height:40px;"  />
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						联系人
					</td>
					
					<td align="left">
						<s:textfield name="contactuser"  id="contactuser" style="width:100%;height:24px;"/>
					</td>
				
					<td class="addTd">
						联系电话
					</td>
					<td align="left">
						<s:textfield name="contactphone" id="contactphone" style="width:100%;height:24px;"/>
					</td>
				</tr>

				<tr>
					<td class="addTd">
						客情内容<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="kqcontent" id="kqcontent" style="width:100%;height:80px;" />
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
		$("#kqcontent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("kqcontent",4000);
		});
	});
	
	
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		if( document.oaKqnotificationForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaKqnotificationForm.transaffairname.focus();
			return false;
		}
		 	totalLen = CommonUtils.checkLength($("#transaffairname").val());
			if( totalLen>400){
					alert("主题标题超出最大长度限制");
					document.oaKqnotificationForm.transaffairname.focus();
					return false;
		    }
			totalLen = CommonUtils.checkLength($("#kqdepname").val());
			if( totalLen>50){
					alert("客情部门超出最大长度限制");
					document.oaKqnotificationForm.kqdepname.focus();
					return false;
		    }
			totalLen = CommonUtils.checkLength($("#contactuser").val());
			if( totalLen>50){
					alert("联系人超出最大长度限制");
					document.oaKqnotificationForm.contactuser.focus();
					return false;
		    }
			totalLen = CommonUtils.checkLength($("#visitors").val());
			if( totalLen>500){
					alert("来访人员超出最大长度限制");
					document.oaKqnotificationForm.visitors.focus();
					return false;
		    }
		if( document.oaKqnotificationForm.periods.value != ""){
			if(!num.test( document.oaKqnotificationForm.periods.value)){
				alert("期数格式不正确");
				$("#periods").val('');
				document.oaKqnotificationForm.periods.focus();
				return false;
			}
        }
	    if(!IsPhoneNumber($("#contactphone").val())){
			   alert("联系电话格式不正确");
// 			   $("#contactphone").val();
			   $("#contactphone").focus();
		       return false;
	    }
			  
		if( document.oaKqnotificationForm.kqcontent.value == ""){
			alert("客情内容不能为空");
			 document.oaKqnotificationForm.kqcontent.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#kqcontent").val());
		if( totalLen>4000){
				alert("客情内容超出最大长度限制");
				document.oaKqnotificationForm.kqcontent.focus();
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