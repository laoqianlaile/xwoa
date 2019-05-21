<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>会议（活动）、培训支出计划申请</title>
 <script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/arrow.js"
            type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/themes/css/arrow.css"
          rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
          rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
    <script
            src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>徐圩新区出差审批单</b>
		</legend>
<s:form action="oaTripPlan"  method="post" namespace="/oa" id="oaTripPlanForm"  target="parent" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
	        <input type="hidden" id="transport" name="transport" value="${object.transport}" />
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

			<%-- 	<tr>
					<td class="addTd">
						标题<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="tripPlanName" id="tripPlanName"  value="%{object.tripPlanName}" cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr> --%>
				<tr>
				<td class="addTd">出差地点<span style="color:red;">*</span>
					</td>
					
					<td align="left" >
					<s:textfield name="tripPalce" id="tripPalce" value="%{object.tripPalce}" style="width:100%;height:24px;" />
                    </td>
					<td class="addTd">
						申请时间<span style="color:red;">*</span>
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="createDate" name="createDate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.createDate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
                </tr>
				<tr>
					<td class="addTd">
						起始时间<span style="color:red;">*</span>
					</td>
					<td align="left"><input type="text" class="Wdate"
                                        style="width: 200px; height: 30px; border-radius: 3px; border: 1px solid #cccccc;"
                                        value='<fmt:formatDate value="${object.startTime}" pattern="yyyy-MM-dd"/>'
                                        id="startTime" name="startTime"
                                        onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                         placeholder="选择日期"></td>
					<td class="addTd">
						结束时间<span style="color:red;">*</span>
					</td>
					<td align="left"><input type="text" class="Wdate"
                                        style="width: 200px; height: 30px; border-radius: 3px; border: 1px solid #cccccc;"
                                        value='<fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd"/>'
                                        id="endTime" name="endTime"
                                        onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                         placeholder="选择日期"></td>
				</tr>
				<tr>
				<td class="addTd">
						乘坐交通工具<span style="color:red;">*</span>
					</td>
					 <td align="left" colspan="3">
					   <input type="checkbox" name="trans" id ="feiji" value="飞机" />飞机
					<input type="checkbox" name="trans" id ="huoche" value="火车" />火车
					<input type="checkbox" name="trans" id ="keche" value="客车" />客车 
					 <input type="checkbox" name="trans" id ="lunchuan" value="轮船" />轮船
					<input type="checkbox" name="trans" id ="danweiche" value="单位用车" />单位用车
					<input type="checkbox" name="trans" id ="qita" value="其他" />其他
					
                    </td>
				
				
				</tr>
				<tr>
						<td class="addTd">
						同行人员
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="teamName" id="teamName"  value="%{object.teamName}" cols="40" rows="2" style="width:100%;height:25px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						出差事由<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="content" id="content" value="%{object.content}"   cols="40" rows="2" style="width:100%;height:60px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="remark" id="remark"  value="%{object.remark}" cols="40" rows="2" style="width:100%;height:60px;" />
					</td>
				</tr>
				<tr>
				<td class="addTd">
						出差人
					</td>
					<td align="left">
						<span>${cp:MAPVALUE('usercode', object.creater)}</span>
					</td>
					<td class="addTd">
						工作部门
					</td>
					<td align="left">
					<input type="hidden" id="depno" name="depno" value="${object.deptno}" />
              		${cp:MAPVALUE("unitcode",object.deptno)}
					</td>
				</tr>
               </table>
				<div align="center" style="margin-right: 20px;margin-top: 20px;">
					<input type="button" class="btn" id="saveBtn" value="保存" onclick="submitItemFrame();" />
					<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
				</div>
			</s:form>
		</fieldset>
	</body>
	<script
src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		if('${object.transport}'.indexOf('飞机') >= 0){
	         $("#feiji").attr("checked","checked");
	     }
		if('${object.transport}'.indexOf('火车') >= 0){
	         $("#huoche").attr("checked","checked");
	     }
		if('${object.transport}'.indexOf('客车') >= 0){
	         $("#keche").attr("checked","checked");
	     }
		if('${object.transport}'.indexOf('其他') >= 0){
	         $("#qita").attr("checked","checked");
	     }
		if('${object.transport}'.indexOf('轮船') >= 0){
	         $("#lunchuan").attr("checked","checked");
	     }
		if('${object.transport}'.indexOf('单位用车') >= 0){
	         $("#danweiche").attr("checked","checked");
	     }
	});
	function cancel() {
		DialogUtil.prototype.close();
	}
	 
	function submitItemFrame() {
		debugger;
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaTripPlanForm");
			srForm.action = 'oaTripPlan!save.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
				$.ajax({
	                type: "post",
	                url: srForm.action,     
	                data: $("#oaTripPlanForm").serialize(),
	                async: false,
	                success: function(data) {
	               		// 如果父页面重载或者关闭其子对话框全部会关闭
	               		win.location.reload(true);
	    				
	                },
	                error: function(data) {
	                    DialogUtil.alert("修改信息失败，请重新尝试！");
	                }
	            });
			}
		}
	}
	
	function checkForm(){
		 var djArr = new Array();
		 var transall="";
	        $(":checkbox[name='trans']").each(function() {
	            if ($(this).attr('checked') == 'checked') {
	                djArr.push($(this).val());
	                $("#trans").val($(this).val() + ",")
	                transall = transall +$(this).val() +",";
	            }
	        });
		var begD = $("#startTime").val();
		var endD = $("#endTime").val();
		if (endD != "" && begD > endD) {
			DialogUtil.alert("结束时间不能早于开始时间。");
			return false;
		}
		
		if( transall == ""){
			DialogUtil.alert("出差交通工具不能为空");
			document.oaTripPlanForm.transport.focus();
			return false;
		}
		
		if( document.oaTripPlanForm.tripPalce.value == ""){
			DialogUtil.alert("出差地点不能为空");
			document.oaTripPlanForm.tripPalce.focus();
			return false;
		}
		
		if( document.oaTripPlanForm.createDate.value == ""){
			DialogUtil.alert("申请时间不能为空");
			document.oaTripPlanForm.createDate.focus();
			return false;
		}
		
		if( document.oaTripPlanForm.startTime.value == ""){
			DialogUtil.alert("起始时间不能为空");
			document.oaTripPlanForm.startTime.focus();
			return false;
		}
		
		if( document.oaTripPlanForm.endTime.value == ""){
			DialogUtil.alert("结束时间不能为空");
			document.oaTripPlanForm.endTime.focus();
			return false;
		}
		if( document.oaTripPlanForm.content.value == ""){
			DialogUtil.alert("出差事由不能为空");
			document.oaTripPlanForm.content.focus();
			return false;
		}
		$("#transport").val(transall);
 		return true; 	
	}
	</script>
</html>