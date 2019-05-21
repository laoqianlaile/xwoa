<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法人员管理
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 执法人员信息查询--审核
			</legend>
			
			<s:form action="lawExecutor" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td class="addTd" align="right">姓名:</td>
						<td><s:textfield name="s_staffname" value="%{#parameters['s_staffname']}"/></td>
						<td class="addTd" align="right">身份证号:</td>
						<td><s:textfield name="s_idcard" id="s_idcard"  value="%{#parameters['s_idcard']}" /> </td>
						<td class="addTd" align="right">证书编号:</td>
						<td><s:textfield name="s_passcode" value="%{#parameters['s_passcode']}"/></td>
						<td>
							<s:submit method="listCheck"  key="opt.btn.query" cssClass="btn"/>
							<s:reset value="重置" cssClass="btn"></s:reset>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<input type="button" class="btn" value="同意" name="agree" />
		<input type="button" class="btn" value="拒绝" name="reject" />
		<ec:table action="powerbase/lawExecutor!listCheck.do" items="objList" var="lawExecutor"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="lawExecutors.xls" ></ec:exportXls>
			<ec:row>
				<ec:column property="lawExecutor.staffno" title='<input type="button" class="btn" value="全选" name="quanxuan" />' sortable="false" style="width:70px">		
					<input type="checkbox" id="${lawExecutor.staffno}" class="ecbox"  value="${lawExecutor.staffno}">
				</ec:column>
				<ec:column property="passcode" title="证书编号" style="text-align:center" />

				<ec:column property="staffname" title="姓名" style="text-align:center" />

				<ec:column property="sex" title="性别" style="text-align:center">
					${cp:MAPVALUE('sex',lawExecutor.sex)}
				</ec:column>

				<ec:column property="nation" title="民族" style="text-align:center" >
					${cp:MAPVALUE('LAW_NATION',lawExecutor.nation)}
				</ec:column>

				<ec:column property="idcard" title="身份证号" style="text-align:center" />

				<ec:column property="politics" title="政治面貌" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_POLITICS',lawExecutor.politics)}
				</ec:column>

				<ec:column property="education" title="学历" style="text-align:center" >
				    ${cp:MAPVALUE('LAW_EDUCATION',lawExecutor.education)}
				</ec:column>

				<ec:column property="deptname" title="执法主体" style="text-align:center" />

				<ec:column property="position" title="职务" style="text-align:center" />

				<ec:column property="plait" title="编制情况" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_PLAIT',lawExecutor.plait)}
				</ec:column>
				
				<ec:column property="auditIdeaCode" title="审核状态" style="text-align:center" >
				   ${cp:MAPVALUE('LAW_AUDIT',lawExecutor.auditIdeaCode)}
				</ec:column>
				
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerbase/lawExecutor!view.do?staffno=${lawExecutor.staffno}&backurl=${backurl}'>详细</a>
					<c:if test="${lawExecutor.auditIdeaCode eq '1'}">
						<a href='powerbase/lawExecutor!check.do?staffno=${lawExecutor.staffno}&backurl=${backurl}'>审核</a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	$("[name='quanxuan']").attr("target","false");
	$("[name='quanxuan']").bind('click',function(){
		if($("[name='quanxuan']").attr("target")=="false"){
		$("[name='quanxuan']").attr("target","true");
		$("input[class='ecbox']").attr("checked","true"); 
		}
		else{
			$("[name='quanxuan']").attr("target","false");
			$("input[class='ecbox']").removeAttr("checked");
		}	
	});
	
	$("[name='agree']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		
		for (var i = 0; i < items.length; i++) {
		     // 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
		      //alert(items.get(i).value);
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
			if(confirm("是否同意您选择的执法人员的申报")){
				location.href= "<%=request.getContextPath()%>/powerbase/lawExecutor!batchCheck.do?auditIdeaCode=2&staffnos="+vfstring+"&backurl=${backurl}";
			}
		} else{
			alert("没有选中的办件");
		}
			
	}); 
	
	$("[name='reject']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		
		for (var i = 0; i < items.length; i++) {
		     // 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
		      //alert(items.get(i).value);
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
			if(confirm("是否拒绝您选择的执法人员的申报")){
				location.href= "<%=request.getContextPath()%>/powerbase/lawExecutor!batchCheck.do?auditIdeaCode=3&staffnos="+vfstring+"&backurl=${backurl}";
			}
		} else{
			alert("没有选中的办件");
		}
	}); 
	</script>
</html>
