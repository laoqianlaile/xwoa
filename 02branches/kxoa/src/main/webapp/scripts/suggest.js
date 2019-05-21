String.prototype.trim = function(){    
	return this.replace(/(^\s*)|(\s*$)/g,"");    
};

function getArr(d,value){
	var arr = new Array(),name = new Array(),code = new Array();
	var v = value;
	var len = d.length;
	for(var i=0;i<len;i++){
		if(d[i]["usercode"].toString().indexOf(v)>=0 || d[i]["username"].indexOf(v)>=0 ){ 
			var s =d[i]["usercode"]+"-"+d[i]["username"];
			arr.push(s)
			name.push(d[i]["username"]);
			code.push(d[i]["usercode"]);
		}
	}
	return {li:arr,name:name,code:code};
}

function getKey(value,keyVal,list,url,keyID,fn){
	var val;
	var $keyVal = keyVal;
	var $list = list;
	var $keyID = keyID;
	if(!value) val = $keyVal.val().trim();
	else val = value;
	
	if(val.length>0){
		 jQuery.ajax({
			url:url,
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data){
					var d = getArr(data,val);
					var len = d["li"].length;
					var liElem="";
					if(len>5) $list.css({"width":"350px","height":"150px","overflow":"auto"});
					else $list.css({"height":32*len});
					for(var i=0;i<len;i++){
						liElem += "<li>"+d["li"][i]+"</li>";
					}
					if(liElem.trim() != ""){
						var t;
						$list.html(liElem).show();
						
						$list.hover(function(){clearTimeout(t);},function(){
							//t = setTimeout(function(){$list.hide().html();},3000);
							$keyVal.bind("blur",function(){$list.hide().html();});
							$("body").click = function(){$list.hide().html();};
							}
						);
						$("li",$list).each(function(index){
							$(this).hover(function(){$keyVal.unbind("blur");clearTimeout(t);},function(){});	
							$(this).bind('click',function(){
								var v = $(this).html();
								//var vk = v.split("-");
								/*if($keyVal.val().split(",").length>1){
									var yuan = $keyVal.val().split(",");
									var sID = $keyID.val().split(",");
									sID.length = yuan.length;
									sID[yuan.length-1] = vk[0];
									yuan.pop();
									alert(yuan.join(",")+","+vk[1]);
									$keyVal.val(yuan.join(",")+","+vk[1]);
									$keyID.val(sID.join(","));
								}else{
									$keyVal.val(vk[1]);
									$keyID.val(vk[0]);
								}*/
								$keyVal.val(d["name"][index]);
								$keyID.val(d["code"][index]);
								t = setTimeout(function(){$list.hide().html();},100);
								if(fn){
									fn();
								}
							});
							
						});
					}else{ $list.hide().html(); }
				}else{
					$list.hide().html();
				}
				
			}
		 });
	}else{
		$list.hide().html();	
	}
}

