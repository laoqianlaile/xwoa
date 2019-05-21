/*  
*@Description   :   湖南省政府门户网站-互动交流平台公共校本文件
*@Company       :   湖南浩基信息技术有限公司
*@Author        :   zzl(zzl@trswcm.com)
*@Create        :   2013年1月20日14:43:33
*@Update        :   2013年3月9日10:20:14
*@desc          :
本方法主要用于对系统中对各厅局部门互动进行不同展现样式的实现
 */
 
 
 //关闭按钮实现方法
var closeSelf  = function(){
    if (confirm("您确定要关闭本页面?")){
		window.open('','_parent',''); 
		window.close();
	}
}
// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) 
{ //author: meizz 
  var o = { 
    "M+" : this.getMonth()+1,                 //月份 
    "d+" : this.getDate(),                    //日 
    "h+" : this.getHours(),                   //小时 
    "m+" : this.getMinutes(),                 //分 
    "s+" : this.getSeconds(),                 //秒 
    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
    "S"  : this.getMilliseconds()             //毫秒 
  }; 
  if(/(y+)/.test(fmt)) 
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o) 
    if(new RegExp("("+ k +")").test(fmt)) 
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
  return fmt; 
}

var cutStr = function(str, len, hasDot) { 
	var newLength = 0; 
	var newStr = ""; 
	var chineseRegex = /[^\x00-\xff]/g; 
	var singleChar = ""; 
	var strLength = str.replace(chineseRegex,"**").length; 
	for(var i = 0;i < strLength;i++){ 
	  singleChar = str.charAt(i).toString(); 
	  if(singleChar.match(chineseRegex) != null) { 
		  newLength += 2; 
	  } 
	  else { 
		  newLength++; 
	  } 
	  if(newLength > len) { 
		  break; 
	  } 
	  newStr += singleChar; 
	} 

	if(hasDot && strLength > len) { 
		newStr += "..."; 
	} 
	return newStr; 
}
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

/**验证相关脚本**/

//验证字符串为空
function isNull(str){
	if(str&&$.trim(str).length>0)
		return false;
	else{
		return true;
	}
}

//获得参数串
String.prototype.getQuery = function(name){ 
　　var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
　　var r = this.substr(this.indexOf("\?")+1).match(reg); 
　　if (r!=null) return unescape(r[2]); return null; 
}

//获得事件触发对象
function getEventEl(){
    var evt=getEvent();
    var element=evt.srcElement || evt.target;
	return element;
}

