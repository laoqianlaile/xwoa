<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <title><s:text name="" /></title>
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
<body>
   <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">拟文单</div>
	     <div class="title-split-line"><span></span></div>
   </div>
   <!-- ======================================================拟文稿纸 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
   <div class="form-container">
      <%--下面的注释不要删掉，这是我截取表单的标记 --%>
      <!--WordStartExport-->
       <table cellpadding="0" cellspacing="0" style="width:100%;font-size:16px;font-family: Microsoft YaHei;text-align:left;table-layout: fixed;">
		    <tbody>
		       <tr>
		         <td colspan="2" style="text-align:center;border-bottom: 2px solid red;">
		             <h2 style="color:red;"><span class="remindIco remindIco-none"></span>${cp:MAPVALUE('SYSPARAM','BizzName')}&nbsp;拟&nbsp;稿&nbsp;纸</h2>    
		         </td>
		       </tr>
		       <tr>
		         <td rowspan="3" width="50%" style="vertical-align: top;padding-left:10px;border-bottom: 1px solid red;border-right: 1px solid red;" >
		             <label style="color:red;">事由：</label><frag:wordCellText cellText="${object.dispatchDocSummary}"/>
		         </td>
		         <td width="50%" style="padding-left:10px;height:50px;border-bottom: 1px solid red;">
		            <label style="color:red;">编号：</label>
		            <c:if test="${empty  object.dispatchDocNo}">
	              ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if>
	                <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo }
	                </c:if>
		         </td>
		       </tr>
		       <tr>
		          <td style="padding-left:10px;height:50px;border-bottom: 1px solid red;">
		             <label style="color:red;">秘密等级：</label><frag:wordCellText cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}"/>
		          </td>
		       </tr>
		       <tr>
		         <td style="padding-left:10px;height:50px;border-bottom: 1px solid red;">
		            <label style="color:red;">主办单位和拟稿人：</label><frag:wordCellText cellText="${object.optUnitName}"/>&nbsp;&nbsp;<frag:wordCellText cellText="${object.draftUserName}"/>
		         </td>
		       </tr>
		       <tr>
		         <td rowspan="2" style="vertical-align:top;padding-left:10px;border-bottom: 1px solid red;border-right: 1px solid red;">
		            <label style="color:red;">部门负责人意见：</label>
		            <p>${FW_CSSH_IDEA}</p>
		         </td>
		         <td style="padding-left:10px;height:50px;border-bottom: 1px solid red;">
		            <label style="color:red;">核稿人：</label>${FW_PB_IDEA}
		         </td>
		       </tr>
		        <tr>
		         <td style="padding-left:10px;height:50px;border-bottom: 1px solid red;">
		            <label style="color:red;">校对人：</label>${FW_PB_IDEA}
		         </td>
		        </tr>
		        <tr>
		         <td style="vertical-align:top;padding-left:10px;height:100px;border-bottom: 1px solid red;border-right: 1px solid red;">
		             <label style="color:red;">签发：</label>
		             <p>${FW_LDQF_IDEA}</p>
		         </td>
		         <td style="vertical-align:top;padding-left:10px;height:100px;border-bottom: 1px solid red;">
		            <label style="color:red;">附件或备注：</label>
		            <p>
		              <c:forEach var="stuff" items="${optStuffs}">
		                 <c:if test="${stuff.archivetype!='zw'}">
		                    <c:if test="${stuff.iszhi=='1'}"><a href="javascript:void(0)">${stuff.filename}</a></c:if>
		                    <c:if test="${stuff.iszhi!='1'}"><a href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid=${stuff.stuffid}">${stuff.filename}</a></c:if>
		                 </c:if>
		              </c:forEach>
		            </p>
		             <p>${fw_dj_IDEA}</p>
		         </td>
		        </tr>
		         <tr>
		         <td colspan="2" style="vertical-align:top;padding-left:10px;height:100px;border-bottom: 1px solid red;">
		            <label style="color:red;">会签：</label>
		             <p>${FW_FGLDHQ_IDEA} </p>
		         </td>
		        </tr>
	         	<tr>
		         <td colspan="2" style="padding-left:10px;height:50px;border-bottom: 1px solid red;"> 
		           <label style="color:red;">文件标题：</label>${object.dispatchDocTitle}
		          </td>
		        </tr>
		        <tr>
		         <td colspan="2" style="padding-left:10px;height:50px;border-bottom: 1px solid red;"> 
		           <label style="color:red;">主题词：</label>${object.dispatchTitle}
		          </td>
		        </tr>
		        <tr>
			        <td colspan="2" style="height:50px;padding-left:10px;">
			           <label style="color:red;">主送：</label>${object.mainNotifyUnit}
			        </td>
		        </tr>
		         <tr>
			         <td colspan="2" style="height:50px;padding-left:10px;border-bottom: 1px solid red">
			            <label style="color:red;">抄送：</label>${object.otherUnits}
			         </td>
		        </tr>
		    </tbody>
		    <tfoot>
		       <tr>
		         <td colspan="2" align="right" style="height:50px;color:red">
		            <span style="display:inline-block;margin-right:30px;">
		              <c:if test="${not empty object.dispatchdate}"><fmt:formatDate value='${object.dispatchdate}'
							pattern='yyyy年MM月dd' /></c:if>
					  <c:if test="${empty object.dispatchdate}">20&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</c:if>
							印发&nbsp;&nbsp;</span>
		            <span style="display:inline-block;">共印发 ${object.dispatchCopies}份</span>
		          </td>
		       </tr>
		    </tfoot>
         </table>
          <!--WordEndExport-->
          <%--上面的注释不要删掉，这是我截取表单的标记 --%>
   </div>
   <!-- ====================================================================拟文稿纸 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
    <div>
	   <span class="span_state">
	    <c:choose>
					<c:when test="${'C' eq bizstate }"><a style="color:red">办件状态：已办结</a></c:when>
					<c:when test="${'W' eq bizstate }"><a style="color:red">办件状态：办理中</a> <a style="text-decoration: underline;"  onclick="openTransactUsers();">查看详细</a>
					</c:when>
		</c:choose>
		<a style="text-decoration: underline;" onclick="print()">打印</a>
		<a style="text-decoration: underline;display:none" id="editFormBtn">编辑</a>
		</span>
    </div>
   <!-- 隐藏表单 供编辑使用-->
   <s:form method="post" namespace="/dispatchdoc"
		action="dispatchDoc!registerProjectEdit" name="dispatchDocForm"
		id="dispatchDocForm">
		<input type="hidden" id="incomeNo" name="incomeNo"
			value="${object.incomeNo}" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowInstId" name="flowInstId"
			value="${flowInstId}" />
		<input type="hidden" id="hqIdeacode" name="hqIdeacode"
			value="${hqIdeacode}" />
		<input type="hidden" id="lhIdeacode" name="lhIdeacode"
			value="${lhIdeacode}" />
		<input id="overdueType" type="hidden" name="overdueType" value="${object.overdueType }" />
   </s:form>
