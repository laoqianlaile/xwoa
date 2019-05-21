<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			格式文书案号管理
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				 查询条件
			</legend>
			
			<s:form action="optWritdef" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td  class="addTd">模版分类:</td>
						<td>
						<select name="s_temptype" id="temptypes" style="width:180px;">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
							<option value="${row.key}"
								<c:if test="${param.s_temptype eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
						</select>	
						</td>
						<td  class="addTd">文号模板:</td>
						<td><s:textfield name="s_writcode" value="%{#parameters['s_writcode']}"  style="width:180px;height:25px;"/> </td>				
					</tr>	

					<tr >
						<td  class="addTd">文号初始值:</td>
						<td><s:textfield name="s_initvalue" value="%{#parameters['s_initvalue']}"  style="width:180px;height:25px;"/> </td>
					</tr>
					
					<tr class="searchButton">
						<td  colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
							<s:submit method="edit"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>	

<!-- 					<tr  class="searchButton">			 -->
<!-- 						<td colspan="4"> -->
<%-- 							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/> --%>
<%-- 							<s:submit method="edit"  key="opt.btn.new" cssClass="btn"/> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/optWritdef!list.do" items="objList" var="optWritdef"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="temptype" title="模版分类" style="text-align:center" >
				${cp:MAPVALUE("ITEM_TYPE",optWritdef.temptype)}
				</ec:column>		
				<ec:column property="writcode" title="文号模板" style="text-align:center" />
				<ec:column property="initvalue" title="文号初始值" style="text-align:center" />
				<ec:column property="isinuse" title="是否在用" style="text-align:center" >
				${cp:MAPVALUE("TrueOrFalse",optWritdef.isinuse)}
				</ec:column>

			
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/optWritdef!edit.do?writid=${optWritdef.writid}'>编辑</a>
					<a href='powerruntime/optWritdef!delete.do?writid=${optWritdef.writid}' 
							onclick='return confirm("${deletecofirm}optWritdef?");'>删除</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>	
</html>
