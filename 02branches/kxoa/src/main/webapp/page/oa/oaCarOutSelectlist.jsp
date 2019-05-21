<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaCarinfo.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="oaCarinfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd"><s:text name="oaCarinfo.carno" />:</td>
					<td><s:textfield name="s_carno" /></td>				
				
					<td><s:submit method="outselectList" key="opt.btn.query" cssClass="btn" />
					</td>
				
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaCarinfo!outselectList.do" items="objList" var="sa"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
        <input type="hidden" id="djid${sa.djid}" name="djid${sa.djid}" value="${sa.djid}">
		<input type="hidden" id="carno${sa.djid}" name="carno${sa.djid}" value="${sa.carno}">
        <input type="hidden" id="driver${sa.djid}" name="driver${sa.djid}" value="${sa.oaDriverInfo.name}">
		<input type="hidden" id="drTelephone${sa.djid}" name="drTelephone${sa.djid}" value="${sa.oaDriverInfo.telephone}">
		<input type="hidden" id="brand${sa.djid}" name="brand${sa.djid}" value="${sa.brand}">
		<input type="hidden" id="modelType${sa.djid}" name="modelType${sa.djid}" value="${sa.modelType}">
		
		<ec:row>

		<%-- 	<c:set var="tdjid">
				<s:text name='oaCarinfo.djid' />
			</c:set>
			<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>


			<c:set var="tcarno">
				<s:text name='oaCarinfo.carno' />
			</c:set>
			<ec:column property="carno" title="${tcarno}"
				style="text-align:center" />

			<c:set var="tisuse">
				<s:text name='oaCarinfo.isuse' />
			</c:set>
			<%-- <ec:column property="isuse" title="${tisuse}"
				style="text-align:center" >
			${USE_STATE[sa.isuse]} 	
			</ec:column> --%>

			<c:set var="tbrand">
				<s:text name='oaCarinfo.brand' />
			</c:set>
			<ec:column property="brand" title="${tbrand}"
				style="text-align:center" />

			<ec:column property="modelType" title="车型"
				style="text-align:center" />

			<%-- <c:set var="tcarType">
				<s:text name='oaCarinfo.carType' />
			</c:set>
			<ec:column property="carType" title="${tcarType}"
				style="text-align:center" >
				${sa.oaCommonType.comName }
				</ec:column> --%>
			<ec:column property="oaDriverInfo.telephone" title="联系电话"
				style="text-align:center" />
					
			<c:set var="tdriver">
			<s:text name='oaCarinfo.driver' />
			</c:set>
			<ec:column property="driver" title="${tdriver}"
				style="text-align:center" >
				${sa.oaDriverInfo.name}
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
	    parentDocument.getElementById('carno').value = document.getElementById('carno' + no).value;
		parentDocument.getElementById('cardjid').value = document.getElementById('djid' + no).value;
	    parentDocument.getElementById('driver').value = document.getElementById('driver' + no).value;
		parentDocument.getElementById('drTelephone').value = document.getElementById('drTelephone' + no).value;
		parentDocument.getElementById('brand').value = document.getElementById('brand' + no).value;
	    parentDocument.getElementById('modelType').value = document.getElementById('modelType' + no).value;
	    parentDocument.getElementById('optCarplan').style.display = "block";
	    parentDocument.getElementById('optCarplanbrand').style.display = "block";
	    window.close();
	}
}
	
	/*****************业务数据页面跳转end*********/

</script>
</html>
