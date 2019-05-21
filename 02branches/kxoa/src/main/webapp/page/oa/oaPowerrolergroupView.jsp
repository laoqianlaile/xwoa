<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaPowerrolergroup.view.title" /></title>
<style type="text/css">
.btn_a1{color:#fff !important;text-decoration: none !important;padding:3px 7px 3px 7px;font-size:14px}
.btn_a2{color:#fff !important;text-decoration: none !important;padding:3px 20px 3px 20px;font-size:14px}
</style>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaPowerrolergroup.view.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaPowerrolergroup" method="post" namespace="/oa"
			id="oaPowerrolergroupForm">
		<s:hidden id="s_groupType" name="s_groupType" value="%{s_groupType}"></s:hidden>
		<s:hidden id="no" name="no" value="%{no}"></s:hidden>
		<table width="200" border="0" cellpadding="1" cellspacing="1"
			class="viewTable">

			<%-- <tr>
				<td class="addTd"><s:text name="oaPowerrolergroup.no" /></td>
				<td align="left"><s:property value="%{no}" /></td>
			</tr> --%>


			<tr>
				<td class="addTd"><s:text name="oaPowerrolergroup.groupType" />
				</td>
				<td align="left"> ${cp:MAPVALUE('OAGroupType',groupType)}</td>
			
				<td class="addTd"><s:text name="oaPowerrolergroup.state" /></td>
				<td align="left">${USE_STATE[state]}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaPowerrolergroup.groupName" />
				</td>
				<td align="left" colspan="3"><s:property value="%{groupName}" /></td>
			</tr>

<!-- 			<tr> -->
<%-- 				<td class="addTd"><s:text name="oaPowerrolergroup.remark" /></td> --%>
<%-- 				<td align="left" colspan="3"><s:property value="%{remark}" /></td> --%>
<!-- 			</tr> -->


			<%-- <tr>
				<td class="addTd"><s:text name="oaPowerrolergroup.creater" />
				</td>
				<td align="left"> ${cp:MAPVALUE("usercode",creater)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaPowerrolergroup.creatertime" />
				</td>
				<td align="left"><fmt:formatDate value="${object.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr> --%>
			
			 <tr id="tr_receive" >
					<td class="addTd">分组人员：</td>
					<td align="left" colspan="3">
					<input id="txt_usercode" type="hidden" name="usercodes"
				     value="${usercodes}" />		
					 <textarea id="txa_name" name="userNames" readonly="readonly" style="width: 80%;display: inline;">${userNames}</textarea> 
                    <%-- <input type="button" value="添加人员" class="btn" onclick="location.href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!putInnermsgTree.do'" />
			         <input type="button" value="添加新人员" class="btn btnWide" onclick="location.href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!putAllUserTree.do'" /> --%>
                    <a id="a_href" type="button" class="btn btn_a1"
							href="${pageContext.request.contextPath}/oa/oaPowerrolergroup!putInnermsgTree.do"
							width="400" height=""  target="dialog">添加人员</a>
					<%--  <a id="a_href" type="button" class="btn_a"
							href="${pageContext.request.contextPath}/oa/oaPowerrolergroup!putAllUserTree.do"
							width="400" height=""  target="dialog">添加新人员</a> --%>
					</td>
				</tr>

		</table>
		<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="javascript:window.location.href='oa/oaPowerrolergroup!list.do?s_groupType=${s_groupType}';" value="返回" />
			<s:submit name="save" method="saveDetails" cssClass="btn" key="opt.btn.save" />
		</div>
		</s:form>
		
	</fieldset>

<ec:table action="oa/oaPowerrolergroup!view.do" items="oaPowergroupDetails" var="detale"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				
				<ec:column property="usercode" title="用户名" style="text-align:center" >
				${cp:MAPVALUE("usercode",detale.usercode)}
				</ec:column>

				
				<ec:column property="depid" title="主部门" style="text-align:center" >
				${cp:MAPVALUE("unitcode",detale.depid)}
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='${pageContext.request.contextPath}/oa/oaPowerrolergroup!deleteDetail.do?id=${detale.id}&no=${detale.no}' 
							onclick='return confirm("确认删除该用户?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>
