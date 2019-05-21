<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 


<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>用户机构编辑--
			<c:out value="${cp:MAPVALUE('usercode',userUnit.id.usercode)}" />:
			<c:out value="${cp:MAPVALUE('unitcode',userUnit.id.unitcode)}"/> 
		</title>
	<style type="text/css">
		.tree .last_collapsable{background: url(../themes/blue/images/tree1.png) center 5px no-repeat !important;margin-left: 17px;margin-top: 9px;margin-right: 5px;float: left;}
		.tree .last_expandable{float:left;}
	</style>
<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<s:include value="/page/common/formValidator.jsp"></s:include>
<script type="text/javascript">
	/* 	function editAddressBook(usercode)
		{
			var addressid = window.showModalDialog("<c:url value='/sys/userdef.do?method=editAddressBook&usercode='/>"+usercode, "编辑通讯信息");
			//alert(addressid);
			window.location.reload();//.location.reload();
		} */
		$(document).ready(function(){
		
			$.formValidator.initConfig({formid:"userUnitForm",
				onerror:function(msg,obj,errorlist){
					alert(msg);
				}
			});
			
			$("#unitcode").formValidator().inputValidator({min:1,onerror:"请选择用户机构"});
			$("#userstation").formValidator().inputValidator({min:1,onerror:"请选择用户岗位"});
			$("#userrank").formValidator().inputValidator({min:1,onerror:"请选择行政职务"});
			
		});

	</script>
	
	</head>

	<body class="sub-flow">
		<fieldset style="padding:10px;"  class="form">
			<legend class="ctitle" style="width:auto;margin-bottom:10px;">用户机构编辑--
			<c:out value="${cp:MAPVALUE('usercode',userUnit.id.usercode)}" />:
			<c:out value="${cp:MAPVALUE('unitcode',userUnit.id.unitcode)}"/> </legend>
		
		<s:form id="userUnitForm" action="userDef.do" namespace="/sys"  theme="simple" >
<%-- 			<s:submit method="saveUserUnit" cssClass="btn" value="保存" /> --%>
			<s:hidden name="underUnit" value="%{underUnit}"></s:hidden>
			<!-- <input type="button" value="返回" class="btn" onclick="window.history.back()"/> -->
			
			<table cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">
						用户代码
					</td>
					<td align="left" >
						<s:textfield name="userUnit.id.usercode" value="%{userUnit.id.usercode}" readonly="true" style="width:140px;" theme="simple" /> 
					</td>
				</tr>
				<tr>
					<td class="addTd">
						用户名
					</td>
					<td align="left" >
						<c:out value="${cp:MAPVALUE('usercode',userUnit.id.usercode)}"/> 
					</td>
				</tr>
				<tr>
					<td class="addTd">
						用户机构
					</td>
					<td align="left"> 
					
					<input type="hidden" id="unitcode" name="userUnit.id.unitcode" value="${userUnit.id.unitcode }">
					<input type="text" id="orgName" name="orgName" value="${cp:MAPVALUE('unitcode',userUnit.id.unitcode)}">
					
					<span id="unitcodeTip"></span>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						是否为主单位
					</td>
					<td align="left" >
					<c:if test="${not empty userUnit.isprimary}">
     					<s:radio name="userUnit.isprimary" list="#{'T':'是','F':'否'}" listKey="key" listValue="value" value="%{userUnit.isprimary}"></s:radio>
     					</c:if>
     				<c:if test="${empty userUnit.isprimary}">
     					<s:radio name="userUnit.isprimary" list="#{'T':'是','F':'否'}" listKey="key" listValue="value" value="'F'"></s:radio>
     				</c:if>
					</td>				</tr>
				<tr>
					<td class="addTd">
						用户岗位
					</td>
					<td align="left" >
					<c:if test="${not empty userUnit.id.userstation }">
						<input type="hidden" name="userUnit.id.userstation" value="${userUnit.id.userstation }">
						<select  id="userstation" name="userUnit.id.userstation" disabled="disabled" style="width:140px;">
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('StationType')}">
               					 <option value="<c:out value='${row.value}'/>" 
               					 <c:if test="${row.value==userUnit.id.userstation}">selected="selected"</c:if>>
                  			 		 <c:out value="${row.label}"/>
              				  </option>
           					</c:forEach>
						</select>
					</c:if>
					
					<c:if test="${empty userUnit.id.userstation }">
						<select  id="userstation" name="userUnit.id.userstation" style="width:140px;">
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('StationType')}">
               					 <option value="<c:out value='${row.value}'/>" 
               					 <c:if test="${row.value==userUnit.id.userstation}">selected="selected"</c:if>>
                  			 		 <c:out value="${row.label}"/>
              				  </option>
           					</c:forEach>
						</select>
						</c:if>
						<span id="userstationTip"></span>
					</td>
					
				</tr>
				<tr>
					<td class="addTd">
						行政职务
					</td>
					<td align="left" >
					<c:if test="${not empty userUnit.id.userrank}">						
						<input type="hidden" name="userUnit.id.userrank" value="${userUnit.id.userrank }">
						<select  id="userrank"  name="userUnit.id.userrank" disabled="disabled" style="width:140px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('RankType')}">
               					 <option value="<c:out value='${row.value}'/>" 
               					 <c:if test="${row.value==userUnit.id.userrank}">selected="selected"</c:if>>
                  			 		 <c:out value="${row.label}"/>
              				  </option>
           					</c:forEach>
						</select>
					</c:if>
					<c:if test="${empty userUnit.id.userrank}">						
						<input type="hidden" name="userrank" value="${userUnit.id.userrank }">
						<select id="userrank"  name="userUnit.id.userrank" style="width:140px;">
						<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('RankType')}">
               					 <option value="<c:out value='${row.value}'/>" 
               					 <c:if test="${row.value==userUnit.id.userrank}">selected="selected"</c:if>>
                  			 		 <c:out value="${row.label}"/>
              				  </option>
           					</c:forEach>
						</select>
					</c:if>
					<span id="userrankTip"></span>
					</td>
				</tr>				
				<tr>
					<td class="addTd">
						授权说明
					</td>
					<td align="left">
						<s:textarea name="userUnit.rankmemo" style="width:600px;height:50px;" value="%{userUnit.rankmemo}" />
					</td>
				</tr>

			</table>
			<div class="formButton">
				<s:submit method="saveUserUnit" cssClass="btn" value="保存" />
				<input type="button" value="返回" class="btn" onclick="window.history.back()"/>
			</div>
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
