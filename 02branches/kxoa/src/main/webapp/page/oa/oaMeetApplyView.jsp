<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ include file="/page/common/print.jsp"%>
<head>
<title>会议申请查看</title>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
</head>

<body class="sub-flow div_wrapper">
	<s:hidden name="viewtype" value="%{viewtype}"></s:hidden>
 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">申请信息</div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
</div>
<h3
			style="display: inline-block; color: red; text-align: center; font-size: 16.0pt; font-family: 方正小标宋_GBK;padding:10px 0;">
			${cp:MAPVALUE('SYSPARAM','BizzName')}&nbsp;议&nbsp;室&nbsp;使&nbsp;用&nbsp;申&nbsp;请&nbsp;单<a id="printBtn" title="打印"onclick="print();" style="background:url('${ctxStatic}/image/print.png') no-repeat;width:16px;height:15px;display:inline-block;cursor:pointer;margin-left:15px;"></a>
			</h3>
		<br />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="div_wrapper_table">
			<tr>
				<td class="addTd">申请单号</td>
				<td align="left"><p><c:if test="${object.state eq '6' }">--</c:if>
					<c:if test="${object.state ne '6' }">
						<s:property value="%{applyNo}" />
					</c:if></p></td>
				<td class="addTd">申请日期</td>
				<td align="left"><p><s:date name="createtime"
						format="yyyy-MM-dd HH:mm" /></p></td>
			</tr>
			<tr>
				<td class="addTd">会议名称</td>
				<td align="left" colspan="3"><p><s:property value="%{title}" /></p></td>
				<%-- <td class="addTd">会议类型</td>
				<td align="left"><p><c:if test="${meetType=='O'}">一般会议</c:if> <c:if
						test="${meetType=='P'}">视频会议</c:if></p></td> --%>
			</tr>
			<c:if test="${meetType=='P'}">
				<tr>
					<td class="addTd">发言单位</td>
					<td align="left"><p>${cp:MAPVALUE("unitcode",units)}</p></td>
					<td class="addTd">会议范围</td>
					<td align="left"><p><c:if test="${meeting_rang=='S' }">交通部会议</c:if>
						<c:if test="${meeting_rang=='M' }">自治区系统会议</c:if></p></td>
				</tr>

				<tr>
					<td class="addTd">使用会场</td>
					<td align="left" colspan="3"><p><c:if test="${use_venue=='4'}">厅四楼会议室</c:if>
						<c:if test="${use_venue=='8'}">厅配八楼会议室</c:if> <c:if
							test="${use_venue=='0'}">其他(${otheruse_venue})</c:if></p></td>
				</tr>
				<tr>
					<td class="addTd">是否双流会议</td>
					<td align="left"><p><c:if test="${isSLmeeting=='P'}">是</c:if> <c:if
							test="${isSLmeeting=='N'}">否</c:if></p></td>
					<td class="addTd">联调时间</td>
					<td align="left"><p><s:date name=" Alignment_time"
							format="yyyy-MM-dd HH:mm:ss" /></p></td>
				</tr>
			</c:if>
			<tr>
				<td class="addTd">会议部门</td>
				<td align="left"><p>${cp:MAPVALUE("unitcode",depno)}</p></td>
				<td class="addTd">会议地点</td>
				<td align="left"><p>${oaMeetApply.oaMeetinfo.meetingName}</p></td>
			</tr>

			<tr>
				<td class="addTd">联系人</td>
				<td align="left"><p>${cp:MAPVALUE("usercode",creater)}</p></td>
				<td class="addTd">联系电话</td>
				<td align="left"><p><s:property value="%{telephone}" /></p></td>
			</tr>
			<tr>
				<td class="addTd">预计开始时间</td>
				<td align="left"><p><s:date name="planBegTime"
						format="yyyy-MM-dd HH:mm" /></p></td>
				<td class="addTd">预计结束时间</td>
				<td align="left"><p><s:date name="planEndTime"
						format="yyyy-MM-dd HH:mm" /></p></td>

			</tr>
			<tr>
				<td class="addTd" width="20%">参会单位</td>
				<td align="left" colspan="3" width="85%"><p>${retValue}</p></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaMeetApply.otherUnits"/></td>
				<td align="left" colspan="3"><p><s:property value="%{otherUnits}" /></p></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaMeetApply.attendingLeaderNames"/></td>
				<td align="left" colspan="3"><p><s:property value="%{attendingLeaderNames}" /></p></td>
			</tr>	
			<tr>
				<td class="addTd"><s:text name="oaMeetApply.recomUnitNames"/></td>
				<td align="left" colspan="3"><p><s:property value="%{recomUnitNames}" /></p></td>
			</tr>	
			<c:if test="${isBasicUnit eq 'T' }">
			<tr>
				<td class="addTd">是否有基层单位参加</td>
				<td align="left"><p>${cp:MAPVALUE('TrueOrFalse',isBasicUnit)}</p></td>
				<td class="addTd">是否需要停车</td>
				<td align="left"><p>${cp:MAPVALUE('TrueOrFalse',isStopCar)}</p></td>
			</tr>
			 </c:if>
			 
			 <c:if test="${isBasicUnit eq 'F' }">
			<tr>
				<td class="addTd">是否有基层单位参加</td>
				<td align="left" colspan="3"><p>${cp:MAPVALUE('TrueOrFalse',isBasicUnit)}</p></td>
			</tr>
			 </c:if>
			<tr>
				<td class="addTd">会议标准</td>
				<td align="left"><p>${reqRemarkNew }<c:if
						test="${not empty otherItem }">（<s:property
							value="%{otherItem }" />）</c:if></p>
				</td>
				<td class="addTd">参会人数</td>
				<td align="left"><p><s:property value="%{meetingPersionsNum}" /></p>
				</td>
			</tr>
			<tr>
				<td class="addTd">会议要求</td>
				<td align="left" colspan="3"><p><s:property value="%{remark}" /></p>
				</td>

			</tr>

			<tr>
				<td class="addTd" width="20%">业务状态</td>
				<td align="left" width="35%">
					<p>${cp:MAPVALUE("oa_bizstate",bizstate)}</p></td>
				<td class="addTd" width="20%">申请状态</td>
				<td align="left" width="35%"><p>${cp:MAPVALUE("meetState",state)}</p>
				</td>
			</tr>
			<c:if test="${not empty solvetime  }">
				<tr>
					<td class="addTd">办结时间</td>
					<td align="left"><s:property value="%{solvetime}" /></td>
				</tr>
			</c:if>
			<c:if test="${ not empty solvenote  }">
				<tr>
					<td class="addTd">办结意见</td>
					<td align="left" colspan="3"><s:property value="%{solvenote}" />
					</td>
				</tr>
			</c:if>
		</table>
