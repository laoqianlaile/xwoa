<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<link href="${pageContext.request.contextPath}/styles/default/css/oabbs/bbs.css" rel="stylesheet" type="text/css" />
<html>
<head>
<title>论坛子版块查看</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>

	<fieldset class="_new">
		<legend> 论坛子版块信息查看 </legend>

		<s:form action="oaBbsDiscussion" method="post" namespace="/bbs"
			id="oaBbsDiscussionForm">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="viewTable">

				<tr>
					<td class="addTd">版面名称</td>
					<td align="left" ><font class="getDashboardName" 
						 data-layoutcode="${layoutcode}" data-layoutno="${layoutno}"  id="${layoutno}"></font></td>

					<td class="addTd" rowspan="3">图片
					<td align="left" rowspan="3">
					<%-- <c:choose>
							<c:when test="${not empty showpicturename }">
								<img id="appicon"
									src='${pageContext.request.contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${object.layoutno}'
									alt="头像" style="width: 200px; height: 70px;" align="left" />
							</c:when>
							<c:otherwise>
								<img id="appicon" style="width: 200px; height: 70px"
									align="left" />
							</c:otherwise>
						</c:choose> --%>
						<img id="appicon"
									src='${pageContext.request.contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${object.layoutno}'
									alt="" style="width: 200px; height: 70px;" align="left" />
				 </td>
				</tr>

				<tr>
					<!-- 				<td class="addTd">图片名称</td> -->
					<%-- 				<td align="left">${object.showpicturename} </td> --%>
					<td class="addTd">子版块名称</td>
					<td align="left"><span class="${object.colorOftitle}">${object.sublayouttitle}</span></td>

				</tr>

				<tr>
					<td class="addTd">顺序号</td>
					<td align="left">${object.orderno}</td>
				</tr>
				<tr>
					<td class="addTd">是否公开</td>
					<td align="left">${cp:MAPVALUE('DiscussOpenType', object.openType)}</td>

					<td class="addTd">是否需审核</td>
					<td align="left"><c:if test="${'T' eq object.isneed }">需要 </c:if>
						<c:if test="${'F' eq object.isneed }">不需要 </c:if></td>
				</tr>

				<%-- <tr>
					<td class="addTd">是否设置开放时间</td>
					<td align="left"><c:if test="${'F' eq object.isdocontral }">不设置</c:if>
						<c:if test="${'T' eq object.isdocontral }">设置</c:if>
					</td>
				</tr> --%>
				
				<c:if test="${'T' eq  object.isdocontral}">
				
				<tr>
					<td class="addTd">开放时间</td>
					
					<td align="left" colspan="3">
					<c:if test="${not empty  object.starttime}">	上午
					<fmt:formatDate value="${object.starttime}"
							pattern="HH:mm" />
					</c:if>
					<c:if test="${not empty  object.endtime}">-
					<fmt:formatDate value="${object.endtime}"
							pattern="HH:mm" />
					</c:if>
					<c:if test="${not empty  object.starttimepm}">	/下午
					<fmt:formatDate value="${object.starttimepm}"
							pattern="HH:mm" />
					</c:if>
					<c:if test="${not empty  object.endtimepm}">-
					<fmt:formatDate value="${object.endtimepm}"
							pattern="HH:mm" />	
					</c:if>	
					</td>
				</tr>
				
				</c:if>

				<tr>
					<td class="addTd">创建人</td>
					<td align="left">${cp:MAPVALUE('usercode',object.creater)}</td>

					<td class="addTd">创建时间</td>
					<td align="left"><fmt:formatDate value="${object.createtime}"
							pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>



				<%-- <tr>
				<td class="addTd">审核人</td>
				<td align="left">${cp:MAPVALUE('usercode',object.approval)}</td>
			
				<td class="addTd">审核时间</td>
				<td align="left"><fmt:formatDate value="${object.approvaltime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			
			<tr>
				<td class="addTd">审核结果</td>
				<td align="left" colspan="3">${object.approvalresults}</td>
			</tr> --%>

				<tr>
					<td class="addTd">主题数</td>
					<td align="left">${object.subjectnum}</td>

					<td class="addTd">帖子数</td>
					<td align="left">${object.postsnum}</td>
				</tr>
				<tr>
					<td class="addTd">最后更新时间</td>
					<td align="left"><fmt:formatDate
							value="${object.lastmodifytime}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</table>

			<div class="formButton">
				<input type="button" onclick="history.go(-1);" class="btn"
					value="返回" />
			</div>

		</s:form>
	</fieldset>

</body>
<script type="text/javascript">
// 初始化 显示模块板块名称
$(".getDashboardName").each(function(){
	var layoutcode=$(this).data("layoutcode");
	var layoutno=$(this).data("layoutno");
	$.ajax({
		type:"post",
		url:"${contextPath}/bbs/oaBbsDashboard!getLayoutName.do?layoutcode="+layoutcode,
		dataType:"json",
		success:function(json){
			$("#" + layoutno).html(json.status);
		},
		error:function(){
			alert("数据获取失败，刷新后重试！");
		}
	});
});

</script>
</html>
