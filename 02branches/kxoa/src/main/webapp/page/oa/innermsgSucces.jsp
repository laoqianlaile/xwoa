<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<style>
body {
	background-color: #ECF7FB;
	height: 500px;
	width: 1000px;
}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />


<title>我的消息</title>
</head>
<body>
	<s:form>
		<span class="successInnermsg" style="width: 200px; height: 80px;"></span>&nbsp;</br>
此邮件已发送成功，并已保存到"发件箱"文件夹下</br>

        <input type="hidden" id="s_msgtype" name="s_msgtype" value="${s_msgtype }" />

		<c:if test="${'F' ne s_msgtype }">
			<input type="button" class="btn btnWide" value="返回草稿箱"
				onclick="openTabinnerCGX();" />
		</c:if>
		<input type="button" class="btn btnWide" value="返回发件箱"
			onclick="openTabinnerFJX();" />
		<input type="button" class="btn btnWide" value="返回收件箱"
			onclick="openTabinnerSJX();" />
		<c:if test="${'F' ne s_msgtype }">
			<input type="button" class="btn btnWide" value="返回未读列表"
				onclick="openTabinnerWD();" />
		</c:if>

	</s:form>
</body>
<script type="text/javascript">
//    var arr=new Array("XXX");
   var s_msgtype = $('#s_msgtype').val();
   function openTabinnerSJX(){
<%-- 	   window.location.href="<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=O"; --%>
       /* debugger; */
	   if('F' == s_msgtype){
		   window.top.parentAClick("menu_QF_SJX",true);
	   }else{
	       window.top.parentAClick("menu_SJX",true);	   
	   }
   }
   function openTabinnerFJX(){
<%-- 	   window.location.href="<%=request.getContextPath()%>/oa/innermsg!list.do?s_msgtype=P&s_mailtype=O"; --%>
		/* debugger; */
		if('F' == s_msgtype){
			   window.top.parentAClick("menu_QF_FJX",true);
		}else{window.top.parentAClick("menu_FJX",true);}
   
   }
   function openTabinnerCGX(){
<%-- 	   window.location.href="<%=request.getContextPath()%>/oa/innermsg!list.do?s_msgtype=P&s_mailtype=D"; --%>
	   window.top.parentAClick("menu_DRAFTSBOX",true);
   }
   function openTabinnerWD(){
<%-- 	   window.location.href="<%=request.getContextPath()%>/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=O&s_msgstate=U"; --%>
	   window.top.parentAClick("menu_WDSJX",true);
	   
   }
   

</script>

</html>


