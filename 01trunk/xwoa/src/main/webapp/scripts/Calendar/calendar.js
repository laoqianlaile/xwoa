
function Calendar(year,month,day){  
  
	//if(typeof year!="number"||typeof month !="number"){  
      //  throw new Error("�������Ϊ����");  
    //}  
        this.year=year;  
        this.month=month;  
        this.day=day;
       
        this.daysOfMonth=[31,0,31,30,31,30,31,31,30,31,30,31];
                
        Calendar.prototype.getDaysOfMonth=function(){  
            var value=this.daysOfMonth[this.month-1];  
            if(value==0){  
                if((this.year%4==0&&this.year%100!=0)||(this.year%400==0))  
                {  
                    value=29;  
                }  
                else  
                {  
                    value=28;  
                }  
            }  
            return value;  
        };
        //最后一天几号
        Calendar.prototype.getDaysOfLastMonth=function(){
        	var value=this.daysOfMonth[this.month-2];  
            if(value==0){  
                if((this.year%4==0&&this.yea%100!=0)||(this.yea%400==0))  
                {  
                    value=29;  
                }  
                else  
                {  
                    value=28;  
                }  
            }
            if(this.month-2==-1)
            	{
            	value=31;
            	}
            //alert(value);
        
            return value;  
        }; 
        Calendar.prototype.getYear=function(){
        	
        	return this.year;
        };
        //某个月第一天星期几
        Calendar.prototype.getDayBeginOfMonth=function(){  
            var dayofweak=new Date();  
            dayofweak.setYear(this.year);           
            dayofweak.setMonth(parseInt(this.month)-1);  
            dayofweak.setDate(1);
            var l=dayofweak.getDay();
            return l;  
        };
        //获得这一天是星期几
        Calendar.prototype.getDayOfWeek=function(){  
            var dayofweak=new Date();  
            dayofweak.setYear(this.year);  
            dayofweak.setMonth(this.month-1);  
            dayofweak.setDate(this.day); 
            var l=dayofweak.getDay();
            return l;  
        };  
        //莫个月最后一天星期几
        Calendar.prototype.getDayEndOfMonth=function(){  
            var dayofweak=new Date();  
            dayofweak.setYear(this.year);  
            dayofweak.setMonth(this.month-1);  
            dayofweak.setDate(this.getDaysOfMonth());  
            return dayofweak.getDay();  
        }; 
        Calendar.prototype.getLines=function(){
        	var h;
        	var days=this.getDaysOfMonth();
        	var firstDay=this.getDayBeginOfMonth(); 
        	if((days+firstDay)%7!=0){
            	h=(parseInt((days+firstDay)/7)+1)*7;
            	}
            else{
            	h=(parseInt((days+firstDay)/7)  )*7;
            }
           return h;
        };
        
        
        //��ȡ�������� 
        Calendar.prototype.getCalData=function(){  
            var days=this.getDaysOfMonth();
            //alert(days);//��ǰ�¶�����
            var daysOfLast=this.getDaysOfLastMonth();
           // alert(daysOfLast);//�ϸ��¶�����
            var firstDay=this.getDayBeginOfMonth(); 
            //alert(firstDay);//��ǰ�µĵ�һ�������ڼ�	
           // var lastDay=this.getDayEndOfMonth();  			//��ǰ�µ����һ�������ڼ�
            var data=new Array();		
           //��������	
            //alert(days+firstDay);
            var h=this.getLines();
            
           // alert(h);
            for(var i=0,j=0,m=0,n=0;i<h;i++){  
                if(i<firstDay){ 
                	//alert(daysOfLast);
                	
                    data[i]=daysOfLast-firstDay+1+m; 
                   // alert(data[i]);
                    
                    m++;
                }
                
                else if(i>=(days+firstDay)){
                	n++;
                	data[i]=n;
                }
                else{  
                    j++;  
                    data[i]=j;  
                }  
            }            
            return data;      
        };  
        Calendar.prototype.getbeginDate=function(){
        	var  begindate =null;
        	
        	return begindate;
        };
        Calendar.prototype.getendDate=function(){
        	var  enddate =null;
        	
        	return enddate;
        };
}

