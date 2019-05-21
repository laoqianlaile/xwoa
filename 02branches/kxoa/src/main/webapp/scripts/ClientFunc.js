function checkIntegerOnBlur(obj, labelName) {
	if (obj.value != "" && !isInteger(obj.value)) {
		alert(labelName + "\u5fc5\u987b\u4e3a\u6709\u6548\u6574\u6570\u3002");
		obj.select();
		return false;
	}
	return true;
}
function isNumber(inputVal) {
	// edit BY Mayer.Ng
	if(inputVal.length>10) return false;
	oneDecimal = false;
	if(inputVal=="")
	  return false;
	inputStr = inputVal.toString();
	for (var i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.charAt(i);
		if (i == 0 && oneChar == "-") {
			continue;
		}
		if (oneChar == "." && !oneDecimal) {
			oneDecimal = true;
			continue;
		}
		if (oneChar < "0" || oneChar > "9") {
			return false;
		}
	}
	return true;
	// inputStr = inputVal.toString();
	// for (var i = 0; i < inputStr.length; i++) {
	// var oneChar = inputStr.charAt(i);
	// if (i == 0 && oneChar == "-") {
	// continue;
	// }
	// if (oneChar == "." && !oneDecimal) {
	// oneDecimal = true;
	// continue;
	// }
	// if (oneChar < "0" || oneChar > "9") {
	// return false;
	// }
	// }
	// return true;
}
function isInteger(inputVal) {
	if (!isNumber(inputVal)) {
		return false;
	}
	if (inputVal.indexOf(".") >= 0) {
		return false;
	} else {
		return true;
	}
}
function LTrim(s) {
	for (var i = 0; i < s.length; i++) {
		if (s.charAt(i) != " ") {
			return s.substr(i, s.length - i);
		}
	}
	return "";
}
function RTrim(s) {
	for (var i = s.length - 1; i >= 0; i--) {
		if (s.charAt(i) != " ") {
			return s.substr(0, i + 1);
		}
	}
	return "";
}
function Trim(s) {
	return RTrim(LTrim(s));
}
function isPageNum(inputVal) {
	inputStr = Trim(inputVal.toString());
	for (var i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.charAt(i);
		if (oneChar < "0" || oneChar > "9") {
			return false;
		}
	}
	return true;
}
function isEmpty(s) {
	return "" == Trim(s);
}
function LTrimZero(s) {
	for (var i = 0; i < s.length; i++) {
		if (s.charAt(i) != "0") {
			return s.substr(i, s.length - i);
		}
	}
	return "";
}
function isDate(o) {
	var sDate = o.value;
	if(sDate == "")
		return true;
	if (sDate.length != 10) {
		alert("\u65e5\u671f\u957f\u5ea6\u6709\u8bef")
		o.select()
		return false;
	}
	var arrDate = sDate.split("-");
	if (arrDate.length != 3) {
		//alert("\u65e5\u671f\u957f\u5ea6\u6709\u8bef")
		o.select()
		return false;
	}
	var year = arrDate[0];
	var month = arrDate[1];
	var day = arrDate[2];
	if (isEmpty(year) || isEmpty(month) || isEmpty(day)) {
		alert("\u65e5\u671f\u683c\u5f0f\u9519\u8bef\uff0c\u5e94\u4e3a[0000-00-00]")
		o.select()
		return false;
	}
	if (isNaN(year) || isNaN(month) || isNaN(day)) {
		alert("\u65e5\u671f\u683c\u5f0f\u9519\u8bef\uff0c\u5e94\u4e3a[0000-00-00]")
		o.select()
		return false;
	}
	year = parseInt(LTrimZero(year));
	month = parseInt(LTrimZero(month));
	day = parseInt(LTrimZero(day));
	if (isNaN(year) || year < 1900 || year > 2100 || month > 12 || day > 31
			|| month <= 0 || day <= 0) {
		alert("\u65e5\u671f\u6708\u4efd\u5e94\u57281-12\u4e4b\u95f4\n\u65e5\u671f\u5929\u6570\u5e94\u57281-31\u4e4b\u95f4")	
		o.select()
		return false;
	}
	switch (month) {
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10 :
		case 12 :
			return true;
		case 4 :
		case 6 :
		case 9 :
		case 11 :
			if (day > 30) {
				alert("\u65e5\u671f\u6708\u4efd\u5e94\u57281-12\u4e4b\u95f4\n\u65e5\u671f\u5929\u6570\u5e94\u57281-31\u4e4b\u95f4")
				o.select()
				return false;
			}
			else
				return true;
		case 2 :

			// ??
			if (0 == year % 4 && (year % 100) != 0 || 0 == year % 400) {
				if (day > 29) {
					alert("\u65e5\u671f\u76842\u6708\u4efd\u5929\u6570\u4e0d\u80fd\u8d85\u8fc728/29")
					o.select()
					return false;
				}
				return true;
			}
			// ???
			if (day > 28) {
				alert("\u65e5\u671f\u76842\u6708\u4efd\u5929\u6570\u4e0d\u80fd\u8d85\u8fc728/29")
				o.select()
				return false;
			}
			return true;
		default :
			return false;
	}
}
//function $(name){document.all(name);}
//created new
function comparedDate(d1,d2,s1,s2){
	var date1 = $(d1).value;
	var date2 = $(d2).value;
	var date1s = date1.split('-');
	var date1D = new Date(date1s[0],date1s[1],date1s[2]);
	var date2s = date2.split('-');
	var date2D = new Date(date2s[0],date2s[1],date2s[2]);
	if((isDate($(d1)) && isDate($(d2))))
	{	
	  if(date1D.getTime() > date2D.getTime()){
		alert(s1+"\u4e0d\u80fd\u5c0f\u4e8e"+s2);
		$(d2).select();
		return false;
	  }
	}
	
	return true;
	
}



