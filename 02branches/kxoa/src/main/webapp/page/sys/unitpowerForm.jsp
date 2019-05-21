<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>机构权限信息</title>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.js"></script>

<script src="${pageContext.request.contextPath}/scripts/roleTree.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/treetable/Treetable_files/jqtreetable.css" />
<style type="text/css">
#treet2 td { height:28px;line-height:28px; }
</style>
</head>

<body class="sub-flow">

<fieldset style="padding:10px;">
	<legend class="ctitle" style="margin-bottom:10px;width:auto;">机构权限信息</legend>

<s:form action="unit" namespace="/sys" styleId="unitpowerForm" theme="simple" >

	<s:submit method="saveUnitPower" cssClass="btn" value="保存" />

	<input type="button" value="返回" Class="btn" onclick="window.history.back()" />
	
	<table cellpadding="0" cellspacing="0" class="viewTable">
		<s:hidden name="unitcode" value="%{object.unitcode}"></s:hidden>
		<tr>
			<td class="addTd">机构代码：</td>
			<td align="left" width="40">
			${object.unitcode}</td>
			<td class="addTd">机构编码：</td>
			<td align="left">${object.depno}</td>
			<td class="addTd">机构名称：</td>
			<td align="left">${object.unitname}</td>
		</tr>
		<tr>
			<td class="addTd">机构描述：</td>
			<td align="left" colspan="4" >${object.unitdesc}</td>
		</tr>
	</table>

<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
			<td class="tableHeader">业务名称</td>   
			<td class="tableHeader">业务操作</td>  
			</tr>  
		</thead>
		
		
				<tbody id="treet2">
					<%-- <c:set value="odd" var="rownum" /> --%>
					<c:forEach var="role" items="${fOptinfos}" varStatus="status">
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
		<%-- <tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="fOptinfo" items="${fOptinfos}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
				<td><c:out value="${fOptinfo.optname}"/></td>   
				<td style="padding-left:10px;text-align:left;">
					<c:forEach var="row" items="${cp:OPTDEF(fOptinfo.optid)}">    
						<input type="checkbox" name="optcodelist" value="${row.optcode}"
							<c:if test="${powerlist[row.optcode] eq '1' }">  checked="checked" </c:if>    />
						<c:out value="${row.optname}"/>    
					</c:forEach>         
				</td>  
			</tr>  
          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
          
		</c:forEach> 
		</tbody>       --%>  
	</table>
</div>
</s:form>
</fieldset>
</body>

<script type="text/javascript">
$(function(){
	var imgpath = '${pageContext.request.contextPath}' + "/scripts/treetable/images/TreeTable";
	var $roleTree = $("#treet2");
	var index = $.parseJSON('${INDEX}').indexes;
	
	var $objRoleTree = new jQueryCheckBoxTree();
	$objRoleTree.makeTreeTable($roleTree ,index, imgpath);
});
</script>
</html>
