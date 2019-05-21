<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />


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
	});
</script>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
</head>

<body>
	<s:form action="oaOptPowerDef" namespace="/oa" id="form1" styleId="roleForm" theme="simple">
		<fieldset style="padding:10px;">
			<legend class="ctitle" style="width:auto;">收发文权限设置</legend>
		
		<div id="role_tree" class="ztree"></div>

		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">

				<thead>
					<tr>
						<td class="tableHeader">行政级别</td>
						<td class="tableHeader">权限级别</td>
					</tr>
				</thead>

				<tbody id="treet2">
					<c:forEach var="role" items="${optinfos }" varStatus="status">
						<tr id="item_${status.count}" align="left">
							<td style="padding-left:20px;"> ${role.datavalue}</td>
							<td style="padding-left:5px;]">
							<input style="float:left; margin:4px 0 0 4px;" type="checkbox" id="${role.datacode}" class="pc"  />全选
							<c:forEach var="row" items="${optdefs }">
								<input type="checkbox"  id="c_${row.datacode}" name="optcodelist" class="cc"  value="${row.datavalue};${role.datacode};${row.datacode}"
									<c:set var='temp' value="${role.datacode}${row.datacode}" scope="page"/>  
									<c:if test="${powerlist[temp] eq '1'}">checked="checked" </c:if> />
								<c:out value="${row.datavalue}"/>
						    </c:forEach>
						   </td>
					</tr>
					
					</c:forEach>

				</tbody>
			
			</table>
		</div>
		</fieldset>
		<div class="formButton">
		<s:submit method="saveDocViewPower" cssClass="btn" value="保存" />
		</div>
	</s:form>
 <script type="text/javascript"> 
		$(function() {
			var imgpath = '${pageContext.request.contextPath}'
					+ "/scripts/treetable/images/TreeTable";
			var $roleTree = $("#treet2");
			var index = $.parseJSON('${INDEX}').indexes;

			var $objRoleTree = new jQueryCheckBoxTree();
			$objRoleTree.makeTreeTable($roleTree, index, imgpath);
		}); 
	</script>	 

</body>

</html>
