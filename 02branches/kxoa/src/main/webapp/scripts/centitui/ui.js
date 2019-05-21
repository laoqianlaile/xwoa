function initEnv() {
	$("body").append(CentitUI.frag["CentitUIFrag"]);

	if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
		try {
			document.execCommand("BackgroundImageCache", false, true);
		}catch(e){}
	}
	//清理浏览器内存,只对IE起效
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}
	$(window).resize(function(){
		initLayout();
		$(this).trigger("resizeGrid");
	});

	var ajaxbg = $("#background,#progressBar");
	ajaxbg.hide();
	/*$(document).ajaxStart(function(){
		ajaxbg.show();
	}).ajaxStop(function(){
		ajaxbg.hide();
	});*/
	
	
	
	if ($.taskBar) $.taskBar.init();
	navTab.init();
	if ($.fn.switchEnv) $("#switchEnvBox").switchEnv();
	if ($.fn.navMenu) $("#navMenu").navMenu();
		
	setTimeout(function(){
		initLayout();
		initUI();
		$("#leftside").jBar({minW:150, maxW:700});
		// navTab styles
		var jTabsPH = $("div.tabsPageHeader");
		jTabsPH.find(".tabsLeft").hoverClass("tabsLeftHover");
		jTabsPH.find(".tabsRight").hoverClass("tabsRightHover");
		jTabsPH.find(".tabsMore").hoverClass("tabsMoreHover");
		
		// 菜单滚动条
		$(".accordionContent").niceScroll({cursorcolor:"#0178BA"});
	}, 10);
	
	
	
}
function initLayout() {
	var iContentW = $(window).width() - (CentitUI.ui.sbar ? $("#sidebar").width() + 10 : 10);
	var iContentH = $(window).height() - $("#header").height() - 34;
	if($("#layout").width() != $(window).width())
		iContentW = $("#layout").width() - (CentitUI.ui.sbar ? $("#sidebar").width() + 10 : 10);
	if ($(".tabBar li .select").attr("title") != "首页" && $(".tabBar li .select").attr("title") != "学习园地")
		$("#container").width(iContentW);
	var h = $(".tabBar li .select").attr("title") == "首页" ? (iContentH + 34) : iContentH + 8;
	$("#container .tabsPageContent").height(h).find("[layoutH]").layoutH();
	//左栏的高度改为默认形式
	$("#sidebar, #sidebar_s .collapse, #splitBar, #splitBarProxy").height(iContentH + 10);
	$("#taskbar").css({top: iContentH + $("#header").height() + 5, width:$(window).width()});
}

