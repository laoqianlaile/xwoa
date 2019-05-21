<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="attendanceSetting.list.title" />
		</title>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
		<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />	
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="form">
			<legend>
					上下班时间
			</legend>
			
			<s:form action="attendanceSetting" namespace="/attendance" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

<%-- 					<tr >
						<td><s:text name="attendanceSetting.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="attendanceSetting.attcode" />:</td>
						<td><s:textfield name="s_attcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.createdate" />:</td>
						<td><s:textfield name="s_createdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.lastcodedate" />:</td>
						<td><s:textfield name="s_lastcodedate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.schedulingtype" />:</td>
						<td><s:textfield name="s_schedulingtype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.statetime" />:</td>
						<td><s:textfield name="s_statetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.onetype" />:</td>
						<td><s:textfield name="s_onetype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.twotype" />:</td>
						<td><s:textfield name="s_twotype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.threetype" />:</td>
						<td><s:textfield name="s_threetype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.fourtype" />:</td>
						<td><s:textfield name="s_fourtype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.fivetype" />:</td>
						<td><s:textfield name="s_fivetype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.sixtype" />:</td>
						<td><s:textfield name="s_sixtype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.onetime" />:</td>
						<td><s:textfield name="s_onetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.twotime" />:</td>
						<td><s:textfield name="s_twotime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.threetime" />:</td>
						<td><s:textfield name="s_threetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.fourtime" />:</td>
						<td><s:textfield name="s_fourtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.fivetime" />:</td>
						<td><s:textfield name="s_fivetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.sextime" />:</td>
						<td><s:textfield name="s_sextime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.notworkweek" />:</td>
						<td><s:textfield name="s_notworkweek" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.notworkdate" />:</td>
						<td><s:textfield name="s_notworkdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.personnelcode" />:</td>
						<td><s:textfield name="s_personnelcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.intermissiontime" />:</td>
						<td><s:textfield name="s_intermissiontime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.state" />:</td>
						<td><s:textfield name="s_state" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.saattendancetime" />:</td>
						<td><s:textfield name="s_saattendancetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceSetting.xaattendancetime" />:</td>
						<td><s:textfield name="s_xaattendancetime" /> </td>
					</tr>	 --%>

					<tr>
						<td>
							<%-- <s:submit method="list"  key="opt.btn.query" cssClass="btn"/> --%>
							<s:submit id="hid" method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="attendance/attendanceSetting!list.do" items="objList" var="attendanceSetting"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
<%-- 			<ec:exportXls fileName="attendanceSettings.xls" ></ec:exportXls>
			<ec:exportPdf fileName="attendanceSettings.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

