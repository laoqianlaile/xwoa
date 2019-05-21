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
<!-- 	<fieldset style="padding: 10px;">
		<legend>党员管理</legend>
	</fieldset>
	<br/> -->
	<fieldset style="padding: 10px;">
		<legend>已选人员 </legend>
		<div style="margin: 10px 0 10px 10px;">
		<input type=button value="批量取消" class="whiteBtnWide"  onclick="sendInformation('cancel')">
		<input type=button value="导出Excel" class="whiteBtnWide"  onclick="exportExcelDues('${s_unitcode}');">
		</div>
		<form>
	    	<input id="hid_usercodes_" type="hidden" name="usercodes_" />
	    </form>
		<ec:table items="oaUserinfos" var="doc" sortable="false"
			showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			tableId="uu">
			<ec:row>
			<ec:column property="none"
					title='<input id="chk_all_" type="checkbox"  value="全选" name="quanxuan"  />'
					sortable="false" style="2%">
					<input class="chk_one_" type="checkbox" id="${doc.usercode}"
						class="ecbox" value="${doc.usercode}">
				</ec:column>
				<ec:column property="usercode" title="人员姓名"
					style="text-align:center">
					${cp:MAPVALUE("usercode",doc.usercode)}
				</ec:column>
				<ec:column property="unitcode" title="所属部门"
					style="text-align:center">
					${cp:MAPVALUE("unitcode",doc.fUserunit.unitcode)} 
				</ec:column>
				<ec:column property="sex" title="性别" style="text-align:center">
					${cp:MAPVALUE("sex",doc.sex)}
				</ec:column>
				<ec:column property="userdesc" title="职务" style="text-align:center">
					${doc.fUserinfo.userdesc}
				</ec:column>
				<ec:column property="userdesc" title="联系电话"
					style="text-align:center">
					${doc.mobilephone}
				</ec:column>
				<ec:column property="roleopt" title="操作" sortable="false" style="text-align:center" width="10%">
				<input type="button" name="subm" Class="btn" value="取消"
					id="s_subm${zwh.usercode}" onclick="attendAct_('${doc.usercode}','${doc.fUserunit.unitcode}')"/>
				</ec:column>
			</ec:row>
		</ec:table>

	</fieldset>
	<fieldset style="padding: 10px;">
		<legend>待选人员 </legend>
		<div style="margin: 10px 0 10px 10px;">
		<input type=button value="批量添加" class="whiteBtnWide"  onclick="sendInformation('add')">
		</div>
	    <input type="hidden" id="s_unitcode_total" name="s_unitcode" value="${s_unitcode}" />
		<form>
	    	<input id="hid_usercodes" type="hidden" name="usercodes" />
	    </form>
		<ec:table items="oaUserinfosNo" var="zwh" sortable="false"
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
					style="text-align:center">
					${cp:MAPVALUE("usercode",zwh.usercode)}
				</ec:column>
				<ec:column property="unitcode" title="所属部门"
					style="text-align:center">
					${cp:MAPVALUE("unitcode",zwh.fUserunit.unitcode)} 
				</ec:column>
				<ec:column property="sex" title="性别" style="text-align:center">
					${cp:MAPVALUE("sex",zwh.sex)}
				</ec:column>
				<ec:column property="userdesc" title="职务" style="text-align:center">
					${zwh.fUserinfo.userdesc}
				</ec:column>
				<ec:column property="userdesc" title="联系电话"
					style="text-align:center">
					${zwh.mobilephone}
				</ec:column>
				<ec:column property="roleopt" title="操作" sortable="false" style="text-align:center" width="10%">
				<input type="button" name="subm" Class="btn" value="添加"
					id="s_subm${zwh.usercode}" onclick="attendAct('${zwh.usercode}','${zwh.fUserunit.unitcode}')"/>
				</ec:column>
			</ec:row>
		</ec:table>
	</fieldset>
	<br/>
</body>
<script type="text/javascript">
		function attendAct(no,unitcode){//添加
			if(confirm("确定此项操作吗?")){
			var url= "oaDuescollection!doUserIn.do?usercode="+no+"&unitcode="+unitcode+"&s_unitcode="+$("#s_unitcode_total").val();
			document.location.href = url;
			}
		}
		function attendAct_(no,unitcode){//取消
			if(confirm("确定此项操作吗?")){
			var url= "oaDuescollection!doUserOut.do?usercode="+no+"&unitcode="+unitcode+"&s_unitcode="+$("#s_unitcode_total").val();
			document.location.href = url;
			}
		}
		function exportExcelDues(unitcode){//导出
			var url = "oaDuescollection!exportExcelUser.do?unitcode="+unitcode;
			document.location.href = url;
		}
		function sendInformation(type){//批量操作
			var usercodes = $('#hid_usercodes').val();
			var usercodes_ = $('#hid_usercodes_').val();
			if('add' == type&&(null == usercodes || '' == usercodes) || 'cancel' == type&&(null == usercodes_ || '' == usercodes_)){
				alert("请选择人员！");
			}else{
				if(confirm("确定此项操作吗?")){
				if('add' == type){
					document.location.href ="<%=request.getContextPath()%>/oa/oaDuescollection!doUsersIn.do?usercodes=" + usercodes+"&s_unitcode="+$("#s_unitcode_total").val();
				}else if('cancel' == type){
					document.location.href ="<%=request.getContextPath()%>/oa/oaDuescollection!doUsersOut.do?usercodes=" + usercodes_+"&s_unitcode="+$("#s_unitcode_total").val();
				}
				}
			}
		}
		
		$(function(){
			LISTMAIL.init();
			
		});
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
					$('#chk_all_').change(
							function() {
								var $checked_ = $(this).attr('checked');

								$.each($('input:checkbox.chk_one_'), function(index,
										checked_) {
									$(this).attr('checked', 'checked' == $checked_);
									if ('checked' == $checked_) {
										$(this).parent('span').addClass($checked_);
									} else {
										$(this).parent('span')
												.removeClass($checked_);
									}
								});
							});
				},

				bindCheckboxClick : function() {
					
					$('#chk_all, input:checkbox.chk_one').change(function() {
						var usercodes = LISTMAIL.getSelected();
						$('#hid_usercodes').val(usercodes.join(','));
						//alert($('#hid_usercodes').val());
					});
					$('#chk_all_, input:checkbox.chk_one_').change(function() {
						var usercodes_ = LISTMAIL.getSelected_();
						$('#hid_usercodes_').val(usercodes_.join(','));
						//alert($('#hid_usercodes_').val());
					});
				},

				getSelected : function() {
					var usercodes = [];
					$.each($('input:checkbox.chk_one:checked'), function(index,
							checkbox) {
						usercodes.push($(this).val());
					});

					return usercodes;
				},
				getSelected_ : function() {
					var usercodes_ = [];
					$.each($('input:checkbox.chk_one_:checked'), function(index,
							checkbox) {
						usercodes_.push($(this).val());
					});

					return usercodes_;
				}
			};

		</script>
</html>
