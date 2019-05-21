//�����ѡ��
function isbrowser(){
	var userAgent = navigator.userAgent,
	    isOpera = userAgent.indexOf("Opera") > -1;
	
	if (userAgent.indexOf("Opera") > -1){ return "Opera"; }
	if (userAgent.indexOf("Firefox") > -1){ return "FF"; }
	if (userAgent.indexOf("Safari") > -1){ return "Safari"; }
	if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera){ return "IE"; }
}
var g = function(id){ return document.getElementById(id); }

var addEvent = function(o,eventType,fn){
	if(document.addEventListener){
  		o.addEventListener(eventType,fn,true);
 	}else if(document.attachEvent){
  		o.attachEvent('on'+eventType,fn);
 	}else{
  		o['on'+eventType] = fn;
 	}
}
var removeEvent = function(o,eventType,fn){
	if(document.removeEventListener){
  		document.removeEventListener(eventType,fn,true);
 	}else if(document.detachEvent){
  		o.detachEvent('on'+eventType,fn);
 	}
}

function drag(o){
	var defaultOptions = {
			moveObject:o
		},
		deltaX = 0, deltaY = 0, initX = 0, initY = 0, pointArray1 = new Array,pointArray2 = new Array,p1_1=new Array,p1_2=new Array,
		p2_1=new Array,p2_2=new Array,cirleX,cirleY,lineArray1=new Array,lineArray2=new Array,nPoint,
		action = {
			move:function(e){
				var e = e || window.event,
					MouseX = e.clientX,
					MouseY = e.clientY,
					o = defaultOptions.moveObject;
				switch(o.nodeName){
					case "rect":
						var text = o.getAttribute("textID");
						o.setAttribute("x",MouseX-deltaX);
						o.setAttribute("y",MouseY-deltaY);
						g(text).setAttribute("x",parseInt(o.getAttribute("x"))+5);
						g(text).setAttribute("y",parseInt(o.getAttribute("y"))+32);
						g(text).setAttribute("dx",parseInt(o.getAttribute("x"))+50);	
						//g(text).getElementsByTagName("tspan")[0].setAttribute("x",g(text).getAttribute("x"));
						break;
					case "ellipse":
						o.setAttribute("cx",MouseX-deltaX);
						o.setAttribute("cy",MouseY-deltaY);
						changePolylinePoint(g(o.getAttribute("lineID")),[MouseX-deltaX,MouseY-deltaY],nPoint);
						break;
					default:break;
				}
				if(o.getAttribute("from")){
					for(var i=0,len=lineArray1.length;i<len;i++){
						pointArray1[i][0] = (p1_1[i] + MouseX-initX) +","+ (p1_2[i] + MouseY-initY);
						
						g(lineArray1[i]).setAttribute("points",pointArray1[i].join(" "));
						g(lineArray1[i]).removeAttribute("marker-end");
					}
					
				}
				if(o.getAttribute("to")){
					for(var i=0,len=lineArray2.length;i<len;i++){
						pointArray2[i][pointArray2[i].length-1] = (p2_1[i] + MouseX-initX) +","+ (p2_2[i] + MouseY-initY);
						//g("getContent2").innerHTML = p2_1[i] + MouseX-initX;
						g(lineArray2[i]).setAttribute("points",pointArray2[i].join(" "));
						g(lineArray2[i]).removeAttribute("marker-end");
					}
					
				}
				
				if(e.stopPropagation){
					e.stopPropagation();
				}else{
					e.cancleBubble = true;
				}
			},
			stop:function(e){
				var e = e || window.event;
				removeEvent(document,"losecapture",action.stop);
				removeEvent(document,"mouseup",action.stop);
				removeEvent(document,"mousemove",action.move);
				if(document.releaseCapture) document.releaseCapture();
				if(o.nodeName=="rect"){
					if(o.getAttribute("from")){
						var line1 = o.getAttribute("from").split(",");
						for(var i=0,len=line1.length;i<len;i++){
							var clen = SVG.get(line1[i]).attr("points").split(" ").length;
							g(line1[i]).setAttribute("marker-end","url(#"+marker.attr("id")+")");
							if(clen<=2){
								var FP = formatLine(SVG.get(SVG.get(line1[i]).attr("from")),SVG.get(SVG.get(line1[i]).attr("to")));
								if(FP["P1"]){
									SVG.get(line1[i]).attr({points:FP["p1"][0]+","+FP["p1"][1]+" "+FP["p2"][0]+","+FP["p2"][1]});									
								}
							}else{
								
							}
						}
						
					}
					if(o.getAttribute("to")){
						var line2 = o.getAttribute("to").split(",");
						for(var i=0,len=line2.length;i<len;i++){
							var clen = SVG.get(line2[i]).attr("points").split(" ").length;
							g(line2[i]).setAttribute("marker-end","url(#"+marker.attr("id")+")");
							if(clen<=2){
								var FP = formatLine(SVG.get(SVG.get(line2[i]).attr("from")),SVG.get(SVG.get(line2[i]).attr("to")));
								if(FP["p1"]){
									SVG.get(line2[i]).attr({points:FP["p1"][0]+","+FP["p1"][1]+" "+FP["p2"][0]+","+FP["p2"][1]});									
								}
							}else{
								
							}
						}
					}
				}
				else{
					g(o.getAttribute("lineID")).setAttribute("marker-end","url(#"+marker.attr("id")+")");
				}
				if(e.stopPropagation){
					e.stopPropagation();
				}else{
					e.cancleBubble = true;
				}
			}	
		};
	function init(){
		var	o = defaultOptions.moveObject;
		//addEvent(o,"mousedown",function(e){
		o.onmousedown = function(e){
			if(flag){//flag ��ȫ�ֱ�
				var e = e || window.event;
				initX = e.clientX;
				initY = e.clientY;
				if(o.getAttribute("cx")){
					deltaX = e.clientX - o.getAttribute("cx");
					deltaY = e.clientY - o.getAttribute("cy");
					nPoint = getcurPoint(g(o.getAttribute("lineID")),[o.getAttribute("cx"),o.getAttribute("cy")]);
					//g("getContent2").innerHTML = nPoint;
				}else{
					deltaX = e.clientX - o.getAttribute("x");
					deltaY = e.clientY - o.getAttribute("y");
				}
				if(o.getAttribute("from") && o.getAttribute("from")!=""){
					lineArray1 = o.getAttribute("from").split(",");
					for(var i=0,len=lineArray1.length;i<len;i++){
						pointArray1[i] = g(lineArray1[i]).getAttribute("points").split(" ");
						var np = pointArray1[i][0].split(",");
						p1_1[i] = parseInt(np[0]);
						p1_2[i] = parseInt(np[1]);
					}
				}
				if(o.getAttribute("to") && o.getAttribute("to")!=""){
					lineArray2 = o.getAttribute("to").split(",");
					for(var i=0,len=lineArray2.length;i<len;i++){
						pointArray2[i] = g(lineArray2[i]).getAttribute("points").split(" ");
						var np = pointArray2[i][pointArray2[i].length-1].split(",");
						p2_1[i] = parseInt(np[0]);
						p2_2[i] = parseInt(np[1]);
					}
				}
				if(document.setCapture) o.setCapture();
				addEvent(document,"mousemove",action.move);
				addEvent(document,"mouseup",action.stop);
				addEvent(document,"losecapture",action.stop);
			}
			if(e.preventDefault){
				e.preventDefault();
			}else{
				return false;	
			}
			
		};
	};
	return init();
}