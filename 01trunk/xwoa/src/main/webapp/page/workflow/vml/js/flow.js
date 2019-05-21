var $G = function(id){
	return document.getElementById(id);
};

var flow = flow || {};
flow.draw = flow.draw || {};
flow.analysisXML || {};
flow.model || {};

flow.model = {
	repeatDrawLine:function(b_o,e_o,line){
		var p = line.getAttribute("points");
		var pArr = p.split(" ");
		var len = pArr.length;
		first = pArr[0].split(",");
		last = pArr[len-1].split(",");
		prev = pArr[len-2].split(",");
		next = pArr[1].split(",");
		first[0] = parseInt(first[0]);
		first[1] = parseInt(first[1]);
		last[0] = parseInt(last[0]);
		last[1] = parseInt(last[1]);
		prev[0] = parseInt(prev[0]);
		prev[1] = parseInt(prev[1]);
		next[0] = parseInt(next[0]);
		next[1] = parseInt(next[1]);
		
		function compaePoint(){
			if(last[0] == prev[0]){
				if(last[1]>prev[1]){ pArr[len-1] = ""+last[0]+","+(last[1]-8)+"";}
				else{pArr[len-1] = ""+last[0]+","+(last[1]+8)+"";}
			}
			else if(last[1] == prev[1]){
				if(last[0]>prev[0])	{pArr[len-1] = ""+(last[0]-8)+","+last[1]+"";}
				else {pArr[len-1] = ""+(last[0]+8)+","+last[1]+"";}
			} 
			else{
				if(last[0]>prev[0]){
					if(last[1]>prev[1]) {pArr[len-1] = ""+(last[0]-8)+","+(last[1]-8)+"";}
					else {pArr[len-1] = ""+(last[0]-8)+","+(last[1]+8)+"";}
				}else {
					if(last[1]>prev[1]) {pArr[len-1] = ""+(last[0]+8)+","+(last[1]-8)+"";}
					else {pArr[len-1] = ""+(last[0]+8)+","+(last[1]+8)+"";}
				}	
			}
		}
		
		if(e_o.nodeName == "rect"){
			compaePoint();
		}
		if(e_o.nodeName == "circle"){
			var r = parseInt(e_o.getAttribute("r"));
			var x = parseInt(e_o.getAttribute("cx"))-r;
			var y = parseInt(e_o.getAttribute("cy"))-r;
			if(prev[0]<x-20){
				last[0] = x-8;
				last[1] = y+r;
			}else if( prev[0]>(20+x+2*r) ){
				last[0] = x+2*r+8;
				last[1] = y+r; 
			}else{
				if(prev[1]>y+2*r){
					last[0] = x+r;
					last[1] = y+2*r+8;
				}else{
					last[0] = x+r;
					last[1] = y-8;
				}
			}
			pArr[len-1] = ""+last[0]+","+last[1]+"";
		}
		if(b_o.nodeName == "circle"){
			var r = parseInt(b_o.getAttribute("r"));
			var x = parseInt(b_o.getAttribute("cx"))-r;
			var y = parseInt(b_o.getAttribute("cy"))-r;
			if(first[0]<x-20){
				first[0] = x;
				first[1] = y+r;
				
			}else if( first[0]>(20+x+2*r) ){
				last[0] = x+2*r;
				last[1] = y+r; 
			}else{
				if(y<next[1]<y+2*r){
					last[0] = x+2*r;
					last[1] = y+r;	
				}else{
					last[0] = x+r;
					last[1] = y+2*r;
				}
				if(next[1]>y+2*r){
					last[0] = x+r;
					last[1] = y+2*r;
				}else if(next[1]<y){
					last[0] = x+r;
					last[1] = y;
				}
			}
			pArr[0] = ""+last[0]+","+last[1]+"";	
		}
		if(b_o.getAttribute("nodetype") == "D"){
			var points = b_o.getAttribute("points").split(" ");
			var d1 = points[0].split(","),d3 = points[2].split(","),d2 = points[1].split(","),d4 = points[3].split(",");
			if(next[0]<d1[0]){ pArr[0] = ""+d1[0]+","+d1[1]+""; }
			else if(next[0]>d3[0]) { pArr[0] = ""+d3[0]+","+d3[1]+""; }
			else{
				if(next[1]>d4[1]){ pArr[0] = ""+d4[0]+","+d4[1]+""; }
				else {pArr[0] = ""+d2[0]+","+d2[1]+"";}
			}
		}
		if(e_o.getAttribute("nodetype") == "D"){
			var points = e_o.getAttribute("points").split(" ");
			var d1 = points[0].split(","),d3 = points[2].split(","),d2 = points[1].split(","),d4 = points[3].split(",");
			if(prev[0]<parseInt(d1[0])-10){ pArr[len-1] = ""+(parseInt(d1[0])-8)+","+parseInt(d1[1])+""; }
			else if(prev[0]>parseInt(d3[0])+10) { pArr[len-1] = ""+(parseInt(d3[0])+8)+","+parseInt(d3[1])+"";  }
			else{
				if(prev[1]>d4[1]){ pArr[len-1] = ""+parseInt(d4[0])+","+(parseInt(d4[1])+8)+""; }
				else {pArr[len-1] = ""+parseInt(d2[0])+","+(parseInt(d2[1])-8)+"";}
			}
		}
		line.setAttribute("points",pArr.join(" "));
	},
	
	dealText:function(text,num){
		var len = text.length,
		    s = new Array,
		    j = len%num==0?len/6:parseInt(len/num)+1;
		for(var i=0;i<j;i++){
			var k = (i+1)*num;
			var last = k>len?len:k;
			s.push(text.slice(i*num,last));
		}
		return s;
	}
};

