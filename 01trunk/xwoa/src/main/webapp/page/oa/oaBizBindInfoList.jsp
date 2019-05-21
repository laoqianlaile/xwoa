<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="_new">
			<legend>
			<c:choose><c:when test="${optType eq 'S'}">关联来源</c:when><c:when  test="${optType eq 'E'}">关联事项</c:when></c:choose></legend>
		<ec:table action="oa/oaBizBindInfo!listbiz.do" items="objList" var="oaBizBindInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" sortable="false" showPagination="false"  showStatusBar="false" showTitle="false">
			<ec:row>
			    <ec:column property="endDjid12" title="业务编码" style="text-align:center">
			  	<a href="#" onclick="show('${oaBizBindInfo.optBaseInfo.djId }')">${oaBizBindInfo.optBaseInfo.djId }</a>			   
			    </ec:column>
				<ec:column property="startDjid12" title="业务名称" style="text-align:center" >
				${oaBizBindInfo.optBaseInfo.transaffairname }
				</ec:column>
				<ec:column property="nodename" title="环节名称" style="text-align:center" />
				<ec:column property="createtime" title="发起日期" style="text-align:center" >
				<fmt:formatDate value="${oaBizBindInfo.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</ec:column>
				<c:set var="optlabel">
					<s:text name="opt.btn.collection" />
				</c:set>
				<%-- <c:if test="${optType eq 'E' and (nodelete ne '1' or empty nodelete) }">
				<ec:column property="opt" title="${optlabel}" style="text-align:center">
					<a href="${contextPath }/oa/oaBizBindInfo!delete.do?no=${oaBizBindInfo.no}&optType=${optType}&s_startDjid=${s_startDjid}&s_endDjid=${s_endDjid}"
						onclick='return confirm("确认删除该记录?");'> 删除 </a>
				</ec:column>
				</c:if> --%>
			</ec:row>
		</ec:table>
		</fieldset>
	</body>
	<script type="text/javascript">
		function show(pdjid){
			var djid=pdjid;
			if(djid!=undefined&&djid.substring(0, 2)=='SQ' || djid!=undefined&&djid.substring(0, 2)=='QB'){
				var url="powerruntime/optApply!generalOptView.do?djId="+djid+"&nodeInstId=0&s_itemtype=SQ";
				openNewWindow(url,'showForm','');	
			}else if(djid!=undefined&&djid.substring(0, 2)=='FW'){
				var url="dispatchdoc/dispatchDoc!generalOptView.do?djId="+djid+"&nodeInstId=0";
				openNewWindow(url,'showForm','');
			}
		}
	</script>
</html>
