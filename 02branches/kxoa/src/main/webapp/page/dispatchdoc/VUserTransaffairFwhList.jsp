<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head><title>已用拟发文文号</title>
<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<fieldset >
		<legend>
			已用拟发文文号
		</legend>
		<s:form action="VUserTransaffair" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center" class="norap">
				<tr>
					<td class="addTd">阅办文编号:</td>
					<td width="180"><s:textfield name="s_acceptArchiveNo" /></td>
					<td class="addTd">项目名称:</td>
					<td width="180"><s:textfield name="s_transaffairname" /></td>
					<td class="addTd"><!-- 公文类型: --></td>
					<td width="180">
<!-- 						<select name="s_flowCode"> -->
<!-- 						<option value="">---请选择---</option>							 -->
<!-- 							<option value="000535">办（阅办）件 </option> -->
<!-- 							<option value="000533">阅件</option> -->
<!-- 							<option value="000536">会签件 </option> -->
<!-- 							<option value="000711">督查件</option> -->
<!-- 							<option value="000730">人大代表建议、政协委员提案</option> -->
<!-- 							<option value="000530">委内督查件</option> -->
<!-- 							<option value="000534">主动发文</option> -->
<!-- 							<option value="000692">代拟发文</option> -->
<!-- 							<option value="000531">专报件</option> -->
<!-- 						</select> -->
					</td>
				</tr>
				<tr>
					<td class="addTd">来文编号:</td>
					<td width="180"><s:textfield name="s_incomeDocNo" /></td>
					<td class="addTd">来文单位:</td>
					<td width="180"><s:textfield name="s_incomeDeptName" /></td>
					<td class="addTd">来文单位类型:</td>
					<td width="180">
						<select id="incomeDeptType" name="s_incomeDeptType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
								<option value="${row.key}" ${(object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="addTd">主办处室:</td>
					<td width="180">
						<s:textfield id="zbUnitName" name="s_zbUnitName" />
						<input type="hidden" id="s_orgcode" name="orgcode" value=""/>
					</td>
					<td class="addTd">会办处室:</td>
					<td width="180">
						<s:textfield id="hbUnitNames" name="s_hbUnitNames" />
						<input type="hidden" id="s_orgcodes" name="orgcodes" value=""/>
					</td>
					<td class="addTd">拟发文文号:</td>
					<td width="180"><s:textfield name="s_sendArchiveNo" /></td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<s:submit method="listFwh"  value="查 询"  cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="${contextPath}/dispatchdoc/VUserTransaffair!listFwh.do" items="objList" var="VUserTransaffair"
		imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="fwh" title="拟发文文号" style="text-align:left" />
			<ec:column property="wjlx" title="文件类型" style="text-align:left">
				${cp:MAPVALUE("WJLX",VUserTransaffair.wjlx)}
			</ec:column>
			<ec:column property="dispatchDocTitle" title="文件标题" style="text-align:left" />
			<ec:column property="incomeDeptName" title="来文单位" style="text-align:left" />
			<ec:column property="headunitcode" title="主办处室" style="text-align:left">
				${cp:MAPVALUE("unitcode",VUserTransaffair.headunitcode)}
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
function bindEvent(o1,o2,$this){
	o1.val($this.html());
	o2.val($this.attr("rel"));
	if(getID("shadow")){
		$("#shadow").hide();
		$("#boxContent").hide();
	}
}
$("#zbUnitName").bind('click',function(){
	var menuList = ${unitsJson};
	var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
	if(getID("shadow")){
		$("#shadow").show();
		$("#boxContent").show();
	}else{
		$("body").append(shadow);
		$("#shadow").height(document.body.scrollHeight);
		setTimeout(function(){
			menuDisplay(menuList,"0");	
		},0);
	};
	$("#lists span").live('click',function(){
		var $this = $(this);
		bindEvent($("#zbUnitName"),$("#s_orgcode"),$this);
		$("#lists span").die("click");
	});
});

$("#hbUnitNames").bind('click',function(){
	var menuList = ${unitsJson};
	var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
	if(getID("shadow")){
		$("#shadow").show();
		$("#boxContent").show();
	}else{
		$("body").append(shadow);
		$("#shadow").height(document.body.scrollHeight);
		setTimeout(function(){
			menuDisplay(menuList,"0");	
		},0);
	};
	$("#lists span").live('click',function(){
		var $this = $(this);
		bindEvent($("#hbUnitNames"),$("#s_orgcodes"),$this);
		$("#lists span").die("click");
	});
});
</script>
</html>
