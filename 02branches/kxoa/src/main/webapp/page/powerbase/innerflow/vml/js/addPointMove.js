function lineMove(o){
	var defaultOptions = {
			moveObject:o
		},
		deltaX = 0, deltaY = 0, initX = 0, initY = 0, pointArray1 = new Array,pointArray2 = new Array,p1_1=new Array,p1_2=new Array,
		p2_1=new Array,p2_2=new Array,cirleX,cirleY,lineArray1=new Array,lineArray2=new Array,nPoint,isMove=0,
		action = {
			move:function(e){
				var e = e || window.event,
					MouseX = e.x,
					MouseY = e.y,
					o = defaultOptions.moveObject;
					
				isMove=1;
				
				changePolylinePoint(o,[e.x,e.y],nPoint);
				
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
				
				/*if(isMove){
					var circle = gCircle.circle(8).cx(e.clientX-canvas.offsetLeft).cy(e.clientY-canvas.offsetTop).stroke({color:"#000"}).fill("#fff").attr({"lineID":o.getAttribute("id")});
				
					if(o.getAttribute("cricleID")){
						var cricleID = o.getAttribute("cricleID");
						o.setAttribute("cricleID",cricleID+","+circle.attr("id"));
					}else{
						o.setAttribute("cricleID",circle.attr("id"));
					}
				}else{
					deleteP(o,[e.clientX-canvas.offsetLeft,e.clientY-canvas.offsetTop]);	
				}
				isMove=0;*/
				if(e.stopPropagation){
					e.stopPropagation();
				}else{
					e.cancleBubble = true;
				}
			}	
		};
	function init(){
		var	o = defaultOptions.moveObject;
		var e = window.event || e;
			if(flag){//flag 是全局变量
				initX = e.x;
				initY = e.y;
				if(deleteP(o,[initX,initY])){
					nPoint = addPolylinePoint(o,[initX,initY]);								
					if(document.setCapture) o.setCapture();
					addEvent(document,"mousemove",action.move);
					addEvent(document,"mouseup",action.stop);
					addEvent(document,"losecapture",action.stop);	
				}				
			}
			if(e.preventDefault){
				e.preventDefault();
			}else{
				return false;	
			}

	};
	return init();
}