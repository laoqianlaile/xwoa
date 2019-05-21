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
		   市总工会上报
		</title>
	</head>
	<body>
	     <div style="margin-top: 25px;">
	      <h2 style="text-align: center;color:red;font-size: 30px;">市总文件上报平台</h2>  
	      <div style="text-align: center;margin-top:20px;">
	         <input class="btnWide" type="button"  value="上报省总" style="color:#fff;border: none;height:33px;margin-right: 15px" onclick="alert('暂未开通')"/>
	         <input class="btnWide" type="button"  value="上报市委市政府" style="color:#fff;border: none;height:33px;margin:0 15px;" onclick="alert('暂未开通')"/>
	         <input class="btnWide" type="button" value="上报其他部门" style="color:#fff;border: none;height:33px;margin-left: 15px;" onclick="alert('暂未开通')"/>
	      </div> 
	    </div>
		<fieldset class="search" >
			<legend class="headTitle">
			查询上报记录	
			</legend>
			<div class="searchDiv">
			<s:form id="optFileTransferSend" action="optFileTransferSend" namespace="/oa" method="post" onsubmit="return false;"><!-- 暂时不让表单提交 -->
				<div class="searchArea">
				<table style="width: auto;">
					<tr>
					    <td class="searchBtnArea"><label class="searchCondTitle">收件人:</label></td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_receiverName" value="${s_receiverName}" />
						</td>
						<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchBtnArea"><label class="searchCondTitle">发送时间:</label></td>
						<td class="searchCountArea">
						  <input type="text" class="Wdate searchCondContent"  id="s_beginTime" <c:if test="${not empty s_beginTime }"> value="${s_beginTime}" </c:if>
	                            <c:if test="${empty s_beginTime  }">value="${param['s_beginTime'] }"</c:if> name="s_beginTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     <label class="searchCondTitle">至</label>
						        <input type="text" class="Wdate searchCondContent"  id="s_endTime" <c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
	                            <c:if test="${empty s_endTime  }">value="${param['s_endTime'] }"</c:if> name="s_endTime" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						</td>
						<%-- <td><s:submit method="list" key="opt.btn.query" cssClass="btn"  /></td> --%>
						<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>	
					</tr>
					<tr class="searchButton">
				        <td colspan="5">
				       
				        </td>
					</tr>
                 </table>
                 </div>
             </s:form>
             </div>
          </fieldset>
		<ec:table action="oa/optFileTransferSend!list.do" items="objList" var="optFileTransferSend"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			        <ec:column property="senderCode" title="发送人" style="text-align:center" width="15%"/>
					<ec:column property="receiverName" title="收件人" style="text-align:center"  width="25%"/>
					<ec:column property="fileName" title="文件名称" style="text-align:center" width="25%"/>
					<ec:column property="fileSize" title="文件大小" style="text-align:center" width="10%"/>
				    <ec:column property="createTime" title="发送时间" style="text-align:center" width="10%"/>
					<ec:column property="opt" title="操作" sortable="false" style="text-align:center" width="15%">
						    <a class="check_email" href="javascript:void(0);">查看</a>
							<a class="delete_email" href="javascript:void(0);">删除</a>
					</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	function sub(){
			$("#optFileTransferSend").attr("action","optFileTransferSend!listReportToSysout.do");
			$("#optFileTransferSend").submit();
	}
	</script>
</html>
