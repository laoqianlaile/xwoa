<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>会议室信息</title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p class="ctitle">编辑会议室信息</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaMeetinfo" method="post" namespace="/oa"
			id="oaMeetinfoForm" enctype="multipart/form-data">
<div align="left">
<!-- 		     	<input type="button" -->
<!-- 					name="back" Class="btn" onclick="history.back(-1);" value="返回" /> -->
				<input type="button" class="btn" id="save" value="保存"
					onclick="submitItemFrame('SAVE');" /> 
				<input type="button" class="btn" id="back" value='返回'> 	
			</div>
			<input type="hidden" id="djid" name="djid" value="${djid }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">编号<span style="color: red">*</span></td>
					<td align="left"><input type="text" name="coding"
						style="width: 176px; height: 25px;" id="coding"
						class="focused required" value="${coding }" /></td>
					<td class="addTd">名称<span style="color: red">*</span></td>
					<td align="left"><input type="text" name="meetingName"
						style="width: 176px; height: 25px;" id="meetingName"
						class="focuse required" value="${meetingName }" /></td>
					<td colspan="2" rowspan="5"><c:choose>
							<c:when test="${not empty meetinfPictureName }">
								<img id="appicon"
									src='${contextPath }/oa/oaMeetinfo!getUserImgFromByte.do?djid=${djid}'
									alt="" style="width: 192px; height: 100px" />
							</c:when>
							<c:otherwise>
								<img id="appicon" src="<%=request.getContextPath()%>/styles/default/images/meetings.jpg" style="width: 192px; height: 100px" />
								
							</c:otherwise>
						</c:choose>
						<div id="localImag"></div>
						 <!-- 				<a style="text-decoration: none; color: blue;" --> <%-- 									href="<%=request.getContextPath()%>/oa/oaMeetinfo!downStuffInfo.do?djid=${djid}">${meetinfPictureName}</a> --%>
					</td>
				</tr>

				<tr>
					<td class="addTd">会议室类型</td>
					<td align="left">
					<select id="meetingType" style="width:200px;height:25px;"
						select name="meetingType">
							<c:forEach var="row" items="${cp:DICTIONARY('meetingType') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==meetingType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					
					</td>
					<td class="addTd">容纳人数</td>
					<td align="left"><input type="text" name="meetingNumber"
						id="meetingNumber" class="focuse number" value="${meetingNumber }"
						style="width: 176px; height: 25px;" /></td>

				</tr>
				
				<tr>
				    <td class="addTd">排序号</td>
					<td align="left"><input type="text" name="orderno"
						id="meetingNumber" class="focuse number" value="${orderno}"
						style="width: 176px; height: 25px;" /></td>
					 <td class="addTd">是否启用</td>
					<td align="left"><select style="width: 176px;" name="isuse">
					        <option value=""> </option>
							<option value="T" <c:if test="${'T' eq isuse }"> selected="selected"</c:if>>启用</option>
							<option value="F" <c:if test="${'F' eq isuse }"> selected="selected"</c:if>>禁用</option>
					</select></td>
				</tr>

				<tr>
					<td class="addTd">地点</td>
					<td align="left" colspan="3"><input type="text"
						name="meetingAddress" id="meetingAddress"
						value="${meetingAddress }" style="width: 100%; height: 25px;" /></td>
				</tr>
				<tr>
					<td class="addTd">楼层</td>
					<td align="left"><input type="text" name="meetingInfloor"
						id="meetingInfloor" value="${meetingInfloor }"
						style="width: 176px; height: 25px;" /></td>
					<td class="addTd">上传图片</td>
					<td align="left"><input type="file" id="fileupload" onchange="javascript:setImagePreview();"
						name="stuffFile" style="width: 176px; height: 25px;" ></td>
				</tr>
				<tr>
					<td class="addTd">主要用途:</td>
					<td align="left" colspan="5"><textarea id="meetingUseing"
							style="width: 100%;height: 50px;" name="meetingUseing" rows="5" cols="50">${meetingUseing }</textarea></td>
				</tr>
				<tr>
					<td class="addTd">基础配置:</td>
					<td align="left" colspan="5"><textarea id="basicConfiguration"
							style="width: 100%;height: 50px;" name="basicConfiguration" rows="3" cols="50">${basicConfiguration }</textarea></td>
				</tr>
				<tr>
					<td class="addTd">附属设备:</td>
					<td align="left" colspan="5"><textarea id="accessoryEquipment"
							style="width: 100%;height: 50px;" name="accessoryEquipment" rows="3" cols="50">${accessoryEquipment }</textarea></td>
				</tr>
				<tr>
					<td class="addTd">简介:</td>
					<td align="left" colspan="5"><textarea id="remark"
							style="width: 100%;height: 50px;" name="remark" rows="2" cols="50">${remark }</textarea></td>
				</tr>


				<tr>
					<td class="addTd">所属机构:<span style="color: red">*</span></td>
					<td align="left"><select id="depno"
						name="depno" style="width: 176px;">
								<option value="">---请选择---</option>
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==depno}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">责任部门<span style="color: red">*</span></td>
					<td align="left"><select 
						id="responsibleDep" ref="#responsiblePersion"
						data-value="${responsibleDep }"
						refUrl="${pageContext.request.contextPath}/oa/oaMeetinfo!option.do?type=US&unitcode={value}"
						name="responsibleDep" style="width: 176px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==responsibleDep}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">责任人<span style="color: red">*</span></td>
					<td align="left"><select id="responsiblePersion" 
						name="responsiblePersion"
						data-value="${responsiblePersion }" style="width: 176px;">
							<c:forEach var="row" items="${users }">
								<option value="${row.usercode}"
									<c:if test="${row.usercode==responsiblePersion}"> selected="selected"</c:if>>
									<c:out value="${row.username}" />
								</option>
							</c:forEach>
					</select></td>

				</tr>
			</table>
			
		</s:form>
	</fieldset>
