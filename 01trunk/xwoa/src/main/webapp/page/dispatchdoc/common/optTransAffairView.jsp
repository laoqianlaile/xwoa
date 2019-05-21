<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
	 <!-- 	悬浮	 -->
	<link href="${pageContext.request.contextPath}/styles/default/css/sidebar.css" rel="stylesheet" type="text/css" />
	<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/lrtk.css">
   <script type="text/javascript" src="jquery-1.6.min.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
		<title>
			${jspInfo.title}
		</title>
	</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<div class="flowTitle">${jspInfo.title}</div>
	<s:form action=" " method="post" namespace="/powerruntime" id="vOptBaselist_form" validator="true">
	 <input type="hidden" id="djId" name="djId" value="${optBaseInfo.djId}" />
	<%-- 	 <s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	<div class="nav_linkage" style="width:89%;padding-top:30px;">
	 <c:forEach var="fInfo" items="${jspInfo.frameList}" >
	  <!-- 		   1查看权限验证  2ifram id为附件 3行政角色看附件  -->
		 <c:if test="${ 'stuffFrame' ne fInfo.frameId  or ('stuffFrame' eq fInfo.frameId  and  !isVailViewPower)  or  (isVailViewPower and 'stuffFrame' eq fInfo.frameId  and cp:CHECKUSEROPTPOWER('DOCXZ', 'DocViewStuff'))}">
		<iframe id="${fInfo.frameId}" name="${fInfo.frameId}" src="<c:url value='${fInfo.frameSrc}'/>" width="98%" style="margin-bottom:10px;min-height:130px"
			frameborder="no" scrolling="false" border="0" marginwidth="0" onload="autoHeight(this);"></iframe>
	    </c:if>
	</c:forEach>
	</div>
	</s:form>
<script type="text/javascript">
	var sh = function(h){
		if(document.getElementById("viewFrame")){ 
			var t = document.getElementById("viewFrame");
			t.height = h;
		}
	};
	
	function proCollect(ele,djId){
		var alertvalue=$.trim($(ele).text());
		if(window.confirm("您确定["+alertvalue+"]操作吗？")){
		$.ajax({
			type : "post",
			async: false,
			dataType : "json",
			url : "${ctx}/powerruntime/VOptProcCollection!saveColl.do?djId="+djId,
			success : function(data) {				
				if(data.status=="coll"){
					$(ele).text("取消收藏");
					alert("["+alertvalue+"]操作成功！\n 被收藏的公文请在公文收藏中查看。");
				}else{
					$(ele).text("办件收藏");
					alert("["+alertvalue+"]操作成功！");
				}			
			},
			error : function() {
				alert("失败");
			}
		});
		}
	}
	//右侧导航滚动关联
	function scrollToAfter(th){
		$('.nav_right_link>a').removeClass('select');
		$(th).addClass('select');
	    $(window).unbind("scroll");
	    setTimeout(function(){
	    	 $(window).bind("scroll",scrollEvent);
	    },200);
	}
	function scrollEvent(){
		var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
		var tempHeight = $(window).height() + scrollTop - 50;//50为手动调的误差值
		var currIndex = 0;
		var h = 70;//初始值位距离顶部的距离
	    $(".nav_linkage iframe").each(function(i,ele){
	    	h+=($(ele).height());//累计iframe的高度
	    	if(h >= tempHeight){
	    		currIndex = i;
	    		return false;
	    	}
	    });
	    $('.nav_right_link>a').removeClass('select');
	    $(".nav_right_link a").eq(currIndex).addClass('select');
	}
	function resizeIframeWidth(){
		var sideWidth = $(".sider").outerWidth(true);
		var navbarWidth = $(".nav_right").outerWidth(true);
		var leftWidth = $(window).width() - sideWidth - navbarWidth -60;
		
		$("iframe").each(function(){
			$(this).width(leftWidth);
		});
	}
	$(document).ready(function() {
	    $(window).unbind("scroll").bind("scroll",scrollEvent);
	    resizeIframeWidth();
	});
	function showView(title,link){
		DialogUtil.open(title,link,1200,700);
	}
