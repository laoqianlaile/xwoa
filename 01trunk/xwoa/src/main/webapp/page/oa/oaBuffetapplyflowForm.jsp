<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaBuffetapply.edit.title" /></title>
</head>

<body class="sub-flow">

<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>自助餐申请单</b>
		</legend>
	<s:form action="oaBuffetapply" method="post" namespace="/oa" id="oaBuffetapplyForm">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
		<input type="hidden" id="s_itemtype" name="s_itemtype" value="${s_itemtype}" />
		<input type="hidden" id="s_applyItemType" name="s_applyItemType" value="${s_applyItemType}" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd"><s:text
						name="oaBuffetapply.transaffairname" /><span style="color:red;">*</span></td>
				<td align="left" colspan="3"><s:textarea name="transaffairname" id="transaffairname" style="width:100%;height:30px;"/></td>
			</tr>
			<tr>
			<td class="addTd"><s:text name="oaBuffetapply.applydate" /><span style="color:red;">*</span>
				</td>
				<td align="left"><input type="text" class="Wdate"id="applydate" name="applydate"   style="width:200px;height:30px;"
			                  value='<fmt:formatDate value="${oaBuffetapply.applydate}"  pattern="yyyy-MM-dd"/>'  
			                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
				<td class="addTd"><s:text name="oaBuffetapply.layoutno" /></td>
				<td align="left"><s:textfield name="layoutno" style="width:100%;height:30px;"/></td>
				
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaBuffetapply.visitors" /><span style="color:red;">*</span></td>
				<td align="left"><s:textarea name="visitors" style="width:100%;height:40px;"/>
				</td>		
				<td class="addTd"><s:text name="oaBuffetapply.visitorsnum" /><span style="color:red;">*</span>
				</td>
				<td align="left"><s:textfield name="visitorsnum" style="width:100%;height:30px;"/></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaBuffetapply.remarkContent" />
				</td>
				<td align="left" colspan="3"><s:textarea name="remarkContent" id="remarkContent" style="width:100%;height:40px;"/></td>
			</tr>
				<c:if test="${not empty moduleParam }">
					<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr>
					<td class="addTd">${moduleParam.teamRoleName}<span style="color:red;">*</span></td>
					<td align="left" colspan="3">
					<input type="text" id="bjUserNames" name="bjUserNames" style="width: 100%;height:30px;" value="${bjUserNames}" 	readonly="readonly" />
					<input type="hidden" id="bjCodes"	name="teamUserCodes" value="" /> 
					<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />	
					</td>		
					</tr>
					</c:if>
				</c:if>
				
		</table>
	</s:form>
	</fieldset>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		
		//页面元素
		$("#bjUserNames").click(function(){
			 var d = '${userjson}';
		     if (d.trim().length) {
		    	 window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"));
			} 
		});
		
		//页面元素		
		$("#transaffairname").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("transaffairname",500);
		});
		$("#remarkContent").keyup(function(){
			CommonUtils.showTextAreaLimitedChSimple("remarkContent",4000);
		});
		
	});
	
	
	
	function checkForm(){
		if( document.oaBuffetapplyForm.transaffairname.value == ""){
			alert("主题标题不能为空");
			 document.oaBuffetapplyForm.transaffairname.focus();
			return false;
		}
		if( document.oaBuffetapplyForm.remarkContent.value == ""){
			alert("情况说明不能为空");
			 document.oaBuffetapplyForm.remarkContent.focus();
			return false;
		}
		
		if('${moduleParam.assignTeamRole}' == 'T'){
			if( document.oaBuffetapplyForm.bjUserNames.val() == ""){
				alert("选择办理人员不能为空");
				 document.oaBuffetapplyForm.bjUserNames.focus();
				return false;
				}
		}
			return true;
	}
	</script>
	</html>
	