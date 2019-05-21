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



<body class="sub-flow">
	<fieldset class="form">
		<legend class="headTitle">
			编辑会议资料
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
					<td align="left" colspan="3"><s:textfield name="meetingName" value="%{object.meetingName}" 
							style="width:100%;height:30px;" /></td>
				</tr>
				
				<tr>
				<td class="addTd">汇报人</td>
					<td align="left" ><s:textfield name="reportName" value="%{object.reportName}" 
							style="width:100%;height:30px;" /></td>
					<td class="addTd">序号</td>
					<td align="left" ><s:textfield name="orderId" value="%{object.orderId}" 
							style="width:100%;height:30px;" /></td>
				</tr>
				

					<tr id="tr_receive">
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingAttendees" /><span
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
							name="meetingUnitnames" value="%{object.meetingUnitnames}" style="width:100%;height:30px;" /></td>
				</tr>

				<tr>
				<td class="addTd">外来人员</td>
					<td align="left" colspan="3"><s:textarea
							name="meetingComein"  value="%{object.meetingComein}" style="width:100%;height:30px;" /></td>
				</tr>
				<tr>
				<td class="addTd">外来单位</td>
					<td align="left" colspan="3"><s:textarea
							name="meetingComeindeps" value="%{object.meetingComeindeps}" style="width:100%;height:30px;" /></td>
				</tr>
			</table>
			
						<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
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
	<script type="text/javascript">
	$(function(){
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
    					//$(tabdiv).find('thead').show();
    					//$(tbody).html('');
    					//var table =  $(tabdiv).find("tbody");
    		            
    		           // table.empty();
    					for(var i=0;i<tbody.children.length;i++){
    						var djId = tbody.children[i].children[3].children[0].className;
    						var name = tbody.children[i].children[1].innerText;
    						if(djId == '${object.djId}'){
    							tbody.children[i].children[0].innerText = document.oaMeetingmaterialForm.orderId.value;
    							tbody.children[i].children[1].innerText = document.oaMeetingmaterialForm.meetingName.value;
    							tbody.children[i].children[2].innerText = document.oaMeetingmaterialForm.meetingAttendees.value;
    							//tbody.children[i].children[1].children[0].value = 
    						}
    					}
    					//gettable();
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
	function gettable(){
		debugger;
		var mId = $("#mId").val();
		var mId = '${mId}';
		 $.ajax({
	    		url:"<%=request.getContextPath()%>/oa/oaMeetingmaterial!queryMeet.do",
	    		type:"POST",
	    		data:{"id":mId},
	    		async: false,
	    		dataType:"json",
	    		success:function(datas){
	    			 debugger;
	    			 var tbody = document.getElementById('tbMain');
	    			 var tabdiv = document.getElementById('tabdiv');
	    			 $(tabdiv).find('thead').show();
	    			 var tbody = $(tabdiv).find('tbody');
	    			 gettable1(tbody,datas);
	    		},
	    		error:function(a,b,c){
	    			DialogUtil.alert("撤回出错，请联系管理员。");
	    			mark = false;
	    		}
	    	});
		
		
		
	}
	
	function gettable1(tbody,datas){
		debugger;
		 for(var i = 0;i < datas.length; i++){ //遍历一下json数据
			 var oaderId = datas[i].orderId;
				var meetingName = datas[i].meetingName;
				var meetingAttendees = datas[i].meetingAttendees;
					 var tr= '<tr style="border:1px solid;">'+
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 5%;">'+oaderId+'</td>' +
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingName+'</td>' +
						'<td style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;">'+meetingAttendees+'</td>' +
						'<td class="del-td" style="font-weight:2px;font-size: 14px;color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 15%;">'+
						'<button onclick="edit_tdd(this,value)" type="button" value = "'+datas[i].djId +'" id="edit_tdd" style="widht:20px;height:20px;">编辑</button>' +
						'&nbsp;&nbsp;<button onclick="view_tdd(this,value)" type="button" value = "'+datas[i].djId +'" id="view_tdd" style="widht:20px;height:20px;">查看</button>' +
							'&nbsp;&nbsp;<button onclick="del_tdd(this,value)" type="button" value = "'+datas[i].djId +'" id="del_td" style="widht:20px;height:20px;">删除</button>'
						'</td>' +
					'</tr>';
		         $(tbody).append(tr);  
	        
	       }   
		 
	}
</script>