</script>
<!-- 返回顶部浮层 -->
<div class="sider">
	<ul>
	<li id="sy" style="display:list-item;">
		<A HREF="javascript:void(0);"
			onclick="returnNewPage('sy')">
			返回首页
		</A>
	</li>
    <c:if test="${empty dashboard}">
    <li id="fhdb" style="display:list-item;">
		<A HREF="javaScript:void(0);" title="返回待办"
		onclick="parent.navTab.reload();"
			>
			返回待办
		</A>
	</li>
	</c:if>
	<c:if test="${not empty dashboard}">
    <li id="fhdb" style="display:list-item;">
		<A HREF="${ctx}/dispatchdoc/vuserTaskListOA!list.do" title="返回待办"
			>
			返回待办
		</A>
	</li>
	</c:if>
	<li id="fsyj" style="display:list-item;">
		<A HREF="javascript:void(0);" title="发邮件"
			onclick="addRT('${ctx}/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&from=public');return false;">
			发送邮件
		</A>
	</li>
	<li id="fstx" style="display:list-item;">
		<A HREF="javascript:void(0);" title="发提醒"
			onclick="addRT('${ctx}/oa/oaRemindInformation!builtV2.do?xzrc_sy=T');return false;">
			发送提醒
		</A>
	</li> 
	
	<li id="myDiv_fhdb">
		<A href="javascript: window.scrollTo(0, 0);">
			返回顶部
		</A>
	</li>
	</ul>
</div> 
 <!-- 右侧导航 -->
    <div class="nav_right">
		<div class="nav_right_link">
			 <input type="hidden" id="pdfFile" name="pdfFile" value="${pdfFile}" style="width:600px"/>
				<!-- 现场电子签章地址 -->
			 <input type="hidden" id="affixUrl" value="http://172.19.193.10/StampServer"/>
	 
			 <input type="hidden" id="stuffid" name="stuffid" value="${stuffid}" />
			 <input type="hidden" id="wj" name="wj" value="${wj}" />
			  <input type="hidden" id="type" name="type" value="${type}" />
			 <input type="hidden" id="uploadfilepath" name="uploadfilepath" value="${uploadfilepath}" />
			 
		   <c:forEach var="fInfo" items="${jspInfo.frameList}" varStatus="vs">
           <!-- 		   1查看权限验证  2ifram id为附件 3行政角色看附件  -->
		   <c:if test="${ 'stuffFrame' ne fInfo.frameId  or ('stuffFrame' eq fInfo.frameId  and  !isVailViewPower)  or  (isVailViewPower and 'stuffFrame' eq fInfo.frameId  and cp:CHECKUSEROPTPOWER('DOCXZ', 'DocViewStuff'))}">
		      <a href="#${fInfo.frameId}" class="${vs.index==0?'select':''}" onclick="scrollToAfter(this)"><label></label>${empty fInfo.frameLegend?'业务信息':fInfo.frameLegend}</a>
		   </c:if>
		   </c:forEach>
		</div>
		<div class="nav_right_button">
		  <!-- 		   1查看权限验证   2行政角色看办理过程  -->
		   <c:if test="${ !isVailViewPower  or  (isVailViewPower and  cp:CHECKUSEROPTPOWER('DOCXZ', 'DocViewAll'))}">
		    <c:if test="${hasRelSubject}">
		       <a href="javascript:void(0);" onclick="showView('关联事项','${ctx}/oa/oaBizBindInfo!listbiz4tab.do?djid=${djId}')">关联事项</a><br/>
		    </c:if>
			<a href="javascript:void(0);" onclick="showView('过程信息','${ctx}/powerruntime/generalOperator!listIdeaLogs.do?djId=${djId}')">过程信息</a><br/>
			 <!--   F--不显示流程图 -->
            <c:if test='${cp:MAPVALUE("SYSPARAM","isFlowShow") ne "F"}'>
			<c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('流程图','${ctx}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${flowInstId}')">流程图</a><br/>
			</c:if>
			</c:if>
			</c:if>
			<%-- <c:if test="${flowInstId !=9999999}">
			<a href="javascript:void(0);" onclick="showView('办件讨论','${ctx}/oa/oaLeaveMessage!replayList.do?s_djid=${djId}&s_infoType=OAGW')">办件讨论</a><br/>
			</c:if> --%>
			<c:if test="${'F' ne optBaseInfo.bizstate}">
			<a href="javascript:void(0);" onclick="proCollect(this,'${djId}')">${collstatus eq 'coll' ? '办件收藏' : '取消收藏'}</a><br>
			<c:if test="${'fw' eq type}">
			<a href="javascript:void(0);" onclick="DetachPdf()">打印盖章<br>正文PDF</a>
			</c:if>
			
			</c:if>
		</div>
	</div>
		<div class="flow-operator">
		<c:if test="${not empty prevDjId || not empty nextDjId}">
		   <ec:reqeustAttributeForm id="listParamForm" action="${ctx}/dispatchdoc/dispatchDoc!generalOptView.do"/>
		</c:if>
	<c:if test="${ not empty startDjid}"><input type="button" name="close" Class="btn"
				onclick="window.close();" value="关闭" /></c:if>	
	<c:if test="${'F' ne show_type and empty startDjid and empty dashboard}">
	   <c:if test="${not empty prevDjId}">
	   <input type="button" name="next" Class="btn"
			onclick="viewNeighbouringCase('${prevDjId}')" value="上一份" />
	   </c:if>
	   <c:if test="${not empty nextDjId}">
	   <input type="button" name="next" Class="btn"
			onclick="viewNeighbouringCase('${nextDjId}')" value="下一份" />
	   </c:if>
		<input type="button" name="back" Class="btn"
			onclick="parent.navTab.reload();" value="返回" />
	</c:if>
	<c:if test="${not empty dashboard and dashboard eq 'T'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'C'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do?s_bizstate=C" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'W'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/oa/vOptBaseList!list.do?s_bizstate=W" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'BMSW'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!listV2.do?show_type=T&s_bmsw=T" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'DWSW'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!listV2.do?show_type=T" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'BMFW'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!listV2.do?show_type=T&s_bmfw=T" 
			 						value='返回' /></c:if>
	<c:if test="${not empty dashboard and dashboard eq 'DWFW'}">
	<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#vOptBaselist_form" 
									data-href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!listV2.do?show_type=T" 
			 						value='返回' /></c:if>
  </div>
