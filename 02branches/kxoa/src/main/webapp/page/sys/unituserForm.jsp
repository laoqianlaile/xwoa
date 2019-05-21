<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/page/common/taglibs.jsp" %>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
    <title>机构用户编辑--
        <c:out value="${cp:MAPVALUE('unitcode',unitForm.map.unitcode)}"/>:
        <c:out value="${cp:MAPVALUE('usercode',unitForm.map.usercode)}"/>
    </title>
    <link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>" type="text/css" rel="stylesheet">
    <script language="javascript" src="<s:url value="/scripts/autocomplete/autocomplete.js"/>" type="text/javascript"></script>
     <script language="javascript" src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>

    <script type="text/javascript" >
        var list = [];
        <c:forEach var="userinfo" varStatus="status" items="${userlist}">
            list[${status.index}] = { username:'<c:out value="${userinfo.username}"/>', loginname:'<c:out value="${userinfo.loginname}"/>', usercode:'<c:out value="${userinfo.usercode}"/>',pinyin:'<c:out value="${userinfo.usernamepinyin}"/>'  };
        </c:forEach>
        function selectUser(obj) {
               userInfo.choose(obj, {dataList:list,userName:$('#userName')});
        }


    </script>

</head>

<body class="sub-flow">
<fieldset style="padding:10px;">
	<legend class="ctitle" style="width:auto;margin-bottom:10px;">机构用户编辑--
    <c:out value="${cp:MAPVALUE('unitcode',unitForm.map.unitcode)}"/>:
    <c:out value="${cp:MAPVALUE('usercode',unitForm.map.usercode)}"/></legend>

<s:form action="unit" name="/sys" >
    
    <table cellpadding="0" cellspacing="0" class="viewTable">

        <tr>
            <td class="addTd">
                机构代码
            </td>
            <td align="left">
                <s:textfield name="userunit.unitcode" value="%{userunit.unitcode}" readonly="true" style="width:140px;" />
            </td>
         </tr>
         <tr>
            <td class="addTd">
                机构名称
            </td>
            <td align="left">
                <c:out value="${cp:MAPVALUE('unitcode',userunit.unitcode)}"/>
            </td>
        </tr>
        <tr>
            <td class="addTd">
                用户代码
            </td>
            <td align="left">
                <s:textfield name="userunit.usercode" onclick="selectUser(this)" id="userCode"  style="width:140px;" />
            </td>
         </tr>
         <tr>
            <td class="addTd">
                用户名
            </td>
            <td align="left" id="un">
                <div id="userName">
                       <c:out value="${cp:MAPVALUE('usercode',userunit.usercode)}"/></div> 
            </td>
        </tr>
        <tr>
            <td class="addTd">
                用户岗位
            </td>
            <td align="left">
                <select name="userunit.userstation" style="width:140px;">
                    <c:forEach var="row" items="${cp:LVB('StationType')}">
                        <option value="<c:out value='${row.value}'/>"
                                <c:if test="${row.value==userUnit.userstation}">selected="selected"</c:if>>
                            <c:out value="${row.label}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
         </tr>
         <tr>
            <td class="addTd">
                	行政职务
            </td>
            <td align="left">
                <select name="userunit.userrank" style="width:140px;">
                    <c:forEach var="row" items="${cp:LVB('RankType')}">
                        <option value="<c:out value='${row.value}'/>"
                                <c:if test="${row.value==userUnit.userrank}">selected="selected"</c:if>>
                            <c:out value="${row.label}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="addTd">
                授权说明
            </td>
            <td align="left" colspan="3">
                <s:textarea name="userunit.rankmemo" style="width:600px;height:50px;" value="%{userUnit.rankmemo}"/>
            </td>
        </tr>

    </table>

	       <div class="formButton">
				<s:submit method="saveUnitUser" cssClass="btn" value="保存" />
				<input type="button" value="返回" class="btn" onclick="window.history.back();" />

			</div>		
		</s:form>
</fieldset>
</body>
</html>
