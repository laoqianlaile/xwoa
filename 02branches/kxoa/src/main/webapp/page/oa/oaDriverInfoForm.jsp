<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

<title><s:text name="oaDriverInfo.edit.title" /></title>

</head>

<body class="sub-flow">
   <fieldset class="form">
		<legend>
		<p class="ctitle">
		<s:text name="oaDriverInfo.edit.title" />
	    </p>
		</legend> 

	<%@ include file="/page/common/messages.jsp"%>

	<form action="" method="post"
		id="oaDriverInfoForm" data-validate="true" enctype="multipart/form-data">
		

		<c:if test="${ not empty no}">
			<input type="hidden" id="no" name="no" value="${no}" />
		</c:if>

		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.codenum" /></td>
				<td align="left"><s:textfield name="codenum" id="codenum" style="width:200px;height:25px"  /></td>
				<td colspan="2" rowspan="4" align="center">
				<c:choose>
				<c:when test="${not empty photoname }">
					<img id="appicon" src='${contextPath }/oa/oaDriverInfo!getUserImgFromByte.do?no=${no}' alt="头像"  style="width: 250px; height: 100px;"  />
				</c:when>
				<c:otherwise>
					<img id="appicon" style="width: 250px; height: 100px"   />
				</c:otherwise>
				</c:choose>
				
				
				</td>

			</tr>
			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.name" /><span style="color: red">*</span></td>
				<td align="left"><input type="text" id="name"  name="name" style="width:200px;height:25px" 
				    value="${object.name }" class="focused required"/></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.sex" /></td>
				<td align="left">
				<select id="sex"   style="width:200px;height:25px" 
						name="sex">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('sex')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==sex}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.state" /></td>
				<td align="left">
				<select id="state"   style="width:200px;height:25px" 
						name="state">
						<c:if test="${ empty state}">
						 <option value=""></option>
						 </c:if>
							<c:forEach var="row" items="${cp:DICTIONARY('equState')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==state}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.drLicenseNo" /><span style="color: red">*</span>
				</td>
				<td align="left"><input type="text" id="drLicenseNo"  name="drLicenseNo" 
				     style="width:200px;height:25px" value="${object.drLicenseNo }" class="focused required"/></td>
				<td class="addTd">个人照片
				</td>
				<td>
					<!-- <input type="file" name="uploadFile_" optID="xxx" inputID="xxx" style="width:200px;height:25px" /> -->
					<s:file name="uploadFile_" style="width:200px;height:25px"/>
				
				</td>
				
			</tr>


			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.publicType" />
				</td> 
				<td align="left">
				<select id="publicType"
						name="publicType" style="width: 200px;height: 25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('canDriveType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==publicType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					
				<td class="addTd"><s:text name="oaDriverInfo.validDate" /></td>
				<td align="left">
				<input type="text" class="Wdate"  id="validDate" name="validDate" 
				style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.validDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期"/>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.releaseDate" />
				</td>
				<td align="left">
				<input type="text" class="Wdate" data-max-date="${currentDate }" id="releaseDate" name="releaseDate" 
				style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.releaseDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />
				
				<td class="addTd"><s:text name="oaDriverInfo.beenDriving" />
				</td>
				<td align="left"><s:textfield id="beenDriving"  name="beenDriving" style="width:200px;height:25px"/></td>
			</tr>



			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.telephone" /></td>
				<td align="left"><input type="text"  id="telephone"  name="telephone" value="${object.telephone }" style="width:200px;height:25px"  class="focused isTelphone"/>
				</td>
				<td class="addTd"><s:text name="oaDriverInfo.mobile" /></td>
				<td align="left"><input type="text" id="mobile"  name="mobile" value="${object.mobile }"  style="width:200px;height:25px" class="focused isphone" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.birthDate" /></td>
				<td align="left">
				<input type="text" class="Wdate"
				id="birthDate" name="birthDate" data-max-date="${currentDate }"
				style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.birthDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
			</td>
				<td class="addTd"><s:text name="oaDriverInfo.age" /></td>
				<td align="left">
				<input type="text" name="age" id="age" readonly="readonly" value="${age}">
				
				
