<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>拟稿纸查看页面[在用]</title>
<style type="text/css">
.form-container {
	width: 98%;
	margin: 0 auto;
}

.group-title {
	position: relative;
	height: 22px;
}

.group-title div {
	position: absolute;
	height: 22px;
	top: 0;
	line-height: 22px;
	font-size: 16px;
	font-weight: bold
}

.group-title .title-ico {
	width: 5px;
	background: #56b9fd;
	z-index: 1;
	left: 0;
}

.group-title .title-name {
	z-index: 1;
	left: 5px;
	background: #fff;
	padding: 0 10px;
}

.group-title .title-split-line {
	width: 100%;
	z-index: 0;
	left: 0;
	padding-top: 10px
}

.group-title .title-split-line span {
	display: block;
	width: 100%;
	height: 1px;
	background: #ccc
}

span.span_state {
	float: right;
	margin: 5px 30px;
}

span.span_state1 {
	float: left;
	margin: 5px 30px;
}

span.span_state a {
	color: #000;
	cursor: pointer;
	font-size: 14px
}

span.remindIco {
	background-position: center;
	display: inline-block;
	background-repeat: no-repeat;
	width: 26px;
	height: 26px
}

span.remindIco-overdue {
	background-image: url("${ctxStatic}/image/ycqclock.gif")
}

span.remindIco-toOverdue {
	background-image: url("${ctxStatic}/image/jjcqclock.gif")
}