//获得事件
function getEvent(){
    if(document.all){
    	return window.event;//如果是ie
    }
	func=getEvent.caller;
	while(func!=null){
    	var arg0=func.arguments[0];
    	if(arg0){
			if((arg0.constructor==Event || arg0.constructor ==MouseEvent)
			||(typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
				return arg0;
			}
    	}
        func=func.caller;
    }
    return null;
}

var validate = function(str,mode){
	if(mode==null){
		alert('验证参数未定义!');
		return false;
	}
	
	var regexEnum = {
		intege:"^-?[1-9]\\d*$",					//整数
		intege1:"^[1-9]\\d*$",					//正整数
		intege2:"^-[1-9]\\d*$",					//负整数
		num:"^([+-]?)\\d*\\.?\\d+$",			//数字
		num1:"^[1-9]\\d*|0$",					//正数（正整数 + 0）
		num2:"^-[1-9]\\d*|0$",					//负数（负整数 + 0）
		decmal:"^([+-]?)\\d*\\.\\d+$",			//浮点数
		decmal1:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",　　	//正浮点数
		decmal2:"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",　 //负浮点数
		decmal3:"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",　 //浮点数
		decmal4:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",　　 //非负浮点数（正浮点数 + 0）
		decmal5:"^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",　　//非正浮点数（负浮点数 + 0）
	
		email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
		color:"^[a-fA-F0-9]{6}$",				//颜色
		url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	//url
		chinese:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",					//仅中文
		ascii:"^[\\x00-\\xFF]+$",				//仅ACSII字符
		zipcode:"^\\d{6}$",						//邮编
		mobile:"^(13|15|18)[0-9]{9}$",				//手机
		ip4:"^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$",				//ip地址
		notempty:"^\\S+$",						//非空
		picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	//图片
		rar:"(.*)\\.(rar|zip|7zip|tgz)$",								//压缩文件
		date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",					//日期
		qq:"^[1-9]*[1-9][0-9]*$",				//QQ号码
		tel:"(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})",	//国内电话
		username:"^\\w+$",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
		username1:"^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$",//用来用户注册。匹配字母、数字、下划线、中文组成，不能以下划线开头和结尾
		letter:"^[A-Za-z]+$",					//字母
		letter_u:"^[A-Z]+$",					//大写字母
		letter_l:"^[a-z]+$",					//小写字母
		idcard:"^[1-9]([0-9]{14}|[0-9]{17})$"	//身份证
	}
	
	//短时间，形如 (13:04:06)
	function isTime(str){
		var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
		if (a == null) {return false}
		if (a[1]>24 || a[3]>60 || a[4]>60)
		{
			return false;
		}
		return true;
	}
	
	//短日期，形如 (2003-12-05)
	function isDate(str){
		var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
		if(r==null)return false; 
		var d= new Date(r[1], r[3]-1, r[4]); 
		return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
	}
	
	//长时间，形如 (2003-12-05 13:04:06)
	function isDateTime(str){
		var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
		var r = str.match(reg); 
		if(r==null) return false; 
		var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
		return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
	}
	
	/********************************************************/	
	
	var result = false;
	switch(mode){
		case 'time':
			result = isTime(str);
			break;
		case 'date':
			result = isDate(str);
			break;
		case 'datetime':
			result = isDateTime(str)
			break;
		default:
			var regEx = new RegExp(regexEnum[mode]);
			result = regEx.test(str);
	}
	
	return result;
}
/**
 * 简易判断输入是否符合规则
 * @param id		输入控件id
 * @param name		属性名称
 * @param min		最小个数
 * @param max		最大个数
 * @return			true  不符合，false 符合
 */
function commonCheck(id,name,min,max){
	$("#"+id).val($.trim($("#"+id).val()));
	if($("#"+id).val().length==0){
		alertMsg.error(name+"必须填写");
		$("#"+id).focus();
		return true;
	}
	if(min){
		if($("#"+id).val().length<min){
			alertMsg.error(name+"必须大于"+min+"个字");
			$("#"+id).focus();
			return true;
		}
	}
	if(max){
		if($("#"+id).val().length>max){
			alertMsg.error(name+"必须小于"+max+"个字");
			$("#"+id).focus();
			return true;
		}
	}
	return false;
}
/**
 * 集成化判断
 * @param id			需要验证的输入框id
 * @param mode			验证模式
 * @param emptyStr		是否可以为空
 * @param errorMsg		错误信息
 * @return				验证通过返回true
 */
function validateMe(id,mode,emptyStr,errorMsg,min,max,name){
	var str = $("#"+id).val();
	if(emptyStr && str.length==0){
		return true;
	}
	if(!validate(str,mode)){
		alertMsg.error(errorMsg);
		$("#"+id).focus();
		return false;
	}
	if(min){
		if($("#"+id).val().length<min){
			alertMsg.error(name+"必须大于"+min+"个字");
			$("#"+id).focus();
			return false;
		}
	}
	if(max){
		if($("#"+id).val().length>max){
			alertMsg.error(name+"必须小于"+max+"个字");
			$("#"+id).focus();
			return false;
		}
	}
	return true;
}

//登录验证
function frmsubmit(){
	if(trimAll(document.frm.userLogin.value).length==0){
		document.frm.userLogin.focus();
		alert("请输入用户名");
		return false;
	}
	if(trimAll(document.frm.userPasswd.value).length==0){
		document.frm.userPasswd.focus();
		alert("请输入密码");
		return false;
	}
	if(document.getElementById("li_regCode").style.display=="" &&  document.frm.registercode.value.length !=4){
		document.frm.registercode.focus();
		alert("请输入4位验证码");
		return false ;
	}
	document.getElementById("frm").submit();
}
//年龄校验
function isage(age){if(age==null || age=="") return true;return (!validate(age,'intege1')||age.length>3)?false:true;}
//名字校验
function isfullname(fullname){if(fullname==null || fullname=="")return true;return (!validate(fullname,'notempty') || fullname.length>10)?false:true;}
//控制描述的长度
var oldValue=new Array();
function checkMaxLen(obj,maxlength,num){
	if(obj.value==undefined){//DIV
		if(obj.innerHTML.length>maxlength){
			obj.innerHTML=oldValue[num];
		}else{
			oldValue[num]=obj.innerHTML;
		}
	}else{//INPUT
		if(obj.value.length>maxlength){
			obj.value=oldValue[num];
		}else{
			oldValue[num]=obj.value;
		}
	}
}
function isattach(file,ImageFileExtend,isAlert){
	//if(ImageFileExtend==null || ImageFileExtend.length<2)//未设置类型，验证常见类型
		//ImageFileExtend = ".doc,.txt,.rar,.jpg,.gif,.bmp,.xls"; 
		var  alertMsg = ImageFileExtend;
		ImageFileExtend = (","+ImageFileExtend+",").replace(/,/g, "|");
	 if(file.value.length>0){
		 var fileExtend=file.value.substring(file.value.lastIndexOf('.')).toLowerCase();        
		 if(ImageFileExtend.indexOf("|"+fileExtend+"|") != -1){   
			 return true;
		 }else{
			 if(isAlert){
				 alert("附件格式错误，请上传" + alertMsg + "格式的附件");
			 }
			return false;
		 }    
	 } 
	 return true;
}
function validateForFront(str,model){
	if(str=="") return true;
	if(model=="fullname"){
		return isfullname(str);
	}
	if(model=="age"){
		return isage(str);
	}
	return validate(str,model);
}
function onlyNum(evt){   
	if(evt==null){
	   evt=window.event;
	}
	var keycode = evt.keyCode;   //取得键盘码
	var realkey = String.fromCharCode(keycode);    //以键盘码转成键盘符号
	if(keycode==8 ||keycode==9 || keycode==46|| (keycode>=35 && keycode<=40)||(keycode>=96 && keycode<=105))
		return;   
	else if(!/\d/.test(realkey)){
		if(navigator.appName=="Netscape"){//区分浏览器种类
			evt.preventDefault();
			 return false; 
		}else{
		   window.event.returnValue = false;
		}
	}
}
function disable(obj) {
    if (obj.type == null) {
        return;
    }
    if (obj.type == 'reset' ||obj.type == 'button' ||obj.type == 'submit') {
        obj.disabled = true;
    } else if (obj.onclick != null) {
        obj.onclick = '';
    } else if (obj.onchange != null) {
        obj.onchange = '';
    } else if (obj.href != null && (obj.rel == null || obj.rel != 'stylesheet')) {
        obj.disabled = true;
        obj.href = '#';
    }
    return;
}
function disabledAllElements(){
	var objs = document.getElementsByTagName("*");
	for(i=0;i<objs.length;i++){
		disable(objs[i]);
	}
}
String.prototype.trim = function() { return this.replace(/(^\s*)|(\s*$)/g, ""); };

function assignField(id){
	document.getElementById("hidd_" + id).value=document.getElementById(id).innerHTML.trim();
}

function trimAll(text){
	return leftTrim(rightTrim(text));
}

function leftTrim(text){
	if(text==null || text=="") return text;
	var leftIndex=0;
	while(
		text.substring(leftIndex,leftIndex+1)==" "){
	    leftIndex++;
	}
	return text.substring(leftIndex,text.length);
}

function rightTrim(text){
	if(text==null || text=="") return text;
	var rightIndex=text.length;
	while(text.substring(rightIndex-1,rightIndex)==" "){
	    rightIndex--;
	}
	return text.substring(0,rightIndex);
}


/****
	 *@param elementId:日期显示的ID
	 *@param isShowTime:是否显示时间否则显示格式为：yyyy年MM月dd日 星期*
	 *@param isRefresh:显示时间的时候是否自动间隔一秒钟刷新当前时间
	***/
var	setCurDateForElement = function(elementId,isShowTime,isRefresh){
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
		$("#"+elementId).html(time);
		if(isRefresh==true &&isShowTime){
			var timer = setTimeout("wcmUtil.setCurDateForElement('"+elementId+"',"+isShowTime+","+isRefresh+")",1000);
		}
  }


var getParam = function(name){
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r!=null)
    return unescape(r[2]);
  return null;
}

 $(function(){
	//设置时间
//  setCurDateForElement("span_topNavDate",false,false);
//$("#li_hotMailList").hide();
//$(".div_copyRight").html('<p>承办单位：湖南省人民政府经济研究信息中心   技术支持：湖南浩基</p>')
  //获取并设置机构编号
	//var groupid=getParam("groupid");
	/*if(groupid==null||groupid==""){
	  groupid = $.cookie('groupid');
	}else{
	  $.cookie('groupid',groupid);
	}*/
	//定制了页面的不同的机构获取不同的数据展现
	/*if(groupid!=null&&groupid!=""){
	  $("head").append('<link rel="stylesheet" href="./template/8/images/css_dept_'+groupid+'.css" />');
	  $("head").append('<script type="text/javascript" src="./template/8/images/script_dept_'+groupid+'.js"></script>');
	}*/
	//$(".div_outWrap").show();
	  //$("#replyQuery_groupid").chosen();
  //$(".ul_replyQuery .chzn-drop").width(120);
	
	
    //link_curpos_homeLink //当前位置首页ID
	//span_topNavSiteLink //顶部部门名称
	//div_bottomCopyRight //底部版权信息
	//span_curGroup //顶部当前位置中
 })