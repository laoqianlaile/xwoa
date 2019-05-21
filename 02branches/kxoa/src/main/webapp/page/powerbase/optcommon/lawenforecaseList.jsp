<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法案例管理
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset style="padding:10px;">
			<legend>
				 <b>查询条件</b>
			</legend>
			
			<s:form action="lawenforecase" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" id="flag" name="flag" value="${flag}"/>
						<c:if test="${flag==1}"> 
				<tr>
				
					<td class="addTd">案例编号:</td>
					<td> <s:textfield name="s_caseno" value="%{#parameters['s_caseno']}"/>
					</td>
					<td class="addTd">案例所属部门:</td>
					<td>
					<input type="text" id="orgName" name="orgName" style="width:200px;height:20px;" value="${cp:MAPVALUE('unitcode',param.s_orgId)}"/>
					<input type="hidden" id="s_orgId" name="s_orgId" value="${param.s_orgId}"/>
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构
					</td>
				<td>
				</td>
				</tr>
				</c:if>		<c:if test="${flag==2}">
				<tr>
				
					<td class="addTd">案例标题:</td>
					<td> <s:textfield name="s_casetitle" value="%{#parameters['s_casetitle']}"/>
					</td>
					<td class="addTd">案例正文:</td>
					<td>
					<td> <s:textfield name="s_fileName" value="%{#parameters['s_fileName']}"/>
				
					</td>
				<td>
				</td>
				</tr>
				
				
				</c:if>
					<tr>
						<td></td>
						<td>
							<s:submit method="List"  key="opt.btn.query" cssClass="btn"/>
						<c:if test="${flag==1}">
							<s:submit method="initalEdit"  key="登记" cssClass="btn"/>
							</c:if>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<ec:table action="powerbase/lawenforecase!List.do" items="Lawsuitlist" var="Lawsuit" 
		          imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="caseno" title="案例编号" style="text-align:center">
						${Lawsuit.caseno}
				</ec:column>
				<ec:column property="casetitle" title="案例标题" style="text-align:center">
						${Lawsuit.casetitle}
				</ec:column>
					<ec:column property="bookdate" title="登记时间" style="text-align:center">
						${Lawsuit.bookdate}
				</ec:column>
				<ec:column property="orgId" title="案例所属部门" style="text-align:center" >
						${cp:MAPVALUE("depno",Lawsuit.orgId)}
				</ec:column>
				<ec:column property="casedoc" title="案例正文" style="text-align:center" >
				        ${Lawsuit.fileName}
				</ec:column>
				<ec:column property="opt" title="操作" style="text-align:center" sortable="false">
					<c:if test="${flag==1}">
					<a href='powerbase/lawenforecase!lawUpdate.do?caseno=${Lawsuit.caseno}&flag=${flag}' >修改</a>
				
					<a href='powerbase/lawenforecase!lawdelete.do?caseno=${Lawsuit.caseno}&flag=${flag}' >删除</a>
					</c:if>
					<c:if test="${flag==2}">
					<a href='powerbase/lawenforecase!lawsuitview.do?caseno=${Lawsuit.caseno}&flag=${flag}' >查看</a>
	
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
		function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=500px,width=790px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
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
				bindEvent($("#orgName"),$("#s_lawsuitapplyunit"),$this);
				$("#lists span").die("click");
			});
		});
	</script>
</html>
