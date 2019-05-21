<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
</head>
<body>
    <s:form action="" method="post" namespace="/powerruntime" id="generalOperatorForm" target="_self" onsubmit="">
    <c:if test="${itemtype eq 'SQ' }">
       <input type="button" name="glnbsq" class="btn btnWide" onclick="oaOpenGLSQ();" value="关联已有事权" />
	   <input type="button" name="fqqdfw" class="btn btnWide" onclick="openFQSQ();" value="发起新事权" />
	 </c:if>
	 <c:if test="${itemtype eq 'FW' }">
       <input type="button" name="glnbsq" class="btn btnWide" onclick="oaOpenGLFW();" value="关联已有发文" />
       <input type="button" name="fqqdfw" class="btn btnWide" onclick="openFQFW();" value="发起新发文" />
	 </c:if>
	  <c:if test="${itemtype eq 'QB' }">
       <input type="button" name="glnbsq" class="btn btnWide" onclick="oaOpenGLQB();" value="关联已有签报" />
	   <input type="button" name="fqqdfw" class="btn btnWide" onclick="openFQQB();" value="发起新签报" />
	 </c:if>
	 
	 
    </s:form>
    <ec:table action="powerruntime/optApply!startEntrance.do" items="vbizBindInfos" var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<ec:row>
			<ec:column property="itemtype" title="业务类别"	style="text-align:center" sortable="false">
				 ${cp:MAPVALUE('optTypeName',suppower.itemtype)}
			</ec:column>
		
		 <ec:column property="transaffairname" title="办件名称" style="text-align:center" sortable="false">
					<input type="hidden" value="${suppower.transaffairname}"/> 
					<c:choose>
					<c:when test="${fn:length(suppower.transaffairname) > 20}">
						<c:out value="${fn:substring(suppower.transaffairname, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.transaffairname}" />
					</c:otherwise>
				</c:choose>
				</ec:column> 
				<c:if test="${itemtype eq 'FW' }">
			
			<ec:column property="dispatchDocNo" title="发文号"	style="text-align:center" sortable="false"/>
			<ec:column property="dispatchUser" title="签发人"	style="text-align:center" sortable="false">
			 ${cp:MAPVALUE('usercode',suppower.dispatchUser)}
			</ec:column>
			<ec:column property="dispatchDate" title="签发时间"	style="text-align:center" sortable="false">
				${dispatchDate }
			</ec:column>
		</c:if> 
		<c:if test="${itemtype ne 'FW' }">
				<ec:column property="createdate" title="登记时间"	style="text-align:center" sortable="false">
				${createdate }
			</ec:column>
			</c:if>
			<ec:column property="bizstate" title="业务状态"	style="text-align:center" sortable="false">
			 ${cp:MAPVALUE('bizType',suppower.bizstate)}
			</ec:column>
			
				
			
		    <ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
			<a href='javascript:timestamp(powerruntime/optApply!startEntrance.do?djId=${djId}&nodeInstId=${nodeInstId }&itemtype=${itemtype})' onclick="deleteCase('${ suppower.djId}')">删除</a>
			</ec:column>  
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
function deleteCase(id){
	 if(confirm("确定删除该记录？")){
         location.href="powerruntim/optApply!delete4tab.do?end_djId="+id+"&djId=${djId}&&itemtype=${itemtype}&&nodeInstId=${nodeInstId}";
     }
}
function oaOpenGLSQ(){
	var url ="<%=request.getContextPath()%>/oa/voaBizBindInfo!oaOpenGLSQ.do?s_itemtype=SQ&djId=${djId}&nodeInstId=${nodeInstId}";
	document.location.href=url;
}
function oaOpenGLFW(){
	var url ="<%=request.getContextPath()%>/oa/voaBizBindInfo!oaOpenGLSQ.do?s_itemtype=FW&djId=${djId}&nodeInstId=${nodeInstId}";
	document.location.href=url;
}
function openFQSQ(){
	var url =timestamp("<%=request.getContextPath()%>/powerbase/supPower!startFQList.do?startDjId=${djId}&nodeId=${nodeId}&s_itemType=SQ&djId=${djId}&nodeInstId=${nodeInstId}");
	document.location.href=url;
}
function openFQFW(){
	var url =timestamp("<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000392&s_itemType=FW&itemtype=FW&optId=IO_DOC&itemId=XJ370000FG-FW-0001&show_type=F&nodeInstId=${nodeInstId}&startDjId=${djId}");
	document.location.href=url;
}
function oaOpenGLQB(){
	var url ="<%=request.getContextPath()%>/oa/voaBizBindInfo!oaOpenGLSQ.do?s_itemtype=QB&djId=${djId}&nodeInstId=${nodeInstId}&s_djId=${djId}";
	document.location.href=url;
}

function openFQQB(){
	var url =timestamp("<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&startDjId=${djId}&&nodeId=${nodeInstId}");
	document.location.href=url;
}
//加时间戳，以解决删除后不刷新当前页面 dk 2015-12-28
function timestamp(url){
      var getTimestamp=new Date().getTime();
     if(url.indexOf("?")>-1){
       url=url+"&timestamp="+getTimestamp
     }else{
       url=url+"?timestamp="+getTimestamp
     }
     return url;
   }

</script>
</html>