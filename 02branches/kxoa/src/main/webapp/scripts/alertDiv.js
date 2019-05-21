
var _get = function (id) {
	return document.getElementById(id);
};

//字符空格处理
var trim = function (str) {
	return str.replace(/^\s+|\s+$/g, "");
};
	
/**
 * 设置弹出DIV样式，并使背景灰化
 */
function setAlertStyle(id,cHeight,isTM){
	var myAlert = document.getElementById(id);
	myAlert.style.display = "block";
	myAlert.style.position = "absolute";
	myAlert.style.top =  (cHeight||document.body.scrollTop)+20+"px";
	myAlert.style.left = "50%";
	//myAlert.style.marginTop = "-165px";
	myAlert.style.marginLeft = "-300px";
	mybg = document.createElement("div");
	mybg.setAttribute("id","mybg");
	if(isTM === undefined){
		mybg.style.background = "#000";
	}else{
		mybg.style.background = "#fff";
	}
	
	mybg.style.width = "100%";
	mybg.style.height = document.body.scrollHeight;
	mybg.style.position = "absolute";
	mybg.style.top = "0";
	mybg.style.left = "0";
	mybg.style.zIndex = "500";
	mybg.style.opacity = "0.3";
	mybg.style.filter = "Alpha(opacity=30)";
	document.body.appendChild(mybg);
	//document.body.style.overflow = "hidden";
}

//无背景
function setAlertStyleTemp(id, css){
	var myAlert = document.getElementById(id);
	myAlert.style.display = "block";
	myAlert.style.position = "absolute";
	myAlert.style.top =  (css && css.top) ? document.body.scrollTop+css.top+"px" : document.body.scrollTop+20+"px";
	myAlert.style.left = (css && css.left) ? css.left : "50%";
	//myAlert.style.marginTop = "-165px";
	myAlert.style.marginLeft = "-300px";
	/*mybg = document.createElement("div");
	mybg.setAttribute("id","mybg");
	mybg.style.background = "#000";
	mybg.style.width = "100%";
	mybg.style.height = document.body.scrollHeight;
	mybg.style.position = "absolute";
	mybg.style.top = "0";
	mybg.style.left = "0";
	mybg.style.zIndex = "500";
	mybg.style.opacity = "0.3";
	mybg.style.filter = "Alpha(opacity=30)";
	document.body.appendChild(mybg);*/
	//document.body.style.overflow = "hidden";
}


/**
 * 关闭DIV弹出框
 */
function closeAlert(id)
{
	var myAlert = document.getElementById(id);
	//alert(myAlert);
	myAlert.style.display = "none";
	mybg.style.display = "none";
	document.body.style.overflow = "auto";
}

function closeAlertTemp(id)
{
	var myAlert = document.getElementById(id);
	//alert(myAlert);
	myAlert.style.display = "none";
	//mybg.style.display = "none";
	document.body.style.overflow = "auto";
}
