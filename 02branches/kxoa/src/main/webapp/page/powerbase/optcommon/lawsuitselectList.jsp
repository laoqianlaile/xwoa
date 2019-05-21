<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title></title>
		<%-- <sj:head locale="zh_CN" /> --%>
			<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
		
			<s:form action="lawsuit" method="post" namespace="/powerbase" id="lawsuitForm" enctype="multipart/form-data">
					<table cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td class="addTd">权力类型</td>
						<td colspan="3">
						 <s:radio name="flag" list="#{'1':'办件','2':'案件'}" value="%{flag}" listKey="key" listValue="value" onclick="checkVersion()" /></td>
					</tr>
					<tr>
						<td class="addTd"><c:if test="${flag==1}">办件编号</c:if><c:if test="${flag==2}">案件编号</c:if></td>
						<td><s:textfield name="s_internalNo" value="%{#parameters['s_internalNo']}" /></td>
						<td class="addTd">部门名称</td>
								<td>						
				<input type="text" id="orgName" name="orgName"  value="${cp:MAPVALUE('unitcode',param.s_orgcode)}"/>
				<input type="hidden" id="s_orgcode" name="s_orgcode" value="${param.s_orgcode}"/><s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构	
						</td>	
					</tr>
					
					<tr>
					<td>
						<s:submit method="selectList" key="opt.btn.query" cssClass="btn" />
					</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
<c:if test="${flag==1}">
			<ec:table action="powerbase/lawsuit!selectList.do" items="applyList" var="apply" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">

		 <input type="hidden" id="internalNo${apply.internalNo}" name="internalNo${apply.internalNo}" value="${apply.internalNo}">
		 <input type="hidden" id="itemId${apply.internalNo}" name="itemId${apply.internalNo}" value="${apply.itemId}">
		 <input type="hidden" id="orgId${apply.internalNo}" name="orgId${apply.internalNo}" value="${apply.orgId}">
		 
			<ec:row>
					<ec:column property="apply.internalNo" title="办件编号" style="text-align:center">
					${apply.internalNo }
					</ec:column>
				<ec:column property="apply.itemId" title="办件名称" style="text-align:center">
					${cp:MAPVALUE("suppowerId",apply.itemId)}
					</ec:column>
					<ec:column property="apply.orgId" title="主办部门" style="text-align:center">
					${cp:MAPVALUE("depno",apply.orgId)}
					</ec:column>
				  <ec:column property="opt" title="选择" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${apply.internalNo}')">
			</ec:column>
			</ec:row>
		</ec:table>
		</c:if>
	<c:if test="${flag==2}">
			<ec:table action="powerbase/lawsuit!selectList.do" items="punishList" var="punish" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">

		<input type="hidden" id="internalNo${punish.internalNo}" name="internalNo${punish.internalNo}" value="${punish.internalNo}">
		 <input type="hidden" id="itemId${punish.internalNo}" name="itemId${punish.internalNo}" value="${punish.itemId}">
		 <input type="hidden" id="orgId${punish.internalNo}" name="orgId${punish.internalNo}" value="${punish.orgId}">
			<ec:row>
					<ec:column property="internalNo" title="案件编号" style="text-align:center">
					${punish.internalNo }
					</ec:column>
				<ec:column property="itemId" title="案件名称" style="text-align:center">
					${cp:MAPVALUE("suppowerId",punish.itemId)}
					</ec:column>
					<ec:column property="orgId" title="主办部门" style="text-align:center">
					${cp:MAPVALUE("depno",punish.orgId)}
					</ec:column>
			  <ec:column property="opt" title="选择" sortable="false"
				style="text-align:center">
				<input type="radio" name="internalNo"
					onclick="setParentVal('${punish.internalNo}')">
			</ec:column>
			</ec:row>
		</ec:table>
		</c:if>

	</body>
	<script type="text/javascript">
	//获取父页面对象
	var parentDocument = window.opener.document;
	//设置返回值
	function setParentVal(internalNo) {
		if (window.confirm("确认选择吗")) {		
			parentDocument.getElementById('lawsuitno').value = document
			.getElementById('internalNo' + internalNo).value;
			window.close();
		}
	}
	function checkVersion() {
		 var form=document.getElementById("lawsuitForm");
	     form.action="powerbase/lawsuit!selectList.do";     
	     form.submit();
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
