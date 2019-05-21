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
		
			<div style="text-align: left;font-size: 14px;margin-bottom:5px;margin-left:;">
			<font id="forfingle">首页</font>>
			<font id="columnType">${cp:MAPVALUE('columntype',object.columnType) }</font>>
			<font id="content">${object.infoName}</font></div>
			<div style="text-align:left; padding-top:0;margin-top:0;">
			<table style="width:100%;margin:auto;">
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
	  		   <font><strong>时间</strong></font>:&nbsp;&nbsp;<span style="color: red"><fmt:formatDate value="${object.creatertime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>&nbsp;&nbsp;&nbsp;&nbsp; 
			   <font><strong>查看数</strong></font>:&nbsp;&nbsp;<span style="color: red">${object.viewnum}</span>&nbsp;&nbsp;&nbsp;&nbsp; 
			   <font><strong>回复数</strong></font>:&nbsp;&nbsp;<span style="color: red">${object.replynum}</span></span><br/>
			   <p>${remark}</p><br/>
			 	<c:if test="${not empty object.fileDocname}">
			 	<span style="padding-right:20%;">
					<font><strong>附件下载</strong></font>:&nbsp;&nbsp;
					<a href="#" onclick="downFile('${object.djid}')" style="text-decoration: underline"> ${object.fileDocname} </a></span></c:if>
					<input type="hidden" id="djid" name="djid" value="${object.djid}">
			  </td>
			</tr>
			</table>
			</div>
			<br>
			<br>
			<img alt="支持" title="支持" class="bbs" data-laytype="Z" style="width: 25px; height: 25px" src='${ctx}/themes/blue/images/bbszc.png' />
				支持 (<font type="text" id="laytypeZ" readonly="readonly">0</font>) 
			<img alt="反对" title="反对" class="bbs" data-laytype="F" style="width: 25px; height: 25px" src='${ctx}/themes/blue/images/bbsfd.png' /> 
				反对(<font type="text" id="laytypeF" readonly="readonly">0</font>)
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
	
	$(".bbs").click(function() {
				var laytype = $(this).data("laytype");
				var themeno = $("#djid").val();
				var layNum = parseInt($(this).next("font").html()) + 1;

				//收藏，关注
				$.ajax({
							type : "POST",
							url : "${contextPath}/bbs/oaBbsAttention!addSign.do?laytype="
									+ laytype + "&themeno=" + themeno,
							dataType : "json",
							success : function(json) {

								if ("true" == json.status) {
									$("#laytype" + laytype)
											.html(layNum);

									if ("S" == laytype) {
										parent.alert("收藏成功;");
									} else if ("G" == laytype) {
										parent.alert("关注成功;");
									} else {
										parent.alert("操作成功;");
									}

								} else if ("false" == json.status) {
									if ("S" == laytype) {
										parent.alert("已收藏;");
									} else if ("G" == laytype) {
										parent.alert("已关注;");
									} else {
										parent.alert("不可重复操作！");
									}

								}
							},
							error : function() {
								parent.alert("保存失败，刷新后重试！");
							}
						});
			});
			//初始化收藏，支持数据
			$.each($('.bbs'),
					function() {
						var laytype = $(this).data("laytype");
						var themeno = $("#djid").val();
						//收藏，关注
						$.ajax({
									type : "POST",
									url : "${contextPath}/bbs/oaBbsAttention!getAttentionNum.do?laytype="
											+ laytype + "&themeno=" + themeno,
									dataType : "json",
									success : function(json) {
										$("#laytype" + laytype).html(
												json.status);
									},
									error : function() {
										parent.alert("数据获取失败，刷新后重试！");
									}

								})
					});
</script>
</html>