$(document).ready(function() {
	//列表页查询修改
	if($("div.searchArea").length>0){
		$("div.searchArea").find("input").keydown(function(e){
			if(e.keyCode == 13){
				if($("div.searchArea").find("div.clickDiv").length>0){
					$("div.searchArea").find("div.clickDiv").parent().click();	
				}
				 e.preventDefault();
			}
			
		});	
	}
	
	
	if ($("#isReady")[0]) {
		$("#isReady").val("ok");
	} else {
		$("body").prepend("<input type='hidden' id='isReady' name='isReady' value='ok' />");
	}
	
		setTimeout(function() {
			// 如果是页面中tab页显示的入口进入查看流程，则调整父页面以及祖父页面的高度（hll）
			try {
				if (window.frameElement && "tabFrames" == window.frameElement.id) {
					window.parent.document.getElementById("tabFrames").style.height = window.document.body.scrollHeight + 20 + "px";
					window.parent.parent.document.getElementById("AllInfoFrame").style.height = (window.document.body.scrollHeight + 60) + "px";
				}
			}catch(e){
			}
		
		}, 200);
		
		setTimeout(function() {
			try {
			if (window.frameElement && "tabFrame" == window.frameElement.ref) {
				window.parent.document.getElementById( window.frameElement.id).style.height = window.document.body.scrollHeight + "px";
			}
			}catch(e){}
		}, 200);
	
	
	
	//// 页面公用事件绑定
	$("#incomeDeptType").change(function() {
		if ("9" == $(this).val()) {
			$("#provincialGovDocNoArea").show();
		} else {
			$("#provincialGovDocNoArea").hide();
		}
	});
	/*$(".viewTable>tbody>tr").find("td.addTd").each(function(){
		$(this).css({"background":"#dbf2ff"});
	});*/
	$(".searchTable>tbody>tr>td:even").each(function(){
		$(this).css({"text-align":"right"});
	});
//	$("fieldset").find("legend").each(function(){
//		if($(this).is(':has(b)')){
//			var text = $(this).find("b").text();
//			$(this).after("<div style='width:100%;height:24px;'><div class='legend_left'></div><div class='legend'><b>"+text+"</b></div><div class='legend_right'></div></div>");
//			$(this).remove();
//		}
//		else{
//			var text = $(this).text();
//			$(this).after("<div style='width:100%;height:24px;'><div class='legend_left'></div><div class='legend'><b>"+text+"</b></div><div class='legend_right'></div></div>");
//			$(this).remove();
//		}
//	});
//	if($("fieldset._new").size()>0){
//		$("fieldset._new").css({"border-top":"1px solid #cdcdcd !important"});
//		$("fieldset._new .legend_left").removeClass("legend_left").addClass("l-legend");
//		$("fieldset._new .legend").removeClass("legend").addClass("m-legend");
//		$("fieldset._new .legend_right").removeClass("legend_right").addClass("r-legend");
//	}
});

function openLoadIng(flag){
    var _parentBody,_progressBar,_background;
    //子页面
  	if($(parent.window.document.body).find(".progressBar").length > 0){
        _parentBody = $(parent.window.document.body);
    //孙子页面
	}else if($(parent.parent.window.document.body).find(".progressBar").length > 0){
        _parentBody = $(parent.parent.window.document.body);
    //父页面
	}else if($(document.body).find(".progressBar").length > 0){
        _parentBody = $(document.body);
	}else{
		console.info("未知错误");
		return false;
	}
    _progressBar = _parentBody.find(".progressBar");
    _background = _parentBody.find(".background");
    if(flag){
        _progressBar.show();
        _background.show();
    }else{
        _progressBar.hide();
        _background.hide();
    }
}

