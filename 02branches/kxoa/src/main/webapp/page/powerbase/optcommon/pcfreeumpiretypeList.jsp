<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
		<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
		<c:out value="pcfreeumpiretype.list.title" /></title>
	</head>

	<body>
	
		<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding:10px;display:block;margin-bottom:10px;">
	<legend style="padding:4px 8px 3px;"><b>自由裁量处罚项目管理</b></legend>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
					

				 <c:if test="${not empty object.degreeno}">	
				  <tr>
						<td class="addTd">违法事实程度</td>
						<td align="left">
                        ${pcfreeumpiredegree.punishfactgrade}
						</td>
					</tr>	
					</c:if>
					
				</table>
		<input type="button" class="btn" value="新增" id="addnew" name = "addnew" onclick="refresh()"/>
 	<c:if test="${not empty object.degreeno}">		
 	
    <iframe id="view" name="view" src="powerbase/pcfreeumpiretype!viewpcfreeumpiretype.do?degreeno=${object.degreeno}&itemId=${suppower.itemId}&version=${suppower.version}&isPcpunishStandard=1"   frameborder="0" width="100%" scrolling="no" 
		 onload="this.height=window.frames['view'].document.body.scrollHeight"></iframe>
 <br><p>
  

	<iframe id="edit" name="edit" src="powerbase/pcfreeumpiretype!edit.do?degreeno=${object.degreeno}&itemId=${suppower.itemId}&version=${suppower.version}&isPcpunishStandard=1"   frameborder="0" width="100%" scrolling="no" 
	         onload="this.height=window.frames['edit'].document.body.scrollHeight"></iframe>
	
	
	</c:if>
	
</fieldset>

</body>
	
	<script type="text/javascript">
	    	
    	function refresh(){
    
    		window.location.reload();
    	}
    </script>	
</html>
