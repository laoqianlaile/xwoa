<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>OA帮助平台编辑</title>
</head>

<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />

<body class="sub-flow">

	<fieldset class="form">
		<legend>
			编辑OA帮助平台
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/oaHelpinfo!save.do"
			method="post" id="oaHelpinfoForm" enctype="multipart/form-data" data-validate="true">

			<input type="hidden" name="djid" value="${object.djid }" />
			<input type="hidden" id="fileDocname" name="fileDocname" value="${object.fileDocname }" />
			<!-- <input type="button" id="saveBtn" name="save" class="btn" value="保存"/> -->
			<s:submit method="saveModify" cssClass="whiteBtnWide" value="保存" cssStyle="width:74px;height:24px;border:none;" />
			<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			<!-- <input type="button" class="btn" value="返回" onclick="window.history.back();" /> -->
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
				    <td class="addTd">名称<font color="#ff0000">*</font></td>
					<td align="left" colspan="3"><input type="text" name="infoName" value="${object.infoName }" style="width:99%;"/></td>
				</tr>
				
				<tr>
					<td class="addTd">栏目类型</td>
					<td align="left"><select name="columnType" style="width: 200px;" class="focused required">
							<c:forEach items="${cp:LVB('columntype')}" var="u">
								<option value="${u.value }"
									<c:if test="${u.value eq object.columnType }" >selected="selected" </c:if>>
									${u.label }</option>
							</c:forEach>
					</select></td>
					
					<td class="addTd">是否精华</td>
					<td align="left"><select name="isgood" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="0"
								<c:if test="${'0' eq object.isgood }">selected="selected"</c:if>>普通帖</option>
							<option value="1"
								<c:if test="${'1' eq object.isgood }">selected="selected"</c:if>>精华帖</option>
					</select></td>
					
				</tr>
				<tr>
					<td class="addTd">启用状态</td>
					<td align="left"><select name="state" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="T"
								<c:if test="${'T' eq object.state }">selected="selected"</c:if>>启用</option>
							<option value="F"
								<c:if test="${'F' eq object.state }">selected="selected"</c:if>>停用</option>
					</select></td>

					<td class="addTd">是否可回复留言</td>
					<td align="left"><select name="isallowcomment" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="Y"
								<c:if test="${'Y' eq object.isallowcomment }">selected="selected"</c:if>>可以</option>
							<option value="F"
								<c:if test="${'N' eq object.isallowcomment }">selected="selected"</c:if>>不可以</option>
					</select></td>
				</tr>
								
				    <tr>
				    <c:if test="${not empty object.fileDocname}">	
				        <td class="addTd">附件</td>
				        <td align="left">
				        <a id="showFile" href="#" onclick="downFile('${object.djid}')" style="font-size:10px; color:blue; text-decoration: underline"> ${object.fileDocname} </a>
				        &nbsp;&nbsp;&nbsp;<a href="#" id="deleteFile" style="color:red;">[删除]</a>
				        </td>
					</c:if>		
					<c:if test="${empty object.fileDocname }">
						    <td class="addTd">上传附件</td>
						    <td colspan=""><s:file name="fileDoc_" size="40" style="width: 200px;"/></td>
				    </c:if>
				    <td class="addTd">归属菜单<font color="#ff0000">*</font></td>
					<td align="left"><input style="width: 200px;" type="text" id="optname" name="optname" value="${object.optname }"/>
						<input id="optid" type="hidden" name="optid"
						 value="${object.optid }" />
					</td>
				    </tr>
			    
			    
				<tr>
					<td class="addTd">描述</td>
					<td align="left" colspan="3"><textarea name="remark" id="remark" cols="40"
							rows="10" style="width: 100%; height: 200"/>${object.remark }</textarea></td>
				</tr>
			</table>
						<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
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
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</form>

	</fieldset>

	<script type="text/javascript">
	
	$(function(){
		$('#saveBtn').click(function(){
			
			editor.sync();
			$("#oaHelpinfoForm").submit();
		});
	    
		if(null == $('#fileDocname').val()){
   			$('#uploadFile').hide();
		}
		
		$('#deleteFile').click(function(){
			$('#fileDocname').val(null);
			$('#showFile').html(null);
			$(this).hide();
			$('#uploadFile').show();
		});
	});
	
	function downFile(id) {
		var url = "oaHelpinfo!downStuffInfo.do?djid=" + id;
		document.location.href = url;
	}
	//选择菜单
	$(function(){
		$("#optname").click(
				function() {
					var d = '${menusJsonStr}';
					if (d.trim().length) {
						selectPopWin(jQuery.parseJSON(d),
								$("#optname"),//菜单名称
								$("#optid"));//菜单id
					}
					;
				});
	});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow,1).init();//此处限制只能选择一个菜单
		setAlertStyle("attAlert");
	}
	</script>
</body>
</html>
