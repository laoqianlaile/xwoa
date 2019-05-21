
//创建窗口，返回窗口对象
function createInnerWindow(){
	  if(arguments.length<3) return null;
	  var newWin;
	  if(arguments.length<6){
			newWin = new innerWindow(arguments[0],arguments[1],
		                             arguments.length<4?"400":arguments[3],
		                             arguments.length<5?"300":arguments[4]);
		                             
		}
		else{
			newWin = new innerWindow(arguments[0],arguments[1],arguments[3],arguments[4],
		                             arguments[5]);
		 
		}
		if(!newWin.create()) newWin.show();
		//设置关闭窗口的按钮
		var closeBtn = arguments[2];
		var intervalId;
		if(closeBtn != "")
		 intervalId = window.setInterval(function(){
			var result = true;			
			result = newWin.setCloseButton(closeBtn);
			if(result) clearInterval(intervalId);      
		},100);
		return newWin;                             
}

//innerWindow构造函数
function innerWindow(){
	        if(arguments.length<2) return null;
	        //窗口名称
    			this.winName = arguments[0];
    			//url
    			this.winUrl = arguments[1];
    			//窗口高度和宽度
    			this.winWidth= arguments.length <3?"400px":parseInt(arguments[2])+"px";
    			this.winHeight= arguments.length <4?"300px":parseInt(arguments[3])+"px";
    			//窗口位置
    			this.winTop = arguments.length <5?(document.body.offsetHeight-parseInt(this.winHeight))/2+"px":arguments[4].split(",")[0]+"px";
    			this.winLeft = arguments.length <5?(document.body.offsetWidth-parseInt(this.winWidth))/2+"px":arguments[4].split(",")[1]+"px";
    			
    			this.winBody;
    			this.statubar;
    			
				}

        //创建窗体
				innerWindow.prototype.create = function(){	
								
					if(document.getElementById(this.winName+"_win_div") != null){
				      document.frames(this.winName+"_url_content").location.href=this.winUrl;
              this.winBody =  document.frames(this.winName+"_url_content");
              this.statuBar = document.getElementById(this.winName+"_status_div");
						  return false;
					}
					
					
    			var windiv = document.createElement("<div style='width:"+this.winWidth+";height:"+this.winHeight+"'></div>");
			    windiv.id = this.winName+"_win_div";
			    windiv.style.display = "block";//在创建时设置可见
			    windiv.style.top=this.winTop;
			    windiv.style.left=this.winLeft;		    			    
			    windiv.style.backgroundColor = "white";//背静色
			    windiv.style.border = "1px solid #0855DD";//背静边框宽度5,实线,颜色#0855DD
			    windiv.style.position = "absolute";//脱离DOM对象的层次关系
			    windiv.style.zIndex = 100; //设置窗口z序

			    
          //创建headdiv---win
          var headdiv = document.createElement("<div style='width:100%;height:20px'></div>");			    
			    headdiv.id = this.winName+"_head_div";    			    
			    headdiv.style.backgroundColor = "#0055E5";//背静色
			    headdiv.style.borderLeft = "1px solid #2B90FF";//背静边框宽度1,实线,颜色#2B90FF
			    headdiv.style.borderRight = "1px solid #2B90FF";//背静边框宽度1,实线,颜色#2B90FF
			    headdiv.style.borderTop = "1px solid #2B90FF";//背静边框宽度1,实线,颜色#2B90FF
			    windiv.appendChild(headdiv);			    			    
			     
			    //创建bodydiv ---win
			    var bodyHeight = parseInt(this.winHeight)-20 +"px"
			    var bodydiv = document.createElement("<div style='width:100%;height:"+bodyHeight+"'></div>");
			    bodydiv.id = this.winName+"_body_div";    			    
			    bodydiv.style.backgroundColor = "white";//背静色
			    bodydiv.style.border = "2px solid #0855DD";//背静边框宽度2,实线,颜色#0855DD
			    windiv.appendChild(bodydiv);
			    
			    //创建按钮群-head
			    var headContent = document.createElement("TABLE");
          headContent.style.width="100%";
          headContent.style.Height="20px"			    
			    headContent.border=0;
			    headContent.CellSpacing = 0;
			    headContent.cellPadding = 0;
			    headContent.insertRow();
			    headContent.rows[0].insertCell();//创建左上角图标
			    var winImage = document.createElement("IMG");
			    winImage.src="images/winImage.gif";
			    headContent.rows[0].cells[0].appendChild(winImage);
			    headContent.rows[0].cells[0].width="20px";
			    headContent.rows[0].insertCell();//创建capiton栏
			    headContent.rows[0].cells[1].width="100%";  			 		  
			    headContent.rows[0].cells[1].style.cursor="default";
			    headContent.rows[0].cells[1].style.color="white";
			    headContent.rows[0].cells[1].attachEvent("onmousedown",this.event_dragInit);//设置拖拽事件
			    headContent.rows[0].cells[1].attachEvent("onmouseup",this.event_dragInit);//设置拖拽事件
			    headContent.rows[0].cells[1].attachEvent("onmousemove",this.event_drag);//设置拖拽事件     
			    headContent.rows[0].insertCell();//创建按钮容器
			    var closeBtn =  createPicButton(headContent.rows[0].cells[2],this.winName+"_close_btn","","images/button_up.gif");//创建按钮    																
			    closeBtn.bindClick(this.event_close);
			    headdiv.appendChild(headContent);
			    
			    //创建iframe ---body
			    var urlContent = document.createElement("iframe");
			    urlContent.id = this.winName+"_url_content";
			    urlContent.style.width="100%";
			    urlContent.style.height="100%";
			    urlContent.style.overflowX = "auto";
			    urlContent.border="0";		    
			    bodydiv.appendChild(urlContent);
			    
			    //创建statusdiv ---body
			    var stattusdiv = document.createElement("<div style='width:100%;height:15px'></div>");
			    stattusdiv.id = this.winName+"_status_div";   			    
			    stattusdiv.style.backgroundColor = "#ECE9D8";//背静色
			    stattusdiv.style.borderTop = "1px solid #BFBDB0";//背静边框宽度5,实线,颜色#0855DD
			    bodydiv.appendChild(stattusdiv);          
			   
			    
          document.body.appendChild(windiv);//将窗体加入document
          document.frames(this.winName+"_url_content").location.href=this.winUrl;//刷新iframe
          
          this.winBody =  document.frames(this.winName+"_url_content");
          this.statuBar = document.getElementById(this.winName+"_status_div");
          
	  		}

        //显示窗口
				innerWindow.prototype.show = function(){
					document.getElementById(this.winName+"_win_div").style.display = "block";
					
				}
				
				//隐藏窗口
				innerWindow.prototype.hideWindow = function(){
					document.getElementById(this.winName+"_win_div").style.display = "none";
					
				}
				
				//设置urlContent中关闭窗口的按钮
				innerWindow.prototype.setCloseButton = function(){
					var closeButtonName = arguments[0];
					var closeButton;
				  closeButton = this.winBody.document.getElementById(closeButtonName);
				  if(closeButton != null){
				  	 var winName = this.winName;
				  	 var event_closeByOther = function(winName){
				  	 	       alert(winName);
				  	         document.getElementById(winName+"_win_div").style.display = "none";
				  	     }
				  	 closeButton.attachEvent("onclick",this.event_closeByOther);
             closeButton.title=this.winName;
             return true;
          }				
          else return false;
				}
				
				//设置标题
				innerWindow.prototype.setCaption = function(){
					var caption = arguments[0];
					var head = document.getElementById(this.winName+"_head_div").childNodes[0];
					var captionContent = head.rows[0].cells[1];
					captionContent.innerText = " "+caption;
				}
				
				innerWindow.prototype.Sleep = function(){ //让对象暂停..不过貌似整个窗体都被停住了，鸡肋函数-.-
          var iMinSecond = arguments[0];
				  if(window.eventList==null)  
  				window.eventList=new Array();  //如果window事件列表为空创建新的事件列表
  				var idx=-1; 
  				for (var i=0;i<window.eventList.length;i++) 
  				{   
     				if (window.eventList[i]==null)  
     				{  
       				window.eventList[i]=this;    //寻找空的事件节点，装入自己
       				idx=i;   
       				break;   
     				}  
  				}  
  				if (idx==-1) 
  				{   
    				idx=window.eventList.length;   
    				window.eventList[idx]=this;//如果没有空的事件节点，添加新的事件节点并装入自己 
  				}  
   				setTimeout(function(){window.eventList[idx]=null;},iMinSecond); //在事件节点中释放自己
				} 
				
				//内部关闭窗口[事件]
				innerWindow.prototype.event_close = function(){
          var oEvent = arguments[0];
          oEvent.cancelBubble = true;
          var close_btn = oEvent.srcElement;
          var btnId = close_btn.id;
          var winName = btnId.split("_")[0];
					document.getElementById(winName+"_win_div").style.display = "none";
				}
				
				//外部关闭窗口[事件]
				innerWindow.prototype.event_closeByOther = function(){ 
          var oEvent = arguments[0];
          oEvent.cancelBubble = true;
          var close_btn = oEvent.srcElement;
          var btnTitle = close_btn.title;  
					document.getElementById(btnTitle+"_win_div").style.display = "none";
					
				}
				
				//拖拽初始化[事件]
				innerWindow.prototype.event_dragInit = function(){//捕捉mousedown和mouseup事件
				  var oEvent = arguments[0];
				  oEvent.cancelBubble = true;
				  var eventElement = oEvent.srcElement;
				  var offsetX = oEvent.offsetX;
				  var offsetY = oEvent.offsetY;
				  var windiv = eventElement;
				  do{
				  	  offsetX = offsetX + windiv.offsetLeft;
				  	  offsetY = offsetY + windiv.offsetTop;
				      windiv = windiv.parentElement;
				  }while(windiv.parentElement.tagName != "BODY");
				  var eventType = oEvent.type;
				  
				  if(eventType == "mousedown"){
				  	windiv.title = offsetX+","+offsetY;
				  }else if(eventType == "mouseup"){
				    windiv.title = "";
				  }	
				}
				
				//拖拽[事件]
				innerWindow.prototype.event_drag = function(){//mousemove事件处理拖拽
				  var oEvent = arguments[0];
				  oEvent.cancelBubble = true;
				  var eventElement = oEvent.srcElement;
				  var windiv = eventElement;
				  do{
				      windiv = windiv.parentElement;
				  }while(windiv.parentElement.tagName != "BODY");
				  var buttonStatus = oEvent.button;
				  if(buttonStatus == 1){//鼠标左键按下
				  	var mouseOffset =windiv.title.split(",");//获取鼠标偏移量
				  	windiv.style.left = (oEvent.clientX-mouseOffset[0])+"px";
				  	windiv.style.top = (oEvent.clientY-mouseOffset[1])+"px";
				  	
				  }				     
				}
				