// 用于拼接选择机构或者人员后自动生成意见。
function genHandleComments(obj) {	
	var yjUserNames="",yjCode = "",zbOrgName_ = "", ldzdxName = "",ldzdxName_= "",zbOrgRoleCode_ = "", 
	xbOrgNames_="", xbOrgRoleCode_="",zbOrgName = "", zbOrgRoleCode = "", xbOrgNames="", xbOrgRoleCode="", 
	ybOrgNames="", ybOrgRoleCode="",bjUserNames="", roleCode = "", engineUserNames = "";
	var xbUserRoleCode = "", xbUserNames = "", zbUserRoleCode = "", zbUserNames = "";
	var $transcontent = obj["transcontent"], comments = [""];
	var $ybtranscontent = obj["ybtranscontent"], ybcomments = [""];
	var nodeCode=obj["nodeCode"].val();
	//alert($ybtranscontent);
	//alert($transcontent);	
	
	if (obj["ldzdx"]&&obj["ldzdx"].is(':visible')) { //领导字典项
		var $ldzdx = obj["ldzdx"];
		if ("" != $ldzdx.val()) {
			ldzdxName = $ldzdx.find("option:selected").text();
		}
	}
	if (obj["ldzdx_"]&&obj["ldzdx_"].is(':visible')) { //领导字典项
		var $ldzdx_ = obj["ldzdx_"];
		if ("" != $ldzdx_.val()) {
			ldzdxName_ = $ldzdx_.find("option:selected").text();
		}
	}
	if (obj["zbOrgCode_"]&&obj["zbOrgCode_"].is(':visible')) { // 主办机构
		var $zbOrgCode_ = obj["zbOrgCode_"];
		if ("" != $zbOrgCode_.val()) {
			zbOrgName_ = $zbOrgCode_.find("option:selected").text();
		}
	}
	if (obj["xbOrgNames_"]&&obj["xbOrgNames_"].is(':visible')) { // 分办机构
		xbOrgNames_ = obj["xbOrgNames_"].val();
		xbOrgRoleCode_ = obj["xbOrgRoleCode_"].val();
	}
	
	if (obj["ybOrgNames"]&&obj["ybOrgNames"].is(':visible')) { // 分办机构
		ybOrgNames = obj["ybOrgNames"].val();
		ybOrgRoleCode = obj["ybOrgRoleCode"].val();
	}
	if (obj["bjUserNames"]&&obj["bjUserNames"].is(':visible')) { // 指定人员
		bjUserNames = obj["bjUserNames"].val();
		roleCode = obj["roleCode"].val();
	}
	if (obj["yjUserNames"]&&obj["yjUserNames"].is(':visible')) { // 指定人员
		yjUserNames = obj["yjUserNames"].val();
		yjCode = obj["yjCode"].val();
	}
	if (obj["zbOrgCode"]&&obj["zbOrgCode"].is(':visible')) { // 主办机构
		var $zbOrgCode = obj["zbOrgCode"];
		if ("" != $zbOrgCode.val()) {
			zbOrgName = $zbOrgCode.find("option:selected").text();
		}
		zbOrgRoleCode = obj["zbOrgRoleCode"].val();
	}
	
	if (obj["zbOrgCode"]&&obj["zbOrgCode"].is(':visible')) { // 主办机构
		var $zbOrgCode = obj["zbOrgCode"];
		if ("" != $zbOrgCode.val()) {
			zbOrgName = $zbOrgCode.find("option:selected").text();
		}
		zbOrgRoleCode = obj["zbOrgRoleCode"].val();
	}
	if (obj["xbOrgNames"]&&obj["xbOrgNames"].is(':visible')) { // 分办机构
		xbOrgNames = obj["xbOrgNames"].val();
		xbOrgRoleCode = obj["xbOrgRoleCode"].val();
	}
	if (obj["engineUserNames"]&&obj["engineUserNames"].is(':visible')) { // 指定人员
		engineUserNames = obj["engineUserNames"].val();
		engineRoleCode = obj["engineRoleCode"].val();
	}
	if (obj["xbUserNames"]&&obj["xbUserNames"].is(':visible')) { // 指定人员
		xbUserNames = obj["xbUserNames"].val();
		xbUserRoleCode = obj["xbUserRoleCode"].val();
	}
	if (obj["zbUserNames"]&&obj["zbUserNames"].is(':visible')) { // 指定人员
		zbUserNames = obj["zbUserNames"].val();
		zbUserRoleCode = obj["zbUserRoleCode"].val();
	}
	
	//阅示：选择领导和处理部门的
if(yjUserNames || ybOrgNames || ldzdxName){
	ybcomments.push("请");	
	var istld = false;
	if (yjUserNames) {	
		ybcomments.push(yjUserNames.split(",").join("、"));
		ybcomments.push("同志");
		istld=true;
	}
	if (ybOrgNames) {
		ybcomments.push((istld ? "及" : "") + ybOrgNames.split(",").join("、"));
		//ybcomments.push("阅");
	}	
	if (ldzdxName) {
		ybcomments.push(ldzdxName);
		//ybcomments.push("阅示");
	}
	ybcomments.push("。");
	$ybtranscontent.val(ybcomments.join(""));
	
	if(yjUserNames=='' && ybOrgNames==''){//如果都为空的话
		$ybtranscontent.val('');
	}
	}

	//办理：选择主办和协办部门
if (zbOrgName_ || xbOrgNames_ || ldzdxName_) {
	comments.push("请");
	var isZbcsfb = false;
	if (zbOrgName_) { //  && ("zbcsfb" == zbOrgRoleCode || "wbzbzb" == zbOrgRoleCode)
		comments.push(zbOrgName_);
		isZbcsfb = true;
	}
	if (xbOrgNames_) { //  && ("hbcsfb" == xbOrgRoleCode || "wbzbfb" == xbOrgRoleCode)
		comments.push((isZbcsfb ? "会同" : "") + xbOrgNames_.split(",").join("、"));
	}		
	/*if (zbOrgName_ || xbOrgNames_) {
		comments.push("阅处");
	}*/
	if (ldzdxName_) {
		comments.push(ldzdxName_);
		//comments.push("阅处");
	}
	comments.push("。");
	$transcontent.val(comments.join(""));
	
	if(zbOrgName_==''&&xbOrgNames_==''){
		$transcontent.val("");			
	}
	
}



	if (bjUserNames || zbOrgName || xbOrgNames || engineUserNames || xbUserNames || zbUserNames) {
		if ("xwsw_bfz" == nodeCode) {
			comments.push("");
		}else{
			comments.push("请");
		}
		
		if (bjUserNames || engineUserNames || xbUserNames || zbUserNames) {
			//var isEngineUserNamesUsed = $.trim(engineUserNames) ? false : true;
			if ($.trim(bjUserNames)) {
				comments.push(bjUserNames.split(",").join("、"));
			/*	if (!isEngineUserNamesUsed && (("ldys" == roleCode) 
						|| ("wldqf" == roleCode && ("wmsfbyj" == nodeCode || "bgsfgzrsh" == nodeCode || "bgszrsh" == nodeCode 
								|| "bgstcnfyj" == nodeCode || "bgsfgsh" == nodeCode)))) {
					comments.push("、");
					comments.push(engineUserNames.split(",").join("、"));
					isEngineUserNamesUsed = true;
				}*/
				comments.push("同志");					
			}
			if ($.trim(engineUserNames)) {
				comments.push(engineUserNames.split(",").join("、"));
				comments.push("同志");		
				
				if ("sw_py" == nodeCode || "sw_fgldpy" == nodeCode) {//收文分管领导批阅节点
					comments=[""];
					comments.push("呈");
					comments.push(engineUserNames.split(",").join("、") + "领导阅示");
				}
				$transcontent.val(comments.join(""));
			}
			//alert(bjUserNames);		
			//alert(nodeCode);
			if (bjUserNames) {				
				if ("sw_py" == nodeCode || "sw_fgldpy" == nodeCode) {//收文厅领导批分节点
					comments=[""];
					comments.push("呈");
					comments.push(bjUserNames.split(",").join("、") + "领导阅示");
				}			
				else{
					//默认
					comments.push("阅示");
				}
				$transcontent.val(comments.join(""));				
			}
			
			if (zbUserNames) {
				if (bjUserNames || engineUserNames) {
					comments.push("，");
				}
				comments.push(zbUserNames.split(",").join("、") + "同志阅处");
			}
			
			if (xbUserNames) {
				if (bjUserNames || engineUserNames || zbUserNames) {
					comments.push("，");
				}
				comments.push(xbUserNames.split(",").join("、") + "同志阅处");
			}
			
			if (zbOrgName || xbOrgNames) {
				comments.push("，");
			}
		}
		
		
		var isZbcsfb = false;
		if ("xwsw_bfz" == nodeCode) {
			comments.push("");
		}else{
		if (zbOrgName) { //  && ("zbcsfb" == zbOrgRoleCode || "wbzbzb" == zbOrgRoleCode)
			comments.push(zbOrgName);
			isZbcsfb = true;
		}
		if (xbOrgNames) { //  && ("hbcsfb" == xbOrgRoleCode || "wbzbfb" == xbOrgRoleCode)
			comments.push((isZbcsfb ? "会同" : "") + xbOrgNames.split(",").join("、"));
		}		
		}
		if (zbOrgName || xbOrgNames) {
			if ("xwsw_bfz" == nodeCode) {
				comments.push("");
			}else{
				comments.push("阅处");
				comments.push("。");
			}
		}
		
		$transcontent.val(comments.join(""));
		
		if(zbOrgName==''&&xbOrgNames==''){
			//$transcontent.val("");			
		}
	}
	
	
	
}


