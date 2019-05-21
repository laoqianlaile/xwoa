<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaArchive"  method="post" namespace="/oa" id="oaArchiveForm" >
<s:submit name="save"  method="save" onclick="return checkForm();" cssClass="btn" key="opt.btn.submit"/>
    <input type="hidden" id="id" name="id" value="${object.id}" />	
    <input type="hidden" id="flag" name="flag" value="${flag }"/>
    <input type="hidden" id="no" name="no" value="${object.no }"/>
    <fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding: 4px 8px 3px;">
				<b>归档录入</b>
			</legend>
	
	<!-- <input type="button" value="返回" class="btn" onclick="window.history.back();"/> -->
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
 <tr>
				<td class="addTd">
						<s:text name="oaArchive.allcaseno" />
					</td>
					<td align="left">
						<s:textfield name="allcaseno" id="allcaseno" style="width: 100%;height: 27px;" value="%{allcaseno}" />
				</td>
				<td class="addTd">
						<s:text name="oaArchive.titanic" /><font style="color:red;">*</font>
					</td>
					<td align="left">
						<s:textfield id="titanic" name="titanic" style="width: 100%;height: 27px;" />
					</td> 
				</tr>
				
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.title" /><font style="color:red;">*</font>
					</td>
					<td align="left" colspan="3">
						<s:textfield name="title" id="title" style="width: 100%;height: 27px;" value="%{title}" />
				</td>
				</tr>
				
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.parallelTitle" />
					</td>
					<td align="left" colspan="3">
						<s:textfield name="parallelTitle" id="parallelTitle" style="width: 100%;height: 27px;" value="%{parallelTitle}" />
				</td>
				</tr>
				<tr>
				<td class="addTd">
						<s:text name="oaArchive.deputyTitle" />
					</td>
					<td align="left" colspan="3">
						<s:textfield name="deputyTitle" id="deputyTitle" style="width: 100%;height: 27px;" value="%{deputyTitle}" />
				</td>
				</tr>
				<tr>
					<td class="addTd">
						文号
					</td>
					<td align="left">
					<c:if test="${flag ne 'O' }">
						<s:textfield name="docno" style="width:100%" readonly="true" value="%{object.docno}"/>
					</c:if>
					<c:if test="${flag eq 'O' }">
						<s:textfield name="docno" style="width:100%" value="%{object.docno}"/>
					</c:if>
					</td>
				<td class="addTd"><s:text name="oaArchive.bookdate" /></td>
				<td align="left">
				       <input type="text" class="Wdate" style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.bookdate}" pattern="yyyy-MM-dd"/>'
					  		id="bookdate" name="bookdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />				
				   </td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaArchive.pages" />
					</td>
					<td align="left">
						<s:textfield name="pages" id="pages" style="width: 100%;height: 27px;" />
					</td>
					<td class="addTd">
						<s:text name="oaArchive.copies" />
					</td>
					<td align="left">
						<s:textfield name="copies" id="copies"  style="width: 100%;height: 27px;" />
					</td>
				</tr>
				<tr>
					
					
					<c:choose>
					<c:when test="${isEditYear eq 'T'}">
					<td class="addTd">
						<s:text name="oaArchive.filingdate" />
					</td>
					<td align="left">
					<input type="hidden" id="filingdate" name="filingdate" value="${object.filingdate}"/>
				  <fmt:formatDate value='${object.filingdate}' pattern='yyyy-MM-dd HH:mm' />
				    </td>
						<td class="addTd"><s:text name="oaArchive.filingannual" /></td>
						<input type="hidden" id="filingannual" name="filingannual" value="${object.filingannual}"/>
					<td align="left">${object.filingannual}</td>
					</c:when>
					<c:otherwise>
					<td class="addTd">
						<s:text name="oaArchive.filingdate" /><font style="color:red;">*</font>
					</td>
					<td align="left">
				       <input type="text" class="Wdate" style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;" 
				           <c:if test="${empty filingdate }"> selected="selected"
				           value='<fmt:formatDate value="${nowtime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		</c:if>
					  		 <c:if test="${not empty filingdate }"> selected="selected"
				           value='<fmt:formatDate value="${object.filingdate}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		</c:if>
					  		id="filingdate" name="filingdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />				
				   </td>
						<td class="addTd">
						<s:text name="oaArchive.filingannual" /><font style="color:red;">*</font>
					</td>
					<td align="left">
						<select id="filingannual" style="width:200px;height:25px;" name="filingannual">
							<c:forEach begin="${0}" end="${2}" var="i">
							   <option <c:if test="${year+i==filingannual}"> selected="selected"</c:if>
							    <c:if test="${empty filingannual && year+1 == year+i}"> selected="selected"</c:if>
							   >${year+i}</option>
							</c:forEach>
					</select>
					</td>
					</c:otherwise>
				</c:choose>
				</tr>
				<tr>
				 <td class="addTd" >
						<s:text name="oaArchive.responsibledep" />
					</td>
					<td align="left" >
				 <s:textfield name="responsibledep" maxLength="100" id="responsibledep" value="%{responsibledep}"  style="width: 100%;height: 27px;" />
				</td>
					<td class="addTd">
						<s:text name="oaArchive.duration" /><font style="color:red;">*</font>
					</td>
						<td align="left"><select id="duration" style="width:200px;height:25px;" name="duration">
							<c:forEach var="row" items="${cp:DICTIONARY('BGNX') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==duration}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
					<tr>
				<td class="addTd">
						<s:text name="oaArchive.archiveType"  />
					</td>
					<td align="left">
					<s:textfield name="archiveType" id="archiveType" style="width: 100%;height: 27px;"/>
					</td>
				<td class="addTd"><s:text name="oaArchive.classification" /></td>
				<td align="left">
				      <select id="classification" style="width:200px;height:25px;" name="classification">
							<c:forEach var="row" items="${cp:DICTIONARY('GDMJ') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==classification}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> </td>
				</tr>
				<tr>
			     	<td class="addTd">
						<s:text name="oaArchive.locationpath" />
					</td>
					<td align="left">
						<s:textfield name="locationpath" id="locationpath" style="width: 100%;height: 27px;"/>
					</td>
					<td class="addTd">
						<s:text name="oaArchive.unitcode" />
					</td>
					<td align="left">
						<s:textfield name="unitcode" id="unitcode" style="width: 100%;height: 27px;"/>
					</td>
				</tr>
				<tr>
				    <td class="addTd">
						<s:text name="oaArchive.keywords" />
					</td>
					<td align="left" colspan="3">
						<s:textfield name="keywords" id="keywords" style="width: 100%;height: 27px;"/>
					</td>
				</tr>
				<tr>
				    <td class="addTd">
						<s:text name="oaArchive.remark" />
					</td>
					<td align="left" colspan="3">
						<s:textarea name="remark" id="remark" style="width:100%;"/>
					</td>
				</tr>

