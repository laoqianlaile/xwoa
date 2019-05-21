<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaBbsDiscussion.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search" style="width: 97%;">
		<legend>
			<%-- <s:text name="label.list.filter" /> --%>
			${object.oaBbsDashboard.layoutname }版块
		</legend>

		<s:form action="oaBbsDiscussion" namespace="/bbs"
			style="margin-top:0;margin-bottom:5">
			<c:if test="${'T' ne s_isOwner }">
				<input type="hidden" name="s_layoutcode" value="${s_layoutcode }" />
			</c:if>
			<input type="hidden" name="s_discussOrNot" value="${s_discussOrNot }" />
			<input type="hidden" name="s_isOwner" value="${s_isOwner }" />
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">子版块名称：</td>
					<td><input type="text" name="s_sublayouttitle"
						value="${s_sublayouttitle }" /></td>

					<td class="addTd">是否开放：</td>
					<td><select name="s_openType">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('DiscussOpenType')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_openType }">selected="selected" </c:if>>${v.label }</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td class="addTd">是否需要审核：</td>
					<td><select name="s_isneed">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('NeedVerify')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_isneed }">selected="selected" </c:if>>${v.label }</option>
							</c:forEach>
					</select></td>
					<td class="addTd">创建时间：</td>
					<td colspan="3"><input type="text" class="Wdate"
						value="${s_planBeginTimeBegin}" id="s_planBeginTimeBegin"
						name="s_planBeginTimeBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" class="Wdate"
						value="${s_planBeginTimeEnd}" id="s_planBeginTimeEnd"
						name="s_planBeginTimeEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" /></td>
				</tr>

				<tr class="searchButton">
					<c:if test="${'T' eq s_isOwner }">
						<td class="addTd">所属大模块：</td>
						<td style="text-align: left;"><select name="s_layoutcode">
								<option value="">---请选择---</option>
								<c:forEach items="${dashboardList}" var="v">
									<option value="${v.layoutcode }"
										<c:if test="${v.layoutcode eq s_layoutcode}">selected="selected" </c:if>>${v.layoutname }</option>
								</c:forEach>
						</select></td>
					</c:if>
					<td>包含已删除:<input type="checkbox" name="s_includeDel" value="T" <c:if test="${s_includeDel eq 'T'}">checked="checked"</c:if>></td>
					<td colspan="2"><s:submit method="list" key="opt.btn.query"
							cssClass="btn" /> <s:submit method="built" key="opt.btn.new"
							cssClass="btn" /> <input type="button" class="btn" value="返回"
						onclick="window.location.href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!list.do'" />
					</td>
				</tr>

			</table>
		</s:form>
	</fieldset>

	<ec:table action="bbs/oaBbsDiscussion!list.do" items="objList"
		var="oaBbsDiscussion" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
            <!-- 		查询全部时显示大板块信息  开始 -->
            <c:if test="${'0' eq s_layoutcode || empty s_layoutcode}">
			<ec:column property="none" title="模块名称" 
				style="text-align:center" >
				<font class="getDashboardName" 
						 data-layoutcode="${oaBbsDiscussion.layoutcode}" data-layoutno="${oaBbsDiscussion.layoutno}"  id="${oaBbsDiscussion.layoutno}"></font>
			</ec:column>
            </c:if>
            <!-- 		查询全部时显示大板块信息  结束 -->
           
            
			<c:set var="tsublayouttitle">子版块名称 </c:set>
			<ec:column property="sublayouttitle" title="${tsublayouttitle}"
				style="text-align:center" />

			<c:set var="topenType">开放设置</c:set>
			<ec:column property="openType" title="${topenType}"
				style="text-align:center">
				    ${cp:MAPVALUE('DiscussOpenType', oaBbsDiscussion.openType) }
				</ec:column>
			<%-- <ec:column property="createtime" title="创建时间" style="text-align:center" >
				    <fmt:formatDate value='${oaBbsDiscussion.createtime}' pattern='yyyy-MM-dd HH:mm:ss' />
				</ec:column> --%>

			<c:set var="tisneed">审核设置</c:set>
			<ec:column property="isneed" title="${tisneed}"
				style="text-align:center">
				    ${cp:MAPVALUE('NeedVerify', oaBbsDiscussion.isneed) }
				</ec:column>

			<c:set var="tcreater">创建人</c:set>
			<ec:column property="creater" title="${tcreater}"
				style="text-align:center">
				    ${cp:MAPVALUE('usercode', oaBbsDiscussion.creater) }
				</ec:column>

			<c:set var="tsubjectnum">主题数</c:set>
			<ec:column property="subjectnum" title="${tsubjectnum}"
				style="text-align:center" />

			<c:set var="tpostsnum">帖子数</c:set>
			<ec:column property="postsnum" title="${tpostsnum}"
				style="text-align:center" />

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
		
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:if test="${oaBbsDiscussion.state!='D'}">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsTheme!themeAdd.do?layoutno=${oaBbsDiscussion.layoutno}'>发帖</a>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!view.do?layoutno=${oaBbsDiscussion.layoutno}&s_layoutcode=${s_layoutcode }&s_discussOrNot=${s_discussOrNot }&s_isOwner=${s_isOwner }'><s:text
						name="opt.btn.view" /></a>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!edit.do?layoutno=${oaBbsDiscussion.layoutno}&s_layoutcode=${s_layoutcode }&s_discussOrNot=${s_discussOrNot }&s_isOwner=${s_isOwner }'><s:text
						name="opt.btn.edit" /></a>
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!delete.do?layoutno=${oaBbsDiscussion.layoutno}&s_layoutcode=${s_layoutcode }&s_discussOrNot=${s_discussOrNot }&s_isOwner=${s_isOwner }'
					onclick='return confirm("${deletecofirm}?");'><s:text
						name="opt.btn.delete" /></a>
			  </c:if>
			  <c:if test="${oaBbsDiscussion.state=='D'}">
			     <a
					href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!setup.do?layoutno=${oaBbsDiscussion.layoutno}&s_layoutcode=${s_layoutcode }&s_discussOrNot=${s_discussOrNot }&s_isOwner=${s_isOwner }'
					onclick='return confirm("确定启用吗?");'>启用</a>
			  </c:if>
			</ec:column>

		</ec:row>
	</ec:table>


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
