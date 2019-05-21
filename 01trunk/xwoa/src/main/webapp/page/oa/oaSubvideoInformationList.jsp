<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSubvideoInformation.list.title" /></title>
</head>
<style>
li {
	float: left;
	list-style: none
}

div {
	display: inline
}
</style>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="border: hidden 1px #000000;">
		<legend> 节目信息 </legend>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">节目视频标题</td>
				<td align="left" colspan="3">${oa.title }</td>

			</tr>
			<tr>
				<td class="addTd">节目视频分类</td>
				<td align="left">${cp:MAPVALUE("videoType",oa.infoType )}</td>
				<td class="addTd">节目视频类别</td>
				<td align="left"><c:if test="${oa.videoType  eq 'D' }">电影</c:if>
					<c:if test="${oa.videoType  eq 'L' }">连载</c:if></td>
			</tr>

			<tr>
				<td class="addTd">节目视频标签</td>
				<td colspan="3">${oa.bookmark }</td>

			</tr>
			<tr>
				<td class="addTd">视频节目图片</td>
				<td colspan="3"><a style="cursor:pointer;"
					onclick="window.open('${contextPath}/${oa.smallImage}')">查看视频图片</a>
				</td>
				<%-- <td class="addTd">
						视频图片
					</td>
						
					<td	>
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${no}','upload')">&nbsp;
					</td>
				</tr>  --%>
			<tr>
				<td class="addTd">是否可回复</td>
				<td align="left">
					${cp:MAPVALUE("OAISAllowComment",oa.isAllowComment )}</td>

				<td class="addTd">发布日期</td>
				<td align="left"><fmt:formatDate value='${oa.releaseDate}'
						pattern='yyyy-MM-dd' /></td>

			</tr>


		</table>

	</fieldset>
	<div>
		<s:form action="oaSubvideoInformation" namespace="/oa"
			id="oaSubvideoInformationForm" style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="s_no" id="no" value="${no}">
			<input type="button" class="btn" id="save" value="添加视频"
				onclick="add();" />
		</s:form>
	</div>
		<div style="postion:relative;height:100%;width:100%;display:block;margin-top:30px;text-align:center;">
			<c:forEach items="${objList}" var="magazine" varStatus="index">
				<ul style="display: block;marign:auto;">
                   <li style="float:left;width:22%;height:230px;position:relative; overflow:hidden;list-style-type:none;margin-right:20px;">
                      <a style="display:block;position:relative;width:100%;height:200px;font-size:12px;text-decoration:none;">
                        <img style="width: 100%; height: 200px" alt=""
									src='${magazine.smallimage}' />
                      </a>
                       <span style="display:block;text-align:center;width:100%;position:absolute;left:0;top:70%;color:white;">${magazine.title}</span>
                      <span style="display:block;text-align:center;width:100%;">
                      <a style="margin-right:10px;bottom:0px;height:16px;font-size:14px;color:#333;text-decoration:none;"
									href='oa/oaSubvideoInformation!view.do?id=${magazine.id}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
										name="opt.btn.view" /></a>
					  <a style="margin-right:10px;bottom:0px;height:16px;font-size:14px;color:#333;text-decoration:none;"
									href='oa/oaSubvideoInformation!edit.do?id=${magazine.id}'><s:text
										name="opt.btn.edit" /></a>
						<c:if test="${magazine.state=='T' }">
					       <a style="bottom:0px;height:16px;font-size:14px;color:#333;text-decoration:none;"
									href='${ctx}/oa/oaSubvideoInformation!updateState.do?state=F&id=${magazine.id}'
									onclick='return confirm("确定禁用该视频吗?");'><s:text
										name="禁用" /></a>
					      </c:if>
					      <c:if test="${magazine.state=='F' }">
					          <a style="bottom:0px;height:16px;font-size:14px;color:#333;text-decoration:none;"
									href='${ctx}/oa/oaSubvideoInformation!updateState.do?state=T&id=${magazine.id}'
									onclick='return confirm("确定启用该视频吗?");'><s:text
										name="启用" /></a>
					      </c:if>
					   </span>
                   </li>
				</ul>
			  </c:forEach>
		</div>

</body>
</html>
<script type="text/javascript">
	function add(subType) {

		var srForm = document.getElementById("oaSubvideoInformationForm");

		srForm.action = 'oaSubvideoInformation!built.do';

		srForm.submit();

	}
</script>
