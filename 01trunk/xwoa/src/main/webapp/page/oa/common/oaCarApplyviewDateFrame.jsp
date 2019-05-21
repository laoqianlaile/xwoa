<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title></title>
</head>
<body class="sub-flow">
<fieldset>
  <s:form>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable" >		
              <tr>
	             <td class="addTd">
						标题
					</td>
					<td align="left" colspan="3" >${oaCarApply.transaffairname}
					</td>
	
				</tr>
               <tr>
					<td class="addTd" style="white-space:nowrap;width:13%;">
						用车事由
					</td>
					<td align="left" >
						${oaCarApply.reqRemark }
					</td>
					<td class="addTd"style="white-space:nowrap;width:13%;">
						乘车人数
					</td>
					<td align="left">
						${oaCarApply.meetingPersionsNum }
					</td>
				</tr>
				<tr>
					<td class="addTd" style="white-space:nowrap;width:13%;">
						申请开始时间
					</td>
					<td align="left" >
						<fmt:formatDate value='${oaCarApply.planBegTime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
					<td class="addTd"style="white-space:nowrap;width:13%;">
						申请结束时间
					</td>
					<td align="left">
						<fmt:formatDate value='${oaCarApply.planEndTime}' pattern='yyyy-MM-dd HH:mm' />
					</td>
				</tr>
					<tr>
					<td class="addTd">
						申请人
					</td>
					<td align="left" colspan="1">
						${cp:MAPVALUE('userCode',oaCarApply.creater)}
					</td>
					<td class="addTd">
						申请部门
					</td>
					<td align="left" colspan="1">
						${cp:MAPVALUE('unitcode',oaCarApply.depno)}
					</td>
				</tr>
				</tr>
					<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
						${oaCarApply.remark }
					</td>
					
				</tr>
  </table>
 </s:form>
</fieldset>
</body>
</html>