<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title>党费收缴管理
</title>
</head>
<body>
	<fieldset style="padding: 10px;">
		<legend> 党费收缴管理 (${object.transaffairname})</legend>
	</fieldset>
	<br/>
	<fieldset style="padding: 10px;">
		<legend> 已收缴人员 </legend>
		<div style="margin: 10px 0 10px 10px;">
		<input type=button value="导出Excel" class="whiteBtnWide"  onclick="exportExcelDues('${s_unitcode}','${djId}')">
		</div>

		<ec:table items="oaDuesFinished" var="doc" sortable="false"
			showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			tableId="uu">
			<ec:row>
				<ec:column property="usercode" title="人员姓名"
					style="text-align:center">
					${cp:MAPVALUE("usercode",doc.usercode)} 
					</ec:column>
				<ec:column property="unitcode" title="所属部门"
					style="text-align:center">
					${cp:MAPVALUE("unitcode",doc.unitcode)} 
				</ec:column>
				<ec:column property="amount" title="收缴金额" style="text-align:center" />
				<ec:column property="createtime" title="收缴时间" style="text-align:center">
				<fmt:formatDate value='${doc.createtime }'
					pattern='yyyy-MM-dd'/>
				</ec:column>
			</ec:row>
		</ec:table>

	</fieldset>
	<fieldset style="padding: 10px;">
		<legend> 未收缴人员 </legend>
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
	    <input type="hidden" id="djId_total" name="djId" value="${djId}" />
	    <input type="hidden" id="s_unitcode_total" name="s_unitcode" value="${s_unitcode}" />
		<form>
	    	<input id="hid_usercodes" type="hidden" name="usercodes" />
	    </form>
		<ec:table items="oaDuesNoFinish" var="zwh" sortable="false"
			showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			tableId="ur">
			<ec:row>
				<ec:column property="none"
					title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
					sortable="false" style="2%">
					<input class="chk_one" type="checkbox" id="${zwh.usercode}"
						class="ecbox" value="${zwh.usercode}">
				</ec:column>
				<ec:column property="usercode" title="人员姓名"
					style="text-align:center">${cp:MAPVALUE("usercode",zwh.usercode)} 
					</ec:column>
				<ec:column property="unitcode" title="所属部门"
					style="text-align:center">${cp:MAPVALUE("unitcode",zwh.unitcode)} 
				</ec:column>
				<ec:column property="amount" title="收缴金额" style="text-align:center" >
					<input type="text" id="assetnum${zwh.usercode}"  value="" style="height: 30px;width: 100%"/>
				</ec:column>
				<ec:column property="roleopt" title="操作" sortable="false" style="text-align:center" width="10%">
				<input type="button" name="subm" Class="btn" value="缴费"
					id="s_subm${zwh.usercode}" onclick="attendAct('${zwh.usercode}','${zwh.djId}','${zwh.unitcode}')"/>
				</ec:column>

			</ec:row>
		</ec:table>

	</fieldset>
	<br />

</body>

<script type="text/javascript">

		function openNewWindow(winUrl, winName, winProps) {
			if (winProps == '' || winProps == null) {
				winProps = 'height=900px,width=1100px,directories=false,location=false,top=0,left=150,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
			}
			window.open(winUrl, winName, winProps);
		}
		function attendAct(no,id,unitcode){
			var num=/^(\-|\+)?\d+(\.\d{1,2})?$/;
			var temp=/^\d+(\.\d)?$/;
			var amoutnum=$("#assetnum"+no).val();
			if(amoutnum != ""){
				if(num.test(amoutnum)){
					if(!temp.test(amoutnum)){
					alert("金额不能为负");
					return;
				    }
				 }else{
					 alert("金额格式不正确");
					 return;
				 }
	        }else{
	        	alert("金额不能为空！");
	        	return;
	        }
			
			if(confirm("提交后，记录将不可更改！确定此项操作吗?")){
			var url= "oaDuescollection!updateFee.do?usercode="+no+"&amout="+$("#assetnum"+no).val()+"&djId="+id+"&unitcode="+unitcode+"&s_unitcode="+$("#s_unitcode_total").val();
			document.location.href = url;
			}
		}
		function exportExcelDues(unitcode,djId){
			var url = "oaDuescollection!exportExcelDues.do?unitcode="+unitcode+"&djId="+djId;
			document.location.href = url;
		}
		
		$(function(){
			
			LISTMAIL.init();
			
		});
		
		
		function sendInformation(type){
			
			var usercodes = $('#hid_usercodes').val();
			if(null == usercodes || '' == usercodes){
				alert("请选择人员！");
			}else{
				var link="";
				var title="";
				if('msg' == type){
					title="发送短信";
					link="<%=request.getContextPath()%>/oa/oaSmssend!editSend.do?originate=otherSend&usercodes=" + usercodes;
				}else if('mail' == type){
					title="发送邮件";
					link="<%=request.getContextPath()%>/oa/innermsg!editDraft.do?s_msgtype=P&originate=otherSend&usercodes=" + usercodes;
				}else if('remindInfo' == type){
					title="发送内部消息提醒";
					link="<%=request.getContextPath()%>/oa/oaRemindInformation!built.do?originate=otherSend&usercodes=" + usercodes;
				}
				
				DialogUtil.open(title,link,900,600);
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
</html>
