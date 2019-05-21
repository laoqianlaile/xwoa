<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <title>市科协机关人员出差经费预算审批表</title>
 
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
	     <div class="title-name">
	              市科协机关人员出差经费预算审批表
	     </div>
	    <div class="title-split-line"><span></span></div>
   </div>
       <!-- ======================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
   <div class="form-container"  >
		      <%--下面的注释不要删掉，这是我截取表单的标记 --%>
		      <!--WordStartExport-->  
           <h2 style="color:red;font-family: Microsoft YaHei;text-align:center;padding-top:10px;">
			市科协机关人员出差经费预算审批表
			</h2>
		
			</br>
      		<table cellpadding="0" cellspacing="0" style="width:90%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
				 <tbody>
				 <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						部门			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:20%;" >
						${depno}
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;" rowspan="2">				
						出差事由		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:20%;" rowspan="2">
						${title}
					</td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						出差地点				
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:20%;">
						${address}
					</td>
				</tr>
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						出差人员				
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:20%;" colspan="3">
						${meetingNo}
					</td>
				</tr>
				
				 <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						起始时间		
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;">
						<s:date name="begTime" format="yyyy-MM-dd" />
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						结束时间				
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" >
						<s:date name="endTime" format="yyyy-MM-dd" />
					</td>
				</tr>
				
				
				 <tr>
				    <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:25%;">				
						序号		
					</td>
				    <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:25%;">				
						预算明细	
					</td>
				    <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:25%;">				
						标准	
					</td>
				    <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:25%;">				
						金额（元）	
					</td>
				</tr>
				<tr>	
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						1	
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						城市间交通费用	
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${transStandard}
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${transStandard}
					</td>
										
				</tr>
				<tr>	
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						2	
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						住宿地点	
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${accomStandard}
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${accomStandard}
					</td>
										
				</tr>
				<tr>	
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						3	
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						伙食补助地点	
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${amountFood}
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${amountFood}
					</td>
										
				</tr>
				<tr>	
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						4	
					</td>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:28%;">				
						公杂费	
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${amountMiscellaneous}
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;width:35%;">
						${amountMiscellaneous}
					</td>
										
				</tr>
			
				
			
				<tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						费用合计			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="5">
						小写: ${costtotallowcase} &nbsp;&nbsp;&nbsp;大写 : ${costtotalcapital}    
					</td>
										
				</tr>
				
				
				
				 <c:forEach var="idea" items="${ideas}">
		          <tr>
		            <td style="border:1px #e8e8e8 solid;text-align:center; height:100px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="6" style="border:1px #e8e8e8 solid;padding-left:10px;">${idea.transcontent}</td>
		          </tr>
		       </c:forEach>
		      <%--  
		       <tr>
					<td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">				
						备注			
					</td>
					<td style="border:1px #e8e8e8 solid;padding-left:10px;" colspan="4">
						${remark}
					</td>
										
				</tr> --%>
				</tbody>		
	</table>
	    <!--WordEndExport-->
          <%--上面的注释不要删掉，这是我截取表单的标记 --%>
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
		<a style="text-decoration: underline;"  onclick="openEdit();">维护信息</a>
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
	url="${pageContext.request.contextPath}/oa/oaKqreception!editOptBaseSQ.do?djId=${djId}&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '维护信息', width: 800, height: 450});
}
</script>
<script type="text/javascript">
function print(){
	printView(function(LODOP){
		LODOP.PRINT_INIT("出差经费预算审批表打印");
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