span.remindIco-none {
	display: none
}
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
		<div class="title-split-line">
			<span></span>
		</div>
	</div>
	<!-- ======================================================拟文稿纸 （这块不要轻易去动，动了需要自己去测试pdf生成） 开始=========================================== -->
	<div class="form-container">
		<%--下面的注释不要删掉，这是我截取表单的标记 --%>
		<!--WordStartExport-->

			<div style="text-align: center; border-bottom: 1px solid red;font-size:20px;font-family:'FZYaoti'">
					<c:if test="${object.itemId eq 'XW-FW-0001' || object.itemId eq 'XW-FW-0013'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}">${cp:MAPVALUE('SYSPARAM','BizzNameGL')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}）</c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共${cp:MAPVALUE('SYSPARAM','BizzName')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}）</c:if>
						</h2>
					</c:if>
					<c:if test="${object.itemId eq 'XW-FW-0002'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameAJ')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<c:if test="${object.itemId eq 'XW-FW-0005'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameAWB')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<c:if test="${object.itemId eq 'XW-FW-0006'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameAJJ')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<c:if
						test="${object.itemId eq 'XW-FW-0003' || object.itemId eq 'XW-FW-0007'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameHBJ')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<c:if test="${object.itemId eq 'XW-FW-0004'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameDQ')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<!-- 规划建设局 -->
					<c:if test="${object.itemId eq 'XW-FW-0008'|| object.itemId eq 'XW-FW-0009' || object.itemId eq 'XW-FW-0010' || object.itemId eq 'XW-FW-0011'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameGHJ')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
					<!-- 监察室 -->
					<c:if test="${object.itemId eq 'XW-FW-0012'}">
						<h2 style="color: red;">
							<span class="remindIco remindIco-none"></span>
							<c:if test="${object.dispatchDocType eq 'PT'}"></c:if>
							<c:if test="${object.dispatchDocType eq 'ZG'}">中共</c:if>${cp:MAPVALUE('SYSPARAM','BizzNameJCS')}（${cp:MAPVALUE('fw_fwwz',object.fwwz)}
							）
						</h2>
					</c:if>
			</div>
<style>
.table_border{border-left:2px solid red;border-top:2px solid red;width: 100%; font-size: 16px; font-family: Microsoft YaHei;text-align: left}
.table_border td{border-bottom: 2px solid red; border-right: 2px solid red;height: 50px;padding:0 15px}
.table_border td>label{display:inline-block;width:80px;text-align:center}
</style>
		<table cellpadding="0" cellspacing="0" class='table_border'>
			<input id="overdueType" type="hidden" name="overdueType" value="${object.overdueType }" />
			<tbody>
			
			<c:if test="${object.itemId eq 'XW-FW-0001'}">
				<tr>
					<td style="width:80px;">
						<label style="color: red;">编号</label> 
					</td>
					<td colspan="2">	
						<c:if
							test="${empty  object.dispatchDocNo}">
	               ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td colspan="2">	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>

						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_2}
						</td>
					</tr>	
					<tr>
						<td style="width:50px;">
						<label style="color: red;">拟稿单位</label>
						</td>
						<td colspan="2"> 
							<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td colspan="2">
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">处室核稿</label> 
						</td>
					    <td colspan="2">
							${IDEA_0}
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">文把</label>
						</td>
					    <td colspan="2">
					   		${IDEA_1}
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">份数</label> 
						</td>
					    <td colspan="2">
							${object.dispatchCopies}
						</td>
					</tr>
					<tr>
						<td colspan="2" style="color: red;text-align: center;width:100px;">拟&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="2" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
						<td colspan="2" rowspan="3" style="height:200px">
							${IDEA_5}
						</td>
					    <td colspan="2" rowspan="3" style="height:200px">
							${IDEA_3}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_4}</p>
						</td>
					</tr>
				</c:if>
			
			
				<c:if test="${object.itemId eq 'XW-FW-0004'|| object.itemId eq 'XW-FW-0010'}">
				<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	               ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
					

						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_2}
						</td>
					</tr>
					<tr>
						<td style="width:50px;">
						<label style="color: red;">拟稿单位</label>
						</td>
						<td> 
							<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>	
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label> 
						</td>
					    <td>
							${IDEA_0}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">文把</label>
						</td>
					    <td>
					   		${IDEA_1}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label> 
						</td>
					    <td>
							${object.dispatchCopies}
						</td>
					</tr>

					<tr>
						<td colspan="3" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="3" style="height:200px">
							${IDEA_3}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_4}</p>
						</td>
					</tr>
				</c:if>
				
				
				<!-- 环保局拟稿纸 -->
				<c:if test="${object.itemId eq 'XW-FW-0003'}">
				<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	              	 ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
							<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
					
						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_2}
						</td>
					</tr>	
					<tr>
					<td>
						<label style="color: red;">拟稿单位</label>
					</td>
					<td> 
						<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
					</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label> 
						</td>
					    <td>
							${IDEA_0}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">文把</label>
						</td>
					    <td>
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label> 
						</td>
					    <td>
							${object.dispatchCopies}
						</td>
					</tr>

					<tr>
						<td colspan="3" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="3" style="height:200px">
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_1}</p>
						</td>
					</tr>
				</c:if>
				

				<!-- 通用拟稿纸 -->
				<c:if test="${object.itemId eq 'XW-FW-0013'}">
				<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	              	 ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_2}
						</td>
					</tr>	
					<tr>
						<td style="width:50px;">
						<label style="color: red;">拟稿单位</label>
						</td>
						<td> 
							<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label>
						</td>
						<td>
						 	${IDEA_1}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">文把</label>
						</td>
						<td>
						 	${IDEA_0}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label>
						</td>
						<td>
							 ${object.dispatchCopies}
						</td>
					</tr>				
					<tr>
						<td colspan="3" style="color: red;text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="3" style="height:200px">
							${IDEA_3}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_4}</p>
						</td>
					</tr>
					
				</c:if>

				<!--安委会拟稿纸和规委会会议纪要拟稿纸  -->
				<c:if test="${object.itemId eq 'XW-FW-0002' || object.itemId eq 'XW-FW-0011'}">
				<tr>
					<td style="width:80px;">
						<label style="color: red;">编号</label> 
					</td>
					<td colspan="2">	
						<c:if
							test="${empty  object.dispatchDocNo}">
	               ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td colspan="2">	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>

						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_2}
						</td>
					</tr>	
					<tr>
						<td style="width:50px;">
						<label style="color: red;">拟稿单位</label>
						</td>
						<td colspan="2"> 
							<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td colspan="2">
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">处室核稿</label> 
						</td>
					    <td colspan="2">
							
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">文把</label>
						</td>
					    <td colspan="2">
					   		${IDEA_0}
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">份数</label> 
						</td>
					    <td colspan="2">
							${object.dispatchCopies}
						</td>
					</tr>
					<tr>
						<td colspan="2" style="color: red;text-align: center;width:100px;">拟&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="2" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
						<td colspan="2" rowspan="3" style="height:200px">
							${IDEA_1}
						</td>
					    <td colspan="2" rowspan="3" style="height:200px">
							${IDEA_3}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_4}</p>
						</td>
					</tr>
				</c:if>

				<!-- 安委办和安监局拟稿纸 -->
				<c:if test="${object.itemId eq 'XW-FW-0005' || object.itemId eq 'XW-FW-0006'|| object.itemId eq 'XW-FW-0009'}">
				<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	              	 ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
					<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
					</td>
					<td rowspan="6" colspan='3'>
							${IDEA_1}
					</td>
				</tr>		
					<tr>
					<td>
						<label style="color: red;">拟稿单位</label>
					</td>
					<td> 
						<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
					</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">文把</label>
						</td>
						<td>
						 	${IDEA_0}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label>
						</td>
						<td>
							 ${object.dispatchCopies}
						</td>
					</tr>		
					<tr>
						<td colspan="3" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="5" style="height:200px">
								${IDEA_2}
						</td>
						<td colspan="3" rowspan="5" style="height:200px">
							
							<p>${IDEA_3}</p>
						</td>
					</tr>
				</c:if>


				<!-- 局长办公会议纪要拟稿纸 -->
				<c:if test="${object.itemId eq 'XW-FW-0008'}">
					<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	              	 ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
							<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
					<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
					</td>
					<td rowspan="6" colspan='3'>
					</td>
				</tr>	
					<tr>
						<td>
						<label style="color: red;">拟稿单位</label>
						</td>
						<td> 
							<frag:wordCellText
								cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>	
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">文把</label>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label> 
						</td>
						<td>
						${object.dispatchCopies}
						</td>
					</tr>

					<tr>
						<td colspan="3" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="3" style="height:200px">
							${IDEA_0}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_1}</p>
						</td>
					</tr>
				</c:if>



				<!-- 环评发文拟稿纸 -->
				<c:if test="${object.itemId eq 'XW-FW-0007'}">
					<tr>
					<td>
						<label style="color: red;">编号</label> 
					</td>
					<td>	
						<c:if
							test="${empty  object.dispatchDocNo}">
	              	 ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
							<label style="color: red;">标题</label> 
					</td>
					<td>	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
				
					<td rowspan="6">
							<label style="color: red;width:10px">会<p>签</p></label>
					</td>
					<td rowspan="6" colspan='3'>
							${IDEA_2}
					</td>
				</tr>	
					<tr>
						<td>
							<label style="color: red;">拟稿单位</label>
						</td>
						<td> 
							<frag:wordCellText
								cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td>
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">处室核稿</label>
						</td>
						<td>
						 	${IDEA_0}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">局长意见</label>
						</td>
						<td>
							${IDEA_1}
						</td>
					</tr>
					<tr>
						<td>
							<label style="color: red;">份数</label>
						</td>
						<td>
							 ${object.dispatchCopies}
						</td>
					</tr>

					<tr>
						<td colspan="3" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
					    <td colspan="3" rowspan="3" style="height:200px">
							${IDEA_3}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_4}</p>
						</td>
					</tr>
				</c:if>
				
				
				<!-- 监察室发文 -->
		 		<c:if test="${object.itemId eq 'XW-FW-0012'}">
				<tr>
					<td style="width:80px;">
						<label style="color: red;">编号</label> 
					</td>
					<td colspan="2">	
						<c:if
							test="${empty  object.dispatchDocNo}">
	               ${cp:MAPVALUE('WJLX',object.dispatchFileType)}〔20&nbsp;&nbsp;&nbsp;&nbsp;〕&nbsp;&nbsp;&nbsp;&nbsp;号
	                </c:if> <c:if test="${not empty  object.dispatchDocNo}">
	                   ${object.dispatchDocNo}
	                </c:if>
					</td>
					<td style='width:10px'>
						<label style="color: red;">密级</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('GDMJ',object.secretsDegree)}" />
					</td>
					<td style='width:50px'>
						<label style="color: red;">缓急程度</label>
					</td>
					<td>
						 <frag:wordCellText
							cellText="${cp:MAPVALUE('critical_level',object.criticalLevel)}" />
					</td>
				</tr>


				<tr>
					<td style="width:50px;text-align:center;">
						<label style="color: red;">标题</label> 
					</td>
					<td colspan="2">	
						<frag:wordCellText
								cellText="${object.dispatchDocTitle}" />
					</td>
					

						<td rowspan="6">
							<label style="color: red;">会<p>签</p></label>
						</td>
						<td rowspan="6" colspan='3'>
							${IDEA_1}
						</td>
					</tr>	
					<tr>
						<td style="width:50px;">
						<label style="color: red;">拟稿单位</label>
						</td>
						<td colspan="2"> 
							<frag:wordCellText
							cellText=" ${cp:MAPVALUE('unitcode',object.optBaseInfo.orgcode)}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">拟稿人</label> 
						</td>
					    <td colspan="2">
							<frag:wordCellText
								cellText="${object.draftUserName}" />
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">处室核稿</label> 
						</td>
					    <td colspan="2">
							${IDEA_0}
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">文把</label>
						</td>
					    <td colspan="2">
					   		
						</td>
					</tr>
					<tr>
						<td tyle="width:50px;">
							<label style="color: red;">份数</label> 
						</td>
					    <td colspan="2">
							${object.dispatchCopies}
						</td>
					</tr>
				<%-- 	<tr>
						<td>
							<label style="color: red;">拟办意见</label> 
						</td>
					    <td>
							${IDEA_5}
						</td>
					</tr> --%>

					<tr>
						<td colspan="2" style="color: red;text-align: center;width:100px;">拟&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="2" style="color: red;text-align: center;">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见
							<label style="color: red;"></label>
						</td>
						<td colspan="3" style="color: red;text-align: center;">签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发
						</td>
					</tr>
					<tr>
						<td colspan="2" rowspan="3" style="height:200px">
							
						</td>
					    <td colspan="2" rowspan="3" style="height:200px">
							${IDEA_2}
						</td>
						<td colspan="3" rowspan="3" style="height:200px">
							
							<p>${IDEA_3}</p>
						</td>
					</tr>
				</c:if>
	

				
				<%-- <tr>
		         <td colspan="3" style="vertical-align:top;padding-left:10px;height:100px;border-bottom: 1px solid red;">
		            <label style="color:red;">附件：</label>
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
		        </tr> --%>


				<%-- <tr>
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
		        </tr>  --%>
			</tbody>
			
			<c:if test="${object.itemId eq 'XW-FW-0001' || object.itemId eq 'XW-FW-0012' || object.itemId eq 'XW-FW-0011' || object.itemId eq 'XW-FW-0002'}">
				<tfoot>
				<tr>
					<td rowspan="2" colspan="1">
						<label style="color: red;">信息公开属性及原因：</label>
					</td>
					<td colspan='6'>
						<p>
							<label style="color: red;">公开属性： <c:forEach var="row"
									items="${cp:DICTIONARY('FW_CAN_OPEN')}" varStatus="status">
									<input type="checkbox" name="dispatchCanOpen"
										value="${row.key}"
										${(object.dispatchCanOpen eq row.key or (empty object.dispatchCanOpen and status.index eq '0')) ? 'checked = "checked"' : ' disabled="disabled" '} />
									<c:out value="${row.value}" />
								</c:forEach>

							</label>
						</p>
					</td>
				</tr>
				<tr>
				   <td colspan='7'>
						<p>
							<label style="color: red;">不予公开理由： <c:forEach var="row"
									items="${cp:DICTIONARY('FW_NOT_OPEN')}" varStatus="status">
									<input type="checkbox" name="notOpenReason" value="${row.key}"
										${(object.notOpenReason eq row.key) ? 'checked = "checked"' : ' disabled="disabled" '} />
									<c:out value="${row.value}" />
								</c:forEach>
							</label>
						</p>
					</td>
				</tr>
				
				<tr>
					<td colspan="7" align="right" style="height: 50px; color: red">
						<span style="display: inline-block; margin-right: 30px;"> <c:if
								test="${not empty object.dispatchdate}">
								<fmt:formatDate value='${object.dispatchdate}'
									pattern='yyyy年MM月dd' />
							</c:if> <c:if test="${empty object.dispatchdate}">20&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</c:if>
							印发&nbsp;&nbsp;
					</span> <span style="display: inline-block;">共印发
							${object.dispatchCopies}份</span>
					</td>
				</tr>
			</tfoot>
			</c:if>
			<c:if test="${object.itemId ne 'XW-FW-0001' && object.itemId ne 'XW-FW-0012' && object.itemId ne 'XW-FW-0011' && object.itemId ne 'XW-FW-0002'}">
				<tfoot>
				<tr>
					<td rowspan="2">
						<label style="color: red;">信息公开属性及原因：</label>
					</td>
					<td colspan='5'>
						<p>
							<label style="color: red;">公开属性： <c:forEach var="row"
									items="${cp:DICTIONARY('FW_CAN_OPEN')}" varStatus="status">
									<input type="checkbox" name="dispatchCanOpen"
										value="${row.key}"
										${(object.dispatchCanOpen eq row.key or (empty object.dispatchCanOpen and status.index eq '0')) ? 'checked = "checked"' : ' disabled="disabled" '} />
									<c:out value="${row.value}" />
								</c:forEach>

							</label>
						</p>
					</td>
				</tr>
				<tr>
				   <td colspan='6'>
						<p>
							<label style="color: red;">不予公开理由： <c:forEach var="row"
									items="${cp:DICTIONARY('FW_NOT_OPEN')}" varStatus="status">
									<input type="checkbox" name="notOpenReason" value="${row.key}"
										${(object.notOpenReason eq row.key) ? 'checked = "checked"' : ' disabled="disabled" '} />
									<c:out value="${row.value}" />
								</c:forEach>
							</label>
						</p>
					</td>
				</tr>
				
				<tr>
					<td colspan="6" align="right" style="height: 50px; color: red">
						<span style="display: inline-block; margin-right: 30px;"> <c:if
								test="${not empty object.dispatchdate}">
								<fmt:formatDate value='${object.dispatchdate}'
									pattern='yyyy年MM月dd' />
							</c:if> <c:if test="${empty object.dispatchdate}">20&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</c:if>
							印发&nbsp;&nbsp;
					</span> <span style="display: inline-block;">共印发
							${object.dispatchCopies}份</span>
					</td>
				</tr>
			</tfoot>
			</c:if>
			
		</table>
		<!--WordEndExport-->
			<%--上面的注释不要删掉，这是我截取表单的标记 --%>

			<c:if test="${'T' eq object.optBaseInfo.flowSupervise }">
				<table id="table_flowSupervise" display="none" cellpadding="0"
					cellspacing="0"
					style="width: 100%; border: 1px #e8e8e8 solid; border-collapse: collapse; font-size: 14px; font-family: Microsoft YaHei; text-align: left; border-top: none;">

					<tr>
						<td
							style="border: 1px #e8e8e8 solid; text-align: center; height: 40px; font-weight: bold; width: 130px; border-top: none;">
							督办批示时限</td>
						<td
							style="border: 1px #e8e8e8 solid; padding-left: 10px; border-top: none;">
							<fmt:formatDate value='${object.optBaseInfo.flowSuperviseDate}'
								pattern='yyyy-MM-dd' />
						</td>

					</tr>
					<tr>
						<td
							style="border: 1px #e8e8e8 solid; text-align: center; height: 40px; font-weight: bold; width: 130px;">
							批示意见</td>
						<td style="border: 1px #e8e8e8 solid; padding-left: 10px;">
							${object.optBaseInfo.flowSuperviseIdea}</td>
					</tr>
				</table>
			</c:if>
		</div>
		<!-- ====================================================================拟文稿纸 （这块不要轻易去动，动了需要自己去测试pdf生成） 结束============================= -->
		<div>
			<span class="span_state1"> <c:choose>
					<c:when test="${'C' eq bizstate }">
						<a style="color: red">办件状态：已办结</a>
					</c:when>
					<c:when test="${'W' eq bizstate }">
						<a style="color: red">办件状态：办理中</a>
						<a style="text-decoration: underline;"
							onclick="openTransactUsers();">查看详细</a>
					</c:when>
				</c:choose> <c:if test="${not empty object.toBeanfinishedDate }">
					<font style="color: red">办理时限：<fmt:formatDate
							value='${object.toBeanfinishedDate}' pattern='yyyy-MM-dd' /></font>
				</c:if>

			</span> <span class="span_state"> <c:if
					test="${param.canEdit eq 'T'}">
					<a style="text-decoration: underline;" onclick="openEdit();">维护信息</a>
				</c:if> <a style="text-decoration: underline;" onclick="print()">打印</a>
			</span>
		</div>
		<!-- 隐藏表单 供编辑使用-->
