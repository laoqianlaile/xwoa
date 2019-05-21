<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<%-- <sj:head locale="zh_CN" /> --%>
		<title>
		</title>
	</head>
	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search" >
			<legend>签报查看</legend>
			<s:form action="signedReport" namespace="/dispatchdoc" method="post">
			 <s:hidden name="show_type" value="%{show_type}"/>
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">登记号:</td>
						<td >
						<input type="text" name="s_djId" value="${s_djId }" />
<%-- 						<s:textfield id="s_djId" name="s_djId" value="" style="width:180px" /></td> --%>
						<td class="addTd">项目名称:</td>
						<td >
						<input type="text" name="s_transaffairname" value="${s_transaffairname }" />
<%-- 						<s:textfield id="s_transaffairname" name="s_transaffairname" value="" style="width:180px"/></td> --%>
							</tr>
						<tr>
						
						 <td class="addTd">开始时间:</td>
						<td>
					<input type="text" class="Wdate" 
                    value="${param['s_begTime'] }"name="s_begTime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
					      至
					<input type="text" class="Wdate" 
                    value="${param['s_endTime'] }"name="s_endTime" 
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						</td>
				
						<td><s:submit method="list" key="opt.btn.query" cssClass="btn" />&nbsp;</td>
					
				
					</tr>	
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="dispatchdoc/signedReport!list.do" items="objList" var="incomeDoc"
				imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="djId"  title="登记号" style="text-align:center" />
				<ec:column property="signedReportTitle" title="项目名称" style="text-align:center" />
				<ec:column property="draftDate" title="登记时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
				<ec:column property="bizstate" title="状态" style="text-align:center">
					<c:if test="${incomeDoc.bizstate eq 'F'}">
						未提交
					</c:if>
					<c:if test="${incomeDoc.bizstate eq 'T'}">
						已提交
					</c:if>
					<c:if test="${incomeDoc.bizstate eq 'C'}">
						结束
					</c:if>
				</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">

						 <a href="${contextPath}/dispatchdoc/signedReport!generalOptView.do?djId=${incomeDoc.djId}&nodeInstId=0">查看</a>
						<c:if test="${incomeDoc.bizstate eq 'F' }">
						<a href="${contextPath}/dispatchdoc/signedReport!registerDoOrReadEdit.do?djId=${incomeDoc.djId}">编辑</a>
					
						<a href="<%=request.getContextPath()%>/dispatchdoc/signedReport!delete.do?djId=${incomeDoc.djId}">删除</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
		function replaceUrl(a) {
			var doOptUrl = a.href; 		
			doOptUrl = doOptUrl.replaceAll("amp%3B","");
			a.href=doOptUrl;
			return false;
		}
	</script>
</html>