<br>
	<c:if test="${not empty doBegTime}">
 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">安排信息</div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="div_wrapper_table">

				<tr>
					<td class="addTd">安排开始时间</td>
					<td align="left"><p><s:date name="doBegTime"
							format="yyyy-MM-dd HH:mm" /></p></td>
					<td class="addTd">安排结束时间</td>
					<td align="left"><p><s:date name="doEndTime"
							format="yyyy-MM-dd HH:mm" /></p></td>
				</tr>
				<tr>
					<td class="addTd">安排人</td>
					<td align="left"><p>
						<%-- 	<s:property value="%{doCreater}" /> --%>
						${cp:MAPVALUE('usercode',doCreater)}</p>
					</td>
					<td class="addTd">安排部门</td>
					<td align="left"><p>
						<%-- <s:property value="%{doDepno}" /> --%>
						${cp:MAPVALUE('unitCode',doDepno)}</p>
					</td>
				</tr>
				<tr>
					<td class="addTd">安排备注</td>
					<td align="left" colspan="3"><p><s:property value="%{doRemark}" /></p>
					</td>

				</tr>
			</table>
	</c:if>



	<c:if test="${not empty begTime}">
		<fieldset>
			<legend> 反馈信息 </legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="viewTable">

				<tr>
					<td class="addTd">实际开始时间</td>
					<td align="left"><s:date name="begTime"
							format="yyyy-MM-dd HH:mm" /></td>
					<td class="addTd">实际结束时间</td>
					<td align="left"><s:date name="endTime"
							format="yyyy-MM-dd HH:mm" /></td>
				</tr>
				<tr>
					<td class="addTd">安排备注</td>
					<td align="left" colspan="3"><s:property value="%{todoremark}" />
					</td>

				</tr>
			</table>
		</fieldset>
	</c:if>
	
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable div_idea" style="margin-top: 0px; ">
		<%@ include file="/page/common/idea.jsp"%>
	</table>
</body>
<script type="text/javascript">

function print(){
	printView(function(LODOP){
		LODOP.PRINT_INIT("会议申请单打印");
		LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
		var html = document.getElementsByTagName("html")[0].innerHTML;
		var removeHtml = $("<p>").append($(".group-title").clone()).html();
		LODOP.ADD_PRINT_HTM(100,0,"100%","100%",html.replace(removeHtml,""));
	});
}
</script>
</html>


