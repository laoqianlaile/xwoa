<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>部门权力关联</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>
<body>

<p class="ctitle">关联权力部门信息</p>
	<%@ include file="/page/common/messages.jsp"%>
<s:form action="powerOrgInfo" method="post" namespace="/powerruntime" id="powerOrgInfoForm">	
	<input type="button" onclick="savePower();" value="保存" class="btn"/>	
	<input type="hidden" id="retValue" name="retValue"  value="${retValue}" />
 <div class="two">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_b">	
	  <tr>
  		  <td width="45%"><div class="unline">
	       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">	
	            <tr>
	               <td align="left">行使部门</td>
	                <td valign="middle">
                      <select name="unitcode" onchange="doChange(2);" id="unitcode">
								<option value="" selected="selected">
								<c:forEach var="row" items="${cp:LVB('unitcode')}">
									<option value="${row.value}"
									<c:if test="${s_unitcode eq row.value}"> selected = "selected" </c:if>
									><c:out value="${row.label}"/>
									</option>
								</c:forEach>
							</select>
	                </td>
	            </tr>
	        </table></div>
	        </td>
		    <td rowspan="3" width="10%" valign="bottom" align="center">
				<input type="button" onclick="moveItem(document.powerOrgInfoForm.allItem,document.powerOrgInfoForm.selectItem)" class="btnleft" title="添加"><br><br>
				<input type="button" onclick="moveAll(document.powerOrgInfoForm.allItem,document.powerOrgInfoForm.selectItem)"  class="btnleft2" title="全部添加"><br><br>
		        <input type="button" onclick="moveItem(document.powerOrgInfoForm.selectItem,document.powerOrgInfoForm.allItem)" class="btnright"  title="移除"><br><br>
				<input type="button" onclick="moveAll(document.powerOrgInfoForm.selectItem,document.powerOrgInfoForm.allItem)" class="btnright2" title="全部移除"><br><br><br><br><br>
            </td>
		    <td width="45%"><div class="unline">
	        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">	
	            <tr>
					<td align="left">权力类型</td>
	                <td>
	               <select name="type" style="width: 200px" id="type" onchange="doChange(2);">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
							<option value="${row.key}"
							<c:if test="${type eq row.key}"> selected = "selected" </c:if>
								>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select>
	                </td>
	             </tr>
	        </table></div>
	        </td>
	</tr>
	<tr>
	    <td height="30" style="text-align:left;">已选权力事项：</td>	   
	    <td style="text-align:left;">备选权力事项：</td>
	</tr>
	<tr>
	    <td align="center">
	        <select name="selectItem" size="19" style="width=376px;" multiple="true" id="selectItem">							
								<c:forEach var="row" items="${selectList}">
									<option value="${row.value}"><c:out value="${row.label}"/>
									</option>
								</c:forEach>
							</select>
		
					
	    </td>	    
	    <td align="center">
	        <select name="allItem" size="19" style="width=376px;" multiple="true" id="allItem">							
								<c:forEach var="row" items="${allList}">
									<option value="${row.value}"><c:out value="${row.label}"/>
									</option>
								</c:forEach>
							</select>
	    </td>
    </tr>
</table>
</div>

</s:form>
</body>
<script language="JavaScript" type="text/JavaScript">
function checkID(){	
	if($("#unitcode").val()==''){
		alert("请选择行使部门！");
		$('#unitcode').focus();
		return false;
	}else{
	    return true;
	}
}

function doChange(){	
    var url = "powerOrgInfo!powerOrgInfoList.do?type=" + $("#type").val()+"&s_unitcode="+$("#unitcode").val()+"&unitcode="+$("#unitcode").val();
	document.location.href = url;
}

function moveItem(fromList, toList){
    if(checkID()){
       move_Item(fromList, toList);
	}
}

function moveAll(fromList, toList) {
    if(checkID()){
		move_all(fromList, toList);
	}
}

function savePower(){
    if(checkID()){
	    var retValue = "";
		for (var i = 0; i < document.powerOrgInfoForm.selectItem.options.length; i++) {
			retValue += document.powerOrgInfoForm.selectItem.options[i].value;
			retValue += ",";
		}
		document.powerOrgInfoForm.retValue.value = retValue.substring(0, retValue.length - 1);
		 var url = "powerOrgInfo!save.do?type="+$("#type").val()+"&retValue=" +retValue+"&s_unitcode="+$("#unitcode").val()+"&unitcode="+$("#unitcode").val();
			document.location.href = url;
	}
}

function move_Item(fromList, toList){
    for (var i = 0; i < fromList.options.length; i++) {
		  if(fromList.options[i].selected && fromList.options[i].value != "") {
				toList.add(new Option(fromList.options[i].text, fromList.options[i].value));
				toList.options[toList.length-1].title = fromList.options[i].text;
				fromList.options[i].removeNode(true);
				i--;
		  }
	}
}

function move_all(fromList, toList){
    for (var i = 0; i < fromList.options.length; i++) {
		toList.add(new Option(fromList.options[i].text, fromList.options[i].value));
		toList.options[toList.length-1].title = fromList.options[i].text;
	}
	fromList.options.length = 0; 
}
</script>
</html>