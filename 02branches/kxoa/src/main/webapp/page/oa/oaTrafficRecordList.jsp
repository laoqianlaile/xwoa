<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaTrafficRecord.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
	

		<ec:table action="oa/oaTrafficRecord!list.do" items="objList" var="oaTrafficRecord"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" sortable="false">

			<ec:row>

				<c:set var="tno"><s:text name='oaTrafficRecord.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tdjid"><s:text name='oaTrafficRecord.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcarno"><s:text name='oaTrafficRecord.cardjid' /></c:set>	
				<ec:column property="cardjid" title="车辆号" style="text-align:center" />


				<c:set var="tdepno"><s:text name='oaTrafficRecord.depno' /></c:set>	
				<ec:column property="depno" title="${tdepno}" style="text-align:center" />

				<c:set var="tdouser"><s:text name='oaTrafficRecord.douser' /></c:set>	
				<ec:column property="douser" title="${tdouser}" style="text-align:center" />

				<c:set var="tdotime"><s:text name='oaTrafficRecord.dotime' /></c:set>	
				<ec:column property="dotime" title="${tdotime}" style="text-align:center" >
			<fmt:formatDate value='${oaTrafficRecord.dotime}' pattern='yyyy-MM-dd HH:mm' />
				</ec:column>


				<c:set var="tcreater"><s:text name='oaTrafficRecord.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				    ${cp:MAPVALUE("usercode",oaTrafficRecord.creater)} 
				</ec:column>

				<c:set var="tcreatertime"><s:text name='oaTrafficRecord.creatertime' /></c:set>	
				<ec:column property="creatertime" title="提交时间" style="text-align:center" >
				<fmt:formatDate value='${oaTrafficRecord.creatertime}' pattern='yyyy-MM-dd HH:mm' />
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaTrafficRecord!view.do?no=${oaTrafficRecord.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}'><s:text name="opt.btn.view" /></a>
					<c:if test="${show_type eq 'T'}">
					 
					<a href='oa/oaTrafficRecord!delete.do?no=${oaTrafficRecord.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
							</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
