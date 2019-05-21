//x(attrValue(getShape[i],"title","x")).y(attrValue(getShape[i],"title","y")).attr({dx:attrValue(getShape[i],"title","dx")})
function nodeValue(o,nodeName){
	if(o.getElementsByTagName(nodeName)[0]){
		if(o.getElementsByTagName(nodeName)[0].textContent){
			return o.getElementsByTagName(nodeName)[0].textContent;
		}else{
			return o.getElementsByTagName(nodeName)[0].text;	
		}
	}
}

function attrValue(o,nodeName,attrName){
	return o.getElementsByTagName(nodeName)[0].getAttribute(attrName);	
}



var g = function(id){ return document.getElementById(id); }
//obj:{"a":"11","b":22}
var addStyle = function(o,obj){
	if(!o) return false;
	if( (typeof obj) != "object" ) return false;
	for( var able in obj ){
		o.style[able] = obj[able];
	}
	
}

var addNode = function(o,obj){
	if(!o) return false;
	if( (typeof obj) != "object" ) return false;
	for( var able in obj ){
		o.setAttribute(able,obj[able]);
	}
	
}

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
	x = o.x();
	y = o.y();
	w = parseInt(o.attr("width"));
	h = parseInt(o.attr("height"));
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
		/*points = pro.attr("points").split(" ");
		
		for( var i=0,len=points.length;i<len;i++ ){
			var cp = points[i].split(",");
			pointArray.push([cp[0],cp[1]]);	
		}
		top = pointArray[2];
		right = pointArray[1];
		bottom = pointArray[0];
		left = pointArray[3];*/
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
		p1,p2,x1,x2,y1,y2,p=new Array;
	
	if( pocBegin.tagName=="RoundRect" || pocBegin.tagName=="Oval" ){
		x1 = parseInt(pocBegin.style.left);
		y1 = parseInt(pocBegin.style.top);
	}else if( pocBegin.tagName=="Shape" ){
		//x1 = parseInt(pocBegin.attr("getX"))-50;
		//y1 = parseInt(pocBegin.attr("getY"))-25;
	}
	
	if( pocEnd.tagName=="RoundRect" || pocEnd.tagName=="Oval" ){
		x2 = parseInt(pocEnd.style.left);
		y2 = parseInt(pocEnd.style.top);
	}else if( pocEnd.tagName=="Shape" ){
		//x2 = parseInt(pocEnd.attr("getX"))-50;
		//y2 = parseInt(pocEnd.attr("getY"))-25;
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
	p.push(p1[0]+"px");
	p.push(p1[1]+"px");
	p.push(p2[0]+"px");
	p.push(p2[1]+"px");
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

function loadXml(xml,canvas){
	var c = SVG(canvas),
		gLine = c.group().attr({"x":0,"y":0}),
		gText = c.group().attr({"x":0,"y":0}),
		gShape = c.group().attr({"x":0,"y":0}),
		marker = c.marker(),
		markerBlue = c.marker(),
		markerYellow = c.marker();
	var o_filter = document.createElementNS(SVG.ns, "filter");
	var o_feOffset = document.createElementNS(SVG.ns, "feOffset");
	var o_feGaussianBlur = document.createElementNS(SVG.ns, "feGaussianBlur");
	var o_feBlend = document.createElementNS(SVG.ns, "feBlend");
	o_feOffset.setAttribute("result","offOut");
	o_feOffset.setAttribute("in","SourceAlpha");
	o_feOffset.setAttribute("dx",6);
	o_feOffset.setAttribute("dy",6);
	o_feGaussianBlur.setAttribute("result","blurOut");
	o_feGaussianBlur.setAttribute("in","offOut");
	o_feGaussianBlur.setAttribute("stdDeviation",4);
	o_feBlend.setAttribute("in","SourceGraphic");
	o_feBlend.setAttribute("in2","blurOut");
	o_feBlend.setAttribute("mode","normal");
	o_filter.setAttribute("x",0);
	o_filter.setAttribute("y",0);
	o_filter.setAttribute("width","200%");
	o_filter.setAttribute("height","200%");
	o_filter.setAttribute("id","filter");
	o_filter.appendChild(o_feOffset);
	o_filter.appendChild(o_feGaussianBlur);
	o_filter.appendChild(o_feBlend);
	g("s1").appendChild(o_filter);
		
	marker.attr({"viewBox":"0 0 8 8","refX":0,"refY":4,"markerUnits":"strokeWidth","markerWidth":5,"markerHeight":5,"orient":"auto"});
	marker.path().attr({"d":"M 0 0 L 7 4 L 0 7 z"}).fill("#000");
		
	markerBlue.attr({"viewBox":"0 0 8 8","refX":0,"refY":4,"markerUnits":"strokeWidth","markerWidth":5,"markerHeight":5,"orient":"auto"});
	markerBlue.path().attr({"d":"M 0 0 L 7 4 L 0 7 z"}).fill("blue");
	
	markerYellow.attr({"viewBox":"0 0 8 8","refX":0,"refY":4,"markerUnits":"strokeWidth","markerWidth":5,"markerHeight":5,"orient":"auto"});
	markerYellow.path().attr({"d":"M 0 0 L 7 4 L 0 7 z"}).fill("#999");
	
	var xmlDoc;
	if(window.ActiveXObject){
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.loadXML(xml);
	}else if(window.DOMParser){
		parser=new DOMParser();
		xmlDoc=parser.parseFromString(xml,"text/xml");
	}  
	
	var getShape = xmlDoc.getElementsByTagName("flow_seq");
	for( var i=0,len=getShape.length;i<len;i++ ){
		switch( nodeValue(getShape[i],"type").toLowerCase() ){
			case "circle":
				var c = gShape.circle(50).cx(nodeValue(getShape[i],"left")).cy(nodeValue(getShape[i],"top"))
					  .attr({"id":nodeValue(getShape[i],"seq_id")}).stroke({width:2,color:"#000"}).fill("#fff").style({"fill-opacity":0});
				
				var t = gText.text( dealStr(nodeValue(getShape[i],"title")) )
					 .x(nodeValue(getShape[i],"left"))
					 .y(parseInt(nodeValue(getShape[i],"top"))+6)
					 .attr({dx:nodeValue(getShape[i],"left"),"id":i})
					 .font({family:"Arial",size:14,"text-anchor":"middle"});
				c.attr({"textID":t.attr("id"),"filter":"url(#filter)"});
				break;
				
			case "j":
				var p = gShape.polygon(drawPolygon([parseInt(nodeValue(getShape[i],"left")),parseInt(nodeValue(getShape[i],"top"))])).fill("#ccc")
					  .stroke({color:"#000",width:2}).style({"fill-opacity":0})
					  .attr({'transform':"","id":nodeValue(getShape[i],"seq_id"),
						                    "filter":"url(#filter)",
											"type1":nodeValue(getShape[i],"type"),
											"is_risk":nodeValue(getShape[i],"is_risk"),
											"anticipate_day":nodeValue(getShape[i],"anticipate_day"),
											"anticipate_type":nodeValue(getShape[i],"anticipate_type"),
											"risk_desc":nodeValue(getShape[i],"risk_desc"),
											"station_function":nodeValue(getShape[i],"station_function"),
											"accept_name":nodeValue(getShape[i],"accept_name"),
											"title":nodeValue(getShape[i],"title"),
											"getX":parseInt(nodeValue(getShape[i],"left")),
											"getY":parseInt(nodeValue(getShape[i],"top"))
					  });
				var t = gText.text( dealStr(nodeValue(getShape[i],"title")) )
					 .x(nodeValue(getShape[i],"left"))
					 .y(parseInt(nodeValue(getShape[i],"top"))+6)
					 .attr({dx:nodeValue(getShape[i],"left"),"id":i})
					 .font({family:"Arial",size:14,"text-anchor":"middle"});
				p.attr({"textID":t.attr("id")});
				break;
				
			default:
				var r = gShape.rect(100,50).x(nodeValue(getShape[i],"left")).y(nodeValue(getShape[i],"top"))
					  .stroke({color:"#000",width:2}).fill("#ccc").style({"fill-opacity":0})
					  .attr({rx:6,ry:6,"id":nodeValue(getShape[i],"seq_id"),
						               "filter":"url(#filter)",
									   "type1":nodeValue(getShape[i],"type"),
									   "is_risk":nodeValue(getShape[i],"is_risk"),
									   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
									   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
									   "risk_desc":nodeValue(getShape[i],"risk_desc"),
									   "station_function":nodeValue(getShape[i],"station_function"),
									   "accept_name":nodeValue(getShape[i],"accept_name"),
									   "title":nodeValue(getShape[i],"title")
							});

				var t = gText.text( dealStr(nodeValue(getShape[i],"title")) )
					 .x(parseInt(nodeValue(getShape[i],"left"))+5)
					 .y(parseInt(nodeValue(getShape[i],"top"))+32)
					 .attr({dx:parseInt(nodeValue(getShape[i],"left"))+50,"id":i})
					 .font({family:"Arial",size:14,"text-anchor":"middle"});
				r.attr({"textID":t.attr("id")});
				break;
		}
	}
	
	for( var i=0,len=getShape.length;i<len;i++ ){
		
		if(nodeValue(getShape[i],"next_seq_id")){
			
			var line1 = gLine.polyline().stroke({ color:"#000",width: 2 }).fill("#fff")
						.attr({'transform':"","marker-end":"url(#"+marker.attr("id")+")"});
			if(attrValue(getShape[i],"next_seq_id","point")){
				line1.attr({ "points":attrValue(getShape[i],"next_seq_id","point") });	
			}else{
				var p = formatLine( SVG.get(nodeValue(getShape[i],"seq_id")),SVG.get(nodeValue(getShape[i],"next_seq_id")) );
				line1.attr({ "points":p["p1"][0]+","+p["p1"][1]+" "+p["p2"][0]+","+p["p2"][1] });		
			}
			line1.attr({"from":nodeValue(getShape[i],"seq_id"),"to":nodeValue(getShape[i],"next_seq_id")});
			
			var from = SVG.get(nodeValue(getShape[i],"seq_id")),
				to = SVG.get(nodeValue(getShape[i],"next_seq_id"));
			if( from.attr("from") ){
				from.attr({"from":from.attr("from")+","+line1.attr("id")})
			}else{
				from.attr({"from":line1.attr("id")});
			}
			
			if( to.attr("to") ){
				to.attr({"to":to.attr("to")+","+line1.attr("id")})
			}else{
				to.attr({"to":line1.attr("id")});
			}
			
		}
		if(nodeValue(getShape[i],"true_next_seq_id")){
			var line2 = gLine.polyline().stroke({ color:"blue",width: 2 }).fill("#fff")
						.attr({'transform':"","judgeLine":1,"marker-end":"url(#"+markerBlue.attr("id")+")"});
			if(attrValue(getShape[i],"true_next_seq_id","point")){
				line2.attr({ "points":attrValue(getShape[i],"true_next_seq_id","point") });	
			}else{
				var p = formatLine( SVG.get(nodeValue(getShape[i],"seq_id")),SVG.get(nodeValue(getShape[i],"true_next_seq_id")) );
				line2.attr({ "points":p["p1"][0]+","+p["p1"][1]+" "+p["p2"][0]+","+p["p2"][1] });		
			}
			line2.attr({"from":nodeValue(getShape[i],"seq_id"),"to":nodeValue(getShape[i],"true_next_seq_id")});
			
			var from = SVG.get(nodeValue(getShape[i],"seq_id")),
				to = SVG.get(nodeValue(getShape[i],"true_next_seq_id"));
			if( from.attr("from") ){
				from.attr({"from":from.attr("from")+","+line2.attr("id")})
			}else{
				from.attr({"from":line2.attr("id")});
			}
			
			if( to.attr("to") ){
				to.attr({"to":to.attr("to")+","+line2.attr("id")})
			}else{
				to.attr({"to":line2.attr("id")});
			}
			
		}
		if(nodeValue(getShape[i],"false_next_seq_id")){
			var line3 = gLine.polyline().stroke({ color:"#999",width: 2 }).fill("#fff")
						.attr({'transform':"","judgeLine":0,"marker-end":"url(#"+markerYellow.attr("id")+")"});
			if(attrValue(getShape[i],"false_next_seq_id","point")){
				line3.attr({ "points":attrValue(getShape[i],"false_next_seq_id","point") });	
			}else{
				var p = formatLine( SVG.get(nodeValue(getShape[i],"seq_id")),SVG.get(nodeValue(getShape[i],"false_next_seq_id")) );
				line3.attr({ "points":p["p1"][0]+","+p["p1"][1]+" "+p["p2"][0]+","+p["p2"][1] });				
			}
			line3.attr({"from":nodeValue(getShape[i],"seq_id"),"to":nodeValue(getShape[i],"false_next_seq_id")});
			
			var from = SVG.get(nodeValue(getShape[i],"seq_id")),
				to = SVG.get(nodeValue(getShape[i],"false_next_seq_id"));
			if( from.attr("from") ){
				from.attr({"from":from.attr("from")+","+line3.attr("id")})
			}else{
				from.attr({"from":line3.attr("id")});
			}
			
			if( to.attr("to") ){
				to.attr({"to":to.attr("to")+","+line3.attr("id")})
			}else{
				to.attr({"to":line3.attr("id")});
			}
			
		}
	}
	
	SVG.get("s5").attr({"flow_id":xmlDoc.getElementsByTagName("flow_id")[0].textContent,
						"flow_code":xmlDoc.getElementsByTagName("flow_code")[0].textContent,
						"flow_title":xmlDoc.getElementsByTagName("flow_title")[0].textContent});
		
}

function tip(o){
	var left,top,type=new Array,is_risk,anticipate_day,anticipate_type=new Array,risk_desc,station_function,accept_name,title;
	type["u"] = "用户参与";
	type["j"] = "判断环节";
	type["d"] = "系统过程处理";
	anticipate_type = ["天","工作日","月","年","小时"];
	if(g("tip")){
		if(o.nodeName=="rect" ){
			left = parseInt(o.getAttribute("x"))+parseInt(o.getAttribute("width"));
			top = parseInt(o.getAttribute("y"))-2;
		}else if(o.nodeName=="polygon"){
			left = parseInt(o.getAttribute("getX"))+55;
			top = parseInt(o.getAttribute("getY"))-25;
		}else if(o.nodeName=="Shape"){
			left = parseInt(o.style.left)+55;
			top = parseInt(o.style.top);
		}else if(o.nodeName=="Oval"){
			return;
		}else{
			left = parseInt(o.style.left)+parseInt(o.style.width);
			top = parseInt(o.style.top)-2;
		}
		if(o.getAttribute("type1")) g("tip").innerHTML += "<p><span>环节类型：</span>"+type[o.getAttribute("type1").toLowerCase()]+"</p>";
		if(o.getAttribute("title")) g("tip").innerHTML += "<p><span>环节名称：</span>"+o.getAttribute("title")+"</p>";
		if(o.getAttribute("accept_name")) g("tip").innerHTML += "<p><span>办理人员：</span>"+o.getAttribute("accept_name")+"</p>";
		if(o.getAttribute("station_function")) g("tip").innerHTML += "<p><span>岗位职责：</span>"+o.getAttribute("station_function")+"</p>";
		if(o.getAttribute("is_risk")){
			g("tip").innerHTML += "<p><span>风险点岗位</span></p>";
			if(o.getAttribute("risk_desc")) g("tip").innerHTML += "<p><span>风险点描述：</span>"+o.getAttribute("risk_desc")+"</p>";
		}else{
			g("tip").innerHTML += "<p><span>非风险点岗位</span></p>";	
		}
		
		if(parseInt(o.getAttribute("anticipate_day"))){
			g("tip").innerHTML += "<p><span>办理时限：</span>"+o.getAttribute("anticipate_day")+anticipate_type[o.getAttribute("anticipate_type")]+"</p>";
		}else{
			g("tip").innerHTML += "<p><span>无办理时限</span></p>";	
		}
		g("tip").style.left = left+"px";
		g("tip").style.top = top+"px";
		g("tip").style.display="block";	
	}
}

function vmlXml(xml,canvas){
	var shape = new Array();
	shape["rect"] = '<v:RoundRect id="rect" style="cursor:pointer;position:absolute;width:100px;height:50px;left:0px;top:0px;background:#fff;" strokeColor="#000" strokeWeight="1px">' +
					'<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
              		'<v:TextBox id="text" style="display:block;width:90px; margin:8px auto 0;line-height:20px;text-align:center;color:#000;font-size:14px;"></v:TextBox>' +
              		'</v:RoundRect>';
	shape["polyline"] = '<v:PolyLine id="{id}" points="{pt}"style="position:absolute;z-index:10" filled="f" strokeWeight="1px" strokeColor="#000"><v:stroke EndArrow="Classic"></v:stroke></v:PolyLine>';
	shape["polygon"] = '<v:Shape id="{id}" fillcolor="#fff" strokecolor="#000" coordorigin="100 100" coordsize="1 1" style="cursor:pointer;position:absolute;top:0;left:0;height:1px;width:1px;" path="m 100,100 l 155,125 100,150 45,125 x e">'+
					   '<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
					   '<v:TextBox id="text" style="display:block;width:90px; margin:8px auto 0;line-height:20px;text-align:center;color:#000;font-size:14px;"></v:TextBox>'
					   '</v:Shape>';
	shape["circle"] = '<v:Oval id="circle" style="cursor:pointer;position:absolute;left:0;top:0;width:50px;height:50px;z-index:10"" strokecolor="#000" strokeweight="1px">' +
                	  '<v:Shadow on="T" type="single" color="#b3b3b3" offset="3px,3px"/>'+
                	  '<v:TextBox id="text" style="display:block;width:50px; margin:20px auto 0;line-height:20px;text-align:center;color:#000;font-size:14px;"></v:TextBox>' +
               	      '</v:Oval>';
	
	var xmlDoc;
	if(window.ActiveXObject){
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.loadXML(xml);
	}else if(window.DOMParser){
		parser=new DOMParser();
		xmlDoc=parser.parseFromString(xml,"text/xml");
	}  
	
	var canvas = g(canvas);
	var getShape = xmlDoc.getElementsByTagName("flow_seq");
	for( var i=0,len=getShape.length;i<len;i++ ){
		switch( nodeValue(getShape[i],"type").toLowerCase() ){
			case "circle":
				canvas.innerHTML += shape["circle"];
				addStyle( g("circle"),{"left":parseInt(nodeValue(getShape[i],"left"))-25+"px","top":parseInt(nodeValue(getShape[i],"top"))-25+"px" } );
				g("circle").setAttribute( "id",nodeValue(getShape[i],"seq_id") );
				g("text").setAttribute( "id","text"+i );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				break;
				
			case "j":
				canvas.innerHTML += shape["polygon"].replace("{id}", nodeValue(getShape[i],"seq_id"));
				addStyle( g(nodeValue(getShape[i],"seq_id")),{"left":nodeValue(getShape[i],"left")+"px","top":parseInt(nodeValue(getShape[i],"top"))-25+"px" } );
				g("text").setAttribute( "id","text"+i );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				addNode( g(nodeValue(getShape[i],"seq_id")),
						 {
						   "type":nodeValue(getShape[i],"type"),
						   "is_risk":nodeValue(getShape[i],"is_risk"),
						   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
						   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
						   "risk_desc":nodeValue(getShape[i],"risk_desc"),
						   "station_function":nodeValue(getShape[i],"station_function"),
						   "accept_name":nodeValue(getShape[i],"accept_name"),
						   "title":nodeValue(getShape[i],"title")
						 });
				
				break;
				
			default:
				canvas.innerHTML += shape["rect"];
				g("text").setAttribute( "id","text"+i );
				addStyle( g("rect"),{"left":nodeValue(getShape[i],"left")+"px","top":nodeValue(getShape[i],"top")+"px" } );
				g("rect").setAttribute( "id",nodeValue(getShape[i],"seq_id") );
				g("text"+i).innerHTML = nodeValue(getShape[i],"title");
				addNode( g(nodeValue(getShape[i],"seq_id")),
						 {
						   "type":nodeValue(getShape[i],"type"),
						   "is_risk":nodeValue(getShape[i],"is_risk"),
						   "anticipate_day":nodeValue(getShape[i],"anticipate_day"),
						   "anticipate_type":nodeValue(getShape[i],"anticipate_type"),
						   "risk_desc":nodeValue(getShape[i],"risk_desc"),
						   "station_function":nodeValue(getShape[i],"station_function"),
						   "accept_name":nodeValue(getShape[i],"accept_name"),
						   "title":nodeValue(getShape[i],"title")
						 });
									   
				break;
		}
	}
	
	var pointvalue = [],linePoine=[];
	for( var i=0,len=getShape.length;i<len;i++ ){
		
		if(nodeValue(getShape[i],"next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "line_" + i);
			if(attrValue(getShape[i],"next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"next_seq_id")) );
				pointvalue.push(p.join());
			}
			linePoine.push("line_" + i);
		}
		if(nodeValue(getShape[i],"true_next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "trueLine_" + i);
			if(attrValue(getShape[i],"true_next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"true_next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"true_next_seq_id")) );
				pointvalue.push(p.join());
			}
			linePoine.push("trueLine_" + i);
		}
		if(nodeValue(getShape[i],"false_next_seq_id")){
			canvas.innerHTML += shape["polyline"].replace("{id}", "falseLine_" + i);
			if(attrValue(getShape[i],"false_next_seq_id","point")){
				pointvalue.push(attrValue(getShape[i],"false_next_seq_id","point"));
			}else{
				var p = VMLformatLine( g(nodeValue(getShape[i],"seq_id")),g(nodeValue(getShape[i],"false_next_seq_id")) );
				pointvalue.push(p.join());
			}
			linePoine.push("falseLine_" + i);
		}
	}
	
	for (var i=0; i<pointvalue.length; i++) {
		if(g(linePoine[i])) g(linePoine[i]).points.value = pointvalue[i];
	}
	
}