<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html lang="en">
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>登录日志列表</title>
  <link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    $(function(){
    	$("#username").click(
    			function() {
    				var d = '${userjson}';
    				if (d.trim().length) {
    					selectPopWin(jQuery.parseJSON(d),
    							$("#username"),
    							$("#usercode"));
    				}
    				;
    			});
    });
    function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow,1).init();
		setAlertStyle("attAlert",undefined,true);
	}
    </script>
	</head>
	<body>
	    <div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						<ul></ul>
					</div>
					<div id="t-b-arrow">
						<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		<fieldset style="padding-top:6px;" class="search">
			<legend>查询条件</legend>
			<s:form action="accessLog" theme="simple" namespace="/sys">			
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td class="addTd">用户名：</td>
						<td><s:textfield name="s_username" id="username"  value="%{#parameters['s_username']}" />
						  <s:hidden name="s_usercode" id="usercode" value="%{#parameters['s_usercode']}" ></s:hidden>
						  <s:hidden name="s_excludeType" value="3"/>
						</td>
						<td class="addTd">登录时间：</td>
						<td><s:textfield name="s_beginDate" value="%{#parameters['s_beginDate']}" cssClass="Wdate" cssStyle="width:170px"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>到
						<s:textfield name="s_endDate" value="%{#parameters['s_endDate']}"  cssClass="Wdate" cssStyle="width:170px"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
						</td>
					</tr>
					<tr class="searchButton">
						<td colspan="4">
							<s:submit method="list" cssClass="btn" value="查询" ></s:submit>
							</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
			<ec:table action="${pageContext.request.contextPath}/sys/accessLog!list.do" items="objList" var="accessLog"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" 
			rowsDisplayed="15" 
			filterRowsCallback="limit" 
			retrieveRowsCallback="limit"
			sortRowsCallback="limit"
			>
			<ec:row>
				<ec:column property="usercode" title="用户名" style="text-align:center">
				  ${cp:MAPVALUE("usercode",accessLog.usercode)}
				</ec:column>
                <ec:column property="accesstime" title="访问时间" style="text-align:center">
                  <fmt:formatDate value="${accessLog.accesstime}" pattern="yyyy-MM-dd HH:mm:ss" />
                </ec:column>
                <ec:column property="accesstype" title="访问类型" style="text-align:center">
                   <c:if test="${accessLog.accesstype=='1'}">登录</c:if>
                   <c:if test="${accessLog.accesstype=='2'}">退出</c:if>
                   <c:if test="${accessLog.accesstype=='3'}">数据操作</c:if>
                   <c:if test="${accessLog.accesstype=='4'}">异常退出</c:if>
                </ec:column>
                <ec:column property="ip" title="登录IP" style="text-align:center" />
			</ec:row>
		</ec:table>
</body>
</html>
