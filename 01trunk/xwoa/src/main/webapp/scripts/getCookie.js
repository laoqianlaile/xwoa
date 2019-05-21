function setNameCookie(){
	if ($.isFunction($.cookie)){
		$.cookie("userInfo","",{ expires: -1, path: '/' });
		var $usercode = $(".username span:first").attr("usercode");
		var tab = $.cookie("userInfo");
		if(tab == null){
			$.cookie("userInfo",$usercode,{ expires: 0.3, path: '/' });	
		}		
	}
}

function setCookie(obj,external,tabid,url,title){
	if ($.isFunction($.cookie)){
		var tab = $.cookie("userInfo");
		if(tab != null){
			var info = tab.split(",");
			var len = info.length;
			if(len>1){
				for(var i =1 ; i<len; i++){
					var index = info[i].indexOf(tabid);
					if(index != -1){
						break;
					}
				}
				if(index == -1){
					tab += (","+external+";"+tabid+";"+url+";"+title);
				}
			}else{
				tab += (","+external+";"+tabid+";"+url+";"+title);
			}
			$.cookie("userInfo",tab,{ expires: 0.3, path: '/' });
		}
	}
}

function getCookie(){
	if ($.isFunction($.cookie)){
		var tab = $.cookie("userInfo");
		var info = tab.split(",");
		var $ul = $("#default-li");
		var li = "";
		for(var i =1;i<info.length;i++){
			var c = info[i].split(";");
			var his = "his"+i;
			li += "<li><a id='his"+i+"' href='"+c[2]+"' target='navTab' rel='"+c[1]+"' external='"+c[0]+"'>'"+c[3]+"'</a></li>";
		}
		//$ul.append(li);
	}
}

function deleteCookie(tabid){
	if ($.isFunction($.cookie)){
		var tab = $.cookie("userInfo");
		var info = tab.split(",");
		for(var i=1;i<info.length;i++){
			var index = info[i].indexOf(tabid);
			if(index>-1){ var elem = info[i]; break; }
		}
		elem = ','+elem;
		tab = tab.replace(elem,"");
		$.cookie("userInfo",tab,{ expires: 0.3, path: '/' });
	}
}

function deleteAll(){
	if ($.isFunction($.cookie)){
		var tab = $.cookie("userInfo");
		var info = tab.split(",");
		$.cookie("userInfo",info[0],{ expires: 0.3, path: '/' });
	}
}

function deleteOther(tabid){
	if ($.isFunction($.cookie)){
		var tab = $.cookie("userInfo");
		var info = tab.split(",");
		var elem="";
		for(var i=1;i<info.length;i++){
			var index = info[i].indexOf(tabid);
			if(index>-1){ elem = ","+info[i]; break; }
		}
		$.cookie("userInfo",info[0]+elem,{ expires: 0.3, path: '/' });
	}
}