(function($){
	var IdPrefix = "menu_tab_";
	
	function _getTabIndex(jq, tab) {
		if (!tab) {
			tab = jq.tabs('getSelected');
		}
		
		if (typeof tab == 'string') {
			var panel = jq.find('#'+IdPrefix+tab);
			return jq.tabs('getTabIndex', panel);
		}
		else if (typeof tab == 'object') {
			return jq.tabs('getTabIndex', tab);
		}
		
		return -1;
	} 
	
	function _getTabPanel(jq, tab) {
		if (tab == undefined) {
			return jq.tabs('getSelected');
		}
		
		if (typeof tab == 'string') {
			return jq.find('#'+IdPrefix+tab);
		}
		else if (typeof tab == 'number') {
			return jq.tabs('getTab', tab);
		}
	}
	
	function reload(jq, tab) {
		var tab = _getTabPanel(jq, tab);
		if (!tab) return;
		
		$(tab).find('iframe:first').contents()[0].location.reload();
	}
	
	function close(jq, tab) {
		var tab = _getTabPanel(jq, tab);
		if (!tab) return;
		
		var index = jq.tabs('getTabIndex', tab);
		var closable = tab.panel('options').closable;
		if (closable) {
			jq.tabs('close', index);
		}
	}
	
	function closeAll(jq, tab) {
		var tabs = jq.tabs('tabs').length;
		
		for (var i=tabs-1; i>=0; i--) {
			var tab = _getTabPanel(jq, i);
			var closable = tab.panel('options').closable;
			if (closable) {
				jq.tabs('close', jq.tabs('getTabIndex', tab));
			}
		}
	}
	
	function closeOthers(jq, tab) {
		var tabs = jq.tabs('tabs').length;
		var index = _getTabIndex(jq, tab);
		
		for (var i=tabs-1; i>=0; i--) {
			var tab = _getTabPanel(jq, i);
			var closable = tab.panel('options').closable;
			if (index !== i && closable) {
				jq.tabs('close', jq.tabs('getTabIndex', tab));
			}
		}
	}
	
	function open(jq, tab) {
		
		var index = _getTabIndex(jq, tab.id);
		
		// 已经打开
		if (index > -1) {
			jq.tabs('select', index);
		}
		else {
			jq.tabs('add',{
				id: IdPrefix + tab.id,
			    title: tab.text,
			    content: Mustache.render(Template["TEMPLATE_IFRAME"], tab),
			    closable: tab.closable === false ? false : true
			});
			
//			$('#' + 'menu_iframe_' + tab.id).autoHeight();
		}
		
	}

	function init(menuTab, options) {
		menuTab = $(menuTab);
	
		menuTab.tabs({
			onContextMenu: function(e, title, index) {
				menuTab.tabs('select', index);
			},
			onSelect: function(title, index) {
				
				var tab = $(this).tabs('getTab', index);
				var id = tab.panel('options').id;
				
				if (id) {
					id = id.substr(9)
					
					$('#main_menu').centitMenu('clean');
					$('#main_menu').centitMenu('mark', id);
				}
			},
			onAdd: options.onAdd,
			onClose: options.onClose
		});
	
		
		$(document).on('click', 'a.menu-tab-link', function(e) {
			e.preventDefault();
			
			var link = $(this);
			
			open(menuTab, {
				id: link.attr('rel'),
				text: link.text() || link.attr('title'),
				url: link.attr('href')
			});
		});
	}
	
	$.fn.menuTab = function(options, param){
		if (typeof options == 'string'){
			return $.fn.menuTab.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'menuTab');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'menuTab', {
					options: $.extend({}, $.fn.centitMenu.defaults, $.fn.centitMenu.parseOptions(this), options),
					menuTab: $(this).addClass('centit-menu-tab'),
					tabs: []
				});
				
				init(this, options);
			}
			
		});
	};
	
	$.fn.menuTab.methods = {
		open: function(jq, tab) {
			open(jq, tab);
		},
		reload: function(jq, tab) {
			reload(jq, tab);
		},
		close: function(jq, tab) {
			close(jq, tab);
		},
		closeAll: function(jq, tab) {
			closeAll(jq, tab);
		},
		closeOthers: function(jq, tab) {
			closeOthers(jq, tab);
		}
	};
	
	$.fn.menuTab.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target, [
			'width','height',
			{fit:'boolean',border:'boolean',animate:'boolean',multiple:'boolean',selected:'number'}
		]));
	};
	
	$.fn.menuTab.defaults = {
		width: 'auto',
		height: 'auto',
		fit: true,
		border: false,
		animate: true,
		multiple: false,
		selected: 0,
		
		onAdd: function(title, index){},
		onClose: function(title, index){}
	};
})(jQuery)