function isTime(o,s) {
	var sTime = o.value;
	if (sTime == "")
		return true;
	var pattern=/^([0-1]\d|[2][0-3]):[0-5]\d:[0-5]\d$/; // 00:00:00 ~ 23:59:59
	
	if (s==='h') {
		var pattern=/^[0-1]\d|[2][0-3]$/;
	}
	else if (s=='m' || s=='s'){
		var pattern=/^[0-5]\d$/;
	}
	//alert(pattern.test(sTime));
	if (!pattern.test(sTime)) {
		alert("\u65f6\u95f4\u683c\u5f0f\u4e0d\u6b63\u786e")
		o.select()
		return false;
	}
	return true;
	/*if (sTime == "")
		return true;
	var arrTime = sTime.split(":");
	var hour;
	var minute;
	var second;
	if (arrTime.length != 3) {
		alert("\u65e5\u671f\u957f\u5ea6\u6709\u8bef")
		o.select()
		return false;
	}
	if (arrTime.length == 0) { // just hour
	}
	hour = 0;
	minute = 0;
	second = 0;
	if (arrTime.length == 1) {
		hour = arrTime[0];
		minute = 0;
		second = 0;
	}
	if (arrTime.length == 2) {
		hour = arrTime[0];
		minute = arrTime[1];
		second = 0;
	}
	if (arrTime.length == 3) {
		hour = arrTime[0];
		minute = arrTime[1];
		second = arrTime[2];
	}
	if (isNaN(hour) || isNaN(minute) || isNaN(second)) {
		o.select()
		return false;
	}
	hour = parseInt(LTrimZero(hour));
	minute = parseInt(LTrimZero(minute));
	second = parseInt(LTrimZero(second));
	if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0
			|| second > 59) {
		o.select()
		return false; // exit
	}
	return true;*/
}
function isDateTime(sDateTime) {
	var arrDateTime = sDateTime.split(" ");
	if (arrDateTime.length == 1) {
		return isDate(arrDateTime[0]); // exit
	}
	if (arrDateTime.length == 2) {
		return (isDate(arrDateTime[0]) && isTime(arrDateTime[1])); // exit
	}
	return false; // exit
}
// String is not null
function isNull(obj, label) {
	if (obj.value.replace(/(^\s*)|(\s*$)/g, "") == '') {
		alert(label + "\u4e0d\u80fd\u4e3a\u7a7a");
				
		obj.select();
	}
}
function isUnSelect(o,label){
	if(o.value == ''){
		alert(label + "\u4e0d\u80fd\u4e3a\u7a7a");
		o.focus();
	}
}
//
function returnRadioValue(radioList) {
	var i = 0;
	var value;
	if (radioList == null) {
		return null;
	} else {
		if (radioList.length == null) {
			if (radioList.checked) {
				value = radioList.value;
			}
		} else {
			for (i = 0; i < radioList.length; i++) {
				if (radioList[i].checked) {
					value = radioList[i].value;
					break;
				}
			}
		}
	}
	return value;
}
/*
 * //beginDate>endDate function checkLogicDate(beginDate,endDate) { var bDate;
 * var eDate;
 * 
 * 
 * bDate = eval(beginDate.substring(0,beginDate.indexOf("-"))); eDate =
 * eval(endDate.substring(0,endDate.indexOf("-"))); if (bDate>eDate) { return
 * "��ʼʱ����ڽ���ʱ��"; } else { if (bDate<eDate) { return ""; } }
 * 
 * 
 * bDate =
 * eval(beginDate.substring(beginDate.indexOf("-")+1,beginDate.lastIndexOf("-")));
 * eDate =
 * eval(endDate.substring(endDate.indexOf("-")+1,endDate.lastIndexOf("-"))); if
 * (bDate>eDate) { return "��ʼʱ����ڽ���ʱ��"; } else { if (bDate<eDate) { return
 * ""; } }
 * 
 * bDate =
 * eval(beginDate.substring(beginDate.lastIndexOf("-")+1,beginDate.length));
 * eDate = eval(endDate.substring(endDate.lastIndexOf("-")+1,endDate.length));
 * if (bDate>eDate) { return "��ʼʱ����ڽ���ʱ��"; } return ""; }
 * 
 * function checkItem(str,maxLen,isMust){ if(str == null || str ==
 * ""||Trim(str)==""){ // if (isMust == '0') // return null; // else // return
 * "����Ϊ��"; ; // return "ssss"; } else{ var num =str.length; // var
 * arr=str.match(/[^\\\\x00-\\\\x80]/ig); // if(arr!=null){ // for (var
 * nIndex=0;nIndex<arr.length;nIndex++){ // if (arr[nIndex]!=" "){ // num++; // } // } // } //
 * if(num >maxLen) return "���Ȳ��ܳ���"+ maxLen + "�ַ�"; }
 * 
 * //return ""; }
 */
