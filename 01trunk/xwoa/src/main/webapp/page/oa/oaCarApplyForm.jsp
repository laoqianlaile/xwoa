
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>				
</head>

<body class="sub-flow">
<fieldset class="_new">
		<legend>
			<p>有车申请</p>
		</legend>
	<s:form action="oaCarApply" method="post" namespace="/oa"
		id="oaCarApplyForm">
		<!-- <input type="button" class="btn" id="save" value="保存"
			onclick="submitItemFrame('SAVE');" /> -->
		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
<%-- 		<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" /> --%>
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowinstid" name="flowinstid"
			value="${flowinstid}" />
		<input type="hidden" id="flowCode" name="flowCode" value="000858" />
		<input type="hidden" name="show_type" value="${show_type }" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">申请单号</td>
				<input type="hidden" id="applyNo" name="applyNo"
					value="${object.applyNo}" />
				<td align="left">${object.applyNo}</td>
				
				<td class="addTd">申请人</td>
				<td align="left"><span>${cp:MAPVALUE('usercode', object.creater)}</span></td>

			<%-- </tr>

				<td class="addTd">申请时间<span style="color: red">*</span>
				</td>
				<td align="left">
			<input type="text" class="Wdate" id="createtime"
			value='<fmt:formatDate value="${object.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
			name="createtime"
			style="height: 20px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
			placeholder="选择日期">
						</td>
			
			</tr> --%>
			</tr>
			<tr>
				<td class="addTd">标题<span style="color: red">*</span></td>
				<td align="left" colspan="3"><s:textfield name="transaffairname"
						id="transaffairname" style="width:100%;height:35px;"/>
				<span>参考格式: xxxx年xx月xx日某人或部门n人一行用车</span>		
				</td>
			</tr>



			<tr>
				<td class="addTd">预计开始时间<span style="color: red">*</span>
				</td>
				<td align="left">
			<input type="text" class="Wdate" id="planBegTime" readonly="readonly"
			value='<fmt:formatDate value="${object.planBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
			name="planBegTime"
			style="height: 20px; width: 155px; border-radius: 3px; border: 1px solid #cccccc;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})" 
			placeholder="选择日期">
						</td>
				<td class="addTd">预计结束时间<span style="color: red">*</span>
				</td>
				<td align="left">
			<input type="text" class="Wdate" id="planEndTime"name="planEndTime" readonly="readonly"
			value='<fmt:formatDate value="${object.planEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
			style="height: 20px; width: 155px; border-radius: 3px; border: 1px solid #cccccc;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss  '})" 
			placeholder="选择日期">
						</td>
			
			</tr>

			<tr>
			     <td class="addTd">乘车人数</td>
				 <td align="left"><s:textfield name="meetingPersionsNum"
						value="%{object.meetingPersionsNum}" id="meetingPersionsNum" /></td>
				<td class="addTd">联系电话</td>
				<td align="left"><s:textfield name="telephone"
						value="%{object.telephone}" id="telephone" /></td>
		<%-- 
				<td class="addTd">目的地</td>
				<td align="left"><s:textfield name="destination"
						id="destination" /></td>
			</tr>
			<tr>
				<td class="addTd">是否往返</td>
				<td align="left" ><s:textfield name="meetingNo"
						 id="meetingNo" /></td> --%>
		     	<%-- <td class="addTd">用车是由<span style="color: red">*</span></td>
				<td align="left" ><s:textfield name="reqRemark"
						id="reqRemark" /></td> --%>
			</tr>
			<tr>
				<td class="addTd">用车事由<span style="color: red">*</span></td>
				
			<%-- 	<td class="searchCountArea" align="left" colspan="3">
				<select id="reqRemark" name="reqRemark" class="searchCondContent" style="width:80%;height:35px;">
							<option value="">------------------请选择------------------</option>
							<c:forEach var="row" items="${cp:DICTIONARY('req_remark')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==reqRemark}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
				</td> --%>
				
				<td align="left" colspan="3"><s:textfield name="reqRemark"
						id="reqRemark" style="width:100%;height:35px;"/>
				</td>
			</tr>
			<tr>
				<td class="addTd">行车路线</td>
				<td align="left" colspan="3"><s:textarea name="meetingPersions"
						style="width:100%;height:50px;" id="meetingPersions" /></td>
			</tr>

			<tr>
				<td class="addTd">备注</td>
				<td align="left" colspan="3"><s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" /></td>
			</tr>



		</table>
		<%-- <!-- 附件上传-->
	
			<iframe id="basicsj" name="sjfj"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotoCFsqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&suppowerstuffinfo.groupId=286"
				width="100%" frameborder="no" scrolling="false" border="0"
				marginwidth="0" marginheight="0"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>
			<!-- 附件上传--> --%>
 
			</s:form>
			</fieldset>