/* Object Add Method begin */
Array.prototype.contains = function(ele) {
	for (var i=0; i<this.length; i++) {
		if (ele == this[i]) {
			return true;
		}
	}
	return false;
};
Array.prototype.remove = function(ele, num) {
	num = (num && num >= 0) ? Math.floor(num) : 1;
	var indexArray = new Array();
	var count = 0;
	for (var i=0; i<this.length; i++) {
		if (ele == this[i]) {
			indexArray.push(i);
			count++;
			if (num > 0 && count >= num) {
				break;
			}
		}
	}
	
	for (var i=indexArray.length-1; i>=0; i--) {
		this.splice(indexArray[i], 1);
	}
};
Array.prototype.empty = function() {
	this.length = 0;
};
/* Object Add Method end */
/* MyConstant begin */
function MyConstant() {}
MyConstant.initTimeForAdjustHeight = 100;
/* MyConstant end */

/* StringBuffer begin */
function StringBuffer() {
	this._strings_ = new Array();
}
StringBuffer.prototype.append = function(str) {
	this._strings_.push(str);
};
StringBuffer.prototype.toString = function() {
	return this._strings_.join("");
};
/* StringBuffer end */

/* Map begin */
function Map() {
	this._map_ = new Object();
}
Map.prototype.put = function(key, value) {
	this._map_["k_" + key] = value;
};
Map.prototype.get = function(key) {
	return this._map_["k_" + key];
};
Map.prototype.remove = function(key) {
	delete this._map_["k_" + key];
};
Map.prototype.keyset = function() {
	var array = new Array();
	for (var key in this._map_) {
		if (key.indexOf("k_") == 0) {
			array[i++] = key.substr(2);
		}
	}
	return array;
};
/* Map end */


