<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s_text name="innermsg.list.title" />
		</title>
		<script type="text/javascript" >
		 	function msgView(uri){
		 		window.parent.frames[1].location=uri;
		 	}
		 	
		 	function orgType(type){
		 		document.getElementById("s_receivetype").value='O';
		 		document.getElementById("isReveive").value=type;
		 		document.innermsg.submit();
		 	}
		 	
		 	function msgBox(type){
		 		document.getElementById("s_receivetype").value='P';
		 		document.getElementById("isReveive").value=type;
		 		document.innermsg.submit();
		 	}
		 	
		</script>
	</head>

	<body>
	<%@ include file="/page/common/messages.jsp"%>
	<br>
	 <s:form name="innermsg" action="innermsg" namespace="/app" style="margin-top:0;margin-bottom:5">
	 	&emsp;<input type="button" name="built" value="新建消息" class="btn" onclick="msgView('<%=request.getContextPath()%>/app/innermsg!built.do')" >
	 	<br><br>
	 	<input type="hidden" name="s_receivetype" id="s_receivetype"/>
	 	<input type="hidden" name="isReveive" id="isReveive" />
	 	&emsp;<a href="javascript:msgBox('R');">消息收件箱</a>&emsp;<a href="javascript:msgBox('S');">消息发件箱</a>
	 	&emsp;<a href="javascript:orgType('R');">公告收件箱</a>&emsp;<a href="javascript:orgType('S');">公告发件箱</a>
	 </s:form>
		<ec:table action="app/innermsg!list.do" items="objList" var="innermsg"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<c:if test="${isReveive == 'R' || isReveive == 'null'  || isReveive == ''}">
					<ec:column property="msgstate" title="状态" style="text-align:center" >
					<c:if test="${innermsg.receivetype eq 'P' }">
							${cp:MAPVALUE("SysMail",innermsg.msgstate)}
						</c:if>
					</ec:column>
					
				</c:if>
				<ec:column property="msgtitle" title="主题" style="text-align:center" />

				<ec:column property="senddate" title="发送时间" style="text-align:center" />
				
				<ec:column property="receive" title="收件人" style="text-align:center" >
					<c:if test="${innermsg.receivetype eq 'O' }">
						${cp:MAPVALUE("unitcode",innermsg.receive)}
					</c:if>
					<c:if test="${innermsg.receivetype eq 'P' }">
						${cp:MAPVALUE("usercode",innermsg.receive)}
					</c:if>
				</ec:column>
		
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href="javascript:msgView('<%=request.getContextPath()%>/app/innermsg!view.do?msgcode=${innermsg.msgcode}')">查看</a>
					<!-- 
					<a href='app/innermsg!edit.do?msgcode=${innermsg.msgcode}'><s:text name="opt.btn.edit" /></a>
					 -->
					<a href='app/innermsg!delete.do?msgcode=${innermsg.msgcode}' 
							onclick='return confirm("${deletecofirm}innermsg?");'>删除</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
