<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaSurveydetail" namespace="/oa"
		style="margin-top:0;margin-bottom:5">
        <input type="hidden" id="s_djid"  name="s_djid" value="${s_djid}">
	  	<input type="hidden" id="s_itemType" name="s_itemType"  value="${s_itemType}">
    	<input type="hidden" id="s_type"  name="s_type"  value="${s_type }">
	 	<div>
			<span
				style="width: 100%; position: relative; text-align: center; border-bottom: 1px dashed #111; font-size: 20px;"><br>
				<c:if test="${not empty oaSurvey}">${oaSurvey.title }</c:if> </span>
		</div>

		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<s:iterator value="surveyDetailList" status="status" var="oaOnlineItem">
				<tr 
					style="color: #000; font-weight: bold; background-color: #dbf2ff">
					<td>${status.count }、&nbsp;&nbsp;${oaOnlineItem.title }&nbsp;&nbsp;&nbsp;&nbsp;
					 <c:if test="${'T' eq oaOnlineItem.thesign}">
							[<span style="color: red">*</span>该题必填]
					</c:if>
					 <c:if test="${oaOnlineItem.limitnum gt 0}">
							[最多可选${oaOnlineItem.limitnum}项]
					</c:if>
					
					</td>
				</tr>
				<tr>
					<td>
					<span style="color: red">答案：</span>
					${oaOnlineItem.detail}
					</td>
				</tr>
			</s:iterator>
				<tr class="button">
				<td>
	            <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
	            
				</td>
				</tr>
		</table>
	</s:form>
</body>
</html>
