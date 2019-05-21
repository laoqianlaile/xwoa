<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<title></title>
	</head>
	
	<body>
		<fieldset>
			<legend>
				<b>已发起的收文</b>
			</legend>
			
			<s:form id="dispatchdocForm" action="dispatchDoc!incomeDocList.do" namespace="/dispatchdoc" method="post">
				<div id="djIdArea" style="display: none;"></div>
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
					    <td class="addTd">办件编号:</td>
						<td width="180"><s:textfield id="s_djId" name="s_djId" value="%{#parameters['s_djId']}" style="width:180px" /> </td>
						<td class="addTd">办件名称:</td>
						<td width="180"><s:textfield id="s_transaffairname" name="s_transaffairname" value="%{#parameters['s_transaffairname']}" style="width:180px"/></td>
						<td>
						<button id="btn_search" class="btn">查询</button>
						</td>
						<td>
							<button id="btn_save" class="btn">保存</button>
						</td>
					</tr>	
                 </table>
             </s:form>
             
			<ec:table action="dispatchdoc/dispatchDoc!incomeDocList.do?"
				items="incomeDocList" var="incomeDoc" 
				imagePath="${STYLE_PATH}/images/table/*.gif"
				retrieveRowsCallback="limit" target="_self">
				<ec:row>
					<ec:column property="sel" title="选择" sortable="false" style="text-align:center">
						<input type="checkbox" name="djId" value="${incomeDoc.djId}" />
					</ec:column>
					<ec:column property="djId" title="办件编号" style="text-align:center" />
					<ec:column property="transAffairNo" title="登记号" style="text-align:center" />
					<ec:column property="transaffairname" title="办件名称" style="text-align:center" />
					<ec:column property="updateDate" title="更新时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />
					<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
					<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
						<a href="#" onclick="viewFlow('${incomeDoc.flowInstId}');">查看流程图</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</fieldset>
	</body>

	<script type="text/javascript">
		function viewFlow(flowInstId) {
			window.open("<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
		}
	
	
		$(document).ready(function() {
			var djIds = $("input:checkbox[name='djId']"); // 页面checkbox框
			var selDjIds = $("input:hidden[name='djId']"); // 已选择的id
					
			for (var i=djIds.length-1; i>=0; i--) { // 如果已选择，则选中checkbox并且删除已选择的id
				var value = $(djIds[i]).val();
				for (var j=selDjIds.length-1; j>=0; j--) {
					if (value == $(selDjIds[j]).val()) {
						$(djIds[i]).attr("checked", true); // 选中
						$(selDjIds[j]).remove(); // 移除
						selDjIds.splice(j, 1); // 更新列表数组
						rowSelected(djIds[i], i, false);
						break;
					}
				}
			}
			
			function rowSelected(oCheckBox, index, isClick) { // 选中
				var oTr = $(oCheckBox).parent().parent();
			
				var domTr = $(oTr)[0];
				if ($(domTr).data("events") && $(domTr).data("events")["mouseover"]) { // 移除样式
					$(oTr).unbind("mouseover");
				} else {
					$(oTr).removeAttr("onmouseover");
				}
				if ($(domTr).data("events") && $(domTr).data("events")["mouseout"]) {
					$(oTr).unbind("mouseout");
				} else {
					$(oTr).removeAttr("onmouseout");
				}
				
				$(oTr).removeClass("highlight"); // 移除现在的样式，并恢复mouseout的样式
				if (index % 2 == 0) { // odd
					$(oTr).addClass("odd");
				} else { // even
					$(oTr).addClass("even");
				}
				$(oTr).attr("style", "background-color: #C0C0C0"); // 设置颜色
			}
			
			function rowCancelSelected(oCheckBox, index, isClick) { // 取消选中
				var oTr = $(oCheckBox).parent().parent();
			
				$(oTr).removeAttr("style");
				
				$(oTr).bind("mouseover", function() {
					if (index % 2 == 0) {
						$(oTr).removeClass("odd");
					} else {
						$(oTr).removeClass("even");
					}
					$(oTr).addClass("highlight");
				});
				
				$(oTr).bind("mouseout", function() {
					$(oTr).removeClass("highlight");
					if (index % 2 == 0) {
						$(oTr).addClass("odd");
					} else {
						$(oTr).addClass("even");
					}
				});
			}
			
			$("input:checkbox[name='djId']").click(function() {
				var index = -1;
				for (var i=djIds.length-1; i>=0; i--) {
					if ($(this).val() == $(djIds[i]).val()) {
						index = i;
						break;
					}
				}
				if (index == -1) {
					alert("页面出现错误！");
					return false;
				}
				if ($(this).attr("checked")) { // 选中
					rowSelected($(this), index, true);
				} else { // 未选中
					rowCancelSelected($(this), index, true);
				}
			});
			
			
			$("#btn_search").click(function() {
				var hiddenDjIds =  $("input:hidden[name='djId']");
				var checkedDjIds = $("input:checkbox[name='djId']:checked");
				for (var i=0; i<hiddenDjIds.length; i++) {
					$(hiddenDjIds[i]).clone().appendTo($("#djIdArea"));
				}
				for (var i=0; i<checkedDjIds.length; i++) {
					$(checkedDjIds[i]).clone().appendTo($("#djIdArea"));
				}
				$("#dispatchdocForm").submit();
			});
			
			$("#btn_save").click(function() {
				var hiddenDjIds =  $("input:hidden[name='djId']");
				var checkedDjIds = $("input:checkbox[name='djId']:checked");
				
				var array = new Array();
				for (var i=0; i<hiddenDjIds.length; i++) {
					array.push($(hiddenDjIds[i]).val());
				}
				for (var i=0; i<checkedDjIds.length; i++) {
					array.push($(checkedDjIds[i]).val());
				}
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!docRelativeSave.do?dispatchNo=" + $("input[name='dispatchNo']").val() + "&incomeNo=" + array.join(","),
					dataType : "json",
					success : function(data) {
						if ("success" == data.status) {
							if (window.parent.frames["editFrame"]) {
								window.parent.frames["editFrame"].window.refreshDocRelativeArea();
							} else {
								window.parent.window.refreshDocRelativeArea();
							}
							window.parent.window.JDialog.close();
						} else {
							alert("保存失败！请关闭页面后重新关联收文！");
						}
					},
					error : function() {
						alert("保存失败！请关闭页面后重新关联收文！");
					}
				});
				
				return false;
			});
			
		});
	</script>
</html>
