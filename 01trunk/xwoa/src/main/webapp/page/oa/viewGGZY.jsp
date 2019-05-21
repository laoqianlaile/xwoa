<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaInformation.view.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaInformation.view.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>
		<s:hidden name="show_type" value="%{show_type}"></s:hidden>

		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">


			<tr>
				<td class="addTd"><s:text name="oaInformation.infoType" /></td>
				<td align="left">${cp:MAPVALUE('infoType',infoType)}</td>
				<td class="addTd"><s:text name="oaInformation.majorDegree" />
				</td>
				<td align="left">${cp:MAPVALUE('IMP',majorDegree)}</td>
			</tr>




			<tr>
				<td class="addTd"><s:text name="oaInformation.releaseDate" />
				</td>
				<td align="left"><s:date name="releaseDate" format="yyyy-MM-dd" /></td>

				<td class="addTd"><s:text name="oaInformation.validDate" /></td>
				<td align="left"><s:date name="validDate" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="addTd">是否可回复</td>
				<td align="left">${cp:MAPVALUE('OAISAllowComment',isAllowComment)}</td>
				<td class="addTd"><s:text name="oaInformation.state" /></td>
				<td align="left">${cp:MAPVALUE('isStart',state)}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaInformation.publicKey" /></td>
				<td align="left"><s:property value="%{publicKey}" /></td>

				<td class="addTd"><s:text name="oaInformation.docNo" /></td>
				<td align="left"><s:property value="%{docNo}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaInformation.author" /></td>
				<td align="left"><s:property value="%{author}" /></td>

				<td class="addTd"><s:text name="oaInformation.referenceLinks" />
				</td>
				<td align="left"><s:property value="%{referenceLinks}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaInformation.uploadFileName" />
				</td>
				<td align="left" colspan="3"><a href="#"
				onclick="downFile('${object.no}')"
				style="text-decoration: underline"> ${uploadFileName} </a></td>
			</tr>






			<tr>
				<td class="addTd"><s:text name="oaInformation.title" /></td>
				<td align="left" colspan="3"><s:property value="%{title}" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaInformation.bodyContent" />
				</td>
				<td align="left" colspan="3">
					<P>${bodyContent}</P>
				</td>
			</tr>
		<%-- 	<tr>
				<td class="addTd"><s:text name="oaInformation.remark" /></td>
				<td align="left" colspan="3"><s:property value="%{remark}" /></td>
			</tr> --%>
			<tr>
				<td class="addTd"><s:text name="oaInformation.creater" /></td>
				<td align="left"><s:property value="%{creater}" /></td>

				<td class="addTd"><s:text name="oaInformation.creatertime" />
				</td>
				<td align="left"><s:date name="creatertime" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaInformation.lastmodifytime" />
				</td>
				<td align="left"><s:date name="lastmodifytime"
						format="yyyy-MM-dd" /></td>
				<td class="addTd"><s:text name="oaInformation.state" /></td>
				<td align="left">${cp:MAPVALUE('isStart',state)}</td>
			</tr>

		</table>
		<div class="formButton">
			<c:if test="${ empty show_type }">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
			</c:if>
		</div>

	</fieldset>


</body>
<script type="text/javascript">
	function downFile(id) {
		var url = "${ctx}/oa/oaInformation!downLocalStuffInfo.do?no=" + id;
		document.location.href = url;
	}
</script>
</html>
