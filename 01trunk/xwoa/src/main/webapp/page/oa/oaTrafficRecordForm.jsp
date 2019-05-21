<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaTrafficRecord.edit.title" />
</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaTrafficRecord" method="post" namespace="/oa"
		id="oaTrafficRecordForm">
			  <input type="button" class="btn"  value="保存" onclick="submitItemFrame('SAVE');"/>
		<input type="button" class="btn" value="关闭" onclick="window.close();">
		 <input type="hidden" id="no" name="no" value="${object.no}" />
		 <fieldset>
		<legend style="width: auto; margin-bottom: 10px;">	
		  违章记录
		</legend>
		 
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	
			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.djid" /></td>
				<td align="left" >
                        ${object.djid}  
                         <input type="hidden" id="djid" name="djid" value="${object.djid}" />
                </td>
                	<td class="addTd">车辆号<font color='red'>*</font></td>
				<td align="left"><s:textfield name="cardjid" id="cardjid" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.carno" /></td>
				<td align="left"><s:textfield name="carno"  /></td>
		
				<td class="addTd"><s:text name="oaTrafficRecord.carType" />
				</td>
				<td align="left">
				<select  id="carType"
						name="carType" >
							<c:forEach var="row" items="${oaCommonTypeList}">
								<option value="${row.comName}"
							    	<c:if test="${row.comName==carType}"> selected="selected"</c:if>
								  >
									<c:out value="${row.comName}" />
								</option>
							</c:forEach>
					</select></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.brand" /></td>
				<td align="left"><s:textfield name="brand" /></td>
			
				<td class="addTd"><s:text name="oaTrafficRecord.modelType" />
				</td>
				<td align="left"><s:textfield name="modelType" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.depno" /></td>
				<td align="left"><s:textfield name="depno"/></td>
			
				<td class="addTd"><s:text name="oaTrafficRecord.douser" /></td>
				<td align="left"><s:textfield name="douser" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.dotime" /><font color='red'>*</font></td>
				<td align="left"colspan="3">
				<input type="text" class="Wdate"  id="dotime" name="dotime" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.dotime}" pattern="yyyy-MM-dd HH:mm"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" placeholder="选择日期">				
<%-- 					<sj:datepicker id="dotime" --%>
<%-- 						value="%{object.dotime}" name="dotime" --%>
<%-- 						style="width:120px" yearRange="2000:2024"  --%>
<%-- 						displayFormat="yy-mm-dd" timepickerFormat="hh:mm"  timepicker="true" --%>
<%-- 						changeYear="true" readonly="true" 	/> --%>
				</td>
				
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.doaddress" />
				</td>
				<td align="left" colspan="3">
				<s:textarea name="doaddress"
						style="width:100%;height:50px;" id="doaddress" />
				
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.penalty" />
				</td>
				<td align="left"><s:textfield name="penalty"  /></td>
			
				<td class="addTd"><s:text
						name="oaTrafficRecord.actualDamages" /></td>
				<td align="left"><s:textfield name="actualDamages" />

				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaTrafficRecord.remark" /><font color='red'>*</font></td>
				<td align="left" colspan="3">
				<s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" />
				</td>
			</tr>

	
		</table>
	</s:form>
	</body>
		<script type="text/javascript">
function submitItemFrame(subType){
	if(docheck()==false){
		return;
	}else{
	var srForm  = document.getElementById("oaTrafficRecordForm");
	if(subType == 'SAVE'){
		srForm.action = 'oaTrafficRecord!savePermitReg.do';
	}
	srForm.submit();
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
		alert("违章时间不可为空！");
		$('#dotime').focus();
		
		return false;
	}	
	
	if($("#remark").val()==''){
		alert("情况说明不可为空！");
		$('#remark').focus();
		
		return false;
	}	
	
}
	</script>
	</html>