var cal;
var flag=null;      //��־λ
var day =null;		//��¼ÿ�ε���ʱ�����������±�

function drowCalendar(){
	var today= new Date();	
	cal=new Calendar(today.getFullYear(), today.getMonth()+1, today.getDate());
	
	for(var i=1;i<=25;i++){
		$("#year").append("<option value='"+(today.getFullYear()-i)+"'>"+(today.getFullYear()-i)+"</option>");
	}
	$("#year").append("<option value='"+today.getFullYear()+"' selected='selected' >"+today.getFullYear()+"</option>");
	for(var i=1;i<=25;i++){
		$("#year").append("<option value='"+(today.getFullYear()+i)+"'>"+(today.getFullYear()+i)+"</option>");
	}
	for(var i=1;i<=12;i++){
		if(i==(today.getMonth()+1))
		$("#month").append("<option value='"+i+"' selected='selected' >"+i+"</option>");
		else
		$("#month").append("<option value='"+i+"' >"+i+"</option>");
	}
	$("#calendarTable").append("<tr id='tr_1'></tr><tr id='tr_2'></tr> <tr id='tr_3'></tr> <tr id='tr_4'></tr><tr id='tr_5'></tr><tr id='tr_6'></tr>");
	if(cal.getLines()==5)
	{
	$("#tr_6").hide();
	}	
	drawTable(cal, today);
	var f=today.getDate()+cal.getDayBeginOfMonth()-1;
	$("#"+f+"").addClass('today');
}
function drawTable(cal){	
	var firstday=cal.getDayBeginOfMonth();
	var days=cal.getDaysOfMonth();
	var endday=cal.getDayEndOfMonth();
	var data=cal.getCalData();
	if(cal.getLines()==5)
		{
		$("#tr_6").hide();
		}
	else{
		$("#tr_6").show();
	}
	for(var i=0;i<data.length;i++){
		if(i<7){
			if(i<firstday){
			//alert(data[i]);
			$("#tr_1").append("<td id="+ i+" class='last_next_day' onclick='  '><p>"+data[i]+"</p></td>");
			}
			else{
				if(i==0||i==6){
					//alert(cal.getYear());
					//doajax(cal,i);
					$("#tr_1").append("<td id="+ i+"   onclick='clickDay("+i+");'  ondblclick='doajax("+i+")'><p style='color:#D23B0D; '>"+data[i]+"</p></td>");
				}
					else
			$("#tr_1").append("<td id="+ i+"  onclick='clickDay("+i+");' ondblclick='doajax("+i+")' ><p>"+data[i]+"</p></td>");	
			}
			
			
		}
		if(i>=7&&i<14){
			if(i==7||i==13)
				$("#tr_2").append("<td id="+ i+"  onclick='clickDay("+i+");'  ondblclick='doajax("+i+")'><p class='weekDay'>"+data[i]+"</p></td>");
			else
			$("#tr_2").append("<td id="+ i+" onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p>"+data[i]+"</p></td>");	
		}
		if(i>=14&&i<21){
			if(i==14||i==20)
				$("#tr_3").append("<td id="+ i+"   onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p class='weekDay'>"+data[i]+"</p></td>");
			else
			$("#tr_3").append("<td id="+ i+" onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p>"+data[i]+"</p></td>");	
		}
		if(i>=21&&i<28){
			if(i==21||i==27)
				$("#tr_4").append("<td id="+ i+"   onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p class='weekDay'>"+data[i]+"</p></td>");
			else
			$("#tr_4").append("<td id="+ i+" onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p>"+data[i]+"</p></td>");	
		}
		if(i>=28&&i<35){
		
			if(i>firstday+days-1)
				{
					$("#tr_5").append("<td id="+ i+" class='last_next_day' onclick='  '><p>"+data[i]+"</p></td>");
				}
			else{
				if(i==28||i==34)
					$("#tr_5").append("<td id="+ i+"   onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p class='weekDay'>"+data[i]+"</p></td>");
				else
				$("#tr_5").append("<td id="+ i+" onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p>"+data[i]+"</p></td>");
			}

		}
		if(i>=35){
			if(i>firstday+days-1)
			{
				$("#tr_6").append("<td id="+ i+"  class='last_next_day' onclick=' '><p>"+data[i]+"</p></td>");
			}
		else{
			if(i==35)
				$("#tr_6").append("<td id="+ i+"  onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p class='weekDay'>"+data[i]+"</p></td>");
			else
			$("#tr_6").append("<td id="+ i+" onclick='clickDay("+i+");' ondblclick='doajax("+i+")'><p>"+data[i]+"</p></td>");
		}
		}		
	}	
	getDayArray(getBeginDate(firstday,data),getEndDate(endday,data));
	for(var i=firstday;i<firstday+days;i++){
		for(var m=0;m<arrayA.length;m++){
			if(arrayA[m]==data[i])
				$("#"+i+"").addClass("ruleHoliday");
		}
		for(var m=0;m<arrayB.length;m++){
			if(arrayB[m]==data[i])
				$("#"+i+"").addClass("overtime");
		}
	}
	for(var i=0;i<firstday;i++){
		for(var m=0;m<arrayAout.length;m++){
			if(arrayAout[m]==data[i])
				$("#"+i+"").addClass("ruleHoliday");
		}
		for(var m=0;m<arrayBout.length;m++){
			if(arrayBout[m]==data[i])
				$("#"+i+"").addClass("overtime");
		}
	}
	for(var i=firstday+days;i<data.length;i++){
		//alert(data.length);
		for(var m=0;m<arrayAout.length;m++){
			if(arrayAout[m]==data[i])
				$("#"+i+"").addClass("ruleHoliday");
		}
		for(var m=0;m<arrayBout.length;m++){
			if(arrayBout[m]==data[i])
				$("#"+i+"").addClass("overtime");
		}
	}
	clearArray();
}
//获得数组第一个的日期
function getBeginDate(firstday,data){
	var beginDate;
	if(firstday!=0){
		var month=parseInt($("#month").attr("value"))-1;
		var newyear=parseInt($("#year").attr("value"));
		if(month==0){
			month=12;
			newyear = newyear-1;
		}
		beginDate= newyear+"-"+month+"-"+data[0];
	}
	else{
		beginDate=$("#year").attr("value")+"-"+$("#month").attr("value")+"-"+data[0];
	}
	//alert("beginDate:"+beginDate);
	return beginDate;
}

