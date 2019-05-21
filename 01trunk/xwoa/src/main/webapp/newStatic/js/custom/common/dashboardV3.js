// 首页打开链接时对应的跳转及左侧菜单展示方式
function openMenu(obj,tabid, url,type){
    $("#layout").removeClass("improveStyle");
	var $this = $(obj);

	$("#ul_YGJG>li>div>div.first_collapsable").removeClass("first_collapsable").addClass("first_expandable");
	$("#ul_YGJG>li>div>div.collapsable").removeClass("collapsable").addClass("expandable");
	navTab.closeAllTab();
	
	var title = $this.attr("title") || $this.text();
	var tabName = $this.attr("rel") || "_blank";
	var fresh = eval($this.attr("fresh") || "true");
	navTab.openTab(tabName, url,{title:title, fresh:fresh, external:external});
	
	if(tabid == "wdtz"){
		type == type?type:"";
		showSelectedMenu("YGJGRCBG", "GGZY", type, "通知");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid == "ldrc"){
		showSelectedMenu("YGJGGRBG", "GRBGRCAP", "GRBGLDRCAP", "领导日程");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == "wdsw"){
		
		showSelectedMenu("YGJGGWLZ", "GWLZSWGL", "SWGLSWDB", "收文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid == "wdfw"){
		
		showSelectedMenu("YGJGGWLZ", "GWLZFWGL", "FWGLFWDB", "发文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="txl"){//通讯录
		
		showSelectedMenu("YGJGGRBG", "GRBGFZBG", "GRBGGRTXL", "通讯录");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == 'zlk'){
		showSelectedMenu("YGJGGRBG", "GRBGFZBG", "UNITSHAREFILE", "资料库");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=="swdj"){// 收文登记
		
		showSelectedMenu("YGJGGWLZ", "GWLZSWGL", "SWGLSWDJ", "收文登记");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="fwdj"){//发文登记
		
		showSelectedMenu("YGJGGWLZ", "GWLZFWGL", "FWGLFWDJ", "发文登记");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="hydj"){//会议登记
		
		showSelectedMenu("YGJGGRBG", "HYSGL_NEW", "HYSSYDJ", "会议登记");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=="fqtx"){//发起提醒
		
		showSelectedMenu("YGJGRCBG", "TXSJ", "DFSTX", "发起提醒");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid=="fwdb"){//发文待办
		
		showSelectedMenu("YGJGGWLZ", "GWLZFWGL", "FWGLFWDB", "发文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="zxfwdb"){//主席发文待办
		
		showSelectedMenu("YGJGGWLZ", "ZXGWLZFWGL", "ZXFWGLFWDB", "发文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="qbdb"){
		showSelectedMenu("YGJGNBSX", "RCBGQBGL", "QBDB", "签报待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.three'));
	}else if(tabid=="dcdb"){
		showSelectedMenu("YGJGGRBG", "GRBGDBCB", "DBHF", "督办催办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=="swdb"){//收文待办
		showSelectedMenu("YGJGGWLZ", "GWLZSWGL", "SWGLSWDB", "收文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="zxswdb"){//主席收文待办
		
		showSelectedMenu("YGJGGWLZ", "ZXGWLZSWGL", "ZXSWGLSWDB", "收文待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid=="hydb"){//会议待办
		
		showSelectedMenu("YGJGGRBG", "HYSGL_NEW", "HYSSYDB", "会议待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=="dwbl"){//待我办理
		
		var menu1, menu2, menu3, mainMenuObj;
		if('发文' == type){
			menu1 = "YGJGGWLZ";
			menu2 = "GWLZFWGL";
			menu3 = "FWGLFWDB";
			mainMenuObj = '.tabBar li.four';
		}else if('收文' == type){
			menu1 = "YGJGGWLZ";
			menu2 = "GWLZSWGL";
			menu3 = "SWGLSWDB";
			mainMenuObj = '.tabBar li.four';
		}else if('会议申请' == type){
			menu1 = "YGJGGRBG";
			menu2 = "HYSGL_NEW";
			menu3 = "HYSSYDB";
			mainMenuObj = '.tabBar li.to';
		}else{
			menu1 = "YGJGGRBG";
			menu2 = "GRBGGRDB";
			menu3 = "GRBGDBSX";
			mainMenuObj = '.tabBar li.to';
		}
		showSelectedMenu(menu1, menu2, menu3, "待我办理");
		cancelAllSelectedOptions();
		addSelectAttribute($(mainMenuObj));
	}else if(tabid == 'wdtx'){  // 未读提醒
		
		showSelectedMenu("YGJGRCBG", "TXSJ", "WDTX", "未读提醒");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid == 'yfstx'){
		showSelectedMenu("YGJGRCBG", "TXSJ", "YFSTX", "已发送提醒");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid == 'fsdx'){
		showSelectedMenu("YGJGRCBG", "MESSAGEGL", "MESSAGE", "发送短信");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid == 'nbzx'){ // 内部资讯
		var menu3;
		if('通知' ==  type){
			menu3 = "TZGG_GGZY";
		}else if('公告' == type){
			menu3 = "BULLETIN_VIEW";
		}else{
			menu3 = "NEWS_GGZY";
		}
		showSelectedMenu("YGJGRCBG", "GGZY", menu3, "");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.five'));
	}else if(tabid == 'wdyj'){
		showSelectedMenu("YGJGGRBG", "GRBGGRYJ", "WDSJX", "我的邮箱");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == 'sjx'){
		showSelectedMenu("YGJGGRBG", "GRBGGRYJ", "SJX", "我的邮箱");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == 'wjgfw'){
		showSelectedMenu('ZJC','YGJGWJG','WJGWJSB', "文件上报");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == 'wjgsw'){
		showSelectedMenu('ZJC','YGJGWJG','WJGWJSJX', "文件接收");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid == "wdlw"){
		
		showSelectedMenu("YGJGGWLZ", "", "", "未读待办");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.four'));
	}else if(tabid == "jwbl"){
		showSelectedMenu("YGJGGRBG", "GRBGGRDB", "GRBGBJCK", "办件查看");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));	
	}else if(tabid=="sqdj"){
		showSelectedMenu("YGJGNBSX", "NBSQBL", "NBSXSXDJ", "事项登记");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.three'));	
	}else if(tabid=='qbdj'){
		showSelectedMenu("YGJGNBSX", "RCBGQBGL", "QBDJ", "签报登记");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.three'));	
	}else if(tabid=='fsyj'){
		showSelectedMenu("YGJGGRBG", "GRBGGRYJ", "FJX", "发送邮件");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));	
	}else if(tabid=='grrc'){
		showSelectedMenu("YGJGGRBG", "GRBGRCAP", "GRBGGRRCAP", "个人日程");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));	
	}else if(tabid=='gwsq'){
		showSelectedMenu("YGJGGRBG", "GRBGGRDB", "RCBGGWSQ", "公文授权");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='fdx'){
		showSelectedMenu("YGJGRCBG", "MESSAGEGL", "MESSAGE", "发短信");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='ftz'){
		showSelectedMenu("YGJGRCBG", "GGZY", type, "发通知");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='fgg'){
		showSelectedMenu("YGJGRCBG", "GGZY", type, "发公告");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='fhd'){
		showSelectedMenu("YGJGRCBG", "GGZY", type, "发活动");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='gwgd'){
		showSelectedMenu("YGJGGWLZ", "GWLZXGGN", "SWGD", "公文归档");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}else if(tabid=='gjss'){
		showSelectedMenu("YGJGGRBG", "GRBGFZBG", "GRBGGJSS", "高级搜索");
		cancelAllSelectedOptions();
		addSelectAttribute($('.tabBar li.to'));
	}
	
	
	
}

// 将当前元素取消选中
function cancelAllSelectedOptions(){
	
	$('.tabBar li').each(function(){
		var $this = $(this);
		if($this.hasClass('select')){
			$this.removeClass('select');
			$(this).find('a').removeClass('select');		
			return;
		}
	});
}

// 将指定元素加上选中类型
function addSelectAttribute(ele){
	
	ele.addClass('select');
	ele.find('a').addClass('select');
}

// 按照指定的菜单层级展开
function showSelectedMenu(menu1,menu2,menu3,menu1Name){
	menuShow();
	$('.toggleCollapse h2').html(menu1Name);
	hideMenu();
	$('#menu_'+menu1).show();
	if (!$('#ul_'+menu1).is(':visible'))
		$("#menu_" + menu1 + ">div>a").click();
	$('#menu_' + menu1 + '>div').hide();
	
	setTimeout(function() {
		if (!$('#ul_'+menu2).is(':visible'))
			$("#menu_" + menu2 + ">div>a").click();
	},200);
	setTimeout(function() {
		$('#menu_' + menu3 + '>div>a').addClass('visited');
	},300);
}