</body>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">
	// 可以进行对拟文单编辑的环节代码nodeCode枚举
	var formEditParams = [ "FW_BGSMS","FW_CSSH","FW_XGNW","FW_NW" ];
	var nodeCode = "";
	var init = null;
	var func = null;

	var transInit = null;
	var hqIdeacode = "${hqIdeacode}";
	var lhIdeacode = "${lhIdeacode}";

	function changeShow(nodeCode) {
		if (window.parent.frames["transFrame"]) {
			nodeCode = $("#nodeCode",
					window.parent.frames["transFrame"].document).val();
			if ("" != nodeCode) {
				for (var i = 0; i < formEditParams.length; i++) {
					if (nodeCode == formEditParams[i]) {
						$("#editFormBtn").show();
						FrameUtils.adjustParentHeight(window,0);
						break;
					}
				}
			}
		}
	}

	function doTransInit(hqIdeacode, lhIdeacode) {
		if (window.parent.frames["transFrame"]) {
			if ("ok" == $("#isReady",
					window.parent.frames["transFrame"].document).val()) {
				if ("FW_BGSMS" == $("#nodeCode",
						window.parent.frames["transFrame"].document).val()
						&& "" != hqIdeacode) {
					$("#ideacode", window.parent.frames["transFrame"].document)
							.val(hqIdeacode);
				}
				if ("FW_LDQF" == $("#nodeCode",
						window.parent.frames["transFrame"].document).val()
						&& "" != lhIdeacode) {
					$("#ideacode", window.parent.frames["transFrame"].document)
							.val(lhIdeacode);
				}
				clearInterval(transInit);
			}
		} else {
			clearInterval(transInit);
		}
	}

	function openTransactUsers(){
		
		debugger;
		var djId = $('#djId').val();
		art.dialog
		.open('${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!listUsersOfTransaction.do?djId=' + djId,
				 {title: '办理人员状态', width: 800, height: 500});

	}
	
	$(document).ready(
			function() {
				if (!$("#isUnionDispatch").attr("checked")) {
					$("#unionDispatchUnitsTr").hide();
				}
				if (!$("#isCountersign").attr("checked")) {
					$("#countersignUnitsTr").hide();
				}

				func = changeShow;
				setTimeout(function(){
					
				init = setInterval("FrameUtils.initialize(window, init, func)",
						100);
				}, 1000);

				hqIdeacode = $("#hqIdeacode").val();
				lhIdeacode = $("#lhIdeacode").val();
				transInit = setInterval("doTransInit(hqIdeacode, lhIdeacode)",
						100);

				$("#editFormBtn").click(function() {
					$("#dispatchDocForm").submit();
				});
				
				// 根据截止日期状态设置父页面的对应提醒
				var overdueType = $('#overdueType').val();
				/* var remind = $('#remindDiv', window.parent.document); */
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
				LODOP.PRINT_INIT("拟发文拟文单打印");
				LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
				$(".form-container").find("td").each(function(){
					$(this).height($(this).height());
				});
				LODOP.ADD_PRINT_HTM(10,0,"100%","100%",$(".form-container").html());
			});
		}
</script>
</html>