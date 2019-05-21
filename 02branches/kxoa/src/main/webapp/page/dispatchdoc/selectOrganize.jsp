<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="选择机构" />
		</title>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<s:form name="nodeForm" method="post" action="ioDocTasksExcute" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
			<input type="hidden" id="roleCode" name="roleCode" value="${roleCode}" />
			<input type="hidden" id="unitsJson" name="unitsJson" value='${unitsJson}' />
			
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
				<legend style="padding:4px 8px 3px;">
					<b>已选择机构</b>
				</legend>
				<div style="width: 100%; height: 80px; overflow-y: auto;">
					<div id="orgCodeArea" style="display: none;">
						<c:forEach items="${selUnits}" var="unit">
							<input type="hidden" id="orgCode" name="orgCode" value="${unit.unitcode}" />
						</c:forEach>
					</div>
					<div id="orgNameArea">
						<c:forEach items="${selUnits}" var="unit">
							<span id="orgCode_${unit.unitcode}">[${unit.unitname}<c:if test="${'T' ne unit.isvalid}">
								<a href="#" onclick="deleteOrganize('${unit.unitcode}');"><img border="0" src="../images/close.png"></a></c:if>]
								&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							<input type="hidden" id="orgCode" name="orgCode" value="${unit.unitcode}" />
						</c:forEach>
					</div>
				</div>
			</fieldset>
		</s:form>
	</body>
	<script type="text/javascript">
		var isComplete = false;
		var flowInstId = "";
		var roleCode= "";
	
		$(document).ready(function() {
			flowInstId = $("#flowInstId").val();
			roleCode = $("#roleCode").val();
			
			var menuList = jQuery.parseJSON($("#unitsJson").val());
			var shadow = "<div id='boxContent' style='z-index:1000;'><div id='lists' class='getTree' style='left:40%;top:135px;width:400px;margin-left:-158px;border:1px solid #369;'>Loader</div></div>";
			if(getID("boxContent")){
				$("#boxContent").show();
			}else{
				$("body").append(shadow);
			 	setTimeout(function() {
					menuDisplay(menuList,"1");
				}, 0);
			};
			$("#lists span").live('click', function() {
				var $this = $(this);
				addOrganize($this.attr("rel"), $this.html());
// 				$("#lists span").die("click");
			});
		});
		
		function addOrganize(orgCode, orgName) {
			var hiddens = $("#orgCodeArea input[type='hidden']");
			
			for (var i=0; i<hiddens.length; i++) {
				if (orgCode == $(hiddens[i]).val()) {
					return false;
				}
			}
			
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!assignFlowOrganizeAjax.do?flowInstId=" + flowInstId + "&roleCode=" + roleCode + "&orgCode=" + orgCode,
				dataType : "json",
				success : function(data) {
					if ("success" == data.status) {
						var orgCodeHtml = "<input type='hidden' id='orgCode' name='orgCode' value='" + orgCode + "' />";
						$("#orgCodeArea").append(orgCodeHtml);
						var orgNameBuffer = new StringBuffer();
						orgNameBuffer.append("<span id='orgCode_" + orgCode + "'>[" + orgName + "&nbsp;");
						orgNameBuffer.append("<a href='#' onclick='deleteOrganize(\"" + orgCode + "\");'>");
						orgNameBuffer.append("<img border='0' src='../images/close.png'></a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
						$("#orgNameArea").append(orgNameBuffer.toString());
						$("#countersignUnitsArea", window.parent.window.frames['editFrame'].document).append(orgNameBuffer.toString());
						window.parent.window.frames['editFrame'].window.adjustHeight();
					} else {
						alert("添加" + orgName + "失败！");
					}
				},
				error : function() {
					alert("添加" + orgName + "失败！");
				}
			});
			
			return false;
		}
		
		function deleteOrganize(orgCode) {
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!deleteFlowOrganizeUnitAjax.do?flowInstId=" + flowInstId + "&roleCode=" + roleCode + "&orgCode=" + orgCode,
				dataType : "json",
				success : function(data) {
					if ("success" == data.status) {
						$("#orgCode[value=" + orgCode + "]").remove();
						$("#orgCode_" + orgCode).remove();
						$("#orgCode_" + orgCode, window.parent.window.frames['editFrame'].document).remove();
						window.parent.window.frames['editFrame'].window.adjustHeight();
					} else {
						alert("删除失败！");
					}
				},
				error : function() {
					alert("删除失败！");
				}
			});
			
			return false;
		}
	</script>
</html>