//-----------------------------------------------按钮--------------------------------------------------------------------

function createPicButton(){
	  if(arguments.length<4) return null;
	  var picbtn;
	  switch(arguments.length){
    	case 4:
    		picbtn = new picButton(arguments[0],arguments[1],arguments[2],arguments[3]);
    		break;
    	case 5:
    	  picbtn = new picButton(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
    		break;
    	case 6:
    	  picbtn = new picButton(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);
    		break;
    }
    picbtn.create();
    return picbtn;
}

function picButton(){
	  //鼠标停留，图片后缀_on,鼠标抬起,图片后缀_up,鼠标按下，图片后缀_down
    this.parentTag = arguments[0];
    this.btnId = arguments[1];    
    this.btnValue = arguments[2];
    this.pictureUp = arguments[3];  
    this.btnHeight = arguments.length<5?"20px":parseInt(arguments[4])+"px";
    this.btnWidth = arguments.length<6?"20px":parseInt(arguments[5])+"px";
}

			picButton.prototype.create = function(){
          var btn = document.createElement("IMG");
          btn.id = this.btnId;
          btn.value=this.btnValue;
          btn.style.width=this.btnWidth;
          btn.style.Height=this.btnHeight;
          btn.title = this.btnValue;
          btn.src = this.pictureUp;
          
          btn.attachEvent("onmousedown",this.event_btnEvents);
          btn.attachEvent("onmouseup",this.event_btnEvents);
          btn.attachEvent("onmouseenter",this.event_btnEvents);
          btn.attachEvent("onmouseleave",this.event_btnEvents);

          this.parentTag.appendChild(btn);
			}
									
			picButton.prototype.event_btnEvents = function(){
			    var oEvent = arguments[0];
			    var srcBtn = oEvent.srcElement;
			    var eventType = oEvent.type;
			    var bkImage = srcBtn.src;
			    
			    if(bkImage == undefined) return;
			    
			    if(eventType == "mouseenter"||eventType == "mouseup"){//on
			    	  if(bkImage.indexOf("_up") >0) bkImage = bkImage.replace("_up","_on");
			    	  if(bkImage.indexOf("_down") >0) bkImage = bkImage.replace("_down","_on");
			    }
			    if(eventType == "mouseleave"){//up
			    	if(bkImage.indexOf("_on") >0) bkImage = bkImage.replace("_on","_up");
			    	if(bkImage.indexOf("_down") >0) bkImage = bkImage.replace("_down","_up");
			    }
			    if(eventType == "mousedown"){//down
			    	if(bkImage.indexOf("_up") >0) bkImage = bkImage.replace("_up","_down");
			    	if(bkImage.indexOf("_on") >0) bkImage = bkImage.replace("_on","_down");
			    }
			    
			    srcBtn.src = bkImage;
			}
			
			picButton.prototype.getButton = function(){
			    var objs = this.parentTag.childNodes;
			    for(var i=0;i<objs.length;i++){
			    	if(objs[i].id == this.btnId)
			    		return objs[i];
			    } 
			}
			
			picButton.prototype.changeImage = function(){
			    if(arguments.length<1) return;
			    var ImageName = arguments[0];
			    if(ImageName != "") this.getButton().src = ImageName;
			}
			
			picButton.prototype.bindClick = function(){
			    if(arguments.length<1) return;
			    var bindFunc = arguments[0];
			    this.getButton().attachEvent("onclick",bindFunc); 
			}
			
			picButton.prototype.Enabled = function(){
				  var flag = true;
			    if(arguments.length == 1) flag = arguments[0];
			    this.getButton().disabled = !flag;
			    if(flag){ 
			    	  this.getButton().style.filter = "";
			    }
			    else{
			    	  this.getButton().style.filter = "gray";
			    }
			}
				
				
				
				
				
				
				
				
				
				