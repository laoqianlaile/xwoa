<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>
			<s:text name="attendanceDetailed.list.title" />
		</title>
		<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
		<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				个人 考勤信息
			</legend>
			<div class="searchDiv">
			<s:form action="attendanceDetailed" namespace="/attendance" style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
				<table style="width: auto;">
<%-- 
					<tr >
						<td><s:text name="attendanceDetailed.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="attendanceDetailed.createdate" />:</td>
						<td><s:textfield name="s_createdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.usercode" />:</td>
						<td><s:textfield name="s_usercode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.unitcount" />:</td>
						<td><s:textfield name="s_unitcount" /> </td>
					</tr>	
					
					<tr >
						<td><s:text name="attendanceDetailed.workdate" />:</td>
						<td><s:textfield name="s_workdate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.saattendancetime" />:</td>
						<td><s:textfield name="s_saattendancetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.xaattendancetime" />:</td>
						<td><s:textfield name="s_xaattendancetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.latein" />:</td>
						<td><s:textfield name="s_latein" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.earlyout" />:</td>
						<td><s:textfield name="s_earlyout" /> </td>
					</tr>	

					<tr >
						<td><s:text name="attendanceDetailed.overtimehours" />:</td>
						<td><s:textfield name="s_overtimehours" /> </td>
					</tr> --%>	
					
					<tr >
<%-- 						<td class="searchCondArea">
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td> --%>
<%-- 						<td class="searchTitleArea">
							<label style="width: 60px" class="searchCondTitle"><s:text name="attendanceDetailed.unitname" />:</label>
						</td>
						<td class="searchTitleArea">
							<input type="text" style="height: 36px"  class="searchCondContent" name="s_unitname"/>
						</td>
						<td class="fenggexian" style="padding-left: 2;padding-right: 2"> </td>
						<td class="searchCountArea">
							<label style="width: 60px" class="searchCondTitle"><s:text name="attendanceDetailed.username" />:</label>
						</td>
						<td class="searchCountArea">
							<input type="text" style="height: 36px" class="searchCondContent" name="s_username"/>
						</td> --%>
						<td class="searchCountArea"><label style="width: 60px" class="searchCondTitle"><s:text name="attendanceDetailed.workdate" /></label>:</td>
						<td class="searchCountArea">
							<input style="height: 36px" type="text" class="Wdate searchCondContent"  id="s_workdate" name="s_workdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						</td>
						<td class="searchCondArea">
							<s:submit method="userList"  key="opt.btn.query" cssClass="btn"/>
						</td>
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>

		<ec:table action="attendance/attendanceDetailed!userList.do" items="attendanceDetailedUserlist" var="attendanceDetailed"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
<%-- 			<ec:exportXls fileName="attendanceDetaileds.xls" ></ec:exportXls>
			<ec:exportPdf fileName="attendanceDetaileds.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf> --%>
			<ec:row>

			<%-- 	<c:set var="tdjid"><s:text name='attendanceDetailed.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />
 --%>
<%-- 				<c:set var="tusercode"><s:text name='attendanceDetailed.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" /> --%>

				<c:set var="tusername"><s:text name='attendanceDetailed.username' /></c:set>	
				<ec:column property="username" title="${tusername}" style="text-align:center" />

<%-- 				<c:set var="tunitcount"><s:text name='attendanceDetailed.unitcount' /></c:set>	
				<ec:column property="unitcount" title="${tunitcount}" style="text-align:center" /> --%>

				<c:set var="tunitname"><s:text name='attendanceDetailed.unitname' /></c:set>	
				<ec:column property="unitname" title="${tunitname}" style="text-align:center" />

				<c:set var="tworkdate"><s:text name='attendanceDetailed.workdate' /></c:set>	
				<ec:column property="workdate" title="${tworkdate}" style="text-align:center" format="yyyy-MM-dd" cell="date">
				<fmt:formatDate value="${attendanceDetailed.workdate}"
					pattern="yyyy-MM-dd" />
				</ec:column>

				<c:set var="tsaattendancetime"><s:text name='attendanceDetailed.saattendancetime' /></c:set>	
				<ec:column property="saattendancetime" title="${tsaattendancetime}" style="text-align:center" format="HH:mm:ss" cell="date">
				<fmt:formatDate value="${attendanceDetailed.saattendancetime}"
					pattern="yyyy-MM-dd" />
				</ec:column>

				<c:set var="txaattendancetime"><s:text name='attendanceDetailed.xaattendancetime' /></c:set>	
				<ec:column property="xaattendancetime" title="${txaattendancetime}" style="text-align:center" format="HH:mm:ss" cell="date">
				<fmt:formatDate value="${attendanceDetailed.xaattendancetime}"
					pattern="yyyy-MM-dd" />
				</ec:column>

				<c:set var="tovertimehours"><s:text name='attendanceDetailed.overtimehours' /></c:set>	
				<ec:column property="overtimehours" title="${tovertimehours}" style="text-align:center" />
				
				<c:set var="tcreatedate"><s:text name='attendanceDetailed.createdate' /></c:set>	
				<ec:column property="createdate" title="${tcreatedate}" style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date">
				<fmt:formatDate value="${attendanceDetailed.createdate}"
					pattern="yyyy-MM-dd" />
				</ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
<%-- 				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='attendance/attendanceDetailed!view.do?djid=${attendanceDetailed.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a class="bianji" href='attendance/attendanceDetailed!edit.do?djid=${attendanceDetailed.djid}'><s:text name="opt.btn.edit" /></a>
					<a class="delete_email" href='attendance/attendanceDetailed!delete.do?djid=${attendanceDetailed.djid}' 
							onclick='return confirm("${deletecofirm}attendanceDetailed?");'><s:text name="opt.btn.delete" /></a>
				</ec:column> --%>

			</ec:row>
		</ec:table>

	</body>
</html>