//创建 svg 头部
flow.draw.createSVG = function(o){
	var SVG = false,  //判断是否支持svg
	    svgNS = false,
		svg;
	if (document.createElementNS) {
		svgNS = "http://www.w3.org/2000/svg";
		svg = document.createElementNS(svgNS, "svg");
		SVG = (svg.x != null);
	}
	
	//画带箭头的线 黑色
	function createArrow(){
		var o_marker = document.createElementNS(svgNS, "marker");
		var o_path = document.createElementNS(svgNS, "path");
		o_marker.setAttribute("id","arrow");
		o_marker.setAttribute("viewBox","0 0 8 8");
		o_marker.setAttribute("refX","0");
		o_marker.setAttribute("refY","4");
		o_marker.setAttribute("markerUnits","strokeWidth");
		o_marker.setAttribute("markerWidth","8");
		o_marker.setAttribute("markerHeight","8");
		o_marker.setAttribute("orient","auto");
		o_path.setAttribute("d","M 0 0 L 8 4 L 0 8 z");
		o_path.setAttribute("fill","#000");
		o_path.setAttribute("stroke","#000");
		o_marker.appendChild(o_path);
		return o_marker;
	};
	
	//画带箭头的线 红色
	function createArrowH(){
		var o_marker = document.createElementNS(svgNS, "marker");
		var o_path = document.createElementNS(svgNS, "path");
		o_marker.setAttribute("id","arrowH");
		o_marker.setAttribute("viewBox","0 0 8 8");
		o_marker.setAttribute("refX","0");
		o_marker.setAttribute("refY","4");
		o_marker.setAttribute("markerUnits","strokeWidth");
		o_marker.setAttribute("markerWidth","8");
		o_marker.setAttribute("markerHeight","8");
		o_marker.setAttribute("orient","auto");
		o_path.setAttribute("d","M 0 0 L 8 4 L 0 8 z");
		o_path.setAttribute("fill","#FF0000");
		o_path.setAttribute("stroke","#FF0000");
		o_marker.appendChild(o_path);
		return o_marker;
	};
	
	//画阴影	
	function createShadow(){
		var o_defs = document.createElementNS(svgNS, "defs");
		var o_filter = document.createElementNS(svgNS, "filter");
		var o_feGaussianBlur = document.createElementNS(svgNS, "feGaussianBlur");
		var o_feOffset = document.createElementNS(svgNS, "feOffset");
		o_feOffset.setAttribute("dx",3);
		o_feOffset.setAttribute("dy",3);
		o_feGaussianBlur.setAttribute("stdDeviation",3);
		o_filter.setAttribute("x",0);
		o_filter.setAttribute("y",0);
		o_filter.setAttribute("id","filter");
		o_filter.appendChild(o_feGaussianBlur);
		o_filter.appendChild(o_feOffset);
		o_defs.appendChild(o_filter);
		return o_defs;
	}
	if(SVG){
		svg.width = "100%";
		o.appendChild(svg);
		svg.appendChild(createArrow());
		svg.appendChild(createArrowH());
		svg.appendChild(createShadow());
	}
	return {"svgNS":svgNS,"svg":svg,"SVG":SVG};
};

