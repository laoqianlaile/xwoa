//x(attrValue(getShape[i],"title","x")).y(attrValue(getShape[i],"title","y")).attr({dx:attrValue(getShape[i],"title","dx")})
var shape = new Array();
	shape["rect"] = '<v:RoundRect id="rect" style="cursor:pointer;position:absolute;width:100px;height:50px;left:0px;top:0px;background:#fff;" strokeColor="#000" strokeWeight="1px">' +
					'<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
              		'<v:TextBox id="text"  style="display:block;width:90px;margin:8px auto 0;line-height:20px;text-align:center;color:#000;font-size:14px;" ></v:TextBox>' +
              		'</v:RoundRect>';
	shape["polyline"] = '<v:PolyLine id="{id}" points="{pt}" style="position:absolute;z-index:10" filled="f" left="20px" top="20px" strokeWeight="1px" strokeColor="#000"><v:stroke EndArrow="Classic"></v:stroke></v:PolyLine>';
	shape["polygon"] = '<v:Shape id="{id}" type="#diamond" fillcolor="#fff" strokecolor="#000" coordorigin="100 100" coordsize="1 1" style="cursor:pointer;position:absolute;top:0;left:0;height:1px;width:1px;" path="m 100,100 l 155,125 100,150 45,125 x e">'+
					   '<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
					   '<v:TextBox id="text"  style="display:block;width:90px; margin:8px auto 0;line-height:20px;text-align:center;color:#000;font-size:14px;"></v:TextBox>'
					   '</v:Shape>';
	shape["circle"] = '<v:Oval id="circle" style="cursor:pointer;position:absolute;left:0;top:0;width:50px;height:50px;z-index:10"" strokecolor="#000" strokeweight="1px">' +
                	  '<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
                	  '<v:TextBox id="text" style="display:block;width:50px; margin:20px auto 0;line-height:20px;text-align:center;text-valign:middle;color:#000;font-size:14px;"></v:TextBox>' +
               	      '</v:Oval>';
	shape["demoCircle"] = '<v:Oval id="circle" style="cursor:pointer;position:absolute;left:0;top:0;width:8px;height:8px;z-index:10"" strokecolor="#000" strokeweight="1px"></v:Oval>';
	shape["line"] = '<v:line id="demoLine" title="直线" style="z-index:100;position:relative;left:50px;top:60px;" from="0,0" to="0,0"  strokecolor="blue" strokeweight="1">' +
    '<v:stroke id="demoLineArrow" StartArrow="" EndArrow="Classic"/>' +
    '<v:TextBox inset="5pt,1pt,5pt,5pt" style="text-align:center; color:blue; font-size:9pt;"></v:TextBox>' +
  '</v:line>';

	//获得节点文本值，第一个参数是dom对象，第二个参数是节点名称
function nodeValue(o,nodeName){
	if(o.getElementsByTagName(nodeName)[0]){
		if(o.getElementsByTagName(nodeName)[0].textContent){
			return o.getElementsByTagName(nodeName)[0].textContent;
		}else{
			return o.getElementsByTagName(nodeName)[0].text;	
		}
	}
}
 
   //获得节点内属性值，第一个参数是dom对象，第二个参数是节点名称，第三个参数是节点属性名称
function attrValue(o,nodeName,attrName){
	return o.getElementsByTagName(nodeName)[0].getAttribute(attrName);	
}


  //封装g函数
var g = function(id){ return document.getElementById(id); }
//obj:{"a":"11","b":22}
   //设置对象CSS属性
var addStyle = function(o,obj){
	if(!o) return false;
	if( (typeof obj) != "object" ) return false;
	for( var able in obj ){
		o.style[able] = obj[able];
	}
	
}
  //设置节点属性值
var addNode = function(o,obj){
	if(!o) return false;
	if( (typeof obj) != "object" ) return false;
	for( var able in obj ){
		o.setAttribute(able,obj[able]);
	}
	
}
  //增加鼠标事件，第一个参数是dom对象，第二个参数是鼠标事件类型，第三个参数是所引发的函数名称
var addEvent = function(o,eventType,fn){
	if(document.addEventListener){
  		o.addEventListener(eventType,fn,true);
 	}else if(document.attachEvent){
  		o.attachEvent('on'+eventType,fn);
 	}else{
  		o['on'+eventType] = fn;
 	}
}

 //移除鼠标事件
var removeEvent = function(o,eventType,fn){
	if(document.removeEventListener){
  		document.removeEventListener(eventType,fn,true);
 	}else if(document.detachEvent){
  		o.detachEvent('on'+eventType,fn);
 	}
}


  //SVG版本下，根据节点计算线条起始位置
function getPoint(pro){
	var x,y,w,h,left,right,top,bottom,pointArray = new Array, points;
	if(pro.type=="rect"){
		x = pro.x(),y=pro.y(),
		w = pro.attr("width"),
		h = pro.attr("height"),
		left = [x,y+h/2];
		right = [x+w,y+h/2];
		top = [x+w/2,y];
		bottom = [x+w/2,y+h];
	}else if(pro.type=="polygon"){
		points = pro.attr("points").split(" ");
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		top = pointArray[2];
		right = pointArray[1];
		bottom = pointArray[0];
		left = pointArray[3];
	}else{
		x = pro.x(),y=pro.y();
		w = h = parseInt(pro.attr("rx"))*2;
		left = [x,y+h/2];
		right = [x+w,y+h/2];
		top = [x+w/2,y];
		bottom = [x+w/2,y+h];
	}
	return {L:left,T:top,R:right,B:bottom};
}
  
//SVG版本下，根据节点计算线条结束位置
function getEndPoint(pro){
	var x,y,w,h,left,right,top,bottom,pointArray = new Array, points;
	if(pro.type=="rect"){
		x = pro.x(),y=pro.y(),
		w = pro.attr("width"),
		h = pro.attr("height"),
		left = [x-9,y+h/2],
		right = [x+w+9,y+h/2],
		top = [x+w/2,y-9],
		bottom = [x+w/2,y+h+9];
	}else if( pro.type=="polygon" ){
		points = pro.attr("points").split(" ");
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		top = pointArray[2];
		right = pointArray[1];
		bottom = pointArray[0];
		left = pointArray[3];
	}else{
		x = pro.x(),y=pro.y();
		w = h = parseInt(pro.attr("rx"))*2;
		left = [x-9,y+h/2],
		right = [x+w+9,y+h/2],
		top = [x+w/2,y-9],
		bottom = [x+w/2,y+h+9];	
	}
	return {L:left,T:top,R:right,B:bottom};
}

