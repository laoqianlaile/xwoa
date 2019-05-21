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
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:24px">
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
					<td align="left" colspan="3">
                              
						 <select id="kqdepname" name="kqdepname">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${kqdepname == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select> 
	
					</td>
				
					
				</tr>
				<tr>
					<td class="addTd">
						接待对象
					</td>
					<td align="left">
	                   <s:textfield name="approvalremark"  id="approvalremark" style="width:100%;height:24px;"/>
					</td>
					<td class="addTd">
						人数
					</td>
					
					<td align="left">
						<s:textfield name="approval"  id="approval" style="width:100%;height:24px;"/>
					</td>
				</tr>

				<tr>
					<td class="addTd">
						抵达时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="arrivetime" name="arrivetime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.arrivetime}"  pattern="yyyy-MM-dd"/>'  
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

            <input type="button" class="btn" id="saveAndSubmit" value="保存"  onclick="submitItemFrame();" />
	    	<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
			</s:form>
		
		</fieldset>
	</body>
	<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script type="text/javascript">
	
	function checkForm(){
		if( document.oaKqreceptionForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaKqreceptionForm.transaffairname.focus();
			return false;
		}
		if( document.oaKqreceptionForm.bodycontent.value == ""){
			alert("公务内容不能为空");
			 document.oaKqreceptionForm.bodycontent.focus();
			return false;
		}
			return true;
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
	    numArray[0] = numArray[0] ? numArray[0]+"元" : numArray[0], numArray[1] = numArray[1].replace(/^零+/, "")  
	    numArray[1] = numArray[1].match(/分/) ? numArray[1] : numArray[1]+"整"  
	    return numArray[0]+numArray[1]  
		} 

	function cancel() {
		DialogUtil.prototype.close();
	}
	
	function submitItemFrame() {
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaKqreceptionForm");
			srForm.action = 'oaKqreception!saveReditSQ.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaKqreceptionForm").serialize(),
                async: false,
                success: function(data) {
               		// 如果父页面重载或者关闭其子对话框全部会关闭
               		win.location.reload(true);
    				
                },
                error: function(data) {
                    alert("修改信息失败，请重新尝试！");
                }
            });
			}
		}
	}
	</script>
</html>