<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title>格式文书案号管理</title>
</head>

<body>
<%@ include file="/page/common/messages.jsp"%>
<fieldset>
<legend>
		编辑格式文书案号信息
</legend>
<s:form action="optWritdef"  method="post" namespace="/powerruntime" id="optWritdefForm" onsubmit="return checkForm();">
		<input type="hidden" id="writid" name="writid" value="${object.writid}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
 	<c:if test="${empty object.writid }">
				<tr>
					<td class="TDTITLE">
						模版分类
					</td>
					<td align="left">
				<select name="temptype" id="temptype"
					style="width: 200px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${fDatalist}">
							<option value="${row.value}"
								<c:if test="${object.temptype eq row.value}"> selected = "selected" </c:if>>
								<c:out value="${row.label}" />
							</option>
						</c:forEach>
				</select>	
					</td>
				</tr>
</c:if>

 	<c:if test="${not empty object.writid }">
				<tr>
					<td class="TDTITLE">
						模版分类
					</td>
					<td align="left">
					<input type="hidden" id="temptype" name="temptype" value="${object.temptype}" />
			${cp:MAPVALUE("ITEM_TYPE",object.temptype)}	
					</td>
				</tr>				
</c:if>
				<tr>
					<td class="TDTITLE">
						文号模板
					</td>
					<td align="left">
		<s:textfield name="writcode" id="writcode" size="40" value="%{object.writcode}"/><font color="red">说明:包含年份请用$year$或$Y2$;顺序号请用$N3$,3代表序号位数</font>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						文号初始值
					</td>
					<td align="left">
	
  
						<s:textfield name="initvalue"  size="40" value="%{object.initvalue}"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						是否使用 
					</td>
					<td align="left">
		<select name="isinuse" id="isinuse"
					style="width: 200px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('TrueOrFalse')}">
							<option value="${row.key}"
								<c:if test="${object.isinuse eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						备注
					</td>
					<td align="left">
  
						<s:textarea name="remark" cols="40" rows="2" value="%{object.remark}"/>
	
	
					</td>
				</tr>

</table>

<div class="formButton">
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
</div>
</s:form>
</fieldset>
</body>
<script type="text/javascript">
		function checkForm() {
			
			if($("#temptype").val()==''){
				alert("模版分类不可为空！");
				$('#temptype').focus();
				return false;
			}
			
			if($("#writcode").val()==''){
				alert("文号模板不可为空！");
				$('#writcode').focus();
				return false;
			}
			if($("#isinuse").val()==''){
				alert("请选择是否使用！");
				$('#isinuse').focus();
				return false;
			}
			return true;
		}
</script>
</html>
