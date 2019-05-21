<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaSupervise.view.title" /></title>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
</head>

<body class="sub-flow">
<%-- <p class="ctitle"><s:text name="oaSupervise.view.title" /></p> --%>

<%@ include file="/page/common/messages.jsp"%>
  <s:hidden name="viewtype" value="%{viewtype}"></s:hidden>
  <c:if test="${not empty viewtype}">

</a>
</c:if>

		<p>	
		
		<div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">
	         督办基本信息
	     </div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
         </div>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
	
  
				<%-- <tr>
					<td class="addTd">
						督办流水号
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
			
					<td class="addTd">
						督办业务流水号
						
					</td>
					<td align="left">
					<a  href="${pageContext.request.contextPath}/${cp:MAPVALUE('optType',fn:substringBefore(supDjid, '0' ))}!generalOptView.do?djId=${supDjid}&nodeInstId=0" >
					<s:property value="%{supDjid}" />
<!-- 					</a> 	   -->
						
					</td>
				</tr> --%>	

	
				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.title" />
					</td>
					<td align="left" colspan="3" >
						 
						
                         <!-- 		查看督办业务信息 -->
                         <c:if test="${not empty superUrl }">
		                 <a href="javascript:void(0);"  title="查看督办业务信息"  onclick="showView('督办业务信息','<%=request.getContextPath()%>/${superUrl}')">
		                 <s:property value="%{title}" /> <img src='${contextPath }/newStatic/image/ck.png' style="width:20px;height:20px;border:0px;" />
		                 </a>
		              
		                </c:if>
		                
		                <c:if test="${ empty superUrl }">
		                <s:property value="%{title}" />
		                </c:if>
		                
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.superviseType" />
					</td>
					<td align="left" colspan="3">
					${cp:MAPVALUE("oa_ITEM_TYPE",superviseType) }
					
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.remark" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						督办时间
					</td>
					<td align="left">
						<s:date name="creatertime" format="yyyy-MM-dd HH:mm"/>
					</td>
					
					<td class="addTd">
						督办部门
					</td>
					<td align="left">
					${cp:MAPVALUE("unitcode",superviseDepno) }
					
					</td>
				
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.creater" />
					</td>
					<td align="left">
						${cp:MAPVALUE("usercode",creater) }
				
					</td>
				
					<td class="addTd">
						<s:text name="oaSupervise.limittime" />
					</td>
					<td align="left">
							<s:date name="limittime" format="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>	
                <tr>
					<td class="addTd">
						<s:text name="oaSupervise.state" />
					</td>
					<td align="left">
						${cp:MAPVALUE("oa_state",state) }
					</td>
				
					<td class="addTd">
						业务状态
					</td>
					<td align="left" >
					
					${cp:MAPVALUE("oa_bizstate",bizstate)}
					</td>						
				</tr>	
				 <c:if test="${not empty advice }"> 
				<tr>
					<td class="addTd">
						意见
					</td>
					<td align="left" colspan="3">
						 <s:property value="%{advice}" />
					</td>
				</tr>
				 </c:if> 
            <c:if test="${not empty solvetime  }">
				<tr>
					<td class="addTd">
						办结时间
					</td>
					<td align="left" colspan="3">
					<s:date name="solvetime" format="yyyy-MM-dd HH:mm" />
						
					</td>
				</tr>
			</c:if>
			 <c:if test="${ not empty solvenote  }">	
				<tr>
					<td class="addTd" >
						办结意见
					</td>
					<td align="left" colspan="3">
						<s:property value="%{solvenote}" />
					</td>
				</tr>
				</c:if>
			  <c:if test="${not empty replayRemark }">		
				<tr>
					<td class="addTd">
						督办回复人
					</td>
					<td align="left">
							${cp:MAPVALUE("usercode",replayUser) }
					</td>
					
					<td class="addTd">
						<s:text name="oaSupervise.replayDate" />
					</td>
					<td align="left">
						<s:date name="replayDate" format="yyyy-MM-dd HH:mm" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.replayDepno" />
					</td>
					<td align="left" colspan="3">
						${cp:MAPVALUE("unitcode",replayDepno) }
						
					</td>
				</tr>	
				
				<tr>
				    <td class="addTd">
						<s:text name="oaSupervise.replayRemark" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{replayRemark}" />
					</td>
				</tr>
</c:if>
				
        <c:if test="${not empty feedbackRemark }">

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.feedbacker" />
					</td>
					<td align="left">
					${cp:MAPVALUE("usercode",feedbacker) }
					</td>
					
					<td class="addTd">
						<s:text name="oaSupervise.feedbackDate" />
					</td>
					<td align="left">
					<s:date name="feedbackDate" format="yyyy-MM-dd HH:mm" />
					
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.feedbackRemark" />
					</td>
					<td align="left" colspan="3">
						<s:property value="%{feedbackRemark}" />
					</td>
				</tr>	
				<tr>
					<td class="addTd">
						<s:text name="oaSupervise.lastmodifytime" />
					</td>
					<td align="left" colspan="3">
						<s:date name="lastmodifytime" format="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>	
</c:if>
		
			
</table>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable div_idea" style="margin-top: 0px; ">
		<%@ include file="/page/common/idea.jsp"%>
</table>

<%--  <c:if test="${not empty superUrl }">
<!-- 		<legend> -->
<!-- 		督办业务信息 -->
<!-- 		</legend> -->
		<div id="infoView">

	<iframe id="tabFrames" name="tabFrames" src="<%=request.getContextPath()%>/${superUrl}" onload="autoHeight(this);"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0"></iframe>
</div>
		</c:if>  --%>

</body>
<script type="text/javascript">
function showView(title,link){
	DialogUtil.open(title,link,1200,700);
}
</script>
</html>