//拼装申请人员
function getArr_(d,value){
	var arr = new Array();
	var v = value;	
	var len = d.length;
	for(var i=0;i<len;i++){
		//排除有空值的情况
		if(d[i]["proposerName"]){
		if(d[i]["proposerName"].toString().indexOf(v)>=0){ 
			var s =d[i]["proposerName"];
			arr.push(s);
		}
		}
	}
	return arr;
}
////拼装申请人员
function getKey_(value,keyVal,list,url,keyID){
	var val;
	var $keyVal = keyVal;
	var $list = list;
	var $keyID = keyID;
	if(!value) val = $keyVal.val().trim();
	else val = value;
	
	if(val.length>0){
		 jQuery.ajax({
			url:url,
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data){
					var d = getArr_(data,val);
					var len = d.length;
					var liElem="";
					if(len>5) $list.css({"height":"150px","overflow":"auto"});
					else $list.css({"height":30*len});
					for(var i=0;i<len;i++){
						liElem += "<li>"+d[i]+"</li>";
					}
					if(liElem.trim() != ""){
						var t;
						$list.html(liElem).show();
						
						$list.hover(function(){clearTimeout(t);},function(){
							//t = setTimeout(function(){$list.hide().html();},3000);
							$keyVal.bind("blur",function(){$list.hide().html();});
							$("body").click = function(){$list.hide().html();};
							}
						);
						$("li",$list).each(function(index){
							$(this).hover(function(){$keyVal.unbind("blur");clearTimeout(t);},function(){});	
							$(this).bind('click',function(){
								var v = $(this).html();
								var vk = v.split("-");
								if($keyVal.val().split(",").length>1){
									var yuan = $keyVal.val().split(",");
									var sID = $keyID.val().split(",");
									sID.length = yuan.length;
									sID[yuan.length-1] = vk[0];
									yuan.pop();
									$keyVal.val(yuan.join(",")+","+vk[1]);
									$keyID.val(sID.join(","));
								}else{
									$keyVal.val(vk[1]);
									$keyID.val(vk[0]);
								}
								t = setTimeout(function(){$list.hide().html();},100);
								
							});
							
						});
					}else{ $list.hide().html(); }
				}else{
					$list.hide().html();
				}
			}
		 });
	}else{
		$list.hide().html();	
	}
}

//拼装申请人员
function initValue_(keyVal,list,url,keyID){
	var $keyVal = keyVal;
	var $keyID = keyID;
	var text=$keyVal.val().split(","),len=text.length;
	$keyVal.bind("keyup",function(e){
		text = $keyVal.val().split(",");
		len = text.length;
		var ev = e || window.event;
		var keyCode = ev.keyCode;
		var time;
		if(keyCode == 8){
			var k = $keyVal.val().split(",");
			var kID = $keyID.val().split(",");
			var len = k.length;
			if(k[len-1].trim() == ""){
				kID.length = len-1;
				$keyID.val(kID.join(","));
			}
		}
		clearTimeout(time);
		time = setTimeout(function(){
			if(keyCode == 188) clearTimeout(time);
			getKey_(text[len-1].trim(),$keyVal,list,url,keyID);
		},100);
		$keyVal.bind("blur",function(){list.hide().html();});
	}).bind("focus",function(){
		$("#userDiv,.userDiv").css("position","relative");
		getKey_(text[len-1].trim(),$keyVal,list,url,keyID);
		$keyVal.bind("blur",function(){list.hide().html();});
	}).bind("blur",function(){ 
		list.hide().html();
		$("#userDiv,.userDiv").css("position","static");
	});
	
	
}
function initValue(keyVal,list,url,keyID,fn){
	var $keyVal = keyVal;
	var $keyID = keyID;
	var text=$keyVal.val().split(","),len=text.length;
	$keyVal.bind("keyup",function(e){
		
		text = $keyVal.val().split(",");
		len = text.length;
		var ev = e || window.event;
		var keyCode = ev.keyCode;
		var time;
		if(keyCode == 8){
			var k = $keyVal.val().split(",");
			var kID = $keyID.val().split(",");
			var len = k.length;
			if(k[len-1].trim() == ""){
				kID.length = len-1;
				$keyID.val(kID.join(","));
			}
		}
		clearTimeout(time);
		time = setTimeout(function(){
			if(keyCode == 188) clearTimeout(time);
			getKey(text[len-1].trim(),$keyVal,list,url,keyID,fn);
		},100);
		
		$keyVal.bind("blur",function(){list.hide().html();});
	}).bind("focus",function(){
		$("#userDiv,.userDiv").css({"position":"relative"});
		getKey(text[len-1].trim(),$keyVal,list,url,keyID,fn);
		$keyVal.bind("blur",function(){list.hide().html();});
	}).bind("blur",function(){ 
		list.hide().html();
		$("#userDiv,.userDiv").css("position","static");
	});
	
}