<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
	<script type="text/javascript" data-main="${pageContext.request.contextPath }/scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
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
	
	 <s:form action="oaLeaveOverTime" namespace="/oa" style="margin-top:0;margin-bottom:5">
	 
	 <table cellpadding="0"  cellspacing="0"  align="center">
				<tr>
<%-- 					<td><font color="red" style="font-size: 20px;">&nbsp;&nbsp;${nowStr }</font></td> --%>
					
					<td class="addTd">请假人：</td>
					<td align="left" ><input type="text" class="autocomplete"   name="s_usercode"  data-token-limit='1' 
							      	data-pre-populate='${populate}' 
									data-url="${pageContext.request.contextPath}/oa/oaLeaveOverTime!selectUser.do" /></td>
									
				    <td class="addTd">请假类型：</td>
				<td align="left">
				<select data-rel="chosen" name="s_reasonType" >
								<option value="" >---请选择---</option>
								<c:forEach var="v" items="${cp:LVB('LeaveOvertime')}">
								<option <c:if test="${ s_reasonType eq v.value}">selected="selected"</c:if> value="${v.value }"><c:out value="${v.label}" /></option>
								</c:forEach>
				</select>
				</td>
				</tr>
<!-- 				<tr> -->
				
<!-- 				<td class="addTd">考勤月份：</td> -->
<!-- 					<td align="left"><input type="text" class="Wdate" id="s_auditDate" -->
<%-- 						value="${param['s_auditDate']}" name="s_auditDate" --%>
<!-- 						onclick="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="选择统计年月"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				 <tr>
				    <td class="addTd">请假时间范围：</td>
					<td align="left" colspan="3">
					
					<input type="text" class="Wdate" id="s_duringDateBeg"
						<c:if test="${not empty s_duringDateBeg }"> value="${s_duringDateBeg}" </c:if>
						<c:if test="${empty s_duringDateBeg  }">value="${param['s_duringDateBeg'] }"</c:if>
						name="s_duringDateBeg"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择统计年月">
						至 <input type="text" class="Wdate" id="s_duringDateEnd"
						<c:if test="${not empty s_duringDateEnd }"> value="${s_duringDateEnd }" </c:if>
						<c:if test="${empty s_duringDateEnd  }"> value="${param['s_duringDateEnd'] }" </c:if>
						name="s_duringDateEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择统计年月">
						
					</td>
					<td>
					<s:submit method="addDetail"
								id="a_list_mail_box_delete"  cssClass="btn"
								value="新增"></s:submit>
					<s:submit method="listDetail"  key="opt.btn.query" 
							cssClass="btn" ></s:submit>
					</td>
				</tr> 
				<tr >
<!-- 					<td colspan="4" > -->
					
						</td>
<!-- 					<td colspan="2"> -->
<%-- 					<s:submit method=""
								id="a_list_mail_box_delete"  cssClass="btn"
								value="通用汇总"></s:submit>
					<s:submit method="editDraft3"   cssClass="btn"
							value="汇总"></s:submit> --%>
<!-- 						</td> -->
				</tr> 
		</table>			
	 </s:form>
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
</html>