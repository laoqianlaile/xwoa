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


<title>视频上传</title>
</head>
<body>
	<s:form style="text-align: center;">
		<span class="successInnermsg" style="width: 200px; height: 80px;"></span>&nbsp;</br>
        <span style="font-size:18px;">视频处理中,请在半个小时后观看....</span></br>
</br></br>
        <input type="hidden" id="s_msgtype" name="s_msgtype" value="${s_msgtype }" />

			<input type="button" class="btn btnWide" value="返回视频列表"
				onclick="javascript: parent.navTab.openTab('menu_BGZYSPJMGL', '<%=request.getContextPath()%>/oa/oaSubvideoInformation!list.do?no=${object.no }',{title:'视频节目', external:true});" />
	</s:form>
</body>
<!-- <script type="text/javascript">
   function openTabinnerSPLB(){
	   navTab.openTab("main", url,{title:"视频节目", fresh:false, external:false});
	   window.top.parentAClick("menu_BGZYSPJMGL",true);
   }
</script> -->

</html>