flow.draw.createGUI = function(svg,svgNS,opt_options){
	var options = opt_options || {},
		nodeType = options.nodeType || '',  //所需画图的标签如  线 line 矩形 rect 圆 circle 不规则图形 等等
		beginX = options.x || 0, //开始画图时的起点 x 坐标 
		beginY = options.y || 0, //开始画图时的起点 y 坐标
		endX = options.x1 || 0, //开始画图时的尾点 x 坐标 
		endY = options.y1 || 0, //开始画图时的尾点 y 坐标
		width = options.width || 0, //宽度
		height = options.height || 0, //高度
		r = options.r || 0,//半径
		borderColor = options.borderColor || "#000", //边框颜色
		fontColor = options.fongColor || "#000", //字体颜色
		points = options.points || "0,0", //折线坐标
		arrow = options.arrow || '',   //箭头
		id = options.id || '',
		beginID = options.from || '',
		endID = options.to || '',
		text = options.text || '',
		GUI = options.GUI || '',
		t_x = options.t_x,
		t_y = options.t_y;
	
	//画线
	function createLine(){
		var o_line = document.createElementNS(svgNS, "line");
		o_line.setAttribute("shape-rendering", "auto");
		o_line.setAttribute("stroke-width", "1px");
		o_line.setAttribute("stroke", "#ff0000");
		o_line.setAttribute("stroke-linecap","round");
		o_line.setAttribute("x1", beginX);
    	o_line.setAttribute("y1", beginY);
    	o_line.setAttribute("x2", endX);
    	o_line.setAttribute("y2", endY);
		svg.appendChild(o_line);
	};
	
	//画阴影的矩形
	function createBackRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_shadow = document.createElementNS(svgNS, "rect");
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
		o_rect.setAttribute("zIndex", "2");
		o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("nodetype", nodeType);
		o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_rect.setAttribute("id", id);
		o_shadow.setAttribute("filter", "url(#filter)");
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
		
	};
	
	//画圆角矩形
	function createRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("rx", 10);
    	o_rect.setAttribute("ry", 10);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "#000");
		o_text.setAttribute("fill", "#000000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
		
	};
	
	//画一般矩形
	function createCommonRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_shadow = document.createElementNS(svgNS, "rect");
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
    	o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
    	o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
	};
	
	//画游离分支矩形
	function createColorRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_shadow = document.createElementNS(svgNS, "rect");
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","skyblue");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
    	o_rect.setAttribute("rx",10);
    	o_rect.setAttribute("ry",10);
    	o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
    	o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
	};
	
	//画汇聚节点矩形
	function createJuRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_line = document.createElementNS(svgNS, "polyline");
		var o_shadow = document.createElementNS(svgNS, "rect");
		var linePoints = beginX+","+(beginY+height/6)+" "+(beginX+width)+","+(beginY+height/6);
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
    	o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
    	o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		o_line.setAttribute("stroke-width", "1px");
		o_line.setAttribute("stroke", borderColor);
		o_line.setAttribute("fill","none");
		o_line.setAttribute("id", "line_"+id);
		o_line.setAttribute("points",linePoints);
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
		svg.appendChild(o_line);
	};
	//画并行节点矩形
	function createBingRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_shadow = document.createElementNS(svgNS, "rect");
		var o_line = document.createElementNS(svgNS, "polyline");
		var linePoints = beginX+","+(beginY+height-height/5)+" "+(beginX+width)+","+(beginY+height-height/5);
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
    	o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
    	o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		o_line.setAttribute("stroke-width", "1px");
		o_line.setAttribute("stroke", borderColor);
		o_line.setAttribute("fill","none");
		o_line.setAttribute("id", "line_"+id);
		o_line.setAttribute("points",linePoints);
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
		svg.appendChild(o_line);
	};
	
	//画多实例节点矩形
	function createMultiRect(){
		var o_rect = document.createElementNS(svgNS, "rect");
		var o_text = document.createElementNS(svgNS, "text");
		var o_line = document.createElementNS(svgNS, "polyline");
		var linePoints = beginX+","+(beginY+height-height/5)+" "+(beginX+width)+","+(beginY+height-height/5);
		var o_shadow = document.createElementNS(svgNS, "rect");
		o_rect.setAttribute("stroke-width", "1px");
		o_rect.setAttribute("stroke", borderColor);
		o_rect.setAttribute("fill","#fff");
		o_rect.setAttribute("x", beginX);
    	o_rect.setAttribute("y", beginY);
    	o_shadow.setAttribute("x", beginX);
    	o_shadow.setAttribute("y", beginY);
    	o_shadow.setAttribute("width", width);
    	o_shadow.setAttribute("height", height);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_rect.setAttribute("width", width);
    	o_rect.setAttribute("height", height);
		o_rect.setAttribute("id", id);
		o_rect.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+5);
		o_text.setAttribute("y", beginY+height/2);
		o_text.appendChild(document.createTextNode(text));
		o_line.setAttribute("stroke-width", "1px");
		o_line.setAttribute("stroke", borderColor);
		o_line.setAttribute("stroke-dasharray", 4);
		o_line.setAttribute("fill","none");
		o_line.setAttribute("id", "line_"+id);
		o_line.setAttribute("points",linePoints);
		svg.appendChild(o_shadow);
		svg.appendChild(o_rect);
		svg.appendChild(o_text);
		svg.appendChild(o_line);
	};
	
	// 画圆
	function createCircle(){
		var o_circle = document.createElementNS(svgNS, "circle");
		var o_text = document.createElementNS(svgNS, "text");
		var o_shadow = document.createElementNS(svgNS, "circle");
		o_circle.setAttribute("stroke-width", "1px");
		o_circle.setAttribute("stroke", "#ff0000");
		o_circle.setAttribute("fill","#fff");
		o_circle.setAttribute("r", r);
		o_circle.setAttribute("id", id);
		o_circle.setAttribute("cx", beginX+r);
    	o_circle.setAttribute("cy", beginY+r);
		o_circle.setAttribute("zIndex", "2");
		o_shadow.setAttribute("r", r);
		o_shadow.setAttribute("cx", beginX+r);
    	o_shadow.setAttribute("cy", beginY+r);
		o_shadow.setAttribute("fill", "#333");
		o_shadow.setAttribute("filter", "url(#filter)");
		o_circle.setAttribute("nodetype", nodeType);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX+r/2);
		o_text.setAttribute("y", beginY+r);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_shadow);
		svg.appendChild(o_circle);
		svg.appendChild(o_text);
		
	};
	
	//画折线
	function createPolyline(){
		var o_polyline = document.createElementNS(svgNS, "polyline");
		var t = flow.model.dealText(text,6),o_text = new Array;
		o_polyline.setAttribute("stroke-width", "1px");
		o_polyline.setAttribute("stroke", borderColor);
		o_polyline.setAttribute("fill","none");
		o_polyline.setAttribute("id", id);
		o_polyline.setAttribute("points",points);
		o_polyline.setAttribute("marker-end","url("+arrow+")");
		o_polyline.setAttribute("from",beginID);
		o_polyline.setAttribute("to",endID);
		o_polyline.setAttribute("nodetype", nodeType);
		
		for(var i=0;i<t.length;i++){
			o_text[i] = document.createElementNS(svgNS, "text");
			o_text[i].setAttribute("stroke-width", "1px");
			o_text[i].setAttribute("stroke", "none");
			o_text[i].setAttribute("fill", "#000");
			o_text[i].setAttribute("font-size", "9pt");
			o_text[i].setAttribute("x", parseInt(t_x));
			o_text[i].setAttribute("y", parseInt(t_y)+10+i*14);
			o_text[i].appendChild(document.createTextNode(t[i]));
			svg.appendChild(o_text[i]);
		}
		svg.appendChild(o_polyline);
		flow.model.repeatDrawLine($G(beginID),$G(endID),o_polyline);
		
	};
	
	//画棱形
	function createPolygon(){
		var o_polygon = document.createElementNS(svgNS, "polygon");
		var t = flow.model.dealText(text,5),o_text = new Array;
		var o_shadow = document.createElementNS(svgNS, "polygon");
		o_polygon.setAttribute("stroke-width", "1px");
		o_polygon.setAttribute("fill","#fff");
		o_polygon.setAttribute("stroke", borderColor);
		o_polygon.setAttribute("id", id);
		o_polygon.setAttribute("points", ""+(beginX)+","+(beginY+20)+" "+(beginX+50)+","+(beginY)+" "+(beginX+100)+","+(beginY+20)+" "+(beginX+50)+","+(beginY+40)+"");
		o_polygon.setAttribute("nodetype", nodeType);
		o_shadow.setAttribute("points", ""+(beginX)+","+(beginY+20)+" "+(beginX+50)+","+(beginY)+" "+(beginX+100)+","+(beginY+20)+" "+(beginX+50)+","+(beginY+40)+"");
		o_shadow.setAttribute("filter", "url(#filter)");
		svg.appendChild(o_shadow);
		svg.appendChild(o_polygon);	
		for(var i=0;i<t.length;i++){
			o_text[i] = document.createElementNS(svgNS, "text");
			o_text[i].setAttribute("for", id);
			o_text[i].setAttribute("stroke-width", "1px");
			o_text[i].setAttribute("stroke", "none");
			o_text[i].setAttribute("fill", "#000000");
			o_text[i].setAttribute("font-size", "9pt");
			o_text[i].setAttribute("x", beginX+20);
			if(t.length >1) o_text[i].setAttribute("y", beginY+15+i*14);
			else o_text[i].setAttribute("y", beginY+20);
			o_text[i].appendChild(document.createTextNode(t[i]));
			svg.appendChild(o_text[i]);
		}
		
		
	};
	
	//六边形
	function createPolygonL(){
		var o_polygon = document.createElementNS(svgNS, "polygon");
		var o_text = document.createElementNS(svgNS, "text");
		var point = new Array;
		bieginX = parseInt(beginX),beginY = parseInt(beginY);
		p = height/2;
		point[0] = beginX+","+beginY;
		point[1]=(beginX+width-2*p)+","+beginY;
		point[2]=(beginX+width-p)+","+(beginY+p);
		point[3]=(beginX+width-2*p)+","+(beginY+2*p);
		point[4]=beginX+","+(beginY+2*p);
		point[5]=(beginX-p)+","+(beginY+p);
		o_polygon.setAttribute("stroke-width", "1px");
		o_polygon.setAttribute("fill","#fff");
		o_polygon.setAttribute("stroke", borderColor);
		o_polygon.setAttribute("id", id);
		o_polygon.setAttribute("points", point.join(" "));
		o_polygon.setAttribute("nodetype", nodeType);
		o_text.setAttribute("for", id);
		o_text.setAttribute("stroke-width", "1px");
		o_text.setAttribute("stroke", "none");
		o_text.setAttribute("fill", "#000000");
		o_text.setAttribute("font-size", "9pt");
		o_text.setAttribute("x", beginX-p/2);
		o_text.setAttribute("y", beginY+p);
		o_text.appendChild(document.createTextNode(text));
		svg.appendChild(o_polygon);	
		svg.appendChild(o_text);
	};
	
	//选择节点属性画图
	switch(nodeType){
		case "C":
			d_GUI = createCommonRect();
			return d_GUI;
			break;
		case "R":
			d_GUI = createColorRect();
			return d_GUI;
			break;
		case "A":case "F":
			d_GUI = createCircle();
			return d_GUI;
			break;	
		case "B":case "":
			d_GUI = createBackRect();
			return d_GUI;
			break;
		case "E":
			d_GUI = createJuRect();
			return d_GUI;
			break;	
		case "D":
			d_GUI = createPolygon();
			return d_GUI;
			break;	
		case "H":
			d_GUI = createBingRect();
			return d_GUI;
			break;
		case "G":
			d_GUI = createMultiRect();
			return d_GUI;
			break;	
		default:
			if(GUI == "polyline"){
				d_GUI = createPolyline();
				return d_GUI;
			};
			break;
	};
};

