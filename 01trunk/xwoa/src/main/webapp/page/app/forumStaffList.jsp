<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>

		<legend>内部讨论版</legend>

		<s:form id="forumListForm" namespace="/app"
			action="forumStaff" style="margin-top:0;margin-bottom:5"
			method="post">
			<input type="hidden"  name="cid.forumid" value="${empty objList ? '' : objList[0].forumid }" />
			<input type="hidden"  id="userids" name="userids" />
			<input type="hidden"   name="s_forumid" value="${s_forumid}" />
			
			
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd">申请人：</td>
					<td width="180">	
					<input type="text" class="autocomplete"   name="s_usercode"  data-token-limit='1' data-pre-populate='${populate}' data-url="${pageContext.request.contextPath}/oa/equipmentInfo!selectUser.do" /></td>
					<td>
					<s:submit method="list" cssClass="btn" value="查询"/>
<!-- 					<input type="submit" class="btn" -->
<!-- 						data-form="#forumListForm" value="查询" />  -->
					<s:submit method="confirmApplyJoin" value="批量审批通过" cssClass="btn btnWide" />
<!-- 					<input type="button" -->
<!-- 						class="btn" target="submit" data-form="#forumListForm" -->
<%-- 						data-href="${contextPath}/app/forumStaff!confirmApplyJoin.do" --%>
<!-- 						value="批量审批通过">  -->
					<input type="button" name="back" Class="btn" onclick="window.location.href='${pageContext.request.contextPath}/app/forum!manager.do?manager=1'" value="返回" />
					
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/app/forumStaff!list.do"
		items="objList" var="forumStaff"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
      
		<ec:row>
          <ec:column property="forumStaff.usercode" title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan" />' sortable="false" style="width:70px">		
					<input class="chk_one" type="checkbox" id="${forumStaff.usercode}" class="ecbox"  value="${forumStaff.usercode}">
		 </ec:column>

          
			<ec:column property="forum.forumid" title="讨论版名称" style="text-align:center">
				<a href=
				"${pageContext.request.contextPath}/app/thread1!list.do?forum.forumid=${forum.forumid}&pageBoardCood=infoM&s_forumid=${s_forumid}<c:if test="${ ! empty objList }">&cid.forumid=${objList[0].forumid}</c:if>" >
					${forum.forumname} </a>
			</ec:column>
			<ec:column property="usercode" title="申请人" style="text-align:center" >
			${cp:MAPVALUE('usercode', forumStaff.usercode)}
			</ec:column>
			
			<ec:column property="jointime" title="申请时间"
				style="text-align:center">
				<fmt:formatDate value="${forumStaff.jointime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
					<a  href="${contextPath }/app/forumStaff!confirmApplyJoin.do?
					cid.forumid=${forumStaff.forumid}
					&userids=${forumStaff.usercode}
					&s_forumid=${s_forumid}"> 
						审批通过
					</a>
					
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
// $("[name='quanxuan']").attr("target","false");
// 	$("[name='quanxuan']").bind('click',function(){
// 		if($("[name='quanxuan']").attr("target")=="false"){
// 		$("[name='quanxuan']").attr("target","true");
// 		$("input[class='ecbox']").attr("checked","true"); 
// 		}
// 		else{
// 			$("[name='quanxuan']").attr("target","false");
// 			$("input[class='ecbox']").removeAttr("checked");
// 		}	
// 	});

	$(function() {
		$('#chk_all').change(function() {
			var $checked = $(this).attr('checked');
			
			$.each($('input:checkbox.chk_one'), function(index, checkbox) {
				$(this).attr('checked', 'checked' == $checked);
				if('checked' == $checked) {
					$(this).parent('span').addClass($checked);
				}else {
					$(this).parent('span').removeClass($checked);
				}
			});
		});
		
		
		$('#chk_all, input:checkbox.chk_one').change(function() {
			var userids = [];
			$.each($('input:checkbox.chk_one:checked'), function(index, chk) {
				userids.push($(this).val());
			});
			
			$('#userids').val(userids.join(','));
		});
	});
	</script>
</html>