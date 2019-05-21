<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/page/common/taglibs.jsp" %>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
    <title>
            <s:text name="usersetting.edit.title"/>
    </title>
</head>

<body>
<fieldset style="padding:10px;">
	<legend><s:text name="usersetting.edit.title"/></legend>
<s:form action="userSetting" namespace="/sys" theme="simple">
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
<s:hidden name="usercode"/>
<tr>
    <td width="150" class="addTd">
        <s:text name="usersetting.framelayout"/>
    </td>
    <td align="left">
        <select name="framelayout">
            <c:forEach var="row" items="${cp:LVB('framelayout')}">
                <option  <c:if test="${row.value==object.framelayout}">selected="selected"</c:if> value="<c:out value='${row.value}'/>">
                    <c:out value="${row.label}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
            <s:text name="usersetting.menustyle"/>
    </td>
    <td align="left">
        <select name="menustyle">
            <c:forEach var="row" items="${cp:LVB('menustyle')}">
                <option <c:if test="${row.value==object.menustyle}">selected="selected"</c:if> value="<c:out value='${row.value}'/>"   >
                    <c:out value="${row.label}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.pagestyle"/>
    </td>
    <td align="left">
        <select name="pagestyle">
            <c:forEach var="row" items="${cp:LVB('pagestyle')}">
                <option value="<c:out value='${row.value}'/>"  <c:if test="${row.value==object.pagestyle}">selected="selected"</c:if>>
                    <c:out value="${row.label}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.mainpage"/>

    </td>
    <td align="left">
<%--         <select name="mainpage">
            <option value="app/dashboard!show.do" <c:if test="${'app/dashboard!show.do'==object.mainpage}">selected="selected"</c:if>>
                <c:out value="用户仪表盘"/>
            </option>
        </select> --%>
        <s:textfield name="mainpage" value="%{object.mainpage}"></s:textfield>

    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.linesperpage"/>
    </td>
    <td align="left">
        <s:textfield name="linesperpage"  value="%{object.linesperpage}" maxlength="80"/>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.boardlayout"/>

    </td>
    <td align="left">
        <select name="boardlayout">
            <c:forEach var="row" items="${cp:LVB('boardlayout')}">
                <option value="${row.value}" <c:if test="${row.value==object.boardlayout}">selected="selected"</c:if>>
                    <c:out value="${row.label}"/>
                </option>
            </c:forEach>
        </select>

    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl1"/>
    </td>
    <td align="left">
        <select name="favorurl1">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl1}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl2"/>
    </td>
    <td align="left">
        <select name="favorurl2">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl2}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl3"/>
    </td>
    <td align="left">
        <select name="favorurl3">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl3}">selected="selected"</c:if>><c:out value="${row.optname}"/></option>
            </c:forEach>
        </select>

    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl4"/>
    </td>
    <td align="left">
        <select name="favorurl4">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl4}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl5"/>
    </td>
    <td align="left">
        <select name="favorurl5">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl5}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl6"/>
    </td>
    <td align="left">
        <select name="favorurl6">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl6}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl7"/>
    </td>
    <td align="left">
        <select name="favorurl7">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl7}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl8"/>
    </td>
    <td align="left">
        <select name="favorurl8">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl8}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr>
    <td class="addTd">
        <s:text name="usersetting.favorurl9"/>
    </td>
    <td align="left">
        <select name="favorurl9">
        	<option value="">——请选择——</option>
            <c:forEach var="row" items="${functions}">
                <option value="${row.optid}" <c:if test="${row.optid==object.favorurl9}">selected="selected"</c:if>>
                    <c:out value="${row.optname}"/>
                </option>
            </c:forEach>
        </select>
    </td>
</tr>


<tr><td></td><td><s:submit method="save"  key="opt.btn.save" cssClass="btn"/></td></tr>
</table>
</s:form>
</fieldset>
</body>
</html>