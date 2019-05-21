<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>

<html>
<head>
  <title>办（阅办）件</title>
  <style type="text/css">
     .form-container{width:98%;margin:0 auto;}
     .group-title{position:relative;height:22px;}
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
  
	<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	
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
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">公文阅批单</div>
	     <div class="title-split-line"><span></span></div>
   </div>
   <c:if test="${ !isVailViewPower  or  (isVailViewPower and  cp:CHECKUSEROPTPOWER('DOCXZ', 'DocViewTitle'))}">
	<!-- ======================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
   <div class="form-container">
      <%--下面的注释不要删掉，这是我截取表单的标记 --%>
      <!--WordStartExport-->
      <div style="text-align: center;font-size:20px;">
      	<c:if test="${object.itemId eq 'XW-SW-0001' || object.itemId eq 'XW-SW-0013'}">
       		<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameGWH')}办文单</h2>  
		</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0002'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameAJJ')}办文单</h2>  
			</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0003'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameHBJ')}办文单</h2>  
			</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0004'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameDQ')}办文单</h2>  
			</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0005'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameAJ')}办文单</h2>  
			</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0008'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameGHJ')}办文单</h2>  
			</c:if>
			<c:if test="${object.itemId eq 'XW-SW-0014'}">
       			<h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzNameGZB')}办文单</h2>  
			</c:if>
	</div>
       <h3 style="text-align:center;font-family: SimSun;">
			${object.optBaseInfo.acceptArchiveNo}
			</h3>  
       <table cellpadding="0" cellspacing="0" style="width:100%;border-top:1px solid black;border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;">
		    <tbody>
		       <tr >
		      	  <td  rowspan="2" style="text-align:center;height:40px;font-weight: bold;width:130px;border-top:1px solid black">来文单位:</td>
		          <td  rowspan="2" style="border-top:1px solid black;border-right:1px solid black;padding-left:10px;">${object.incomeDeptName}</td>
		          <td  style="border-top:1px solid black;border-left:1px solid black;border-bottom:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px;" >收文日期:</td>
		          <td  style="border-top:1px solid black;border-left:1px solid black;border-bottom:1px solid black;padding-left:10px;">
		          <frag:wordCellText cellText="${object.incomeDateStr}"/></td>
		      	  
		       </tr>
		       <tr>
		       	  
		      	  <td  style="border-left:1px solid black;;text-align:center;height:40px;font-weight: bold;width:130px;">文件原号:</td>
		          <td  style="border-left:1px solid black;padding-left:10px;">${object.incomeDocNo}</td>
		       </tr>
		       <tr>
		          <td style="border-top:1px solid black;text-align:center;height:40px;font-weight: bold;">来文标题:</td>
		          <td colspan="3" style="border-top:1px solid black;padding-left:10px;"><frag:wordCellText cellText="${object.incomeDocTitle}"/>
		          </td>
		       </tr>
		       <c:forEach var="idea" items="${ideas}">
		       <c:if test="${idea.transidea eq '领导批示:'}">
		          <tr width="100%">
		           <td style="border-top:1px solid black;text-align:center; height:310px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border-top:1px solid black;padding-left:10px;">${idea.transcontent}</td>   </tr>
		       </c:if>
		       <c:if test="${idea.transidea ne '领导批示:'}">
		       <c:if test="${idea.transidea eq '办理结果:' and object.itemId eq 'XW-SW-0001'}">
		          <tr width="100%">
		            <td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border-top:1px solid black;padding-left:10px;">${idea.transcontent}</td>
		          </tr>
		       </c:if>
		        <c:if test="${idea.transidea eq '办理结果:' and object.itemId ne 'XW-SW-0001'}">
		          <tr width="100%">
		            <td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border-top:1px solid black;padding-left:10px;">${idea.transcontent}</td>
		          </tr>
		        </c:if>
		         <c:if test="${idea.transidea ne '办理结果:'}">
		          <tr width="100%">
		           <td style="border-top:1px solid black;text-align:center; height:130px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border-top:1px solid black;padding-left:10px;">${idea.transcontent}</td>   
		          </tr>
		         </c:if>
		       </c:if>
		       </c:forEach>
		    
		       </tbody>
         </table>
          <!--WordEndExport-->
          <%--上面的注释不要删掉，这是我截取表单的标记 --%>
          <c:if test="${'T' eq object.optBaseInfo.flowSupervise }">
         <table  id="table_flowSupervise"  display="none" cellpadding="0" cellspacing="0" style="width:100%;border-top:1px solid black; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left; border-top: none;">	
				
                  <tr >
					<td style="border:1px solid black;text-align:center;height:40px;font-weight: bold;width:130px; border-top: none;">
						督办批示时限
					</td>
					<td style="border:1px solid black;padding-left:10px; border-top: none;">
							<fmt:formatDate value='${object.optBaseInfo.flowSuperviseDate}' pattern='yyyy-MM-dd' /> 
					</td>
					
				</tr>
				<tr >
				<td style="border:1px solid black;text-align:center;height:200px;font-weight: bold;width:130px;">
					批示意见
				</td>
				<td style="border:1px solid black;padding-left:10px;">
					${object.optBaseInfo.flowSuperviseIdea}
				</td>
				</tr>
          </table>
          </c:if>
          
   </div>
   <!-- ====================================================================办文单 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
   </c:if>
    <div>
       <span class="span_state1" >
             <c:choose>
					<c:when test="${'C' eq bizstate }"><a style="color:red">办件状态：已办结</a></c:when>
					<c:when test="${'W' eq bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
		     </c:choose>
		      <c:if test="${not empty object.toBeanfinishedDate }">
		     <font  style="color:red">办理时限：<fmt:formatDate value='${object.toBeanfinishedDate}' pattern='yyyy-MM-dd' /></font>
		     </c:if>
		 <%--     <c:if test="${'T' eq object.optBaseInfo.flowSupervise }">
		                 <span  id="flowSupervise" >查看督办批示</span> 
		     </c:if> --%>
		  
       </span>
	   <span class="span_state">
	   
		<a style="text-decoration: underline;" onclick="print()">打印</a>
		<c:if test="${canEdit eq 'T'}"> 
		<a style="text-decoration: underline;" id="editFormBtn" onclick="openEditNew()">编辑信息</a>
		</c:if>
		</span>
    </div>
   <!-- 隐藏表单 供编辑使用-->
   <s:form method="post" namespace="/dispatchdoc"
		action="dispatchDoc!registerProjectEdit" name="dispatchDocForm"
		id="dispatchDocForm">
		 <input id="overdueType" type="hidden" name="overdueType" value="${object.overdueType }" />
         <input id="djId" type="hidden" name="djId" value="${object.djId}" />
   </s:form>
</body>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">

function openEditNew() {
	url="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEditNew.do?djId=${djId}";
	art.dialog
	.open(url,
			 {title: '编辑信息', width: 900, height:650});
}

$(function(){
	
	// 根据截止日期状态设置对应提醒
	var overdueType = $('#overdueType').val();
	var remind = $('.remindIco');
	if('I' == overdueType){
		remind.removeClass("remindIco-none").addClass('remindIco-toOverdue');
		remind.attr("title","即将超期");
	}else if('O' == overdueType){
		remind.removeClass("remindIco-none").addClass('remindIco-overdue');
		remind.attr("title","已超期");
	}
	
});

function print(){
	printView(function(LODOP){
		LODOP.PRINT_INIT("公文阅批单打印");
		LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
		$(".form-container").find("td").each(function(){
			$(this).height($(this).height());
		});
		LODOP.ADD_PRINT_HTM("150px","40px","650px","87%",$(".form-container").html());
	});
}
function openTransactUsers(){
	art.dialog
	.open('${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!listUsersOfTransaction.do?djId=${object.djId}',
			 {title: '办理人员状态', width: 800, height: 500});
}
</script>
</html>