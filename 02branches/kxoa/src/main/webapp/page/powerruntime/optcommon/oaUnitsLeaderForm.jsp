<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitsLeader.edit.title" /></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript">	
	function openNewWindow_1(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=950px,width=900px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	</script>
</head>

<body>
	<p class="ctitle">
		<s:text name="oaUnitsLeader.edit.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaUnitsLeader" method="post" namespace="/powerruntime"
		id="oaUnitsLeaderForm" onsubmit="return doCheck();">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input id="backBtn" name="backBtn" type="button" value="返回" class="btn" onclick="window.history.go(-1);" />
<c:if test="${not empty object.leadercode}">
<input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow_1('<%=request.getContextPath()%>/powerruntime/oaUnitsLeaderLog!list.do?s_leadercode=${object.leadercode}','orgWindow1',null);"
					value="配置记录">
</c:if>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd"><s:text name="oaUnitsLeader.leadercode" />
				</td>
				<input type="hidden" id="leadercode" name="leadercode" value="${object.leadercode}" />
				<td align="left" width="85%"><c:if test="${empty object.leadercode}">
				<input type="text" id="librarystaffnames"
					name="leadercodes" style="width: 70%; height: 27px;" value="" readonly="readonly" /> 				
						<span style="color: red">*</span>
					</c:if> 
					<c:if test="${not empty object.leadercode}">						
						${cp:MAPVALUE("usercode",object.leadercode)}
					</c:if></td>
			</tr>


			<tr>
				<td class="addTd"><s:text name="oaUnitsLeader.isuse" /></td>
				<td align="left"><select name="isuse"
					style="width: 150px" id="isuse">
					<option value="">--请选择--</option>
						<c:forEach var="row" items="${cp:DICTIONARY('TrueOrFalse')}">
							<option value="${row.key}"
								<c:if test="${object.isuse eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaUnitsLeader.unitcodes" />
				</td>
				<td align="left">
				<input type="hidden" id="unitcode" name="unitcodes" value="${object.unitcodes}"/>
				<s:textarea name="unitNames" id="uninName" readonly="true"
						cols="40" rows="2" style="width:70%;height:40px;" value="%{object.unitNames}"/>
					    <input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/generalOperator!listSelect.do?ec_o_1=true&ec_o_1=true','orgWindow',null);"
					value="选择">
				</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaUnitsLeader.remark" /></td>
				<td align="left"><s:textarea name="remark" cols="40" style="width:70%;height:39px"/>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="4"><font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;友情提示：给领导配置分管部门后，其可以查看当前分配的分管部门的所有办件。</font>
				</td>
			</tr>
		</table>
		
		<!-- 选择人员的模块 -->
		<div id="attAlert" class="alert"
			style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
			<h4>
				<span id="selectTT">选择</span><span id="close2"
					style="float: right; margin-right: 8px;" class="close"
					onclick="closeAlert('attAlert');">关闭</span>
			</h4>
			<div class="fix">
				<div id="leftSide"></div>
				<div id="l-r-arrow">
					<div class="lb"></div>
					<div class="rb"></div>
				</div>
				<div id="rightSide">
					<ul></ul>
				</div>
				<div id="t-b-arrow">
					<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
					<b class="btns"> <input id="save" class="btn" type="button"
						value="保     存" /><input id="clear" class="btn" type="button"
						value="取     消" style="margin-top: 5px;" /></b>
				</div>
			</div>
		</div>
		
		
	</s:form>
	</body>
	<script type="text/javascript">	
	
	$("#librarystaffnames").click(
			function() {
				var d = '${userjson}';

				if (d.trim().length) {
					new treePerson(jQuery.parseJSON(d), $("#librarystaffnames"),
							$("#leadercode")).init();
					setAlertStyle("attAlert");
				}
				;
			});
	function doCheck(){
		 if ($('#leadercode').val() == '') {
			 alert("领导不可为空，请选择人员！");
				$('#leadercode').focus();
				return false;
		 }
		 if ($('#leadercode').val() != '') {
			 var librarystaffcodes=$('#leadercode').val().split(',');
			 if(librarystaffcodes.length>=2){
				 alert("配置人员只限一人，请确定人员！");
				 $("#librarystaffnames").click();
					return false;
			 }			
		 }
		 if ($('#unitcode').val() == '') {
			 alert("分管部门不可为空，请选择部门！");
				$('#unitcode').focus();
				return false;
		 }
		 if(window.confirm("确定提交当前配置内容吗？")){
			 return true;			 
		 }else{
			 return false;
		 }
	}
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	
</script>
	</html>