<%-- 				<s:textfield id="age"  name="age" style="width:200px;height:25px" readonly="readonly"/> --%>
				
				
				</td>
			</tr>




			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.address" /></td>
				<td align="left" colspan="3"><s:textarea id="address"  name="address" 
						cols="40" rows="2" style="width: 100%;height: 27px;" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.depno" /></td>
				<td align="left" colspan="3"><s:textarea id="depno"  name="depno" cols="40"
						rows="2" style="width: 100%;height: 27px;" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.workExperience" />
				</td>
				<td align="left" colspan="3"><s:textarea id="workExperience"   name="workExperience" 
						cols="40" rows="2" style="width: 100%;height: 27px;" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaDriverInfo.remark" /></td>
				<td align="left" colspan="3"><s:textarea  id="remark"   name="remark"
						cols="40" rows="2" style="width: 100%;height: 27px;"/></td>
			</tr>

		</table>
     <div class="formButton">
		<input type="button" class="btn" id="save" value="保存"
			onclick="submitItemFrame('SAVE');" />	
		
		<!-- <button type="submit" class="btn btn-primary">保存</button> -->
		<input type="button" name="back" Class="btn"
			onclick="history.back(-1);" value="返回" />
		</div>
	</form>
</fieldset>
	<script type="text/javascript">
	
	var currentDate = new Date();
	// 当前年份
	var currentYear = currentDate.getYear();
	// 不同浏览器的支持年份不同，如果是chrome，就加1900
	if(currentYear < 1000){
		currentYear += 1900;
	}
	
	// 通过发证日期计算驾龄
	$('#releaseDate').bind('focusout mouseover',function(){
		// 页面中发证日期
		var releaseDate = $(this).val();
		
		if('' != releaseDate && releaseDate.length == 10){
			var year = releaseDate.substr(0,4);
			$('#beenDriving').val(currentYear - year);			
		}
	});
	
	/* 通过出生日期计算年龄 */
	$('#birthDate').bind('focusout mouseover',function(){
		// 页面中出生日期
		var birthDate = $(this).val();
		
		if('' != birthDate && birthDate.length == 10){
			var year = birthDate.substr(0,4);
			$('#age').val(currentYear - year);
		}
	});
	
	function submitItemFrame(subType){
		
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaDriverInfoForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaDriverInfo!save.do';
		}
		srForm.submit();
		}
}
	
	
	function docheck() {
		if($("#codenum").val()!=''){
			if($("#codenum").val().length>25){
				alert("编号长度不能超过50个字！");
				$('#codenum').focus();
				return false;
			}
		}
		
		if($("#name").val()==''){
			alert("人员名称不能为空！");
			$('#name').focus();
			return false;
		}	

		if($("#drLicenseNo").val()==''){
			alert("驾驶证不可为空！");
			$('#drLicenseNo').focus();
			return false;
		}	
		
		if($("#drLicenseNo").val()!=''){
			if($("#drLicenseNo").val().length>25){
				alert("驾驶证号不能超过50个字！");
				$('#drLicenseNo').focus();
				return false;
			}
		}
		
		var releaseDate = stringToDate($('#releaseDate').val());
		if(null != releaseDate && releaseDate > currentDate){
			alert("发证日期不能大于当前时间！");
			$('#releaseDate').focus();
			return false;
		}
		
		var birthDate = stringToDate($('#birthDate').val());
		if(null != birthDate && birthDate > currentDate){
			alert("出生日期不能大于当前时间！");
			$('#birthDate').focus();
			return false;
		}
		
		if($("#telephone").val()!=''|| $("#mobile").val()!=''){
			
			var reg = /^((0\d{2,3})-)?(\d{1,11})$/;
			var val1 = $("#telephone").val();
			var val2 = $("#mobile").val();
			if(val1!=''){
			if (!reg.test(val1)) {
				
				alert("申请者固定电话格式不对！");
				$('#telephone').focus();
				return false;
			}
			}
			if(val2!=''){
	        if (!reg.test(val2)) {
				
				alert("申请者手机电话格式不对！");
				$('#mobile').focus();
				return false;
			}
			}
		    }
    }	
	
	/* 将字符串"yyyy-MM-dd"转换成日期形式 */
	function stringToDate(dateString){
		
		if('' != dateString && 10 == dateString.length){
			var year = dateString.substr(0,4);
			var month = dateString.substr(5,2)-1;//用整数表示月份，从（1月）到11（12月）
			var day = dateString.substr(8,2);
			var date = new Date(year,month,day);
			
			return date;
		}
		
		return null;
	}
	</script>


</body>
<%@ include file="/page/common/scripts_new.jsp"%> 
</html>
