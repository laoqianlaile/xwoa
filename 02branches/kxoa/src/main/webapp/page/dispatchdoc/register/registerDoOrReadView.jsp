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
       <h2 style="color:red;font-family: Microsoft YaHei;text-align:center"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzName')}&nbsp;办&nbsp;文&nbsp;单</h2>    
       <table cellpadding="0" cellspacing="0" style="width:100%;border:1px #e8e8e8 solid; border-collapse:collapse;font-size:14px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
		    <tbody>
		       <tr>
		          <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">来文单位</td>
		          <td style="border:1px #e8e8e8 solid;padding-left:10px;">${object.incomeDeptName}</td>
		          <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;width:130px;">收文日期</td>
		          <td style="border:1px #e8e8e8 solid;padding-left:10px;"><fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' /></td>
		       </tr>
		       <tr>
		          <td style="border:1px #e8e8e8 solid;text-align:center;height:40px;font-weight: bold;">文件标题</td>
		          <td style="border:1px #e8e8e8 solid;padding-left:10px;">${object.incomeDocTitle}</td>
		          <td style="border:1px #e8e8e8 solid;text-align:center;font-weight: bold;width:80px;">收文字号</td>
		          <td style="border:1px #e8e8e8 solid;padding-left:10px;">${object.optBaseInfo.acceptArchiveNo}</td>
		       </tr>
		       <c:forEach var="idea" items="${ideas}">
		          <tr>
		            <td style="border:1px #e8e8e8 solid;text-align:center; height:100px;font-weight: bold;">${idea.transidea}</td>
		            <td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;">${idea.transcontent}</td>
		          </tr>
		       </c:forEach>
		        <!-- <tr>
		          <td style="border:1px #e8e8e8 solid;text-align:center; height:80px;font-weight: bold;">备注</td>
		          <td colspan="3" style="border:1px #e8e8e8 solid;padding-left:10px;"></td>
		       </tr> -->
		       </tbody>
         </table>
          <!--WordEndExport-->
          <%--上面的注释不要删掉，这是我截取表单的标记 --%>
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
		  
       </span>
       
	   <span class="span_state">
	 
		<a style="text-decoration: underline;" onclick="print()">打印</a>
		<a style="text-decoration: underline;display:none" id="editFormBtn">编辑</a>
		</span>
    </div>
   <!-- 隐藏表单 供编辑使用-->
   <s:form method="post" namespace="/dispatchdoc"
		action="dispatchDoc!registerProjectEdit" name="dispatchDocForm"
		id="dispatchDocForm">
		 <input id="overdueType" type="hidden" name="overdueType" value="${object.overdueType }" />
         <input id="djId" type="hidden" name="djId" value="${object.djId }" />
   </s:form>
</body>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">

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