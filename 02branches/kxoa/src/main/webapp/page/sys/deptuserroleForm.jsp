<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		
		<title>用户角色编辑--
			<c:out value="${cp:MAPVALUE('usercode',fUserrole.usercode)}" />:
			<c:out value="${cp:MAPVALUE('rolecode',fUserrole.rolecode)}"/> 
		 </title>
		 <sj:head locale="zh_CN"/>
		
		<script type="text/javascript"	src="<c:url value='/scripts/datepicker/WdatePicker.js'/>"></script>
	<script type="text/javascript">
	function compare() {
		
		var d1=$("#secededate").attr("value");
		var endate=new Date(Date.parse(d1.replace(/-/g, "/")));
		var d2=$("#obtaindate").attr("value");
		var begindate=new Date(Date.parse(d2.replace(/-/g, "/")));
		if(begindate>endate){
			alert("请重新设置到期时间，到期时间必须比获取时间晚");
			return false;
		}
		else{
			return true;
		}
	}	
	</script>
	</head>

	<body class="sub-flow">
		<fieldset style="padding:10px;">
			<legend class="ctitle" style="width:auto;">用户角色编辑--
			<c:out value="${cp:MAPVALUE('usercode',fUserrole.usercode)}"/>:
			<c:out value="${cp:MAPVALUE('rolecode',fUserrole.rolecode)}"/> </legend>
		
		<s:form action="deptManager" name="/sys" onsubmit="return compare();" >
			

		<table cellpadding="0" cellspacing="0" class="viewTable">
				
				<tr>
					<td class="addTd">
						用户代码
					</td>
					<td align="left" >
						<s:textarea name="userrole.usercode" readonly="true" style="width:160px;height:20px;" /> 
					</td>
				</tr>
				<tr>
					<td class="addTd">
						用户名
					</td>
					<td align="left" >
						<c:out value="${cp:MAPVALUE('usercode',userrole.usercode)}"/> 
					</td>
				</tr>
				<tr>
					<td class="addTd">
						用户部门角色
					</td>
					<td align="left">
						<select name="userrole.rolecode" style="width:160px;">      
							<c:forEach var="row" items="${cp:ROLEINFO(roleprefix)}">       
								<option value="${row.rolecode}"
								<c:if test="${row.rolecode==userrole.rolecode}">selected="selected"</c:if>>   
									<c:out value="${row.rolename}"/>   
								</option>       
							</c:forEach>     
							<c:forEach var="row" items="${cp:ROLEINFO('P-')}">       
								<option value="${row.rolecode}"
								<c:if test="${row.rolecode==userrole.rolecode}">selected="selected"</c:if>>   
									<c:out value="${row.rolename}"/>   
								</option>       
							</c:forEach>     
						</select>  
					</td>
				</tr>
				<tr>
					<td class="addTd">
						获取时间
					</td>
					<td align="left" >
						<sj:datepicker id="obtaindate" name="userrole.obtaindate" style="width:140px;" value="%{userrole.obtaindate}" yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"/>
					</td>				
				</tr>
				<tr>
					<td class="addTd">
						到期时间
					</td>
					<td align="left" >
						<sj:datepicker id="secededate" onchange="compare();" name="userrole.secededate" style="width:140px;" value="%{userrole.secededate}" yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true"/>			
					</td>				
				</tr>				
				<tr>
				<td class="addTd">
						授权说明
					</td>
					<td align="left">
						<s:textarea name="userrole.changedesc" style="width:400px;height:40px;" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<s:submit method="saveUserRole" cssClass="btn" value="保存"  />
						<input type="button" value="返回" Class="btn" onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
		</s:form>
		</fieldset>
	</body>
</html>
