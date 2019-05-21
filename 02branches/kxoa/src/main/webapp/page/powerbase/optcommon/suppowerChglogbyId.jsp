<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<%@page import="com.centit.powerbase.po.Suppower"%>
<%@ page import="java.util.List"%>


<html>
	<head>
		<title>
			权力库查看
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
	</head>

	<body>
<%@ include file="/page/common/messages.jsp"%>
	<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
<fieldset>
<legend><b>权力事项变更记录</b></legend>
		<ec:table action="powerbase/suppowerchglog!listVersion.do" items="versionList" var="suppowerchglog" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false">
			<ec:row>
				<ec:column property="version" title="版本号" style="text-align:center" >
						${suppowerchglog.version}
				</ec:column>
				<ec:column property="chgReason" title="变更原因" style="text-align:center" />
				<ec:column property="chgResult" title="变更结果" style="text-align:center" >
					${cp:MAPVALUE("CHG_RESULT",suppowerchglog.chgResult)}
				</ec:column>
				<ec:column property="auditTime" title="最终审核时间" style="text-align:center" />
			</ec:row>
		</ec:table>
</fieldset>
<fieldset style="padding:10px;display:block;margin-bottom:10px;">
   <legend style="padding:4px 8px 3px;"><b>权力事项历史版本</b></legend>
   <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
   	<tr class="b_darkblue">
			<td style="text-align:center">版本号</td>
			<td style="text-align:center">权力编码</td>
			<td style="text-align:center">权力名称</td>
			<td style="text-align:center">启用时间</td>
			<td style="text-align:center">停用时间</td>
		</tr>
   <c:forEach items="${suppowerList }" varStatus="i" var="suppowerInfo" >
		
	    	 <tr class="b_gray">
	   <td style="text-align:center"><a href='suppowerchglog!listVersion.do?itemId=${ suppowerInfo.itemId}&version=${ suppowerInfo.version}'>
					${suppowerInfo.version }</a></td>
	   <td style="text-align:center">${suppowerInfo.itemId }</td>
	   <td style="text-align:center">${suppowerInfo.itemName }</td>
	   <td style="text-align:center">${suppowerInfo.beginTime }</td>
	   <td style="text-align:center">${suppowerInfo.endTime }</td>
	    </tr>
	    </c:forEach>
       <%--   <iframe id="itemFrame" src="../powerbase/supPower!SuppowerListOld.do?itemId=${suppowerchglog.itemId}&version=${suppowerchglog.version}" frameborder="0" width="100%" scrolling="no"
            onload="this.height=window.frames['itemFrame'].document.body.scrollHeight">
         </iframe> --%>
         </table>
 </fieldset>
