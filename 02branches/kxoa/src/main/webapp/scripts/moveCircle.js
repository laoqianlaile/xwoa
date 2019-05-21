function moveCircle(o){
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
					
				var text = o.getAttribute("textID");
				
				if(o.getAttribute("cx") && o.getAttribute("type")){
					o.setAttribute("cx",MouseX-deltaX);
					o.setAttribute("cy",MouseY-deltaY);
					g(text).setAttribute("x",parseInt(o.getAttribute("cx")));
					g(text).setAttribute("y",parseInt(o.getAttribute("cy"))+6);
					if(isbrowser().toLowerCase()=="ff"){
						g(text).setAttribute("dx",parseInt(o.getAttribute("cx"))*2);
					}else{
						g(text).setAttribute("dx",parseInt(o.getAttribute("cx")));	
					}
				}
				if(o.nodeName=="polygon"){
					var pp = drawPolygon([MouseX-deltaX,MouseY-deltaY]);
					o.setAttribute("points",pp);
					o.setAttribute("getX",MouseX-deltaX);
					o.setAttribute("getY",MouseY-deltaY);
					g(text).setAttribute("x",parseInt(o.getAttribute("getX")));
					g(text).setAttribute("y",parseInt(o.getAttribute("getY"))+6);
					if(isbrowser().toLowerCase()=="ff"){
						g(text).setAttribute("dx",parseInt(o.getAttribute("getX"))*2);
					}else{
						g(text).setAttribute("dx",parseInt(o.getAttribute("getX")));	
					}
				}
				
				if(o.getAttribute("from")){
					for(var i=0,len=lineArray1.length;i<len;i++){
						pointArray1[i][0] = (p1_1[i] + MouseX-initX) +","+ (p1_2[i] + MouseY-initY);
						g(lineArray1[i]).setAttribute("points",pointArray1[i].join(" "));//固定节点变化线
					}
					
				}
				if(o.getAttribute("to")){
					for(var i=0,len=lineArray2.length;i<len;i++){
						pointArray2[i][pointArray2[i].length-1] = (p2_1[i] + MouseX-initX) +","+ (p2_2[i] + MouseY-initY);
						g(lineArray2[i]).setAttribute("points",pointArray2[i].join(" "));
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
				if(o.nodeName=="polygon" || o.nodeName=="ellipse"){
					if(o.getAttribute("from")){
						var line1 = o.getAttribute("from").split(",");
						for(var i=0,len=line1.length;i<len;i++){
							var clen = SVG.get(line1[i]).attr("points").split(" ").length;
							if(clen<=2){
								var FP = formatLine(SVG.get(SVG.get(line1[i]).attr("from")),SVG.get(SVG.get(line1[i]).attr("to")));
								SVG.get(line1[i]).attr({points:FP["p1"][0]+","+FP["p1"][1]+" "+FP["p2"][0]+","+FP["p2"][1]});
							}else{
								
							}
						}
						
					}
					if(o.getAttribute("to")){
						var line2 = o.getAttribute("to").split(",");
						for(var i=0,len=line2.length;i<len;i++){
							var clen = SVG.get(line2[i]).attr("points").split(" ").length;
							if(clen<=2){
								var FP = formatLine(SVG.get(SVG.get(line2[i]).attr("from")),SVG.get(SVG.get(line2[i]).attr("to")));
								SVG.get(line2[i]).attr({points:FP["p1"][0]+","+FP["p1"][1]+" "+FP["p2"][0]+","+FP["p2"][1]});
							}else{
								
							}
						}
					}
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
			if(flag){//flag 是全局变量
				var e = e || window.event;
				initX = e.clientX;
				initY = e.clientY;
				if(o.getAttribute("cx") && o.getAttribute("type")){
					deltaX = e.clientX - o.getAttribute("cx");
					deltaY = e.clientY - o.getAttribute("cy");
				}
				if(o.nodeName=="polygon"){
					deltaX = e.clientX - o.getAttribute("getX");
					deltaY = e.clientY - o.getAttribute("getY");
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