<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script	src="${pageContext.request.contextPath}/scripts/jquery-ui/jquery.easyui.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/mycalendar.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/superslider/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
<div class="improveLeft">
    <table class="improveleft_wrapper">

        <tr>
            <td valign="top" class="XW_left">

                <div class="XWweather">
                    <div style="position:absolute;left: 20px;height: 30px;top: 0;text-align:left;width:210px">
                    <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=10" width="300" height="25" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" ></iframe>
                    </div>
                    <div style="margin-left:230px;text-align: left;position: relative;">
                       <c:if test="${not empty zytzList}">
                        <span style="color:red;display: inline-block;border-left:2px solid #ececec;position: absolute;top:15px;">&nbsp;通知公告：</span>
                        <span style="display:inline-block;position: absolute;left:75px;height:24px;overflow:hidden" id="importantInfo">
                              <ul>
                              <c:forEach var="info" items="${zytzList}">
                               <c:choose> 
                           <c:when test="${info.infoType=='news'}"><c:set var="menuType" value="NEWS_GGZY"/></c:when>
                           <c:when test="${info.infoType=='info'}"><c:set var="menuType" value="TZGG_GGZY"/></c:when>
                           <c:when test="${info.infoType=='bulletin'}"><c:set var="menuType" value="BULLETIN_VIEW"/></c:when>
                            <c:when test="${info.infoType=='activity'}"><c:set var="menuType" value="INFO_ACT"/></c:when>
                            <c:otherwise><c:set var="menuType" value=""/></c:otherwise>
                         </c:choose>
                              <li style="height:24px;line-height: 24px;"><a href="javascript:void(0);" onclick="openMenu(this,'wdtz','<%=request.getContextPath()%>/oa/oaInformation!view.do?no=${info.no}&flag=GGZY','${menuType}');return false;">${info.title}</a></li>
                              </c:forEach>
                             </ul>
                        </span>
                        </c:if>
                    </div>
                </div>

                 <div class="XWoption">
                    <a class="dbsx" href="javascript:void(0);" onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do');return false;">
                        <span class="mumber">${todoTaskNum}</span>
                        <span>待办事项</span>
                    </a>
                    <a class="tzgg" href="javascript:void(0);" onclick="openMenu(this,'wdtz','<%=request.getContextPath()%>/oa/oaInformation!mainlistFromDashboard.do');return false;">
                        <span class="mumber">${unReadBulletinNum}</span>
                        <span>通知公告</span>
                    </a>
                    <a class="xxtx" href="javascript:void(0);" onclick="openMenu(this,'wdtx','<%=request.getContextPath()%>/oa/oaRemindInformation!recipientList.do?s_bizType=0');return false;">
                        <span class="mumber">${unReadRemindNum}</span>
                        <span>消息提醒</span>
                    </a>
                    <a class="qbdb" href="javascript:void(0);" onclick="openMenu(this,'qbdb','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=QB');return false;">
                        <span class="mumber">${qbTaskNum}</span>
                        <span>签报待办</span>
                    </a>
                    <a class="sw" href="javascript:void(0);" onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemTypeName=SW');return false;"">
                        <span class="mumber">${swTaskNum}</span>
                        <span>收文</span>
                    </a>
                    <a class="fw" href="javascript:void(0);" onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do?s_itemTypeName=FW');return false;">
                        <span class="mumber">${fwTaskNum}</span>
                        <span>发文</span>
                    </a>
                </div>

                <div class="XWdbsx">
                    <h3 class="XWtitle">待办事项<a href="javascript:void(0);" onclick="openMenu(this,'dwbl','<%=request.getContextPath()%>/dispatchdoc/vuserTaskListOA!list.do');return false;">更多</a></h3>
                   <ul class="XWdbsx_ul">
                        <li onclick="xwtab(this)" data-item='dwbl'>
                            <a>待我办理<label style="position: absolute;top:2px;padding: 2px 4px;border-radius: 50%;font-size: 12px;color: white;background-color: #CC5D5D;">${numberMap.total}</label><span></span></a>
                            <div>
                                <table>
                                   
                                </table>
                            </div>
                        </li>
                        <li onclick="xwtab(this)" data-item='jjcq'>
                            <a>即将超期<label style="position: absolute;top:2px;padding: 2px 4px;border-radius: 50%;font-size: 12px;color: white;background-color: #CC5D5D;">${numberMap.jjcq}</label><span></span></a>
                            <div>
                                <table>
                                  
                                </table>
                            </div>
                        </li>
                        <li onclick="xwtab(this)" data-item='yjcq'>
                            <a>已超期<label style="position: absolute;top:2px;padding: 2px 4px;border-radius: 50%;font-size: 12px;color: white;background-color: #CC5D5D;">${numberMap.ycq}</label><span></span></a>
                            <div>
                                <table>
                                  
                                </table>
                            </div>
                        </li>
                        <li onclick="xwtab(this)" data-item='cqwb'>
                            <a>长期未办理<label style="position: absolute;top:2px;padding: 2px 4px;border-radius: 50%;font-size: 12px;color: white;background-color: #CC5D5D;">${numberMap.cqwbl}</label></a>
                            <div>
                                <table>
                                
                                </table>
                            </div>
                        </li>
                    </ul>
                </div>
            </td>
            <td valign="top" class="XWright">

                <div class="XWseach">
                    	<input type="text" placeholder="请输入查询内容" id="search-text-box"/>
                     	<a  style="cursor: pointer;" onclick="gaoji()"></a>
                   		<!--  <a class="XWseach_btn"></a> -->
                </div>
                <div class="XWbanner">
                    <c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
                     	<a class="XWbanner_fwdj" style="cursor: pointer;" onclick="openMenu(this,'fwdj','${pageContext.request.contextPath}/powerbase/supPower!documentList.do?s_itemType=FW');return false;">发文登记</a>
                    </c:if>   
               	    <c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
               	     	<a class="XWbanner_swdj" style="cursor: pointer;" onclick="openMenu(this,'sqdj','${pageContext.request.contextPath}/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1');return false;">内部事务</a>
               		</c:if>  
               		<c:if test="${cp:HASOPTPOWER('QBDJ') }">  
                    	<a class="XWbanner_qbdj" style="cursor: pointer;" onclick="openMenu(this,'qbdj','${pageContext.request.contextPath}/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard');return false;">签报登记</a>
                	</c:if>   
                	<c:if test="${cp:HASOPTPOWER('DFSTX') }">
                    	<a class="XWbanner_wdyj" style="cursor: pointer;" onclick="openMenu(this,'fqtx','${pageContext.request.contextPath}/oa/oaRemindInformation!built.do?dashboard=dashboard');return false;">发起提醒</a>
                	</c:if> 
                		<a class="XWbanner_zkqb" id="expendall" style="cursor: pointer;">展开全部</a>
                </div>
               
                <div class="XWjwbl" style="overflow:hidden">
                    <h5 class="XWtitle" onclick="xwopen(this)">经我办理<a href="javascript:void(0);" onclick="openMenu(this,'jwbl','<%=request.getContextPath()%>/oa/vOptBaseList!list.do');return false;">更多</a></h5>
                    <ul>
                      <c:forEach var="mycase" items="${casesHandleBySelf}">
                        <li title="${mycase.transaffairname}">
                          <a href="javascript:void(0);" onclick="openMenu(this,'jwbl','${pageContext.request.contextPath}/${mycase.itemType}!generalOptView.do?djId=${mycase.djId}&nodeInstId=0&applyItemType=&dashboard=T');return false;">${mycase.transaffairname}</a>
                        </li>
                       </c:forEach>
                       <c:if test="${empty casesHandleBySelf}">
                         <div>无记录</div>
                       </c:if>
                    </ul>
                </div>
                <div class="XWhd" style="overflow:hidden">
                    <h5 class="XWtitle" onclick="xwopen(this)">活动<a href="javascript:void(0);" onclick="openMenu(this,'fhd','<%=request.getContextPath()%>/oa/oaInformation!mainlist.do?infoType=activity');return false;">更多</a></h5>
                    <ul>
                       <c:forEach var="info" items="${activityList}">
                        <li title="${info.title}"><a href="javascript:void(0);" onclick="openMenu(this,'fhd','<%=request.getContextPath()%>/oa/oaInformation!view.do?no=${info.no}&flag=GGZY','INFO_ACT');return false;">${info.title}</a></li>
                        </c:forEach>
                        <c:if test="${empty activityList}">
                         <div>无记录</div>
                      </c:if>
                    </ul>
                </div>
                <div class="XWcalendar" style="margin-top:20px;">
                	<div  style="height:300px;width:300px">
                		<div id="Calendar" class="easyui-calendar" fit='true'></div>
                	</div>
                </div>
            </td>
        </tr>

    </table>
