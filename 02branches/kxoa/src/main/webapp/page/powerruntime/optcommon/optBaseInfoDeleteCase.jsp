<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			删除办件
		</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
			<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
		  <legend>查询条件</legend>
			<s:form action="optApply" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
                  <td class="addTd" >办件编号:</td>
                  <td><s:textfield name="s_djId" value="%{djId}" /> 
                  <td class="addTd" >办件名称:</td>
                  <td><s:textfield name="s_transaffairname" value="%{transaffairname}" /> </td>
				</tr>
				<tr>
				 <%--  <td class="addTd" >办件类型:</td>
				  <td><s:textfield name="s_transaffairname" value="%{transaffairname}"/></td> --%>
				  <td></td>	
				   <td><s:submit method="listXforDelete"  key="查询" cssClass="btn"/></td>
				</tr>
			</table>
			</s:form>
		</fieldset>
		<ec:table action="powerruntime/optApply!listXforDelete.do" items="optBaseInfos" var="srPermitApply"
 			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" 
 			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit">  
 			<ec:row>
 			    <ec:column property="user"
				  title='<button name="quanxuan"  id="quanxuan" target="false" >全选</button><button name="czpw" target="false" >删除</button>'
				  sortable="false"  style="width:5%">		
					<input type="checkbox" id="${srPermitApply.djId}" class="ecbox" 
						value="${srPermitApply.djId}">
			    </ec:column>  
 			    <ec:column property="djId" title="办件编号" style="text-align:center" sortable="false"/>
				<ec:column property="transaffairname" title="办件名称" style="text-align:center" sortable="false"/>
				<ec:column property="srPermitApply.createdate" title="登记日期" style="text-align:center" sortable="false" >
				<fmt:formatDate value="${srPermitApply.createdate}" pattern="yyyy-MM-dd"/>
				</ec:column> 
 			</ec:row>  
 		</ec:table>
	</body>
	<script type="text/javascript">
	
	$("[name='quanxuan']").bind('click',function(){
		if($("[name='quanxuan']").attr("target")=="false"){
		$("[name='quanxuan']").attr("target","true");
		$("input[class='ecbox']").attr("checked","true"); 
		$("#quanxuan").html('取消');
		}
		else{
			$("[name='quanxuan']").attr("target","false");
			$("input[class='ecbox']").removeAttr("checked");
			$("#quanxuan").html('全选');
		}	
	});

	$("[name='czpw']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		for (var i = 0; i < items.length; i++) {
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
		if(confirm("是否确定删除?")){
			$.ajax({
				   type : 'post',
				   url: "<%=request.getContextPath()%>/powerruntime/optApply!deleteCase.do",
				   dataType:'text',
				   async: false,
				   data : {
					   resetUsers:  vfstring
				   },
				   success: function(data){
				           location.href="<%=request.getContextPath()%>/powerruntime/optApply!listXforDelete.do";
				   }
		});
		}
		
		}
		else{
			alert("请选中办件再删除 !");
			
		}
			
	}); 
	
</script>
</html>