flow.draw.createFlow = {
	redrawLine:function(option_flow){
		var options = option_flow || {},
			id = options.id,
			inststate = parseInt(options.inststate);
		if(inststate>0){
			$G(id).setAttribute("marker-end","url(#arrowH)");
			$G(id).setAttribute("stroke", "#FF0000");
		}	
	},
	
	redrawGUI:function(option_GUI){
		var options = option_GUI || {},
			id = options.id,
			inststate = options.inststate;
		switch(inststate){
			case "complete":
				if($G(id)){
					$G(id).setAttribute("stroke", "#0000ff");
					var elem = document.getElementsByTagName("text");
					var len = elem.length;
					for(var i=0;i<len;i++){
						if(elem[i].getAttribute("for")==id){
							elem[i].setAttribute("fill","#0000ff");		
						}
					}
					if($G("line_"+id)){
						$G("line_"+id).setAttribute("stroke", "#0000ff");
					}
				}
				break;
			case "waiting":
				if($G(id)){
					$G(id).setAttribute("stroke", "#FF0000");
					var elem = document.getElementsByTagName("text");
					var len = elem.length;
					for(var i=0;i<len;i++){
						if(elem[i].getAttribute("for")==id){
							elem[i].setAttribute("fill","#0000ff");	
						}
					}
					if($G("line_"+id)){
						$G("line_"+id).setAttribute("stroke", "#FF0000");
					}
				}
				break;
			case "ready":
				break;
			default:
				break;
		}		
	}
};