</div>
<div class="work-desk">
      <div class="desk-row">
         <ul>
           <c:if test="${cp:HASOPTPOWER('FWGLFWDJ') }">
           		<li><a onclick="workdeskForward(this,'fwdj','${pageContext.request.contextPath}/powerbase/supPower!documentList.do?s_itemType=FW');return false;"><i class='desk-itemico-fwdj'></i><span>发文登记</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('NBSXSXDJ') }">
           <li><a onclick="workdeskForward(this,'sqdj','${pageContext.request.contextPath}/powerbase/supPower!sqList.do?s_itemType=SQ&s_isNetwork=1');return false;"><i class='desk-itemico-sqdj'></i><span>内部事务</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('QBDJ') }">
           <li><a onclick="workdeskForward(this,'qbdj','${pageContext.request.contextPath}/powerruntime/optApply!editDoOrRead.do?optId=OA_OPT&itemId=QB-001&isapplyuser=F&flag=QB&s_itemtype=QB&show_type=F&orgcode=1&dashboard=dashboard');return false;"><i class="desk-itemico-qbdj"></i><span>签报登记</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('DFSTX') }">
           <li><a onclick="workdeskForward(this,'fqtx','${pageContext.request.contextPath}/oa/oaRemindInformation!built.do?dashboard=dashboard');return false;"><i class="desk-itemico-fqtx"></i><span>发起提醒</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('SJX') }">
           <li><a onclick="workdeskForward(this,'fsyj','${pageContext.request.contextPath}/oa/innermsg!editDraft.do?s_msgtype=P&s_mailtype=I&mailtype=I&dashboard=dashboard');return false;"><i class="desk-itemico-fsyj"></i><span>发送邮件</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('GRBGRCAP') }">
           <li><a onclick="workdeskForward(this,'grrc','${pageContext.request.contextPath}/oa/oaSchedule!built.do?s_sehType=1&dashboard=dashboard');return false;"><i class="desk-itemico-grrc"></i><span>个人日程</span></a></li>
           </c:if>
           <c:if test="${cp:HASOPTPOWER('MESSAGEGL') }">
           <li><a onclick="workdeskForward(this,'fdx','${pageContext.request.contextPath}/oa/oaSmssend!list.do?');return false;"><i class="desk-itemico-fdx"></i><span>发短信</span></a></li>
           </c:if>
         </ul>
      </div>
      <div class="desk-row">
         <ul>
         <c:if test="${cp:HASOPTPOWER('RCBGGWSQ') }">
           <li><a onclick="workdeskForward(this,'gwsq','${pageContext.request.contextPath}/sampleflow/sampleFlowRelegate!list.do?grant=T&s_grant=T');return false;"><i class="desk-itemico-gwsq"></i><span>公务授权</span></a></li>
         </c:if> 
         <c:if test="${cp:HASOPTPOWER('SWGLSWDJ') }">
           <li><a onclick="workdeskForward(this,'swdj','${pageContext.request.contextPath}/powerbase/supPower!documentList.do?s_itemType=SW');return false;"><i class="desk-itemico-swdj"></i><span>收文登记</span></a></li>
         </c:if> 
         <c:if test="${cp:CHECKUSEROPTPOWER('GGZY','edit')}">
           <li><a onclick="workdeskForward(this,'ftz','${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=info&flag=GGZY');return false;"><i class="desk-itemico-ftz"></i><span>发通知</span></a></li>
           <li><a onclick="workdeskForward(this,'fgg','${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=bulletin&flag=GGZY');return false;"><i class="desk-itemico-fgg"></i><span>发公告</span></a></li>
           <li><a onclick="workdeskForward(this,'fhd','${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=activity&flag=GGZY');return false;"><i class="desk-itemico-fhd"></i><span>发活动</span></a></li>
         </c:if>  
         <c:if test="${cp:HASOPTPOWER('GWLZXGGN') }">
           <li><a onclick="workdeskForward(this,'gwgd','${pageContext.request.contextPath}/oa/voaUnitArchiveIncomedoc!list.do');return false;"><i class="desk-itemico-gwgd"></i><span>公文归档</span></a></li>
         </c:if>
         </ul>
      </div>