<%-- 				<c:set var="tdjid"><s:text name='attendanceSetting.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />


				<c:set var="tattcode"><s:text name='attendanceSetting.attcode' /></c:set>	
				<ec:column property="attcode" title="${tattcode}" style="text-align:center" />


				<c:set var="tlastcodedate"><s:text name='attendanceSetting.lastcodedate' /></c:set>	
				<ec:column property="lastcodedate" title="${tlastcodedate}" style="text-align:center" />

				<c:set var="tschedulingtype"><s:text name='attendanceSetting.schedulingtype' /></c:set>	
				<ec:column property="schedulingtype" title="${tschedulingtype}" style="text-align:center" />

				<c:set var="tstatetime"><s:text name='attendanceSetting.statetime' /></c:set>	
				<ec:column property="statetime" title="${tstatetime}" style="text-align:center" />

				<c:set var="tonetype"><s:text name='attendanceSetting.onetype' /></c:set>	
				<ec:column property="onetype" title="${tonetype}" style="text-align:center" />

				<c:set var="ttwotype"><s:text name='attendanceSetting.twotype' /></c:set>	
				<ec:column property="twotype" title="${ttwotype}" style="text-align:center" />

				<c:set var="tthreetype"><s:text name='attendanceSetting.threetype' /></c:set>	
				<ec:column property="threetype" title="${tthreetype}" style="text-align:center" />

				<c:set var="tfourtype"><s:text name='attendanceSetting.fourtype' /></c:set>	
				<ec:column property="fourtype" title="${tfourtype}" style="text-align:center" />

				<c:set var="tfivetype"><s:text name='attendanceSetting.fivetype' /></c:set>	
				<ec:column property="fivetype" title="${tfivetype}" style="text-align:center" />

				<c:set var="tsixtype"><s:text name='attendanceSetting.sixtype' /></c:set>	
				<ec:column property="sixtype" title="${tsixtype}" style="text-align:center" />

				<c:set var="tonetime"><s:text name='attendanceSetting.onetime' /></c:set>	
				<ec:column property="onetime" title="${tonetime}" style="text-align:center" />

				<c:set var="ttwotime"><s:text name='attendanceSetting.twotime' /></c:set>	
				<ec:column property="twotime" title="${ttwotime}" style="text-align:center" />

				<c:set var="tthreetime"><s:text name='attendanceSetting.threetime' /></c:set>	
				<ec:column property="threetime" title="${tthreetime}" style="text-align:center" />

				<c:set var="tfourtime"><s:text name='attendanceSetting.fourtime' /></c:set>	
				<ec:column property="fourtime" title="${tfourtime}" style="text-align:center" />

				<c:set var="tfivetime"><s:text name='attendanceSetting.fivetime' /></c:set>	
				<ec:column property="fivetime" title="${tfivetime}" style="text-align:center" />

				<c:set var="tsextime"><s:text name='attendanceSetting.sextime' /></c:set>	
				<ec:column property="sextime" title="${tsextime}" style="text-align:center" />

				<c:set var="tnotworkweek"><s:text name='attendanceSetting.notworkweek' /></c:set>	
				<ec:column property="notworkweek" title="${tnotworkweek}" style="text-align:center" />

				<c:set var="tnotworkdate"><s:text name='attendanceSetting.notworkdate' /></c:set>	
				<ec:column property="notworkdate" title="${tnotworkdate}" style="text-align:center" />

				<c:set var="tpersonnelcode"><s:text name='attendanceSetting.personnelcode' /></c:set>	
				<ec:column property="personnelcode" title="${tpersonnelcode}" style="text-align:center" />

				<c:set var="tintermissiontime"><s:text name='attendanceSetting.intermissiontime' /></c:set>	
				<ec:column property="intermissiontime" title="${tintermissiontime}" style="text-align:center" />

				<c:set var="tstate"><s:text name='attendanceSetting.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" /> --%>

				<c:set var="tsaattendancetime"><s:text name='attendanceSetting.saattendancetime' /></c:set>	
				<ec:column property="saattendancetime" title="${tsaattendancetime}" style="text-align:center" />

				<c:set var="txaattendancetime"><s:text name='attendanceSetting.xaattendancetime' /></c:set>	
				<ec:column property="xaattendancetime" title="${txaattendancetime}" style="text-align:center" />
				
				<c:set var="tcreatedate"><s:text name='attendanceSetting.createdate' /></c:set>	
				<ec:column property="createdate" title="${tcreatedate}" style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date">
				<fmt:formatDate value="${attendanceSetting.createdate}"
					pattern="yyyy-MM-dd" />
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<input type="hidden" id="optlabel" value="${optlabel}">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<%-- <a class="check_email" href='attendance/attendanceSetting!view.do?djid=${attendanceSetting.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a> --%>
					<a class="bianji" href='attendance/attendanceSetting!newedit.do'>修改</a>
<%--  					<a class="delete_email" href='attendance/attendanceSetting!delete.do?djid=${attendanceSetting.djid}' 
							onclick='return confirm("${deletecofirm}?");'><s:text name="opt.btn.delete" /></a> --%>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
		window.onload=function(){
			/* 判断数据库是否有数据，没有数据则，显示新增按钮，有数据则隐藏新增按钮 */
			var  hid=$("#optlabel").val();
			if(hid!=null){
				$("#hid").hide();
			};
		};
	</script>
</html>
