<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<%@ page import="java.util.List"%>
<%@ page import="com.centit.support.utils.StringBaseOpt"%>
<html>
<body>
<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<%
			List powerList = (List) request.getAttribute("powerList");
			for(int i = 0; i < powerList.size(); i++){
				Object[] temp = (Object[])powerList.get(i);
		%>
		
		<tr>
			<%
				for(int j = 0; j < temp.length; j++){
			%>
				<%if(j == 0){ %>
				<td class="addTd"><%=temp[j] == null?"": temp[j] %></td>
				<%}if(j == 1){ %>
				<td align="left"><%=temp[j] == null?"":temp[j]%></td>
				<%}if(j > 1){ 
					if(temp[j] != null && !temp[j].equals(temp[j -1 ])){%>
				<td align="left" style="color: red"><%=temp[j] == null?"": temp[j] %></td>
				<%}else{ %>
				<td align="left"><%=temp[j] == null?"": temp[j] %></td>
				<%}} %>
			<%} %>
		</tr>
	
	<%} %>
		
	</table>

</body>

	<script type="text/javascript">

	function openFlow(){
		var url="${pageContext.request.contextPath}/page/workflow/svg/readXml.html";
		window.open(url);
	}
	</script>
</html> 