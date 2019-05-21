<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
	<title>公文分级管理--<c:out  value="${userinfo.username}" />
	</title>

</head>
<body>
	<br/>
		<fieldset style="padding:10px;">
	<legend>
		公文分级管理
	</legend>
	
  
	<table cellpadding="0" cellspacing="0" >
		
		<tr>
			<td>&nbsp;</td>
			<td colspan="5">
		     <input type="hidden" name="s_unitcode" value="${s_unitcode}"/>
		     
			 <input type="button" value="添加文书模板" class="btn btnWide" onClick="openNewWindow('<%=request.getContextPath()%>/dispatchdoc/oaFwdepmange!selectDocList.do?s_unitcode=${s_unitcode}&num='+Math.random()+new Date(),'depmangeDocWindow',null);" />
			  <input type="button" value="添加文号规则" class="btn btnWide" onClick="openNewWindow('<%=request.getContextPath()%>/dispatchdoc/oaFwdepmange!selectZWHList.do?s_unitcode=${s_unitcode}&num='+Math.random()+new Date(),'depmangeZwhWindow',null);" />
			
			 <input type="button" value="管理文书模板" class="btn btnWide" onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/templateFile!list.do?num='+Math.random()+new Date(),'depmangeDocWindow',null);" />
			  <input type="button" value="管理文号字典" class="btn btnWide" onClick="openNewWindow('<%=request.getContextPath()%>/sys/dictionary!list.do?s_isdepManager=true&s_style=U&num='+Math.random()+new Date(),'depmangeZwhWindow',null);" />
			
			 
			 
			</td>			
		</tr>
	</table>
    </fieldset>
	<br/>
	
	
	<c:if test="${not empty oaDocdepmange }"> 
	<fieldset style="padding:10px;">
	<legend>
		${cp:MAPVALUE('AGENCYNAME','02')}文书模板管理
	</legend>
	
	<ec:table items="oaDocdepmange" var="doc"  sortable="false" showPagination="false"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="uu" >
		<ec:row>
			<ec:column property="unitcode" title="机构代码" style="text-align:center" />
			<ec:column property="unitname" title="机构名" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('unitcode',doc.unitcode)}" />
			</ec:column>
			<ec:column property="templateids" title="文书编号" style="text-align:center" >
		 	
			</ec:column>						
			<ec:column property="descript" title="文书名称" style="text-align:center" />
			<ec:column property="creatertime" title="获取时间" format="yyyy-MM-dd" cell="date"  style="text-align:center"/>
		
			<ec:column property="unitopt" title="操作" sortable="false"	 >
				
				<a href='oaFwdepmange!delete.do?no=${doc.no}'
					onclick='return confirm("是否删除此条数据");'>
					删除
				</a>
				<a href="javascript:void(0);" onclick="openDocEdit('${doc.templateids}');" >
				           查看文书
				</a> 
			</ec:column>
		
		</ec:row>
	</ec:table> 	
    </fieldset>
	<br/>
	</c:if>
	
	<c:if test="${not empty oaDocdepmange }"> 	
    <fieldset style="padding:10px;">
	<legend>
		${cp:MAPVALUE('AGENCYNAME','02')}文号规则管理
	</legend>
	
 		<ec:table items="oaZWHdepmange" var="zwh" sortable="false"  showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="ur" >
			<ec:row>
								<ec:column property="unitcode" title="机构代码" style="text-align:center" />
				<ec:column property="unitname" title="机构名" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('unitcode',zwh.unitcode)}" />
				</ec:column>
				<ec:column property="dispatchfiletype" title="发文代字" style="text-align:center" >
				 ${cp:MAPVALUE('WJLX',zwh.dispatchfiletype)}
				</ec:column>
				
				<ec:column property="dispatchfiletype" title="文号规则示例" style="text-align:center" >
				  ${cp:MAPDESC('ZWHGZ',zwh.dispatchfiletype)}
				</ec:column>
				<ec:column property="creatertime" title="获取时间" format="yyyy-MM-dd" cell="date" style="text-align:center"/>
				<ec:column property="roleopt" title="操作" sortable="false"	 >
				
					<a href='oaFwdepmange!delete.do?no=${zwh.no}'
							onclick='return confirm("是否删除此条数据?");'>
							删除
					</a>
					<a href='oaFwdepmange!reset.do?no=${zwh.no}'
							onclick='return confirm("是否重置此条数据");'>
							重置
					</a>
					
				</ec:column>
			</ec:row>
		</ec:table> 			
    </fieldset>
	<br/>
	</c:if>
	
   <!-- 	管委会文书模板管理 -->
	<c:if test="${not empty oaDocParentdepmange }">
	<fieldset style="padding:10px;">
	<legend>
		${cp:MAPVALUE('AGENCYNAME','01')}文书模板管理
	</legend>
	
	<ec:table items="oaDocParentdepmange" var="doc"  sortable="false" showPagination="false"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="uu1" >
		<ec:row>
			<ec:column property="unitcode" title="机构代码" style="text-align:center" />
			<ec:column property="unitname" title="机构名" style="text-align:center" >
				<c:out value="${cp:MAPVALUE('unitcode',doc.unitcode)}" />
			</ec:column>
			<ec:column property="templateids" title="文书编号" style="text-align:center" >
		 	
			</ec:column>						
			<ec:column property="descript" title="文书名称" style="text-align:center" />
			<ec:column property="creatertime" title="获取时间" format="yyyy-MM-dd" cell="date"  style="text-align:center"/>
		
			<ec:column property="unitopt" title="操作" sortable="false"	 >
			    <c:if test="${doc.unitcode eq session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}">
				<a href='oaFwdepmange!delete.do?no=${doc.no}'
					onclick='return confirm("是否删除此条数据");'>
					删除
				</a>
				</c:if>
				<a href="javascript:void(0);" onclick="openDocEdit('${doc.templateids}');" >
				           查看文书
				</a> 
				
			</ec:column>
		
		</ec:row>
	</ec:table> 	
    </fieldset>
	<br/>
	</c:if>	
	
	<c:if test="${not empty oaZWHParentdepmange}">  
    <fieldset style="padding:10px;">
	<legend>
		${cp:MAPVALUE('AGENCYNAME','01')}文号规则管理
	</legend>
	
 		<ec:table items="oaZWHParentdepmange" var="zwh" sortable="false"  showPagination="false"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="ur1" >
			<ec:row>
								<ec:column property="unitcode" title="机构代码" style="text-align:center" />
				<ec:column property="unitname" title="机构名" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('unitcode',zwh.unitcode)}" />
				</ec:column>
				<ec:column property="dispatchfiletype" title="发文代字" style="text-align:center" >
				 ${cp:MAPVALUE('WJLX',zwh.dispatchfiletype)}
				</ec:column>
				
				<ec:column property="dispatchfiletype" title="文号规则示例" style="text-align:center" >
				  ${cp:MAPDESC('ZWHGZ',zwh.dispatchfiletype)}
				</ec:column>
				<ec:column property="creatertime" title="获取时间" format="yyyy-MM-dd" cell="date" style="text-align:center"/>
				<ec:column property="roleopt" title="操作" sortable="false"	 >
				 <c:if test="${zwh.unitcode eq session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}">
					<a href='oaFwdepmange!delete.do?no=${zwh.no}'
							onclick='return confirm("是否删除此条数据?");'>
							删除
					</a>
					<a href='oaFwdepmange!reset.do?no=${zwh.no}'
							onclick='return confirm("是否重置此条数据");'>
							重置
					</a>
				</c:if>
					
					
				</ec:column>
			</ec:row>
		</ec:table> 			


    </fieldset>
   
	<br/>	
    </c:if>

</body>

<script type="text/javascript">

 //选择模版上传文档
function openDocEdit(val){
	 
	if(val==null || val==''){
		alert("操作失败，对应模版没有配置!");
	}else{
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var param = "flowStep=ZW_EDIT&Template=" + val;
	openNewWindow(uri + "?"+ param,'editForm','');
	}
}
		function openNewWindow(winUrl, winName, winProps) {
			if (winProps == '' || winProps == null) {
				winProps = 'height=900px,width=1100px,directories=false,location=false,top=0,left=150,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
			}
			window.open(winUrl, winName, winProps);
		}
</script>
</html>
