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
		<style type="text/css">
		.se2{    width:100px;    height:36px;    position:absolute;    top:11px;    left:712px;    z-index: 2;    opacity: 0;
		filter:alpha(opacity=0);}
		.se1{    width:100px;    height:36px;    font-size:16px;    color:#fff;    background: #28abde; z-index: 1;    border-radius:5px;    position:absolute;    top:11px;    left:712px;}
		.se3{ position:absolute;    top:11px;    left:822px;}
		.se1:hover{    cursor: pointer;}
		.se2:hover{    cursor: pointer;}
		</style>

	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				 考勤信息
			</legend>
			<div class="searchDiv">
			<s:form action="attendanceDetailed!importAtt.do" namespace="/attendance"  method="POST"
				enctype="multipart/form-data" name="form1" id="form1" target="" style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
				<table style="width: auto;">
					
					<tr >
						<c:if test="${param.listType!=1 && param.listType!=2}">
							<td class="searchCondArea">
								<input type="button" class="btn" value="新增" onclick="built()">
								<%-- <s:submit method="built"  key="opt.btn.new" cssClass="btn"/> --%>
							</td>
						</c:if>
						<c:if test="${param.listType!=1 && param.listType!=2}">
							<td class="searchCountArea">
								<label style="width: 60px" class="searchCondTitle"><s:text name="attendanceDetailed.username" />:</label>
							</td>
							<td class="searchCountArea">
								<input type="text" style="height: 36px" class="searchCondContent" name="s_username" value="${param['s_username']}"/>
							</td>
							<td class="fenggexian" style="padding-left: 2;padding-right: 2"> </td>
							
						</c:if>
						<td class="searchCountArea">
							<label style="width: 60px" class="searchCondTitle">月份:</label>
						</td>
						<td class="searchCountArea">
						<input type="text" class="Wdate" id="s_workdate" name="s_workdate" style="height: 36px"
						onclick="WdatePicker({dateFmt:'yyyy/MM'})" value="${param['s_workdate']}" placeholder="选择月份">	
						</td>
					
						<td class="searchCondArea">
							<input type="hidden" id="listType" value="${param.listType}">
							<input type="button" class="btn" value="查询" onclick="query()">
						</td>
						<c:if test="${param.listType!=1 && param.listType!=2}">
							<td class="searchCondArea" id="aaa">
							<input class="se2" id="file" type="file" name="image" onchange="beforeSubmit(this);"/>    
							    <label for="file">         
							       <input class="se1" type="button" value="导入" style="cursor: pointer;"/>       
							     </label>

								<!-- <input type="button" class="btn" value="导入" onclick="importAtt()">
								<input type="file" id="file" onchange="beforeSubmit(this);"  style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/> -->
								<div class="se3">
								<%-- <s:submit method="getExcelAttendance"  value="导出" cssClass="btn"/> --%>
								<s:submit onclick="exportExcelP()" value="下载导入模板"  cssClass="btn" />
								<!-- <input type="button" class="btn" value="时间设置" onclick="updateTime()"> -->
								</div>
							</td>
						</c:if>
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>

		<ec:table action="attendance/attendanceDetailed!list.do" items="attendanceDetailedlist" var="attendanceDetailed"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<%-- <c:set var="tdjid"><s:text name='attendanceDetailed.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />
 --%>
<%-- 				<c:set var="tusercode"><s:text name='attendanceDetailed.usercode' /></c:set>	
				<ec:column property="usercode" title="${tusercode}" style="text-align:center" /> --%>
				<c:set var="tunitname"><s:text name='attendanceDetailed.unitname' /></c:set>	
				<ec:column property="unitname" title="${tunitname}" style="text-align:center" />

				<c:set var="tusername"><s:text name='attendanceDetailed.username' /></c:set>	
				<ec:column property="username" title="${tusername}" style="text-align:center" />

<%-- 				<c:set var="tunitcount"><s:text name='attendanceDetailed.unitcount' /></c:set>	
				<ec:column property="unitcount" title="${tunitcount}" style="text-align:center" /> --%>

				
				<c:set var="tworkdate"><s:text name='出勤月份' /></c:set>	
				<ec:column property="workdate" title="${tworkdate}" style="text-align:center" />
			
				<!-- 如果上班时间不为空就显示正常数据 -->	
				<c:if test="${attendanceDetailed.saattendancetime != null}">
					<ec:column property="saattendancetime" title="${tsaattendancetime}" style="text-align:center" />
				</c:if>
				<!-- 如果上班时间为空就显示--代替空 -->	
				<c:if test="${attendanceDetailed.saattendancetime == null}">
					<c:set var="tsaattendancetime"><s:text name='attendanceDetailed.saattendancetime' /></c:set>
					<ec:column property="saattendancetime" title="${tsaattendancetime}" style="text-align:center">--</ec:column>
				</c:if>
				
				<!-- 如果下班时间不为空就显示正常数据 -->	
				<c:if test="${attendanceDetailed.xaattendancetime != null}">
					<c:set var="txaattendancetime"><s:text name='attendanceDetailed.xaattendancetime' /></c:set>	
					<ec:column property="xaattendancetime" title="${txaattendancetime}" style="text-align:center" />
				</c:if>
				<!-- 如果下班时间为空就显示--代替空 -->	
				<c:if test="${attendanceDetailed.xaattendancetime == null}">
					<c:set var="txaattendancetime"><s:text name='attendanceDetailed.xaattendancetime' /></c:set>
					<ec:column property="xaattendancetime" title="${txaattendancetime}" style="text-align:center">--</ec:column>
				</c:if>
				
				<!-- 如果工作时长不为空，则显示正常数据 -->
				<c:if test="${attendanceDetailed.overtimehours != null}">
					<c:set var="tovertimehours"><s:text name='attendanceDetailed.overtimehours' /></c:set>	
					<ec:column property="overtimehours" title="${tovertimehours}" style="text-align:center" />
				</c:if>
				<!-- 如果工作时长为空，用--代替 -->
				<c:if test="${attendanceDetailed.overtimehours == null}">
					<c:set var="tovertimehours"><s:text name='attendanceDetailed.overtimehours' /></c:set>	
					<ec:column property="overtimehours" title="${tovertimehours}" style="text-align:center">--</ec:column>
				</c:if>
				
				<c:if test="${attendanceDetailed.attenstate == 'T'}">
					<ec:column property="attenstate"  title="出勤状态"  style="text-align:center"><span style="color: #f00;">迟到</span></ec:column>
				</c:if>
				<c:if test="${attendanceDetailed.attenstate == 'F'}">
					<ec:column property="attenstate"  title="出勤状态"  style="text-align:center"><span style="color: #f00;">早退</span></ec:column>
				</c:if>
				<c:if test="${attendanceDetailed.attenstate == 'Y'}">
					<ec:column property="attenstate"  title="出勤状态"  style="text-align:center"><span style="color: #f00;">迟到+早退</span></ec:column>
				</c:if>
				<c:if test="${attendanceDetailed.attenstate != 'T' && attendanceDetailed.attenstate != 'F' && attendanceDetailed.attenstate != 'Y'}">
					<ec:column property="attenstate"  title="出勤状态"  style="text-align:center"><span>正常</span></ec:column>
				</c:if>
				
				
				<ec:column property="createdate" title="导入时间" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
		
				<c:if test="${param.listType!=1 && param.listType!=2}">
					<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
					<ec:column property="opt" title="${optlabel}" sortable="false"
						style="text-align:center">
						<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
						<%-- <a href='attendance/attendanceDetailed!view.do?djid=${attendanceDetailed.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a> --%>
						<a class="bianji" href='attendance/attendanceDetailed!edit.do?djid=${attendanceDetailed.djid}'><s:text name="opt.btn.edit" /></a>
						<a class="delete_email" href='attendance/attendanceDetailed!delete.do?djid=${attendanceDetailed.djid}' 
								onclick='return confirm("${deletecofirm}?");'><s:text name="opt.btn.delete" /></a>
					</ec:column>
				</c:if>
			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
		//选择文件的时候，点击导入按钮触发file
        function importAtt(){  
            //触发 文件选择的click事件  
            $("#file").trigger("click");  
        } 
        /*控件上传*/
        function beforeSubmit(file) {
        	var formxx=document.getElementById("form1");
        	
        	file.name = "stuffFile";
        	$("#form1").submit();
        	//formxx.submit();
        }
        //查询方法，获取类型不同，查询的结果也不同
        function query(){
        	var listType = $("#listType").val();//获取类型
            var srEXForm  = document.getElementById("form1");//获取form表单id
            srEXForm.action= 'attendanceDetailed!list.do?listType='+listType;
            srEXForm.submit();
        };
        //时间设置
        function updateTime(){
	        window.location.href='${pageContext.request.contextPath}/attendance/attendanceSetting!newedit.do?';
        }
      	//新增
        function built(){
	        var listType = $("#listType").val();//获取类型
            var srEXForm  = document.getElementById("form1");//获取form表单id
            srEXForm.action= 'attendanceDetailed!built.do?listType='+listType;
            srEXForm.submit();
        }
      	//下载导入模板
        function exportExcelP(){
	        var listType = $("#listType").val();//获取类型
            var srEXForm  = document.getElementById("form1");//获取form表单id
            srEXForm.action= 'attendanceDetailed!exportExcelP.do?listType='+listType;
            srEXForm.submit();
        }
	</script>
</html>
