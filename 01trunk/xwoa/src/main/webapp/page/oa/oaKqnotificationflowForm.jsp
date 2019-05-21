<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>客情通报</title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>客情通报</b>
		</legend>
<s:form action="oaKqnotification"  method="post" namespace="/oa" id="oaKqnotificationForm"  >
	
	        <input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
			<input type="hidden" id="itemtype" name="itemtype" value="SQ" />
			<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">	
			<table  border="0" cellpadding="0" cellspacing="0" class="viewTable">	

				<tr>
					<td class="addTd">
						主题标题<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="transaffairname" cols="40" rows="2" style="width:100%;height:40px;" />
	
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						期数
					</td>
					<td align="left" >
						第  <s:textfield name="periods"  id="periods" style="width:50px;height:24px;"/>  期
	
					</td>
					<td class="addTd">
					            登记人
					</td>
					<td align="left">
  
						<span>${cp:MAPVALUE('usercode', object.creater)}</span>
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
						客情部门
					</td>
					<td align="left">
                              
						<s:textfield name="kqdepname"  style="width:100%;height:24px;"/>
	
					</td>
				
					<td class="addTd">
						客情时间
					</td>
					<td align="left">
	                 <input type="text" class="Wdate"id="kqtime" name="kqtime"   style="width:200px;height:25px;"
			                  value='<fmt:formatDate value="${object.kqtime}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				<tr>
					<td class="addTd">
						来访人员
					</td>
					<td align="left" colspan="3">
	
                     	<s:textarea name="visitors" style="width:100%;height:40px;"  />
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						联系人
					</td>
					
					<td align="left">
						<s:textfield name="contactuser"  style="width:100%;height:24px;"/>
					</td>
				
					<td class="addTd">
						联系电话
					</td>
					<td align="left">
						<s:textfield name="contactphone"  style="width:100%;height:24px;"/>
					</td>
				</tr>

				<tr>
					<td class="addTd">
						客情内容<span style="color:red;">*</span>
					</td>
					<td align="left" colspan="3">
						<s:textarea name="kqcontent"  style="width:100%;height:60px;" />
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
		if( document.oaKqnotificationForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaKqnotificationForm.transaffairname.focus();
			return false;
		}
		if( document.oaKqnotificationForm.kqcontent.value == ""){
			alert("客情内容不能为空");
			 document.oaKqnotificationForm.kqcontent.focus();
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
			var srForm = document.getElementById("oaKqnotificationForm");
			srForm.action = 'oaKqnotification!saveReditSQ.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaKqnotificationForm").serialize(),
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