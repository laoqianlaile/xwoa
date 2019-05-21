<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaHelpinfo.list.title" />
		</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/css/icon-css.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaHelpinfo" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
<%-- 						<td  class="addTd"><s:text name="oaHelpinfo.djid" />:</td> --%>
<%-- 						<td><s:textfield name="s_djid" value="%{#parameters.s_djid}" /> </td> --%>
						<td class="addTd"><s:text name="oaHelpinfo.columnType" />:</td>
						<td>
							<select name="s_columnType">
							<option value="">---请选择---</option>
			                <c:forEach var="row" items="${cp:DICTIONARY('columntype')}">
			                <option value="${row.datacode}"
			                 <c:if test="${row.datacode eq param['s_columnType']}"> selected="selected"</c:if>>
			                 <c:out value="${row.datavalue}" />
			             </option>
						</c:forEach></select> 
						 </td>
						<td class="addTd"><s:text name="oaHelpinfo.infoName" />:</td>
						<td><s:textfield name="s_infoName"  value="%{#parameters.s_infoName}"/>
						<s:checkbox label="包含禁用主题" name="s_isALL" value="#parameters['s_isALL']" fieldValue="true" />包含禁用主题
						 </td>
					</tr>	
					<tr>
						
						<td class="addTd"><s:text name="oaHelpinfo.remark"  />:</td>
						<td><s:textfield name="s_remark"  value="%{#parameters.s_remark}"/> </td>
						<td  class="addTd"><s:text name="oaHelpinfo.creatertime" />:</td>
						<td> <input type="text" class="Wdate" 
						value="${param['s_begCreatertime']}"name="s_begCreatertime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">至
						<input type="text" class="Wdate" 
						value="${param['s_endCreatertime']}"name="s_endCreatertime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						</td>
					</tr>	
<!-- 					<tr> -->
						
<%-- 							<td class="addTd"><s:text name="oaHelpinfo.fileDocname" />:</td> --%>
<%-- 						<td><s:textfield name="s_fileDocname"   value="%{#parameters.s_fileDocname}"/> </td> --%>
<!-- 					</tr>	 -->
<!-- 					<tr> -->
<%-- 						<td class="addTd"><s:text name="oaHelpinfo.state" />:</td> --%>
<!-- 						<td> -->
<!-- 							<select name="s_state" class="combox"> -->
<!-- 				                <option value="T"  -->
<%-- 				                <c:if test="${empty  param['s_state'] || param['s_state'] eq 'T'}"> selected="selected"</c:if>> --%>
<!-- 				                	启用</option> -->
<%-- 				                 <option value="F" <c:if test="${ param['s_state'] eq 'F'}" > selected="selected" </c:if>>禁用</option> --%>
<!-- 							</select>  -->
<!-- 						</td> -->
<%-- 						<td class="addTd"><s:text name="oaHelpinfo.isallowcomment" />:</td> --%>
<!-- 						<td>     -->
<!-- 						<select name="s_isallowcomment" class="combox"> -->
<%-- 			                <c:forEach var="row" items="${cp:DICTIONARY('OAISAllowComment')}"> --%>
<%-- 			                <option value="${row.datacode}" --%>
<%-- 			                 <c:if test="${row.datacode eq param['s_isallowcomment']}"> selected="selected"</c:if>> --%>
<%-- 			                 <c:out value="${row.datavalue}" /> --%>
<!-- 			             </option> -->
<%-- 						</c:forEach></select>  --%>
<!-- 						 </td> -->
<!-- 					</tr>	 -->
					<tr class="searchButton">		
						<td colspan="4">
							<s:submit method="list4Manager"  key="opt.btn.query" cssClass="btn"/>
<%-- 							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/> --%>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaHelpinfo!list4Manager.do" items="objList" var="oaHelpinfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

