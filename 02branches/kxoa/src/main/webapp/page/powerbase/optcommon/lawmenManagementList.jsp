<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法人员管理
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
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" name="flag" value="${flag}">
				<tr>
					<td class="addTd">部门名称:</td>
					<td>
					<input type="text" id="orgName" name="orgName" style="width:58%;height:25px;" value="${cp:MAPVALUE('unitcode',param.s_orgcode)}"/>
					<input type="hidden" id="s_orgcode" name="s_orgcode" value="${param.s_orgcode}"/>
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构
					</td>
				
					<td class="addTd">人员姓名:</td>
					<td> <s:textfield name="s_userName" value="%{#parameters['s_userName']}"/></td>
				</tr>
					<tr>
						<td></td>
						<td>
							<s:submit method="lawmenManagementList"  key="opt.btn.query" cssClass="btn"/>
							<c:if test="${flag==1}">
							<s:submit method="initalEdit"  key="opt.btn.add" cssClass="btn"/>
							</c:if>
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
				<ec:column property="opt" title="操作" style="text-align:center" sortable="false">
					<a href='powerbase/lawmen!lawmenUpdate.do?lawmenId=${lawmen.lawmenId}&flag=${flag}' >修改</a>
					<c:if test="${flag==1}">
					<a href='powerbase/lawmen!delete.do?lawmenId=${lawmen.lawmenId}&flag=${flag}' >删除</a>
					<a href='powerbase/lawmen!updateState.do?lawmenId=${lawmen.lawmenId}&flag=${flag}&userId=${lawmen.userId}' onclick='return confirm("确定申报该执法人员?");'>申报</a>
					</c:if>
					<c:if test="${flag==2}">
					<a href='powerbase/lawmen!delete.do?lawmenId=${lawmen.lawmenId}&flag=${flag}' >注销</a>
					<a href='powerbase/lawmen!updateState.do?lawmenId=${lawmen.lawmenId}&flag=${flag}&userId=${lawmen.userId}' >审核</a>
					</c:if>
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
