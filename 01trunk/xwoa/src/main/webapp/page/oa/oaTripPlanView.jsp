<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <title>徐圩新区出差审批单</title>
 
<style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     。group-title{position:relative;height:22px;}
 	.group-title div{position:absolute;height:22px;top:0;line-height:22px;font-size:16px;font-weight:bold}
	.group-title .title-ico{width:5px;background:#56b9fd;z-index:1;left:0;}
	.group-title .title-name{z-index:1;left:5px;background:#fff;padding:0 10px;}
	.group-title .title-split-line{width:100%;z-index:0;left:0;padding-top:10px}
	.group-title .title-split-line span{display:block;width:100%;height:1px;background:#ccc}
    span.span_state{float: right;margin: 5px 30px;}
    span.span_state1{float: left;margin: 5px 30px;}
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
<body>

		<c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	    <input id="djId" type="hidden" name="djId" value="${optBaseInfo.djId }" />
	 <div class="group-title">
	     <div class="title-ico"></div>
	     <!-- <div class="title-name">
	              业务信息
	     </div> -->
	    <div class="title-split-line"><span></span></div>
   </div>
       <!-- ======================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
   <div class="form-container"  >
		      <%--下面的注释不要删掉，这是我截取表单的标记 --%>
		      <!--WordStartExport-->  
           <h2 style="color:red;font-family: Microsoft YaHei;text-align:center;padding-top:10px;">
			徐圩新区出差审批单
			</h2>
		
			</br>
			<span style="float:right;">
			填报日期:<s:date name="createDate" format="yyyy年MM月dd日 " />
			
			</span></br>
      		<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
				 <tbody>
				 <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						出差人		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;" >
						${cp:MAPVALUE('usercode', object.creater)}
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:15%;">				
						工作部门		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:25%;">
					${cp:MAPVALUE("unitcode",object.deptno)}
					</td>
				</tr>
				<tr>
				
					<td style="border:1px #e8e8e8 solid;text-align:center;height:60px;font-weight: bold;width:15%;">				
						同行人员			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">
						${teamName}
					</td>
					
				</tr>
				<tr>
				
					<td style="border:1px #e8e8e8 solid;text-align:center;height:60px;font-weight: bold;width:15%;">				
						出差地点			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">
						${tripPalce}
					</td>
					
				</tr>
				<tr>
				<td style="border:1px #e8e8e8 solid;text-align:center;height:80px;font-weight: bold;width:15%;">				
						出差事由			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">
						${content}
					</td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:60px;font-weight: bold;width:15%;">				
						起止日期				
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						<s:date name="startTime" format="yyyy年MM月dd日" />——<s:date name="endTime" format="yyyy年MM月dd日" />
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:60px;font-weight: bold;width:15%;">				
						乘坐交通工具		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${transport}
					</td>
				</tr>
		         
				 <c:forEach var="idea" items="${ideas}">
				  <tr>
		            <td style="border:1px #e8e8e8 solid;text-align:center; height:120px;font-weight: bold;">${idea.transidea}</td>
		            <td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">${idea.transcontent}</td>
		           </tr>
		       </c:forEach>
		       <td style="border:1px #e8e8e8 solid;text-align:center;height:80px;font-weight: bold;width:15%;">				
						备注		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="3">
						${remark}
					</td>
				</tr>
				</tbody>		
	</table>
	</div>
	<!-- ====================================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
	
	   <div>
	   <span class="span_state1" >
             <c:choose>
					<c:when test="${'C' eq optBaseInfo.bizstate }"><a style="color:red">办件状态：已办结</a></c:when>
					<c:when test="${'W' eq optBaseInfo.bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
					<c:when test="${'W' eq optBaseInfo.bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
			</c:choose>
       </span>
	   <span class="span_state">
	   
		<c:if test="${param.canEdit eq 'T'}">
		<a style="text-decoration: underline;"  onclick="openEdit();">编辑信息</a>
	    </c:if>
		<a style="text-decoration: underline;" onclick="print()">打印</a>
		</span>
    </div>
</body>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>

<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">
function openEdit() {
	url="${pageContext.request.contextPath}/oa/oaTripPlan!editOptBaseSQ.do?djId=${djId}&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '维护信息', width: 1000, height: 600});
}
</script>
<script type="text/javascript">
function print(){
	printView(function(LODOP){
		LODOP.PRINT_INIT("南京市经信委承办会议（活动）、培训支出计划审批单打印");
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
