<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head>
	   <meta name="decorator" content='${LAYOUT}'/>
	   <title>业务模块查看</title>
        <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script>
        <script>
          $(function(){
            var module= $.require("menuDetailForm");
             module.init({ctx:"${pageContext.request.contextPath}",saveFormId:"form1",saveBtnId:"btnSave",superModuleRef:parent.module});
       });
        </script>
	</head>

	<body class="sub-flow">
        <fieldset  class="form">
           <legend style="border-bottom-width:1px">
			基本信息<a style="float:right;margin-right:10" href="javascript:void(0)" id="btnSave">保存</a>
		   </legend>
            <s:form action="optInfo!save.do" namespace="/sys" id="form1" >
            <table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">
						业务代码：
					</td>
					<td align="left">
					<c:if test="${not empty optid }">
						<s:textfield name="optid" readonly="true"  style="width: 100%;height: 27px;"/></c:if>
					<c:if test="${ empty optid }">
						<s:textfield name="optid" id="optid" style="width: 100%;height: 27px;"/></c:if>
						<span id="optidTip"></span>
					</td>
                    <td class="addTd">
						父类业务代码：
					</td>
					<td align="left">
						<s:textfield name="preoptid"  id="preoptid" style="width: 100%;height: 27px;"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						业务名称：
					</td>
					<td align="left">
						<s:textfield name="optname" id="optname" rows="1" cols="40" style="width: 100%;height: 27px;"/>
						<span id="optnameTip"></span>
					</td>
                    <td class="addTd">
						业务排序号：
					</td>
					<td align="left">
						<s:textfield name="orderind" rows="1" cols="40" style="width: 100%;height: 27px;"/>
					</td>
				</tr>
                <tr>
					<td class="addTd">
						打开方式：
					</td>
					<td align="left">
						<s:radio name="pageType" list="#{'D':'Div','F':'iframe'}" cssStyle="vertical-align: middle"></s:radio>						
					</td>
                     <td class="addTd">
						是否放在菜单栏：
					</td>
					<td align="left">
						<s:radio name="isintoolbar" list="#{'Y':'是','N':'否'}" cssStyle="vertical-align: middle"></s:radio>
					</td>
				</tr>	
				<tr>
					
                    <td class="addTd">
						业务类别：
					</td>
					<td align="left" colspan="3">
						<s:radio  id="opttype"   name="opttype" list="#{'M':'系统管理','S':'系统业务','N':'普通业务','W':'流程业务','E':'外部业务','H':'首页面模块','T':'统计模块'}" 
                                 cssStyle="vertical-align: middle"></s:radio>						
					</td>
				</tr>	
				<tr>
					<td class="addTd">
						业务URL：
					</td>
					<td align="left" colspan="3">
						<s:textfield name="opturl" id="opturl" size="50" style="width: 100%;height: 27px;"/>
					</td>
				</tr>
				
				<tr id="wfcodeTR" style="display:${opttype=='W'?'':'none'}">
					<td class="addTd">
						流程代码：
					</td>
					<td align="left" colspan="3">
						<s:textfield name="wfcode" id="wfcode" size="50" />
					</td>
				</tr>
			</table>
            </s:form>
        </fieldset>
        <!--操作按钮信息列表-->
        <c:if test="${empty param.preoptid}">
           <iframe src="${pageContext.request.contextPath}/sys/optDef!list.do?optid=${optid}" width="100%" height="280px"
			frameborder="0" scrolling="false" id="optDefListIframe"></iframe>
		 <!--业务变量-->
           <iframe src="${pageContext.request.contextPath}/sys/optVar!list.do?s_OPTID=${optid}&s_isAll=true" width="100%" height="280px"
			frameborder="0" scrolling="false" id="optVarListIframe" style="display:${opttype=='W'?'':'none'}"></iframe>
		</c:if>
       
	</body>
</html>