//矩形中心点
function getRectCenter(o){
	var x,y,w,h;
	x = parseInt(o.style.left);
	y = parseInt(o.style.top);
	w = 100;
	h = 50;
	return {x:x+(w/2),y:y+(h/2)};
}

//格式化线的位置 此方法适合于只有两个点的直线，如果是多点就不合适
function formatLine(pocBegin,pocEnd){
	var F1 = getPoint(pocBegin),
		F2 = getEndPoint(pocEnd),
		w = 100,
		h = 50,
		p1,p2,x1,x2,y1,y2;
	
	if( pocBegin.type=="rect" || pocBegin.type=="ellipse"){
		x1 = pocBegin.x();
		y1 = pocBegin.y();
	}else if( pocBegin.type=="polygon" ){
		x1 = parseInt(pocBegin.attr("getX"))-50;
		y1 = parseInt(pocBegin.attr("getY"))-25;
	}
	
	if( pocEnd.type=="rect" || pocEnd.type=="ellipse" ){
		x2 = pocEnd.x();
		y2 = pocEnd.y();
	}else if( pocEnd.type=="polygon" ){
		x2 = parseInt(pocEnd.attr("getX"))-50;
		y2 = parseInt(pocEnd.attr("getY"))-25;
	}
		
	if(x1+w+50<x2){
		if(y1+h+50<y2){
			p1 = F1.R;
			p2 = F2.T;
		}else if(y1-h-50>y2){
			p1 = F1.R;
			p2 = F2.B;
		}else{
			p1 = F1.R;
			p2 = F2.L;
		}
	}else if(x1-w-50>x2){
		if(y1+h+50<y2){
			p1 = F1.L;
			p2 = F2.T;
		}else if(y1-h-50>y2){
			p1 = F1.L;
			p2 = F2.B;
		}else{
			p1 = F1.L;
			p2 = F2.R;
		}
	}else{
		if(y1+h<y2){
			p1 = F1.B;
			p2 = F2.T;
		}else if(y1-h>y2){
			p1 = F1.T;
			p2 = F2.B;
		}
	}
	return {p1:p1,p2:p2};
}
    
   //VML版本下，根据节点计算线条位置
function VMLgetPoint(pro){
	var x,y,w,h,left,right,top,bottom,pointArray = new Array, points;
	if(pro.tagName=="RoundRect"){
		x = parseInt(pro.style.left),
		y = parseInt(pro.style.top),
		w = parseInt(pro.style.width),
		h = parseInt(pro.style.height),
		left = [x,y+h/2];
		right = [x+w,y+h/2];
		top = [x+w/2,y];
		bottom = [x+w/2,y+h];
	}else if(pro.tagName=="Shape"){
       points = pro.getAttribute("points").split(" ");		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		top = pointArray[2];
		right = pointArray[1];
		bottom = pointArray[0];
		left = pointArray[3];
	}else{
		x = parseInt(pro.style.left),
		y = parseInt(pro.style.top),
		w = h = parseInt(parseInt(pro.style.width));
		left = [x,y+h/2];
		right = [x+w,y+h/2];
		top = [x+w/2,y];
		bottom = [x+w/2,y+h];
	}
	return {L:left,T:top,R:right,B:bottom};
}

//VML格式化线的位置 此方法适合于只有两个点的直线，如果是多点就不合适
function VMLformatLine(pocBegin,pocEnd){
	var F1 = VMLgetPoint(pocBegin),
		F2 = VMLgetPoint(pocEnd),
		w = 100,
		h = 50,
		p1,p2,x1,x2,y1,y2,p;
	
	if( pocBegin.tagName=="RoundRect" || pocBegin.tagName=="Oval" ){
		x1 = parseInt(pocBegin.style.left);
		y1 = parseInt(pocBegin.style.top);
	}else if( pocBegin.tagName=="Shape" ){
		x1 = parseInt(pocBegin.style.left);
		y1 = parseInt(pocBegin.style.top);
	}
	
	if( pocEnd.tagName=="RoundRect" || pocEnd.tagName=="Oval" ){
		x2 = parseInt(pocEnd.style.left);
		y2 = parseInt(pocEnd.style.top);
	}else if( pocEnd.tagName=="Shape" ){
		x2 = parseInt(pocEnd.style.left);
		y2 = parseInt(pocEnd.style.top);
	}
		
	if(x1+w+50<x2){
		if(y1+h+50<y2){
			p1 = F1.R;
			p2 = F2.T;
		}else if(y1-h-50>y2){
			p1 = F1.R;
			p2 = F2.B;
		}else{
			p1 = F1.R;
			p2 = F2.L;
		}
	}else if(x1-w-50>x2){
		if(y1+h+50<y2){
			p1 = F1.L;
			p2 = F2.T;
		}else if(y1-h-50>y2){
			p1 = F1.L;
			p2 = F2.B;
		}else{
			p1 = F1.L;
			p2 = F2.R;
		}
	}else{
		if(y1+h<y2){
			p1 = F1.B;
			p2 = F2.T;
		}else if(y1-h>y2){
			p1 = F1.T;
			p2 = F2.B;
		}
		else{
			p1 = F1.R;
			p2 = F2.L;
		}
	}
	p = p1[0]+","+p1[1]+" "+p2[0]+","+p2[1];
	//p.push(p1[1]+"px");
	//p.push(p2[0]+"px");
	//p.push(p2[1]+"px");
	return p;
}

//处理字串方法
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");	
}
//截取多余的字串
function dealStr(str,length){
	if(!length) var length = 6;
	if(str.length==0){ return ""; }
	else{
		if(str.trim().length>length){
			return str.substr(0,length)+"...";
		}else{
			return 	str;
		}
	}
}

