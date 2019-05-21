<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.tokeninput/styles/token-input-facebook.css" rel="stylesheet" type="text/css" />
	<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
	</script>
		<title>请假加班出差列表</title>
		<script type="text/javascript" >
		</script>
	</head>

	<body>
	<%@ include file="/page/common/messages.jsp"%>
	<br>
	
	<fieldset class="search">
		<legend>
		<font color="red" style="font-size: 20px;">&nbsp;&nbsp;${nowStr }</font>
		</legend>
	<div class="searchDiv">
	 <s:form id="oaleaveovertimedetailform"  method="post" action="oaLeaveOverTime" namespace="/oa" style="margin-top:0;margin-bottom:5">
	 <div class="searchArea">
	 <table style="width: auto;">
				<tr >
				<td class="searchBtnArea" style="width: 100px;">
				<s:submit method="addDetail"
								id="a_list_mail_box_delete"  cssClass="whiteBtnWide"
								value="新增"></s:submit></td>
					<td class="searchBtnArea" style="padding-right:0">
					<label class="searchCondTitle">请假类型：</label>	
					<select data-rel="chosen" name="s_reasonType" class="searchCondContent">
								<option value="" >---请选择---</option>
								<c:forEach var="v" items="${cp:LVB('LeaveOvertime')}">
								<option <c:if test="${ s_reasonType eq v.value}">selected="selected"</c:if> value="${v.value }"><c:out value="${v.label}" /></option>
								</c:forEach>
					</select>
					<label class="searchCondTitle">请假人：</label>
					</td>
					<td class="searchBtnArea" style="padding-left:0;padding-right: 0;">	
					<input  type="text" class="autocomplete" style="background-color:#e6f0fa !important; color:#6b9bcf; "  name="s_usercode"  data-token-limit='1' 
							      	data-pre-populate='${populate}' 
									data-url="${pageContext.request.contextPath}/oa/oaLeaveOverTime!selectUser.do" />
					</td>
					<td class="searchBtnArea" style="padding-left: 0">
					<label class="searchCondTitle">请假时间范围：</label>	
					<input type="text" class="Wdate searchCondContent" style="height: 24px" id="s_duringDateBeg"
						<c:if test="${not empty s_duringDateBeg }"> value="${s_duringDateBeg}" </c:if>
						<c:if test="${empty s_duringDateBeg  }">value="${param['s_duringDateBeg'] }"</c:if>
						name="s_duringDateBeg"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择统计年月">
						<label class="searchCondTitle">至</label> <input type="text" class="Wdate searchCondContent" style="height: 24px" id="s_duringDateEnd"
						<c:if test="${not empty s_duringDateEnd }"> value="${s_duringDateEnd }" </c:if>
						<c:if test="${empty s_duringDateEnd  }"> value="${param['s_duringDateEnd'] }" </c:if>
						name="s_duringDateEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择统计年月">
						<img src="${contextPath }/newStatic/image/search.png" style="height:26px;vertical-align: bottom" onclick="sub()"/>
					</td>
					
					
				</tr> 
		</table>
		</div>			
	 </s:form>
	 </div>
	 </fieldset>
	<ec:table action="oaLeaveOverTime!listDetail.do"  items="objList"  var="obj"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			<ec:column property="usercode" title="请假人" style="text-align:center" >
						${cp:MAPVALUE('usercode',obj.usercode) }
			</ec:column>
			
				<%-- <ec:column property="reasonType" title="类型" style="text-align:center" >
						${cp:MAPVALUE('LeaveOvertime',obj.reasonType) }
				</ec:column> --%>
				<ec:column property="reasonDesc" title="[类型]事由" style="text-align:left"  sortable="false">
					[${cp:MAPVALUE('LeaveOvertime',obj.reasonType) }]<c:choose>
							<c:when test="${fn:length(obj.reasonDesc) gt 10 }">${fn:substring(obj.reasonDesc , 0, 10) }...</c:when>
							<c:otherwise>${obj.reasonDesc} </c:otherwise>
						</c:choose>	
				</ec:column>
				<ec:column property="createDate1" title="请假时间" style="text-align:center"  sortable="false">
					<fmt:formatDate value="${obj.beginTime }" pattern="yyyy-MM-dd" />至
					<fmt:formatDate value="${obj.endTime }" pattern="yyyy-MM-dd" />
				</ec:column>
				
				<ec:column property="proposer" title="登记人" style="text-align:center" >
						${cp:MAPVALUE('usercode',obj.proposer) }
				</ec:column>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm" /></c:set>
					<a href="oaLeaveOverTime!view.do?loroNo=${obj.loroNo}">查看</a>
					<a href="oaLeaveOverTime!editDetail.do?loroNo=${obj.loroNo}">编辑</a>
					<a href='oaLeaveOverTime!deleteDetail.do?loroNo=${obj.loroNo}' 
							onclick='return confirm("${deletecofirm}?");'>删除</a>
				</ec:column>

			</ec:row>
		</ec:table> 
	</body>
	<script type="text/javascript">
	function sub(){
		$("#oaleaveovertimedetailform").attr("action","oaLeaveOverTime!listDetail.do");
		$("#oaleaveovertimedetailform").submit();
	} 
	
	</script>
</html>