/* CommonUtils begin */
function CommonUtils() {}
CommonUtils._webSite_;
CommonUtils.setWebSite = function(webSite) {
	if (typeof CommonUtils._webSite_ == "undefined") {
		CommonUtils._webSite_ = webSite;
	}
};
CommonUtils.linkWebSite = function(url) {
	return (CommonUtils._webSite_ + url);
};
CommonUtils.showTextAreaLimited = function(textAreaId, messageId, maxLen) {
	var textAreaNode = document.getElementById(textAreaId);
	var messageNode = document.getElementById(messageId);
	if (!textAreaNode || !messageNode) {
		alert("Can not found textarea or message!");
	} else {
		try {
			var len = textAreaNode.value.length;
			var strlen = textAreaNode.value;
			if (0 == len) {
				messageNode.innerHTML = "&nbsp;";
			} else {
				if (len <= maxLen) {
					messageNode.innerHTML = "<font color=#0000ff>当前还可以输入 " + (maxLen - len) + " 字";
				} else {
					textAreaNode.value = strlen.substr(0,maxLen);
					messageNode.innerHTML = "<font color=#ff0000>最多只能输入"+maxLen+"个字"; 
					/*messageNode.innerHTML = "<font color=#ff0000>当前已经超出 " + (len - maxLen) + " 字";*/
				}
			}
		} catch (e) {
			messageNode.innerHTML = "<font color=#ff0000>Input check error!</font>";
		}
	}
};

/**
 * 
 * @param textAreaId 验证字段名
 * @param messageNodeId 验证结果显示位置 为null时默认紧跟字段显示 
 * @param maxLen 验证字段长度
 */
CommonUtils.showTextAreaLimitedChSimple = function(textAreaId, maxLen) {
	CommonUtils.showTextAreaLimitedCh(textAreaId,null, maxLen);
}