</body>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
function addRT(url) {
	art.dialog
			.open(url,
					 {title: '', width: 1050, height: 640});

}
function returnNewPage(type){
	if(type=="sy"){
	 	top.location.href="<%=request.getContextPath()%>/sys/mainFrame!showMain.do";
	}
}

function DetachPdf() 
{	
	doit2();
	if (pdfFile.value == "") {
		alert("请选择PDF文件");
		return;
	}
	var lID = 0;
	var nNum = 0;
	var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
	
	var lRet = obj.DetachPdf(pdfFile.value, 1, "0");
	if(lRet == 0)
		alert("脱密成功");
	else
		alert(obj.GetErrorMsg());
	SendPrintPDF();
	
}

function SendPrintPDF() 
{
	if (pdfFile.value == "") {
		alert("请选择PDF文件");
		return;
	}
	var lID = 0;
	var nNum = 0;
	var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
	
	var lRet = obj.SendPrintPDF(pdfFile.value, "", affixUrl.value + "/extend/interfaces/ReceivePrint.aspx");
	if(lRet == 0)
		alert("打印成功");
	else
		alert(obj.GetErrorMsg());
	
}

//自动下载正文生成PDF文件到C盘GZpdf文件夹下
function doit2(){
	var stuffid = $("#stuffid").val();
	var ado_stream = new ActiveXObject("ADODB.Stream");
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	//下载url地址
	var strUrl = "${pageContext.request.contextPath}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid="+stuffid;
	//保存本地的文件名与磁盘上文件名保持一直
	var wj = $('#wj').val();
	var strHelpFile = "C:\\GZpdf\\"+wj;
	xmlhttp.open("Get",strUrl,false);
	xmlhttp.send();
	ado_stream.Type = 1;
	ado_stream.open();
	ado_stream.Write(xmlhttp.responseBody);
	ado_stream.SaveToFile(strHelpFile,2);
	ado_stream.close();
	
} 

