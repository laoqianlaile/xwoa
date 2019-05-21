<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta name="s_decorator" content='${LAYOUT}' />
<title>阅办文目录</title>
<%-- <sj:head locale="zh_CN" /> --%>
<script type="text/javascript" src="<c:url value='/scripts/datepicker/WdatePicker.js'/>"></script>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
function qlsx(){
	var s=document.getElementById("sfql").checked;
	if(s==true){
		document.getElementById("powerid").disabled=false;
	}else {
		document.getElementById("powerid").selectedIndex=0;
		document.getElementById("powerid").disabled=true;
	}
}

function clearInput(){
	document.getElementById("s_swhTime1").value="";
	document.getElementById("s_swhTime2").value="";
	document.getElementById("s_pfTime1").value="";
	document.getElementById("s_pfTime2").value="";
	document.getElementById("swh1").value="";
	document.getElementById("swh2").value="";
	document.getElementById("wjbt").value="";
	document.getElementById("ywh").value="";
	document.getElementById("fl").selectedIndex=0;
	document.getElementById("zbcs").selectedIndex=0;
	document.getElementById("lwdw").value="";
	document.getElementById("username").value="";
	document.getElementById("zbcbr").value="";
	document.getElementById("s_NP_pow").checked=false;
	document.getElementById("powerid").selectedIndex=0;
	document.getElementById("flowphase").selectedIndex=0;
}

function SWPrint() {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DIRPrintEdit.jsp";
		var param = "FileType=.doc&flowStep=SW_DIR&EditType=1,0&ShowType=1" +"&Template=SW_DIR";
		//var winProp = 'height='	+window.screen.availHeight+',width='+window.screen.availWidth
		//		+',directories=false,location=false,menubar=false,resizeable=true,scrollbars=yes,toolbar=false';
		//window.open(uri + "?"+ param,'editForm',winProp);
		openNewWindow(uri + "?"+ param,'editForm','');
}

function checkBeginDate(){
	var nowDate=new Date();
	var beginDate=$("#s_swhTime1").val();
	var beginNum=dateToNum(beginDate);
	var nDate=nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
	var nowNum=dateToNum(nDate);
	if(beginNum>nowNum){
		document.getElementById("s_swhTime1").value=nDate;
		alert("选择的收文日期不能大于当前日期！");
	}
}

function checkEndDate(){
	var nowDate=new Date();
	var endDate=$("#s_swhTime2").val();
	var endNum=dateToNum(endDate);
	var nDate=nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
	var nowNum=dateToNum(nDate);
	if(endNum>nowNum){
		document.getElementById("s_swhTime2").value=nDate;
		alert("选择的收文日期不能大于当前日期！");
	}
}

function checkPfBeginDate(){
	var nowDate=new Date();
	var beginDate=$("#s_pfTime1").val();
	var beginNum=dateToNum(beginDate);
	var nDate=nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
	var nowNum=dateToNum(nDate);
	if(beginNum>nowNum){
		document.getElementById("s_pfTime1").value=nDate;
		alert("选择的批分日期不能大于当前日期！");
	}
}

function checkPfEndDate(){
	var nowDate=new Date();
	var endDate=$("#s_pfTime2").val();
	var endNum=dateToNum(endDate);
	var nDate=nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
	var nowNum=dateToNum(nDate);
	if(endNum>nowNum){
		document.getElementById("s_pfTime2").value=nDate;
		alert("选择的批分日期不能大于当前日期！");
	}
}

function dateToNum(datevalue){
	var num=parseInt(datevalue.split("-")[0])*10000
			+ parseInt(datevalue.split("-")[1]*500)
			+ parseInt(datevalue.split("-")[2]*10);
	return num;
}
</script>
<style type="text/css">
.viewTable td { width:37%; }
.viewTable .addTd { width:13%; }
</style>
</head>
<body>
	<p class="ctitle">收文目录</p>
	<%@ include file="/page/common/messages.jsp"%>
	<s:form action="swSearch" method="post" namespace="/dispatchdoc">
 	<fieldset style="display: block; width: 99%; padding: 10px;"> 
 		<legend><b>收文查询条件</b></legend> 
 	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">		
 	  	<tr>  
 		    <td class="addTd">收文日期</td> 
 			<td> 
 			<input type="text" class="Wdate"  id="s_swhTime1" name="s_swhTime1" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value="${params['s_swhTime1']}" onchange="checkBeginDate();"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 				<sj:datepicker id="s_swhTime1" name="s_swhTime1"  --%>
<%-- 				yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"  --%>
<%--  				style="width:80px;" value="%{#parameters['s_swhTime1']}" onchange="checkBeginDate();"/> --%>
 					起&nbsp; 
 				<input type="text" class="Wdate"  id="s_swhTime2" name="s_swhTime2" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value="${params['s_swhTime2']}" onchange="checkEndDate();"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">	
