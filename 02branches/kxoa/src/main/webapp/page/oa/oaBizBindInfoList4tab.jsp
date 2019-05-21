<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>	
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<c:if test="${not empty eobjectlist }">
		<fieldset class="_new" id="list_one">
			<legend>关联来源</legend>
		<ec:table action="oa/oaBizBindInfo!listbiz4tab.do" items="eobjectlist" var="oaBizBindInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" sortable="false" showPagination="false"  showStatusBar="false" showTitle="false">
			<ec:row>
			    <ec:column property="endDjid12" title="业务编码" style="text-align:center">
			     
			  	<a href="#" onclick="show('${oaBizBindInfo.optBaseInfo.djId }','${startDjid}','${cp:MAPVALUE('optType',fn:substring(oaBizBindInfo.optBaseInfo.djId, 0, 2))}')">${oaBizBindInfo.optBaseInfo.djId }</a>			   
			    </ec:column>
				<ec:column property="startDjid12" title="业务名称" style="text-align:center" >
				   <input type="hidden" value="${oaBizBindInfo.optBaseInfo.transaffairname}"/>      
			          <c:choose>
						<c:when test="${fn:length(oaBizBindInfo.optBaseInfo.transaffairname) > 16}">
							<c:out
								value="${fn:substring(oaBizBindInfo.optBaseInfo.transaffairname, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaBizBindInfo.optBaseInfo.transaffairname}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="createuser12" title="业务发起人" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaBizBindInfo.optBaseInfo.createuser) }
				</ec:column>
				<ec:column property="nodename" title="环节名称" style="text-align:center" />
				<ec:column property="bizstate12" title="业务状态" style="text-align:center" >
					${cp:MAPVALUE('bizType',oaBizBindInfo.optBaseInfo.bizstate) }					
				</ec:column>
				<ec:column property="createtime" title="发起日期" style="text-align:center" >
				<fmt:formatDate value="${oaBizBindInfo.createtime }" pattern="yyyy-MM-dd HH:mm" />
				</ec:column>
				<c:set var="optlabel">
					<s:text name="opt.btn.collection" />
				</c:set>
			</ec:row>
		</ec:table>
		</fieldset>
		</c:if>
		
		<c:if test="${not empty sobjectlist }">
		<fieldset class="_new" id="list_old">
			<legend>关联事项</legend>
		<ec:table action="oa/oaBizBindInfo!listbiz4tab.do" items="sobjectlist" var="oaBizBindInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" sortable="false" showPagination="false"  showStatusBar="false" showTitle="false">
			<ec:row>
			    <ec:column property="endDjid12" title="业务编码" style="text-align:center">
			    
			  	<a href="javaScript:void(0);" onclick="show('${oaBizBindInfo.optBaseInfo.djId}','${oaBizBindInfo.optBaseInfo.flowInstId}','${startDjid}','${cp:MAPVALUE('optType',fn:substring(oaBizBindInfo.optBaseInfo.djId, 0, 2))}');return false;">${oaBizBindInfo.optBaseInfo.djId }</a>			   
			    </ec:column>
				<ec:column property="startDjid12" title="业务名称" style="text-align:center" >
				  <input type="hidden" value="${oaBizBindInfo.optBaseInfo.transaffairname}"/>      
			          <c:choose>
						<c:when test="${fn:length(oaBizBindInfo.optBaseInfo.transaffairname) > 16}">
							<c:out
								value="${fn:substring(oaBizBindInfo.optBaseInfo.transaffairname, 0, 16)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaBizBindInfo.optBaseInfo.transaffairname}" />
						</c:otherwise>
					</c:choose>
				</ec:column>
				<ec:column property="createuser12" title="业务发起人" style="text-align:center" >
				${cp:MAPVALUE('usercode',oaBizBindInfo.optBaseInfo.createuser) }
				</ec:column>
				<ec:column property="nodename" title="环节名称" style="text-align:center" /> 
				<ec:column property="bizstate12" title="业务状态" style="text-align:center" >
					${cp:MAPVALUE('bizType',oaBizBindInfo.optBaseInfo.bizstate) }
				</ec:column>
				<ec:column property="createtime" title="发起日期" style="text-align:center" >
				<fmt:formatDate value="${oaBizBindInfo.createtime }" pattern="yyyy-MM-dd HH:mm" />
				</ec:column>
				<c:set var="optlabel">
					<s:text name="opt.btn.collection" />
				</c:set>
				<%-- <c:if test="${nodelete ne '1' or empty nodelete }">
				<ec:column property="opt" title="${optlabel}" style="text-align:center">
					<a href="${contextPath }/oa/oaBizBindInfo!delete4tab.do?no=${oaBizBindInfo.no}&djid=${oaBizBindInfo.endDjid}"
						onclick='return confirm("确认删除该记录?");'> 删除 </a>
				</ec:column>
				</c:if> --%>
			</ec:row>
		</ec:table>
		</fieldset>
		</c:if>
	</body>
	<script type="text/javascript">
	 /*    $(function(){
	    	var list_one ='${sobjectlist.size()}';
	    	var list_old ='${eobjectlist.size()}';
	    	alert(parseInt(list_one));
	    	alert(parseInt(list_old));
	    	if(parseInt(list_one)<1){
	    		$("#list_one").css("display","none");
	    	}
	    	if(parseInt(list_old)<1){
	    		$("#list_old").css("display","none");
	    	}
	    }); */
		function show(pdjid,flowInstId,startdjid,menthods){
			var djid=pdjid;
			//alert(menthods);
			//if(djid!=undefined&&djid.substring(0, 2)=='SQ' || djid!=undefined&&djid.substring(0, 2)=='QB'){
				var url="${contextPath }/"+menthods+"!generalOptView.do?djId="+djid+"&flowInstId="+flowInstId+"&nodeInstId=0&s_itemtype="+djid.substring(0, 2)+"&startDjid="+startdjid;
				//openNewWindow(url,'showForm','');	
				//DialogUtil.open(null,url,1200,700);
				//window.showModalDialog(url, "", "dialogWidth=1200px;dialogHeight=700px");
				//DialogUtil.clase();
				window.location.href=url;
			/* }else if(djid!=undefined&&djid.substring(0, 2)=='FW'||djid!=undefined&&djid.substring(0, 2)=='SW'){
				var url="dispatchdoc/dispatchDoc!generalOptView.do?djId="+djid+"&nodeInstId=0"+"&startDjid="+startdjid;
				openNewWindow(url,'showForm','');
			} */
		}
	</script>
</html>
