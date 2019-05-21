<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			执法车辆管理
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
			
			<s:form action="lawVehicle" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
				<input type="hidden" id="flag" name="flag" value="${flag}"/>
					
				<tr>
				
					<td class="addTd">车牌号:</td>
					<td> <s:textfield name="s_plateNumber" value="%{#parameters['s_plateNumber']}"/>
					</td>
					<td class="addTd">车辆型号:</td>
					<td> <s:textfield name="s_vehicleType" value="%{#parameters['s_vehicleType']}"/>
					</td>
					
				<td>
				</td>
				</tr>
	
					<tr>
						<td></td>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>

							<s:submit method="initalEdit"  key="登记" cssClass="btn"/>
							
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<ec:table action="powerbase/lawVehicle!list.do" items="lawVehiclelist" var="lawVehicle" 
		          imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="plateNumber" title="车牌号" style="text-align:center">
						${lawVehicle.plateNumber}
				</ec:column>
				<ec:column property="vehicleType" title="车辆型号" style="text-align:center">
						${lawVehicle.vehicleType}
				</ec:column>
					<ec:column property="recordDate" title="录入时间" style="text-align:center">
						${lawVehicle.recordDate}
				</ec:column>
				<ec:column property="ownerUnit" title="所属单位" style="text-align:center" >
						${cp:MAPVALUE("depno",lawVehicle.ownerUnit)}
				</ec:column>
				<ec:column property="vehicleAdmin" title="所属管理人员" style="text-align:center" >
				        ${lawVehicle.vehicleAdmin}
				</ec:column>
				<ec:column property="opt" title="操作" style="text-align:center" sortable="false">
				
					<a href='powerbase/lawVehicle!lawupdate.do?vehicleId=${lawVehicle.vehicleId}' >修改</a>
				
					<a href='powerbase/lawVehicle!delete.do?vehicleId=${lawVehicle.vehicleId}' >删除</a>
			
					<a href='powerbase/lawVehicle!lawview.do?vehicleId=${lawVehicle.vehicleId}' >查看</a>
	
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
/* 		function openNewWindow(winUrl, winName, winProps) {
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
		}); */
	</script>
</html>
