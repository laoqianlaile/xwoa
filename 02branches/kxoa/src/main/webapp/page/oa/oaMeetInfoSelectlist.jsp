<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaMeetinfo.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search" >
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="oaMeetinfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd"><s:text name="oaMeetinfo.meetingName" />:</td>
					<td><s:textfield name="s_meetingName" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaMeetinfo.meetingType" />:</td>
					<td><select name="s_meetingType" id="s_meetingType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${oaCommonTypeList}">
								<option value="${row.no}"
									<c:if test="${s_meetingType eq row.no }">selected="selected"</c:if>>
									<c:out value="${row.comName}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><s:submit method="selectList" key="opt.btn.query" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaMeetinfo!selectList.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<input type="hidden" id="djid${sa.djid}" name="djid${sa.djid}"
			value="${sa.djid}">
		<input type="hidden" id="meetingName${sa.djid}" name="meetingName${sa.djid}"
			value="${sa.meetingName}">
		<input type="hidden" id="meetingAddress${sa.djid}" name="meetingAddress${sa.djid}"
			value="${sa.meetingAddress}">	
		

		<ec:row>

			<ec:column property="djid" title="序号" style="text-align:center" />


			<ec:column property="coding" title="编号" style="text-align:center" />
			<ec:column property="meetingName" title="会议室名称"
				style="text-align:left；"/>
			<ec:column property="meetingType" title="会议室类型"
				style="text-align:center"> 
				
 				${sa.oaCommonType.comName} 
              </ec:column> 

			<ec:column property="meetingNumber" title="容纳人数"
				style="text-align:left；">
			</ec:column>			
			<ec:column property="meetingUseing" title="主要用途"
				style="text-align:center" />
			<ec:column property="meetingAddress" title="地点"
				style="text-align:center" />
			<ec:column property="meetingInfloor" title="所在楼层"
				style="text-align:center" />
			<ec:column property="remark" title="备注" style="text-align:center"
				sortable="false">
				<c:choose>
					<c:when test="${fn:length(sa.remark) gt 10}">
						<c:out value="${fn:substring(sa.remark, 0, 10)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${sa.remark}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${sa.djid}')">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	//获取父页面对象
	var parentDocument = window.opener.document;
	//设置返回值
	function setParentVal(no) {

		if (window.confirm("确认选择吗")) {

			// 		parentDocument.getElementById('djid').value = no;
// 			parentDocument.getElementById('meetid').value = document
// 					.getElementById('meetingName' + no).value;
			parentDocument.getElementById('address').value = document
			.getElementById('meetingName' + no).value;

			window.close();
		}
	}

	/*****************业务数据页面跳转end*********/
</script>
</html>
