<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="qlyxSqcl.list.title" /></title>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".upbtn").click(
						function() {
							var nodeInstId = document
									.getElementById("nodeInstId").value;
							var djId = document.getElementById("djId").value;
							var id = $(this).attr("retvalue");
							$("#showbox").load(
									'qlyxSqcl!edit.do?nodeInstId=' + nodeInstId
											+ '&djId=' + djId + "&clid=" + id
											+ "&id="
											+ "&flowInstId=${flowInstId}");
						});

				$("#checkOpp").click(function() {
					var oppname = $("#checkOpp").html();
					if (oppname == '全选')
						oppname = '取消';
					else
						oppname = '全选'
					$("#checkOpp").html(oppname);

					$("[name='items']:checkbox").each(function() {
						$(this).attr("checked", !$(this).attr("checked"));

					});

				});

				$("#SubmitID").click(function() {
					var Str = "";
					$("[name='items']:checkbox:checked").each(function() {
						Str += $(this).val() + "\n\r";
					});

					if (Str == "") {
						alert("您还没有选择要提交的材料或者所有材料已经提交完毕!")
						return;
					}
					$("#qlyxSqclForm").submit();

				});

			});

	function downFile(id) {
		var url = "qlyxSqcl!downloaddb.do?id=" + id;
		document.location.href = url;
	}

</script>
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
						
						<td class="tableHeader">办理</td>
					</tr>
				</thead>

				<tbody class="tableBody">
					<c:forEach items="${stuffnames0}" var="stuffname" >
						
						<tr class="odd" align="center">
						
							<td rowspan='${stuffname[1]}'>${stuffname[0]}</td>
				
							<c:forEach items="${optStuffs}" var="optStuff" varStatus="status">
							<c:if test="${stuffname[0] eq optStuff.stuffname}">
								
							<td>${optStuff.filename}</td>
							<td>${optStuff.nodename}</td>
							<td>${cp:MAPVALUE("usercode",optStuff.uploadusercode)}</td>
							<td>
								<a style="text-decoration: none; color: blue;"
										href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${optStuff.stuffid}">${optStuff.filename}</a>
							</td>
		
								
						<c:if test="${stuffname[1]>1}">
							</tr><tr class="odd" align="center">
						</c:if>
									
							</c:if>
								
							</c:forEach>
						</tr>
					</c:forEach>
				
				
					<%-- <c:forEach items="${optStuffs}" var="optStuff" varStatus="status">
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
						<td>${optStuff.nodename}</td>
						<td>${cp:MAPVALUE("usercode",optStuff.uploadusercode)}</td>
					
						<td>
						<a style="text-decoration: none; color: blue;"
										href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${optStuff.stuffid}">${optStuff.filename}</a>
							</td>
						</tr>
					</c:forEach> --%>
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
