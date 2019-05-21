<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
	收文登记[在用]
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	
h1 {
	text-align:center;
	font-size:2.2em;
}
.divc {
	border:1px solid #ECE2E2;
}
.des {
	width:500px;
	background-color:lightyellow;
	border:1px solid #555;
	padding:30px;
	margin-top:30px;
}
.mouseover {
	color:#ffffff;
	background-color:highlight;
	width:99.9%;
	cursor:default;
}
.mouseout {
	color:#666666;
	width:99.9%;
	background-color:#ffffff;
	cursor:default;
}
	</style>
	
	</head>

	<body class="sub-flow">
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="incomeDoc" method="post" namespace="/dispatchdoc" id="incomeDocForm">
<%-- 		    <c:if test="${empty show_type}"> --%>
<%-- 			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
<%-- 			</c:if> --%>
			<c:if test="${show_type eq 'F' }">
				<input type="button" name="back" Class="btn"
					onclick="window.location.href='${pageContext.request.contextPath}/powerbase/supPower!SQlist.do?s_itemType=SQ';" 
					value="返回" />
			</c:if>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="optId" name="optId" value="${optId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
			<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId" value="${flowInstId}" />	
			<input type="hidden" id="m" name="m"/>	
			<!-- 			文号						 -->
<!--            <input type="hidden" id="incomeDeptType" name="incomeDeptType" value="XZ31" /> -->
           <input type="hidden" id="incomeDocType" name="incomeDocType" value="SWXZ" />						
			<fieldset class="_new">
				<legend>收文登记 [ ${object.optBaseInfo.powername}]</legend>
					
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
						<input type="hidden" id="itemId" name="itemId" value="${object.optBaseInfo.powerid}">
							<tr>
							<td class="addTd">
								文件标题<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocTitle" name="incomeDocTitle" maxlength="200" value="${object.incomeDocTitle}" style="width: 100%;height:30px;"/>
							</td>
							
							</tr>
						
						<tr>
							<td class="addTd">
								发文机关<font color="#ff0000">*</font>
							</td>
							<td align="left" style="position:relative;z-index:100;">
								<input type="text" id="incomeDeptName" name="incomeDeptName" maxlength="200" 
								value="${object.incomeDeptName}" style="width: 100%;height:30px;"
								onkeyup="incomeDeptAuto.handleEvent(this.value,'incomeDeptName',event)"/>
								<div id="deptDivc" class="divc" >
								</div>
							</td>	
								<td class="addTd">
								发文<span style="font-family:Simsun !important">字号</span>
                             <!-- 								<font color="#ff0000">*</font> -->
							</td>
							<td align="left">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" style="width: 100%;height:30px;"/>
							</td>					
												
						</tr>
		
				<tr>
						<td class="addTd">
							收文编号 
						</td>
						<td align="left" style="position:relative">
							<input type="hidden" id="acceptArchiveNo" name="optBaseInfo.acceptArchiveNo"
							value="${object.optBaseInfo.acceptArchiveNo}" />
							
							<c:if test="${object.itemId eq 'XW-SW-0001'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="示范区办字" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0002'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="示范区安监办" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0003'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="示范区环保办" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0004'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="示范区党群办" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0008'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="示范区建办" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0013'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
							<c:if test="${object.itemId eq 'XW-SW-0014'}">
							<input style="height:30px;" type="text" name="acceptArchiveNoPrex" id="acceptArchiveNoPrex" value="国资办" placeholder="请输入来文单位分类" onblur="acceptArchiveNoMouseout();"
								onkeyup="incomeAcceptAuto.handleEvent(this.value,'acceptArchiveNoPrex',event)"/>	
							</c:if>
								<div id="acceptDivc" class="divc">
								</div>
							<span id="acceptArchiveNoYear"></span>		
							<input style="height:30px;" type="text" id="acceptArchiveNumber" value="${object.acceptArchiveNumber}" name="acceptArchiveNumber" placeholder="请输入编号" onblur="acceptArchiveNoMouseout();"/>
							<span id="acceptArchiveNoSuffix">号</span>
						</td>				
							<td class="addTd">
								收文日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<input type="text" class="Wdate" 
							style="width: 200px;height:30px; border-radius: 3px; border: 1px solid #cccccc;"
							 value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							id="incomeDate" name="incomeDate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" 
							 onpropertychange="changeSwh();"
							 placeholder="选择日期">
							</td>
							</tr>
						<tr>
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" >
						<select id="criticalLevel" name="criticalLevel" style="width: 200px;height:30px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}">
								<option value="${row.key}" ${(object.criticalLevel eq row.key or (empty object.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
								办结截止日期
							</td>
							<td align="left">
							<input type="text" class="Wdate" 
							style="width: 200px;height:30px; border-radius: 3px; border: 1px solid #cccccc;"
							 value='<fmt:formatDate value="${object.toBeanfinishedDate}" pattern="yyyy-MM-dd"/>'
							id="toBeanfinishedDate" name="toBeanfinishedDate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" 
							 onpropertychange="changeSwh();"
							 placeholder="选择日期">
							</td>		
						</tr>
						<tr>
							<td class="addTd">
								备注
							</td>
							<td align="left" colspan="3">
								<input type="text" id="remark" name="remark" maxlength="200" value="${object.remark}" style="width: 100%;height:30px;"/>
							</td>
							
						</tr>

				<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen')}">
					<tr>
						<td class="addTd">短信提醒&nbsp;</td>
						<td align="left" colspan="3"><input id="isSendMsgView"
							class="checkboxClass" type="checkbox" name="isSendMsgView" />短信提醒下一步操作人员
							<input type="hidden" id="isSendMessage" name="isSendMessage" />

						</td>
					</tr>
				</c:if>
			<!-- 	<tr>
							<td colspan="4" align="center">
							<font color="blue">温馨提示：阅办文编号可支持手动修改；如需更改不同年度的阅办文编号，请选择对应年度收文日期进行调整。</font>
							</td>											
						</tr> -->
	</table>
				</fieldset>			
				
		<br/><br/>
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		<div align="center" style="margin-right: 20px;margin-top: 20px;">
					<input type="button" class="btn" id="saveBtn" value="保存" onclick="submitItemFrame();" />
					<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
				</div>
		</s:form>
	</body>
		<script
src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script src="${pageContext.request.contextPath}/newStatic/js/custom/common/autoSelect.js" type="text/javascript"></script>
	<script type="text/javascript">
	//加载完成后获得来文单位分类下拉列表  by dk 2016-01-05
	function cancel() {
		DialogUtil.prototype.close();
	}
	function checkForm(){
		if ("" == $.trim($("#incomeDocTitle").val())) {
			alert("文件标题不可为空！");
			$('#incomeDocTitle').focus();
			return false;
		}
		if ("" == $.trim($("#incomeDeptName").val())) {
			alert("发文机关不可为空！");
			$('#incomeDeptName').focus();
			return false;
		}
		
		if("" != $.trim($("#acceptArchiveNoPrex").val())||"" != $.trim($("#acceptArchiveNumber").val())){
			if ("" == $.trim($("#acceptArchiveNoPrex").val())||"" == $.trim($("#acceptArchiveNumber").val())) {
				alert("请填写完整收文编号！");
				$('#acceptArchiveNoPrex').focus();
				return false;
			}
		
		}
		
		if ("" == $.trim($("#incomeDate").val())) {
			alert("收文日期不可为空！");
			$('#incomeDate').focus();
			return false;
		}
		
		
		 if(acceptArchiveNoMouseout()==false){
			return false;
		} 
		return true; 	
	}
	function submitItemFrame() {
		debugger;
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("incomeDocForm");
			srForm.action = 'incomeDoc!saveRegisterDoOrRead.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
				$.ajax({
	                type: "post",
	                url: srForm.action,     
	                data: $("#incomeDocForm").serialize(),
	                async: false,
	                success: function(data) {
	               		// 如果父页面重载或者关闭其子对话框全部会关闭
	               		win.location.reload(true);
	               		//win.frames('stuffFrame').location.reload();
	                },
	                error: function(data) {
	                    DialogUtil.alert("修改信息失败，请重新尝试！");
	                }
	            });
			}
		}
	}
	
	$().ready(function(){
// 		var val=$('input:radio[name="incomeDocType"]:checked').val();
        var val=$('#incomeDocType').val();
		showDepType(val);
		//设置选中项
		if('${object.incomeDeptType}'!=null&&'${object.incomeDeptType}'!=''){
			setTimeout(function(){
				var count = $("#incomeDeptType option").length;  
	        	for ( var i = 0; i < count; i++) {  
	            	if ($("#incomeDeptType ").get(0).options[i].value == '${object.incomeDeptType}') {  
	               	 	$("#incomeDeptType ").get(0).options[i].selected = true;  
	               		 break;  
	            	}  
	            }
	        },1000);
		}
		
		if(null == $('#acceptArchiveNoYear').text() || '' == $('#acceptArchiveNoYear').text()){
			$('#acceptArchiveNoYear').html('〔' + new Date().getFullYear() + "〕");
		}
		//收文编号分割
		//split();
		splitV2();
		//收文编号验证
		acceptArchiveNoMouseout();
		
	});
	
	$(function(){
	});
	//收文流程文书分类来文单位互动
	$("input:radio[name='incomeDocType']").click(function(){
// 		var val=$('input:radio[name="incomeDocType"]:checked').val();
		var val=$('#incomeDocType').val();
		showDepType(val);
		setTimeout('changeSwh();',1000);
		var v=$("#incomeDeptType").val();
	}) ;
	
	function showDepType(val){
		var refUrl ="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!incomeDoc.do?datacode="+val;
		
		$.get(refUrl, function(jsonList) {
			$("#incomeDeptType").find('option').remove();
			
			for ( var i = 0; i < jsonList.length; i++) {
				var key = jsonList[i].key;
				var value = jsonList[i].value;
				$("#incomeDeptType").append('<option value="'+key+'">'
						+ value + '</option>');
			}

		}, "json");
		
	};
	
	
	
	
	
	$("#incomeDeptType").click(function(){
// 		var incomeDocType=$('input:radio[name="incomeDocType"]:checked').val();
        var incomeDocType=$('#incomeDocType').val();
		var incomeDeptType=$('#incomeDeptType').val();	
		var swdate = $.trim($("#incomeDate").val());
		//不同字典项相同名字的部门类型值也不一样
		 if(incomeDeptType=='${object.incomeDeptType}'&&swdate=='${object.incomeDate}'){
		$("#acceptArchiveNo").val('${object.optBaseInfo.acceptArchiveNo}');
		split();
		 }
		else if (incomeDeptType) { 
			incomeDeptTyeajax(incomeDocType,incomeDeptType,swdate);
		}
		
	});
	function changeSwh() {
// 		var incomeDocType=$('input:radio[name="incomeDocType"]:checked').val();
         var incomeDocType=$('#incomeDocType').val();
		var incomeDeptType=$('#incomeDeptType').val();	
		var swdate = $.trim($("#incomeDate").val());
		
			incomeDeptTyeajax(incomeDocType,incomeDeptType,swdate);
		 $.ajax({
		type : "POST",
		url :  "${contextPath}/dispatchdoc/incomeDoc!changeSwh.do?incomeDocType=" + incomeDocType+"&incomeDeptType=" + incomeDeptType+"&swdate=" +swdate,
		dataType : "json",
		success : function(json) {
			//debugger;
		$("#acceptArchiveNo").val(json.status);
		split();
		},
		error : function() {
		alert("检查来文类别是否选择正确");
		}
		}); 
		
		}
	function incomeDeptTyeajax(incomeDocType,incomeDeptType,swdate)
	{
		$.ajax({
			type : "POST",
			url :  "${contextPath}/dispatchdoc/incomeDoc!changeSwh.do?incomeDocType=" + incomeDocType+"&incomeDeptType=" + incomeDeptType+"&swdate=" +swdate,
			dataType : "json",
			success : function(json) {
				//debugger;
			$("#acceptArchiveNo").val(json.status);
			split();
			},
			error : function() {
			alert("检查来文类别是否选择正确");
			}
			});
	}
		function editShip(djId, no) {
			JDialog.open({
				src : "${contextPath}/dispatchdoc/incomeDoc!editShip.do?djId=" + djId + "&ship.no=" + (no ? no : ""),
				width : 800,
				height : 350
			});
		}
	
		function deleteShip(self, djId, no) {
			var result = $.ajax({
				url: "${contextPath}/dispatchdoc/incomeDoc!deleteShip.do?djId=" + djId + "&ship.no=" + no,
				async: false
			}).responseText;
			if ("success" == result) {
				$(self).parent().parent().remove();
			} else {
				alert("删除失败");
			}
		}
	
		function doSubmitCheck(subType) {	
		
			if ("" == $.trim($("#incomeDocTitle").val())) {
				alert("文件标题不可为空！");
				$('#incomeDocTitle').focus();
				return false;
			}
			if ("" == $.trim($("#incomeDeptName").val())) {
				alert("发文机关不可为空！");
				$('#incomeDeptName').focus();
				return false;
			}
			
			if("" != $.trim($("#acceptArchiveNoPrex").val())||"" != $.trim($("#acceptArchiveNumber").val())){
				if ("" == $.trim($("#acceptArchiveNoPrex").val())||"" == $.trim($("#acceptArchiveNumber").val())) {
					alert("请填写完整收文编号！");
					$('#acceptArchiveNoPrex').focus();
					return false;
				}
			
			}
			
			if ("" == $.trim($("#incomeDate").val())) {
				alert("收文日期不可为空！");
				$('#incomeDate').focus();
				return false;
			}
			
			
			 if(acceptArchiveNoMouseout()==false){
				return false;
			} 
			 if("" == $.trim($("#acceptArchiveNoPrex").val())&&"" == $.trim($("#acceptArchiveNumber").val())){
					$('#acceptArchiveNo').val("");
				}
			var srForm = document.getElementById("incomeDocForm");
			if (subType == 'SAVE') {
				srForm.action = 'incomeDoc!saveRegisterDoOrRead.do?save=save';
			}

			if (subType == 'SUB') {
				srForm.action = 'incomeDoc!saveAndSubmitRegisterDoOrRead.do';
			}
			srForm.submit();
		}
		function getStringLen(Str) {
			var i, len, code;
			if (Str == null || Str == "")
				return 0;
			len = Str.length;
			for (i = 0; i < Str.length; i++) {
				code = Str.charCodeAt(i);
				if (code > 255) {
					len++;
				}
			}
			return len;
		}
		
		function refreshShip(jsonArray) {
			$("#ship_list > tr:gt(0)").remove();
			if (jsonArray && jsonArray.length > 0) {
				for (var i=0; i<jsonArray.length; i++) {
					var shipObj = jsonArray[i];
					var buffer = new StringBuffer();
					buffer.append("<tr><td>" + shipObj.leaderName + "</td><td>" + shipObj.unitName + "</td><td></td><td></td><td></td></tr>");
					$("#ship_list").append(buffer.toString());
				}
			}
		}
		function acceptArchiveNoMouseout(){
			//splice();
			spliceV2();
			var t=false;
			var acceptArchiveNo=$("#acceptArchiveNo").val();
			if(acceptArchiveNo!=null&&acceptArchiveNo!=''){
				var djId=$("#djId").val();
				var itemId=$("#itemId").val();
				$.ajax({
					async: false,
					type : "POST",
					url :  "${contextPath}/dispatchdoc/incomeDoc!acceptArchiveNoVali.do",
					data: {"djId":djId,"acceptArchiveNo":acceptArchiveNo,"itemId":itemId},
					dataType : "json",
					success : function(json) {
						if(json.msg=="F"){
							alert("该收文编号已存在，请确认后再次输入！");
							}
						else if(json.msg=="T"){
							t= true;
						}		
					},
					error : function() {
					alert("检查收文编号是否正确");
					}
					});
			}
			return t;
		}
		//分割
		function split(){
			var acceptArchiveNo=$('#acceptArchiveNo').val();
			 var s=acceptArchiveNo.split("〕");
			 if(s.length>1){
				 var title=s[0]+"〕";
				 var tail=acceptArchiveNo.substring(acceptArchiveNo.length-1, acceptArchiveNo.length);
				 var num=s[1].substring(0,s[1].length-1);
				 $("#acceptArchiveNoTitle").text(title);
				 $("#acceptArchiveNoTail").text(tail);
				 $("#acceptArchiveNoNum").val(num);
				
			 }
			 acceptArchiveNoMouseout();
		}
		//拼合
		function splice(){
			var title=$("#acceptArchiveNoTitle").text();
			var tail=$("#acceptArchiveNoTail").text();
			var num= $("#acceptArchiveNoNum").val();
			$("#acceptArchiveNo").val(title+$.trim(num)+tail);
		}
		
		function splitV2(){
			
			var acceptArchiveNo=$('#acceptArchiveNo').val();
			var array1 = acceptArchiveNo.split('〔');
			if(array1.length >1){
				$("#acceptArchiveNoPrex").val(array1[0]);
				var yearAndNum = array1[1];
				var array2 = yearAndNum.split('〕');
				if(array2.length >1){
					$('#acceptArchiveNoYear').text('〔' + array2[0] + '〕');
					$('#acceptArchiveNumber').val(array2[1].substring(0, array2[1].length -1));
				}
			}
			 acceptArchiveNoMouseout();
		}
		
		// 拼接阅办文编号(手动输入来文单位和编号)
		function spliceV2(){
			var prex=$("#acceptArchiveNoPrex").val();
			var year = $('#acceptArchiveNoYear').text();
			var number = $('#acceptArchiveNumber').val();
			var suffix=$("#acceptArchiveNoSuffix").text();
			$("#acceptArchiveNo").val($.trim(prex)+ year + $.trim(number) + suffix);
		}
		
		$('#isSendMsgView').click(function(){
			var $this = $(this);
			if($this.attr('checked') == 'checked'){
				$('#isSendMessage').val('T');
			}else{
				$('#isSendMessage').val('F');
			}
		});
		
		var incomeDeptAuto = new jsAuto("incomeDeptAuto","deptDivc");
		var incomeAcceptAuto = new jsAuto("incomeAcceptAuto","acceptDivc");
		incomeDeptAuto.item('${incomeDeptNames}');
		incomeAcceptAuto.item('${incomeAcceptNos}');
		
		// 失去焦点时不显示匹配框
		/* $('#incomeDeptName').blur(function(){
			incomeDeptAuto.doclick($(this).val());
		});
		$('#acceptArchiveNoPrex').blur(function(){
			incomeAcceptAuto.doclick($(this).val());
		}); */
	</script>
</html>

