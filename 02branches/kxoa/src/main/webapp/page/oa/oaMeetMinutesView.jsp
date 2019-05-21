
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>会议室查看</title>
</head>

<body>
<%@ include file="/page/common/messages.jsp"%>
<%-- <p class="ctitle">
		<s:text name="oaMeetMinutes.view.title" />
	</p> --%>
	<s:form action="oaMeetMinutes"  method="post" namespace="/oa" id="oaMeetMinutesForm" data-validate="true" enctype="multipart/form-data">
<a href='oa/oaMeetMinutes!list.do'>
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />
</a>
 </s:form>
<p>

	<fieldset class="_new">
<legend><b>会议纪要信息</b></legend>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  
				<tr>
					<td class="addTd">
						会议室申请ID
					</td>
					<td align="left" >
					${oaMeetMinutes.djid} 
					<%-- 	<s:property value="%{djid}" /> --%>
					</td>
					<td class="addTd">
						版本
					</td>
					<td align="left" >
					${oaMeetMinutes.version} 
				<%-- 		<s:property value="%{version}" /> --%>
					</td>
					</tr>
					<tr>
					
			
					<td class="addTd">
						会议主题
					</td>
					<td align="left" colspan="3">
				<%-- 		<s:property value="%{title}" /> --%>
							${oaMeetMinutes.title} 
					</td>
					
				</tr>	
				<tr>
					<td class="addTd">
						参会人员
					</td>
					<td align="left" colspan="3">
						${oaMeetMinutes.meetingPersions} 
						<%-- <s:property value="%{meetingPersions}" /> --%>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						抄送人员
					</td>
					<td align="left" colspan="3">
						${oaMeetMinutes.ccPersonnel} 
					<%-- 	<s:property value="%{ccPersonnel}" /> --%>
					</td>
				</tr>
				
				<td class="addTd">
						会议室
					</td>
					<td align="left">
						${oaMeetMinutes.oaMeetinfo.meetingName} 
						<%-- <s:property value="%{meetingNo}" /> --%>
					</td>
					<td class="addTd">
					主持人
					</td>
					<td align="left">
						${oaMeetMinutes.meetingHost} 
					<%-- 	<s:property value="%{meetingHost}" /> --%>
					</td>
				</tr>	
           
				<tr>
					<td class="addTd">
						开始时间
					</td>
					<td align="left">
					<fmt:formatDate value='${oaMeetMinutes.begTime}' pattern='yyyy-MM-dd HH:mm ' />
					<%-- 	<s:date name="begTime" format="yyyy-MM-dd" /> --%>
					
					</td>
					<td class="addTd">
						结束时间
					</td>
					<td align="left">
					<fmt:formatDate value='${oaMeetMinutes.endTime}' pattern='yyyy-MM-dd HH:mm' />
					<%-- 	<s:date name="endTime" format="yyyy-MM-dd" /> --%>
					
					</td>
				</tr>
				

				<tr>
					<td class="addTd">
						 使用部门
					</td>
					<td align="left" >
						${oaMeetMinutes.doDepno} 
				<%-- 	 <s:property value="%{doDepno}" /> --%>
					</td>
	             	<td class="addTd">
					使用人
					</td>
					<td align="left" >
						${oaMeetMinutes.doCreater} 
				<%-- 	<s:property value="%{doCreater}" /> --%>
					</td>
	
				</tr>	
				<tr>
					<td class="addTd">
						正文名称
					</td>
					<td align="left" >
					<c:if test="${not empty oaMeetMinutes.docFileName}">
					<a href='oaMeetMinutes!downStuffInfo.do?djid=${oaMeetMinutes.djid}&version=${oaMeetMinutes.version}'>
					${oaMeetMinutes.docFileName} </a>
					</c:if>
					<a href='oaMeetMinutes!downStuffInfo.do?djid=${djid}&version=${version}'>
					<%--  <s:property value="%{docFileName}" /> --%>
					 
					 </a>
					</td>
	             		<td class="addTd">
						最后更新时间
					</td>
					<td align="left">
					<fmt:formatDate value='${oaMeetMinutes.motifyTime}' pattern='yyyy-MM-dd HH:mm' />
						<%-- <s:date name="motifyTime" format="yyyy-MM-dd" /> --%>
					
					</td>
	
				</tr>	
            <tr>
				<td class="addTd">
					备注
					</td>
					<td align="left" colspan="3">
					${oaMeetMinutes.remark} 
			<%-- 		<s:property value="%{remark}" /> --%>
					</td>
				
				</tr>
</table>

</fieldset>
<fieldset class="_new">
   <legend style="padding:4px 8px 3px;"><b>会议纪要历史版本</b></legend>

		<ec:table action="oa/oaMeetMinutes!view.do" items="versionList" var="log" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" >
			<ec:row>
				<ec:column property="version" title="版本号" style="text-align:center" sortable="false" >
						${log.version}
				</ec:column>
				<ec:column property="title" title="会议室主题" style="text-align:center" sortable="false"/>
				<ec:column property="meetingNo" title="会议室" style="text-align:center" sortable="false">
			      ${ log.oaMeetinfo.meetingName }  
				</ec:column>
				
				<ec:column property="docFileName" title="正文名称" style="text-align:center" sortable="false">
			
				<a href='oaMeetMinutes!downStuffInfo.do?djid=${log.djid}&version=${log.version}'>${log.docFileName}</a>
				</ec:column>
					<ec:column property="motifyTime" title="更新时间" style="text-align:center" sortable="false">
					
					<fmt:formatDate value='${oaMeetMinutes.motifyTime}' pattern='yyyy-MM-dd HH:mm' />
					</ec:column>
					
				 <c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">

					<a href='oa/oaMeetMinutes!view.do?s_djid=${log.djid}&version=${log.version}&djid=${log.djid}'>查看</a>
				
				</ec:column> 
			</ec:row>
		</ec:table>

 </fieldset>

</body>
</html>