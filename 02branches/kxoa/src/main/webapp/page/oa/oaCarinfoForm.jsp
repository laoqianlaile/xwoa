<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <%-- <sj:head locale="zh_CN" /> --%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaCarinfo.edit.title" /></title>
</head>

<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaCarinfo.edit.title" />
			</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>
		<form 
			action="${pageContext.request.contextPath}/oa/oaCarinfo!save.do"
			method="post" id="oaCarinfoForm" enctype="multipart/form-data"  data-validate="true">
			<div align="left">
				<button type="submit" class="btn btn-primary">保存</button>	
				<input type="button" name="back" class="btn"
					onclick="window.location.href='${pageContext.request.contextPath}/oa/oaCarinfo!list.do'" value="返回" />
			</div>
			<c:if test="${ not empty djid}">
				<input type="hidden" id="djid" name="djid" value="${djid}" />
			</c:if>
			<input type="hidden" id="rangeType" name="rangeType" value="N" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.carno" /><span
						style="color: red">*</span></td>
					<td align="left">
						<input type="text"  id="carno" name="carno" class="focuse required" value="${carno }"  style="width:200px;height:25px" />
<%-- 					<s:textfield id="carno" name="carno" size="40" class="focuse required" /> --%>
					</td>
					<td rowspan="5" class="addTd">图片</td>
					<td rowspan="5" align="center"><c:choose>
							<c:when test="${not empty carPictureName }">
								<img id="appicon"
									src='${contextPath }/oa/oaCarinfo!getUserImgFromByte.do?djid=${djid}'
									alt="头像" style="width: 300px; height: 150px;" align="left" />
							</c:when>
							<c:otherwise>
								<img id="appicon" style="width: 300px; height: 150px" src="<%=request.getContextPath()%>/newStatic/image/celiang.png"
									align="left" />
							</c:otherwise>
						</c:choose>
						<div id="localImag"></div>
						</td>

				</tr>

				<tr>

					<td class="addTd"><s:text name="oaCarinfo.isuse" /></td>
					<td align="left"><select id="isuse" style="width:200px;height:25px"
						name="isuse">
							<c:if test="${ empty isuse}">
								<option value="">---请选择---</option>
							</c:if>
							<c:forEach var="row" items="${cp:DICTIONARY('equState')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==isuse}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.carType" /></td>
					<td align="left"><select id="carType"  style="width:200px;height:25px"
						name="carType" >
								<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('canDriveType')}">
								<option value="${row.value}"
									<c:if test="${row.value==carType}"> selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
					</select></td>

				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.brand" /></td>
					<td align="left">
						<input type="text" name="brand" value="${brand }"  data-rule-maxlength="10" style="width:200px;height:25px" />
<%-- 					<s:textfield name="brand" size="40" /> --%>
					
					</td>

				</tr>
				
				<tr>
					<td class="addTd">行驶证号 <span
						style="color: red">*</span></td>
					<td align="left">
						<input type="text" name="driveNo" value="${driveNo }" data-rule-maxlength="50"  class="focuse required" style="width:200px;height:25px" />					
					</td>

				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.modelType" /></td>
					<td align="left">
						<input type="text" name="modelType" value="${modelType }"  data-rule-maxlength="10"  style="width:200px;height:25px" />
<%-- 					<s:textfield name="modelType" size="40" /> --%>
					</td>
					<td class="addTd">车辆图片</td>
					<td><s:file id="fileupload" onchange="javascript:setImagePreview();" name="uploadFile_" size="40"   style="width:200px;height:25px" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.ratifyNumber" /></td>
					<td align="left">
						<input type="text" name="ratifyNumber" class="focuse digits"  value="${ratifyNumber}"  style="width:200px;height:25px" />
<%-- 					<s:textfield name="ratifyNumber" size="40"  class="focuse digits" /> --%>
					</td>
					<td class="addTd"><s:text name="oaCarinfo.ratifyLoad" /></td>
					<td align="left">
						<input type="text" name="ratifyLoad" value="${ratifyLoad }" maxlength="10" style="width:200px;height:25px" />
<%-- 					<s:textfield name="ratifyLoad" size="40" /> --%>
					</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.ratifyOil" /></td>
					<td align="left">
						<input type="text" name="ratifyOil" value="${ratifyOil }" maxlength="10" style="width:200px;height:25px" />
<%-- 					<s:textfield name="ratifyOil" size="40" /> --%>
					</td>
					<td class="addTd"><s:text name="oaCarinfo.displacement" /></td>
					<td align="left">
						<input type="text" name="displacement" value="${displacement }"  style="width:200px;height:25px" />
