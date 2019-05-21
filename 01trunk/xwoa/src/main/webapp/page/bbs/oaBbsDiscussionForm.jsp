<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title><s:text name="oaBbsDiscussion.edit.title" /></title>
<link href="${pageContext.request.contextPath}/styles/default/css/oabbs/bbs.css" rel="stylesheet" type="text/css" />
</head>
<body class="sub-flow">

	<fieldset class="form">
		<legend>
			<p>编辑子版块</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/bbs/oaBbsDiscussion!save.do"
			method="post" id="oaBbsDiscussionForm" enctype="multipart/form-data"
			data-validate="true">
             <input type="hidden" id="state" name="state"
				value="${object.state }" />
			<!-- 查询用 -->
			<input type="hidden" id="s_layoutcode" name="s_layoutcode"
				value="${object.layoutcode }" /> <input type="hidden"
				id="s_discussOrNot" name="s_discussOrNot" value="${s_discussOrNot }" />
			<input type="hidden" name="s_isOwner" value="${s_isOwner }" />
			<!-- 保存子版块用 -->
			
			<input type="hidden" name="layoutno" value="${object.layoutno }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">版面名称：</td>
					<td align="left">
					
<%-- 					<span>${object.oaBbsDashboard.layoutname}</span> --%>

                     <!-- 编辑  -->
                     <c:if test="${not empty  s_layoutcode}">
                     <input type="hidden" name="layoutcode" value="${object.layoutcode }" />
					<font class="getDashboardName" 
						 data-layoutcode="${object.layoutcode}"   id="${object.layoutcode}"></font>	
					</c:if>	 	
					 <!-- 编辑 -->	
					  
                     <!-- 新增						  -->
                    <c:if test="${empty  s_layoutcode}">
					<select name="layoutcode" style="width: 200px; height: 30px;">
