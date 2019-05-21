<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title><s:text name="oaLeaveMessage.list.title" /></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/default/css/tv/liuyan.css" />
</head>
<body>

	<div id="msgBox">

		<div id="msgBox_bottom">
			<s:form action="oaLeaveMessage" namespace="/oa" id="oaLeaveMessageForm">
			<input type="hidden" id="s_djid" name="s_djid" value="${s_djid}" />
		    <input type="hidden" id="s_infoType" name="s_infoType"
			value="${s_infoType}" />
				<div id="pic_div">
					<img
						src="${contextPath }/oa/oaUserinfo!showImage.do?usercode=${user.usercode}" />
				</div>
				<div id="area_div">
					<div id="area_div_outline">
						<textarea name="messageComment" class="text_area" id="conBox" ></textarea>
					</div>
				</div>
				<div class="tr">
					<p>
						<span class="countTxt">还能输入</span><strong class="maxNum">140</strong><span>个字</span>
						<input id="sendBtn" type="button" value="发布"
							title="快捷键 Ctrl+Enter" />
					</p>
				</div>
				<div style="clear: both;"></div>
			</s:form>
		</div>
		<c:if test="${fn:length(objList)>=1}">
		<s:iterator value="objList" status="i" var="oaLeaveMessage">
		<div class="list">
			<div class="list-div">
				<div class="userPic">
					<img
						src="${contextPath }/oa/oaUserinfo!showImage.do?usercode=${oaLeaveMessage.creater}" />
				</div>
				<div class="content">
				<input type="hidden" id="parent_lno" value="${oaLeaveMessage.no }"/>
					<div class="userName">
						<a href="javascript:;" title="${cp:MAPVALUE('usercode',oaLeaveMessage.creater)}">${cp:MAPVALUE("usercode",oaLeaveMessage.creater)}</a>:
					</div>
					<div class="msgInfo">${oaLeaveMessage.messageComment}</div>
					<div class="times">
						<span><fmt:formatDate value='${oaLeaveMessage.creatertime}' pattern='yyyy-MM-dd hh:mm:ss' /></span>
						<a class="del" href="javascript:;">回复</a>
						<a class="email_icon"></a>
					</div>
					<div style="clear:both;"></div>
				</div>

			</div>
		   <c:if test="${not empty  oaLeaveMessage.oaLeaveMessagereplys}">
		   <c:forEach items="${oaLeaveMessage.oaLeaveMessagereplys }" var="replys">
			<div class="list-div extend_list_div">

				<div class="userPic">
					<img
						src="${contextPath }/oa/oaUserinfo!showImage.do?usercode=${replys.creater}" />
				</div>
				<div class="content">
				   <input type="hidden" id="parent_lno" value="${replys.lno }"/>
					<div class="userName">
						<a href="javascript:;" title="${cp:MAPVALUE('usercode',replys.creater)}">${cp:MAPVALUE("usercode",replys.creater)}</a>:
					</div>
					<div class="msgInfo">${replys.messagecomment}</div>
					<div class="times">
						<span><fmt:formatDate value='${replys.creatertime}' pattern='yyyy-MM-dd hh:mm:ss' /></span>
						<a class="del" href="javascript:;">回复</a>
						<a class="email_icon"></a>
					</div>
					<div style="clear:both;"></div>
				</div>
			</div>
			</c:forEach>
			</c:if>
		</div>
		</s:iterator>
			</c:if>
	</div>

</body>
<script type="text/javascript">
/*-------------------------- +
获取id, class, tagName
+-------------------------- */
var get = {
	byId: function(id) {
		return typeof id === "string" ? document.getElementById(id) : id;
	},
	byClass: function(sClass, oParent) {
		var aClass = [];
		var reClass = new RegExp("(^| )" + sClass + "( |$)");
		var aElem = this.byTagName("*", oParent);
		for (var i = 0; i < aElem.length; i++) reClass.test(aElem[i].className) && aClass.push(aElem[i]);
		return aClass;
	},
	byTagName: function(elem, obj) {
		return (obj || document).getElementsByTagName(elem);
	}
};
/*-------------------------- +
事件绑定, 删除
+-------------------------- */
var EventUtil = {
	addHandler: function (oElement, sEvent, fnHandler) {
		oElement.addEventListener ? oElement.addEventListener(sEvent, fnHandler, false) : (oElement["_" + sEvent + fnHandler] = fnHandler, oElement[sEvent + fnHandler] = function () {oElement["_" + sEvent + fnHandler]();}, oElement.attachEvent("on" + sEvent, oElement[sEvent + fnHandler]));
	},
	removeHandler: function (oElement, sEvent, fnHandler) {
		oElement.removeEventListener ? oElement.removeEventListener(sEvent, fnHandler, false) : oElement.detachEvent("on" + sEvent, oElement[sEvent + fnHandler]);
	},
	addLoadHandler: function (fnHandler) {
		this.addHandler(window, "load", fnHandler);
	}
};
/*-------------------------- +
设置css样式
读取css样式
+-------------------------- */
function css(obj, attr, value)
{
	switch (arguments.length)
	{
		case 2:
			if(typeof arguments[1] == "object")
			{	
				for (var i in attr) i == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + attr[i] + ")", obj.style[i] = attr[i] / 100) : obj.style[i] = attr[i];
			}
			else
			{	
				return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj, null)[attr];
			}
			break;
		case 3:
			attr == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + value + ")", obj.style[attr] = value / 100) : obj.style[attr] = value;
			break;
	}
};






