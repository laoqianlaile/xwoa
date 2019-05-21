<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="qlyxSqcl.list.title" /></title>

</head>

<body style="border: 0">
	<%-- <sj:head locale="zh_CN" /> --%>
	<s:form action="qlyxSqcl!submitFile.do" method="post" namespace="/yxxk"
		id="qlyxSqclForm">
		<input type="hidden" id="nodeInstId" name="nodeinstid"
			value="${nodeInstId}" />
		<input type="hidden" id="djId" name="djId" value="${djnodeid}" />
		<input type="hidden" id="flowInstId" name="flowInstId"
			value="${flowInstId}" />
		<fieldset class="_new">
			<legend>
				<b>已上传材料列表</b>
			</legend>
			<table border="0" cellspacing="0" cellpadding="0" class="viewTable"
				width="100%">

				<thead>
					<tr>
						<td class="tableHeader">材料名称</td>
						 <td class="tableHeader">文件名称</td> 
						<td class="tableHeader">节点名称</td>
						<td class="tableHeader">材料上传人</td>
						<!-- <td class="tableHeader">是否纸质</td> -->
						<td class="tableHeader">办理</td>
					</tr>
				</thead>

				<tbody class="tableBody">
					<c:forEach items="${optStuffs}" var="optStuff" varStatus="status">
						<c:if test="${status.index %2==0}">
							<tr class="odd" align="center"
								onmouseover="this.className='highlight'"
								onmouseout="this.className='odd'">
						</c:if>
						<c:if test="${status.index %2==1}">
							<tr class="even" align="center"
								onmouseover="this.className='highlight'"
								onmouseout="this.className='even'">
						</c:if>
						<td>${optStuff.stuffname}</td>
						<td>${optStuff.filename}</td>
						<%-- <td>${qlyxSqcl.fileName}</td> --%>
						<td>${optStuff.nodename}</td>
						<td>${cp:MAPVALUE("usercode",optStuff.uploadusercode)}</td>
						<%-- <td>${nodeinstName}</td> --%>
						<%-- <td><c:if test="${qlyxSqcl.iszhi=='T'}">是</c:if> <c:if
								test="${qlyxSqcl.iszhi=='F'}">否</c:if></td> --%>
						<td>
						<a  style="text-decoration:none;color: blue;" href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${optStuff.stuffid}">下载</a>
								<%-- <button class="btn" onclick="downFile('${optStuff.stuffid}')">下载</button> --%>
							
						<%-- 		<button class="btn" onclick="delFile('${optStuff.stuffid}')">删除</button> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</s:form>
	<script type="text/javascript">
		function getFileName(object) {
			var url = object.value;
			var pos = url.lastIndexOf("/");
			if (pos == -1) {
				pos = url.lastIndexOf("\\");
			}
			var filename = url.substr(pos + 1);
			document.getElementById("clname").value = filename;
		}
	</script>
</body>
</html>