<%-- 				<c:set var="tdjid"><s:text name='oaHelpinfo.djid' /></c:set>	 --%>
<%-- 				<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>


				<c:set var="tcolumnType"><s:text name='oaHelpinfo.columnType' /></c:set>	
				<ec:column property="columnType" title="${tcolumnType}" style="text-align:center" >
				${cp:MAPVALUE('columntype',oaHelpinfo.columnType)}
				</ec:column>
				<c:set var="tinfoName"><s:text name='oaHelpinfo.infoName' /></c:set>	
				<ec:column property="infoName" title="${tinfoName}" style="text-align:center" >
						<c:if test="${oaHelpinfo.isgood eq '1' }">
			  			 <i class="icon icon-color icon-star-on" style="margin-left:15px;" title="精华帖"></i>
			  			</c:if>
			  			<c:if test="${oaHelpinfo.isgood eq '0' }">
			  			 <i class="icon icon-color icon-star-off" style="margin-left:15px;" title="普通帖"></i>
			  			</c:if>
			  			<!-- 已删除的帮助帖子名称为红色 -->
			  		<a href='oa/oaHelpinfo!view.do?djid=${oaHelpinfo.djid}&backcolumnType=mgr&ec_p=${ec_p}&ec_crd=${ec_crd}' style="text-decoration: none;">	
			  		<c:choose>
			  			<c:when test="${oaHelpinfo.state eq 'F' }">
			  					<font color='red'>
			  					<!--截取帮助帖子名称长度 -->			  					
			  					<c:choose>
								<c:when test="${fn:length(oaHelpinfo.infoName) gt 5 }">${fn:substring(oaHelpinfo.infoName , 0, 5) }...</c:when>
								<c:otherwise>${oaHelpinfo.infoName} </c:otherwise>
								</c:choose>
								</font>
			  			</c:when>
			  			<c:otherwise>
			  					<!--截取帮助帖子名称长度 -->
			  					<c:choose>
								<c:when test="${fn:length(oaHelpinfo.infoName) gt 5 }">${fn:substring(oaHelpinfo.infoName , 0, 5) }...</c:when>
								<c:otherwise>${oaHelpinfo.infoName} </c:otherwise>
								</c:choose>
			  			</c:otherwise>
			  		</c:choose>	
			  		</a>
				</ec:column>

				<%-- <c:set var="tremark"><s:text name='oaHelpinfo.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" >
							<c:choose>
								<c:when test="${fn:length(oaHelpinfo.remark) gt 10 }">${fn:substring(oaHelpinfo.remark , 0, 10) }...</c:when>
								<c:otherwise>${oaHelpinfo.remark} </c:otherwise>
							</c:choose>
				</ec:column> --%>

				<c:set var="tcreater"><s:text name='oaHelpinfo.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaHelpinfo.creater) }
				</ec:column>

				<c:set var="tcreatertime"><s:text name='oaHelpinfo.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
					<fmt:formatDate value="${oaHelpinfo.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</ec:column>

				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href='oa/oaHelpinfo!modify.do?djid=${oaHelpinfo.djid }'>编辑</a>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<c:if test="${oaHelpinfo.state ne 'F' }">
					<a href='oa/oaHelpinfo!delete.do?djid=${oaHelpinfo.djid}' 
							onclick='return confirm("${deletecofirm}oaHelpinfo?");'><s:text name="opt.btn.delete" /></a>
					</c:if>
					
					<c:if test="${oaHelpinfo.isgood eq '0' && oaHelpinfo.state ne 'F'  }">
					<a href='oa/oaHelpinfo!setGood.do?djid=${oaHelpinfo.djid}' 
							onclick='return confirm("确定将此置为精华帖?");'>置为精华帖</a>
					</c:if>
					<c:if  test="${oaHelpinfo.isgood eq '1' && oaHelpinfo.state ne 'F' }">
					<a href='oa/oaHelpinfo!setNormal.do?djid=${oaHelpinfo.djid}' 
							onclick='return confirm("确定取消精华帖?");'>取消精华帖</a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
