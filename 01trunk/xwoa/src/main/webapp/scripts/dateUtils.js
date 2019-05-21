function newDate(datestr) {
	if (null != datestr) {
		if (datestr.length > 10) {
			datestr = datestr.substring(0, 10);
		}
		if (navigator.appName == "Microsoft Internet Explorer") {
			datestr = datestr.replace(/\//g, "-").split('-');
			var date = new Date();
			date.setUTCFullYear(datestr[0], datestr[1] - 1, datestr[2]);
			date.setUTCHours(0, 0, 0, 0);
			return date;
		}
		return new Date(datestr);
	} else {
		if (navigator.appName == "Microsoft Internet Explorer") {
			if (navigator.appVersion.match(/8./i) == '8.') {
				var mydate = new Date();
				return newDate(mydate.getFullYear() + "-"
						+ (mydate.getMonth() + 1) + "-" + mydate.getDate());
			}
		}
		return new Date();
	}
}
/**
 * 时间加减天数
 */
function addDate(strDate, days) {
	var d = newDate(strDate);
	d.setDate(d.getDate() + days);
	var month = d.getMonth() + 1;
	var day = d.getDate();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	var val = d.getFullYear() + "-" + month + "-" + day;
	return val;
}
/**
 * 根据当前时间和当前的周几获取本周的起始日期，周一算本周第一天
 */
function getBeginDate(strDate, weekDay) {
	var temp = weekDay.substring(1);
	weekDay = parseInt(temp);
	return getDate(newDate(strDate), 1 - weekDay)
}
/**
 * 根据当前时间和当前的周几获取本周的结束日期，周日算本周最后一天
 */
function getEndDate(strDate, weekDay) {
	var temp = weekDay.substring(1);
	weekDay = parseInt(temp);
	return getDate(newDate(strDate), 7 - weekDay);
}
function getDate(nowDate, diffDay) {
	var time = nowDate.getTime();
	time += diffDay * 24 * 3600000;
	nowDate.setTime(time);
	return dateFormat(nowDate, 'yyyy-MM-dd');
}
function dateFormat(date, fmt) { // author: meizz
	var o = {
		"M+" : date.getMonth() + 1, // 月份
		"d+" : date.getDate(), // 日
		"h+" : date.getHours(), // 小时
		"m+" : date.getMinutes(), // 分
		"s+" : date.getSeconds(), // 秒
		"S" : date.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
/**
 * 根据日期获取当前是第几周
 */
function getWeeks(year, mouth, day) {
	var day1 = new Date(year, mouth - 1, day);
	var day2 = new Date(year, 0, 1);

	var firstweek = day2.getDay();// 1月1日是星期几
	if (firstweek == 0) {
		firstweek = 6;
	} else {
		firstweek = firstweek - 1;
	}// 转化为0表示星期一,6表示星期日
	firstweek = (7 - firstweek) % 7;// 计算1月1日离第一周的天数
	var day3 = new Date(year, 0, 1 + firstweek)
	var result = Math.round((day1.getTime() - day3.getTime())
			/ (1000 * 60 * 60 * 24));
	result = Math.floor(result / 7) + 1;// 这个地方应该用floor返回最小次数然后+1
	return result;
};