<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>出差经费预算</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>出差经费预算</b>
		</legend>
<s:form action="oaBudgetstravel"  method="post" namespace="/oa" id="oaBudgetstravelForm"  target="parent" >
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
						出差事由 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="title" id="title"  cols="40" rows="2" style="width:100%;height:40px;" />
	
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						部 门
					</td>
					<td align="left">
                        <select id="depno" name="depno" style="width: 200px;height:27px">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${depno == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
				
					<td class="addTd">
						出差地点
					</td>
					
					<td align="left">
						<s:textfield name="address"  id="address" style="width:100%;height:24px;"/>
					</td>
				</tr>
				<tr>
				<td class="addTd">
						出差人员
					</td>
					
					<td align="left"  colspan="3">
						<s:textfield name="meetingNo"  id="meetingNo" style="width:100%;height:24px;"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						起始时间<span style="color:red;">*</span>
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="begTime" name="begTime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.begTime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				
					<td class="addTd">
						结束时间<span style="color:red;">*</span>
					</td>
					<td align="left" >
					<input type="text" class="Wdate"id="endTime" name="endTime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.endTime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				<tr>
				<td class="addTd">城市间交通方式
					</td>
					
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('trans_level')}" varStatus="status">
					         <input type="radio"  name="trans" value="${row.key}" ${(object.trans eq row.key or (empty object.trans and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
                    </td>
                    <td class="addTd">
						城市间交通费用
					</td>
					<td align="left">
					<s:textfield name="transStandard" id="transStandard" style="width:100%;height:24px;" />
					</td>
                </tr>
                <tr>
					
					<td class="addTd">
						住宿地点
					</td>
					
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('amountTrans')}" varStatus="status">
					         <input type="radio"  name="amountTrans" value="${row.key}" ${(object.amountTrans eq row.key or (empty object.amountTrans and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
                    </td>
				
					<td class="addTd">
						住宿费用
					</td>
					<td align="left">
					<s:textfield name="accomStandard" id="accomStandard" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						伙食补助地点
					</td>
					
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('standardFood')}" varStatus="status">
					         <input type="radio"  name="standardFood" value="${row.key}" ${(object.standardFood eq row.key or (empty object.standardFood and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
					</td>
				
					<td class="addTd">
						伙食补助费用
					</td>
					<td align="left">
					<s:textfield name="amountFood" id="amountFood" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						公杂费
					</td>
					
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('miscellaneous')}" varStatus="status">
					         <input type="radio"  name="miscellaneousStandard" value="${row.key}" ${(object.miscellaneousStandard eq row.key or (empty object.standardFood and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
					</td>
				
					<td class="addTd">
						公杂费用
					</td>
					<td align="left">
					<s:textfield name="amountMiscellaneous" id="amountMiscellaneous" style="width:100%;height:24px;" />
					</td>
				<tr>
				<tr>
				<td class="addTd">
						申请人
					</td>
					
					<td align="left">
						<s:textfield name="creater"  id="creater" style="width:100%;height:24px;"/>
					</td>
					<td class="addTd">
						申请时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="createtime" name="createtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.createtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				<tr>
					<td class="addTd">
						费用合计
					</td>
					<td align="left" colspan="3">
					小写  <s:textfield name="amountAll"  id="amountAll" style="width:150px;height:24px;"/>  
					大写 <s:textfield name="costtotalcapital"  id="costtotalcapital" style="width:250px;height:24px;"/> (小写金额录入以后自动生成)
					
					
					</td>
				</tr>	
				<%-- <tr>
					<td class="addTd">
						制单时间
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="approtime" name="approtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.approtime}"  pattern="yyyy-MM-dd"/>'  
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
				 --%>
               </table>


			</s:form>
		
		</fieldset>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		
		/* //页面元素
		$("#bjUserNames").click(function(){
			 var d = '${userjson}';
		     if (d.trim().length) {
		    	 window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"));
			} 
		}); */
		
		
		
		
		$("#title").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("title",400);
		});
		
	});
	
	function checkForm(){
		alert("1");
		var begD = $("#begTime").val();
		var endD = $("#endTime").val();
		if (endD != "" && begD > endD) {
			alert("结束时间不能早于开始时间。");
			return false;
		}
		
		alert("2");
		/* var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		var temp=/^\d+(\.\d)?$/;
		if( document.oaBudgetstravelForm.title.value == ""){
			alert("出差事由不能为空");
			 document.oaBudgetstravelForm.title.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#title").val());
		if( totalLen>400){
				alert("出差事由超出最大长度限制");
				document.oaBudgetstravelForm.title.focus();
				return false;
	    } */	
		
		alert("3");
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
	 
	 
	 //费用合计小写
	
	 $("#transStandard").blur(function(){
		 $("#amountAll").val(Number($("#transStandard").val())+Number($("#amountFood").val())+Number($("#accomStandard").val())+Number($("#amountMiscellaneous").val()));
	 })
	 $("#amountFood").blur(function(){
		 $("#amountAll").val(Number($("#transStandard").val())+Number($("#amountFood").val())+Number($("#accomStandard").val())+Number($("#amountMiscellaneous").val()));
	 })
	 $("#accomStandard").blur(function(){
		 $("#amountAll").val(Number($("#transStandard").val())+Number($("#amountFood").val())+Number($("#accomStandard").val())+Number($("#amountMiscellaneous").val()));
	 })
	 $("#amountMiscellaneous").blur(function(){
		 $("#amountAll").val(Number($("#transStandard").val())+Number($("#amountFood").val())+Number($("#accomStandard").val())+Number($("#amountMiscellaneous").val()));
	 })
	 
	 //大写金额
	  $("#amountAll").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#transStandard").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amountFood").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#accomStandard").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amountMiscellaneous").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });

	 function changeChineseNumber(num)  
		{  
	    if (isNaN(num) || num > Math.pow(10, 12)) return ""  
	    var cn = "零壹贰叁肆伍陆柒捌玖"  
	    var unit = new Array("拾百千", "分角")  
	    var unit1= new Array("万亿", "")  
	    var numArray = num.toString().split(".")  
	    var start = new Array(numArray[0].length-1, 2)  
	    function toChinese(num, index)  
	    {  
	        var num = num.replace(/\d/g, function ($1)  
	        {  
	            return cn.charAt($1)+unit[index].charAt(start--%4 ? start%4 : -1)  
	        })  
	        return num  
	    }  
	    for (var i=0; i<numArray.length; i++)  
	    {  
	        var tmp = ""  
	        for (var j=0; j*4<numArray[i].length; j++)  
	        {  
	        var strIndex = numArray[i].length-(j+1)*4  
	        var str = numArray[i].substring(strIndex, strIndex+4)  
	        var start = i ? 2 : str.length-1  
	        var tmp1 = toChinese(str, i)  
	        tmp1 = tmp1.replace(/(零.)+/g, "零").replace(/零+$/, "")  
	        tmp1 = tmp1.replace(/^壹拾/, "拾")  
	        tmp = (tmp1+unit1[i].charAt(j-1)) + tmp  
	        }  
	        numArray[i] = tmp  
	    }  
	    numArray[1] = numArray[1] ? numArray[1] : ""  
	    numArray[0] = numArray[0] ? numArray[0]+"元" : numArray[0], numArray[1] = numArray[1].replace(/^零+/, "")  
	    numArray[1] = numArray[1].match(/分/) ? numArray[1] : numArray[1]+"整"  
	    return numArray[0]+numArray[1]  
		} 
	</script>
</html>