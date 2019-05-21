<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
</head>
<body scroll="no" style="width:97%;" class="sub-flow">
<fieldset>
  <s:form>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable" >		
  
				<tr>
					<td class="addTd" width="22%">
						会议室
					</td>
					<td align="left" colspan="3">
						${oaMeetApply.oaMeetinfo.meetingName}
					</td>
				</tr>
				<tr>
					<td class="addTd" style="white-space:nowrap;width:13%; text-align: center;">
						申请开始时间
					</td>
					<td align="left" >
						<fmt:formatDate value='${oaMeetApply.planBegTime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
					<td class="addTd"style="white-space:nowrap;width:13%;text-align: center;">
						申请结束时间
					</td>
					<td align="left">
						<fmt:formatDate value='${oaMeetApply.planEndTime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
				</tr>
					<tr>
					<td class="addTd">
						申请人
					</td>
					<td align="left" colspan="1">
						${cp:MAPVALUE('userCode',oaMeetApply.creater)}
					</td>
					<td class="addTd">
						申请部门
					</td>
					<td align="left" colspan="1">
						${cp:MAPVALUE('unitcode',oaMeetApply.depno)}
					</td>
				</tr>
  </table>
  <c:if test="${flag eq true }">
  </br><span>已申请此会议室的记录:</span></br>
  <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
  <tr align="center" style="font-weight:bold;">
     <td>会议室</td>
     <td>会议主题</td>
     <td>分配时间</td>
     <td>申请人</td>
     <td>申请部门</td>
   </tr>
    <c:forEach items="${applylist}" varStatus="i" var="o" end="5">
   <tr align="center">
   <td >${o.oaMeetinfo.meetingName}</td>
    <td>${o.title}</td>
    <td style="color:red;"><fmt:formatDate value='${o.doBegTime}' pattern='yyyy-MM-dd HH:mm:ss' />&nbsp;&nbsp;<fmt:formatDate value='${o.doEndTime}' pattern='yyyy-MM-dd HH:mm:ss' />
    <td>${cp:MAPVALUE('userCode',o.creater)}</td>
    <td>${cp:MAPVALUE('unitcode',o.depno)}</td>
  </tr>
   </c:forEach>
   </table></br>
   <span style="color:red;">此会议室在此时间段已被分配，请考虑其他时间段或更换会议室！！</span>
   </c:if>
   <c:if test="${flag ne true }">
    </br><span style="color:green;font-weight:bold;">此时间段没有分配，可以使用！</span></br>
   </c:if>
 </s:form>
</fieldset>
</body>
</html>