</body>
<%-- <%@ include file="/page/common/scripts.jsp"%>  --%>
<script type="text/javascript">
$(function(){
	$('#back').click(function(){
		var srForm = document.getElementById("oaMeetinfoForm");
		srForm.action = 'oaMeetinfo!list.do';
		srForm.submit();
	});
});
	function submitItemFrame(subType) {
		if (docheck() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaMeetinfoForm");
			if (subType == 'SAVE') {
				srForm.action = 'oaMeetinfo!save.do';
			}
			srForm.submit();
		}
	}

	function docheck() {

		if ($('#coding').val() == '') {
			alert("编号不可为空！");
			$('#coding').focus();
			return false;
		}
		if ($('#meetingName').val() == '') {
			alert("会议室名称不可为空！");
			$('#meetingName').focus();
			return false;
		}
		var depno=$('#depno').find('option:selected').val();
		if(null == depno || '' == depno){
			alert("所属机构不可为空！");
			$('#depno').focus();
			return false;
		}
		var responsibleDep = $('#responsibleDep').find('option:selected').val();
		if (null == responsibleDep || '' == responsibleDep) {
			alert("责任部门不可为空！");
			$('#responsibleDep').focus();
			return false;
		}
		var meetingInfloor=$('#meetingInfloor').val();
		if(null!=meetingInfloor&&meetingInfloor.length>13){
			alert("楼层长度不大于13！");
			$('#meetingInfloor').focus();
			return false;
		}
		var responsiblePersion = $('#responsiblePersion').find(
				'option:selected').val();
		if (null == responsiblePersion || '' == responsiblePersion) {
			alert("责任人不可为空！");
			$('#responsiblePersion').focus();
			return false;
		}

	}

	$(function() {

		$('select[refUrl]').change(
				function() {

					var select = $(this);

					var ref = select.attr('ref');
					var url = select.attr('refUrl');

					if (ref && url) {
						var refSelect = $(ref);
						var refUrl = url.replaceAll('{value}', select.val());

						$.get(refUrl, function(json) {
							refSelect.find('option').remove();

							for ( var i = 0; i < json.length; i++) {
								var key = json[i].key;
								var value = json[i].value;

								refSelect.append('<option value="'+key+'">'
										+ value + '</option>');
							}

							//	                            无autoSelect()	
							// 				refSelect.autoSelect();

							// 如果是chosen下拉框：
							if ('chosen' == refSelect.data('rel')) {
								refSelect.trigger('liszt:updated');
							}

						}, "json");
					}

				});
	});
	function setImagePreview() {   
		var docObj = document.getElementById("fileupload");   
		var imgObjPreview = document.getElementById("appicon");   
		if (docObj.files && docObj.files[0]) {   
			//火狐下，直接设img属性    
			imgObjPreview.style.display = 'block';  
			imgObjPreview.style.width = '192px';   
			imgObjPreview.style.height = '100px';    
			//imgObjPreview.src = docObj.files[0].getAsDataURL();  
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式   
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);   
			} else {
				//IE下，使用滤镜    
				docObj.select();  
				var imgSrc = document.selection.createRange().text;   
				var localImagId = document.getElementById("localImag");
				//必须设置初始大小    
				localImagId.style.width = "192px"; 
				localImagId.style.height = "100px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片   
				try {  
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; 
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
					} catch (e) {    
						alert("您上传的图片格式不正确，请重新选择!");
						return false;  
						} 
					imgObjPreview.style.display = 'none'; 
					document.selection.empty();  
					}   
		return true;  
	} 
</script>
</html>