<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaMeetingmaterial.edit.title" /></title>
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

<body  class="sub-flow">
	<fieldset class="form">
		<legend>
			<p>编辑会议资料</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaMeetingmaterial" method="post" namespace="/oa"
			id="oaMeetingmaterialForm">
			<input type="button" class="btn" value="保存" onclick="add('SUB')">
			<input type="button" class="btn" value="关闭" onclick="window.close();">
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="mId" name="mId" value="${mId}" />
			
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
				<tr>
					<td class="addTd">议程名称<span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textfield name="meetingName"
							style="width:100%;height:30px;" /></td>
				</tr>
				
				<tr>
				<td class="addTd">汇报人</td>
					<td align="left" ><s:textfield name="reportName"
							style="width:100%;height:30px;" /></td>
					<td class="addTd">序号<span
						style="color: red">*</span></td>
					<td align="left" ><s:textfield name="orderId"
							style="width:100%;height:30px;" /></td>
				</tr>
				

				<tr id="tr_receive">
					<td class="addTd">参与人员<span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><input type="text" readonly="readonly"
						id="meetingAttendees" name="meetingAttendees" value="${object.meetingAttendees }"
						class="focused" style="width:100%;height:30px;" > 
						<input id="meetingAttendeesCodes" type="hidden" name="meetingAttendeesCodes"
						 value="${meetingAttendeesCodes }" />
						</td>
				</tr>
				<tr>
					<td class="addTd">参与部门</td>
					<td align="left" colspan="3"><s:textarea
							name="meetingUnitnames" style="width:100%;height:30px;" /></td>
				</tr>

				<tr>
				<td class="addTd">外来人员</td>
					<td align="left" colspan="3"><s:textarea
							name="meetingComein" style="width:100%;height:30px;" /></td>
				</tr>
				<tr>
				<td class="addTd">外来单位</td>
					<td align="left" colspan="3"><s:textarea
							name="meetingComeindeps" style="width:100%;height:30px;" /></td>
				</tr>
			</table>
						<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 0px; left: -19999px; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						<ul></ul>
					</div>
					<div id="t-b-arrow">
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</s:form>
	</fieldset>
	<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqclOfBookPage.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=394"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		<br><br><br>
		</body>
		
	<script type="text/javascript">
	
	$("#meetingAttendees").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#meetingAttendees"),
							$("#meetingAttendeesCodes"));
				}
				;
			});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	
	//提交按钮
	var parentDocument = window.opener.document;
	function add(subType){
		if(docheck()){
			var srForm  = document.getElementById("oaMeetingmaterialForm");
			if(subType == 'SUB'){
				debugger;
				srForm.action = 'oaMeetingmaterial!save.do';
				$.ajax({
	                type: "post",
	                url: srForm.action,     
	                data: $("#oaMeetingmaterialForm").serialize(),
	                async: false,
	                success: function(data) {
	               		// 如果父页面重载或者关闭其子对话框全部会关闭
	               		debugger;
	               		var tbody = parentDocument.getElementById('tbMain');
    			 		var tabdiv = parentDocument.getElementById('tabdiv');
    			 		//var thead = parentDocument.getElementById('tbhead');
    			 		//$(tabdiv).find('thead').show();
    					gettable(tbody);
	                	window.close();
	    				
	                },
	                error: function(data) {
	                    DialogUtil.alert("修改信息失败，请重新尝试！");
	                }
	            });
				
				
			}
	
	}
	 	
	}
	function docheck() {
		debugger;
		var reg="^[1-9]\\d*$";
		if(document.oaMeetingmaterialForm.orderId.value == ""){
			 alert("议题序号不能为空");
	            $("#orderId").focus();
	            return false;
		}else{
			var isvalid= (new RegExp(reg)).test(document.oaMeetingmaterialForm.orderId.value);
	        if(!isvalid){
	            alert("排序号必须为数字");
	            return false;
	        }
			
		}
	      if( document.oaMeetingmaterialForm.meetingName.value == ""){
	            alert("议题名称不能为空");
	            $("#meetingName").focus();
	            return false;
	        }
	        if( document.oaMeetingmaterialForm.meetingAttendees.value == ""){
	            alert("参会人员不能为空");
	            return false;
	        }
			return true;
	}
	function gettable(tbody){
		debugger;
		var oaderId = document.oaMeetingmaterialForm.orderId.value;
		var meetingName = document.oaMeetingmaterialForm.meetingName.value;
		var meetingAttendees = document.oaMeetingmaterialForm.meetingAttendees.value;
		var djId = '${object.djId}';
		 var tr= '<tr style="border:1px solid;">'+
			'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 5%;">'+oaderId+'</td>' +
			'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingName+'<input type="hidden" value="'+djId+'"></td>' +
			'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingAttendees+'</td>' +
			'<td class="del-td" style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 15%;">'+
			'<button onclick="edit_tdd(this,className)" type="button" class = "${object.djId}" id="edit_tdd" style="widht:20px;height:20px;">编辑</button>' +
			'&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="view_tdd(this,className)" type="button" class = "${object.djId}" id="view_tdd" style="widht:20px;height:20px;">查看</button>' +
				'&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="del_tdd(this,className)" type="button" class = "${object.djId}" id="del_td" style="widht:20px;height:20px;">删除</button>' 
			'</td>' +
		'</tr>';
	         $(tbody).append(tr);  
	        
		 
	}
</script>
</html>