<fieldset>
<legend><b>权力事项详细信息</b></legend>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd"><s:text name="suppower.itemId" /></td>
				<td align="left"><s:property value="%{itemId}" /></td>
		
				<td class="addTd">版本号</td>
				<td align="left"><s:property value="%{version}" /></td>
			</tr>
			<tr>
				<td class="addTd">启用时间</td>
				<td align="left"><fmt:formatDate value='${sup.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
		
				<td class="addTd">本版本停用时间</td>
				<td align="left"><fmt:formatDate value='${sup.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.itemName" /></td>
				<td align="left" colspan="3">${sup.itemName}</td>
			</tr>

			

			<tr>
				<td class="addTd"><s:text name="suppower.itemType" /></td>
				<td align="left">
					${cp:MAPVALUE("ITEM_TYPE",sup.itemType)}
				</td>
				<td class="addTd">行使部门</td>
				<td align="left">${cp:MAPVALUE("depno",sup.orgId)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.timeLimit" /></td>
				<td align="left">${sup.timeLimit}
					${cp:MAPVALUE("Promise_Type",sup.promiseType)}</td>
		
				<td class="addTd"><s:text name="suppower.legalTimeLimit" /></td>
				<td align="left">${sup.legalTimeLimit}
					${cp:MAPVALUE("Anticipate_Type",sup.anticipateType)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.isNetwork" /></td>
				<td align="left">
				<c:if
						test='${ sup.isNetwork==\"1\"}'>否</c:if> <c:if
						test='${ sup.isNetwork==\"0\"}'>是</c:if>
				</td>
			
				<td class="addTd"><s:text name="suppower.isFormula" /></td>
				<td align="left">
					 <c:if
						test='${ sup.isFormula==\"0\"}'>否</c:if> <c:if
						test='${ sup.isFormula==\"1\"}'>是</c:if>
				</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.ischarge" /></td>
				<td align="left" colspan="3">
					 <c:if
						test='${ sup.ischarge==\"0\"}'>否</c:if> <c:if
						test='${ sup.ischarge==\"1\"}'>是</c:if>
				</td>
			</tr>
			<c:if test='${ sup.ischarge==\"1\"}'>
				<tr>
					<td class="addTd"><s:text name="suppower.chargeBasis" /></td>
					<td align="left" colspan="3">${sup.chargeBasis}</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="suppower.chargeStandard" /></td>
					<td align="left" colspan="3">${sup.chargeStandard}</td>
				</tr>
			</c:if>
			<tr>
				<td class="addTd"><s:text name="suppower.phone" /></td>
				<td align="left">${sup.phone}</td>
				<td class="addTd"><s:text name="suppower.monitorPhone" /></td>
				<td align="left">${sup.monitorPhone}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.address" /></td>
				<td align="left" colspan="3">${sup.address}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.transactDepname" /></td>
				<td align="left">${sup.transactDepname}</td>
				<td class="addTd"><s:text name="suppower.qlDepid" /></td>
				<td align="left">
					${cp:MAPVALUE("depno",sup.qlDepid)}
				</td>
			</tr>
				<tr>
				<td class="addTd"><s:text name="suppower.qlDepstate" /></td>
				<td align="left">
					${cp:MAPVALUE("QL_DepState",sup.qlDepstate)}
				</td>
			
				<td class="addTd"><s:text name="suppower.entrustName" /></td>
				<td align="left">
					${cp:MAPVALUE("ENTRUST_Name",sup.entrustName)}
				</td>
			</tr>
			<c:if test="${sup.itemType eq 'CF' }">
				
				<tr>
					<td class="addTd">法律依据条文</td>
					<td align="left" colspan="3">${sup.punishbasiscontent}</td>
				</tr>
				<tr>
					<td class="addTd">法律依据</td>
					<td align="left" colspan="3">${punishbasis}</td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.punishClass" /></td>
					<td align="left" colspan="3">${sup.punishClass}</td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="suppower.freeJudge" /></td>
					<td align="left" colspan="3">${sup.freeJudge}</td>
				</tr>
				
				
			</c:if>
			<tr>
					<td class="addTd"><s:text name="suppower.levyUpon" /></td>
					<td align="left" colspan="3">${sup.levyUpon}</td>
				</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.applyForm" /></td>
				<td align="left" colspan="3"><a
					href='supPower!downloadStuff.do?itemId=${sup.itemId}&version=${sup.version}&fileType=applyForm_'>
						${sup.formName}
				</a></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.inFlowImg" /></td>
				<td align="left" colspan="3"><a
					href='supPower!downloadStuff.do?itemId=${sup.itemId}&version=${sup.version}&fileType=inFlowImg_'>
						${sup.inFlowImgName}
				</a></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.inFlowInfo" /></td>
				<td align="left" colspan="3">${sup.inFlowInfo}</td>
			</tr>
			<tr>
					<td class="addTd" width="130"><s:text
							name="suppower.condition" /></td>
					<td align="left" colspan="3">${sup.condition}</td>
				</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.processDesc" /></td>
				<td align="left" colspan="3"><a
					href='supPower!downloadStuff.do?itemId=${sup.itemId}&version=${sup.version}&fileType=processDesc_'>
						${sup.fileName}
				</a></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.srvDirectory" /></td>
				<td align="left" colspan="3">${sup.srvDirectory}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.acceptLink" /></td>
				<td align="left" colspan="3">${sup.acceptLink}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.question" /></td>
				<td align="left" colspan="3">${sup.question}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="suppower.remark" /></td>
				<td align="left" colspan="3">${sup.remark}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.qlState" /></td>
				<td align="left">
					${cp:MAPVALUE("QL_State",sup.qlState)}
				</td>
			
				<td class="addTd"><s:text name="suppower.lastmodifytime" /></td>
				<td align="left"><fmt:formatDate value='${sup.lastmodifytime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
		</table>
		</fieldset>
	</body>
	<script type="text/javascript">
	function selDegree(){
	     var form=document.getElementById("lkmform");
	     form.action="punishobjectbasic!seldegreefacilitydes.do";     
	     form.submit();
	}


	</script>
</html>
