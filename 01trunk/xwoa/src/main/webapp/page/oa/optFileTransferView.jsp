﻿<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <title></title>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>					
</head>
<body class="sub-flow">
<fieldset class="form">

		<legend>
			<p>文件传输</p>
		</legend>

	<s:form>
   
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		   <tr>
			    <td class="addTd">标题</td> 
				<td align="left">
				  ${object.subject}
				</td>
			</tr>
			<tr>
			    <td class="addTd">发送人</td> 
				<td align="left">
				   ${object.senderName}
				</td>
			</tr>
			<tr>
			    <td class="addTd">收件人</td> 
				<td align="left">
				   ${object.receiverName}
				</td>
			</tr>
			<tr>
			<td class="addTd">文件</td>
			<td align="left">
				 <c:forEach var="fileItem" items="${fileList}">
				    <div><a href="${ctx}/oa/optFilingCabinets!downloadAffix.do?id=${fileItem.id}"><span>${fileItem.fileName}</span><span>(${fileItem.fmtFileSize})</span></a></div>
				 </c:forEach>
			</td> 
			</tr>
			<tr>
			    <td class="addTd">备注</td> 
				<td align="left">
				  ${object.remark}
				</td>
			</tr>
		</table>
		
	 <div align="center">
				<c:if test="${ empty show_type }">
				<input type="button" name="back" Class="btn" onclick="history.go(-1)" value="返回" />
				</c:if>
				<c:if test="${not empty show_type and show_type eq 'art'}">
				<input class="btn" id="back" type="button"  value="返回" onclick="doback(-1)"/>
				</c:if>   
		
	 </div>
	 </s:form>
</fieldset>
</body>
<script type="text/javascript">
	function doback(type){	
		var win = art.dialog.open.origin;//来源页面
			if (type=-1){
				if(win){
					// 如果父页面重载或者关闭其子对话框全部会关闭
		       		art.dialog.close();
				}
				
			}else{
				window.location="${contextPath}/oa/oaSchedule!tabList.do?s_sehType=1&s_canAdd=T";
				}
			}
	</script>	
</html>
