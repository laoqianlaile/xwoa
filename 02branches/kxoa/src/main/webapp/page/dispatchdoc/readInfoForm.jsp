<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<p class="ctitle">
		编辑阅件
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="readInfo" method="post" namespace="/dispatchdoc"
		id="readInfoForm" onsubmit="return doSubmitCheck();" enctype="multipart/form-data">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input type="button" value="返回" Class="btn" onclick="doback()" />
		<s:hidden name="readNo" value="%{object.readNo}" />
		<s:hidden name="createName" value="%{object.createName}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
			<tr>
				<td class="addTd">文件标题<font color="#ff0000">*</font>
				</td>
				<td align="left" colspan="3">
				<s:textfield id="incomeDocTitle" name="incomeDocTitle" style="width: 100%;height: 24" value="%{object.incomeDocTitle}"/>
				</td>
			</tr>

			<tr>
				<td class="addTd">来文单位<font color="#ff0000">*</font>
				</td>
				<td align="left"><s:textfield id="incomeDeptName" name="incomeDeptName" style="width: 100%;height: 24" value="%{object.incomeDeptName}"/>
				</td>

				<td class="addTd">制发日期<font color="#ff0000">*</font>
				</td>
				<td align="left">
					<input type="text" class="Wdate"  id="incomeDate" name="incomeDate" 
					style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
					value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 				<sj:datepicker id="incomeDate" name="incomeDate"  --%>
<%-- 				value="%{object.incomeDate}" style="width: 120px;" --%>
<%-- 				yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 				changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
				</td>
			</tr>
			<tr>
				<td class="addTd">来文文号</td>
				<td align="left"><s:textfield name="incomeDocNo" style="width: 100%;height: 24" value="%{object.incomeDocNo}"/>
				</td>
				<td class="addTd">正文</td>
				<td align="left"><s:file name="incomeDocFile_"  style="width: 100%;height: 24"/>
				<c:if test="${!empty object.readNo }">
				<a  style="text-decoration:none;color: blue;" href="<%=request.getContextPath()%>/dispatchdoc/readInfo!downloadstuff.do?readNo=${object.readNo}">查看</a>
				</c:if>
				</td>
			</tr>

			<tr>
				<td class="addTd">登记日期</td>
				<td align="left">
					<fmt:formatDate value='${object.createDate}' pattern='yyyy-MM-dd' />
<%-- 				<sj:datepicker id="createDate" name="createDate" value="%{object.createDate}" style="width: 120px;" --%>
<%-- 									 yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
				</td>
			
				<td class="addTd">登记人</td>
				<td align="left">
				${cp:MAPVALUE("userCode",object.createName)}
				</td>
				
			</tr>
			<tr>
				<td class="addTd">文件类型<font color="#ff0000">*</font>
				</td>
				<td align="left">
				<select id="docFileType"  name="docFileType">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('DOC_FILE_TYPE')}">
								<option value="${row.key}" <c:if test="${object.docFileType==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
				</td>
				<td class="addTd">查阅权限<font color="#ff0000">*</font>
				</td>
				<td align="left">
				<select id="readInfoRole"  name="readInfoRole">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('READ_INFO_ROLE')}">
								<option value="${row.key}" <c:if test="${object.readInfoRole==row.key}">selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
				</td>
			</tr>
		</table>
</s:form>
</body>
<script type="text/javascript">
	function doSubmitCheck() {
			if ("" == $.trim($("#incomeDocTitle").val())) {
				alert("请输入文件标题");
				$("#incomeDocTitle").focus();
				return false;
			}
			if ("" == $.trim($("#incomeDeptName").val())) {
				alert("请输入来文单位");
				$("#incomeDeptName").focus();
				return false;
			}
			if ("" == $.trim($("#incomeDate").val())) {
				alert("请输入制发日期");
				$("#incomeDate").focus();
				return false;
			}
			
			if ("" == $.trim($("#docFileType").val())) {
				alert("请选择文件类型");
				$("#docFileType").focus();
				return false;
			}
			if ("" == $.trim($("#readInfoRole").val())) {
				alert("请选择查阅权限");
				$("#readInfoRole").focus();
				return false;
			}
			return true;
		}
		
		function doback(){
		window.location.href = "<%=request.getContextPath()%>/dispatchdoc/readInfo!list.do?";
		}
</script>
</html>
