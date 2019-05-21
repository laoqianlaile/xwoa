<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
    <style>
      .BoaSchedule{background-color: #ade2fc;color: #fff;}
      .GoaSchedule{background-color:#9fda9f;color: #fff;}
      .NoaSchedule{background-color:#FFFFFF;color: #000000;}
      .YoaSchedule{background-color:#fdfd95;color: blue;}
      .PoaSchedule{background-color:#e6c2e6;color: #fff;}
      .RoaSchedule{background-color: #cb6768;color: #fff;}
      .OoaSchedule{background-color: #ffd68c;color: #fff;}
    </style>
<script type="text/javascript">

$(function(){
	var importantTag = $("input[type=radio]").is(':checked');
	if(!importantTag){
		$("input[type=radio]:eq(1)").attr("checked","checked");
	}
});
</script>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaSchedule.edit.title" /></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>

</head>

<body class="sub-flow">

 <fieldset class="form">
    <c:if test="${empty xzrc_sy}">
		<legend>
		<s:text name="oaSchedule.edit.title" />
		</legend> 
	</c:if>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form name="myForm"  id="myForm" action="oaSchedule!saveAddOaSchedule.do" method="post" namespace="/oa" >
		
		<c:if test="${empty xzrc_sy}">
            <div style="margin-top:5px;">
			<input type="button" class="btn" onclick="checkForm();" value="提交"/>
			<input type="button" name="back" Class="btn"
			onclick="art.dialog.close();" value="关闭" />
			</c:if>
			<c:if test="${not empty xzrc_sy}">
			<div style="margin-top:0px;margin-bottom: 0px;">
				<input type="button" class="whiteBtnWide" onclick="checkForm();" value="提交"/>
			</c:if>			
			</div>
		
		
		<c:if test="${ not empty schno}">
			<input type="hidden" id="schno" name="schno" value="${schno}" />
		</c:if>
		<s:hidden id="s_sehType" name="s_sehType" value="%{s_sehType}"></s:hidden>
		<s:hidden id="seh_type" name="seh_type" value="%{s_sehType}"></s:hidden>
		<input type="hidden" id="xzrc_sy" value="${xzrc_sy}" />
		<table style="margin-top: 10px;" border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd"><s:text name="oaSchedule.itemtype" /><font color='red'>*</font></td>
				<td align="left" colspan="2"><select  id="itemtype"
					name="itemtype" style="width: 200px;height:25px;">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('oaItemType')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==itemtype}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select></td>
				<td class="addTd"><s:text name="oaSchedule.sehType" /></td>
				<td align="left"  colspan="2"><c:if test="${ not empty schno}">
						<input type="hidden" id="sehType" name="sehType"
							value="${sehType}"  style="width: 200px;height:25px;" />
				${cp:MAPVALUE('oaSehType',sehType)}
		</c:if> <c:if test="${empty schno}">
						<input type="hidden" id="sehType" name="sehType"  style="width: 200px;height:25px;"
							value="${s_sehType}" />
			${cp:MAPVALUE('oaSehType',s_sehType)}
		</c:if></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaSchedule.importantTag" /></td>
				<td align="left"  colspan="2">
                <s:radio name="importantTag" id="importantTag"  list="#{'L':'低','M':'中','H':'高' }" listKey="key" listValue="value"   value="%{importantTag }" ></s:radio>
				<%-- <select id="importantTag"
					name="importantTag"  style="width: 200px;height:25px;">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('IMP')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==importantTag}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select> --%>
				</td>
				<td class="addTd"><s:text name="oaSchedule.taskTag" /></td>

				<td align="left"  colspan="2"><select id="taskTag"
					name="taskTag"  style="width: 100px;height:25px;">
						<c:forEach var="row" items="${cp:DICTIONARY('TASKTAG')}">
							<option value="${row.datacode}" class="${row.datacode}oaSchedule"
								<c:if test="${row.datacode==taskTag}"> selected="selected"</c:if>>
								 <c:out value="${row.datavalue}" /> 
							</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td class="addTd"><s:text name="oaSchedule.planBegTime" /><font color='red'>*</font></td>

				<td align="left"  colspan="2">					  		
				<input type="text" class="Wdate"  id="planBegTime" name="planBegTime" 
				style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.planBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期">
						</td>
				<td class="addTd"><s:text name="oaSchedule.planEndTime" /><font color='red'>*</font></td>

				<td align="left"  colspan="2">
				<input type="text" class="Wdate"  id="planEndTime" name="planEndTime" 
				style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.planEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期">
						</td>

			</tr>
            <tr>
				<td class="addTd"><s:text name="oaSchedule.title" /><font color='red'>*</font></td>
				<td align="left" colspan="5"><s:textarea name="title"
						id="title" cols="40" rows="2"  style="width:100%;height:25px;" /></td>
			</tr>
			<c:if test="${  sehType eq '2'or s_sehType eq '2'}">
				<tr id="tr_leaders">
					<td class="addTd">领导成员</td>
					<td align="left" colspan="5"><input type="text" id="leaders"
						name="leaders"  style="width: 100%; height: 25px;"
						value="${leaders}" readonly="readonly" /> <input type="hidden"
						id="leaderValue" name="leaderValue" value="${leaderValue}" />
				</tr>
			</c:if>

			

<!-- 			<tr id="tr_meetid"> -->
<%-- 				<td class="addTd"><s:text name="oaSchedule.meetid" /></td> --%>
<%-- 				<td align="left" colspan="3"><s:textfield name="meetid" --%>
<%-- 						id="meetid" size="40" style="width: 80%;height: 27px;" /> <input --%>
<!-- 					type="button" class='btn' name="powerBtn" -->
<%-- 					onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetinfo!selectList.do?','powerWindow',null);" --%>
<!-- 					value="选择"></td> -->
<!-- 			</tr> -->

			<tr id="tr_city">
				<td class="addTd"><s:text name="oaSchedule.city" /></td>
				<td align="left" colspan="5"><input type="text" id="city"
					name="city" style="width: 100%; height: 25px;" value="${city}"
					readonly="readonly" /> <input type="hidden" id="userValue"
					name="userValue" value="${userValue}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaSchedule.address" /></td>
				<td align="left" colspan="5">
				<s:textfield id="address"
						name="address" cols="40" rows="4"
						 style="width:100%;height:25px;" />
				 <input type="button" class='btn btnWide' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetinfo!selectList.do?','powerWindow',null);"
					value="选择会议室"></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaSchedule.remark" /></td>
				<td align="left"colspan="5"><s:textarea name="remark" id="remark"
						cols="40" rows="2" style="width: 100%;height: 50px;" /></td>
						
			</tr>
			
			
			
			<tr class="tr_itemtype">
				<td class="addTd"><s:text name="oaSchedule.isEmail" /></td>

				<td align="left" colspan="2"><select id="isEmail"
					name="isEmail"  style="width: 200px;height:25px;">
						<c:forEach var="row" items="${cp:DICTIONARY('oaIsEmail')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==isEmail}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select></td>
				<td class="addTd"><s:text name="oaSchedule.noticeSign" /></td>

				<td align="left" colspan="2"><select id="noticeSign"
					name="noticeSign"  style="width: 200px;height:25px;">
						<c:forEach var="row" items="${cp:DICTIONARY('oaNoticeSign')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode==noticeSign}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr class="tr_itemtype">
				<td class="addTd"><s:text name="oaSchedule.isDo" /></td>

				<td align="left" colspan="5"><select id="isDo"
						name="isDo"  style="width: 200px;height:25px;">
							<c:forEach var="row" items="${cp:DICTIONARY('oaIsDo')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==isDo}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
			</tr>
			<!-- 			日程响应，领导日程不显示 -->

			<c:if test="${  sehType eq '1'or s_sehType eq '1'}">
				
				<!-- 				编辑时出现响应信息 -->

				<c:if test="${ not empty schno}">
					<tr class="tr_itemtype">
						<td class="addTd">接受响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListJS}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应
						<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
						
						&nbsp;<br>
							</c:forEach></td>
					</tr>

					<tr class="tr_itemtype">
						<td class="addTd">暂缓响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListZH}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应,${cp:MAPVALUE('oaResType',att.resType)}至
						<fmt:formatDate value='${att.stopTime}'
									pattern='yyyy-MM-dd  HH:mm' />&nbsp;
							<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
								<br>
							</c:forEach></td>
					</tr>

					<tr class="tr_itemtype">
						<td class="addTd">拒绝响应</td>
						<td align="left" colspan="5"><c:forEach items="${resListJJ}"
								varStatus="i" var="att">
						${cp:MAPVALUE('usercode',att.usercode)} 于
						<fmt:formatDate value='${att.createdate}'
									pattern='yyyy-MM-dd  HH:mm' />
						做出  ${cp:MAPVALUE('oaResType',att.resType)} 响应
						<c:if test="${not empty  att.remark}">
						,备注：${att.remark}
						</c:if>
								<br>
							</c:forEach></td>
					</tr>
				</c:if>
<!-- 				<tr class="tr_itemtype"> -->
<%-- 					<td class="addTd"><s:text name="oaSchedule.isDo" /></td> --%>

<!-- 					<td align="left"><select data-rel="chosen" id="isDo" -->
<!-- 						name="isDo" class="combox" style="width: 120px;"> -->
<%-- 							<c:forEach var="row" items="${cp:DICTIONARY('oaIsDo')}"> --%>
<%-- 								<option value="${row.datacode}" --%>
<%-- 									<c:if test="${row.datacode==isDo}"> selected="selected"</c:if>> --%>
<%-- 									<c:out value="${row.datavalue}" /> --%>
<!-- 								</option> -->
<%-- 							</c:forEach> --%>
<!-- 					</select></td> -->
<!-- 					<td colspan="2"></td> -->
<!-- 				</tr> -->
				<tr class="tr_itemtype">
					<td class="addTd"><s:text name="oaSchedule.resRemark" /></td>

					<td align="left" colspan="5"><s:textarea name="resRemark"
							cols="40" rows="2" style="width: 100%;height: 27px;" /></td>
				</tr>
			</c:if>


		</table>
		<%-- <div class="formButton">
		<c:if test="${not empty xzrc_sy }">
			<input type="button" class="btn" onclick="checkForm();" value="提交"/>
		</c:if>
		<c:if test="${empty xzrc_sy }">
		<input type="button" class="btn" onclick="checkForm();" value="保存"/>
		<input type="button" name="back" Class="btn"
			onclick="window.close();" value="关闭" />
			</c:if>
		</div>	 --%>
		

		<!-- 选择人员的模块 -->
		<div id="attAlert" class="alert"
			style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
			<h4>
				<span id="selectTT">选择</span><span id="close2"
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
					<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
					<b class="btns"> <input id="save" class="btn" type="button"
						value="保     存" /><input id="clear" class="btn" type="button"
						value="取     消" style="margin-top: 5px;" /></b>
				</div>
			</div>
		</div>


		<%-- 	<c:if test="${ not empty creater }"> --%>

		<!-- 	<fieldset -->
		<!-- 			style="border: hidden 1px #000000; "> -->
		<%-- 	 <iframe id="tabFrames1" name="tabFrames" src="<%=request.getContextPath()%>/oa/oaScheduleResponse!list.do?s_schno=${schno}&viewtype=F" onload="autoHeight(this);"  width="100%" --%>
		<!-- 			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe>  -->
		<!-- 	</fieldset> -->

		<%-- 	</c:if> --%>

		<%-- 	<jsp:include page ="/page/oa/common/oaSchedule.jsp" flush="true"> --%>
		<%--     <jsp:param name = "type" value = "list"/> --%>
		<%--     <jsp:param name = "viewtype" value = "F"/> --%>
		<%--      </jsp:include> --%>
		<%-- <%@ include file="/page/oa/common/oaSchedule.jsp"%> --%>
	</s:form>
	</fieldset>
	<script type="text/javascript">
		function openNewWindow(winUrl, winName, winProps) {
			if (winProps == '' || winProps == null) {
				winProps = 'height=600px,width=800px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
			}
			window.open(winUrl, winName, winProps);
		}

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

		$("#city").click(function() {
			var d = '${userjson}';

			if (d.trim().length) {
				new treePerson(jQuery.parseJSON(d ), $("#city"), $("#userValue")).init();
				setAlertStyle("attAlert");
			}
			;
		});
		$("#leaders").click(
				function() {
					var d = '${userjson}';

					if (d.trim().length) {
						new treePerson(jQuery.parseJSON(d ), $("#leaders"), $("#leaderValue")).init();
						setAlertStyle("attAlert");
					}
					;
				});

		

		$(function() {

			function isShow() {
				var v = $('#itemtype').find('option:selected').val();

				//if ('3' == v) {
					//$('#tr_meetid').show();
 					//$('#tr_city').show();
					//$('.tr_itemtype').show();
					//$('input[name="powerBtn"]').show();
					
				//} else {
					$('#tr_meetid').hide();
					$('#tr_city').hide();
					$('.tr_itemtype').hide();
					$('input[name="powerBtn"]').hide();
				//}
			}

			$('#itemtype').change(function() {
				isShow();
			});

			isShow();

		});
		function checkForm() {
			var flag=true;
            if ($('#title').val() == '') {
				alert("主题不可为空！");
				$('#title').focus();
				flag = false;
				return flag;
			}else if($('#title').val().length>150){
				
				alert("主题超出最大长度限制！");
				$('#title').focus();
				flag = false;
				return flag;
			}
            
            var v = $('#itemtype').find('option:selected').val();
			if (null == v || '' == v) {
				alert("事项类型不可为空！");
				$('#itemtype').focus();
				flag = false;
				return flag;
			}
			
			 if ($("#planBegTime").val() == '') {
					alert("开始时间不能为空！");
					$('#planBegTime').focus();
					flag = false;
					return flag;
			}
			 if ($("#planEndTime").val() == '') {
					alert("结束时间不能为空！");
					$('#planEndTime').focus();
					flag = false;
					return flag;
			}
			 if($("#planBegTime").val()>$("#planEndTime").val()){
				 alert("开始时间不能大于结束时间！");
				 $('#planBegTime').focus();
				 flag = false;
				 return flag;
			}	
				
				var date=new Date();
				if ($("#planBegTime").val() != '') {
				var dt = new Date($("#planBegTime").val().replace(/-/,"/"));  
				if (dt <date) {
// 					$('#planBegTime').focus();
                    var flg=confirm("所选预计开始时间发生在过去，确定使用该时间吗？");
                    if(!flg){
                    	flag = false;
    					return flag;
				   }
				 }
				}
				
				if ($("#planEndTime").val() != '') {
					var dt = new Date($("#planEndTime").val().replace(/-/,"/"));  
					if (dt <date) {
						
						var flg=confirm("所选预计结束时间发生在过去，确定使用该时间吗？");
	                    if(!flg){
	                    	flag = false;
	    					return flag;
					   }
					}
				}
				if ($("#address").val() != '') {
					//数据库汉字占2-3个字节，
					if($("#address").val().trim().length>100){
						
						alert("地点长度不超过100个字！");
						$("#address").focus();
						flag = false;
    					return flag;
					}
				}
				if ($("#remark").val() != '') {
					if($("#remark").val().trim().length>600){
						alert("事项说明长度不超过600个字！");
						$("#remark").focus();
						flag = false;
    					return flag;
					}
				}
				
                
				if ('2' == $("#sehType").val() && $("#leaders").val() != '') {
					if($("#leaders").val().trim().length>1200){
						alert("领导成员长度不超过1200个字！");
						$("#leaders").focus();
						flag = false;
    					return flag;
					}
				}
				/* 这里用ajax方式提交是因为用普通方式提交后后面的内容不会被执行 */
				if($("#xzrc_sy").val()!=null&&$("#xzrc_sy").val()!=""){
				$.ajax({
	                 type: "post",
	                 url: "oa/oaSchedule!saveAddOaSchedule.do",     
	                 data: $("#myForm").serialize(),    
	                 success: function(data) {
	                    var s=$("#xzrc_sy").val();
	         			if(s=="T"){
	        				var time=$('#newtime', parent.document).val();
	        				var t="../oa/oaSchedule!addOaSchedule.do?&planBegTime_s="+time+" 11:00:00&planEndTime_s="+time+" 20:00:00&s_sehType=1&s_canAdd=T&xzrc_sy=T";
	        				var c=$('#rcap_table', parent.document);
	        				c.attr('src',t);
	        				}
	                 },
	                 error: function(data) {
	                     alert("提交失败，请重新尝试或联系管理员！");
	                 }
	             })
				}else{
					
					$.ajax({
		                 type: "post",
		                 url: "oa/oaSchedule!saveAddOaSchedule.do",     
		                 data: $("#myForm").serialize(),    
		                 success: function(data) {
		                	 var win = art.dialog.open.origin;//来源页面
		                		// 如果父页面重载或者关闭其子对话框全部会关闭
		                		win.location.reload();
		                		art.dialog.close();
		     				
		                 },
		                 error: function(data) {
		                     alert("提交失败，请重新尝试或联系管理员！");
		                 }
		             })
				}
			
			/* window.close(); */	
		}
	</script>