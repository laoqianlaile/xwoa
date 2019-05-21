<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaCarinfo.view.title" /></title>
<style type="text/css">
	td{height: 30px}
</style>
</head>

<body class="sub-flow">
 <fieldset class="form">
		<legend>
		<s:text name="oaCarinfo.view.title" />
		</legend> 


	<%@ include file="/page/common/messages.jsp"%>
	  <div align="left">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
		</div>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable"><tr>
			<td class="addTd"><s:text name="oaCarinfo.djid" /></td>
			<td align="left"><s:property value="%{djid}" /></td>
        <td rowspan="6" class="addTd">车辆图片</td>
			<td rowspan="6" align="center"><c:choose>
					<c:when test="${not empty carPictureName }">
						<img id="appicon"
							src='${contextPath }/oa/oaCarinfo!getUserImgFromByte.do?djid=${djid}'
							alt="头像" style="width: 250px; height: 100px;" align="left" />
					</c:when>
					<c:otherwise>
						<img id="appicon" style="width: 250px; height: 100px"align="left" src="<%=request.getContextPath()%>/newStatic/image/celiang.png" />
					</c:otherwise>
				</c:choose></td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaCarinfo.carno" /></td>
			<td align="left"><s:property value="%{carno}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.isuse" /></td>
			<td align="left">${USE_STATE[object.isuse]}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.carType" /></td>
			<td align="left">${cp:MAPVALUE('canDriveType',object.carType)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.brand" /></td>
			<td align="left"><s:property value="%{brand}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.modelType" /></td>
			<td align="left"><s:property value="%{modelType}" /></td>
		</tr>



		<tr>
			<td class="addTd"><s:text name="oaCarinfo.ratifyNumber" /></td>
			<td align="left"><s:property value="%{ratifyNumber}" /></td>
			<td class="addTd"><s:text name="oaCarinfo.ratifyLoad" /></td>
			<td align="left"><s:property value="%{ratifyLoad}" /></td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaCarinfo.ratifyOil" /></td>
			<td align="left"><s:property value="%{ratifyOil}" /></td>
			<td class="addTd"><s:text name="oaCarinfo.displacement" /></td>
			<td align="left"><s:property value="%{displacement}" /></td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaCarinfo.oilLabel" /></td>
			<td align="left"><s:property value="%{oilLabel}" /></td>
			<td class="addTd"><s:text name="oaCarinfo.frameNumber" /></td>
			<td align="left"><s:property value="%{frameNumber}" /></td>
		</tr>



		<tr>
			<td class="addTd"><s:text name="oaCarinfo.engineNo" /></td>
			<td align="left"><s:property value="%{engineNo}" /></td>
			<td class="addTd"><s:text name="oaCarinfo.driver" /></td>
			<td align="left">${oaDriverInfo.name}</td>
		</tr>



		<tr>
			<td class="addTd"><s:text name="oaCarinfo.usingNature" /></td>
			<td align="left"><s:property value="%{usingNature}" /></td>
			<td class="addTd"><s:text name="oaCarinfo.depno" /></td>
			<td align="left">${cp:MAPVALUE('unitcode',depno)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.responsibleDep" />
			</td>
			<td align="left">${cp:MAPVALUE('unitcode',responsibleDep)}</td>
			<td class="addTd"><s:text name="oaCarinfo.responsiblePersion" />
			</td>
			<td align="left">${cp:MAPVALUE('usercode',responsiblePersion)}</td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaCarinfo.carItems" /></td>
			<td align="left"  colspan="3"><s:property value="%{carItems}" /></td>
		</tr>

        <tr>
			<td class="addTd">行驶证编号</td>
			<td align="left"  colspan="3"><s:property value="%{driveNo}" /></td>
		</tr>
        <tr>
			<td class="addTd">司机联系方式</td>
			<td align="left"  colspan="3"><s:property value="%{oaDriverInfo.telephone}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="oaCarinfo.remark" /></td>
			<td align="left"  colspan="3"><s:property value="%{remark}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaCarinfo.creater" /></td>
			<td align="left">${cp:MAPVALUE('usercode',creater)}</td>
				<td class="addTd"><s:text name="oaCarinfo.createtime" /></td>
			<td align="left"><s:date name="%{createtime}" format="yyyy-MM-dd HH:mm" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaCarinfo.motifytime" /></td>
			<td align="left"  colspan="3"><s:date name="%{motifytime}" format="yyyy-MM-dd HH:mm" /></td>
		</tr>
	</table>
    

	</fieldset>
	

</body>
</html>
