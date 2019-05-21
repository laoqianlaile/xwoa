<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>材料上传</title> 
<script type="text/javascript">
	function setTab(tab) {
		var id = tab.id;
		$("li").removeClass("select");
		$(tab).addClass("select");
		$("tr[name='filetr']").hide();
		$("tr[class='" + id + "']").show();
	}
	function setAll() {
		$("tr[name='filetr']").show();
		$("li").removeClass("select");
		$("#suoyou").addClass("select");
	}
</script>
<style type="text/css">
#heads ul {
	height: 25px;
	border-bottom: 1px solid #bbb;
}

#heads li {
    float:left;
    list-style:none;
	position: relative;
	width: 80px;
	margin: 0px 6px 0px;
	height: 24px;
	line-height: 24px;
	border: 1px solid #bbb;
	text-align: center;
	border-bottom: none;
	top: 1px;
}

#heads li.select {
	background: #ddd;
}
</style>
</head>
<body class="sub-flow">
	<%-- <c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	<h3 class="sub-flow-title ${notitle }">材料上传信息</h3> --%>
	<div>
		<div id="heads">
			<ul>
			<li id="suoyou" class="li select" onclick="setAll();">所有</li>
				<c:forEach var="row" items="${cp:DICTIONARY('FILETYPE')}">
					<li class="li" onclick="setTab(this);" id="${row.key}" style="display: none;">${row.value}</li>
				</c:forEach>				
			</ul>
		</div>

		<div class="tabList">
			<div>
				<table border="0" cellspacing="0" cellpadding="0" class="viewTable" width="100%">
					<tr>
						<td class="tableHeader" width="10%">类别</td>
						<td class="tableHeader">材料名称</td>
						<td class="tableHeader">文件名称</td>
						<td class="tableHeader" width="12%">节点名称</td>
						<td class="tableHeader" width="11%">材料上传人</td>
					</tr>
		<%-- 			<c:forEach items="${optStuffs}" var="stuffname">
						<tr name="filetr" class="${stuffname[2]}" align="center">
							<td rowspan='${stuffname[1]}'>${cp:MAPVALUE("FILETYPE",stuffname[2])}</td>
							<td rowspan='${stuffname[1]}'>${stuffname[0]}</td>
							<c:forEach items="${vOptStuffs }" var="optStuff" varStatus="status">
								<c:if test="${stuffname[0] eq optStuff.stuffname}">
									<script type="text/javascript">
										var id = '${optStuff.filetype}';
										if (id != '')
											document.getElementById(id).style.display = "";
									</script>

									<td>
										<c:if test="${optStuff.iszhi eq '1'}">${optStuff.filename}</c:if>
										<c:if test="${optStuff.iszhi ne '1'}">
											<a style="text-decoration: none; color: blue;"
												href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${optStuff.stuffid}">${optStuff.filename}</a>
										</c:if>
									</td>
									<td>${optStuff.nodename}</td>
									<td>${cp:MAPVALUE("usercode",optStuff.uploadusercode)}</td>
									<c:if test="${stuffname[1]>1}">
										</tr>
										<tr name="filetr" class="${stuffname[2]}" align="center">
									</c:if>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach> --%>
	<!--不是收发文材料展示  -->
					<c:if test="${empty vStuffnames1 }">
					<c:forEach var="stuff" items="${optStuffs}">
							<script type="text/javascript">
								var id = '${stuff.filetype}';
								if (id != '')
									document.getElementById(id).style.display = "";
							</script>
							<tr name="filetr" class="${stuff.filetype}" align="center">
								<td>${cp:MAPVALUE("FILETYPE",stuff.filetype)}</td>
								<td>${stuff.stuffname}</td>
								<td><c:if test="${stuff.iszhi=='1'}">${stuff.filename}</c:if>
									<c:if test="${stuff.iszhi !='1'}">
										<a style="text-decoration: none; color: blue;"
											href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${stuff.stuffid}">${stuff.filename}</a>
									</c:if></td>
								<td>${stuff.nodename}</td>
								<td>${cp:MAPVALUE("usercode",stuff.uploadusercode)}</td>
							</tr>
						</c:forEach>
                      </c:if>
					 <c:forEach var="stuff" items="${vStuffnames1}">
						<script type="text/javascript">
							var id = '${stuff.filetype}';
							if (id != '')
								document.getElementById(id).style.display = "";
						</script>
						
						<tr name="filetr" class="${stuff.filetype}" align="center">
							<td>${cp:MAPVALUE("FILETYPE",stuff.filetype)}</td>
							<td>${stuff.stuffname} 
								<c:if test="${not empty stuff.archivetype and 'fj' ne stuff.archivetype and 'nwfj' ne stuff.archivetype}">
									${cp:MAPEXPRESSION("TEMPLATE_TYPE",stuff.archivetype)}
								</c:if>
							</td>
							<td>
								<c:if test="${stuff.iszhi eq '1'}">${stuff.filename}</c:if>
								<c:if test="${stuff.iszhi  ne '1'}">
									<a style="text-decoration: none; color: blue;"
										href="<%=request.getContextPath()%>/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${stuff.stuffid}">${stuff.filename}</a>
								</c:if>
							</td>
							<td>${stuff.nodename}</td>
							<td>${cp:MAPVALUE("usercode",stuff.uploadusercode)}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>