flow.analysisXML = {
	getXML:function(xml){
		var xmlDoc;
		//读取xml文件的方式
		/*if(window.ActiveXObject){
			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = false;
			xmlDoc.load(xml);
		}else if(document.implementation && document.implementation.createDocument){
			var xmlHttp = new window.XMLHttpRequest();
			xmlHttp.open("GET",xml,false);
			xmlHttp.send(null);
			xmlDoc = xmlHttp.responseXML;
		}*/ 
		// 读取类xml字符串的方式
		if(window.ActiveXObject){
			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.loadXML(xml);
		}else if(window.DOMParser){
			parser=new DOMParser();
  			xmlDoc=parser.parseFromString(xml,"text/xml");	
		}  
		return xmlDoc;
	}
};


function point(p){
	var p = p ,new_p="";
	var pArr = p.split(",");
	var len = pArr.length;
	for(var i=0;i<len;i++){
		pArr[i] = Math.round(parseFloat(pArr[i])*1.333);
	}
	
	for(var i=0;i<len;i++){
		if(i!=0 && i%2!=0){
			if(i == len-1) new_p += pArr[i];	
			else new_p += pArr[i]+" ";
		}else{
			if(i == len-1) new_p += pArr[i];
			else new_p += pArr[i]+",";	
		}	
	}
	return new_p;
};

