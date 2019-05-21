<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>角色信息</title>
	<s:include value="/page/common/formValidator.jsp"></s:include>
	<script type="text/javascript">
	$(document).ready(function(){
	
		var items=$('#ec_table tbody tr');	 
		 for( var i=0;i<items.length+1;i++)
		 {		
		 	$('#item_'+i+' :checkbox:first').change(function(){
		 	var ls =$(this).parent().parent().children().last().find(':checkbox');
		 		if($(this).attr('checked'))
		 		{ 
		 			ls.attr('checked','checked');		 				 			
		 		}
		 		else
		 		{ 
		 			ls.removeAttr('checked');
		 		}		 		
		 	});
		 }
		 $.formValidator.initConfig({formid:"form1",
				onerror:function(msg,obj,errorlist){
					alert(msg);
				}
			});
		 	
		 	$("#rolecode").formValidator().inputValidator({min:1,max:10,onerror:"角色代码请输入1到10个字符"})
		 	.regexValidator({regexp:"username",datatype:"enum",onerror:"输入字母或者数字"});
			$("#rolename").formValidator().inputValidator({min:1,max:100,onerror:"角色名称请输入1到100个字符"});		 
	});	
	
	</script>

</head>

<body class="sub-flow">
<p>角色信息</p>


<s:form action="roleDef" namespace="/sys"  id="form1" styleId="roleForm" theme="simple" >
	
	<s:submit method="save" cssClass="btn" value="保存" />
	<input type="button" value="返回" class="btn" onclick="window.history.back()"/>
	<table cellpadding="1" cellspacing="1" align="center">
		
		<tr>
			<td class="TDTITLE">角色代码*</td>
			<td align="left" width="300">
				<c:if test="${not empty rolecode}">
				<s:textfield name="rolecode"  value="%{rolecode}" readonly="true" /></c:if>
				<c:if test="${empty rolecode}">
					<s:textfield  id="rolecode" name="rolecode"  value="%{rolecode}"  /></c:if>
				<span id="rolecodeTip"></span>
				<input type="checkbox" id="isPublic" name="isPublic" <c:if test="${isPublic eq 'T' }">checked="true"</c:if>>公共角色
			</td>
			<td class="TDTITLE">角色名*</td>
			<td align="left">
			
				<s:textfield id="rolename" name="rolename" value="%{rolename}" />
			<span id="rolenameTip"></span>
			</td>
		</tr>
		<tr>
			<td class="TDTITLE">角色描述</td>

			<td align="left" colspan="3"><s:textfield name="roledesc" value="%{roledesc}"
				rows="1" cols="40" /></td>
		</tr>
	</table>
	<p>角色权限</p>

<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
			<td class="tableHeader">业务名称</td>   
			<td class="tableHeader">业务操作</td>  
			</tr>  
		</thead>
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="fOptinfo" items="${fOptinfos}" varStatus="status">    
			<tr class="${rownum}" id="item_${status.count}" onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
				<td><input type="checkbox" name="all_${status.count}"  ><c:out value="${fOptinfo.optname}"/></td>   
				<td>
					<c:forEach var="row" items="${cp:OPTDEF(fOptinfo.optid)}">    
						<input type="checkbox"  name="optcodelist" value="${row.optcode}"
							<c:if test="${powerlist[row.optcode] eq '1' }">  checked="checked" </c:if>    />
						<c:out value="${row.optname}"/>    
					</c:forEach>         
				</td>  
			</tr>  
          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
          
		</c:forEach> 
		</tbody>        
	</table>
</div>

</s:form>


</body>

</html>
