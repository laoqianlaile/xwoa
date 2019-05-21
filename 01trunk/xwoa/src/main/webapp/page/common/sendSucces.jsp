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
	width: 900px;
}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />


<title></title>
<script language="Javascript">
function countDown(secs){
var jumpTo = document.getElementById('jumpTo');  
jumpTo.innerHTML=secs;   
if(--secs>0){    
	setTimeout("countDown("+secs+")",1000);   
	}else {     
		DialogUtil.close();
		}  
		} 
</script>
</head>
<body>
	<s:form>
		<span class="successOK"
			style="width: 400px; height: 280px; padding-left: 150px; padding-right: 150px; text-align: center;"></span>&nbsp;</br>
		<span
			style="text-align: center; padding-left: 250px; padding-right: 150px; font-size: 15px; font-family: Microsoft YaHei !important;">页面将在<span
			id="jumpTo">5</span>秒后自动<a href="#" onclick="doClose();"
			style="color: red; font-weight: bold;">关闭</a></span>
		</br>
		<script type="text/javascript"> countDown(5); </script>


	</s:form>
</body>
<script type="text/javascript">
function doClose(){
	DialogUtil.close();
}
</script>

</html>


