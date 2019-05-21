<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	   <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	   <link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	   <link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
	   <link href="${pageContext.request.contextPath}/themes/blue/style.css" rel="stylesheet" type="text/css" />		
	   <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	   <script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<title>
		   中间层上报
		</title>
	</head>
	<body>
	<div style="margin-top: 25px;">
	      <h2 style="text-align: center;color:red;font-size: 30px;">市总文件上报平台</h2>  
	    </div>
		<fieldset class="search" >
			<legend  class="headTitle">
			查询条件
			</legend>
			<div class="searchDiv">
			<s:form action="optFileTransferSend" namespace="/oa" method="post">
			    <input type="hidden" name="sendType" value="1"/>
			    <input type="hidden" name="scopeType" value="SYSIN"/>
			    <div class="searchArea">
				<table style="width: auto;">
					<tr>
					   <td class="searchBtnArea">
						 	<s:submit method="built"  key="上报" cssClass="whiteBtnWide" />
						</td>
					    <td  class="searchBtnArea"><label class="searchCondTitle">标题:</label></td>
						<td class="searchCountArea">
						  <input class="searchCondContent" type="text" name="s_subject" value="${s_subject}" />
						</td>
						<td class="searchBtnArea"><label class="searchCondTitle">发送时间:</label></td>
						<td class="searchCountArea">
						  <input class="searchCondContent" type="text" name="s_beginTime" value="${s_beginTime}"  class="Wdate searchCondContent" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						      至
						  <input class="searchCondContent" type="text" name="s_endTime" value="${s_endTime}"  class="Wdate searchCondContent"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						</td>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>	
<!-- 				        <td class="searchCountArea"> -->
<%-- 				        <s:submit method="listReportToSysin" key="opt.btn.query" cssClass="btn"  /> --%>
<%-- 				        <s:submit method="built"  key="上报" cssClass="btn" /> --%>
<!-- 				        </td> -->
					</tr>
                 </table>
                 </div>
             </s:form>
              </div>
          </fieldset>
         
		<ec:table action="oa/optFileTransferSend!list.do" items="objList" var="optFileTransferSend"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			        <ec:column property="subject" title="标题" style="text-align:center" width="30%">
			          <c:choose>
			            <c:when test="${fn:length(optFileTransferSend.subject) gt 20}">${fn:substring(optFileTransferSend.subject , 0, 20) }...</c:when>
													<c:otherwise>${optFileTransferSend.subject} </c:otherwise>
					   </c:choose>
			        </ec:column>
			        <ec:column property="senderCode" title="发送人" style="text-align:center" width="15%">
			           ${cp:MAPVALUE('usercode',optFileTransferSend.senderCode)}
			        </ec:column>
					<ec:column property="receiverName" title="收件人" style="text-align:center"  width="25%"/>
				    <ec:column property="createTime" title="发送时间" style="text-align:center" width="10%">
				      <fmt:formatDate value="${optFileTransferSend.createTime }"
													pattern="yyyy-MM-dd HH:mm" />
				    </ec:column>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="10%">
						    <a href="${ctx}/oa/optFileTransferSend!view.do?id=${optFileTransferSend.id}">查看</a>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	function sub(){
			$("#optFileTransferSend").attr("action","optFileTransferSend!listReportToSysin.do");
			$("#optFileTransferSend").submit();
	}
	</script>
</html>