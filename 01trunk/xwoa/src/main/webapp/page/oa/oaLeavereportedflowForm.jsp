<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>外出请假报备</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>外出请假报备</b>
		</legend>
<s:form action="oaLeavereported"  method="post" namespace="/oa" id="oaLeavereportedForm"  >
	
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="itemtype" name="itemtype" value="SQ" />
			<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						缓急程度
					</td>
					<td align="left" >
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:24px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(optBaseInfo.criticalLevel eq row.key or (empty optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>	
					<td class="addTd">
						日期
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="creatertime" name="creatertime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.creatertime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>	
				</tr>
				<tr>
					
					<td class="addTd">
						姓名
					</td>
					<td align="left" ><span>${cp:MAPVALUE('usercode', object.creater)}</span></td>
					</td>
					
					<td class="addTd">
						职务
					</td>
					<td align="left" >
                          <%--  <span>${cp:MAPVALUE('userunit', object.postleve)}</span>    --%>
                          
						<s:textfield name="postleve"  id="postleve" style="width:100%;height:24px;"/>
					</td>
			
				</tr>

				

				<tr>
					<td class="addTd">
						外出事由<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remarkContent" id="remarkContent" style="width:100%;height:80px;" />
					</td>
				</tr>
					
				<tr>	
					<td class="addTd">
						离开时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					<td class="addTd">
						返回时间
					</td>
					
					<td align="left" >
					<input type="text" class="Wdate"id="endtime" name="endtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.endtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				
				<tr>
				<td class="addTd">
						外出地点
					</td>
					<td align="left" >
                              
						<s:textfield name="leaveaddress"  id="leaveaddress" style="width:100%;height:24px;"/>
	
					</td>
					<td class="addTd">
						联系方式
					</td>
					<td align="left" >
                              
						<s:textfield name="telephone"  id="telephone" style="width:100%;height:24px;"/>
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
					
						<s:textarea name="remark" id="remark" style="width:100%;height:80px;" />
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
		if( document.oaLeavereportedForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaLeavereportedForm.transaffairname.focus();
			return false;
		}
		if( document.oaLeavereportedForm.remark.value == ""){
			alert("备注不能为空");
			 document.oaLeavereportedForm.remark.focus();
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
			var srForm = document.getElementById("oaLeavereportedForm");
			srForm.action = 'oaLeavereported!saveReditSQ.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaLeavereportedForm").serialize(),
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