function initUI(_box){
	var $p = $(_box || document);

	//tables
	$("table.table", $p).jTable();
	
	// css tables
	$('table.list', $p).cssTable();

	//auto bind tabs
	$("div.tabs", $p).each(function(){
		var $this = $(this);
		var options = {};
		options.currentIndex = $this.attr("currentIndex") || 0;
		options.eventType = $this.attr("eventType") || "click";
		$this.tabs(options);
	});

	$("ul.tree", $p).jTree({
		alwaysOpen: false
	});
	$('div.accordion', $p).each(function(){
		var $this = $(this);
		$this.accordion({fillSpace:$this.attr("fillSpace"),alwaysOpen:true,active:0});
	});
	
	$(":button.checkboxCtrl, :checkbox.checkboxCtrl", $p).checkboxCtrl($p);
	
	if ($.fn.combox) $("select.combox",$p).combox();
	
	if ($.fn.xheditor) {
		$("textarea.editor", $p).each(function(){
			var $this = $(this);
			var op = {html5Upload:false, skin: 'vista',tools: $this.attr("tools") || 'full'};
			var upAttrs = [
				["upLinkUrl","upLinkExt","zip,rar,txt"],
				["upImgUrl","upImgExt","jpg,jpeg,gif,png"],
				["upFlashUrl","upFlashExt","swf"],
				["upMediaUrl","upMediaExt","avi"]
			];
			
			$(upAttrs).each(function(i){
				var urlAttr = upAttrs[i][0];
				var extAttr = upAttrs[i][1];
				
				if ($this.attr(urlAttr)) {
					op[urlAttr] = $this.attr(urlAttr);
					op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
				}
			});
			
			$this.xheditor(op);
		});
	}
	
	if ($.fn.uploadify) {
		$(":file[uploader]", $p).each(function(){
			var $this = $(this);
			var options = {
				uploader: $this.attr("uploader"),
				script: $this.attr("script"),
				cancelImg: $this.attr("cancelImg"),
				queueID: $this.attr("fileQueue") || "fileQueue",
				fileDesc: $this.attr("fileDesc") || "*.jpg;*.jpeg;*.gif;*.png;*.pdf",
				fileExt : $this.attr("fileExt") || "*.jpg;*.jpeg;*.gif;*.png;*.pdf",
				folder	: $this.attr("folder"),
				auto: true,
				multi: true,
				onError:uploadifyError,
				onComplete: uploadifyComplete,
				onAllComplete: uploadifyAllComplete
			};
			if ($this.attr("onComplete")) {
				options.onComplete = CentitUI.jsonEval($this.attr("onComplete"));
			}
			if ($this.attr("onAllComplete")) {
				options.onAllComplete = CentitUI.jsonEval($this.attr("onAllComplete"));
			}
			if ($this.attr("scriptData")) {
				options.scriptData = CentitUI.jsonEval($this.attr("scriptData"));
			}
			$this.uploadify(options);
		});
	}
	
	// init styles
	$("input[type=text], input[type=password], textarea", $p).addClass("textInput").focusClass("focus");

	$("input[readonly], textarea[readonly]", $p).addClass("readonly");
	$("input[disabled=true], textarea[disabled=true]", $p).addClass("disabled");

	$("input[type=text]", $p).not("div.tabs input[type=text]", $p).filter("[alt]").inputAlert();

	//Grid ToolBar
	$("div.panelBar li, div.panelBar", $p).hoverClass("hover");

	//Button
	$("div.button", $p).hoverClass("buttonHover");
	$("div.buttonActive", $p).hoverClass("buttonActiveHover");
	
	//tabsPageHeader
	$("div.tabsHeader li, div.tabsPageHeader li, div.accordionHeader, div.accordion", $p).hoverClass("hover");
	
	$("div.panel", $p).jPanel();

	//validate form
	$("form.required-validate", $p).each(function(){
		$(this).validate({
			focusInvalid: false,
			focusCleanup: true,
			errorElement: "span",
			ignore:".ignore",
			invalidHandler: function(form, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					var message = CentitUI.msg("validateFormError",[errors]);
					alertMsg.error(message);
				} 
			}
		});
	});

	if ($.fn.datepicker){
		$('input.date', $p).each(function(){
			var $this = $(this);
			var opts = {};
			if ($this.attr("format")) opts.pattern = $this.attr("format");
			if ($this.attr("yearstart")) opts.yearstart = $this.attr("yearstart");
			if ($this.attr("yearend")) opts.yearend = $this.attr("yearend");
			$this.datepicker(opts);
		});
	}

	// navTab
	$("a[target=navTab],button[target=navTab]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			CentitUI.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || CentitUI.msg("alertSelectMsg"));
				return false;
			}
			//if(tabid != "main") setCookie($this,external,tabid,url,title);
			navTab.openTab(tabid, url,{title:title, fresh:fresh, external:external});
			event.preventDefault();
		});
		
		
	});
	
		
	
	//dialogs
	$("a[target=dialog],button[target=dialog]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var rel = $this.attr("rel") || "_blank";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w) options.width = w;
			if (h) options.height = h;
			options.max = eval($this.attr("max") || "false");
			options.mask = eval($this.attr("mask") || "false");
			options.maxable = eval($this.attr("maxable") || "true");
			options.minable = eval($this.attr("minable") || "true");
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");
			options.drawable = eval($this.attr("drawable") || "true");
			options.close = eval($this.attr("close") || "");
			options.param = $this.attr("param") || "";

			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			CentitUI.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || CentitUI.msg("alertSelectMsg"));
				return false;
			}
			$.pdialog.open(url, rel, title, options);
			
			return false;
		});
		
	});
	
	
	
	$("a[target=ajax],button[target=ajax]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var rel = $this.attr("rel");
			if (rel) {
				var $rel = $("#"+rel);
				$rel.loadUrl($this.attr("href"), {}, function(){
					$rel.find("[layoutH]").layoutH();
				});
			}

			event.preventDefault();
		});
	});
	
	$("div.pagination", $p).each(function(){
		var $this = $(this);
		$this.pagination({
			targetType:$this.attr("targetType"),
			rel:$this.attr("rel"),
			totalCount:$this.attr("totalCount"),
			numPerPage:$this.attr("numPerPage"),
			pageNumShown:$this.attr("pageNumShown"),
			currentPage:$this.attr("currentPage")
		});
	});
	
	// CentitUI.ajax.js
	if ($.fn.ajaxTodo) $("a[target=ajaxTodo],button[target=ajaxTodo]", $p).ajaxTodo();
	if ($.fn.CentitUIExport) $("a[target=CentitUIExport]", $p).CentitUIExport();

	if ($.fn.lookup) $("a[lookupGroup]", $p).lookup();
	if ($.fn.multLookup) $("[multLookup]:button", $p).multLookup();
	if ($.fn.suggest) $("input[suggestFields]", $p).suggest();
	if ($.fn.itemDetail) $("table.itemDetail", $p).itemDetail();
	if ($.fn.selectedTodo) $("a[target=selectedTodo]", $p).selectedTodo();
	if ($.fn.pagerForm) $("form[rel=pagerForm]", $p).pagerForm({parentBox:$p});
	
	// 这里放其他第三方jQuery插件...
	
	var $this = $(this);
	$('.Wdate', _box).each(function() {
		var options = $(this).data('option');
		options = $.extend({readOnly:true, skin:'ext'}, options);
		
		$(this).click(function() {
			WdatePicker(options);
		});
	});
	
	$('.Wtime', _box).each(function() {
		var options = $(this).data('option');
		options = $.extend({readOnly:true, skin:'ext', dateFmt:"yyyy-MM-dd HH:mm:ss"}, options);
		
		$(this).click(function() {
			WdatePicker(options);
		});
	});
	
	
	if ($.fn.uploadify) {
		$("input[type=file]").each(function() {
			$.myUpload.init.call(this);
		});
	}
	//$("#firstPage").height(parseInt($("#firstPage").height())+7);
}