/**
 * 
 * @param textAreaId 验证字段名
 * @param messageNodeId 验证结果显示位置 为null时默认紧跟字段显示 
 * @param maxLen 验证字段长度
 */
CommonUtils.showTextAreaLimitedCh = function(textAreaId,messageNodeId, maxLen) {
	if(!messageNodeId ){
		messageNodeId=textAreaId+"Message";
	}
	var textAreaNode = document.getElementById(textAreaId);
	var messageNode = document.getElementById(messageNodeId);
	if( undefined==messageNode){
		$("#"+textAreaId).after("<span id="+messageNodeId+">&nbsp;</span>");
		messageNode = document.getElementById(messageNodeId);
	}
	
	if (!textAreaNode || !messageNode) {
		alert("验证失败!");
	} else {
		try {
			var totalLen = CommonUtils.checkLength(textAreaNode.value);
			if (0 == totalLen) {
				messageNode.innerHTML = "&nbsp;";
			} else {
				if (totalLen <= maxLen) {
					messageNode.innerHTML = "<font color=#0000ff>当前还可以输入 " + (maxLen - totalLen) + " 字符(1个汉字=2个字符)";
				} else {
					messageNode.innerHTML = "<font color=#ff0000>当前已经超出 " + (totalLen - maxLen) + " 字符(1个汉字=2个字符)";
				}
			}
		} catch (e) {
			messageNode.innerHTML = "<font color=#ff0000>输入错误!</font>";
		}
	
		//高度自适应
		
		if (window.parent.document && window.frameElement && window.frameElement.id) {
			window.parent.document.getElementById(window.frameElement.id).style.height = window.document.body.scrollHeight + "px";
		}
	}
};

CommonUtils.checkLength = function (objVal) {

	var dLen = 0, sLen = 0;
	if(objVal!='undefined'){
	for (var i = 0; i < objVal.length; i++) {
		if (objVal.charCodeAt(i) > "0" && objVal.charCodeAt(i) < "128") {
			sLen += 1;
		} else {
			dLen += 2;
		}
	}
	}
	totalLen = sLen + dLen;
	return totalLen;
}
/* CommonUtils end */

/*add by dh EventUtil start */
function EventUtil(){}

EventUtil.getEvent=function(event){//返回event对象的引用
	return event ? event :window.event;
};
EventUtil.preventDefault=function(event){//取消事件的默认行为
	if(event.preventDefault){
		event.preventDefault();
	}else{
		event.returnValue=false;
	}
};
EventUtil.stopPropagation=function(event){//阻止事件冒泡
	if(event.stopPropagation){
		event.stopPropagation();
	}else{
		event.cancelBubble=true;
	}
};
EventUtil.getTarget=function(event){//获取事件的目标
	return event.target || event.srcElement;
};
/* EventUtil end */

/* FrameUtils begin */
function FrameUtils() {}
FrameUtils.adjustParentHeight = function(_window,diff) {
	if ("ok" == $("#isReady", _window.parent.document).val() && _window.frameElement && _window.frameElement.id) {
		_window.parent.document.getElementById(_window.frameElement.id).style.height = (_window.document.body.scrollHeight+diff) + "px";
	}
};
FrameUtils.setFrame = function(id, preId, src) {
	if ($("#" + preId).attr("id")) {
		$("<iframe id='" + id + "Frame' name='" + id + "Frame' width='100%' height='100%' " 
				+ "frameborder='1' scrolling='no' marginwidth='0' marginheight='0'></iframe>").insertAfter("#" + preId);
		$("#" + id).attr("src", src);
	}
};
FrameUtils.initialize = function(_window, _init, _func) {
	if (_init) {
		try {
			if ("ok" == $("#isReady").val()) {
				clearInterval(_init);
				try {
					if (_func && _func());
					while (_window.parent.document) {
						/*
						 * TODO:因为此处设置的全局的iframe高度，所以有些功受到影响，如果不想被全局设置高度，在此排除iframe的id
						 *没有办法暂时写死了这
						 */
						if (_window.frameElement.id != 'external_FWGLFWDB' && _window.frameElement.id !='external_FWGLFWCK'
							&& _window.frameElement.id != 'external_GWSC_SW' &&  _window.frameElement.id != 'external_GWSC_FW'
							&&  _window.frameElement.id != 'external_FWGD'&&  _window.frameElement.id != 'external_GRBGBJCK'&&  _window.frameElement.id != 'external_GRBGDBSX'){
							_window.parent.document.getElementById(_window.frameElement.id).style.height = _window.document.body.scrollHeight + "px";
						}
						_window = _window.parent;
					}
				} catch (e) {}
			}
		} catch (e) {
			clearInterval(_init);
		}
	}
};
FrameUtils.initialize_stuff = function(_window, _init, _func) {
	if (_init) {
		try {
				clearInterval(_init);
				try {
					if (_func && _func());
					_window.parent.document.getElementById(_window.frameElement.id).style.height = _window.document.body.scrollHeight + "px";
				} catch (e) {}
			
		} catch (e) {
			clearInterval(_init);
		}
	}
};