// 画菱形的4个点
function drawPolygon(point){
	var p = new Array;
	p[0] = [point[0],point[1]+25];
	p[1] = [point[0]+55,point[1]];
	p[2] = [point[0],point[1]-25];
	p[3] = [point[0]-55,point[1]];
	return p.join(" ");
}

    //绑定属性工具栏
function tip(o){

	if(g("argumentTool")){
		g("f_id").innerHTML = o.id;
		g("f_name").value = o.getAttribute("title");
		g("f_name").onchange = function(){
			if(changeflag){
				return false;
			}
			o.setAttribute("title",this.value);
			g(o.getAttribute("textId")).innerHTML = this.value;
			changeflag = false;
		};
		var selectObj = g("f_type");
		for(var i=0,len=selectObj.options.length;i<len;i++){
			if(selectObj.options[i].value.toLowerCase()==o.getAttribute("type1").toLowerCase()){
				selectObj.options[i].selected = true;	
			}	
		}
		//节点类型变化函数，菱形与矩形的切换
		selectObj.onchange = function(){
			var objId = o.id;
			if(selectObj.value.toLowerCase()=="j"){
				var center = getRectCenter(o);
				var polygonCenter = drawPolygon([center.x,center.y]);
				canvas.innerHTML += shape["polygon"].replace("{id}", "polygon");
				addStyle( g("polygon"),{"left":parseInt(o.style.left)+50+"px","top":parseInt(o.style.top)+"px" } );
				g("text").innerHTML = o.getAttribute("title");
				g("text").setAttribute("imgId",objId);
				g("text").setAttribute("id",o.getAttribute("textId"));
				addNode( g("polygon"),
						 {
						   "type1":"j",
						   "is_risk":o.getAttribute("is_risk"),
						   "anticipate_day":o.getAttribute("anticipate_day"),
						   "anticipate_type":o.getAttribute("anticipate_type"),
						   "risk_desc":o.getAttribute("risk_desc"),
						   "station_function":o.getAttribute("station_function"),
						   "accept_name":o.getAttribute("accept_name"),
						   "title":o.getAttribute("title"),
						   "textId":o.getAttribute("textId"),
						   "to":o.getAttribute("to"),
						   "from":o.getAttribute("from"),
						   "toLine":o.getAttribute("toLine"),
						   "fromLine":o.getAttribute("fromLine"),
						   "getX":center.x,
						   "getY":center.y,
						   "points":polygonCenter
						 });	
				for (var i=0; i<linePoine.length; i++) {
					if(g(linePoine[i])) g(linePoine[i]).points.value = g(linePoine[i]).getAttribute("point");
				}
				g("polygon").setAttribute("id",objId);
				canvas.removeChild(g(objId));
				o = g(objId);
			}
			else{
				if(g(objId).getAttribute("type1").toLowerCase()!="j"){
					g(objId).setAttribute("type1",selectObj.value.toLowerCase());
				}
				else{
					canvas.innerHTML += shape["rect"].replace("{id}", "rect");
					addStyle( g("rect"),{"left":parseInt(o.style.left)-50+"px","top":parseInt(o.style.top)+"px" } );
					g("text").innerHTML = o.getAttribute("title");
					g("text").setAttribute("imgId",objId);
					g("text").setAttribute("id",o.getAttribute("textId"));
					addNode( g("rect"),
							{
						"type1":selectObj.value.toLowerCase(),
						"is_risk":o.getAttribute("is_risk"),
						"anticipate_day":o.getAttribute("anticipate_day"),
						"anticipate_type":o.getAttribute("anticipate_type"),
						"risk_desc":o.getAttribute("risk_desc"),
						"station_function":o.getAttribute("station_function"),
						"accept_name":o.getAttribute("accept_name"),
						"title":o.getAttribute("title"),
						"textId":o.getAttribute("textId"),
						"to":o.getAttribute("to"),
						"from":o.getAttribute("from"),
						"toLine":o.getAttribute("toLine"),
						"fromLine":o.getAttribute("fromLine")
							});					
					//出入新的图形元素，对原来的线条进行位置重赋值
					for (var i=0; i<linePoine.length; i++) {
						if(g(linePoine[i])) g(linePoine[i]).points.value = g(linePoine[i]).getAttribute("point");
					}
					g("rect").setAttribute("id",objId);
					canvas.removeChild(g(objId));
					o = g(objId);
				}
			}
			
		};
		g("f_user").value = o.getAttribute("accept_name");
		g("f_user").onchange = function(){
			if(changeflag){
				return false;
			}
			o.setAttribute("accept_name",this.value);
			changeflag = false;
		};
		g("station_function").value = o.getAttribute("station_function");
		g("station_function").onchange = function(){
			if(changeflag){
				return false;
			}
			o.setAttribute("station_function",this.value);
			changeflag = false;
		};
		g("anticipate_day").value = o.getAttribute("anticipate_day");
		g("anticipate_day").onchange = function(){
			if(changeflag){
				return false;
			}
			if(isNaN(this.value)){
				alert("请输入数字！");
			}
			else{
				o.setAttribute("anticipate_day",this.value);
			}
			changeflag = false;
		};
		var anticipate_type = g("anticipate_type");
		for(var i=0,len=anticipate_type.options.length;i<len;i++){
			if(anticipate_type.options[i].value==o.getAttribute("anticipate_type")){
				anticipate_type.options[i].selected = true;	
			}	
		}
		anticipate_type.onchange = function(){
			o.setAttribute("anticipate_type",anticipate_type.value);
		};
		g("risk_desc").value = o.getAttribute("risk_desc");
		g("risk_desc").onchange = function(){
			if(changeflag){
				return false;
			}
			o.setAttribute("risk_desc",this.value);
			changeflag = false;
		};
		var radio = document.getElementsByName("risk");
		for(var j=0,jLen=radio.length;j<jLen;j++){
			if(radio[j].value==o.getAttribute("is_risk")){
				radio[j].checked = true;
			}
			if(!parseInt(this.value)){
				g("risk_desc").disabled=true;
			}
			radio[j].onclick = function(){
				o.setAttribute("is_risk",this.value);
				if( parseInt(this.value) ){
					g("risk_desc").disabled=false;
				}else{
					g("risk_desc").disabled=true;
					o.setAttribute("risk_desc","");
					g("risk_desc").value="";
				}
			};
		}
		g("ismain").style.display = "none";
		g("ispro").style.display = "block";
	}
}

  //当节点类型为u时（菱形），绑定线条属性框，设置线条判断为真或假
   function bindLine(o){
	   var isLine = document.getElementsByName("judgeLine");
	   var other = g(o.getAttribute("from")).getAttribute("toLine").replace(o.id,"").replace(",","");
		   for(var i=0,len=isLine.length;i<len;i++){
			   if(isLine[i].value==o.getAttribute("judge")){
				   isLine[i].checked = true;
			   }
			   else{
				   isLine[i].checked = false;
			   }
			   isLine[i].onclick = function(){
				   o.setAttribute("judge",this.value);
				   if(parseInt(this.value)){//判断为真
					   o.strokeColor = "blue";
					   o.strokeWeight = 1.3;
					   g(o.getAttribute("from")).setAttribute("true_point",o.getAttribute("point"));
					   if(other){//判断为假
						   g(other).strokeColor = "#999";
						   g(other).strokeWeight = 1.3;
						   g(other).setAttribute("judge",0);
						   g(o.getAttribute("from")).setAttribute("false_point",g(other).getAttribute("point"));
					   }
				   }
				   else{//判断为假
					   o.strokeColor = "#999";
					   o.strokeWeight = 1;
					   g(o.getAttribute("from")).setAttribute("false_point",o.getAttribute("point"));
					   if(other){//判断为真
						   g(other).strokeColor = "blue";
						   g(other).strokeWeight = 1.3;
						   g(other).setAttribute("judge",1);
						   g(o.getAttribute("from")).setAttribute("true_point",g(other).getAttribute("point"));
					   }
				   }
			   };
		   }
	   	   
   }

   function createXml(){
	   canvas.innerHTML += shape["circle"];
		addStyle( g("circle"),{"left":400+"px","top":106+"px" } );
		g("circle").setAttribute( "id","begin");			
		g("text").setAttribute( "imgId","begin");
		g("text").setAttribute( "id","beginText" );
		g("beginText").innerHTML = "开始";
		addNode( g("begin"),
				 {
				   "type1":"circle",
				   "is_risk":"",
				   "anticipate_day":"",
				   "anticipate_type":"",
				   "risk_desc":"",
				   "station_function":"",
				   "accept_name":"",
				   "title":"开始",
				   "to":""
				 });
		 canvas.innerHTML += shape["circle"];
			addStyle( g("circle"),{"left":400+"px","top":306+"px" } );
			g("circle").setAttribute( "id","end");			
			g("text").setAttribute( "imgId","end");
			g("text").setAttribute( "id","endText" );
			g("endText").innerHTML = "结束";
			addNode( g("end"),
					 {
					   "type1":"circle",
					   "is_risk":"",
					   "anticipate_day":"",
					   "anticipate_type":"",
					   "risk_desc":"",
					   "station_function":"",
					   "accept_name":"",
					   "title":"结束",
					   "to":""
					 });
   }
   
   //根据xml加载图形
