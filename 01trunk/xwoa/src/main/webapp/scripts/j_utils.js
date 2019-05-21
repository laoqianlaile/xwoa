function ConfigItems()	{}
ConfigItems.isAlert = true;



/* 
var init = [["enter!", "id_a", "S", "length:1,4","method:submit","format:email","prefix:YBXM_,YBQL_","suffix:YBXM_,YBQL_"],
			["enter!", "id_b", "N", "scope:[min,max]","method:submit","format:(12,4)","prefix:YBXM_,YBQL_","suffix:YBXM_,YBQL_"],
			["enter!", "id_c", "D", "scope:[2010-11-22,2011-11-22]","method:submit","format:yyyy-mm-dd","prefix:YBXM_,YBQL_","suffix:YBXM_,YBQL_"],
];
*/
//function ValidationUtils() {}
//ValidationUtils.initializeValidation
var _defaultValidation_ = "";
var _validateBeforeSave_ = "";
var _validateBeforeSubmit_ = "";
var _currentValidationFix_ = "";
$(document).ready(function() {
	try {
		if (initializeValidation && initializeValidation.length > 0) {
			for (var i=0; i<initializeValidation.length; i++) {
				var params = initializeValidation[i];
				var length, scope, method, format, prefix, suffix;
				for (var i=3; i<params.length; i++) {
					if ((params[i].indexOf("length") >= 0) && (length = params[i].replace("length:", "")));
					if ((params[i].indexOf("scope") >= 0) && (scope = params[i].replace("scope:", "")));
					if ((params[i].indexOf("method") >= 0) && (method = params[i].replace("method:", "")));
					if ((params[i].indexOf("format") >= 0) && (format = params[i].replace("format:", "")));
					if ((params[i].indexOf("prefix") >= 0) && (prefix = params[i].replace("prefix:", "")));
					if ((params[i].indexOf("suffix") >= 0) && (suffix = params[i].replace("suffix:", "")));
				}
				format = format ? format.replace(/\s/g, "").replace(/(^[,]*)|([,]*$)/g, "").replace(/[,]+/g, ",") : undefined;
				prefix = prefix ? prefix.replace(/\s/g, "").replace(/(^[,]*)|([,]*$)/g, "").replace(/[,]+/g, ",") : undefined;
				suffix = suffix ? suffix.replace(/\s/g, "").replace(/(^[,]*)|([,]*$)/g, "").replace(/[,]+/g, ",") : undefined;
				if ("S" == params[2] || "s" == params[2]) {
					var length = length.replace(/[(]|[)]|[[]|[]]/g, "");
					var array = length.split(",");
					var minLength = array[1] ? parseInt(array[0].replace(/\s/g, "")) : 0;
					var maxLength = array[1] ? parseInt(array[1].replace(/\s/g, "")) : parseInt(length.replace(/\s/g, ""));
					format = format ? (format.replace(/\s/g, "").split(",")) : undefined;
					var 
				} else if ("N" == params[2] || "n" == params[2]) {
					
				} else if ("D" == params[2] || "d" == params[2]) {
					
				} else {
					alert("Initialize Validation failed!\n" + params[0]);
				}
			}
			
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	
	
	
	
	
	
	
	
});


function Regexs() {}
Regexs.notEmpty = "^(.*)\\S+(.*)$"; //非空

$(document).ready(function() {
	if (initValidation) {
		try {
			for (var i=0; i<initValidation.length; i++) {
				for (var j=0; j<initValidation[i].length; j++) {
					var params = initValidation[i][j];
					if (params.length > 4) {
						var message = params[0];
						var id = params[1];
						var type = params[2];
						var scope = params[3];
						var method, format, prefix, suffix, current;
						for (var k=4; k<params.length; k++) {
							
						}
					}
					
				}
			}
		} catch (e) {
			alert("初始化验证失败！");
		}
	}
});

var undefined;
function FunctionUtils() {}
FunctionUtils.isFunction = function(func) {
	return ("function" == typeof(func));
};
FunctionUtils.getFuncName = function(func) {
	try {
		if (FunctionUtils.isFunction(func)) {
			return func.toString().split("(")[0].replace("function", "").replace(/\s/g, "");
		}
	} catch (e) {}
	return undefined;
};
FunctionUtils.getFuncHead = function(func) {
	try {
		if (FunctionUtils.isFunction(func)) {
			return func.toString().split("{")[0].replace("function", "").replace(/\s/g, "");
		}
	} catch (e) {}
	return undefined;
};
FunctionUtils.getFuncBody = function(func) {
	try {
		if (FunctionUtils.isFunction(func)) {
			var code = func.toString();
			return code.substring(code.indexOf("{")+1, code.length-1);
		}
	} catch (e) {}
	return undefined;
};
FunctionUtils.rewriteFunc = function (funcName, sign, otherFunc) {
	try {
		var func = eval(funcName);
		sign = sign.toLocaleLowerCase();
		if (FunctionUtils.isFunction(arguments[0])) {
			
			
			if ("and" == sign) {
				var code = "function " + FunctionUtils.getHead(func) + "{";
				
				
			}
			var code = func.toString();
			return code.substring(code.indexOf("{")+1, code.length-1);
		}
	} catch (e) {}
	return undefined;
};


function DomUtils()	{}
// 生成JQuery选择器，id或者name选择器
DomUtils.genJFinder = function(ele) {
	ele = arg0.replace(/\s/g, "");
	return ele ? (("#" == ele.charAt(0)) ? (ele) : ("[name=" + ele + "]")) : undefined;
};
// 获取当前id或者name（多个时取第一个）的标签名称
DomUtils.getTagName = function(source) {
	try {
		return ("string" == typeof(source) ? ($(DomUtils.genJFinder(source)).prop("tagName")) : ($(source).prop("tagName")));
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DomUtils.getTagName failed!"));
	return undefined;
};
// 获取当前id或者name（多个时取第一个）的标签名称（当为INPUT时，为标签类型）
DomUtils.getTagType = function(source) {
	try {
		var tagName = DomUtils.getTagName(source);
		return ("INPUT" == tagName) ? $(DomUtils.genJFinder(source)).attr("type") : tagName;
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DomUtils.getTagName failed!"));
	return undefined;
};
// // 生成JQuery对象，id或者name选择器所生成的JQuery对象
DomUtils.genJSelector = function(ele) {
	try {
		var finder = DomUtils.genJFinder(ele);
		var tagType = DomUtils.getTagType(ele);
		return $(("CHECKBOX" == tagType || "RADIO" == tagType) ? (finder + ":checked") : finder);
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DomUtils.genJSelector failed!"));
	return undefined;
};

function ValidateUtils() {}
/* 格式匹配，参数：元素id或name；元素值的类型（S、N、D，分别表示字符串、数字、日期）；格式
 * format：	当为字符串时，如果format是数字，则表示最大长度，如果是字符串，则表示对应字符串所表示的正则表达式规则
 * 			当为数字时，其中只包含一个数字，则表示是最大多少位的整数，当包含两个数字A和B时，表示总共A位，其中小数为B位，其中A>=B
 * 			当为日期时，匹配是否正确的日期格式，只能是从"yyyy-mm-dd hh:mi:ss"或者其中的一段
 */
ValidateUtils.formatValidation = function(ele, type, format) {
	try {
		var tagType = DomUtils.getTagName(ele);
		if ("RADIO" == tagType) {
			return $(DomUtils.genJSelector(ele)).length > 0;
		}
		var value = $(DomUtils.genJSelector(ele)).val();
		if ("SELECT" == tagType) {
			return value.replace(/\s/g, "").length > 0;
		}
		if ("")
		format = format ? format.replace(/\s/g, "") : undefined;
		if (!format && "S" == type.toUpperCase()) {
			if (!isNaN(parseInt(format))) {
				return value.length <= parseInt(format);
			} else {
				var regs = format.split(",");
				for (var i=0; i<regs.length; i++) {
					if (!(new RegExp(regexItems[regs[i]])).test(value)) {
						return false;
					}
				}
				return true;
			}
		} else if (!format && "N" == type.toUpperCase()) {
			var size, dSize=0;
			if (format.indexOf(",") >= 0) {
				size = parseInt(format.split(",")[0]);
				dSize = parseInt(format.split(",")[1]);
			} else {
				size = parseInt(format);
			}
			if (!isNaN(size) && !isNaN(dSize) && size >= dSize && dSize >= 0) {
				var reg = "";
				if (dSize == 0) {
					reg = "^-?\\d{1," + size + "}$";
				} else if (size == dSize) {
					reg = "^-?0(?:\\.\\d{1," + dSize + "})?$";
				} else if (size > dSize) {
					reg = "^-?\\d{1," + (size - dSize) + "}(?:\\.\\d{1," + dSize + "})?$";
				}
				return (new RegExp(reg)).test(value);
			}
		} else if (!format && "D" == type.toUpperCase()) {
			return DateUtils.isFormat(value, format);
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("ValidateUtils.formatValidation failed!"));
	return undefined;
};

ValidateUtils.scopeValidation = function(ele, type, scope) {
	try {
		var value = $(DomUtils.genJSelector(ele)).val();
		if ("" == value.replace(/\s/g, "")) {
			return true;
		}
		format = format ? format.replace(/\s/g, "") : undefined;
		if (!format && "S" == type.toUpperCase()) {
			if (!isNaN(parseInt(format))) {
				return value.length <= parseInt(format);
			} else {
				var regs = format.split(",");
				for (var i=0; i<regs.length; i++) {
					if (!(new RegExp(regexItems[regs[i]])).test(value)) {
						return false;
					}
				}
				return true;
			}
		} else if (!format && "N" == type.toUpperCase()) {
			var size, dSize=0;
			if (format.indexOf(",") >= 0) {
				size = parseInt(format.split(",")[0]);
				dSize = parseInt(format.split(",")[1]);
			} else {
				size = parseInt(format);
			}
			if (!isNaN(size) && !isNaN(dSize) && size >= dSize && dSize >= 0) {
				var reg = "";
				if (dSize == 0) {
					reg = "^-?\\d{1," + size + "}$";
				} else if (size == dSize) {
					reg = "^-?0(?:\\.\\d{1," + dSize + "})?$";
				} else if (size > dSize) {
					reg = "^-?\\d{1," + (size - dSize) + "}(?:\\.\\d{1," + dSize + "})?$";
				}
				return (new RegExp(reg)).test(value);
			}
		} else if (!format && "D" == type.toUpperCase()) {
			return DateUtils.isFormat(value, format);
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("ValidateUtils.formatValidation failed!"));
	return undefined;
};


function DateUtils() {}
/* 判断是否为Date类型 */
DateUtils.isDate = function(date) {
	return (date instanceof Date);
};
/* 克隆一个副本 */
DateUtils.clone = function(date) {
	if ((date instanceof Date)) {
		return new Date(date.toString());
	}
	if (ConfigItems.isAlert && alert("DateUtils.clone failed!"));
	return undefined;
};
/* 将日期字符串转化为Date类型 */
DateUtils.parseString = function(str) {
	try {
		var date = new Date(str.replace(/-/g,"/"));
		if (!isNaN(date)) {
			return date;
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DateUtils.parseString failed!"));
	return undefined;
};
DateUtils.isFormat = function(str, format) {
	format = format.replace(/[a-z]/g, "\\d");
	return (new RegExp("^" + format + "$")).test(str);
}
/* 将Date转化成指定格式内容的字符串 */
DateUtils.parseDate = function(date, format) {
	try {
		format = format.toLocaleLowerCase();
		if (format.indexOf("yyyy") >= 0 && (format = format.replace("yyyy", date.getFullYear())));
		if (format.indexOf("mm") >= 0 && (format = format.replace("mm", date.getMonth()+1)));
		if (format.indexOf("dd") >= 0 && (format = format.replace("dd", date.getDate())));
		if (format.indexOf("hh") >= 0 && (format = format.replace("hh", date.getHours())));
		if (format.indexOf("mi") >= 0 && (format = format.replace("mi", date.getMinutes())));
		if (format.indexOf("ss") >= 0 && (format = format.replace("ss", date.getSeconds())));
		if (!new RegExp("[a-z]").test(format)) {
			return format;
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DateUtils.parseDate failed!"));
	return undefined;
};

/* 重新设置date（属性有：year, month, date, hour, minute, second） */
DateUtils.moveTo = function(date, appoint, type) {
	try {
		if (("year" == type || "y" == type) && date.setFullYear(appoint));
		if (("month" == type || "M" == type) && date.setMonth(parseInt(appoint)-1));
		if (("date" == type || "d" == type) && date.setDate(appoint));
		if (("hour" == type || "h" == type) && date.setHours(appoint));
		if (("minute" == type || "m" == type) && date.setMinutes(appoint));
		if (("second" == type || "s" == type) && date.setSeconds(appoint));
		if (!isNaN(date)) {
			return date;
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DateUtils.moveTo failed!"));
	return undefined;
};
/* 偏移num个月的日期 */
DateUtils.addMonth = function(date, num) {
	var year = date.getFullYear();
	var month = date.getMonth();
	var moveYear = parseInt((num + month) / 12);
	month = (num + month) % 12;
	if (month < 0) {
		moveYear -= 1;
		month += 12;
	}
	date.setFullYear(year + moveYear);
	date.setMonth(month);
};
DateUtils.move = function(date, size, type) {
	try {
		var num = parseInt(size);
		if ("number" == typeof(num) && (date instanceof Date) && type) { // 指定类型偏移(year, month, date, hour, minute, second)
			if (("year" == type || "y" == type)) {
				date.setFullYear(date.getFullYear() + num);
				return date;
			} else if ("month" == type || "M" == type) {
				DateUtils.addMonth(date, num);
				return date;
			} else {
				var mark = 0;
				if (("second" == type || "s" == type) && (mark = 1000));
				if (("minute" == type || "m" == type) && (mark = 60000));
				if (("hour" == type || "h" == type) && (mark = 3600000));
				if (("date" == type || "d" == type) && (mark = 86400000));
				if (mark > 0) {
					date.setTime(date.getTime() + mark * num);
					return date;
				}
			}
		} else if ((date instanceof Date)) { // 计算表达式
			var multiple = (size.indexOf("-") >= 0) ? -1 : 1;
			exp = size.replace(/^[+]|[-]/, "");
			var index = exp.indexOf("y");
			if (index >= 0) {
				date.setFullYear(date.getFullYear() + multiple * parseInt(exp.substring(0, index)));
				exp = exp.substring(index+1);
			}
			index = exp.indexOf("M");
			if (index >= 0) {
				DateUtils.addMonth(date, multiple * parseInt(exp.substring(0, index)));
				exp = exp.substring(index+1);
			}
			exp = exp.replace("d", "*86400000+").replace("h", "*3600000+").replace("m", "*60000+").replace("s", "*1000+");
			var millis = eval("(" + exp + "0)") * multiple;
			date.setTime(date.getTime() + millis);
			return date;
		}
	} catch (e) {if (ConfigItems.isAlert && alert(e));}
	if (ConfigItems.isAlert && alert("DateUtils.move failed!"));
	return undefined;
};





/*
Regexs.blank = "";

twonum:"^\\d+\\.\\d{2}$ ",			//精确到2位的有效数字		
intege:"^-?[1-9]\\d*$",					//整数
intege1:"^[1-9]\\d*$",					//正整数
intege2:"^-[1-9]\\d*$",					//负整数
num:"^([+-]?)\\d*\\.?\\d+$",			//数字
num1:"^[1-9]\\d*|0$",					//正数（正整数 + 0）
num2:"^-[1-9]\\d*|0$",					//负数（负整数 + 0）
decmal:"^([+-]?)\\d*\\.\\d+$",			//浮点数

email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
color:"^[a-fA-F0-9]{6}$",				//颜色
url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	//url
chinese:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",					//仅中文
ascii:"^[\\x00-\\xFF]+$",				//仅ACSII字符
zipcode:"^\\d{6}$",						//邮编
mobile:"^(13|15)[0-9]{9}$",				//手机
ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",	//ip地址
notempty:"^(.*)\\S+(.*)$",						//非空
//notempty:"/^\s*$/", //非空
picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	//图片
rar:"(.*)\\.(rar|zip|7zip|tgz)$",								//压缩文件
date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",					//日期
qq:"^[1-9]*[1-9][0-9]*$",				//QQ号码
tel:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",	//电话号码的函数(包括验证国内区号,国际区号,分机号)
username:"^\\w+$",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
letter:"^[A-Za-z]+$",					//字母
letter_u:"^[A-Z]+$",					//大写字母
letter_l:"^[a-z]+$",					//小写字母
idcard:"^[1-9]([0-9]{14}|[0-9]{17})$"//身份证 */