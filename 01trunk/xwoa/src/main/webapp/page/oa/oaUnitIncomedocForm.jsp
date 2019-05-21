<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaUnitIncomedoc" method="post" namespace="/oa"
		id="oaUnitIncomedocForm">
		<c:if test="${object.isopen eq '1'}">
		<s:submit name="save" method="save" cssClass="btn" key="公开" onclick="return check();"/>
		</c:if>
		<c:if test="${object.isopen ne '1'}">
		<s:submit name="save" method="save" cssClass="btn" key="不公开" onclick="return check();"/>
		</c:if>
		<input type="button" class="btn" value="返回"
			onclick="window.history.back();" />
		<fieldset>
			<legend> 收文归档</legend>
			<table width="200" border="0" cellpadding="1" cellspacing="1"
				class="viewTable">

				<tr>
					<td class="addTd"><s:text name="oaUnitIncomedoc.id" /></td>
					<td align="left" ><s:textfield name="id" style="width: 50%;height: 27px;" readonly="true"/></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaUnitIncomedoc.no" /></td>
					<td align="left" ><s:textfield name="no" style="width:50%;height: 27px;"  readonly="true"/></td>
				</tr>
				<%-- <tr>
					<td class="addTd"><s:text name="oaUnitIncomedoc.unitcode" />
					</td>
					<td align="left" colspan="4"><input type="hidden"
						id="unitcode" name="retValue" value="${AttendingUnits}" />
							<s:textarea name="uninName" id="uninName"
							style="width:85%;height:40px;" value="%{retValue}" />
			        <input type="button" class='btn' name="powerBtn"
						onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetApply!listSelectOrg.do','orgWindow',null);"
						value="选择">		</td> 
					</td>
				</tr> --%>
				<tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.createuser" />
					</td>
					<td align="left" >
					     ${cp:MAPVALUE("usercode",usercode)}
					</td>
				</tr>
				<%-- <tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.createtime" />
					</td>
					<td align="left">
						 <input type="text" class="Wdate"
						style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						id="createtime" name="createtime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" />				
					</td>
				</tr> --%>
				<%-- <tr>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.lastmodifytime" />
					</td>
					<td align="left">
						 <input type="text" class="Wdate"
						style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${lastmodifytime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						id="lastmodifytime" name="lastmodifytime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" />				
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.isopen" />
					</td>
					<td align="left">
						<s:radio name="isopen" id="s_isopen"
							onclick="checkMeetType(this.value);" list="#{'N':'否' ,'P':'是'}"
							listKey="key" listValue="value"></s:radio>
					</td>
					<td class="addTd">
						<s:text name="oaUnitIncomedoc.updateuser" />
					</td>
					<td align="left">
						<s:textfield name="updateuser"  size="40"/>
					</td>
				</tr> --%>
</table>

</fieldset>
</s:form>
</body>
<script type="text/javascript">
     function check(){
    	 var ev = '${object.isopen}';
    	 if(ev=='0'||ev==''){
    		 var c =window.confirm("确认不公开?");
    		 if(!c){
        		 return false;
        	 } 
    	 }else{
    		 var c =window.confirm("确认公开?");
    		 if(!c){
        		 return false;
        	 } 
    	 }
    	 
     }

</script>
</html>
