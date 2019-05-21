<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>
<%@ include file="/page/common/print.jsp"%>
<%-- <%@ include file="/page/common/css.jsp"%> --%>
<html>
<head>
  <title>办件详情</title>
<style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     。group-title{position:relative;height:22px;}
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
<body>

		<c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	    <input id="djId" type="hidden" name="djId" value="${optBaseInfo.djId }" />
	 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">
	    <c:if test="${itemType eq 'SQ' }">事项信息</c:if>
	    <c:if test="${itemType eq 'QB' }">签报信息</c:if>
	     </div>
	    <div class="title-split-line"><span></span></div>
   </div>
       <!-- ======================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
   <div class="form-container"  >
		      <%--下面的注释不要删掉，这是我截取表单的标记 --%>
		      <!--WordStartExport-->  
		<div style="text-align: center;font-size:25px;">
           <h3 style="color:red;font-family: Microsoft YaHei;text-align:center;padding-top:15px;">
			${cp:MAPVALUE('SYSPARAM','BizzName')}<c:if test="${itemType eq 'SQ' }">事权</c:if><c:if test="${itemType eq 'QB' }">签报</c:if>单
			</h3>
		</div>
      		<table cellpadding="0" cellspacing="0" style="width:100%;border-top:1px solid black; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
				 <tbody>
				<tr>
					<td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">				
						标题				
					</td>
					<td style="border-top:1px solid black;border-left:1px solid black;padding-left:10px;" colspan="3">
						${optBaseInfo.transaffairname}
					</td>					
				</tr>
				<c:if test="${itemType ne 'QB' }">
				<tr>
					<td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">
						事项内容
					<td style="border-top:1px solid black;padding-left:10px;" colspan="3" style='border-bottom: 0px'>
						${optBaseInfo.content}
					</td>
				</tr>
				</c:if>
				<c:if test="${itemType eq 'QB' }">
				
				<tr>
				<td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">
						日期
					</td>
					<td style="border-top:1px solid black;border-left:1px solid black;border-right:1px solid black;padding-left:10px;">
					<s:date name="applyDate" format="yyyy-MM-dd" />
<%-- 				   <fmt:formatDate value="${optBaseInfo.createdate }" pattern="yyyy-MM-dd" /> --%>
					</td>	
				   <td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">
						缓急程度
					</td>
					<td style="border-top:1px solid black;border-left:1px solid black;padding-left:10px;">
					<div id="remindDiv" ></div><font><c:if test="${empty optBaseInfo.criticalLevel}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
						${cp:MAPVALUE("critical_levelsq",optBaseInfo.criticalLevel)}</font>
					</td>					
				</tr>
				<tr>
				    <td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">
						主办单位
					</td>
					<td style="border-top:1px solid black;border-left:1px solid black;border-right:1px solid black;padding-left:10px;width:40%;">
						${cp:MAPVALUE("unitcode",optBaseInfo.orgcode)}
					</td>		
				    <td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;">
						经办人
					</td>
					<td style="border-top:1px solid black;border-left:1px solid black;padding-left:10px;width:40%;" >
						${cp:MAPVALUE("usercode",optBaseInfo.createuser)}
					</td>										
				</tr>	
				<tr>
					<td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;" style="border-bottom: 0px">
						签报内容					
					<td style="border-top:1px solid black;border-left:1px solid black;padding-left:10px;" colspan="3" style="border-bottom: 0px">
						${optBaseInfo.content}
					</td>
				</tr>
				</c:if>	
				<%--  <c:forEach var="idea" items="${ideas}">
		          <tr>
		            <td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border-top:1px solid black;padding-left:10px;">${idea.transcontent}</td>
		          </tr>
		       </c:forEach> --%>
		        <tr>
					<c:forEach var="idea" items="${ideas}">
					<c:choose>
					<c:when test="${'拟办意见' eq idea.transidea }">
				 	  <td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">
						拟办意见		
					  </td>
					  <td colspan="3" style="border-top:1px solid black;padding-left:10px;">
						<%-- <frag:wordCellText cellText="${idea.transcontent}"/> --%>
						${idea.transcontent}
					  </td>
					
					</c:when>
					</c:choose>
					</c:forEach>
				</tr>
				 <tr>
					<c:forEach var="idea" items="${ideas}">
					<c:choose>
					<c:when test="${'主要领导批示' eq idea.transidea }">
				 	<td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">
						领导批示		
					</td>
					<td colspan="3" style="border-top:1px solid black;padding-left:10px;">
						<%-- <frag:wordCellText cellText="${idea.transcontent}"/> --%>
						${idea.transcontent}
					</td>
					
					</c:when>
					</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="idea" items="${ideas}">
					<c:choose>
					<c:when test="${'办理结果' eq idea.transidea }">
				 	<td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">
						办理结果		
					</td>
					<td colspan="3" style="border-top:1px solid black;padding-left:10px;">
					    <%-- <frag:wordCellText cellText="${idea.transcontent}"/> --%>
						${idea.transcontent}
					</td>
					
					</c:when>
					</c:choose>
					</c:forEach>
				</tr>
				</tbody>		
	</table>
<%-- 	<table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
		<%@ include file="/page/common/idea.jsp"%>
	</table> --%>
	    <!--WordEndExport-->
          <%--上面的注释不要删掉，这是我截取表单的标记 --%>
	</div>
	<!-- ====================================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
	
	    <div>
	   <span class="span_state">
	    <c:choose>
					<c:when test="${'C' eq optBaseInfo.bizstate }"><a style="color:red">办件状态：已办结</a></c:when>
					<c:when test="${'W' eq optBaseInfo.bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
		</c:choose>
		<c:if test="${itemType eq 'QB' }">
				<a style="text-decoration: underline;"  onclick="openEdit();">编辑信息</a>
		</c:if>
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
function print(){
	printView(function(LODOP){
		LODOP.PRINT_INIT("签报单打印");
		LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
		$(".form-container").find("td").each(function(){
			$(this).height($(this).height());
		});
		LODOP.ADD_PRINT_HTM("150px","50px","650px","90%",$(".form-container").html());
	});
}
function openEdit() {
	url="${pageContext.request.contextPath}/powerruntime/optApply!editQB.do?dj_id=${djId}&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '维护信息', width: 1000, height: 600});
}
function openTransactUsers(){
	
	var djId = $('#djId').val();
	art.dialog
	.open('${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!listUsersOfTransaction.do?djId=' + djId,
			 {title: '办理人员状态', width: 800, height: 500});

}
</script>
</html>
