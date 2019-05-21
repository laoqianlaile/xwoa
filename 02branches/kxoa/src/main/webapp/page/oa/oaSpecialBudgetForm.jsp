<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>科普专项经费预算</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>科普专项经费预算</b>
		</legend>
<s:form action="oaSpecialBudget"  method="post" namespace="/oa" id="oaSpecialBudgetForm"  target="parent" >
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
						项目名称 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="title" id="title"  cols="40" rows="2" style="width:100%;height:40px;" />
	
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						部室名称
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
						预算科目
					</td>
					
					<td align="left">
						<s:textfield name="applyNo"  id="applyNo" style="width:100%;height:24px;"/>
					</td>
				</tr>
				<tr>
				<td class="addTd">
						会议名称
					</td>
					
					<td align="left" >
						<s:textfield name="meetingNo"  id="meetingNo" style="width:100%;height:24px;"/>
					</td>
				<td class="addTd">
						会议类别
					</td>
					
					<td align="left">
						<s:textfield name="meettype"  id="meettype" style="width:100%;height:24px;"/>
					</td>
				</tr>
				<tr>
				<td class="addTd">
						参会人数
					</td>
					
					<td align="left" >
						<s:textfield name="meetingPersionsNum"  id="meetingPersionsNum" style="width:100%;height:24px;"/>
					</td>
				<td class="addTd">
						会议预算
					</td>
					
					<td align="left">
						<s:textfield name="outerPersions"  id="outerPersions" style="width:100%;height:24px;"/>
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
					<td class="addTd">
					明细及标准
					</td>
					<td align="left" >
					<s:textfield name="standard"  id="standard" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额
					</td>
					<td align="left">
					<s:textfield name="amount" id="amount" style="width:100%;height:24px;" />
					</td>
                </tr>
				<tr>
					<td class="addTd">
					明细及标准2
					</td>
					<td align="left" >
					<s:textfield name="standard2"  id="standard2" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额2
					</td>
					<td align="left">
					<s:textfield name="amount2" id="amount2" style="width:100%;height:24px;" />
					</td>
                </tr>
				<tr>
					<td class="addTd">
					明细及标准3
					</td>
					<td align="left" >
					<s:textfield name="standard3"  id="standard3" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额3
					</td>
					<td align="left">
					<s:textfield name="amount3" id="amount3" style="width:100%;height:24px;" />
					</td>
                </tr>
				<tr>
					<td class="addTd">
					明细及标准4
					</td>
					<td align="left" >
					<s:textfield name="standard4"  id="standard4" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额4
					</td>
					<td align="left">
					<s:textfield name="amount4" id="amount4" style="width:100%;height:24px;" />
					</td>
                </tr>
				<tr>
					<td class="addTd">
					明细及标准5
					</td>
					<td align="left" >
					<s:textfield name="standard5"  id="standard5" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额5
					</td>
					<td align="left">
					<s:textfield name="amount5" id="amount5" style="width:100%;height:24px;" />
					</td>
                </tr>
				<tr>
					<td class="addTd">
					明细及标准6
					</td>
					<td align="left" >
					<s:textfield name="standard6"  id="standard6" style="width:100%;height:24px;"/>
                    </td>
                    <td class="addTd">
						金额6
					</td>
					<td align="left">
					<s:textfield name="amount6" id="amount6" style="width:100%;height:24px;" />
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
		
		
		
	/* 	
		$("#title").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("title",400);
		}); */
		
	});
	
	function checkForm(){
		var begD = $("#begTime").val();
		var endD = $("#endTime").val();
		if (endD != "" && begD > endD) {
			alert("结束时间不能早于开始时间。");
			return false;
		}
		
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		var temp=/^\d+(\.\d)?$/;
		if( document.oaSpecialBudgetForm.title.value == ""){
			alert("项目名称不能为空");
			document.oaSpecialBudgetForm.title.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#title").val());
		if( totalLen>400){
				alert("项目名称超出最大长度限制");
				document.oaSpecialBudgetForm.title.focus();
				return false;
	    } 
		
		
		
		
		
		
		/* totalLen = CommonUtils.checkLength($("#amountAll").val());
		if( totalLen>20){
				alert("合计小写超出最大长度限制");
				document.oaSpecialBudgetForm.amountAll.focus();
				return false;
	    }
		
		
		
		
		if( document.oaSpecialBudgetForm.approval.value != ""){
			if(!num.test( document.oaSpecialBudgetForm.approval.value)){
				alert("人数格式不正确");
				$("#approval").val('');
				document.oaSpecialBudgetForm.approval.focus();
				return false;
			}
        } 
		if( document.oaSpecialBudgetForm.amount.value != ""){
			if(num.test( document.oaSpecialBudgetForm.amount.value)){
				if(!temp.test( document.oaSpecialBudgetForm.amount.value)){
				alert("住宿费用不能为负");
				$("#amount").val('');
				document.oaSpecialBudgetForm.amount.focus();
				return false;
			    }
			 }else{
				 alert("住宿费用格式不正确");
			     $("#amount").val('');
			     document.oaSpecialBudgetForm.amount.focus();
				 return false;
			 }
        }
		
	*/
		
		
	//return false;
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
	
	 $("#amount").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 $("#amount2").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 $("#amount3").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 $("#amount4").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 $("#amount5").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 $("#amount6").blur(function(){
		 $("#amountAll").val(Number($("#amount").val())+Number($("#amount2").val())+Number($("#amount3").val())+Number($("#amount4").val())+Number($("#amount5").val())+Number($("#amount6").val()));
	 })
	 
	 //大写金额
	  $("#amountAll").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount2").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount3").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount4").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount5").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#amountAll").val()) );
		  });
	  $("#amount6").blur(function(){
		   
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