// 保存到xml附件，并且通过ajax 上传  
function SaveAttachment(){

	var upload_filename = $('#wj').val();//正文pdf文件名

	var localFilePath = "C:\\GZpdf\\"+upload_filename;//已盖章文件本地路径
	
	var uploadfilepath = $('#uploadfilepath').val();//上传服务器磁盘路径
	
    var upload_target_url = "${pageContext.request.contextPath}/powerruntime/generalOperator!doPost.do"; //调用后台上传文件方法 
      
    var strTempFile = localFilePath;  
    
    // 创建XML对象，组合XML文档数据  
    var xml_dom = createDocument();  
    
    xml_dom.loadXML('<?xml version="1.0" encoding="GBK" ?> <root/>');  
  
    // 创建ADODB.Stream对象  
    var ado_stream = new ActiveXObject("ADODB.Stream");
    // 设置流数据类型为二进制类型  
    ado_stream.Type = 1; // adTypeBinary  
    // 打开ADODB.Stream对象  
    ado_stream.Open();  
    // 将本地文件装载到ADODB.Stream对象中  
	//var strTempFile = "C:\\GZpdf\\"+upload_filename;
    ado_stream.LoadFromFile(strTempFile);  
    // 获取文件大小（以字节为单位）  
    var byte_size = ado_stream.Size; 
    // 设置数据传输单元大小为1KB  
    var byte_unit = 1024;  
    // 获取文件分割数据单元的数量  
    var read_count = parseInt((byte_size/byte_unit).toString())+parseInt(((byte_size%byte_unit)==0)?0:1);  
      
    // 创建XML元素节点，保存上传文件名称  
    var node = xml_dom.createElement("uploadfilename");  
    node.text = upload_filename.toString();  
    var root =  xml_dom.documentElement; 
    root.appendChild(node);  
    // 创建XML元素节点，保存上传文件路径  
    var filepathnode = xml_dom.createElement("uploadfilepath");  
    filepathnode.text = uploadfilepath.toString();  
    root.appendChild(filepathnode);  

    var sizenode = xml_dom.createElement("uploadfilesize");  
    sizenode.text = byte_size.toString();  
    root.appendChild(sizenode); 
    
    // 创建XML元素节点，保存上传文件内容  
    for(var i=0;i<read_count;i++)  
    {  
        var node = xml_dom.createElement("uploadcontent");  
        // 文件内容编码方式为Base64  
        node.dataType = "bin.base64";  
        // 判断当前保存的数据节点大小，根据条件进行分类操作  
        if((parseInt(byte_size%byte_unit)!=0)&&(i==parseInt(read_count-1)))  
        {  
            // 当数据包大小不是数据单元的整数倍时，读取最后剩余的小于数据单元的所有数据  
            node.nodeTypedValue = ado_stream.Read();  
        }  
        else  
        {  
            // 读取一个完整数据单元的数据  
            node.nodeTypedValue = ado_stream.Read(byte_unit);  
        }  
        root.appendChild(node);  
    }  
    // 关闭ADODB.Stream对象  
    ado_stream.Close();  
    delete ado_stream;  
    // 创建Microsoft.XMLHTTP对象  
    // var xmlhttp = new ActiveXObject("microsoft.xmlhttp");  
    var xmlhttp= window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHttp");  
    // 打开Microsoft.XMLHTP对象  
    xmlhttp.open("post",upload_target_url,false);  
    // 使用Microsoft.XMLHTP对象上传文件  
    xmlhttp.send(xml_dom);  
    var state = xmlhttp.readyState;  
    var success_state = true; 
    if(state != 4){  
        success_state = false;  
    }  
      
    delete xmlhttp;  
      
    return success_state;  
}  

// 创建DOMdocuemnt  
function createDocument() {  
    var xmldom;  
    var versions = ["MSXML2.DOMDocument.6.0", "MSXML2.DOMDocument.5.0","MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument"],  
        i, len;  
    for (i = 0, len = versions.length; i < len; i++) {  
        try {  
            xmldom = new ActiveXObject(versions[i]);  
            if(xmldom != null)  
                break;  
        } catch (ex) {  
            //跳过  
            alert("创建document对象失败！");  
        }  
    }  
    return xmldom;  
}  



function viewNeighbouringCase(djId){
	var djIdInput = $("#listParamForm").find("input[name='djId']"),
	nodeInstIdInput = $("#listParamForm").find("input[name='nodeInstId']");
	if(djIdInput.length == 0){
		djIdInput = $("<input>",{"name":"djId","type":"hidden"});
		$("#listParamForm").append(djIdInput);
	}
	if(nodeInstIdInput.length == 0){
		nodeInstIdInput = $("<input>",{"type":"hidden","name":"nodeInstId"});
		$("#listParamForm").append(nodeInstIdInput);
	}
	djIdInput.val(djId);
	nodeInstIdInput.val(0);
	var type = djId.replace(/[0-9]/g,'');
	if(type=='FW'){
		$("#listParamForm").attr("action","${ctx}/dispatchdoc/dispatchDoc!generalOptView.do");
	}else if(type=='SW'){
		$("#listParamForm").attr("action","${ctx}/dispatchdoc/incomeDoc!generalOptView.do");
	}else if(type=="CARSQ"){
		$("#listParamForm").attr("action","${ctx}/oa/oaCarApply!generalOptView.do");
	}
	else{
		alert("无法识别服务器地址");
		return;
	}
	$("#listParamForm").submit();
}
</script>
</html>