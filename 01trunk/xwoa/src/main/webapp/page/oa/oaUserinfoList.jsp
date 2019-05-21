<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUserinfo.list.title" /></title>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
</head>

<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
			${cp:MAPVALUE("unitcode",bodycode)}内部通讯录
		</legend>

		<s:form action="oaUserinfo" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
              <input type="hidden" name="bodycode" value="${bodycode }"/>
              <%-- <input type="hidden" name="s_usercode" id="usercode" value="${s_usercode }"/> --%>
				<tr>
				   <td class="addTd">
						用户名:
					</td>
					<td align="left">
					<div id="userDiv">
						<s:textfield name="userName"/>
						<input type="hidden" name="s_usercode"/>
						<ul id="list"></ul>
					</div>
						<script type="text/javascript">
						$(function(){
							initValue($("input[name=userName]"),$("#list"),"${pageContext.request.contextPath}/sys/userDef!getUsers.do",$("input[name=s_usercode]"));
						})
						</script>
					</td>
					<td class="addTd"><s:text name="oaUserinfo.sex" />:</td>
					<td>
							<select id="s_sex" name="s_sex" style="width: 138px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:LVB('sex')}">
									<option value="${row.value}" 
										<c:if test="${param.s_sex == row.value}">selected</c:if>>
										<c:out value="${row.label}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaUserinfo.mobilephone" />:</td>
					<td><input type="text" name="s_mobilephone"
						value="${s_mobilephone}" /></td>
					<td class="addTd"><s:text name="oaUserinfo.telephone" />:</td>
					<td><input type="text" name="s_telephone" value="${s_telephone}" />
					<c:if test="${bodycode eq '1'}">
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构	
					</c:if>
					</td>
				</tr>
				<tr class="searchButton">
				   <td colspan="3"></td>
					<td><s:submit method="oaList" key="opt.btn.query" cssClass="btn" />
					 <s:submit method="exportExcelByPo" value="导出通讯录"
							cssClass="btn btnWide" /> 			
					</td>
				</tr>
			</table>
		</s:form>
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
			<ec:column property="sex" title="${tsex}" style="text-align:center" >
			${cp:MAPVALUE("sex",oaUserinfo.sex)}
			</ec:column>

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
				<a
					href='oa/oaUserinfo!view.do?usercode=${oaUserinfo.usercode}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a>
				<c:if test="${cp:CHECKUSEROPTPOWER('NBTXL','edit') }">
				<a href='oa/oaUserinfo!edit.do?usercode=${oaUserinfo.usercode}&bodycode=${bodycode}'><s:text
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
	})
}
</script>
</html>
