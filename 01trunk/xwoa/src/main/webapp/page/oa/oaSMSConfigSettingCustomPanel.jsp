<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>短信平台配置
		</title>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>
<body class="sub-flow">
	 <fieldset style="padding:10px;">
	 	<legend style="margin-bottom:10px;">短信平台参数配置</legend>
	 	
	 	
	 <s:form action="oaSMSConfig" method="post" namespace="/oa"  id="oaSMSConfigForm" onsubmit="return checkForm();">
	       <input type="hidden" name="smsid" id="smsid" />
	       <input type="hidden" name="status" id="status" />
	       
           <table cellpadding="0" cellspacing="0" class="viewTable">
	       <div id="t-b-arrow">
					<b class="btns"> <input id="save" class="btn" type="button"
						value="暂存"  onclick="saveTempData()"/></b>
					<b class="btns"> <input id="save" class="btn" type="button"
						value="启用"  onclick="saveActiveData()"/></b>
			</div>
				<tr>
					<td class="addTd">
						运营商
					</td>
					<td align="left" >
						${cp:MAPVALUE("sendMsgSource",operatorSource)}
						<select  id="operatorSource" name="operatorSource" style="width: 200px;height:30px;">
							<option value="">---请选择运营商---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('sendMsgSource')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==object.operatorSource}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						开启状态
					</td>
					<td align="left">
					  <s:radio name="openOrClose" id="openOrClose"  list="#{'T':'开启','F':'关闭' }" listKey="key" listValue="value"   value="%{object.openOrClose }" ></s:radio>
                    <%-- 	${cp:MAPVALUE("YES_NO",limitYesOrNo)} --%>
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						短信网关
					</td>
					<td align="left">
					<input type="text" id="gateway" name="gateway"  style="width: 100%; height: 25px;" value="${object.gateway}" />
					</td>

					<td class="addTd">
						授权端口
					</td>
					<td align="left">
					<input type="text" id="port" name="port"  style="width: 70%; height: 25px;" value="${object.port}" />
						${port }
					</td>
				</tr>
				<tr>
				    <td class="addTd">
						授权用户
					</td>
					<td align="left">
					<input type="text" id="username" name="username"  style="width: 100%; height: 25px;" value="${object.username}" autocomplete="off" />
					</td>

					<td class="addTd">
						授权密码
					</td>
					<td align="left">
					<input type="text" id="password" name="password"  style="width: 70%; height: 25px;" value="${object.password}" />
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						短信模板
					</td>
					<td align="left" >
					<c:if test="${empty sendMSgMod  }">
					<input type="text" id="sendMSgMod" name="sendMSgMod"  style="width: 100%; height: 25px;" value="sendMSgMod" />
					</c:if>
					<c:if test="${not empty sendMSgMod  }">
					<input type="text" id="sendMSgMod" name="sendMSgMod"  style="width: 100%; height: 25px;" value="${object.sendMSgMod}" />
					</c:if>
					</td>
					<td align="left" colspan="2">
						(详情查看对应字典项)
					</td>
				</tr>
				
				<tr>
				   <td class="addTd">
						是否限制条数
					</td>
					<td align="left">
					  <s:radio name="importantTag" id="importantTag"  list="#{'T':'是','F':'否' }" listKey="key" listValue="value"   value="%{object.limitYesOrNo }" ></s:radio>
                    <%-- 	${cp:MAPVALUE("YES_NO",limitYesOrNo)} --%>
					</td>
					<td class="addTd">
						限制条数
					</td>
					<td align="left" >
					<input type="text" id="limitNumber" name="limitNumber"  style="width:70%; height: 25px;" value="${object.limitNumber}" /> 条
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						备注说明
					</td>
					<td align="left" colspan="3">
					<s:textarea name="remark"	id="remark" cols="40" rows="3"  style="width:100%;height:50px;" />
					</td>
				</tr>
				
			</table>
			
		
		</s:form>
 </fieldset>
						
</body>
	<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
  <script type="text/javascript">
  /**
           暂存为草稿 status A
  */
  function saveTempData(){
	  $("#status").val("A");
	  saveData();
  }
  /**
	  保存并启用 statu s
	*/
	function saveActiveData(){
	$("#status").val("B");
	saveData();
	}
  function saveData(){
      setTimeout(function(){
      	if(!checkLocal()){
	    	  return;
	      }
	      if(!checkProfileAvailable()){
	    	  return;
	      }
	      var win = art.dialog.open.origin;//来源页面
	  	  $.ajax({
	  		  type:"post",
	  		  url:"${ctx}/oa/oaSMSConfig!saveSMSConfig.do",
	  		  dataType:"json",
	  		  data:$("#oaSMSConfigForm").serialize(),
	  		  success:function(data){
	  			  if(!data){
	  				DialogUtil.alert("操作失败");
	  			  }else{
	  				win.location.reload(true);
	  				DialogUtil.alert("操作成功");
	  			  }
	  		  }
	  	  });
      },0);
    }
  
  
  
  /**
   * 输入校验
   */
  function checkLocal(){
	  var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
	  if($.trim($("#operatorSource").val()).length==0){
		  DialogUtil.alert("请选择运营商");
		  return false;
	  }
	 
	  if($.trim($("#gateway").val()).length==0){
		  DialogUtil.alert("短信网关不能为空");
		  return false;
	  }
	  if($.trim($("#username").val()).length==0){
		  DialogUtil.alert("授权用户不能为空");
		  return false;
	  }
	  if($.trim($("#password").val()).length==0){
		  DialogUtil.alert("授权密码不能为空");
		  return false;
	  }
	  if($.trim($("#port").val()).length==0){
		  DialogUtil.alert("授权端口不能为空");
		  return false;
	  }
	  
	  if( $.trim($("#port").val()) != ""){
			if(!num.test( $.trim($("#port").val()))){
				DialogUtil.alert("授权端口格式不正确");
				$("#port").val('');
				$("#port").focus();
				return false;
			}
      }
	  if( $.trim($("#limitNumber").val()) != ""){
			if(!num.test( $.trim($("#limitNumber").val()))){
				DialogUtil.alert("短信限制条数格式不正确");
				$("#limitNumber").val('');
				$("#limitNumber").focus();
				return false;
			}
    }
	  return true;
  }
  
  /**
   *短信配置是否能连接校验
   */
  function checkProfileAvailable(){
	  var f = false;

	  f = true;
	  return f;
  }
  </script>
</html>
