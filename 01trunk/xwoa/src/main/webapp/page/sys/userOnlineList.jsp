<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html lang="en">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta name="decorator" content='${LAYOUT}'/>
		<title>在线人员列表</title>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>				
		<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>
			<script type="text/javascript">
		  $(function(){
			  $("tbody td.userNameTD").poshytip({
				  content:function(){
					  var usercode = $(this).find("span").data("usercode");
					  var html = "";
					   $.ajax({
						  type:'post',
						  async:false,
						  url:"${pageContext.request.contextPath}/oa/oaUserinfo!getUserConnectWay.do",
						  dataType:"json",
						  data:{"usercode":usercode},
						  success:function(data){
							  html = "办公室电话："+(data.workphone==''?'无':data.workphone)+" 手机:"+(data.mobile==''?'无':data.mobile);
						  }
					  }); 
					  return html;
				  }
			  });
			  
			  $("tbody td.userstateTD").poshytip({
				  content:function(){
					  var userstate = $(this).find("img").data("userstate");
					  var html = "";
					  if(userstate=='1'){
						 html="正在使用"; 
					  }
					  if(userstate=='2'){
						  html="离开"; 
					  }
					  return html;
				  }
			  });
			  
			  
		  });
		</script>
		<style type="text/css">
		.eXtremeTable .odd td, .eXtremeTable .even td{white-space: normal;}
		.eXtremeTable .highlight td{white-space: normal;}
		</style>
	</head>
	<body>
	    
	    <div style="margin: 10px 0 10px 10px;">
		    <c:if test="${cp:HASOPTPOWER('MESSAGE') }">
		    	<input type="button" class="whiteBtnWide" value="发送短信" id="sendMsgBtn" onclick="sendInformation('msg');"/>
		    </c:if>
		    
		    <c:if test="${cp:HASOPTPOWER('GRBGGRYJ') }">
		    	<input type="button" class="whiteBtnWide" value="发送邮件" id="sendEmailBtn" onclick="sendInformation('mail');"/>
		    </c:if>
		    
		    <c:if test="${cp:HASOPTPOWER('TXSJ') }">
		    	<input type="button" class="whiteBtnWide" value="发送提醒" id="sendRemindInfoBtn" onclick="sendInformation('remindInfo');"/>
		    </c:if>
	    </div>
	    
	    <form>
	    	<input id="hid_usercodes" type="hidden" name="usercodes" />
	    </form>
			<ec:table action="${pageContext.request.contextPath}/sys/vUserOnline!list.do" items="objList" var="userOnline"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" 
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"
			>
			<ec:row>
			     
			   <ec:column property="userOnline.usercode"
				title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
				sortable="false" width="2%">
				<input class="chk_one" type="checkbox" id="${userOnline.usercode}"
					class="ecbox" value="${userOnline.usercode}">
			   </ec:column>
			     <ec:column property="userstate" title="当前状态" style="text-align:center" styleClass="userstateTD" width="10%">
			      <c:if test="${userOnline.userstate=='1'}"><img data-userstate='${userOnline.userstate}' src="${pageContext.request.contextPath}/themes/css/images/use.png"></c:if>
			      <c:if test="${userOnline.userstate=='2'}"><img data-userstate='${userOnline.userstate}' src="${pageContext.request.contextPath}/themes/css/images/out.png"></c:if>
			   </ec:column>
				<ec:column property="unitname" title="部门" style="text-align:center" width="18%">
				 ${cp:MAPVALUE('unitcode2JC',userOnline.unitcode)}
				</ec:column>
                <ec:column property="username" title="登陆人员" style="text-align:center" styleClass="userNameTD" width="20%">
				 <span data-usercode='${userOnline.usercode}'> ${userOnline.username}</span>
				 
				<!--rtx 开关 -->
				<img data-usercode='${userOnline.usercode}' align="absbottom" width =16 height=16 src="${pageContext.request.contextPath}/scripts/rtx/images/blank.gif" <c:if test="${cp:MAPVALUE('SYSPARAM','RTX') eq 'T'}">onload='RAP("${cp:MAPVALUE('userloginname',userOnline.usercode)}");'</c:if>>
				</ec:column>
				<ec:column property="accesstime" title="登陆时间" style="text-align:center" width="20%">
				  <fmt:formatDate value="${userOnline.accesstime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</ec:column>
				<ec:column property="userdesc" title="用户描述" style="text-align:center" width="20%">
				  ${userOnline.userdesc}
				</ec:column>
			</ec:row>
		</ec:table>
		
		<script type="text/javascript">
		
		$(function(){
			
			LISTMAIL.init();
			
		});
		
		
		function sendInformation(type){
			
			var usercodes = $('#hid_usercodes').val();
			if(null == usercodes || '' == usercodes){
				alert("请选择人员！");
			}else{
				if('msg' == type){
					window.location.href="<%=request.getContextPath()%>/oa/oaSmssend!editSend.do?originate=usersOnline&usercodes=" + usercodes;
				}else if('mail' == type){
					window.location.href="<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&originate=usersOnline&usercodes=" + usercodes;
				}else if('remindInfo' == type){
					window.location.href="<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?originate=usersOnline&usercodes=" + usercodes;
				}
			}
		}
		
		var LISTMAIL = {
				init : function() {
					for ( var e in LISTMAIL) {
						if ('init' != e && $.isFunction(LISTMAIL[e])) {
							LISTMAIL[e]();
						}
					}
				},
				initCheckbox : function() {
					$('#chk_all').change(
							function() {
								var $checked = $(this).attr('checked');

								$.each($('input:checkbox.chk_one'), function(index,
										checkbox) {
									$(this).attr('checked', 'checked' == $checked);
									if ('checked' == $checked) {
										$(this).parent('span').addClass($checked);
									} else {
										$(this).parent('span')
												.removeClass($checked);
									}
								});
							});
				},

				bindCheckboxClick : function() {
					
					$('#chk_all, input:checkbox.chk_one').change(function() {
						var usercodes = LISTMAIL.getSelected();
						$('#hid_usercodes').val(usercodes.join(','));
					});
				},

				getSelected : function() {
					var usercodes = [];
					$.each($('input:checkbox.chk_one:checked'), function(index,
							checkbox) {
						usercodes.push($(this).val());
					});

					return usercodes;
				}
			};

		</script>
</body>
</html>
