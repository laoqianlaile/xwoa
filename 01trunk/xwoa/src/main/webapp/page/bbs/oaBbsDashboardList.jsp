<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaBbsDashboard.list.title" /></title>
</head>

			
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" style="width:97%;">
			<legend>
				${cp:MAPVALUE('unitcode', bodycode)}论坛模块管理
			</legend>
			
			<s:form action="oaBbsDashboard" namespace="/bbs" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd">版面名称:</td>
					<td><input type="text" name="s_layoutname" value="${s_layoutname }" /></td>
					<td class="addTd">是否公开：</td>
				    <td><select name="s_openType" style="width:200px;height: 30px;">
						    <option value="" >---请选择---</option>
							<c:forEach items="${cp:LVB('DashOpenType')}" var="v">
							    <option value="${v.value }" <c:if test="${v.value eq s_openType }">selected="selected" </c:if>>${v.label }</option>
							</c:forEach> 
						</select></td>
					<td class="addTd" style="width:130px;height: 30px;">是否设置开放时间：</td>	
				    <td align="left">
						    <select id="isdocontral" name="s_isdocontral"  >
						    <option value="">---请选择---</option>
								<option value="F" <c:if test="${s_isdocontral=='F'}">selected="selected"</c:if>>不设置</option>
						        <option value="T" <c:if test="${s_isdocontral=='T'}">selected="selected"</c:if>>设置</option>
						    </select></td>
				</tr>
				<tr>
				    <td class="addTd">创建时间：从</td>
					<td><input type="text" name="s_startCreateTime" value="${s_startCreateTime }"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
					<td class="addTd">到：</td>	
				    <td align="left"><input type="text" name="s_endCreateTime" value="${s_endCreateTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				    <td class="addTd">包含已删除：</td>
				    <td><input type="checkbox" name="s_includeDel" value="T" <c:if test="${s_includeDel eq 'T'}">checked="checked"</c:if>></td>
				</tr>

				<tr class="searchButton">
					<td colspan="6">
						<s:submit method="list" key="opt.btn.query" cssClass="btn" /> 
						<s:submit method="built" key="opt.btn.new" cssClass="btn" />
						<input type=hidden name="bodycode" value="${bodycode }"/>
					</td>
				</tr>

			</table>
								
			</s:form>
		</fieldset>

		<ec:table action="bbs/oaBbsDashboard!list.do" items="objList" var="oaBbsDashboard"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">			
			<ec:row>

			<c:set var="tlayoutcode">版面名称</c:set>
			<ec:column property="layoutcode" title="${tlayoutcode}" style="text-align:center">
				<a href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!view.do?layoutcode=${oaBbsDashboard.layoutcode}&ec_p=${ec_p}&ec_crd=${ec_crd}' title="${oaBbsDashboard.layoutname }">${oaBbsDashboard.layoutname }</a>
			</ec:column>

			<c:set var="tunitcode">所属部门</c:set>	
				<ec:column property="unitcode" title="${tunitcode}" style="text-align:center" >
				${cp:MAPVALUE('unitcode2JC', oaBbsDashboard.unitcode) }
			</ec:column>
			
			<c:set var="topenType">模块类型</c:set>
			<ec:column property="layouttype" title="${topenType}" style="text-align:center" >
			    ${cp:MAPVALUE('LayOutType', oaBbsDashboard.layouttype) }
			</ec:column>
			
			<c:set var="topenType">是否公开</c:set>
			<ec:column property="openType" title="${topenType}" style="text-align:center" >
			    ${cp:MAPVALUE('DashOpenType',oaBbsDashboard.openType)}
			</ec:column>
			
			
			<%-- <c:set var="tcreater">创建人</c:set>
			<ec:column property="creater" title="${tcreater}" style="text-align:center">
				${cp:MAPVALUE('usercode', oaBbsDashboard.creater)}
			</ec:column> --%>

			<c:set var="tcreatetime">创建时间</c:set>
			<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" >
			    <fmt:formatDate value="${oaBbsDashboard.createtime}" pattern="yyyy-MM-dd HH:mm"/>
			</ec:column>

			

			<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:if test="${oaBbsDashboard.state!='D'}">
					
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<c:if test="${'Y' eq oaBbsDashboard.isGranted }">
						<a href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!built.do?s_layoutcode=${oaBbsDashboard.layoutcode}'>创建子版块</a>
					</c:if>
					<a href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!list.do?s_layoutcode=${oaBbsDashboard.layoutcode}'>查看子版块</a>
					<a href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!edit.do?layoutcode=${oaBbsDashboard.layoutcode}'><s:text name="opt.btn.edit" /></a>
					<a href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!delete.do?layoutcode=${oaBbsDashboard.layoutcode}' 
							onclick='return confirm("${deletecofirm}?");'><s:text name="opt.btn.delete" /></a>
				 </c:if>
				 <c:if test="${oaBbsDashboard.state=='D'}">
				    <a href='${pageContext.request.contextPath}/bbs/oaBbsDiscussion!list.do?s_layoutcode=${oaBbsDashboard.layoutcode}'>查看子版块</a>
				    <a href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!setup.do?layoutcode=${oaBbsDashboard.layoutcode}' 
							onclick='return confirm("确定启用吗?");'>启用</a>
				 </c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
