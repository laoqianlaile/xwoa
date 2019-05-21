function moveTip(o,t){
	var defaultOptions = {
			moveObject:o,
			trigger:t,
		},
		deltaX = 0,
		deltaY = 0,
		action = {
			move:function(e){
				var e = e || window.event,
					MouseX = e.clientX,
					MouseY = e.clientY,
					o = defaultOptions.moveObject;
				o.style.left = MouseX-deltaX+"px";
				o.style.top = MouseY-deltaY+"px";
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
				defaultOptions.moveObject.style.zIndex = 1;
				if(e.stopPropagation){
					e.stopPropagation();
				}else{
					e.cancleBubble = true;
				}
			}	
		};
	function init(){
		var	o = defaultOptions.moveObject,
			t = defaultOptions.trigger;
		addEvent(t,"mousedown",function(e){
			var e = e || window.event;
			deltaX = e.clientX - o.offsetLeft;
			deltaY = e.clientY - o.offsetTop;
			o.style.zIndex = 10;
			if(document.setCapture) o.setCapture();
			addEvent(document,"mousemove",action.move);
			addEvent(document,"mouseup",action.stop);
			addEvent(document,"losecapture",action.stop);
			if(e.preventDefault){
				e.preventDefault();
			}else{
				return false;	
			}
		});
	};
	return init();
}