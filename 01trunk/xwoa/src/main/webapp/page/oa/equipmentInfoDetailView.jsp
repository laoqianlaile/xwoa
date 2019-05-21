<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head >
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>资源管理</title>

</head>
<body>

  <fieldset class="form">
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">
			<c:if test="${ empty useRequestId}">
		新增使用信息
	</c:if>
			<c:if test="${ not empty useRequestId}">
		使用信息
	</c:if>
		</legend>
	<%@ include file="/page/common/messages.jsp"%>
	<form method="post" action="${pageContext.request.contextPath}/oa/equipmentUsing!save.do" class="form-horizontal" id="equipmentUsingForm" validate="false">
		<input type="hidden" id=equipmentState name="equipmentState" value="${equipmentState}" /> 
		<input type="hidden" id=useRequestId name="useRequestId" value="${useRequestId}" />
		<input type="hidden" id=supEquipmentType name="s_supEquipmentType" value="${s_supEquipmentType}" />
		<input type="hidden" id=equipmentType name="s_equipmentType" value="${s_equipmentType}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
                 <tr>
					<td class="addTd">资产名称：</td>
					<td align="left"styleClass="editable" colspan="3">
					<c:if test="${not empty equipmentId}">
					<input type="hidden" id=equipmentId name="equipmentId" value="${equipmentId}" />
						${object.equipmentInfo.equipmentName }
					</c:if>
					<c:if test="${ empty equipmentId}">
					
					<input type="text" id="equipmentName" value="${equipmentName }" target="dialog" data-title="点击选择资源" class="selectable"
								data-href="${pageContext.request.contextPath}/oa/equipmentInfo!listSmall.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}" data-ref="equipmentId" name="equipmentName" />
					<input type="hidden" id="equipmentId"value="${equipmentId }" name="equipmentId" />
							
						<span
						    class="add-on clearInput" refInput="#equipmentId,#equipmentName" title="点击清空">清空</span>
					</c:if>
                   </td>
				</tr>
				<c:if test="${not empty equipmentState}">
				<tr>
				<td  colspan="4" align="left">申请信息</td>
			    </tr>
				<tr>
				<td class="addTd">申请人：</td>
				<td align="left" colspan="3">${cp:MAPVALUE('usercode',applicant)}</td>
			    </tr>
			    <tr>
			    <td class="addTd">申请时间：</td>
				<td align="left"><fmt:formatDate value='${applicantTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td class="addTd">用途类别：</td>
				<td align="left">${cp:MAPVALUE("oaPurType",purposeType)}</td>
			    </tr>
			    <tr>
			    <td class="addTd">预计开始时间：</td>
				<td align="left"> <fmt:formatDate value='${planBeginTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td class="addTd">预计结束时间：</td>
				<td align="left"><fmt:formatDate value='${planEndTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			    </tr>
			    <tr>
				<td class="addTd">用途说明：</td>
				<td align="left" colspan="3">${purposeDesc }</td>
			    </tr>
			   
			  </c:if>
			 <c:if test="${ equipmentState>='2'}">
				<tr>
				<td  colspan="4" align="left">确认信息</td>
			    </tr>
				<tr>
				<td class="addTd">确认时间：</td>
				<td align="left" ><fmt:formatDate value='${auditTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td class="addTd">是否接受申请：</td>
				<td align="left"><c:if test="${beAccepted=='0' || empty beAccepted}">否</c:if> <c:if test="${beAccepted=='1' }">是</c:if></td>
			    </tr>
			    <tr>
			    <td class="addTd">确认人：</td>
				<td align="left" colspan="3">${cp:MAPVALUE('usercode',auditor)}</td>
			    </tr>
			   
			  </c:if>
             <c:if test="${ equipmentState>='3'}">
				<c:if test="${ beAccepted=='1'}">
				<tr>
				<td  colspan="4" align="left">反馈信息</td>
			    </tr>
				<tr>
				<td class="addTd">反馈时间：</td>
				<td align="left" ><fmt:formatDate value='${feedbackTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td class="addTd">使用反馈人：</td>
				<td align="left">${cp:MAPVALUE('usercode',feedbackUser)}</td>
			    </tr>
			    <tr>
			   <td class="addTd">是否实际使用：</td>
				<td align="left" ><c:if test="${beUsed=='0' || empty beUsed}">否</c:if> <c:if test="${beUsed=='1' }">是</c:if></td>
				<td class="addTd">使用费用：</td>
				<td align="left"><fmt:formatNumber value="${yearlyDepreciation }" type="number" /></td>
			    </tr>
			     <tr>
			   <td class="addTd">实际开始时间：</td>
				<td align="left" ><fmt:formatDate value='${beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td class="addTd">实际结束时间：</td>
				<td align="left"><fmt:formatDate value='${endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			    </tr>
			    <tr>
				<td class="addTd">实际使用说明：</td>
				<td align="left" colspan="3">${useDesc }</td>
			    </tr>
			   
			    </c:if>
			  </c:if>
			   </table>
			   
			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
			</div>
		</form>

	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

<script type="text/javascript">
	$(function() {
		var imgpath = '${pageContext.request.contextPath}'
				+ "/scripts/plugin/treetable/images/TreeTable";
		var $roleTree = $("#table-detailTree");
		var index = $.parseJSON('${INDEX}').indexes;
// 		var $objRoleTree = new jQueryCheckExt();
// 		$objRoleTree.makeCkeckBoxTreeTable($roleTree, index, imgpath);

		 $('#equipmentUsingForm').validate(

		{
			rules : {
				planEndTime : {
					remote : {
						url : '${contextPath}/oa/equipmentUsing!isFree.do',
						type : "get",
						dataType : "json",
						data : { //要传递的数据
							planEndTime : function() {
								return $.trim($("#planEndTime").val().trim());

							},
							planBeginTime : function() {
								return $.trim($("#planBeginTime").val());

							},
							equipmentId : function() {
								return $.trim($("#equipmentId").val());

							}

						}
					}
				} 
			}, 

			messages : {
				planEndTime : {
					remote : $.format("预计时间已被申请")
				},
				planBeginTime : {
					remote : $.format("预计时间已被申请")
				},
			},
			
		// 			onsubmit : true,
		// 			onfocusout:false,// 是否在获取焦点时验证 
		// 			onkeyup :false,

		}); 

		$('#tr_${s_equipmentType}').css('background-color', '#5CACEE');

	});

	$(function() {
		$('#myModal').on('hide', function() {
			var tr = $(this).find('tr.selected');
			if (tr[0]) {
				var source = $(this).data('source');
				source.val(tr.find('td:first').text().trim());
				source.prev().val(tr.data('equipment-id'));
			}
		});

		initHide();
	});
	var initHide = function() {
		//alert($("#radio_unbeUsed").val());
		$("#div_beginTime").hide();
		$("#div_endTime").hide();

		$('#beginTime').attr('disabled', 'disabled');
		$('#endTime').attr('disabled', 'disabled');
		//debugger;

		/* $("#div_beginTime").removeClass('error');
		$("#div_endTime").removeClass('error');
		$("#beginTime").next('span').removeClass('error');
		$("#endTime").next('span').removeClass('error'); */

	};

	$("#radio_unbeUsed").click(initHide);
	$("#radio_beUsed").click(function() {
		$("#div_endTime").show();
		$("#div_beginTime").show();

		$('#beginTime').removeAttr('disabled');
		$('#endTime').removeAttr('disabled');
	});


</script>
</html>