function checkInput(obj, maxlen, labelname) {
	var num = obj.value.length;
	// alert(obj);
	// alert(maxlen);
	if (num > maxlen) {
		alert(labelname + "\u65e0\u6548");
		obj.select();
		return false;
	}
	return true;
}
/** Check Number's Valid Create By mao */
function checkValid(obj, maxlen, validlen, labelname, type) {
	if (!checkNum(obj, maxlen, validlen, labelname)) {
		alert(labelname
				+ "\u5fc5\u987b\u4e3a\u6709\u6548\u7684"
				+ type
				+ "\u3002\n[\u5c0f\u6570\u7cbe\u786e\u5230\u5c0f\u6570\u70b9\u540e\u7b2c"
				+ validlen + "\u4f4d]");
		obj.select();
		return false;
	} else {
		return true;
	}
}
function checkNum(obj, maxlen, validlen) {
	var str = obj.value;
	var pattern = /^(([1-9]\d*)(\.\d{1,})|(0\.[1-9]\d*)|(0\.\d*[1-9])|[+]?\d*)$/;
	var index = str.indexOf(".");
	var leave = str.length - index - 1;
	if (str == "" || str == null || str.length == 0) {
		return true;
	}
	if (str.length > maxlen+1) {
		return false;
	} else {
		if (!pattern.test(str)) {
			return false;
		} else {
			if (index < 0) {
				return true;
			}
			if (leave > validlen+1) {
				return false;
			}
			return true;
		}
	}
}
/** 选择项目处理 Create By CP */
function changesssssss() {
	if (document.indsticForm.items.value == "1") {
		a.style.display = "";
		b.style.display = "";
		c.style.display = "";
	} else {
		document.all.a.style.display = "none";
		document.all.b.style.display = "none";
		document.all.c.style.display = "none";
	}
	if (document.indsticForm.items.value == "2") {
		d.style.display = "";
		e.style.display = "";
		f.style.display = "";
	} else {
		d.style.display = "none";
		e.style.display = "none";
		f.style.display = "none";
	}
	if (document.indsticForm.items.value == "3") {
		g.style.display = "";
		h.style.display = "";
	} else {
		g.style.display = "none";
		h.style.display = "none";
	}
	if (document.indsticForm.items.value == "4") {
		i.style.display = "";
		j.style.display = "";
		k.style.display = "";
	} else {
		i.style.display = "none";
		j.style.display = "none";
		k.style.display = "none";
	}
	// if (document.form1.items.value == "5") {
	// l.style.display = "";
	// m.style.display = "";
	// n.style.display = "";
	// o.style.display = "";
	// } else {
	// l.style.display = "none";
	// m.style.display = "none";
	// n.style.display = "none";
	// o.style.display = "none";
	// }
	if (document.indsticForm.items.value == "6") {
		p.style.display = "";
		q.style.display = "";
		r.style.display = "";
	} else {
		p.style.display = "none";
		q.style.display = "none";
		r.style.display = "none";
	}
	if (document.indsticForm.items.value == "7") {
		s.style.display = "";
	} else {
		s.style.display = "none";
	}
	// if (document.form1.items.value == "8") {
	// t.style.display = "";
	// u.style.display = "";
	// v.style.display = "";
	// } else {
	// t.style.display = "none";
	// u.style.display = "none";
	// v.style.display = "none";
	// }
	// if (document.form1.items.value == "9") {
	// w.style.display = "";
	// } else {
	// w.style.display = "none";
	// }
	if (document.indsticForm.items.value == "10") {
		x.style.display = "";
		y.style.display = "";
		z.style.display = "";
	} else {
		x.style.display = "none";
		y.style.display = "none";
		z.style.display = "none";
	}
}
/** checkbox Create By Mao */
function checkByName(obj) {
	if (document.getElementById(obj).checked == false) {
		document.getElementById(obj).checked = true;
		document.getElementById(obj + "_text1").style.display = "block";
		document.getElementById(obj + "_text2").style.display = "none"

	} else {
		document.getElementById(obj).checked = false;
		document.getElementById(obj + "_text1").style.display = "none";
		document.getElementById(obj + "_text2").style.display = "block"
	}
}
/** 限制文本域字数大小 */
function show(obj, i, len) {
	var size = obj.value.length;
	document.getElementById("limit" + i).style.display = "";
	if (size > len) {
		obj.value = obj.value.substring(0, len);
	}
	var curr = len - size;
	if (curr < 0) {
		curr = 0;
	}
	document.getElementById("chLeft" + i).innerHTML = "(\u5b57\u6570\u9650\u5236\u4e3a"
			+ len + "\u5b57\uff0c\u8fd8\u5269" + curr.toString() + "\u5b57)";
}
function checkLength(which, i, len) {
	var maxChars = len;
	var old = which.value.length;
	if (old > maxChars) {
		which.value = which.value.substring(0, maxChars);
	}
	var curr = maxChars - old;
	if (curr < 0) {
		curr = 0;
	}
	document.getElementById("chLeft" + i).innerHTML = "(\u5b57\u6570\u9650\u5236\u4e3a"
			+ len + "\u5b57\uff0c\u8fd8\u5269" + curr.toString() + "\u5b57)";
}
function hidden(obj, i) {
	document.getElementById("limit" + i).style.display = "none";
}
/** 图片效果：半透明-->透明 */
function high(which2) {
	theobject = which2;
	highlighting = setInterval("highlightit(theobject)", 50);
}
function low(which2) {
	clearInterval(highlighting);
	which2.filters.alpha.opacity = 40;
}
function highlightit(cur2) {
	if (cur2.filters.alpha.opacity < 100) {
		cur2.filters.alpha.opacity += 5;
	} else {
		if (window.highlighting) {
			clearInterval(highlighting);
		}
	}
}
/** 文本框内嵌字效果* */
function Text_hid(o) {
	alert(o);
}
/***/
function clearSelected(obj) {
	if (document.getElementById(obj).value == "") {
		document.getElementById(obj).value = "";
	}
}

