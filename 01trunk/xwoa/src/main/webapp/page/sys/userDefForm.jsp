<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 


<html>
<head><meta name="decorator" content='${LAYOUT}'/>
	<title>人员信息</title>
	<sj:head/>
	<s:include value="/page/common/formValidator.jsp"></s:include>
	<script type="text/javascript">
	/* 	function editAddressBook(usercode)
		{
			var addressid = window.showModalDialog("<c:url value='/sys/userdef.do?method=editAddressBook&usercode='/>"+usercode, "编辑通讯信息");
			//alert(addressid);
			window.location.reload();//.location.reload();
		} */
		$(document).ready(function(){
		
			$.formValidator.initConfig({formid:"form1",
				onerror:function(msg,obj,errorlist){
					alert(msg);
				}
			});
			$("#usercode").formValidator().inputValidator({min:1,max:8,onerror:"输入1到8个字符"})
			.regexValidator({regexp:"username",datatype:"enum",onerror:"输入字母或者数字"});
	 		$("#username").formValidator().inputValidator({min:1,max:64,onerror:"输入1到64个字符"}); 
			$("#loginname").formValidator().inputValidator({min:1,max:16,onerror:"输入1到16个字符"});
			$("#unitcode").formValidator().inputValidator({min:1,onerror:"请选择主机构"});
			$("#userstation").formValidator().inputValidator({min:1,onerror:"请选择岗位"});
			$("#userrank").formValidator().inputValidator({min:1,onerror:"请选择行政职务"});
			$("#officePhone").formValidator().inputValidator({min:1,onerror:"输入办公电话"});
			$("#userorder").formValidator().inputValidator({min:1,max:4,onerror:"输入1到4个数字"})
			.regexValidator({regexp:"intege",datatype:"enum",onerror:"输入数字"});
			$("#sortid").formValidator().inputValidator({min:1,max:8,onerror:"输入1到8个数字"})
			.regexValidator({regexp:"intege",datatype:"enum",onerror:"输入数字"});
			
			
		});

	</script>	
	
<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.tree .last_collapsable{background: url(../themes/blue/images/tree1.png) center 5px no-repeat !important;margin-left: 17px;margin-top: 9px;margin-right: 5px;float: left;}
	.tree .last_expandable{float:left;}
</style>
</head>

<body class="sub-flow">
<fieldset style="padding:10px;" class="form">
	<legend class="ctitle" style="width:auto;margin-bottom:10px;">用户信息</legend>

