var addEvent = function(o,eventType,fn){
	if(document.addEventListener){
  		o.addEventListener(eventType,fn,true);
 	}else if(document.attachEvent){
  		o.attachEvent('on'+eventType,fn);
 	}else{
  		o['on'+eventType] = fn;
 	}
};
var removeEvent = function(o,eventType,fn){
	if(document.removeEventListener){
  		document.removeEventListener(eventType,fn,true);
 	}else if(document.detachEvent){
  		o.detachEvent('on'+eventType,fn);
 	}
};
function moveTip(o,t,s){
	var defaultOptions = {
			moveObject:o,
			shadow:s,
			trigger:t
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
				s.style.left = MouseX-deltaX-4+"px";
				s.style.top = MouseY-deltaY-2+"px";
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
				//defaultOptions.moveObject.style.zIndex = 1;
				if(e.stopPropagation){
					e.stopPropagation();
				}else{
					e.cancleBubble = true;
				}
			}	
		};
	function init(){
		var	o = defaultOptions.moveObject,
		    s = defaultOptions.shadow,
			t = defaultOptions.trigger;
		addEvent(t,"mousedown",function(e){
			var e = e || window.event;
			deltaX = e.clientX - o.offsetLeft;
			deltaY = e.clientY - o.offsetTop;
			//o.style.zIndex = 10;
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