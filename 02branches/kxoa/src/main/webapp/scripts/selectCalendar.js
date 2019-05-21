function SelectCalendar(options) {
	var calendar;
	this.getCalendar = function() {
		return calendar;
	};

	calendar = $('#' + options.param.id).fullCalendar(
			{
				noneTitle : options.param.noneTitle || false,
				firstDay : 0,
				/*header : options.param.header || {
					left : 'today prev,next',
					center : 'title',
					right : 'month,agendaWeek,agendaDay'
				},*/
				header : options.param.header || {
//					left : 'month,basicWeek',
					left : '',
					center : 'prev,title,next,today',
					right : ''

				},
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ],
				monthNamesShort : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				allDayText : '全天',
				axisFormat : 'h(:mm)tt',
				weekMode : 'liquid',
				editable : true,
				weekends : true,
				defaultView: options.param.defaultView ||'basicWeek' ,
				disableDragging:  options.param.disableDragging || false,
				buttonText : {
					prev : '&nbsp;&#9668;&nbsp;',
					next : '&nbsp;&#9658;&nbsp;',
					prevYear : '&nbsp;&lt;&lt;&nbsp;',
					nextYear : '&nbsp;&gt;&gt;&nbsp;',
					today : '&nbsp;今天&nbsp;',
					month : '&nbsp;月&nbsp;',
					week : '&nbsp;周&nbsp;',
					day : '&nbsp;日&nbsp;'
				},
				titleFormat : {
					month : 'yyyy年MMMM',
					week : "yyyy年 MMMd日[ yyyy]{'-'[ MMM]d'日'}",
					day : 'yyyy年MMMd日 dddd'
				},
				columnFormat : {
					month : 'ddd',
					week : 'M月d日 ddd',
					day : 'M月d日 dddd'
				},
				timeFormat : options.param.timeFormat || {
					month : 'M月d日  HH:mm{ - M月d日 HH:mm}',
					week : 'd日  HH:mm{ - d日 HH:mm}',
					'': 'MM月dd日 HH:mm{ - MM月dd日 HH:mm}' 
				},
				selectable : true,
				selectHelper : true,
				select : options.callback.select,

				events : options.param.events,

				eventClick : options.callback.eventClick || function(event) {
					
					var start = new Date(event._start), end = new Date(event._end), content = '';
					var startdText = Date.create(start).format('{M}月{dd}日 ') + getMyDay(start.getDay());
					var enddText = Date.create(end).format('{M}月{dd}日 ') + getMyDay(end.getDay());
					var starttText = Date.create(start).format(' {H}:{m}');
					var endtText = Date.create(end).format(' {H}:{m}');
					if (!event.allDay) {
						content += startdText + starttText + '&nbsp;&nbsp;–&nbsp;' + endtText;
					} else {
						if (startdText == enddText) {
							content += startdText;
						} else {
							content += startdText + '&nbsp;&nbsp;–&nbsp;&nbsp;' + enddText;
						}
					}

					var btnDel = {
						name : '删除',
						disabled : !event.editable,
						callback : function() {
							$.dialog({
								id : 'selectDialog',
								lock : false,
								title : '删除日程',
								content : '删除之后将无法恢复',
								button : [ {
									name : '确定',
									callback : function() {
										//ZENG.msgbox.show('正在更新中，请稍后...', 6, 100000);
										$.ajax({
											type : 'POST',
											url : options.param.url,
											data : 'ac=quick&do=del&id=' + event._id + '&instance=' + event.instance,
											success : function() {
												//ZENG.msgbox._hide();
												calendar.fullCalendar('removeEvents', event._id);
											}
										});
									},
									focus : true
								}, {
									name : '取消',
									callback : function() {

									}
								} ]
							});
						}
					};

					

					// 根据权限设置当前可见的按钮
					var button = [ btnDel ];

					$.dialog({
						title : event.title,
						content : content,
						width : 350,
						button : button
					});
					return false;
				},
				eventDrop : options.callback.eventDrop
						|| function(event, dayDelta, minuteDelta) {
							//ZENG.msgbox.show('正在更新中，请稍后...', 6, 100000);
							$.ajax({
								type : 'POST',
								url : options.param.url,
								data : 'ac=quick&do=drop&id=' + event._id + '&dayDelta=' + dayDelta + '&minuteDelta='
										+ minuteDelta + '&instance=' + event.instance,
								success : function() {
									//ZENG.msgbox._hide();
								}
							});
						},
				eventResize : options.callback.eventResize
						|| function(event, dayDelta, minuteDelta) {
							//ZENG.msgbox.show('正在更新中，请稍后...', 6, 100000);
							$.ajax({
								type : 'POST',
								url : options.param.url,
								data : 'ac=quick&do=resize&id=' + event._id + '&dayDelta=' + dayDelta + '&minuteDelta='
										+ minuteDelta + '&instance=' + event.instance,
								success : function() {
									//ZENG.msgbox._hide();
								}
							});
						},
				loading : function(bool) {
					if (bool) {
						//ZENG.msgbox.show('正在加载中，请稍后...', 6, 100000);
					} else {
						//ZENG.msgbox._hide();
					}
				}
			});
}

function getMyDay(day){
	var text = '周';
	switch(day){
		case 0: text += '日'; break;
		case 1: text += '一'; break;
		case 2: text += '二'; break;
		case 3: text += '三'; break;
		case 4: text += '四'; break;
		case 5: text += '五'; break;
		case 6: text += '六'; break;
	}
	return text;
}