EventUtil.addLoadHandler(function ()
{
	var oMsgBox = get.byId("msgBox");
	var oConBox = get.byId("conBox");
	var oSendBtn = get.byId("sendBtn");
	var oMaxNum = get.byClass("maxNum")[0];
	var oCountTxt = get.byClass("countTxt")[0];
	var oList = get.byClass("list")[0];
	var oUl = get.byTagName("ul", oList)[0];
	var aLi = get.byTagName("li", oList);
	var bSend = false;
	var rSend = false;
	var timer = null;
	var i = 0;
	var maxNum = 140;
	
	

	//禁止表单提交
	EventUtil.addHandler(get.byTagName("form", oMsgBox)[0], "submit", function () {return false;});
	
	//为广播按钮绑定发送事件
	EventUtil.addHandler(oSendBtn, "click", fnSend);

	
	
	//为Ctrl+Enter快捷键绑定发送事件
	EventUtil.addHandler(document, "keyup", function(event){
		var event = event || window.event;
		event.ctrlKey && event.keyCode == 13 && fnSend();
	});
	
	
	//发送留言
	function fnSend	 (){
		var reg = /^\s*$/g;
		 if(reg.test(oConBox.value)){
			alert("\u968f\u4fbf\u8bf4\u70b9\u4ec0\u4e48\u5427\uff01");
			oConBox.focus();
		}else if(!bSend){
			alert("\u4f60\u8f93\u5165\u7684\u5185\u5bb9\u5df2\u8d85\u51fa\u9650\u5236\uff0c\u8bf7\u68c0\u67e5\uff01");
			oConBox.focus();
		}else{
			var srForm  = document.getElementById("oaLeaveMessageForm");
			srForm.action = 'oaLeaveMessage!save.do';
			srForm.submit();
		}
	}
	//发送回复
	function replySend(){
		var reg = /^\s*$/g;
		var ev = $(this).parent().prev().prev().find("div #rec-area-text-info")[0];
		if(reg.test(ev.value)){
			alert("\u968f\u4fbf\u8bf4\u70b9\u4ec0\u4e48\u5427\uff01");
			$(ev).focus();
		}else if(!rSend){
			alert("\u4f60\u8f93\u5165\u7684\u5185\u5bb9\u5df2\u8d85\u51fa\u9650\u5236\uff0c\u8bf7\u68c0\u67e5\uff01");
			$(ev).focus();
		}else{
			var srForm  = document.getElementById("oaLeaveMessagereplyForm");
			srForm.action = 'oaLeaveMessagereply!save.do';
			srForm.submit();
		}
	}
	
	//事件绑定, 判断字符输入
	EventUtil.addHandler(oConBox, "keyup", confine);	
	EventUtil.addHandler(oConBox, "focus", confine);
	EventUtil.addHandler(oConBox, "change", confine);
	
	//发布字符限制
	function confine (){
		var iLen = 0;		
		for (i = 0; i < oConBox.value.length; i++) iLen += /[^\x00-\xff]/g.test(oConBox.value.charAt(i)) ? 1 : 0.5;
		oMaxNum.innerHTML = Math.abs(maxNum - Math.floor(iLen));	
		maxNum - Math.floor(iLen) >= 0 ? (css(oMaxNum, "color", ""), oCountTxt.innerHTML = "\u8fd8\u80fd\u8f93\u5165", bSend = true) : (css(oMaxNum, "color", "#f60"), oCountTxt.innerHTML = "\u5df2\u8d85\u51fa", bSend = false);
	}
	
	//回复字符控制
	function replyControl(){

		var iLen = 0;
		var oReplyMaxNum = $(this).parent().parent().next().find(".maxReply")[0];
		
		var oReplyTxt =$(this).parent().parent().next().find(".replyTxt")[0];
		
		for (i = 0; i < $(this).val().length; i++)
		iLen += /[^\x00-\xff]/g.test($(this).val().charAt(i)) ? 1 : 0.5;
		oReplyMaxNum.innerHTML = Math.abs(maxNum - Math.floor(iLen));	
		maxNum - Math.floor(iLen) >= 0 ? (css(oReplyMaxNum, "color", ""), oReplyTxt.innerHTML = "", rSend = true) : (css(oReplyMaxNum, "color", "#f60"), oReplyTxt.innerHTML = "-", rSend = false);
	}
	
	
	//加载即调用
	confine();		
	
	//广播按钮鼠标划过样式
	EventUtil.addHandler(oSendBtn, "mouseover", function () {this.className = "hover";});

	//广播按钮鼠标离开样式
	EventUtil.addHandler(oSendBtn, "mouseout", function () {this.className = "";});
	
	//回复功能
	function delLi(){
		var aA = get.byClass("del", oUl);
		
		for (i = 0; i < aA.length; i++){
			aA[i].onclick = function (e){
				var oLiDiv = this.parentNode.parentNode;
				var atUser = $(oLiDiv).find(".userName a").text();
				if(atUser=='${user.username}'){
					var e = e || window.event;
					var element =e.srcElement ||e.target;
					$(element).css("cursor","not-allowed");
					return false;
				}
				if(oLiDiv.lastChild.id=='rec-div'){
					oLiDiv.removeChild(oLiDiv.lastChild);
				}else{
					
				var oReplyDiv = document.createElement("div");
				oReplyDiv.setAttribute("id","rec-div");
				oReplyDiv.innerHTML = "<form action=\"oaLeaveMessagereply\" namespace=\"/oa\" id=\"oaLeaveMessagereplyForm\">\
				        <input type=\"hidden\" id=\"oaLeaveMessagelno\" value=\"\" name=\"lno\"/>\
				        <input type=\"hidden\" id=\"s_djid\" name=\"s_djid\" value=\"${s_djid}\" />\
					    <input type=\"hidden\" id=\"s_infoType\" name=\"s_infoType\" value=\"${s_infoType}\" />\
				        <div id=\"rec-text\">\
					 	<div id=\"rec-img\"><img src=\"${contextPath }/oa/oaUserinfo!showImage.do?usercode=${user.usercode}\"/></div>\
						<div id=\"rec-area\">\
						    <div id=\"rec-area-text\"><textarea id=\"rec-area-text-info\" name=\"messagecomment\"></textarea></div>\
						</div>\
						<div id=\"rec-maxNum\"><span class=\"replyTxt\"></span><span class=\"maxReply\">140</span></div>\
						<div id=\"rec-btn\"><a id=\"rec-btn-reply\" >\u56de\u590d</a></div>\
					 </div></form>";
			    //插入回复框
				oLiDiv.appendChild(oReplyDiv);

				//为回复按钮绑定事件
				var subbtn =$(oLiDiv.lastChild).find("div #rec-btn-reply");
				var replyBox = $(oLiDiv.lastChild).find("div #rec-area-text-info");
				subbtn=subbtn[0];
				replyBox=replyBox[0];
				EventUtil.addHandler(subbtn, "click", replySend);
				EventUtil.addHandler(replyBox, "keyup", replyControl);	
				EventUtil.addHandler(replyBox, "focus", replyControl);
				EventUtil.addHandler(replyBox, "change", replyControl);
				
				$(oLiDiv).find("#oaLeaveMessagelno").val($(oLiDiv).find("#parent_lno").val());
				
				if($(oLiDiv.parentNode).hasClass("extend_list_div")){
					$(oLiDiv).removeClass("content");
					$(oLiDiv).find(".times").css("padding-left","60px");
				}
				
				$(oLiDiv).find("#rec-area-text-info").val("@"+atUser+":");
				$(oLiDiv).find("#rec-area-text-info").css("font-size","13px").css("font-family","Verdana, Arial, Sans-serif;");
				$(oLiDiv).find("#rec-btn-reply").css("font-style","normal").css("font-family","'Times New Roman',Georgia,Serif; ");
				//将元素高度保存
				var iHeight = oReplyDiv.clientHeight - parseFloat(css(oReplyDiv, "paddingTop")) - parseFloat(css(oReplyDiv, "paddingBottom"));
				var alpah = count = 0;
				css(oReplyDiv, {"opacity" : "0", "height" : "0"});	
				timer = setInterval(function (){
					css(oReplyDiv, {"display" : "block", "opacity" : "0", "height" : (count += 8) + "px"});
					if (count > iHeight){
						clearInterval(timer);
						
						timer = setInterval(function (){
							css(oReplyDiv, "opacity", (alpah += 10));
							alpah > 100 && (clearInterval(timer), css(oReplyDiv, "opacity", 100));
						},30);
					}
				},30);
				$(document).ready(
						function() {
							init = setInterval("FrameUtils.initialize(window, init)",
									MyConstant.initTimeForAdjustHeight);
						});
				/* window.setInterval(function(){
					if(parent.document.getElementById("tabFrames1")){
					   parent.document.getElementById("tabFrames1").height = $("#msgBox").height();
					}
					}, 200); */
			  }
			};			
		}
	}
	delLi();
});



$(document).ready(
		function() {
			init = setInterval("FrameUtils.initialize(window, init)",
					MyConstant.initTimeForAdjustHeight);
		});
/* window.setInterval(function(){
	 if(parent.document.getElementById("tabFrames1")){ 
   parent.document.getElementById("tabFrames1").height = $("#msgBox").height();
	 }
}, 200);	 */
</script>
</html>