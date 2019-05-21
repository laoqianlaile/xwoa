<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form  action="oaSurveydetail" namespace="/oa"
		style="margin-top:0;margin-bottom:5" onsubmit="return checkForm();">
        <input type="hidden" id="s_djid"  name="s_djid" value="${s_djid}">
	  	<input type="hidden" id="s_itemType" name="s_itemType"  value="${s_itemType}">
    	<input type="hidden" id="s_type"  name="s_type"  value="${s_type }">
	 	<div>
			<span
				style="width: 100%; position: relative; text-align: center; border-bottom: 1px dashed #111; font-size: 20px;"><br>
				<c:if test="${not empty oaSurvey}">${oaSurvey.title }</c:if> </span>
		</div>


		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<s:iterator value="objList" status="status" var="oaOnlineItem">
				<tr class="item" id="tr_${oaOnlineItem.no}_item"
					style="color: #000; font-weight: bold; background-color: #dbf2ff">
					<td>${status.count }、&nbsp;&nbsp;${oaOnlineItem.title }&nbsp;&nbsp;&nbsp;&nbsp;
					 <c:if test="${'T' eq oaOnlineItem.thesign}">
							[<span style="color: red">*</span>该题必填]
					</c:if>
					 <c:if test="${oaOnlineItem.limitnum gt 0}">
							[最多可选${oaOnlineItem.limitnum}项]
					</c:if>
					
					</td>
				</tr>
				<tr class="items" id="tr_${oaOnlineItem.no}_items" data-limitnum="${oaOnlineItem.limitnum}" data-thesign="${oaOnlineItem.thesign }" data-chosetype="${oaOnlineItem.chosetype }">
				<input type="hidden"  id="${oaOnlineItem.no}_djid" value="${djid }" name="oaSurveydetails[${status.count-1 }].djid">
				<input type="hidden"  id="${oaOnlineItem.no}_no" value="${no }" name="oaSurveydetails[${status.count-1 }].no">
					<td><c:choose>

							<c:when test="${'3' ne oaOnlineItem.chosetype}">
								<s:iterator value="oaOnlineItemss" status="s"
									var="oaOnlineItems">
									<c:choose>
										<c:when test="${'1' eq oaOnlineItem.chosetype}">
												<input type="checkbox" data-name="oaOnlineItems"  name="oaSurveydetails[${status.count-1 }].cid.itemid" value="${oaOnlineItems.itemid}"/> ${oaOnlineItems.title }
<%-- 									        	<input type="hidden"  id="${oaOnlineItem.no}_title" value="${oaOnlineItems.title }" name="oaSurveydetails[${status.count-1 }].title"> --%>
										</c:when>
										<c:when test="${'2' eq oaOnlineItem.chosetype}">
												<input type="radio" data-name="oaOnlineItems" name="oaSurveydetails[${status.count-1 }].cid.itemid" value="${oaOnlineItems.itemid}" /> ${oaOnlineItems.title }
<%-- 										        <input type="hidden"  id="${oaOnlineItem.no}_title" value="${oaOnlineItems.title }" name="oaSurveydetails[${status.count-1 }].title"> --%>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
									<c:if test="${not empty oaOnlineItem.chosenum and s.count%oaOnlineItem.chosenum eq 0}">
									<br>
									</c:if>
                                    
								</s:iterator>
							</c:when>
							<c:otherwise>
							<s:iterator value="oaOnlineItemss" status="s"
									var="oaOnlineItems">
							<input type="hidden"   name="oaSurveydetails[${status.count-1 }].cid.itemid" value="${oaOnlineItems.itemid}">
								<s:textarea data-name="oaOnlineItems" name="oaSurveydetails[%{#status.count-1 }].title" cols="40" rows="2" 
									style="width: 100%;" />
							</s:iterator>		
							</c:otherwise>
							</c:choose>
							</td>
				</tr>
			</s:iterator>
				<tr class="button">
				<td>
				<c:if test="${'surList' eq s_itemType}">
				
				<c:if test="${'false' eq  isSaved}">
				
				<s:submit name="save"  method="saveSurveydetail" cssClass="btn" key="opt.btn.save" />
				</c:if>
				</c:if>
<%-- 	            <s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>


	            <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
	            
				</td>
				</tr>
		</table>
	</s:form>
</body>
<script type="text/javascript">

function checkForm() {
	
	var flag=true;
	//但是在each代码块内不能使用break和continue,要实现break和continue的功能的话，要使用其它的方式break----用return false;continue --用return ture;
	$("tr.items").each(function(i)
	        {
		var chosetype=$(this).attr("data-chosetype");//题目类型1:多选2:单选3:问答
		
		var thesign=$(this).attr("data-thesign");//是否必填 T,F
		
		var limitnum=$(this).attr("data-limitnum");//最多可选个数
		
        var allValue=$(this).find("[data-name=oaOnlineItems]");//chackboox，radio,问答对象
       
        if('3'!=chosetype){
        var select=$(allValue).filter(':checked').length;//获取选择值
        
        //必填验证
		if('T'==thesign){
			if(select==''||select=='0'){
				alert("第"+eval(i+1)+"题为必填项！");
				flag=false;
				return false;
			}
		}
     
		//最多选择 为空或者为0 时不验证
		if(limitnum!="" &&limitnum!="0"){
			if(select>limitnum){
				alert("第"+eval(i+1)+"题超过最多选择限制，请重新选择！");
				flag=false;
				return false;
			}
		}
       }
        if('3'==chosetype){
        	
           if('T'==thesign&&$(allValue).val()==""){//获取选择值
        	   alert("第"+eval(i+1)+"题为必填项！");  
        	   flag=false;
				return false;
           }
           
         }
        
    });
    return flag;
}
</script>
</html>
