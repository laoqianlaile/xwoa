/************************
 *
 *WCM模板网页中常用的工具方法
 *@addTime		:2010年9月28日9:06:55
 *@updateTime	:2012年12月8日 18:46:56
 *@author		:TRSzzl(trszzl@qq.com)
 *
 *
 *@function	函数功能说明
 *wcmUtil.$time()--------------------------------获取系统当前时间
 *wcmUtil.$random(min,max)-----------------------获取一个min~max间的随机数
 *wcmUtil.getObject(objId)-----------------------获取id为objId的DOM对象(兼容性更好)
 *wcmUtil.createPageHTML(_nPageCount, _nCurrIndex, _sPageName, _sPageExt)---------TRS分页置标
 *wcmUtil.JSAddFavorite(thisURL,URLname)---------添加到收藏夹方法，兼容火狐、IE等主流浏览器
 *wcmUtil.setTheHomePage(thisURL)----------------设为首页方法，需在页面添加homepageId的DOM元素ID
 *wcmUtil.setCurDateForElement(elementId,isShowTime,isRefresh)----------------设置系统当前时间
 *************************/
 /****
		==============modify log================
		2011年2月5日11:03:40：修改火狐下的日期年份显示错误。
		2011年5月1日22:19:15：处理了时间动态显示错误的bug
		2011年11月8日 2:13:08：加入jquery扩展插件include方法
		2012年12月8日 18:46:27：使用StringBuffer改进分页方法
 ***/
/*******************************************************
* JavaScript的StringBuffer工具
*******************************************************/

function StringBuffer() {
	this.str = "";
	this.objArray = new Array();
	this.append = function (s) {
		if (this.length() == 0) {
			this.objArray[0] = s;
		} else {
			this.objArray[this.length() + 1] = s;
		}
	};
	this.toString = function () {
		if (this.length() == 0) {
			return "";
		} else {
			return this.objArray.join("");
		}
	};
	this.chatAt = function (s) {
		if (this.toString() != "") {
			return this.toString().indexOf(s);
		}
	};
	this.clear = function () {
		if (this.length() != 0) {
			this.objArray.length = 0;
		}
	};
	this.length = function () {
		return this.objArray.length;
	};
	this.substring = function (start, end) {
		if (start > 0 || start == 0) {
			if (end <= this.length() && (end > start)) {
				return this.toString().substring(start, end);
			} else {
				alert("JavaScriptException:IndexOutOfBounds");
				return null;
			}
		} else {
			alert("JavaScriptException:IndexOutOfBounds");
			return null;
		}
	};
	this.toArray = function () {
		var tempArray = this.objArray.join(",");
		return tempArray.split(",");
	};
	this.setcharAt = function (charIndex, str) {
		if (charIndex < 0 || charIndex > this.length()) {
			alert("JavaScriptException:IndexOutOfBounds");
			return null;
		} else {
			this.objArray[charIndex] = str;
		}
		return this.toString();
	};
	this.replace = function (oldchar, newchar) {
		var foundChar = this.toString().indexOf(oldchar);
		if (foundChar < 0) {
			alert("JavaScriptException:not found oldchar");
			return null;
		} else {
			return this.toString().replace(oldchar, newchar);
		}
	};
}

