<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			附件管理
		</title>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 附件管理
			</legend>
			
			<s:form action="VStuffTransaffair" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
				<s:hidden name="s_subItemTypenogw" value="%{#parameters['s_subItemTypenogw']}"/>
				<table cellpadding="0" cellspacing="0" align="center" class="norap">
					<tr>
						<td class="addTd">收文编号:</td>
						<td width="180"><s:textfield name="s_acceptArchiveNo" /></td>
						<td class="addTd">项目名称:</td>
						<td width="180"><s:textfield name="s_transaffairname" /></td>
						<td class="addTd"><!-- 公文类型: --></td>
						<td width="180">
<!-- 							<select name="s_flowCode"> -->
<!-- 							<option value="">---请选择---</option>							 -->
<!-- 								<option value="000535">办（阅办）件 </option> -->
<!-- 								<option value="000533">阅件</option> -->
<!-- 								<option value="000536">会签件 </option> -->
<!-- 								<option value="000711">督查件</option> -->
<!-- 								<option value="000730">人大代表建议、政协委员提案</option> -->
<!-- 								<option value="000530">委内督查件</option> -->
<!-- 								<option value="000534">主动发文</option> -->
<!-- 								<option value="000692">代发文</option> -->
<!-- 								<option value="000531">专报件</option> -->
<!-- 							</select> -->
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
						<c:if test="${s_bizstate eq 'C' }">
						<td class="addTd">发文文号:</td>
						<td width="180"><s:textfield name="s_sendArchiveNo" /></td>
					</c:if>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<s:submit method="list" value="查 询" cssClass="btn" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/VStuffTransaffair!list.do" items="objList" var="VStuffTransaffair"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="acceptArchiveNo" title="收文编号" style="text-align:left" />
				<ec:column property="transaffairname" title="项目名称" style="text-align:left" >
					<a href='<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${VStuffTransaffair.djId}&flowInstId=${VStuffTransaffair.flowInstId}'>${VStuffTransaffair.transaffairname}</a>
				</ec:column>
				<ec:column property="powername" title="权力名称" style="text-align:left" />
				<ec:column property="headunitcode" title="主办处室" style="text-align:left">
					${cp:MAPVALUE("unitcode",VStuffTransaffair.headunitcode)}
				</ec:column>
				<ec:column property="headusercode" title="主办承办人" style="text-align:left">
					${cp:MAPVALUE("usercode",VStuffTransaffair.headusercode)}
				</ec:column>
				<ec:column property="createdate" title="创建时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
					<a href="#" onclick="manageStuffInfo('${VStuffTransaffair.djId}');">材料管理</a>
					<a href="#" onclick="viewFlow('${VStuffTransaffair.flowInstId}');">查看流程图</a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>
<script type="text/javascript">
function manageStuffInfo(djId) {
	window.open("${contextPath}/dispatchdoc/VStuffTransaffair!edit.do?djId=" + djId, "_self");
}

function viewFlow(flowInstId) {
	window.open("${contextPath}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
}


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