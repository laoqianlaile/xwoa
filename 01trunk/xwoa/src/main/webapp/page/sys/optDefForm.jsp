<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>操作按钮定义</title>
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
		<s:form action="optDef!save.do" namespace="/sys" id="form1">
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">
						业务代码：
					</td>
					<td align="left">
						<s:textfield name="optid" rows="1" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						操作代码：
					</td>
					
					<td align="left">
					<c:if test="${not empty object.optcode}">
						<s:textfield name="optcode" rows="1" cols="40"  readonly="true" /></c:if>
					<c:if test="${empty optcode}">
						<s:textfield name="optcode" rows="1" cols="40" /></c:if>
					</td>
					
				</tr>
				<tr>
					<td class="addTd">
						操作方法：
					</td>
					<td align="left">
						<s:textfield name="optmethod" rows="1" cols="40" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						方法名称：
					</td>
					<td align="left">
						<s:textfield name="optname" rows="1" cols="40" />
					</td>
				</tr>
				<tr>
				<tr>
					<td class="addTd">
						方法说明：
					</td>
					<td align="left">
						<s:textarea name="optdesc" rows="2" cols="40" />
					</td>
				</tr>
				<tr>
					<td class="addTd" style="width:120px">
						是否为流程操作：
					</td>
					<td align="left">
						<s:radio name="isinworkflow" list="#{'F':'否','T':'是' }" listKey="key" listValue="value" ></s:radio>
					</td>
				</tr>							
			</table>
			<div style="text-align: center">
			   <input type="button" value="保存" Class="btn" 
			      onclick="module.saveForm('form1','${pageContext.request.contextPath}/sys/optDef!save.do','optDefListIframe')" />
			   <input type="button" value="关闭" Class="btn" onclick="DialogUtil.close();" />
			</div>
			
		</s:form>
	</body>
</html>
