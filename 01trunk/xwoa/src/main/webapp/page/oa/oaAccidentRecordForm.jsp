<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaAccidentRecord.edit.title" />
</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaAccidentRecord" method="post" namespace="/oa" id="oaAccidentRecordForm" enctype="multipart/form-data">
		  <input type="button" class="btn"  value="保存" onclick="submitItemFrame('SAVE');"/>
		<input type="button" class="btn" value="关闭" onclick="window.close();">
		 <input type="hidden" id="no" name="no" value="${object.no}" />
		 <fieldset>
		<legend style="width: auto; margin-bottom: 10px;">
		  事故记录
		</legend>
		 
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	
			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.djid" /></td>
				<td align="left">
				${object.djid} 
					 <input type="hidden" id="djid" name="djid" value="${object.djid}" />
				
				</td>
			<td class="addTd"><s:text name="oaAccidentRecord.cardjid" /><font color='red'>*</font></td>
				<td align="left" >
			<s:textfield name="cardjid"
						value="%{object.cardjid}" id="cardjid"/>
				</td>
			</tr>
	
			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.carno" /></td>
				<td align="left">
				<s:textfield name="carno"
						value="%{object.carno}" id="carno" />
				
				</td>
	
				<td class="addTd"><s:text name="oaAccidentRecord.carType" />
				</td>
				<td align="left">
					<select id="carType"
						name="carType" >
							<c:forEach var="row" items="${oaCommonTypeList}">
								<option value="${row.comName}"
							    	<c:if test="${row.comName==carType}"> selected="selected"</c:if>
								  >
									<c:out value="${row.comName}" />
								</option>
							</c:forEach>
					</select>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.brand" /></td>
				<td align="left">
		<s:textfield name="brand"
						value="%{object.brand}" id="brand" />
				</td>
		
				<td class="addTd"><s:text name="oaAccidentRecord.modelType" />
				</td>
				<td align="left">
					<s:textfield name="modelType"
						value="%{object.modelType}" id="modelType" />
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.depno" /><font color='red'>*</font></td>
				<td align="left">
				<s:textfield name="depno"
						value="%{object.depno}" id="depno"/>
				</td>
			
				<td class="addTd"><s:text name="oaAccidentRecord.douser" /><font color='red'>*</font>
				</td>
				<td align="left">
					<s:textfield name="douser"
						value="%{object.douser}" id="douser" />
				</td>
			</tr>

			

			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.dotime" /><font color='red'>*</font>
				</td>
				<td align="left" colspan="3">
								<input type="text" class="Wdate"  id="dotime" name="dotime" 
								style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
								value='<fmt:formatDate value="${object.dotime}" pattern="yyyy-MM-dd HH:mm"/>'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm',minDate:'2000-01-01 00:00',maxDate:'2024-12-31 23:59 '})" placeholder="选择日期 ">				
<%-- 			<sj:datepicker id="dotime" --%>
<%-- 						value="%{object.dotime}" name="dotime" --%>
<%-- 						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true" --%>
<%-- 						changeYear="true" readonly="true" 	/> --%>
				</td>
				
		</tr>
		<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.doaddress" />
				</td>
				<td align="left" colspan="3">
				<s:textarea name="doaddress"
						style="width:100%;height:50px;" id="doaddress" />
						</td>
			</tr>

			<tr>
				<td class="addTd"><s:text
						name="oaAccidentRecord.responsibility" /></td>
				<td align="left">
				<s:textfield name="responsibility"
						value="%{object.responsibility}" id="responsibility" />
				</td>
		
				<td class="addTd"><s:text name="oaAccidentRecord.casualties" />
				</td>
				<td align="left">
			<s:textfield name="casualties"
						value="%{object.casualties}" id="casualties"/>
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.losses" />
				</td>
				<td align="left">
				<s:textfield name="losses"
						value="%{object.losses}" id="losses"/>
				</td>
	
				<td class="addTd"><s:text name="oaAccidentRecord.penalty" />
				</td>
				<td align="left">
								<s:textfield name="penalty"
						value="%{object.penalty}" id="penalty"/>
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text
						name="oaAccidentRecord.actualDamages" /></td>
				<td align="left">
					<s:textfield name="actualDamages"
						value="%{object.actualDamages}" id="actualDamages" />

				</td>
		
				<td class="addTd"><s:text
						name="oaAccidentRecord.claimDifference" /></td>
				<td align="left">
				<s:textfield name="claimDifference"
						value="%{object.claimDifference}" id="claimDifference" />
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text
						name="oaAccidentRecord.accidentAfter" /><font color='red'>*</font></td>
				<td align="left" colspan="3">
					<s:textarea name="accidentAfter"
						style="width:100%;height:50px;" id="accidentAfter" />
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.scenePhotos" />
				</td>
				<td align="left" colspan="3">
<!-- 				<input type="file" id="fileupload" name="stuffFile"> -->
				 <s:file name="scenePhotos_"/> 
			</td>
		
			</tr>

	
			<tr>
				<td class="addTd"><s:text name="oaAccidentRecord.remark" />
				</td>
				<td align="left" colspan="3">
				<s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" /></td>
			</tr>

		</table>
	</s:form>
	</body>
	<script type="text/javascript">
function submitItemFrame(subType){
	if(docheck()==false){
		return;
	}else{
	var srForm  = document.getElementById("oaAccidentRecordForm");
	if(subType == 'SAVE'){
		srForm.action = 'oaAccidentRecord!savePermitReg.do';
	}
	srForm.submit();
// 	setInterval("window.close()",600);
	window.close();
	}
}


function docheck() {
	if($("#cardjid").val()==''){
		alert("车辆号不可为空！");
		$('#cardjid').focus();
		
		return false;
	}	
	if($("#dotime").val()==''){
		alert("肇事时间不可为空！");
		$('#dotime').focus();
		
		return false;
	}	
	if($("#depno").val()==''){
		alert("肇事单位不可为空！");
		$('#depno').focus();
		
		return false;
	}	

	if($("#douser").val()==''){
		alert("肇事人不可为空！");
		$('#douser').focus();
		
		return false;
	}	
	if($("#accidentAfter").val()==''){
		alert("事故经过不可为空！");
		$('#accidentAfter').focus();
		
		return false;
	}	
	
}

</script></map></html>