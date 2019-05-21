<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			办（阅办）件登记
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
	</head>
	<body class="sub-flow">
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="incomeDoc" method="post" namespace="/dispatchdoc" id="incomeDocForm">
			<fieldset class="_new">
				<legend>${object.optBaseInfo.powername}信息</legend>
					<%-- <div class="legend_left"></div>
					   <div class="legend"><b>${object.optBaseInfo.powername}信息</b></div>
					   <div class="legend_right"></div> --%>
					<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
						<tr>
					<td class="addTd">文件标题</td>
					<td align="left" colspan="3"><c:out value="${object.incomeDocTitle}" /></td>
				</tr>
				<tr>
					<td class="addTd">收文编号</td>
					<td align="left" >${object.optBaseInfo.acceptArchiveNo}</td>
					<td class="addTd">收文日期</td>
					<td align="left"><fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' /></td>
				
				</tr>
				<tr>
					<td class="addTd">发文机关</td>
					<td align="left"><c:out value="${object.incomeDeptName}" /></td>
					<td class="addTd">发文字号</td>
					<td align="left"><c:out value="${object.incomeDocNo}" /></td>
				</tr>
			
				<tr>
				<td class="addTd">
								文书分类
							</td>
							<td align="left">
								${cp:MAPVALUE("incomeDocType",object.incomeDocType)}
							</td>
				<td class="addTd">来文单位分类</td>
					<td align="left">
					<c:if test="${object.incomeDocType eq 'SWXZ' }">
					${cp:MAPVALUE("SWXZ", object.incomeDeptType)}	
					</c:if>		
					<c:if test="${object.incomeDocType eq 'SWDW' }">
					${cp:MAPVALUE("SWDW", object.incomeDeptType)}	
					</c:if>				
					</td>
				</tr>
			 	<tr>
					<td class="addTd">密级</td>
					<td align="left">${cp:MAPVALUE("FW_SECRETS_LEVEL", object.secretDegree)}</td>
					<td class="addTd">缓急程度</td>
					<td align="left">${cp:MAPVALUE("critical_level", object.criticalLevel)}</td>
				</tr>
				<tr>
				    <td class="addTd">登记人员</td>
					<td align="left"> ${cp:MAPVALUE('usercode',object.optBaseInfo.createuser)}</td>
					<td class="addTd">登记时间</td>
					<td align="left"colspan="4"><fmt:formatDate value='${object.createDate}' pattern='yyyy-MM-dd' /></td>
				</tr> 
						<%-- <tr>
							<td class="addTd">
								正文材料
							</td>
							<td align="left" colspan="3">
								<a  style="text-decoration:underline" href="<%=request.getContextPath()%>/dispatchdoc/incomeDoc!downloadstuff.do?object.djId=${object.djId}">${object.incomeDocFileName}</a>
							</td>
						</tr> --%>
		
						
					</table>
				</fieldset>			
		</s:form>		
	</body>
	</html>