</table>

</fieldset>
<c:if test="${ not empty moduleParam.stuffGroupId}">
<br/>
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.id}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${moduleParam.stuffGroupId}"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		</c:if>
</s:form>
</body>
<script type="text/javascript">
   var r="^[1-9]\\d*$";
   function checkForm(){
	   if( document.oaArchiveForm.title.value == ""){
			alert("标题不能为空");
			 document.oaArchiveForm.title.focus();
			return false;
		}
	   if($("#titanic").val()==''){
			alert("件号不能为空");
			 document.oaArchiveForm.titanic.focus();
			return false;
		}
	   if( document.oaArchiveForm.title.value != "" &&document.oaArchiveForm.title.value.length>100){
			alert("标题长度不能超过200个字符");
			$('#title').val('');
		    document.oaArchiveForm.title.focus();
			return false;
		}
	   if($("#pages").val()!=''){
			var isvalid= (new RegExp(r)).test($("#pages").val());
			if(!isvalid){
				alert("页数格式不正确");
				$("#pages").focus();
				return  false;
			}
		}
	   if($("#copies").val()!=''){
			var isvalid= (new RegExp(r)).test($("#copies").val());
			if(!isvalid){
				alert("输入的份数格式不正确");
				$("#copies").focus();
				return  false;
			}
		}
	   if(document.oaArchiveForm.responsibledep.value != "" &&document.oaArchiveForm.responsibledep.value.length>100){
		   alert("责任者不能超过100个字符");
			$('#responsibledep').val('');
		    document.oaArchiveForm.responsibledep.focus();
			return false;
	   }
       if(document.oaArchiveForm.locationpath.value != "" &&document.oaArchiveForm.locationpath.value.length>100){
    	   alert("存放位置不能超过200个字符");
			$('#locationpath').val('');
		    document.oaArchiveForm.locationpath.focus();
			return false;
	   }
       if(document.oaArchiveForm.remark.value != "" &&document.oaArchiveForm.remark.value.length>100){
    	   alert("备注不能超过200个字符");
			$('#remark').val('');
		    document.oaArchiveForm.remark.focus();
			return false;
	   }
	   if( document.oaArchiveForm.filingdate.value == ""){
			alert("归档日期不能为空");
			 document.oaArchiveForm.filingdate.focus();
			return false;
		}
	   //验证件号是否重复
	   var result = $.ajax({
			url: "${contextPath}/oa/oaArchive!checkTitanic.do?id=${id}&titanic=" + $("#titanic").val()+"&filingannual="+$("#filingannual").val(),
			async: false
		}).responseText;
		if ("success" == result) {
			return true;
		} else {
			alert("此归档件号已被占用，请确认件号和归档年度是否正确！");
			document.oaArchiveForm.titanic.focus();
			return false;
		}
   }

</script>
</html>
