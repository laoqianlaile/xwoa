<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="cn">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%> 

<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<title>我的消息</title>
</head>
<body>
	
	<form action="" method="post">
		<input type="hidden" name="numPerPage" value="6"/>
	</form>
	
	<div class="container-fluid">
	
		<div class="row-fluid">
			
			<div class="span12">
			
				<table class="table table-striped table-bordered bootstrap-datatable datatable custom center" form="#innermsg_form">
					
					<thead>
						<tr>
							<th width="35%">标题</th>
			                <th width="25%">发件人</th>
			                <th>发送时间</th>
							<!-- <th width="12%" orderField="msgstate">状态</th> -->
							
						</tr>
					</thead>
				
					<tbody id="list_mail_tbody">
						<c:forEach items="${objList }" var="innermsgRecipient">
			                <tr>
			                    <td title="${innermsgRecipient.innermsg.msgtitle}" style="text-align: left;">
			                            <a href="#" class="a_msgtitle" msgcode="${innermsgRecipient.innermsg.msgcode }">${innermsgRecipient.innermsg.msgtitle}</a>
										<c:if test="${'U' eq innermsgRecipient.msgstate }">
											<i class="icon icon-mail-closed"/>
										</c:if>
										<c:if test="${'R' eq innermsgRecipient.msgstate }">
											<i class="icon icon-mail-open"/>
										</c:if>
										<c:if test="${not empty innermsgRecipient.innermsg.msgannexs }">
											<i class="icon icon-page" style="margin-left: 15px;"/>
										</c:if>
			                    </td>
			
			                    <td title="${cp:MAPVALUE('usercode', innermsgRecipient.innermsg.sender)}">${cp:MAPVALUE("usercode", innermsgRecipient.innermsg.sender)}</td>
			
								
			                    <td><fmt:formatDate value="${innermsgRecipient.innermsg.senddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                </tr>
			            </c:forEach>
					
					
					</tbody>
				</table>
				
				<c:set var="maxPageItems" value="5" />
				<c:set var="maxIndexPages" value="3" />
				<c:set var="listURL" value="${pageContext.request.contextPath}/app/innermsgRecipient!userPortal.do" />
				<%@ include file="/page/common/pagingBar.jsp" %>
			</div>
		</div>
		
	</div>
	
	<div class="background" id="background" style="display:none;"></div>
	<div class="progressBar" id="progressBar" style="display:none;"></div>
	<%@ include file="/page/common/charisma-js.jsp"%> 
	<script>
		$(function(){
			$('#list_mail_tbody a.a_msgtitle').click(function() {
				var msgcode = $(this).attr('msgcode');
				window.parent.window.parent.HROS.window.createTemp({
				    id : 'msgcode_' + msgcode,  // 窗口id，必须唯一，建议命名可以个性化点
				    title : '消息/' + $(this).parent('td').attr('title'),  // 窗口标题
				    url : '${contextPath }/app/innermsgRecipient!view.do?id=' + msgcode + "&istempWindow=1",  // 访问地址
				    width : 750,  // 宽
				    height : 650,  // 高
				    isresize : false,  // 窗口是否可拉伸
				    isflash : false  // 窗口里是否有flash（因为 flash 应用如果未设置透明属性，会遮罩住其他应用窗口，如果不确定临时应用窗口会显示什么内容，建议设置为 false ）
				});
			});
			
			
			
			
		});
	</script>
</body>
</html>


