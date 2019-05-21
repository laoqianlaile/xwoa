<%@ include file="/page/common/taglibs.jsp"%>
<%-- <c:redirect url="/sys/mainFrame!showMain.do" /> --%>

<c:if test='${flage}'>
<%@ include file="/page/rtx/loginRtx.jsp"%>
</c:if>

<meta http-equiv="refresh" content="1; url='<%=request.getContextPath()%>/sys/mainFrame!logincas.do'">

<%-- <c:redirect url="/sysexpand/mainFrameExpand!logincas.do" /> --%>