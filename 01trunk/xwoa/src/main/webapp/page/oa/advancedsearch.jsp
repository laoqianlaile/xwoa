<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
		<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	
		<title>
			高级搜索
		</title>
	</head>
	<body>
	<fieldset    class="search">
		<s:form id="equipmentInfoForm" namespace="/oa"
			action="advsearch" style="margin-top:0;margin-bottom:5"
		 	method="post">
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd" align="right">业务类别:</td>
					<td align="right" style="width: 50px">
						<select style="width: 80px" id="optval" name="optval">
							<option value="0" <c:if test="${optval eq 0}">selected="selected"</c:if>>全部</option>
							<option value="1" <c:if test="${optval eq 1}">selected="selected"</c:if>>办件</option>
							<option value="2" <c:if test="${optval eq 2}">selected="selected"</c:if>>附件</option>
							<option value="3" <c:if test="${optval eq 3}">selected="selected"</c:if>>邮件</option>
							<option value="4" <c:if test="${optval eq 4}">selected="selected"</c:if>>资讯</option>
						</select>
					</td>
					<td style="width: 50px" class="addTd">标题:</td>
					<td>
							<c:if test="${not empty title}"> <!-- 条件表达式，如果是从首页点击查询，则获取首页输入框的值填充到input框中 --> 
								<input style="width: 80%;height: 36px;float:left;"  id="title" name="title" value="<%=request.getAttribute("title")%>">
								<s:submit method="goSearchIndex" key="opt.btn.query" cssClass="btn"  style="float:left;height: 36px;line-height:36px;margin-left:1px"/>
							</c:if> 
							<c:if test="${empty title}">  <!-- 如果不是从首页过来，则不要默认值，不加此判断，获取的则是null -->
								<input style="width: 80%;height: 36px;float:left;line-height:36px" id="title" name="title">
								<s:submit method="goSearchIndex" key="opt.btn.query" cssClass="btn"  style="float:left;height: 36px;line-height:36px;margin-left:1px"/>
							</c:if> 
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

		<ec:table action="${pageContext.request.contextPath}/oa/advsearch!goSearchIndex.do" items="objList" var="slist"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			
			<ec:row>
				<ec:column property="itemType" title="办件类型" style="text-align:center" width="15%"
					sortable="true">${cp:MAPVALUE('optTypeName',slist.itemType)}
				</ec:column>
					<ec:column property="title" title="标题" style="text-align:center">
						<a  href="${pageContext.request.contextPath}/${cp:MAPVALUE('optType',fn:substringBefore(slist.djId, '0' ))}!generalOptView.do?djId=${slist.djId}&nodeInstId=0" >
							${slist.title}
						</a>
					</ec:column>
			</ec:row>
		</ec:table>
          
	</body>
	<script type="text/javascript"> 
	</script>
</html>