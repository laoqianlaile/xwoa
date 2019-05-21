<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><s:text name="oaKqvisitorgroup.edit.title" /></title>
</head>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p>编辑来访客团</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaKqvisitorgroup" method="post" namespace="/oa"
			id="oaKqvisitorgroupForm">
			<div class="formButtonOfMail">
				<s:submit name="save" method="save" cssClass="btn"
					key="opt.btn.save" />
				<s:submit type="button" name="back" cssClass="btn"
					key="opt.btn.back" />
			</div>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd"><s:text
							name="oaKqvisitorgroup.approvalremark" /><span style="color:red;">*</span></td>
					<td align="left" colspan="3"><s:textfield
							name="approvalremark" style="width:100%;height:30px;" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.jdtype" /><span style="color:red;">*</span></td>
					<td align="left"><c:forEach var="row"
							items="${cp:DICTIONARY('jdType')}" varStatus="status">
							<input type="radio" name="jdtype" value="${row.key}"
								${(object.jdtype eq row.key or (empty object.jdtype and status.index eq '0')) ? 'checked = "checked"' : ''} />
							<c:out value="${row.value}" />
						</c:forEach></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.approtime" /><span style="color:red;">*</span></td>
					<td align="left"><input type="text" class="Wdate"
						id="approtime" readonly="readonly"
						value='<fmt:formatDate value="${object.approtime}" pattern="yyyy-MM-dd"/>'
						name="approtime"
						style="height: 30px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})"
						placeholder="选择日期"></td>
				</tr>
				
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.bodycontent" /><span style="color:red;">*</span></td>
					<td align="left"><s:textfield name="bodycontent"
							style="width:100%;height:30px;" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.approval" /><span style="color:red;">*</span></td>
					<td align="left" colspan="3"><s:textfield name="approval"
							style="width:100%;height:30px;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.mealcase" /></td>
					<td align="left" colspan="3"><s:textfield name="mealcase"
							style="width:100%;height:30px;" /></td>
					
				</tr>
				<tr id="tr_receive">
				<td class="addTd"><s:text name="oaKqvisitorgroup.kqdepname" /><span style="color:red;">*</span></td>
					<td align="left"><s:textfield name="kqdepname"
							style="width:100%;height:30px;" /></td>
					<td class="addTd"><s:text name="oaKqvisitorgroup.meetplase" /><span style="color:red;">*</span></td>
					<td><input type="text" id="txa_innermsg_receive_name"
						name="meetplase" value="${object.meetplase }"
						class="focused required " style="width: 100%; height: 30px;"></td>
				</tr>

				<tr>

					<td class="addTd"><s:text name="oaKqvisitorgroup.leavetime" /><span style="color:red;">*</span></td>
					<td align="left">
					<c:forEach var="row"
							items="${cp:DICTIONARY('LUNCH_TYPE')}" varStatus="status">
							<input type="radio" name="leavetime" value="${row.key}"
								${(object.leavetime eq row.key or (empty object.leavetime and status.index eq '0')) ? 'checked = "checked"' : ''} />
							<c:out value="${row.value}" />
						</c:forEach>
					</td>
						<td class="addTd"><s:text name="oaKqvisitorgroup.mealplace" /></td>
					<td align="left">
					<c:forEach var="row"
							items="${cp:DICTIONARY('ZWSS_TYPE')}" varStatus="status">
							<input type="radio" name="mealplace" value="${row.key}"
								${(object.mealplace eq row.key or (empty object.mealplace and status.index eq '0')) ? 'checked = "checked"' : ''} />
							<c:out value="${row.value}" />
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.lodgingcase" /></td>
					<td align="left" colspan="3"><s:textfield name="lodgingcase"
							style="width:100%;height:30px;" /></td>
				</tr>
					<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.travel" /><span style="color:red;">*</span></td>
					<td align="left" colspan="3"><s:textfield name="travel"
							style="width:100%;height:40px;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaKqvisitorgroup.remark" /></td>
					<td align="left" colspan="3"><s:textfield name="remark"
							style="width:100%;height:40px;" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<script type="text/javascript">
	
	</script>