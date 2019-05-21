<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<title>添加执法人员</title>
    <c:out value="${cp:MAPVALUE('unitcode',unitForm.map.unitcode)}"/>
    <c:out value="${cp:MAPVALUE('usercode',unitForm.map.usercode)}"/>
    <link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>" type="text/css" rel="stylesheet">
    <script language="javascript" src="<s:url value="/scripts/autocomplete/autocomplete.js"/>" type="text/javascript"></script>
    <script language="javascript" src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
<style type="text/css">
	.viewTable td { width:37%; }
	.viewTable td.addTd { width:13%; }
</style>
	
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			添加执法人员
		</legend>
		<s:form action="lawmen" method="post" namespace="/powerbase" id="lawmenForm" enctype="multipart/form-data">
			<input type="button" class="btn" value="保存" onclick="save();"/>
			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" width="20%"><span style="color: red">*</span><s:text name="lawmen.userName" /></td>
					<td align="left"><s:textfield name="userName"  value="%{object.userName}" /></td>
					<td class="addTd" ><font color="red"><strong>*&nbsp;</strong></font>出生年月</td>	
				<td align="left">
				<sj:datepicker id="birth" name="birth"  style="width:140px" readonly="true"
			      yearRange="2000:2020"  displayFormat="yy-mm-dd" changeYear="true"  
			    value="%{object.birth}"/>
		       </td>
			</tr>
			<tr>
					<td class="addTd" width="130"><span style="color: red">*</span>工作单位</td>
					<td align="left" ><input type="text" id="orgName" name="orgName"  value="${cp:MAPVALUE('unitcode',param.deptcode)}"/>
					<input type="hidden" id="deptcode" name="deptcode" value="${param.deptcode}"/></td>
					<td class="addTd" width="20%"><span style="color: red">*</span>系统用户编码</td>
					<td align="left"> <s:textfield name="usercode" onclick="selectUser(this)" id="usercode"  style="width:140px;" /></td>
					</tr>
			<tr>
			<td class="addTd" ><span style="color: red">*</span><s:text name="lawmen.tel" /></td>	
				<td class="left" ><s:textfield name="tel"  value="%{object.tel}" /></td>
				<td class="addTd" width="130"><span style="color: red">*</span><s:text name="lawmen.sex" /></td>
					<td align="left">
					<select name="sex" >
							<c:forEach var="row" items="${cp:DICTIONARY('sex') }">
								<option value="${row.key}"
									<c:if test="${object.sex eq row.key}"> selected="selected"</c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
							</select></td>
			</tr>
			<tr>
					<td class="addTd" width="130"><span style="color: red">*</span><s:text name="lawmen.organization" /></td>
					<td align="left"><select name="organization" >
							<option value="">---------请选择---------</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ORGANIZATION')}">
								<option value="${row.key}"
									<c:if test="${object.organization eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd" width="130"><span style="color: red">*</span><s:text name="lawmen.politicalLandscape" /></td>
					<td align="left"><select name="politicalLandscape" >
							<option value="">-----------请选择----------</option>
							<c:forEach var="row" items="${cp:DICTIONARY('POLLANDSCAPE')}">
								<option value="${row.key}"
									<c:if test="${object.politicalLandscape eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
			<tr>
					<td class="addTd" width="130"><span style="color: red">*</span><s:text name="lawmen.education" /></td>
					<td align="left"><select name="education" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('EDUCATION')}">
								<option value="${row.key}"
									<c:if test="${object.education eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				<td class="addTd" width="130"><span style="color: red">*</span><s:text name="lawmen.position" /></td>
					<td align="left"><select name="position" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('POSITION')}">
								<option value="${row.key}"
									<c:if test="${object.position eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
					</tr>
				<tr>
					<td class="addTd" width="20%"><span style="color: red">*</span><s:text name="执法证编号" /></td>
					<td align="left"> <s:textfield name="userId"  value="%{object.userId}" /></td>
					<td class="addTd" width="130"><s:text name="执法证有效期" /></td>
					<td align="left"><s:textfield name="legalArea"  value="%{object.legalArea}" /></td>
				</tr>
				<tr>
					<td class="addTd" width="130"><s:text name="发证机关" /></td>
					<td align="left"><s:textfield name="giveCertOrgan"  value="%{object.giveCertOrgan}" /></td>
				<td class="addTd" ><span style="color: red">*</span><s:text name="执法种类" /></td>	
				<td class="left" width="20%"><s:textfield name="legalRange"  value="%{object.legalRange}" /></td>
				</tr>
				</table>
		</s:form>
	</fieldset>
	</body>
	<script type="text/javascript">
	function bindEvent(o1,o2,$this){
		o1.val($this.html());
		o2.val($this.attr("rel"));
		if(getID("shadow")){
			$("#shadow").hide();
			$("#boxContent").hide();
		}
	}
	$("#orgName").bind('click',function(){
		var menuList = ${unitsJson};
		var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
		if(getID("shadow")){
			$("#shadow").show();
			$("#boxContent").show();
		}else{
			$("body").append(shadow);
			$("#shadow").height(document.body.scrollHeight);
			setTimeout(function(){
				menuDisplay(menuList,"${parentunit}");	
			},0);
		};
		$("#lists span").live('click',function(){
			var $this = $(this);
			bindEvent($("#orgName"),$("#deptcode"),$this);
			$("#lists span").die("click");
		});
	});
	function save(){
		  var userName=document.getElementById("userName").value;
	     var userId=document.getElementById("userId").value;
	     var deptcode=document.getElementById("deptcode").value;
	     var usercode=document.getElementById("usercode").value;
	     var birth=document.getElementById("birth").value;
	     var politicalLandscape=document.getElementById("politicalLandscape").value;
	     var education=document.getElementById("education").value;
	     var tel=document.getElementById("tel").value;
	     var position=document.getElementById("position").value;
	     var organization=document.getElementById("organization").value;
	     var legalArea=document.getElementById("legalArea").value;
	     var giveCertOrgan=document.getElementById("giveCertOrgan").value;
	     var legalRange=document.getElementById("legalRange").value;
	     if(""==userName){
	    	 alert("请填写姓名");
	    	 document.forms[0].userName.focus();
	    	 return;
	     }
	     if(""==birth){
	    	 alert("请选择出生年月");
	    	 document.forms[0].birth.focus();
	    	 return;
	     }
	     if(""==deptcode){
	    	 alert("请选择工作单位");
	    	 document.forms[0].deptcode.focus();
	    	 return;
	     }
	     if(""==usercode){
	    	 alert("请选择系统用户编码");
	    	 document.forms[0].usercode.focus();
	    	 return;
	     }
	     if(""==tel){
	    	 alert("请填写电话号码");
	    	 document.forms[0].tel.focus();
	    	 return;
	     }
	     if(""==organization){
	    	 alert("请选择编制情况");
	    	 document.forms[0].organization.focus();
	    	 return;
	     }
	     if(""==politicalLandscape){
	    	 alert("请选择政治面貌");
	    	 document.forms[0].politicalLandscape.focus();
	    	 return;
	     }
	     if(""==education){
	    	 alert("请选择学历");
	    	 document.forms[0].education.focus();
	    	 return;
	     }
	    
	     if(""==position){
	    	 alert("请选择职务");
	    	 document.forms[0].position.focus();
	    	 return;
	     }
	     if(""==userId){
	    	 alert("请填写执法证编号");
	    	 document.forms[0].userId.focus();
	    	 return;
	     }
	     if(""==legalArea){
	    	 alert("请填写执法证有效期");
	    	 document.forms[0].legalArea.focus();
	    	 return;
	     }
	    
	     if(""==giveCertOrgan){
	    	 alert("请填写发证机关");
	    	 document.forms[0].giveCertOrgan.focus();
	    	 return;
	     }
	     if(""==legalRange){
	    	 alert("请填写执法种类");
	    	 document.forms[0].legalRange.focus();
	    	 return;
	     }
	     var form=document.getElementById("lawmenForm");
	     form.action="lawmen!lawmenSave.do?flag=3";     
	     form.submit();
	     
	}
	 var list = [];
     <c:forEach var="userinfo" varStatus="status" items="${userlist}">
         list[${status.index}]= { username:'<c:out value="${userinfo.username}"/>', loginname:'<c:out value="${userinfo.loginname}"/>', usercode:'<c:out value="${userinfo.usercode}"/>',pinyin:'<c:out value="${userinfo.usernamepinyin}"/>'  };
     </c:forEach>
     function selectUser(obj) {
            userInfo.choose(obj, {dataList:list,userName:$('#userName')});
     }
	</script>
</html>
