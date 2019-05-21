var CreatedOKLodop7766=null;

function getLodop(oOBJECT,oEMBED){
/**************************
  本函数根据浏览器类型决定采用哪个页面元素作为Lodop对象：
  IE系列、IE内核系列的浏览器采用oOBJECT，
  其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED,
  如果页面没有相关对象元素，则新建一个或使用上次那个,避免重复生成。
  64位浏览器指向64位的安装程序install_lodop64.exe。
**************************/
        var strHtmInstall="打印控件未安装,是否现在下载安装？\u000d注意:安装后请刷新页面或重新进入。";
        var strHtmUpdate="打印控件需要升级,是否现在下载安装？\u000d注意:安装后请刷新页面或重新进入。";
        var strHtmFireFox="\u000d（如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）";
        var strHtmChrome="\u000d(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）";
        var LODOP;		
	try{	
	     //=====判断浏览器类型:===============
	     var isIE	 = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
	     var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
	     //=====如果页面有Lodop就直接使用，没有则新建:==========
	     if (oOBJECT!=undefined || oEMBED!=undefined) { 
               	 if (isIE) 
	             LODOP=oOBJECT; 
	         else 
	             LODOP=oEMBED;
	     } else { 
		 if (CreatedOKLodop7766==null){
          	     LODOP=document.createElement("object"); 
		     LODOP.setAttribute("width",0); 
                     LODOP.setAttribute("height",0); 
		     LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");  		     
                     if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA"); 
		     else LODOP.setAttribute("type","application/x-print-lodop");
		     document.documentElement.appendChild(LODOP); 
	             CreatedOKLodop7766=LODOP;		     
 	         } else 
                     LODOP=CreatedOKLodop7766;
	     };
	     //=====判断Lodop插件是否安装过，没有安装或版本过低就提示下载安装:==========
	     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
	             if (navigator.userAgent.indexOf('Chrome')>=0){
	            	 if(navigator.userAgent.indexOf('64')>=0){
	            		 lodopUninstallWarn(strHtmInstall+strHtmChrome,64);
	            	 }else{
	            		 lodopUninstallWarn(strHtmInstall+strHtmChrome,32);
	            	 }
	             } else if (navigator.userAgent.indexOf('Firefox')>=0){
	            	 if(navigator.userAgent.indexOf('64')>=0){
	            		 lodopUninstallWarn(strHtmInstall+strHtmFireFox,64);
	            	 }else{
	            		 lodopUninstallWarn(strHtmInstall+strHtmFireFox,32);
	            	 }
	             } else if(is64IE) 
	            	 lodopUninstallWarn(strHtmInstall,64); 
	             else if (isIE)   
	            	 lodopUninstallWarn(strHtmInstall,32);   
	             else {
	            	 if(navigator.userAgent.indexOf('64')>=0){
	            		 lodopUninstallWarn(strHtmInstall,64);
	            	 }else{
	            		 lodopUninstallWarn(strHtmInstall,32);
	            	 }
	             }
	             return LODOP;
	     } else 
	     if (LODOP.VERSION<"6.1.9.8") {
	             if (is64IE) lodopUninstallWarn(strHtmInstall,64); else
	             if (isIE) lodopUninstallWarn(strHtmInstall,32); else{
	            	 if(navigator.userAgent.indexOf('64')>=0){
	            		 lodopUninstallWarn(strHtmInstall+strHtmChrome,64);
	            	 }else{
	            		 lodopUninstallWarn(strHtmInstall+strHtmChrome,32);
	            	 }
	             }
	    	     return LODOP;
	     };
	     //=====如下空白位置适合调用统一功能(如注册码、语言选择等):====	     


	     //============================================================	     
	     return LODOP; 
	} catch(err) {
	     if (is64IE)	
	    	 lodopUninstallWarn(strHtmInstall,64);else
	    		 lodopUninstallWarn(strHtmInstall,32);
	     return LODOP; 
	};
}

/**
 * lodop插件没有安装提出警告
 * @param msg 消息
 * @param version 版本
 * @author lay
 */
function lodopUninstallWarn(msg,version){
	if(confirm(msg)){
		   //如果是32位
		   if(version==32){
			   window.location.href=ctx3rdJS+"/lodop/install_lodop32.exe";
		   }else if(version==64){
			   window.location.href=ctx3rdJS+"/lodop/install_lodop64.exe";
		   }
	   }
}