<%-- 					<s:textfield name="displacement" size="40" /> --%>

					</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.oilLabel" /></td>
					<td align="left">
						<input type="text" name="oilLabel" value="${oilLabel }" maxlength="10" style="width:200px;height:25px" />
<%-- 					<s:textfield name="oilLabel" size="40" /> --%>
					</td>
					<td class="addTd"><s:text name="oaCarinfo.frameNumber" /></td>
					<td align="left">
						<input type="text" name="frameNumber" class="focuse" value="${frameNumber }" maxlength="13" style="width:200px;height:25px" />
<%-- 					<s:textfield name="frameNumber" size="40" class="focuse" maxlength="13"   /> --%>
					</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.driver" /></td>
					<td align="left"><select id="driver"  style="width:200px;height:25px"
						name="driver">
							<c:forEach var="row" items="${oaDriverInfoList}">
								<option value="${row.no}"
									<c:if test="${row.no==driver}"> selected="selected"</c:if>>
									<c:out value="${row.name}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd"><s:text name="oaCarinfo.engineNo" /></td>
					<td align="left">
						<input type="text" name="engineNo" class="focuse" value="${engineNo }" maxlength="13"   style="width:200px;height:25px" />
<%-- 					<s:textfield name="engineNo" size="40" class="focuse" maxlength="13"  /> --%>
					</td>

				</tr>


				<tr>
					<td class="addTd"><s:text name="oaCarinfo.usingNature" /></td>
					<td align="left">
						<input type="text" name="usingNature" class="focuse" value="${usingNature }" maxlength="6"  style="width:200px;height:25px" />
<%-- 					<s:textfield name="usingNature" size="40" class="focuse"  maxlength="6"   /> --%>
					
					</td>
					<td class="addTd"><s:text name="oaCarinfo.depno" /></td>
					<td align="left"><select id="depno"  style="width:200px;height:25px"
						name="depno">
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==depno}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.responsibleDep" /><font color='red'>*</font>
					</td>
					<td align="left"><select id="responsibleDep"   class="focuse required"  style="width:200px;height:25px"
						ref="#responsiblePersion" data-value="${responsibleDep }"
						refUrl="${pageContext.request.contextPath}/oa/oaCarinfo!option.do?type=US&unitcode={value}"
						name="responsibleDep" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${units }">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==responsibleDep}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd"><s:text name="oaCarinfo.responsiblePersion" /><font color='red'>*</font>
					</td>
					<td align="left"><select id="responsiblePersion"  class="focuse required"  style="width:200px;height:25px"
						name="responsiblePersion"
						data-value="${responsiblePersion }">
							<c:forEach var="row" items="${users }">
								<option value="${row.usercode}"
									<c:if test="${row.usercode==responsiblePersion}"> selected="selected"</c:if>>
									<c:out value="${row.username}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.carItems" /></td>
					<td align="left" colspan="3">
						<input type="text" name="carItems" value="${carItems }"   style="width:200px;height:25px"/>
<%-- 					<s:textfield name="carItems" --%>
<%-- 							size="40" style="width: 100%;height: 27px;" /> --%>
							</td>
				<%-- 	<td class="addTd">联系方式</td>
					<td align="left" >
						<input type="text" name="telephone" value="${telephone }"   style="width:200px;height:25px"/>
							</td> --%>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaCarinfo.remark" /></td>
					<td align="left" colspan="3">
					<input type="text" name="remark" value="${remark}"   style="width:200px;height:25px"/>
<%-- 					<s:textfield name="remark" --%>
<%-- 							size="40" style="width: 100%;height: 27px;" /> --%>
							</td>
				</tr>
			</table>


		</form>
	</fieldset>

	<script type="text/javascript">
		$(function() {

			$('select[refUrl]').change(
					function() {

						var select = $(this);

						var ref = select.attr('ref');
						var url = select.attr('refUrl');

						if (ref && url) {
							var refSelect = $(ref);
							var refUrl = url
									.replaceAll('{value}', select.val());

							$.get(refUrl, function(json) {
								refSelect.find('option').remove();

								for ( var i = 0; i < json.length; i++) {
									var key = json[i].key;
									var value = json[i].value;

									refSelect.append('<option value="'+key+'">'
											+ value + '</option>');
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
				imgObjPreview.style.width = '300px';   
				imgObjPreview.style.height = '150px';    
				//imgObjPreview.src = docObj.files[0].getAsDataURL();  
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式   
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);   
				} else {
					//IE下，使用滤镜    
					docObj.select();  
					var imgSrc = document.selection.createRange().text;   
					var localImagId = document.getElementById("localImag");
					//必须设置初始大小    
					localImagId.style.width = "300px"; 
					localImagId.style.height = "150px";
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
</body>
</html>