/* FrameUtils end */
/* ValidationUtils begin */
function ValidationUtils() {}
ValidationUtils.checkEmpty = function(id, message) {
	var $this = $("#" + id);
	try {
		if ("" == $.trim($this.val())) {
			alert(message);
			$this.focus();
			return false;
		}
		return true;
	} catch (e) {
		alert("Check Empty Exception! id: " + id + "!");
		return false;
	}
};
ValidationUtils.checkLength = function(id, maxLength, message) {
	var $this = $("#" + id);
	try {
		if ($this.val().length > maxLength) {
			alert(message);
			$this.focus();
			return false;
		}
		return true;
	} catch (e) {
		alert("Check Length Exception! id: " + id + "!");
		return false;
	}
};
ValidationUtils.checkNumber = function() {
	try {
		var $this = $("#" + arguments[0]);
		var len = arguments.length;
		if (isNaN($this.val())) {
			alert(arguments[len-1]);
		} else {
			if (len > 2) {
				var positions = $this.val().replace("+", "").replace("-", "").split(".");
				if (positions.length > 2) {
					alert("Check Number Error! id:" + arguments[0] + "!");
					return false;
				}
				var fLen = (len > 3 ? arguments[2] : 0);
				if (parseInt(positions[0]) >= Math.power(10, arguments[1]-fLen)) {
					alert(arguments[len-1]);
					return false;
				}
				if (positions.length = 2 && positions[1].length > 0) {
					var dCount = positions[1].length;
					for (var i=positions[1].length-1; i>=0; i--) {
						if ("0" != positions[1].charAt(i)) {
							break;
						}
						dCount--;
					}
					if (dCount > fLen) {
						alert(arguments[len-1]);
						return false;
					}
				}
			}
			return true;
		}
	} catch (e) {
		alert("Check Number Exception! id:" + arguments[0] + "!");
		return false;
	}
};
ValidationUtils.signs = ["+","-","*","/",">","<","=","(",")"];
ValidationUtils.allCompareSigns = [">=","<=","==",">","<"];
ValidationUtils.compareSigns = [">","<","="];
ValidationUtils.checkCompare = function(expression, message) {
	try {
		expression = expression.replace(/\s/g, "");
		var exps = [], chs = [];
		for (var i=0; i<expression.length; i++) {
			var ch = expression.charAt(i);
			if (ValidationUtils.signs.contains(ch)) {
				if (chs.length > 0) {
					exps.push(chs.join(""));
					chs = [];
				}
				exps.push(ch);
			} else {
				chs.push(ch);
			}
		}
		exps.push(chs.join(""));
		for (var i=exps.length-2; i>0; i--) {
			if ("=" == exps[i] && !ValidationUtils.compareSigns.contains(exps[i-1]) && !ValidationUtils.compareSigns.contains(exps[i+1])) {
				exps.splice(i,0,"=");
			}
		}
		var maxAccuracy = 1;
		for (var i=0; i<exps.length; i++) {
			if ("#" == exps[i].charAt(0)) {
				if ("" == $(exps[i]).val()) {
					exps[i] = "0";
				} else {
					var accuracy = 1;
					var thisVal = $(exps[i]).val();
					var index = thisVal.indexOf(".");
					if (index >= 0 && index < thisVal.length - 1) {
						var tmpExp = thisVal.substring(index + 1);
						for (var j=tmpExp.length-1; j>=0; j--) {
							if ("0" != tmpExp[j]) {
								accuracy = j + 1;
								break;
							}
						}
					}
					maxAccuracy = (maxAccuracy < accuracy) ? accuracy : maxAccuracy;
					exps[i] = "Math.round(parseFloat($('" + exps[i] + "').val())*Math.pow(10," + accuracy + "))/Math.pow(10," + accuracy + ")";
				}
			}
		}
		var newExp = exps.join("");
		var valArray = new Array();
		var sign = "";
		for (var i=0; i<ValidationUtils.allCompareSigns.length; i++) {
			if (newExp.indexOf(ValidationUtils.allCompareSigns[i]) >= 0) {
				sign = ValidationUtils.allCompareSigns[i];
				valArray = newExp.split(sign);
				break;
			}
		}
		for (var i=0; i<valArray.length; i++) {
			valArray[i] = "Math.round((" + valArray[i] + ")*Math.pow(10," + maxAccuracy + "))/Math.pow(10," + maxAccuracy + ")";
		}
		if (!(eval("(" + valArray.join(sign) + ")"))) {
			if (message) {
				alert(message);
			}
			return false;
		}
		return true;
	} catch (e) {
		alert("Check Compare Error!");
	}
	return false;
};
ValidationUtils.checkZipCode = function(id, message) {
	var $this = $("#" + id);
	try {
		if ("" != $this.val()) {
			var reg= /^[1-9][0-9]{5}$/;
			if (!reg.test($this.val())) {
				alert(message);
				$this.focus();
				return false;
			}
		}
		return true;
	} catch (e) {
		alert("Check Zip Code Exception! id: " + id + "!");
		return false;
	}
};
ValidationUtils.checkEmail = function(id, message) {
	var $this = $("#" + id);
	try {
		if ("" != $this.val()) {
			var reg= /\w@\w*\.\w/;
			if (!reg.test($this.val())) {
				alert(message);
				$this.focus();
				return false;
			}
		}
		return true;
	} catch (e) {
		alert("Check Email Exception! id: " + id + "!");
		return false;
	}
};
ValidationUtils.checkMobilePhone = function(id, message) {
	var $this = $("#" + id);
	try {
		if ("" != $this.val()) {
			var reg=  /^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$/;
			if (!reg.test($this.val())) {
				alert(message);
				$this.focus();
				return false;
			}
		}
		return true;
	} catch (e) {
		alert("Check Mobile Phone Exception! id: " + id + "!");
		return false;
	}
};
/* ValidationUtils end */
/* FormUtils begin */
function FormUtils() {};
FormUtils.cloneFormElements = function (targetId, frameId, formId) {
	// 在ie<8 以下直接使用jQuery的clone方法，会失败，因此在此处避免直接使用了clone方法，而是用拼接的方式
	try {
		$("#" + targetId).empty();
		var findForm = formId ? ("#" + formId + " ") : "";
		var dSelects = $("#" + frameId).contents().find(findForm + " select");
		for (var i=0; i<dSelects.length; i++) {
			var html = "<input type='hidden'" + ($(dSelects[i]).attr("id") ? (" id='" + $(dSelects[i]).attr("id") + "'") : "")
						+ " name='" + $(dSelects[i]).attr("name") + "' value='" + $(dSelects[i]).val() + "' />";
			$(html).appendTo("#" + targetId);
		}
		var dInputs = $("#" + frameId).contents().find(findForm + " input");
		for (var i=0; i<dInputs.length; i++) {
			var html = "<input type='hidden'" + ($(dInputs[i]).attr("id") ? (" id='" + $(dInputs[i]).attr("id") + "'") : "")
						+ " name='" + $(dInputs[i]).attr("name") + "' value='" + $(dInputs[i]).val() + "' />";
			$(html).appendTo("#" + targetId);
		}
		var dTextAreas = $("#" + frameId).contents().find(findForm + " textarea");
		for (var i=0; i<dTextAreas.length; i++) {
			var html = "<textarea" + ($(dTextAreas[i]).attr("id") ? (" id='" + $(dTextAreas[i]).attr("id") + "'") : "")
						+ " name='" + $(dTextAreas[i]).attr("name") + "'>" + $(dTextAreas[i]).val() + "</textarea>";
			$(html).appendTo("#" + targetId);
		}
		return true;
	} catch (e) {
		alert("Clone Error!");
		return false;
	}
};
/* FormUtils end */
function validateEmpty(id, message) {
	if ("" == $.trim($("#" + id).val())) {
		alert(message);
		return false;
	}
	return true;
}
function validateLength(id, maxLength, message) {
	if ($("#" + id).val().length > maxLength) {
		alert(message);
		return false;
	}
	return true;
}
function validateNumber(id, message) {
	if ("" != $("#" + id).val() && isNaN(parseFloat($("#" + id).val()))) {
		alert(message);
		return false;
	}
	return true;
}
function validateNumberRange(id, size, dSize, message) {
	reg = "^-?\\d{1," + (size - dSize) + "}(?:\\.\\d{1," + dSize + "})?$";
	if ("" != $("#" + id).val() && !(new RegExp(reg).test($("#" + id).val()))) {
		alert(message);
		return false;
	}
	return true;
}
function validateRegExp(id, reg, message) {
	var regex = "";
	if ("email" == reg && (regex = "\w@\w*\.\w"));
	if ("zipCode" == reg && (regex = "^[1-9][0-9]{5}$"));
	if ("mobilePhone" == reg && (regex = "^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$"));
	if ("landline" == reg && (regex = "^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$"));
//	if ("telephone" == reg && (regex = "^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$"));
	
	if ("" != $("#" + id).val() && !(new RegExp(regex)).test($("#" + id).val())) {
		alert(message);
		return false;
	}
	return true;
}
function validateCompare(expression, message) {
	return ValidationUtils.checkCompare(expression, message);
}

