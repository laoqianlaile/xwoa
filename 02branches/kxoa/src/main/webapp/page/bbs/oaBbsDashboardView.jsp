<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>论坛模块查看</title>
</head>

<body>

<%@ include file="/page/common/messages.jsp"%>

     <fieldset class="_new">
		<legend >	
		论坛模块信息查看
		</legend>
		
		<s:form action="oaBbsDashboard" method="post" namespace="/bbs"
		id="oaBbsDashboardForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
		
				<tr>
					<td class="addTd">版面名称</td>
					<td align="left">
						${object.layoutname }
					</td>
			
					<td class="addTd">所属部门</td>
					<td align="left">
						 ${cp:MAPVALUE('unitcode2JC',object.unitcode)}
					</td>
				</tr>	

				<tr>
					<td class="addTd">模块类型</td>
					<td align="left">
						${cp:MAPVALUE('LayOutType', object.layouttype) }
					</td>
				
					<td class="addTd">顺序号</td>
					<td align="left">
						<s:property value="%{orderno}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">创建人员</td>
					<td align="left">
						 ${cp:MAPVALUE('usercode',object.creater)}
					</td>
					
					<td class="addTd">创建时间</td>
					<td align="left">
						 <fmt:formatDate value="${object.createtime}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>	
			  
				<tr>
					<td class="addTd">是否公开</td>
					<td align="left">
					     <c:choose>
							<c:when test="${'F' eq object.openType }">
							 不公开
						     </c:when>
							<c:otherwise>公开</c:otherwise>
					     </c:choose>
				    </td>
					
					<td class="addTd">子模块管理员</td>
					<td align="left">
						 ${object.approvalNames}
					</td>
				</tr>

                <tr>
					<td class="addTd">是否设置开放时间</td>
					<td align="left">
						<c:if test="${object.isdocontral=='F'}">不设置</c:if>
						<c:if test="${object.isdocontral=='T'}">设置</c:if>
					</td>
					<!-- 设置了开放时间的显示开放时间 -->
					<c:if test="${object.isdocontral=='T'}">
					<td class="addTd">开放时间</td>
					<td align="left">
					    上午&nbsp;<fmt:formatDate value="${object.starttime}" pattern="HH:mm"/> -
						  <fmt:formatDate value="${object.endtime}" pattern="HH:mm"/>
				     /&nbsp;下午&nbsp;<fmt:formatDate value="${object.starttimepm}" pattern="HH:mm"/> -
						<fmt:formatDate value="${object.endtimepm}" pattern="HH:mm"/>
					</td>
					</c:if>
					<!-- 没设置的显示空 -->
					<c:if test="${object.isdocontral=='F'}">
					  <td></td>
					  <td></td>
					</c:if>
				</tr>
				 <tr>
					<td class="addTd">备注</td>
					<td align="left" colspan="3">
						${approvalremark}
					</td>
				</tr>
        </table>
        
        <div class="formButton">
			<input type="button" onclick="history.go(-1);" class="btn" value="返回"/>
		</div>
        
        </s:form>
        </fieldset>

</body>
</html>
