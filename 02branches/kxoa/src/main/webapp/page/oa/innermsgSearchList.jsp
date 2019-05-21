
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="cn">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%> 


<title>我的消息</title>
</head>
<body>

	<div class="container-fluid">
	
		<ul class="breadcrumb">
			<c:choose>
				<c:when test="${'O' eq param['mailtype'] }">
					<li><a href="${contextPath }/app/innermsg!list.do">发件箱</a> <span class="divider">/</span></li>
				</c:when>
				<c:when test="${'I' eq param['mailtype'] }">
					<li><a href="${contextPath }/app/innermsgRecipient!list.do">收件箱</a> <span class="divider">/</span></li>
				</c:when>
			</c:choose>
		
		  
		  <li class="active">搜索</li>
		</ul>
		
	
		<div class="row-fluid toolsBar" style="padding: 10px 0px;">
			<div class="span12">
				
				<div class="btn-group" style="float: right; ">
					<div class="input-append">
					  <input class="span4" name="key" id="txt_search" style="width: 175px;" type="text" value="${param['key'] }">
					  <span class="add-on" id="span_search">搜索</span>
					</div>
				</div>
				
			</div>
			
		</div>
	
		<div class="row-fluid">
			<div class="span12">
				<c:forEach items="${objList }" var="innermsg">
					<div>
						<div style="color: #006699">
							<h3>
								<a href="${contextPath }/app/innermsg!view.do?msgcode=${innermsg.msgcode}">${innermsg.msgtitle }</a>
							</h3>
						</div>
						<div STYLE="height: 80px; overflow: hidden; text-overflow: ellipsis">
							${innermsg.summary }
						</div>
					</div>
				</c:forEach>
				
				
				<c:forEach items="${innermsgRecipients }" var="innermsgRecipient">
					<div>
						<div style="color: #006699">
							<h3>
								<a href="${contextPath }/app/innermsg!view.do?msgcode=${innermsgRecipient.innermsg.msgcode}">${innermsgRecipient.innermsg.msgtitle }</a>
							</h3>
						</div>
						<div STYLE="height: 80px; overflow: hidden; text-overflow: ellipsis">
							${innermsgRecipient.innermsg.summary }
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<c:set var="listURL" value="${pageContext.request.contextPath}/app/innermsg!search.do" ></c:set>
		<%@ include file="/page/common/pagingBar.jsp" %>			
	</div>
		
	
	
	<div class="background" id="background" style="display:none;"></div>
	<div class="progressBar" id="progressBar" style="display:none;"></div>
	<%@ include file="/page/common/charisma-js.jsp"%>
	
	<script type="text/javascript">
		$(function(){
			$('#span_search').click(function(){
				var key = $('#txt_search').val();
				if('' == $.trim(key)) {
					return;
				}
				
				window.location = "${contextPath }/app/innermsg!search.do?key=" + key;
			});
		});
	</script>
</body>
</html>