var wcmUtil = new Object();
wcmUtil = {
	version:'1.2',
	author:'TRS朱治龙',
	createTime:'2010年11月30日14:07:56',
	isIE : !!window.ActiveXObject,  
	isIE6 : wcmUtil.isIE && !window.XMLHttpRequest, 
	isIE8 : wcmUtil.isIE && !!document.documentMode,
	isIE7 : wcmUtil.isIE && !wcmUtil.isIE6 && !wcmUtil.isIE8,
	$time : Date.now || function(){
		return +new Date;
	},
	$random:function(min, max){
		return Math.floor(Math.random() * (max - min + 1) + min);
	},
	getObject : function(objectId) {
		if(document.getElementById && document.getElementById(objectId)) {
		// W3C DOM
		return document.getElementById(objectId);
		} else if (document.all && document.all(objectId)) {
		// MSIE 4 DOM
		return document.all(objectId);
		} else if (document.layers && document.layers[objectId]) {
		// NN 4 DOM
		return document.layers[objectId];
		} else {
		return false;
		}
	},
	getQueryString:function(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
var r = window.location.search.substr(1).match(reg);
if (r!=null) return unescape(r[2]); return null;
},
	createPageHTML : function(_nPageCount, _nCurrIndex, _sPageName, _sPageExt){
		var pageStr = new StringBuffer();
		if(_nPageCount == null || _nPageCount<=1){//如果总页数小于1页则不输出分页项
			return;
		}
		var startNum =1;//记录显示的第一页位置
		var endNum = _nPageCount<9?_nPageCount:9;//记录显示的最后一页位置
		var nCurrIndex = _nCurrIndex || 0;//这句基本的点多余
		pageStr.append("<div id='div_page'>");
		if(_nPageCount>=2&&nCurrIndex>=1){//判断输出上一页
			pageStr.append("<a href=\""+_sPageName+ "."+_sPageExt+"\">&laquo;</a>&nbsp;");
			if(nCurrIndex==1){
				pageStr.append("<a href=\""+_sPageName+ "."+_sPageExt+"\">&lt;</a>&nbsp;");
			}else{
				pageStr.append("<a href=\""+_sPageName+"_" + (nCurrIndex-1) + "."+_sPageExt+"\">&lt;</a>&nbsp;");
			}
		}
		
		if(nCurrIndex == 0)
			pageStr.append("<a class='current'>1</a>&nbsp;");
		else if(nCurrIndex<5)
			pageStr.append("<a href=\""+_sPageName+"."+_sPageExt+"\">1</a>&nbsp;");
		
		if(_nPageCount>9&&nCurrIndex>=5){//如果总分页数大于11页，则仅显示当前页前后5条数据
			endNum = nCurrIndex-0+5>_nPageCount?_nPageCount:nCurrIndex-0+5;
		}
		if(nCurrIndex>4){
			startNum = nCurrIndex-4;
		}
		for(var i=startNum;i<endNum; i++){//循环输出页码数
			if(nCurrIndex == i)
				pageStr.append("<a class='current'>"+(i-0+1) + "</a>&nbsp;");
			else
				pageStr.append("<a href=\""+_sPageName+"_" + i + "."+_sPageExt+"\" >"+(i-0+1)+"</a>&nbsp;");
		}
		if(_nPageCount>=2&&_nCurrIndex!=_nPageCount-1){//判断输出下一页标识
			pageStr.append("<a href=\""+_sPageName+"_" + (nCurrIndex-0+1) + "."+_sPageExt+"\">&gt;</a>&nbsp;");
			pageStr.append("<a href=\""+_sPageName+"_" + (_nPageCount-1) + "."+_sPageExt+"\">&raquo;</a>&nbsp;");
			
		}
		pageStr.append("</div>");
		$(".div_cutPage").html(pageStr.toString());
	},
	JSAddFavorite : function(thisURL,URLname){
		if ( window.sidebar && "object" == typeof( window.sidebar ) && "function" == typeof( window.sidebar.addPanel ) )
		{
				//  firefox
				window.sidebar.addPanel(URLname,thisURL,'');
		}
		else if ( document.all && "object" == typeof( window.external ) )
		{
				//  ie                
				window.external.addfavorite(thisURL,URLname);
		}
	},
	setTheHomePage : function(thisURL)
	{
		try{ 
		  document.body.style.behavior='url(#default#homepage)';
		  document.body.setHomePage(thisURL); 
		}catch(e){ 
		  if(window.netscape) { 
			try { 
			  netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
			} 
			catch (e) { 
			  alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。"); 
		  } 
		  var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); 
		  prefs.setCharPref('browser.startup.homepage',thisURL); 
		} 
	  } 
	},
	/****
	 *@param elementId:日期显示的ID
	 *@param isShowTime:是否显示时间否则显示格式为：yyyy年MM月dd日 星期*
	 *@param isRefresh:显示时间的时候是否自动间隔一秒钟刷新当前时间
	***/
	setCurDateForElement :  function(elementId,isShowTime,isRefresh){
		//设置当前系统时间。
		var now = new Date();
		var year = now.getYear();
		year = (year<1900?(1900+year):year);
		var month = now.getMonth();
		var date = now.getDate();
		var day = now.getDay();
		var week;
		month = month+1;
		if(month<10)month="0"+month;
		if(date<10)date="0"+date;
		var arr_week = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
		week = arr_week[day];
		var time = "";
		time = year+"年"+month+"月"+date+"日 "+week;
		if(isShowTime==true){
			var hour = now.getHours();
			var minu = now.getMinutes();
			var sec = now.getSeconds();
			if(hour<10)hour="0"+hour;
			if(minu<10)minu="0"+minu;
			if(sec<10)sec="0"+sec;
			time += " "+hour+"："+minu+"："+sec;
		}
		if(document.all){
			wcmUtil.getObject(elementId).innerHTML=time;
		}
		if(isRefresh==true &&isShowTime){
			var timer = setTimeout("wcmUtil.setCurDateForElement('"+elementId+"',"+isShowTime+","+isRefresh+")",1000);
		}
  }
}

