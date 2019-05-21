<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="VUserTransaffairDcyjGr.list.title" />
		</title>
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
			
			<s:form action="VUserTransaffairDcyjGr" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" class="norap" align="center">
				<s:hidden name="s_bizstate" value="%{#parameters['s_bizstate']}"/>
				<s:hidden name="s_subItemType" value="%{#parameters['s_subItemType']}"/>
				<s:hidden name="s_subItemTypenogw" value="%{#parameters['s_subItemTypenogw']}"/>
					<tr>
						<td class="addTd">姓名:</td>
						<td width="180">
							<s:textfield id="leaderName" name="s_leaderName" value="%{#parameters['s_leaderName']}" />
						</td>	
							<td colspan="6" align="center">
							<s:submit method="list" value="查 询" cssClass="btn" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/VUserTransaffairDcyjGr!list.do" items="objList" var="VUserTransaffairDcyjGr"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="transaffairname" title="项目名称" style="text-align:left" >
				<a href='<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${VUserTransaffairDcyjGr.djId}&flowInstId=${VUserTransaffairDcyjGr.flowInstId}'>${VUserTransaffairDcyjGr.transaffairname}</a>
				</ec:column>
				<ec:column property="powername" title="权力名称" style="text-align:left" />
				
				<ec:column property="headunitcode" title="主办处室" style="text-align:left">
					${cp:MAPVALUE("unitcode",VUserTransaffairDcyjGr.headunitcode)}
				</ec:column>
				<ec:column property="headusercode" title="主办承办人" style="text-align:left">
					${cp:MAPVALUE("usercode",VUserTransaffairDcyjGr.headusercode)}
				</ec:column>
				<ec:column property="acceptDate" title="受理时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
				<ec:column property="solvetime" title="办结时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
				<ec:column property="leaderNote" title="批示意见" style="text-align:left" >
				${VUserTransaffairDcyjGr.leaderName }&nbsp;${VUserTransaffairDcyjGr.unitName }&nbsp;<fmt:formatDate value='${VUserTransaffairDcyjGr.shipDate}' pattern='yyyy-MM-dd' />&nbsp;&nbsp;${VUserTransaffairDcyjGr.leaderNote }
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<a href="#" onclick="viewFlow('${VUserTransaffairDcyjGr.flowInstId}');">查看流程图</a>
				</ec:column>
			</ec:row>
		</ec:table>

	</body>
</html>
<script type="text/javascript">
	function viewFlow(flowInstId) {
		window.open("<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
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