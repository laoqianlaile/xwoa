<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaDriverInfo.view.title" /></title>
</head>

<body class="sub-flow">
  <fieldset class="form">
		<legend>
		<p class="ctitle">
		<s:text name="oaDriverInfo.view.title" />
	    </p>
		</legend> 
	
	<%@ include file="/page/common/messages.jsp"%>

	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.no" /></td>
			<td align="left"><s:property value="%{no}" /></td>
				<td colspan="2" rowspan="6" align="center">
				<c:choose>
				<c:when test="${not empty photoname }">
					<img id="appicon" src='${contextPath }/oa/oaDriverInfo!getUserImgFromByte.do?no=${no}' alt="头像"  style="width: 250px; height: 150px;"  />
				</c:when>
				<c:otherwise>
					<img id="appicon" style="width: 350px; height: 150px"   />
				</c:otherwise>
				</c:choose>
			</td>		
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.codenum" /></td>
			<td align="left"><s:property value="%{codenum}" /></td>
		</tr>
        <tr>
			<td class="addTd"><s:text name="oaDriverInfo.state" /></td>
			<td align="left">
			${USE_STATE[object.state]} 
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.name" /></td>
			<td align="left"><s:property value="%{name}" /></td>					
		</tr>
         <tr>
			<td class="addTd"><s:text name="oaDriverInfo.sex" /></td>
			<td align="left">${cp:MAPVALUE('sex',object.sex)}</td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.drLicenseNo" />
			</td>
			<td align="left"><s:property value="%{drLicenseNo}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.releaseDate" />
			</td>
			<td align="left"><fmt:formatDate value='${releaseDate}' pattern='yyyy-MM-dd' /></td>
			<td class="addTd"><s:text name="oaDriverInfo.validDate" /></td>
			<td align="left"><fmt:formatDate value='${validDate}' pattern='yyyy-MM-dd' /></td>
		</tr>		

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.publicType" /></td>
			<td align="left">${cp:MAPVALUE('canDriveType',object.publicType)}</td>
				<td class="addTd"><s:text name="oaDriverInfo.beenDriving"/>
			</td>
			<td align="left"><s:property value="%{beenDriving}" /></td>			
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.telephone" /></td>
			<td align="left"><s:property value="%{telephone}" /></td>
			<td class="addTd"><s:text name="oaDriverInfo.mobile" /></td>
			<td align="left"><s:property value="%{mobile}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.birthDate" /></td>
			<td align="left"><fmt:formatDate value='${birthDate}' pattern='yyyy-MM-dd' /></td>
			<td class="addTd"><s:text name="oaDriverInfo.age" /></td>
			<td align="left"><s:property value="%{age}" /></td>
		</tr>	

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.address" /></td>
			<td align="left" colspan="3"><s:property value="%{address}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.depno" /></td>
			<td align="left" colspan="3"><s:property value="%{depno}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.workExperience" />
			</td>
			<td align="left" colspan="3"><s:property value="%{workExperience}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.remark" /></td>
			<td align="left" colspan="3"><s:property value="%{remark}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaDriverInfo.creater" /></td>
			<td align="left">${cp:MAPVALUE('usercode', creater) }</td>
	
			<td class="addTd"><s:text name="oaDriverInfo.creatertime" />
			</td>
			<td align="left"><fmt:formatDate value='${creatertime}' pattern='yyyy-MM-dd' /></td>
		</tr>
	</table>
	<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
	</div>
	</fieldset>
	
</body>
</html>
