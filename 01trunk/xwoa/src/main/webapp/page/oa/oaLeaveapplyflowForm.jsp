<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>请假条</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>请假条</b>
		</legend>
<s:form action="oaLeaveapply"  method="post" namespace="/oa" id="oaLeaveapplyForm"  >
	
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="itemtype" name="itemtype" value="SQ" />
			<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
				<td class="addTd" >
						标题<span style="color:red;">*</span>
					</td>
					<td align="left"  colspan="5">
                              
						<s:textfield name="transaffairname"  id="transaffairname" style="width:100%;height:60px;"/>
	
				</td>
				</tr>
				<tr>
				<td class="addTd">
						缓急程度
				</td>
					<td align="left"  colspan="5">
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:24px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
				   </td>	
				</tr>
				<tr>
					<td class="addTd">
						部门 <span style="color:red;">*</span>
					</td>
					<td align="left">
                        <select id="applyuser" name="applyuser">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${applyuser == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
					
					
					<td class="addTd">
						职位<span style="color:red;">*</span>
					</td>
					<td align="left" >
                           <%-- <span>${cp:MAPVALUE('areacode', Postleve)}</span>    --%>
						<s:textfield name="postleve"  id="postleve" style="width:200px;height:24px;"/>
	
					</td>
					
					<td class="addTd">
						姓名
					</td>
					<td align="left" ><span>${cp:MAPVALUE('usercode', object.creater)}</span></td>
					</td>
				
				</tr>
				
				<tr>
					<td class="addTd">
						请假开始时间<span style="color:red;">*</span>
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					
					<td class="addTd">
						请假结束时间<span style="color:red;">*</span>
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="endtime" name="endtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.endtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					
					<td class="addTd">
						请假天数
					</td>
					<td align="left" >
                              
						<s:textfield name="leavenum"  id="leavenum" style="width:150px;height:24px;"/> 天
	
					</td>
			
				</tr>
				
				<tr>
					<td class="addTd">
						请假事由<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="5">
					
						<s:textarea name="remarkContent" id="remarkContent" style="width:100%;height:80px;" />
					</td>
				</tr>


               </table>

			<%-- <center style="margin-top: 10px;">
			<span style="display: none;"> <s:submit name="saveAndSubmit"
					method="submitReditSQ" cssClass="btn" value="提 交" id="submitBtn" />
			</span> <span style="display: none;"> <s:submit name="save"
					method="saveReditSQ" cssClass="btn" value="保 存" id="saveBtn" />
			</span> <span style="display: none;"> <input type="button"
				value="返回" class="btn" onclick="window.history.go(-1);" id="backBtn" />
			</span>
	    	</center> --%>
	    	<input type="button" class="btn" id="saveAndSubmit" value="保存"  onclick="submitItemFrame();" />
	    	<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
			</s:form>
		
		</fieldset>
	</body>
	<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script type="text/javascript">
	
	
	function checkForm(){
		if( document.oaLeaveapplyForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaLeaveapplyForm.transaffairname.focus();
			return false;
		}
		if( document.oaLeaveapplyForm.remarkContent.value == ""){
			alert("请假事由不能为空");
			 document.oaLeaveapplyForm.remarkContent.focus();
			return false;
		}
			return true;
	}
	
	function cancel() {
		DialogUtil.prototype.close();
	}
	
	function submitItemFrame() {
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaLeaveapplyForm");
			srForm.action = 'oaLeaveapply!saveReditSQ.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaLeaveapplyForm").serialize(),
                async: false,
                success: function(data) {
               		// 如果父页面重载或者关闭其子对话框全部会关闭
               		win.location.reload(true);
    				
                },
                error: function(data) {
                    alert("修改信息失败，请重新尝试！");
                }
            });
			}
		}
	}
	</script>
</html>