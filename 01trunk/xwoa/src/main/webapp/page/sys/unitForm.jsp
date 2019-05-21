<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>部门列表</title>
        <sj:head/>
	</head>

<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />

<body class="sub-flow">
	<fieldset style="padding:10px;" class="form">
		<legend style="margin-bottom:10px;">部门信息</legend>
	
	<s:form action="unit"  namespace="/sys" id="unitForm" theme="simple" >

	<s:submit method="save"  cssClass="btn" value="保存" />
	<input type="button" value="返回" Class="btn" onclick="window.history.back()" />
<c:if test="${underUnit!=null}">
	<s:hidden name="underUnit" value="T"></s:hidden>
	</c:if>

	<table cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd">部门编号</td>
			<td align="left"><s:textfield name="unitcode" value="%{unitcode}" readonly="true" style="width:200px;height:25px;" /></td>
		</tr>
		<tr>
			<td class="addTd">上级部门</td>
			<td>
			
			<input type="hidden" id="parentunit" name="parentunit" value="${object.parentunit}">
			<input type="text" id="orgName" name="orgName" style="width:200px;height:25px;"   value="${cp:MAPVALUE('unitcode',object.parentunit)}">
			
			</td>
		</tr>
		<tr>
			<td class="addTd">部门名称</td>
			<td align="left"><s:textfield id="unitname" name="unitname" vlaue="%{unitname}"  style="width:200px;height:25px;"  />
				<span id="unitnameTip"></span>
			</td>
		</tr>
		<tr>
			<td class="addTd">部门简称</td>
			<td align="left"><s:textfield id="unitshortname" name="unitshortname" vlaue="%{unitshortname}"  style="width:200px;height:25px;" />
				<span id="unitshortnameTip"></span>
			</td>
		</tr>
		<tr>
			<td class="addTd">部门编码</td>
			<td align="left"><s:textfield name="depno" value="%{depno}"  style="width:200px;height:25px;"  /></td>
		</tr>
		<tr>
			<td class="addTd">部门状态</td>
			<td align="left">
				<s:radio name="isvalid" list="#{'T':'启用','F':'禁用' }" value="%{isvalid}"></s:radio>
			
			</td>
		</tr>
		<tr>
			<td class="addTd">部门类型</td>
			<td>
				<select name="unittype" style="width:200px;height:25px;" >      
					<c:forEach var="row" items="${cp:LVB('UnitType')}">       
						<option value="<c:out value='${row.value}'/>"
						<c:if test="${row.value==object.unittype}">selected="selected"</c:if>> 
							<c:out value="${row.label}"/>   
						</option>       
					</c:forEach>         
				</select>  
			</td>
		</tr>		
			<tr>
			<td class="addTd">部门排序号</td>
			<td align="left"><s:textfield name="unitorder" value="%{unitorder}"  style="width:200px;height:25px;"  /></td>
		</tr>	
		<tr>
			<td class="addTd">部门备注</td>
			<td align="left"><s:textarea name="unitdesc" style="width:600px; height:50px;" /></td>
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
			if ("" != menuList) {
				var shadow = "<div id='shadow'></div><div id='boxContent'><div class='listShadow'></div><div id='lists' class='getTree'>Loader</div><div id='close'>×</div></div>";
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
					bindEvent($("#orgName"),$("#parentunit"),$this);
					$("#lists span").die("click");
				});
			}
		});

	</script>
</html>
