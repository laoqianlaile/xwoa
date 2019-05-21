/* ConfigurationItems begin */
function ConfigurationItems() {
}
ConfigurationItems.isAlertException = false;
/* ConfigurationItems end */

/* Object.prototype begin */
/* Object.prototype end */

/* Date.prototype begin */
Date.prototype.format = function(mask) {
	var d = this;
	var zeroize = function (value, length) {
		if (!length) length = 2;
		value = String(value);
		for (var i=0, zeros=""; i<(length-value.length); i++) {
			zeros += "0";
		}
		return zeros + value;
	};
	return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function($0) {
		switch($0) {
		case "d": return d.getDate();
		case "dd": return zeroize(d.getDate());
		case "ddd": return ["周日","周一","周二","周三","周四","周五","周六"][d.getDay()];
		case "dddd": return ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"][d.getDay()];
		case "M": return d.getMonth() + 1;
		case "MM": return zeroize(d.getMonth() + 1);
		case "MMM": return ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"][d.getMonth()];
		case "MMMM": return ["January","February","March","April","May","June","July","August","September","October","November","December"][d.getMonth()];
		case "yy": return String(d.getFullYear()).substr(2);
		case "yyyy": return d.getFullYear();
		case "h": return d.getHours() % 12 || 12;
		case "hh": return zeroize(d.getHours() % 12 || 12);
		case "H": return d.getHours();
		case "HH": return zeroize(d.getHours());
		case "m": return d.getMinutes();
		case "mm": return zeroize(d.getMinutes());
		case "s": return d.getSeconds();
		case "ss": return zeroize(d.getSeconds());
		case "l": return zeroize(d.getMilliseconds(), 3);
		case "L": var m = d.getMilliseconds();
			if (m > 99) m = Math.round(m / 10);
			return zeroize(m);
		case "tt": return d.getHours() < 12 ? "am" : "pm";
		case "TT": return d.getHours() < 12 ? "AM" : "PM";
		case "Z": return d.toUTCString().match(/[A-Z]+$/);
		// Return quoted strings with the surrounding quotes removed
		default: return $0.substr(1, $0.length - 2);
		}
	});
};
Date.prototype.addYears = function(y) {
	var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);
    if (m < this.getMonth()) {
    	this.setDate(0);
    }
};
Date.prototype.addMonths= function(m) {
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);
    if (this.getDate() < d) {
    	this.setDate(0);
    } 
};
Date.prototype.addWeeks = function(w) {
    this.addDays(w * 7);
};
Date.prototype.addDays = function(d) {
    this.setDate(this.getDate() + d);
};









/* Date.prototype end */

/* String.prototype begin */
String.prototype.isDate = function(fmt) {
	try {
		fmt = fmt.replace(/\w/g, "\\d");
		return new RegExp(fmt).test(this);
	} catch (e) {
		if (ConfigurationItems.isAlertException && alert(e))
			;
	}
	return false;
};
String.prototype.isFloat = function() {
	try {
		return !isNaN(parseFloat(this));
	} catch (e) {
		if (ConfigurationItems.isAlertException && alert(e))
			;
	}
	return false;
};
String.prototype.isInteger = function() {
	try {
		return !isNaN(parseInt(this))
				&& (parseInt(this) * 1.0 == parseFloat(this));
	} catch (e) {
		if (ConfigurationItems.isAlertException && alert(e))
			;
	}
	return false;
};
String.prototype.getFloatVal = function() {
	try {
		return parseFloat(this);
	} catch (e) {
		if (ConfigurationItems.isAlertException && alert(e))
			;
	}
	return undefined;
};
String.prototype.getIntVal = function() {
	try {
		return parseInt(this);
	} catch (e) {
		if (ConfigurationItems.isAlertException && alert(e))
			;
	}
	return undefined;
};
/* String.prototype end */

/* Number.prototype begin */
/* Number.prototype end */

/* Function.prototype begin */
/* Function.prototype end */

/* Array.prototype begin */
/* Array.prototype end */