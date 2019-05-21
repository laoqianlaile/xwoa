<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<title>
		   文件接收
		</title>
	</head>
	<body>
		<div style="margin-top: 25px;">
	      <h2 style="text-align: center;color:red;font-size: 30px">市总文件收件平台</h2>  
	    </div>
		<fieldset class="search" >
			<legend class="headTitle">
			文件收件箱查询	
			</legend>
			<div class="searchDiv">
			<s:form action="optFileTransferReceive" id="optFileTransferReceiveform" namespace="/oa" method="post">
			<div class="searchArea">
				<table style="width: auto;">
					<tr>
					    <td class="searchBtnArea"><label class="searchCondTitle">标题:</label></td>
						<td class="searchCountArea">
						  <input class="searchCondContent" type="text" name="s_subject" value="${s_subject}" />
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchBtnArea"><label class="searchCondTitle">发送时间:</label></td>
						<td class="searchCountArea">
						  <input type="text" name="s_beginTime" value="${s_beginTime}"  class="Wdate searchCondContent" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						      <label class="searchCondTitle">至</label>
						  <input type="text" name="s_endTime" value="${s_endTime}"  class="Wdate searchCondContent"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>	
					</tr>
                 </table>
                 </div>
             </s:form>
             </div>
          </fieldset>
		<ec:table action="oa/optFileTransferReceive!list.do" items="objList" var="optFileTransferReceive"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
					<ec:column property="readstate" title="状态" style="text-align:center" width="10%">
						<c:if test="${optFileTransferReceive.readstate eq '已读'}">
					<span class="read" /><span style="color: green;">
					【${optFileTransferReceive.readstate}】</span>
				</c:if>	
				<c:if test="${optFileTransferReceive.readstate eq '未读'}">
					<c:if test="${optFileTransferReceive.newmsg eq '1' }">
					<img style='vertical-align: middle;margin-right: 1px;' src='${pageContext.request.contextPath}/themes/default/improve/new.png' border='0' />
					</c:if>
					<c:if test="${optFileTransferReceive.newmsg eq '0' }">
					<span class="notread" /><span style="color: orange;">
						【${optFileTransferReceive.readstate}】</span>
					</c:if>
				</c:if>
					</ec:column>
			        <ec:column property="subject" title="标题" style="text-align:center" width="30%">
			        	<input type="hidden" value="${optFileTransferReceive.subject}" />
			          <c:choose>
			            <c:when test="${fn:length(optFileTransferReceive.subject) gt 20}">${fn:substring(optFileTransferReceive.subject , 0, 20) }...</c:when>
													<c:otherwise>${optFileTransferReceive.subject} </c:otherwise>
					   </c:choose>
			        </ec:column>
			        <ec:column property="senderCode" title="发送人" style="text-align:center" width="15%">
			           ${cp:MAPVALUE('usercode',optFileTransferReceive.senderCode)}
			        </ec:column>
					<ec:column property="receiverCode" title="收件人" style="text-align:center"  width="25%">
					  ${cp:MAPVALUE('usercode',optFileTransferReceive.receiverCode)}
					</ec:column>
				    <ec:column property="createTime" title="发送时间" style="text-align:center" width="10%">
				      <fmt:formatDate value="${optFileTransferReceive.createTime }"
													pattern="yyyy-MM-dd HH:mm" />
				    </ec:column>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="10%">
						    <a class="check_email" href="${ctx}/oa/optFileTransferReceive!view.do?id=${optFileTransferReceive.id}">查看</a>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	function sub(){
			$("#optFileTransferReceiveform").attr("action","optFileTransferReceive!list.do");
			$("#optFileTransferReceiveform").submit();
	}
	</script>
</html>
