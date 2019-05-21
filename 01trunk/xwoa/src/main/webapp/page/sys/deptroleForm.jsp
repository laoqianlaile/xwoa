<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head><meta name="decorator" content='${LAYOUT}'/>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />	
<script type="text/javascript" 
src="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.js"></script>

<script src="${pageContext.request.contextPath}/scripts/roleTree.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.css" />
<style type="text/css">
#treet2 td { height:28px;line-height:28px; }
</style>
<title>部门角色信息</title>
	<s:include value="/page/common/formValidator.jsp"></s:include>
	<script type="text/javascript">
		
	$(document).ready(function(){
		
		$.formValidator.initConfig({formid:"form1",
			onerror:function(msg,obj,errorlist){
			//$.map(errorlist,function(msg1){alert(msg1)});
				alert(msg);
			}
		});
		$("#rolecode").formValidator().inputValidator({min:1,max:10,onerror:"输入1到10个字符"})
		.regexValidator({regexp:"username",datatype:"enum",onerror:"输入字母或者数字"});
		$("#rolename").formValidator().inputValidator({min:1,max:10,onerror:"输入1到10个字符"})
		.regexValidator({regexp:"chinese",datatype:"enum",onerror:"输入中文"});
	
	});
		
	
	</script>

</head>

<body class="sub-flow">

<s:form action="deptManager" namespace="/sys" styleId="deptroleForm" onsubmit="return validateDeptroleForm(this);" >
<fieldset style="padding-top:10px;">
	<legend>部门角色信息</legend>
	<table cellpadding="0" cellspacing="0" align="center" class="viewTable">
		
		<tr>
			<td class="addTd">角色代码<font color='red'>*</font></td>
			<td align="left" width="350">
			<c:if test="${not empty roleinfo.rolecode}">
				<s:textfield name="roleinfo.rolecode"  value="%{roleinfo.rolecode}" readonly="true" /></c:if>
			<c:if test="${empty roleinfo.rolecode}">	
				<s:textfield name="roleinfo.rolecode" id="rolecode" />
				</c:if>
				<span id="rolecodeTip"></span>
			</td>
			<td class="addTd">角色名<font color='red'>*</font></td>
			<td align="left"><s:textfield name="roleinfo.rolename"  id="rolename"/>
				<span id="rolenameTip"></span>
			</td>
		</tr>
		<tr>
			<td class="addTd">角色描述</td>
			<td align="left" colspan="3"><s:textarea name="roleinfo.roledesc" style="width:400px; height:40px;" /></td>
		</tr>
	</table>
</fieldset>
	
<fieldset style="padding:10px;">
<legend>角色权限</legend>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
			<td class="tableHeader">业务名称</td>   
			<td class="tableHeader">业务操作</td>  
			</tr>  
		</thead>
	<%-- 	<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="optpower" items="${fOptPowers}"> 
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
				<td><c:out value="${optpower.optname}"/></td>   
				<td>
					<c:forEach var="optcode" items="${optpower.powerlist}">    
						<c:if test="${powerlist[optcode] eq '1' }">
							<input type="checkbox" name="optlist" value="${optcode}" checked="checked" />
						</c:if>    
						<c:if test="${not(powerlist[optcode] eq '1') }">
							<input type="checkbox" name="optlist" value="${optcode}"  />
						</c:if> 
						<c:out value="${cp:MAPVALUE('optcode',optcode)}"/>    
					</c:forEach>         
				</td>  
			</tr>  
 
          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
          
		</c:forEach> 
		</tbody>  
		 --%>
		
			<tbody id="treet2">
					<%-- <c:set value="odd" var="rownum" /> --%>
					<c:forEach var="role" items="${fOptPowers}" varStatus="status">
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
<div style="padding:10px 0 0 10px;"><s:submit method="saveDeptRole" cssClass="btn" value="保存" />
	<input type="button"  value="返回" Class="btn" onclick="window.history.back()"/></div>
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
