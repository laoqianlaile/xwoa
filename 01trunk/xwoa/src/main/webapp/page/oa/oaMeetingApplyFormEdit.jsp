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

<body  class="sub-flow">
	<fieldset class="form">
		<legend>
			<p>新增会议</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaMeetingApply" method="post" namespace="/oa"
			id="oaMeetingApplyForm">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" onclick ="return beforeSubmit()"/>
			<input type="button" class="btn" value="新增议程" onclick="similarity('<%=request.getContextPath()%>/oa/oaMeetingmaterial!editNew.do?mId=${object.mId}',null,null)">
			<input type="button" class="btn" value="返回" onclick="renturns()">
			<input type="hidden" id="mId" name="mId" value="${object.mId}" />
			<input type="hidden" id="releteCode" name="releteCode" value="${object.releteCode}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" style ="text-align: center;">会议名称<span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textfield name="meetApplyName" value="%{object.meetApplyName}" style="width:100%;height:30px;"/></td>
				</tr>
				<tr id="tr_receive">
					<td class="addTd" style ="text-align: center;">参会领导<span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><input type="text" readonly="readonly"
						id="attendLeaderName" name="attendLeaderName" value="${object.attendLeaderName }"
						class="focused" style="width:100%;height:30px;" > 
						<input id="attendLeaderCode" type="hidden" name="attendLeaderCode"
						 value="${attendLeaderCode }" />
						</td>
				</tr>		
				<tr>
					<td class="addTd" style ="text-align: center;">会议地点<span style="color: red">*</span></td>
					<td align="left"><s:textarea name="meetApplyAddress" value="%{object.meetApplyAddress}"
							style="height:30px;" /></td>
					<td class="addTd" style ="text-align: center;">会议时间<span style="color: red">*</span></td>
					<td align="left"><input type="text" class="Wdate"
						id="meetApplytime" readonly="readonly"
						value='<fmt:formatDate value="${object.meetApplytime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						name="meetApplytime"
						style="height: 30px; width:300px!important; border: 1px solid #cccccc;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})" placeholder="选择日期">
					</td>
				</tr>
				
				<%-- <tr>
					<td class="addTd" style ="text-align: center;">参会单位</td>
					<td align="left" colspan="3"><s:textfield name="attendUnitName" value="%{object.attendUnitName}"
							style="width:100%;height:30px;" /></td>
				</tr>
				<tr>
					<td class="addTd" style ="text-align: center;">外来人员</td>
					<td align="left" colspan="3"><s:textfield name="foreigUserName" value="%{object.foreigUserName}"
							style="width:100%;height:30px;" /></td>
				</tr>
				<tr>
					<td class="addTd" style ="text-align: center;">外来部门</td>
					<td align="left" colspan="3"><s:textfield name="foreigUnitName" value="%{object.foreigUnitName}"
							style="width:100%;height:30px;" /></td>
				</tr> --%>
				<tr>
					<td class="addTd" style ="text-align: center;">备注</td>
					<td align="left" colspan="3"><s:textarea name="meetingRemark" value="%{object.meetingRemark}"
							style="height:80px;width:100%" /></td>
				</tr> 
				<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen')}">
					<tr>
						<td class="addTd" style ="text-align: center;">短信提醒&nbsp;</td>
						<td align="left" colspan="3"><input id="isSendMsgView"
							class="checkboxClass" type="checkbox" name="isSendMsgView" />短信提醒参会领导
							<input type="hidden" id="isSendMessage" name="isSendMessage" />

						</td>
					</tr>
				</c:if>
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
		<br>
			<div class="group-title">
			     <div class="title-ico"></div>
			     <div class="title-name">议程</div>
			     <div class="title-split-line"><span></span></div>
		   </div>
		<div id = "tabdiv">
                        <table width="600" cellpadding="0" cellspacing="0" class='table_border'> 
   								<thead>  
     								 <tr style="border:1px solid;">
     								 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 5%;background: #EDEDED;">序号</th>
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;background: #EDEDED;">议程名称</th>  
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 40%;background: #EDEDED;">参与人员</th>  
        							 <th style="font-weight:2px;font-size: 14px;font-family:'宋体';color:#000;border:1px #e8e8e8 solid;text-align:center;height:40px;width : 15%;background: #EDEDED;">操作</th>  
      									</tr>   
  										 </thead>    
   									<tbody id="tbMain"></tbody>
							</table> 
                 </div>   
		
	</fieldset>
		</body>
		
	<script type="text/javascript">
	
	$(function(){
		var mId = '${object.mId}';
		 $.ajax({
	    		url:"<%=request.getContextPath()%>/oa/oaMeetingmaterial!queryMeet.do",
	    		type:"POST",
	    		data:{"id":mId},
	    		async: false,
	    		dataType:"json",
	    		success:function(datas){
	    			 if(datas != null && datas !=""){
		    			 var tbody = document.getElementById('tbMain');
		    			 var tabdiv = document.getElementById('tabdiv');
		    			 $(tabdiv).find('thead').show();
		    			 gettable(tbody,datas);
	    			 }
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
						'<button onclick="edit_tdd(this,className)" type="button" class = "'+ datas[i].djId +'" id="edit_tdd" style="width:40px;height:30px;">编辑</button>' +
						'&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="view_tdd(this,className)" type="button" class = "'+ datas[i].djId +'" id="view_tdd" style="width:40px;height:30px;">查看</button>' +
							'&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="del_tdd(this,className)" type="button" class = "'+ datas[i].djId +'" id="del_td" style="width:40px;height:30px;">删除</button>'
						'</td>' +
					'</tr>';
		         $(tbody).append(tr);  
	        
	       }   
		 
	}
	
	//议程表格删除事件
	var del_tdd = function(self,value){
		//删除数据
		if(confirm('是否确定删除！')){
			
			var releteCode = $("#releteCode").val();
			if(releteCode ==""){
				releteCode = value;
			}else{
				releteCode = releteCode + "," + value
			}
			$("#releteCode").val(releteCode);
			
			 //删除表格
			$(self).parent().parent().remove();
			/* var srForm  = document.getElementById("oaMeetingApplyForm");
			srForm.action = 'oaMeetingmaterial!deleteYC.do';
			 $.ajax({
	            type: "post",
	            url: srForm.action,     
	            data:{"djId":value},
	            async: false,
	            success: function(data) {
					
	            },
	            error: function(data) {
	                DialogUtil.alert("修改信息失败，请重新尝试！");
	            }
	        });  */
			 
			/* if (!$('#tbMain').find('tr')[0]) {
				$('#tabdiv').find('thead').hide()
			}  */
		}
			
	}
	//议程查看按钮
	var view_tdd=function(self,value){
		similarityNew('<%=request.getContextPath()%>/oa/oaMeetingmaterial!viewNew.do?djId='+value,null,null)
	}
	
	//议程编辑按钮
	var edit_tdd=function(self,value){
		similarityNew('<%=request.getContextPath()%>/oa/oaMeetingmaterial!editNew.do?djId='+value+'&mId=${object.mId}',null,null)
		 //删除表格
		//$(self).parent().parent().remove();
	}
		$("#attendLeaderName").click(
				function() {
					var d = '${userjson}';
					if (d.trim().length) {
						selectPopWin(jQuery.parseJSON(d),
								$("#attendLeaderName"),
								$("#attendLeaderCode"));
					}
					;
				});
	
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	//返回按钮
	
	function similarityNew(winUrl, winName, winProps) {
	    if (winProps == '' || winProps == null) {
	        winProps = 'height=500px,width=1000px,directories=false,location=false,top=300,left=350,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	    }
	    window.open(winUrl, winName, winProps);
	}
	
	function renturns(){
		window.location.href='${pageContext.request.contextPath}/oa/oaMeetingApply!list.do';
	}
	function beforeSubmit() {
		if( document.oaMeetingApplyForm.meetApplyName.value == ""){
            DialogUtil.alert("会议名称不能为空");
            document.oaMeetingApplyForm.meetApplyName.focus();
            return false;
        }
		if( document.oaMeetingApplyForm.attendLeaderName.value == ""){
            DialogUtil.alert("参会领导不能为空");
            document.oaMeetingApplyForm.attendLeaderName.focus();
            return false;
        }
        if( document.oaMeetingApplyForm.meetApplyAddress.value == ""){
            DialogUtil.alert("会议地点不能为空");
            document.oaMeetingApplyForm.meetApplyAddress.focus();
            return false;
        }
        if( document.oaMeetingApplyForm.meetApplytime.value == ""){
            DialogUtil.alert("会议时间不能为空");
            document.oaMeetingApplyForm.meetApplytime.focus();
            return false;
        }
		totalLen = CommonUtils.checkLength($("#meetingRemark").val());
		if( totalLen>400){
				DialogUtil.alert("备注内容超出最大长度限制");
				document.oaMeetingApplyForm.meetingRemark.focus();
				return false;
	    }
		return true;
	}
	function similarity(winUrl, winName, winProps) {
        if (winProps == '' || winProps == null) {
            winProps = 'height=500px,width=1000px,directories=false,location=false,top=300,left=350,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
        }
        window.open(winUrl, winName, winProps);
    }
	
	$('#isSendMsgView').click(function(){
		var $this = $(this);
		if($this.attr('checked') == 'checked'){
			$('#isSendMessage').val('T');
		}else{
			$('#isSendMessage').val('F');
		}
	});
</script>
</html>