function getEndDate(endday,data){
	var endDate;
	if(endday!=6){
		var month=parseInt($("#month").attr("value"))+1;
		var newyear=parseInt($("#year").attr("value"));
		if(month==13){
			month=1;
			newyear = newyear+1;
		}
	//	alert(month);
		endDate= newyear +"-"+month+"-"+data[data.length-1];
	}
	else{
		endDate=$("#year").attr("value")+"-"+$("#month").attr("value")+"-"+data[data.length-1];
	}
//	alert("endDate:"+endDate);
	return endDate;
}
var arrayA= new Array();   //放假的数组A
var arrayB= new Array();	//加班的数组B
var arrayAout= new Array();	//不在本月的A
var arrayBout= new Array();//不在本月的B

function clearArray(){
	arrayA.length=0;
	arrayB.length=0;
	arrayAout.length=0;
	arrayBout.length=0;
}
function getDayArray(beginDate,endDate){
	//alert(endDate);
	//alert(beginDate);
	//alert($("#month").attr("value"));
	$.ajax({
		type: 'POST',
		url:path+"/app/calendar!getDayArray.do",
		data:"beginDate="+beginDate+"&endDate="+endDate,
		dataType:"json",
		async:false,	
		success:function(data){
			if(data!=null){
			var m=0;
			var m1=0;
			var n=0;
			var n1=0;		
			for(var k in data ){
				var yue=jiexiYue(k);
				var riqi=jiexiRiqi(k);
				//alert(yue);
				//alert(riqi);
					
				if("A"==data[k]){
						if(yue==$("#month").attr("value")){
							//alert(riqi);
							arrayA[m]=riqi;
							m++;
						}
						else{
							arrayAout[m1]=riqi;							
							//alert("***"+arrayAout[m1]);
							m1++;
						}
				}
				else{
					if(yue==$("#month").attr("value")){
							arrayB[n]=riqi;
							n++;
						}
						else{
							arrayBout[n1]=riqi;
							n1++;
						}
					}
								
			}
			}

			
		},
		error:function(){
			alert("获取数据错误");
		}	
	});
		
}

