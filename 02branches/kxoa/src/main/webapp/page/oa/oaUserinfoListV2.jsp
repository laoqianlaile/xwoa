<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title><s:text name="oaUserinfo.list.title" /></title>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
<style type="text/css">
	.eXtremeTable{margin-top:0!important}
	.searchDiv{margin-bottom:0!important;padding-bottom:0px!important}
	</style>
</head>

<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle">
			${cp:MAPVALUE("unitcode",bodycode)}内部通讯录
		</legend>
	<div class="searchDiv">
		<s:form action="oaUserinfo" namespace="/oa" id="oauserfolistv2form"
			>
			<div class="searchArea">
              <input type="hidden" name="bodycode" value="${bodycode }"/>
			<table style="width: auto;">
				<tr>
				<td class="searchBtnArea">
				<s:submit method="exportExcelByPo" value="导出通讯录"
							cssClass="whiteBtnWide" /> 			
				</td>
				<c:if test="${bodycode eq '1'}">
				<td class="searchTitleArea">
					<s:checkbox cssClass="checkboxV2" label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" /><span style="color: #6b9bcf">包含下属机构</span>	
				</td>
				</c:if>
				<%-- <td class="searchTitleArea" >
				<label class="searchCondTitle" style="width: 35px;">性别:</label>
				 </td>
				<td class="searchCountArea">
				<s:radio name="s_sex" id="s_sex" cssStyle="color:#6b9bcf;background:none;border:none;vertical-align:middle"
							list="#{'1':'男','2':'女','':'全部' }" listKey="key"
							listValue="value" value="%{#parameters['s_sex']}"></s:radio>
				</td> --%>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 45px;">用户名:</label>
				 </td>
				<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_userName" value="${s_userName}" />
						<input type="hidden" name="s_usercode" value="${s_usercode}" />&nbsp;&nbsp;
				<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
				<script type="text/javascript">
						$(function(){
							initValue($("input[name=s_userName]"),$("#list"),"${pageContext.request.contextPath}/sys/userDef!getUsers.do",$("input[name=s_usercode]"));
						});
						</script>
				</td>
				 <td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
				<tr id="gaoji_more" style="display: none;">
				<c:if test="${bodycode eq '1'}"></c:if>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;"><s:text name="oaUserinfo.mobilephone" />:</label>
				 </td>
				<td class="searchCountArea" >
				<input class="searchCondContent" type="text" name="s_mobilephone"
						value="${s_mobilephone}" />
				</td>
				<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
				<td class="searchTitleArea">
				<label class="searchCondTitle" style="width: 65px;"><s:text name="oaUserinfo.telephone" />:</label>
				 </td>
				<td class="searchCountArea">
				<input class="searchCondContent" type="text" name="s_telephone" value="${s_telephone}" />
				</td>
				</tr> 
				   
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="oa/oaUserinfo!oaList.do" items="objList"
		var="oaUserinfo" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<input type="hidden" name="bodycode" value="${bodycode }"/>
		<ec:row>

			<c:set var="tusername">
				用户名
			</c:set>
			<ec:column property="username" title="${tusername}"
				style="text-align:center" >
				${cp:MAPVALUE("usercode",oaUserinfo.usercode)} 
			</ec:column>

			<c:set var="tsex">
				<s:text name='oaUserinfo.sex' />
			</c:set>
			<%-- <ec:column property="sex" title="${tsex}" style="text-align:center" >
			${cp:MAPVALUE("sex",oaUserinfo.sex)}
			</ec:column> --%>

			<c:set var="ttelephone">
				固定电话
			</c:set>
			<ec:column property="telephone" title="${ttelephone}"
				style="text-align:center" />

			<c:set var="tmobilephone">
				移动电话
			</c:set>
			<ec:column property="mobilephone" title="${tmobilephone}"
				style="text-align:center" />

			<c:set var="tworkplace">
				<s:text name='oaUserinfo.workplace' />
			</c:set>
			<ec:column property="workplace" title="${tworkplace}"
				style="text-align:center" />

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a class="check_email"
					href='oa/oaUserinfo!view.do?usercode=${oaUserinfo.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<c:if test="${cp:CHECKUSEROPTPOWER('NBTXL','edit') }">
				<a  class="bianji"
					href='oa/oaUserinfo!edit.do?usercode=${oaUserinfo.usercode}&bodycode=${bodycode}'><s:text
						name="opt.btn.edit" /></a>
				</c:if>
				<%-- <a href='oa/oaUserinfo!delete.do?usercode=${oaUserinfo.usercode}'
					onclick='return confirm("${deletecofirm}oaUserinfo?");'><s:text
						name="opt.btn.delete" /></a> --%>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
function selects(winName, winProps) {
	if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		var winUrl='<%=request.getContextPath()%>/oa/oaUserinfo!selectuser.do';

		window.open(winUrl, winName, winProps);
	} 
function syncRtx(){
	$.ajax({
		type:"post",
		url:"${ctx}/oa/oaUserinfo!syncUserInfoToRTX.do",
		dataType:"json",
		data:$("#oaUserinfo").serialize(),
		success:function(data){
			if(data){
				alert("同步成功");
			}else{
				alert("同步失败");
			}
		}
	});
}
function sub(){
	$("#oauserfolistv2form").attr("action","oaUserinfo!oaList.do");
	$("#oauserfolistv2form").submit();
}
function showgaoji(){
	$("#shouqi").show();
	$("#gaoji_more").show();
	$("#gaoji").hide();
}
function toshouqi(){
	$("#shouqi").hide();
	$("#gaoji_more").hide();
	$("#gaoji").show();
}
function gj(){
	var t=false;
	if($("input[name=s_mobilephone]").val().trim()!=""&&$("input[name=s_mobilephone]").val()!=null){
		t=true;
	}
	if($("input[name=s_telephone]").val().trim()!=""&&$("input[name=s_telephone]").val()!=null){
		t=true;
	}
	return t;
}
$(document).ready(function() {
	if(gj()){
		showgaoji();
	}
});
</script>
</html>