</body>
<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js"
	type="text/javascript"></script>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">
	function openEdit() {
		url = "${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!editOptBase.do?djId=${djId}&random="
				+ Math.random();
		art.dialog.open(url, {
			title : '维护信息',
			width : 900,
			height : 650
		});
	}

	function openTransactUsers() {
		art.dialog
				.open(
						'${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!listUsersOfTransaction.do?djId=${object.djId}',
						{
							title : '办理人员状态',
							width : 800,
							height : 500
						});

	}

	$(document).ready(
			function() {

				// 根据截止日期状态设置父页面的对应提醒
				var overdueType = $('#overdueType').val();
				/* var remind = $('#remindDiv', window.parent.document); */
				var remind = $('.remindIco');
				if ('I' == overdueType) {
					remind.removeClass("remindIco-none").addClass(
							'remindIco-toOverdue');
					remind.attr("title", "即将超期");
				} else if ('O' == overdueType) {
					remind.removeClass("remindIco-none").addClass(
							'remindIco-overdue');
					remind.attr("title", "已超期");
				}

			});

	function print() {
		printView(function(LODOP) {
			LODOP.PRINT_INIT("发文拟文单打印");
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
			$(".form-container").find("td").each(function() {
				$(this).height($(this).height());
			});
			LODOP.ADD_PRINT_HTM("150px","80px","600px","90%",$(".form-container").html());
		});
	}
</script>
</html>