<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>短信平台配置
		</title>
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>
<body class="sub-flow">
	 <fieldset style="padding:10px;">
	 	<legend style="margin-bottom:10px;">短信平台参数配置</legend>
	 	
	 	 <input type="button" value="添加新配置" class="btn btnWide"  onclick="openAdd();"  />
	 
         <table cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">
						运营商
					</td>
					<td align="left">
						${cp:MAPVALUE("sendMsgSource",object.operatorSource)}
					</td>
					<td class="addTd">
						开启状态
					</td>
					<td align="left">
					<c:if test="${'T' eq  object.openOrClose}">
					开启
					</c:if>
					<c:if test="${'F' eq  object.openOrClose}">
					关闭
					</c:if>
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						短信网关
					</td>
					<td align="left">
						${object.gateway }
					</td>

					<td class="addTd">
						授权端口
					</td>
					<td align="left">
						${object.port }
					</td>
				</tr>
				<tr>
				    <td class="addTd">
						授权用户
					</td>
					<td align="left">
						${object.username }
					</td>

					<td class="addTd">
						授权密码
					</td>
					<td align="left">
						${object.password }
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						短信模板
					</td>
					<td align="left" colspan="3">
						${object.sendMSgMod }
					</td>
				</tr>
				
				<tr>
				   <td class="addTd">
						是否限制条数
					</td>
					<td align="left">
					    ${cp:MAPVALUE("YES_NO",object.limitYesOrNo)}
					</td>
					<td class="addTd">
						限制条数
					</td>
					<td align="left" >
						${object.limitNumber } 条
					</td>
				</tr>
				
				<tr>
				    <td class="addTd">
						备注说明
					</td>
					<td align="left" colspan="3">
						${object.remark }
					</td>
				</tr>
				
			</table>
 </fieldset>
			 <fieldset style="padding:10px;">
	 	<legend style="margin-bottom:10px;">短信平台历史参数</legend>
	 	<ec:table items="objList" var="var"  sortable="false" showPagination="false"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  tableId="uu" >
		<ec:row>
<%-- 		    <ec:column property="smsid" title="版本号" style="text-align:center" /> --%>
			<ec:column property="operatorSource" title="运营商" style="text-align:center" >
			<c:out value="${cp:MAPVALUE('sendMsgSource',var.operatorSource)}" />
			</ec:column>
			<ec:column property="status" title="发布状态" style="text-align:center" >
			<c:out value="${cp:MAPVALUE('sendMsgConfigState',var.status)}" />
			</ec:column>
			<ec:column property="openOrClose" title="否使用短信功能" style="text-align:center" >
			<c:if test="${'T' eq  var.openOrClose}">
			开启
			</c:if>
			<c:if test="${'F' eq  var.openOrClose}">
			关闭
			</c:if>
			</ec:column>
			<ec:column property="gateway" title="短信网关" style="text-align:center" />
			<ec:column property="username" title="授权用户" style="text-align:center" />
			<ec:column property="port" title="授权端口" style="text-align:center" />
			<ec:column property="sendMSgMod" title="短信模板" style="text-align:center" />
			<ec:column property="settime" title="更新时间" format="yyyy-MM-dd hh:mm" cell="date"  style="text-align:center"/>
		
			<ec:column property="unitopt" title="操作" sortable="false"	 >
			<span  onclick='openEdit("${var.smsid}")'>
					维护
			</span>
		    <c:if test="${'B' eq  var.status}">
			<a href='oaSMSConfig!updateState.do?smsid=${var.smsid}&status=D'
					onclick='return alert("是否停用此条配置");'>
					停用
			</a>
			</c:if>
			<c:if test="${'B' ne var.status}">
			<a href='oaSMSConfig!updateState.do?smsid=${var.smsid}&status=B'
			        onclick='return alert("是否启用此条配置");'>
				           启用
			</a> 
			</c:if>
				
			</ec:column>
		
		</ec:row>
	</ec:table> 

 </fieldset>			
</body>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>

<script type="text/javascript">
function openAdd() {
	url="${pageContext.request.contextPath}/oa/oaSMSConfig!addSMSConfig.do?smsid=${object.smsid}&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '维护信息', width: 800, height: 450});
}
function openEdit(smsid) {
	url="${pageContext.request.contextPath}/oa/oaSMSConfig!editSMSConfig.do?smsid="+smsid+"&random=" + Math.random();
	art.dialog
	.open(url,
			 {title: '维护信息', width: 800, height: 450});
}
</script>
</html>
