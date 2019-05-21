<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
		<script>
			document.createElement('template');
		</script>
		<title>测试</title>
		<link href="${pageContext.request.contextPath}/themes/css/extremecomponents.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/css/am.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/gray/style.css" rel="stylesheet" type="text/css" />
   		
	</head>

	<body>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			<s:form action="apply" namespace="/monitor" style="margin-top:0;margin-bottom:5" id="applyForm" method="post" >
				<table cellpadding="0" cellspacing="0" align="center" class="norap">
					<tr>
						<td>日期：</td>
						<td>
							<input type="text" class="Wdate" name="date" />
						</td>
						<td>时间：</td>
						<td>
							<input type="text" class="Wdate" data-type="time" name="time" />
						</td>
					</tr>
					
					<tr>
						<td>下拉框：</td>
						<td>
							<select name="testSelect">
								<option value="">——请选择——</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</td>
						<td>下拉框（固定值）：</td>
						<td>
							<select data-value="4" name="testSelect2">
								<option value="">——请选择——</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>多选框：</td>
						<td>
							<input type="checkbox" name="checkbox1" value="1" />1
							<input type="checkbox" name="checkbox1" value="2" />2
							<input type="checkbox" name="checkbox1" value="3" />3
						</td>
						<td>多选框（固定值）：</td>
						<td>
							<input type="checkbox" name="checkbox2" value="1" data-value='["2", "3"]' />1
							<input type="checkbox" name="checkbox2" value="2" />2
							<input type="checkbox" name="checkbox2" value="3" />3
						</td>
					</tr>
					
					<tr>
						<td>单选框：</td>
						<td>
							<input type="radio" name="radio1" value="1" data-value="2" />1
							<input type="radio" name="radio1" value="2" />2
							||
							<input type="radio" name="radio2" value="0" data-value="true" />假
							<input type="radio" name="radio2" value="1" />真
						</td>
						<td>单选框（固定值）：</td>
						<td>
							<span class="radio-group" data-value="2">
								<input type="radio" name="radio3" value="1" />1
								<input type="radio" name="radio3" value="2" />2
							</span>
							||
							<span class="radio-group" data-value="true">
								<input type="radio" name="radio4" value="0" />假
							<input type="radio" name="radio4" value="1" />真
							</span>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<s:submit method="fgList" key="opt.btn.query" cssClass="btn" />
							
							<input type="reset" class="btn" name="reset" value="重置" />
							
							<c:if test="${opt eq 'compare'}">
								<input type="button" class="btn" value="对比" name="compare" id="compare" onclick="doCompare();"/>
							</c:if>
							
							<input type="button" class="btn" target="navTab" data-href="www.baidu.com" 
								data-rel="baidu1" title="百度1" value="百度1" /> 
								
							<input type="button" class="btn" target="dialog" data-href="www.baidu.com" 
								data-rel="baidu2" title="百度2" value="百度2" /> 
								
							<input type="button" class="btn" target="submit" value="直接提交" />
							<input type="button" class="btn" target="submit" data-href="www.baidu.com" value="提交其他方法" />
						</td>

					</tr>
				</table>
			</s:form>
		</fieldset>
		
		<fieldset>
		
			<legend>验证框架</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">必填项：</td>
						<td align="left">
							<input name="id" class="required"type="text" />
						</td>
						<td align="left">
							class="required"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">必填项：</td>
						<td align="left">
							<span class="radio-group">
								<input name="sex" class="required" type="radio" />男
								<input name="sex" type="radio" />女
							</span>
						</td>
						<td align="left">
							class="required"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">必填项：</td>
						<td align="left">
							<span class="checkbox-group">
								<input name="type" class="required" type="checkbox" />禁用
								<input name="type" type="checkbox" />启用
								<input name="type" type="checkbox" />过期
							</span>
						</td>
						<td align="left">
							class="required"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">邮箱：</td>
						<td align="left">
							<input name="email" class="required email" type="text" />
						</td>
						<td align="left">
							class="email"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">网址：</td>
						<td align="left">
							<input name="url" class="required url" type="text" />
						</td>
						<td align="left">
							class="url"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">日期：</td>
						<td align="left">
							<input name="date" class="dateISO Wdate" required type="text" />
						</td>
						<td align="left">
							class="dateISO"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">数字：</td>
						<td align="left">
							<input name="digits" class="required digits" type="text" />
						</td>
						<td align="left">
							class="digits"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">数（正负数、小数）：</td>
						<td align="left">
							<input name="number" class="required number" type="text" />
						</td>
						<td align="left">
							class="number"
						</td>
					</tr>	
					
					<tr>
						<td class="TDTITLE">不超过8位：</td>
						<td align="left">
							<input name="maxlength" type="text" class="required"
								 data-rule-maxlength="8" />
						</td>
						<td align="left">
							data-rule-maxlength="8"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">不少于4位：</td>
						<td align="left">
							<input name="minlength" type="text" class="required"
								 data-rule-minlength="4" />
						</td>
						<td align="left">
							data-rule-minlength="4"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">4位-8位：</td>
						<td align="left">
							<input name="rangelength" type="text" 
								 data-rule-rangelength="[4,8]" />
						</td>
						<td align="left">
							data-rule-rangelength="[4,8]"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">不大于100：</td>
						<td align="left">
							<input name="max" type="text" 
								 data-rule-max="100" />
						</td>
						<td align="left">
							data-rule-max="100"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">不小于10：</td>
						<td align="left">
							<input name="min" type="text" 
								 data-rule-min="10" />
						</td>
						<td align="left">
							data-rule-min="10"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">10到100：</td>
						<td align="left">
							<input name="range" type="text" class="required"
								 data-rule-range="[10,100]" />
						</td>
						<td align="left">
							data-rule-range="[10,100]"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
				<input type="reset" class="btn" value="清除" />
				<input type="button" onclick="javascript:history.go(1);" class="btn" value="返回" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>远程校验</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">用户名（admin为已存在）：</td>
						<td align="left">
							<input name="username" class="required" type="text" 
								data-rule-remote="${pageContext.request.contextPath}/app/dashboard!checkUserName.do" />
						</td>
						<td align="left">
							data-rule-remote="${pageContext.request.contextPath}/app/dashboard!checkUserName.do"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>自定义校验信息</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">必填项：</td>
						<td align="left">
							<input name="username" class="required" type="text" 
								data-msg-required="这个是必填项你必须要写" />
						</td>
						<td align="left">
							data-msg-required="这个是必填项你必须要写"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>值相等校验</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">密码：</td>
						<td align="left">
							<input name="password" class="required" type="text" id="password" />
						</td>
						<td align="left">
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">确认密码：</td>
						<td align="left">
							<input name="password-repeat" type="text"
								data-rule-equalto="#password" data-msg-equalto="确认密码和密码不一致，请重新输入" />
						</td>
						<td align="left">
							data-rule-equalto="#password"
							<br> 
							data-msg-equalto="确认密码和密码不一致，请重新输入"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>时间比较</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">开始时间：</td>
						<td align="left">
							<input name="begindate" class="Wdate" type="text" id="begindate"
								data-rule-ltdate="#enddate" />
						</td>
						<td align="left">
							data-rule-ltdate="#enddate"
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">结束时间：</td>
						<td align="left">
							<input name="enddate" class="Wdate" type="text" id="enddate"
								data-rule-gtdate="#begindate" />
						</td>
						<td align="left">
							data-rule-gtdate="#begindate"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>一组数据中指定数目必填</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">手机：</td>
						<td align="left">
							<input name="phone" class="phone-group" type="text"
								data-rule-require_from_group='[1, ".phone-group"]' />
						</td>
						<td align="left">
							data-rule-require_from_group='[1, ".phone-group"]'
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">固话：</td>
						<td align="left">
							<input name="fphone" class="phone-group" type="text"
								data-rule-require_from_group='[1, ".phone-group"]' />
						</td>
						<td align="left">
							data-rule-require_from_group='[1, ".phone-group"]'
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">办公电话：</td>
						<td align="left">
							<input name="ophone" class="phone-group" type="text"
								data-rule-require_from_group='[1, ".phone-group"]' />
						</td>
						<td align="left">
							data-rule-require_from_group='[1, ".phone-group"]'
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>自动补全</legend>
			
			<input type="text" class="autocomplete" data-url="http://shell.loopj.com/tokeninput/tvshows.php" />
		</fieldset>
		
		<fieldset>
		
			<legend>依赖其他项目的校验</legend>
			
			<form action="#" id="testForm" data-validate="true">
				<table width="200" border="0" cellpadding="1" cellspacing="1">		
					<tr>
						<td class="TDTITLE">是否愿意接受电子邮件：</td>
						<td align="left">
							<input name="checkemail" type="checkbox" id="checkemail" />
						</td>
						<td align="left">
						</td>
					</tr>
					
					<tr>
						<td class="TDTITLE">邮箱：</td>
						<td align="left">
							<input name="email2" type="text" 
								data-rule ='{required : {depents: "#checkemail:checked"}}' />
						</td>
						<td align="left">
							data-rule-gtdate="#begindate"
						</td>
					</tr>
				</table>
				
				<input type="submit" class="btn" value="提交" />
			</form>
		</fieldset>
		
		<fieldset>
		
			<legend>可编辑表格</legend>
			
			<template data-ref="#editable_table" data-property='["name","sex","work","weaponName","weapon"]' 
				data-name="weapons" data-add-button="#addBtn" data-submit-button="#submitBtn">
				<tr>
					<td></td>
					<td class="E">
						<select class="autocomplete">
							<option value="001" data-py="zs">张三</option>
							<option value="002" data-py="ls">李四</option>
							<option value="003" data-py="wem">王二麻</option>
						</select>
					</td>
					<td class="E">
						<select>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</td>
					<td></td>
					<td class="E">
						<input type="text" />
						<input type="hidden" />
					</td>
				</tr>
			</template>
			
			<form action="#" id="weaponForm" data-validate="true">
				<input type="button" id="addBtn" class="btn" value="添加数据" />
				<input type="submit" id="submitBtn" class="btn" value="提交" />
			
				<ec:table action="app/dashboard!test.do" items="applyList" var="apply" showPagination="false" tableId="editable" 
					imagePath="${contextPath}/themes/css/images/table/*.gif" sortable="false" showStatusBar="false" showTitle="false">
					<ec:row>
						<ec:column property="id" title="工号"></ec:column>
						<ec:column property="name" title="姓名" styleClass="editable">
							<select class="autocomplete" data-value="${apply.name }">
								<option value="001" data-py="zs">张三</option>
								<option value="002" data-py="ls">李四</option>
								<option value="003" data-py="wem">王二麻</option>
							</select>
						</ec:column>
						<ec:column property="sex" title="性别" styleClass="editable">
							<select name="sex" data-value="0">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</ec:column>
						<ec:column property="work" title="职位"></ec:column>
						<ec:column property="weapon" title="武器" styleClass="editable">
							<input type="text" value="${apply.weaponName }" target="dialog" data-title="选择武器" class="selectable"
								data-href="${contextPath}/app/dashboard!weapons.do" data-ref="weapon" name="weaponName" />
							<input type="hidden" value="${apply.weapon }" name="weapon" />
						</ec:column>
					</ec:row>
				</ec:table>
			</form>
		</fieldset>	
		
		
		<fieldset>
		
			<legend>上传文件</legend>
			
			<input type="file" id="upload-test" data-opt-id="TEST" />
		</fieldset>
		
	</body>
	
	<%@ include file="/page/common/scripts.jsp"%>
	
</html>

