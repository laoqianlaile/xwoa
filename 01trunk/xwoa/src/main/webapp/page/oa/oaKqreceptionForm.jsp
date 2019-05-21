<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>接待清单</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>接待清单</b>
		</legend>
<s:form action="oaKqreception"  method="post" namespace="/oa" id="oaKqreceptionForm"  target="parent" >
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
						主题标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
	
	
					</td>
				</tr>
				<tr>
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
				<tr>
					<td class="addTd">
						接待部门
					</td>
					<td align="left">
                        <select id="kqdepname" name="kqdepname" style="width: 200px;height:27px">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${kqdepname == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
				
					<td class="addTd">
						人数
					</td>
					
					<td align="left">
						<s:textfield name="approval"  id="approval" style="width:50px;height:24px;"/>   位
					</td>
				</tr>
				
				<c:if test="${not empty moduleParam }">
					<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr>
					<td class="addTd" title="客情经办人将对提交的接待信息进行确认">${moduleParam.teamRoleName}<span style="color:red;">*</span></td>
					<td align="left" colspan="3">
					<input type="text" id="bjUserNames" name="bjUserNames" style="width: 100%;" value="${bjUserNames}" 	readonly="readonly" />
					<input type="hidden" id="bjCodes"	name="teamUserCodes" value="" /> 
					<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />	
					</td>		
					</tr>
					</c:if>
				</c:if>
				
				
				
				<tr>
					<td class="addTd">
						接待对象
					</td>
					<td align="left" colspan="3">
	                   <s:textfield name="approvalremark"  id="approvalremark" style="width:100%;height:30px;"/>
					</td>
					
				</tr>

				<tr>
					<td class="addTd">
						抵达时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="arrivetime" name="arrivetime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.approphone}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				
					<td class="addTd">
						离开时间
					</td>
					<td align="left" >
					<input type="text" class="Wdate"id="leavetime" name="leavetime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.leavetime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>

				<tr>
					<td class="addTd">
						公务内容 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="bodycontent" id="bodycontent" style="width:100%;height:80px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						住宿地点
					</td>
					
					<td align="left" >
					<s:textfield name="lodgingplace" id="lodgingplace" style="width:100%;height:24px;" />
					</td>
				
					<td class="addTd">
						住宿费用
					</td>
					<td align="left">
					<s:textfield name="lodgingcase" id="lodgingcase" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						用餐地点
					</td>
					
					<td align="left" >
					<s:textfield name="mealplace" id="mealplace" style="width:100%;height:24px;" />
					</td>
				
					<td class="addTd">
						用餐费用
					</td>
					<td align="left">
					<s:textfield name="mealcase" id="mealcase" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
					
					<td class="addTd">
						会议地点
					</td>
					
					<td align="left">
					<s:textfield name="meetplase" id="meetplase" style="width:100%;height:24px;" />
					</td>
				
					<td class="addTd">
						会议费用
					</td>
					<td align="left">
					<s:textfield name="meetcase" id="meetcase" style="width:100%;height:24px;" />
					</td>
				<tr>	
					<td class="addTd">
						会议内容
					</td>
					<td align="left" >
					<s:textfield name="meetcontent" id="meetcontent" style="width:100%;height:24px;" />
					</td>
				
					<td class="addTd">
						参会人数
					</td>
					<td align="left">
					<s:textfield name="meetnum" id="meetnum" style="width:100%;height:24px;" />
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						其他内容
					</td>
					<td align="left" colspan="3">
						<s:textarea name="otheritems" id="otheritems" style="width:100%;height:60px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						费用合计
					</td>
					<td align="left" colspan="3">
					小写  <s:textfield name="costtotallowcase"  id="costtotallowcase" style="width:150px;height:24px;"/>  
					大写 <s:textfield name="costtotalcapital"  id="costtotalcapital" style="width:250px;height:24px;"/> (小写金额录入以后自动生成)
					
					
					</td>
				</tr>
				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
					<s:textarea name="remark" id="remark" style="width:100%;height:60px;" />
					
					</td>
				</tr>
               </table>


			</s:form>
		
		</fieldset>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		
		//页面元素
		$("#bjUserNames").click(function(){
			 var d = '${userjson}';
		     if (d.trim().length) {
		    	 window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"));
			} 
		});
		
		
		
		
		$("#transaffairname").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("transaffairname",400);
		});
		$("#bodycontent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("bodycontent",4000);
		});
	});
	
	function checkForm(){
		var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
		var totalLen;
		var temp=/^\d+(\.\d)?$/;
		 if( document.oaKqreceptionForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaKqreceptionForm.transaffairname.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#transaffairname").val());
		if( totalLen>400){
				alert("主题标题超出最大长度限制");
				document.oaKqreceptionForm.transaffairname.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#kqdepname").val());
		if( totalLen>50){
				alert("接待部门超出最大长度限制");
				document.oaKqreceptionForm.kqdepname.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#approvalremark").val());
		if( totalLen>500){
				alert("接待对象超出最大长度限制");
				document.oaKqreceptionForm.approvalremark.focus();
				return false;
	    }
		
		totalLen = CommonUtils.checkLength($("#approval").val());
		if( totalLen>50){
				alert("人数超出最大长度限制");
				document.oaKqreceptionForm.approval.focus();
				return false;
	    }
		if( document.oaKqreceptionForm.bodycontent.value == ""){
			alert("公务内容不能为空");
			 document.oaKqreceptionForm.bodycontent.focus();
			return false;
		}
		totalLen = CommonUtils.checkLength($("#bodycontent").val());
		if( totalLen>4000){
				alert("公务内容超出最大长度限制");
				document.oaKqreceptionForm.bodycontent.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#lodgingplace").val());
		if( totalLen>300){
				alert("住宿地点超出最大长度限制");
				document.oaKqreceptionForm.lodgingplace.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#lodgingcase").val());
		if( totalLen>8){
				alert("住宿费用超出最大长度限制");
				document.oaKqreceptionForm.lodgingcase.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#mealplace").val());
		if( totalLen>300){
				alert("用餐地点超出最大长度限制");
				document.oaKqreceptionForm.mealplace.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#mealcase").val());
		if( totalLen>8){
				alert("用餐费用超出最大长度限制");
				document.oaKqreceptionForm.mealcase.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#meetplase").val());
		if( totalLen>300){
				alert("会议地点超出最大长度限制");
				document.oaKqreceptionForm.meetplase.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#meetcase").val());
		if( totalLen>8){
				alert("会议费用超出最大长度限制");
				document.oaKqreceptionForm.meetcase.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#meetcontent").val());
		if( totalLen>300){
				alert("会议内容超出最大长度限制");
				document.oaKqreceptionForm.meetcontent.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#meetnum").val());
		if( totalLen>8){
				alert("参会人数超出最大长度限制");
				document.oaKqreceptionForm.meetnum.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#otheritems").val());
		if( totalLen>300){
				alert("其他内容超出最大长度限制");
				document.oaKqreceptionForm.otheritems.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#costtotalcapital").val());
		if( totalLen>20){
				alert("合计大写超出最大长度限制");
				document.oaKqreceptionForm.costtotalcapital.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#costtotallowcase").val());
		if( totalLen>20){
				alert("合计小写超出最大长度限制");
				document.oaKqreceptionForm.costtotallowcase.focus();
				return false;
	    }
		totalLen = CommonUtils.checkLength($("#remark").val());
		if( totalLen>4000){
				alert("备注超出最大长度限制");
				document.oaKqreceptionForm.remark.focus();
				return false;
	    }
		
		
		
		if( document.oaKqreceptionForm.approval.value != ""){
			if(!num.test( document.oaKqreceptionForm.approval.value)){
				alert("人数格式不正确");
				$("#approval").val('');
				document.oaKqreceptionForm.approval.focus();
				return false;
			}
        } 
		if( document.oaKqreceptionForm.lodgingcase.value != ""){
			if(num.test( document.oaKqreceptionForm.lodgingcase.value)){
				if(!temp.test( document.oaKqreceptionForm.lodgingcase.value)){
				alert("住宿费用不能为负");
				$("#lodgingcase").val('');
				document.oaKqreceptionForm.lodgingcase.focus();
				return false;
			    }
			 }else{
				 alert("住宿费用格式不正确");
			     $("#lodgingcase").val('');
			     document.oaKqreceptionForm.lodgingcase.focus();
				 return false;
			 }
        }
		if( document.oaKqreceptionForm.mealcase.value != ""){
			if(num.test( document.oaKqreceptionForm.mealcase.value)){
				if(!temp.test( document.oaKqreceptionForm.lodgingcase.value)){
				alert("用餐费用不能为负");
				$("#mealcase").val('');
				document.oaKqreceptionForm.mealcase.focus();
				return false;
				}
				}else{
					 alert("用餐费用格式不正确");
			    	 $("#mealcase").val('');
			     	 document.oaKqreceptionForm.mealcase.focus();
					 return false;
			}
        }
		if( document.oaKqreceptionForm.meetcase.value != ""){
			if(num.test( document.oaKqreceptionForm.meetcase.value)){
				if(!temp.test( document.oaKqreceptionForm.lodgingcase.value)){
				alert("会议费用格式不正确");
				$("#meetcase").val('');
				document.oaKqreceptionForm.meetcase.focus();
				return false;
				}
			}else{
				 alert("会议费用格式不正确");
		    	 $("#meetcase").val('');
		     	 document.oaKqreceptionForm.meetcase.focus();
				 return false;
			}
        }
		if( document.oaKqreceptionForm.meetnum.value != ""){
			if(num.test( document.oaKqreceptionForm.meetnum.value)){
				if(!temp.test( document.oaKqreceptionForm.lodgingcase.value)){
				alert("参会人数不能为负");
				$("#meetnum").val('');
				document.oaKqreceptionForm.meetnum.focus();
				return false;
				}
			}else{
				 alert("参会人数格式不正确");
		    	 $("#meetnum").val('');
		     	 document.oaKqreceptionForm.meetnum.focus();
				 return false;
			}
        }
		if( document.oaKqreceptionForm.costtotallowcase.value != ""){
			if(!num.test( document.oaKqreceptionForm.costtotallowcase.value)){
				alert("费用合计小写格式不正确");
				$("#costtotallowcase").val('');
				document.oaKqreceptionForm.costtotallowcase.focus();
				return false;
			}
        }
		var begD = $("#arrivetime").val();
		var endD = $("#leavetime").val();
		if (endD != "" && begD > endD) {
			alert("离开时间不能早于抵达时间。");
			return false;
		}
		
// 		return false;
		return chackModuleParam();
	}
	
	
	function chackModuleParam(){
		
		if('${moduleParam.assignTeamRole}' == 'T'){
			if( $("#bjUserNames").size()>0 &&$("#bjUserNames").val() == ""){
				alert("选择办理人员不能为空");
				$("#bjUserNames").focus();
				return false;
				}
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
	 
	 
	 //费用合计小写
	
	 $("#lodgingcase").blur(function(){
		 $("#costtotallowcase").val(Number($("#lodgingcase").val())+Number($("#mealcase").val())+Number($("#meetcase").val()));
	 })
	 $("#mealcase").blur(function(){
		 $("#costtotallowcase").val(Number($("#lodgingcase").val())+Number($("#mealcase").val())+Number($("#meetcase").val()));
	 })
	 $("#meetcase").blur(function(){
		 $("#costtotallowcase").val(Number($("#lodgingcase").val())+Number($("#mealcase").val())+Number($("#meetcase").val()));
	 })
	 
	 //大写金额
	  $("#costtotallowcase").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#costtotallowcase").val()) );
		  });
	  $("#lodgingcase").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#costtotallowcase").val()) );
		  });
	  $("#mealcase").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#costtotallowcase").val()) );
		  });
	  $("#meetcase").blur(function(){
		   
		    $("#costtotalcapital").val(changeChineseNumber( $("#costtotallowcase").val()) );
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
	    numArray[0] = numArray[0] ? numArray[0]+"圆" : numArray[0], numArray[1] = numArray[1].replace(/^零+/, "")  
	    numArray[1] = numArray[1].match(/分/) ? numArray[1] : numArray[1]+"整"  
	    return numArray[0]+numArray[1]  
		} 
	</script>
</html>