function readXML(xmlURL,xmlFlowURL){
	var o = document.getElementById("canvas");
	var stage = flow.draw.createSVG(o);
	var xmlDoc=flow.analysisXML.getXML(xmlURL),
	    xmlFlow = flow.analysisXML.getXML(xmlFlowURL),
	    xmlShap=xmlDoc.getElementsByTagName("Nodes")[0],
	    xmlLine=xmlDoc.getElementsByTagName("Transitions")[0],
		xmlStep = xmlFlow.getElementsByTagName("Steps")[0],
		xmlProc = xmlFlow.getElementsByTagName("Procs")[0],
	    number_GUI=xmlShap.childNodes.length,
		number_Line=xmlLine.childNodes.length,
		number_Proc = xmlProc.childNodes.length;
	var pointX = new Array,pointY = new Array,z_x,z_y;
	if(!stage.SVG) return null; 
	
	//画图
	for(var i=0; i<number_GUI; i++){
		var node = xmlShap.childNodes[i].getElementsByTagName("VMLProperties")[0];
		var base = xmlShap.childNodes[i].getElementsByTagName("BaseProperties")[0];
		pointX.push(parseInt(node.getAttribute("x")));
		pointY.push(parseInt(node.getAttribute("y")));
		flow.draw.createGUI(stage.svg,stage.svgNS,{
								nodeType:base.getAttribute("nodetype"),
								x:parseInt(node.getAttribute("x")),
								y:parseInt(node.getAttribute("y")),
								r:parseInt(node.getAttribute("width"))/2,
								width:parseInt(node.getAttribute("width")),
								height:parseInt(node.getAttribute("height")),
								id:base.getAttribute("id"),
								text:base.getAttribute("name")
							});
	}
	//画连接线
	for(var j=0; j<number_Line; j++){
		var node = xmlLine.childNodes[j].getElementsByTagName("VMLProperties")[0];
		var base = xmlLine.childNodes[j].getElementsByTagName("BaseProperties")[0];
		var textPos = xmlLine.childNodes[j].getElementsByTagName("LabelProperties")[0];
		flow.draw.createGUI(stage.svg,stage.svgNS,{
								nodeType:"none",
								points:point(node.getAttribute("points")),
								arrow:"#arrow",
								id:base.getAttribute("id"),
								from:base.getAttribute("from"),
								to:base.getAttribute("to"),
								GUI:"polyline",
								text:base.getAttribute("name"),
								t_x:textPos.getAttribute("x"),
								t_y:textPos.getAttribute("y")
							});
	}
	//对已完成的流程进行颜色变化
	for(var k=0; k<number_Line;k++){
		var step = xmlStep.childNodes[k];
		flow.draw.createFlow.redrawLine({
						id:step.getAttribute("id"),
						inststate:step.getAttribute("inststate")
					});
	}

	for(var k=0; k<number_Proc;k++){
		var proc = xmlProc.childNodes[k];
		flow.draw.createFlow.redrawGUI({
						id:proc.getAttribute("id"),
						inststate:proc.getAttribute("inststate")
					});
	}
	
	
	// 整体画面的大小
	var len = pointX.length;
	for(var i=0;i<len;i++){
		for(var j=i+1;j<len;j++){
			if(pointX[i]<pointX[j]){
				z_x = pointX[i];
				pointX[i] = pointX[j];
				pointX[j] = z_x;
			}
			if(pointY[i]<pointY[j]){
				z_y = pointY[i];
				pointY[i] = pointY[j];
				pointY[j] = z_y;
			}
		}
	}
	//o.style.width = pointX[0]+100+"px";
	o.style.width = document.documentElement.clientWidth+"px";
	o.style.height = pointY[0]+100+"px";
}
  function mouseDown(e){
	  var e = window.event || e, target = e.srcElement || e.target;
	  var objId,color;
	  if(target.nodeName=="text"){
		   objId = target.getAttribute("for");
		   color = target.getAttribute("fill")?target.getAttribute("fill"):"#0000ff";
	  }
	  else{
		  objId = target.getAttribute("id");	
		  color = target.getAttribute("stroke")?target.getAttribute("stroke"):"#0000ff";
	  }
	  if(target.nodeName=="svg"){
			 return false;
		 }
	 if(target.nodeName!="circle"&&(color.toLowerCase()=="#ff0000"||color.toLowerCase()=="#0000ff")){		  
		  $("#nodetable").empty();
		  $.ajax({
			  type:"POST",
			  url:path+"/sampleflow/sampleFlowManager!viewFlowNodeInfo.do?flowInstId="+flowid+"&nodeId="+objId,
			  dataType:"json",
			  async: false,
			  success:function(data){	
				  if(data.instance!=null){
					  debugger;
					  //$("#nodetable").empty();
					  var h = parseInt(document.body.scrollTop)+200;
					  //$("#shadow").height(document.body.scrollHeight).show();
					  $("#NodeView").css({"top":h+"px"}).show();
					  //$("body").css({"height":document.body.clientHeight,"overflow":"hidden"});
					  $("#nodebt").html(data.nodename);
					  var instances=eval(data.instance);
					  	var tasks=eval(instances[0].task);
					  for(var i=0;i<instances.length;i++){
						  if (instances[i].isTimer=='T') {
							  $("#nodetable").append('<tr><td>当前状态为<span style="color:red;">'+instances[i].state+'</span>，使用时间'+instances[i].usetime+'，剩余时间'+instances[i].timelimit+'</td></tr>');
							}
						  else{
							  $("#nodetable").append('<tr><td>当前状态为<span style="color:red;">'+instances[i].state+'</span></td></tr>');
						  }
						  if(instances[i].unitname==null){
							  $("#nodetable").append('<tr><td>创建于<span style="color:0000ff;">'+instances[i].createtime+'</span></td></tr>');
						  }
						  else
							  $("#nodetable").append('<tr><td>创建于<span style="color:0000ff;">'+instances[i].createtime+'</span>('+instances[i].unitname+')</td></tr>');	
						  if(instances[i].action!=null){
							  var actions=eval(instances[i].action);
							  var j=actions.length;
							  //$("#nodetable").append('<tr><th rowspan="'+j+'">详细信息</th><td>'+actions[0].username+'</td><th>操作时间</th><td>'+actions[0].actiontime +'</td><th>操作类型</th><td>'+actions[0].actiontype+'</td></tr>');
							  if(j>=1){
								  for(var m=0;m<j;m++){
									  if(m==j-1){
										  $("#nodetable").append('<tr><td style="border-bottom:1px solid #aaa;">'+actions[m].username+'于<span style="color:0000ff;">'+actions[m].actiontime+'</span>'+actions[m].actiontype+'</td></tr>');
									  }else{
										  $("#nodetable").append('<tr><td>'+actions[m].username+'于<span style="color:0000ff;">'+actions[m].actiontime+'</span>'+actions[m].actiontype+'</td></tr>');
									  }
								  }
							  }
						  }
						  else if (instances[i].task!=null){
							  var tasks=eval(instances[i].task);
							  var j=tasks.length;
							  var arry = new Array();
							  for(var m=0;m<j;m++){
								  arry.push(tasks[m].username);
							  }
							  $("#nodetable").append('<tr><td style="border-bottom:1px solid #aaa;">可操作人员：'+arry.join("，")+'</td></tr>');
						  }
					  }
					  moveTip( g("NodeView"),g("nodebt") );
				  }
				  
			  },
			  error:function(){
				  $("#shadow").hide();
				  $("#NodeView").hide();
				  $("#nodetable").empty();
				  alert("失败");
			  }
		  });
	  }
  }
