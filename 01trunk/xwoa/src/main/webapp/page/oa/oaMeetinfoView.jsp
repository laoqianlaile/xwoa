<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>会议室信息</title>
</head>
<s:hidden name="viewtype" value="%{viewtype}"></s:hidden>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p class="ctitle">查看会议室信息</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/oaMeetinfo!save.do"
			method="post" id="oaMeetinfoForm" data-validate="true"
			enctype="multipart/form-data">
			<c:if test="${ empty showTag}">
			<div align="left">
			<input type="button" class="btn" target="submit" style="cursor:pointer;" 
						data-form="#oaMeetinfoForm" 
						data-href="${pageContext.request.contextPath}/oa/oaMeetinfo!list.do" 
						value='返回'> 
			<input type="button" class="btn" target="submit" style="cursor:pointer;" 
						data-form="#oaMeetinfoForm" 
						data-href="${pageContext.request.contextPath}/oa/oaMeetinfo!edit.do?djid=${djid}" 
						value='编辑'> 
<!-- 				<input type="button" name="back" Class="btn" -->
<!-- 					onclick="history.back(-1);" value="返回" /> -->
			</div>
			</c:if>	
			<input type="hidden" id="djid" name="djid" value="${djid }" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">编号</td>
					<td align="left">${coding }</td>
					<td class="addTd">名称</td>
					<td align="left" colspan="2">${object.meetingName }</td>
					<td colspan="1" rowspan="4" style="width: 128px; height: 128px"><c:choose>
							<c:when test="${not empty meetinfPictureName }">
								<img id="appicon"
									src='${contextPath }/oa/oaMeetinfo!getUserImgFromByte.do?djid=${djid}'
									alt="" style="width: 128px; height: 128px" />
							</c:when>
							<c:otherwise>
								<img id="appicon" style="width: 100%;"
									src="<%=request.getContextPath()%>/styles/default/images/meetings.jpg" />
							</c:otherwise>
						</c:choose></td>
				</tr>

				<tr>
					<td class="addTd">会议室类型</td>
					<td align="left">${cp:MAPVALUE("meetingType",object.meetingType)}</td>
					<td class="addTd">容纳人数</td>
					<td align="left" colspan="2">${meetingNumber}</td>

				</tr>


				<tr>
					<td class="addTd">地点</td>
					<td align="left" colspan="4">${meetingAddress }</td>
				</tr>
				<tr>
					<td class="addTd">楼层</td>
					<td align="left" colspan="4">${meetingInfloor }</td>

				</tr>
				<tr>
					<td class="addTd">主要用途</td>
					<td align="left" colspan="5">${meetingUseing }</td>
				</tr>
				<tr>
					<td class="addTd">基础配置</td>
					<td align="left" colspan="5">${basicConfiguration }</td>
				</tr>
				<tr>
					<td class="addTd">附属设备</td>
					<td align="left" colspan="5">${accessoryEquipment }</td>
				</tr>
				<tr>
					<td class="addTd">简介</td>
					<td align="left" colspan="5">${remark }</td>
				</tr>


				<tr>
					<td class="addTd">所属机构</td>
					<td align="left">${cp:MAPVALUE('unitcode',depno)}</td>
					<td class="addTd">责任部门</td>
					<td align="left">${cp:MAPVALUE('unitcode',responsibleDep)}</td>
					<td class="addTd">责任人</td>
					<td align="left">${cp:MAPVALUE('usercode',responsiblePersion)}</td>

				</tr>
			</table>
			
		</form>

	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>