<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="optProcAttention.edit.title" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
<p class="ctitle" style="padding:15px 0 20px 0;"><s:text name="optProcAttention.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="optProcAttention"  method="post" namespace="/powerruntime" id="optProcAttentionForm" >
	<s:submit name="save"  method="saveIdeaInfo" cssClass="btn" key="opt.btn.save" />
	<s:submit  name="back" cssClass="btn" key="opt.btn.back"/>
<input type="hidden" id="djId" name="djId"  value="${object.cid.djId}" />
<input type="hidden" id="nodeInstId" name="nodeInstId" value="${object.nodeInstId}" />
<input type="hidden" id="userCode" name="userCode" value="${object.userCode}" />
<fieldset style="display:block;padding:10px;">
<legend>关注意见</legend>
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">		
				<tr>
					<td class="addTd">
						关注意见
					</td>
					<td>
						<s:textarea  style="width:100%;height:60px;" name="attIdea"></s:textarea>
					</td>
				</tr>
</table>
</fieldset>

</s:form>
<fieldset style="display:block;margin-top:10px;">
<legend>办件信息查看</legend>
		<iframe
			src="<%=request.getContextPath()%>/wwd/srPermitApply!generalOptView.do?djId=${djId}"
			width="100%" name="attFrame" frameborder="no" scrolling=no
			border="0" marginwidth="0" marginheight="0"
			onload="this.height=window.frames['attFrame'].document.body.scrollHeight"></iframe>

</fieldset>