/** 文件上传类型判断* */
function uploadtype() {
	typevalue = document.getElementById("type").value;// list 选中类型
	filestr = document.getElementById("ph").value.toLowerCase();// 转换成小写
	if (typevalue == "0" || typevalue == "1" || typevalue == "2"
			|| typevalue == "3" || typevalue == "4" || typevalue == "5"
			|| typevalue == "6" || typevalue == "7") {
		if (!(filestr.indexOf(".gif") > 0 || filestr.indexOf(".jpg") > 0
				|| filestr.indexOf(".bmp") > 0 || filestr.indexOf(".png") > 0)) {
			alert("\u6587\u4ef6\u7c7b\u578b\u9519\u8bef[*.gif/*.jpg/*.bmp/*.png\u683c\u5f0f]");
			document.getElementById('ph').outerHTML = document
					.getElementById('ph').outerHTML;
			return false;
		}
	}
	if (typevalue == "8" || typevalue == "9") {
		if (!(filestr.indexOf(".xls") > 0 || filestr.indexOf(".cvs") > 0)) {
			alert("\u6587\u4ef6\u7c7b\u578b\u9519\u8bef[*.xls/*.cvs\u683c\u5f0f]");
			document.getElementById('ph').outerHTML = document
					.getElementById('ph').outerHTML;
			return false;
		}
	}
	return true;
}
/** 弹出一个对话层* */
function hidediv(obj) {
	document.getElementById(obj).style.display = "none";
	// document.getElementById('DivShim').style.display="none";
}
function showdiv(obj, obj1, obj2, obj3, obj4) {
	document.getElementById('rsid').value = obj3;
	document.getElementById('daacdt').value = obj4;
	document.body.scrollTop = "0px";
	// document.body.style.overflow="hidden";
	var _so = document.getElementById(obj);
	var _sm = document.getElementById(obj1);
	var _sh = document.getElementById(obj2);
	// var iframe = document.getElementById('DivShim');
	_sh.style.width = "0px";

	_sh.style.height = "0px";

	_sm.style.display = "none";
	_so.style.display = "";
	// iframe.style.display="";
	_so.style.height = document.body.scrollHeight + "px";
	// iframe.style.height=_so.offsetHeight;
	// iframe.style.width =_so.offsetWidth;
	// iframe.style.zIndex = _so.style.zIndex-1;
	_sm.style.top = (document.body.clientHeight - 200) / 2 + "px";
	_sm.style.left = (document.body.clientWidth - 300) / 2 + "px";
	_sh.style.top = parseInt(_sm.style.top) + 3 + "px";
	_sh.style.left = parseInt(_sm.style.left) + 3 + "px";
	_sh.style.backgroundcolor = '#000000';
	function reSizeObj() {
		var w = parseInt(_sh.style.width);
		var h = parseInt(_sh.style.height);
		var W = parseInt(_sm.style.width);
		var H = 127;
		w += Math.ceil((W - w) / 2);
		h += Math.ceil((H - h) / 2);
		if (w > W) {
			w = W;
		}
		if (h > H) {
			h = H;
		}
		_sh.style.width = w + "px";
		_sh.style.height = h - 3 + "px";
		if (w != W) {
			setTimeout(reSizeObj, 10);
		} else {
			_sm.style.display = "";
		}
	}
	_so.oncontextmenu = function() {
		window.event.returnValue = false;
	}
	_so.onselectstart = function() {
		window.event.returnValue = false;
	}
	reSizeObj();
}
/** *鼠标经过弹出and隐藏一个层* */
function altDiv(obj) {
	x = window.event.x;
	y = window.event.y;

	document.getElementById(obj).style.left = x + 8;
	document.getElementById(obj).style.top = y + 12;
	document.getElementById(obj).style.zIndex = 1000;
	document.getElementById(obj).style.display = "block";
}
function altDivHid(obj) {
	document.getElementById(obj).style.display = "none";
}
/** *checkbox 全选择和全取消** */
function checkAll() {
	obj = document.getElementsByTagName('input');

	for (var i = 0; i < obj.length; i++) {
		obj[i].checked = "checked";
	}
}
function checkNoAll() {
	obj = document.getElementsByTagName('input');

	for (var i = 0; i < obj.length; i++) {
		obj[i].checked = "";
	}
}
// //
function Show(divid) {
	divid.filters.revealTrans.apply();
	
	divid.style.visibility = "visible";
	divid.filters.revealTrans.play();
}
function Hide(divid) {
	divid.filters.revealTrans.apply();
	divid.style.visibility = "hidden";
	
	divid.filters.revealTrans.play();
}
//重置所有选项
function resetAll(){
	var obj = document.getElementsByTagName("select");
	for(var i=0;i<obj.length;i++){
		obj[i].selectedIndex = 0;
	}
	var obj2 = document.getElementsByTagName("input");
	for(var j=0;j<obj2.length;j++){
		
			if(obj2[j].type == "radio")
				obj2[j].checked = "false";
			if(obj2[j].type == "check")
				obj2[j].checked = "false";
			else
				obj2[j].value = "";
		
	}
}
//鼠标经过历史记录时候的事件
function onMouseOverEvent(obj){
	obj.className="newbsinf";	
}
function onMouseOutEvent(obj){
	obj.className="bsinf";	
}