<!-- 								<option value="">---请选择---</option> -->
								<c:forEach items="${dashboardList}" var="v">
									<option value="${v.layoutcode }"
										<c:if test="${v.layoutcode eq s_layoutcode}">selected="selected" </c:if>>${v.layoutname }</option>
								</c:forEach>
						</select>
					</c:if>
					 <!-- 新增						  -->		 
							</td>
                    <td class="addTd" rowspan="3">图片：</td>
					<td align="left" rowspan="3">
					<%--<c:choose>
						 <c:when test="${not empty showpicturename }">
								<img id="appicon"
									src='${pageContext.request.contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${object.layoutno}'
									alt="头像" style="width: 200px; height: 70px;" align="left" />
							</c:when>
							<c:otherwise>
								<img id="appicon" style="width: 200px; height: 70px"
									align="left" />
							</c:otherwise>
						</c:choose> --%>
						<img id="appicon"
									src='${pageContext.request.contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${object.layoutno}'
									alt="" style="width: 200px; height: 70px;" align="left" />
						</td>
					
				</tr>
				<tr>
				<td class="addTd">子版块名称：<font color="#ff0000">*</font></td>
					<td align="left"><input type="text" name="sublayouttitle"
						value="${object.sublayouttitle }"
						style="width: 200px; height: 30px;" class="focused required" /></td>
				</tr>		
				<tr>
					<td class="addTd">子模块名称色标：<font color="#ff0000">*</font></td>

					<td align="left"><select id="colorOftitle" name="colorOftitle"
						style="width: 200px; height: 30px;">
							<c:forEach var="row" items="${cp:DICTIONARY('TASKTAGBBS')}">
								<option value="${row.datacode}" class="${row.datacode}"
									<c:if test="${row.datacode==colorOftitle}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd">顺序号：</td>
					<td align="left"><input type="text" name="orderno"
						value="${object.orderno }" style="width: 200px; height: 30px;"
						class="focused number" /></td>
					<td class="addTd">子版块图片</td>
					<td><s:file name="uploadFile_" size="40"
							style="width:200px;height:30px" /></td>	
				</tr>
				<tr>
					<td class="addTd">所属帖子是否需要审核：<font color="#ff0000">*</font></td>
					<td align="left"><select name="isneed" style="width: 200px; height: 30px;"
						class="focused required">
							<option value="T"
								<c:if test="${'T' eq object.isneed }">selected="selected" </c:if>>需要</option>
							<option value="F"
								<c:if test="${'F' eq object.isneed }">selected="selected" </c:if>>不需要</option>
					</select></td>
                   <td class="addTd">是否公开：<font color="#ff0000">*</font></td>
					<td align="left"><select name="openType" style="width: 200px; height: 30px;"
						class="focused required">
							<c:forEach items="${cp:LVB('DiscussOpenType')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq object.openType }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<%-- <td class="addTd">图片名称：<font color="#ff0000">*</font></td>
					<td align="left"><input type="text" name="showpicturename" class="focused required" 
					    value="${object.showpicturename }" style="width: 200px; height: 24px;"/></td> --%>

					<td class="addTd">是否设置开放时间：</td>
					<td align="left" colspan="3"><select id="isdocontral" name="isdocontral"
						style="width: 200px;height: 30px;" class="focused required">
							<option value="F"
								<c:if test="${'F' eq object.isdocontral }">selected="selected" </c:if>>不设置</option>
							<option value="T"
								<c:if test="${'T' eq object.isdocontral }">selected="selected" </c:if>>设置</option>
					</select></td>

					
				</tr>

				<tr id="amtime">
					<td class="addTd">上午时间开始</td>
					<td align="left"><input type="text" class="Wdate"
						data-type="time" id="starttimeTemp" name="starttimeTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						<c:if test="${null ne object.starttime && '' ne object.starttime}" >
						value="${fn:substring(object.starttime, 11, 16) }"						
						</c:if>
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'00:00',maxDate:'12:00'})"
						placeholder="选择日期" />
					<td class="addTd">上午时间结束</td>
					<td align="left"><input type="text" class="Wdate"
						id="endtimeTemp" name="endtimeTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						<c:if test="${null ne object.endtime && '' ne object.endtime}" >
						value="${fn:substring(object.endtime, 11, 16) }"						
						</c:if>
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'00:00',maxDate:'12:00'})"
						placeholder="选择日期" />
				</tr>

				<tr id="pmtime">
					<td class="addTd">下午时间开始</td>
					<td align="left"><input type="text" class="Wdate"
						id="starttimepmTemp" name="starttimepmTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						<c:if test="${null ne object.starttimepm && '' ne object.starttimepm}">
						value="${fn:substring(object.starttimepm, 11, 16) }"						
						</c:if>
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'12:00',maxDate:'23:59'})"
						placeholder="选择日期" /></td>

					<td class="addTd">下午时间结束</td>
					<td align="left"><input type="text" class="Wdate"
						id="endtimepmTemp" name="endtimepmTemp"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						<c:if test="${null ne object.endtimepm && '' ne object.endtimepm}" >
						value="${fn:substring(object.endtimepm, 11, 16) }"						
						</c:if>
						onclick="WdatePicker({dateFmt:'HH:mm',minDate:'12:00',maxDate:'23:59'})"
						placeholder="选择日期" /></td>
				</tr>

				<%-- <tr>
				    <td class="addTd">审核意见：<font color="#ff0000">*</font></td>
					<td align="left" colspan="3">
					    <select name="approvalresults" style="width: 200px;" class="focused required" >
					        <option value="T"
								<c:if test="${'T' eq object.approvalresults }">selected="selected" </c:if>>通过</option>
							<option value="F"
								<c:if test="${'F' eq object.approvalresults }">selected="selected" </c:if>>不通过</option>
					    </select></td>
				</tr> --%>


			</table>

			<div class="formButton">
				<input type="button" id="saveBtn" name="save" class="btn" value="保存" />

				<input type="button" id="backBtn" name="back" class="btn" value="返回"
					onclick="window.history.back();" />

			</div>

		</form>
	</fieldset>

	<script type="text/javascript">
	// 初始化 显示模块板块名称
	$(".getDashboardName").each(function(){
		var layoutcode=$(this).data("layoutcode");
		$.ajax({
			type:"post",
			url:"${contextPath}/bbs/oaBbsDashboard!getLayoutName.do?layoutcode="+layoutcode,
			dataType:"json",
			success:function(json){
				$("#" + layoutcode).html(json.status);
			},
			error:function(){
				alert("数据获取失败，刷新后重试！");
			}
		});
	});
	
	
		$(function() {

			/* 如果设置返回按钮为链接时 */
			/* var s_discussOrNot = $('#s_discussOrNot').val();
			$('#backBtn').click(function() {

				var action;
				if ('Y' == s_editOrNot) {
					action = "${pageContext.request.contextPath}/bbs/oaBbsDiscussion!list.do";
				}else{
					$('#s_layoutcode').val(null);
					action = "${pageContext.request.contextPath}/bbs/oaBbsDashboard!list.do";
				}
				$('#oaBbsDiscussionForm').attr('action',action);
				$('#oaBbsDiscussionForm').submit();

			}); */

			var amtime = $('#amtime');
			var pmtime = $('#pmtime');

			var isdocontral = $('#isdocontral').val();

			var starttime = $('#starttimeTemp');
			var endtime = $('#endtimeTemp');
			var starttimepm = $('#starttimepmTemp');
			var endtimepm = $('#endtimepmTemp');

			/* 判断是否显示开放时间控件 */
			if ('T' == isdocontral) {
				amtime.show();
				pmtime.show();
			} else {
				amtime.hide();
				pmtime.hide();
			}

			/* 根据“是否开放设置时间”值显示或隐藏控件 */
			$('#isdocontral').change(function() {
				var isdocontral = $(this).val();

				if ('T' == isdocontral) {
					amtime.show();
					pmtime.show();
				} else {
					amtime.hide();
					pmtime.hide();
					// 不设置开放时间时，将原先所有的时间点都设为空
					starttime.val('');
					endtime.val('');
					starttimepm.val('');
					endtimepm.val('');
				}
			});

			/* 提交时给时间点加上默认的年月日 */
			$('#saveBtn').click(function() {

				if (null != starttime.val() && '' != starttime.val()) {
					var v = '2000-01-01 ' + starttime.val();
					starttime.val(v);
				} else
					starttime.val('');

				if (null != endtime.val() && '' != endtime.val()) {
					var v = '2000-01-01 ' + endtime.val();
					endtime.val(v);
				} else
					endtime.val('');

				if (null != starttimepm.val() && '' != starttimepm.val()) {
					var v = '2000-01-01 ' + starttimepm.val();
					starttimepm.val(v);
				} else
					starttimepm.val('');

				if (null != endtimepm.val() && '' != endtimepm.val()) {
					var v = '2000-01-01 ' + endtimepm.val();
					endtimepm.val(v);
				} else
					endtimepm.val('');

				$('#oaBbsDiscussionForm').submit();
			});

		});
	</script>


</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>
