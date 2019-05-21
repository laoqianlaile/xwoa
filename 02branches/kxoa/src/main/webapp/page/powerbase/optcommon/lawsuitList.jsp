<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			行政诉讼管理
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
			
			<s:form action="lawsuit" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" id="flag" name="flag" value="${flag}"/>
				<tr>
					<td class="addTd">诉讼单位:</td>
					<td>
					<input type="text" id="orgName" name="orgName" style="width:200px;height:20px;" value="${cp:MAPVALUE('unitcode',param.s_lawsuitapplyunit)}"/>
					<input type="hidden" id="s_lawsuitapplyunit" name="s_lawsuitapplyunit" value="${param.s_lawsuitapplyunit}"/>
					<s:checkbox label="包含下属机构" name="s_queryUnderUnit" value="#parameters['s_queryUnderUnit']" fieldValue="true" />包含下属机构
					</td>
				<td>
				</td>
				</tr>
					<tr height="22">
						<td class="addTd">诉讼时间:</td>
						<td><sj:datepicker name="s_beginlawsuitdate" readonly="true"
								value="%{#parameters['s_beginlawsuitdate']}" yearRange="2010:2030"
								displayFormat="yy-mm-dd" changeYear="true" />&nbsp;&nbsp;至&nbsp;&nbsp;<sj:datepicker name="s_endlawsuitdate" readonly="true"
								value="%{#parameters['s_endlawsuitdate']}" yearRange="2010:2030"
								displayFormat="yy-mm-dd" changeYear="true" /></td> 
					</tr>
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
		<ec:table action="powerbase/lawsuit!List.do" items="Lawsuitlist" var="Lawsuit" 
		          imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="lawsuitno" title="诉讼编号" style="text-align:center">
						${Lawsuit.lawsuitno}
				</ec:column>
				<ec:column property="lawsuitdate" title="诉讼时间" style="text-align:center">
						${Lawsuit.lawsuitdate}
				</ec:column>
				<ec:column property="lawsuitapplyunit" title="诉讼单位" style="text-align:center" >
						${cp:MAPVALUE("depno",Lawsuit.lawsuitapplyunit)}
				</ec:column>
				<ec:column property="lawsuitresult" title="诉讼结果" style="text-align:center" >
				${cp:MAPVALUE("LawsuitResult",Lawsuit.lawsuitresult)}
				</ec:column>
				<ec:column property="opt" title="操作" style="text-align:center" sortable="false">
					<c:if test="${flag==1}">
					<a href='powerbase/lawsuit!lawsuitUpdate.do?lawsuitno=${Lawsuit.lawsuitno}&flag=${flag}' >修改</a>
				
					<a href='powerbase/lawsuit!delete.do?lawsuitno=${Lawsuit.lawsuitno}&flag=${flag}' >删除</a>
					</c:if>
					<c:if test="${flag==2}">
					<a href='powerbase/lawsuit!lawsuitview.do?lawsuitno=${Lawsuit.lawsuitno}&flag=${flag}' >查看</a>
	
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
