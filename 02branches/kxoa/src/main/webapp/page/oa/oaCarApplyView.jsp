<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/print.jsp"%>
<html>
<head>
<title>车辆使用查看</title>
  <style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     .group-title{position:relative;height:22px;}
	.group-title div{position:absolute;height:22px;top:0;line-height:22px;font-size:16px;font-weight:bold}
	.group-title .title-ico{width:5px;background:#56b9fd;z-index:1;left:0;}
	.group-title .title-name{z-index:1;left:5px;background:#fff;padding:0 10px;}
	.group-title .title-split-line{width:100%;z-index:0;left:0;padding-top:10px}
	.group-title .title-split-line span{display:block;width:100%;height:1px;background:#ccc}
	span.span_state{float: right;margin: 5px 30px;}
	span.span_state a{color:#000;cursor:pointer;font-size:14px}
	span.remindIco{background-position: center;display: inline-block;background-repeat:no-repeat;width: 26px; height: 26px}
	span.remindIco-overdue{background-image: url("${ctxStatic}/image/ycqclock.gif")}
	span.remindIco-toOverdue{background-image: url("${ctxStatic}/image/jjcqclock.gif")}
	span.remindIco-none{display:none}
  </style>
</head>
<!-- 
 *********************************************************************** *
 *        注意：1、该页面修改必须测试pdf生成；                                                                                                                   *
 *            2、不许引入css.jsp;                                            *
 *            3、定义样式，在head里定义，不要干扰到table元素，能用行级样式就用行级样式；                     *
 *            4、不允许用js来填充table里数据，因为浏览器上才能执行js，用java中url打开的流            *
 *               js渲染的内容是没有效果的 ；                                                                                                                  *
 *********************************************************************** *
 -->
<body >
	<s:hidden name="viewtype" value="%{viewtype}"></s:hidden>
	 <input id="djId" type="hidden" name="djId" value="${djId }" />
	<div class="group-title">
		<div class="title-ico"></div>
		<div class="title-name">申请信息</div>
		<hr class="title-split-line"
			style="position: absolute; width: 100%; height: 1px; border: none; border-top: 1px solid #CCC; padding: 0 !important; top: 10px; z-index: 0" />
	</div>
	
	<!-- ======================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
	 <div class="form-container">
	    <%--下面的注释不要删掉，这是我截取表单的标记 --%>
      <!--WordStartExport-->
	<h2 style="color:red;font-family: Microsoft YaHei;text-align:center;padding-top:10px;">
		${cp:MAPVALUE('SYSPARAM','BizzName')}&nbsp;车&nbsp;辆&nbsp;使&nbsp;用&nbsp;申&nbsp;请&nbsp;单
	</h2>
	<br></br>
	<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;" >
		<tr>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">标题</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3"><s:property
					value="%{transaffairname}" /></td>

		</tr>
		<tr>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">申请单号</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{applyNo}" /></td>

			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">申请部门</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;">${cp:MAPVALUE("unitcode",depno)}</td>
		</tr>
		<tr>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">申请人</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;">${cp:MAPVALUE("usercode",creater)}</td>

			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">申请日期</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="createtime" format="yyyy-MM-dd" />
			</td>
		</tr>

		<tr>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">乘车人数</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{meetingPersionsNum}" /></td>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">联系电话</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{telephone}" /></td>
			<%-- <td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">
						目的地
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;">
							<s:property value="%{destination}" />
						
						
					</td> --%>
		</tr>

		<tr>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">预计用车开始时间</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="planBegTime"
					format="yyyy-MM-dd HH:mm" /></td>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">预计用车结束时间</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="planEndTime"
					format="yyyy-MM-dd HH:mm" /></td>
		</tr>


		<%-- <td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">
							是否往返
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;">
						<s:property value="%{meetingNo}" />
					</td> --%>
		<tr>
			<%-- <td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">
						行车路线
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" >
					 <s:property value="%{meetingPersions}" />
					</td> --%>
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">用车事由</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">${cp:MAPVALUE("req_remark",reqRemark)}
			</td>

		</tr>
		<tr>

			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">备注</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3"><s:property value="%{remark}" />
			</td>

		</tr>
	  <tr>
		   
			<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">
						申请状态
			</td>
			<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">
					${cp:MAPVALUE("meetState",state)}
			</td>
		</tr>	
		<%-- <c:if test="${not empty solvetime  }">
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">办结时间</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{solvetime}" /></td>
			</tr>
		</c:if>
		<c:if test="${ not empty solvenote  }">
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">办结意见</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3"><s:property value="%{solvenote}" />
				</td>
			</tr>
		</c:if> --%>
		</table>
		<br></br>
		<c:if test="${not empty doBegTime}">

         <table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">安排开始时间</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="doBegTime"
						format="yyyy-MM-dd HH:mm" /></td>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">安排结束时间</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="doEndTime"
						format="yyyy-MM-dd HH:mm" /></td>
			</tr>

			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">安排部门</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;">${cp:MAPVALUE("unitcode",doDepno)}</td>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">派车人</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;">${cp:MAPVALUE("usercode",doCreater)}</td>

			</tr>
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">用车来源</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;">
				<c:choose>
						<c:when test="${range_type eq 'W'}">外租车</c:when>
						<c:otherwise>内部车</c:otherwise>
					</c:choose>
				
				</td>	
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">车牌号</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{carno}" /></td>

			</tr>
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">司机</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{driver}" /></td>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">司机联系电话</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{drTelephone}" /></td>
			</tr>
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">品牌</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{brand}" /></td>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">车型</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:property value="%{modelType}" /></td>

			</tr>
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">安排备注</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3"><s:property value="%{doRemark}" />
				</td>


			</tr>
			</table>
			<!-- 				</fieldset> -->
		</c:if>
		<br></br>
		<c:if test="${not empty begTime}">
        <table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;"><tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">实际开始时间</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="begTime"
						format="yyyy-MM-dd HH:mm" /></td>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">实际结束时间</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;"><s:date name="endTime"
						format="yyyy-MM-dd HH:mm" /></td>
			</tr>
			<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:30px;font-weight: bold;width:130px;">实际使用备注</td>
				<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3"><s:property value="%{todoremark}" />

				</td>
			</tr>
	</table>

	</c:if>
	<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
		<%@ include file="/page/common/idea.jsp"%>
	</table>
	  <!--WordEndExport-->
      <%--上面的注释不要删掉，这是我截取表单的标记 --%>
	</div>
	  
   <!-- ====================================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
       <div>
	   <span class="span_state">
	    <c:choose>
					<c:when test="${'C' eq bizstate }"><a style="color:red">办件状态：已办结</a></c:when>
					<c:when test="${'W' eq bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
		</c:choose>
		<a style="text-decoration: underline;" onclick="print()">打印</a>
		</span>
    </div>
</body>


<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">
	
	function adjustHeight() {//自动调整页面高度
		if (window.parent.document.getElementById("3")) {
			window.parent.document.getElementById("3").style.height = document.body.scrollHeight
					+ "px";
		}
	}
	function print() {
		printView(function(LODOP) {
			LODOP.PRINT_INIT("车辆使用申请单打印");
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
			$(".form-container").find("td").each(function(){
				$(this).height($(this).height());
			});
			LODOP.ADD_PRINT_HTM(100,0,"100%","100%",$(".form-container").html());
			
			
		});
	}
	function openTransactUsers(){
		
		var djId = $('#djId').val();
		art.dialog
		.open('${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!listUsersOfTransaction.do?djId=' + djId,
				 {title: '办理人员状态', width: 800, height: 500});

	}
</script>
</html>


