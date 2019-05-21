<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.js"></script>

<script src="${pageContext.request.contextPath}/scripts/roleTree.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.css" />
<style type="text/css">
#treet2 td { height:28px;line-height:28px; }
</style>

<title>角色信息</title>

<s:include value="/page/common/formValidator.jsp"></s:include>
<script type="text/javascript">
	$(document).ready(function() {

		$.formValidator.initConfig({
			formid : "form1",
			onerror : function(msg, obj, errorlist) {
				alert(msg);
			}
		});

		$("#rolecode").formValidator().inputValidator({
			min : 1,
			max : 10,
			onerror : "角色代码请输入1到10个字符"
		}).regexValidator({
			regexp : "username",
			datatype : "enum",
			onerror : "输入字母或者数字"
		});
		$("#rolename").formValidator().inputValidator({
			min : 1,
			max : 12,
			onerror : "角色名称请输入1到12个字符"
		});
	});
</script>

</head>

<body class="sub-flow">

	
	
	<s:form action="roleDef" namespace="/sys" id="form1" styleId="roleForm" theme="simple">
	<fieldset  class="form">
		<legend>角色信息</legend>
		<input id="hid_optcodelist" type="hidden" name="optcodelist"/>
		<div class="formButton">
		<s:submit method="save" cssClass="btn" value="保存" />
		<input type="button" value="返回" class="btn" onclick="window.history.back()" />
		</div>
		<table cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">角色代码*</td>
				<td align="left" width="400"><c:if test="${not empty rolecode}">
						<s:textfield name="rolecode" value="%{rolecode}" readonly="true" style="width:250px;" />
					</c:if> <c:if test="${empty rolecode}">
						<s:textfield id="rolecode" name="rolecode" value="%{rolecode}" style="width:250px;" />
					</c:if> <span id="rolecodeTip"></span>
					<c:if test="${empty rolecode}"><input type="checkbox" name="isPublic" value="T" <c:if test="${isPublic eq 'T' }">checked="true"</c:if>>公共角色</c:if></td>
				<td class="addTd">角色名*</td>
				<td align="left"><s:textfield id="rolename" name="rolename" value="%{rolename}" style="width:250px;" /> <span id="rolenameTip"></span></td>
			</tr>
			<tr>
				<td class="addTd">角色描述</td>

				<td align="left" colspan="3"><s:textfield name="roledesc" value="%{roledesc}" style="width:600px;height:50px;" /></td>
			</tr>
		</table>
		</fieldset>
		<fieldset style="padding:10px;">
			<legend>角色权限</legend>
		
		<div id="role_tree" class="ztree"></div>

		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

				<thead>
					<tr>
						<td class="tableHeader">业务名称</td>
						<td class="tableHeader">业务操作</td>
					</tr>
				</thead>

				<tbody id="treet2">
					<%-- <c:set value="odd" var="rownum" /> --%>
					<c:forEach var="role" items="${fOptinfos }" varStatus="status">
						<tr id="item_${status.count}" align="left">
							<td><input style="float:left; margin:4px 0 0 4px;" type="checkbox" id="${role.optid}" class="pc" value="${role.optid}" /> ${role.optname}</td>
							<td style="padding-left:5px;]"><c:forEach var="row" items="${cp:OPTDEF(role.optid)}">
								<input type="checkbox"  id="c_${row.optcode}" name="optcodelist" class="cc"  value="${row.optcode}"
									<c:if test="${powerlist[row.optcode] eq '1'}">checked="checked" </c:if> />
								<c:out value="${row.optname}"/>
						</c:forEach></td>
						</tr>
						<%-- <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" /> --%>
					</c:forEach>

				</tbody>
			
			</table>
		</div>
		</fieldset>
	</s:form>
<script type="text/javascript">
$(function(){
	var imgpath = '${pageContext.request.contextPath}' + "/scripts/treetable/images/TreeTable";
	var $roleTree = $("#treet2");
	var index = $.parseJSON('${INDEX}').indexes;
	
	var $objRoleTree = new jQueryCheckBoxTree();
	$objRoleTree.makeTreeTable($roleTree ,index, imgpath);
});
</script>	

</body>

</html>
