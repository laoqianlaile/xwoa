<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>机构用户列表--<c:out
				value="${unitinfo.unitname}" />
		</title>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>
	
	<body class="sub-flow">
	
	 <div style="padding-top:10px;">
	 <input type="button" value="添加用户机构" class="btn btnWide" onclick="location.href='unit!builtUnitUser.do?userunit.unitcode=${object.unitcode}'" />
	 <input type="button" value="返回" class="btn" onclick="location.href='deptManager!listunit.do'" />
	 <%-- <a href='unit!builtUnitUser.do?userunit.unitcode=${object.unitcode}'><span class="btn btnWide">添加机构用户</span></a>
	 <a href='deptManager!listunit.do'><span class="btn">返回</span></a> --%>
    <table cellpadding="0" cellspacing="0" class="viewTable" style="width:99.5%;">
				<tr>
					<td class="addTd">
						机构代码
					</td>
					<td align="left">
						${object.unitcode }				
					</td>
			<td class="addTd">机构编码：</td>
			<td align="left">${object.depno}</td>
					<td class="addTd">
						机构名称
					</td>
					<td align="left">
						${object.unitname}
					</td>
				</tr>
				<tr>
					<td class="addTd">
						机构描述
					</td>
					<td align="left" colspan="4">
						${object.unitdesc}						
					</td>
				</tr>
			</table>
		</div>
      <ec:table items="unitusers" var="user"  action="sys/unit!view.do" 
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" >
			<ec:row>
				<ec:column property="usercode" title="用户代码" style="text-align:center" />
				<ec:column property="username" title="用户名" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('usercode',user.usercode)}" />
				</ec:column>
				<ec:column property="userstation" title="用户岗位" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('StationType',user.userstation)}" />
				</ec:column>
				<ec:column property="userrank" title="行政职务" style="text-align:center" >
					<c:out value="${cp:MAPVALUE('RankType',user.userrank)}" />
				</ec:column>
				<ec:column property="isprimary" title="是否主单位" sortable="false" style="text-align:center" >
					<c:out value="${YES_NO[user.isprimary]}" />
				</ec:column>
				<ec:column property="rankmemo" title="授权说明" sortable="false" style="text-align:center" />
				 <ec:column property="useropt" title="操作" sortable="false"	 >
					<c:if test="${user.isprimary != 'T'}">	
					<a href='unit!deleteUnitUser.do?userunit.unitcode=${user.unitcode}&userunit.usercode=${user.usercode}&userunit.userstation=${user.userstation}&userunit.userrank=${user.userrank}'
						onclick='return confirm("是否删除用户：${cp:MAPVALUE("usercode",user.usercode)}?");'>
						删除
					</a>
					</c:if>		
				</ec:column>
			 
			</ec:row>
		</ec:table> 	
 
</body>
</html>