//去除字符串的前后空白
String.prototype.trim = function(){
      return this.replace(/(^\s*)|(\s*$)/g,"");
}
//判断字符串是否全为数字
String.prototype.IsNum = function(){
    var reg = /^\d+$/g;
    return reg.test(this);
}

var openIframe = function(url){
  window.location.href="/iframe_content.html?url="+url;
}

//jquery动态加载加载JS和CSS文件的插件。
$.extend({
    includePath: "http://www.hunan.gov.cn/xxgk/images/",
    include: function(file){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='"+ $.includePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});
$.extend({
    hudongincludePath: "http://act.hunan.gov.cn/",
    hudonginclude: function(file){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='"+ $.hudongincludePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});

$(function(){
	//设置简繁体
	if (getCookie('zh_choose')) {
        var zh_choose = getCookie('zh_choose');	   
	    if(zh_choose!=null&&zh_choose=="s"){
		  $("#link_language_s").hide();
		  $("#link_language_t").show();
		}else{
			$("#link_language_s").hide();
			$("#link_language_t").show();
		  
		}
	}
	
	
	
	//输入框获取焦点事件
	$('#txt_searchWord_nav').focusin(function(){
		if($('#txt_searchWord_nav').val()=="请输入关键词"){
			$('#txt_searchWord_nav').val("");
			$('#txt_searchWord_nav').css('color','#000');
		}
	});
	//输入框失去焦点事件
	$('#txt_searchWord_nav').focusout(function(){
		if($('#txt_searchWord_nav').val()==""){
			$('#txt_searchWord_nav').val("请输入关键词");
			$('#txt_searchWord_nav').css('color','gray');
		}
	});
	//检索表单提交时间
	$("#form_commonSearch_nav").submit(function(){
	  var searchWord = document.getElementById("txt_searchWord_nav").value;
	  if(searchWord=="请输入关键词"||searchWord==""){
		document.getElementById("txt_searchWord_nav").value="请输入关键词";
	    document.getElementById("txt_searchWord_nav").select();
		return false;
	  }
	  document.getElementById("txt_searchWordHide").value=searchWord.replace(/\ /g,"*").replace(/\ /g,"*");
	  return true;
    })
		//检索表单提交时间
	$("#form_govSimpleSearch").submit(function(){
	  var searchWord = document.getElementById("txt_searchWord_nav").value;
	  if(searchWord=="请输入关键词"||searchWord==""){
		document.getElementById("txt_searchWord_nav").value="请输入关键词";
	    document.getElementById("txt_searchWord_nav").select();
		return false;
	  }
	  document.getElementById("txt_searchWordHide").value=searchWord.replace(/\ /g,"*").replace(/\ /g,"*");
	  return true;
    })

})