</div>
<script type="text/javascript">
$(function(){
	//重要通知滚动
	if($("#importantInfo").length > 0){
		$("#importantInfo").slide({mainCell:"ul",autoPage:true,effect:"top",autoPlay:true});
	}
	///消息提醒\经我办理\通知公告,标题详情查看
	$('.XWul>li').click(function(){
		var _class=$(this).hasClass('XWli_select');
		var obj_siblings=$(this).siblings('li');
		if(_class){
			$(this).removeClass('XWli_select');
		}
		else{
			obj_siblings.removeClass('XWli_select');
			$(this).addClass('XWli_select');
		}
	});
	
    $('#Calendar').calendar({
        weeks:['日','一','二','三','四','五','六'],
        months:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        onSelect:function(date){
        	showMySchedule(date);
        }
    });
    showMySchedule();
  //待办事项tab切换到第一项
	$(".XWdbsx_ul").find("li:eq(0)").click();
   //展开全部
   $("#expendall").click(function(){
	   DialogUtil.openHtml("工作台",$(".work-desk")[0].outerHTML);   
   });
   //高级搜索
   $(".XWseach_btn").click(function(){
	   var widths=$(window).width()-500;
		var heights=$(window).height()-200;
		if($.trim($("#search-text-box").val()).length==0){
			DialogUtil.alert("请输入检索的关键字","提示");
			//DialogUtil.open("高级搜索",'${pageContext.request.contextPath}/oa/advsearch!goSearchIndex.do',widths,heights,null,null,null,'');
		}else{
			DialogUtil.open("高级搜索",'${pageContext.request.contextPath}/oa/advsearch!goSearchIndex.do',widths,heights,null,null,null,$("#search-text-box").val());	
		}
   });
});
    function workdeskForward(obj,tabid, url,type){
    	openMenu(obj,tabid, url,type);
    	DialogUtil.close();
    }
    
    function openSearchMenu(obj,tabid, url,type,highsearch){
    	var highsearch = $("#search-text-box").val();
    	
        $("#layout").removeClass("improveStyle");
    	var $this = $(obj);

    	$("#ul_YGJG>li>div>div.first_collapsable").removeClass("first_collapsable").addClass("first_expandable");
    	$("#ul_YGJG>li>div>div.collapsable").removeClass("collapsable").addClass("expandable");
    	navTab.closeAllTab();
    	
    	var title = $this.attr("title") || $this.text();
    	var tabName = $this.attr("rel") || "_blank";
    	var fresh = eval($this.attr("fresh") || "true");
    	url=url+"?highsearch="+highsearch;
    	navTab.openTab(tabName, url,{title:title, fresh:fresh, external:external});
    	
        if(tabid=='gjss'){
    		showSelectedMenu("YGJGGRBG", "GRBGFZBG", "GRBGGJSS", "高级搜索");
    		cancelAllSelectedOptions();
    		addSelectAttribute($('.tabBar li.to'));
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    function showMySchedule(date){
    	if(!date) date = new Date();
    	var strDate = date.formatDate('yyyy-MM-dd');
    	var bgcolor = {'会谈':"#EE0000",'事件':"#FFA633",'会议':"#F3CC19",'出差':"#57ADE8",'考察':"#5AE8E5",'休假':'#F2E0FF','其他':'#FEE0E4'};
    	$.ajax({
    		 url:"<%=request.getContextPath()%>/app/dashboard!showCalendar.do?date="+strDate+" 00:00:00",
	    		type:"post",
	    		dataType:"json",
	    		success:function(data){
	    			$('.Calendardata').remove();
	    			if(data && data.length >0){
	    				var ul = $("<ul>",{"class":"Calendardata"}).css("margin-bottom","20px");
	    				$.each(data,function(){
	    					var omittitle = this.title;
	    					if(omittitle.length > 13){
	    						omittitle = omittitle.substring(0,13)+"...";
	    					}
	    					var li = $("<li>",{title:this.title});
	    					
	    					var partHtml = '<div style="padding-top:5px;">'
	                	        +'<span style="display:inline-block;width:13px;height:13px;background:{bgcolor};margin-right:5px;margin-bottom:-1px;"></span>'
	                	        +'<span  style="display:inline-block;">'+this.itemType+'</span>'
	                	        +'</div>'
	                	        + '<div style="padding-top:5px;padding-left:20px;">'+omittitle+'<span style="float:right">'+this.begTime.substr(this.begTime.length-8,5)+'</span></div>';
	                	        partHtml = partHtml.replace("{bgcolor}",bgcolor[this.itemType]);
	                	    li.append(partHtml);
	                	    var schno = this.no;
	                	    li.click(function(){openMenu(this,'grrc','${pageContext.request.contextPath}/oa/oaSchedule!view.do?schno='+schno+'&ec_p=&ec_crd=&s_sehType=1&dashboard=dashboard');return false;});
	                	    ul.append(li);   
	    				});
	    				 $('.XWcalendar').append(ul);
	    			}
	    		}
    		 });
    }
    ///待办事项的tab切换功能
    function xwtab(th) {
        var obj_th=$(th);//获取当前点击的元素
        var obj_table=obj_th.find('div');
        if (obj_th.hasClass('select')){return;}
        else {
            obj_th.siblings('li').removeClass('select');
            obj_th.siblings('li').find('div').css('display','none');
            obj_th.addClass('select');
            
            var requestData = {};
            if(obj_th.data("item")=="jjcq"){//即将超期
            	requestData.s_NP_toBeExtended = true;
            }else if(obj_th.data("item")=="yjcq"){//已经超期
            	requestData.s_NP_hasExtended = true;
            }else if(obj_th.data("item")=="cqwb"){//长期未处理
            	requestData.s_NP_seriousExtended = true;
            }
            $.ajax({
            	url:"${pageContext.request.contextPath}/app/dashboard!sydb_new.do?acType=auto",
            	type:"post",
            	dataType:"json",
            	data:requestData,
            	success:function(datas){
            		obj_th.find("table").html("");
            		//数组的索引对应criticalLevel的值
            		
            		var templateData = [{color:"",text:"普通"},{color:"green",text:"平急"},{color:"#00ADEF",text:"加急"},
            		                    {color:"#FABC09",text:"急件"},{color:"#F1521B",text:"特急"},{color:"red",text:"特提"}];
            		var templateDataSQ = [{color:"",text:"普通"},{color:"green",text:"一般"},{color:"#00ADEF",text:"较急"},
            		                    {color:"#FABC09",text:"紧急"}];
            		
            		$.each(datas,function(){
            			var itemHtml = "<tr>";
            			var warningLevel = 0,warningLevelText = '',remainDays = this.remainingDays;
            			if(remainDays){
            				remainDays = parseInt(remainDays);
            				if(remainDays >= 0 && remainDays <= 3){//即将超期
            					warningLevel = 1;
            					warningLevelText = "即将超期";
                			}
                			if(remainDays < 0){//已经超期
                				warningLevel = 2;
                				warningLevelText = "已超期";
                			}
                            if(remainDays <= -30){//超期已久
                            	warningLevel = 3;
                            	warningLevelText = "长期未办理";
                			}
            			}else{//长期未办理不是通过remainDays筛选的，所以里面可能存在控制，当remainDays为空的时候，默认为长期未办理
            				warningLevel = 0;
                        	warningLevelText = "";
            			}
            			
            			//<td>收文<span class="tj">【特急】</span>关于印发《公路工程营业税改征增值税计价依据调整方案》的通知<span class="XWdbsx_date">12-08</span></td>
            			var templateHtml = "<td>"+this.itemtype+"<span style='color:{0}'>[{1}]</span>"+this.transaffairname
            			                +"<span class='warning_level_"+warningLevel+"'>"+warningLevelText+"</span>"
            			                +"<span class='XWdbsx_date'>"+formatTime(this.nodeCreateTime)+"</span>"
            			                +"<span class='XWdbsx_readstate_"+(this.readstate=='已读'?'T':'F')+"'>["+this.readstate+"]</span>"+"</td>";
            			
            			var index = parseInt(this.criticalLevel?this.criticalLevel:'0');
            			var text ="";
            			
            			if("0"!=this.criticalLevel&&this.criticalLevel.indexOf("0") >-1){//事权缓急程度 ：高中低
            				 text =templateDataSQ[index].text;
            			}else{//公文缓急程度 紧急。。。
            				 text =templateData[index].text;
            			}
            		
            			templateHtml = templateHtml.replace("{0}",templateData[index].color);
            			templateHtml = templateHtml.replace("{1}",text);
            			itemHtml += templateHtml;
            			itemHtml+="</tr>";
            			obj_th.find("table").append(itemHtml);
            			var that = this;
            			obj_th.find("table").find("tr:last").click(function(){
            				openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + that.nodeOptUrl+'%26dashboard%3DT' );	
            			});
            					
            		});
            		
            	}
            });
            
            obj_table.css('display','block');
        }
    }
    function formatTime(date){
		var times=date.split("-");
		var changetime=times[0]+"-"+times[1]+"-"+times[2].substring(0,2);
		return changetime;
	}
    ///(经我办理展\通知公告\消息提醒)展开或者收起列表
     function xwopen(th) {
        var obj_th=$(th).children("a");//获取当前点击的元素
        var obj_ul=obj_th.parent('h5').next('ul');//获取要展开或者收起的元素
        if(obj_th.hasClass('open')){//元素已经是展开状态，则收起
            obj_th.removeClass('open');
            obj_ul.slideUp(500);
        }
        else{//元素已经是收起状态，则展开
            obj_th.addClass('open');
            obj_ul.slideDown(500);
        }
    }
    //高级搜索，需要获取输入参数带到跳转页面去
    function gaoji(){
   	    var	title= $("#search-text-box").val();
    	openMenu('', 'gjss', '<%=request.getContextPath()%>/oa/advsearch!goSearchIndex.do?title='+title);
    }
</script>