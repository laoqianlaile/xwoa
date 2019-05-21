<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 

<html>
	<head>
	  <title>会议申请</title>
	  	 <link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/bootstrap/css/bootstrap.min.css">	
	  <link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css">
	 <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
	 <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
	 <style type="text/css">
	     * { margin:0; padding:0; font-family:Microsoft YaHei!important;}
	     .container{width:99%;margin:0 0.5%;}
	     .container .searchArea{height:40px;line-height:36px;background:#c1d9f3}
	      .container table{border:1px solid #ccc}
	      .container table td,.container table th{border:1px solid #ccc;text-align:center}
	       .container table td{font-size:13px;color:#4b555b}
	        .container table td a{text-decoration: none;color:#4b555b;font-weight: bold;}
	      .container .dateControl{height:30px;text-align: center;line-height:30px;}
	      .container .dateControl span{display:inline-block;}
	       .container .dateControl span.right{
	        width: 0px;
            height: 0px;
            border-top: 7px solid transparent;
            border-bottom: 7px solid transparent;
            border-left: 10px solid black;
            font-size: 0px;
            line-height: 0px;
            margin-left:10px;
            cursor: pointer;
            }
            .container .dateControl span.left{
	        width: 0px;
            height: 0px;
            border-top: 7px solid transparent;
            border-bottom: 7px solid transparent;
            border-right: 10px solid black;
            font-size: 0px;
            line-height: 0px;
            margin-right:10px;
            cursor: pointer;}
 			.themeList li { float:left; padding:0 3px; list-style:none;cursor:default}
 			.sqz_from{
 				float:left; 
 				 list-style:none;
 				 margin-left:10px;
 				 cursor:default;
 				background-color: #fdfd95;
 				 width: 15px;
 				 height: 15px;
 			}
 			.yap_from{
 				float:left; 
 				 list-style:none;
 				 margin-left:10px;
 				 cursor:default;
 				background-color: #9fda9f;
 				 width: 15px;
 				 height: 15px;
 			}
	 </style>
	 <script type="text/javascript">
	 $(function(){
		  var currDate = new Date();
		  //周几
		  var weekDay = ("0"+currDate.getDay()).replace('00','07');
		  //第几周
		  var week = getWeeks(currDate.getFullYear(),(currDate.getMonth()+1),currDate.getDate());
		  initAfterRending(dateFormat(currDate,'yyyy/MM/dd'),week,weekDay);
		  startLoadData();
	  });
	  
	  function initAfterRending(strDate,week,weekDay){
		  var firstDayOfWeek = getBeginDate(strDate,weekDay);
		  var lastDayOfWeek = getEndDate(strDate,weekDay);
		  var monitorText = "第"+week+"周("+firstDayOfWeek+"~"+lastDayOfWeek+")";
		  
		  $("#showDate").val(monitorText);
		  $("#monitor").html(monitorText);
		  $("#beginDate").val(firstDayOfWeek);
		  $("#endDate").val(lastDayOfWeek);
	  }
	  function datePickerClick(){
		   WdatePicker({el:"dateHidden",isShowWeek:true,onpicked:function() {
				  var week=$dp.cal.getP('W','WW');
				  var currDate = $dp.cal.getP('y')+"/"+$dp.cal.getP('M')+"/"+$dp.cal.getP('d');
				 var weekDay = $dp.cal.getP('w').replace('00','07');
				  initAfterRending(currDate,week,weekDay);
			    }
			  });
	   }
	  /**
	  * 加载数据
	  */
	  function startLoadData(){
		 $.ajax({
			 type:"post",
			 url:"${ctx}/oa/oaMeetApply!wirteDatasBoardroomArranged.do",
			 dataType:"json",
			 data:{
				 "beginDate":$("#beginDate").val(),
				 "endDate":$("#endDate").val(),
				 "from":$("#from").val()
			 },
			 success:function(resp){
				 if(!resp.result){
					 alert(resp.mest);
				 }else{
					 rendTable(resp.data);
				 }
			 }
		 })
	  }
	  /**
	  *前一周
	  */
     function turnPrevTerm(){
    	 prepareForTurn(-7);
    	 startLoadData();
     }
	  /**
	  *后一周
	  */
     function turnNextTrem(){
    	 prepareForTurn(7);
    	 startLoadData();
     }
     
     function prepareForTurn(diffDays){
    	 var beginDate = $("#beginDate").val();
    	 var endDate = $("#endDate").val();
    	 var otherTermBeginDate = addDate(beginDate.replace(/-/g,"/"),diffDays);
    	 var otherTermEndDate = addDate(endDate.replace(/-/g,"/"),diffDays);
    	 
    	 var d = new Date(otherTermBeginDate.replace(/-/g,"/"));
    	 var week = getWeeks(d.getFullYear(),(d.getMonth()+1),d.getDate());
    	 var monitorText = "第"+week+"周("+otherTermBeginDate+"~"+otherTermEndDate+")";
		  
		  $("#showDate").val(monitorText);
		  $("#monitor").html(monitorText);
		  $("#beginDate").val(otherTermBeginDate);
		  $("#endDate").val(otherTermEndDate);
     }
     /**
     *时间加减天数
     */
     function addDate(strDate,days){ 
    	 var d=new Date(strDate); 
    	 d.setDate(d.getDate()+days); 
    	 var month=d.getMonth()+1; 
    	 var day = d.getDate(); 
    	 if(month<10){ 
    	 month = "0"+month; 
    	 } 
    	 if(day<10){ 
    	 day = "0"+day; 
    	 } 
    	 var val = d.getFullYear()+"-"+month+"-"+day; 
    	 return val; 
     }
     /**
     * 渲染表格
     */
	  function rendTable(data){
		  var table = $(".dataArea").find("table");
		  table.find("tr").each(function(index){
			  if(index>0){
				  $(this).remove();
			  }
		  });
		  $.each(data,function(i,item){
			  var tr = $("<tr>");
			  var tdRoomName = $("<td>",{"height":"60px"}).html("<a href='javascript:void(0)' onclick='popForRoom(\""+item["roomId"]+"\")'>"+item["roomName"]+"</a>").appendTo(tr);
			  for(var i=1;i<=7;i++){
				  $("<td>").html(getTimeHtml(item["weekDay-"+i])).appendTo(tr);
			  }
			  table.append(tr);
		  });
	  }
	  
	  function getTimeHtml(arr){
		  var html = "";
		  $.each(arr,function(i,item){
			  var tempArr = item.split(",");
			  html+="<div>"+isplan(tempArr[2])+"<a href='javascript:void(0);' onclick='popForTime(\""+tempArr[1]+"\")'>"+tempArr[0]+"</a></div>";
		  });
		  return html;
	  }
	  
	  function popForRoom(djId){
		  DialogUtil.open("会议室安排情况","${ctx}/oa/oaMeetinfo!generalOptView.do?nodeInstId=0&hideReturnBtn=T&showTag=F&djid="+djId,"80%","90%");
	  }
	  function popForTime(djId){
	      DialogUtil.open("会议申请明细","${ctx}/oa/oaMeetApply!view.do?viewtype=T&djId="+djId,"70%","80%");
	  }
	   /**
	   * 根据当前时间和当前的周几获取本周的起始日期，周一算本周第一天
	   */
	   function getBeginDate(strDate,weekDay){
		  var temp = weekDay.substring(1);
		  weekDay = parseInt(temp);
		  return getDate(new Date(strDate),1-weekDay)
	   }
	   /**
		* 根据当前时间和当前的周几获取本周的结束日期，周日算本周最后一天
		*/
	   function getEndDate(strDate,weekDay){
		   var temp = weekDay.substring(1);
			  weekDay = parseInt(temp);
			  return getDate(new Date(strDate),7-weekDay);
	   }
	   function getDate(nowDate,diffDay){
		 var time = nowDate.getTime();
		 time+=diffDay*24*3600000;
		 nowDate.setTime(time);
		 return dateFormat(nowDate,'yyyy-MM-dd');
		 }
    /**
	  *根据日期获取当前是第几周
	  */
	  function getWeeks(year, mouth, day) {
		  var day1 = new Date(year, mouth-1, day);
		  var day2 = new Date(year, 0, 1);

		  var firstweek = day2.getDay();//1月1日是星期几
		  if(firstweek == 0) {
		  firstweek = 6;
		  }else {
		  firstweek = firstweek - 1;
		  }//转化为0表示星期一,6表示星期日
		  firstweek = (7 - firstweek) % 7;//计算1月1日离第一周的天数
		  var day3 = new Date(year, 0, 1+firstweek)
		  var result = Math.round((day1.getTime() - day3.getTime())/(1000*60*60*24));
		  result = Math.floor(result / 7)+1;//这个地方应该用floor返回最小次数然后+1
		  return result;
     };
	/**
	 *对Date的扩展，将 Date 转化为指定格式的String 
	 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	 * 例子： 
   */
	function dateFormat(date,fmt){ //author: meizz 
	  var o = { 
	    "M+" : date.getMonth()+1,                 //月份 
	    "d+" : date.getDate(),                    //日 
	    "h+" : date.getHours(),                   //小时 
	    "m+" : date.getMinutes(),                 //分 
	    "s+" : date.getSeconds(),                 //秒 
	    "q+" : Math.floor((date.getMonth()+3)/3), //季度 
	    "S"  : date.getMilliseconds()             //毫秒 
	  }; 
	  if(/(y+)/.test(fmt)) 
	    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o) 
	    if(new RegExp("("+ k +")").test(fmt)) 
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  return fmt; 
	}
	function goApply(){
		window.location.href="${ctx}/oa/oaMeetApply!built.do";
	}
	function isplan(state){
		if($("#from").val()=="doMeeting"){
			if(state=="1"){
				return "<span class='sqz_from' ></span>";
			}else if(state=="2"){
				return "<span class='yap_from' ></span>";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	 </script>
	</head>
    <body>
         <div class="container">
            <h5 style="color:#004492">
           <c:if test="${empty from }"> 
           		 会议室申请
           </c:if>
            <c:if test="${not empty from }"> 
           		 安排记录
           </c:if>
           		 
           		 </h5>
            <c:if test="${empty from }">
            <div class="searchArea">
                <input type="button" value="申请" onclick="goApply()"style="height:25px;vertical-align: middle;background:url(${ctx}/themes/blue/images/whiteBtnWide.png)#fff;width:75px;margin:0 10px;"/>
           
              <label style="height:25px;display:inline-block;line-height:25px;vertical-align: middle;color:#fff">申请日期：</label>
                  <input type="text" class="Wdate" id="showDate" style="width:250px;height:25px;line-height:25px;border:1px solid #ccc;vertical-align: middle;" onclick="datePickerClick()"/> 
                
                  
                  <a style="cursor:pointer; display:inline-block;background:url(${ctxStatic}/image/searchBtn.png) 0 2px no-repeat;width:45px;height:28px;vertical-align: middle;" 
                        href="javascript:void(0)" onclick="startLoadData()"></a>
                 
            </div>
             </c:if>
             <input type="hidden" id="dateHidden"/>
              <input type="hidden" id="beginDate"/>
                  <input type="hidden" id="endDate"/>
                  <input type="hidden" id="from" value="${from}" />
            <div class="dateControl"><span class="left" onclick="turnPrevTerm()"></span><span id="monitor"></span><span class="right" onclick="turnNextTrem()"></span></div>
            <div>
				<span id="span_calendar_instance">
					<c:forEach var="value" items="${from }">
						<label style="display: inline;">
							<ul class="themeList" id="themeList">
								<li>颜色说明： </li>
								<li >申请:</li>
								<li  style=" background-color: #fdfd95; width: 15px;height: 15px; "></li>
								<li >安排:</li>
								<li  style=" background-color: #9fda9f; width: 15px;height: 15px; "></li>
                   			</ul>
						</label>
					</c:forEach>
				</span>
			</div>
            <div class="dataArea">
               <table style="width:100%;border-collapse:collapse;">
                  <tr>
                     <th style="width:12.5%;height:30px;background:#fafafa;color:#888"></th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期一</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期二</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期三</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期四</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期五</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期六</th>
                     <th style="width:12.5%;background:#fafafa;color:#888">星期日</th>
                  </tr>
               </table>
            </div>
         </div>  
    </body>
</html>
