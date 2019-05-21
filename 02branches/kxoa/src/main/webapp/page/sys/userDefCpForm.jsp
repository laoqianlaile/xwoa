<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/page/common/taglibs.jsp" %>
<%@ include file="/page/common/css.jsp" %>


<html>
<head>
    <meta name="decorator" content='${LAYOUT}' />
    <title>复制人员权限信息</title>
    <sj:head />
    <s:include value="/page/common/formValidator.jsp"></s:include>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#btn_cp_user').click(function() {
                if('' == $.trim($('#userCode').val())) {

                    alert('未选择源用户');
                    return;
                }

                $.post('${pageContext.request.contextPath}/sys/userDef!userHaveRole.do', {
                    usercode : '${param.usercode}'
                }, callback, 'text');
            });


            var callback = function(respData) {
                var $action = $(':radio[name="action"]:checked');
                var operatVal = $(':radio[name="operatAction"]:checked').val();

                if(0 == operatVal && 1 == respData && 'undefined' == typeof $action.val()) {
                    alert('当前人员已具有岗位权限，请选择复制岗位权限的操作类型');
                    return;
                }

                var object = {
                    cpUsercode : $('#userCode').val(),
                    cpAction : $action.val(),
                    operatAction : $(':radio[name="operatAction"]:checked').val()
                };
                window.returnValue = object;

                window.close();
            };


            $(':radio[name="operatAction"]').click(function() {
                debugger;
                var operatVal = $(':radio[name="operatAction"]:checked').val();

                console.debug(operatVal);

                if('1' == operatVal) {
                    $(':radio[name="action"]').attr('disabled', 'disabled');
                } else {
                    $(':radio[name="action"]').removeAttr('disabled');
                }
            });
        });

    </script>

    <script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />

    <link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>" type="text/css" rel="stylesheet">
    <script language="javascript" src="<s:url value="/scripts/autocomplete/autocomplete.js"/>" type="text/javascript"></script>
    <script language="javascript" src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>

    <script type="text/javascript">
        var list = [];
        <c:forEach var="userinfo" varStatus="status" items="${cp:ALLUSER('T')}">
        list[${status.index}] = {
            username: '<c:out value="${userinfo.username}"/>',
            loginname: '<c:out value="${userinfo.loginname}"/>',
            usercode: '<c:out value="${userinfo.usercode}"/>',
            pinyin: '<c:out value="${userinfo.usernamepinyin}"/>'
        };
        </c:forEach>
        function selectUser(obj) {
            userInfo.choose(obj, {dataList: list, userName: $('#cpUserName')});
        }


    </script>
</head>

<body class="sub-flow">
<fieldset style="padding:10px;">
    <legend class="ctitle" style="width:auto;margin-bottom:10px;">用户信息</legend>

    <s:form action="userDef" id="form1">

        <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
            <c:choose>
                <c:when test="${'1' eq param.isnew}">
                    <tr>
                        <td class="addTd">业务选择</td>
                        <td><label>
                            <input name="operatAction" value="0" type="radio" checked="checked"  />复制
                            <input name="operatAction" value="1" type="radio" />交换
                        </label></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <input name="operatAction" value="0" type="hidden" />
                </c:otherwise>
            </c:choose>



            <tr>
                <td class="addTd">源用户代码*</td>
                <td><label>
                    <s:textfield onclick="selectUser(this);" id="userCode" />
                    <span id="usercodeTip"></span>
                </label></td>
            </tr>
            <tr>
                <td class="addTd">用户名*</td>
                <td><label>
                    <span id="cpUserName"></span>
                </label></td>
            </tr>
            <tr>
                <td class="addTd">复制操作</td>
                <td><label>
                    <input name="action" value="0" type="radio" />覆盖
                    <input name="action" value="1" type="radio" />合并
                    <input name="action" value="2" type="radio" />忽略
                </label></td>
            </tr>

            <tr>
                <td class="addTd"></td>
                <td><label>
                    <input type="button" id="btn_cp_user" class="btn" value="提交" />
                </label></td>
            </tr>


        </table>

    </s:form>
</fieldset>

</body>

</html>