</body>
<script type="text/javascript">
	function submitItemFrame(subType) {
		if (docheck() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaCarApplyForm");
			if (subType == 'SAVE') {
				srForm.action = 'oaCarApply!savePermitReg.do';
			}

			if (subType == 'SUB') {
				srForm.action = 'oaCarApply!saveAndSubmit.do';
			}
			
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaCarApplyForm").serialize(),
                async: false,
                success: function(data) {
               		// 如果父页面重载或者关闭其子对话框全部会关闭
               		win.location.reload(true);
               		art.dialog.close();
    				
                },
                error: function(data) {
                    alert("提交失败，请重新尝试或联系管理员！");
                }
            })
			}
			srForm.submit();
		}
	}

	function docheck() {
	
		var flag = true;
		if ($("#transaffairname").val() == '') {
			alert("标题不可为空！");
			$('#transaffairname').focus();
			return false;
		}
		if ($("#planBegTime").val() == '') {
			alert("开始时间不能为空！");
			$('#planBegTime').focus();
			flag = false;
			return flag;
		}
		var date=new Date();
		if ($("#planBegTime").val() != '') {
		var dt = new Date($("#planBegTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选计划开始时间发生在过去，请重新选择时间段！");
			$("#planBegTime").focus();
			flag = false;
			return flag;
		   }
		}
		if ($("#planEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#planEndTime').focus();
			flag = false;
			return flag;
		}
		if ($("#planEndTime").val() != '') {
			var dt = new Date($("#planEndTime").val().replace(/-/,"/"));  
			if (dt <date) {
				alert("所选计划结束时间发生在过去,请确认提交！");
				$('#doEndTime').focus();
				flag = false;
				return flag;
			   }
			}
		if ($("#planBegTime").val() > $("#planEndTime").val()) {
			alert("开始时间不能大于结束时间！");
			$("#planBegTime").focus();
			flag = false;
			return flag;
		}
		if ($("#reqRemark").val() == '') {
			alert("用车事由不可为空！");
			$('#reqRemark').focus();
			flag = false;
			return flag;
		}
		var r="^[1-9]\\d*$";
		if($("#meetingPersionsNum").val()!=''){
		var isvalid= (new RegExp(r)).test($("#meetingPersionsNum").val());
		if(!isvalid){
			alert("输入的乘车人数格式不正确");
			$("#meetingPersionsNum").focus();
			flag = false;
			return flag;
		}
		}
		if ($("#telephone").val() != null
				&& getStringLen($("#telephone").val()) > 12) {
			alert("手机号不可 超过11位！");
			flag = false;
			return flag;
		}
		return flag;

	}
	/* function docheck() {
		if(docheckBase() == true){
			var flag = true;
			$.ajax({
				type : "POST",
				async: false,
				dataType : "json",
				url : "oaCarApply!isTFree.do?djId="+$("#djId").val() +"&planBegTime="+$("#planBegTime").val()+"&planEndTime="+$("#planEndTime").val()+"&cardjid="+$("#meetingNo").val(),
				success : function(json) {
					if(!json){
						$('#planBegTime').focus();
						
						//ajax直接返回无效
						flag = false;
					}
				},
				error : function() {
					alert("失败");
					flag = false;
				}
			});
			if(!flag){
    			if(window.confirm("申请的会议室已被占用,是否确认提交!")){
    				return true;
    			}else{
    				return false;
    			}
    		}else{
    			return flag;
    		}	
		}else{
    		return false;
    	}
		
	} */
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
</script>
</html>