<%-- 				<sj:datepicker id="s_swhTime2" name="s_swhTime2" yearRange="2000:2020"  --%>
<%-- 				displayFormat="yy-mm-dd" changeYear="true"   --%>
<%--  				style="width:80px;" value="%{#parameters['s_swhTime2']}"  --%>
<%--  				onchange="checkEndDate();"/> --%>
 				止 
 			</td>
 
			<td class="addTd">文件标题</td> 
 			<td> 
 				<s:textfield id="s_incomeDocTitle" name="s_incomeDocTitle" type="text" value="%{#parameters['s_incomeDocTitle']}" maxlength="2000" style="width: 100%;"/> 
			</td> 
		</tr> 

		<tr>
			<td class="addTd">来文单位</td> 
 			<td> 
 				<s:textfield id="s_incomeDeptName" name="s_incomeDeptName" type="text" value="%{#parameters['s_incomeDeptName']}" style="width: 100%;"/> 
 			</td> 
 			<td class="addTd">来文字号</td> 
 			<td> 
 				<s:textfield id="s_incomeDocNo" name="s_incomeDocNo" type="text" value="%{#parameters['s_incomeDocNo']}" style="width: 100%;"/> 
 			</td>
		
 		</tr> 
 			<tr>
			<td class="addTd">分办意见</td> 
 			<td> 
 				<s:textfield id="s_transcontent" name="s_transcontent" type="text" value="%{#parameters['s_transcontent']}" style="width: 100%;"/> 
 			</td> 
 			<td class="addTd">编号</td> 
 			<td> 
 				<s:textfield id="s_begNo" name="s_begNo" type="text" value="%{#parameters['s_begNo']}" style="width: 100px;"/>-
 				<s:textfield id="s_endNo" name="s_endNo" type="text" value="%{#parameters['s_endNo']}" style="width: 100px;"/>
 			</td>
		
 		</tr> 
 		
 		<tr>  
 		    <td class="addTd">批分日期</td> 
 			<td colspan="3"> 
 			<input type="text" class="Wdate"  id="s_pfTime1" name="s_pfTime1" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value="${params['s_swhTime1']}" onchange="checkPfBeginDate();"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 				<sj:datepicker id="s_pfTime1" name="s_pfTime1" yearRange="2000:2020"  --%>
<%-- 				displayFormat="yy-mm-dd" changeYear="true"  --%>
<%--  				style="width:80px;" value="%{#parameters['s_swhTime1']}" onchange="checkPfBeginDate();"/> --%>
 					起&nbsp; 
 			<input type="text" class="Wdate"  id="s_pfTime2" name="s_pfTime2" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value="${params['s_swhTime2']}" onchange="checkPfEndDate();"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">		
<%-- 				<sj:datepicker id="s_pfTime2" name="s_pfTime2" yearRange="2000:2020"  --%>
<%-- 				displayFormat="yy-mm-dd" changeYear="true"   --%>
<%--  				style="width:80px;" value="%{#parameters['s_swhTime2']}" onchange="checkPfEndDate();"/> --%>
 				止 
 			</td>
		</tr> 
 		
 	</table> 
	<div style="margin-top:6px;"><strong style="color:#ff0000;">注:标题可以输入多个关键字,以空格分开</strong></div> 
 	<div style="margin-top:10px; text-align:center;"> 
		<s:submit method="list" value="检索" cssClass="btn" /> 

	    <input type="button" onclick="SWPrint();" value="目录结构打印" class="btn infoBtn" style="padding-left:0; padding-right:0;" /> 
    </div> 
 	</fieldset>
    </s:form> 
<!--  	<p/> -->

	
 	<ec:table action="swSearch!list.do" items="objList" var="swSearch" imagePath="${STYLE_PATH}/images/table/*.gif" filterRowsCallback="limit"
		retrieveRowsCallback="limit" sortRowsCallback="limit" rowsDisplayed="8">
		<ec:exportXls fileName="sw.xls"></ec:exportXls>
		<ec:row>
			<ec:column property="incomeDate" title="收文日期" style="text-align:center;word-break: keep-all; white-space: nowrap;" sortable="true" cell="date" format="yyyy-MM-dd"/>
			<ec:column property="incomeDeptName" title="来文单位" style="text-align:center"  sortable="true" />
			<ec:column property="incomeDocNo" title="来文字号" style="text-align:center" />
			
		    <ec:column property="incomeDocTitle" title="文件标题" style="text-align:left" />			
			<ec:column property="transcontent" title="分办意见" style="text-align:center" />
			<ec:column property="acceptArchiveNo" title="编号" style="text-align:center" />
		
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
// 	if ('${s_NP_pow}' == 'true') {
// 		document.getElementById("s_NP_pow").checked = 'checked';
// 	}

	function viewbj(flowid) {
		var sheight = screen.availHeight-50;
 		var swidth = screen.availWidth-10;
		window.open(
				'${pageContext.request.contextPath}/yxxk/qlyxDj!viewBjInfo.do?swcx=1&flowInstId='+flowid,
				'_blank',
				'height='+sheight+',width='+swidth+',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
	}
</script>
</html>