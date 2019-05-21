<%@ tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ attribute name="cellText" required="true"%>
<%
if(cellText != null && !"".equals(cellText)){
	char[]chs = cellText.toCharArray();
	for(int i=0;i<chs.length;i++){
		out.write(chs[i]);
		if(i>0 && i%20==0){
			out.write("\r\n");
		}
	}
}
%>

