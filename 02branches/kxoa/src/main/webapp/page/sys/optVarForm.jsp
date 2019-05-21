<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 


<html>
<head><meta name="decorator" content='${LAYOUT}'/>
	<title>变量信息</title>
	<sj:head/>
	<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
     <script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
     <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script>
        <script>
          var module;//发布出去供全局对象使用
          $(function(){
             module= $.require("menuDependies");
             module.init();
         });
        </script>
</head>
<body class="sub-flow">
<s:form action="optVar" id="form1">
<table width="200" border="0" cellpadding="1" cellspacing="1">
	<tr>
		<s:hidden name="optid" value="%{optid}"></s:hidden>
		<c:if test="${ empty varname}">
		<td class="TDTITLE">变量名称*：</td>
		<td><s:textfield name="varname" /> </td>
		</c:if>
		<c:if test="${not empty varname}">
		<td class="TDTITLE">变量名称×：</td>
		<td><s:textfield name="varname" value="%{varname}" readonly="true" /> </td>
		</c:if>
		
	</tr>
	<tr>
		<td class="TDTITLE">变量描述：</td>
		<td><s:textfield name="vardesc" value="%{vardesc}"  /> </td>
	</tr>
	
	<tr>
		<td class="TDTITLE">变量类型：</td>
		<td>
		
		<s:select name="vartype"  list="#{'N':'数据','S':'字符串','D':'日期' }" value="%{vartype}"></s:select>
		
		</td>
	</tr>
	<tr>
		<td class="TDTITLE">变量初值：</td>
		<td><s:textfield name="defaultvalue" value="%{defaultvalue}" /> </td>
	</tr>
	
</table>
<div style="text-align: center">
	<input type="button" value="保存" Class="btn" 
			      onclick="module.saveForm('form1','${pageContext.request.contextPath}/sys/optVar!save.do','optVarListIframe')" />
	<input type="button" value="关闭" Class="btn" onclick="DialogUtil.close();" />
	</div>
</s:form>
</body>
</html>	