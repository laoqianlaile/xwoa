function MakeTopMenu()
{
	// 考虑将来扩展到frame任意定位菜单，需要指定document和document.body
	var menuDoc=document;
	var menuBody=$("#top-side");
	var menuDiv=$("#page",menuBody);
	
	if(menuDiv.length==0)
	{
		menuDiv=$("<body onload='MM_preloadImages('image/nav1_1.gif')'><ul id='webmenu' class='first-menu'></ul></body>");
		$(menuBody).append(menuDiv);
	}
}

var i=0;

function AddTopMenu(selfMenuID,selfMenuText,selfMenusrc,parentMenuID,srcTarget)
{
	i++;
	
	// 考虑将来扩展到frame任意定位菜单，需要指定document和document.body
	var menuDoc=document;
	var menuBody=$("#top-side");
	
	var menuPad=$("#webmenu",menuBody);
	if(menuPad.length==0)
	{
		MakeTopMenu();
		menuPad=$("#webmenu",menuBody);
	}

	var strInnerMenu; // 拼接菜单的html串
	var parentMenu=$("\"#menu_"+parentMenuID+"\"",menuBody);
	
	if(parentMenu.length==0)  // 一级菜单
	{				
		strInnerMenu="<li id='menu_"+selfMenuID+"' cla='menu-box' ><a href='#'  onmouseout='MM_swapImgRestore()' onmouseover=MM_swapImage('Image"+i+"','','',"+i+") >"+selfMenuText+"</a>";
		
		menuPad.append(strInnerMenu);
	}
	else if(parentMenu.attr("cla")=="menu-box")  // 二级菜单
	{	
		
		strInnerMenu="<li id='menu_"+selfMenuID+"'><a href='"+selfMenusrc+"' target='"+srcTarget+"' class='arrow'>"+selfMenuText+"</a></li>";
		
		var S_ul=$("\"#ul_"+parentMenuID+"\"",menuBody);	

		if(S_ul.length==0)
		{			
			S_ul=$("<ul id='ul_"+parentMenuID+"' class='second-menu'></ul>");			
			parentMenu.append(S_ul);			
		}
		S_ul.append(strInnerMenu);
		
	}
	else  // 三级以下菜单
	{
		
		strInnerMenu="<li id='menu_"+selfMenuID+"'><a href='"+selfMenusrc+"' target='"+srcTarget+"'>"+selfMenuText+"</a></li>";
		//strInnerMenu="<li id='sub2'><a href='"+selfMenusrc+"' target='"+srcTarget+"'>"+selfMenuText+"</a></li>";
		var T_ul=$("\"#ul_"+parentMenuID+"\"",menuBody);
		if(T_ul.length==0)
		{
			//T_ul=$("<ul id='ul_"+parentMenuID+"'></ul>");
			T_ul=$("<ul id='ul_"+parentMenuID+"' class='third-menu'></ul>");
			parentMenu.append(T_ul);	
			//parentMenu.addClass("fold-close");
			//parentMenu.find("span:first").attr("id","T_span");
		}
		T_ul.append(strInnerMenu);
	}	

}





function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
 
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
 
function MM_swapImage() { //v3.0 
	
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
     
   