<s:form action="userDef" id="form1">
	<c:if test="${empty underUnit}">
	<s:submit method="save" cssClass="btn" value="保存" />
	</c:if>
	<c:if test="${not empty  underUnit}">
	<s:hidden name="underUnit" value="T"></s:hidden>
	<s:submit method="saveUnderUnit" cssClass="btn" value="保存" />
	</c:if>
	<c:if test="${object.isvalid eq 'T'}">
		<s:submit method="resetpwd" cssClass="btn" value="重置密码" onclick="return confirm('是否重置该用户密码?');" />
	</c:if> 
	<%-- <c:if test="${ not empty object.addrbookid }">
	<input type="button"  value="编辑通讯录信息" Class="btn"  onclick="window.location.href='userDef!editAddressBook.do?usercode=${object.usercode}'"/>
	</c:if> --%>
	<input type="button"  value="返回" Class="btn"  onclick="window.history.back()"/>
	<br>
		
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  <tr>
    <td class="addTd">用户代码<font color='red'>*</font></td>
    <td><label>
    <c:if test="${not empty object.usercode}"><s:textfield name="usercode" value="%{object.usercode}" style="width:200px;height:25px;"  readonly="true"/></c:if>
    <c:if test="${empty object.usercode}"> <s:textfield id="usercode" name="usercode"  style="width:200px;height:30px;" /></c:if>
    <span id="usercodeTip" ></span>
    </label></td>
   </tr>
    <tr> 
    <td class="addTd">用户名<font color='red'>*</font></td>
    <td><label>
      <s:textfield   id="username"  name="username"  style="width:200px;height:30px;" />
       <span id="usernameTip" style="line-height: "></span>
    </label></td>
  </tr>
    <tr> 
     	<td class="addTd">登录名<font color='red'>*</font></td>
    <td><label>
      <s:textfield  id="loginname"  name="loginname" style="width:200px;height:30px;" />
      <span id="loginnameTip" ></span>
    </label></td>
  	</tr>
  	<tr>
		<td class="addTd">
			主机构
		</td>
		<td align="left">
		
					<input type="hidden" id="unitcode" name="userUnit.unitcode" value="${userUnit.unitcode }">
					<input type="text" id="orgName" name="orgName" style="height:30px;width:200px;" value="${cp:MAPVALUE('unitcode',userUnit.unitcode)}">
			<span id="unitcodeTip"></span>
		</td>
	</tr>
	<tr>
		<td class="addTd">
			岗位
		</td>
		<td align="left" >
		
			<select id="userstation" name="userUnit.userstation"  style="width:200px;height:35px;">      
				<option value="">---请选择---</option>
				<c:forEach var="row" items="${cp:LVB('StationType')}">       
					<option value="${row.value}"
					<c:if test="${row.value==userUnit.userstation}">selected="selected"</c:if>>  
						<c:out value="${row.label}"/>   
					</option>       
				</c:forEach>         
			</select> 
	
			<span id="userstationTip"></span>
		</td>
	</tr>
	<tr>
		<td class="addTd">
			行政职务
		</td>
		<td align="left" >
			
	
			<select id="userrank" name="userUnit.userrank" style="width:200px;height:35px;"> 
				<option value="">---请选择---</option>     
				<c:forEach var="row" items="${cp:LVB('RankType')}">       
					<option value="${row.value}"
					<c:if test="${row.value==userUnit.userrank}">selected="selected"</c:if>> 
						<c:out value="${row.label}"/>   
					</option>       
				</c:forEach>         
			</select> 
		
			<span id="userrankTip"></span>
		</td>
	</tr>	
	<tr>
		<td class="addTd">行政级别</td>
		<td><s:textfield id="userorder" name="userorder" value="%{object.userorder}" style="width:200px;height:30px;"></s:textfield>  
		<span id="userorderTip" ></span></td>
	
	</tr>
	<tr>
		<td class="addTd">办公电话</td>
		<td><s:textfield id="officePhone" name="officePhone" value="%{object.officePhone}" style="width:200px;height:30px;"></s:textfield> 
		 <span id="officePhoneTip" ></span></td>
	</tr>
	<tr>
		<td class="addTd">排序号</td>
		<td><s:textfield id="sortid" name="sortid" value="%{object.sortid}" style="width:200px;height:30px;"></s:textfield> 
		 <span id="sortidTip" ></span></td>
	</tr>
     <tr> 
     	<td class="addTd">用户状态</td>
    <td>
    <s:radio name="isvalid" list="#{'T':'启用','F':'禁用' }" listKey="key" listValue="value" ></s:radio>
		
  	</td>
  	</tr>

  <tr>
    <td class="addTd">用户说明</td>
    <td >
      <label>
        <s:textarea name="userdesc" style="width:300px;height:60px;" />
        </label>
    </td>
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
			var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree' style='background-color: #DDDDDD;'>Loader</div><div id='close'>×</div></div>";
			if(getID("shadow")){
				$("#shadow").show();
				$("#boxContent").show();
			}else{
				$("body").append(shadow);
				$("#shadow").height(document.body.scrollHeight);
				setTimeout(function(){
					menuDisplay(menuList,"0");	
				},0);
			};
			$("#lists span").live('click',function(){
				var $this = $(this);
				bindEvent($("#orgName"),$("#unitcode"),$this);
				$("#lists span").die("click");
			});
		});

	</script>
</html>
