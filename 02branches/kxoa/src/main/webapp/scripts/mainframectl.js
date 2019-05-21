/**
 * 控制mainframe的布局
 */

function changeTop(obj,imgpath) {
    var fss= window.parent.document.getElementsByTagName("frameset");
	var rows = fss[0].rows;
	
	var imgs = document.getElementsByTagName("img");
	
    var topHeight = rows.substr(0,2);
	var otherRows = rows.substr(2,rows.length) ;

	if (topHeight == "82") {
		fss[0].rows = "31"+otherRows;	
		obj.src = imgpath + "/arrow_s.gif";	
		for(var i=0;i<imgs.length; i++)
		{
			imgs[i].src = imgs[i].src.replace("_l.jpg","_s.jpg");
		}		
	} else {
		fss[0].rows = "82"+otherRows;	
		obj.src = imgpath +"/arrow_l.gif";	
		for(var i=0;i<imgs.length; i++)
		{
			imgs[i].src = imgs[i].src.replace("_s.jpg","_l.jpg");
		}
	}
}


function hideLeft(obj,imgpath) {
    var fss= window.parent.document.getElementsByTagName("frameset");
	var cols = fss[1].cols;

    var defaultCols = "140,9,*";
	var hiddenCols = "0,9,*" ;

	if (cols == defaultCols) {
		fss[1].cols = hiddenCols;	
		obj.src = imgpath + "/right_button.jpg";	
		obj.alt = "打开菜单"	;
	} else {
		fss[1].cols = defaultCols;
		obj.src = imgpath + "/left_button.jpg";
		obj.alt = "关闭菜单";			
	}
}

function hideRight(obj,imgpath) {
    var fss= window.parent.document.getElementsByTagName("frameset");
	var cols = fss[1].cols;
    var defaultCols = "*,9,140";
	var hiddenCols = "*,9,0" ;

	if (cols == defaultCols) {
		fss[1].cols = hiddenCols;	
		obj.src = imgpath + "/left_button.jpg";	
		obj.alt = "打开菜单"	;
	} else {
		fss[1].cols = defaultCols;
		obj.src = imgpath + "/right_button.jpg";
		obj.alt = "关闭菜单";			
	}
}

function hideFix(obj,imgpath) {
    var fss= window.parent.document.getElementsByTagName("frameset");
	var cols = fss[1].cols;
    var defaultCols = "*,140,9,875,*";
	var hiddenCols = "*,0,9,1015,*" ;

	if (cols == defaultCols) {
		fss[1].cols = hiddenCols;	
		obj.src = imgpath + "/left_button.jpg";	
		obj.alt = "打开菜单"	;
	} else {
		fss[1].cols = defaultCols;
		obj.src = imgpath + "/right_button.jpg";
		obj.alt = "关闭菜单";			
	}
}