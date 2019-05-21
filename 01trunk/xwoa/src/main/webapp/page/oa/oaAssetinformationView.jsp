<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformation.view.title" /></title>
<script type="text/javascript">
function reEditno(no){
	var ev=$("#assetleftalert").val();
	var remark = $("#createRemark").val();
	var currentHref = encodeURI("<%=request.getContextPath()%>/oa/oaAssetinformation!oaAssetinformationAdd.do?object.no="+no+"&object.assetleftalert="+ev+"&object.createRemark="+remark);
   $.post(
		 currentHref,
         {"no":no},
         function(){
          window.location.href="oa/oaAssetinformation!list.do";
         }
   );

}
</script>
</head>

<body>
<fieldset class="form">
        <legend>
			<p class="ctitle">查看资产信息</p>
		</legend>
  <c:if test="${callback ne 'T' }">
		
		<input type="button"
				name="back" Class="btn" onclick="history.back(-1);" value="返回" />
	</c:if>
	<c:if test="${callback eq 'F' }">
	<input type="button" onclick="reEditno('${no }');" value="保存" class="btn"> 
	</c:if>
<%@ include file="/page/common/messages.jsp"%>

<p>	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
  
				<tr>
					<td class="addTd">
						资产名称
					</td>
					<td align="left" >
						${cp:MAPVALUE('equipment', datacode) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.assetnum" />
					</td>
					<td align="left">
						<s:property value="%{assetnum}" />&nbsp;(<s:property value="%{assetunit}" />)
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformation.creater" />
					</td>
					<td align="left">
						${cp:MAPVALUE('usercode', creater) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.createtime" />
					</td>
					<td align="left">
					<fmt:formatDate value='${createtime}' pattern='yyyy-MM-dd HH:mm:ss' />
					</td>
				</tr>	

				<tr>
			    	<td class="addTd">
						<s:text name="oaAssetinformation.senddeptype" />
					</td>
					<td align="left">
				    	${cp:MAPVALUE('UnitType', senddeptype) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.createDepno" />
					</td>
					<td align="left">
					${cp:MAPVALUE('unitcode', createDepno) }
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformation.assetleftalert" />
					</td>
					<td align="left" colspan="4">
					<c:if test="${callback ne 'F' }">
						<s:property value="%{assetleftalert}" />
					</c:if>
					<c:if test="${callback eq 'F' }">
					<s:textfield name="assetleftalert" value="%{assetleftalert}" id="assetleftalert" />
					</c:if>
					</td>
					<%-- <td class="addTd">
						<s:text name="oaAssetinformation.state" />
					</td>
					<td align="left">
						<s:property value="%{state}" />
					</td> --%>
				</tr>	

				<tr>
				   <td class="addTd">
						<s:text name="oaAssetinformation.createRemark" />
					</td>
					<td align="left" colspan="5">
					<c:if test="${callback ne 'F' }">
						<s:property value="%{createRemark}" />
					</c:if>
					<c:if test="${callback eq 'F' }">
					     <s:textfield name="createRemark" value="%{createRemark}" id="createRemark" />
					</c:if>
					</td>
					
				</tr>	

</table>

</fieldset>
</body>
</html>
