/**
* 首页领导日程安排
*/
;(function($){
	/**
	 *  {
	 *        "year":2017,
     *		  "weekNo":1,
     *		  "firstDayOfWeek":"2017年01月01日",
     *		  "lastDayOfWeek":"2017年01月07日",
     *        "daysEnum":["2017-01-01","2017-01-02","2017-01-03","2017-01-04","2017-01-05","2016-01-06","2017-01-07"],
     *        "weekEnum":["周日","周一","周二","周三","周四","周五","周六"],
     *        "data":[
     *                {
     *                	"leaderCode":"99999",
     *                	"leaderName":"领导A",
     *                	"events":[
     *                	          {"beginTime":"2017-01-01 09:30:11","endTime":"2017-01-01 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"},
     *                	          {"beginTime":"2017-01-02 09:30:11","endTime":"2017-01-02 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"}
     *                             ...
     *                             ]
     *                 }
     *                 ...
     *              ]
     * };
	 */
	var scheduleData,contextPath,currentYear,currentWeekNo;

	/**
	 *渲染完整的html
	 */
    function render(){
       var html = '';
       html += renderMycalendarNavbar();
       html+=' <div style="margin-left:5px;margin-right:5px;border:1px solid #F0F0F0">';
       html+= renderMycalendarHeader();
       html+= renderMycalendarBody();
       html+='</div>';
       return html;
    };

	/**
	 * 渲染导航控制部分
	 * <div class="mycalendar-navbar" style="margin-left:5px;margin-right:5px;height:80px;line-height:80px;">
     *        <span style="top:40%;left:35%;font-size:20px;font-weight: bold;color:#7F7F7F">2016年10月01日~2016年10月07日 第1周</span>
     *        <span style="top:40%;right:5px;font-size:14px;color:#7F7F7F"><a>&lt;&nbsp;上周</a><a>今天</a><a>下周&nbsp;&gt;</a></span>
     *  </div>
	 */
	function renderMycalendarNavbar(){
		 var mycalendarNavbarHtml = '<div class="mycalendar-navbar" style="margin-left:5px;margin-right:5px;height:80px;line-height:80px;">'
                                  + ' <span style="top:40%;left:35%;font-size:20px;font-weight: bold;color:#7F7F7F">{firstDayOfWeek}~{lastDayOfWeek} 第{weekNo}周</span>'
                                  + '<span style="top:40%;right:5px;font-size:14px;color:#7F7F7F"><a id="prevWeek">&lt;&nbsp;上周</a><a id="today">本周</a><a id="nextWeek">下周&nbsp;&gt;</a></span>'
                                  + '</div>';
            mycalendarNavbarHtml = mycalendarNavbarHtml.replace("{firstDayOfWeek}",scheduleData.firstDayOfWeek)
                                   .replace("{lastDayOfWeek}",scheduleData.lastDayOfWeek)
                                   .replace("{weekNo}",scheduleData.weekNo);
            $(mycalendarNavbarHtml).find("#prevWeek").click(function(){alert(1)});
           return mycalendarNavbarHtml;
	}
	
	/**
	 * 渲染头部
	 *  <div class="mycalendar-header">
     *     <table>
     *     <tr>
     *        <th style="width:9%"></th>
     *        <th style="background-color:#E8E8E8;width:13%">周日 1/1</th>
     *        <th style="background-color:#E8E8E8;width:13%">周一 1/2</th>
     *        <th style="background-color:#E8E8E8;width:13%">周二 1/3</th>
     *        <th style="background-color:#E8E8E8;width:13%">周三 1/4</th>
     *        <th style="background-color:#E8E8E8;width:13%;">周四 1/5</th>
     *        <th style="background-color:#E8E8E8;width:13%;">周五 1/6</th>
     *        <th style="background-color:#E8E8E8;width:13%;">周六 1/7</th>
     *     </tr>
     *    </table>
     *   </div> 
	 */
	function renderMycalendarHeader(){
		var mycalendarHeaderHtml='<div class="mycalendar-header"><table><tr><th style="background-color:#E8E8E8;width:9%">领导人员</th>';
		 eachEveryDayOfWeek(function(i){
			mycalendarHeaderHtml+='<th style="background-color:#E8E8E8;width:13%">'+scheduleData.weekEnum[i]+' '+formate(scheduleData.daysEnum[i])+"</th>";
		 });
		 
	       mycalendarHeaderHtml+="</tr></table></div>";
	       return mycalendarHeaderHtml;
	}
	function formate(str){
		return parseInt(str.substring(5,7))+"/"+parseInt(str.substring(8,10))
	}
	/**
	 *  <div class="mycalendar-body">
	 *                        <div  style="position: relative;">
	 *	                          <div class="mycalendar-body-bg">
	 *	                             <table>
	 *	                               <tr>
	 *	                                 <td style="width:9%;border-left:none;">领导D</td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                               </tr>
	 *	                             </table>
	 *	                          </div>
	 *	                          <div class="mycalendar-body-content" style="position: relative;min-height:45px;">
	 *                             <table>
	 *	                               <tr>
	 *	                                 <td style="width:9%;"></td>
	 *	                                 <td style="width:13%"><a style="display:block;background:red;margin:5px 5px 0 5px;">3:00-15:00 开会</a></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td colspan="2"><a style="display:block;background:red;margin:5px 5px 0 5px;">3:00-15:00 开会</a></td>
	 *	                               </tr>
	 *	                               <tr>
	 *	                                 <td style="width:9%;"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"></td>
	 *	                                 <td style="width:13%"><a style="display:block;background:red;margin:5px 5px 0 5px;">3:00-15:00 开会</a></td>
	 *	                               </tr>
	 *	                             </table>
	 *	                          </div>
	 *                         </div>
	 *                        
	 *   </div>
	 */
	function renderMycalendarBody(){
		 var partHtml = '<div class="mycalendar-body">';
		 if(!scheduleData.data || scheduleData.data.length==0){
			 partHtml = '<div style="height:60px;line-height:60px;">暂无安排</div>';
		 }else{
			 $.each(scheduleData.data,function(){
				 partHtml+='<div  style="position: relative;">';
				 partHtml+=renderBodyBg(this.leaderName);
				 partHtml+=reanderBodyContent(this.events);
				 partHtml+='</div>';
			 });	 
		 }
		 
		 partHtml+='</div>';
		 return partHtml;
	}
	/**
	 * 渲染栅格
	 */
	function renderBodyBg(leaderName){
		var partHtml = '<div class="mycalendar-body-bg"><table><tr>';
		partHtml+='<td style="width:9%;border-left:none;">'+leaderName+'</td>';
		eachEveryDayOfWeek(function(i){
				 partHtml+='<td style="width:13%"></td>';
		});
		partHtml+="</table></div>"
		return partHtml;
	}
	/**
	 * 渲染色块
	 */
	function reanderBodyContent(eventDatas){
		var partHtml = '<div class="mycalendar-body-content" style="position: relative;min-height:45px;"><table>';
		var matrix = createMatrix(eventDatas);
       
		$.each(matrix,function(){
           var TR = $("<tr>"),that = this;
           TR.append('<td style="width:9%;"></td>');
           var TD;
           for(var i = 0;i < that.length;i++){
	           	if(that[i] == -1){//如果当前位置为-1，代表无事件，创建一个空列
	           		TD = $("<td>",{colspan:1}).css("width","13%");	
	           		TR.append(TD);
	           	}else{
	               if(i==0){
	               	  TD = $("<td>",{colspan:1}).css("width","13%");
	               	  TD.append(renderEvent(eventDatas[that[i]]));	
	           		  TR.append(TD);
	               }else{
	               	  //如果上一项更当前项相同，代表是同一个事件
                      if(that[i-1]==that[i]){
                           var colspan = parseInt(TD.attr("colspan")) + 1;
                           TD.attr("colspan",colspan);
                           TD.css("width",colspan*13+"%");
                      }else{
                      	TD = $("<td>",{colspan:1}).css("width","13%");
	               	    TD.append(renderEvent(eventDatas[that[i]]));	
	           		    TR.append(TD);
                      }
	               }
	           	}
           }
          partHtml+=(TR[0].outerHTML);
		});
		partHtml+="</table></div>"
		return partHtml;
	}
    /**
    *  渲染事件
    */
	function renderEvent(event){
		var background = {'B':'#ade2fc','G':'#9fda9f','N':'#F7F7F7','Y':'#fdfd95','P':'#e6c2e6','R':'#cb6768','O':'#ffd68c'};
		var eventJQObj = $("<a>").css({
			background:background[event.bgcolorType]
		});
		var desc = event.beginTime.substring(11,16)+'-'+event.endTime.substring(11,16)+'['+event.type+']'+event.title;
		eventJQObj.attr('title',desc);
		eventJQObj.text(desc);
		return eventJQObj;
	}
	/**
	 * 构造矩阵
	 * N行7列，每一个单元格的默认值为-1，用事件在事件数组的索引来填充矩阵
	 */
	function createMatrix(eventDatas){
		var matrix = new Array();
		matrix.push([-1,-1,-1,-1,-1,-1,-1]);
		$.each(eventDatas,function(evtIdx){
           var eventBeginTime = this.beginTime.substring(0,10),eventEndTime = this.endTime.substring(0,10);
            var eventBeginIndex = getIndex(scheduleData.daysEnum,eventBeginTime);//开始时间在一周的位置
    		var eventEndIndex = getIndex(scheduleData.daysEnum,eventEndTime);//结束时间在一周的位置
           
           //事件可能跨多天,这里申明一个数组用来存放7天中事件发生在第几天
           var idxArr = [];
           if(eventBeginIndex == eventEndIndex){//如果事件的时间在同一天
             idxArr.push(eventBeginIndex);
           }else if(eventBeginIndex < eventEndIndex){
                for(var i=eventBeginIndex;i<=eventEndIndex;i++){
    					   idxArr.push(i);
    			}
           }
           //将第几天填充到矩阵中相应的位置去，矩阵中每一行的位置代表第几天，如果这个位置存在不为-1的值
           //到矩阵的下一行去填充，idxArr代表同一行的相连位置，只要有一个位置这个数组就得在矩阵中重启一行判断，
           //只判断idxArr第一个位置是不行的，防止和其他事件重叠
           var needAppendRow = true;
           
            $.each(matrix,function(){
            	var that = this,isOccupied = false;
                $.each(idxArr,function(){
                    if(that[this] != -1){//当前行的当前位置被占用了
                    	isOccupied = true;
                    	return false;//跳出循环没必要继续循环了
                    }
                });
                if(!isOccupied){//如果当前行没有被占用
                   needAppendRow = false;
                    $.each(idxArr,function(){
                    	that[this] = evtIdx;
                    });
                    return false;//跳出matrix循环
                }
            });
             //所有行的位置都存在被占用过，那么我们新加一个行
            if(needAppendRow){
            	 var appendRow = [-1,-1,-1,-1,-1,-1,-1];
            	   $.each(idxArr,function(){
            	   	 appendRow[this] = evtIdx;
            	   });
            	   matrix.push(appendRow);		
            }
		});
       return matrix;
	}
     /**
       * 注意事件可能从上周延续过来的，也可能本周延续到下周的
       */
	 function getIndex(arr,item){
    	for(var i=0;i<arr.length;i++){
    		if(arr[i]===item){
    			return i;
    		}
    	}
    	//如果当前日期比第一个日期还小的话，那么索引为0
    	if(dateDiff(item,arr[0]) < 0){
    		return 0;
    	}
    	//如果当前日期比最后一个日期还大的话，那么索引为最大位置
    	if(dateDiff(item,arr[arr.length -1]) > 0){
    		return arr.length -1;
    	}
    	return -1;
    }

    function dateDiff(d1,d2){
     var result = Date.parse(d1.replace(/-/g,"/"))- Date.parse(d2.replace(/-/g,"/"));
     return result;
    }
	/**
	 * 遍历一周的每一天
	 */
	function eachEveryDayOfWeek(callback){
		for(var i=0;i<7;i++){
			callback(i);
		}
	}
    /**
     * 加载数据
     * type 0-当前 -1-上一周 1-下一周
     *
     * 示例数据
     * scheduleData = {
        			"weekNo":1,
        			"firstDayOfWeek":"2017年01月01日",
        			"lastDayOfWeek":"2017年01月07日",
        	        "daysEnum":["2017-01-01","2017-01-02","2017-01-03","2017-01-04","2017-01-05","2017-01-06","2017-01-07"],
        	        "weekEnum":["周日","周一","周二","周三","周四","周五","周六"],
        	        "data":[
        	                {
        	                	"leaderCode":"99999",
        	                	"leaderName":"领导A",
        	                	"events":[
        	                	          {"beginTime":"2017-01-01 09:30:11","endTime":"2017-01-01 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"},
        	                	          {"beginTime":"2017-01-01 13:30:11","endTime":"2017-01-01 15:30:11","title":"关于人大代表研讨","type":"约会","bgcolorType":"P"},
        	                	          {"beginTime":"2017-01-02 09:30:11","endTime":"2017-01-02 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"},
        	                	          {"beginTime":"2017-01-02 11:00:11","endTime":"2017-01-03 10:30:11","title":"关于伟哥贷款的事","type":"开会","bgcolorType":"O"}
        	                             ]
        	                 },
        	                 {
         	                	"leaderCode":"99999",
         	                	"leaderName":"领导B",
         	                	"events":[
         	                	          {"beginTime":"2017-01-01 09:30:11","endTime":"2017-01-01 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"},
         	                	          {"beginTime":"2017-01-01 13:30:11","endTime":"2017-01-01 15:30:11","title":"关于人大代表研讨","type":"约会","bgcolorType":"P"},
         	                	          {"beginTime":"2017-01-02 09:30:11","endTime":"2017-01-02 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"Y"},
         	                	          {"beginTime":"2017-01-02 11:00:11","endTime":"2017-01-03 10:30:11","title":"关于伟哥贷款的事","type":"开会","bgcolorType":"O"}
         	                             ]
         	                 },
       	                 {
        	                	"leaderCode":"99999",
        	                	"leaderName":"领导C",
        	                	"events":[
        	                	          {"beginTime":"2017-01-01 09:30:11","endTime":"2017-01-06 10:30:11","title":"关于老杨这个枪毙","type":"开会","bgcolorType":"G"},
        	                	          {"beginTime":"2017-01-06 09:30:11","endTime":"2017-01-07 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"G"}
        	                             ]
        	                 },
        	                 {
         	                	"leaderCode":"99999",
         	                	"leaderName":"领导E",
         	                	"events":[
         	                	          {"beginTime":"2016-12-31 09:30:11","endTime":"2017-01-01 10:30:11","title":"关于老杨这个枪毙","type":"开会","bgcolorType":"G"},
         	                	          {"beginTime":"2017-01-07 09:30:11","endTime":"2017-01-08 10:30:11","title":"关于人大代表研讨","type":"开会","bgcolorType":"G"}
         	                             ]
         	                 }
        	              ]
        	};
     */
    function loadData(type,container){
    	var param = {};
    	if(type==0){//如果查询今天的数据
    		if(!scheduleData){
    			param.year = currentYear;
    			param.weekNo = currentWeekNo;
    		}
    	}
    	if(type==-1){//如果查询上一周
    		var year = parseInt(scheduleData.year);
    		var weekNo = parseInt(scheduleData.weekNo);
    		if(scheduleData.weekNo == 1){
    			param.year = year-1;
    			param.weekNo = scheduleData.maxWeekLastYear;
    		}else{
    			param.year = year;
    			param.weekNo = weekNo-1;
    		}
    	}
    	if(type==1){//如果查询下一周
    		var year = parseInt(scheduleData.year);
    		var weekNo = parseInt(scheduleData.weekNo);
    		if(scheduleData.weekNo == scheduleData.maxWeek){//如果最后一周
    			param.year = year+1;
    			param.weekNo = 1;
    		}else{
    			param.year = year;
    			param.weekNo = weekNo+1;
    		}
    	}
    	
    	$.ajax({
			url:contextPath+'/app/dashboard!viewScheduleOfLeader.do?acType=auto&v='+new Date().getTime(),
			type:'post',
			async:false,
			dataType:'json',
			data:param,
			success:function(data){
				scheduleData = data;
				if(currentYear==undefined || currentWeekNo == undefined){
					currentYear = scheduleData.year;
					currentWeekNo = scheduleData.weekNo;
				}
			}
		});
    	
    	container.html(render());
    	container.find("#prevWeek").click(function(){
    		loadData(-1,$(this).parents(".mycalendar"));
    	});
    	container.find("#today").click(function(){
    		loadData(0,$(this).parents(".mycalendar"));
    	});
    	container.find("#nextWeek").click(function(){
    		loadData(1,$(this).parents(".mycalendar"));
    	});
    }
    
  
    $.fn.loadLeaderSchedule = function(ctx){
    	contextPath = ctx;
    	return this.each(function(){
    		loadData(0,$(this));
    	});
    };
	
})(jQuery);