function vmlXml(xml){
	var xmlDoc;
	if(window.ActiveXObject){
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.loadXML(xml);
	}else if(window.DOMParser){
		parser=new DOMParser();
		xmlDoc=parser.parseFromString(xml,"text/xml");
	}  
	var getShape = xmlDoc.getElementsByTagName("flow_seq");
	leng = getShape.length;
	for( var i=0,len=getShape.length;i<len;i++ ){
		switch( nodeValue(getShape[i],"type").toLowerCase() ){
			case "circle"://圆形
				canvas.innerHTML += shape["circle"];
				addStyle( g("circle"),{"left":parseInt(nodeValue(getShape[i],"left"))-25+"px","top":parseInt(nodeValue(getShape[i],"top"))-25+"px" } );
				g("circle").setAttribute( "id",nodeValue(getShape[i],"seq_id") );			
				g("text").setAttribute( "imgId",nodeValue(getShape[i],"seq_id"));
				g("text").setAttribute( "id","text"+i );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				addNode( g(nodeValue(getShape[i],"seq_id")),
						 {
						   "type1":nodeValue(getShape[i],"type"),
						   "is_risk":nodeValue(getShape[i],"is_risk"),
						   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
						   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
						   "risk_desc":nodeValue(getShape[i],"risk_desc"),
						   "station_function":nodeValue(getShape[i],"station_function"),
						   "accept_name":nodeValue(getShape[i],"accept_name"),
						   "title":nodeValue(getShape[i],"title"),
						   "to":nodeValue(getShape[i],"next_seq_id")
						 });	
				break;
				
			case "j"://菱形
				canvas.innerHTML += shape["polygon"].replace("{id}", nodeValue(getShape[i],"seq_id"));
				addStyle( g(nodeValue(getShape[i],"seq_id")),{"left":nodeValue(getShape[i],"left")+"px","top":parseInt(nodeValue(getShape[i],"top"))-25+"px" } );
				g("text").setAttribute( "imgId",nodeValue(getShape[i],"seq_id"));
				g("text").setAttribute( "id","text"+i );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				addNode( g(nodeValue(getShape[i],"seq_id")),
						 {
						   "type1":nodeValue(getShape[i],"type"),
						   "is_risk":nodeValue(getShape[i],"is_risk"),
						   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
						   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
						   "risk_desc":nodeValue(getShape[i],"risk_desc"),
						   "station_function":nodeValue(getShape[i],"station_function"),
						   "accept_name":nodeValue(getShape[i],"accept_name"),
						   "title":nodeValue(getShape[i],"title"),
						   "to":nodeValue(getShape[i],"next_seq_id"),
						   "points":drawPolygon([parseInt(nodeValue(getShape[i],"left")),parseInt(nodeValue(getShape[i],"top"))]),
						   "textId":"text"+i
						 });
				
				break;
				
			default://圆矩形
				canvas.innerHTML += shape["rect"];
			    g("text").setAttribute( "imgId",nodeValue(getShape[i],"seq_id"));
				g("text").setAttribute( "id","text"+i );
				addStyle( g("rect"),{"left":nodeValue(getShape[i],"left")+"px","top":nodeValue(getShape[i],"top")+"px" } );
				g("rect").setAttribute( "id",nodeValue(getShape[i],"seq_id") );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				addNode( g(nodeValue(getShape[i],"seq_id")),
						 {
						   "type1":nodeValue(getShape[i],"type"),
						   "is_risk":nodeValue(getShape[i],"is_risk"),
						   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
						   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
						   "risk_desc":nodeValue(getShape[i],"risk_desc"),
						   "station_function":nodeValue(getShape[i],"station_function"),
						   "accept_name":nodeValue(getShape[i],"accept_name"),
						   "title":nodeValue(getShape[i],"title"),
						   "to":nodeValue(getShape[i],"next_seq_id"),
						   "textId":"text"+i
						 });
									   
				break;
		}
	}
	
	//画线
	var pointvalue = [];
	for( var i=0,len=getShape.length;i<len;i++ ){
		
		if(nodeValue(getShape[i],"next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "line_" + i);
			g("line_"+i).setAttribute("from",nodeValue(getShape[i],"seq_id"));
			g("line_"+i).setAttribute("to",nodeValue(getShape[i],"next_seq_id"));
			if(attrValue(getShape[i],"next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"next_seq_id","point"));
				g(nodeValue(getShape[i],"seq_id")).setAttribute("point",attrValue(getShape[i],"next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"next_seq_id")) );
				pointvalue.push(p);
				g(nodeValue(getShape[i],"seq_id")).setAttribute("point",p);
			}			
			g(nodeValue(getShape[i],"next_seq_id")).setAttribute("from",nodeValue(getShape[i],"seq_id"));			
			g(nodeValue(getShape[i],"seq_id")).setAttribute("toLine","line_" + i);
			g(nodeValue(getShape[i],"next_seq_id")).setAttribute("fromLine","line_" + i);
			linePoine.push("line_" + i);
		}
		if(nodeValue(getShape[i],"true_next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "trueLine_" + i);
			g("trueLine_"+i).strokeColor = "blue";
			g("trueLine_"+i).strokeWeight = 1.3;
			g("trueLine_"+i).setAttribute("from",nodeValue(getShape[i],"seq_id"));
			g("trueLine_"+i).setAttribute("to",nodeValue(getShape[i],"true_next_seq_id"));
			g("trueLine_"+i).setAttribute("judge","1");
			g(nodeValue(getShape[i],"true_next_seq_id")).setAttribute("fromLine","trueLine_"+i);
			g(nodeValue(getShape[i],"seq_id")).setAttribute("toLine","trueLine_"+i);
			if(attrValue(getShape[i],"true_next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"true_next_seq_id","point"));
				g(nodeValue(getShape[i],"seq_id")).setAttribute("true_point",attrValue(getShape[i],"true_next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"true_next_seq_id")) );
				pointvalue.push(p);
				g(nodeValue(getShape[i],"seq_id")).setAttribute("true_point",p);
			}
			g(nodeValue(getShape[i],"true_next_seq_id")).setAttribute("from",nodeValue(getShape[i],"seq_id"));
			if(g(nodeValue(getShape[i],"seq_id")).getAttribute("to")){
				g(nodeValue(getShape[i],"seq_id")).setAttribute("to",g(nodeValue(getShape[i],"seq_id")).getAttribute("to")+","+nodeValue(getShape[i],"true_next_seq_id"));
			}
			else{
				g(nodeValue(getShape[i],"seq_id")).setAttribute("to",nodeValue(getShape[i],"true_next_seq_id"));
			}
			linePoine.push("trueLine_" + i);
		}
		if(nodeValue(getShape[i],"false_next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "falseLine_" + i);
			g("falseLine_"+i).strokeColor = "#999";
			g("falseLine_"+i).strokeWeight = 1.3;
			g("falseLine_"+i).setAttribute("from",nodeValue(getShape[i],"seq_id"));
			g("falseLine_"+i).setAttribute("to",nodeValue(getShape[i],"false_next_seq_id"));
			g("falseLine_"+i).setAttribute("judge","0");
			g(nodeValue(getShape[i],"false_next_seq_id")).setAttribute("fromLine","falseLine_"+i);
			g(nodeValue(getShape[i],"seq_id")).setAttribute("toLine","trueLine_"+i+",falseLine_" + i);
			if(attrValue(getShape[i],"false_next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"false_next_seq_id","point"));
				g(nodeValue(getShape[i],"seq_id")).setAttribute("false_point",attrValue(getShape[i],"false_next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"false_next_seq_id")) );
				pointvalue.push(p);
				g(nodeValue(getShape[i],"seq_id")).setAttribute("false_point",p);
			}
			g(nodeValue(getShape[i],"false_next_seq_id")).setAttribute("from",nodeValue(getShape[i],"seq_id"));
			if(g(nodeValue(getShape[i],"seq_id")).getAttribute("to")){
				g(nodeValue(getShape[i],"seq_id")).setAttribute("to",g(nodeValue(getShape[i],"seq_id")).getAttribute("to")+","+nodeValue(getShape[i],"false_next_seq_id"));
			}
			else{
				g(nodeValue(getShape[i],"seq_id")).setAttribute("to",nodeValue(getShape[i],"false_next_seq_id"));
			}
			linePoine.push("falseLine_" + i);
		}
	}
	
	//线条位置赋值
	for (var i=0; i<pointvalue.length; i++) {
		if(g(linePoine[i])) g(linePoine[i]).points.value = pointvalue[i];
		g(linePoine[i]).setAttribute("point",pointvalue[i]);
	}
	
	g("flow_id").value = nodeValue(xmlDoc,"flow_id");
	g("flow_code").value = nodeValue(xmlDoc,"flow_code");
	g("flow_title").value = nodeValue(xmlDoc,"flow_title");

}
   
    
  //拖动某一节点变化位置 n为变化的第几个节点【重要】
	function changePolylinePoint(line,point,n){
		var pointArray = new Array, points = line.getAttribute("point").split(" ");
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}		
		pointArray[n]=point[0]+","+point[1];
		var newp = pointArray.join(" ");
		g(line.id).points.value = newp;
		g(line.id).setAttribute("point",pointArray.join(" "));
		if(g(line.getAttribute("from")).getAttribute("type1")=="j"){
			if(line.getAttribute("judge")){
				if(line.getAttribute("judge")=="1"){
					g(line.getAttribute("from")).setAttribute("true_point",newp);
				}
				else{
					g(line.getAttribute("from")).setAttribute("false_point",newp);
				}
			}
			else{
				g(line.getAttribute("from")).setAttribute("point",newp);
			}
		}
		else{
			g(line.getAttribute("from")).setAttribute("point",newp);
		}
		
	}
    
  //折线工具 添加节点 
	function addPolylinePoint(line,newPoint){
		var pointArray = new Array, points = line.getAttribute("point").split(" "),
			flagX,flagY,newPosition,newPointArray=new Array;
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		
		for( var j=0,jLen=pointArray.length;j<jLen-1;j++ ){
			flagX = (( newPoint[0]>=pointArray[j][0] && newPoint[0]<=pointArray[j+1][0] ) || ( newPoint[0]<=pointArray[j][0] && newPoint[0]>=pointArray[j+1][0] ) );
			flagY = (( newPoint[1]>=pointArray[j][1] && newPoint[1]<=pointArray[j+1][1] ) || ( newPoint[1]<=pointArray[j][1] && newPoint[1]>=pointArray[j+1][1] ) );
			if( flagX && flagY ){
				newPosition=j+1;
				break;
			}
		}
		
		for( var h=0,hLen=pointArray.length;h<hLen;h++ ){
			if(h==newPosition) newPointArray.push(newPoint[0]+","+newPoint[1]);
			newPointArray.push(pointArray[h][0]+","+pointArray[h][1]);
		}
		var df = newPointArray.join(" ");
		//line.points.value = newPointArray.join(" ");
		line.setAttribute("point",newPointArray.join(" "));
		return newPosition;
	}
	
	//得到上一方法的 n
	function getcurPoint(line,point){
		var pointArray = new Array, points = line.getAttribute("point").split(" ");
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		
		for( var h=0,hLen=pointArray.length;h<hLen;h++ ){
			if( parseInt(pointArray[h][0])==point[0] && parseInt(pointArray[h][1])==point[1] ){
				return h;
				break;
			}
		}
	}
	
	//删除选中添加的节点
	function deleteP(line,point){
		var pointArray = new Array,
			points = line.getAttribute("point").split(" ");
			
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		for(var h=0,hLen=pointArray.length;h<hLen;h++ ){
			if(Math.abs(point[0]-pointArray[h][0])<5&&Math.abs(point[1]-pointArray[h][1])<5){
				canvas.innerHTML += shape["demoCircle"];
				addStyle( g("circle"),{"left":(pointArray[h][0]-4)+"px","top":(pointArray[h][1]-4)+"px" } );
				g("circle").setAttribute("cx",pointArray[h][0]);
				g("circle").setAttribute("cy",pointArray[h][1]);
				g("circle").setAttribute("lineID",line.id);
				g("circle").setAttribute("id",pointID+"c_"+line.id);
				if(g(line.id).getAttribute("circleID")){
					var circleID = g(line.id).getAttribute("circleID");
					g(line.id).setAttribute("circleID",circleID+","+g(pointID+"c_"+line.id).getAttribute("id"));
				}else{
					g(line.id).setAttribute("circleID",g(pointID+"c_"+line.id).getAttribute("id"));
				}
				pointID++;
				for (var i=0; i<linePoine.length; i++) { //插入新的图形元素后需对原先的线进行重画，所有线的数据保存在数组中
					if(g(linePoine[i])) g(linePoine[i]).points.value = g(linePoine[i]).getAttribute("point");
				}
				return false;
			}
		}
		return true;
	}
	
    //圆矩形、圆、菱形拖动
    function drag(o){
    	var defaultOptions = {
			moveObject:o
		},
		MouseX = 0, MouseY = 0,deltaX = 0, deltaY = 0, initX = 0, initY = 0, pointArray1 = new Array,pointArray2 = new Array,p1_1=new Array,p1_2=new Array,
		p2_1=new Array,p2_2=new Array,lineArray2=new Array,nPoint;
    	function stop(e){//鼠标弹起停止
    		var e = e || window.event;
    		removeEvent(document,"mousemove",move);
        	removeEvent(document,"mouseup",stop);
        	removeEvent(document,"losecapture",stop);
        	if(document.releaseCapture)document.releaseCapture();
    	}
    	//鼠标按下拖动
    	function move(e){
    		var e = e || window.event; 
    		MouseX = e.x;
			MouseY = e.y;
			o.style.left = MouseX-deltaX;
    		o.style.top = MouseY-deltaY;
    		if(o.getAttribute("type1")=="j"){      		
        		o.setAttribute("points",drawPolygon([MouseX-deltaX,MouseY-deltaY+25])); 
        		//节点起始线条位置重计算
    			if(o.getAttribute("fromLine")){
    				if(g(o.getAttribute("fromLine")).getAttribute("point").split(" ").length<=2){
    					var p1 = VMLformatLine(g(g(o.getAttribute("fromLine")).getAttribute("from")),g(g(o.getAttribute("fromLine")).getAttribute("to")));
    				}
    				else{
    					pointArray1[pointArray1.length-1] = (p1_1[i] + MouseX-initX) +","+ (p1_2[i] + MouseY-initY);
        				var p1 = pointArray1.join(" ");
    				}
    	    		g(o.getAttribute("fromLine")).points.value = p1;
    	    		g(o.getAttribute("fromLine")).setAttribute("point",p1);
    	    		g(g(o.getAttribute("fromLine")).getAttribute("from")).setAttribute("point",p1);
    			}
    			//节点目的线条位置重计算
    			if(o.getAttribute("toLine")){
    				var to = o.getAttribute("toLine").split(",");
    				for( var i=0;i<to.length;i++){
    					if(g(to[i]).getAttribute("point").split(" ").length<=2){
    						var p2 = VMLformatLine(g(g(to[i]).getAttribute("from")),g(g(to[i]).getAttribute("to")));
    					}
    					else{
    						pointArray2[i][0] = (p2_1[i] + MouseX-initX) +","+ (p2_2[i] + MouseY-initY);
            				var p2 = pointArray2[i].join(" ");
    					}
    					
        	    		g(to[i]).points.value = p2;	
        	    		g(to[i]).setAttribute("point",p2);
        	    		if(g(to[i]).getAttribute("judge")){
        	    			if(g(to[i]).getAttribute("judge")=="1")
        	    				{
        	    				g(g(to[i]).getAttribute("from")).setAttribute("true_point",p2);
        	    				}
        	    			else{
        	    				g(g(to[i]).getAttribute("from")).setAttribute("false_point",p2);
        	    			}
        	    		}
        	    		else{
        	    			g(g(to[i]).getAttribute("from")).setAttribute("point",p2);
        	    		}
    				}
    			}
    		}
    		else if(o.getAttribute("cx")){
    			changePolylinePoint(g(o.getAttribute("lineID")),[parseInt(o.style.left)+4,parseInt(o.style.top)+4],nPoint);
    			o.setAttribute("cx",parseInt(o.style.left)+4);
    			o.setAttribute("cy",parseInt(o.style.top)+4);
    		}
    		else{
    			//节点起始线条位置重计算
    			if(o.getAttribute("fromLine")){
    				if(g(o.getAttribute("fromLine")).getAttribute("point").split(" ").length<=2){
    					var p1 = VMLformatLine(g(g(o.getAttribute("fromLine")).getAttribute("from")),g(g(o.getAttribute("fromLine")).getAttribute("to")));
    				}
    				else{
    					pointArray1[pointArray1.length-1] = (p1_1[i] + MouseX-initX) +","+ (p1_2[i] + MouseY-initY);
        				var p1 = pointArray1.join(" ");
    				}
    			
    	    		g(o.getAttribute("fromLine")).points.value = p1;
    	    		g(o.getAttribute("fromLine")).setAttribute("point",p1);
    	    		if(g(o.getAttribute("fromLine")).getAttribute("judge")=="1"){
    	    			g(g(o.getAttribute("fromLine")).getAttribute("from")).setAttribute("true_point",p1);
    	    		}
    	    		else if(g(o.getAttribute("fromLine")).getAttribute("judge")=="0"){
    	    			g(g(o.getAttribute("fromLine")).getAttribute("from")).setAttribute("false_point",p1);
    	    		}
    	    		else{
    	    			g(g(o.getAttribute("fromLine")).getAttribute("from")).setAttribute("point",p1);	
    	    		}
    	    		
    			}
    			//节点目的线条位置重计算
    			if(o.getAttribute("toLine")){
    				if(g(o.getAttribute("toLine")).getAttribute("point").split(" ").length<=2){
    					var p2 = VMLformatLine(g(g(o.getAttribute("toLine")).getAttribute("from")),g(g(o.getAttribute("toLine")).getAttribute("to")));
    				}
    				else{
    					pointArray2[0][0] = (p2_1[0] + MouseX-initX) +","+ (p2_2[0] + MouseY-initY);
        				var p2 = pointArray2[0].join(" ");
    				}
    				
    	    		g(o.getAttribute("toLine")).points.value = p2;
    	    		g(o.getAttribute("toLine")).setAttribute("point",p2);
    	    		o.setAttribute("point",p2);
    			}
    		}
    		   		
    	}
    	//鼠标按下
    	function init(e){
    		var	o = defaultOptions.moveObject;
    		var e = window.event || e;
    			if(flag){
        			initX = e.x;
    				initY = e.y;
    				deltaX = e.x-parseInt(o.style.left);
    				deltaY = e.y-parseInt(o.style.top);
    				if(o.getAttribute("fromLine") && o.getAttribute("fromLine")!=""){
    					pointArray1 = g(o.getAttribute("fromLine")).getAttribute("point").split(" ");
    					var np = pointArray1[pointArray1.length-1].split(",");
    					p1_1[i] = parseInt(np[0]);
						p1_2[i] = parseInt(np[1]);
    				}
    				if(o.getAttribute("toLine") && o.getAttribute("toLine")!=""){
    					lineArray2 = o.getAttribute("toLine").split(",");
    					for(var i=0,len=lineArray2.length;i<len;i++){
    						pointArray2[i] = g(lineArray2[i]).getAttribute("point").split(" ");
    						var np = pointArray2[i][0].split(",");
    						p2_1[i] = parseInt(np[0]);
    						p2_2[i] = parseInt(np[1]);
    					}
    				}
    				if(o.getAttribute("cx")){
    					nPoint = getcurPoint(g(o.getAttribute("lineID")),[o.getAttribute("cx"),o.getAttribute("cy")]);
    				}
        			if(document.setCapture) o.setCapture();
        			addEvent(document,"mousemove",move);
        			addEvent(document,"mouseup",stop);
        			addEvent(document,"losecapture",stop);	
    			}
    			if(e.preventDefault){
    				e.preventDefault();
    			}else{
    				return false;	
    			}    		
    	}
    	return init();
    }
    //设置xml节点数据
    function setValue(o,content){;
	if(o.text!=undefined){
		o.text = content;
	}else{
		o.textContent = content;	
	}
  }
    
  //生成xml空文档
	function createNewXml(){ 
		var xmlDom = null;   
	  	if (window.ActiveXObject){   
	   		xmlDom = new ActiveXObject("Microsoft.XMLDOM");   
	   		xmlDom.async=false;   
	  	} 
	  	else if(document.implementation && document.implementation.createDocument){   
	  		xmlDom = document.implementation.createDocument("", "", null); 
	  	}else{   
			xmlDom = null;   
	  	}
	  	return xmlDom;   
	}
    
	//保存得到xml数据
    function getXml(){
    	var xmlDoc = createNewXml(),
		//xmlSer=new XMLSerializer(),
	    newPI=xmlDoc.createProcessingInstruction("xml","version=\"1.0\" encoding=\"utf-8\""),
		xmlString,flow,flow_id,flow_code,flow_title,flow_seq,seq_id,type,title,accept_name,station_function,next_seq_id,true_next_seq_id,false_next_seq_id,is_risk,risk_desc,left,top,heigth,width,anticipate_day,anticipate_type;
		
	flow = xmlDoc.createElement("flow");
	flow_id = xmlDoc.createElement("flow_id");
	flow_code = xmlDoc.createElement("flow_code");
	flow_title = xmlDoc.createElement("flow_title");
	
	xmlDoc.appendChild(newPI);
	xmlDoc.appendChild(flow);
	flow.appendChild(flow_id);
	flow.appendChild(flow_code);
	flow.appendChild(flow_title);
	
	setValue(flow_id,g("flow_id").value);
	setValue(flow_code,g("flow_code").value);
	setValue(flow_title,g("flow_title").value);
	
	var obj = g("canvas").childNodes;
	for( var i=0,len=obj.length;i<len;i++ ){
		if(obj[i].nodeName!="PolyLine"){
			if(obj[i].getAttribute("cx")){
				break;
			}
			if( !obj[i].getAttribute("to") && !obj[i].getAttribute("from") ){
			alert("流程不全,请补全！");
			return false;
		}
			
			flow_seq = xmlDoc.createElement("flow_seq");
			seq_id = xmlDoc.createElement("seq_id");
			type = xmlDoc.createElement("type");
			title = xmlDoc.createElement("title");
			accept_name = xmlDoc.createElement("accept_name");
			station_function = xmlDoc.createElement("station_function");
			if(obj[i].getAttribute("type1").toLowerCase()=="j"){
				true_next_seq_id = xmlDoc.createElement("true_next_seq_id");
				false_next_seq_id = xmlDoc.createElement("false_next_seq_id");
				
				flow_seq.appendChild(true_next_seq_id);
				flow_seq.appendChild(false_next_seq_id);
				var to = obj[i].getAttribute("toLine").split(",");
				if(to.length!=2){ alert("判断过程有两分支，请补全！"); return false; }
				if( g(to[0]).getAttribute("judge") || g(to[1]).getAttribute("judge") ){}
				else{
					alert("判断过程两分支线，请设定条件！"); return false;
				}
				if( parseInt(g(to[0]).getAttribute("judge")) ){
					setValue(true_next_seq_id,g(to[0]).getAttribute("to"));
					setValue(false_next_seq_id,g(to[1]).getAttribute("to"));
					true_next_seq_id.setAttribute("point",obj[i].getAttribute("true_point"));
					false_next_seq_id.setAttribute("point",obj[i].getAttribute("false_point"));
				}else{
					setValue(true_next_seq_id,g(to[1]).getAttribute("to"));
					setValue(false_next_seq_id,g(to[0]).getAttribute("to"));
					true_next_seq_id.setAttribute("point",obj[i].getAttribute("true_point"));
					false_next_seq_id.setAttribute("point",obj[i].getAttribute("false_point"));
				}
				
			}else{
				next_seq_id = xmlDoc.createElement("next_seq_id");
				
				flow_seq.appendChild(next_seq_id);
				
				if( obj[i].getAttribute("to") ){
					setValue(next_seq_id,obj[i].getAttribute("to"));
					next_seq_id.setAttribute("point",obj[i].getAttribute("point"));
				}
			}
			is_risk = xmlDoc.createElement("is_risk");
			risk_desc = xmlDoc.createElement("risk_desc");
			left = xmlDoc.createElement("left");
			top = xmlDoc.createElement("top");
			//width = xmlDoc.createElement("width");
			//height = xmlDoc.createElement("height");
			
			anticipate_day = xmlDoc.createElement("anticipate_day");
			anticipate_type = xmlDoc.createElement("anticipate_type");
			
			flow.appendChild(flow_seq);
			flow_seq.appendChild(seq_id);
			flow_seq.appendChild(type);
			flow_seq.appendChild(title);
			flow_seq.appendChild(accept_name);
			flow_seq.appendChild(station_function);
			flow_seq.appendChild(is_risk);
			flow_seq.appendChild(risk_desc);
			flow_seq.appendChild(anticipate_day);
			flow_seq.appendChild(anticipate_type);
			flow_seq.appendChild(left);
			flow_seq.appendChild(top);
			//flow_seq.appendChild(width);
			//flow_seq.appendChild(height);			
			//var textObj = g(obj[i].getAttribute("textID"))
			//title.setAttribute("x", textObj.getAttribute("x") );
			//title.setAttribute("y", textObj.getAttribute("y") );
			//title.setAttribute("dx", textObj.getAttribute("dx") );
			
			setValue(seq_id,obj[i].getAttribute("id"));
			setValue(type,obj[i].getAttribute("type1"));
			setValue(title,obj[i].getAttribute("title"));
			setValue(accept_name,obj[i].getAttribute("accept_name"));
			setValue(station_function,obj[i].getAttribute("station_function"));
			setValue(is_risk,obj[i].getAttribute("is_risk"));
			setValue(anticipate_day,obj[i].getAttribute("anticipate_day"));
			setValue(anticipate_type,obj[i].getAttribute("anticipate_type"));
			if(obj[i].nodeName == "RoundRect"){
				setValue(left,parseInt(obj[i].style.left));
				setValue(top,parseInt(obj[i].style.top));
			}else if(obj[i].nodeName == "Oval"){
				setValue(left,parseInt(obj[i].style.left)+25);
				setValue(top,parseInt(obj[i].style.top)+25);	
			}else{
				setValue(left,parseInt(obj[i].style.left));
				setValue(top,parseInt(obj[i].style.top)+25);	
			}
		}
	}
	if(xmlDoc.xml){
		xmlString = xmlDoc.xml;
	}else{
		//xmlString = xmlSer.serializeToString(xmlDoc);
	}
	window.opener.document.getElementById("inFlowInfo").value = xmlString;//xml数据传回父窗口
	window.close();
  }
