<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaAccidentRecord.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>

		<ec:table action="oa/oaAccidentRecord!list.do" items="objList" var="oaAccidentRecord"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit" sortable="false">

			<ec:row>

				<c:set var="tno"><s:text name='oaAccidentRecord.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />


				<c:set var="tdjid"><s:text name='oaAccidentRecord.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcarno"><s:text name='oaAccidentRecord.cardjid' /></c:set>	
				<ec:column property="cardjid" title="${tcarno}" style="text-align:center" />

				<c:set var="tdepno"><s:text name='oaAccidentRecord.depno' /></c:set>	
				<ec:column property="depno" title="${tdepno}" style="text-align:center" />

				<c:set var="tdouser"><s:text name='oaAccidentRecord.douser' /></c:set>	
				<ec:column property="douser" title="${tdouser}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaAccidentRecord.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" >
				     ${cp:MAPVALUE("usercode",oaAccidentRecord.creater)}
				     </ec:column>
				<c:set var="tcreatertime"><s:text name='oaAccidentRecord.creatertime' /></c:set>	
				<ec:column property="creatertime" title="${tcreatertime}" style="text-align:center" >
								<fmt:formatDate value='${oaAccidentRecord.creatertime}' pattern='yyyy-MM-dd HH:mm' />
				
				</ec:column>

				<c:set var="tphotoName"><s:text name='oaAccidentRecord.photoName' /></c:set>	
				<ec:column property="photoName" title="${tphotoName}" style="text-align:center" >
				
					${fn:length(oaAccidentRecord.photoName) > 20 ?fn:substring(oaAccidentRecord.photoName, 0 , 20):oaAccidentRecord.photoName }
				</ec:column>

			
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaAccidentRecord!view.do?no=${oaAccidentRecord.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}'><s:text name="opt.btn.view" /></a>
						<c:if test="${show_type eq 'T'}">
					<a href='oa/oaAccidentRecord!delete.do?no=${oaAccidentRecord.no}&s_djid=${s_djid}&djid=${s_djid}&show_type=${show_type}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
							</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