function jiexiYue(datestr){
		return Number(datestr.substring(0,2));
	}
	
function jiexiRiqi(datestr){
	return Number(datestr.substring(2));
}




function clearTable(cal){
	for( var i=1;i<=cal.getLines();i++ ){
		$("#tr_"+i+"").empty();
	}
}

function getYear(oItem){	
	var year=oItem.value;
	var month=$("#month").attr("value");
	
	 cal= new Calendar(year,month);	
	for( var i=1;i<=6;i++ ){
		$("#tr_"+i+"").empty();
	}
	drawTable(cal);	
}

function getMonth(oItem){	
	var month=oItem.value;
	var year=$("#year").attr("value");
	cal= new Calendar(year,month);	
	for( var i=1;i<=6;i++ ){
		$("#tr_"+i+"").empty();
	}
	drawTable(cal);	
}

function doajax(i){	
	window.event.returnvalue=false;
	day=i;
	var date=$("#year").attr("value")+"-"+$("#month").attr("value")+"-"+(i-cal.getDayBeginOfMonth()+1);
	var selectDate=new Date();
	selectDate.setYear($("#year").attr("value"));
	selectDate.setMonth($("#month").attr("value")-1);
	selectDate.setDate(i-cal.getDayBeginOfMonth()+1);
	var d=selectDate.getDay();
	$("#workday").attr("value",date);
	if(d=="0"||d=="6"){
		$("#typeA").hide();
		$("#typeALable").hide();
		$("#typeB").show();
		$("#typeBLable").show();
		}
	else{		
		$("#typeB").hide();
		$("#typeBLable").hide();
		$("#typeA").show();
		$("#typeALable").show();
	}
		
		//	"<s:radio name='daytype' list='#{'C':'正常','A':'加班','B':'放假' }' onchange='isdesc(this);' ></s:radio>");
	$('#editDate').dialog('open');	
	return false;
}

function clickDay(i){
	if(flag!=null)
	$("#"+flag+"").removeClass('clickDay');	
	$("#"+i+"").addClass("clickDay");
	flag=i;	
}


function ajaxSave(form){
	var $form=$(form);
	//alert($form.attr("action"));
	$.ajax({
		type: 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(data){
			//alert(data.DayType);
			//alert(day);
			if(data.DayType=="A"){
				$("#"+day+"").addClass('ruleHoliday'); //设置放假的背景
			}
			if(data.DayType=="B"){
				$("#"+day+"").addClass('overtime');	//设置加班的背景
			}
			if(data.DayType=="C"){
				$("#"+day+"").removeClass('ruleHoliday');
				$("#"+day+"").removeClass('overtime');
			}
			$("input[type=radio]").each(function(){
				if($(this).attr("checked")) 
				{ 
				$(this).removeAttr("checked"); 
				} 
			
			});
			$('#editDate').dialog('close');	
			
		},
		error: function(){
			alert("保存失败");
		}
	});	
}
