<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>执法人员信息</title>
<%-- <sj:head locale="zh_CN" /> --%>
<s:include value="/page/common/formValidator.jsp"></s:include>
	<script type="text/javascript">
		$(document).ready(function() {
			$.formValidator.initConfig({
				formid : "lawExecutorForm",
				onerror : function(msg, obj, errorlist) {
					alert(msg);
				}
			});
			$('#inspectDate').formValidator({
				onshow : "请选择 年检时间"
			}).inputValidator({
				min:1,
				onerror : "年检时间 是必选项"
			});
			$('#inspectValidate').formValidator({
				onshow : "请选择 有效期"
			}).inputValidator({
				min:1,
				onerror : "有效期 是必选项"
			});
			$('#inspectRemark').formValidator({
				onshow : "年检备注 是必填项"
			}).inputValidator({
				min:1,
				onerror : "年检备注 是必填项"
			});
		});
	</script>
</head>
<body>

	<%@ include file="/page/common/messages.jsp"%>

	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">执法人员信息</legend>
		<s:form action="lawexecutorInspectAction" method="post" namespace="/powerbase" id="lawExecutorForm">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			<input type="button" value="返回" Class="btn" onclick="window.location.replace('${backurl}')" />
			<s:hidden name="staffno"/>
			<!-- 返回参数 -->
			<s:hidden name="backurl"/>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="TDTITLE">年检类型</td>
					<td align="left">
						<select id="inspectType" name="inspectType" style="width: 100px">
					   		<c:forEach items="${cp:LVB('LAW_STATUS') }" var="row">
					     		<option value="${row.value }">
					      		<c:out value="${row.label}"></c:out>
					     		</option>
					   		</c:forEach>
					 	</select>
					</td>
					<td class="TDTITLE">年检时间</td>
					<td align="left">
						<sj:datepicker id="inspectDate" name="inspectDate"
						style="width:120px;" timepicker="false" timepickerFormat="hh:mm"
						displayFormat="yy-mm-dd" validator="input" min="1"
						errorshow="请输入年检时间" />
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">有效期</td>
					<td align="left">
						<sj:datepicker id="inspectValidate" name="inspectValidate"
							value="" style="width:120px;"
							timepicker="false" timepickerFormat="hh:mm"
							displayFormat="yy-mm-dd" validator="input" min="1"
							errorshow="请输入 有效时间" />
					</td>
					<td class="TDTITLE">年检备注</td>
					<td align="left"><s:textarea name="inspectRemark" id="inspectRemark" cols="40" rows="2" /></td>
				</tr>
			</table>
		</s:form>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="TDTITLE">流水号</td>
				<td align="left"><s:property value="%{staffno}" /></td>

				<td class="TDTITLE">证书编号</td>
				<td align="left"><s:property value="%{passcode}" /></td>
			</tr>

			<tr>
				<td class="TDTITLE">姓名</td>
				<td align="left"><s:property value="%{staffname}" /></td>
				<td class="TDTITLE" rowspan="3">证件照片</td>
				<td align="left" rowspan="3">
					<c:if
						test="${not empty object.cardphoto }">
						<img alt="证件照"
							src="<%=request.getContextPath()%>/powerbase/lawExecutor!downloadPhoto.do?staffno=${object.staffno}"
							width="75px" height="100px">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="TDTITLE">性别</td>
				<td align="left">${cp:MAPVALUE('sex',object.sex) }</td>
			</tr>
			<tr>
				<td class="TDTITLE">民族</td>
				<td align="left">${cp:MAPVALUE('LAW_NATION',object.nation) }</td>
			</tr>
			<tr>
				<td class="TDTITLE">身份证号</td>
				<td align="left"><s:property value="%{idcard}" /></td>
				<td class="TDTITLE">证件种类</td>
				<td align="left">${cp:MAPVALUE('LAW_CARDKIND',object.cardkind)
					}</td>
			</tr>
			<tr>
				<td class="TDTITLE">政治面貌</td>
				<td align="left">
					${cp:MAPVALUE('LAW_POLITICS',object.politics) }
				</td>

				<td class="TDTITLE">学历</td>
				<td align="left">
					${cp:MAPVALUE('LAW_EDUCATION',object.education) }
				</td>
			</tr>

			<tr>
				<td class="TDTITLE">执法主体</td>
				<td align="left"><s:property value="%{deptname}" /></td>

				<td class="TDTITLE">职务</td>
				<td align="left"><s:property value="%{position}" /></td>
			</tr>

			<tr>
				<td class="TDTITLE">电话</td>
				<td align="left"><s:property value="%{telephone}" /></td>

				<td class="TDTITLE">编制情况</td>
				<td align="left">
					${cp:MAPVALUE('LAW_PLAIT',object.plait) }
				</td>
			</tr>

			<tr>
				<td class="TDTITLE">发证时间</td>
				<td align="left">
					<c:if
						test="${not empty object.getpasstime }">
						<fmt:formatDate value="${object.getpasstime }"
							pattern="yyyy-MM-dd" />
					</c:if>
				</td>

				<td class="TDTITLE">发证部门</td>
				<td align="left"><s:property value="%{issueddept}" /></td>
			</tr>

			<tr>
				<td class="TDTITLE">执法区域</td>
				<td align="left"><s:property value="%{executionarea}" /></td>

				<td class="TDTITLE">执法种类</td>
				<td align="left"><s:property value="%{executionclass}" /></td>
			</tr>

			<tr>
				<td class="TDTITLE">职级</td>
				<td align="left">
					${cp:MAPVALUE('EXECUTIONJOB',object.executionjob) }
				</td>
				<td class="TDTITLE">执法证变更时间</td>
				<td align="left">
					<c:if
						test="${not empty object.changepasstime }">
						<fmt:formatDate value="${object.changepasstime }"
							pattern="yyyy-MM-dd" />
					</c:if>
				</td>
			</tr>

			<tr>
				<td class="TDTITLE">有效期</td>
				<td align="left">
					<c:if
						test="${not empty object.passlife }">
						<fmt:formatDate value="${object.passlife }"
							pattern="yyyy-MM-dd" />
					</c:if>
				</td>
				<td class="TDTITLE">人员状态</td>
				<td align="left">
					${cp:MAPVALUE('LAW_STATUS',object.status) }
				</td>
				
			</tr>
			
			<tr>
				<td class="TDTITLE">执法证状态</td>
				<td align="left">
					${cp:MAPVALUE('LAW_STAFFSTATUS',object.staffstatus) }
				</td>
				<td class="TDTITLE">备注</td>
				<td align="left"><s:property value="%{memo}" /></td>
			</tr>
			
			<tr>
				<td class="TDTITLE">录入人员</td>
				<td align="left">
					${cp:MAPVALUE('usercode', object.operator)}
				</td>
				<td class="TDTITLE">录入时间</td>
				<td align="left">
					<c:if
						test="${not empty object.inputtime }">
						<fmt:formatDate value="${object.inputtime }"
							pattern="yyyy-MM-dd hh:mm" />
					</c:if>
				</td>
			</tr>

			<tr>
				<td class="TDTITLE">审核状态</td>
				<td>
					${cp:MAPVALUE('LAW_AUDIT',object.auditIdeaCode)}
				</td>
				<td class="TDTITLE">修改日期</td>
				<td align="left"><c:if test="${not empty object.repairdate }">
						<fmt:formatDate value="${object.repairdate }"
							pattern="yyyy-MM-dd hh:mm" />
					</c:if></td>
			</tr>
			
			<tr>
				<td class="TDTITLE">审核人</td>
				<td align="left">
					${cp:MAPVALUE('usercode', object.auditUserCode)}
				</td>
				<td class="TDTITLE">审核时间</td>
				<td align="left">
					<c:if
						test="${not empty object.auditDate }">
						<fmt:formatDate value="${object.auditDate }"
							pattern="yyyy-MM-dd hh:mm" />
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="TDTITLE">审核意见说明</td>
				<td align="left" colspan="3"><s:property value="%{auditIdeaContent}" /></td>
			</tr>
		</table>
		
		<ec:table items="inspectList" var="lawexecutorInspect"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" showPagination="false" sortable="false">
			<ec:row>
				<ec:column property="inspectId" title="流水号" style="text-align:center" />
				
				<ec:column property="inspectType" title="年检类型" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_STATUS',lawexecutorInspect.inspectType)}
				</ec:column>
				
				<ec:column property="inspectDate" title="年检时间" style="text-align:center" />
				
				<ec:column property="inspectValidate" title="有效期" style="text-align:center" />
				
				<ec:column property="inspectRemark" title="备注" style="text-align:center" />
				
				<ec:column property="recorder" title="录入人" style="text-align:center" >
					${cp:MAPVALUE('usercode',lawexecutorInspect.recorder)}
				</ec:column>
				
				<ec:column property="recordDate" title="录入时间" style="text-align:center" />
			</ec:row>
		</ec:table>
	</fieldset>
</body>
</html>