/***********************************
 * 弹框工具类
 * @author lay
 * @createDate 2015-10-30
 **********************************/
function DialogUtil(){}
/**
 * 提示框,可设置宽高，标题
 * @param title
 * @param msg
 * @param w
 * @param h
 */
DialogUtil.prototype.alert = function(msg,title,w,h,callback){
	var d = top.art.dialog.alert(msg,callback);
	if(title)
		d.title(title);
	if(w||h){
		d.size(w?w:'auto',h?h:'auto');
	}
	return d;
};

/**
 * 询问框
 * @param msg 询问内容
 * @param okCallback 确认回调函数
 * @param cancelCallback 取消回调函数
 */
DialogUtil.prototype.confirm = function(msg,okCallback,cancelCallback){
	top.art.dialog.confirm(msg, function () {
	    if(okCallback){
	    	okCallback();
	    }
	}, function () {
		  if(cancelCallback){
		    	okCallback();
		   }
	});
};
/**
 * 弹出模式窗口，窗口内容为html
 */
DialogUtil.prototype.openHtml = function(title,content,w,h,okcall,cancelcall,closecall){
	var setting = {title: title,lock: true,padding:'1px'};
	if(w){
		setting.width = w;
	}
	if(h){
		setting.height = h;
	}
	if(okcall){
		setting.ok = okcall;
	}
	if(cancelcall){
		setting.cancel = cancelcall;
	}
	if(closecall){
		setting.close = closecall;
	}
	setting.content = content;
	var d = top.art.dialog(setting);
	return d;
}
/**
 * 弹出模式窗口
 * @param url
 * @param w
 * @param h
 */
DialogUtil.prototype.open = function(title,url,w,h,okcall,cancelcall,closecall,data){
	var setting = {title: title,lock: true};
	if(w){
		setting.width = w;
	}
	if(h){
		setting.height = h;
	}
	if(okcall){
		setting.ok = okcall;
	}
	if(cancelcall){
		setting.cancel = cancelcall;
	}
	if(closecall){
		setting.close = closecall;
	}
	if(data){//如果存在数据传输
		top.art.dialog.data("transferData",data);
	}else{
		top.art.dialog.removeData("transferData");
	}
	var d = top.art.dialog.open(url,setting);
	return d;
};

/**
 * 关闭模式窗口
 * @param id
 */
DialogUtil.prototype.close = function(id){
	if(id){
		var d =top.art.dialog.list[id];
		d.close();
	}else{
		//关闭所有的
		var list = top.art.dialog.list;
		for (var i in list) {
		    list[i].close();
		};
	}
};
$.extend(window.DialogUtil,new DialogUtil());