<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>耗材配件购置信息</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>耗材配件购置信息</b>
		</legend>
<s:form action="oaConsumablesParts"  method="post" namespace="/oa" id="oaConsumablesPartsForm"  >
	
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="itemtype" name="itemtype" value="SQ" />
			<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						申请标题 <span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="transaffairname" id="transaffairname"  cols="40" rows="2" style="width:100%;height:40px;" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						申请时间
					</td>
					<td align="left" >
	                 <input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
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
				</tr>
				<tr>
					<td class="addTd">
						申请人
					</td>
					<td align="left" >
                              
						<%-- <s:textfield name="applyuser"  id="applyuser" style="width:100%;height:24px;"/> --%>
						<span>${cp:MAPVALUE('usercode', Creater)}</span>
	
					</td>
			
			
					<td class="addTd">
						申请人所在部门
					</td>
					<td align="left">
                        <select id="unitcode" name="unitcode">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${unitcode == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select>      
	
					</td>
				</tr>
		
				<tr>
					<td class="addTd">耗材/配件</td>
					<td align="left" colspan="3" style="width: 170px;" >
					<select name="sbtype" id="sbtype" onchange="sbtypeChanged(this.value);">
					<option value="1" <c:if test="${sbtype eq 1 }">selected="selected"</c:if> >耗材</option>
					<option value="2" <c:if test="${sbtype eq 2 }">selected="selected"</c:if> >配件</option>
					</select>
					</td>
				</tr>

				<tr id="hc_td" <c:if test="${sbtype eq 2 and not empty sbtype}">style="display: none;"</c:if>>
					<td class="addTd">
						耗材内容<span style="color:red;">*</span>
					</td>
					<td align="left" >
					
						<s:textfield name="consContent" id="consContent" style="width:100%;height:60px;" />
					</td>
					<td class="addTd">
						耗材数量
					</td>
					
					<td align="left" >
					<s:textfield name="consNum" id="consNum" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr id="pj_td" <c:if test="${sbtype eq 1 or empty sbtype}">style="display: none;"</c:if>>
					<td class="addTd">
						配件内容<span style="color:red;">*</span>
					</td>
					<td align="left" >
					<s:textfield name="partsContent" id="partsContent" style="width:100%;height:60px;" />
					</td>
					<td class="addTd">
						配件数量
					</td>
					
					<td align="left" >
					<s:textfield name="partsNum" id="partsNum" style="width:100%;height:24px;" />
					</td>
				</tr>
				<tr>
				<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
					<s:textfield name="remark" id="remark" style="width:100%;height:80px;" />
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
		if( document.oaConsumablesPartsForm.transaffairname.value == ""){
			alert("申请标题不能为空");
			 document.oaConsumablesPartsForm.transaffairname.focus();
			return false;
		}
		if( document.oaConsumablesPartsForm.partsContent.value == ""){
			alert("配件内容不能为空");
			 document.oaConsumablesPartsForm.partsContent.focus();
			return false;
		}
			return true;
	}
	function sbtypeChanged(objValue) {
	   	 if (objValue == "1") {
	   		 $("#hc_td").show();
	   		 $("#pj_td").hide();
	   	 } else {
	   		 $("#pj_td").show();
	   		 $("#hc_td").hide();
	   	 }
	    }
	
	function cancel() {
		DialogUtil.prototype.close();
	}
	
	function submitItemFrame() {
		if (checkForm() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaConsumablesPartsForm");
			srForm.action = 'oaConsumablesParts!saveReditSQ.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaConsumablesPartsForm").serialize(),
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