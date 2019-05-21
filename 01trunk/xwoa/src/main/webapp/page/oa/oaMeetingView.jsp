<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaMeetingmaterial.edit.title" /></title>
  <style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     .group-title{position:relative;height:22px;}
	.group-title div{position:absolute;height:22px;top:0;line-height:22px;font-size:16px;font-weight:bold}
	.group-title .title-ico{width:5px;background:#56b9fd;z-index:1;left:0;}
	.group-title .title-name{z-index:1;left:5px;background:#fff;padding:0 10px;}
	.group-title .title-split-line{width:100%;z-index:0;left:0;padding-top:0px;position:relative;height: 22px; 
				background: url("${ctxStatic}/image/special-title-line.png") repeat-x center ;}
	span.span_state{float: right;margin: 5px 30px;}
	span.span_state1{float: left;margin: 5px 30px;}
	span.span_state a{color:#000;cursor:pointer;font-size:14px}
	span.remindIco{background-position: center;display: inline-block;background-repeat:no-repeat;width: 26px; height: 26px}
	span.remindIco-overdue{background-image: url("${ctxStatic}/image/ycqclock.gif")}
	span.remindIco-toOverdue{background-image: url("${ctxStatic}/image/jjcqclock.gif")}
	span.remindIco-none{display:none}
  </style>
</head>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<body>
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">会议资料</div>
	     <div class="title-split-line"></div>
   </div>
   <div class="form-container">
  
	<fieldset class="form">
	 <h2 style="color:red;font-family: Microsoft YaHei;text-align:center">${meetApplyName}</h2>
		<s:form action="oaMeetingApply" method="post" namespace="/oa" id="oaMeetingApplyForm">
			<input type="button" class="btn" value="返回" onclick="renturns()">
			<input type="hidden" id="mId" name="mId" value="${object.mId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议名称</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetApplyName}" /></td>
				</tr>

				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议地点</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetApplyAddress}" /></td>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">会议时间</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="meetApplytime" format="yyyy-MM-dd HH:mm" /></td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">参会单位</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{attendUnitName}" /></td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来人员</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{foreigUserName}" /></td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">外来部门</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{foreigUnitName}" /></td>
				</tr>
				
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center; height:40px;font-weight: bold;width: 100px;">备注1</td>
					<td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingRemark}" /></td>
				</tr>
			</table>
		</s:form>
		<div id = "tabdiv">
                        <table width="600" cellpadding="0" cellspacing="0" class='table_border'> 
   								<thead style="display: none;">  
     								 <tr style="border:1px solid;">
     								 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 5%;">序号</th>
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">议题名称</th>  
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">参会人员</th>  
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 15%;">操作</th>  
      									</tr>   
  										 </thead>    
   									<tbody id="tbMain"></tbody>
							</table> 
                 </div>   
	</fieldset>
	</div>
	<%-- <br>
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">议程</div>
	     <div class="title-split-line"><span></span></div>
   </div>
	<fieldset>
		<div class="improvecustom" style="height: 92%;">
			<ec:table items="oaMeetingmaterialinfos" var="doc" sortable="false"
			showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			tableId="uu">
			<ec:row>
				<ec:column property="initalStuffId" title="原始附件" style="text-align:center">
				<a style="text-decoration: underline; color: blue;" title="${docFile.filename}"  onclick="downFile('${doc.initalStuffId}');return false;">
					${doc.optStuffInfoBeg.filename}</a>
				</ec:column>
				<ec:column property="stuffId" title="签阅后附件" style="text-align:center">
				<c:if test="${doc.isback eq 'T' and  not empty doc.remark }">
				<a style="text-decoration: underline; color: blue;" title="${docFile.filename}" onclick="downFile('${doc.cid.stuffId}');return false;">
					${doc.optStuffInfoEnd.filename}</a>
				</c:if>
				</ec:column>
				<ec:column property="backtime" title="提交时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date">
				</ec:column>
				<ec:column property="remark" title="会议记录内容" style="text-align:center"/>
			</ec:row>
		</ec:table>
		</div>
	</fieldset> --%>
<script>
	$(function(){
		var mId = '${object.mId}';
		 $.ajax({
	    		url:"<%=request.getContextPath()%>/oa/oaMeetingmaterial!queryMeet.do",
	    		type:"POST",
	    		data:{"ids":mId},
	    		async: false,
	    		dataType:"json",
	    		success:function(datas){
	    			 debugger;
	    			 var tbody = document.getElementById('tbMain');
	    			 var tabdiv = document.getElementById('tabdiv');
	    			 $(tabdiv).find('thead').show();
	    			 gettable(tbody,datas);
	    		},
	    		error:function(a,b,c){
	    			DialogUtil.alert("撤回出错，请联系管理员。");
	    			mark = false;
	    		}
	    	});
	});
	
	function gettable(tbody,datas){
		 for(var i = 0;i < datas.length +1; i++){ //遍历一下json数据
			 var oaderId = datas[i].orderId;
				var meetingName = datas[i].meetingName;
				var meetingAttendees = datas[i].meetingAttendees;
					 var tr= '<tr style="border:1px solid;">'+
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 5%;">'+oaderId+'</td>' +
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingName+'</td>' +
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingAttendees+'</td>' +
						'<td class="del-td" style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 15%;">'+
							'<button onclick="del_tdd(this,value)" type="button" value = "'+ datas[i].djId +'" id="del_td" style="widht:20px;height:20px;">删除</button>'
						'</td>' +
					'</tr>';
		         $(tbody).append(tr);  
	        
	       }   
		 
	}
	
	
function info(meetingAttendee,djId) {
	var url ="<%=request.getContextPath()%>/oa/oaMeetingmaterialinfos!meetingDownFile.do?meetingAttendee="+ meetingAttendee+"&djId="+ djId;
	DialogUtil.open("会议材料附件查看",url,600,400);
}

//返回按钮
function renturns(){
	window.location.href='${pageContext.request.contextPath}/oa/oaMeetingApply!list.do';
}

//点击触发下载文件
function downFile(id) {
	$.ajax({
		type : "POST",
		async : false,
		dataType : "json",
		url : "${ctx}/powerruntime/generalOperator!fileNull.do?stuffInfo.stuffid=" + id,
		success : function(json) {
			//alert(1);
			var url1 = "${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=" + id;
			document.location.href = url1;
		},
		error : function() {
			alert("系统找不到此文件！");			
		}
	});
}
function showgaoji(){
	
	$("#tr_notOpenReason").show();
	$("#saveProxy").show()
}
$(function(){
	$("#meetingProxys").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#meetingProxys"),
							$("#meetingProxyCodes"));
				}
				;
			});
});

function selectPopWin(json, o1, o2, oShow) {
	new treePerson(json, o1, o2, oShow).init();
	setAlertStyle("attAlert");
}
</script>