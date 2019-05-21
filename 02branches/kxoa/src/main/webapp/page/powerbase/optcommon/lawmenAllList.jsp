<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法人员汇总
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset style="padding:10px;">
			<legend>
				 <b>查询条件</b>
			</legend>
			
			<s:form action="lawmen" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
			<input type="hidden" id="flag" name="flag" value="${flag}"/>
				<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd">部门名称:</td>
					<td>
					<input type="text" id="orgName" name="orgName" style="height:25px;" value="${cp:MAPVALUE('unitcode',param.s_orgcode)}"/>
					<input type="hidden" id="s_orgcode" name="s_orgcode" value="${param.s_orgcode}"/>
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构
					</td>
					<td class="addTd">人员姓名:</td>
					<td> <s:textfield name="s_userName" value="%{#parameters['s_userName']}"/></td>
				</tr>
				<tr>
				<td class="addTd">职务:</td>
					<td><select name="s_position" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('POSITION')}">
								<option value="${row.key}"
									<c:if test="${parameters.s_position[0] eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">执法证号:</td>
					<td> <s:textfield name="s_userId" value="%{#parameters['s_userId']}"/></td>
				</tr>
				<tr>
				<td class="addTd">人员状态:</td>
					<td><select name="s_updateType" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('jcry_type')}">
								<option value="${row.key}"
									<c:if test="${parameters.s_updateType[0] eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
					<tr>
						<td></td>
						<td>
							<s:submit method="lawmenManagementList"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<ec:table action="powerbase/lawmen!lawmenManagementList.do" items="lawmenManagementList" var="lawmen" 
		          imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="userId" title="执法证号" style="text-align:center">
						${lawmen.userId}
				</ec:column>
				<ec:column property="userName" title="人员姓名" style="text-align:center">
						<a href='powerbase/lawmen!view.do?lawmenId=${lawmen.lawmenId}' >${lawmen.userName}</a>
				</ec:column>
				<ec:column property="deptcode" title="部门名称" style="text-align:center" >
						${cp:MAPVALUE("depno",lawmen.deptcode)}
				</ec:column>
				<ec:column property="position" title="职务" style="text-align:center" >
				${cp:MAPVALUE("POSITION",lawmen.position)}
				</ec:column>
				<ec:column property="updateType" title="执法种类" style="text-align:center" >
						${lawmen.legalRange}
				</ec:column>
				<ec:column property="updateType" title="人员状态" style="text-align:center" >
						${cp:MAPVALUE("jcry_type",lawmen.updateType)}
				</ec:column>
				<ec:column property="state" title="审核状态" style="text-align:center" >
						<c:if test="${lawmen.state eq '0' or lawmen.state eq '1'}">
						未审核
					</c:if>
					<c:if test="${lawmen.state eq '2' or lawmen.state eq '3'}">
						已审核
					</c:if>
				</ec:column>
				<ec:column property="state" title="审核结果" style="text-align:center" >
					<c:if test="${lawmen.state eq '2'}">
						通过
					</c:if>
					<c:if test="${lawmen.state eq '3'}">
						未通过
					</c:if>
					<c:if test="${lawmen.state eq '0' or lawmen.state eq '1'}">
					&nbsp;
					</c:if>
				</ec:column>
				<ec:column property="opt" title="操作" style="text-align:center" sortable="false">
				<a href='powerbase/lawmen!view.do?lawmenId=${lawmen.lawmenId}' >查看</a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
		function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=500px,width=790px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
		function bindEvent(o1,o2,$this){
			o1.val($this.html());
			o2.val($this.attr("rel"));
			if(getID("shadow")){
				$("#shadow").hide();
				$("#boxContent").hide();
			}
		}
		$("#orgName").bind('click',function(){
			var menuList = ${unitsJson};
			var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
			if(getID("shadow")){
				$("#shadow").show();
				$("#boxContent").show();
			}else{
				$("body").append(shadow);
				$("#shadow").height(document.body.scrollHeight);
				setTimeout(function(){
					menuDisplay(menuList,"${parentunit}");	
				},0);
			};
			$("#lists span").live('click',function(){
				var $this = $(this);
				bindEvent($("#orgName"),$("#s_orgcode"),$this);
				$("#lists span").die("click");
			});
		});
	</script>
</html>
