<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/themes/oaHelpInfo/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/icon-css.css" rel="stylesheet" type="text/css" />
<title><s:text name="oaInformation.view.title" /></title>
<style type="text/css">
   .infoType {
       background:#00f;
   }
font.imp {
	font-size: 25px
}
</style>
</head>
<body  style="width:100%;">
		<div style="width:100%;text-algin:center;">
		
			<div style="text-align: left;font-size: 14px;margin-bottom:5px;margin-left:17%;">
			<font id="forfingle">首页</font>>
			<font id="columnType">${cp:MAPVALUE('columntype',object.columnType) }</font>>
			<font id="content">${object.infoName}</font></div>
			<div style="text-align:left; padding-top:0;margin-top:0;">
			<table style="width:960px;margin:auto;">
				<a id="scrlBotm" style="right: 50px; position: fixed; top: 110px; z-index: 16; cursor: pointer; overflow: hidden; text-indent: -9999px; width: 50px; height: 50px; background: url(${contextPath}/images/gototop-bg-v2.png);"></a>
			<tr>
			  <td style="width:15%;padding:10px 0px;vertical-align:text-top;">作者:${cp:MAPVALUE('usercode',object.creater)}<br/><br/>
			  <s:date name="creatertime" format="yyyy-MM-dd  HH:mm:ss" /></td>
			  <td style="padding-left:10px;background:#fff;">
			

			  	<c:if test="${object.isgood eq '1' }">
	  			 <i class="icon icon-color icon-star-on"  title="精华帖"></i>
	  			</c:if>
	  			<c:if test="${object.isgood eq '0' }">
	  			 <i class="icon icon-color icon-star-off" title="普通帖"></i>
	  			</c:if>&nbsp;&nbsp;
	  			<font class="imp" >${object.infoName}</font>
	  		
               <br/>
	  		   <span style="padding-right:20%;">
	  		   <font color="blue"><strong>时间</strong></font>:&nbsp;&nbsp;<fmt:formatDate value="${object.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp; 
			   <font color="blue"><strong>查看数</strong></font>:&nbsp;&nbsp;${object.viewnum}&nbsp;&nbsp;&nbsp;&nbsp; 
			   <font color="blue"><strong>回复数</strong></font>:&nbsp;&nbsp;${object.replynum}</span><br/>
			   <p>${remark}</p><br/>
			 	<c:if test="${not empty object.fileDocname}">
			 	<span style="padding-right:20%;">
					<font color="blue" ><strong>附件下载</strong></font>:&nbsp;&nbsp;
					<a href="#" onclick="downFile('${object.djid}')" style="text-decoration: underline"> ${object.fileDocname} </a></span></c:if>
			  </td>
			</tr>
			</table>
			</div>
		</div>
	<c:if test="${'Y' eq object.isallowcomment}">			
		<div style="width: 100%;" id='div_replay'>
			<iframe id="tabFrames1" name="tabFrames1"
				src="${pageContext.request.contextPath}/oa/oaLeaveMessage!replayList.do?s_djid=${object.djid}&s_infoType=${object.columnType}&backcolumnType=${backcolumnType}"
				 onload="reinitIframe(this)"
				 width="100%" frameborder="0" 
				scrolling="no" border="0" marginwidth="0"></iframe>
		</div>
	</c:if>
</body>

<script type="text/javascript">
$(function(){
	//参考
// 	$('#scrlBotm').click(function () {
//         $('html, body').animate({
//             scrollTop: 90099
//         },
//         1500);
//         return false;
//     });
// 	$('#scrlTop').click(function(){
// 		  $('html, body').animate({
//                 scrollTop:parseInt(0)
//             },
//             1500);
//             return false;
// 	});
	
	 $('#scrlBotm').click(function () {
		 window.top.scroll();
	    });
	$('#forfingle').click(function(){
		var url="oaHelpinfo!list.do";
		parent.document.location.href=url;
	});
	$('#columnType').click(function(){
		var url="oaHelpinfo!list.do?s_columnType="+'${object.columnType}';
		parent.document.location.href=url;
	});
	$('#content').click(function(){
		var djid='${object.djid}';
		var columnType='${backcolumnType}';
		var url="oa/oaHelpinfo!view.do?djid="+djid+"&backcolumnType="+columnType;
		document.location.href=url;
	});
});

function reinitIframe(iframe){
	try{

	var bHeight = iframe.contentWindow.document.body.scrollHeight;

	var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

	var height = Math.max(bHeight, dHeight);

	iframe.height =  height;
	parent.document.getElementById("ifr").height = $("body").height();
	}catch (ex){}

	}
	function downFile(id) {
		var url = "oaHelpinfo!downStuffInfo.do?djid=" + id;
		document.location.href = url;
	}
</script>
</html>