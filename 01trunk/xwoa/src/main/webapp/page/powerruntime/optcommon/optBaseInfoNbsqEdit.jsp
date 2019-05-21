<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>签报申请</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>签报申请</b>
		</legend>
<s:form action="optApply"  method="post" namespace="/powerruntime" id="oaQBForm"  target="parent" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	        <input type="hidden" id="djId" name="optBaseInfo.djId" value="${object.djId}" />
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						签报标题<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="optBaseInfo.transaffairname" id="transaffairname" value="%{object.optBaseInfo.transaffairname}" cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr>
				<tr>
						<td class="addTd">
						缓急程度<span style="color:red;">*</span>
					</td>
					
					<td align="left" >
						<select id="criticalLevel" name="optBaseInfo.criticalLevel" style="width: 200px;height:24px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_levelsq')}">
								<option value="${row.key}" ${(object.optBaseInfo.criticalLevel eq row.key or (empty object.optBaseInfo.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>		
				</tr>
				 <tr>
				<tr>
					<td class="addTd">
						签报内容<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="optBaseInfo.content" id="content"  value="%{object.optBaseInfo.content}" cols="40" rows="2" style="width:100%;height:60px;" />
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
	function cancel() {
		DialogUtil.prototype.close();
	}
	 
	function submitItemFrame() {
		debugger;
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaQBForm");
			srForm.action = 'optApply!saveQB.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
				$.ajax({
	                type: "post",
	                url: srForm.action,     
	                data: $("#oaQBForm").serialize(),
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
		
		
		if( document.oaQBForm.content.value == ""){
			DialogUtil.alert("签报内容不能为空");
			document.oaQBForm.content.focus();
			return false;
		}
		
		if( document.oaQBForm.transaffairname.value == ""){
			DialogUtil.alert("签报标题不能为空");
			document.oaQBForm.transaffairname.focus();
			return false;
		}
 		return true; 	
	}
	</script>
</html>