<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<sj:head locale="zh_CN" />
<!--以上为选择申请人组件使用的文件  -->
<title><s:text name="oaDriverInfo.edit.title" /></title>
</head>

<body>
   <fieldset class="form">
		<legend>
		<p class="ctitle">
			添加请假
	    </p>
		</legend> 

	<%@ include file="/page/common/messages.jsp"%>
	<form action="" method="post"
		id="oaLeaveOverTimeForm">
			<input type="hidden" id="loroNo" name="loroNo" value="${object.loroNo }" /><!-- object的主键 -->
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">请假类型：</td>
				<td align="left">${cp:MAPVALUE('LeaveOvertime',object.reasonType) }
				</td>
			</tr>
			<tr>
			<td class="addTd">申请人：</td>
				<td align="left">${cp:MAPVALUE('usercode',object.usercode) }</td>
			</tr>
			<tr>
				<td class="addTd">原因描述：</td>
				<td align="left">${object.reasonDesc }</td>
			</tr>
			<tr>
				<td class="addTd">开始时间：</td>
				<td align="left"><fmt:formatDate value="${object.beginTime}" pattern="yyyy-MM-dd"/></td>
			</tr>

			<tr>
				<td class="addTd">结束时间：</td>
				<td align="left"><fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="addTd">请假天数：</td>
				<td align="left">${object.holidayNum}</td>
			</tr>
		</table>
     <div class="formButton">
		<!-- <input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" /> -->
		<input type="button" name="back" Class="btn"
			onclick="history.back(-1);" value="返回" />
		</div>
	</form>
</fieldset>
</body>
</html>
